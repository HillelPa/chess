import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;

public class EchiquierG extends JFrame{
public Echiquier ech;
	
	public EchiquierG(Echiquier aEch){
		
		ech = aEch;
		
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
					if(ech.echiquier[i][j].piece != null){
						g.drawImage(ech.echiquier[i][j].piece.image,xP + 3 + j*86,yP + 3 +  i*86,80,80,this);
					}
				}
			}
		
		
    }
	
}
