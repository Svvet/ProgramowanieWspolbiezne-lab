
package Zadanie4;


import java.util.Random;
import java.util.concurrent.Semaphore;


public class Sem extends Thread {
    int nr;
    int synch;
    int lp;
    int i=0;
    int nr2;
    int arrnr;
    Semaphore sem;
    Random rand;
    static char[] znaki=new char[]{'+','-','*','^','#'};
    
    Sem(int nr, int synch, int lp, Random rand, Semaphore sem){
        this.nr=nr;
        this.synch=synch;
        this.lp=lp;
        this.rand=rand;
        this.sem=sem;
        arrnr=nr-1;
    }
    void dzialanieSynchr() {
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        //koniec sekcji lokalnej
        try{
        sem.acquire();
        }
        catch(InterruptedException e){}
   
        System.out.println("Sekcja krytyczna wątku: Sem-"+nr+", nr powt.= "+(i+1));
        for(int j=0;j<100;j++)System.out.print(znaki[arrnr]);
        System.out.println();
        //koniec sekcji krytycznej
        sem.release();
        
    }
    void dzialanieNiesynchr(){
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        System.out.println("Sekcja krytyczna wątku: Sem-"+nr+", nr powt.= "+(i+1));
        for(int j=0;j<100;j++)System.out.print(znaki[arrnr]);
        
    }
    public void run(){
        if(synch==1){
        for(i=0;i<lp;i++){
            dzialanieSynchr();
        }}
        else {for( i=0;i<lp;i++){
            dzialanieNiesynchr();
        }}
    }
}
