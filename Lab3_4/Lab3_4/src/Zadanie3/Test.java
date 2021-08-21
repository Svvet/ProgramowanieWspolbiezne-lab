
package Zadanie3;
import java.util.Random;
public class Test {
    public static void main(String[] args){
    Random rand=new Random();
    int synch=0;
    Lamport w1=new Lamport(1,synch,100,rand);
    Lamport w2=new Lamport(2,synch,100,rand);
    Lamport w3=new Lamport(3,synch,100,rand);
    Lamport w4=new Lamport(4,synch,100,rand);
    Lamport w5=new Lamport(5,synch,100,rand);
    w1.start();
    w2.start();
    w3.start();
    w4.start();
    w5.start();
}
}
