/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafoEtiquetado;

/**
 *
 * @author agust
 */
public class NodoVert {
   private Object elem;//le ponemos Object pero despues vemos como es en el tp
   private NodoVert sigVertice;
   private NodoAdy primerAdy;
   
   public NodoVert(Object elemento,NodoVert sigVert, NodoAdy priAdy){
       this.elem=elemento;
       this.sigVertice=sigVert;
       this.primerAdy=priAdy;
   }
   
   //Observadoras
   public Object getElem(){
       return this.elem;
   }
   
   public NodoVert getSigVertice(){
       return this.sigVertice;
   }
   
   public NodoAdy getPrimerAdy(){
       return this.primerAdy;
   }
   
   //Modificadoras
   public void setElem(Object elemento){
       this.elem=elemento;
   }
   
   public void setSigVertice(NodoVert sigVert){
       this.sigVertice=sigVert;
   }
   
   public void setPrimerAdy(NodoAdy priAdy){
       this.primerAdy=priAdy;
   }
}
