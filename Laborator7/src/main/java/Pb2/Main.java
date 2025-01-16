package Pb2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Set<InstrumentMuzical> instrumente = new HashSet<>();

        boolean iesire = true;
        while (iesire) {
            System.out.println("1.adauga chitara");
            System.out.println("2.adauga tobe");
            System.out.println("3.afisare");
            System.out.println("4.salvare");
            System.out.println("5.incarcare din fisier");
            System.out.println("6.verificare dubluri");
            System.out.println("7.stergere >3000");
            System.out.println("8.afisare chitara");
            System.out.println("9.afisare tobe");
            System.out.println("10.chitara cu cele mai multe corzi");
            System.out.println("11.afisare tobe ordonat");
            System.out.println("0.iesire");
            System.out.print("opt: ");

            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 0:
                    iesire = false;
                    break;
                case 1:
                    System.out.print("prod: ");
                    String producatorChitara = scanner.nextLine();
                    System.out.print("pret: ");
                    double pretChitara = scanner.nextDouble();
                    System.out.print("tip chitara(1-ELECTRICA,2-ACUSTICA,3-CLASICA):");
                    int tipChitaraOption = scanner.nextInt();
                    TipChitara tipChitara = TipChitara.values()[tipChitaraOption - 1];
                    System.out.print("nr corzi: ");
                    int nrCorzi = scanner.nextInt();
                    scanner.nextLine();
                    instrumente.add(new Chitara(producatorChitara, pretChitara, tipChitara, nrCorzi));
                    System.out.println("chitara adaugata");
                    break;
                case 2:

                    System.out.print("prod: ");
                    String producatorTobe = scanner.nextLine();
                    System.out.print("pret: ");
                    double pretTobe = scanner.nextDouble();
                    System.out.print("tip tobe(1-ELECTRONICE,2-ACUSTICE):");
                    int tipTobeOption = scanner.nextInt();
                    TipTobe tipTobe = TipTobe.values()[tipTobeOption - 1];
                    System.out.print("nr tobe: ");
                    int nrTobe = scanner.nextInt();
                    System.out.print("nr cinele: ");
                    int nrCinele = scanner.nextInt();
                    scanner.nextLine();
                    instrumente.add(new SetTobe(producatorTobe, pretTobe, tipTobe, nrTobe, nrCinele));
                    System.out.println("tobe adaugate");
                    break;
                case 3:
                    instrumente.forEach(InstrumentMuzical::descriereInstrument);
                    break;
                case 4:
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
                    mapper.enable(SerializationFeature.INDENT_OUTPUT);
                    mapper.writeValue(new File("instrumente.json"), instrumente);
                    System.out.println("salvare ok");
                    break;
                case 5:
//                    mapper = new ObjectMapper();
//                    instrumente = new HashSet<>(mapper.readValue(new File("instrumente.json"),
//                                    mapper.getTypeFactory().constructCollectionType(Set.class, InstrumentMuzical.class)));
//                    System.out.println("ai citit din fisier");
                    break;
                case 6:

                    Chitara chitaranoua = new Chitara("Fender", 2500.0, TipChitara.ELECTRICA, 6);
                    boolean adaugata = instrumente.add(chitaranoua);
                    if (!adaugata) {
                        System.out.println("duplicat");
                    } else {
                        System.out.println("nu e duplicat si a fost adaugat");
                    }
                    break;
                case 7:

                    instrumente.removeIf(instrument -> instrument.getPret() > 3000);
                    System.out.println("stergerea a fost facuta");
                    break;
                case 8:
                    instrumente.stream()
                            .filter(instrument -> instrument instanceof Chitara)
                            .forEach(InstrumentMuzical::descriereInstrument);
                    break;
                case 9:

                    instrumente.stream()
                            .filter(instrument -> instrument instanceof SetTobe)
                            .forEach(InstrumentMuzical::descriereInstrument);
                    break;
                case 10:
                    Optional<Chitara> multecorzi = instrumente.stream()
                            .filter(instrument -> instrument instanceof Chitara)
                            .map(instrument -> (Chitara) instrument)
                            .max(Comparator.comparingInt(Chitara::getNrCorzi));
                    multecorzi.ifPresent(Chitara::descriereInstrument);
                    break;
                case 11:
                    instrumente.stream()
                            .filter(instrument -> instrument instanceof SetTobe)
                            .map(instrument -> (SetTobe) instrument)
                            .filter(setTobe -> setTobe.getTipTobe() == TipTobe.ACUSTICE)
                            .sorted(Comparator.comparingInt(SetTobe::getNrTobe))
                            .forEach(InstrumentMuzical::descriereInstrument);
                    break;
                default:
                    System.out.println("ceva nu e ok");
                    break;
            }
        }

        scanner.close();
    }


}
