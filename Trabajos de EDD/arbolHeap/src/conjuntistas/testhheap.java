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
public class testhheap {
    public static void main(String[] args){
    HeapMinimo h=new HeapMinimo();
    h.insertar(10);
     h.insertar(12);
      h.insertar(15);
       h.insertar(21);
        h.insertar(45);
         h.insertar(19);
        System.out.println(h.toString());
        System.out.println("-------------------------------");
        HeapMaximo b=new HeapMaximo();
        b.insertar(1);
 
        
            b.insertar(100);
            b.insertar(19); 
            b.insertar(36);
            b.insertar(17);
            
            b.insertar(3);
            b.insertar(25);
            b.insertar(1);
            b.insertar(2);
            b.insertar(7);
            System.out.println(b.toString());
}
}
