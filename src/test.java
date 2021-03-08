import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
	
public class Test{
	
	public boolean bloqueDeplacement(Deplacement deplacement, LinkedList<Piece> ech){
		
		for(Piece p : ech){
			if(p.num == 8*deplacement.getArrivee().getX()+deplacement.getArrivee().getY() ){
				return false;
			} else {
				return true;
			}
		}
		return false;
		
	}
}


