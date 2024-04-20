package model;
import java.io.Serializable;

public class Lettre implements Serializable{

    //Attributs
    private char lettre;
    private int points;
    private boolean joker = false;
    private Case casee = null;

    //Constructeur
    public Lettre(char l) {
        this.lettre = l;

        if (l == 'A' || l == 'E' || l == 'I' || l == 'L' || l == 'N' || l == 'O' || l == 'R' || l == 'S' || l == 'T' || l == 'U') {
            this.points = 1;
        } else if (l == 'D' || l == 'G' || l == 'M') {
            this.points = 2;
        } else if (l == 'B' || l == 'C'  || l == 'P') {
            this.points = 3;
        } else if (l == 'F' || l == 'H' || l == 'V') {
            this.points = 4;
        } else if (l == 'J' || l == 'Q') {
            this.points = 8;
        } else if (l == 'K' || l == 'W' || l == 'X' || l == 'Y' || l == 'Z') {
            this.points = 10;
        } else if (l == '*') {
            this.points = 0;
            this.joker = true;
        }
    }

    //Getter / Setter
    public char getLettre() {
        return this.lettre;
    }
    public int getPoints() {
        return this.points;
    }
    public boolean getJoker() {
        return this.joker;
    }
    public Case getCase() {
        return this.casee;
    }
    public void setCase(Case c) {
        this.casee = c;
    }
    public void setLettre(char l) {
        this.lettre = l;
    }

    public void removeCase() {
        this.casee = null;
    }

    //Méthodes

}