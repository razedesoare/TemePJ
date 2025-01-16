package ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Mobilier> citesteDinJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Mobilier>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int calculeazaColiPal(List<Placa> placi) {
        final int SUPRAFATA_COALA=2800*2070;
        int suprafataTotala=0;

        for (Placa placa : placi) {
            int suprafataPlaca = placa.getLungime() * placa.getLatime() * placa.getNr_bucati();
            suprafataTotala += suprafataPlaca;
        }

        return (int) Math.ceil((double) suprafataTotala / SUPRAFATA_COALA); //e pentru a afisa minumul intreg
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\bianc\\IdeaProjects\\Lab5\\src\\main\\resources\\mobilier.json";
        List<Mobilier> mobilierList = citesteDinJson(filePath);

        if (mobilierList == null) {
            System.out.println("ceva nu e ok");
            return;
        }


        System.out.println("afisarea pieselor:");
        for (Mobilier mobilier : mobilierList) {
            System.out.println(mobilier.getNume() + ":");
            for (Placa placa : mobilier.getPlaci()) {
                System.out.println("  - " + placa.toString());
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("ce tip de mobilier pentru detalii:");
        String numeMobilier = scanner.nextLine();

        Mobilier mobilierCautat = mobilierList.stream()
                .filter(m -> m.getNume().equalsIgnoreCase(numeMobilier))
                .findFirst()
                .orElse(null);

        if (mobilierCautat != null) {
            System.out.println("ce cauti este: "+ mobilierCautat.getNume());
            for (Placa placa : mobilierCautat.getPlaci()) {
                System.out.println(placa.toString());
            }
        } else {
            System.out.println("ceva nu e ok");
        }

        System.out.println("ce tip pentru calcul coli:");
        String numeMobilierPal = scanner.nextLine();

        Mobilier mobilierPal = mobilierList.stream()
                .filter(m -> m.getNume().equalsIgnoreCase(numeMobilierPal))
                .findFirst()
                .orElse(null);

        if (mobilierPal != null) {
            int coliNecesare = calculeazaColiPal(mobilierPal.getPlaci());
            System.out.println("estimare " + mobilierPal.getNume() + ": " + coliNecesare);
        } else {
            System.out.println("ceva nu e ok");
        }

        scanner.close();
    }
}
