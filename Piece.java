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

	final int indice; //numéro de la piece, valeur fixe !
	
	//contructeur de la piece (nom, couleur, coordonnées)
	public Piece(int aIndice, int aNum, String aNom, int aValeur, boolean aCouleur) {
		indice = aIndice;
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
		return 8*j + i ;
	}

	public boolean caseVide(int i, LinkedList<Piece> ech){
		for(Piece p : ech){
			if(p.num == i){
				return false;
			}
		}
		return true;
	}

	public boolean casePleine(int i, LinkedList<Piece> pieces, boolean couleur){
		for(Piece p : pieces){
			if(p.num == i && p.couleur == couleur){
				return true;
			}
		}
		return false;
	}

	public boolean equals(Piece p){
		if(p.num == num){
			return true;
		}else{
			return false;
		}
	}

	public String toString(){
		return "en position "+num;
	}

	// méthode qui créer une liste des Coordonnées de deplacements possibles
	public abstract LinkedList<Coordonnee> listeDeplacementsPossibles(LinkedList<Piece> ech);
	public abstract LinkedList<Coordonnee> listEat(LinkedList<Piece> pieces);
	public abstract Piece copyPiece();
	public abstract Piece copyCim();
}
