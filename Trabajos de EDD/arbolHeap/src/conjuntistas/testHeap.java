/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author Administrador
 */

     public class testHeap {

    
    public static void main(String[] args){
        Comparable w,r,m,i,n;
        w=5;
        r=9;
        m=1;i=0;
        n=0;
        System.out.println("Comparamos");
        if(w.compareTo(r)<0){
            System.out.println("5 es menor que 9");
            System.out.println(w.compareTo(r));
        }
        if(r.compareTo(m)>0){
            System.out.println("es mayor");
            System.out.println(r.compareTo(m));
        }
       if(i.compareTo(n)==0){
           System.out.println("Son iguales");
           System.out.println(i.compareTo(n));
       }
 }

}