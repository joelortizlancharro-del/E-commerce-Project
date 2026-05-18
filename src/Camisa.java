public class Camisa extends Producte {
    private int tallaColl;
    private int ampladaPit;

    public Camisa(int id, String nom, int familia, double preuBase, int iVA, int stock, int tallaColl,
            int ampladaPit) {
        super(id, nom, familia, preuBase, iVA, stock);
        this.tallaColl = tallaColl;
        this.ampladaPit = ampladaPit;
    }

    public int getTallaColl() {
        return tallaColl;
    }

    public void setTallaColl(int tallaColl) {
        this.tallaColl = tallaColl;
    }

    public int getAmpladaPit() {
        return ampladaPit;
    }

    public void setAmpladaPit(int ampladaPit) {
        this.ampladaPit = ampladaPit;
    }
@Override
public String toString() {
    return super.toString() + " | Talla coll: " + tallaColl + " | Amplada pit: " + ampladaPit;
}

}