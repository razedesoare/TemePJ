public class Imprimanta extends Echipament {
    private int ppm;
    private String rezolutie;
    private int pCar;
    private Mod mod;

    public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, Stare stare, int ppm, String rezolutie, int pCar, Mod mod) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.pCar = pCar;
        this.mod = mod;
    }


    public int getPpm() {
        return ppm;
    }

    public String getRezolutie() {
        return rezolutie;
    }

    public int getPCar() {
        return pCar;
    }

    public Mod getMod() {
        return mod;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; imprimanta; pag/min: %d; rezolutie: %s; pag/cartus: %d; mod: %s",
                ppm, rezolutie, pCar, mod);
    }
}