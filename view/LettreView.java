package view;

import model.Lettre;
import javax.swing.*;
import java.awt.*;

public class LettreView extends JPanel {
    private Lettre piece;

    public LettreView(char lettre) {
        this.piece = new Lettre(lettre);

        // Définir la mise en page du JPanel
        setLayout(new BorderLayout());

        // un JLabel pour afficher la lettre au centre
        JLabel lettreLabel = new JLabel(String.valueOf(lettre));
        lettreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lettreLabel.setVerticalAlignment(SwingConstants.CENTER);
        lettreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(lettreLabel, BorderLayout.CENTER);

        // JLabel pour afficher les points
        JLabel pointsLabel = new JLabel(String.valueOf(piece.getPoints()));
        pointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pointsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(pointsLabel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(100, 100));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LettreView lettreView = new LettreView('W');
            JFrame frame = new JFrame();
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new FlowLayout());
            mainPanel.add(lettreView);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(400, 200));
            frame.add(mainPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}