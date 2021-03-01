import java.awt.*;

public abstract class Piece {
	
	//IMAGE
	public Image image;
	
	//Nom de la piece
	public String nom;
	
	//Valeur de la piece
	public int valeur;
	
	//Couleur de la piece, false noir, true blanc
	public boolean couleur;
	
	// Coordonnées
	//public int coordonneeX;
	//public int coordonneeY;
	
	//contructeur de la piece (nom, couleur, coordonnées)
	public Piece(String aNom, int aValeur, boolean aCouleur) {
		
		nom = aNom;
		valeur = aValeur;
		couleur = aCouleur;
		
		
	}
	
	//rajouter eventuellement ici une méthode test de déplacement unique à chaque type de piece
}
