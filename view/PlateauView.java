package view;

import model.Case;
import model.Plateau;

import javax.swing.*;

import controler.ButtonsControler;
import controler.CaseControler;
import controler.LettreControler;

import java.awt.*;
import java.util.*;

public class PlateauView extends JFrame {
    private Plateau plateau;
    private java.util.List<CaseView> allCases = new ArrayList<CaseView>();

    public LettreView lettreClicked;

    public JPanel lettreClicked;
    
    public PlateauView() {
        initGame();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    private void initGame() {
        plateau = new Plateau();
        plateau.initPlateau();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Scrabble POO");
        // Création du panneau principal du plateau
        JPanel mainPanel = new JPanel(new GridLayout(15, 15));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
              
                
                CaseView panel = new CaseView(plateau.getCase(i, j));
                panel.addMouseListener(new CaseControler(casee, this));
              
                // Création d'un panneau pour chaque case
                // Mettre tout ça dans CaseView
                panel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Bordure blanche
              
                // TMP
                JLabel label = new JLabel();

                if (i == 7 && j == 7) {
                    panel.setBackground(Color.PINK);

                } else {
                    // Récupérer le bonus de la case
                    String bonus = plateau.getCase(i, j).getBonus();

                    // Modifier la couleur de fond en fonction du bonus
                    switch (bonus) {
                        case "LD":
                            panel.setBackground(new Color(153, 204, 255)); // Bleu clair
                            label.setText("<html><center>LETTRE<br>DOUBLE</center></html>");
                            break;
                        case "LT":
                            panel.setBackground(new Color(40, 103, 199)); // Bleu foncé
                            label.setText("<html><center>LETTRE<br>TRIPLE</center></html>");
                            break;
                        case "MD":
                            panel.setBackground(new Color(255, 204, 102)); // Saumon clair
                            label.setText("<html><center>MOT<br>DOUBLE</center></html>");
                            break;
                        case "MT":
                            panel.setBackground(new Color(204, 51, 51)); // Rouge foncé
                            label.setText("<html><center>MOT<br>TRIPLE</center></html>");
                            break;
                        default:
                            panel.setBackground(new Color(75, 173, 101));
                    }
                    Font font = new Font(" Ubuntu", Font.BOLD, 12);
                    label.setFont(font);

                    // Ajout de l'effet 3D
                    panel.add(label, BorderLayout.CENTER);
                }
                mainPanel.add(panel);

                allCases.add(casee);


            }
        }

        // Ajout du panneau principal et du panneau flou à la fenêtre
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setLocationRelativeTo(null);

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si
        // nécessaire
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");
        LettreView lettre = new LettreView('A');
        LettreView lettre2 = new LettreView('B');
        LettreView lettre3 = new LettreView('C');


        footerPanel.add(valider);
        footerPanel.add(annuler);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);
        footerPanel.add(lettre);
        footerPanel.add(lettre2);
        footerPanel.add(lettre3);
        
        add(footerPanel, BorderLayout.SOUTH);

        lettre.addMouseListener(new LettreControler(lettre, this));
        lettre2.addMouseListener(new LettreControler(lettre2, this));
        lettre3.addMouseListener(new LettreControler(lettre3, this));
        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));

    }

    public void removeAllLetters() {
        for (CaseView c : this.allCases) {
            c.removeLettrePosee();
        }
        repaint();
        //revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
        });
    }

}

// TODO, la pose de pièces sur le plateau
