package controler;

import java.awt.event.*;
import javax.swing.*;

import view.PlateauView;

public class ButtonsControler implements ActionListener {
    private JButton button;
    private String action;
    private PlateauView plateauView;

    public ButtonsControler(JButton button, PlateauView plateauView) {
        this.button = button;
        this.action = button.getText();
        this.plateauView = plateauView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action.equals("Valider")) {
            System.out.println("Valider clicked");
            plateauView.valider();
        } 
    }
}
