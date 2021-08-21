
package Zadanie3;
import java.util.Random;

public class Lamport extends Thread {
    int nr;
    int synch;
    int lp;
    int i=0;
    
    int arrnr;
    Random rand;
    static char[] znaki=new char[]{'+','-','*','^','#'};
    static volatile boolean[] wybieranie = new boolean[]{false,false,false,false,false};
    static volatile int[] numerek = new int[]{0,0,0,0,0};
    int max(int[] arr){
        int j=0;
        int max=0;
        for(j=0;j<arr.length;j++){
            if(max<arr[j])max=arr[j];
            
        }
        return max;
    }
    Lamport(int nr, int synch, int lp, Random rand){
        this.nr=nr;
        this.synch=synch;
        this.lp=lp;
        this.rand=rand;       
        arrnr=nr-1;
    }
    void dzialanieSynchr() {
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        //koniec sekcji lokalnej
        wybieranie[arrnr]=true;
        numerek[arrnr]=max(numerek)+1;
        wybieranie[arrnr]=false;
        for(int k=0;k<5;k++){
            while(wybieranie[k]){}
            while(numerek[k]!=0&&(numerek[k]<numerek[arrnr]||(numerek[k]==
                    numerek[arrnr]&&k<arrnr))){}
            
        }
        
        
        System.out.println("Sekcja krytyczna wątku: Lamport-"+arrnr+", arrnr powt.= "+(i+1));
        for(int j=0;j<100;j++)System.out.print(znaki[arrnr]);
        System.out.println();
        //koniec sekcji krytycznej
        numerek[arrnr]=0;
        
        
    }
    void dzialanieNiesynchr(){
        int res = 1 + rand.nextInt(10 - 1 + 1);
        try{sleep(res);}
        catch(Exception e){
            
        }
        System.out.println("Sekcja krytyczna wątku: Lamport-"+nr+", nr powt.= "+(i+1));
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
