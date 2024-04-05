package view;

import model.Case;
import model.Lettre;

import java.awt.*;
import javax.swing.*;

public class CaseView extends JPanel {
    private Case casee;
    private LettreView lettrePosee;

    public CaseView(Case casee) {
        this.casee = casee;
        int i = casee.getX();
        int j = casee.getY();

        setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Création d'un panneau pour chaque case
        // Mettre tout ça dans CaseView
        setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Bordure blanche

        if (i == 7 && j == 7) {
            setBackground(Color.PINK);

        } else {
            // Récupérer le bonus de la case
            String bonus = casee.getBonus();

            // Modifier la couleur de fond en fonction du bonus
            switch (bonus) {
                case "LD":
                    setBackground(new Color(153, 204, 255)); // Bleu clair
                    label.setText("<html><center>LETTRE<br>DOUBLE</center></html>");
                    break;
                case "LT":
                    setBackground(new Color(40, 103, 199)); // Bleu foncé
                    label.setText("<html><center>LETTRE<br>TRIPLE</center></html>");
                    break;
                case "MD":
                    setBackground(new Color(255, 204, 102)); // Saumon clair
                    label.setText("<html><center>MOT<br>DOUBLE</center></html>");
                    break;
                case "MT":
                    setBackground(new Color(204, 51, 51)); // Rouge foncé
                    label.setText("<html><center>MOT<br>TRIPLE</center></html>");
                    break;
                default:
                    setBackground(new Color(75, 173, 101));
            }
            Font font = new Font(" Ubuntu", Font.BOLD, 12);
            label.setFont(font);

            // Ajout de l'effet 3D
            add(label, BorderLayout.CENTER);
        }
    }

    public Case getCase() {
        return casee;
    }
    public LettreView getLettrePosee() {
        return lettrePosee;
    }

    public void setLettrePosee(LettreView lettrePosee) {
        this.lettrePosee = lettrePosee;
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                component.setVisible(false);
            }
        }
    
        add(lettrePosee, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    public void removeLettrePosee() {
        // A FAIRE : La remettre dans la main du joueur
        if (lettrePosee != null) {
            remove(lettrePosee);
            lettrePosee = null;

            Component[] components = getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    component.setVisible(true);
                }
            }

            revalidate();
        }
    }
}
