package view;

import model.Plateau;

import javax.swing.*;
import java.awt.*;

public class PlateauView extends JFrame {
    private Plateau plateau;

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
        // Création du panneau principal du plateau
        JPanel mainPanel = new JPanel(new GridLayout(15, 15));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                // Création d'un panneau pour chaque case
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Bordure blanche
                JLabel label = new JLabel(String.valueOf(plateau.getCase(i, j).getBonus()));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                if (i == 7 && j == 7) {
                    panel.setBackground(Color.PINK);

                } else {
                    // Récupérer le bonus de la case
                    String bonus = plateau.getCase(i, j).getBonus();

                    // Modifier la couleur de fond en fonction du bonus
                    switch (bonus) {
                        case "LD":
                            panel.setBackground(new Color(153, 204, 255)); // Bleu clair
                            label.setText("<html><center>LETTRE<br>DOUBLE</center></html>");
                            break;
                        case "LT":
                            panel.setBackground(new Color(40, 103, 199)); // Bleu foncé
                            label.setText("<html><center>LETTRE<br>TRIPLE</center></html>");
                            break;
                        case "MD":
                            panel.setBackground(new Color(255, 204, 102)); // Saumon clair
                            label.setText("<html><center>MOT<br>DOUBLE</center></html>");
                            break;
                        case "MT":
                            panel.setBackground(new Color(204, 51, 51)); // Rouge foncé
                            label.setText("<html><center>MOT<br>TRIPLE</center></html>");
                            break;
                        default:
                            panel.setBackground(new Color(75, 173, 101));
                    }
                    Font font = new Font(" Ubuntu", Font.BOLD, 12);
                    label.setFont(font);

                    // Ajout de l'effet 3D
                    panel.add(label, BorderLayout.CENTER);
                }
                mainPanel.add(panel);

            }
        }

        // Ajout du panneau principal et du panneau flou à la fenêtre
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si
        // nécessaire
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton effacer = new JButton("Effacer");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");

        footerPanel.add(valider);
        footerPanel.add(effacer);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);

        add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }

}

// TODO, la pose de pièces sur le plateau
