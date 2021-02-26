package routage;

import java.util.ArrayList;

public class Commutateur {
    private ArrayList<Liaison> listLiaison;
    private ArrayList<Interface> listInterface;
    private String nom;

    public Commutateur(String nom) {
        this.nom = nom;
        listLiaison = new ArrayList<>();
        listInterface = new ArrayList<>();
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
}
