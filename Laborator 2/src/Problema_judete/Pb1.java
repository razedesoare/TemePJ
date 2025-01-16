package Problema_judete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Pb1 {
    public static void main(String[] args) throws IOException  {
        String fisier ="C:\\Users\\bianc\\IdeaProjects\\Laborator 2\\src\\judete_in.txt";
        String line;
        int nr=0;
        String[] judete=new String[8];

        BufferedReader br =new BufferedReader(new FileReader(fisier));
        Scanner scan=new Scanner(System.in);

        while((line = br.readLine())!=null)
        {
            judete[nr]= line;
            nr++;
        }

        String[] s= Arrays.copyOf(judete, nr);
        Arrays.sort(s);

        System.out.println("ordine alfabetica:\n");

        for(String jud : s)
            System.out.println(jud);

        System.out.println("judet cautat?: ");
        String judet_cautat=scan.nextLine();
        System.out.println("judetul cautat de afla pe pozitia "+(Arrays.binarySearch(s, judet_cautat)+1));
    }
}
