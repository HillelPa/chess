public class Pion extends Piece {

	public Pion(boolean aCouleur){ // c true : blanc ; c false : noir
	
	super("Pion",0,aCouleur);
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
