/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinamicas;

/**
 *
 * @author agust
 */
public class prueba {
    public static void main(String [] args){
        Cola cola= new Cola();
        Pila p=new Pila();
        p.apilar(1);
         p.apilar(2);
         p.apilar(3);
         p.apilar(4);
         p.apilar(5);
         System.out.println("es: "+p.toString());
         System.out.println("Tope: "+p.obtenerTope());
         System.out.println("Saco algo: "+p.desapilar());
         System.out.println(p.toString());
        cola.poner(1);
        cola.poner(2);
        cola.poner(3);
        cola.poner(4);
        cola.clone();
        System.out.println(cola.toString());
    }
}
