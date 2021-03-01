package routage;

public class Route {
    private Commutateur routeur;
    private int poids;

    public Route(Commutateur routeur, int poids) {
        this.routeur = routeur;
        this.poids = poids;
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
        return "Route{" +
                "routeur=" + routeur +
                ", poids=" + poids +
                '}';
    }
}
