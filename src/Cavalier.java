import java.awt.*;
import javax.swing.*;

public class Cavalier extends Piece {
	
	
    public Cavalier(int aNum,boolean aCouleur){ // c true : blanc ; c false : noir

        super(aNum,"Cavalier",2,aCouleur);

        if(aCouleur){
            image = new JLabel(new ImageIcon("Cavalier_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Cavalier_Noir.png"));
        }
        image.setSize(80,80);
    }

    public String toString(){
        if(couleur){
            return "cW";
        }else{
            return "cB";
        }
    }

    public boolean typeDeplacement(Deplacement deplacement){

        //Le cavalier se déplace en forme de L

        if((Math.abs(deplacement.getDeplacementX()) == 2 && Math.abs(deplacement.getDeplacementY()) == 1 )|
                (Math.abs(deplacement.getDeplacementX()) == 1 && Math.abs(deplacement.getDeplacementY()) == 2)){
            return true;
        }
        return false;
    }

}
