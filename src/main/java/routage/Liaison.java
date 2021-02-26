package routage;

public class Liaison {
    private int poids;
    private Commutateur destination;

    public Liaison(int poids, Commutateur destination) {
        this.poids = poids;
        this.destination = destination;
    }

    public Commutateur getDestination() {
        return destination;
    }
}
