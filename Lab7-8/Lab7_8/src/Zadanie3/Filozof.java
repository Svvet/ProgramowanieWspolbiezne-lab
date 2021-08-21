package Zadanie3;

import static java.lang.Thread.sleep;
import java.util.Random;

public class Filozof extends Thread {

    int nr;
    Widelce wid;
    int lp;

    Filozof(int nr, int lp, Widelce wid) {
        this.nr = nr;
        this.lp = lp;
        this.wid = wid;
    }

    void sleepRand(int a, int b) {
        Random rand = new Random();
        int res = a + rand.nextInt(b - a + 1);
        try {
            sleep(res);
        } catch (InterruptedException e) {
        }
    }

    public void run() {
        for (int i = 0; i < lp; i++) {
            sleepRand(5, 15);
            wid.wez(nr, i);

            sleepRand(1, 5);
            wid.odloz(nr, i);

        }
    }

}
/*System.out.println(">>> [F-"+nr+", "+(i+1)+"] :: ["+(wid.zajety[0]?1:0)+
                    ", "+(wid.zajety[1]?1:0)+", "+(wid.zajety[2]?1:0)+
                    ", "+(wid.zajety[3]?1:0)+", "+(wid.zajety[4]?1:0)+
                    "] - "+wid.jest);*/
