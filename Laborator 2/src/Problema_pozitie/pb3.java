package Problema_pozitie;

import java.util.Scanner;

public class pb3 {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        System.out.print("sir initial: ");
        StringBuilder sirinitial = new StringBuilder(scan.nextLine());

        System.out.print("sir caractere de inserat: ");
        StringBuilder sircaractere = new StringBuilder(scan.nextLine());

        System.out.print("pe ce pozitie vrei sa se insereze sirul de caractere?: ");
        int poz = scan.nextInt();
        int nr = sircaractere.length();

        sirinitial.delete(poz, poz + nr);
        sirinitial.insert(poz, sircaractere);

        System.out.println("sirul final dupa inserare este: " + sirinitial);


    }
}
