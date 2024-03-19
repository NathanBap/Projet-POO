package model;
import java.util.*;
import view.PlateauView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;  


public class Case extends MouseAdapter {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // Action à effectuer lorsque la case est cliquée
        System.out.println("Case cliquée : [" + x + ", " + y + "]");
    }
    public void placerLettre(Lettre lettre) {
        lettre.setCase(this);
        this.lettre = lettre;
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

} 