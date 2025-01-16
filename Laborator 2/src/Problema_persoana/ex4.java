package Problema_persoana;

import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("da-ti un n: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] nume = new String[n];
        String[] cnppersoana = new String[n];
        int[] varsta = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("nume: ");
            nume[i] = scanner.nextLine();

            String cnp;
            while (true) {
                System.out.print("cnp: ");
                cnp = scanner.nextLine();
                if (eokcnp(cnp)) {
                    break;
                } else {
                    System.out.println("nu e bun cnp-ul");
                }
            }
            cnppersoana[i] = cnp;
            varsta[i] = calculeazavarsta(cnp);
        }

        System.out.println("\nPersoane:");
        for (int i = 0; i < n; i++) {
            System.out.println("nume: " + nume[i]);
            System.out.println("cnp: " + cnppersoana[i]);
            System.out.println("varsta: " + varsta[i]);
            System.out.println();
        }
    }

    public static boolean eokcnp(String cnp) {
        if (cnp.length() != 13) {
            return false;
        }

        char firstDigit = cnp.charAt(0);
        if (firstDigit != '1' && firstDigit != '2' && firstDigit != '5' && firstDigit != '6') {
            return false;
        }
        return true;
    }



    public static int calculeazavarsta(String cnp) {
        int ancurent = java.time.Year.now().getValue();
        int anulnasterii;

        char primacifra = cnp.charAt(0);
        if (primacifra == '1' || primacifra == '2') {
            anulnasterii = 1900 + Integer.parseInt(cnp.substring(1, 3));
        } else {
            anulnasterii = 2000 + Integer.parseInt(cnp.substring(1, 3));
        }

        return ancurent - anulnasterii;
    }

}
