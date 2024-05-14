package controler;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import javax.swing.*;

import view.LettreView;
import view.PlateauView;
import model.Plateau;

public class ButtonsControler implements ActionListener, Serializable {
    private JButton button;
    private String action;
    private Plateau plateau;
    private PlateauView plateauView;
    public static float passerCount = 0;

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
                    if (plateau.getJoueurActuel().getListeLettre().size() == 0) {
                        plateauView.finPartie();
                        return;
                    }
                    plateauView.remplirMain();
                    plateauView.joueurSuivant();
                    passerCount = 0;
                }

            }

        } else if (action.equals("Aide")) {
            List<LettreView> footerLettres = plateauView.getLettreView();

            // Conversion des LettreView en caractères
            List<Character> availableLetters = new ArrayList<>();
            for (LettreView lettreView : footerLettres) {
                availableLetters.add(lettreView.getLettre());
            }

            List<String> suggestedWords = plateau.suggestWord(availableLetters);
            if (!suggestedWords.isEmpty()) {
                StringBuilder sb = new StringBuilder("Mots suggérés :\n");
                for (String word : suggestedWords) {
                    sb.append(word).append("\n");
                }
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false); // Empêche la modification du texte
                JScrollPane scrollPane = new JScrollPane(textArea);

                scrollPane.setPreferredSize(new Dimension(300, 200));

                JOptionPane.showMessageDialog(null, scrollPane, "Mots suggérés", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Aucun mot suggéré trouvé.", "Aucun mot suggéré",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        else if (action.equals("Annuler tout")) {
            plateauView.removeAllLetters();
            plateau.annuler();
        }
        else if (action.equals("Annuler")) {
            plateauView.removeLastLetter();
        }
        else if (action.equals("Echanger")) {
            plateauView.echangerLettres();
            passerCount = 0;
        }
        else if (action.equals("Passer")) {
            plateauView.joueurSuivant();
            passerCount += 1;
            // La partie s'arrête si tous les joueurs passent 3 fois d'affilé
            if (passerCount / plateau.getJoueurs().size() >= 3) {
                //Décompte du score de chaque joueur le cumul des valeurs des lettres restantes dans leur main
                plateauView.finPartie();
            }
        }
        else if (action.equals("Reprendre")) {

        } else if (action.equals("Sauvegarder et quitter")) {
            // echapDialog.setVisible(false);
            try {
                FileOutputStream fos = new FileOutputStream("sauvegarde.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(plateauView); 
                oos.close();
                fos.close();
                System.exit(0);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
