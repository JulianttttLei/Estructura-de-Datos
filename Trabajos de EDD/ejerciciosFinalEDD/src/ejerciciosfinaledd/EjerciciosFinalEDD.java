/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinaledd;

import Estructuras.ArbolBin;
import Estructuras.ArbolGen;
import Estructuras.Lista;
import Estructuras.grafoEtiquetado;

/**
 *
 * @author agust
 */
public class EjerciciosFinalEDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    /*   ArbolGen g=new ArbolGen();
       g.insertar('A', 'R');
        g.insertar('B', 'A');
         g.insertar('C', 'A');
          g.insertar('D', 'A');
           g.insertar('E', 'B');
            g.insertar('F', 'B');
             g.insertar('G', 'B');
              g.insertar('H', 'D');
              g.insertar('I', 'H');
              System.out.println(g.toString());
              System.out.println(g.listaQueJustificaLaAltura());
      
    grafoEtiquetado v=new grafoEtiquetado();
    v.insertarVertice('D');
      v.insertarVertice('C');
        v.insertarVertice('B');
          v.insertarVertice('A');
          
          v.insertarArco('A', 'B', 12);
           v.insertarArco('A', 'C', 5);
            v.insertarArco('B', 'C', 8);
             v.insertarArco('A', 'D', 10);
              v.insertarArco('C', 'D', 3);
               v.insertarArco('B', 'D', 7);
          
          System.out.println(v.toString());
          System.out.println(v.caminoDePesoMenorA('A', 'C', 30));
         */
    ArbolGen t=new ArbolGen();
   
    ArbolGen g= new ArbolGen();
    g.insertar('A', 'e');
    g.insertar('B', 'A');
    g.insertar('C', 'A');
   
    g.insertar('D', 'A');
    g.insertar('E', 'B');
    g.insertar('Z', 'E');
    g.insertar('V', 'Z');
    g.insertar('F', 'B');
    g.insertar('J', 'F');
    g.insertar('K', 'F');
    g.insertar('L', 'F');
    g.insertar('G', 'D');
    g.insertar('H', 'D');
    g.insertar('I', 'D');
    g.insertar('M', 'G');
    g.insertar('P', 'M');
    g.insertar('D', 'H');
    g.insertar('Q', 'M');
    g.insertar('N', 'I');
    g.insertar('O', 'I');
    g.insertar('T', 'O');
    g.insertar('U', 'T');
    
    //   System.out.println(g.toString());
      //  System.out.println(g.listarPreorden());
        //Object c='';
     //   System.out.println("nivel del descendiente: "+c+ "  es: "+g.descendienteMasLejano(c));
      //  System.out.println("nivel del descendiente: "+c+" es: "+g.descendienteMasCercano(c));
        //System.out.println(g.toString());
        //System.out.println(g.descendienteMasCercano(c));
       // System.out.println(g.descendienteMasCercano(c));
       ArbolBin b=new ArbolBin();
       b.insertar(100, 1, 'd');
        b.insertar(19,  100,'I');
         b.insertar(17, 19, 'I');
          b.insertar(2,  17,'I');
           b.insertar(7,  17,'D');
            b.insertar(3,  19,'D');
            
             
               b.insertar(36,  100,'D');
                b.insertar(25,  36,'I');
                 
                  b.insertar(1,  36,'D');
                  
                 
                  //   System.out.println(b.toString());
                     
                    // System.out.println(b.listarPreorden() );
                    ArbolGen e=new ArbolGen();
                    e.insertar('A', 1);
                    e.insertar('B', 'A');
                     e.insertar('C', 'A');
                      e.insertar('D', 'A');
                       e.insertar('G', 'D');
                      e.insertar('H', 'D');
                      e.insertar('I', 'D');
                      e.insertar('M', 'G');
                      e.insertar('P', 'M');
                      e.insertar('Q', 'M');
                     
                      e.insertar('N', 'I');
                      e.insertar('O', 'I');
                      e.insertar('E', 'B');
                      e.insertar('F', 'B');
                      e.insertar('J', 'F');
                      e.insertar('G', 'F');
                      e.insertar('L', 'F');
                     
                     // System.out.println(e.toString());
                     //System.out.println(e.listaQueJustificaLaAltura()); 
                   //  System.out.println(e.descendienteMasCercano('G'));
                  //System.out.println("Ancestro L: "+e.ancestros('L'));
                  Lista lis=new Lista();
                  lis.insertar('A', 1);
                  lis.insertar('B', 2);
                  lis.insertar('F', 3);
                  lis.insertar('L', 4);
                  
                 // System.out.println(e.verificarPatron(lis));
                 
    }
    
}
