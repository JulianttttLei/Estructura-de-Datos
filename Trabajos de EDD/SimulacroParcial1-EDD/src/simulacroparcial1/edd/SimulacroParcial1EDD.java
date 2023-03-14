/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacroparcial1.edd;
import dinamicas.Cola;
import dinamicas.Lista;
import dinamicas.Pila;
import jerarquicas.ArbolBin;

import jerarquicas.ArbolGen;

/**
 *
 * @author Administrador
 */
/*EJERCICIO TIPO 1: Implementar una operación de un TDA lineal
a) Agregar al TDA Lista la operación obtenerMultiplos(int num) que recibe un número y devuelve una lista nueva
que contiene todos los elementos de las posiciones múltiplos de num, en el mismo orden encontrado,
haciendo un único recorrido de las estructuras original y copia; y sin usar otras operaciones del TDA.
Ejemplo: si se invoca con la lista <A,B,C,D,E,F,G,H,I,J> y num=3, el método debe devolver la lista <C,F,I>*/
// ◦ Realizar la definición de tipos de todas las clases involucradas
// ◦ En todas las operaciones recorrer lo menos posible las estructuras
public class SimulacroParcial1EDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArbolGen a=new ArbolGen();
         a.insertar('A','O');
     a.insertar('B','A');
      a.insertar('C','A');
       a.insertar('D','A');
        a.insertar('E','B');
         a.insertar('F','B');
          a.insertar('G','B');
           a.insertar('H','D');
      //     System.out.println("es: "+a.toString());
             
   Lista l=new Lista();
   
     l.insertar(1, 1);
     l.insertar(2, 2);
     l.insertar(3, 3);
     l.insertar(4, 4);
     l.insertar(5, 5);
     l.insertar(6, 6);
     l.insertar(7, 7);
   //    System.out.println("Lista comun: "+l.toString());
  //      System.out.println("Clonar invertido: "+l.clonarInvertido().toString());
 //       System.out.println("Clone: "+l.clone());
       // l.agregarElem(0, 2);
        //System.out.println("New lista: "+l.toString());
      
    //    System.out.println("Lista con 0 agregado cada 2 posiciones: "+l.toString());
      //  System.out.println("Lista: "+l.toString());
       
        //System.out.println("clonar invertido: "+l.clonarInvertido().toString());
       
        //System.out.println("listar invertido: "+l.toString());
      
       
    
  //ejercicio 1a hecho
  //ejercicio 1b hecho
    
 /*EJERCICIO TIPO 2: Usar TDA lineales
c) En una clase TestCadenas, que utilice los TDA Lista, Pila y Cola vistos en la materia, para guardar elementos
de tipo CHAR, implementar el método generar (Cola c1) que recibe por parámetro una estructura cola c1
que tiene el siguiente formato: a1#a2#a3#….#an, donde cada ai en una sucesión de letras mayúsculas y a
partir de c1 debe generar como salida otra Cola de la forma: a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la
secuencia de letras mayúsculas ai pero invertida. Ejemplo.: Si c1 es : AB#C#DEF , entonces la operación
generar devolverá una Cola con el siguiente formato: ABBAAB#CCC#DEFFEDDEF
• Consideraciones ejercicio 2 c y d:
◦ Dibujar la cajas UML del TDA Lista, Pila y Cola indicando nombre, parámetros y tipo de salida de su
interfaz (métodos públicos) y sólo las operaciones del TDA sin indicar la implementación.
*/
 
 Cola c=new Cola();
 c.poner('A');
 c.poner('B');
 c.poner('#');
 c.poner('C');
 c.poner('#');
 c.poner('D');
 c.poner('E');
 c.poner('F');
//c.poner('#');
       System.out.println("La cola agegrada es: "+c.toString());
        System.out.println("generar: "+generar(c));
      
        
        
        
        
        
        
     ArbolGen j=new ArbolGen();   
        j.insertar(1, 0);
         j.insertar(2, 1);
          j.insertar(3,1);
           j.insertar(4,1);
            j.insertar(5, 2);
             j.insertar(6, 2);
              j.insertar(7, 2);
               j.insertar(8, 4);
              
     //   System.out.println("es: "+j.toString());
        
        
        
        
        
        ArbolBin n=new ArbolBin();
        n.insertar('A', 'I','p' );
        n.insertar('B', 'A','I' );
        n.insertar('C', 'A','D' );
        n.insertar('D', 'B','I' );
        n.insertar('E', 'C','I' );
        n.insertar('F', 'C','D' );
        n.insertar('G', 'E','I' );
        n.insertar('H', 'E','D' );
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   Cola m=new Cola();
   m.poner(0);
   m.poner(1);
   m.poner(2);
    m.poner(3);
     m.poner(4);
      m.poner(5);
       m.poner(6);
        m.poner(7);
         m.poner(8);
          m.poner(9);
          Pila p=new Pila();
          p.apilar(0);
          p.apilar(1);
          p.apilar(2);
          p.apilar(3);
          //System.out.println("fa "+p.toString());
          //System.out.println("tuiene: "+p.obtenerTope());
          p.desapilar();
         // System.out.println("dsf " +p.obtenerTope());
       Lista k=new Lista();
       k.insertar(0, 1);
       k.insertar(1, 2);
       k.insertar(4, 3);
       k.insertar(8, 2);
      //  System.out.println("lista: "+k.toString());//0 8 1 4

 //generarSecuencia(m, 4);
    }
 
    public static boolean esMayuscula(Object letra){
        //Este metodo verficia si es una letra es mayuscula o no
        char c=(char) letra;//Realizamos un casting de tipo para pasar de object a char
        boolean mayuscula=false;
        if(c>='A' || c<='Z'){
           mayuscula=true; 
        }
        return mayuscula;
    }
    //INEFICIENTE:PERO FUNCIONA PORQUE LLAMAMOS DOS VECES AL METODO
