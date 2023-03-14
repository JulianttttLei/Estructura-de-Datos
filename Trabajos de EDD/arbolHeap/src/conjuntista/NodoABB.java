/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntista;

import conjuntistas.*;

public class NodoABB {
private Comparable elem;
private NodoABB izquierdo;
private NodoABB derecho;

public NodoABB(Comparable element, NodoABB izq, NodoABB der ){
    this.elem=element;
    this.izquierdo=izq;
    this.derecho=der;
}
// observadores
public Comparable getElem(){
    return elem;
}

public NodoABB getIzquierdo(){
    return izquierdo;
}

public NodoABB getDerecho(){
    return derecho;
}

// modificadoras
public void setElem(Comparable element){
    this.elem=element;
}
 
public void setIzquierdo(NodoABB izq){
    this.izquierdo=izq;
}

public void setDerecho(NodoABB der){
    this.derecho=der;
}

}
