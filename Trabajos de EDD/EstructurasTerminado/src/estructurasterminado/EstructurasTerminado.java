/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasterminado;

import dinamicas.Cola;
import dinamicas.Lista;
import jerarquica.ArbolGen;

/**
 *
 * @author Administrador
 */
public class EstructurasTerminado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ArbolGen c=new ArbolGen();
       c.insertar(1, 0);
       c.insertar(2, 1);
       c.insertar(3, 1);
       c.insertar(4, 1);
       
        System.out.println("es: "+c.pertenece(0));
        
    }
    
}
