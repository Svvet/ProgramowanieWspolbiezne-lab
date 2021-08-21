package Zadanie1;

public class Licznik {

    public int val;
    int size;

    public void inc() {
        val = (val + 1) % size;
    }

    Licznik(int start, int size) {
        val = start;
        this.size = size;
    }
}
