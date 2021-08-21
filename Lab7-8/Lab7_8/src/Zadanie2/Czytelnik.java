package Zadanie2;

import static java.lang.Thread.sleep;
import java.util.Random;

public class Czytelnik extends Thread {

    int id;
    Monitor monit;
    int i = 0;
    int lp;

    Czytelnik(Monitor monit, int id, int lp) {
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

    void czytanie() {

        sleepRand(1, 5);

    }

    public void run() {
        for (i = 0; i < lp; i++) {
            sleepRand(5, 15);
            monit.pocz_czytania(id, i);

            czytanie();
            monit.kon_czytania(id, i);

        }
    }
}
/*System.out.println(">>>[C-" + id + ", " + (i+1) + "] :: [" + monit.licz_czyt + ", "
                + monit.czyt_pocz + ", " + monit.licz_pisz + ", " + monit.pis_pocz + "]");*/
