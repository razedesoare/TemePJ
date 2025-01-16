package ex4;

import java.util.Random;

public class exercitiul4 {
    public static int cmmdc(int a, int b) {
        while (b != 0) {
            int rest = a % b;
            a = b;
            b = rest;
        }
        return a;
    }
    public static void main(String[] args) {
        Random random = new Random();

        int nr1 = random.nextInt(30) + 1;
        int nr2 = random.nextInt(30) + 1;

        System.out.println("nr1 " + nr1 + " nr2 " + nr2);

        int cmmdc = cmmdc(nr1, nr2);

        System.out.println("cmmdc: " + cmmdc);
    }


}
