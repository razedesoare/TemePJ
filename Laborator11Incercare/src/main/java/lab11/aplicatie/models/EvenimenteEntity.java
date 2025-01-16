package lab11.aplicatie.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "evenimente")
public class EvenimenteEntity {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "denumire")
    private String denumire;

    @Column(name = "locatie")
    private String locatie;

    @Column(name = "data")
    private Date data;

    @Column(name = "timp")
    private String timp;

    @Column(name ="pret_bilet")
    private Integer pretBilet;

    public EvenimenteEntity() {

    }

    public EvenimenteEntity(int id, String denumire, String locatie, Date data, String timp, Integer pretBilet) {
        this.id = id;
        this.denumire = denumire;
        this.locatie = locatie;
        this.data = data;
        this.timp = timp;
        this.pretBilet = pretBilet;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTimp() {
        return timp;
    }

    public void setTimp(String timp) {
        this.timp = timp;
    }

    public Integer getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(Integer pretBilet) {
        this.pretBilet = pretBilet;
    }
}
