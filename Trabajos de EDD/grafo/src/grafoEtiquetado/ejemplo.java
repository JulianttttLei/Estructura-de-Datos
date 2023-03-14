/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafoEtiquetado;

/**
 *
 * @author agust
 */
//Este codigo
public class ejemplo {
    public static void main(String[] args){
        int num;
        System.out.println("Ingrese la contraseña numerica");
        num=TecladoIn.readLineInt();
        if(num>100){
            
            System.out.println("La contraseña es aceptada");
        }else{
            
            System.out.println("La constraseña no es aceptada");
        }
        System.out.println("La contraseña es: "+num);
    }
    
}
