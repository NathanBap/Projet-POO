package controler;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CaseControler extends MouseAdapter{
    private JLabel casee;

    public CaseControler(JLabel casee) {
        this.casee = casee;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Case clicked");
        casee.setOpaque(true);
        casee.setBackground(Color.LIGHT_GRAY);
        casee.setText("...");
    }
}
