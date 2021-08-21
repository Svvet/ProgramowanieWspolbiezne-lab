package Zadanie2;

import static java.lang.Thread.sleep;
import java.util.Random;

public class Pisarz extends Thread {

    int id;
    int lp;
    Monitor monit;
    int i = 0;

    Pisarz(Monitor monit, int id, int lp) {
        this.id = id;
        this.monit = monit;
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

        sleepRand(1, 5);

    }

    public void run() {
        for (i = 0; i < lp; i++) {
            sleepRand(5, 15);
            monit.pocz_pisania(id, i);

            pisanie();
            monit.kon_pisania(id, i);

        }
    }
}
