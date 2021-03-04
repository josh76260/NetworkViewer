package routage.metier;

import java.util.*;

public class Commutateur {
    private ArrayList<Liaison> listLiaison;
    private ArrayList<Interface> listInterface;
    private String nom;
    private ArrayList<Route> tabRoutage;

    public Commutateur(String nom) {
        this.nom = nom;
        listLiaison = new ArrayList<>();
        listInterface = new ArrayList<>();
        tabRoutage = new ArrayList<>();
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
        for (Route r:
             tabRoutage) {
            if(r.getRouteur() == routeur && r.getDest() == dest)return;
        }
        tabRoutage.add(new Route(dest, routeur, poids));
        Collections.sort(tabRoutage);
    }

    public ArrayList<Route> getRoutes() {
        return tabRoutage;
    }

    public String toString(){
        return nom;
    }

    public void delLiaison(Liaison l) {
        if (l != null)listLiaison.remove(l);
    }
}
