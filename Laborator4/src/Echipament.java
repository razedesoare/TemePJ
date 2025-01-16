
enum Stare {
    ACHIZITIONAT, EXPUS, VANDUT
}

enum Mod {
    COLOR, ALB_NEGRU
}

enum Format {
    A3, A4
}

enum Sistem {
    WINDOWS, LINUX
}

public abstract class Echipament {
    private String denumire;
    private int nrInv;
    private double pret;
    private String zonaMag;
    private Stare stare;

    public Echipament(String denumire, int nrInv, double pret, String zonaMag, Stare stare) {
        this.denumire = denumire;
        this.nrInv = nrInv;
        this.pret = pret;
        this.zonaMag = zonaMag;
        this.stare = stare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNrInv() {
        return nrInv;
    }

    public void setNrInv(int nrInv) {
        this.nrInv = nrInv;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getZonaMag() {
        return zonaMag;
    }

    public void setZonaMag(String zonaMag) {
        this.zonaMag = zonaMag;
    }

    public Stare getStare() {
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return String.format("denum: %s; nr inv: %d; pret: %.2f; zona maga: %s; stare: %s",
                denumire, nrInv, pret, zonaMag, stare);
    }

}

