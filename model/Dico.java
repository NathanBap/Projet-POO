package model;

import java.util.*;
import java.io.BufferedReader;// lire un texte à partir d'un flux d'entrée caractère
import java.io.FileReader; //lire des caractères a partir d un fichier
import java.io.IOException;

public class Dico extends ArrayList<String>{
    // attribut
    //private List<String> dictionnaire;

    public Dico() {
        chargerDictionnaire();
    }

    // charger le dictionnaire depuis un fichier
    public void chargerDictionnaire() {
        String nomFichier = "dico_Scrabble.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String mot;
            // Boucle de lecture des lignes du fichier
            while ((mot = br.readLine()) != null) {
                add(mot.trim());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        // for (int i = 0; i < 20; i++) {
        // System.out.println(dictionnaire.get(i));
        // }
    }

    // vérifier si un mot est valide
    public boolean estValide(String mot) {
        return contains(mot);
    }

}
