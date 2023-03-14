//Reentrega del TDA lista + generarLista
package testLineales;

import dinamicas.Cola;
import dinamicas.Lista;
import dinamicas.Pila;

/**
 *
 * @author Administrador
 */
public class MixLineales {

    public static void main(String[] args) {
        Lista ls = new Lista();
        ls.insertar('A', 1);
        ls.insertar('B', 2);
        ls.insertar('#', 3);
        ls.insertar('C', 4);
        ls.insertar('#', 5);
        ls.insertar('D', 6);
        ls.insertar('E', 7);
        ls.insertar('F', 8);

        // System.out.println("Lista original: " + ls.toString());
      // System.out.println("Lista modificada: " +  generaOtroTipo1Lista(ls).toString());
        Cola c = new Cola();
        c.poner('A');
        c.poner('B');
        c.poner('$');
        c.poner('C');
        c.poner('$');
        c.poner('D');
        c.poner('E');
        c.poner('F');
        //  System.out.println("Cola original: "+c.toString());
        //  System.out.println("Cola Modificada: "+generarOtraCola(c).toString());
        Lista l1 = new Lista();
        Lista l2 = new Lista();
        l1.insertar(2, 1);
        l1.insertar(4, 2);
        l1.insertar(6, 3);

        l2.insertar(1, 1);
        l2.insertar(1, 2);
        l2.insertar(3, 3);
        l2.insertar(4, 4);
       // System.out.println("Lista: "+l2.toString());
      //l2.eliminarApariciones(1);
        //System.out.println("Sin apariciones: "+l2.toString());
      
        // System.out.println("Lista l1: " + l1.toString());
        //System.out.println("Lista l2: " + l2.toString());
        //System.out.println("Lista concatenada: " + concatenar(l1, l2));

        Lista l3 = new Lista();
        l3.insertar(9, 1);
        l3.insertar(6, 2);
        l3.insertar(5, 3);
        l3.insertar(0, 4);
        l3.insertar(9, 5);
        l3.insertar(6, 6);
        l3.insertar(5, 7);
        l3.insertar(0, 8);
        l3.insertar(5, 9);
        l3.insertar(6, 10);
        l3.insertar(9, 11);
        // System.out.println("Comprueba lista: "+comprobar(l3));

        Lista l4 = new Lista();
        l4.insertar(1, 1);
        l4.insertar(2, 2);
        l4.insertar(3, 3);
        //  System.out.println("Lista original: "+l4.toString());
        //System.out.println("Lista invertida: "+invertir(l4));

        Cola c4 = new Cola();
        c4.poner(0);
        c4.poner(1);
        c4.poner(2);
        c4.poner(3);
        c4.poner(4);
        c4.poner(5);
        c4.poner(6);
        c4.poner(7);
        c4.poner(8);
        c4.poner(9);

        System.out.println("Cola: "+c4.toString());
        System.out.println("Lista con otra secuencia: "+generarSecuencia(c4,4));
        Lista l5 = new Lista();
        l5.insertar(1, 1);
        l5.insertar(2, 2);
        l5.insertar(3, 3);
        l5.insertar(4, 4);
        l5.insertar(5, 5);
        l5.insertar(6, 6);
        l5.insertar(7, 7);
        l5.insertar(8, 8);
        l5.insertar(9, 9);

        // System.out.println("Lista "+l5.toString());
        //System.out.println("Lista modificada:  "+agregarProducto(3,l5).toString());
        // System.out.println("intercala: "+intercalar(l5,l6));
        //System.out.println("cont rep: "+esCapicua(l6));
        Cola c5 = new Cola();
        //Si q es ← { 5 + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver TRUE
        //Si q es ← { 5 + 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver FALSE
        c5.poner('{');
        c5.poner('5');
        c5.poner('+');
        c5.poner('[');
        c5.poner('8');
        c5.poner('*');
        c5.poner('9');
        c5.poner('-');
        c5.poner('(');
        c5.poner('4');
        c5.poner('/');
        c5.poner('2');
        c5.poner(')');
        c5.poner('+');
        c5.poner('7');
        c5.poner(']');
        c5.poner('-');
        c5.poner('1');
        c5.poner('}');
        //System.out.println("Cola: " + c5.toString());
        //System.out.println("Esta balanceada? " + verificarBalanceo(c5));
        
        Lista l7=new Lista();
        l7.insertar('A', 1);
        l7.insertar('B', 2);
      
     //   ejemplo(l7);
     
     Cola cola1=new Cola();
      cola1.poner(0);
      cola1.poner(1);
      cola1.poner(2);
      cola1.poner(3);
      cola1.poner(4);
      cola1.poner(5);
      cola1.poner(6);
      cola1.poner(7);
      cola1.poner(8);
      cola1.poner(9);
      
            System.out.println("es: "+colaToLista(cola1,4).toString());
        
    }

