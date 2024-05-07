package view;

import model.*;

import javax.swing.*;

import controler.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.util.List;
import java.awt.dnd.*;
import javax.swing.Timer;

public class PlateauView extends JFrame {
    private Plateau plateau;
    private List<CaseView> allCases = new ArrayList<CaseView>();
    private Joueur joueur;
    public LettreView lettreClicked;
    private JPanel listeLettres;
    private JButton passer;
    private JButton echanger;
    private JPanel footerPanel;
    private JLabel scoreLabel;
    private JPanel mainPanel;
    
    public PlateauView() {
        initGame();
        this.joueur = plateau.getJoueurActuel();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    private void initGame() {
        plateau = new Plateau();
        plateau.initPlateau();
        plateau.initJoueurs();
        plateau.debutDuJeu();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Scrabble POO");
        
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
        //setLocationRelativeTo(null);  

        footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);

        // A FAIRE : Créer une méthode create button, qui l'ajoute au panel et ajoute un action listener
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton annulerTout = new JButton("Annuler tout");
        JButton aide = new JButton("Aide");
        // Attributs de classe
        passer = new JButton("Passer");
        echanger = new JButton("Echanger");
        
        listeLettres = new JPanel();

        for (Lettre l : lettresDuJoueur) {
            LettreView lettre = new LettreView(l);

            lettre.addMouseListener(new LettreControler(lettre, this, listeLettres));
            listeLettres.add(lettre);
        }

        int score = joueur.getScore();
        scoreLabel = new JLabel(String.valueOf(score));

        footerPanel.add(valider);
        footerPanel.add(annuler);
        footerPanel.add(annulerTout);
        footerPanel.add(echanger);
        footerPanel.add(passer);

        footerPanel.add(listeLettres);
        footerPanel.add(scoreLabel);
        add(footerPanel, BorderLayout.SOUTH);

        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));
        annulerTout.addActionListener(new ButtonsControler(annulerTout, this));
        echanger.addActionListener(new ButtonsControler(echanger, this));
        passer.addActionListener(new ButtonsControler(passer, this));

        keyboardShortcuts(mainPanel);
        
    }

    public void editScore() {
        scoreLabel.setText(String.valueOf(joueur.getScore()));
        scoreLabel.revalidate();
    }

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
        String msg = "Cliquer sur \"OK\" quand le joueur suivant est prêt";
        JOptionPane.showMessageDialog(this, msg, "Joueur suivant", JOptionPane.INFORMATION_MESSAGE);
        remplirMain();
        enableButtons();
        editScore();
    }

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
                lettreCopy.addMouseListener(new LettreControler(lettreCopy, this, copiedListeLettres));
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

    private void keyboardShortcuts(JPanel mainPanel) {
        // Define the key stroke
        KeyStroke commandZ = KeyStroke.getKeyStroke("meta Z");
        KeyStroke ctrlZ = KeyStroke.getKeyStroke("control Z");
        KeyStroke echap = KeyStroke.getKeyStroke("ESCAPE");

        // Get the InputMap and ActionMap
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPanel.getActionMap();

        // Map the key stroke to an action name
        inputMap.put(ctrlZ, "undo");
        inputMap.put(commandZ, "undo");
        inputMap.put(echap,"pause");

        // Map the action name to an action
        actionMap.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when CTRL+Z is pressed
                removeLastLetter();
            }
        });

        actionMap.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when ESCAPE is pressed
                System.out.println("Pause");
                JDialog dialog = new JDialog(); // Create the dialog
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS)); // Set the layout to BoxLayout with vertical alignment

                // Create buttons
                JButton button1 = new JButton("Button 1");
                JButton button2 = new JButton("Button 2");
                JButton button3 = new JButton("Button 3");

                // Add buttons to the dialog
                dialog.add(button1);
                dialog.add(button2);
                dialog.add(button3);

                dialog.pack(); // Adjust the size of the dialog to fit its content
                dialog.setVisible(true); // Show the dialog
            }
        });

        JDialog dialog = new JDialog(); // Create the dialog
        dialog.setLayout(new GridBagLayout()); // Set the layout to GridBagLayout

        JPanel gameState = new JPanel();
        gameState.setLayout(new BoxLayout(gameState, BoxLayout.Y_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // Center the panel
        dialog.add(gameState, gbc); // Add the panel with the constraints

        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);

        // Define the key stroke
        KeyStroke pressM = KeyStroke.getKeyStroke("pressed M");
        KeyStroke releaseM = KeyStroke.getKeyStroke("released M");

        // Map the key stroke to an action name
        inputMap.put(pressM, "showDialog");

        // Map the action name to an action
        actionMap.put("showDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when M is pressed
                gameState.removeAll();
                for (Joueur j : plateau.getJoueurs()) {
                    gameState.add(new JLabel(j.getNom() + " - " + j.getScore() + " points"));
                }
                dialog.setVisible(true);
            }
        });

        InputMap inputMap2 = gameState.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(releaseM, "hideDialog");
        ActionMap actionMap2 = gameState.getActionMap();

        actionMap2.put("hideDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when M is released
                dialog.setVisible(false);
            }
        });

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
        revalidate();
    }

    public void disableButtons() {
        passer.setEnabled(false);
        echanger.setEnabled(false);
    }
    public void enableButtons() {
        passer.setEnabled(true);
        echanger.setEnabled(true);
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
            
        } else {
            System.out.println("Nouvelle partie");
            dispose();
            new PlateauView();
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }
}

