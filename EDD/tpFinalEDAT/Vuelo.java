/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpFinalEDAT2020;

import Estructuras.Lista;
import java.util.Objects;

/**
 *
 * @author agust
 */
public class Vuelo implements Comparable{
    
     private String clave;
      private String aeroOrigen;
      private String aeroDestino;
      private String horaSalida;
      private String horaLlegada;
        private int asientoTotal;
      private Lista viajes;//guarda fecha
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.clave);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vuelo other = (Vuelo) obj;
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        return true;
    }
    
    public Vuelo(String clave) {
        this.clave = clave;
    }
    
    public Vuelo(String clave, String aeroOrigen, String aeroDestino, String horaSalida, String horaLlegada, int asientoTotal) {
        this.clave = clave;
        this.aeroOrigen = aeroOrigen;
        this.aeroDestino = aeroDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.viajes = new Lista();
        this.asientoTotal=asientoTotal;
    }
      
    public String getClave() {
        return clave;
    }
    
 public String getAeroOrigen() {
        return aeroOrigen;
    }
 
   public String getAeroDestino() {
        return aeroDestino;
    }
   
    public String getHoraSalida() {
        return horaSalida;
    }  
    
    public String getHoraLlegada() {
        return horaLlegada;
    }
    
     public Lista getViajes() {
        return viajes;
    }
     public int getAsientoTotal(){
         return asientoTotal;
     }
    
    public void setAeroOrigen(String aeroOrigen) {
        this.aeroOrigen = aeroOrigen;
    }

    public void setAeroDestino(String aeroDestino) {
        this.aeroDestino = aeroDestino;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setViajes(Lista viajes) {
        this.viajes = viajes;
    }
     @Override
    public String toString(){
        return "[clave = " + clave + " ] ";
    }

  
    public String muestraDatosVuelo(){
        return "[ clave=" + clave + ", aeroOrigen=" + aeroOrigen + ", aeroDestino=" + aeroDestino + ", horaSalida=" 
                + horaSalida + ", horaLlegada=" + horaLlegada + ", asientoTotal=" + asientoTotal + ", viajes=" + viajes + ']'+"\n";
    }
    
   
     


    @Override
    public int compareTo(Object elem) {
       Vuelo elemento= (Vuelo) elem;
        int i;
        if(this.clave.compareTo(elemento.clave)>0){
            i=1;    
        }else{
            if(this.clave.compareTo(elemento.clave)<0){
                i=-1;
            }else{
                i=0;
            }           
}
        return i;
    
    }     
   public void vendXFechaVuelo(Viaje viajeAux){
      int p=1;
     Viaje fechaV = null ;  
       boolean rta=false;     
       while(!rta && p<=this.viajes.longitud()){
           //Recuperamos el el objeto que tiene guardado fecha               
            fechaV=(Viaje)this.viajes.recuperar(p);         
           //Comparamos el elemento e que pertenece
           rta=(((Object)viajeAux.getFecha()).equals((Object)fechaV.getFecha()));
           p++;      
       }
        if(rta){
           // si existe ese viaje con esa fecha aumentamos la cantidad de asientos vendidos 
          if(fechaV!=null){
           fechaV.vendXFechaViaje();
            }
        }
      
   }
      
 public  boolean fechaEnViaje(Viaje viaje){  
     Viaje  elemFecha;
     int pos=1;
     boolean buscado=false;       
     if(!this.viajes.esVacia()){
     while(pos<=this.viajes.longitud() && !buscado){
            elemFecha=(Viaje) this.viajes.recuperar(pos);
            buscado=viaje.getFecha().equals(elemFecha.getFecha());
            pos++;
        }
     
        }    
       return buscado;
        
        
    }
 public int obtenerMinutos(){
     String[] salida,llegada;
     salida=this.horaSalida.split(":");
     llegada=this.horaLlegada.split(":");
     int minutos=agregaCargasIniciales.pasaHoraMinAMin(salida,llegada);
     return minutos;
 }
 public void sacaVendidosFecha(Viaje viaje){
     viaje.sacaAsientosVend();
 }
   public  Viaje buscaFechaViaje(String fecha){       
        int longitud=this.viajes.longitud(),pos=1;
        boolean buscado=false;
        Viaje viaje=null;
         while (pos <= longitud && !buscado) {
            viaje = (Viaje) this.viajes.recuperar(pos);
        
            buscado = viaje.getFecha().equals(fecha);
            pos++;
        }
         if(!buscado){
             viaje=null;
         }
         return viaje;
        
    }
 
   


}