    //generarOtraCola
    public static Cola generarOtraCola(Cola c1) {
        //Este metodo genera una cola nueva con otro formato
        //sea AB$C$DEF---> ABBA$CC$DEFFED
        Pila p = new Pila();//pila auxiliar para colocar los tramos invertidos
        Cola c = new Cola();//cola auxiliar 
        Cola colaCopia = c1.clone();//cola clon de c1
        Object elemento;
        if (!c1.esVacia()) {
            colaCopia.poner('$');
        }
        while (!colaCopia.esVacia()) {
            elemento = colaCopia.obtenerFrente();
            if (elemento.equals('$')) {
                while (!p.esVacia()) {
                    c.poner(p.obtenerTope());
                    p.desapilar();
                }
                if (!c1.esVacia()) {
                    c.poner('$');
                }
            } else {
                p.apilar(elemento);
                c.poner(elemento);
            }
            colaCopia.sacar();
            c1.sacar();
        }
        return c;
    }

    //concatenar
    public static Lista concatenar(Lista l1, Lista l2) {
        //Este metodo recibe 2 listas y devuelve una lista nueva con los elementos de l1 y l2
        // concatenados
        int pos, i = 1, longi = l1.longitud() + l2.longitud();
        Lista l3 = new Lista();
        if (!l1.esVacia()) {
            l3 = l1.clone();
        } else {
            //caso especial
            //si la lista l1 esta vacia
            if (l1.esVacia()) {
                while (i <= l2.longitud()) {
                    l3.insertar(l2.recuperar(i), i);
                    i++;
                }
            } else {
                //si la lista l2 esta vacia
                if (l2.esVacia()) {
                    while (i <= l1.longitud()) {
                        l3.insertar(l1.recuperar(i), i);
                        i++;
                    }
                }
            }
        }
        if (!l1.esVacia() && !l2.esVacia()) {
            //caso comun
            for (pos = l1.longitud() + 1; pos <= longi; pos++) {
                l3.insertar(l2.recuperar(1), pos);
                l2.eliminar(1);
            }
        }
        return l3;
    }

    //comprobar
    public static boolean comprobar(Lista l1) {
        //Este metodo verifica si los elementos de una lista contienen la forma cadena0cadena0cadena^
        boolean valido, sigue = true;
        int i = 0;
        Pila pila = new Pila();
        Cola cola = new Cola();
        int longitud = l1.longitud();
        Object elemento;
        //En primer lugar, se guardan los elementos en la cola y pila
        while (i <= longitud && sigue) {
            i++;
            elemento = l1.recuperar(i);
            if (!elemento.equals(0)) {
                cola.poner(elemento);
                pila.apilar(elemento);

            } else {
                sigue = false;
            }
        }
        valido = esCero(i, l1);
        longitud = longitud - i;//11-4=7
        sigue = true;
        while (i <= longitud && valido && sigue) {
            i++;
            if (!cola.esVacia()) {
                valido = cola.obtenerFrente().equals(l1.recuperar(i));
                cola.sacar();
            } else {
                sigue = false;
            }
        }
        valido = esCero(i, l1);
        while (i <= longitud && valido) {
            i++;
            if (!pila.esVacia()) {
                valido = pila.obtenerTope().equals(l1.recuperar(i));
                pila.desapilar();
            }
        }
        return valido;
    }

