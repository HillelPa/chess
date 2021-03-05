import java.awt.*;
import javax.swing.*;
	
public class Pion extends Piece {


	
	public Pion(int aNum,boolean aCouleur){ // c true : blanc ; c false : noir
	
	super(aNum,"Pion",0,aCouleur);

	if(aCouleur){
            image = new JLabel(new ImageIcon("Pion_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Pion_Noir.png"));
        }
		image.setSize(80,80);
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
			if(Math.abs(deplacement.getDeplacementY()) == 2 && deplacement.getDepart().getY() == 6 ||
			Math.abs(deplacement.getDeplacementY()) == 2 && deplacement.getDepart().getY() == 1){
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
