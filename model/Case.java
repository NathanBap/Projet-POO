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
    public void verifierEmplacement() {
        // TODO implement here
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
    // public void setX(int x){
    //     this.x=x;
    // }


    //setter getter 

    // public void setY(int y){
    //     this.y=y;
    // }


}