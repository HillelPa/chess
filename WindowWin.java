import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowWin extends JFrame implements ActionListener{
	
	JButton leave;
	JButton restart;
	String s;
	JPanel back;
	JLabel mess;
	
    public WindowWin(boolean couleur, boolean tps, int nbr){
    	//On enregistre les dimensions pour avoir une fenêtre adaptable à tous les écrans
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int width = (int) screenSize.getWidth();
    	int height = (int) screenSize.getHeight();
    	
    	int fenWidth = (int) (width/3.0);
    	int fenHeight = (int) (height/3.0);
    	
    	Color kaki = new Color(135,164,91);

    	//On crée la fenêtre de fin
        new JFrame("FIN DE LA PARTIE");
        setSize((int)(fenWidth),(int)(fenHeight));

        //On ajoute à la fenêtre le fond càd un JPanel sur lequel on va poser les boutons        
        back = new JPanel();
        back.setBounds(-100,-100,fenWidth+100,fenHeight+100);
        back.setLayout(null);
        back.setVisible(true);
        add(back);
        
        JLabel fond = new JLabel();
        fond.setBounds(0,0,this.getWidth(), this.getHeight());
        fond.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("fond.jpg")).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
        back.add(fond);
        
        s = victoire(couleur,tps,nbr);
        System.out.println(s);
        		
        mess = new JLabel(s,JLabel.CENTER);
        mess.setLayout(null);
        mess.setBounds(0,0,(int)(this.getWidth()),(int)(this.getHeight()/4.0));
        mess.setForeground(Color.WHITE);
        fond.add(mess);
        
        
        leave = new JButton("Quitter");
        leave.setBounds(2*(int)(this.getWidth()/3.0),2*(int)(this.getHeight()/3.0),(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        leave.setForeground(Color.WHITE);
        leave.setBackground(kaki);
        leave.addActionListener(this);
        fond.add(leave);
        
     
        restart = new JButton("Recommencer");
        restart.setBounds((int)(this.getWidth()/12.0),2*(int)(this.getHeight()/3.0),(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        restart.setForeground(Color.WHITE);
        restart.setBackground(kaki);
        restart.addActionListener(this);
        fond.add(restart);
        
        setLocationRelativeTo(null);
        setResizable(false);        
        setAlwaysOnTop(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        
    }
    // Selon le type de fin (mat,tps,abandon) on affiche la fenetre de fin avec un message différent donnant le vainqueur)   
    public String victoire(boolean couleur, boolean tps, int nbr){
    	s = "";
    	if(tps == true && nbr == 0) {
    		s += "Le temps limite des ";
            if(!couleur){
           		s += "Blancs est dépassé, les Noirs ";
           	}else{
           		s += "Noirs est dépassé, les Blancs ";
           	}
           	s += "ont gagné !";
    	
    	}if(tps == false && nbr ==0){
        	s = "Echec et mat, les ";
            if(couleur){
                s += "Blancs ";
            }else{
                s += "Noirs ";
            }
            s += "ont gagné !"; 
            
    	}if(couleur == true && tps == false && nbr == 1   ) {
    		s = "Les Blancs ont concédé, victoire des Noirs par abandon !";
    	}if(couleur == false && tps == false && nbr == 1 ) {
    		s = "Les Noirs ont concédé, victoire des Blancs par abandon !"; 
    	}
    	return s;
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
