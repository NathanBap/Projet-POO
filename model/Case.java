package model;
import java.util.*;


 
public class Case {
    //attributs 
    private String bonus;
    private int x;
    private int y;


    //constructeurs
    public Case() {
    }

    public Case(String bonus){
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

    //setter getter BONUS
     public String getBonus () {
    	return bonus;
    }
    public String setBonus(String bonus){
        this.bonus=bonus;
    }


    //setter getter 
     public String getX () {
    	return x;
    }
    public String setX(int x){
        this.x=x;
    }


    //setter getter 
     public String getY () {
    	return y;
    }
    public String setY(int y){
        this.y=y;
    }


}