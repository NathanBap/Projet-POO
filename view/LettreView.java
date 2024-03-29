package view;
import model.Lettre;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LettreView extends JPanel {
    public LettreView(List<Lettre> lettres) {
        setLayout(new GridLayout(1, lettres.size())); // mise en page pour afficher toutes les lettres en ligne
        for (Lettre lettre : lettres) {
            JPanel lettrePanel = new JPanel(new BorderLayout());
            // un JLabel pour afficher la lettre au centre
            JLabel lettreLabel = new JLabel(String.valueOf(lettre.getLettre()));
            lettreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lettreLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            lettreLabel.setFont(new Font("Arial", Font.BOLD, 20));
            lettrePanel.add(lettreLabel, BorderLayout.CENTER);

            // JLabel pour afficher les points
            JLabel pointsLabel = new JLabel(String.valueOf(lettre.getPoints()));
            pointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            pointsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            pointsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            lettrePanel.add(pointsLabel, BorderLayout.SOUTH);

            lettrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            lettrePanel.setPreferredSize(new Dimension(50, 50));
            lettrePanel.setBackground(new Color(255, 245, 215));
            // Appliquer un effet 3D
            lettrePanel.setBorder(BorderFactory.createRaisedBevelBorder());

            this.add(lettrePanel);
        }
    }
}