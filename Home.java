import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class Home extends JFrame implements ActionListener {

    JPanel panel;
    JLabel img;
    JButton btnPlay;
    JButton btnRules;
    JButton btnSettings;
    Echiquier ech;

    public Home() {

        int x = 480;
        int y = 80;
        int width = 480;
        int height = 720;


        //FENETRE
        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle("Home");
        setResizable(false);
        setLayout(null);


        //PANEL
        panel = new JPanel();
        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setBackground(Color.orange);
        panel.setLayout(null);

        //INTERFACE
        img = new JLabel(new ImageIcon("Background_chess.png"));
        img.setBounds(0,-27,this.getWidth(),this.getHeight());

        //boutons
        btnPlay = new JButton("play");
        btnPlay.setBounds(75, 212,340, 78 );
        btnPlay.addActionListener(this);

        btnRules = new JButton("rules");
        btnRules.setBounds(75, 320,340, 78 );
        btnRules.addActionListener(this);

        btnSettings = new JButton("set");
        btnSettings.setBounds(420, 630,60, 60 );
        btnSettings.addActionListener(this);

        panel.add(img);
        panel.add(btnPlay);
        panel.add(btnRules);
        panel.add(btnSettings);

        add(panel);

        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        ech = new Echiquier();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnPlay){
            ech.setVisible(true);
            this.dispose();
        }
        if(e.getSource() == btnRules){
            String link = "https://ecole.apprendre-les-echecs.com/regles-echecs/";
            try{
                Desktop.getDesktop().browse(new URL(link).toURI());
            }catch(URISyntaxException | IOException er){}
        }
        if(e.getSource() == btnSettings){
            System.out.println("PARAMÈTRES");
        }
    }

public static void main (String [] args){
	Home h = new Home();
}

}
