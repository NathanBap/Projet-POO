package model;

import java.io.*;
import java.util.*;

import view.LettreView;

public class Plateau implements Serializable {
    //Attributs
    private Case[][] plateau;
    private List<Case> pendingCases = new ArrayList<Case>();
    private Joueur joueurActuel;
    private boolean premierTour = true;
    private Dico dico = new Dico();

    private List<Joueur> joueurs = new ArrayList<Joueur>();
    private int joueurActuelIndex;
    private Sac sac;

    //Constructeur
    public Plateau() {
        this.plateau = new Case[15][15];
        this.sac = new Sac();
    }

    // Getters / Setters
    public Case[][] getPlateau() {
        return this.plateau;
    }

    public Case getCase(int x, int y) {
        return this.plateau[x][y];
    }

    public boolean getPremierTour() {
        return this.premierTour;
    }
    public Joueur getJoueur() {
        return this.joueurActuel;
    }
    public Sac getSac() {
        return this.sac;
    }
    public List<Joueur> getJoueurs() {
        return this.joueurs;
    }

    // Méthodes
    public void addPendingCase(Case c) {
        pendingCases.add(c);
    }

    public boolean arePendingCases() {
        return !this.pendingCases.isEmpty();
    }

    public List<Case> getPendingCases() {
        return this.pendingCases;
    }


