package model;
import java.io.Serializable;
import java.util.*;

public class Sac implements Serializable {
    //Attributs
    private List<Lettre> listLettres = new ArrayList<Lettre>();

    //Constructeur
    public Sac() {
        this.addLettre('*', 2);
        this.addLettre('A', 9);
        this.addLettre('B', 2);
        this.addLettre('C', 2);
        this.addLettre('D', 3);
        this.addLettre('E', 15);
        this.addLettre('F', 2);
        this.addLettre('G', 2);
        this.addLettre('H', 2);
        this.addLettre('I', 8);
        this.addLettre('J', 1);
        this.addLettre('K', 1);
        this.addLettre('L', 5);
        this.addLettre('M', 3);
        this.addLettre('N', 6);
        this.addLettre('O', 6);
        this.addLettre('P', 2);
        this.addLettre('Q', 1);
        this.addLettre('R', 6);
        this.addLettre('S', 6);
        this.addLettre('T', 6);
        this.addLettre('U', 6);
        this.addLettre('V', 2);
        this.addLettre('W', 1);
        this.addLettre('X', 1);
        this.addLettre('Y', 1);
        this.addLettre('Z', 1);
        this.melanger();
    }

    //Getters / Setters
    public int getNbrLettres() {
        return this.listLettres.size();
    }
    public List<Lettre> getListLettres() {
        return this.listLettres;
    }

    //Méthodes
    private void addLettre(char l, int n) {
        for (int i=0; i<n; i++) {
            this.listLettres.add(new Lettre(l));
        }
    }
    public Lettre piocher() {
        if (this.listLettres.size() > 0) {
            return this.listLettres.remove(0);            
        }
        return null;
    }
    public void melanger() {
        Collections.shuffle(this.listLettres);
    }
    public void addAll(List<Lettre> list) {
        this.listLettres.addAll(list);
    }

}