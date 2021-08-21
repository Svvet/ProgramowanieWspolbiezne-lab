package Zadanie1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor {

    final Lock dostep = new ReentrantLock();
    final Condition zmWar1 = dostep.newCondition();
    final Condition zmWar2 = dostep.newCondition();
    Licznik wej = new Licznik(0);
    Licznik wyj = new Licznik(0);
    Licznik licz = new Licznik(0);
    String[] buff;
    int size;

    Monitor(String[] buff, int size) {
        this.buff = buff;

        this.size = size;
    }

    public void wstaw(String el) {
        dostep.lock();
        try {
            while (licz.val == size) {
                try {
                    zmWar1.await();
                } catch (InterruptedException e) {
                }
            }
            buff[wej.val] = el;
            wej.val = (wej.val + 1) % size;
            licz.val++;
            zmWar2.signal();

        } finally {
            dostep.unlock();
        }
    }

    public String pobierz() {
        String el;
        dostep.lock();
        try {
            while (licz.val == 0) {
                try {
                    zmWar2.await();
                } catch (InterruptedException e) {
                }
            }
            el = buff[wyj.val];
            wyj.val = (wyj.val + 1) % size;

            licz.val--;
            zmWar1.signal();

        } finally {
            dostep.unlock();
        }
        return el;
    }
}
