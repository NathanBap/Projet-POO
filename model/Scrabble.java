package model;

import java.util.*;

public class Scrabble {
    private List<Joueur> joueurs = new ArrayList<Joueur>();
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        plateau.initPlateau();

        Sac sac = new Sac();

        afficherPlateau(plateau);
    }

    public static void afficherPlateau(Plateau plateau) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(plateau.getCase(i, j).getBonus() + " ");
            }
            System.out.println();
        }
    }
    // 2 joueurs minimum 4 maximum
    public static void initJoueurs() {

    }
    // Pour déterminer quel joueur commence
    public static void tirageAuSort() {
        
    }
}