package view;

import javax.swing.*;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class MainPage extends JFrame {
    public MainPage() {
        JMenuItem e1, e2;
        JMenuBar menubar = new JMenuBar();
        // Crée le menu
        JMenu menu = new JMenu("Menu");
        // Crée les éléments du menu
        e1 = new JMenuItem("Options");
        e2 = new JMenuItem("Lien GitHub");
        // Ajoute les éléments au menu
        menu.add(e1);
        menu.addSeparator();
        menu.add(e2);

        menubar.add(menu);
        this.setJMenuBar(menubar);
        setTitle("Page de Bienvenue");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        e1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://github.com/NathanBap/Projet-POO");
            }
        });

        e2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://github.com/NathanBap/Projet-POO");
            }
        });

        JLabel welcomeLabel = new JLabel("Bienvenue sur notre Scrabble !");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton launchButton = new JButton("Lancer l'application");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton
                launchOtherJavaFile();
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(25));
        panel.add(launchButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void launchOtherJavaFile() {
        // Code pour lancer l'autre fichier Java ici
            PlateauView plateauView = new PlateauView();
            plateauView.setVisible(true);

    }

    private void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage();
            }
        });
    }
}

//TODO, comment faire en sorte que la fermeture du plateau ne soit pas celle de la totalité du main