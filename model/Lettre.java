package model;
import java.util.*;

public class Lettre {

    //Attributs
    private String lettre;
    private int points;
    private boolean joker;
    private Case casee;

    //Constructeur
    public Lettre(String l) {
        this.lettre = l;
    }

    //Getter / Setter
    public String getLettre() {
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

    //Méthodes

}