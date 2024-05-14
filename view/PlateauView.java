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
    public LettreView lettreClicked;
    private JPanel footerPanel;
    private JComboBox<String> motssugg;

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
        motssugg = new JComboBox<>();

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
        // setLocationRelativeTo(null);

        footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");
        LettreView lettre = new LettreView('A');
        LettreView lettre2 = new LettreView('L');
        LettreView lettre3 = new LettreView('L');
        LettreView lettre4 = new LettreView('E');
        LettreView lettre5 = new LettreView('E');
        LettreView lettre6 = new LettreView('A');
        LettreView lettre7 = new LettreView('C');
        LettreView lettre8 = new LettreView('S');

        footerPanel.add(valider);
        footerPanel.add(annuler);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);
        footerPanel.add(lettre);
        footerPanel.add(lettre2);
        footerPanel.add(lettre3);
        footerPanel.add(lettre4);
        footerPanel.add(lettre5);
        footerPanel.add(lettre6);
        footerPanel.add(lettre7);
        footerPanel.add(lettre8);

        add(footerPanel, BorderLayout.SOUTH);

        lettre.addMouseListener(new LettreControler(lettre, this));
        lettre2.addMouseListener(new LettreControler(lettre2, this));
        lettre3.addMouseListener(new LettreControler(lettre3, this));
        lettre4.addMouseListener(new LettreControler(lettre4, this));
        lettre5.addMouseListener(new LettreControler(lettre5, this));
        lettre6.addMouseListener(new LettreControler(lettre6, this));
        lettre7.addMouseListener(new LettreControler(lettre7, this));
        lettre8.addMouseListener(new LettreControler(lettre8, this));
        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));
        aide.addActionListener(new ButtonsControler(aide, this));
    }

    public List<LettreView> getLettreView() {

        List<LettreView> footerLettres = new ArrayList<>();

        for (Component component : footerPanel.getComponents()) {
            // Vérifier si le composant est une instance de LettreView
            if (component instanceof LettreView) {
                LettreView lettreView = (LettreView) component;
                footerLettres.add(lettreView);
            }

        }
        return footerLettres;
    }

    public void removeAllLetters() {
        for (CaseView c : this.allCases) {
            List<Case> pendingCases = plateau.getPendingCases();
            if (pendingCases.contains(c.getCase())) {
                c.removeLettrePosee();
            }
        }
        repaint();
        // revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }

}

// TODO, la pose de pièces sur le plateau
