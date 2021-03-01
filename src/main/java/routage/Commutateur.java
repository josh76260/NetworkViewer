package routage;

import java.util.ArrayList;
import java.util.HashMap;

public class Commutateur {
    private ArrayList<Liaison> listLiaison;
    private ArrayList<Interface> listInterface;
    private String nom;
    private HashMap<Commutateur, Route> tabRoutage;

    public Commutateur(String nom) {
        this.nom = nom;
        listLiaison = new ArrayList<>();
        listInterface = new ArrayList<>();
        tabRoutage = new HashMap<>();
    }

    public void addLiaison(Liaison liaison) {
        listLiaison.add(liaison);
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Liaison> getLiaisons() {
        return listLiaison;
    }

    public void addRoute(Commutateur dest, Commutateur routeur, int poids) {
        tabRoutage.putIfAbsent(dest, new Route(routeur, poids));
    }

    public HashMap<Commutateur, Route> getRoutes() {
        return tabRoutage;
    }

    public String toString(){
        return nom;
    }
}
