package controler;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel;

import view.*;

public class LettreControler extends MouseAdapter {
    private LettreView lettreView;
    private PlateauView plateau;

    public LettreControler(LettreView lettreView, PlateauView plateau) {
        this.lettreView = lettreView;
        this.plateau = plateau;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (plateau.lettreClicked != null) {
            this.plateau.lettreClicked.setBackground(new Color(255, 245, 215));
        }
        System.out.println("Lettre clicked");
        this.lettreView.setBackground(Color.RED);
        this.plateau.lettreClicked = lettreView;   
    }
}
