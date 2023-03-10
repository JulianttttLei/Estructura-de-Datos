/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpFinalEDAT2020;

import Estructuras.Lista;

/**
 *
 * @author agust
 */
public class Viaje {
    
    private String fecha;
    private int cantAsientoVendido;
 private  Lista lsNumAsiento=new Lista();
  
    public Viaje (String fecha){
        this.fecha=fecha;
      
    }

    @Override
    public String toString() {
        return  "[ fecha=" + fecha + ']'+"";
    }

    

  
    public String getFecha() {
        return fecha;
    }


    public int getAsientoVendido() {
        return cantAsientoVendido;
    }
    public Lista getLsNumAsiento(){
        return lsNumAsiento;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   

    public void setAsientoVendido(int asientoVendido) {
        this.cantAsientoVendido = asientoVendido;
    }
    
    
    
    public void vendXFechaViaje(){
        this.cantAsientoVendido=this.cantAsientoVendido+1;
    }
    public void sacaAsientosVend(){
        this.cantAsientoVendido=this.cantAsientoVendido-1;
    } 
  
  }
  

