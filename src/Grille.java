import javax.swing.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.awt.event.*;

public class Grille extends JPanel implements MouseListener, MouseMotionListener  {

    LinkedList<Piece> piecesBlancs;
    LinkedList<Piece> piecesNoirs;

    LinkedList<Piece> [] ech = new LinkedList[2]; // tableau de liste en 0 noirs en 1 blancs (true -> 1 et false -> 0);
    LinkedList<Piece> totPieces = new LinkedList<Piece>();


    LinkedList<Coordonnee> coups; //Liste de coordonnees ou on peut se deplacer
    LinkedList<Coordonnee> eat;  //Liste de coordonnees ou on peut manger

    Piece PieceSelect;
    boolean sourisInt;
    boolean tour = true;
    int tourInt = tour ? 1 : 0; // ligne qui converti un boolean en int : true envoie 1 false envoie 0

    public Grille(){
        int largP = 688;

        piecesBlancs = init(true);
        piecesNoirs = init(false);

        majTot();

        ech[0] = piecesNoirs;
        ech[1] = piecesBlancs;
        new JPanel();
        setBounds(0, 0, largP, largP);
        setOpaque(false);
        setLayout(null);

        addMouseListener(this);
        addMouseMotionListener(this);
        aff();
    }

    public LinkedList<Piece> init(boolean couleur){
        LinkedList<Piece> pieces = new LinkedList<Piece>();
            if(couleur){
                pieces.add(new Tour(0,56,true));
                pieces.add(new Cavalier(1,57,true));
                pieces.add(new Fou(2,58,true));
                pieces.add(new Reine(3,59,true));
                pieces.add(new Roi(4,60,true));
                pieces.add(new Fou(5,61,true));
                pieces.add(new Cavalier(6,62,true));
                pieces.add(new Tour(7,63,true));

                for (int j = 0; j < 8; j++) {
                    pieces.add(new Pion(j+8, getNum(j,6),true));
                }
            }else{
                pieces.add(new Tour(0,0,false));
                pieces.add(new Cavalier(1,1,false));
                pieces.add(new Fou(2,2,false));
                pieces.add(new Reine(3,3,false));
                pieces.add(new Roi(4,4,false));
                pieces.add(new Fou(5,5,false));
                pieces.add(new Cavalier(6,6,false));
                pieces.add(new Tour(7,7,false));
                for (int j = 0; j < 8; j++) {
                    pieces.add(new Pion(j+8,getNum(j,1),false));
                }
            }
            return pieces;
    }

    public int getNum(int i, int j){
        return 8*j + i;
    }

