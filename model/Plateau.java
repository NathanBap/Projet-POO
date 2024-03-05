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
    public Case getCase(int x, int y) {
        return this.plateau[x][y];
    }

    //Méthodes
    public void initPlateau() {  // A modifier pour les cases avec des bonus
        List<Integer> LD = new ArrayList<Integer>(Arrays.asList(3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221));
        List<Integer> LT = new ArrayList<Integer>(Arrays.asList(20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204));
        List<Integer> MD = new ArrayList<Integer>(Arrays.asList(16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208));
        List<Integer> MT = new ArrayList<Integer>(Arrays.asList(0, 7, 14, 105, 119, 210, 217, 224)); 

        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                if (LD.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "LD");
                } else if (LT.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "LT");
                } else if (MD.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "MD");
                } else if (MT.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "MT");
                } else {
                    this.plateau[row][col] = new Case(row, col);
                }
            }
        }
    }

    public boolean jouerLettre(int x, int y, Lettre lettre) {
        if (this.plateau[x][y].placerLettre(lettre)) {
            return true;
        }
        return false;
    }

    public void jouerMot() { // Appelé par la classe joueur qui prend en entrée soit une liste de lettres soit un String
    }
}