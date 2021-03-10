package routage.metier;

import java.util.*;

public class Commutateur extends Liable {
    private ArrayList<Route> tabRoutage;

    public Commutateur(String nom) {
        super(nom);
        tabRoutage = new ArrayList<>();
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

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() == Commutateur.class) {
                return nom.equals(((Commutateur) obj).getNom());
            }
        }
        return false;
    }

    public void delRoutes() {
        tabRoutage = new ArrayList<>();
    }
}
