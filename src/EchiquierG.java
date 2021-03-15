
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.util.Collections;
import java.util.LinkedList;

public class EchiquierG extends JFrame implements MouseListener, MouseMotionListener {

	LinkedList<Piece> ech;

	JPanel plateau;
	JPanel grille;
	JPanel cimetiere;

	Piece PieceSelect;
	boolean sourisInt;

	public EchiquierG() {

		//DIMENSION DE L'ECRAN
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		int larg = width - 50;
		int haut = height - 100;

		//FENETRE
		setSize(larg, haut);
		setLocationRelativeTo(null);
		setTitle("Game");
		setResizable(false);
		setLayout(null);

		//JPanels
		plateau = new JPanel();
		int largP = 688;
		plateau.setBounds((getWidth() - largP) / 2, (getHeight() - largP) / 2, largP, largP);
		plateau.setLayout(null);

		grille = new Grille(); //toute la partie avec les pieces qui bougent sont dans une autre classe
		cimetiere = new Cimetiere();
		add(cimetiere);
		
		//Image plateau
		JLabel imgPlateau = new JLabel(new ImageIcon("150.png"));
		imgPlateau.setBounds(0, 0, largP, largP);



		plateau.add(grille);
		plateau.add(imgPlateau);
		add(plateau);

		grille.addMouseListener(this);
		grille.addMouseMotionListener(this);

		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}



}
