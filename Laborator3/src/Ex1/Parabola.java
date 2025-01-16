package Ex1;

public class Parabola {
    private int a;
    private int b;
    private int c;


    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public int[] varf() {
        int n = -b / (2 * a);
        int m = (a * n * n) + (b * n) + c;
        return new int[]{n, m};
    }

    public int[] mijloc(Parabola p) {
        int[] vf1 = this.varf();
        int[] vf2 = p.varf();

        int[] mij = new int[2];
        mij[0] = (vf1[0] + vf2[0]) / 2;
        mij[1] = (vf1[1] + vf2[1]) / 2;
        return mij;
    }

    public double distanta(Parabola p) {
        int[] vf1 = this.varf();
        int[] vf2 = p.varf();
        return Math.hypot(vf1[0] - vf2[0], vf1[1] - vf2[1]);
    }

    @Override
    public String toString() {
        return "Parabola{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

}
