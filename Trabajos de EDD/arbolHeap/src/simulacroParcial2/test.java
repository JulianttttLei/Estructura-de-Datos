/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacroParcial2;

import conjuntista.ArbolBB;

/**
 *
 * @author Administrador
 */
public class test {
    public static void main(String [] args){
    ArbolBB a=new ArbolBB(); 
    a.insertar(56);
    a.insertar(13);
    a.insertar(78);
    a.insertar(7);
    a.insertar(24);
    a.insertar(15);
    a.insertar(100);
        System.out.println(a.toString());
    //ArbolBB b=a.clonarInvertido(13);
        
        //System.out.println(a.listarMayorIgual(7));
        //System.out.println(a.listarMenorIgual(24));
        //System.out.println(a.clonarInvertidoPorRango(7, 24).toString());
       // System.out.println(a.frontera());
        System.out.println(a.listarRango(2, 13));
}
 // Metodos ejercicio parcial
    
/*   Implementar el método clonarParteInvertida(elem) que devuelve un nuevo
árbol que es una copia del subárbol original, cuya raíz es el elemento dado
y cada hijo está cambiado de lugar . Si el elemento no existe, el árbol que devuelve es vacío. Ejemplo: si
tiene el árbol A de la derecha y elem=13, el resultado debe ser el árbol B
    */
    //
}