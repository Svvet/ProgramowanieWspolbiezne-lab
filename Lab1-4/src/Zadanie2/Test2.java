/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zadanie2;


public class Test2 {
    public static void main(String[] args){
        Thread[] w = new Thread[10];
        for (int i = 0; i<w.length;i++){
           MyThread2 t = new MyThread2();
           w[i] = new Thread(t, ""+i);
        }
        for (int i = 0; i<w.length;i++){
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
        System.out.println("Koniec");
    }
}
