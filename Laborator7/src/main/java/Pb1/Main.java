package Pb1;

import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Map<Integer, Carte> citire() {
        try {
            File file = new File("C:\\Users\\bianc\\IdeaProjects\\Laborator7\\src\\main\\resources\\carti.json");
            ObjectMapper mapper = new ObjectMapper();

            Map<Integer, Carte> carti = mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {});
            return carti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void salvare(Map<Integer, Carte> carti) {
        try {
            File file = new File("C:\\Users\\bianc\\IdeaProjects\\Laborator7\\src\\main\\resources\\carti.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, carti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<Integer, Carte> carti = citire();
        Scanner scan = new Scanner(System.in);
        boolean iesire = true;

        while (iesire) {
            System.out.println("1.afisare");
            System.out.println("2.stergere");
            System.out.println("3.adaugare");
            System.out.println("4.salvare");
            System.out.println("5.carti dupa autor harari");
            System.out.println("6.afisare dupa titlu");
            System.out.println("7.cea mai veche carte");
            System.out.println("0.iesire");
            System.out.print("opt: ");

            int opt = scan.nextInt();
            scan.nextLine();

            switch (opt) {
                case 0:
                    iesire = false;
                    break;

                case 1:
                    carti.forEach((id, carte) -> System.out.println("ID: " + id + ", " + carte));
                    break;

                case 2:
                    System.out.print("da-ti id pentru stergere: ");
                    int idDeSters = scan.nextInt();
                    carti.remove(idDeSters);
                    System.out.println("cartea a fost stearsa.");
                    break;

                case 3:
                    System.out.print("titlu: ");
                    String titlu = scan.nextLine();
                    System.out.print("a: ");
                    String autor = scan.nextLine();
                    System.out.print("an: ");
                    int anAparitie = scan.nextInt();
                    int idNou = carti.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
                    carti.putIfAbsent(idNou, new Carte(titlu, autor, anAparitie));
                    System.out.println("success");
                    break;

                case 4:
                    salvare(carti);
                    System.out.println("success");
                    break;

                case 5:
                    Set<Carte> cartiHarari = carti.values().stream()
                            .filter(carte -> "Yuval Noah Harari".equals(carte.getAutor()))
                            .collect(Collectors.toSet());
                    cartiHarari.forEach(System.out::println);
                    break;

                case 6:
                    carti.values().stream()
                            .sorted(Comparator.comparing(Carte::getTitlu))
                            .forEach(System.out::println);
                    break;

                case 7:
                    carti.values().stream()
                            .min(Comparator.comparingInt(Carte::getAnAparitie))
                            .ifPresent(System.out::println);
                    break;

                default:
                    System.out.println("Opțiune invalidă.");
                    break;
            }
        }

        scan.close();
    }

}