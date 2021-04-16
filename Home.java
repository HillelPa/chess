import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class Home extends JFrame implements ActionListener, ChangeListener {

    JPanel panel;
    JLabel img;
    JButton btnPlay;
    JButton btnRules;
    JButton btnSettings;
    Echiquier ech;
    int time = 1200000;

    // Attributs de la fenetre settings

    JPanel panelSet;
    JSlider slider;
    JLabel temps;
    JLabel imgSettings;

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
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnPlay){
            ech = new Echiquier(time);
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
            JFrame settings = new JFrame("Parametres");
            settings.setSize(400,300);
            settings.setLocationRelativeTo(null);

            panelSet = new JPanel();
            panelSet.setBounds(0,0,400,300);
            //panelSet.setLayout(new GridLayout(5,1));
		
	    imgSettings = new JLabel(new ImageIcon("parametres.jpg"));
	    imgSettings.setBounds(0,0,400,300);

            temps = new JLabel("Temps : 20 minutes", JLabel.CENTER);
            temps.setAlignmentX(Component.CENTER_ALIGNMENT);

            slider = new JSlider(JSlider.HORIZONTAL, 5, 50, 20);
            slider.addChangeListener(this);
            slider.setMajorTickSpacing(15);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            
	    panelSet.add(imgSettings);
            panelSet.add(temps);
            panelSet.add(slider);

            settings.add(panelSet);
            settings.setAlwaysOnTop(true);
            settings.setVisible(true);
        }
    }

    public void stateChanged(ChangeEvent e) {
        temps.setText("Temps : "+slider.getValue()+" minutes");
        time = slider.getValue()*60000;
    }

public static void main (String [] args){
	Home h = new Home();
}

}
