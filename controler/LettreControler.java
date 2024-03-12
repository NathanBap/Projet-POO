package controler;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import model.Lettre;

public class LettreControler extends MouseAdapter {
    private JPanel lettre;

    public LettreControler(JPanel lettre) {
        this.lettre = lettre;
        this.lettre.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Lettre clicked");
        lettre.setBackground(Color.RED);
    }
}