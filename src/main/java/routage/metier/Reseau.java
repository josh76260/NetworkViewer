package routage.metier;

import java.util.ArrayList;

public class Reseau {
    private final String nom;
    private ArrayList<Commutateur> listComm;
    private ArrayList<Machine> listMachine;

    public Reseau(String nom) {
        this.nom = nom;
        listComm = new ArrayList<>();
    }

    public ArrayList<Commutateur> getCommutateurs() {
        return listComm;
    }

    public Commutateur getCommutateur(String id) {
        for (Commutateur c : listComm) {
            if (c.getNom().equals(id)) {
                return c;
            }
        }

        return null;
    }
}
