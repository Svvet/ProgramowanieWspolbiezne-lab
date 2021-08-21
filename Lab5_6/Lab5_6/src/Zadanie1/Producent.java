package Zadanie1;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producent extends Thread {

    int id;
    int lp;
    String[] buf;
    int i = 0;
    int size;
    Licznik j;
    Semaphore wolne;
    Semaphore zajete;
    Semaphore chron_j;

    Producent(int id, int lp, String[] buf, Semaphore wolne, Semaphore zajete, Semaphore chron_j, Licznik j, int size) {
        this.id = id;
        this.lp = lp;
        this.buf = buf;
        this.wolne = wolne;
        this.zajete = zajete;
        this.chron_j = chron_j;
        this.j = j;
        this.size = size;
    }

    void sleepRand(int a, int b) {
        Random rand = new Random();
        int res = a + rand.nextInt(b - a + 1);
        try {
            sleep(res);
        } catch (InterruptedException e) {
        }
    }

    String produkujDane() {
        sleepRand(1, 10);
        String dana;
        Random rand = new Random();
        int res = rand.nextInt(100);
        dana = "[P-" + id + ", " + (i + 1) + ", " + res + "]";
        return dana;
    }

    public void run() {
        for (i = 0; i < lp; i++) {
            
        int temp;
        String dana;
        dana=produkujDane();
        try {
            wolne.acquire();
        } catch (InterruptedException e) {
        }
        try {
            chron_j.acquire();
        } catch (InterruptedException e) {
        }
        temp = j.val;
        j.inc();
        chron_j.release();
        buf[temp] = dana;
        zajete.release();
        }
    }
}
