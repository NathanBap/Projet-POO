package model;
import java.util.*;


public class Plateau {
    //Attributs
    private Case[][] plateau;

    //Constructeur
    public Plateau() {
        this.plateau = new Case[15][15];
    }

    //Getters / Setters
    public Case[][] getPlateau() {
        return this.plateau;
    }

    //Méthodes
    public void initPlateau() {  // A modifier pour les cases avec des bonus
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.plateau[i][j] = new Case(i, j);
            }
        }
    }

}