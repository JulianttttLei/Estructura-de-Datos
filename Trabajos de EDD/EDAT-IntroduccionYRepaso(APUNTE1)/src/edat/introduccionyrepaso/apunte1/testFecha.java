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
public class testFecha {
    
    public static void main(String[] args){
        
        formatoFecha();
      
    }
    public static Fecha crearFecha(){
        Fecha f;
        int d,m,a;
        System.out.println("Ingrese el dia");
        d=TecladoIn.readLineInt();
        System.out.println("Ingrese el mes");
        m=TecladoIn.readLineInt();
        System.out.println("Ingrese el año");
        a=TecladoIn.readLineInt();
        f=new Fecha(d,m,a);
        return f;
    }
    public static void formatoFecha(){
        Fecha f;
        int mes,dia,año;
        f=crearFecha();
        String dCad,mCad,aCad,cadFormato;
         
        
        
        do{
                aCad=Integer.toString(f.getAño());
        dCad=Integer.toString(f.getDia());
        mCad=Integer.toString(f.getMes());
            if(aCad.length()!=4){
                System.out.println("Debe colocar el año con 4 digitos");
                  año=TecladoIn.readLineInt();
                  f.setAño(año);
            }else if(mCad.length()!=2){
                System.out.println("Debe colocar el mes con 2 digitos ");
                mes=TecladoIn.readLineInt();
                f.setMes(mes);
            }else if(dCad.length()!=2){
                System.out.println("Debe colocar el dia con 2 digitos");
                dia=TecladoIn.readLineInt();
               f.setDia(dia);
            }
            
            
            
        }while(aCad.length()!=4 ||(dCad.length()!=2 || mCad.length()!=2));
       
       cadFormato=""+aCad+mCad+dCad;
            System.out.println("El formato fecha es: "+cadFormato);
    }
}