    public static boolean esCero(int pos, Lista l) {
        //Este metodo verfica si hay un cero en esa posicion
        boolean verifica = false;
        if (l.recuperar(pos).equals(0)) {
            verifica = true;
        }
        return verifica;
    }

    //invertir 
    public static Lista invertir(Lista l1) {
        //Este metodo invierte una lista
        int i, longitud = l1.longitud(), pos = 1;
        Lista newLista = new Lista();
        for (i = longitud; i > 0; i--) {
            newLista.insertar(l1.recuperar(i), pos);
            pos++;
        }
        return newLista;
    }

    //intercalar
    public static Lista intercalar(Lista l1, Lista l2) {
        //Este metodo intercala 2 listas
        Lista newLista = null;
        int i = 1, longil2 = l2.longitud(), longil1 = l1.longitud(), cantElem, pos = 1;
        if (!l1.esVacia() && !l2.esVacia()) {
            newLista = l1.clone();
            cantElem = (longil1 - 1) * 2;
            while (i <= longil2) {
                if (pos <= cantElem) {
                    pos = pos * 2;
                    newLista.insertar(l2.recuperar(i), pos);
                    if (pos > cantElem) {
                        pos = pos - 2;
                        i = i - 2;
                    }
                } else {
                    newLista.insertar(l2.recuperar(i), pos);
                    pos = pos + 1;
                }
                i++;
            }
        } else {
            if (l1.esVacia()) {
                newLista = l2.clone();
            } else {
                if (l2.esVacia()) {
                    newLista = l1.clone();
                }
            }
        }
        return newLista;
    }

    public static int contarRepIterativo(Lista l1, Object elemBuscado) {
        int i, longil1 = l1.longitud(), cont = 0, p;
        Object elemento;
        for (i = 1; i <= longil1; i++) {
            elemento = l1.recuperar(i);
            if (elemento.equals(elemBuscado)) {
                cont++;
            }
        }
        return cont;
    }

    public static boolean esCapicua(Lista l1) {
        boolean capicua = true;
        int longi = l1.longitud(), i = 1, mitad;
        if (!l1.esVacia()) {
            if (longi % 2 == 0) { // si la lista es par entonces
                while (i <= longi && capicua) {
                    if (!l1.recuperar(i).equals(l1.recuperar(longi))) {
                        capicua = false;
                    }
                    i++;
                    longi--;
                }
            } else {
                //si la lista es impar
                mitad = (longi / 2) + 1;
                while (i <= mitad && longi >= mitad && capicua) {
                    if (!l1.recuperar(i).equals(l1.recuperar(longi))) {
                        capicua = false;
                    }
                    i++;
                    longi--;
                }

            }
        }
        return capicua;
    }

    public static Lista generarLista2(Lista l1) {
        //Este metodo genera una lista con otro formato
        //Sea AB*C*DEF--> ABBAAB*CCC*DEFFEDDEF
        Lista copiaLista;
        Cola c = new Cola();
        Pila p = new Pila();
        Lista newLista = new Lista();
        copiaLista = l1.clone();
        int i = 1;
        Object elemento;
        if (!l1.esVacia()) {
            copiaLista.insertar('*', l1.longitud() + 1);
        }
        while (!copiaLista.esVacia()) {
            elemento = copiaLista.recuperar(1);
            if (!elemento.equals('*')) {
                newLista.insertar(elemento, i);
                c.poner(elemento);
                p.apilar(elemento);
            } else {
                while (!p.esVacia()) {
                    newLista.insertar(p.obtenerTope(), i);
                    i++;
                    p.desapilar();
                }
                while (!c.esVacia()) {
                    newLista.insertar(c.obtenerFrente(), i);
                    i++;
                    c.sacar();
                }
                if (!l1.esVacia()) {
                    newLista.insertar('*', i);
                }
            }
            i++;
            l1.eliminar(1);
            copiaLista.eliminar(1);
        }
        return newLista;
    }

