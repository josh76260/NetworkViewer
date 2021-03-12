package routage.metier;

import java.util.ArrayList;

/**
 * Classe représentant une liaison entre deux Liable
 */
public class Liaison {

    /**
     * Liste des liaisons déjà existantes
     */
    private static ArrayList<Liaison> liaisonsDejaExistantes = new ArrayList<>();

    /**
     * Poids de la liaison
     */
    private int poids;

    /**
     * Élément a de la liaison
     */
    private final Liable a;

    /**
     * Élément b de la liaison
     */
    private final Liable b;

    /**
     * Constructeur
     *
     * @param poids poids de la liaison
     * @param a     élément a de la liaison
     * @param b     élément b de la liaison
     */
    private Liaison(int poids, Liable a, Liable b) {
        this.poids = poids;
        this.a = a;
        this.b = b;
    }

    /**
     * Méthode de création d'une liaison
     * Vérifie si la liaison existe déjà
     *
     * @param poids poids de la liaison
     * @param l1    premier élément de la liaison
     * @param l2    second élément de la liaison
     */
    public static void creerLiaison(int poids, Liable l1, Liable l2) {
        if (getLiaisonEntre(l1, l2) == null) {
            Liaison l = new Liaison(poids, l1, l2);
            liaisonsDejaExistantes.add(l);
        }
    }

    /**
     * Méthode permettant de supprimer une liaison
     *
     * @param c1 premier élément
     * @param c2 second élément
     */
    public static void supprLiaison(Liable c1, Liable c2) {
        liaisonsDejaExistantes.removeIf(l -> (l.getLiableA().equals(c1) && l.getLiableB().equals(c2)) ||
                (l.getLiableA().equals(c2) && l.getLiableB().equals(c1)));

    }

    /**
     * Renvoie les voisins d'un élément
     *
     * @param liable l'élément courant
     * @return les voisins de l'élément courant
     */
    public static ArrayList<Liable> getVoisins(Liable liable) {
        ArrayList<Liable> lReturn = new ArrayList<>();
        for (Liaison l : liaisonsDejaExistantes) {
            if (l.getLiableA() == liable) lReturn.add(l.getLiableB());
            if (l.getLiableB() == liable) lReturn.add(l.getLiableA());
        }

        return lReturn;
    }

    /**
     * Renvoie toutes les liaisons existantes
     *
     * @return la liste des liaisons
     */
    public static ArrayList<Liaison> getLiaisons() {
        return liaisonsDejaExistantes;
    }

    /**
     * Réinitialise la liste des liaisons
     */
    public static void resetLiaison() {
        liaisonsDejaExistantes = new ArrayList<>();
    }

    /**
     * Renvoie la liaison entre deux élément
     *
     * @param l1 premier élément
     * @param l2 second élément
     * @return la liaison entre les deux éléments
     */
    public static Liaison getLiaisonEntre(Liable l1, Liable l2) {
        for (Liaison l : liaisonsDejaExistantes) {
            if ((l.a.equals(l1) && l.b.equals(l2)) || (l.a.equals(l2) && l.b.equals(l1))) return l;
        }

        return null;
    }

    /**
     * Renvoie le liable a
     *
     * @return le liable a
     */
    public Liable getLiableA() {
        return a;
    }

    /**
     * Renvoie le liable b
     *
     * @return le liable b
     */
    public Liable getLiableB() {
        return b;
    }

    /**
     * renvoie le poids d'une liaison
     *
     * @return le poids de la liaison
     */
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

    /**
     * Modificateurs sur le poids d'une liaison
     *
     * @param poids le nouveau poids
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }
}
