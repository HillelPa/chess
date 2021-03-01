import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class Home extends JFrame implements MouseListener {

    Image image;
    JPanel panel;

    public Home() {

        //DIMENSION DE L'ECRAN
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        int larg = (int)width/3;
        int haut = (int)width/2 + 27;

        //FENETRE
        setBounds(width/2 - larg/2 , height/2 - haut/2, larg, haut);
        setTitle("Home");
        setResizable(false);
        setLayout(null);

        //PANEL
        panel = new JPanel();
        panel.setBounds(getX(), getY(), getWidth(), getHeight());
        panel.setBackground(Color.orange);
        panel.setLayout(null);

        //INTERFACE
        Toolkit T = Toolkit.getDefaultToolkit();
        image = T.getImage("Background_chess.png");

        add(panel);
        addMouseListener(this);

        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 0,27,this.getWidth(),this.getHeight()-27, this);
    }

    public void mouseClicked(MouseEvent e){

        if(e.getX() <= (int)(this.getWidth()*345/420) && e.getX()>= (int)(136*this.getWidth()/480)
                && e.getY() <= (int)(this.getHeight()*323/720) && e.getY() >= (int)(229*this.getHeight()/720)){
            EchiquierG ech = new EchiquierG();
            this.dispose();
        }
        if(e.getX() <= (int)(this.getWidth()*345/420) && e.getX()>= (int)(136*this.getWidth()/480)
                && e.getY() <= (int)(this.getHeight()*465/720) && e.getY() >= (int)(396*this.getHeight()/720)){
            String link = "https://ecole.apprendre-les-echecs.com/regles-echecs/";
            try{
                Desktop.getDesktop().browse(new URL(link).toURI());
            }catch(URISyntaxException | IOException er){}
        }
        if(e.getX() <= (int)(this.getWidth()*449/420) && e.getX()>= (int)(414*this.getWidth()/480)
                && e.getY() <= (int)(this.getHeight()*672/720) && e.getY() >= (int)(639*this.getHeight()/720)){
            System.out.println("PARAMÈTRES");
        }

    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

public static void main (String [] args){
	Home h = new Home();
}

}
