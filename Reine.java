import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Reine extends Piece {
	
	
    public Reine(int aIndice, int aNum, boolean aCouleur){
        super(aIndice, aNum,"Reine",5,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Reine_Blanche.png"));
        }else{
            image = new JLabel(new ImageIcon("Reine_Noire.png"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "qW " +super.toString();
        }else{
            return "qB " +super.toString();
        }
    }

    // méthode qui créer une liste des Coordonnées de deplacements possibles
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
        return new Reine(indice, num, couleur);
    }
    public Reine copyCim(){
        Reine p = new Reine(1,1,couleur);
        p.image = image;
        return p;
    }

}
