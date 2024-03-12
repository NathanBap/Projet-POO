package controler;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel;

import view.PlateauView;

public class LettreControler extends MouseAdapter {
    private JPanel lettreView;
    private PlateauView plateau;

    public LettreControler(JPanel lettreView, PlateauView plateau) {
        this.lettreView = lettreView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Lettre clicked");
        lettreView.setBackground(Color.RED);
        plateau.lettreClicked = lettreView;
    }
}
