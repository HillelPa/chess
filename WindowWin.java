import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowWin extends JFrame implements ActionListener{
	
    public WindowWin(){

        new JFrame("FIN DE LA PARTIE");
        setSize(400,200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setAlwaysOnTop(true);

    }
    
    public WindowWin(boolean couleur){
    	WindowWin endGame = new WindowWin();
        String s = "Echec et mat, les ";
            if(couleur){
                s += "Blancs ";
            }else{
                s += "Noirs ";
            }
        s += "ont gagné !";

        JLabel mess = new JLabel(s, JLabel.CENTER);

        endGame.add(mess);
    }
    
    public WindowWin(boolean couleur,boolean tps){
    	WindowWin endGame = new WindowWin();
        String s = "Le temps limite des ";
            if(!couleur){
                s += "Blancs est dépassé, les Noirs";
            }else{
                s += "Noirs est dépassé, les Blancs ";
            }
        s += "ont gagné !";

        JLabel mess = new JLabel(s, JLabel.CENTER);

        endGame.add(mess);
    }

	/*public void actionPerformed(ActionEvent e) {
		WindowWin endGame = new WindowWin();
		
	}*/
}
