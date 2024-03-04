package model;
import java.util.*;


public class Case {
    //attributs 
    private String bonus = "  ";
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
    public boolean placerLettre(Lettre lettre) {
        if (this.lettre == null) {
            lettre.setCase(this);
            this.lettre = lettre;
            return true;
        }
        return false;
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

}