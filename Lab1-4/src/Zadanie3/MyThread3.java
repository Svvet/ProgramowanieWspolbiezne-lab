
package Zadanie3;


public class MyThread3 extends Thread {
    public MyThread3(String num){
        super(num);
    }
    public void run(){
        for(int i=0;i<10;i++){
    try{
        Thread.sleep(1000);
        System.out.println("Spałem przez 1s - "  +  Thread.currentThread().getName());
    }
    catch(InterruptedException e){
    System.out.println("Zostałem obudzony - " + Thread.currentThread().getName());
    return;
    }
        }
    }
}
