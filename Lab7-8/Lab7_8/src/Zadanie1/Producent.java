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
    Monitor monit;

    Producent(int id, int lp, Monitor monit) {
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
            String dana = produkujDane();
            monit.wstaw(dana);
        }
    }
}
