
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;

public class Echiquier extends JFrame implements ActionListener/**, MouseListener, MouseMotionListener**/
{

    JPanel plateau;
    JPanel grille;
    JPanel foreground;
    JPanel sideNoir;
    JPanel sideBlanc;

    //Attributs utilisés par tous les panels
    LinkedList<Piece> piecesBlancs;
    LinkedList<Piece> piecesNoirs;
    LinkedList<Piece> cimBlancs;
    LinkedList<Piece> cimNoirs;
    LinkedList<String> deplacements;
    boolean tour = true;
    int tourInt = tour ? 1 : 0; // ligne qui converti un boolean en int : true envoie 1 false envoie 0
    boolean finie = false;
    // --------------------- //

    //Attributs utilisés par la grille

    JLabel imgPlateau;
    ImageIcon[] grilles = new ImageIcon[2];

    LinkedList<Piece>[] ech = new LinkedList[2]; // tableau de liste en 0 noirs en 1 blancs (true -> 1 et false -> 0);
    LinkedList<Piece>[] cim = new LinkedList[2];
    LinkedList<Piece> totPieces = new LinkedList<Piece>();

    LinkedList<Coordonnee> coups; //Liste de coordonnees ou on peut se deplacer
    LinkedList<Coordonnee> eat;  //Liste de coordonnees ou on peut manger

    Piece PieceSelect;

    JTextArea coupsJouees;
    JScrollPane ascenseur;

    int numCoup = 0;
    boolean sensP = true;
    int sensInt = sensP ? 1 : 0;

    //Test coup du berger
    Timer t;
    LinkedList<Piece> bergerP = new LinkedList<Piece>();
    LinkedList<Coordonnee> bergerC = new LinkedList<Coordonnee>();
    int i;
    Piece p;
    Coordonnee c;
    LinkedList<Coordonnee> aCoups;
    LinkedList<Coordonnee> aEat;

    String pathWrong = "wrong.wav";
    String pathRight = "right.wav";
    Clip clip;

    // --------------------- //

    //Attributs utilisés par les sides

    JPanel cimetiereNoir;
    JPanel cimetiereBlanc;
    JPanel[] cimetieres = new JPanel[2];
    Timer timerW;
    Timer timerB;
    Timer[] timer = new Timer[2]; // 0 : timerB 1 : timerW
    JLabel chronoW;
    JLabel chronoB;
    JLabel[] chrono = new JLabel[2]; // 0 = chronoB, 1 : chronoW
    JButton sens = new JButton("Inverser");
    double tempsB;
    double tempsW;
    double[] time = new double[2];  // O : tempsB, 1 = tempsW

    // --------------------- //

    /**
     * CONSTRUCTEUR
     **/

