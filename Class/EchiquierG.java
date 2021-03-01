import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;

public class EchiquierG extends JFrame{
	
	Case [][] echiquier;
	
	public EchiquierG(){
		
		echiquier = init();
		
		//DIMENSION DE L'ECRAN
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        int larg = width - 50;
        int haut = height - 100;

        //FENETRE
        setBounds(width/2 - larg/2 , height/2 - haut/2, larg, haut);
        setTitle("Game");
        setResizable(false);
        setLayout(null);
        
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	public void paint(Graphics g) {
		Toolkit T = Toolkit.getDefaultToolkit();
		
		int largP = 688;
		int xP = (getWidth()-largP)/2;
		int yP = (getHeight()-largP)/2;
		g.drawImage(T.getImage("150.png"),xP,yP,largP,largP,this);

			for(int i = 0; i<8; i++){
				for(int j = 0; j<8; j++){
					if(echiquier[i][j].piece != null){
						g.drawImage(echiquier[i][j].piece.image,xP + 3 + j*86,yP + 3 +  i*86,80,80,this);
					}
				}
			}	
    }
    
    public Case [][] init(){
		Case [][] echiquier = new Case[8][8];
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(i % 2 == 1 ^ j % 2 == 1){
				echiquier[i][j] = new Case(false);
				}else{
				echiquier[i][j] = new Case(true);
				}
			}
		}
		
		echiquier[0][0].piece = new Tour(false);
		echiquier[0][1].piece = new Cavalier(false);
		echiquier[0][2].piece = new Fou(false);
		echiquier[0][3].piece = new Reine(false);
		echiquier[0][4].piece = new Roi(false);
		echiquier[0][5].piece = new Fou(false);
		echiquier[0][6].piece = new Cavalier(false);
		echiquier[0][7].piece = new Tour(false);
		
		for(int j = 0; j<8; j++){
			echiquier[1][j].piece = new Pion(false);
		}
		
		echiquier[7][0].piece = new Tour(true);
		echiquier[7][1].piece = new Cavalier(true);
		echiquier[7][2].piece = new Fou(true);
		echiquier[7][3].piece = new Reine(true);
		echiquier[7][4].piece = new Roi(true);
		echiquier[7][5].piece = new Fou(true);
		echiquier[7][6].piece = new Cavalier(true);
		echiquier[7][7].piece = new Tour(true);
		
		for(int j = 0; j<8; j++){
			echiquier[6][j].piece = new Pion(true);
		}
		return echiquier;
	}
		 
	
}
