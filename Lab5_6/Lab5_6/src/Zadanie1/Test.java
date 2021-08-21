package Zadanie1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Test {

    public static void main(String[] args) {
        //100 powtorzen co najmniej
        int m = 4;
        int n = 5;
        int size = 10;
        int lpP = 250;
        int lpK = 200;
        Licznik j = new Licznik(0, size);
        Licznik k = new Licznik(0, size);
        String[] buff = new String[size];
        Producent[] pr = new Producent[m];
        Konsument[] ko = new Konsument[n];
        Semaphore wolne = new Semaphore(size);
        Semaphore zajete = new Semaphore(0);
        Semaphore chron_j = new Semaphore(1);
        Semaphore chron_k = new Semaphore(1);

        int i;
        for (i = 0; i < m; i++) {
            pr[i] = new Producent(i + 1, lpP, buff, wolne, zajete, chron_j, j, size);
        }
        for (i = 0; i < n; i++) {
            ko[i] = new Konsument(i + 1, lpK, buff, wolne, zajete, chron_k, k, size);
        }
        for (i = 0; i < m; i++) {
            pr[i].start();
        }
        for (i = 0; i < n; i++) {
            ko[i].start();
        }

    }
}
