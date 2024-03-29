package model;

import java.util.*;

public class Scrabble {
    private List<Joueur> joueurs = new ArrayList<Joueur>();
    private int joueurActuelIndex;
    public Joueur joueurActuel;


    public Scrabble(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.joueurActuelIndex = 0;
        this.joueurActuel = null;
    }
    //joueurActuel.getindex
    //joeurActuel = joueurActuelIndex

    public Joueur getJoueurActuel() {
        return joueurs.get(joueurActuelIndex);
    }

    public void passerAuJoueurSuivant() {
        joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.size();
    }
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

    public void debutDuTour() {
        // Initialiser les mains des joueurs
        for (Joueur joueur : joueurs) {
            joueur.initMain();
        }
        // Déterminer quel joueur commence
        joueurActuelIndex = tirageAuSort(joueurs);
        joueurActuel = joueurs.get(joueurActuelIndex);
    }
    
    public void changementJoueur() {
        // obtenir le joueur actuel
        Joueur joueurActuel = getJoueurActuel();
        if (/*validé est cliqué*/){
            passerAuJoueurSuivant();
        }
    }

    // 2 joueurs minimum 4 maximum
    public static void initJoueurs() {

    }
    
    // Pour déterminer quel joueur commence
    public int tirageAuSort(List<Joueur> joueurs) {
        Random rand = new Random();
        return rand.nextInt(joueurs.size());
    }
}