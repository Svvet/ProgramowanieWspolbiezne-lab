package Zadanie3;

public class Test {

    public static void main(String[] args) {
        int lp = 50;
        Widelce wid = new Widelce();
        Filozof[] fil = new Filozof[5];
        int i;
        for (i = 0; i < 5; i++) {
            fil[i] = new Filozof(i, lp, wid);
        }
        for (i = 0; i < 5; i++) {
            fil[i].start();
        }

    }
}
