/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edat.introduccionyrepaso.apunte1;

import java.util.Random;

/** 1. TDA Alumno
a) Diseñar el TDA considerando campo clave legajo y como información asociada los datos nombre, apellido,
tipo y número de DNI, domicilio (calle, número y ciudad), teléfono, usuario y clave en el sistema SIU.
Especique las operaciones primitivas de acuerdo a las recomendaciones anteriores.
b) Implemente el TDA Alumno para el lenguaje Java, de acuerdo a la especicación anterior.
 *
 * 
 */
public class Alumno {

    private int legajo;
    private String nombre;
    private String apellido;
    private String dni;
    private String tipoDni;
    private String domicilio;
    private int telefono;
    private String usuario;
    private String clave;

    // Constructor
    public Alumno(int leg, String nom, String ape, String dn, String dom, int tel, String usu, String cla, String tipD ){
        nombre=nom;
        apellido=ape;
        dni=dn;
        domicilio=dom;
        telefono=tel;
        usuario=usu;
        clave=cla;
        tipoDni=tipD;
        legajo=leg;
        
    }
 /*   public Alumno(int leg, String nom, String ape, String dn, String dom, int tel, String usu, String cla, String tipD ){
        this.legajo=leg;
        this.nombre=nom;
        this.apellido=ape;
        this.dni=dn;
        this.domicilio=dom;
        this.telefono=tel;
        this.usuario=usu;
        this.clave=cla;
        this.tipoDni=tipD;
        
      
    }
*/
    // Modificadoras
    public void setTipoDni(String tD){
        tipoDni=tD;
    }
    public void setDni(String dn){
        dni=dn;
    }
    public void setNombre(String n){
        nombre=n;
    }
    public void setApellido(String ap){
        apellido=ap;
    }
    public void setDomicilio(String domi){
        domicilio=domi;
    }
    public void setTelefono(int tel){
        telefono=tel;
    }
    public void setClave(String cla){
        clave=cla;
    }
    public void setUsuario(String us){
        usuario=us;
    }
    // como legajo es la clave que identifica al alumno, es unico; en caso de querer cambiarlo  
      
      //Observadoras
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
                
    }
        public int getLegajo(){
            return legajo;
        }
        public String getDni(){
            return dni;
        }
        public String getDomicilio(){
            return domicilio;
        }
        public int getTelefono(){
            return telefono;
        }
        public String getTipoDni(){
            return tipoDni;
        }
        public String getClave(){
            return clave;
        }
        public String getUsuario(){
            return usuario;
        }
    @Override
         public String toString(){
          return "DATOS DEL ALUMNO: \n"+
                  "Nombre: "+nombre+"\n"+
                  "Apellido: "+apellido+"\n"+
                  "Tipo Dni: "+tipoDni+"\n"+
                  "Dni: "+dni+"\n"+
                  "Domicilio(calle,numero y ciudad): "+domicilio+"\n"+
                  "Telefono: "+telefono+"\n";
                  
                  
         }
        // Propias del tipo
         //Si es true es la contraseña correcta del siu
         // en los parametros no se coloca nada ya que estamos comparando contraseñas????????? PREGUNTAR
        public boolean esPasswordCorrecto(Alumno a){
            return this.clave.equals(a.clave);
        }
        // en este caso el usuario del alumno corresponde con su dniy no con el usuario que quiera el alumno
        public boolean usuarioValido(){
            return this.usuario.equals(this.dni);
        }
       
}
    

