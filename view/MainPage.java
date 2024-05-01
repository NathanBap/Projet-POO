package view;

import javax.swing.*;
import controler.Main;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame {

    Main controlleur = new Main();

    public MainPage() {
        //La totalité ou presque des fonctions seront deplacées dans le controlleur
        //Crée le panel principal
        setTitle("Scrabble POO - Page d'accueil");
        setSize(850, 600);
        setResizable(false); /*sinon placement des éléments raté mais cela ne pose pas de problème au plateau */
        // Arrière-plan
		ImageIcon bgIcon = new ImageIcon("ressources/Scrabble.jpg");
		JLabel bgLabel = new JLabel(bgIcon);
		bgLabel.setBounds(0, 0, 850, 600);
		setIconImage(new ImageIcon("Logo.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MouseListener redirectionGit = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                controlleur.openWebPage("https://github.com/NathanBap/Projet-POO");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        };

        MouseListener lancementPlateau = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                controlleur.launchOtherJavaFile();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        };

        MouseListener affichageRegles = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                JTextArea regles = new JTextArea("Principe du jeu : Le Scrabble est un jeu de société où les joueurs doivent former des mots à partir de lettres tirées aléatoirement. Chaque lettre a une valeur en fonction de sa rareté. Le but est de former des mots de manière stratégique pour obtenir le plus de points possible. Bonne chance !");
                regles.setLineWrap(true); // Pour que le texte se présente sur plusieurs lignes
                regles.setWrapStyleWord(true); // Pour que le texte se coupe aux espaces
                regles.setRows(10); // Définir le nombre de lignes souhaité
                regles.setColumns(40); // Définir le nombre de colonnes souhaité
                UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
                JOptionPane.showMessageDialog(null,regles, "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
                    
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        };

        MouseListener quitterScrabble = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        };

        MouseListener sujetProjet = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                //ouvrir fichier PDF avec le sujet du projet
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        };

        JLabel welcomeLabel = new JLabel("Bienvenue sur notre Scrabble !");
        welcomeLabel.setFont(new Font("Century Gothic Italic", Font.PLAIN, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBounds((850 - welcomeLabel.getPreferredSize().width) / 2, 45, welcomeLabel.getPreferredSize().width, 30);

        JLabel regles = new JLabel("Règles");
        regles.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        regles.setHorizontalAlignment(SwingConstants.CENTER);
		regles.setForeground(Color.WHITE);
		regles.setBounds(25, 120, 400, 40);
        regles.addMouseListener(affichageRegles);

        JLabel lienGit = new JLabel("Lien GitHub");
		lienGit.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        //lienGit.setHorizontalAlignment(SwingConstants.CENTER);
		lienGit.setForeground(Color.WHITE);
		lienGit.setBounds(450, 120, 400, 40);
        lienGit.addMouseListener(redirectionGit);

        JLabel projet = new JLabel("Le projet");
        projet.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        projet.setHorizontalAlignment(SwingConstants.CENTER);
        projet.setForeground(Color.WHITE);
        projet.setBounds(25, 190, 400, 40);
        projet.addMouseListener(sujetProjet);

        JLabel quitButton = new JLabel("Quitter l'application");
        quitButton.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        //quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setForeground(Color.WHITE);
        quitButton.setBounds(450, 190, 400, 40);
        quitButton.addMouseListener(quitterScrabble);

        JLabel launchButton = new JLabel("Lancer le Scrabble");
        launchButton.setFont(new Font("Century Gothic", Font.PLAIN, 50));
		launchButton.setForeground(Color.WHITE);
		launchButton.setBounds((850 - launchButton.getPreferredSize().width) / 2, 360, 500, 120);
        launchButton.addMouseListener(lancementPlateau);

        /*JLabel continuer = new JLabel("Continuer");
		continuer.setFont(new Font("Century Gothic Italic", Font.PLAIN, 30));
		continuer.setForeground(Color.WHITE);
		continuer.setBounds(20, 100, 500, 30);*/
        
        JLabel texteFooter = new JLabel("Projet de programmation Orienté Objet de fin d'année - 2023/2024");
        texteFooter.setFont(texteFooter.getFont().deriveFont(10f));
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.add(texteFooter);
        add(footerPanel, BorderLayout.SOUTH);

        add(bgLabel);
        bgLabel.add(welcomeLabel);
        bgLabel.add(regles);
        bgLabel.add(lienGit);
        bgLabel.add(projet);
        bgLabel.add(launchButton);
        bgLabel.add(quitButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage();
            }
        });
    }
}

//Une fois le squelette du Scrabble assez poussé -> faire une page d'introduction au principe du scrabble et du projet
        