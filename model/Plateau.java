package model;
import java.util.*;


public class Plateau {
    //Attributs
    private Case[][] plateau;
    private List<Case> pendingCases = new ArrayList<Case>();
    private Joueur joueurActuel;
    private boolean premierTour = true;

    //Constructeur
    public Plateau() {
        this.plateau = new Case[15][15];
    }

    //Getters / Setters
    public Case[][] getPlateau() {
        return this.plateau;
    }
    public Case getCase(int x, int y) {
        return this.plateau[x][y];
    }
    public boolean getPremierTour() {
        return this.premierTour;
    }

    //Méthodes
    public void addPendingCase(Case c) {
        pendingCases.add(c);
    }
    public boolean arePendingCases() {
        return !this.pendingCases.isEmpty();
    }
    public List<Case> getPendingCases() {
        return this.pendingCases;
    }

    public void initPlateau() {  // A modifier pour les cases avec des bonus
        List<Integer> LD = new ArrayList<Integer>(Arrays.asList(3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221));
        List<Integer> LT = new ArrayList<Integer>(Arrays.asList(20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204));
        List<Integer> MD = new ArrayList<Integer>(Arrays.asList(16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208));
        List<Integer> MT = new ArrayList<Integer>(Arrays.asList(0, 7, 14, 105, 119, 210, 217, 224)); 

        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                if (LD.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "LD");
                } else if (LT.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "LT");
                } else if (MD.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "MD");
                } else if (MT.contains(row * 15 + col)) {
                    this.plateau[row][col] = new Case(row, col, "MT");
                } else {
                    this.plateau[row][col] = new Case(row, col);
                }
            }
        }
    }

    // A FAIRE : Changer le type de retour en String pour avoir des messages d'erreur personnalisés
    // A FAIRE : Prendre en compte le cas où on pose des lettres adjacentes à des lettres déjà posées
    public boolean valider() { // Appelé par PlateauView
        for (Case c : this.pendingCases) {
            System.out.println("Case : " + c.getX() + " " + c.getY());
        }
        Collections.sort(this.pendingCases, new Comparator<Case>() {
            @Override
            public int compare(Case c1, Case c2) {
                if (c1.getX() != c2.getX()) {
                    return Integer.compare(c1.getX(), c2.getX());
                } else {
                    return Integer.compare(c1.getY(), c2.getY());
                }
            }
        });

        // Vérifier si le mot est valide avec Dictionnaire

        int count = 0;
        int motDouble = 1;
        int motTriple = 1;

        if (this.premierTour) {
            boolean centre = false;
            for (Case c : this.pendingCases) {
                if (!this.adjacence(c)) {
                    for (Case c2 : this.pendingCases) {
                        c2.retirerLettre();
                    }
                    return false;
                }
                if (c.getX() == 7 && c.getY() == 7) {
                    centre = true;
                }
                if (c.getBonus() == "LD") {
                    count += c.getLettre().getPoints() * 2;
                } else if (c.getBonus() == "LT") {
                    count += c.getLettre().getPoints() * 3;
                } else if (c.getBonus() == "MD") {
                    motDouble *= 2;
                    count += c.getLettre().getPoints();
                } else if (c.getBonus() == "MT") {
                    motTriple *= 3;
                    count += c.getLettre().getPoints();
                } else {
                    count += c.getLettre().getPoints();
                }
            }
            if (!centre) {
                return false;
            }
        }
        else {
            for (Case c : this.pendingCases) {
                if (!this.adjacence(c)) {
                    for (Case c2 : this.pendingCases) {
                        c2.retirerLettre();
                    }
                    return false;
                }
                if (c.getBonus() == "LD") {
                    count += c.getLettre().getPoints() * 2;
                } else if (c.getBonus() == "LT") {
                    count += c.getLettre().getPoints() * 3;
                } else if (c.getBonus() == "MD") {
                    motDouble *= 2;
                    count += c.getLettre().getPoints();
                } else if (c.getBonus() == "MT") {
                    motTriple *= 3;
                    count += c.getLettre().getPoints();
                } else {
                    count += c.getLettre().getPoints();
                }
            }
        }

        int score = count * motDouble * motTriple;
        System.out.println("Score : " + score);

        //this.joueurActuel.addScore(score);

        this.pendingCases.clear();

        return true;
    }

    public void annuler() {
        for (Case c : this.pendingCases) {
            //c.retirerLettre();
        }
        this.pendingCases.clear();
    }


    // Regarde s'il y a des lettres adjacentes à la case
    public boolean adjacence(Case c) {
        int x = c.getX();
        int y = c.getY();

        if (x > 0 && !this.plateau[x-1][y].isEmpty()) {
            return true;
        }
        if (x < 14 && !this.plateau[x+1][y].isEmpty()) {
            return true;
        }
        if (y > 0 && !this.plateau[x][y-1].isEmpty()) {
            return true;
        }
        if (y < 14 && !this.plateau[x][y+1].isEmpty()) {
            return true;
        }
        System.out.println("Aucune lettre adjacente");
        return false;
    }
}