    public void aff() {
        this.removeAll();
        for (int i = 0; i < 2; i++) {
            for (Piece p : ech[i]) {
                try {
                    p.majLocation();
                    add(p.image);
                } catch (NullPointerException e) {
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e) {

        try {
            PieceSelect = selection(e);
            coups = PieceSelect.listeDeplacementsPossibles(totPieces);
            eat = PieceSelect.listEat(totPieces);
            removeCoupsEchecs();

            if(PieceSelect.couleur == tour){
                affPossible();
                affEat();
            }
        } catch (NullPointerException er) {
            System.out.println("SELECTIONNEZ UNE PIECE "+tour);
        }
    }

    public void mouseReleased(MouseEvent e) {
        boolean b = false;
        boolean b2 = false;
            try {
                b = containsCoor(coups, new Coordonnee(caseX(e),caseY(e)));
                b2 = containsCoor(eat, new Coordonnee(caseX(e),caseY(e)));
            } catch (NullPointerException er) {}

            this.removeAll();
            aff();
            repaint();
            try {
                if (sourisInt && (b || b2) ){
                    PieceSelect.num = caseX(e) + caseY(e) * 8;
                    PieceSelect.maj();
                    majTot();
                    tour = !tour;
                    tourInt = tour ? 1 : 0;
                    boolean b3 = echec(ech[tourInt], ech[(tourInt+1)%2]);
                if(b2) {
                     Piece mangée = selection(e);
                     ech[tourInt].remove(mangée);
                     aff();
                }
                }

                PieceSelect.majLocation();
                majTot();

            } catch (NullPointerException er) {
            }

            if(mat(ech[(tourInt+1)%2])){
                System.out.println("LES "+!tour+" ONT GAGNE");
                removeMouseListener(this);
                removeMouseMotionListener(this);
            }
    }

    public void mouseDragged(MouseEvent e){
            try {
                PieceSelect.image.setLocation(e.getX() - 40, e.getY() - 40);
            }catch(NullPointerException er){}
    }

    public int caseX(MouseEvent e) {
        return (int) (e.getX() / 86);
    }

    public int caseY(MouseEvent e) {
        return (int) (e.getY() / 86);
    }

    public Piece selection(MouseEvent e) {
        for (Piece p : ech[tourInt]) {
            try {
                if (caseX(e) + caseY(e) * 8 == p.num) {
                    return p;
                }
            } catch (NullPointerException er) {}
        }
        return null;
    }

    public void affPossible(){
        for(Coordonnee c : coups){
            JLabel pos = new JLabel(new ImageIcon("Mouvement_possible.png"));
            pos.setSize(80,80);
            pos.setLocation(c.getX()*86+3, c.getY()*86+3);
            this.add(pos);
            repaint();
        }
    }

    public void affEat(){
        for(Coordonnee c : eat){
            JLabel pos = new JLabel(new ImageIcon("Eat.png"));
            pos.setSize(86,86);
            pos.setLocation(c.getX()*86, c.getY()*86);
            this.add(pos);
            repaint();
        }
    }

    //je comprend pas pk ca marche pas contains alors je la refait : a demander
    public boolean containsCoor(LinkedList<Coordonnee> coordonnees, Coordonnee co){
        for(Coordonnee c : coordonnees){
           if(c.equals(co)){
                return true;
            }
        }
        return false;
    }

    public void majTot(){
        totPieces.clear();
        totPieces.addAll(piecesNoirs);
        totPieces.addAll(piecesBlancs);
    }

    //methodes pour obtenir un piece dans une liste : voir doc
    public Piece getIndice(LinkedList<Piece> l, int i){
        for(Piece p : l){
            if(p.indice == i){
                return p;
            }
        }
        return null;
    }
    public Piece getParCase(LinkedList<Piece> l, int c){
        for(Piece p : l){
            if(p.num == c){
                return p;
            }
        }
        return null;
    }

    //methodes qui copie une liste pour pouvoir simuler des coups
    public LinkedList<Piece> copyP(LinkedList<Piece> l){
        LinkedList<Piece> pieceCopy = new LinkedList<Piece>();
        for(Piece p : l){
            pieceCopy.add(p.copyPiece());
        }
        return pieceCopy;
    }
    public LinkedList<Coordonnee> copyC(LinkedList<Coordonnee> l){
        LinkedList<Coordonnee> coorCopy = new LinkedList<Coordonnee>();
        for(Coordonnee c : l){
            coorCopy.add(new Coordonnee(c));
        }
        return coorCopy;
    }

    // Methode qui enleve de la liste des coordonnées de mouvements ce qui menent a un echec
    public void removeCoupsEchecs() {

        boolean b = true;
        LinkedList<Coordonnee> toRemove = new LinkedList<Coordonnee>();
        for(Coordonnee c : coups){

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

            Piece man = getParCase(piecesAdv, getNum(c.getX(),c.getY()));
            piecesAdv.remove(man);

            b = echec(pieces, piecesAdv);
            if (b) {
                toRemove.add(c);
            }
        }
        eat.removeAll(toRemove);
    }

    public boolean echec(LinkedList<Piece> piecesTest, LinkedList<Piece> piecesAdv){
        LinkedList<Coordonnee> eats = new LinkedList<Coordonnee>();
        LinkedList<Piece> tot = new LinkedList<Piece>();
        tot.addAll(piecesTest);
        tot.addAll(piecesAdv);
        for(Piece p : piecesAdv){
            eats.addAll(p.listEat(tot));
        }

        if(containsCoor(eats, new Coordonnee(getIndice(piecesTest,4).x, getIndice(piecesTest,4).y))){
            return true;
        }
        return false;
    }

    //A FAIRE
    public boolean mat(LinkedList<Piece> pieces){
        LinkedList<Coordonnee> totMove = new LinkedList<Coordonnee>();
        for(Piece p : pieces){
            coups = p.listeDeplacementsPossibles(totPieces);
            eat = p.listEat(totPieces);

            try {
                removeCoupsEchecs();
            }catch(NullPointerException er){}

            totMove.addAll(coups);
            totMove.addAll(eat);
        }
        if(totMove.size() == 0)
            return true;
        else
            return false;
    }


    public void mouseEntered(MouseEvent e){
        sourisInt = true;
    }
    public void mouseExited(MouseEvent e){
        sourisInt = false;
    }
    public void mouseMoved(MouseEvent e){}
}
