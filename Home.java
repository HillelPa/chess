import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;


public class Home extends JFrame implements ActionListener, ChangeListener {

    JPanel panel;
    JLabel img;
    JButton btnPlay;
    JButton btnRules;
    JButton btnReseau;
    JButton btnSettings;
    Echiquier ech;
    JFrame settings;
    int time = 1200000;

    // Attributs de la fenetre settings

    JPanel panelSet;
    JSlider slider;
    JLabel temps;
    JLabel reseau;
    JRadioButton client;
    JRadioButton serveur;
    JLabel instr;
    JLabel ip;
    JLabel instr2;
    JTextField ipClient;
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
        btnPlay = new JButton();
        btnPlay.setBounds(75, 172,340, 80 );
        btnPlay.setOpaque(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        btnPlay.addActionListener(this);

        btnRules = new JButton("");
        btnRules.setBounds(75, 270,340, 80 );
        btnRules.setOpaque(false);
        btnRules.setContentAreaFilled(false);
        btnRules.setBorderPainted(false);
        btnRules.addActionListener(this);

        btnReseau = new JButton("");
        btnReseau.setBounds(75, 368,340, 80);
        btnReseau.setOpaque(false);
        btnReseau.setContentAreaFilled(false);
        btnReseau.setBorderPainted(false);
        btnReseau.addActionListener(this);

        btnSettings = new JButton("set");
        btnSettings.setBounds(420, 630,60, 60 );
        btnSettings.setOpaque(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.setBorderPainted(false);
        btnSettings.addActionListener(this);

        panel.add(btnReseau);
        panel.add(btnRules);
        panel.add(btnPlay);
        panel.add(img);
        panel.add(btnSettings);

        add(panel);

        initSet();

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

        if(e.getSource() == btnReseau){
            if(client.isSelected() || serveur.isSelected()) {
                this.dispose();
                if (client.isSelected()){
                   new EchiquierClient(ipClient.getText());
                }else {
                    if (serveur.isSelected()) {
                        new EchiquierServeur(time);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "Attenion ! \n pour utiliser le jeu en réseau local,\n assurez vous d'avoir completer les paramètres en cliquant sur le rouage");
                settings.setVisible(true);
            }
        }
        if(e.getSource() == btnSettings){
            settings.setVisible(true);
        }
    }

    public void stateChanged(ChangeEvent e) {
        temps.setText("Temps : "+slider.getValue()+" minutes");
        time = slider.getValue()*60000;
    }

    public void initSet(){
        settings = new JFrame("Parametres");
        settings.setSize(400,500);
        settings.setLayout(null);
        settings.setLocationRelativeTo(null);
        settings.setResizable(false);
        settings.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panelSet = new JPanel();
        panelSet.setBounds(0,40,400,460);
        panelSet.setOpaque(false);
        panelSet.setLayout(null);

        imgSettings = new JLabel(new ImageIcon("parametres.jpg"));
        imgSettings.setBounds(0,0,400,500);

        temps = new JLabel("Temps : 20 minutes", JLabel.CENTER);
        temps.setForeground(Color.WHITE);
        temps.setBounds(0,0,400,100);
        temps.setOpaque(false);
        temps.setAlignmentX(Component.CENTER_ALIGNMENT);

        slider = new JSlider(JSlider.HORIZONTAL, 5, 50, 20);
        slider.setOpaque(false);
        slider.setForeground(Color.WHITE);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(15);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(0,40,400,100);

        reseau = new JLabel("Si vous jouez en réseau local : ", JLabel.CENTER);
        reseau.setForeground(Color.WHITE);
        reseau.setBounds(0,110,400,100);
        reseau.setOpaque(false);
        reseau.setAlignmentX(Component.CENTER_ALIGNMENT);

        client = new JRadioButton("Noirs");
        client.setBounds(50,170,200,30);
        client.setOpaque(false);
        client.setForeground(Color.WHITE);

        serveur = new JRadioButton("Blancs");
        serveur.setBounds(250,170,200,30);
        serveur.setOpaque(false);
        serveur.setForeground(Color.WHITE);

        ButtonGroup gr = new ButtonGroup();
        gr.add(client);
        gr.add(serveur);

        instr = new JLabel("Si vous êtes les blancs, donnez cette IP à votre adversaire", JLabel.CENTER);
        instr.setForeground(Color.WHITE);
        instr.setBounds(0,180,400,100);
        instr.setOpaque(false);
        instr.setAlignmentX(Component.CENTER_ALIGNMENT);

        ip = new JLabel("", JLabel.CENTER);
        try {
            ip.setText(InetAddress.getLocalHost().getHostAddress());
        }catch(UnknownHostException er){};
        ip.setForeground(Color.WHITE);
        ip.setFont(new Font("t", Font.BOLD,30));
        ip.setBounds(0,220,400,100);
        ip.setOpaque(false);
        ip.setAlignmentX(Component.CENTER_ALIGNMENT);


        instr2 = new JLabel("Si vous etes les noirs, entrez ici l'IP fournie par votre adversaire", JLabel.CENTER);
        instr2.setForeground(Color.WHITE);
        instr2.setBounds(0,260,400,100);
        instr2.setOpaque(false);
        instr2.setAlignmentX(Component.CENTER_ALIGNMENT);

        ipClient = new JTextField("ICI");
        ipClient.setBounds(100,320,200,50);
        ipClient.setAlignmentX(Component.CENTER_ALIGNMENT);


        panelSet.add(temps);
        panelSet.add(slider);
        panelSet.add(reseau);
        panelSet.add(client);
        panelSet.add(serveur);
        panelSet.add(instr);
        panelSet.add(ip);
        panelSet.add(instr2);
        panelSet.add(ipClient);

        settings.add(panelSet);
        settings.add(imgSettings);

        settings.setAlwaysOnTop(true);
    }
}
