import javax.swing.*;

public class Vide extends Piece {

    public Vide(){
        super("vide", -1, true);
        image = new JLabel(new ImageIcon("Vide_png"));
    }

    public String toString(){
        return "VIDE";
    }
}
