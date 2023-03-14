/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

public class NodoArbol {
private Object elem;
private NodoArbol izquierdo;
private NodoArbol derecho;

public NodoArbol(Object element, NodoArbol izq, NodoArbol der ){
    this.elem=element;
    this.izquierdo=izq;
    this.derecho=der;
}
// observadores
public Object getElem(){
    return elem;
}

public NodoArbol getIzquierdo(){
    return izquierdo;
}

public NodoArbol getDerecho(){
    return derecho;
}

// modificadoras
public void setElem(Object element){
    this.elem=element;
}
 
public void setIzquierdo(NodoArbol izq){
    this.izquierdo=izq;
}

public void setDerecho(NodoArbol der){
    this.derecho=der;
}

}
