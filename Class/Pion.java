public class Pion extends Piece {

	public Pion(boolean aCouleur, int num){ // c true : blanc ; c false : noir
	
	super("pion",aCouleur);
	valeur = 0;
		if(aCouleur){
			coordonneeY = 1;
		}else{
			coordonneeY = 7;
		}
	coordonneeX = (num - 1);
	}
	
	public String toString(){
		if(couleur){
			return "pW";
		}else{
			return "pB";
		}
	}
}
