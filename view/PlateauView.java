package view;
import model.Case;
import model.Plateau;

import javax.swing.*;

import controler.ButtonsControler;
import controler.CaseControler;
import controler.LettreControler;

import java.awt.*;
import java.util.*;

public class PlateauView extends JFrame {
    private Plateau plateau;
    private java.util.List<CaseView> allCases = new ArrayList<CaseView>();

    public LettreView lettreClicked;
    
    public PlateauView() {
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
        plateau = new Plateau();
        plateau.initPlateau();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Scrabble POO");

        JPanel mainPanel = new JPanel(new GridLayout(15, 15));
        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                CaseView casee = new CaseView(plateau.getCase(i, j));
                casee.addMouseListener(new CaseControler(casee, this));

                mainPanel.add(casee);
                allCases.add(casee);
            }
        }

        add(mainPanel);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setLocationRelativeTo(null);

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");
        LettreView lettre = new LettreView('A');
        LettreView lettre2 = new LettreView('B');
        LettreView lettre3 = new LettreView('C');


        footerPanel.add(valider);
        footerPanel.add(annuler);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);
        footerPanel.add(lettre);
        footerPanel.add(lettre2);
        footerPanel.add(lettre3);
        
        add(footerPanel, BorderLayout.SOUTH);

        lettre.addMouseListener(new LettreControler(lettre, this));
        lettre2.addMouseListener(new LettreControler(lettre2, this));
        lettre3.addMouseListener(new LettreControler(lettre3, this));
        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));

    }

    public void removeAllLetters() {
        for (CaseView c : this.allCases) {
            c.removeLettrePosee();
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

//TODO, la pose de pièces sur le plateau