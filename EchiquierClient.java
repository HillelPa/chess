import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;


public class EchiquierClient extends JFrame implements ActionListener
{

    String ip;


    JPanel plateau;
    JPanel grille;
    JPanel foreground;
    JPanel sideNoir;
    JPanel sideBlanc;
    JPanel centre;

    Object objetRecu;
    char fin = 'f';
    long tempsAvant, tempsApres;
    ObjectInputStream in;
    ObjectOutputStream out;
    int temps2;

    Timer t;
    int temps = 0;

    Socket s;
    JButton envoyer;

    //Attributs utilisés par tous les panels
    LinkedList<Piece> piecesBlancs;
    LinkedList<Piece> piecesNoirs;
    LinkedList<Piece> cimBlancs;
    LinkedList<Piece> cimNoirs;
    boolean tour;
    int tourInt;
    boolean finie = false;
    // --------------------- //

    //Attributs utilisés par la grille

    JLabel imgPlateau;

    LinkedList<Piece>[][] ech = new LinkedList[2][2]; // tableau de liste en 0 noirs en 1 blancs (true -> 1 et false -> 0);
    LinkedList<Piece> totPieces = new LinkedList<Piece>();

    LinkedList<Coordonnee> coups; //Liste de coordonnees ou on peut se deplacer
    LinkedList<Coordonnee> eat;  //Liste de coordonnees ou on peut manger

    Piece PieceSelect;

    MouseAdapter mouseAdapt;

    int numCoup = 0;
    final boolean sensP = false;

    String pathWrong = "wrong.wav";
    String pathRight = "right.wav";
    Clip clip;

    // --------------------- //

    //Attributs utilisés par les sides

    JPanel cimetiereNoir;
    JPanel cimetiereBlanc;
    JPanel[] cimetieres = new JPanel[2];
    JLabel chronoW;
    JLabel chronoB;
    double tempsB;
    double tempsW;
    double[] time = new double[2];  // O : tempsB, 1 = tempsW
    JButton giveUpN = new JButton();

    // --------------------- //

    /**
     * CONSTRUCTEUR
     **/

    public EchiquierClient(String aIp) {

        ip = aIp;
        tempsAvant = System.currentTimeMillis();

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
        plateau.setBounds((getWidth() - largP) / 2, ((getHeight() - largP) / 2) - 10, largP, largP);
        plateau.setLayout(null);

        /** Init du centre **/
        centre = new JPanel();
        centre.setBackground(new Color(40,40,40));
        centre.setBounds(320,0,760,getHeight());

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
        time[0] = tempsB;
        time[1] = tempsW;


        /** Init des SONS **/

        //Image plateau
        imgPlateau = new JLabel(new ImageIcon("GrilleNoirs_Res.png"));
        imgPlateau.setBounds(0, 0, largP, largP);
        imgPlateau.setOpaque(false);

        plateau.setOpaque(false);
        plateau.add(foreground);
        plateau.add(grille);
        plateau.add(imgPlateau);

        add(sideNoir);
        add(sideBlanc);
        add(plateau);
        add(centre);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toFront();
        repaint();
        setVisible(true);

        tour = false;
        tourInt = 0;

        t = new Timer(1000, this);
        t.start();

    }

    //Init de la grille (ancien constructeur de grille)
    public void initGrille() {

        int largG = 688;

        piecesBlancs = init(true);
        piecesNoirs = init(false);
        cimBlancs = new LinkedList<Piece>();
        cimNoirs = new LinkedList<Piece>();

        majTot();
        tourner();

        ech[0][0] = piecesNoirs;
        ech[0][1] = piecesBlancs;
        ech[1][0] = cimNoirs;
        ech[1][1] = cimBlancs;

        grille = new JPanel();
        grille.setBounds(36, 36, largG, largG);
        grille.setOpaque(false);
        grille.setLayout(null);

        mouseAdapt= new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                mousePressedGrille(e);
            }

