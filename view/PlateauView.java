package view;
import javax.swing.JFrame;


public class PlateauView extends JFrame {
    public PlateauView() {
        setTitle("Jeu de Scrabble");
        setSize(800, 600); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour fermer l'application

        GrilleView boardPanel = new GrilleView();
        add(boardPanel);
        pack(); // Ajuste la taille de JFrame basée sur le contenu
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            PlateauView frame = new PlateauView();
            frame.setVisible(true);
        });
    }
}
