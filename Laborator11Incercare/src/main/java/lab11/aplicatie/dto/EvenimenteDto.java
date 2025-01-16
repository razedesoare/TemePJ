package lab11.aplicatie.dto;



import java.util.Date;

public class EvenimenteDto {


   public EvenimenteDto() {

   }

   public EvenimenteDto(String denumire, String locatie, Date data, String timp, Integer pretBilet) {
      this.denumire = denumire;
      this.locatie = locatie;
      this.data = data;
      this.timp = timp;
      this.pretBilet = pretBilet;
   }

   private String denumire;

   private String locatie;

   private Date data;

   private String timp;

   private Integer pretBilet;

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
