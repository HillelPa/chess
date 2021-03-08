import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public abstract class Piece extends JFrame implements Comparable<Piece>{
	
	//IMAGE
	public JLabel image;
	
	//Nom de la piece
	public String nom;
	
	//Valeur de la piece
	public int valeur;
	
	//Couleur de la piece, false noir, true blanc
	public boolean couleur;
	
	// Coordonnées
	public int x;
	public int y;

	public int num; //numéro de la case entre 0 et 63
	
	//contructeur de la piece (nom, couleur, coordonnées)
	public Piece(int aNum, String aNom, int aValeur, boolean aCouleur) {
		num = aNum;
		nom = aNom;
		valeur = aValeur;
		couleur = aCouleur;
		maj();
		
	}

	public void maj(){
		this.y = (int)(num/8);
		this.x = num%8;
	}

	public int compareTo(Piece autrePiece){
		if(this.num < autrePiece.num){
			return -1;
		} else if (this.num > autrePiece.num){
			return 1;
		} else {
			return 0;
		}
	}

	public void majLocation(){
		image.setLocation(x*86+3, y*86+3);
	}

	public int getNum(int i, int j){
		return 8*i + j;
	}

	//méthode donnant le type de déplacement unique à chaque type de piece, retourne true pour chaque case si le deplacement y est possible
	public abstract boolean typeDeplacement(Deplacement deplacement, LinkedList<Piece> ech);

	public boolean caseVide(int i, LinkedList<Piece> ech){
		for(Piece p : ech){
			if(p.num == i){
				return true;
			}
		}
		return false;
	}
}
