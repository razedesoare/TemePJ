package ex1;

public class Pereche {
    private int nr1;
    private int nr2;

    public Pereche(int nr1, int nr2) {
        this.nr1 = nr1;
        this.nr2 = nr2;
    }
    public Pereche() {

    }

    public int getNr1() {
        return nr1;
    }

    public void setNr1(int nr1) {
        this.nr1 = nr1;
    }

    public int getNr2() {
        return nr2;
    }

    public void setNr2(int nr2) {
        this.nr2 = nr2;
    }
    @Override
    public String toString() {
        return "numar1=" + nr1 + ", numar2=" + nr2;
    }
    public boolean suntConsecutiveFibonacci() {
        int a = 0, b = 1;
        while (b <= nr2) {
            if (a == nr1 && b == nr2) {
                return true;
            }
            int temp = a;
            a = b;
            b = temp + b;
        }
        return false;
    }
    public int cmmdc(int a, int b) {
        int x = nr1;
        int y = nr2;

        while (x != 0 && y != 0)
            if (x > y)
                x = x - y;
            else
                y = y - x;

        return x;
    }
    public int cmmmc() {
        return (nr1 * nr2) / cmmdc(nr1, nr2);
    }

    public boolean auSumaCifrelorEgala() {
        return sumaCifrelor(nr1) == sumaCifrelor(nr2);
    }

    private int sumaCifrelor(int numar) {
        int suma = 0;
        while (numar != 0) {
            suma += numar % 10;
            numar /= 10;
        }
        return suma;
    }

    public boolean auCifrePareEgale() {
        return numarCifrePare(nr1) == numarCifrePare(nr2);
    }

    private int numarCifrePare(int numar) {
        int count = 0;
        while (numar != 0) {
            if ((numar % 10) % 2 == 0) {
                count++;
            }
            numar /= 10;
        }
        return count;
    }


}
