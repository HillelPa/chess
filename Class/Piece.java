public abstract class Piece {
	
	//Nom de la piece
	public String nom;
	
	//Valeur de la piece
	public int valeur;
	
	//Couleur de la piece, false noir, true blanc
	public boolean couleur;
	
	// Coordonnées
	public int coordonneeX;
	public int coordonneeY;
	
	//contructeur de la piece (nom, couleur, coordonnées)
<<<<<<< HEAD
	public Piece(String aNom, int aValeur, boolean aCouleur, int aCoordonneeX, int aCoordonneeY) {
=======
	public Piece(String aNom, boolean aCouleur) {
>>>>>>> 30684fe40bcd1954d2bd892886a7f6359110a170
		
		nom = aNom;
		valeur = aValeur;
		couleur = aCouleur;
		
		
	}
	
	//rajouter eventuellement ici une méthode test de déplacement unique à chaque type de piece
}
