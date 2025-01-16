package ex2;

import java.io.*;
import java.util.*;

public class exercitiul2 {
    public static void main(String[] args)
    {
        try
        {
            File fin = new File("C:\\JAVA\\Lab1\\src\\ex2\\in.txt");
            File fout = new File("C:\\JAVA\\Lab1\\src\\ex2\\out.txt");

            Scanner scanner = new Scanner(fin);
            PrintStream print = new PrintStream(fout);

            int i = 0, suma = 0, medie, nr, min =100000, max =0;

            System.out.println("numerele din fisierul in.txt:");
            while (scanner.hasNext())
            {
                nr = scanner.nextInt();
                System.out.print(nr + " ");
                i++;
                suma=suma+nr;
                if (nr<= min)
                    min = nr;
                if (nr>= max)
                    max = nr;
            }

            medie = suma / i;

            print.println("suma = " + suma);

            print.println("medie = " + medie);

            print.println("val min = " + min);

            print.println("val max = " + max);

            System.out.println("  fisierul a fost creat cu succes  ");

            scanner.close();
            print.close();
        }
        catch (FileNotFoundException exception) { System.out.println("ceva nu e ok"); }
    }

}
