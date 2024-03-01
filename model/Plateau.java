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
        List<Integer> LD = new ArrayList<Integer>(Arrays.asList(3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221));
        List<Integer> LT = new ArrayList<Integer>(Arrays.asList(20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204));
        List<Integer> MD = new ArrayList<Integer>(Arrays.asList(16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208));
        List<Integer> MT = new ArrayList<Integer>(Arrays.asList(0, 7, 14, 105, 119, 210, 217, 224)); 

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (LD.contains(i * 15 + j)) {
                    this.plateau[i][j] = new Case(i, j, "LD");
                } else if (LT.contains(i * 15 + j)) {
                    this.plateau[i][j] = new Case(i, j, "LT");
                } else if (MD.contains(i * 15 + j)) {
                    this.plateau[i][j] = new Case(i, j, "MD");
                } else if (MT.contains(i * 15 + j)) {
                    this.plateau[i][j] = new Case(i, j, "MT");
                } else {
                    this.plateau[i][j] = new Case(i, j);
                }
            }
        }
    }

    public Case getCase(int x, int y) {
        return this.plateau[x][y];
    }

}