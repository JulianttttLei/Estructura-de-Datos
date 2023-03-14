/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablaBusqueda;

/**
 *
 * @author agust
 */
public class test {
    public static void main(String[] args){
       Diccionario d= new Diccionario();
       Diccionario e=new Diccionario();
       e.insertar(3, 2);
       System.out.println("Se agrega el 75");
        d.insertar(75, 0);
        System.out.println(d.toString());
        System.out.println("Se agrega el 20");    
        d.insertar(20,1);
      System.out.println(d.toString()); 
       System.out.println("Se agrega el 15");
        d.insertar(15,2);
        System.out.println(d.toString());
        System.out.println("Se agrega el 80");
        d.insertar(80,3);
        System.out.println(d.toString());
        System.out.println("Se agrega el 93");
        d.insertar(93,4);
        System.out.println(d.toString());
         System.out.println("Se agrega el 77");
        d.insertar(77,5);
        System.out.println(d.toString());
         System.out.println("Se agrega el 18");
        d.insertar(18,6);
        System.out.println(d.toString());
        System.out.println("Se agrega el 78");
        d.insertar(78,7);
        System.out.println(d.toString());
       
         System.out.println("Se agrega el 13");
        d.insertar(13,8);
        System.out.println(d.toString());
        System.out.println("Se agrega el 14");
        d.insertar(14,9);
        System.out.println(d.toString());
         System.out.println("Se agrega el 25");
        d.insertar(25,10);
        System.out.println(d.toString());
         System.out.println("Se agrega el 16");
        d.insertar(16,11);
        System.out.println(d.toString());
        System.out.println("---------------------------------------------------------");
        System.out.println("Eliminamos un hijo hoja: ");
        System.out.println(d.eliminar(16));
        System.out.println(d.toString());
        System.out.println("Eliminamos un elemento que no existe");
        System.out.println(d.eliminar(1));
        System.out.println("Eliminamos 20 que tiene un hijo derecho 25");
        System.out.println(d.eliminar(20));
        System.out.println(d.toString());
        System.out.println("Eliminamos 14 que tiene 2 hijos que son 13 y 15");
        System.out.println(d.eliminar(14));
        System.out.println(d.toString());
        System.out.println("Eliminamos el nodo raiz 75");
        System.out.println(d.eliminar(75));
        System.out.println(d.toString());
        System.out.println("Eliminamos de la estructura e, el elemento 3");
        System.out.println(e.eliminar(3));
     //   System.out.println(e.toString());
        System.out.println("----------------------------------");

        System.out.println("Existe clave 13: "+d.existeClave(13));
        System.out.println("Existe clave 80: "+d.existeClave(80));
        System.out.println("Existe clave 6: "+d.existeClave(6));
        System.out.println("--------------------------------------------");
        System.out.println("Obtener informacion de la clave 13: "+d.obtenerInformacion(13));
        System.out.println("Obtener informacion de la clave 80: "+d.obtenerInformacion(80));
        System.out.println("Obtener informacion de la clave 6: "+d.obtenerInformacion(6));
        System.out.println("Obtener informacion de la clave 15: "+d.obtenerInformacion(15));
        System.out.println("-------------------------------------------");
        System.out.println("Listamos las claves: "+d.listarClaves());
        System.out.println("Listamos los datos: "+d.listarDatos());
        System.out.println("D es: "+d.esVacio());
        System.out.println("E es: "+e.esVacio());
        Diccionario r;
        r=d.clonar();
        System.out.println("Clon Diccionario: ");
       // System.out.println(r.toString());
      
      ;
    
}
}
