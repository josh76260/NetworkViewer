package routage.metier;

import java.util.ArrayList;

public class Reseau {
    private final String nom;
    private ArrayList<Commutateur> listComm;
    private ArrayList<Machine> listMachine;

    public Reseau(String nom) {
        this.nom = nom;
        listComm = new ArrayList<>();
        Liaison.resetLiaison();
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
            for (int i = 0; i < Liaison.getLiaisons().size(); i++) {
                Liaison l = Liaison.getLiaisons().get(i);

                if (l.getCommutateurB() == commutateur || l.getCommutateurA() == commutateur) {
                    Liaison.supprLiaison(c, commutateur);
                }
            }
        }
        listComm.remove(commutateur);
    }
}