    public Echiquier(int temps) {

        /** Init de la fenetre **/
        int larg = 1400;
        int haut = 800 + 27;

        setSize(larg, haut);
        setLocationRelativeTo(null);
        setTitle("Game");
        setResizable(false);
        setLayout(null);

        coupsJouees = new JTextArea();
        coupsJouees.setEditable(false);
        ascenseur = new JScrollPane(coupsJouees);
        ascenseur.setBounds(400, 30, 600, 40);

        add(ascenseur);

        sens.setBounds(10, 30, 50, 50);
        sens.addActionListener(this);
        add(sens);

        /** Init de l'image du plateau **/

        plateau = new JPanel();
        int largP = 760;
        plateau.setBounds((getWidth() - largP) / 2, (getHeight() - largP) / 2, largP, largP);
        plateau.setLayout(null);

        /** Init de la grille et des attributs **/

        initGrille(); //toute la partie avec les pieces qui bougent

        /**          Init du 1er plan        **/

        foreground = new JPanel();
        foreground.setBounds(grille.getBounds());
        foreground.setOpaque(false);


        /** Init des side et des attributs **/

        initSideNoir(temps);
        initSideBlanc(temps); //cotés
        cimetieres[0] = cimetiereNoir;
        cimetieres[1] = cimetiereBlanc;
        timer[0] = timerB;
        timer[1] = timerW;
        chrono[0] = chronoB;
        chrono[1] = chronoW;
        time[0] = tempsB;
        time[1] = tempsW;

        /** Init des SONS **/


        //Image plateau
        grilles[0] = new ImageIcon("GrilleNoirs.png");
        grilles[1] = new ImageIcon("GrilleBlancs.png");
        imgPlateau = new JLabel(new ImageIcon("GrilleBlancs.png"));
        imgPlateau.setBounds(0, 0, largP, largP);
        imgPlateau.setOpaque(false);

        plateau.setOpaque(false);
        plateau.add(foreground);
        plateau.add(grille);
        plateau.add(imgPlateau);

        add(sideNoir);
        add(sideBlanc);
        add(plateau);

        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //test d'une partie automatique
        t = new Timer(1000, this);
        bergerP.add(getIndice(piecesBlancs, 12));
        bergerP.add(getIndice(piecesNoirs, 12));
        bergerP.add(getIndice(piecesBlancs, 5));
        bergerP.add(getIndice(piecesNoirs, 1));
        bergerP.add(getIndice(piecesBlancs, 3));
        bergerP.add(getIndice(piecesNoirs, 6));
        bergerP.add(getIndice(piecesBlancs, 3));

        bergerC.add(new Coordonnee(4, 4));
        bergerC.add(new Coordonnee(4, 3));
        bergerC.add(new Coordonnee(2, 4));
        bergerC.add(new Coordonnee(2, 2));
        bergerC.add(new Coordonnee(7, 3));
        bergerC.add(new Coordonnee(5, 2));
        bergerC.add(new Coordonnee(5, 1));
    }

