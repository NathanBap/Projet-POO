package view;

import javax.swing.*;
import controler.Main;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainPage extends JFrame {

    Main controlleur = new Main();

    public MainPage() {
        //La totalité ou presque des fonctions seront deplacées dans le controlleur
        //Crée le panel principal
        setTitle("Scrabble POO - Page d'accueil");
        setSize(850, 600);
        setResizable(false); /*sinon placement des éléments raté mais cela ne pose pas de problème au plateau */
		setIconImage(new ImageIcon("ressources/Logo.png").getImage());
        // Arrière-plan
        Color transparentColor = new Color(1, 1, 1, 100);
		ImageIcon bgIcon = new ImageIcon("ressources/Scrabble.jpg");
		JLabel bgLabel = new JLabel(bgIcon);
		bgLabel.setBounds(0, 0, 850, 600);
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
                label.setOpaque(true);
                label.setBackground(transparentColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
            }
        };

        MouseListener lancementPlateau = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                //controlleur.launchOtherJavaFile();
                PlateauView plateauView = new PlateauView();
                plateauView.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.GREEN);
                label.setOpaque(true);
                label.setBackground(transparentColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
            }
        };

        MouseListener continuerPartie = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                //controlleur.launchOtherJavaFile();
                try {
                    FileInputStream fis = new FileInputStream("sauvegarde.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    PlateauView plateau = (PlateauView) ois.readObject(); 
                    plateau.resetControlers();
                    plateau.setVisible(true);
                    dispose();
                    plateau.requestFocus();

                    ois.close();
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("Class not found");
                    c.printStackTrace();
                    return;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.GREEN);
                label.setOpaque(true);
                label.setBackground(transparentColor);
                
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
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
                label.setOpaque(true);
                label.setBackground(transparentColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
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
                label.setOpaque(true);
                label.setBackground(transparentColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
            }
        };

        MouseListener sujetProjet = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                controlleur.ouvrirPDF();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLUE);
                label.setOpaque(true);
                label.setBackground(transparentColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
            }
        };

        JLabel welcomeLabel = new JLabel("Bienvenue sur notre Scrabble !");
        welcomeLabel.setFont(new Font("Century Gothic Italic", Font.PLAIN, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBounds((850 - welcomeLabel.getPreferredSize().width) / 2, 45, welcomeLabel.getPreferredSize().width, 50);

        JLabel regles = new JLabel("Règles");
        regles.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        regles.setHorizontalAlignment(SwingConstants.CENTER);
		regles.setForeground(Color.WHITE);
		regles.setBounds(212 - (regles.getPreferredSize().width / 2), 120, (10 + regles.getPreferredSize().width), 50);
        regles.setOpaque(false);
        regles.addMouseListener(affichageRegles);

        JLabel lienGit = new JLabel("Lien GitHub");
		lienGit.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        lienGit.setHorizontalAlignment(SwingConstants.CENTER);
		lienGit.setForeground(Color.WHITE);
		lienGit.setBounds(637 - (lienGit.getPreferredSize().width /2), 120, (10 + lienGit.getPreferredSize().width), 50);
        lienGit.setOpaque(false);
        lienGit.addMouseListener(redirectionGit);

        JLabel projet = new JLabel("Le projet");
        projet.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        projet.setHorizontalAlignment(SwingConstants.CENTER);
        projet.setForeground(Color.WHITE);
        projet.setBounds(212 - (projet.getPreferredSize().width / 2), 200, (10 + projet.getPreferredSize().width), 50);
        projet.setOpaque(false);
        projet.addMouseListener(sujetProjet);

        JLabel quitButton = new JLabel("Quitter");
        quitButton.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setForeground(Color.WHITE);
		quitButton.setBounds(637 - (quitButton.getPreferredSize().width / 2), 200, (10 + quitButton.getPreferredSize().width), 50);
        quitButton.setOpaque(false);
        quitButton.addMouseListener(quitterScrabble);        

        JLabel launchButton = new JLabel("Nouvelle partie");
        launchButton.setFont(new Font("Century Gothic", Font.PLAIN, 50));
        launchButton.setHorizontalAlignment(SwingConstants.CENTER);
		launchButton.setForeground(Color.WHITE);
		launchButton.setBounds((850 - launchButton.getPreferredSize().width) / 2, 300, (10 + launchButton.getPreferredSize().width), 60);
        launchButton.setOpaque(false);
        launchButton.addMouseListener(lancementPlateau);

        
        // si on veut ajouter un bouton pour continuer une partie sauvegardée
        JLabel continuer = new JLabel("Continuer");
        File f = new File("sauvegarde.ser");
        continuer.setFont(new Font("Century Gothic", Font.PLAIN, 50));
        continuer.setHorizontalAlignment(SwingConstants.CENTER);
        continuer.setBounds((850 - continuer.getPreferredSize().width) / 2, 400, (10 + continuer.getPreferredSize().width), 60);
        continuer.setOpaque(false);
        if(f.exists() && !f.isDirectory()) {
            continuer.setForeground(Color.WHITE);
            continuer.addMouseListener(continuerPartie);
        } else {
            continuer.setForeground(Color.GRAY);
        }

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
        bgLabel.add(continuer);
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
        