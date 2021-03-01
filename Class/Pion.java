import java.awt.*;
	
public class Pion extends Piece {


	
	public Pion(boolean aCouleur){ // c true : blanc ; c false : noir
	
	super("Pion",0,aCouleur);
	
	Toolkit T = Toolkit.getDefaultToolkit();
	if(aCouleur){
            image = T.getImage("Pion_Blanc.png");
        }else{
            image = T.getImage("Pion_Noir.png");
        }
	}
	
	public String toString(){
		if(couleur){
			return "pW";
		}else{
			return "pB";
		}
	}
}
