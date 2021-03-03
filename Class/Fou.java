import java.awt.*;
import javax.swing.*;

public class Fou extends Piece {

    public Fou(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Fou",4,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Fou_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Fou_Noir.png"));
        }
    }

    public String toString(){
        if(couleur){
            return "fW";
        }else{
            return "fB";
        }
    }

    public boolean typeDeplacement(Deplacement deplacement){

        //Le fou se déplace sur les diagonales de n'importe quel nombre de cases

        if((Math.abs(deplacement.getDeplacementX()))-(Math.abs(deplacement.getDeplacementY()))==0){
            return true;
        }
        return false;
    }

}
