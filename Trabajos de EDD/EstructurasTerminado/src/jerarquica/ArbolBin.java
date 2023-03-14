/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquica;

import dinamicas.Lista;
import dinamicas.Cola;

/**
 *
 * @author Administrador
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    // insertar
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        // Este metodo inserta elemNuevo como hijo del primer nodo encontrado en preorden 
        // igual a elemPadre, como hijo izquierdo (I) o derecho (D), segun
        // lo indique el parametro lugar

        boolean exito = true;

        if (this.raiz == null) {
            // si el arbol esta vacio, ponemos el elem nuevo en la raiz 
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // si no esta vacio, se busca al padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    //si el padre existe y no tiene HI se lo agrega
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else {
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        // si el padre existe y no tiene HD se lo agrega
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                    } else {
                        // si el padre no existe o ya tiene ese hijo, da error
                        exito = false;
                    }

                }
            } else {
                exito = false;
            }
        }
        return exito;

    }

    private NodoArbol obtenerNodo(NodoArbol nodo, Object buscado) {
        // Este metodo privado busca un elemento y devuelve el nodo que lo contiene.
        // Si no se encuentra buscado devuelve null

        NodoArbol resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                //si el resultado es nodo, lo devuelve
                resultado = nodo;

            } else {
                //no es el buscado: busca primero en el HI
                resultado = obtenerNodo(nodo.getIzquierdo(), buscado);
                // si no lo encuentra en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(nodo.getDerecho(), buscado);
                }

            }

        }
        return resultado;

    }

    // es vacio
    public boolean esVacio() {
        // Este metodo determina si el arbol es vacio o no
        boolean vacio = true;// Devuelve true si no hay elementos en el arbol
        if (this.raiz != null) {//Devuelve false si al menos hay un elemento cargado en el arbol
            vacio = false;
        }
        return vacio;
    }

    //  altura
    public int altura() {
        // Este metodo devuelve la altura del arbol, es decir la longitud del camino mas largo
        // desde la raiz hasta una hoja
        NodoArbol nodo = this.raiz;
        int altura = -1;
        return obtenerAltura(nodo, 0, altura);

    }

    private int obtenerAltura(NodoArbol nodo, int nivel, int altura) {
        // Este es un metodo recursivo privado 

        if (nodo != null) {
            altura = obtenerAltura(nodo.getIzquierdo(), nivel + 1, altura);
            if (nivel > altura) {
                altura = nivel;
            }
            altura = obtenerAltura(nodo.getDerecho(), nivel + 1, altura);
        }

        return altura;
    }

    public int nivel(Object elemento) {
        // Este metodo devuelve el nivel de un elemento en el arbol

        NodoArbol nodo = this.raiz;
        return obtenerNivel(elemento, nodo, 0);
    }

    private int obtenerNivel(Object elemento, NodoArbol nodo, int nivel) {
        //Este es un metodo recursivo privado
        // Devuelve -1 si el elemento no existe en el arbol
        int nivelFin = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {// si elemento es igual al  nodo del elemento
                nivelFin = nivel;

            } else {
                nivelFin = obtenerNivel(elemento, nodo.getIzquierdo(), nivel + 1);
                if (nivelFin == -1) {
                    nivelFin = obtenerNivel(elemento, nodo.getDerecho(), nivel + 1);
                }
            }
        }
        return nivelFin;

    }

    //padre 
    public Object padre(Object elemento) {
        // En este metodo, dado un elemento devuelve un valor almacenado en su nodo padre
        // (busca la primera aparicion de elemento)

        return obtenerPadre(elemento, this.raiz);
    }

    private Object obtenerPadre(Object hijo, NodoArbol nodo) {
        Object padre = null;
        if (nodo != null) {
            if ((nodo.getIzquierdo() != null && nodo.getIzquierdo().getElem().equals(hijo))
                    || (nodo.getDerecho() != null && nodo.getDerecho().getElem().equals(hijo))) {

                padre = nodo.getElem();

            } else {
                // si estamos en la raiz es null
                if (nodo.getElem().equals(hijo)) {
                    padre = null;

                } else {

                    padre = obtenerPadre(hijo, nodo.getIzquierdo());
                    if (padre == null) {
                        padre = obtenerPadre(hijo, nodo.getDerecho());
                    }
                }
            }
        }

        return padre;
    }

    //listar preorden
    public Lista listarPreorden() {
        // Este metodo devuelve una lista con los elementos del arbol binario en el recorrido en preorden
        Lista lista = new Lista();
        obtenerListaPreorden(this.raiz, lista);
        return lista;
    }

    private void obtenerListaPreorden(NodoArbol nodo, Lista l) {
        // Este es un metodo recursivo privado 

        // se inserta el elemento del nodo a la lista
        l.insertar(nodo.getElem(), l.longitud() + 1);

        if (nodo.getIzquierdo() != null) {
            obtenerListaPreorden(nodo.getIzquierdo(), l);
        }
        if (nodo.getDerecho() != null) {
            obtenerListaPreorden(nodo.getDerecho(), l);
        }

    }

    // listar en inorden
    public Lista listarInorden() {
        // Este metodo devuelve una lista con los elementos del arbol binario en el recorrido en inorden
        Lista lista = new Lista();
        obtenerListaInorden(this.raiz, lista);
        return lista;
    }

    private void obtenerListaInorden(NodoArbol nodo, Lista l1) {
        // Este es un metodo recursivo privado 

        if (nodo.getIzquierdo() != null) {
            obtenerListaInorden(nodo.getIzquierdo(), l1);
        }

        // se inserta el elemento del nodo a la lista
        l1.insertar(nodo.getElem(), l1.longitud() + 1);

        if (nodo.getDerecho() != null) {
            obtenerListaInorden(nodo.getDerecho(), l1);
        }

    }

// listar en posorden
    public Lista listarPosorden() {
        // Este metodo devuelve una lista con los elementos del arbol binario en el recorrido en posorden
        Lista lista = new Lista();
        obtenerListaPosorden(this.raiz, lista);
        return lista;
    }

    private void obtenerListaPosorden(NodoArbol nodo, Lista l2) {
        // Este es un metodo recursivo privado 

        if (nodo.getIzquierdo() != null) {
            obtenerListaPosorden(nodo.getIzquierdo(), l2);
        }

        if (nodo.getDerecho() != null) {
            obtenerListaPosorden(nodo.getDerecho(), l2);
        }

        // se inserta el elemento del nodo a la lista
        l2.insertar(nodo.getElem(), l2.longitud() + 1);
    }

    // listar en niveles
    public Lista listaNiveles() {
        // Este metodo devuelve una lista con los elementos del arbol binario en el recorrido por niveles
        Lista lista = new Lista();
        // La cola es una cola de nodos
        Cola q = new Cola();
        NodoArbol nodoActual;

        q.poner(this.raiz);

        while (!q.esVacia()) {

            nodoActual = (NodoArbol) q.obtenerFrente();// obtenemos el nodo
            q.sacar();

            // visita al nodo
            lista.insertar(nodoActual.getElem(), lista.longitud() + 1);

            if (nodoActual.getIzquierdo() != null) {
                q.poner(nodoActual.getIzquierdo());

            }
            if (nodoActual.getDerecho() != null) {
                q.poner(nodoActual.getDerecho());

            }

        }

        return lista;
    }

    //clone
    public ArbolBin clone() {
        // Este metodo genera y devuelve un arbol binario que es equivalente al arbol original
        ArbolBin clon = new ArbolBin();

        if (this.raiz != null) {
            clon.raiz = (obtenerClone(this.raiz));
        }
        return clon;

    }

    private NodoArbol obtenerClone(NodoArbol nodo) {
        // System.out.println("El nodo es: "+nodo.getElem());

        NodoArbol nodoNew = new NodoArbol(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {

            nodoNew.setIzquierdo(obtenerClone(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nodoNew.setDerecho(obtenerClone(nodo.getDerecho()));
        }

        return nodoNew;
    }
    // toString

    @Override
    public String toString() {
        //Este metodo genera y devuelve una cadena de caracteres que indica cual 
        // es la raiz del arbol y quienes son los hijos del arbol
        String cadena = "Arbol Vacio";
        if (!esVacio()) {
            cadena = obtenerToString(this.raiz);
        }
        return cadena;

    }

    private String obtenerToString(NodoArbol nodo) {
        String cadena = "";
        if (nodo != null) {

            cadena += " Nodo " + nodo.getElem();
            if (nodo.getIzquierdo() != null) {

                cadena += " HI: " + nodo.getIzquierdo().getElem();
            } else {
                cadena += " HI: " + null;
            }
            if (nodo.getDerecho() != null) {

                cadena += " HD: " + nodo.getDerecho().getElem() + " \n";
            } else {
                cadena += " HD: " + null + "\n";

            }

            cadena = cadena + obtenerToString(nodo.getIzquierdo());
            cadena = cadena + obtenerToString(nodo.getDerecho());

        }
        return cadena;

    }
    // vaciar

    public void vaciar() {
        // Este metodo quita todos los elementos de la estructura 
        this.raiz = null;
    }

}
