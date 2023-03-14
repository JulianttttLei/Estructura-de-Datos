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
public class CeldaHash {
    
     private Object elem;
     private int estado;
    
public CeldaHash(Object elem,int estado){
    this.elem=elem;
    this.estado=estado;
    // por defecto es 0 porque esta 
}
//Modificadoras
public void setElem(Object elem){
    this.elem=elem;
}
public void setEstado(int estado){
    this.estado=estado;
}

//Observadoras
public Object getElem(){
    return this.elem;
}
public int getEstado(){
    return this.estado;
}

}
    

