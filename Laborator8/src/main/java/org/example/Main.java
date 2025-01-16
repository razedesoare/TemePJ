package org.example;

import java.sql.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        String url = "jdbc:mysql://localhost:3306/lab8";
        String user = "root";
        String parola = "parolasql";

        while (running) {

            System.out.println("1.adauga persoana");
            System.out.println("2.adauga excursie");
            System.out.println("3.afisare");
            System.out.println("4.afisare excursii dupa persoana");
            System.out.println("5.afisare persoana dupa excursie");
            System.out.println("6.afisare persoana dupa an");
            System.out.println("7.stergere excursie");
            System.out.println("8.stergere persoana si excursie");
            System.out.println("0.iesire");
            System.out.println("opt:");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1:
                    adaugaPersoana(scanner, url, user, parola);
                    break;
                case 2:
                    adaugaExcursie(scanner, url, user, parola);
                    break;
                case 3:
                    afiseazaPersoaneSiExcursii(url, user, parola);
                    break;
                case 4:
                    afiseazaExcursiiPersoana(scanner, url, user, parola);
                    break;
                case 5:
                    afiseazaPersoaneDupaDestinatie(scanner, url, user, parola);
                    break;
                case 6:
                    afiseazaPersoaneDupaAn(scanner, url, user, parola);
                    break;
                case 7:
                    stergeExcursie(scanner, url, user, parola);
                    break;
                case 8:
                    stergePersoana(scanner, url, user, parola);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("ceva nu e ok");
            }
        }
    }

    public static Connection getConnection(String url, String user, String parola) throws SQLException {
        return DriverManager.getConnection(url, user, parola);
    }

    public static void adaugaPersoana(Scanner scanner, String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            System.out.print("nume: ");
            String nume = scanner.nextLine();

            int varsta = -1;
            while (varsta < 0 || varsta > 120) {
                System.out.print("varsta: ");
                try {
                    varsta = Integer.parseInt(scanner.nextLine());
                    if (varsta < 0 || varsta > 120) {
                        throw new ExceptieVarsta("nu e ok varsta");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("nu e ok varsta");
                } catch (ExceptieVarsta e) {
                    System.out.println(e.getMessage());
                }
            }

            String query = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, nume);
                ps.setInt(2, varsta);
                ps.executeUpdate();
                System.out.println("ok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adaugaExcursie(Scanner scanner, String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            System.out.print("id persoana ");
            int idPersoana = scanner.nextInt();
            scanner.nextLine();

            String checkPersoanaQuery = "SELECT * FROM persoane WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkPersoanaQuery)) {
                ps.setInt(1, idPersoana);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("nu e bun id");
                    return;
                }
            }

            System.out.print("destinatia: ");
            String destinatia = scanner.nextLine();

            int anExcursie = -1;
            while (anExcursie < 1900 || anExcursie > 2025) {
                System.out.print("an: ");
                try {
                    anExcursie = Integer.parseInt(scanner.nextLine());
                    if (anExcursie < 1900 || anExcursie > 2025) {
                        throw new ExceptieAnExcursie("nu e ok anu");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("nu e ok anu");
                } catch (ExceptieAnExcursie e) {
                    System.out.println(e.getMessage());
                }
            }

            String insertExcursieQuery = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertExcursieQuery)) {
                ps.setInt(1, idPersoana);
                ps.setString(2, destinatia);
                ps.setInt(3, anExcursie);
                ps.executeUpdate();
                System.out.println("ok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afiseazaPersoaneSiExcursii(String url, String user, String parola) {
        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT p.id, p.nume, p.varsta, e.destinatia, e.anul " +
                    "FROM persoane p LEFT JOIN excursii e ON p.id = e.id_persoana";
            try (Statement ps = conn.createStatement()) {
                ResultSet rs = ps.executeQuery(query);
                while (rs.next()) {
                    String nume = rs.getString("nume");
                    int varsta = rs.getInt("varsta");
                    String destinatia = rs.getString("destinatia");
                    int anExcursie = rs.getInt("anul");

                    System.out.println("persoana: " + nume + ", varsta: " + varsta);
                    if (destinatia != null) {
                        System.out.println("excursie: " + destinatia + ", an: " + anExcursie);
                    } else {
                        System.out.println(" nu are excursii");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afiseazaExcursiiPersoana(Scanner scanner, String url, String user, String parola) {
        System.out.print("nume persoana: ");
        String nume = scanner.nextLine();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT e.destinatia, e.anul FROM excursii e " +
                    "JOIN persoane p ON e.id_persoana = p.id WHERE p.nume = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, nume);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("nu exista ");
                    return;
                }
                System.out.println("excursii pentru " + nume + ":");
                do {
                    String destinatia = rs.getString("destinatia");
                    int anExcursie = rs.getInt("anul");
                    System.out.println(" - " + destinatia + ", anul: " + anExcursie);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afiseazaPersoaneDupaDestinatie(Scanner scanner, String url, String user, String parola) {
        System.out.print("destinatia: ");
        String destinatie = scanner.nextLine();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT p.nume FROM persoane p " +
                    "JOIN excursii e ON p.id = e.id_persoana WHERE e.destinatia = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, destinatie);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("un avem persoana la destinata aia.");
                    return;
                }
                System.out.println("cine a vizitat este" + destinatie + ":");
                do {
                    String nume = rs.getString("nume");
                    System.out.println(" - " + nume);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afiseazaPersoaneDupaAn(Scanner scanner, String url, String user, String parola) {
        System.out.print("an: ");
        int an = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "SELECT p.nume FROM persoane p " +
                    "JOIN excursii e ON p.id = e.id_persoana WHERE e.anul = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, an);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("nu exista persoane cu anu ala");
                    return;
                }
                System.out.println("persoane in anul " + an + ":");
                do {
                    String nume = rs.getString("nume");
                    System.out.println(" - " + nume);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void stergeExcursie(Scanner scanner, String url, String user, String parola) {
        System.out.print("id pentru stergere: ");
        int idExcursie = scanner.nextInt();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "DELETE FROM excursii WHERE id_excursie = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idExcursie);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("sters.");
                } else {
                    System.out.println("nu exista");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void stergePersoana(Scanner scanner, String url, String user, String parola) {
        System.out.print("id persoana: ");
        int idPersoana = scanner.nextInt();

        try (Connection conn = getConnection(url, user, parola)) {
            String query = "DELETE FROM persoane WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idPersoana);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("ai sters");
                } else {
                    System.out.println("nu exista");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
