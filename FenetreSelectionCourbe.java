/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 */
 
import java.util.LinkedList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreSelectionCourbe extends JFrame{
	
	public FenetreSelectionCourbe(){		//LinkedList<Courbe> maListeCourbe
		
		this.setTitle("IHM Courbe - Selection");
		this.setSize(400,400);
		this.setLocation(300,200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel firstEtiquette = new JLabel("Entrez un numéro de courbe (entre 1 et 5)");
		firstEtiquette.setSize(150,40);
		firstEtiquette.setLocation(75,60);

		JTextField firstChampsTexte = new JTextField("");
		firstChampsTexte.setBounds(120,110,150,40);
		
		JPanel firstConteneur = new JPanel();
		firstConteneur.setLayout(null);
		firstConteneur.add(firstEtiquette);
		firstConteneur.add(firstChampsTexte);
		firstConteneur.setBounds(10,10,160,40);
		
		JButton firstBouton = new JButton("Afficher");
		firstBouton.setBounds(150,10,150,40);
		
		JTextField secondChampsTexte = new JTextField("");
		secondChampsTexte.setBounds(120,110,150,40);
		
		JPanel secondConteneur = new JPanel();
		secondConteneur.setLayout(null);
		secondConteneur.add(firstBouton);
		secondConteneur.add(secondChampsTexte);
		secondConteneur.setBounds(10,110,160,40);
		
		JPanel thirdConteneur = new JPanel();
		thirdConteneur.setLayout(null);
		thirdConteneur.add(firstConteneur);
		thirdConteneur.add(secondConteneur);
		thirdConteneur.setBounds(10,10,160,40);
		add(thirdConteneur);
					
		
		
		this.setVisible (true);
		
	}
}
		

		
		

		
		
		
