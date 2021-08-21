package Zadanie3;

import java.util.concurrent.Semaphore;

public class Test {

    public static void main(String[] args) {
        int m = 5;
        int n = 2;
        int k = 3;
        int i;
        int lpC = 100;
        int lpP = 100;
        Licznik lczyt = new Licznik(0);
        Licznik lpis = new Licznik(0);
        Licznik j=new Licznik(0);

        Semaphore wolne = new Semaphore(k);
        Semaphore pis = new Semaphore(1);
        Czytelnik[] cz = new Czytelnik[m];
        Pisarz[] pi = new Pisarz[n];
        for (i = 0; i < m; i++) {
            cz[i] = new Czytelnik(i + 1, lpC, k, wolne, pis, lczyt, lpis,j);
        }
        for (i = 0; i < n; i++) {
            pi[i] = new Pisarz(i + 1, lpP, k, wolne, pis, lczyt, lpis,j);
        }
        for (i = 0; i < m; i++) {
            cz[i].start();
        }
        for (i = 0; i < n; i++) {
            pi[i].start();
        }

    }
}
