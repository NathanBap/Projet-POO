package controler;

import java.awt.event.*;
import javax.swing.*;

import view.PlateauView;
import model.Plateau;

public class ButtonsControler implements ActionListener {
    private JButton button;
    private String action;
    private Plateau plateau;
    private PlateauView plateauView;

    public ButtonsControler(JButton button, PlateauView plateauView) {
        this.button = button;
        this.action = button.getText();
        this.plateauView = plateauView;
        this.plateau = plateauView.getPlateau();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action.equals("Valider")) {
            System.out.println("Valider clicked");

            if (!plateau.arePendingCases()) {
                String msg = "Aucune lettre n'a été placée sur le plateau.";
                JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);

            } else {
                //plateauView.valider();
                if (plateau.valider()) {
                    System.out.println("Valider");  // A FAIRE : Remplacer par un affichage graphique
                    // A FAIRE : Remettre le background des lettres normal
                    
                } else {
                    System.out.println("Impossible de valider");  // Remplacer par un affichage graphique
                    String msg = "Les lettres ne sont pas adjacentes ou ne forment pas un mot valide.";
                    JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
                    plateau.annuler();
                    plateauView.removeAllLetters();
                }
            }

        }
        
        else if (action.equals("Annuler")) {
            System.out.println("Annuler clicked");
            plateau.annuler();
            plateauView.removeAllLetters();
        }
    }
}
