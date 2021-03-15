public class Deplacement{

	//Deplacement en largeur
	private int deplacementX;
	//Deplacement en hauteur
	private int deplacementY;
	
	//Coordonnee du depart
	private Coordonnee depart;
	//Coordonnee de l'arrivee
	private Coordonnee arrivee;
	
	//Constructeur de l'objet deplacement
	public Deplacement(Coordonnee depart, Coordonnee arrivee){
		
		//depart et arrivee
		this.depart = depart;
		this.arrivee = arrivee;
		
		//deplacement en X et Y
		deplacementX = arrivee.getX()-depart.getX();
		deplacementY = arrivee.getY()-depart.getY(); 
	}
	
	//renvoie le deplacement en X
	public double getDeplacementX() {
		return deplacementX;
	}

	//renvoie le deplacement en Y
	public double getDeplacementY() {
		return deplacementY;
	}
	
	//renvoie un objet Coordonnee de la case d'arrivee
	public Coordonnee getArrivee() {
		return arrivee;
	}
	
	//renvoie un objet coordonnee de la case de départ
	public Coordonnee getDepart() {
		return depart;
	}
	
	public void setDepart(Coordonnee departA){
		this.depart = departA;
	}
	
	public void setArrivee(Coordonnee arriveeA){
		this.arrivee = arriveeA;
	}
}
