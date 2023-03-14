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
public class NodoAdy {
    
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;//segun como sea el trabajo lo ponemos object pero hay que ver como es
    
    public NodoAdy(NodoVert vert,NodoAdy sigAdy,Object eti){
        this.vertice=vert;
        this.sigAdyacente=sigAdy;
        this.etiqueta=eti;
        
    }
 //Observadoras
    public NodoVert getVertice(){
        return this.vertice;
    }
    
    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }
    
    public Object getEtiqueta(){
        return this.etiqueta;
    }
    
    //Modificadoras
    public void setVertice(NodoVert vert){
        this.vertice=vert;
    }
    
    public void setSigAdyacente(NodoAdy sigAdy){
        this.sigAdyacente=sigAdy;
    }
    
    public void setEtiqueta(Object eti){
        this.etiqueta=eti;
    }
    
    
}
