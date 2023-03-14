/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

/**
 *
 * @author agust
 */
public class testHashAbierto {
 public static void main(String[] args){
   
     int longTabla;
     System.out.println("Ingrese la longitud de la tabla");
    
     TablaHashA t=new TablaHashA(13);
     
     //INSERTAR
     System.out.println("Inserte el 91");
   
     System.out.println("es: "+t.insertar(91));
   
    
     System.out.println("Inserte el 119");
     t.insertar(119);
     System.out.println("Inserte el 147");
     t.insertar(147);
     System.out.println("Inserte el 43");
     t.insertar(43);
    
     System.out.println("Inserte el 148");
     t.insertar(148);
     System.out.println("Inserte el 109");
     t.insertar(109);
     System.out.println("Inserte el 137");
     t.insertar(137);
     System.out.println("Inserte el 72");
     t.insertar(72);
     System.out.println("Inserte el 85");
     t.insertar(85);
     System.out.println("Inserte el 101");
     t.insertar(101);
     System.out.println("Inserte el 141");
     t.insertar(141);
     System.out.println("Inserte el 38");
     t.insertar(38);
     //LISTA
    
     
     
     //ELIMINAR
     int n=9;
   //  System.out.println("Eliminamos el "+n );
     
    
     System.out.println("Veo: "+t.eliminar(43));
     System.out.println("Lista: "+t.listar());
  
     //PERTENECE
  //   System.out.println("Pertenece "+n+" : "+t.pertenece(n));
    // System.out.println("Es vacio: "+t.esVacio());
     //t.vaciar();
     //System.out.println("v: "+t.listar());
     //System.out.println("Realizamos un clon de la lista");
     //TablaHashA copia;
     //copia=t.clone();
    // System.out.println("Vemos clon: "+copia.listar());
    
 }   
}
