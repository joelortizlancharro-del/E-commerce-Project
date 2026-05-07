public class Producte {
    protected int id;
    protected String nom;
    protected String familia;
    protected double preuBase;
    protected int IVA;
    protected int stock;

    public Producte(int id, String nom, String familia, double preuBase, int iVA, int stock) {
        this.id = id;
        this.nom = nom;
        this.familia = familia;
        this.preuBase = preuBase;
        this.IVA = iVA;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public double getPreuBase() {
        return preuBase;
    }

    public void setPreuBase(double preuBase) {
        this.preuBase = preuBase;
    }

    public int getIVA() {
        return IVA;
    }

    public void setIVA(int iVA) {
        IVA = iVA;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
@Override
public String toString() {
    return "ID: " + id + " | Nom: " + nom + " | Família: " + familia + 
           " | Preu base: " + preuBase + " | IVA: " + IVA + "% | Stock: " + stock;
}

   
    
}