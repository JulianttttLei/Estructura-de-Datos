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
public  class NodoAVL {
    private Comparable elem;
    private int altura;
    private  NodoAVL izquierdo;
    private NodoAVL derecho;
    
    public NodoAVL(Comparable elemento,NodoAVL izq,NodoAVL der){
        this.elem=elemento;
        this.izquierdo=izq;
        this.derecho=der;
        recalcularAltura();
    }
    
    
    
//Observadoras    
public Comparable getElem(){
    return elem;
}

public NodoAVL getIzquierdo(){
    return izquierdo;
}

public NodoAVL getDerecho(){
    return derecho;
}
public int getAltura(){
    return altura;
}
public final void recalcularAltura(){
  int altIzq=-1;
  int altDer=-1;
  if(this.izquierdo!=null){
      altIzq=this.izquierdo.getAltura();
  }
    if(this.derecho!=null){
        altDer=this.derecho.getAltura();
    }
     
    this.altura=Math.max(altIzq, altDer)+1;
     
    }



//Modificadores
public void setElem(Comparable element){
    this.elem=element;
}
 
public void setIzquierdo(NodoAVL izq){
    this.izquierdo=izq;
}

public void setDerecho(NodoAVL der){
    this.derecho=der;
}


        
    }

