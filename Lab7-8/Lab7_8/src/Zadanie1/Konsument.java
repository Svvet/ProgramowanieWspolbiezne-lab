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
    Monitor monit;

    Konsument(int id, int lp, Monitor monit) {
        this.id = id;
        this.lp = lp;
        this.monit = monit;
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
            String dana = monit.pobierz();
            konsumujDane(dana);
        }
    }
}
