import java.awt.*;

public class Roi extends Piece {

	
    public Roi(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Roi",6,aCouleur);
        
        Toolkit T = Toolkit.getDefaultToolkit();
        if(aCouleur){
            image = T.getImage("Roi_Blanc.png");
        }else{
            image = T.getImage("Roi_Noir.png");
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
