import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;

public class Grille extends JPanel implements MouseListener, MouseMotionListener  {

    LinkedList<Piece> ech;
    Piece PieceSelect;
    boolean sourisInt;
    boolean tour = true;

    public Grille(){
        int largP = 688;
        ech = init();
        new JPanel();
        setBounds(0, 0, largP, largP);
        setOpaque(false);
        setLayout(null);

        addMouseListener(this);
        addMouseMotionListener(this);

        aff();
    }



    public LinkedList<Piece> init(){
        LinkedList<Piece> echiquier = new LinkedList<Piece>();
        echiquier.add(new Tour(0,false));
        echiquier.add(new Cavalier(1,false));
        echiquier.add(new Fou(2,false));
        echiquier.add(new Reine(3,false));
        echiquier.add(new Roi(4,false));
        echiquier.add(new Fou(5,false));
        echiquier.add(new Cavalier(6,false));
        echiquier.add(new Tour(7,false));
        for (int j = 0; j < 8; j++) {
            echiquier.add(new Pion(getNum(1,j),false));
        }

        echiquier.add(new Tour(56,true));
        echiquier.add(new Cavalier(57,true));
        echiquier.add(new Fou(58,true));
        echiquier.add(new Reine(59,true));
        echiquier.add(new Roi(60,true));
        echiquier.add(new Fou(61,true));
        echiquier.add(new Cavalier(62,true));
        echiquier.add(new Tour(63,true));

        for (int j = 0; j < 8; j++) {
            echiquier.add(new Pion(getNum(6,j),true));
        }
        return echiquier;
    }

    public int getNum(int i, int j){
        return 8*i + j;
    }

    public void aff() {
        for(Piece p : ech){
            try {
                p.majLocation();
                add(p.image);
            } catch (NullPointerException e) {}
        }
    }

    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e) {
        
			try {
				PieceSelect = selection(e);
				if(PieceSelect.couleur == tour){
					affPossible();
				}
			} catch (NullPointerException er) {
				System.out.println("Pas de piece selectionnée");
			}
    }

    public void mouseReleased(MouseEvent e) {
        boolean b = false;
        if(PieceSelect.couleur == tour){
			try {
				 b = PieceSelect.typeDeplacement(new Deplacement(new Coordonnee(PieceSelect.x, PieceSelect.y), new Coordonnee(caseX(e), caseY(e))));
			}catch(NullPointerException er){}

			this.removeAll();
			aff();
			repaint();
			try {
				if (sourisInt && b && caseVide(getNum(caseY(e), caseX(e)), PieceSelect.couleur)){
					PieceSelect.num = caseX(e) + caseY(e) * 8;
					PieceSelect.maj();
					tour = !tour;
				}
				PieceSelect.majLocation();
				
			} catch (NullPointerException er) {
			}
			
		}
    }

    public void mouseDragged(MouseEvent e){
        if(PieceSelect.couleur == tour){
			try {
				PieceSelect.image.setLocation(e.getX() - 40, e.getY() - 40);
			}catch(NullPointerException er){}
		}
    }

    public void mouseEntered(MouseEvent e){
        sourisInt = true;
    }
    public void mouseExited(MouseEvent e){
        sourisInt = false;
    }
    public void mouseMoved(MouseEvent e){}
	
	//verif si une case est occupée par la meme couleur
    public boolean caseVide(int i, boolean coul){
        for(Piece p : ech){
            System.out.println(p);
            if(p.num == i && p.couleur == coul){
                return false;
            }
        }
        return true;
    }

    public int caseX(MouseEvent e) {
        return (int) (e.getX() / 86);
    }

    public int caseY(MouseEvent e) {
        return (int) (e.getY() / 86);
    }

    public Piece selection(MouseEvent e) {
        for (Piece p : ech) {
            try {
                if (caseX(e) + caseY(e) * 8 == p.num) {
                    return p;
                }
            } catch (NullPointerException er) {}
        }
        return null;
    }

    public void affPossible(){
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(PieceSelect.typeDeplacement(new Deplacement(new Coordonnee(PieceSelect.x, PieceSelect.y), new Coordonnee(i,j))) && caseVide(j*8+i, PieceSelect.couleur)){
                    JLabel pos = new JLabel(new ImageIcon("Mouvement_possible.png"));
                    pos.setSize(80,80);
                    pos.setLocation(i*86+3, j*86+3);
                    this.add(pos);
                    repaint();
                }
            }
        }
    }
}
