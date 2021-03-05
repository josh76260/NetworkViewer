package routage.metier;

import java.util.ArrayList;

public class Liaison {
    private static ArrayList<Liaison> liaisonsDejaExistantes = new ArrayList<>();

    private int poids;
    private Commutateur a;
    private Commutateur b;

    private Liaison(int poids, Commutateur a, Commutateur b) {
        this.poids = poids;
        this.a = a;
        this.b = b;
    }

    public static Liaison creerLiaison(int poids, Commutateur c1, Commutateur c2) {
        for (Liaison l : liaisonsDejaExistantes) {
            if (l.poids == poids && l.a.getNom().equals(c1.getNom()) && l.b.getNom().equals(c2.getNom())) return l;
        }

        Liaison l = new Liaison(poids, c1, c2);
        liaisonsDejaExistantes.add(l);

        return l;
    }

    public static void supprLiaison(Commutateur c1, Commutateur c2){
        liaisonsDejaExistantes.removeIf(l -> (l.getCommutateurA().equals(c1) && l.getCommutateurB().equals(c2)) ||
                (l.getCommutateurA().equals(c2) && l.getCommutateurB().equals(c1)));

    }

    public static ArrayList<Commutateur> getVoisins(Commutateur commutateur) {
        ArrayList<Commutateur> lReturn = new ArrayList<>();
        for (Liaison l : liaisonsDejaExistantes) {
            if (l.getCommutateurA() == commutateur) lReturn.add(l.getCommutateurB());
            if (l.getCommutateurB() == commutateur) lReturn.add(l.getCommutateurA());
        }

        return lReturn;
    }

    public static ArrayList<Liaison> getLiaisons() {
        return liaisonsDejaExistantes;
    }

    public static void resetLiaison() {
        liaisonsDejaExistantes = new ArrayList<>();
    }

    public Commutateur getCommutateurA() {
        return a;
    }

    public Commutateur getCommutateurB() {
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
