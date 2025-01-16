package Pb2;

import com.fasterxml.jackson.annotation.JsonProperty;

enum TipChitara {
    ELECTRICA,
    ACUSTICA,
    CLASICA
}

public class Chitara extends InstrumentMuzical {
    @JsonProperty("tip_chitara")
    private TipChitara tip_chitara;
    @JsonProperty("nr_corzi")
    private int nr_corzi;

    public Chitara(String producator, double pret, TipChitara tip_chitara, int nr_corzi) {
        super(producator, pret);
        this.tip_chitara = tip_chitara;
        this.nr_corzi = nr_corzi;
    }
    public Chitara() {
        super();
    }

    public TipChitara getTipChitara() {
        return tip_chitara;
    }

    public int getNrCorzi() {
        return nr_corzi;
    }

    @Override
    public void descriereInstrument() {
        System.out.println("chitara " + tip_chitara + "  " + nr_corzi + " " + producator + " " + pret );
    }
}
