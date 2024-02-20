package model;
import java.util.*;

public class Joueur {

// Attributs
    private int score;
    private String nom;
    private List ListesLettre;
    
//Constructeur
    public Joueur(String nameGiven) {
        this.score = 0;
        this.nom = nameGiven;
    }

// Méthodes;
    public int getScore(){
        return this.score   
    }
    public String getNom(){
        return this.nom  
    }
    public void jouer() {
        // TODO implement here
    }
    public void piocherSac() {
        // TODO implement here
    }
    public void prendreLettre(List Lettre) {
        this.ListesLettre.add(Lettre)    
    }
    public void deposerLettre() {
        // TODO implement here
    }
    public void calculScore() {
        // TODO implement here
    }
    public void annulerTour() {}
        /*/ TODO implement here
    }
    public void placerLettre/**(Coordonées + placerLettre et deposerLettre){
        // TODO implement here
    }
    

}