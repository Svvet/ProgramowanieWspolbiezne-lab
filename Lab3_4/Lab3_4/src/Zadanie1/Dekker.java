
package Zadanie1;

import java.util.Random;
public class Dekker extends Thread {
    int nr;
    int synch;
    int lp;
    int i=0;
    int nr2;
    int arrnr;
    Random rand;
    static char[] znaki=new char[]{'+','-'};
    static volatile boolean[] chce = new boolean[]{false,false};
    static volatile int czyjaKolej=1;
    Dekker(int nr, int synch, int lp, Random rand){
        this.nr=nr;
        this.synch=synch;
        this.lp=lp;
        this.rand=rand;
        if(nr==1)nr2=1;
        else nr2=0;
        arrnr=nr-1;
    }
    void dzialanieSynchr() {
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        //koniec sekcji lokalnej
        chce[arrnr]=true;
        chce=chce; //wymuszanie volatile na elementach tablicy
        while (chce[nr2]){
            if(czyjaKolej==nr2){
            chce[arrnr]=false;
            chce=chce;
            while(czyjaKolej==nr2){}
            chce[arrnr]=true;
            chce=chce;
            }
        }
        System.out.println("Sekcja krytyczna wątku: Dekker-"+nr+", nr powt.= "+(i+1));
        for(int j=0;j<100;j++)System.out.print(znaki[arrnr]);
        System.out.println();
        //koniec sekcji krytycznej
        chce[arrnr]=false;
        chce=chce;
        czyjaKolej=nr2;
    }
    void dzialanieNiesynchr(){
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        System.out.println("Sekcja krytyczna wątku: Dekker-"+nr+", nr powt.= "+(i+1));
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
