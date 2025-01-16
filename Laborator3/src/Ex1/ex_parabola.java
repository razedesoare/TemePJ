package Ex1;

import java.io.*;
import java.util.*;

public class ex_parabola {
    public static void main(String[] args) {

        List<Parabola> parabole = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bianc\\IdeaProjects\\Laborator3\\src\\Ex1\\in.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coef = line.split(" ");
                int a = Integer.parseInt(coef[0]);
                int b = Integer.parseInt(coef[1]);
                int c = Integer.parseInt(coef[2]);


                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < parabole.size(); i++) {
            Parabola p1 = parabole.get(i);

            int[] varf1 = p1.varf();
            System.out.println("parabola are varful (" + varf1[0] + ", " + varf1[1] + ")");

            for (int j = i + 1; j < parabole.size(); j++) {
                Parabola p2 = parabole.get(j);
                int[] mijloc = p1.mijloc(p2);
                double distanta = p1.distanta(p2);

                System.out.println("mijlocul(" + mijloc[0] + ", " + mijloc[1] + ")");
                System.out.println("distanta: " + distanta);
            }
            System.out.println();
        }
    }
}