public static Cola generar(Cola c1) {
      Object b='#';
              int i=0;
        Cola colaNew = new Cola();//creamos una cola nueva
        Lista l=new Lista();//creamos una lista nueva
        while (!c1.esVacia()) {//mientras la cola original no sea vacia
            if (c1.obtenerFrente().equals(b) || i==1){ //si el obtenerFrente es igual al # -
                
            concatenaCarac(colaNew,l);//hacemos una llamada al metodo
              l.vaciar();//vaciamos la lista
              if(i==0){ //-
                colaNew.poner('#');//ponemos en la cola nueva el elemento #
              }
            }else{
                 colaNew.poner(c1.obtenerFrente()); //ponemos el obtenerFrente en la cola nueva
               l.insertar(c1.obtenerFrente(), l.longitud()+1);//insertamos en la lista el elemento de la cola original         
                }
         c1.sacar();  //sacamos el frente de la cola original
         if(c1.esVacia() && i==0 ){//-
             i=1;//-
             c1.poner(1);//-
         }
            }
       // concatenaCarac(colaNew,l);
        return colaNew;
    }



    public static void concatenaCarac(Cola newCola,Lista aux1) {
     //Este metodo concatena los caracteres segun el formato deseado
        Lista copia;  
        copia=aux1.clone();//Creamos un clon de la lista
         int lonLista=aux1.longitud(),lonCopia=copia.longitud(),p=1;
        while (lonLista!=0){//mientras la longitud de la lista es distinta de 0 entonces:
          newCola.poner(aux1.recuperar(lonLista));//ponemos en la cola nueva el ultimo elemento de la lista y otros, disminuyendo 
            lonLista--;    
           if(lonLista==0){//si la longitud de la lista es 0
               while(p<=lonCopia){
               newCola.poner(copia.recuperar(p));//ponemos el elemento en la cola nueva el elemento menor hasta ir, incrementandose      
               p++;
           }         
    }
}                
}
    /*
    ) En la clase Matematica, que utiliza a los TDA Lista, Pila y Cola vistos en la materia para guardar elementos
tipo CHAR que representan una expresión matemática, desarrollar el método verificarBalanceo (Cola q)
que recibe por parámetro una cola con una expresión matemática y verifique que los paréntesis, corchetes y
llaves estén correctamente balanceados. Debe usar como estructura auxiliar alguno de los TDA lineales
vistos, el que considere más apropiado.

    */
 public static boolean verificarBalanceo(Cola q){
  //Este metodo recibe por parametro una cola con una expresion matematica y verifica que los parentesis
  // corchetes y llaves esten correctamente balanceados. Devuelve true si la expresion es correcta y false en caso contrario
  //Ejemplos: Si q es ← { 5 + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver TRUE
   //Si q es ← { 5 + 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver FALSE

    boolean verifica=false;
    Lista ls=new Lista();
    return verifica;
 }
 public static Lista generarSecuencia(Cola cola, int t) {
        
        Lista lista = new Lista();
        Object elemento;
        int ext = 1, i = 0, r = 1;
        while (!cola.esVacia()) {
            i += 2;
            elemento = cola.obtenerFrente();
            System.out.println("obtener frente: "+elemento);
            lista.insertar(elemento, r);
            lista.insertar(elemento, i);
            System.out.println("Elemento de pos: "+r+" es: "+lista.recuperar(r));
            System.out.println("Elemento de pos: "+i+" es: "+lista.recuperar(i));
            System.out.println("queda: "+lista.toString());
            cola.sacar();
            if (ext % t == 0 && !cola.esVacia()) {
                i++;
                lista.insertar('$', i);
                r = i + 1;
            }
            ext++;
        }
        return lista;
    }
  public static Lista generarSecuencia2(Cola q, int t) {
        int i=t+1,m=t;
        Lista lista = new Lista();
        Object elemento;
        
        while (!q.esVacia()) {
          
            elemento = q.obtenerFrente();
            System.out.println("obtener frente: "+elemento);
            lista.insertar(elemento, m);//
           lista.insertar(elemento,i);//[3210 0123]         ]
            q.sacar();
            
            if(m==0 && !q.esVacia()){
            lista.insertar('$', i+1);
            m=i;
            
        }else{
                m--;
            
            }
            i++;
        }
        return lista;
    }
 
        }

       

   

  
    

