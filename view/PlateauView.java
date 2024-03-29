package view;
import model.Plateau;

import javax.swing.*;

import controler.CaseControler;
import controler.LettreControler;

import java.awt.*;

public class PlateauView extends JFrame {
    private Plateau plateau;
    public JPanel lettreClicked;
    
    public PlateauView() {
        initGame();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
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
                JPanel casee = new JPanel(new BorderLayout());
                JLabel label = new JLabel(String.valueOf(plateau.getCase(i, j).getBonus()));

                casee.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                //label.setHorizontalAlignment(SwingConstants.CENTER);

                casee.add(label, BorderLayout.CENTER);
                mainPanel.add(casee);

                label.addMouseListener(new CaseControler(casee, this));
            }
        }

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton effacer = new JButton("Effacer");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");
        JPanel lettre = new LettreView('A');

        footerPanel.add(valider);
        footerPanel.add(effacer);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);
        footerPanel.add(lettre);

        add(footerPanel, BorderLayout.SOUTH);

        lettre.addMouseListener(new LettreControler(lettre, this));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }
}

//TODO, la pose de pièces sur le plateau