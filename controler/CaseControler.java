package controler;

import view.*;
import model.*;

import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import javax.swing.*;

import java.awt.*;

public class CaseControler extends MouseAdapter{
    private CaseView casee;
    private PlateauView plateauView;

    public CaseControler(CaseView casee, PlateauView plateauView) {
        this.casee = casee;
        this.plateauView = plateauView;

        createDropControler();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Rajouter une condition pour vérifier si la case est adjacente à une lettre déjà placée SAUF pour la première lettre
        if (this.plateauView.lettreClicked != null && this.casee.getCase().isEmpty() && this.plateauView.lettreClicked.getPiece().getCase() == null){
            Lettre lettrePlaced = this.plateauView.lettreClicked.getPiece();

            // Place la lettre dans le model
            this.casee.getCase().placerLettre(lettrePlaced);
            this.plateauView.getPlateau().addPendingCase(casee.getCase());

            // A FAIRE : Ajouter le fait d'enlever la lettre de la main du joueur

            // Place la lettre dans la vue
            this.casee.setLettrePosee(plateauView.lettreClicked);
            this.plateauView.lettreClicked = null;
            this.casee.revalidate();
            this.casee.repaint();
            this.plateauView.repaint();
        } else {
            System.out.println("Aucune lettre n'a été sél tionnée u case pas adjacente.");
        }
    }

    private void createDropControler() {
        // Création de l'écouteur de drop
        DropTarget dropTarget = new DropTarget(casee, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable transferable = dtde.getTransferable();
                    if (transferable.isDataFlavorSupported(LettreControler.PanelTransferable.PANEL_DATA_FLAVOR)) {
                        // LettreView lettreDropped = (LettreView) transferable.getTransferData(LettreControler.PanelTransferable.PANEL_DATA_FLAVOR);
                        LettreView lettreDropped = LettreControler.lettreDragged;
                        if (casee.getCase().isEmpty()){
                            Lettre lettrePlaced = lettreDropped.getPiece();
                            // Place la lettre dans le model
                            casee.getCase().placerLettre(lettrePlaced);
                            plateauView.getPlateau().addPendingCase(casee.getCase());
                
                            // Place la lettre dans la vue
                            casee.setLettrePosee(lettreDropped);
                            plateauView.lettreClicked = null;

                            dtde.dropComplete(true);
                        } else {
                            System.out.println("Une lettre est déjà placée ici");
                            dtde.dropComplete(false);
                        }
                        LettreControler.lettreDragged = null;
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                        
                    
                    } else {
                        System.out.println("No drop");
                        dtde.rejectDrop();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    dtde.rejectDrop();
                }
            }
        });
    }
}
