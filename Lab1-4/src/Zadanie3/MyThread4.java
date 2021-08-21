
package Zadanie3;


public class MyThread4 extends Thread {
    public MyThread4(String num){
        super(num);
    }
    public void run(){
    Thread w2 = new MyThread3("3");
    w2.start();
    for(int i =0;i<5;i++){
        try{
        Thread.sleep(2000);
        System.out.println("Spałem przez 2s - "  +  Thread.currentThread().getName());
    }
    catch(InterruptedException e){
    System.out.println("Zostałem obudzony - " + Thread.currentThread().getName());
    w2.interrupt();
    try{
            w2.join();
            }
    catch(InterruptedException e2){
                System.out.println(e2.getMessage());
            }
    return;
    }
    }
    }
  
    
}
