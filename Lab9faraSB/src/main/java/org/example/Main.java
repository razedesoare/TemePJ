package org.example;

import java.sql.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean iesire = true;

        String url = "jdbc:mysql://localhost:3306/lab9";
        String user = "root";
        String parola = "parolasql";

        while (iesire) {

            System.out.println("1.adaugare masina");
            System.out.println("2.sterge masina dupa nr de inmatriculare");
            System.out.println("3.cauta masina dupa nr de inmatriculare");
            System.out.println("4.afisare");
            System.out.println("5.numara masini dupa marca");
            System.out.println("6.numara masini cu km<100000");
            System.out.println("7.afisare masini noi <5");
            System.out.println("0.iesire");
            System.out.println("opt:");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1:
                    adaugaMasina(scanner, url, user, parola);
                    break;
                case 2:
                    stergeMasina(scanner, url, user, parola);
                    break;
                case 3:
                    cautaMasina(scanner, url, user, parola);
                    break;
                case 4:
                    afiseazaMasini(url, user, parola);
                    break;
                case 5:
                    numaraMasiniDupaMarca(scanner, url, user, parola);
                    break;
                case 6:
                    numaraMasiniSub100000Km(url, user, parola);
                    break;
                case 7:
                    afiseazaMasiniMaiNoiDe5Ani(url, user, parola);
                    break;
                case 0:
                    iesire = false;
                    break;
                default:
                    System.out.println("ceva nu e ok");
            }
        }
    }

    public static Connection getConnection(String url, String user, String parola) throws SQLException {
        return DriverManager.getConnection(url, user, parola);
    }

    public static void adaugaMasina(Scanner scanner, String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            System.out.print("nr inmatriculare: ");
            String numarInmatriculare = scanner.nextLine();

            System.out.print("marca: ");
            String marca = scanner.nextLine();

            int anFabricatie = -1;
            while (anFabricatie < 1900 || anFabricatie > 2025) {
                System.out.print("an: ");
                try {
                    anFabricatie = Integer.parseInt(scanner.nextLine());
                    if (anFabricatie < 1900 || anFabricatie > 2025) {
                        System.out.println("an nu e ok");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("an nu e ok");
                }
            }

            System.out.print("culoare: ");
            String culoare = scanner.nextLine();

            int km = -1;
            while (km < 0) {
                System.out.print("nr km: ");
                try {
                    km = Integer.parseInt(scanner.nextLine());
                    if (km < 0) {
                        System.out.println("nr km nu e ok");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("nr km nu e ok");
                }
            }

            String query = "INSERT INTO masini (nr_inmatriculare, marca, anul_fabricatie, culoare, km) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, numarInmatriculare);
                ps.setString(2, marca);
                ps.setInt(3, anFabricatie);
                ps.setString(4, culoare);
                ps.setInt(5, km);
                ps.executeUpdate();
                System.out.println("ok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void stergeMasina(Scanner scanner, String url, String user, String parola) {
        System.out.print("nr inmatriculare: ");
        String numarInmatriculare = scanner.nextLine();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "DELETE FROM masini WHERE nr_inmatriculare = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, numarInmatriculare);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("sters");
                } else {
                    System.out.println("nu e sters");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cautaMasina(Scanner scanner, String url, String user, String parola) {
        System.out.print("nr inmatriculare ");
        String numarInmatriculare = scanner.nextLine();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT * FROM masini WHERE nr_inmatriculare = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, numarInmatriculare);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String marca = rs.getString("marca");
                    int anFabricatie = rs.getInt("anul_fabricatie");
                    String culoare = rs.getString("culoare");
                    int km = rs.getInt("km");
                    System.out.println("masina: " + marca + " " + anFabricatie + " " + culoare + " " + km);
                } else {
                    System.out.println("nu este masina asta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afiseazaMasini(String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT * FROM masini";
            try (Statement ps = conn.createStatement()) {
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    String numarInmatriculare = rs.getString("nr_inmatriculare");
                    String marca = rs.getString("marca");
                    int anFabricatie = rs.getInt("anul_fabricatie");
                    String culoare = rs.getString("culoare");
                    int km = rs.getInt("km");
                    System.out.println(numarInmatriculare + ": " + marca + ", " + anFabricatie + ", " + culoare + ", " + km + " km");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void numaraMasiniDupaMarca(Scanner scanner, String url, String user, String parola) {
        System.out.print("marca: ");
        String marca = scanner.nextLine();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT COUNT(*) FROM masini WHERE marca = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, marca);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("masini " + marca + ": " + count);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void numaraMasiniSub100000Km(String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT COUNT(*) FROM masini WHERE km < 100000";
            try (Statement ps = conn.createStatement()) {
                ResultSet rs = ps.executeQuery(query);
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("nr masini sub 100000 km: " + count);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afiseazaMasiniMaiNoiDe5Ani(String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT * FROM masini WHERE anul_fabricatie > ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, 2020); // Anul curent - 5 ani
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String numarInmatriculare = rs.getString("nr_inmatriculare");
                    String marca = rs.getString("marca");
                    int anFabricatie = rs.getInt("anul_fabricatie");
                    String culoare = rs.getString("culoare");
                    int km = rs.getInt("km");
                    System.out.println(numarInmatriculare + ": " + marca + ", " + anFabricatie + ", " + culoare + ", " + km + " km");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
