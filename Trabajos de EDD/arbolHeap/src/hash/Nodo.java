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
public class Nodo {
    
    private Object elem;
    private Nodo enlace;
    
public Nodo(Object elemento, Nodo enl){
    this.elem=elemento;
    this.enlace=enl;
}
//Modificadoras
public void setElem(Object elemento){
    this.elem=elemento;
}
public void setEnlace(Nodo enl){
    this.enlace=enl;
}

//Observadoras
public Object getElem(){
    return elem;
}
public Nodo getEnlace(){
    return enlace;
}

}