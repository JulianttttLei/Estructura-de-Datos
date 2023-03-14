/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edat.introduccionyrepaso.apunte1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class testAlumno {
    public static void main(String[] args){
      
        crearAlumno();
       //PREGUNTAR SI EL DOMICILIO SE PUEDE HACER EN OTRA CLASE
       
        
    } 
   
        private static Alumno crearAlumno(){
          
         Alumno a;
       Scanner sc= new Scanner(System.in);
         String apellido,nombre,domicilio,tipoDni,dni,usuario,clave;
       
         int telefono,legajo;
          legajo=legajoRandom();
            System.out.println("DATOS DEL ALUMNO: ");
            System.out.println("Ingrese el nombre: ");
            nombre=TecladoIn.readWord();
           
            System.out.println("Ingrese el apellido: ");
            apellido=TecladoIn.readWord();
          
            System.out.println("Ingrese su domicilio: ");
            domicilio=sc.nextLine();
           
            System.out.println("Ingrese su dni: ");
            dni=TecladoIn.readWord();
            usuario=dni;
            clave=dni;
           
            System.out.println("Ingrese el tipo de dni: ");
            tipoDni=TecladoIn.readLine();
           
            System.out.println("Ingrese su telefono");
            telefono=TecladoIn.readLineInt();
          a=new Alumno(legajo,nombre,apellido,dni,domicilio,telefono,usuario,clave,tipoDni);
            System.out.println("El usuario sera: "+a.getUsuario());
            System.out.println("El legajo del alumno sera: "+a.getLegajo()); 
            
         return a;
        }
         public static int legajoRandom(){
                //Este programa te da un numero aletatorio desde 0 a 4000 en este caso(no tomando el  4000)
      Random numero=new Random();
      int rangoMax=4000;
      return numero.nextInt(rangoMax)+0;
    }
      
}