    public static Lista generarSecuencia(Cola q, int t) {
        //Este metodo recibe por parametro una estructura con una sucesion de digitos y
        //debe generar como salida una Lista de la forma: a1^a1$a2^a2$....$an^an donde
        //a es una secuencia de t digitos tomada de la lista original y a^ es la secuencia
       // a invertida. La ultima subcadena puede ser de longitud menor a t
  //Cola: [0,1,2,3,4,5,6,7,8,9]-----> Lista con otra secuencia: [3,2,1,0,0,1,2,3,$,7,6,5,4,4,5,6,7,$,9,8,8,9]
        int pos = 0, i = 1, m = 0;
        Cola clon = q.clone();
        Lista ls = new Lista();
        Object elemento;
        while (!clon.esVacia()) {
            pos++;
            m = m + 2;
            elemento = clon.obtenerFrente();
            ls.insertar(elemento, i);
            ls.insertar(elemento, m);
            if (pos % t == 0) {
                i = m + 2;
                m++;
                ls.insertar('$', m);

            }
            clon.sacar();
        }
        return ls;
    }

    public static Lista agregarProducto(int x, Lista ls) {
//Este metodo recibe por parametro un valor x y recorre la lista una sola vez
// ,tomando de a x elementos y agregando a continuacion un elemento nuevo con el producto de los
//x valores anteriores. Si la longitud de la lista no es divisible por x, el ultimo elemento que se agrega
//es el producto del elemento que queden.
//Sea la lista[1,2,3,4,5,6,7,8] y el valor x=2 modifica a la lista a [1,2,2,3,4,12,5,6,30,7,8,56]
// donde los elementos nuevos son 2=1*2, 12=3*4, 30=5*6, y 56=7*8
//Sea la lista [1,2,3,4,5,6,7,8] y el valor x=3 modifica a la lista a [1,2,3,6,4,5,6,120,7,8,56]
//donde los elementos nuevos son 6=1*2*3, 120=4*5*6 y 56=7*8 
        Lista clon = ls.clone();
        Object elemento;
        int pos = 1, nuevo = 1, m = 0, i = 0;
        while (pos <= clon.longitud()) {
            elemento = clon.recuperar(pos);
            nuevo = nuevo * (int) elemento;
            if (pos % x == 0) {
                m = x + 1 + m;
                ls.insertar(nuevo, m);
                nuevo = 1;
                i++;
            } else {
                if (pos == clon.longitud() && pos % x != 0) {
                    m = pos + i + 1;
                    ls.insertar(nuevo, m);
                }
            }
            pos++;
        }
        return ls;
    }

    public static boolean verificarBalanceo(Cola q) {
//Este metodo recibe por parametro una cola con una expresion matematica y verifique 
//que los parentesis, corchetes y llaves esten correctamente balanceados. Se debe usar como
//estructura auxiliar alguno de los tda lineales ya visto, que consisdere el mas apropiado
//Si q es -->{ 5 + 8 * ( 9 - [ 4 / 2 ] + 7 ) - 1)
        Cola clon = q.clone();
        Pila pila = new Pila();
        Object elemento, elemPila;
        boolean hayBalance = true;
        while (!clon.esVacia() && hayBalance) {
            elemento = clon.obtenerFrente();
            if (elemento.equals('{')
                    || elemento.equals('[')
                    || elemento.equals('(')) {
                pila.apilar(elemento);
            } else {
                if ((elemento.equals(')')
                        || elemento.equals(']')
                        || elemento.equals('}')) && !pila.esVacia()) {
                    elemPila = pila.obtenerTope();
                    if ((elemPila.equals('(') && elemento.equals(')'))
                            || (elemPila.equals('[') && elemento.equals(']'))
                            || (elemPila.equals('{') && elemento.equals('}'))) {
                        pila.desapilar();
                    } else {
                        hayBalance = false;
                    }
                }
            }
            clon.sacar();
        }
        if (!pila.esVacia()) {
            hayBalance = false;
        }
        return hayBalance;
    }
    
