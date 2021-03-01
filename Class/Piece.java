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
	public Piece(String aNom, boolean aCouleur) {
		
		nom = aNom;
		couleur = aCouleur;
		
		
	}
	
	//rajouter eventuellement ici une méthode test de déplacement unique à chaque type de piece
}
