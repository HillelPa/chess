import java.awt.*;

public class Case implements Comparable<Case>{
	
	public boolean couleur; // true : blanche ; false : noire
	public Piece piece;
	public int x;
	public int y;
	public int num; //numéro de la case entre 0 et 63
	
	public Case(int aNum, boolean aCouleur){ //case libre
		couleur = aCouleur;
		num = aNum;
		maj();
	}
	
	public Case(int aNum, boolean aCouleur, Piece aPiece){
		num = aNum;
		couleur = aCouleur;
		piece = aPiece;
		maj();
	}
	
	public String toString(){
		return "case "+couleur+" avec comme piece "+piece+" case num : "+num+" position : ["+x+":"+y+"]\n";
	}

	public int [][] getArea(){
		int [][] area = {{x*86, y*86},{(x+1)*86, (y+1)*86}};
		return area;
	}

	public void switchCase(Case aSwitch){
		System.out.println("On switch");
		int newNum = aSwitch.num;
		aSwitch.num = num;
		num = newNum;
		aSwitch.maj();
		maj();

		System.out.println(this);
		System.out.println(aSwitch);
	}

	public int compareTo(Case autreCase){
		if(this.num < autreCase.num){
			return -1;
		} else if (this.num > autreCase.num){
			return 1;
		} else {
			return 0;
		}
	}

	public void maj(){
		this.y = (int)(num/8);
		this.x = num%8;
	}
}
