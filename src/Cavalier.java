import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Cavalier extends Piece {
	
	
    public Cavalier(int aNum,boolean aCouleur){ // c true : blanc ; c false : noir

        super(aNum,"Cavalier",2,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Cavalier_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Cavalier_Noir.png"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "cW " +super.toString();
        }else{
            return "cB " +super.toString();
        }
    }

    // méthode qui créer une liste des Coordonnées de deplacements possibles
    public LinkedList<Coordonnee> listeDeplacementsPossibles(LinkedList<Piece> ech){
        LinkedList<Coordonnee> coups = new LinkedList<Coordonnee>();
        int i, j;

            // 8 positions possibles
            i = x + 2;
            j = y - 1;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x + 1;
            j = y - 2;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x - 1;
            j = y - 2;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x - 2;
            j = y - 1;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x - 2;
            j = y + 1;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x - 1;
            j = y + 2;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x + 1;
            j = y + 2;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }
            i = x + 2;
            j = y + 1;
            if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
                coups.add(new Coordonnee(i, j));
            }

        return coups;
    }

    public LinkedList<Coordonnee> listEat(LinkedList<Piece> pieces){
        LinkedList<Coordonnee> eat = new LinkedList<Coordonnee>();
        int i, j;

        // 8 positions possibles
        i = x + 2;
        j = y - 1;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x + 1;
        j = y - 2;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x - 1;
        j = y - 2;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x - 2;
        j = y - 1;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x - 2;
        j = y + 1;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x - 1;
        j = y + 2;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x + 1;
        j = y + 2;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }
        i = x + 2;
        j = y + 1;
        if (casePleine(getNum(i, j), pieces, !couleur) && i < 8 && i >= 0 && j < 8 && j >= 0) {
            eat.add(new Coordonnee(i, j));
        }

        return eat;
    }

}
