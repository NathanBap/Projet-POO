package view;

import model.*;

import javax.swing.*;

import controler.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.util.List;
import java.awt.dnd.*;

public class PlateauView extends JFrame {
    private Plateau plateau;
    private List<CaseView> allCases = new ArrayList<CaseView>();
    public Joueur joueur;
    public LettreView lettreClicked;
    private JPanel listeLettres;
    
    public PlateauView() {
        initGame();
        this.joueur = plateau.getJoueurActuel();
        initComponents();
        pack(); // Ajuste automatiquement la taille
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    private void initGame() {
        // Scrabble scrabble = new Scrabble(joueurs);
        plateau = new Plateau();
        plateau.initPlateau();
        plateau.initJoueurs();
        plateau.debutDuJeu();
        // scrabble.debutDuTour();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Scrabble POO");
        
        List<Lettre> lettresDuJoueur = joueur.getListeLettre();

        JPanel mainPanel = new JPanel(new GridLayout(15, 15));
        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
              
                
                CaseView casee = new CaseView(plateau.getCase(i, j));
                casee.addMouseListener(new CaseControler(casee, this));

                mainPanel.add(casee);
                allCases.add(casee);

                // Création de l'écouteur de drop
                DropTarget dropTarget = new DropTarget(casee, new DropTargetAdapter() {
                    @Override
                    public void drop(DropTargetDropEvent dtde) {
                        try {
                            Transferable transferable = dtde.getTransferable();
                            if (transferable.isDataFlavorSupported(PanelTransferable.PANEL_DATA_FLAVOR)) {
                                LettreView lettreDropped = (LettreView) transferable.getTransferData(PanelTransferable.PANEL_DATA_FLAVOR);
                                if (casee.getCase().isEmpty()){
                                    Lettre lettrePlaced = lettreDropped.getPiece();
  
                                    // Place la lettre dans le model
                                    casee.getCase().placerLettre(lettrePlaced);
                                    getPlateau().addPendingCase(casee.getCase());
                        
                                    // Place la lettre dans la vue
                                    casee.setLettrePosee(lettreDropped);
                                    lettreClicked = null;
                                } else {
                                    System.out.println("Une lettre est déjà placée ici");
                                }

                                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                                dtde.dropComplete(true);
                            
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
        // Ajout du panneau principal et du panneau flou à la fenêtre
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setLocationRelativeTo(null);  


        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);

        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        JButton aide = new JButton("Aide");
      
        listeLettres = new JPanel();
        for (Lettre l : lettresDuJoueur) {
            LettreView lettre = new LettreView(l.getLettre());
            lettre.addMouseListener(new LettreControler(lettre, this));
            //lettre.addMouseMotionListener(new LettreControler(lettre, this));
            listeLettres.add(lettre);

            // Création de l'écouteur de drag and drop
            DragSource dragSource = new DragSource();
            DragSourceListener dsl = new DragSourceAdapter() {
                @Override
                public void dragDropEnd(DragSourceDropEvent dsde) {
                    if (dsde.getDropSuccess()) {
                        listeLettres.remove(lettre);
                        listeLettres.revalidate();
                    }
                }
            };
            dragSource.addDragSourceListener(dsl);
            dragSource.createDefaultDragGestureRecognizer(lettre, DnDConstants.ACTION_COPY, new DragGestureListener() {
                @Override
                public void dragGestureRecognized(DragGestureEvent dge) {
                    Transferable transferable = new PanelTransferable(lettre);
                    dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, null);
                }
            });
        }

        int score = joueur.getScore();
        JLabel scoreLabel = new JLabel(String.valueOf(score));

        footerPanel.add(valider);
        footerPanel.add(annuler);

        footerPanel.add(listeLettres);
        footerPanel.add(scoreLabel);
        add(footerPanel, BorderLayout.SOUTH);

        valider.addActionListener(new ButtonsControler(valider, this));
        annuler.addActionListener(new ButtonsControler(annuler, this));

    }

    public void removeAllLetters() {
        for (CaseView c : this.allCases) {
            List<Case> pendingCases = plateau.getPendingCases();
            if (pendingCases.contains(c.getCase())) {
                LettreView lettrePosee = c.getLettrePosee();
                listeLettres.add(lettrePosee);
                c.removeLettrePosee();
                lettrePosee.addMouseListener(new LettreControler(lettrePosee, this));
                // A FAIRE : Rajouter le listener pour le drag and drop
            }
        }
        revalidate();
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);
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

