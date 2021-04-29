import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowWin extends JFrame implements ActionListener{
	
	JButton leave;
	JButton restart;
	String s;
	JPanel back;
	JLabel mess;
	Echiquier ech;
	
    public WindowWin(boolean couleur, boolean tps, int nbr, Echiquier aEch){

        ech = aEch;

    	//On enregistre les dimensions pour avoir une fen�tre adaptable � tous les �crans
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int width = (int) screenSize.getWidth();
    	int height = (int) screenSize.getHeight();
    	
    	int fenWidth = (int) (width/3.0);
    	int fenHeight = (int) (height/3.0);
    	
    	Color kaki = new Color(135,164,91);

    	//On creer la fenetre de fin
        new JFrame("FIN DE LA PARTIE");
        setSize((int)(fenWidth),(int)(fenHeight));
        setTitle("Fin de la Partie");

        //On ajoute a la fenetre le fond c-à-d un JPanel sur lequel on va poser les boutons
        back = new JPanel();
        back.setBounds(-100,-100,fenWidth+100,fenHeight+100);
        back.setLayout(null);
        back.setVisible(true);
        add(back);
        
        JLabel fond = new JLabel();
        fond.setBounds(0,0,this.getWidth(), this.getHeight());
        fond.setIcon(new ImageIcon(new ImageIcon("fond.jpg").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
        back.add(fond);
        
        s = victoire(couleur,tps,nbr);

        mess = new JLabel(s,JLabel.CENTER);
        mess.setLayout(null);
        mess.setBounds(0,0,(int)(this.getWidth()),(int)(this.getHeight()/4.0));
        mess.setForeground(Color.WHITE);
        fond.add(mess);
        
        
        leave = new JButton("Quitter");
        leave.setBounds(2*(int)(this.getWidth()/3.0),2*(int)(this.getHeight()/3.0),(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        leave.setBackground(kaki);
        leave.addActionListener(this);
        fond.add(leave);
        
     
        restart = new JButton("Recommencer");
        restart.setBounds((int)(this.getWidth()/12.0),2*(int)(this.getHeight()/3.0),(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        restart.setBackground(kaki);
        restart.addActionListener(this);
        fond.add(restart);
        
        setLocationRelativeTo(null);
        setResizable(false);        
        setAlwaysOnTop(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }
    // Selon le type de fin (mat,tps,abandon) on affiche la fenetre de fin avec un message diff�rent donnant le vainqueur)   
    public String victoire(boolean couleur, boolean tps, int nbr){
    	s = "";
    	if(tps == true && nbr == 0) {
    		s += "Le temps limite des ";
            if(!couleur){
           		s += "Blancs est depasse, les Noirs ";
           	}else{
           		s += "Noirs est depasse, les Blancs ";
           	}
           	s += "ont gagne !";
    	
    	}if(tps == false && nbr ==0){
        	s = "Echec et mat, les ";
            if(couleur){
                s += "Blancs ";
            }else{
                s += "Noirs ";
            }
            s += "ont gagne !";
            
    	}if(couleur == false && tps == false && nbr == 1   ) {
    		s = "Les Blancs ont concede, victoire des Noirs par abandon !";
    	}if(couleur == true && tps == false && nbr == 1 ) {
    		s = "Les Noirs ont concede, victoire des Blancs par abandon !";
    	}
    	return s;
    }
    
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == leave){
			System.exit(0);
		}
        if(e.getSource() == restart){
            ech.dispose();
            this.dispose();
        	new Home();
        }			
	}
		
}
