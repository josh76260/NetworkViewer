package routage;

import java.util.ArrayList;

public class Reseau {
    private final String nom;
    private ArrayList<Commutateur> listComm;
    private ArrayList<Machine> listMachine;

    public Reseau(String nom){
        this.nom = nom;
        listComm = new ArrayList<>();
    }

    public void initReseau(){
        for (int i = 0; i < 4; i++) {
            listComm.add(new Commutateur("c" + i));
        }

        listComm.get(0).addLiaison(new Liaison(2, listComm.get(1)));
        listComm.get(1).addLiaison(new Liaison(10, listComm.get(3)));
    }

    public ArrayList<Commutateur> getCommutateurs() {
        return listComm;
    }
}
