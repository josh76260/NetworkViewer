package routage.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe représentant le réseau
 *
 * @author Joshua Galien
 */
public class Reseau {

    /**
     * Liste des commutateurs du réseau
     */
    private final ArrayList<Commutateur> listComm;

    /**
     * Liste des machines du réseau
     */
    private final ArrayList<Machine> listMachine;

    /**
     * Constructeur du réseau
     */
    public Reseau() {
        listComm = new ArrayList<>();
        listMachine = new ArrayList<>();
        Liaison.resetLiaison();
    }

    /**
     * Renvoie la liste des commutateurs du réseau
     *
     * @return la liste des commutateurs du réseau
     */
    public ArrayList<Commutateur> getCommutateurs() {
        return listComm;
    }

    /**
     * Renvoie un commutateur identifié par son nom
     *
     * @param id le nom du commutateur
     * @return le commutateur qui a le nom passée en paramètre, nuul si il n'existe pas
     */
    public Commutateur getCommutateur(String id) {
        for (Commutateur c : listComm) {
            if (c.getNom().equals(id)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Ajoute un commutateur au réseau
     *
     * @param commutateur le commutateur à ajouter
     * @return vrai si le commutateur a bien été ajouté, faux sinon
     */
    public boolean ajouterCommutateur(Commutateur commutateur) {
        for (Commutateur c : listComm) {
            if (c.getNom().equals(commutateur.getNom())) return false;
        }
        listComm.add(commutateur);
        return true;
    }

    /**
     * Ajoute une machine au réseau
     *
     * @param machine la machine à ajouter
     * @return vrai si la machine a été ajouté, faux sinon
     */
    public boolean ajouterMachine(Machine machine) {
        for (Machine m : listMachine) {
            if (m.getNom().equals(machine.getNom())) return false;
        }
        listMachine.add(machine);
        return true;
    }

    /**
     * Renvoie une liste avec tout les élément liables du réseau
     *
     * @return la liste des liables
     */
    public List<Liable> getLiables() {
        return Stream.concat(listComm.stream(), listMachine.stream())
                .collect(Collectors.toList());
    }

    /**
     * Renvoie la liste des machines du réseau
     *
     * @return la liste des machines du réseau
     */
    public ArrayList<Machine> getMachines() {
        return listMachine;
    }

    /**
     * Supprime un élément du réseau ainsi que toutes ses liaisons
     *
     * @param liable l'élément à supprimer
     */
    public void supprimerLiable(Liable liable) {
        for (Liable liable1 : getLiables()) {
            for (int i = 0; i < Liaison.getLiaisons().size(); i++) {
                Liaison l = Liaison.getLiaisons().get(i);

                if (l.getLiableB() == liable || l.getLiableA() == liable) {
                    Liaison.supprLiaison(liable1, liable);
                }
            }
        }

        if (listMachine.contains(liable)) listMachine.remove(liable);
        else listComm.remove(liable);
    }

    /**
     * Retourne l'élément qui correspond à ce nom
     *
     * @param id le nom de l'élément à chercher
     * @return l'élément qui possède ce nom
     */
    public Liable getLiable(String id) {
        for (Liable l : getLiables()) {
            if (l.getNom().equals(id)) return l;
        }

        return null;
    }
}


