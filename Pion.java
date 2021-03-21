import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
	
public class Pion extends Piece {


	
	public Pion(int aIndice, int aNum,boolean aCouleur){ // c true : blanc ; c false : noir
	
	super(aIndice, aNum,"Pion",0,aCouleur);

	if(aCouleur){
            image = new JLabel(new ImageIcon("Pion_Blanc.png"));
        }else{
            image = new JLabel(new ImageIcon("Pion_Noir.png"));
        }
		image.setSize(80,80);
	}


	public String toString(){
		if(couleur){
			return "pW " +super.toString();
		}else{
			return "pB " +super.toString();
		}
	}

	// méthode qui créer une liste des Coordonnées de deplacements possibles
	public LinkedList<Coordonnee> listeDeplacementsPossibles(LinkedList<Piece> ech){
		LinkedList<Coordonnee> coups = new LinkedList<Coordonnee>();
		int i = x;
		int j = y;
		if(couleur) {
			j = y - 1;
				if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
					coups.add(new Coordonnee(i, j));
				}
			j = y - 2;
				if (y == 6 && caseVide(getNum(i, j), ech)) {
					coups.add(new Coordonnee(i, j));
				}
		}else{
			j = y + 1;
				if (caseVide(getNum(i, j), ech) && i < 8 && i >= 0 && j < 8 && j >= 0) {
					coups.add(new Coordonnee(i, j));
				}
			j = y + 2;
				if (y == 1 && caseVide(getNum(i, j), ech)) {
					coups.add(new Coordonnee(i, j));
				}
			}
	return coups;
	}

	//méthode qui créer une liste de Coordonnées de deplacements ou on peut manger
	public LinkedList<Coordonnee> listEat(LinkedList<Piece> pieces){
		LinkedList<Coordonnee> eat = new LinkedList<Coordonnee>();
		int i = x;
		int j = y;

		if(couleur){
			j-= 1;
		}else{
			j+= 1;
		}
		for(i = x-1; i<= x+1; i+= 2) {
			if (casePleine(getNum(i, j), pieces, !couleur) && i>= 0 && i<8)
				eat.add(new Coordonnee(i, j));
		}
		return eat;
	}

	public Piece copyPiece(){
		return new Pion(indice, num, couleur);
	}

	public Pion copyCim(){
		Pion p = new Pion(1,1, couleur);
		if(couleur){
			p.image = new JLabel(new ImageIcon("Pion_Blanc.png"));
		}else{
			p.image = new JLabel(new ImageIcon("Pion_Noir.png"));
		}
		p.image.setSize(80,80);
		return p;
	}
}
