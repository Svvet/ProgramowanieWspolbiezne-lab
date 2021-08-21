package Zadanie3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Widelce {

    final Lock dostep = new ReentrantLock();
    final Condition[] widelec = new Condition[5];
    final Condition lokaj = dostep.newCondition();
    public boolean[] zajety = new boolean[5];
    public int jest;

    Widelce() {
        for (int i = 0; i < 5; i++) {
            widelec[i] = dostep.newCondition();
            zajety[i] = false;
        }
        jest = 0;
    }

    void wysP(int nr, int i) {
        System.out.println(">>> [F-" + nr + ", " + (i + 1) + "] :: [" + (zajety[0] ? 1 : 0)
                + ", " + (zajety[1] ? 1 : 0) + ", " + (zajety[2] ? 1 : 0)
                + ", " + (zajety[3] ? 1 : 0) + ", " + (zajety[4] ? 1 : 0)
                + "] - " + jest);
    }

    void wysK(int nr, int i) {
        System.out.println("<<< [F-" + nr + ", " + (i + 1) + "] :: [" + (zajety[0] ? 1 : 0)
                + ", " + (zajety[1] ? 1 : 0) + ", " + (zajety[2] ? 1 : 0)
                + ", " + (zajety[3] ? 1 : 0) + ", " + (zajety[4] ? 1 : 0)
                + "] - " + jest);
    }

    public void wez(int i, int j) {
        dostep.lock();
        try {
            wysP(i, j);
            if (jest == 4) {
                try {
                    lokaj.await();
                } catch (InterruptedException e) {
                }
            }
            jest++;
            if (zajety[i]) {
                try {
                    widelec[i].await();
                } catch (InterruptedException e) {
                }
            }
            zajety[i] = true;
            if (zajety[(i + 1) % 5]) {
                try {
                    widelec[(i + 1) % 5].await();
                } catch (InterruptedException e) {
                }
            }
            zajety[(i + 1) % 5] = true;
            wysK(i, j);
        } finally {
            dostep.unlock();
        }
    }

    public void odloz(int i, int j) {
        dostep.lock();
        try {
            wysP(i, j);
            zajety[i] = false;
            widelec[i].signal();
            zajety[(i + 1) % 5] = false;
            widelec[(i + 1) % 5].signal();
            jest--;
            lokaj.signal();
            wysK(i, j);
        } finally {
            dostep.unlock();
        }
    }

}
