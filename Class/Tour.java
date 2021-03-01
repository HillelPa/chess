import java.awt.*;

public class Tour extends Piece {
	
    public Tour(boolean aCouleur){ 		// c true : blanc ; c false : noir
        
        super("Tour",3,aCouleur);
        Toolkit T = Toolkit.getDefaultToolkit();
        
        if(aCouleur){
            image = T.getImage("Tour_Blanche.png");
        }else{
            image = T.getImage("Tour_Noire.png");
        }
    }

    public String toString(){
        if(couleur){
            return "tW";
        }else{
            return "tB";
        }
    }
}