            public void mouseReleased(MouseEvent e) {
                mouseReleasedGrille(e);
            }

        };


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
        sideNoir.setLocation(1080, 0);
        sideNoir.setSize(320, 800);
        sideNoir.setLayout(null);

        JLabel fond = new JLabel(new ImageIcon("Side_J.png"));
        fond.setBounds(0, 0, 320, 800);

        cimetiereNoir = new JPanel();
        cimetiereNoir.setBounds(25, 215, 260, 245);
        cimetiereNoir.setOpaque(false);
        cimetiereNoir.setLayout(null);

        envoyer = new JButton("");
        envoyer.setBounds(100,540,120,60);
        envoyer.setOpaque(false);
        envoyer.setContentAreaFilled(false);
        envoyer.setBorderPainted(false);

        giveUpN.setBounds(this.getWidth()-225, this.getHeight()-175, 135, 65);
        giveUpN.setOpaque(false);
        giveUpN.setContentAreaFilled(false);
        giveUpN.setBorderPainted(false);
        giveUpN.addActionListener(this);

        chronoB = new JLabel("-- : --", SwingConstants.CENTER);
        chronoB.setFont(new Font("maPolice", Font.BOLD, 50));
        chronoB.setBounds(0, 50, 320, 100);

        sideNoir.add(chronoB);
        sideNoir.add(cimetiereNoir);
        affSide(1);
        sideNoir.add(fond);
        sideNoir.add(envoyer);
        sideNoir.add(giveUpN);
    }

    //Init du side droit
    public void initSideBlanc(int temps) {
        sideBlanc = new JPanel();
        sideBlanc.setLocation(0, 0);
        sideBlanc.setSize(320, 800);
        sideBlanc.setLayout(null);

        JLabel fond = new JLabel(new ImageIcon("Side_A.png"));
        fond.setBounds(0, 0, 320, 800);

        chronoW = new JLabel("-- : --", SwingConstants.CENTER);
        chronoW.setFont(new Font("maPolice", Font.BOLD, 50));
        chronoW.setBounds(0, 50, 320, 100);

        cimetiereBlanc = new JPanel();
        cimetiereBlanc.setBounds(25, 215, 260, 245);
        cimetiereBlanc.setOpaque(false);
        cimetiereBlanc.setLayout(null);

        sideBlanc.add(chronoW);
        sideBlanc.add(cimetiereBlanc);
        affSide(0);
        sideBlanc.add(fond);
    }

    /**
     * METHODES INTERACTIONS
     **/

    public void mousePressedGrille(MouseEvent e) {
        if (!finie && !tour) {
            try {
                PieceSelect = selection(e);

                coups = PieceSelect.listeDeplacementsPossibles(totPieces, sensP);
                eat = PieceSelect.listEat(totPieces, sensP);

                if (PieceSelect instanceof Roi) {
                    coups.addAll(listRoques(piecesNoirs, piecesBlancs));
                }

                removeCoupsEchecs(PieceSelect);
                affPossible();
                affEat();
                foreground.add(PieceSelect.image);
                grille.remove(PieceSelect.image);

            } catch (NullPointerException er) {
            }
        }
    }

    public void mouseReleasedGrille(MouseEvent e) {
        if (!finie && !tour) {
            Coordonnee c = new Coordonnee(caseX(e), caseY(e));
            move(PieceSelect, c, coups, eat);
        }
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

        if(e.getSource() == t){
            temps2 += t.getDelay();

            if(temps2 >= 6000){
                t.stop();
                try{

                    s = new Socket(ip, 2626);

                    out = new ObjectOutputStream(s.getOutputStream());
                    in = new ObjectInputStream(s.getInputStream());
                    grille.addMouseListener(mouseAdapt);

                    objetRecu = in.readObject();
                    temps = (int)objetRecu;
                    setTemps();


                }catch(IOException | ClassNotFoundException er){
                }

                cEstAAdv();

                tempsApres = System.currentTimeMillis();
                tempsW = tempsW - (tempsApres - tempsAvant);
                tempsAvant = System.currentTimeMillis();
                sec = (int)(tempsW / 1000 % 60);
                min = (int)(tempsW / 60000);
                chronoW.setText(min+" : "+sec);

                try {
                    objetRecu = in.readObject();
                    ech = (LinkedList<Piece>[][]) objetRecu;
                    majList();
                    majTot();
                    tourner();
                    affGrille();
                    grille.repaint();
                    cEstAVous();
                } catch (ClassNotFoundException | IOException | NullPointerException er) {
                    t.stop();
                    int choix = JOptionPane.showOptionDialog(null,"Probleme de réseau ! " +
                                    "\n Verifiez l'IP entrée, " +
                                    "\n Verifiez que leux deux machines sont connectées au même réseau"+
                                    "\n réessayez en lançant la partie d'abord sur l'autre machine" +
                                    "\n\n Retour à l'accueil ? ",

                            "Erreur", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null, null, null  );
                    if(choix == 0) {
                        new Home();
                    }
                    this.dispose();
                }
                }
            }

        if (e.getSource() == giveUpN) {
            toFront();
            try {
                out.writeObject(fin);
                out.flush();
                out.reset();
            }catch(IOException er){}
            int choix = JOptionPane.showOptionDialog(null,"Les noirs ont concede la victoire, Victoire aux blancs !"+
                            "\n \n Retour à l'accueil ?",
                    "Fin de partie",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);

            this.dispose();
            if(choix == 0){
                new Home();
            }
            finie = true;
        }

            if (e.getSource() == envoyer) {
    if (tempsB <= 0) {
        toFront();
        int choix = JOptionPane.showOptionDialog(null,"Le temps limite des noirs est depassé, victoire aux blancs !" +
                        "\n \n Retour à l'accueil ?",
                "Fin de partie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        this.dispose();
        if(choix == 0){
            new Home();
        }
        finie = true;
    }
    if (tempsW <= 0) {
        toFront();
        int choix = JOptionPane.showOptionDialog(null,"Le temps limite des blancs est depasse, victoire aux noirs !" +
                        "\n \n Retour à l'accueil ?",
                "Fin de partie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);
        this.dispose();
        if(choix == 0){
            new Home();
        }
        finie = true;
    }
                envoyer.removeActionListener(this);
                try {
                    out.writeObject(ech);
                    out.flush();
                    out.reset();

                    tour = true;
                    tourInt = 1;
                    cEstAAdv();
                    if (mat(piecesBlancs)) {
                        toFront();
                        int choix = JOptionPane.showOptionDialog(null,"Les noirs ont gagné !" +
                                        "\n \n Retour à l'accueil ?",
                                "Fin de partie",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, null, null);
                        this.dispose();
                        if(choix == 0){
                            new Home();
                        }
                        finie = true;
                    }

                    tempsApres = System.currentTimeMillis();
                    tempsB = tempsB - (tempsApres - tempsAvant);
                    sec = (int) (tempsB / 1000 % 60);
                    min = (int) (tempsB / 60000);
                    chronoB.setText(min + " : " + sec);
                    tempsAvant = System.currentTimeMillis();

                } catch (IOException er) {
                }

                try {
                    objetRecu = in.readObject();
                    if(objetRecu instanceof Character){
                        int choix = JOptionPane.showOptionDialog(null,"Les blancs ont concede la victoire, Victoire aux noirs !"+
                                        "\n \n Retour à l'accueil ?",
                                "Fin de partie",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, null, null);

                        this.dispose();
                        if(choix == 0){
                            new Home();
                        }
                        finie = true;
                    }else {
                        ech = (LinkedList<Piece>[][]) objetRecu;
                    }
                    majList();
                    majTot();
                    tourner();
                    affGrille();
                    jouer(pathRight);
                    affSide(0);
                    grille.repaint();

                    if (tempsB <= 0) {
                        toFront();
                        int choix = JOptionPane.showOptionDialog(null,"Le temps limite " + "des noirs est depassé, victoire aux blancs !" +
                                        "\n \n Retour à l'accueil ?",
                                "Fin de partie",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, null, null);

                        this.dispose();
                        if(choix == 0){
                            new Home();
                        }
                        finie = true;
                    }
                    if (tempsW <= 0) {
                        toFront();
                        int choix = JOptionPane.showOptionDialog(null,"Le temps limite des blancs est depasse, victoire aux noirs !" +
                                        "\n \n Retour à l'accueil ?",
                                "Fin de partie",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, null, null);
                        this.dispose();
                        if(choix == 0){
                            new Home();
                        }
                        finie = true;
                    }

                    tour = false;
                    tourInt = 0;
                    cEstAVous();
                    if (mat(piecesNoirs)) {
                        toFront();
                        int choix = JOptionPane.showOptionDialog(null,"Les blancs ont gagné !" +
                                        "\n \n Retour à l'accueil ?",
                                "Fin de partie",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, null, null);
                        this.dispose();
                        if(choix == 0){
                            new Home();
                        }
                        finie = true;
                    }

                    tempsApres = System.currentTimeMillis();
                    tempsW = tempsW - (tempsApres - tempsAvant);
                    sec = (int) (tempsW / 1000 % 60);
                    min = (int) (tempsW / 60000);
                    chronoW.setText(min + " : " + sec);

                    grille.addMouseListener(mouseAdapt);
                } catch (ClassNotFoundException | IOException er) {
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

    //maj des listes
    public void majList(){
        piecesBlancs = ech[0][1];
        piecesNoirs = ech[0][0];
        cimBlancs = ech[1][1];
        cimNoirs = ech[1][0];
    }

    //numéro de la case avec des coordonnées
    public int getNum(int i, int j) {
        return 8 * j + i;
    }

    //affichage des pieces
    public void affGrille() {
        grille.removeAll();
        for (Piece p : totPieces) {
            try {
                p.majLocation();
                grille.add(p.image);
            } catch (NullPointerException e) {
            }
        }
    }

    public void tourner(){
        for(Piece p : totPieces){
            p.num = 63 - p.num;
            p.maj();
            p.majLocation();
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
                        deplacer(getIndice(piecesNoirs, 0), new Coordonnee(3, 0));
                    }
                    if(c.getX() == 6) {
                        deplacer(getIndice(piecesNoirs, 7), new Coordonnee(5, 0));
                    }
                }
                p.roquable = false;

                if(containsCoor(aEat, c)){
                    Piece mangee = getParCase(piecesBlancs, getNum(c.getX(), c.getY()));
                    piecesBlancs.remove(mangee);
                    cimBlancs.add(mangee);
                    majTot();
                    affGrille();
                    affSide(1);
                }

                numCoup++;

                grille.add(PieceSelect.image);
                foreground.remove(PieceSelect.image);
                majTot();
                grille.removeMouseListener(mouseAdapt);
                envoyer.addActionListener(this);
            }else{
                jouer(pathWrong);
            }

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

    //obtenir la piece sur laquelle on clique
    public Piece selection(MouseEvent e) {
        for (Piece p : piecesNoirs) {
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

        boolean b;
        LinkedList<Coordonnee> toRemove = new LinkedList<Coordonnee>();
        for (Coordonnee c : coups) {

            LinkedList<Piece> pieces = copyP(ech[0][tourInt]);

            Piece sel = getIndice(pieces, p.indice);
            sel.num = c.getX() + c.getY() * 8;
            sel.maj();

            b = echec(pieces, ech[0][(tourInt+1)%2]);
            if (b) {
                toRemove.add(c);
            }
        }
        coups.removeAll(toRemove);
        toRemove.clear();
        for (Coordonnee c : eat) {
            LinkedList<Piece> pieces = copyP(ech[0][tourInt]);
            LinkedList<Piece> piecesAdv = copyP(ech[0][(tourInt+1)%2]);

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
            if(p.y == 0){
                piecesNoirs.remove(p);
                Piece newP = new Reine(p.indice, p.num, p.couleur);
                newP.majLocation();
                PieceSelect = newP;
                piecesNoirs.add(newP);
                majTot();
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

    public void affSide(int couleurInt) {
        int x, y;
        int i = 0;
        try {
            for (Piece p : ech[1][couleurInt]) {
                x = (int) (i%4 * 62.5);
                y = (int) (i/4 * 57.5);
                i++;
                p.image.setLocation(x, y);
                cimetieres[(couleurInt + 1) % 2].add(p.image);
            }
            cimetieres[(couleurInt + 1) % 2].repaint();

        } catch (Exception e) {}
    }

    public void cEstAVous(){
        setTitle("C'est à vous ! Appuyez sur l'horloge pour valider votre coup");
    }

    public void cEstAAdv(){
        setTitle("C'est aux blancs ! Attendez que votre adversaire finisse son coup");
    }

    public void setTemps(){
        tempsW = temps;
        int sec = (int) (tempsW / 1000 % 60);
        int min = (int) (tempsW / 60000);
        chronoW .setText(min + " : " + sec);
        sideBlanc.repaint();

        tempsB = temps;
        sec = (int) (tempsB / 1000 % 60);
        min = (int) (tempsB / 60000);
        chronoB.setText(min + " : " + sec);
        sideNoir.repaint();

    }

    /** FIN DES METHODES UTILISEES PAR SIDES **/
}
