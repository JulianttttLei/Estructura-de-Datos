/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edat.introduccionyrepaso.apunte1;

import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class Recursion4a {
    public static void main(String[] args){
        String palabra;
        char letra;
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese una palabra");
        palabra="cascara";//constitucion - cascara
        //System.out.println("Ingrese una letra que aparezca en la palabra");
        //letra='c';
        System.out.println("ees: "+ cadInvOrig(palabra));
      //  System.out.println("La ultima posicion de la letra: "+letra+" es: "+ultimaPosLetra(palabra,letra));
    }
    // EJERCICIO 4A
    public static int primerPosLetra(String pal,char c){
       int buscado,pos=0;
       if(pos>pal.length()){
           buscado=-1;//La LETRA no existe en la palabra buscada
       }else if(c==pal.charAt(pos)){
           buscado=pos;
           
       }else{
           System.out.println("es: "+pal);
           buscado=primerPosLetra(pal.substring(pos+1),c)+1;
       }
       return buscado;
}
    // EJERCICIO 4B
 public static int ultimaPosLetra(String pal, char c){
     int buscado=0,pos=pal.length()-1;
     if(pos>=0){
        if(c==pal.charAt(pos)){
            buscado=pos;
  }else{
            System.out.println("es "+ pal);
          buscado=ultimaPosLetra(pal.substring(0,pos-1)+pal.charAt(pos-1),c);
     }
    
 } 
     return buscado;
}
 // EJERCICIO 4C
 public static String cadInvOrig(String pal){
     char letra;
    
     String cadInv,cadena="",cadFinal;
     if(pal.length()==1){
          
         cadena=pal;
     
 }else{
        letra=pal.charAt(pal.length()-1);
      
        cadena+=letra+cadInvOrig(pal.substring(0,pal.length()-1));
         
     }
    cadFinal=cadena;
     System.out.println("La cadena es: "+cadFinal);
     return cadFinal;
}
}
