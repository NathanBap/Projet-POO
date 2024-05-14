package view;

import controler.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

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
        Color transparentColor = new Color(255, 255, 255, 255);
		ImageIcon bgIcon = new ImageIcon("ressources/Scrabble.jpg");
		JLabel bgLabel = new JLabel(bgIcon);
		bgLabel.setBounds(0, 0, 850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MouseListener redirectionGit = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                controlleur.jouerSon("ressources/click.wav");
                controlleur.openWebPage("https://github.com/NathanBap/Projet-POO");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLUE);
                label.setOpaque(true);
                label.setBackground(transparentColor);
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
                label.setBorder(null);
            }
        };

        MouseListener lancementPlateau = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                //controlleur.launchOtherJavaFile();
                controlleur.jouerSon("ressources/click.wav");
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
                label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
                label.setBorder(null);
            }
        };

        MouseListener continuerPartie = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                //controlleur.launchOtherJavaFile();
                controlleur.jouerSon("ressources/click.wav");
                try {
                    FileInputStream fis = new FileInputStream("sauvegarde.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    PlateauView plateau = (PlateauView) ois.readObject(); 
                    plateau.resetControlers();
                    plateau.setVisible(true);
                    plateau.showPlayer();
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
                label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
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
                controlleur.jouerSon("ressources/click.wav");
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
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
                label.setBorder(null);
            }
        };

        MouseListener quitterScrabble = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                controlleur.jouerSon("ressources/click.wav");
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.RED);
                label.setOpaque(true);
                label.setBackground(transparentColor);
                label.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
                label.setBorder(null);
            }
        };

        MouseListener sujetProjet = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à exécuter lors du clic
                controlleur.jouerSon("ressources/click.wav");
                controlleur.ouvrirPDF();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action à exécuter lors du hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLUE);
                label.setOpaque(true);
                label.setBackground(transparentColor);
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // Action à exécuter lorsque le curseur quitte le hover
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
                label.setOpaque(false);
                label.setBorder(null);
            }
        };

        JLabel welcomeLabel = new JLabel("Bienvenue sur notre Scrabble !");
        welcomeLabel.setFont(new Font("Century Gothic Italic", Font.PLAIN, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBounds((850 - welcomeLabel.getPreferredSize().width) / 2, 45, welcomeLabel.getPreferredSize().width, 50);

        JLabel regles = new JLabel("Règles");
        regles.setFont(new Font("Malgun Gothic", Font.PLAIN, 35));
        regles.setHorizontalAlignment(SwingConstants.CENTER);
		regles.setForeground(Color.WHITE);
		regles.setBounds(212 - (regles.getPreferredSize().width / 2), 120, (10 + regles.getPreferredSize().width), 50);
        regles.setOpaque(false);
        regles.addMouseListener(affichageRegles);

        JLabel lienGit = new JLabel("Lien GitHub");
        lienGit.setFont(new Font("Malgun Gothic", Font.PLAIN, 35));        
        lienGit.setHorizontalAlignment(SwingConstants.CENTER);
		lienGit.setForeground(Color.WHITE);
		lienGit.setBounds(637 - (lienGit.getPreferredSize().width /2), 120, (10 + lienGit.getPreferredSize().width), 50);
        lienGit.setOpaque(false);
        lienGit.addMouseListener(redirectionGit);

        JLabel projet = new JLabel("Le projet");
        projet.setFont(new Font("Malgun Gothic", Font.PLAIN, 35));
        projet.setHorizontalAlignment(SwingConstants.CENTER);
        projet.setForeground(Color.WHITE);
        projet.setBounds(212 - (projet.getPreferredSize().width / 2), 200, (10 + projet.getPreferredSize().width), 50);
        projet.setOpaque(false);
        projet.addMouseListener(sujetProjet);

        JLabel quitButton = new JLabel("Quitter");
        quitButton.setFont(new Font("Malgun Gothic", Font.PLAIN, 35));
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setForeground(Color.WHITE);
		quitButton.setBounds(637 - (quitButton.getPreferredSize().width / 2), 200, (10 + quitButton.getPreferredSize().width), 50);
        quitButton.setOpaque(false);
        quitButton.addMouseListener(quitterScrabble);        

        JLabel launchButton = new JLabel("Nouvelle partie");
        launchButton.setFont(new Font("Malgun Gothic", Font.PLAIN, 50));
        launchButton.setHorizontalAlignment(SwingConstants.CENTER);
		launchButton.setForeground(Color.WHITE);
		launchButton.setBounds((850 - launchButton.getPreferredSize().width) / 2, 300, (10 + launchButton.getPreferredSize().width), 60);
        launchButton.setOpaque(false);
        launchButton.addMouseListener(lancementPlateau);

        
        // si on veut ajouter un bouton pour continuer une partie sauvegardée
        JLabel continuer = new JLabel("Continuer");
        continuer.setFont(new Font("Malgun Gothic", Font.PLAIN, 50));
        continuer.setHorizontalAlignment(SwingConstants.CENTER);
        continuer.setBounds((850 - continuer.getPreferredSize().width) / 2, 400, (10 + continuer.getPreferredSize().width), 60);
        continuer.setOpaque(false);
        
        File f = new File("sauvegarde.ser");
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

        KeyStroke pressM = KeyStroke.getKeyStroke('m');

        InputMap inputMap = ((JPanel)this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = ((JPanel)this.getContentPane()).getActionMap();

        JDialog dialog = new JDialog(this, "Tableau des records"); 
        dialog.setLayout(new GridBagLayout()); 

        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(new BoxLayout(leaderboard, BoxLayout.Y_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // Center the panel
        dialog.add(leaderboard, gbc); // Add the panel with the constraints

        dialog.setMinimumSize(new Dimension(300, 200));
        dialog.setLocationRelativeTo(null);

        // Map the key stroke to an action name
        inputMap.put(pressM, "showDialog");

        File leaderBoardFile = new File("leaderboard.ser");
        Map<String, Integer> leaderBoardMap;
        if(leaderBoardFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream("leaderboard.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                leaderBoardMap = (Map<String,Integer>) ois.readObject(); 

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
        } else {
            leaderBoardMap = new HashMap<String,Integer>();
        }

        // Map the action name to an action
        actionMap.put("showDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when M is pressed
                leaderboard.removeAll();
                for (String joueur : leaderBoardMap.keySet()) {
                    leaderboard.add(new JLabel(joueur + " : " + leaderBoardMap.get(joueur)));
                }
                dialog.pack();
                dialog.setVisible(true);
                dialog.requestFocus();
            }
        });

        InputMap inputMap2 = leaderboard.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap2 = leaderboard.getActionMap();

        inputMap2.put(pressM, "hideDialog");

        actionMap2.put("hideDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when M is released
                dialog.setVisible(false);
            }
        });

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
        