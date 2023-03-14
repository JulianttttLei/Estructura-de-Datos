/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosLineales;

import dinamicas.Cola;
import dinamicas.Lista;
import dinamicas.Pila;

/**
 *
 * @author Administrador
 */
public class listaToCola {
    public static void main(String [] args){
        Lista ls=new Lista();
        ls.insertar('a', 1);
         ls.insertar('b', 2);
          ls.insertar('c', 3);
           ls.insertar('d', 4);
            ls.insertar('e', 5);
             ls.insertar('f', 6);
              ls.insertar('g', 7);
               ls.insertar('h', 8);
                ls.insertar('i', 9);
                ls.insertar('j', 10); 
               Object elem= ls.recuperar(1);
               System.out.println("es: "+elem);
                System.out.println("Lista original: "+ls.toString());
                System.out.println("Cola: "+listaToCola1(ls,4));
    }
    public static Cola listaToCola1(Lista ls, int t){
       Pila pila1=new Pila();
       Pila pila2=new Pila();
       Lista clonLista=ls.clone();
       int p=0;
       Object elemento;
       Cola cola=new Cola();
       while(!clonLista.esVacia()){
           if(p%t!=0 ||p==0){
           elemento=clonLista.recuperar(1);
           cola.poner(elemento);
           pila1.apilar(elemento);
           pila2.apilar(elemento);
           p++;
           clonLista.eliminar(1);
           }else{ 
               if(p%t==0){
               while(!pila1.esVacia()){
                   cola.poner(pila1.obtenerTope());
                   pila1.desapilar();
               }
               while(!pila2.esVacia()){
                   cola.poner(pila2.obtenerTope());
                   pila2.desapilar();
               }
               cola.poner('&');
               cola.poner('&');
               p=0;
           }
           }
       }
       // si se termina la el clonLista guada los restantes elementos en la cola, que sera menor a t
           while(!pila1.esVacia()){
                   cola.poner(pila1.obtenerTope());
                   pila1.desapilar();
               }
               while(!pila2.esVacia()){
                   cola.poner(pila2.obtenerTope());
                   pila2.desapilar();
               }
           
       
       
       return cola;
    }
}
