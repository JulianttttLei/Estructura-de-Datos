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
public class testGrafoEti {
    public static void main(String[] args){
        grafoEtiquetado g= new grafoEtiquetado();
        grafoEtiquetado clone;
      g.insertarVertice("D");
      g.insertarVertice("C");
      g.insertarVertice("B");
      g.insertarVertice("A");
      
      //Agregamos los arcos con etiquetas
      
    
        g.insertarArco("A", "B", 12);
       g.insertarArco("A", "C", 5);

      g.insertarArco("A", "D", 10);
      g.insertarArco("B", "C", 8);
     g.insertarArco("B", "D", 7);
      
     
      g.insertarArco("C", "D", 3);
       // System.out.println("El camino corto de A a C es: "+g.caminoMasLargo("D", "A"));
        System.out.println(g.toString());
        
     
        System.out.println("Eliminado: "+g.eliminarVertice("B"));
        System.out.println(g.toString());
            
        
            
    }
}
