package controler;
import view.PlateauView;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CaseControler extends MouseAdapter{
    private JPanel casee;
    private PlateauView plateau;

    public CaseControler(JPanel casee, PlateauView plateau) {
        this.casee = casee;
        this.plateau = plateau;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (plateau.lettreClicked != null) {
            System.out.println("Case clicked");
            casee.removeAll();
            casee.add(plateau.lettreClicked);
            plateau.lettreClicked.setBackground(Color.LIGHT_GRAY);
            plateau.lettreClicked = null;
            casee.revalidate();
            casee.repaint();
            plateau.repaint();
        } else {
            System.out.println("Aucune lettre n'a été sélectionnée.");
        }


    }
}
