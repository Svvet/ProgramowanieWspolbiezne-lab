package Zadanie1;

public class Test {

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        int size = 10;
        int lpP = 250;
        int lpK = 200;
        int i;
        String[] buff = new String[size];
        Monitor monit = new Monitor(buff, size);
        Producent[] p = new Producent[m];
        Konsument[] k = new Konsument[n];
        for (i = 0; i < m; i++) {
            p[i] = new Producent(i, lpP, monit);
        }
        for (i = 0; i < n; i++) {
            k[i] = new Konsument(i, lpK, monit);
        }
        for (i = 0; i < m; i++) {
            p[i].start();
        }
        for (i = 0; i < n; i++) {
            k[i].start();
        }

    }
}
