import java.awt.*;

public class Fou extends Piece {

    public Fou(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Fou",4,aCouleur);
        
        Toolkit T = Toolkit.getDefaultToolkit();

        if(aCouleur){
            image = T.getImage("Fou_Blanc.png");
        }else{
            image = T.getImage("Fou_Noir.png");
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
		if((Math.abs(deplacement.getDeplacementX()))-(Math.abs(deplacement.getDeplacementY()))==0){
			return true;
		}
		return false;
	}
}
