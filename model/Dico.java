package model;

import java.util.*;
import java.io.BufferedReader;// lire un texte à partir d'un flux d'entrée caractère
import java.io.FileReader; //lire des caractères a partir d un fichier
import java.io.IOException;
import java.io.Serializable;

public class Dico extends ArrayList<String> implements Serializable{
    // attribut
    // private List<String> dictionnaire;

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
    }

    // vérifier si un mot est valide
    public boolean estValide(String mot) {
        return contains(mot);
    }

    public List<String> getMots() {
        return this;
    }

}
