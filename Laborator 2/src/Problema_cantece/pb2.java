package Problema_cantece;

import java.io.*;


public class pb2 {
    public static void main(String[] args) throws IOException {

        String in = "C:\\Users\\bianc\\IdeaProjects\\Laborator 2\\src\\Problema_cantece\\cantec_in.txt";
        String out = "C:\\Users\\bianc\\IdeaProjects\\Laborator 2\\src\\Problema_cantece\\cantec_out.txt";

        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintStream ps = new PrintStream(out);


        Vers vers;
        String linie;

        while ((linie = br.readLine()) != null) {
            vers = new Vers(linie);



            ps.print(vers.getvers() + " " + vers.numarcuvinte() + " " + vers.numarvocale() + " ");

            if (vers.terminatie("ra"))
                ps.print("*");

            ps.print("\n");

        }

    }
}
