public class Pion extends Piece {

	public Pion(boolean aCouleur){ // c true : blanc ; c false : noir
	
	super("pion",aCouleur);
	valeur = 0;
	}
	
	public String toString(){
		if(couleur){
			return "pW";
		}else{
			return "pB";
		}
	}
}
