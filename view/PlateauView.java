package view;
import model.*;
import javax.swing.*;
import java.util.List;
import java.awt.*;
import controler.CaseControler;

public class PlateauView extends JFrame {
    private Plateau plateau;
    public JPanel lettreClicked;
    public Joueur joueur;
    public Lettre lettre;
    
    
    public PlateauView(Joueur joueur) {
        this.joueur = joueur;
        initGame();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }

    private void initGame() {
        plateau = new Plateau();
        plateau.initPlateau();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Scrabble POO");
        
        List<Lettre> lettresDuJoueur = joueur.getListeLettre();

        JPanel mainPanel = new JPanel(new GridLayout(15, 15));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                JPanel casee = new JPanel(new BorderLayout());
                JLabel label = new JLabel(String.valueOf(plateau.getCase(i, j).getBonus()));

                casee.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                //label.setHorizontalAlignment(SwingConstants.CENTER);
                // Ajout du gestionnaire d'événements
                label.addMouseListener(new Case(i, j));
                casee.add(label, BorderLayout.CENTER);
                mainPanel.add(casee);

                label.addMouseListener(new CaseControler(casee, this));
            }
        }

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);

        //JPanel lettresPanel = this.VueDeLettre(joueur);

        JButton valider = new JButton("Valider");
        JButton effacer = new JButton("Effacer");
        JButton effacerAll = new JButton("Effacer tout");
        JButton aide = new JButton("Aide");
        JButton test = new JButton("Test");
        JPanel listeLettres = new LettreView(lettresDuJoueur);

        footerPanel.add(valider);
        footerPanel.add(effacer);
        footerPanel.add(effacerAll);
        footerPanel.add(aide);
        footerPanel.add(test);
        footerPanel.add(listeLettres);
        add(footerPanel, BorderLayout.SOUTH);


    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        Sac sac = new Sac();
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("Test", sac, plateau);
        PlateauView plateauView = new PlateauView(joueur);
            plateauView.setVisible(true);
        });
    }
}