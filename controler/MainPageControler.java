package controler;

import view.*;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainPageControler extends MouseAdapter {

    private Clip musiqueFond;
    public boolean sonMute = false;

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
    
    public void ouvrirPDF() {
        String filePath = "ressources/scrabble.pdf";
        if (Desktop.isDesktopSupported()) {
            try {
                File file = new File(filePath);
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    

    public void jouerSon(String filepath) {
		try {
			File musicPath = new File(filepath);
			if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
			} else {
				System.out.println("Can't find file");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void jouerSonContinu(String filePath) {
        try {
            // Charger le fichier audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            
            // Créer un clip audio
            musiqueFond = AudioSystem.getClip();
            
            // Ouvrir le flux audio et charger le fichier dans le clip
            musiqueFond.open(audioInputStream);
            
            // Définir le clip pour qu'il se répète en continu
            musiqueFond.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSonContinu() {
        if (musiqueFond != null && musiqueFond.isRunning()) {
            musiqueFond.stop();
        }
    }
}
