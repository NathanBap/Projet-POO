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
                String msg = "";
                String valider = plateau.valider();
                
                switch (valider) {
                    case "Mot invalide":
                        msg = "Le mot formé n'est pas valide.";
                        break;
                    case "Non centre":
                        msg = "Le mot formé au premier tour doit passer par la case centrale.";
                        break;
                    case "Mot non adjacent":
                        msg = "Le mot formé n'est pas adjacent à un ancien mot.";
                        break;
                    case "Pas assez de lettres":
                        msg = "Il faut poser minimum 2 lettres.";
                        break;
                    case "Pas aligne":
                        msg = "Les lettres posées ne sont pas toutes sur la même ligne ou colonne.";
                        break;
                    case "Pas connecte":
                        msg = "Les lettres posées ne sont pas toutes connectées.";
                        break;
                    default:
                        break;
                }

                if (msg != "") {
                    JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
                    plateauView.removeAllLetters();
                    plateau.annuler();
                } else {
                    msg = "Les lettres ont été placées avec succès, mots formés : \n" + valider;
                    JOptionPane.showMessageDialog(null, msg, "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                // A FAIRE : Remettre le background des lettres normal
            }

        }
        
        else if (action.equals("Annuler")) {
            System.out.println("Annuler clicked");
            plateauView.removeAllLetters();
            plateau.annuler();
        }
    }
}
