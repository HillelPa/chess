import java.awt.*;
import javax.swing.*;

public class Tour extends Piece {
	
    public Tour(int aNum,boolean aCouleur){ 		// c true : blanc ; c false : noir
        
        super(aNum,"Tour",3,aCouleur);
        
        if(aCouleur){
            image = new JLabel(new ImageIcon("Tour_Blanche.png","Tour blanche"));
        }else{
            image = new JLabel(new ImageIcon("Tour_Noire.png","Tour blanche"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "tW";
        }else{
            return "tB";
        }
    }

    public boolean typeDeplacement(Deplacement deplacement){

        //La tour se déplace sur les ligne de n'importe quel nombre de cases

        if(deplacement.getDeplacementX() * deplacement.getDeplacementY() == 0){
            return true;
        }
        return false;
    }
}