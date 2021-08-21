package Zadanie1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Konsument extends Thread {

    int id;
    int lp;
    String[] buf;
    int i = 0;
    Licznik k;
    int size;
    Semaphore wolne;
    Semaphore zajete;
    Semaphore chron_k;

    Konsument(int id, int lp, String[] buf, Semaphore wolne, Semaphore zajete, Semaphore chron_k, Licznik k, int size) {
        this.id = id;
        this.lp = lp;
        this.buf = buf;
        this.wolne = wolne;
        this.zajete = zajete;
        this.chron_k = chron_k;
        this.k = k;
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

    void konsumujDane(String dana) {
        sleepRand(2, 12);
        System.out.println("[K-" + id + ", " + (i + 1) + "] >> Dana=" + dana);
    }

    public void run() {

        for (i = 0; i < lp; i++) {
            int temp;
            String dana;

            try {
                zajete.acquire();
            } catch (InterruptedException e) {
            }
            try {
                chron_k.acquire();
            } catch (InterruptedException e) {
            }
            temp = k.val;
            k.inc();

            chron_k.release();
            dana = buf[temp];
            wolne.release();
            konsumujDane(dana);
        }
    }
}/*
int temp;
        String dana;
        Random rand = new Random();

        try {
            zajete.acquire();
        } catch (InterruptedException e) {
        }
        try {
            chron_k.acquire();
        } catch (InterruptedException e) {
        }
        temp = k.val;
        k.inc();

        chron_k.release();
        dana = buf[temp];
        wolne.release();
        sleepRand(2, 12);
        System.out.println("[K-" + id + ", " + (i + 1) + "] >> Dana=" + dana + "K:" + temp);
 */
