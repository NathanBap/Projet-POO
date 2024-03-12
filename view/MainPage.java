package view;

import javax.swing.*;
import controler.Main;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class MainPage extends JFrame {

    Main controlleur = new Main();

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
        // Ajoute le menu à la barre du Menu (bizarre mais sinon on a pas l'option menu)
        menubar.add(menu);
        this.setJMenuBar(menubar);
        setTitle("Page de Bienvenue");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        e1.addActionListener(new ActionListener() {
            //Fonction de l'option donc potentiellement une autre action que rediriger vers le dépot
                @Override
                public void actionPerformed(ActionEvent e) {
                    controlleur.openWebPage("https://github.com/NathanBap/Projet-POO");
                }
            });

        e2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.openWebPage("https://github.com/NathanBap/Projet-POO");
            }
        });

        JLabel welcomeLabel = new JLabel("Bienvenue sur notre Scrabble !");
        //Une fois le squelette du Scrabble assez poussé -> faire une page d'introduction au principe du scrabble et du projet
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton launchButton = new JButton("Lancer l'application");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.launchOtherJavaFile();
            }
        });

        JPanel footerPanel = new JPanel();
        // Définir la couleur de fond ou ajouter d'autres composants au footerPanel si nécessaire
        footerPanel.setBackground(Color.WHITE);
        add(footerPanel, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(25));
        panel.add(launchButton);
        //Tout pomper sur le code de l'hotel
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
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