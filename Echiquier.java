
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Echiquier extends JFrame /**implements ActionListener, MouseListener, MouseMotionListener**/{

    JPanel plateau;
    JPanel grille;
    JPanel sideNoir;
    JPanel sideBlanc;

    //Attributs utilisés par tous les panels
    LinkedList<Piece> piecesBlancs;
    LinkedList<Piece> piecesNoirs;
    LinkedList<Piece> cimBlancs;
    LinkedList<Piece> cimNoirs;
    boolean tour = true;
    int tourInt = tour ? 1 : 0; // ligne qui converti un boolean en int : true envoie 1 false envoie 0
    boolean finie = false;
    // --------------------- //

    //Attributs utilisés par la grille
    LinkedList<Piece>[] ech = new LinkedList[2]; // tableau de liste en 0 noirs en 1 blancs (true -> 1 et false -> 0);
    LinkedList<Piece>[] cim = new LinkedList[2];
    LinkedList<Piece> totPieces = new LinkedList<Piece>();

    LinkedList<Coordonnee> coups; //Liste de coordonnees ou on peut se deplacer
    LinkedList<Coordonnee> eat;  //Liste de coordonnees ou on peut manger

    Piece PieceSelect;

    // --------------------- //

    //Attributs utilisés par les sides

    JPanel cimetiereNoir;
    JPanel cimetiereBlanc;
    JPanel [] cimetieres = new JPanel [2];

    // --------------------- //

    /** CONSTRUCTEUR **/

    public Echiquier() {

    /** Init de la fenetre **/
        int larg = 1400;
        int haut = 800 + 27;

        setSize(larg, haut);
        setLocationRelativeTo(null);
        setTitle("Game");
        setResizable(false);
        setLayout(null);

    /** Init de l'image du plateau **/

        plateau = new JPanel();
        int largP = 760;
        plateau.setBounds((getWidth() - largP) / 2, (getHeight() - largP) / 2, largP, largP);
        plateau.setLayout(null);

    /** Init de la grille et des attributs **/

        initGrille(); //toute la partie avec les pieces qui bougent

    /** Init des side et des attributs **/

        initSideNoir(); initSideBlanc(); //cotés
        cimetieres[0] = cimetiereNoir;
        cimetieres[1] = cimetiereBlanc;

        //Image plateau
        JLabel imgPlateau = new JLabel(new ImageIcon("150.png"));
        imgPlateau.setBounds(0, 0, largP, largP);

        plateau.add(grille);
        plateau.add(imgPlateau);

        add(sideNoir);
        add(sideBlanc);
        add(plateau);

        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Init de la grille (ancien constructeur de grille)
    public void initGrille(){

        int largG = 688;

        piecesBlancs = init(true);
        piecesNoirs = init(false);
        cimBlancs = new LinkedList<Piece>();
        cimNoirs = new LinkedList<Piece>();

        majTot();

        ech[0] = piecesNoirs;
        ech[1] = piecesBlancs;
        cim[0] = cimNoirs;
        cim[1] = cimBlancs;

        grille = new JPanel();
        grille.setBounds(36, 36, largG, largG);
        grille.setOpaque(false);
        grille.setLayout(null);

        grille.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent e) {
                mousePressedGrille(e);
            }
            public void mouseReleased(MouseEvent e){
                mouseReleasedGrille(e);
            }
        });

        grille.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e){
                mouseDraggedGrille(e);
            }
        });

        affGrille();
    }

    //Init du side gauche (ancien constructeur de Side)
    public void initSideNoir(){
        sideNoir = new JPanel();
        sideNoir.setLocation(0,0);
        sideNoir.setSize(320, 800);
        sideNoir.setLayout(null);

        JLabel fond = new JLabel(new ImageIcon("Side_Noir.png"));
        fond.setBounds(0,0,320,800);

        cimetiereNoir = new JPanel();
        cimetiereNoir.setBounds(168,187,145,426);
        cimetiereNoir.setOpaque(false);
        cimetiereNoir.setLayout(null);

        sideNoir.add(cimetiereNoir);
        affSide(1);
        sideNoir.add(fond);
    }

    //Init du side droit
    public void initSideBlanc(){
        sideBlanc = new JPanel();
        sideBlanc.setLocation(1080,0);
        sideBlanc.setSize(320, 800);
        sideBlanc.setLayout(null);

        JLabel fond = new JLabel(new ImageIcon("Side_Noir.png"));
        fond.setBounds(0,0,320,800);

        cimetiereBlanc = new JPanel();
        cimetiereBlanc.setBounds(168,187,145,426);
        cimetiereBlanc.setOpaque(false);
        cimetiereBlanc.setLayout(null);

        sideBlanc.add(cimetiereBlanc);
        affSide(0);
        sideBlanc.add(fond);
    }

    /** METHODES INTERACTIONS **/

    public void mousePressedGrille(MouseEvent e) {
        if(!finie) {
            try {
                PieceSelect = selection(e);
                coups = PieceSelect.listeDeplacementsPossibles(totPieces);
                eat = PieceSelect.listEat(totPieces);
                removeCoupsEchecs();

                if (PieceSelect.couleur == tour) {
                    affPossible();
                    affEat();
                }
            } catch (NullPointerException er) {
                System.out.println("SELECTIONNEZ UNE PIECE " + tour);
            }
        }
    }

    public void mouseReleasedGrille(MouseEvent e){
        if(!finie) {
            Coordonnee c = new Coordonnee(caseX(e), caseY(e));
            move(PieceSelect, c, coups, eat);
        }
    }

    public void mouseDraggedGrille(MouseEvent e){
        if(!finie) {
            try {
                PieceSelect.image.setLocation(e.getX() - 40, e.getY() - 40);
                grille.repaint();
            } catch (NullPointerException er) {
            }
        }
    }

   /** METHODES UTILISEES PAR LA GRILLE **/

    //init des listes
    public LinkedList<Piece> init(boolean couleur) {
       LinkedList<Piece> pieces = new LinkedList<Piece>();
       if (couleur) {
           pieces.add(new Tour(0, 56, true));
           pieces.add(new Cavalier(1, 57, true));
           pieces.add(new Fou(2, 58, true));
           pieces.add(new Reine(3, 59, true));
           pieces.add(new Roi(4, 60, true));
           pieces.add(new Fou(5, 61, true));
           pieces.add(new Cavalier(6, 62, true));
           pieces.add(new Tour(7, 63, true));

           for (int j = 0; j < 8; j++) {
               pieces.add(new Pion(j + 8, getNum(j, 6), true));
           }
       } else {
           pieces.add(new Tour(0, 0, false));
           pieces.add(new Cavalier(1, 1, false));
           pieces.add(new Fou(2, 2, false));
           pieces.add(new Reine(3, 3, false));
           pieces.add(new Roi(4, 4, false));
           pieces.add(new Fou(5, 5, false));
           pieces.add(new Cavalier(6, 6, false));
           pieces.add(new Tour(7, 7, false));
           for (int j = 0; j < 8; j++) {
               pieces.add(new Pion(j + 8, getNum(j, 1), false));
           }
       }
       return pieces;
   }

    //maj de la liste totale
    public void majTot() {
        totPieces.clear();
        totPieces.addAll(piecesNoirs);
        totPieces.addAll(piecesBlancs);
    }

    //numéro de la case avec des coordonnées
    public int getNum(int i, int j) {
        return 8 * j + i;
   }

    //affichage des pieces
    public void affGrille() {
        grille.removeAll();
        for (int i = 0; i < 2; i++) {
            for (Piece p : ech[i]) {
                try {
                    p.majLocation();
                    grille.add(p.image);
                } catch (NullPointerException e) {
                }
            }
        }
    }

    //affichage des coups possibles
    public void affPossible() {
        for (Coordonnee c : coups) {
            JLabel pos = new JLabel(new ImageIcon("Mouvement_possible.png"));
            pos.setSize(80, 80);
            pos.setLocation(c.getX() * 86 + 3, c.getY() * 86 + 3);
            grille.add(pos);
            grille.repaint();
        }
    }

    //affichage des coups ou on peut manger
    public void affEat() {
        for (Coordonnee c : eat) {
            JLabel pos = new JLabel(new ImageIcon("Eat.png"));
            pos.setSize(86, 86);
            pos.setLocation(c.getX() * 86, c.getY() * 86);
            grille.add(pos);
            grille.repaint();
        }
    }

    // Methode pour les mouvements : utile pour que l'ordinateur teste des mouvements
    public void move(Piece p, Coordonnee c, LinkedList<Coordonnee> aCoups, LinkedList<Coordonnee> aEat) {

        grille.removeAll();
        affGrille();
        grille.repaint();
        try {
            if (containsCoor(aCoups, c) || containsCoor(aEat, c)) {
                p.num = c.getX() + c.getY() * 8;
                p.maj();
                majTot();
                tour = !p.couleur;
                tourInt = tour ? 1 : 0;
                if (containsCoor(aEat, c)) {
                    Piece mangee = getParCase(ech[tourInt], getNum(c.getX(), c.getY()));
                    ech[tourInt].remove(mangee);
                    cim[tourInt].add(mangee);
                    affGrille();
                    affSide(tourInt);
                }

                System.out.println("On test mat sur les "+tourInt+" normalement = a "+tour);
                if (mat(ech[tourInt])) {
                    System.out.println("LES " + !tour + " ONT GAGNE");
                    finie = true;
                }
            }

            p.majLocation();
            majTot();

        } catch (NullPointerException er) {
        }

    }

    //obtenir la piece sur laquelle on clique
    public Piece selection(MouseEvent e) {
        for (Piece p : ech[tourInt]) {
            try {
                if (caseX(e) + caseY(e) * 8 == p.num) {
                    return p;
                }
            } catch (NullPointerException er) {
            }
        }
        return null;
    }

    //obtenir la coordonnee x de la ou on clique
    public int caseX(MouseEvent e) {
        return (int) (e.getX() / 86);
    }

    //obtenir la coordonnee y de la ou on clique
    public int caseY(MouseEvent e) {
        return (int) (e.getY() / 86);
    }

    // Methode qui enleve de la liste des coordonnées de mouvements ce qui menent a un echec
    public void removeCoupsEchecs() {

        boolean b = true;
        LinkedList<Coordonnee> toRemove = new LinkedList<Coordonnee>();
        for (Coordonnee c : coups) {

            LinkedList<Piece> pieces = copyP(ech[tourInt]);

            Piece sel = getIndice(pieces, PieceSelect.indice);
            sel.num = c.getX() + c.getY() * 8;
            sel.maj();

            b = echec(pieces, ech[(tourInt + 1) % 2]);
            if (b) {
                toRemove.add(c);
            }
        }
        coups.removeAll(toRemove);
        toRemove.clear();
        for (Coordonnee c : eat) {
            LinkedList<Piece> pieces = copyP(ech[tourInt]);
            LinkedList<Piece> piecesAdv = copyP(ech[(tourInt + 1) % 2]);

            Piece sel = getIndice(pieces, PieceSelect.indice);
            sel.num = c.getX() + c.getY() * 8;
            sel.maj();

            Piece man = getParCase(piecesAdv, getNum(c.getX(), c.getY()));
            piecesAdv.remove(man);

            b = echec(pieces, piecesAdv);
            if (b) {
                toRemove.add(c);
            }
        }
        eat.removeAll(toRemove);
    }

    //je comprend pas pk ca marche pas contains alors je la refait : a demander
    public boolean containsCoor(LinkedList<Coordonnee> coordonnees, Coordonnee co) {
        for (Coordonnee c : coordonnees) {
            if (c.equals(co)) {
                return true;
            }
        }
        return false;
    }

    //verifier si on est en echec
    public boolean echec(LinkedList<Piece> piecesTest, LinkedList<Piece> piecesAdv) {
        LinkedList<Coordonnee> eats = new LinkedList<Coordonnee>();
        LinkedList<Piece> tot = new LinkedList<Piece>();
        tot.addAll(piecesTest);
        tot.addAll(piecesAdv);
        for (Piece p : piecesAdv) {
            eats.addAll(p.listEat(tot));
        }

        if (containsCoor(eats, new Coordonnee(getIndice(piecesTest, 4).x, getIndice(piecesTest, 4).y))) {
            return true;
        }
        return false;
    }

    //verifier si on est mat ATTENTION BUG !!!!
    public boolean mat(LinkedList<Piece> pieces) {
        LinkedList<Coordonnee> totMove = new LinkedList<Coordonnee>();
        for (Piece p : pieces) {
            coups = p.listeDeplacementsPossibles(totPieces);
            eat = p.listEat(totPieces);
            try {
                removeCoupsEchecs();
            } catch (NullPointerException er) {
            }
            System.out.println(p);
            System.out.println("coups : "+coups);
            System.out.println("eat : "+eat);

            totMove.addAll(coups);
            totMove.addAll(eat);
            System.out.println("-----");

        }

        System.out.println("Nombres de coups "+totMove.size());
        if (totMove.size() == 0)
            return true;
        else
            return false;
    }

    //methodes pour obtenir un piece dans une liste :
    public Piece getIndice(LinkedList<Piece> l, int i) {
        for (Piece p : l) {
            if (p.indice == i) {
                return p;
            }
        }
        return null;
    }
    public Piece getParCase(LinkedList<Piece> l, int c) {
        for (Piece p : l) {
            if (p.num == c) {
                return p;
            }
        }
        return null;
    }

    //methodes qui copie une liste pour pouvoir simuler des coups
    public LinkedList<Piece> copyP(LinkedList<Piece> l) {
        LinkedList<Piece> pieceCopy = new LinkedList<Piece>();
        for (Piece p : l) {
            pieceCopy.add(p.copyPiece());
        }
        return pieceCopy;
    }


   /** FIN DES METHODES UTILISEES PAR LA GRILLE **/

   /** METHODES UTILISEES PAR SIDES **/

   public void affSide(int couleurInt){
       int x, y;
       int i = 0;

       for(Piece p : cim[couleurInt]){
           y = (int)(i/2 * 52.5);
           if (i % 2 == 0) {
               x = 2;
           }else{
               x = 63;
           }
           i++;
           p.image.setLocation(x,y);
           cimetieres[(couleurInt+1)%2].add(p.image);
           cimetieres[(couleurInt+1)%2].repaint();
       }
   }

   /** FIN DES METHODES UTILISEES PAR SIDES **/
}