    //Init de la grille (ancien constructeur de grille)
    public void initGrille() {

        int largG = 688;

        piecesBlancs = init(true);
        piecesNoirs = init(false);
        cimBlancs = new LinkedList<Piece>();
        cimNoirs = new LinkedList<Piece>();
        deplacements = new LinkedList<String>();

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
            public void mousePressed(MouseEvent e) {
                //t.start(); //ENLEVER LE COMM POUR LA DEMO DU COUP DU BERGER
                mousePressedGrille(e);
            }

            public void mouseReleased(MouseEvent e) {
                mouseReleasedGrille(e);
            }

            public void mouseClicked(MouseEvent e) {
                mouseClickedGrille(e);
            }
        });

        grille.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                mouseDraggedGrille(e);
            }
        });
        affGrille();
    }

    //Init du side gauche (ancien constructeur de Side)
    public void initSideNoir(int temps) {
        sideNoir = new JPanel();
        sideNoir.setLocation(0, 0);
        sideNoir.setSize(320, 800);
        sideNoir.setLayout(null);

        JLabel fond = new JLabel(new ImageIcon("Side_Noir.png"));
        fond.setBounds(0, 0, 320, 800);

        cimetiereNoir = new JPanel();
        cimetiereNoir.setBounds(168, 187, 145, 426);
        cimetiereNoir.setOpaque(false);
        cimetiereNoir.setLayout(null);

        timerB = new Timer(1000, this);
        tempsB = temps;
        int sec = (int) (tempsB / 1000 % 60);
        int min = (int) (tempsB / 60000);
        chronoB = new JLabel(min + " : " + sec, SwingConstants.CENTER);

        chronoB.setFont(new Font("maPolice", Font.BOLD, 50));
        chronoB.setBounds(0, 50, 320, 100);

        sideNoir.add(chronoB);
        sideNoir.add(cimetiereNoir);
        affSide(1);
        sideNoir.add(fond);
    }

    //Init du side droit
    public void initSideBlanc(int temps) {
        sideBlanc = new JPanel();
        sideBlanc.setLocation(1080, 0);
        sideBlanc.setSize(320, 800);
        sideBlanc.setLayout(null);

        JLabel fond = new JLabel(new ImageIcon("Side_Noir.png"));
        fond.setBounds(0, 0, 320, 800);

        cimetiereBlanc = new JPanel();
        cimetiereBlanc.setBounds(168, 187, 145, 426);
        cimetiereBlanc.setOpaque(false);
        cimetiereBlanc.setLayout(null);

        timerW = new Timer(1000, this);
        tempsW = temps;
        int sec = (int) (tempsB / 1000 % 60);
        int min = (int) (tempsB / 60000);
        chronoW = new JLabel(min + " : " + sec, SwingConstants.CENTER);
        chronoW.setFont(new Font("maPolice", Font.BOLD, 50));
        chronoW.setBounds(0, 50, 320, 100);

        sideBlanc.add(chronoW);

        sideBlanc.add(cimetiereBlanc);
        affSide(0);
        sideBlanc.add(fond);
    }

    /**
     * METHODES INTERACTIONS
     **/

    public void mousePressedGrille(MouseEvent e) {
        if (!finie) {
            try {
                timer[tourInt].start();
                PieceSelect = selection(e);

                coups = PieceSelect.listeDeplacementsPossibles(totPieces, sensP);
                eat = PieceSelect.listEat(totPieces, sensP);

                if (PieceSelect instanceof Roi) {
                    coups.addAll(listRoques(ech[tourInt], ech[(tourInt + 1) % 2]));
                }

                removeCoupsEchecs(PieceSelect);
                affPossible();
                affEat();
                foreground.add(PieceSelect.image);
                grille.remove(PieceSelect.image);

            } catch (NullPointerException er) {
                //System.out.println("SELECTIONNEZ UNE PIECE " + tour);
            }
        }
    }

    public void mouseReleasedGrille(MouseEvent e) {
        if (!finie) {
            Coordonnee c = new Coordonnee(caseX(e), caseY(e));
            move(PieceSelect, c, coups, eat);
        }
    }

    public void mouseClickedGrille(MouseEvent e) {
    }

    public void mouseDraggedGrille(MouseEvent e) {
        if (!finie) {
            try {
                PieceSelect.image.setLocation(e.getX() - 40, e.getY() - 40);
                grille.repaint();
            } catch (NullPointerException er) {
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        int sec, min;
        if (e.getSource() == timerB) {
            tempsB -= timerB.getDelay();
            sec = (int) (tempsB / 1000 % 60);
            min = (int) (tempsB / 60000);
            //chronoB.setText(min+" : " +sec);
            this.setTitle("C'est aux NOIRS de jouer, il leur reste : " + min + " minutes et " + sec + " secondes");
        }

        if (e.getSource() == timerW) {
            tempsW -= timerW.getDelay();
            sec = (int) (tempsW / 1000 % 60);
            min = (int) (tempsW / 60000);
            //chronoW.setText(min+" : " +sec);
            this.setTitle("C'est aux BLANCS de jouer, il leur reste : " + min + " minutes et " + sec + " secondes");
        }
        if (tempsB <= 0)
            new WindowWin(true);
        if (tempsW <= 0)
            new WindowWin(false);


        /** INVERSER LE PLATEAU **/
        if (e.getSource() == sens) {
            tourner();
        }

        /** POUR LE COUP DU BERGER **/

        if (e.getSource() == t) {
            try {
                p = bergerP.get(i);
                c = bergerC.get(i);
                aCoups = p.listeDeplacementsPossibles(totPieces, sensP);
                aEat = p.listEat(totPieces, sensP);
                move(p, c, aCoups, aEat);

                i++;

            } catch (IndexOutOfBoundsException er) {
                System.out.println("LES " + !tour + " ONT GAGNE");
                finie = true;
                t.stop();
            }
        }
    }

    /**  METHODES POUR LES SONS **/

    public void setFile(String musicPath) {

        try {
            File file = new File(musicPath);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void jouer(String musicPath){
        setFile(musicPath);
        play();
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

    public void tourner(){
        sensInt = !sensP ? 1 : 0;
        for(Piece p : totPieces){
            p.num = 63 - p.num;
            p.maj();
            p.majLocation();
        }
        sensP = !sensP;
        imgPlateau.setIcon(grilles[sensInt]);

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

    public LinkedList<Coordonnee> listRoques(LinkedList<Piece> piecesT, LinkedList<Piece> piecesA){


        int d, l; // d : debut pour tester echec, l : limite de la boucle for. PETIT ROQUE
        int D, L; // pareil mais GRAND ROQUE

        Coordonnee c, C;

        if(piecesT.get(0).couleur){
            d = 60;
            l = 63;
            c = new Coordonnee(6, 7);

            D = 58;
            L = 61;
            C = new Coordonnee(2,7);
        }else{
            d = 4;
            l = 7;
            c = new Coordonnee(6, 0);

            D = 2;
            L = 5;
            C = new Coordonnee(2, 0);
        }

        LinkedList<Coordonnee> roques = new LinkedList<Coordonnee>();
        Piece roi = getIndice(piecesT, 4);
        Piece tourPetit = getIndice(piecesT, 7);
        Piece tourGrand = getIndice(piecesT, 0);

        LinkedList<Piece> copy = copyP(piecesT);
        Piece roiTest;
        Piece tourTest;

        int case1Petit = roi.num +1;
        int case2Petit = roi.num + 2;
        int case1Grand = roi.num - 3;
        int case2Grand = roi.num - 2;
        int case3Grand = roi.num -1;

        // PETIT ROQUE
        boolean b3 = true;

        if(roi.roquable && tourPetit.roquable){
            if(vide(totPieces, case1Petit) && vide(totPieces, case2Petit)){
                for(int i = d; i < l ; i++) {
                    roiTest = getIndice(copy, 4);
                    roiTest.num = i;
                    roiTest.maj();

                    if (echec(copy, piecesA)) {
                        b3 = false;
                        break;
                    }
                }

                if(b3){
                    roques.add(c);
                }
            }
        }

        // GRAND ROQUE
        b3 = true;

        if(roi.roquable && tourGrand.roquable){
            if(vide(totPieces, case1Grand) && vide(totPieces, case2Grand) && vide(totPieces, case3Grand)){
                for(int i = D; i < L ; i++) {
                    roiTest = getIndice(copy, 4);
                    roiTest.num = i;
                    roiTest.maj();

                    if (echec(copy, piecesA)) {
                        b3 = false;
                        break;
                    }
                }
                if(b3){
                    roques.add(C);
                }
            }
        }
        return roques;
    }

    // Methode pour les mouvements : utile pour que l'ordinateur teste des mouvements
    public void move(Piece p, Coordonnee c, LinkedList<Coordonnee> aCoups, LinkedList<Coordonnee> aEat) {

        boolean roque = false;
        grille.removeAll();
        affGrille();
        grille.repaint();
        try {
            if (containsCoor(aCoups, c) || containsCoor(aEat, c)) {
                p.num = c.getX() + c.getY() * 8;
                p.maj();
                majTot();
                p.majLocation();
                dame(p);
                jouer(pathRight);


                if(p instanceof Roi && p.roquable){
                    if(c.getX() == 2){
                        if(c.getY() == 0) {
                            deplacer(getIndice(piecesNoirs, 0), new Coordonnee(3, 0));
                        }
                        if(c.getY() == 7){
                            deplacer(getIndice(piecesBlancs, 0), new Coordonnee(3, 7));
                        }
                        deplacements.add(numCoup+". : O-O-O -- ");
                        roque = true;
                    }
                    if(c.getX() == 6) {
                        if (c.getY() == 0) {
                            deplacer(getIndice(piecesNoirs, 7), new Coordonnee(5, 0));
                        }
                        if(c.getY() == 7){
                            deplacer(getIndice(piecesBlancs, 7), new Coordonnee(5, 7));
                        }
                        deplacements.add(numCoup+". : O-O -- ");
                        roque = true;
                    }
                }
                p.roquable = false;


                double tempsAMAJ;
                if(tour) {
                    tempsAMAJ = tempsW;
                }else{
                    tempsAMAJ = tempsB;
                }
                int sec = (int)(tempsAMAJ/1000 % 60);
                int min = (int)(tempsAMAJ/60000);

                chrono[tourInt].setText(min+" : " +sec);

                tour = !p.couleur;
                tourInt = tour ? 1 : 0;

                timer[(tourInt+1)%2].stop();
                timer[tourInt].start();


                if (containsCoor(aEat, c)) {
                    Piece mangee = getParCase(ech[tourInt], getNum(c.getX(), c.getY()));
                    ech[tourInt].remove(mangee);
                    cim[tourInt].add(mangee);
                    affGrille();
                    affSide(tourInt);
                    if(!roque)
                        if(p instanceof Pion){
                            deplacements.add(numCoup+". : "+((Pion) p).toStringX()+""+c.toStringEat()+" -- ");
                        }else {
                            deplacements.add(numCoup+". : "+p + "" + c.toStringEat()+" -- ");
                        }
                }else{
                    if(!roque)
                        deplacements.add(numCoup+". : "+p+""+c+" -- ");
                }
                coupsJouees.append(deplacements.get(numCoup));
                numCoup++;

                if (mat(ech[tourInt])) {
                    System.out.println("LES " + !tour + " ONT GAGNE");
                    new WindowWin(!tour);
                    finie = true;
                }
            }else{
                jouer(pathWrong);
            }

            grille.add(PieceSelect.image);
            foreground.remove(PieceSelect.image);
            majTot();

        } catch (NullPointerException er) {
        }
        PieceSelect = null;
    }

    //deplacer sans les verifications
    public void deplacer(Piece p, Coordonnee c){
        p.num = c.getX() + c.getY() * 8;
        p.maj();
        majTot();
        p.majLocation();
    }

    public String coder(Piece p, Coordonnee c){
        String m = Integer.toString(c.getX());
        m += Integer.toString(c.getY());
        m += Integer.toString(p.couleur ? 1 : 0);
        m += Integer.toHexString(p.indice);

        return m;
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

    public boolean vide(LinkedList<Piece> l, int aCase){
        for(Piece p : l){
            if(p.num == aCase){
                return false;
            }
        }
        return true;
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
    public void removeCoupsEchecs(Piece p) {

        boolean b = true;
        LinkedList<Coordonnee> toRemove = new LinkedList<Coordonnee>();
        for (Coordonnee c : coups) {

            LinkedList<Piece> pieces = copyP(ech[tourInt]);

            Piece sel = getIndice(pieces, p.indice);
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

            Piece sel = getIndice(pieces, p.indice);
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
            eats.addAll(p.listEat(tot, sensP));
        }
        if (containsCoor(eats, new Coordonnee(getIndice(piecesTest, 4).x, getIndice(piecesTest, 4).y))) {
            return true;
        }

        return false;
    }

    //verifier si on est mat
    public boolean mat(LinkedList<Piece> pieces) {
        LinkedList<Coordonnee> totMove = new LinkedList<Coordonnee>();
        for (Piece p : pieces) {
            coups = p.listeDeplacementsPossibles(totPieces, sensP);
            eat = p.listEat(totPieces, sensP);
            try {
                removeCoupsEchecs(p);
            } catch (NullPointerException er) {
            }

            totMove.addAll(coups);
            totMove.addAll(eat);
        }

        if (totMove.size() == 0)
            return true;
        else
            return false;
    }

    //transforme un pion en dame si il atteint le bord adverse
    public void dame(Piece p){
        if(p instanceof Pion){
            if(((p.couleur ^ !sensP) && p.y == 0) || (((!p.couleur ^ !sensP) && p.y == 7))){
                int couleur = p.couleur ? 1 : 0;
                ech[couleur].remove(p);
                Piece newP = new Reine(p.indice, p.num, p.couleur);
                newP.majLocation();
                PieceSelect = newP;
                ech[couleur].add(newP);
                affGrille();
            }
        }
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
