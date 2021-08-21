package Zadanie3;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Pisarz extends Thread {

    int id;
    int lp;
    int i = 0;
    int k;
    Semaphore wolne, pis;
    Licznik lczyt, lpis;
    Licznik j;

    Pisarz(int id, int lp, int k, Semaphore wolne, Semaphore pis, Licznik lczyt,
            Licznik lpis, Licznik j) {
        this.id = id;
        this.lp = lp;
        this.wolne = wolne;
        this.pis = pis;
        this.k = k;
        this.lczyt = lczyt;
        this.lpis = lpis;
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

    void pisanie() {
        lpis.val++;
        
        System.out.println(">>> [P-" + id + ", " + (i + 1) + "] :: [" + (wolne.availablePermits()) + ", "
                + lpis.val + "]");
        sleepRand(1, 5);
        System.out.println("<<< [P-" + id + ", " + (i + 1) + "] :: [" + (wolne.availablePermits()) + ", "
                + lpis.val + "]");
        j.val=0;
        lpis.val--;
        
    }

    public void run() {
        for (i = 0; i < lp; i++) {
            sleepRand(5, 15);
            try {
                pis.acquire();
            } catch (InterruptedException e) {
            }
            for (int j = 0; j < k; j++) {
                try {
                    wolne.acquire();
                } catch (InterruptedException e) {
                }
            }
            pisanie();
            for (j.val = 0; j.val < k; j.val++) {
                wolne.release();
            }
            
            pis.release();
        }
    }
}
