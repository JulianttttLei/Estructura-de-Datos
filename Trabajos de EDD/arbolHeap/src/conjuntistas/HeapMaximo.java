/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author agust
 */
public class HeapMaximo {
    
      private static final int TAMANIO = 11;
    private final Comparable[] heap;
    private int ultimo;

    public HeapMaximo() {
        this.ultimo = 0;
        this.heap = new Comparable[TAMANIO];
    }
    //insertar 

    public boolean insertar(Comparable elem) {
        // Este metodo recibe un elemento y lo inserta en el arbol. Si la operacion termina con exito
        //devuelve true y false en caso contrario
        boolean exito;

        if (this.ultimo + 1 <= TAMANIO) {
            this.heap[ultimo + 1] = elem;
            this.ultimo++;
            ubicarElemento(this.ultimo);
            exito = true;
        } else {
            //si ultimo(cantidad de celdas utilizadas) se pasa del tamaño del arreglo
            exito = false;
        }
        return exito;
    }

    private void ubicarElemento(int posHijo) {
        boolean salir = false;
        int posPadre;
        Comparable aux = this.heap[posHijo];
        while (!salir) {
            posPadre = posHijo / 2;
            if (posPadre > 0) {
             
                if (this.heap[posHijo].compareTo(this.heap[posPadre]) > 0) {

                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = aux;
                    posHijo = posPadre;
                } else {
                    //si el padre es menor que su hijo
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

// eliminar
    public boolean eliminarCima() {
        // Este metodo elemina el elemento de la raiz(o cima del monticulo). Si la operacion termina
        // con exito devuelve true y false en caso contrario
        boolean exito;
        if (this.ultimo == 0) {
            //estructura vacia
            exito = false;
        } else {
            //saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            //reestablece la propiedad de heap minimo
            hacerSubir(1);
            exito = true;
        }
        return exito;
    }

    private void hacerSubir(int posPadre) {
        int posHijo;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posHijo = posPadre * 2;//2
            if (posHijo <= this.ultimo) {
               

                if (posHijo < this.ultimo) {
                  

                    if (this.heap[posHijo+1].compareTo(this.heap[posHijo]) > 0) {
                     
                        posHijo++;
                    }
                }
     
                if (this.heap[posHijo].compareTo(temp) > 0) {
    
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = temp;
                    posPadre = posHijo;
                } else {
                    //el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }
    //recuperar cima

    public Comparable recuperarCima() {
        //Este metodo devuelve el elemento que esta en la raiz(cima del monticulo)
        //Precondicion: el arbol no esta vacio
        Comparable elem = null;

        if (this.ultimo > 0) {//el arbol tiene algun elemento cargado
            elem = this.heap[1];

        }
        return elem;
    }

    //esVacio
    public boolean esVacio() {
        //Este metodo devuelve false si hay al menos un elemento cargado en la tabla 
        //y true en caso contrario
        boolean vacio = false;
        if (this.ultimo == 0) {
            vacio = true;
        }
        return vacio;
    }
    //Agregamos los metodos  vaciar, clone y toString

    //vaciar 
    public void vaciar() {
        //Este metodo vacia por completo la estructura
        this.ultimo = 0;
    }

    //clone
    public HeapMaximo clone() {
        //Este metodo hace una copia de la estructura originial 
        HeapMaximo clonHeap = new HeapMaximo();
        clonHeap.ultimo = this.ultimo;
        while (clonHeap.ultimo > 0) {
            clonHeap.heap[clonHeap.ultimo] = this.heap[clonHeap.ultimo];
            clonHeap.ultimo--;
        }
        clonHeap.ultimo = this.ultimo;
        return clonHeap;
    }

    //toString 
    @Override
     
    
    public String toString() {
        //Este metodo crea y genera una cadena de caracteres, 
        //imprime el padre con su hijo izquierdo su hijo derecho
        String cadena = "";
        int pos = 1, hijoIzq, hijoDer;
        if (!esVacio()) {
            while (pos <= this.ultimo) {
                hijoIzq = 2 * pos;
                
                cadena +="\n" +this.heap[pos] + ":";
                
                if (this.heap[pos] != null && hijoIzq <= this.ultimo) {

                    cadena += " HI--> " + this.heap[hijoIzq];
                } else {

                    cadena += " HI--> - ";
                }
                hijoDer = 2 * pos + 1;

                if (this.heap[pos] != null && hijoDer <= this.ultimo) {
                    cadena += " HD--> " + this.heap[hijoDer];
                } else {

                    cadena += " HD--> - " ;
                
                }
                
                pos++;
            }
        } else {
            cadena = "Arbol vacio";
        }

        return cadena;
    }

}
