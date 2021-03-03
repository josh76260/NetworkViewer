package routage;

public class Route implements Comparable {
    private Commutateur dest;
    private Commutateur routeur;
    private int poids;

    public Route(Commutateur dest, Commutateur routeur, int poids) {
        this.dest = dest;
        this.routeur = routeur;
        this.poids = poids;
    }

    public Commutateur getDest() {
        return dest;
    }

    public void setDest(Commutateur dest) {
        this.dest = dest;
    }

    public Commutateur getRouteur() {
        return routeur;
    }

    public void setRouteur(Commutateur routeur) {
        this.routeur = routeur;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        return dest.getNom() + "    " + routeur.getNom() + ":" + poids;
    }

    @Override
    public int compareTo(Object autre) {
        return this.dest.getNom().compareTo(((Route) autre).dest.getNom());
    }
}
