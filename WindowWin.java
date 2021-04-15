import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowWin extends JFrame implements ActionListener{
	
	JButton leave;
	JButton restart;
	JPanel back;
	WindowWin endGame;
	
    public WindowWin(){
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int width = (int) screenSize.getWidth();
    	int height = (int) screenSize.getHeight();

    	
        new JFrame("FIN DE LA PARTIE");
        setSize((int)(width /3.0),(int)(height/3.0));
                
        back = new JPanel();
        back.setBounds(-100,-100,this.getWidth()+100,this.getHeight()+100);
        back.setBackground(Color.DARK_GRAY);
        back.setLayout(null);
        back.setVisible(true);
        
        
        leave = new JButton("Quitter");
        leave.setBounds(2*(int)(this.getWidth()/3.0),2*(int)(this.getHeight()/3.0),(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        leave.setForeground(Color.DARK_GRAY);
        leave.setBackground(Color.GREEN);
        leave.addActionListener(this);
        back.add(leave);
        
     
        restart = new JButton("Recommencer");
        restart.setBounds((int)(this.getWidth()/12.0),2*(int)(this.getHeight()/3.0),(int)(this.getWidth()/4.0),(int)(this.getHeight()/6.0));
        restart.setForeground(Color.DARK_GRAY);
        restart.setBackground(Color.GREEN);
        restart.addActionListener(this);
        back.add(restart);
        
        add(back);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setAlwaysOnTop(true);

    }
    
    public WindowWin(boolean couleur){
    	
        String s = "Echec et mat, les ";
            if(couleur){
                s += "Blancs ";
            }else{
                s += "Noirs ";
            }
        s += "ont gagné !";
        
        System.out.println(s);
        JLabel mess = new JLabel(s);
        mess.setBounds(0,0,this.getWidth(),(int)(this.getHeight()/2.0));
        
        
        endGame = new WindowWin(); 
        endGame.setVisible(true);
        endGame.add(back);
        back.add(mess);                
    }
    
    public WindowWin(boolean couleur,boolean tps){

        String s = "Le temps limite des ";
            if(!couleur){
                s += "Blancs est dépassé, les Noirs ";
            }else{
                s += "Noirs est dépassé, les Blancs ";
            }
        s += "ont gagné !";
        
        System.out.println(s);
    	endGame = new WindowWin();
    	endGame.setVisible(true);
        JLabel mess = new JLabel(s);
        mess.setBounds(0,0,this.getWidth(),(int)(this.getHeight()/2.0));
        mess.setVisible(true);
                
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
