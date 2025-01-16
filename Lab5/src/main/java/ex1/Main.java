package ex1;

import java.io.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void scriere(List<Pereche> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("C:\\Users\\bianc\\IdeaProjects\\Lab5\\src\\main\\resources\\perechenumere_out.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pereche> citire() {
        try {
            File file=new File("C:\\Users\\bianc\\IdeaProjects\\Lab5\\src\\main\\resources\\perechenumere.json");
            ObjectMapper mapper=new ObjectMapper();
            List<Pereche> lista = mapper
                    .readValue(file, new TypeReference<List<Pereche>>(){});
            return lista;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        List<Pereche> listaPerechi = new ArrayList<>();

        scriere(listaPerechi);

        List<Pereche> listaCitita = citire();
        for (Pereche pereche : listaCitita) {
            System.out.println(pereche.toString());
        }

        for (Pereche pereche : listaCitita) {
            System.out.println(pereche.toString());
            System.out.println("consecutive in fibbo? " + pereche.suntConsecutiveFibonacci());
            System.out.println("CMMMC: " + pereche.cmmmc());
            System.out.println("suam cifre = ? " + pereche.auSumaCifrelorEgala());
            System.out.println("nr cifre pare = ? " + pereche.auCifrePareEgale());
        }

    }
}
