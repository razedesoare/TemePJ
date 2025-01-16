import java.io.*;
import java.util.*;

public class Main {
    private static List<Echipament> echipamente = new ArrayList<>();

    public static void main(String[] args) {
        citireDinFisier("C:\\Users\\bianc\\IdeaProjects\\Laborator4\\src\\echipamente.txt");

        Scanner sc = new Scanner(System.in);
        boolean iesire = true;

        while (iesire) {
            System.out.println("1.afisare echipamente");
            System.out.println("2.afisare imprimante");
            System.out.println("3.afisare copiatoarele");
            System.out.println("4.afisare sistemele de calcul");
            System.out.println("5.modificare stare");
            System.out.println("6.setare mod scriere");
            System.out.println("7.setare format");
            System.out.println("8.instalare sistem");
            System.out.println("9.afisare vandut");
            System.out.println("10.serializare/salvare");
            System.out.println("11.deserializare");
            System.out.println("0.iesire");
            System.out.print("opt: ");

            int optiune = sc.nextInt();
            sc.nextLine(); // Consuma linia rămasă după nextInt()

            switch (optiune) {
                case 1:
                    afisareEchipamente();
                    break;
                case 2:
                    afisareImprimante();
                    break;
                case 3:
                    afisareCopiatoare();
                    break;
                case 4:
                    afisareSistemeDeCalcul();
                    break;
                case 5:
                    modificareStareEchipament(sc);
                    break;
                case 6:
                    seteazaModScriere(sc);
                    break;
                case 7:
                    seteazaFormatCopiere(sc);
                    break;
                case 8:
                    instaleazaSistemOperare(sc);
                    break;
                case 9:
                    afisareEchipamenteVandute();
                    break;
                case 10:
                    serializare(echipamente, "echip.bin");
                    break;
                case 11:
                    Echipament []q;
                    q=(Echipament[])deserializare("echip.bin");
                    for(Echipament e:q)
                        if(e!=null)
                            System.out.println(e);
                    break;
                case 0:
                    iesire = false;
                    break;
                default:
                    System.out.println("Opțiune invalidă.");
                    break;
            }
        }

        sc.close();
    }

