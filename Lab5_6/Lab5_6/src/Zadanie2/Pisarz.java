package Zadanie2;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Pisarz extends Thread {

    Licznik ac, ap, dc, dp;
    Semaphore czyt, pis, chron, mutex_pis;
    int lp;
    int id;
    int i = 0;

    Pisarz(Licznik ac, Licznik ap, Licznik dc, Licznik dp, int id, int lp,
            Semaphore czyt, Semaphore pis, Semaphore chron, Semaphore mutex_pis) {
        this.ac = ac;
        this.ap = ap;
        this.dc = dc;
        this.dp = dp;
        this.czyt = czyt;
        this.pis = pis;
        this.chron = chron;
        this.mutex_pis = mutex_pis;
        this.id = id;
        this.lp = lp;
        

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
        System.out.println(">>>[P-" + id + ", " + i + "] :: [" + dc.val + ", "
                + (ac.val-dc.val) + ", " + ((mutex_pis.availablePermits()+1)%2) + ", " + (ap.val-((mutex_pis.availablePermits()+1)%2)) + "]");
        sleepRand(1, 5);
        
        System.out.println("<<<[P-" + id + ", " + i + "] :: [" + dc.val + ", "
                + (ac.val-dc.val) + ", " + ((mutex_pis.availablePermits()+1)%2) + ", " + (ap.val-((mutex_pis.availablePermits()+1)%2)) + "]");
        
    }

    public void run() {
        for (i = 0; i < lp; i++) {
            sleepRand(5, 15);
            try {
                chron.acquire();
            } catch (InterruptedException e) {
            }
            ap.val++;
            if (ac.val == 0) {
                dp.val++;
                pis.release();
            }
            chron.release();
            try {
                pis.acquire();
            } catch (InterruptedException e) {
            }
            try {
                mutex_pis.acquire();
            } catch (InterruptedException e) {
            }
            pisanie();
            mutex_pis.release();
            try {
                chron.acquire();
            } catch (InterruptedException e) {
            }
            dp.val--;
            ap.val--;
            if (dp.val == 0) {
                while (dc.val < ac.val) {
                    dc.val++;
                    czyt.release();
                }
            }
            chron.release();
        }
    }
}
