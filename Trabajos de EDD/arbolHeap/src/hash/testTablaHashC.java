/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

/**
 *
 * @author agust
 */
public class testTablaHashC {
    public static void main(String [] args){
       TablaHashC m=new TablaHashC(10);
        System.out.println(m.insertar(83));
         System.out.println(m.insertar(45));
         System.out.println( m.insertar(376));
           System.out.println(m.insertar(25));
           System.out.println(m.insertar(85));
           System.out.println(m.insertar(35));
           System.out.println(m.listar());
           System.out.println("eleimina: "+m.eliminar(25));
           
          
         System.out.println("Pertenece: "+m.pertenece(35));
        
         System.out.println("veo: "+m.listar());
           
           
    }
}
