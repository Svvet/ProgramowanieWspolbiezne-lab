package Zadanie2;

import java.util.concurrent.Semaphore;

public class Test {

    public static void main(String[] args) {
        int m = 4;
        int n = 2;
        int lpC = 100;
        int lpP = 100;
        int i;
        Licznik ac = new Licznik(0);
        Licznik ap = new Licznik(0);
        Licznik dc = new Licznik(0);
        Licznik dp = new Licznik(0);
        Semaphore czyt = new Semaphore(0);
        Semaphore pis = new Semaphore(0);
        Semaphore chron = new Semaphore(1);
        Semaphore mutex_pis = new Semaphore(1);
        Czytelnik[] cz = new Czytelnik[m];
        Pisarz[] pi = new Pisarz[n];
        
        for (i = 0; i < m; i++) {
            cz[i] = new Czytelnik(ac, ap, dc, dp, i + 1, lpC, czyt, pis, chron,mutex_pis);
        }
        for (i = 0; i < n; i++) {
            pi[i] = new Pisarz(ac, ap, dc, dp, i + 1, lpP, czyt, pis, chron, mutex_pis);
        }
        for (i = 0; i < m; i++) {
            cz[i].start();
        }
        for (i = 0; i < n; i++) {
            pi[i].start();
        }
    }
}
