public class Tiquet {
    private int id;
    private String data;
    private String dniClient;
    private double totalBase;
    private double totalIva;
    private double totalFinal;

    public Tiquet(int id, String data, String dniClient, double totalBase, double totalIva, double totalFinal) {
        this.id = id;
        this.data = data;
        this.dniClient = dniClient;
        this.totalBase = totalBase;
        this.totalIva = totalIva;
        this.totalFinal = totalFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDniClient() {
        return dniClient;
    }

    public void setDniClient(String dniClient) {
        this.dniClient = dniClient;
    }

    public double getTotalBase() {
        return totalBase;
    }

    public void setTotalBase(double totalBase) {
        this.totalBase = totalBase;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    @Override
    public String toString() {
        return "ID Tiquet: " + id + " | Data: " + data + " | Client: " + dniClient +
                " | Base: " + totalBase + " | IVA: " + totalIva + " | Total: " + totalFinal;
    }
}