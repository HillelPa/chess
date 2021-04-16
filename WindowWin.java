import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowWin extends JFrame implements ActionListener{
	
	JButton leave;
	JButton restart;
	String s;
	JLabel mess;
	
    public WindowWin(){
    	//On enregistre les dimensions pour avoir une fenêtre adaptable à tous les écrans
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int width = (int) screenSize.getWidth();
    	int height = (int) screenSize.getHeight();
    	
    	int fenWidth = (int) (width/3.0);
    	int fenHeight = (int) (height/3.0);

    	//On crée la fenêtre de fin
        new JFrame("FIN DE LA PARTIE");
        setSize((int)(fenWidth),fenHeight);
        setLocationRelativeTo(null);
        setResizable(false);        
        setAlwaysOnTop(true);
        setVisible(true);

        //On ajoute à la fenêtre le fond càd un JPanel sur lequel on va poser les boutons        
        JPanel back = new JPanel();
        back.setBounds(-100,-100,fenWidth+100,fenHeight+100);
        back.setBackground(Color.DARK_GRAY);
        back.setLayout(null);
        back.setVisible(true);
        //add(back);
        
        
        leave = new JButton("Quitter");
        leave.setBounds(2*fenWidth,2*fenHeight,(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        leave.setForeground(Color.DARK_GRAY);
        leave.setBackground(Color.GREEN);
        leave.addActionListener(this);
        //add(leave);
        
     
        restart = new JButton("Recommencer");
        restart.setBounds((int)(this.getWidth()/12.0),2*fenHeight,(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        restart.setForeground(Color.DARK_GRAY);
        restart.setBackground(Color.GREEN);
        restart.addActionListener(this);
        //back.add(restart);
                
        
    }
    // Selon le type de fin (mat,tps,abandon) on affiche la fenetre de fin avec un message différent donnant le vainqueur)   
    public WindowWin(boolean couleur, boolean tps){
    	WindowWin endGame = new WindowWin();
    	s = "";
    	if(tps == true) {
    		s = "Le temps limite des ";
            if(!couleur){
           		s += "Blancs est dépassé, les Noirs ";
           	}else{
           		s += "Noirs est dépassé, les Blancs ";
           	}
           	s += "ont gagné !";
    	
    	}else{
        	s = "Echec et mat, les ";
            if(couleur){
                s += "Blancs ";
            }else{
                s += "Noirs ";
            }
        s += "ont gagné !";        
    	}
    	
        System.out.println(s);
        mess = new JLabel(s,JLabel.CENTER);
        endGame.add(mess);
    }
    
    public WindowWin(int nbr) {
    	WindowWin endGame = new WindowWin();
    	if(nbr == 1) {
    		s = "Les Blancs ont concédé, victoire des Noirs par abandon!";
    	}else {
    		s = "Les Noirs ont concédé, victoire des Blancs par abandon!"; 
    	}
    	System.out.println(s);
        mess = new JLabel(s,JLabel.CENTER);
        endGame.add(mess);
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == leave){
			System.exit(0);
		}
        if(e.getSource() == restart){
        	new Home();
        }
			
	}
		
}
