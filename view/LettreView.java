package view;

import model.Lettre;
import javax.swing.*;
import java.awt.*;

public class LettreView extends JLabel {
    private Lettre piece;

    public LettreView() {
        this.piece = new Lettre('A');
        setText(String.valueOf(piece.getLettre()));
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(50, 50));
    }
}