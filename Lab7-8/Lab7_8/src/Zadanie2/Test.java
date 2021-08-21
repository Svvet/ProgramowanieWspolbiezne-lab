package Zadanie2;

public class Test {

    public static void main(String[] args) {
        Monitor monit = new Monitor();
        int i = 0;
        int n = 2;
        int m = 4;
        int lp = 100;
        Czytelnik[] cz = new Czytelnik[m];
        Pisarz[] p = new Pisarz[n];
        for (i = 0; i < m; i++) {
            cz[i] = new Czytelnik(monit, i, lp);
        }
        for (i = 0; i < n; i++) {
            p[i] = new Pisarz(monit, i, lp);
        }
        for (i = 0; i < m; i++) {
            cz[i].start();
        }
        for (i = 0; i < n; i++) {
            p[i].start();
        }

    }
}
