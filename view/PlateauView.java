package view;
import model.Plateau;

import javax.swing.*;
import java.awt.*;

public class PlateauView extends JFrame {
    private Plateau plateau;
    
    public PlateauView() {
        initGame();
        initComponents();
    }

    private void initGame() {
        plateau = new Plateau();
        plateau.initPlateau();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Scrabble POO");

        JPanel mainPanel = new JPanel(new GridLayout(15, 15));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                JLabel label = new JLabel(String.valueOf(plateau.getCase(i, j).getBonus()));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                mainPanel.add(label);
            }
        }

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }
}
