package view;

import model.Case;
import model.Plateau;

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
    
    public PlateauView(Joueur joueur) {
        this.joueur = joueur;
        initGame();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    private void initGame() {
        List<Joueur> joueurs = new ArrayList<Joueur>();
        Scrabble scrabble = new Scrabble(joueurs);
        plateau = new Plateau();
        plateau.initPlateau();
        scrabble.debutDuTour();
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

        //JPanel lettresPanel = this.VueDeLettre(joueur);

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");
      
        // A MODIFIER
        // JPanel listeLettres = new LettreView(lettresDuJoueur);
      
        int score = joueur.getScore();
        JLabel scoreLabel = new JLabel(String.valueOf(score));

        // LettreView lettre = new LettreView('A');
        //LettreView lettre2 = new LettreView('L');
        //LettreView lettre3 = new LettreView('L');
        //LettreView lettre4 = new LettreView('E');
        //LettreView lettre5 = new LettreView('E');
        //LettreView lettre6 = new LettreView('A');
        //LettreView lettre7 = new LettreView('C');
        //LettreView lettre8 = new LettreView('S');

        footerPanel.add(valider);
        footerPanel.add(annuler);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);

        footerPanel.add(listeLettres);
        footerPanel.add(scoreLabel);
        add(footerPanel, BorderLayout.SOUTH);

        //footerPanel.add(lettre);
        //footerPanel.add(lettre2);
        //footerPanel.add(lettre3);
        //footerPanel.add(lettre4);
        //footerPanel.add(lettre5);
        //footerPanel.add(lettre6);
        //footerPanel.add(lettre7);
        //footerPanel.add(lettre8);
        
        //add(footerPanel, BorderLayout.SOUTH);

        //lettre.addMouseListener(new LettreControler(lettre, this));
        //lettre2.addMouseListener(new LettreControler(lettre2, this));
        //lettre3.addMouseListener(new LettreControler(lettre3, this));
        //lettre4.addMouseListener(new LettreControler(lettre4, this));
        //lettre5.addMouseListener(new LettreControler(lettre5, this));
        //lettre6.addMouseListener(new LettreControler(lettre6, this));
        //lettre7.addMouseListener(new LettreControler(lettre7, this));
        //lettre8.addMouseListener(new LettreControler(lettre8, this));
        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));

    }

    public void removeAllLetters() {
        for (CaseView c : this.allCases) {
            List<Case> pendingCases = plateau.getPendingCases();
            if (pendingCases.contains(c.getCase())) {
                c.removeLettrePosee();
            }
        }
        repaint();
        //revalidate();

    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        Sac sac = new Sac();
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("Test", sac, plateau);
        PlateauView plateauView = new PlateauView(joueur);
            plateauView.setVisible(true);
        });
    }
}

