package Pb2;

import com.fasterxml.jackson.annotation.JsonProperty;

enum TipTobe {
    ELECTRONICE,
    ACUSTICE
}

public class SetTobe extends InstrumentMuzical {
    @JsonProperty("tip_tobe")
    private TipTobe tip_tobe;
    @JsonProperty("nr_tobe")
    private int nr_tobe;
    @JsonProperty("nr_cinele")
    private int nr_cinele;

    public SetTobe(String producator, double pret, TipTobe tip_tobe, int nr_tobe, int nr_cinele) {
        super(producator, pret);
        this.tip_tobe = tip_tobe;
        this.nr_tobe = nr_tobe;
        this.nr_cinele = nr_cinele;
    }
    public SetTobe() {
        super();
    }
    public TipTobe getTipTobe() {
        return tip_tobe;
    }

    public int getNrTobe() {
        return nr_tobe;
    }

    public int getNrCinele() {
        return nr_cinele;
    }

    @Override
    public void descriereInstrument() {
        System.out.println("tobe " + tip_tobe + " " + nr_tobe + " " + nr_cinele + " " + producator + " " + pret);
    }
}
