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
public class Pasaje {
    
    private Vuelo vuelo;
    private String fecha;
    private int numAsiento;
    private String estado;

    public Pasaje(Vuelo vuelo, String fecha, int numAsiento, String estado) {
        this.vuelo = vuelo;
        this.fecha = fecha;
        this.numAsiento = numAsiento;
        this.estado = estado;
    }
    
     
    
    public Vuelo getVuelo() {
        return vuelo;
    }

    public String getFecha() {
        return fecha;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.vuelo);
        hash = 23 * hash + Objects.hashCode(this.fecha);
        hash = 23 * hash + this.numAsiento;
        hash = 23 * hash + Objects.hashCode(this.estado);
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
        final Pasaje other = (Pasaje) obj;
        if (this.numAsiento != other.numAsiento) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.vuelo, other.vuelo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + "vuelo=" + vuelo + ", fecha=" + fecha + ", numAsiento=" + numAsiento + ", estado=" + estado + ']'+"\n";
    }

 
   
}
      
    
    
    
    

