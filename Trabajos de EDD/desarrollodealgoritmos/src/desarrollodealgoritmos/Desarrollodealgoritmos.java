/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desarrollodealgoritmos;

/**
 *
 * @author Administrador
 */
public class Desarrollodealgoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //
       int sumaParcial;
       sumaParcial=0;
       for(int j=1; j<=2;j++) {
           sumaParcial=j*j*j;
          // System.out.println("La cant de suma parcial es:"+sumaParcial);
           sumaParcial=sumaParcial+1;
          // System.out.println("Es:"+sumaParcial);
           
       }
        System.out.println("El resultado es:"+sumaParcial);
    }
    
}
