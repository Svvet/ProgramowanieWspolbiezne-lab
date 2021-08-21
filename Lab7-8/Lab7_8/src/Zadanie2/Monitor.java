package Zadanie2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor {

    final Lock dostep = new ReentrantLock();
    final Condition czytelnicy = dostep.newCondition();
    final Condition pisarze = dostep.newCondition();

    public int licz_czyt;
    public int licz_pisz;
    public int czyt_pocz;
    public int pis_pocz;

    Monitor() {
        licz_czyt = 0;
        licz_pisz = 0;
        czyt_pocz = 0;
        pis_pocz = 0;
    }

    void wysPocz(int id, int i, String rodz) {
        System.out.println(">>>[" + rodz + "-" + id + ", " + (i + 1) + "] :: [" + licz_czyt + ", "
                + czyt_pocz + ", " + licz_pisz + ", " + pis_pocz + "]");
    }

    void wysKon(int id, int i, String rodz) {
        System.out.println("<<<[" + rodz + "-" + id + ", " + (i + 1) + "] :: [" + licz_czyt + ", "
                + czyt_pocz + ", " + licz_pisz + ", " + pis_pocz + "]");
    }

    public void pocz_czytania(int id, int i) {

        dostep.lock();

        try {
            wysPocz(id, i, "C");
            if ((licz_pisz + pis_pocz) > 0) {
                czyt_pocz++;
                try {
                    czytelnicy.await();
                } catch (InterruptedException e) {
                }
                czyt_pocz--;
            }
            licz_czyt++;
            wysKon(id, i, "C");
        } finally {
            dostep.unlock();
        }

    }

    public void kon_czytania(int id, int i) {

        dostep.lock();

        try {
            wysPocz(id, i, "C");
            licz_czyt--;
            if (licz_czyt == 0) {

                pisarze.signal();

            }
            wysKon(id, i, "C");
        } finally {
            dostep.unlock();
        }

    }

    public void pocz_pisania(int id, int i) {
        dostep.lock();

        try {
            wysPocz(id, i, "P");
            if ((licz_czyt + licz_pisz) > 0) {
                pis_pocz++;
                try {
                    pisarze.await();
                } catch (InterruptedException e) {
                }
                pis_pocz--;
            }
            licz_pisz = 1;
            wysKon(id, i, "P");
        } finally {
            dostep.unlock();
        }

    }

    public void kon_pisania(int id, int i) {
        dostep.lock();
        try {
            wysPocz(id, i, "P");
            licz_pisz = 0;
            if (czyt_pocz > 0) {
                czytelnicy.signalAll();

            } else {
                pisarze.signal();
            }
            wysKon(id, i, "P");
        } finally {
            dostep.unlock();
        }
    }
}
