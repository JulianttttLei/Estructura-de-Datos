/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author agust
 */
public class NodoAVLDicc {

   private Comparable clave;
   private Object dato;
   private int altura;
   private NodoAVLDicc izquierdo;
   private NodoAVLDicc derecho;

   public NodoAVLDicc(Comparable clave, Object dato, NodoAVLDicc izquierdo, NodoAVLDicc derecho ){
       this.clave=clave;
       this.dato=dato;
       this.izquierdo=izquierdo;
       this.derecho=derecho;
       recalcularAltura();
      
       }
   
    public Comparable getClave(){
        return clave;
    }
    
    public Object getDato(){
        return dato;
    }
    
   public int getAltura(){
       return altura;
   }
   
   public NodoAVLDicc getIzquierdo(){
       return izquierdo;
   }
   
   public NodoAVLDicc getDerecho(){
       return derecho;
   }
   
   public void setClave(Comparable clave){
       this.clave=clave;
   }
   
   public void setDato(Object dato){
       this.dato=dato;
   }
   
   public void setIzquierdo(NodoAVLDicc izquierdo){
       this.izquierdo=izquierdo;
       
   }
   
   public void setDerecho(NodoAVLDicc derecho){
       this.derecho=derecho;     
   }
   
   public  void recalcularAltura(){
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
}
