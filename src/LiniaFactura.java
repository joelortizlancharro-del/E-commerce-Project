public class LiniaFactura {
    private int idTiquet;
    private int idArticle;
    private int quantitat;
    private double preuBase;
    private int iva;
    private double preuFinal;

    public LiniaFactura(int idTiquet, int idArticle, int quantitat, double preuBase, int iva, double preuFinal) {
        this.idTiquet = idTiquet;
        this.idArticle = idArticle;
        this.quantitat = quantitat;
        this.preuBase = preuBase;
        this.iva = iva;
        this.preuFinal = preuFinal;
    }

    public int getIdTiquet() {
        return idTiquet;
    }

    public void setIdTiquet(int idTiquet) {
        this.idTiquet = idTiquet;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public double getPreuBase() {
        return preuBase;
    }

    public void setPreuBase(double preuBase) {
        this.preuBase = preuBase;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public double getPreuFinal() {
        return preuFinal;
    }

    public void setPreuFinal(double preuFinal) {
        this.preuFinal = preuFinal;
    }

    public void llegirArrayList(){
        
    }

    @Override
    public String toString() {
        return "Tiquet: " + idTiquet + " | Article: " + idArticle + " | Quantitat: " + quantitat +
                " | Preu base: " + preuBase + " | IVA: " + iva + "% | Total: " + preuFinal;
    }
}