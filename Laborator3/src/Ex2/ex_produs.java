package Ex2;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ex_produs {
    public static void meniu() {
        System.out.println("opt:");
        System.out.println("1.afisare toate produse");
        System.out.println("2.afisare produse expirate");
        System.out.println("3.vanzare produs");
        System.out.println("4.afisare produs pret minim");
        System.out.println("5.salvare fisier");
        System.out.println("0.iesire");
    }

    public static void adaugaredinfiser(List<Produs> prod) throws IOException {
        File file = new File("C:\\Users\\bianc\\IdeaProjects\\Laborator3\\src\\Ex2\\produse.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            String denumire = split[0].trim();
            double pret = Double.parseDouble(split[1].trim());
            int cantitate = Integer.parseInt(split[2].trim());
            LocalDate data = LocalDate.parse(split[3].trim());
            prod.add(new Produs(denumire, pret, cantitate, data));
        }
    }

    public static void afisare(List<Produs> prod) {
        for (Produs p : prod) {
            System.out.println(p);
        }
    }

    public static void expirat(List<Produs> prod) {
        LocalDate today = LocalDate.now();
        for (Produs p : prod) {
            if (p.getData().isBefore(today)) {
                System.out.println(p + "expirat");
            } else {
                System.out.println(p + "nu expirat");
            }
        }
    }

    public static void pretmin(List<Produs> prod) {
        Produs minPretProdus = prod.get(0);
        for (Produs p : prod) {
            if (p.getPret() < minPretProdus.getPret()) {
                minPretProdus = p;
            }
        }
        System.out.println("prod minim este: " + minPretProdus);
    }

    public static void vanzare(List<Produs> prod, String denumireProdus, int cantitateVanduta) {
        for (Produs p : prod) {
            if (p.getDenumire().equalsIgnoreCase(denumireProdus)) {
                p.vindeProdus(cantitateVanduta);
                if (p.getCantitate() == 0) {
                    prod.remove(p);
                }
                return;
            }
        }
    }

    public static void salvareCantitateMica(List<Produs> prod) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ce este cantitate mica: ");
        int cantitatePrag = scanner.nextInt();

        BufferedWriter writer = new BufferedWriter(new FileWriter("salvarefisierproduse.csv"));
        for (Produs p : prod) {
            if (p.getCantitate() < cantitatePrag) {
                writer.write(p.toString() + "\n");
            }
        }
        writer.close();
        System.out.println("produse salvate cu succes.");
    }

    public static void main(String[] args) throws IOException {
        List<Produs> prod = new ArrayList<>();
        adaugaredinfiser(prod);

        Scanner scanner = new Scanner(System.in);
        boolean iesire=true;

        while (iesire) {
            meniu();
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    afisare(prod);
                    break;
                case 2:
                    expirat(prod);
                    break;
                case 3:
                    System.out.print("denumire: ");
                    String denumire = scanner.nextLine();
                    System.out.print("cantitate: ");
                    int cantitate = scanner.nextInt();
                    vanzare(prod, denumire, cantitate);
                    break;
                case 4:
                    pretmin(prod);
                    break;
                case 5:
                    salvareCantitateMica(prod);
                    break;
                case 0:
                    iesire=false;
                    break;
                default:
                    System.out.println("ceva nue ok.");
            }
        }


    }
}
