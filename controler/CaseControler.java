package controler;
import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CaseControler extends MouseAdapter{
    private CaseView casee;
    private PlateauView plateauView;

    public CaseControler(CaseView casee, PlateauView plateauView) {
        this.casee = casee;
        this.plateauView = plateauView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Rajouter une condition pour vérifier si la case est adjacente à une lettre déjà placée SAUF pour la première lettre
        if (this.plateauView.lettreClicked != null && this.casee.getCase().isEmpty() && this.plateauView.lettreClicked.getPiece().getCase() == null){
            Lettre lettrePlaced = this.plateauView.lettreClicked.getPiece();

            this.casee.getCase().placerLettre(lettrePlaced);
            this.plateauView.getPlateau().addPendingCase(casee.getCase());
            System.out.println("Cases in controler : " + this.casee.getCase());
            //System.out.println(this.plateauView.getPlateau().getCase(casee.getCase().getX(), casee.getCase().getY()));
            // Ajouter le fait d'enlever la lettre de la main du joueur

            //System.out.println("Case clicked");
            this.casee.removeAll();
            this.casee.add(plateauView.lettreClicked);
            this.plateauView.lettreClicked.setBackground(Color.LIGHT_GRAY);
            this.plateauView.lettreClicked = null;
            this.casee.revalidate();
            this.casee.repaint();
            this.plateauView.repaint();
        } else {
            System.out.println("Aucune lettre n'a été sélectionnée ou case pas adjacente.");
        }
    }
}
