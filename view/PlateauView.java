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

public class PlateauView extends JFrame {
    private Plateau plateau;
    private List<CaseView> allCases = new ArrayList<CaseView>();
    public Joueur joueur;
    public LettreView lettreClicked;
    private JPanel listeLettres;
    
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

        JPanel mainPanel = new JPanel(new GridLayout(15, 15));
        
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

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton annulerTout = new JButton("Annuler tout");
        JButton aide = new JButton("Aide");
      
        listeLettres = new JPanel();

        for (Lettre l : lettresDuJoueur) {
            LettreView lettre = new LettreView(l);

            lettre.addMouseListener(new LettreControler(lettre, this, listeLettres));
            listeLettres.add(lettre);
        }

        int score = joueur.getScore();
        JLabel scoreLabel = new JLabel(String.valueOf(score));

        footerPanel.add(valider);
        footerPanel.add(annuler);
        footerPanel.add(annulerTout);

        footerPanel.add(listeLettres);
        footerPanel.add(scoreLabel);
        add(footerPanel, BorderLayout.SOUTH);

        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));
        annulerTout.addActionListener(new ButtonsControler(annulerTout, this));

        keyboardShortcuts(mainPanel);
        
    }

    public void removeAllLetters() {
        if (plateau.arePendingCases()) {
            for (CaseView c : this.allCases) {
                List<Case> pendingCases = plateau.getPendingCases();
                if (pendingCases.contains(c.getCase())) {
                    LettreView lettrePosee = c.getLettrePosee();
                    listeLettres.add(lettrePosee);
                    c.removeLettrePosee();
                    lettrePosee.addMouseListener(new LettreControler(lettrePosee, this, listeLettres));
    
                    c.revalidate();
                    c.repaint();
                }
            }
            revalidate();
        }
    }

    public void removeLastLetter() {
        if (plateau.arePendingCases()) {
            List<Case> pendingCases = plateau.getPendingCases();
            Case lastCase = pendingCases.get(pendingCases.size() - 1);
            for (CaseView c : this.allCases) {
                if (c.getCase().equals(lastCase)) {
                    // View
                    LettreView lettrePosee = c.getLettrePosee();
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
    }

    private void keyboardShortcuts(JPanel mainPanel) {
        // Define the key stroke
        KeyStroke commandZ = KeyStroke.getKeyStroke("meta Z");
        KeyStroke ctrlZ = KeyStroke.getKeyStroke("control Z");

        // Get the InputMap and ActionMap
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPanel.getActionMap();

        // Map the key stroke to an action name
        inputMap.put(ctrlZ, "undo");
        inputMap.put(commandZ, "undo");

        // Map the action name to an action
        actionMap.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when CTRL+Z is pressed
                removeLastLetter();
            }
        });
    }

    // TMP
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
                System.out.println("Lettre ajoutée");
            }
        }
        revalidate();
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }
}

