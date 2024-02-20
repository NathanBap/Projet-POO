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
   


    //setter getter 
     public int getX () {
    	return x;
    }
    // public void setX(int x){
    //     this.x=x;
    // }


    //setter getter 
     public int getY () {
    	return y;
    }
    // public void setY(int y){
    //     this.y=y;
    // }


}