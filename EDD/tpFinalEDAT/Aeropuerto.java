/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpFinalEDAT2020;


import java.util.Objects;

/**
 *
 * @author agust
 */
public class Aeropuerto {

    private String nombre;
    private String ciudad;
    private String numTelefono;
    
    public Aeropuerto(String nombre,String ciudad,String numTelefono){
        this.nombre=nombre;
        this.ciudad=ciudad;
        this.numTelefono=numTelefono;
    }

    public Aeropuerto(String nombre) {
        this.nombre = nombre;
    }

   

    @Override
    public String toString() {
        return  "[nombre = " + nombre + ", ciudad = " + ciudad + " Y numTelefono = " + numTelefono +']'+"\n";
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
        final Aeropuerto other = (Aeropuerto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
     
        
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

   
 
    public String muestraNombre(){
        return "[ clave: " + nombre + "]";
    }
    
    

    public String getNombre(){
        return nombre;
    }
    
    public String getCiudad(){
        return ciudad;
    }
    
    public String getNumTelefono(){
        return numTelefono;
    }
    
    public void setNumTelefono(String numTelefono){
        this.numTelefono=numTelefono;
    }
   
    
}

