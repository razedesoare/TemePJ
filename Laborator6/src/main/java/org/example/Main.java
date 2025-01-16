package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<Angajat> citire() {
        try {
            File file = new File("C:\\Users\\bianc\\IdeaProjects\\Laborator6\\src\\main\\resources\\angajati.json");
            ObjectMapper mapper = new ObjectMapper();

            mapper.registerModule(new JavaTimeModule());

            List<Angajat> angajati = mapper
                    .readValue(file, new TypeReference<List<Angajat>>() {});
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        List<Angajat> lista_angajati = new ArrayList<>();
        int opt;

        Scanner my_scanner = new Scanner(System.in);
        boolean iesire = true;

        while (iesire) {
            System.out.println("1.citire si afisare json");
            System.out.println("2.afisare lista");
            System.out.println("3.afisare angajati cu salar >2500");
            System.out.println("4.creare lista");
            System.out.println("5.afisare angajati care nu sunt in conducere");
            System.out.println("6.afisare nume cu majuscule");
            System.out.println("7.afisare salarii <3000");
            System.out.println("8.afisare primul angajat");
            System.out.println("9.afisare statistici salarii");
            System.out.println("10.avem sau nu un Ion");
            System.out.println("11.afisare daca e cineva angajat in vara anului trecut");
            System.out.println("0.iesire");
            System.out.print("\nopt: ");

            opt = my_scanner.nextInt();
            my_scanner.nextLine();

            switch (opt) {
                case 0:
                    iesire = false;
                    break;

                case 1:
                    System.out.println();
                    lista_angajati = citire();
                    for (Angajat a : lista_angajati)
                        System.out.println(a.toString());
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    lista_angajati.forEach(System.out::println);
                    System.out.println();
                    break;

                case 3:
                    System.out.println("\nangajati >25000: ");
                    lista_angajati
                            .stream()
                            .filter((a) -> a.getSalariul() > 2500)
                            .forEach(System.out::println);
                    System.out.println();
                    break;

                case 4:
                    int an_curent = LocalDate.now().getYear();
                    System.out.println();
                    List<Angajat> lista_noua = lista_angajati
                            .stream()
                            .filter(angajat -> angajat.getData_angajarii().getMonthValue() == 4 &&
                                    angajat.getData_angajarii().getYear() == an_curent - 1 &&
                                    (angajat.getPostul().contains("sef") || angajat.getPostul().contains("director")))
                            .collect(Collectors.toList());
                    lista_noua.forEach(System.out::println);
                    System.out.println();
                    break;

                case 5:
                    lista_angajati
                            .stream()
                            .filter(angajat -> !angajat.getPostul().contains("sef") && !angajat.getPostul().contains("director"))
                            .sorted((a1, a2) -> Float.compare(a2.getSalariul(), a1.getSalariul()))
                            .forEach(System.out::println);
                    System.out.println();
                    break;

                case 6:
                    System.out.println();
                    List<String> lista_majuscule = lista_angajati
                            .stream()
                            .map(a -> a.getNume().toUpperCase())
                            .collect(Collectors.toList());
                    lista_majuscule.forEach(System.out::println);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("\nangajati salar <3000: ");
                    lista_angajati
                            .stream()
                            .map(Angajat::getSalariul)
                            .filter(salariul -> salariul < 3000)
                            .forEach(System.out::println);
                    System.out.println();
                    break;

                case 8:
                    System.out.println("\nprimul angajat:");
                    lista_angajati
                            .stream()
                            .min(Comparator.comparing(Angajat::getData_angajarii))
                            .ifPresent(System.out::println);
                    System.out.println();
                    break;

                case 9:
                    System.out.println("statistica: ");
                    DoubleSummaryStatistics statistici = lista_angajati
                            .stream()
                            .collect(Collectors.summarizingDouble(Angajat::getSalariul));
                    System.out.println("salar mediu: " + statistici.getAverage());
                    System.out.println("salar maxim: " + statistici.getMax());
                    System.out.println("salar minim: " + statistici.getMin());
                    System.out.println();
                    break;

                case 10:
                    System.out.println();
                    Optional<Angajat> numeIon = lista_angajati
                            .stream()
                            .filter(angajat -> angajat.getNume().equalsIgnoreCase("Ion"))
                            .findAny();

                    numeIon.ifPresentOrElse(angajat -> System.out.println("avem Ion"),
                            () -> System.out.println("nu avem Ion"));
                    System.out.println();
                    break;

                case 11:
                    System.out.println();
                    int anCurent = LocalDate.now().getYear();
                    long nr_angajati = lista_angajati
                            .stream()
                            .filter(angajat -> angajat.getData_angajarii().getMonthValue() >= 6 &&
                                    angajat.getData_angajarii().getMonthValue() <= 8 &&
                                    angajat.getData_angajarii().getYear() == anCurent - 1)
                            .count();
                    System.out.println(nr_angajati);
                    System.out.println();
                    break;

                default:
                    System.out.println("ceva nu e ok");
                    break;
            }
        }
    }
}
