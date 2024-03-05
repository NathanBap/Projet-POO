package model;
import java.util.*;

public class Joueur {

// Attributs
    private int score;
    private String nom;
    private Sac sac;
    private List<Lettre> listeLettre = new ArrayList<Lettre>();
    private int tailleMain;
    private Plateau plateau;
    
//Constructeur
    public Joueur(String name, Sac sac, Plateau plateau) {
        this.score = 0;
        this.nom = name;
        this.sac = sac;
        this.plateau = plateau;
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
        // Gérer à l'interface le drag and drop des lettres sur le plateau
        // Bouton valider qui valide le mot 

        List<Lettre> mot = new ArrayList<Lettre>();
        int xDep, yDep, xArr, yArr; // Coordonnées de départ du mot
        String direction; // H pour Horizontal ou V pour Vertical

        xDep = 0;
        yDep = 0;
        xArr = 0;
        yArr = 0;

        if (mot.size() < 2  /* || !méthode qui vérifie si un mot est valide */) {
            return false;
        }

        if (xDep == xArr) {
            direction = "H";
        } else {
            direction = "V";
        }

        int scoreMot = this.plateau.jouerMot(mot, xDep, yDep, direction);
        if (scoreMot == -1) {
            return false;
        }

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
    public void deposerLettre() {
        // TODO implement here
    }

    public void afficherMain() {
        for (Lettre l : listeLettre) {
            System.out.println(l.getLettre());
        }
    }
    

}