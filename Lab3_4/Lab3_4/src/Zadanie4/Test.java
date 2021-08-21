/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zadanie4;


import java.util.Random;
import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args){
    
    Random rand=new Random();
    int synch=0;
    Semaphore sem = new Semaphore(1);
    Sem w1=new Sem(1,synch,100,rand,sem);
    Sem w2=new Sem(2,synch,100,rand,sem);
    Sem w3=new Sem(3,synch,100,rand,sem);
    Sem w4=new Sem(4,synch,100,rand,sem);
    Sem w5=new Sem(5,synch,100,rand,sem);
    w1.start();
    w2.start();
    w3.start();
    w4.start();
    w5.start();
}
}
