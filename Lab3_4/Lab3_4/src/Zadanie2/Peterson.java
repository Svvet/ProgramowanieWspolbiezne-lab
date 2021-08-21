
package Zadanie2;


import java.util.Random;


public class Peterson extends Thread {
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
    Peterson(int nr, int synch, int lp, Random rand){
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
        czyjaKolej=nr2;
       
            
            while(chce[nr2]&&czyjaKolej==nr2){}
            
            
        
        System.out.println("Sekcja krytyczna wątku: Peterson-"+nr+", nr powt.= "+(i+1));
        for(int j=0;j<100;j++)System.out.print(znaki[arrnr]);
        System.out.println();
        //koniec sekcji krytycznej
        chce[arrnr]=false;
        chce=chce;
        
    }
    void dzialanieNiesynchr(){
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        System.out.println("Sekcja krytyczna wątku: Peterson-"+nr+", nr powt.= "+(i+1));
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
