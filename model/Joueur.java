package model;
import java.io.Serializable;
import java.util.*;

public class Joueur implements Serializable {

// Attributs
    private int score = 0;
    private String nom;
    private Sac sac;
    private List<Lettre> listeLettre = new ArrayList<Lettre>();
    private int tailleMain;
    private Plateau plateau;
    
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

    // Marque le début du tour du joueur
    public void startTour() {
        String choix = "";  // Le choix est déterminé en cliquant sur un bouton pour choisir l'action de son tour

        // Remplacer par l'affichage graphique et les boutons //
        System.out.println("Voici votre main : \n");
        this.afficherMain();
        System.out.println("Quelle action voulez-vous effectuer ? \n 1 : Jouer \n 2 : Passer \n 3 : Echanger des lettres \n");
        //

        if (choix == "1") {
            this.jouer();
        } else if (choix == "2") {
            this.passer();
        } else {
            this.echangerLettre();
        }
    }

    // Choix 1
    public boolean jouer() {
        return true;
        
    }
    // Choix 2
    public void passer() {

    }
    // Choix 3
    public void echangerLettre() {

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