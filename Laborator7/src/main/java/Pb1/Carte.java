package Pb1;

public class Carte {
    private String titlu;
    private String autor;
    private int anAparitie;

    public Carte(String titlu, String autor, int anAparitie) {
        this.titlu = titlu;
        this.autor = autor;
        this.anAparitie = anAparitie;
    }
    public Carte() {}

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "titlul='" + titlu + '\'' +
                ", autorul='" + autor + '\'' +
                ", anul=" + anAparitie +
                '}';
    }
}
