import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Cimetiere extends JPanel implements ActionListener{
	JButton giveup_B;
	JButton giveup_N;
	
	public Cimetiere() {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)screenSize.getWidth();
	int height = (int)screenSize.getHeight();
		
	new JPanel();
	setBounds(0,0,width, height);
    setOpaque(false);
    setLayout(null);
    
	giveup_B = new JButton("Abandonner");
	giveup_B.setBounds(20,50,120,70);
	giveup_B.setLayout(null);	
	giveup_B.setBackground(Color.WHITE);
	giveup_B.setForeground(Color.BLACK);
	giveup_B.addActionListener(this);
	add(giveup_B);
	
	giveup_N = new JButton("Abandonner");
	giveup_N.setBounds(width/2 + 540,50,120,70);
	giveup_N.setLayout(null);
	giveup_N.setBackground(Color.BLACK);
	giveup_N.setForeground(Color.WHITE);
	giveup_N.addActionListener(this);
	add(giveup_N);

	}
	
	public void actionPerformed(ActionEvent e) {
		JFrame ggFrame;
		JPanel panelFin = new JPanel();			

		JButton restart = new JButton("Recommencer");
		restart.setBounds(50,50,200,200);
		restart.setLayout(null);
		
		ggFrame = new JFrame("Fin de la partie");
		ggFrame.setSize(300,300);
		ggFrame.add(panelFin);
		
		
		panelFin.add(restart);
		
		if(e.getSource() == giveup_B){			
			JLabel bravo = new JLabel("Les blancs ont concédé !\n Victoire pour les noirs !");			
			panelFin.add(bravo);
			ggFrame.setVisible(true);
			
		}else {
			JLabel bravo = new JLabel("Les noirs ont concédé !\n Victoire pour les blancs !");			
			panelFin.add(bravo);
			ggFrame.setVisible(true);
		}
	}
	
}
