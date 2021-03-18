package routage.metier;

/**
 * Classe représentant une route du réseau
 *
 * @author Joshua Galien
 */
public class Route implements Comparable<Route> {

    /**
     * Destination de la route
     */
    private Commutateur dest;

    /**
     * Routeur pour utilisé pour aller à la destination
     */
    private Commutateur routeur;

    /**
     * Poids de la route
     */
    private int poids;

    /**
     * Construite une route
     *
     * @param dest    destinationde la route
     * @param routeur routeur de la route
     * @param poids   poids de la route
     */
    public Route(Commutateur dest, Commutateur routeur, int poids) {
        this.dest = dest;
        this.routeur = routeur;
        this.poids = poids;
    }

    /**
     * Renvoie la destination de la route
     *
     * @return la destination de la route
     */
    public Commutateur getDest() {
        return dest;
    }

    /**
     * Modifie la destination de la route
     *
     * @param dest la nouvelle destination de la route
     */
    public void setDest(Commutateur dest) {
        this.dest = dest;
    }

    /**
     * Retourne le routeur de la route
     *
     * @return le routeur de la route
     */
    public Commutateur getRouteur() {
        return routeur;
    }

    /**
     * Modifie le routeur de la route
     *
     * @param routeur le nouveau routeur
     */
    public void setRouteur(Commutateur routeur) {
        this.routeur = routeur;
    }

    /**
     * Retourne le poids de la route
     *
     * @return le poids de la route
     */
    public int getPoids() {
        return poids;
    }

    /**
     * Modifie le poids de la route
     *
     * @param poids le nouveau poids de la route
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        return dest.getNom() + "    " + routeur.getNom() + ":" + poids;
    }

    @Override
    public int compareTo(Route autre) {
        return this.dest.getNom().compareTo(autre.dest.getNom()) != 0 ?
                this.dest.getNom().compareTo(autre.dest.getNom()) : this.poids - autre.poids;
    }
}

