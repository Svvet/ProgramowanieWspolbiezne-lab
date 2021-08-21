
package Zadanie1;

import java.util.Random;
public class Test {
    //1000 powtorzen
    public static void main(String[] args){
    Random rand=new Random();
    Dekker w1=new Dekker(1,1,1000,rand);
    Dekker w2=new Dekker(2,1,1000,rand);
    w1.start();
    w2.start();
    /*try{w1.join();}
    catch(Exception e){}
    try{w2.join();}
    catch(Exception e){}*/
    }
}
