package ex1;

import java.util.Scanner;

public class exercitiul1 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Da-mi lungimea: ");
        int lungimea = sc.nextInt();
        System.out.println("Da-mi latimea: ");
        int latimea = sc.nextInt();
        int perimetru = 2 * (lungimea + latimea);
        int aria = lungimea * latimea;


        System.out.println("Perimetru: " + perimetru);
        System.out.println("Aria: " + aria);
        sc.close();
    }
}
