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
public class Cliente implements Comparable {
    
    private String tipo;
    private String num;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String domicilio;
    private String numTelefono;
    
    public Cliente(String tipo, String num){
        this.tipo=tipo;
        this.num=num;
    }
    
    public Cliente(String tipo,String num,String nombre, String ape,String fechaNac,String dom,String tel){
        this.tipo=tipo;
        this.num=num;
        this.nombre=nombre;
        this.apellido=ape;
        this.fechaNacimiento=fechaNac;
        this.domicilio=dom;
        this.numTelefono=tel;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getNum(){
        return num;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public String getDomicilio(){
        return domicilio;
    }
    
    public String getNumTelefono(){
        return numTelefono;
    }
    
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public void setApellido(String apellido){
        this.apellido=apellido;
    }
    
    public void setDomicilio(String domicilio){
        this.domicilio=domicilio;
    }
    
    public void setNumTelefono(String numTelefono){
        this.numTelefono=numTelefono;
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.tipo, other.tipo) ) {
            return false;
        }
   
        if (!Objects.equals(this.num, other.num)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.tipo);
        hash = 59 * hash + Objects.hashCode(this.num);
        return hash;
    }

    
  public String muestraDatosCliente(){
    return "[tipo: " + tipo + " y numero: " + num + " de documento , nombre: " + nombre + 
                " , apellido: "+ apellido + " , fecha de "   + "nacimiento: " + fechaNacimiento + 
                " , domicilio: " + domicilio + " y telefono: " + numTelefono + "]"+"\n";
}

    @Override
   public String toString(){
       return "[tipo: " + tipo + " y numero: " + num + " de documento]";
   }
   

    @Override
    public int compareTo(Object elem) {
        Cliente elemento= (Cliente) elem;
        int i;
        if(this.tipo.compareTo(elemento.tipo)>0){
            i=1;    
        }else{
            if(this.tipo.compareTo(elemento.tipo)<0){
                i=-1;
        }else{    
                if(this.num.compareTo(elemento.num)<0){          
                i=-2;
                }else{
                    if(this.num.compareTo(elemento.num)>0){
                        i=2;
                    }else{
                        i=0;
                    }
                }          
}
        }
        return i;
    }
}
