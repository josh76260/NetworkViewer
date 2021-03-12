package routage.metier;

/**
 * Classe regroupant des méthodes utils dans Commutateur et Machine
 */
public class Liable {

    /**
     * Nom de l'élément
     */
    protected String nom;

    /**
     * Contructeur
     *
     * @param nom le nom de l'élément
     */
    public Liable(String nom) {
        this.nom = nom;
    }

    /**
     * Accesseur sur l'attribut nom
     *
     * @return le nom de l'élément
     */
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    /**
     * Modificateur sur l'attribut nom
     *
     * @param nom le nouveau nom de l'élément
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return nom.equals(((Liable) obj).getNom());
        }
        return false;
    }
}
