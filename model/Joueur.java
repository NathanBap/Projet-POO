package model;
import java.util.*;

public class Joueur {

// Attributs
    private int score;
    private String nom;
    private List<Lettre> ListesLettre = new ArrayList<Lettre>();
    
//Constructeur
    public Joueur(String nameGiven) {
        this.score = 0;
        this.nom = nameGiven;
    }

// Méthodes;
    public int getScore(){
        return this.score;   
    }
    public String getNom(){
        return this.nom;  
    }
    public void jouer() {
        // TODO implement here
    }
    public void piocherSac() {
        // TODO implement here
    }
    public void prendreLettre(Lettre l) {
        this.ListesLettre.add(l);  
    }
    public void deposerLettre() {
        // TODO implement here
    }
    public void calculScore() {
        // TODO implement here
    }
    public void annulerTour() {
    }
    public void placerLettre(/*Coordonées + placerLettre et deposerLettre*/){
        // TODO implement here
    }
    

}