
package Zadanie4;


public class Test4 {
     public static void main(String[] args){
         Licznik licz=new Licznik();
         Thread w[]=new Thread[10];
         for(int i = 0;i<10;i++){
             w[i]=new MyThread5(""+i,licz);
         }
         for(int i = 0;i<10;i++){
             w[i].start();
         }
         for (int i = 0; i<w.length;i++){
            try{
            w[i].join();
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
         System.out.println("Stan licznika = " +licz.get());
     }
}
