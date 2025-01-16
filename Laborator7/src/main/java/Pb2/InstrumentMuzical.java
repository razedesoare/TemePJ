package Pb2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tip")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Chitara.class, name = "chitara"),
        @JsonSubTypes.Type(value = SetTobe.class, name = "setTobe")
})

abstract class InstrumentMuzical {
    protected String producator;
    protected double pret;

    public InstrumentMuzical(String producator, double pret) {
        this.producator = producator;
        this.pret = pret;
    }

    public InstrumentMuzical() {

    }

    public String getProducator() {
        return producator;
    }

    public double getPret() {
        return pret;
    }


    public abstract void descriereInstrument();


}