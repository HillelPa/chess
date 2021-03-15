// classe objet créant l'objet coordonnée

public class Coordonnee{
	
	private int coordonneeX;
	
	private int coordonneeY;
	
	public Coordonnee(int colonne, int ligne){
		
		//construit la coordonnee en X
		coordonneeX = colonne;
		
		//construit la coordonnee en Y
		coordonneeY = ligne;
		
	}

	public Coordonnee(Coordonnee c){
		coordonneeX = c.coordonneeX;
		coordonneeY = c.coordonneeY;
	}
	
	//obtenir une coordonnee en X
	public int getX() {
		return coordonneeX;
	}
	
	//obtenir une coordonnee en Y
	public int getY() {
		return coordonneeY;
	}
	
	//modifier une cordonnee en X
	public void setX(int colonne) {
		coordonneeX = colonne;
	}
	
	//modifier une coordonnee en Y
	public void setY(int ligne) {
		coordonneeY = ligne;
	}

	//renvoie true si deux positions sont egales
	public boolean equals(Coordonnee coordonnee) {
		return coordonnee.getX() == this.getX()
				&& coordonnee.coordonneeY == this.coordonneeY;
	}

	public String toString(){
		return  coordonneeX +" : "+coordonneeY;
	}

	
}
