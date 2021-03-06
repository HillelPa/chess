import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;


public class Fou extends Piece {

    public Fou(int aIndice, int aNum,boolean aCouleur){ // c true : blanc ; c false : noir

        super(aIndice, aNum,"Fou",4,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Fou_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Fou_Noir.png"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "fW " +super.toString();
        }else{
            return "fB " +super.toString();
        }
    }

    //liste de coordonnées ou on peut se deplacer
    public LinkedList<Coordonnee> listeDeplacementsPossibles(LinkedList<Piece> ech){
       LinkedList<Coordonnee> coups = new LinkedList<Coordonnee>();
       int j, i;

       j = y-1;
       i = x+1;
           while (caseVide(getNum(i, j), ech) && i < 8 && j >= 0) {
               coups.add(new Coordonnee(i, j));
               j--;
               i++;
           }
       j = y-1;
       i = x-1;
            while (caseVide(getNum(i, j), ech) && i >= 0 && j >= 0) {
                coups.add(new Coordonnee(i, j));
                j--;
                i--;
            }
       i = x-1;
       j = y+1;
            while (caseVide(getNum(i, j), ech) && i >= 0 && j < 8) {
                coups.add(new Coordonnee(i, j));
                j++;
                i--;
            }
       i = x+1;
       j = y+1;
            while (caseVide(getNum(i, j), ech) && i < 8 && j < 8) {
                coups.add(new Coordonnee(i, j));
                j++;
                i++;
            }
       return coups;
    }

    public LinkedList<Coordonnee> listEat(LinkedList<Piece> pieces){
        LinkedList<Coordonnee> eat = new LinkedList<Coordonnee>();
        int j, i;

        j = y-1;
        i = x+1;
        while (caseVide(getNum(i, j), pieces) && i < 8 && j >= 0) {
            if (casePleine(getNum(i, j), pieces, !couleur)) {
                eat.add(new Coordonnee(i, j));
                break;
            }
            i++;
            j--;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        j = y-1;
        i = x-1;
        while (caseVide(getNum(i, j), pieces) && i >= 0 && j >= 0) {
            if (casePleine(getNum(i, j), pieces, !couleur)) {
                eat.add(new Coordonnee(i, j));
                break;
            }
            i--;
            j--;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        i = x-1;
        j = y+1;
        while (caseVide(getNum(i, j), pieces) && i >= 0 && j < 8) {
            if (casePleine(getNum(i, j), pieces, !couleur)) {
                eat.add(new Coordonnee(i, j));
                break;
            }
            j++;
            i--;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));


        i = x+1;
        j = y+1;
        while (caseVide(getNum(i, j), pieces) && i < 8 && j < 8) {
            if (casePleine(getNum(i, j), pieces, !couleur)) {
                eat.add(new Coordonnee(i, j));
                break;
            }
            j++;
            i++;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        return eat;
    }

    public Piece copyPiece(){
        return new Fou(indice, num, couleur);
    }


}
