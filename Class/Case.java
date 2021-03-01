public class Case {
	
	public boolean couleur; // true : blanche ; false : noire
	public Piece piece;
	
	public Case(boolean aCouleur){ //case libre
		couleur = aCouleur;
	}
	
	public Case(boolean aCouleur, Piece aPiece){
		couleur = aCouleur;
		piece = aPiece;
	}
	
	public String toString(){
		return "case "+couleur+" avec comme piece "+piece;
	}
}
