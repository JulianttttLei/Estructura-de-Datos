/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edat.introduccionyrepaso.apunte1;

/**
 *
 * @author Administrador
 */
public class Fecha {
    
    private int dia;
    private int mes;
    private int año;
    // constructor
    public Fecha(int d, int m, int a){
        dia=d;
        mes=m;
        año=a;
    }
    //observadora
    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
        
    }
    public int getAño(){
        return año;
    }
    // modificadoras
    public void setDia(int d){
        dia=d;
    }
    public void setMes(int m){
        mes=m;
    }
    public void setAño(int a){
        año=a;
    }
    
    
}
