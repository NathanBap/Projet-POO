package model;

import java.util.*;

public class Scrabble {



    public Scrabble(List<Joueur> joueurs) {

    }
    //joueurActuel.getindex
    //joeurActuel = joueurActuelIndex

    

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

    
}