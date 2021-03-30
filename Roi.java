import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Roi extends Piece {


    public Roi(int aIndice, int aNum,boolean aCouleur){ // c true : blanc ; c false : noir

        super(aIndice,aNum,"Roi",6,aCouleur);
        if(aCouleur){
            image = new JLabel(new ImageIcon("Roi_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Roi_Noir.png"));
        }
        image.setSize(80,80);
    }


    public String toString(){
        if(couleur){
            return "♔";
        }else{
            return "♚";
        }
    }

    // méthode qui créer une liste des Coordonnées de deplacements possibles
    public LinkedList<Coordonnee> listeDeplacementsPossibles(LinkedList<Piece> ech){
        LinkedList<Coordonnee> coups = new LinkedList<Coordonnee>();
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                        coups.add(new Coordonnee(i, j));
                    }
                }
            }
        return coups;
    }

    public LinkedList<Coordonnee> listEat(LinkedList<Piece> pieces){
        LinkedList<Coordonnee> eat = new LinkedList<Coordonnee>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                    eat.add(new Coordonnee(i, j));
                }
            }
        }
        return eat;
    }
    public Piece copyPiece(){
        return new Roi(indice, num, couleur);
    }

    public Roi copyCim(){
        Roi p = new Roi(1,1,couleur);
        p.image = image;
        return p;
    }

}
