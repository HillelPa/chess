import java.awt.*;
	
public class Pion extends Piece {


	
	public Pion(boolean aCouleur){ // c true : blanc ; c false : noir
	
	super("Pion",0,aCouleur);
	
	Toolkit T = Toolkit.getDefaultToolkit();
	if(aCouleur){
            image = T.getImage("Pion_Blanc.png");
        }else{
            image = T.getImage("Pion_Noir.png");
        }
	}
	
	public String toString(){
		if(couleur){
			return "pW";
		}else{
			return "pB";
		}
	}
	
	public boolean typeDeplacement(Deplacement deplacement){
		
		//Le pion ne peut pas se deplacer sur X et peut se deplacer de 1 ou 2 case en avant selon se position de départ
		//Ici n'est pas codé le fait de pouvoir manger une pièce en diagonale
		
		if (deplacement.getDeplacementX() == 0){
			if(Math.abs(deplacement.getDeplacementY()) == 2 && deplacement.getDepart().getY() == 6){
				return true;
			} else {
				if(Math.abs(deplacement.getDeplacementY()) == 1){
					return true;
				}
			}
		}
		return false;
	}
}
