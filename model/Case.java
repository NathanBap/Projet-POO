package model;

import java.io.Serializable;  


public class Case implements Serializable {
    //attributs 
    private String bonus = "";
    private int x;
    private int y;
    private Lettre lettre;

    //constructeurs
    public Case() {
    }

    public Case(int x, int y, String bonus){
        this.x=x;
        this.y=y;
        this.bonus=bonus;
    }

    public Case(int x,int y){
        this.x=x;
        this.y=y;
    }

    //méthodes 
    public void placerLettre(Lettre lettre) {
        lettre.setCase(this);
        this.lettre = lettre;
    }
    public void retirerLettre() {
        this.lettre.removeCase();
        this.lettre = null;
    }
    public boolean isEmpty() {
        return this.lettre == null;
    }

    //setter getter
     public String getBonus () {
    	return this.bonus;
    }
     public int getX () {
    	return this.x;
    }
    public int getY () {
    	return this.y;
    }
    public Lettre getLettre() {
        return this.lettre;
    }
    public void removeBonus() {
        this.bonus = "";
    }

} 