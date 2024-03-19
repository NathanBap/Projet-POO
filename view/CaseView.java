package view;
import model.Case;

import java.awt.*;
import javax.swing.*;

public class CaseView extends JPanel{
    private Case casee;

    public CaseView(Case casee) {
        this.casee = casee;
        setLayout(new BorderLayout());
        JLabel label = new JLabel(String.valueOf(casee.getBonus()));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Case getCase() {
        return casee;
    }
}
