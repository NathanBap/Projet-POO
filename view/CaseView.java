package view;
import model.Case;
import model.Lettre;

import java.awt.*;
import javax.swing.*;

public class CaseView extends JLayeredPane{
    private Case casee;
    private LettreView lettrePosee;

    public CaseView(Case casee) {
        this.casee = casee;
        setLayout(new BorderLayout());
        JLabel label = new JLabel(String.valueOf(casee.getBonus()));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, 1);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Case getCase() {
        return casee;
    }
    public void setLettrePosee(LettreView lettrePosee) {
        //removeAll();
        this.lettrePosee = lettrePosee;
        add(lettrePosee, 2);
        repaint();
        revalidate();
    }
    public void removeLettrePosee() {
        // A FAIRE : La remettre dans la main du joueur
        if (lettrePosee != null) {
            remove(lettrePosee);
            lettrePosee = null;
        }
        //repaint();
        revalidate();
    }
}
