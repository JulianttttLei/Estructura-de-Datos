/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquica;

/**
 *
 * @author Administrador
 */
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    public NodoGen(Object elem,NodoGen hijoIzq,NodoGen herDer){
        this.elem=elem;
        this.hijoIzquierdo=hijoIzq;
        this.hermanoDerecho=herDer;
        
        
}
    // observadoras
    public Object getElem(){
        return elem;
    }
    public NodoGen getHijoIzquierdo(){
        return hijoIzquierdo;
    }
    public NodoGen getHermanoDerecho(){
        return hermanoDerecho;
    }
    
    //modificadoras
    public void setElem(Object elem){
        this.elem=elem;
    }
    public void setHijoIzquierdo(NodoGen hijoI){
        this.hijoIzquierdo=hijoI;
    }
    public void setHermanoDerecho(NodoGen herDer){
        this.hermanoDerecho=herDer;
    }
    
    
}
