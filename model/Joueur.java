package model;
import java.util.*;

public class Joueur {

// Attributs
    private int score;
    private String nom;
    private List<Lettre> listeLettre = new ArrayList<Lettre>();
    
//Constructeur
    public Joueur(String nameGiven) {
        this.score = 0;
        this.nom = nameGiven;
    }

    //Getters / Setters
    public int getScore(){
        return this.score;   
    }
    public String getNom(){
        return this.nom;  
    }
    public List<Lettre> getListeLettre() {
        return this.listeLettre;
    }

    // Méthodes;
    public void jouer() {
        // TODO implement here
    }
    public void piocherSac() {
        // TODO implement here
    }
    public void prendreLettre(Lettre l) {
        this.listeLettre.add(l);  
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