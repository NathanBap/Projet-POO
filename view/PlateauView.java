package view;

import model.*;

import javax.swing.*;

import controler.ButtonsControler;
import controler.CaseControler;
import controler.LettreControler;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        // Scrabble scrabble = new Scrabble(joueurs);
        plateau = new Plateau();
        plateau.initPlateau();
        plateau.initJoueurs();
        plateau.debutDuJeu();
        // scrabble.debutDuTour();
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
        JButton aide = new JButton("Aide");
      
        listeLettres = new JPanel();
        for (Lettre l : lettresDuJoueur) {
            LettreView lettre = new LettreView(l.getLettre());
            lettre.addMouseListener(new LettreControler(lettre, this));
            listeLettres.add(lettre);
        }

        int score = joueur.getScore();
        JLabel scoreLabel = new JLabel(String.valueOf(score));

        footerPanel.add(valider);
        footerPanel.add(annuler);

        footerPanel.add(listeLettres);
        footerPanel.add(scoreLabel);
        add(footerPanel, BorderLayout.SOUTH);

        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));

    }

    public void removeAllLetters() {
        for (CaseView c : this.allCases) {
            List<Case> pendingCases = plateau.getPendingCases();
            if (pendingCases.contains(c.getCase())) {
                LettreView lettrePosee = c.getLettrePosee();
                listeLettres.add(lettrePosee);
                c.removeLettrePosee();
            }
        }
        repaint();
        //revalidate();
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }
}

