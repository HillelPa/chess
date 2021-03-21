import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Tour extends Piece {
	
    public Tour(int aIndice, int aNum,boolean aCouleur){ 		// c true : blanc ; c false : noir
        
        super(aIndice, aNum,"Tour",3,aCouleur);
        
        if(aCouleur){
            image = new JLabel(new ImageIcon("Tour_Blanche.png","Tour blanche"));
        }else{
            image = new JLabel(new ImageIcon("Tour_Noire.png","Tour blanche"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "tW " +super.toString();
        }else{
            return "tB " +super.toString();
        }
    }

    // méthode qui créer une liste des Coordonnées de deplacements possibles
    public LinkedList<Coordonnee> listeDeplacementsPossibles(LinkedList<Piece> ech){
        LinkedList<Coordonnee> coups = new LinkedList<Coordonnee>();
        int j, i;
        j = y;
        i = x+1;
            while (caseVide(getNum(i, j), ech) && i < 8) {
                coups.add(new Coordonnee(i, j));
                i++;
            }
        j = y-1;
        i = x;
            while (caseVide(getNum(i, j), ech) && j >= 0) {
                coups.add(new Coordonnee(i, j));
                j--;
            }
        i = x-1;
        j = y;
            while (caseVide(getNum(i, j), ech) && i >= 0) {
                coups.add(new Coordonnee(i, j));
                i--;
            }
        i = x;
        j = y+1;
            while (caseVide(getNum(i, j), ech) && j < 8) {
                coups.add(new Coordonnee(i, j));
                j++;
            }
        return coups;
    }

    public LinkedList<Coordonnee> listEat(LinkedList<Piece> pieces){
        LinkedList<Coordonnee> eat = new LinkedList<Coordonnee>();
        int j, i;

        j = y;
        i = x+1;
        while (caseVide(getNum(i, j), pieces) && i<8) {
                if (casePleine(getNum(i, j), pieces, !couleur)) {
                    eat.add(new Coordonnee(i, j));
                    break;
                }
            i++;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        i = x-1;
        while (caseVide(getNum(i, j), pieces) && i >= 0) {
                if (casePleine(getNum(i, j), pieces, !couleur)) {
                    eat.add(new Coordonnee(i, j));
                    break;
                }
             i--;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        i = x;
        j = y-1;
        while (caseVide(getNum(i, j), pieces) && j>=0) {
                if (casePleine(getNum(i, j), pieces, !couleur)) {
                    eat.add(new Coordonnee(i, j));
                    break;
                }
            j--;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        j = y+1;
        while (caseVide(getNum(i, j), pieces) && j <8){
                if (casePleine(getNum(i, j), pieces, !couleur)) {
                    eat.add(new Coordonnee(i, j));
                    break;
                }
            j++;
        }
        if (casePleine(getNum(i, j), pieces, !couleur))
            eat.add(new Coordonnee(i, j));

        return eat;
    }

    public Piece copyPiece(){
        return new Tour(indice, num, couleur);
    }

    public Tour copyCim(){
        Tour p = new Tour(1,1,couleur);
        p.image = image;
        return p;
    }
}
