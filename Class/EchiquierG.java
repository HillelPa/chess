import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.util.Collections;
import java.util.LinkedList;

public class EchiquierG extends JFrame implements MouseListener {

	Case[][] echiquier;

	LinkedList<Case> ech;

	JPanel plateau;
	JPanel grille;
	int [][] area = new int [2][2];
	GridBagConstraints gbc = new GridBagConstraints();
	Case caseSelect;

	public EchiquierG() {

		ech = init2();

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

		grille = new JPanel();
		grille.setBounds(0, 0, largP, largP);
		grille.setOpaque(false);
		

		grille.setLayout(new GridBagLayout());

		//Image plateau
		JLabel imgPlateau = new JLabel(new ImageIcon("150.png"));
		imgPlateau.setBounds(0, 0, largP, largP);

		affichage();

		plateau.add(grille);
		plateau.add(imgPlateau);
		add(plateau);

		grille.addMouseListener(this);

		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public LinkedList<Case> init2() {
		LinkedList<Case> ech = new LinkedList<Case>();
		for (int i = 0; i < 64; i++) {
			switch (i) {
				case 0:
					ech.addLast(new Case(i, i % 2 == 0, new Tour(false)));
					break;
				case 1:
					ech.addLast(new Case(i, i % 2 == 0, new Cavalier(false)));
					break;
				case 2:
					ech.addLast(new Case(i, i % 2 == 0, new Fou(false)));
					break;
				case 3:
					ech.addLast(new Case(i, i % 2 == 0, new Reine(false)));
					break;
				case 4:
					ech.addLast(new Case(i, i % 2 == 0, new Roi(false)));
					break;
				case 5:
					ech.addLast(new Case(i, i % 2 == 0, new Fou(false)));
					break;
				case 6:
					ech.addLast(new Case(i, i % 2 == 0, new Cavalier(false)));
					break;
				case 7:
					ech.addLast(new Case(i, i % 2 == 0, new Tour(false)));
					break;
				case 56:
					ech.addLast(new Case(i, i % 2 == 0, new Tour(true)));
					break;
				case 57:
					ech.addLast(new Case(i, i % 2 == 0, new Cavalier(true)));
					break;
				case 58:
					ech.addLast(new Case(i, i % 2 == 0, new Fou(true)));
					break;
				case 59:
					ech.addLast(new Case(i, i % 2 == 0, new Reine(true)));
					break;
				case 60:
					ech.addLast(new Case(i, i % 2 == 0, new Roi(true)));
					break;
				case 61:
					ech.addLast(new Case(i, i % 2 == 0, new Fou(true)));
					break;
				case 62:
					ech.addLast(new Case(i, i % 2 == 0, new Cavalier(true)));
					break;
				case 63:
					ech.addLast(new Case(i, i % 2 == 0, new Tour(true)));
					break;
			}
			if (i > 7 && i < 16) {
				ech.addLast(new Case(i, i % 2 == 0, new Pion(false)));
			}

			if (i > 15 && i < 48) {
				ech.addLast(new Case(i, i % 2 == 0, new Vide()));
			}

			if (i > 47 && i < 56) {
				ech.addLast(new Case(i, i % 2 == 0, new Pion(true)));
			}
		}
		return ech;
	}

	public void affichage() {
		Collections.sort(ech);
		gbc = new GridBagConstraints();
		gbc.gridheight = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.ipadx = 6;
		gbc.ipady = 6;
		for (Case c : ech) {
			try {
				c.piece.image.setPreferredSize(new Dimension(80,80));
				addCase(grille, c);
			} catch (NullPointerException e) {
				System.out.println("Chelou");
			}
		}
		this.repaint();
	}

	public void mouseClicked(MouseEvent e){
		//majAff();
	}

	public void mousePressed(MouseEvent e) {
		caseSelect = selection(e);
		System.out.println(caseSelect);
		grille.remove(caseSelect.piece.image);
		grille.repaint();
		
		/*test
		Coordonnee co1 = new Coordonnee(caseSelect.getX(),caseSelect.getY());
		Coordonnee co2 = new Coordonnee(0,0);
		Deplacement dep = new Deplacement(co1,co2);
		
		boolean [][] mouvement = new boolean [8][8];
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				co2 = new Coordonnee(j,i);
				dep = new Deplacement(co1,co2);
				mouvement[i][j] = Reine1.typeDeplacement(dep);
			}
		}
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(echiquier[i][j].piece == null && mouvement[i][j] == true){
					//g.drawImage(T.getImage("Mouvement_possible.png"),xP + 3 + j*86,yP + 3 +  i*86,80,80,this);
				}
			}
		}
		fin du test*/

	}
	public void mouseReleased(MouseEvent e){
		//grille.removeAll();
		//grille.repaint();
		//System.out.println("RELEASED");

		//Case caseSwitch = selection(e);
		//System.out.println("CASE A CHANGER "+caseSwitch);

		//grille.remove(caseSwitch.piece.image);

		//caseSelect.switchCase(caseSwitch);


		//System.out.println("Case Select"+caseSelect);
		//affichage();
		//majAff();

	}

	public void majAff(){
		System.out.println(ech);
		ech.get(0).switchCase(ech.get(1));
		System.out.println(ech);
		Collections.sort(ech);
		System.out.println(ech);
		grille.removeAll();
		grille.repaint();
		gbc.gridx = 0;
		gbc.gridy = 0;
		grille.add(ech.get(0).piece.image);
	}
	public void mouseEntered(MouseEvent e){ }
	public void mouseExited(MouseEvent e){ }

	public int caseX(MouseEvent e) {
		return (int) (e.getX() / 86);
	}

	public int caseY(MouseEvent e) {
		return (int) (e.getY() / 86);
	}

	public Case selection(MouseEvent e){
		for(Case c : ech){
			if(caseX(e)+caseY(e)*8 == c.num){
				return c;
			}
		}
		return null;
	}

	public void addCase(JPanel pan, Case c){
		System.out.println(c);
		gbc.gridx = c.x;
		gbc.gridy = c.y;
		pan.add(c.piece.image, gbc);

	}
}