    public void initPlateau() {
        List<Integer> LD = new ArrayList<Integer>(Arrays.asList(3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221));
        List<Integer> LT = new ArrayList<Integer>(Arrays.asList(20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204));
        List<Integer> MD = new ArrayList<Integer>(
                Arrays.asList(16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208));
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

    public void debutDuJeu() {
        // Initialiser les mains des joueurs
        for (Joueur joueur : joueurs) {
            joueur.initMain();
        }
        // Déterminer quel joueur commence
        joueurActuelIndex = tirageAuSort(joueurs);
        joueurActuel = joueurs.get(joueurActuelIndex);
    }

    // 2 joueurs minimum 4 maximum
    public void initJoueursTest() {
        // TMP : Création de 2 joueurs
        Joueur joueur1 = new Joueur("Joueur 1", this);
        Joueur joueur2 = new Joueur("Joueur 2", this);

        joueurs.add(joueur1);
        joueurs.add(joueur2);
    }
    public void initJoueurs(List<String> noms) {
        for (String nom : noms) {
            joueurs.add(new Joueur(nom, this));
        }
    }

    public Joueur getJoueurActuel() {
        return joueurs.get(joueurActuelIndex);
    }

    public void passerAuJoueurSuivant() {
        joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.size();
        joueurActuel = joueurs.get(joueurActuelIndex);
    }
    
    public int tirageAuSort(List<Joueur> joueurs) {
        Random rand = new Random();
        return rand.nextInt(joueurs.size());
    }

    // FAIT : Changer le type de retour en String pour avoir des messages d'erreur personnalisés
    // FAIT : Changement du système du calcul du score
    // FAIT : Quand on pose un mot validé, supprimer le bonus de la case
    // FAIT : Retourner un map avec mot : score pour afficher les points gagnés pour chaque mot

    public String valider() { // Appelé par PlateauView
        boolean sameRow = true;
        boolean sameColumn = true;
        int firstX = pendingCases.get(0).getX();
        int firstY = pendingCases.get(0).getY();
        int lastX = pendingCases.get(pendingCases.size() - 1).getX();
        int lastY = pendingCases.get(pendingCases.size() - 1).getY();

        for (Case c : pendingCases) {
            if (c.getX() != firstX) {
                sameRow = false;
            }
            if (c.getY() != firstY) {
                sameColumn = false;
            }
        }

        if (!sameRow && !sameColumn) {
            return "Pas aligne";
        }

        if (sameRow) {
            for (int y = pendingCases.get(0).getY(); y < lastY; y++) {
                if (getCase(firstX, y).isEmpty()) {
                    return "Pas connecte";
                }
            }
        } else {
            for (int x = pendingCases.get(0).getX(); x < lastX; x++) {
                if (getCase(x, firstY).isEmpty()) {
                    return "Pas connecte";
                }
            }
        }

        Comparator<Case> comparator = new Comparator<Case>() {
            @Override
            public int compare(Case c1, Case c2) {
                if (c1.getX() != c2.getX()) {
                    return Integer.compare(c1.getX(), c2.getX());
                } else {
                    return Integer.compare(c1.getY(), c2.getY());
                }
            }
        };
        Collections.sort(this.pendingCases, comparator);

        int count = 0;
        int motDouble = 1;
        int motTriple = 1;
        int score = 0;
        Map<String, Integer> motsPoint = new HashMap<String, Integer>();

        if (this.premierTour) {
            boolean centre = false;
            if (this.pendingCases.size() < 2) {
                return "Pas assez de lettres";
            }
            String mot = "";
            for (Case c : this.pendingCases) {
                if (c.getX() == 7 && c.getY() == 7) {
                    centre = true;
                }
                int[] newScore = updateScore(c, count, motDouble, motTriple);
                count = newScore[0];
                motDouble = newScore[1];
                motTriple = newScore[2];

                mot += c.getLettre().getLettre();
            }
            if (!centre) {
                return "Non centre";
            }
            if (!dico.estValide(mot)) {
                return "Mot invalide";
            }
            this.premierTour = false;
            score = count * motDouble * motTriple;
            motsPoint.put(mot, score);
        } else {
            if (!this.motAdjacent()) {
                return "Mot non adjacent";
            }
            Set<Case> casesVues = new HashSet<Case>();
            for (Case c : pendingCases) {
                System.out.println("Cases adjacentes : " + casesAdjacentes(c, casesVues));
                for (Case cBis : casesAdjacentes(c, casesVues)) {
                    int scoreMot = calcMot(c, cBis, casesVues, motsPoint);
                    if (scoreMot == -1) {
                        return "Mot invalide";
                    }
                    score += scoreMot;
                }
            }
            if (!casesVues.containsAll(pendingCases)) {
                int scoreMot = calcMot(pendingCases.get(0), pendingCases.get(pendingCases.size() - 1), casesVues,
                        motsPoint);
                if (scoreMot == -1) {
                    return "Mot invalide";
                }
                score += scoreMot;
            }
        }
        for (Case c : pendingCases) {
            joueurActuel.deposerLettre(c.getLettre());
            c.removeBonus();
        }
        pendingCases.clear();
        joueurActuel.addScore(score);
        joueurActuel.remplirMain(sac);
        return motsPoint.toString();
    }

    public List<Character> getAvailableLetters(List<LettreView> footerLettres) {
        List<Character> availableLetters = new ArrayList<>();
        for (LettreView lettreView : footerLettres) {

            availableLetters.add(lettreView.getLettre());

        }
        return availableLetters;
    }

    private boolean canBeFormed(String word, List<Character> availableLetters) {
        // Créer une copie de la liste
        List<Character> remainingLetters = new ArrayList<>(availableLetters);
        for (char letter : word.toCharArray()) {
            // Vérifier si la lettre est disponible dans la liste des lettres restantes
            if (remainingLetters.contains(letter)) {

                remainingLetters.remove(Character.valueOf(letter));
            } else {

                return false;
            }
        }
        return true;
    }

    public List<String> suggestWord(List<Character> availableLetters) {
        List<String> suggestedWords = new ArrayList<>();

        Dico dico = new Dico();

        for (String word : dico.getMots()) {
            if (canBeFormed(word, availableLetters)) {
                suggestedWords.add(word);

            }
        }
        return suggestedWords;

    }

    // c correspond à la case posée, cBis à une case adjacente
    public int calcMot(Case c, Case cBis, Set<Case> casesVues, Map<String, Integer> motsPoints) {
        int x = c.getX();
        int y = c.getY();
        int xBis = cBis.getX();
        int count = 0;
        int motDouble = 1;
        int motTriple = 1;
        String mot = "";

        // Soit horizontal soit vertical
        if (x == xBis) {
            // Pour aller au début du mot dans tous les cas
            while (!getCase(x, y - 1).isEmpty())
                y--;

            Case currentCase = getCase(x, y);
            while (!currentCase.isEmpty()) {
                int[] newScore = updateScore(currentCase, count, motDouble, motTriple);
                count = newScore[0];
                motDouble = newScore[1];
                motTriple = newScore[2];
                casesVues.add(currentCase);
                mot += currentCase.getLettre().getLettre();
                currentCase = getCase(x, ++y);
            }
        } else {
            while (!getCase(x - 1, y).isEmpty())
                x--;

            Case currentCase = getCase(x, y);
            while (!currentCase.isEmpty()) {
                int[] newScore = updateScore(currentCase, count, motDouble, motTriple);
                count = newScore[0];
                motDouble = newScore[1];
                motTriple = newScore[2];
                casesVues.add(currentCase);
                mot += currentCase.getLettre().getLettre();
                currentCase = getCase(++x, y);
            }
        }
        if (!this.dico.estValide(mot)) {
            System.out.println("Mot invalide : " + mot);
            return -1;
        }
        int score = count * motDouble * motTriple;
        System.out.println("Score du mot : " + mot + " -> " + score);
        while (motsPoints.containsKey(mot)) {
            mot += '\u200B';  // Caractère invisible pour éviter les doublons
        }
        motsPoints.put(mot, score);
        return score;
    }

    public int[] updateScore(Case c, int count, int motDouble, int motTriple) {
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
        return new int[] { count, motDouble, motTriple };
    }

    public void annuler() {
        for (Case c : this.pendingCases) {
            c.retirerLettre();
        }
        this.pendingCases.clear();
    }

    public List<Case> casesAdjacentes(Case c, Set<Case> casesVues) {
        int x = c.getX();
        int y = c.getY();
        List<Case> casesAdj = new ArrayList<Case>();

        if (x > 0 && !getCase(x - 1, y).isEmpty() && !this.pendingCases.contains(getCase(x - 1, y))
                && !casesVues.contains(getCase(x - 1, y))) {
            casesAdj.add(getCase(x - 1, y));
        }
        if (x < 14 && !getCase(x + 1, y).isEmpty() && !this.pendingCases.contains(getCase(x + 1, y))
                && !casesVues.contains(getCase(x + 1, y))) {
            casesAdj.add(getCase(x + 1, y));
        }
        if (y > 0 && !getCase(x, y - 1).isEmpty() && !this.pendingCases.contains(getCase(x, y - 1))
                && !casesVues.contains(getCase(x, y - 1))) {
            casesAdj.add(getCase(x, y - 1));
        }
        if (y < 14 && !getCase(x, y + 1).isEmpty() && !this.pendingCases.contains(getCase(x, y + 1))
                && !casesVues.contains(getCase(x, y + 1))) {
            casesAdj.add(getCase(x, y + 1));
        }
        System.out.println("Aucune lettre adjacente");
        return casesAdj;
    }

    // Pour savoir si le mot placé est adjacent à un ancien mot
    public boolean motAdjacent() {
        for (Case c : this.pendingCases) {
            int x = c.getX();
            int y = c.getY();

            if (x > 0 && !this.plateau[x - 1][y].isEmpty() && !this.pendingCases.contains(this.plateau[x - 1][y])) {
                return true;
            }
            if (x < 14 && !this.plateau[x + 1][y].isEmpty() && !this.pendingCases.contains(this.plateau[x + 1][y])) {
                return true;
            }
            if (y > 0 && !this.plateau[x][y - 1].isEmpty() && !this.pendingCases.contains(this.plateau[x][y - 1])) {
                return true;
            }
            if (y < 14 && !this.plateau[x][y + 1].isEmpty() && !this.pendingCases.contains(this.plateau[x][y + 1])) {
                return true;
            }
        }
        return false;
    }

    public List<Lettre> echangerLettres(List<Lettre> lettres) {
        sac.addAll(lettres);
        joueurActuel.getListeLettre().removeAll(lettres);
        List<Lettre> lettresPiochees = joueurActuel.remplirMain(sac);
        return lettresPiochees;
    }

    public void finPartie() {
        Map<String, Integer> joueurScores = new LinkedHashMap<String, Integer>();

        for (Joueur joueur : joueurs) {
            int pointsRestants = 0;
            for (Lettre l : joueur.getListeLettre()) {
                pointsRestants += l.getPoints();
            }
            joueur.addScore(-pointsRestants);
            if (joueurActuel.getListeLettre().size() == 0) {
                joueurActuel.addScore(pointsRestants);
            }
        }
        List<Joueur> joueursCopy = new ArrayList<Joueur>(joueurs);

        try {
            File leaderBoardFile = new File("leaderboard.ser");
            if (leaderBoardFile.exists()) {
                FileInputStream fis = new FileInputStream("leaderboard.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Map<String, Integer> leaderBoardMap = (Map<String,Integer>)ois.readObject(); 
                for (String s : leaderBoardMap.keySet()) {
                    Joueur tmp = new Joueur(s, this);
                    tmp.addScore(leaderBoardMap.get(s));
                    joueursCopy.add(tmp);
                }
                Collections.sort(joueursCopy);
                for (Joueur j : joueursCopy) {
                    String nom = j.getNom();
                    while (joueurScores.containsKey(nom)) {
                        nom += '\u200B';
                    }
                    joueurScores.put(nom, j.getScore());
                }
                ois.close();
                fis.close();
                
            } else {
                Collections.sort(joueurs);
                for (Joueur j : joueurs) {
                    joueurScores.put(j.getNom(), j.getScore());
                }
            }
            FileOutputStream fos = new FileOutputStream("leaderboard.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(joueurScores);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}