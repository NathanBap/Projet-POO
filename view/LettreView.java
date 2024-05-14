package view;
import model.Lettre;
import javax.swing.*;
import java.awt.*;

public class LettreView extends JPanel {
    private Lettre piece;
    private boolean isSelected = false;

    public LettreView(Lettre piece) {
        this.piece = piece;
        paintLettreView();
    }

    public void paintLettreView() {
        char lettre = piece.getLettre();

        removeAll();
        // Définir la mise en page du JPanel
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

    public void setSelected() {
        this.isSelected = !this.isSelected;
        if (this.isSelected) {
            setBorder(null);
        } else {
            setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }
    public boolean isSelected() {
        return isSelected;
    }
}