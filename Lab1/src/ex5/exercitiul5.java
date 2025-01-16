package ex5;

import java.util.Random;

public class exercitiul5 {
    public static boolean esteFibonacci(int n) {
        int a = 0;
        int b = 1;

        while (a <= n) {
            if (a == n) {
                return true;
            }
            int aux = a + b;
            a = b;
            b = aux;
        }
        return false;
    }
     public static void main(String[] args) {
         Random random = new Random();
         int nr = random.nextInt(20) + 1;
         System.out.println("nr " + nr);
        if(esteFibonacci(nr)) {System.out.println("este fibonacci "+ nr);}
        else {System.out.println("nu este fibonacci");}
     }

}
