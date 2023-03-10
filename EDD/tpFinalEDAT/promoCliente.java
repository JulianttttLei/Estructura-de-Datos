/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpFinalEDAT2020;



/**
 *
 * @author agust
 */
public class promoCliente implements Comparable {
    private  Cliente clave;
    private  Comparable  pasajesComprados;
    
    public promoCliente(Cliente clave,Comparable pc){
        this.clave=clave;
        this.pasajesComprados=pc;
        
    }
    public Cliente getCliente(){
        return clave;
    }
    public Comparable getPasajesComprados(){
        return pasajesComprados;
    }

    @Override
    public int compareTo(Object elem) {
        promoCliente pC=(promoCliente) elem;
        int rta;
        if(this.pasajesComprados.compareTo(pC.pasajesComprados)<0){
            rta=-1;
        }else{
            if(this.pasajesComprados.compareTo(pC.pasajesComprados)>0){
                rta=1;
            }else{
                rta=0;
            }
        }
        return rta;
    }
}
