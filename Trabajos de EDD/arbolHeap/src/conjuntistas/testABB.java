/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author agust
 */
public class testABB {
    public static void main(String[] args){
        ArbolBBModificado abb= new ArbolBBModificado();
        abb.insertar(32);
        abb.insertar(9);
        abb.insertar(56);
        abb.insertar(5);
        abb.insertar(19);
        abb.insertar(43);
        abb.insertar(72);
        abb.insertar(1);
        abb.insertar(6);
        abb.insertar(17);
        abb.insertar(23);
        abb.insertar(41);
        abb.insertar(53);
        abb.insertar(64);
        abb.insertar(80);
        System.out.println(abb.toString());
      
        System.out.println(  abb.listarRango(1, 53));
    }
}
