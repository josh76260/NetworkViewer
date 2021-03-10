package routage.metier;

public class Liable {
    protected String nom;

    public Liable(String nom) {
        this.nom = nom;
    }

    public String getNom(){
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
