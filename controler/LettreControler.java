package controler;

import java.awt.event.*;
import java.io.Serializable;
import java.awt.*;
import java.awt.datatransfer.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.dnd.*;

import view.*;

public class LettreControler extends MouseAdapter implements Serializable {
    private LettreView lettreView;
    private PlateauView plateau;
    private JPanel listeLettres;
    // La référence changeait lors du drag and drop, donc on a décidé de la rendre statique
    public static LettreView lettreDragged;

    public LettreControler(LettreView lettreView, PlateauView plateau, JPanel listeLettres) {
        this.lettreView = lettreView;
        this.plateau = plateau;
        this.listeLettres = listeLettres;

        createDragControler();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Lettre clicked : " + lettreView.isSelected());
        if (plateau.lettreClicked != null) {
            this.plateau.lettreClicked.setSelected();
        }
        if (lettreView.getPiece().getCase() == null) {
            if (plateau.lettreClicked == lettreView) {
                this.plateau.lettreClicked = null;
            } else {
                this.lettreView.setSelected();
                this.plateau.lettreClicked = lettreView;
            }
        }
    }

    private void createDragControler() {
        DragSource dragSource = new DragSource();
        DragSourceListener dsl = new DragSourceAdapter() {
            @Override
            public void dragDropEnd(DragSourceDropEvent dsde) {
                if (dsde.getDropSuccess()) {
                    listeLettres.remove(lettreView);
                    listeLettres.revalidate();
                }
            }
        };
        dragSource.addDragSourceListener(dsl);
        dragSource.createDefaultDragGestureRecognizer(lettreView, DnDConstants.ACTION_COPY, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable transferable = new PanelTransferable(lettreView);
                lettreDragged = lettreView;
                dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, null);
            }
        });
    }

    static class PanelTransferable implements Transferable {
        public static final DataFlavor PANEL_DATA_FLAVOR = new DataFlavor(LettreView.class, "LettreView");
        private LettreView panel;

        public PanelTransferable(LettreView panel) {
            this.panel = panel;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{PANEL_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(PANEL_DATA_FLAVOR);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (flavor.equals(PANEL_DATA_FLAVOR)) {
                return panel;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }
    }
}
