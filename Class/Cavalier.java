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
            return "cB";
        }else{
            return "cN";
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
