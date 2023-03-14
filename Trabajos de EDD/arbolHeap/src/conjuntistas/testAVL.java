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
public class testAVL {
    public static void main(String [] args){
        ArbolAVL v=new ArbolAVL();
        
        System.out.println("Se agrega el 75");
        v.insertar(75);
          System.out.println(v.toString());    
        System.out.println("Se agrega el 20");    
     v.insertar(20);
      System.out.println(v.toString());    
        System.out.println("Se agrega el 15");
        v.insertar(15);
        System.out.println(v.toString());
        System.out.println("Se agrega el 80");
        v.insertar(80);
        System.out.println(v.toString());
        System.out.println("Se agrega el 93");
        v.insertar(93);
        System.out.println(v.toString());
        System.out.println("Se agrega el 77");
        v.insertar(77);
        System.out.println(v.toString());
        System.out.println("Se agrega el 18");
        v.insertar(18);
        System.out.println(v.toString());
        System.out.println("Se agrega el 78");
        v.insertar(78);
        System.out.println(v.toString());
        System.out.println("Se agrega el 13");
        v.insertar(13);
        System.out.println(v.toString());
        System.out.println("Se agrega el 14");
        v.insertar(14);
        System.out.println(v.toString());
        System.out.println("Se agrega el 25");
        v.insertar(25);
        System.out.println(v.toString());
        System.out.println("Se agrega el 16");
        v.insertar(16);
        System.out.println(v.toString());
    
        System.out.println("Se elimina el 16");
        v.eliminar(16);
        System.out.println(v.toString());
       
     
     
     
                   
       
    }
    // 40 35 58 20 37 39
}
