public class Pantalo extends Producte {
    private int tallaCintura;
    private int llargadaCamal;

    public Pantalo(int id, String nom, int familia, double preuBase, int iVA, int stock, int tallaCintura,
            int llargadaCamal) {
        super(id, nom, familia, preuBase, iVA, stock);
        this.tallaCintura = tallaCintura;
        this.llargadaCamal = llargadaCamal;
    }

    public int getTallaCintura() {
        return tallaCintura;
    }

    public void setTallaCintura(int tallaCintura) {
        this.tallaCintura = tallaCintura;
    }

    public int getLlargadaCamal() {
        return llargadaCamal;
    }

    public void setLlargadaCamal(int llargadaCamal) {
        this.llargadaCamal = llargadaCamal;
    }

    @Override
    public String toString() {
        return super.toString() + " | Talla cintura: " + tallaCintura + " | Llargada camal: " + llargadaCamal;
    }
}