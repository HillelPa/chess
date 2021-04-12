import javax.swing.*;

public class WindowWin extends JFrame {

    public WindowWin(boolean couleur){
        String s = "Bravo les ";
            if(couleur){
                s += "Blancs ";
            }else{
                s += "Noirs ";
            }
        s += "ont gagné !";

        new JFrame("FIN DE LA PARTIE");
        setSize(400,200);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel mess = new JLabel(s, JLabel.CENTER);

        add(mess);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
