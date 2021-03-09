package routage.metier;

import java.util.*;

public class Commutateur implements Liable {
    private ArrayList<Interface> listInterface;
    private String nom;
    private ArrayList<Route> tabRoutage;

    public Commutateur(String nom) {
        this.nom = nom;
        listInterface = new ArrayList<>();
        tabRoutage = new ArrayList<>();
    }

    @Override
    public String getNom() {
        return nom;
    }

    public void addRoute(Commutateur dest, Commutateur routeur, int poids) {
        for (Route r : tabRoutage) {
            if (r.getRouteur() == routeur && r.getDest() == dest) return;
        }
        tabRoutage.add(new Route(dest, routeur, poids));
        Collections.sort(tabRoutage);
    }

    public ArrayList<Route> getRoutes() {
        return tabRoutage;
    }

    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return nom.equals(((Commutateur) obj).getNom());
        }
        return false;
    }

    public void delRoutes() {
        tabRoutage = new ArrayList<>();
    }
}
