
package Zadanie2;

import java.util.Random;

public class Test {
    public static void main(String[] args){
    Random rand=new Random();
    Peterson w1=new Peterson(1,1,1000,rand);
    Peterson w2=new Peterson(2,1,1000,rand);
    w1.start();
    w2.start();
    }
}
