package controler;

import view.*;
import model.*;

import java.awt.event.*;
import java.io.Serializable;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import javax.swing.*;

import java.awt.*;

public class CaseControler extends MouseAdapter implements Serializable{
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
            this.plateauView.lettreClicked.setSelected();
            putLetter(this.plateauView.lettreClicked);
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
                            putLetter(lettreDropped);
                        
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

    private void putLetter(LettreView lettreViewPlaced) {
        Lettre lettrePlaced = lettreViewPlaced.getPiece();

        if (lettrePlaced.getJoker()) {
            String msg = "Veuillez choisir la lettre, elle vaudra 0 points";
            String response = null;
            do {
                response = JOptionPane.showInputDialog(null, msg, "Choisir lettre", JOptionPane.INFORMATION_MESSAGE);
            } while (response.length() == 0 || response.length() > 1 || !response.matches("[a-zA-Z]"));                                
            lettrePlaced.setLettre(response.toUpperCase().charAt(0));
            lettreViewPlaced.paintLettreView();
        } 
        lettreViewPlaced.removeMouseListener(lettreViewPlaced.getMouseListeners()[0]);
        lettreViewPlaced.removeMouseListener(lettreViewPlaced.getMouseListeners()[0]);
        lettreViewPlaced.setBorder(BorderFactory.createRaisedBevelBorder());

        // Place la lettre dans le model
        casee.getCase().placerLettre(lettrePlaced);
        plateauView.getPlateau().addPendingCase(casee.getCase());
        plateauView.disableButtons();
        
        // Place la lettre dans la vue
        casee.setLettrePosee(lettreViewPlaced);
        plateauView.lettreClicked = null;
        casee.revalidate();
    }
}
