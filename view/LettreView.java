package view;

import controler.LettreControler;
import model.Lettre;
import javax.swing.*;
import java.awt.*;

public class LettreView extends JPanel {
    private Lettre piece;
    private char lettre;

    public LettreView(char lettre) {

        this.lettre = lettre; // Initialisation de la lettre
        this.piece = new Lettre(lettre);

        setLayout(new BorderLayout());

        // un JLabel pour afficher la lettre au centre
        JLabel lettreLabel = new JLabel(String.valueOf(lettre));
        lettreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lettreLabel.setVerticalAlignment(SwingConstants.CENTER);
        lettreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(lettreLabel, BorderLayout.CENTER);

        // // JLabel pour afficher les points
        JLabel pointsLabel = new JLabel(String.valueOf(piece.getPoints()));
        pointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pointsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(pointsLabel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(50, 50));
        setBackground(new Color(255, 245, 215));
        // Appliquer un effet 3D
        setBorder(BorderFactory.createRaisedBevelBorder());

    }

    public Lettre getPiece() {
        return piece;
    }

    // Dans la classe LettreView
    public char getLettre() {
        // Code pour extraire et renvoyer la lettre associée à cette vue de lettre
        return lettre;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LettreView lettreView = new LettreView('B');
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