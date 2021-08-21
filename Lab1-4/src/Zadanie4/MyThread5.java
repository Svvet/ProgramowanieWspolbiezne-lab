
package Zadanie4;


public class MyThread5 extends Thread {
    
    
    private Licznik licz;
 public MyThread5(String num, Licznik licz) {
     super(num);
 this.licz = licz ;
 }
public void run ( ) {
    for(int i = 0; i <  5000000;i++) {
        licz.inc();
        }
        }

 
    }

