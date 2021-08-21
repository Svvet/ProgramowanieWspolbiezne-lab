package Zadanie3;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Czytelnik extends Thread {

    int id;
    int lp;
    int i = 0;
    int k;
    Semaphore wolne, pis;
    Licznik lczyt,lpis;
    Licznik j;

    Czytelnik(int id, int lp, int k, Semaphore wolne, Semaphore pis,
            Licznik lczyt, Licznik lpis,Licznik j) {
        this.id = id;
        this.lp = lp;
        this.wolne = wolne;
        this.pis = pis;
        this.k = k;
        this.lczyt=lczyt;
        this.lpis=lpis;
        this.j=j;
    }

    void sleepRand(int a, int b) {
        Random rand = new Random();
        int res = a + rand.nextInt(b - a + 1);
        try {
            sleep(res);
        } catch (InterruptedException e) {
        }
    }

    void czytanie() {
        
        System.out.println(">>> [C-" + id + ", " + (i + 1) + "] :: [" + (k-wolne.availablePermits()) + ", "
                + lpis.val + "]");
        sleepRand(1, 5);
        
        System.out.println("<<< [C-" + id + ", " + (i+1) + "] :: [" + (k-wolne.availablePermits()) + ", "
                + lpis.val + "]");
        
    }

    public void run() {
        for (i = 0; i < lp; i++) {
            sleepRand(5, 15);
            try {
                wolne.acquire();
            } catch (InterruptedException e) {
            }
            
            czytanie();
            
            wolne.release();
            
        }
    }
}
