import java.awt.*;

public class Reine extends Piece {
	
	
    public Reine(boolean aCouleur){
        super("Reine",5,aCouleur);
        
        Toolkit T = Toolkit.getDefaultToolkit();
        if(aCouleur){
            image = T.getImage("Reine_Blanche.png");
        }else{
            image = T.getImage("Reine_Noire.png");
        }
    }

    public String toString(){
        if(couleur){
            return "qW";
        }else{
            return "qB";
        }
    }
}
