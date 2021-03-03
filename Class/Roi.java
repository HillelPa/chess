import java.awt.*;
import javax.swing.*;

public class Roi extends Piece {

	
    public Roi(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Roi",6,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Roi_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Roi_Noir.png"));
        }
    }

    public String toString(){
        if(couleur){
            return "kW";
        }else{
            return "kB";
        }
    }

    public boolean typeDeplacement(Deplacement deplacement){

        //Le roi se déplace d'une case dans n'import quel sens

        if(Math.abs(deplacement.getDeplacementX() * deplacement.getDeplacementY()) <= 1 &&
                Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) <= 1 &&
                Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) >= -1){
            return true;
        }
        return false;
    }
}