    public static void citireDinFisier(String numeFisier) {

        try (Scanner sc = new Scanner(new File(numeFisier))) {
            while (sc.hasNextLine()) {
                String linie = sc.nextLine();
                String[] date = linie.split(";");

                String denumire = date[0];
                int nrInv = Integer.parseInt(date[1]);
                double pret = Double.parseDouble(date[2]);
                String zonaMag = date[3];
                Stare stare = Stare.valueOf(date[4].toUpperCase());
                String tip = date[5];

                if (tip.equalsIgnoreCase("imprimanta")) {
                    int ppm = Integer.parseInt(date[6]);
                    String rezolutie = date[7];
                    int pCar = Integer.parseInt(date[8]);
                    Mod mod = Mod.valueOf(date[9].toUpperCase());

                    Imprimanta imprimanta = new Imprimanta(denumire, nrInv, pret, zonaMag, stare, ppm, rezolutie, pCar, mod);
                    echipamente.add(imprimanta);
                } else if (tip.equalsIgnoreCase("copiator")) {
                    int ppm = Integer.parseInt(date[6]);
                    Format format = Format.valueOf(date[7].toUpperCase());

                    Copiator copiator = new Copiator(denumire, nrInv, pret, zonaMag, stare, ppm, format);
                    echipamente.add(copiator);
                } else if (tip.equalsIgnoreCase("sistem de calcul")) {
                    String tipMon = date[6];
                    double vitProc = Double.parseDouble(date[7]);
                    int cHdd = Integer.parseInt(date[8]);
                    Sistem sistem = Sistem.valueOf(date[9].toUpperCase());

                    SistemDeCalcul sistemDeCalcul = new SistemDeCalcul(denumire, nrInv, pret, zonaMag, stare, tipMon, vitProc, cHdd, sistem);
                    echipamente.add(sistemDeCalcul);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ceva nue ok!");
        }
    }


    public static void serializare(Object o, String fis) {
        try {
            FileOutputStream f = new FileOutputStream(fis);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
            f.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializare(String fis) {
        try (FileInputStream f = new FileInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(f)) {

            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void afisareEchipamente() {
        for (Echipament echipament : echipamente) {
            System.out.println(echipament);
        }
    }


    public static void afisareImprimante() {
        for (Echipament echipament : echipamente) {
            if (echipament instanceof Imprimanta) {
                System.out.println(echipament);
            }
        }
    }

    public static void afisareCopiatoare() {
        for (Echipament echipament : echipamente) {
            if (echipament instanceof Copiator) {
                System.out.println(echipament);
            }
        }
    }


    public static void afisareSistemeDeCalcul() {
        for (Echipament echipament : echipamente) {
            if (echipament instanceof SistemDeCalcul) {
                System.out.println(echipament);
            }
        }
    }


    public static void modificareStareEchipament(Scanner sc) {
        System.out.print("nr inventar: ");
        int nrInv = sc.nextInt();
        sc.nextLine();

        Echipament echipament = cautaEchipament(nrInv);
        if (echipament != null) {
            System.out.println("stare actuala: " + echipament.getStare());
            System.out.println("stare noua: ");
            System.out.println("1.achizitionat");
            System.out.println("2.expus");
            System.out.println("3.vandut");
            int optiune = sc.nextInt();
            Stare stareNoua = Stare.values()[optiune - 1];
            echipament.setStare(stareNoua);
            System.out.println("succes.");
        } else {
            System.out.println("ceva nu e ok");
        }
    }

    public static Echipament cautaEchipament(int nrInv) {
        for (Echipament echipament : echipamente) {
            if (echipament.getNrInv() == nrInv) {
                return echipament;
            }
        }
        return null;
    }

    public static void seteazaModScriere(Scanner sc) {
        System.out.print("nr inventar: ");
        int nrInv = sc.nextInt();
        sc.nextLine();

        Echipament echipament = cautaEchipament(nrInv);
        if (echipament instanceof Imprimanta) {
            System.out.println("mod actual: " + ((Imprimanta) echipament).getMod());
            System.out.println("mod nou: ");
            System.out.println("1.color");
            System.out.println("2.alb-negru");
            int optiune = sc.nextInt();
            Mod modNou = Mod.values()[optiune - 1];
            //((Imprimanta) echipament).setMod(modNou);
            System.out.println("succes.");
        } else {
            System.out.println("ceva nu e ok");
        }
    }

    public static void seteazaFormatCopiere(Scanner sc) {
        System.out.print("nr inventar: ");
        int nrInv = sc.nextInt();
        sc.nextLine();

        Echipament echipament = cautaEchipament(nrInv);
        if (echipament instanceof Copiator) {
            System.out.println("format actual: " + ((Copiator) echipament).getFormat());
            System.out.println("format nou: ");
            System.out.println("1.A3");
            System.out.println("2.A4");
            int optiune = sc.nextInt();
            Format formatNou = Format.values()[optiune - 1];
            //((Copiator) echipament).setFormat(formatNou);
            System.out.println("succes.");
        } else {
            System.out.println("ceva nu e ok");
        }
    }

    public static void instaleazaSistemOperare(Scanner sc) {
        System.out.print("nr inventar: ");
        int nrInv = sc.nextInt();
        sc.nextLine();

        Echipament echipament = cautaEchipament(nrInv);
        if (echipament instanceof SistemDeCalcul) {
            System.out.println("sistem actual: " + ((SistemDeCalcul) echipament).getSistem());
            System.out.println("sistem nou: ");
            System.out.println("1.windows");
            System.out.println("2.linux");
            int optiune = sc.nextInt();
            Sistem sistemNou = Sistem.values()[optiune - 1];
            //((SistemDeCalcul) echipament).setSistem(sistemNou);
            System.out.println("succes.");
        } else {
            System.out.println("ceva nu e ok");
        }
    }


    public static void afisareEchipamenteVandute() {
        for (Echipament echipament : echipamente) {
            if (echipament.getStare() == Stare.VANDUT) {
                System.out.println(echipament);
            }
        }
    }
}
