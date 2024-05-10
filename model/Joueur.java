package model;
import java.io.Serializable;
import java.util.*;

public class Joueur implements Serializable, Comparable<Joueur> {

// Attributs
    private int score = 0;
    private String nom;
    private Sac sac;
    private List<Lettre> listeLettre = new ArrayList<Lettre>();
    private int tailleMain;
    private Plateau plateau;
    
    @Override
    public int compareTo(Joueur j) {
        return j.score - this.score;
    }   

    //Constructeur
    public Joueur(String name, Plateau plateau) {
        this.nom = name;
        this.plateau = plateau;
        this.sac = plateau.getSac();
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
    public void initMain() {
        for (int i = 0; i < 7; i++) {
            this.listeLettre.add(sac.piocher());
        }
        tailleMain = 7;
    }
    public void addScore(int score) {
        this.score += score;
    }

    public void prendreLettre(Lettre l) {
        this.listeLettre.add(l);  
    }
    public void deposerLettre(Lettre l) {
        System.out.println("remove : " + listeLettre.remove(l));
    }

    public void afficherMain() {
        for (Lettre l : listeLettre) {
            System.out.println(l.getLettre());
        }
    }
    public List<Lettre> remplirMain(Sac sac) {
        List<Lettre> lettrePiochees = new ArrayList<>();
        while (listeLettre.size() < 7) {
            Lettre l = sac.piocher();
            if (l != null) {
                listeLettre.add(l);
                lettrePiochees.add(l);
            } else {
                break;
            }
        }
        return lettrePiochees;
    }

}