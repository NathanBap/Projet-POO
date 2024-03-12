package controler;

import view.*;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends MouseAdapter {
    
    public void launchOtherJavaFile() {
        // Code pour lancer la vue du plateau
        PlateauView plateauView = new PlateauView();
        plateauView.setVisible(true);
    }

        public void openWebPage(String url) {
        //Fonction d'ouverture du navigateur par defaut et recherche avec l'url donnée en paramètre
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}