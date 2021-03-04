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

    public boolean ajouterCommutateur(Commutateur commutateur) {
        for (Commutateur c : listComm) {
            if (c.getNom().equals(commutateur.getNom())) return false;
        }
        listComm.add(commutateur);
        return true;
    }

    public void supprimerCommutateur(Commutateur commutateur) {
        assert commutateur != null;
        for (Commutateur c : listComm) {
            assert c != commutateur;
            for (int i = 0; i < c.getLiaisons().size(); i++) {
                Liaison l = c.getLiaisons().get(i);

                if (l.getDestination() == commutateur) {
                    c.delLiaison(l);
                }
            }
        }
        listComm.remove(commutateur);
    }
}


