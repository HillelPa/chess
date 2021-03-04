import java.awt.*;
import javax.swing.*;

public class Reine extends Piece {
	
	
    public Reine(int aNum, boolean aCouleur){
        super(aNum,"Reine",5,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Reine_Blanche.png"));
        }else{
            image = new JLabel(new ImageIcon("Reine_Noire.png"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "qW";
        }else{
            return "qB";
        }
    }

    public boolean typeDeplacement(Deplacement deplacement){

        // La reine peut se deplacer de sur les lignes et en diagonale

        if(Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) == 0 |
                deplacement.getDeplacementX() * deplacement.getDeplacementY() == 0){

            return true;
        }
        return false;
    }
}
