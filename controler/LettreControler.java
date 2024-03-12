package controler;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class LettreControler extends MouseAdapter implements DragGestureListener {
    private JPanel lettreView;

    public LettreControler(JPanel lettreView) {
        this.lettreView = lettreView;

        // Configure lettreView for dragging
        DragSource ds = DragSource.getDefaultDragSource();
        ds.createDefaultDragGestureRecognizer(lettreView, DnDConstants.ACTION_MOVE, this);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        try {
            // Start the drag
            Transferable transfer = new StringSelection(lettreView.getName());
            dge.startDrag(DragSource.DefaultCopyDrop, transfer);
            lettreView.setBackground(Color.RED);

        } catch (InvalidDnDOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Lettre clicked");
    }
}