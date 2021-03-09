package routage.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reseau {
    private final String nom;
    private ArrayList<Commutateur> listComm;
    private ArrayList<Machine> listMachine;

    public Reseau(String nom) {
        this.nom = nom;
        listComm = new ArrayList<>();
        listMachine = new ArrayList<>();
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

    public boolean ajouterMachine(Machine machine) {
        for (Machine m : listMachine) {
            if (m.getNom().equals(machine.getNom())) return false;
        }
        listMachine.add(machine);
        return true;
    }

    public void supprimerCommutateur(Commutateur commutateur) {
        assert commutateur != null;
        for (Liable liable : getLiables()) {
            for (int i = 0; i < Liaison.getLiaisons().size(); i++) {
                Liaison l = Liaison.getLiaisons().get(i);

                if (l.getLiableB() == commutateur || l.getLiableA() == commutateur) {
                    Liaison.supprLiaison(liable, commutateur);
                }
            }
        }
        listComm.remove(commutateur);
    }

    public List<Liable> getLiables() {
        return Stream.concat(listComm.stream(), listMachine.stream())
                .collect(Collectors.toList());
    }

    public ArrayList<Machine> getMachines() {
        return listMachine;
    }
}


