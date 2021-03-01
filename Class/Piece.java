public abstract class Piece {
	
	//Nom de la piece
	private String nom;
	
	//Valeur de la piece
	private int valeur;
	
	//Couleur de la piece, false noir, true blanc
	private boolean couleur;
	
	// Coordonnées
	private int coordonneeX;
	private int coordonneeY;
	
	//contructeur de la piece (nom, couleur, coordonnées)
	public Piece(String aNom, int aValeur, boolean aCouleur, int aCoordonneeX, int aCoordonneeY) {
		
		nom = aNom;
		valeur = aValeur;
		couleur = aCouleur;
		CoordonneeX = aCoordonneeX;
		CoordonneeY = aCoordonneeY;
		
	}
	
	//rajouter eventuellement ici une méthode test de déplacement unique à chaque type de piece
}
