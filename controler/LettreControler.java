package controler;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel;

public class LettreControler extends MouseAdapter {
        private JPanel lettreView;

    public LettreControler(JPanel lettreView) {
        this.lettreView = lettreView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Lettre clicked");
        lettreView.setBackground(Color.RED);
    }
}
