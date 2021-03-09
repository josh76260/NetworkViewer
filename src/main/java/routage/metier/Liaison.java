package routage.metier;

import java.util.ArrayList;

public class Liaison {
    private static ArrayList<Liaison> liaisonsDejaExistantes = new ArrayList<>();

    private int poids;
    private Liable a;
    private Liable b;

    private Liaison(int poids, Liable a, Liable b) {
        this.poids = poids;
        this.a = a;
        this.b = b;
    }

    public static Liaison creerLiaison(int poids, Liable c1, Liable c2) {
        for (Liaison l : liaisonsDejaExistantes) {
            if (l.poids == poids && l.a.getNom().equals(c1.getNom()) && l.b.getNom().equals(c2.getNom())) return l;
        }

        Liaison l = new Liaison(poids, c1, c2);
        liaisonsDejaExistantes.add(l);

        return l;
    }

    public static void supprLiaison(Liable c1, Liable c2){
        liaisonsDejaExistantes.removeIf(l -> (l.getLiableA().equals(c1) && l.getLiableB().equals(c2)) ||
                (l.getLiableA().equals(c2) && l.getLiableB().equals(c1)));

    }

    public static ArrayList<Commutateur> getVoisins(Commutateur commutateur) {
        ArrayList<Commutateur> lReturn = new ArrayList<>();
        for (Liaison l : liaisonsDejaExistantes) {
            if (l.getLiableA() == commutateur) lReturn.add((Commutateur) l.getLiableB());
            if (l.getLiableB() == commutateur) lReturn.add((Commutateur) l.getLiableA());
        }

        return lReturn;
    }

    public static ArrayList<Liaison> getLiaisons() {
        return liaisonsDejaExistantes;
    }

    public static void resetLiaison() {
        liaisonsDejaExistantes = new ArrayList<>();
    }

    public Liable getLiableA() {
        return a;
    }

    public Liable getLiableB() {
        return b;
    }

    public int getPoids() {
        return poids;
    }

    @Override
    public String toString() {
        return "Liaison{" +
                "poids=" + poids +
                ", dep=" + a +
                ", arr=" + b +
                '}';
    }
}