 public static void ejemplo(Lista ls){
  Lista newLista=new Lista();
  Cola cola=new Cola();
  Pila pila=new Pila();
  while(!ls.esVacia()){
      Object elem=ls.recuperar(1);
      pila.apilar(elem);
      cola.poner(elem); 
   ls.eliminar(1);
  }
   System.out.println("pila: "+pila.toString()); //pila: [B,A]
          System.out.println("tope: "+pila.obtenerTope()); //tope: B
          System.out.println("cola: "+cola.toString());//cola: [A,B]
          System.out.println("frente: "+cola.obtenerFrente());//frente: A
 }
 
 public static Lista generaOtroTipo1Lista(Lista ls){
  //Este metodo reciba por parametro una lista ls con elementos de tipo char, con el formato
  // a1#a2#..#an donde cada ai es una sucesion de letras, y debe generar como salida
  // otra Lista de la forma  a1a1^#a2a2^#...#anan^ donde ai^ es la secuencia de letras de ai invertida.
  //Ejemplo: si ls es AB#C#DEF, la operacion devolvera BAAB#CC#FEDDEF
  Lista newLista=new Lista();
  int i=1,m=0;
   Object elemento;
  Lista clon=ls.clone();
  while(!clon.esVacia()){
       elemento=clon.recuperar(1);
       
      if(elemento.equals('#')){
          m++;
          newLista.insertar(elemento, m);
          i=m+1;
      }else{
        m=m+2;
      newLista.insertar(elemento, i);
      newLista.insertar(elemento, m);
      }
       
      clon.eliminar(1);
  }
  
  return newLista;
}
 
 public static Lista colaToLista(Cola c,int t){
 // Este metodo recibe por parametro un numero entero t  y una estructura Cola
 // con una sucesion de digitos(que fueron ingresados como tipo char de java)
 // y, sin destruit la estuctura original, genera y retorna una lista con la 
 // forma a1a1^a1$a2a2^a2$...$anan^an donde ai es una secuencia de t elementos
 //tomada de la estructura original y cada ai^ es la secuencia ai invertida.
 //La ultima subcadena puede ser de longitud menor a t.
 //Ejemplo: Si la estructura de entrada es: <0,1,2,3,4,5,6,7,8,9> y t es 4 la 
 // operacion colaToLista devuelve una estructura lista con los siguientes elementos
 // [0,1,2,3,3,2,1,0,0,1,2,3,$,4,5,6,7,7,6,5,4,4,5,6,7,$,8,9,9,8,8,9]
 
 Lista newLista=new Lista();
 int cant=1,i=0;
 Object elemento,elemPila,elemCola;
  Cola clon=c.clone();
  Pila pila1=new Pila();
  Cola cola1=new Cola();
  while(!clon.esVacia()){
       while(cant<=t){
           
           elemento=clon.obtenerFrente();
           if(!clon.esVacia()){
           newLista.insertar(elemento, newLista.longitud()+1);
           cola1.poner(elemento);
           pila1.apilar(elemento);
           cant++;
           clon.sacar();
          
       }
       while(!pila1.esVacia()){
           elemPila=pila1.obtenerTope();
           newLista.insertar(elemPila, newLista.longitud()+1);
           pila1.desapilar();
       }
       while(!cola1.esVacia()){
           elemCola=cola1.obtenerFrente();
           newLista.insertar(elemCola, newLista.longitud()+1);
           cola1.sacar();
           i++;
           if(i%t==0){
               newLista.insertar('$', newLista.longitud()+1);
           }
       }
       
       cant=1;
      
  }
  }
  return newLista;
 }
}

