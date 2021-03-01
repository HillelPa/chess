import java.awt.*;

public class Cavalier extends Piece {
	
	
    public Cavalier(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Cavalier",2,aCouleur); 
          
        Toolkit T = Toolkit.getDefaultToolkit();
        if(aCouleur){
            image = T.getImage("Cavalier_Blanc.png");
        }else{
            image = T.getImage("Cavalier_Noir.png");
        }
    }

    public String toString(){
        if(couleur){
            return "cW";
        }else{
            return "cB";
        }
    }
}
