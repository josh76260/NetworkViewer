package routage.metier;

import java.util.*;

/**
 * Classe représentant un commutateur du réseau
 *
 * @author Joshua Galien
 */
public class Commutateur extends Liable {

    /**
     * Table de routage du commutateur
     */
    private ArrayList<Route> tabRoutage;

    /**
     * Constructeur du commutateur
     *
     * @param nom nom du commutateur
     */
    public Commutateur(String nom) {
        super(nom);
        tabRoutage = new ArrayList<>();
    }

    /**
     * Méthode permettant d'ajouter une route à la table de routage
     *
     * @param dest    destination de la route
     * @param routeur voisin par lquel on doit passer pour aller à la detsination
     * @param poids   le poids de cette route
     */
    public void addRoute(Commutateur dest, Commutateur routeur, int poids) {
        for (Route r : tabRoutage) {
            if (r.getRouteur() == routeur && r.getDest() == dest) return;
        }
        tabRoutage.add(new Route(dest, routeur, poids));
        Collections.sort(tabRoutage);
    }

    /**
     * Accesseur sur la table de routage du commutateur
     *
     * @return la table de routage
     */
    public ArrayList<Route> getRoutes() {
        return tabRoutage;
    }

    /**
     * Méthode permettant la suppression d'une route de la table de routage
     */
    public void delRoutes() {
        tabRoutage = new ArrayList<>();
    }
}
