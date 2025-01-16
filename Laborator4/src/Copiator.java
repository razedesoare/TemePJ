public class Copiator extends Echipament {
    private int ppm;
    private int pTon;
    private Format format;

    public Copiator(String denumire, int nrInv, double pret, String zonaMag, Stare stare, int ppm, Format format) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.ppm = ppm;
        this.pTon = pTon;
        this.format = format;
    }

    public int getPpm() {
        return ppm;
    }

    public int getPTon() {
        return pTon;
    }

    public Format getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; copiator; pag/min: %d; pag/toner: %d; format: %s",
                ppm, pTon, format);
    }
}