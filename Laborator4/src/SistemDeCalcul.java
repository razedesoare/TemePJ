public class SistemDeCalcul extends Echipament {
    private String tipMon;
    private double vitProc;
    private int cHdd;
    private Sistem sistem;

    public SistemDeCalcul(String denumire, int nrInv, double pret, String zonaMag, Stare stare, String tipMon, double vitProc, int cHdd, Sistem sistem) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.tipMon = tipMon;
        this.vitProc = vitProc;
        this.cHdd = cHdd;
        this.sistem = sistem;
    }

    public String getTipMon() {
        return tipMon;
    }

    public double getVitProc() {
        return vitProc;
    }

    public int getCHdd() {
        return cHdd;
    }

    public Sistem getSistem() {
        return sistem;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Sistem de calcul; tip  monitor: %s; viteza procesor: %.2f; capacitate HDD: %d; sistem operare: %s",
                tipMon, vitProc, cHdd, sistem);
    }
}