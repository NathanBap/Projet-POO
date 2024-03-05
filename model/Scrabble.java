package model;

public class Scrabble {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        plateau.initPlateau();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(plateau.getCase(i, j).getBonus() + " ");
            }
            System.out.println();
        }
    }
}