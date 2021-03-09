package routage.metier;

public class Machine implements Liable{
    private String nom;

    public Machine(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
