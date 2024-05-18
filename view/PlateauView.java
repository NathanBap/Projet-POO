package view;

import model.*;

import controler.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.awt.datatransfer.*;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


public class PlateauView extends JFrame implements Serializable {
    private Plateau plateau;
    private List<CaseView> allCases = new ArrayList<CaseView>();

    private JComboBox<String> motssugg;

    private Joueur joueur;
    public LettreView lettreClicked;
    private JPanel listeLettres;
    private JButton passer;
    private JButton echanger;
    private JButton aide = new JButton("Aide");
    private JButton valider;
    private JButton annuler;
    private JButton annulerTout;
    private JPanel footerPanel;
    private JLabel scoreLabel;
    private JPanel mainPanel;
    private JLabel sacLabel;
    
    // --- Initialisation de la fenêtre --- //
    public PlateauView() {
        File f = new File("sauvegarde.ser");
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
        initGame();
        this.joueur = plateau.getJoueurActuel();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setIconImage(new ImageIcon("ressources/Logo.png").getImage());
        setVisible(true);
        showPlayer();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    private void initGame() {
        plateau = new Plateau();
        plateau.initPlateau();
        initJoueurs();
        // plateau.initJoueursTest();  // Mettre en commentaire et décommenter initJoueurs() 
        plateau.debutDuJeu();
    }

    private void initJoueurs() {
        String title = "Bienvenue dans le jeu du Scrabble !";
        String msg = "Pour commencer, veuillez choisir le nombre des joueurs :";
        Integer[] options = {2, 3, 4};
        Integer n = (Integer)JOptionPane.showInputDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (n == null) {
            System.exit(0);
        }
        List<String> noms = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            String nom = JOptionPane.showInputDialog(this, "Nom du joueur " + (i) + " :");
            if (nom == null) {
                System.exit(0);
            }
            noms.add(nom);
        }

        msg = "Les joueurs sont : ";
        for (String nom : noms) {
            msg += "\n- " + nom;
        }
        int choice = JOptionPane.showConfirmDialog(this, msg, "Êtes vous sur ?", JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.CANCEL_OPTION) {
            initJoueurs();
        } else {
            plateau.initJoueurs(noms);
        }

        msg = "Voulez-vous jouer avec une aide ?";
        choice = JOptionPane.showConfirmDialog(this, msg, "Aide", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            aide.setEnabled(false);
        } 
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(PlateauView.this,"Voulez-vous sauvegarder la partie ? ", "Sauvegarder ? ", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        FileOutputStream fos = new FileOutputStream("sauvegarde.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(PlateauView.this);
                        oos.close();
                        fos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                } 
                System.exit(0);
            }
        });        
        setTitle("Scrabble POO");
      
        motssugg = new JComboBox<>();
        
        List<Lettre> lettresDuJoueur = joueur.getListeLettre();

        mainPanel = new JPanel(new GridLayout(15, 15));
        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                CaseView casee = new CaseView(plateau.getCase(i, j));
                casee.addMouseListener(new CaseControler(casee, this));

                mainPanel.add(casee);
                allCases.add(casee);

            }       
        }
        // Ajout du panneau principal et du panneau flou à la fenêtre
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setLocationRelativeTo(null);

        footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);

        // Attributs de classe
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        annulerTout = new JButton("Annuler tout");
        passer = new JButton("Passer");
        echanger = new JButton("Echanger");

        ImageIcon iconeSon = new ImageIcon("ressources/sonActifNoir.png");
		JLabel buttonSon = new JLabel(iconeSon);
		buttonSon.setBounds(785, 480, 50, 50);
        buttonSon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Si le son est actuellement muet, reprendre le son continu
                if (MainPageControler.sonMute) {
                    MainPageControler.reprendreSonContinu();
                } else {
                    // Si le son est muet, arrêter le son continu
                    MainPageControler.stopSonContinu();
                }
        
                // Changer l'icône du bouton en fonction de l'état du son
                JLabel bouton = (JLabel) e.getSource();
                ImageIcon icone = MainPageControler.sonMute ? new ImageIcon("ressources/sonInactifNoir.png") : new ImageIcon("ressources/sonActifNoir.png");
                bouton.setIcon(icone);
            }
        });
        
        annuler.setEnabled(false);
        annulerTout.setEnabled(false);
        
        listeLettres = new JPanel();

        for (Lettre l : lettresDuJoueur) {
            LettreView lettre = new LettreView(l);

            lettre.addMouseListener(new LettreControler(lettre, this, listeLettres));
            listeLettres.add(lettre);
        }

        int score = joueur.getScore();
        scoreLabel = new JLabel(joueur.getNom() + " : " + String.valueOf(score) + " points");
        footerPanel.add(buttonSon);

        try {
            URL url = getClass().getResource("/ressources/sac_image.png");
            Image image = ImageIO.read(url);

            // Redimensionner l'image
            Image scaledImage = image.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
            ImageIcon sacIcon = new ImageIcon(scaledImage);

            // Créer le JLabel avec l'image et le texte
            sacLabel = new JLabel(String.valueOf(plateau.getSac().getNbrLettres()), sacIcon, JLabel.CENTER);

            // Positionner le texte sous l'image
            sacLabel.setVerticalTextPosition(JLabel.CENTER);
            sacLabel.setHorizontalTextPosition(JLabel.CENTER);

            // Ajouter le JLabel au panel
            footerPanel.add(sacLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        footerPanel.add(new JLabel("        "));
        footerPanel.add(valider);
        footerPanel.add(annuler);

        footerPanel.add(annulerTout);
        footerPanel.add(listeLettres);

        footerPanel.add(aide);
        footerPanel.add(echanger);
        footerPanel.add(passer);
      

        footerPanel.add(scoreLabel);
        add(footerPanel, BorderLayout.SOUTH);

        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));
        aide.addActionListener(new ButtonsControler(aide, this));
        annulerTout.addActionListener(new ButtonsControler(annulerTout, this));
        echanger.addActionListener(new ButtonsControler(echanger, this));
        passer.addActionListener(new ButtonsControler(passer, this));

        keyboardShortcuts(mainPanel);
    }

    public List<LettreView> getLettreView() {

        List<LettreView> footerLettres = new ArrayList<>();

        for (Component component : listeLettres.getComponents()) {
            // Vérifier si le composant est une instance de LettreView
            if (component instanceof LettreView) {
                LettreView lettreView = (LettreView) component;
                footerLettres.add(lettreView);
            }

        }
        return footerLettres;
      
    }

    // Fin -- Initialisation de la fenêtre -- //

    // --- Actualisation de la vue --- //
    public void editScore() {
        scoreLabel.setText(joueur.getNom() + " : " + String.valueOf(joueur.getScore()) + " points");
        scoreLabel.revalidate();
    }
    public void refreshSac() {
        sacLabel.setText(String.valueOf(plateau.getSac().getNbrLettres()));
        sacLabel.revalidate();
    }

    // Retire la dernière lettre posée
    public void removeLastLetter() {
        if (plateau.arePendingCases()) {
            List<Case> pendingCases = plateau.getPendingCases();
            Case lastCase = pendingCases.get(pendingCases.size() - 1);
            for (CaseView c : this.allCases) {
                if (c.getCase().equals(lastCase)) {
                    // View
                    LettreView lettrePosee = c.getLettrePosee();
                    if (lettrePosee.getPiece().getJoker()) {
                        lettrePosee.getPiece().setLettre('*');
                        lettrePosee.paintLettreView();
                    }
                    listeLettres.add(lettrePosee);
                    c.removeLettrePosee();
                    lettrePosee.addMouseListener(new LettreControler(lettrePosee, this, listeLettres)); 
                    c.revalidate();
                    c.repaint();
    
                    // Model
                    pendingCases.remove(lastCase);
                    lastCase.retirerLettre();
                    break;
                }
            }
            revalidate();
        }
        if (!plateau.arePendingCases()) { enableButtons(); }
    }

    // Retire toutes les lettres posées pendant ce tour
    public void removeAllLetters() {
        if (plateau.arePendingCases()) {
            for (CaseView c : this.allCases) {
                List<Case> pendingCases = plateau.getPendingCases();
                if (pendingCases.contains(c.getCase())) {
                    LettreView lettrePosee = c.getLettrePosee();
                    if (lettrePosee.getPiece().getJoker()) {
                        lettrePosee.getPiece().setLettre('*');
                        lettrePosee.paintLettreView();
                    }
                    listeLettres.add(lettrePosee);
                    c.removeLettrePosee();
                    lettrePosee.addMouseListener(new LettreControler(lettrePosee, this, listeLettres));
    
                    c.revalidate();
                    c.repaint();
                }
            }
            revalidate();
        }
        enableButtons();
    }

    public void remplirMain() {
        List<Lettre> lettresDuJoueur = joueur.getListeLettre();

        for (Lettre l : lettresDuJoueur) {
            boolean found = false;
            for (Component lView : listeLettres.getComponents()) {
                if (lView instanceof LettreView) {
                    LettreView lettreView = (LettreView) lView;
                    if (lettreView.getPiece().equals(l)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                LettreView lettre = new LettreView(l);
                lettre.addMouseListener(new LettreControler(lettre, this, listeLettres));
                listeLettres.add(lettre);
            }
        }
        sacLabel.setText(String.valueOf(plateau.getSac().getNbrLettres()));
        sacLabel.revalidate();
        revalidate();
    }

    public void disableButtons() {
        passer.setEnabled(false);
        echanger.setEnabled(false);
        annuler.setEnabled(true);
        annulerTout.setEnabled(true);
    }
    public void enableButtons() {
        passer.setEnabled(true);
        echanger.setEnabled(true);
        annuler.setEnabled(false);
        annulerTout.setEnabled(false);
    }

    // Change de joueur et actualise la vue
    public void joueurSuivant() {
        plateau.passerAuJoueurSuivant();
        joueur = plateau.getJoueurActuel();
        for (Component c : listeLettres.getComponents()) {
            if (c instanceof LettreView) {
                LettreView lettre = (LettreView) c;
                lettre.removeMouseListener(lettre.getMouseListeners()[0]);
                listeLettres.remove(lettre);
            }
        }
        listeLettres.revalidate();

        String msg = "<html>Cliquer sur \"OK\" quand <b>" + joueur.getNom() + "</b> est prêt</html>";
        JOptionPane.showMessageDialog(this, msg, "Au tour de " + joueur.getNom(), JOptionPane.INFORMATION_MESSAGE);

        remplirMain();
        enableButtons();
        editScore();
    }

    public void showPlayer() {
        String msg = "<html>C'est au tour de : <b>" + joueur.getNom() + "</b></html>";
        JOptionPane.showMessageDialog(this, msg, "Début de la partie", JOptionPane.INFORMATION_MESSAGE);
    }

    public void echangerLettres() {
        if (plateau.getSac().getNbrLettres() < 7) {
            JOptionPane.showMessageDialog(this, "Il n'y a plus assez de lettres dans le sac", "Erreur", JOptionPane.ERROR_MESSAGE);
            echanger.setEnabled(false);
            return;
        }

        JPanel copiedListeLettres = new JPanel();

        // Add a copy of each letter to the new panel
        for (Component c : listeLettres.getComponents()) {
            if (c instanceof LettreView) {
                LettreView lettre = (LettreView) c;
                LettreView lettreCopy = new LettreView(lettre.getPiece());
                // lettreCopy.addMouseListener(new LettreControler(lettreCopy, this, copiedListeLettres));
                lettreCopy.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        lettreCopy.setSelected();
                    }
                });
                copiedListeLettres.add(lettreCopy);
            }
        }

        int retour = JOptionPane.showConfirmDialog(this, copiedListeLettres, "Choisissez les lettres à échanger", JOptionPane.OK_CANCEL_OPTION);
        if (retour == JOptionPane.OK_OPTION) {
            String msg = "<html>Les lettres : <b>";
            System.out.println("Echanger clicked");
            List<Lettre> lettresEchangees = new ArrayList<Lettre>();
            // Parcour la liste des lettres copiées
            for (Component c : copiedListeLettres.getComponents()) {
                if (c instanceof LettreView) {
                    LettreView lettreCopiee = (LettreView) c;
                    if (lettreCopiee.isSelected()) {
                        // Parcour la liste des lettres du joueur originale
                        for (Component l : listeLettres.getComponents()) {
                            if (l instanceof LettreView) {
                                LettreView lettreMain = (LettreView) l;
                                // Retrouve la lettre copiée cliquée dans la liste du joueur puis l'enlève
                                if (lettreMain.getPiece().equals(lettreCopiee.getPiece())) {
                                    listeLettres.remove(lettreMain);
                                    lettresEchangees.add(lettreMain.getPiece());
                                    msg += lettreMain.getPiece().getLettre() + " ";
                                    System.out.println("Lettres retirée : " + lettreCopiee.getPiece().getLettre());
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            msg += " </b>ont été échangées avec succès.<br>Lettres piochées : <b>";
            for (Lettre l : plateau.echangerLettres(lettresEchangees)) {
                msg += l.getLettre() + " ";
            }
            msg += "</b></html>";
            JOptionPane.showMessageDialog(this, msg, "Succès", JOptionPane.INFORMATION_MESSAGE);
            joueurSuivant();
        }
    }


    public void finPartie() {
        plateau.finPartie();

        String msg = "La partie est terminée !\n";
        for (Joueur j : plateau.getJoueurs()) {
            msg += j.getNom() + " a obtenu " + j.getScore() + " points.\n";
        }
        Object[] options = {"Nouvelle partie", "Menu principal", "Quitter"};

        int n = JOptionPane.showOptionDialog(this,
            msg,
            "Fin de partie",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        if (n == 2) {
            System.exit(0);
        } else if (n == 1) {
            new MainPage();
            dispose();
        } else {
            System.out.println("Nouvelle partie");
            ButtonsControler.passerCount = 0;
            dispose();
            new PlateauView();
        }
    }

    // --- Fin de l'actualisation de la vue --- //

    // --- Contrôleurs --- //
    private void keyboardShortcuts(JPanel mainPanel) {
        // Define the key stroke
        KeyStroke commandZ = KeyStroke.getKeyStroke("meta Z");
        KeyStroke ctrlZ = KeyStroke.getKeyStroke("control Z");
        KeyStroke echap = KeyStroke.getKeyStroke("ESCAPE");
        KeyStroke pressM = KeyStroke.getKeyStroke("pressed M");
        KeyStroke releaseM = KeyStroke.getKeyStroke("released M");
        

        // Get the InputMap and ActionMap
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPanel.getActionMap();

        // Map the key stroke to an action name
        inputMap.put(ctrlZ, "undo");
        inputMap.put(commandZ, "undo");
        inputMap.put(echap,"pause");
        inputMap.put(pressM, "showDialog");

        // Map the action name to an action
        actionMap.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when CTRL+Z is pressed
                removeLastLetter();
            }
        });

        // Quand on appui sur échap : 
        JDialog echapDialog = new JDialog(PlateauView.this, "Menu");
        JPanel pan = (JPanel)echapDialog.getContentPane();
        actionMap.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pan.removeAll();
                JButton reprendre = new JButton("Reprendre");
                JButton quitter = new JButton("Sauvegarder et quitter");

                echapDialog.add(new JPanel());
                echapDialog.add(reprendre);
                echapDialog.add(quitter);

                pan.setLayout(new GridLayout(4, 1));
                echapDialog.setSize(300, 300);
                echapDialog.setLocationRelativeTo(null);
                echapDialog.setVisible(true); 

                reprendre.addActionListener(new ButtonsControler(reprendre, PlateauView.this, echapDialog));
                quitter.addActionListener(new ButtonsControler(quitter, PlateauView.this));
        
            }
        });
        // Quand on rappuie sur échap :
        InputMap inputMap2 = pan.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(echap, "unPause");
        ActionMap actionMap2 = pan.getActionMap();
        actionMap2.put("unPause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                echapDialog.dispose();
            }
        });

        // Quand on appui sur M : 
        JDialog dialog = new JDialog(); 
        JPanel gameState = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        dialog.setLayout(new GridBagLayout()); 
        dialog.setSize(300, 200);
        dialog.setTitle("Score des joueurs");
        
        gbc.anchor = GridBagConstraints.CENTER; // Center the panel
        dialog.add(gameState, gbc); // Add the panel with the constraints
        dialog.setLocationRelativeTo(null);

        gameState.setLayout(new BoxLayout(gameState, BoxLayout.Y_AXIS));
        actionMap.put("showDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when M is pressed
                gameState.removeAll();
                for (Joueur j : plateau.getJoueurs()) {
                    gameState.add(new JLabel(j.getNom() + " - " + j.getScore() + " points"));
                }
                dialog.setVisible(true);
                dialog.requestFocus();
            }
        });

        inputMap2 = gameState.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(releaseM, "hideDialog");
        actionMap2 = gameState.getActionMap();

        actionMap2.put("hideDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when M is released
                dialog.setVisible(false);
            }
        });

    }

    // La reprise de partie et le drag and drop enlèvent certains controlers des composants
    public void resetControlers() {
        // Demande une confirmation à la fermeture de la fenêtre
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(PlateauView.this,"Voulez-vous sauvegarder la partie ? ", "Sauvegarder ? ", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        FileOutputStream fos = new FileOutputStream("sauvegarde.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(PlateauView.this);
                        oos.close();
                        fos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                } 
                System.exit(0);
            }
        });   

        for (CaseView c : allCases) {
            c.removeMouseListener(c.getMouseListeners()[0]);
            c.addMouseListener(new CaseControler(c, this));
        }
        for (Component l : listeLettres.getComponents()) {
            LettreView lettre = (LettreView) l;
            lettre.removeMouseListener(lettre.getMouseListeners()[0]);
            lettre.removeMouseListener(lettre.getMouseListeners()[0]);
            lettre.addMouseListener(new LettreControler(lettre, this, listeLettres));
        }

        keyboardShortcuts(mainPanel);
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }

}

