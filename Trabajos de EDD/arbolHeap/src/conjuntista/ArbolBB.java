/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntista;

import dinamicas.Lista;

/**
 *
 * @author Administrador
 */
public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    //insertar
    public boolean insertar(Comparable elem) {
        //Este método recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento
        //ya se encuentra en el árbol no se realiza la inserción. Devuelve true si el elemento
        //se agrega a la estructura y false en caso contrario.

        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elemento) {
        //Precondicion: nodo no es nulo
        boolean exito = true;

        if (elemento.compareTo(nodo.getElem()) == 0) {
            //Error: elemento repetido
            exito = false;
        } else {
            if (elemento.compareTo(nodo.getElem()) < 0) {
                //elemento es menor que nodo.getElem()
                // si tiene HI baja a la izquierda,sino agrega elemento
                if (nodo.getIzquierdo() != null) {
                    exito = insertarAux(nodo.getIzquierdo(), elemento);
                } else {
                    nodo.setIzquierdo(new NodoABB(elemento, null, null));
                }
            } else {
                //elemento es mayor que nodo.getElem()
                //si tiene HD baja a la derecha, sino agrega elemento
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), elemento);
                } else {
                    nodo.setDerecho(new NodoABB(elemento, null, null));
                }

            }
        }
        return exito;
    }

      //listarRango
    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
       //Este método recorre parte del árbol(sólo lo necesario) y devuelve una lista
        //ordenada con los elementos que se encuentran en el intervalo [elemMinimo,elemMaximo].
        Lista listaRan = new Lista();
        listarRangoAux(elemMinimo, elemMaximo, this.raiz, listaRan);
        return listaRan;
    }

    private void listarRangoAux(Comparable elemMin, Comparable elemMax, NodoABB nodo, Lista ls) {
        if (nodo != null) {

            if (elemMin.compareTo(nodo.getElem()) < 0) {
                listarRangoAux(elemMin, elemMax, nodo.getIzquierdo(), ls);
            }
            if (elemMax.compareTo(nodo.getElem()) >= 0 && elemMin.compareTo(nodo.getElem()) <= 0) {
                ls.insertar(nodo.getElem(), ls.longitud() + 1);
            }
            if (elemMax.compareTo(nodo.getElem()) > 0) {
                listarRangoAux(elemMin, elemMax, nodo.getDerecho(), ls);
            }

        }
    }
    
   //eliminarMax
    public boolean eliminarMax() {
        return obtenerMax(this.raiz, null);
    }
   //obtenerMax
    private boolean obtenerMax(NodoABB nodo, NodoABB padreMax) {
        //Este metodo elimina el maximo elemento
        Comparable elemMax = null;
        NodoABB nodoMax = null;
        while (nodo != null) {
            elemMax = nodo.getElem();
            if (nodo.getDerecho() != null) {
                padreMax = nodo;
            }
            nodoMax = nodo;
            nodo = nodo.getDerecho();
        }
        return eliminarNodo(nodoMax, padreMax, elemMax);
    }
    //elimarMin
    public boolean eliminarMin() {

        return obtenerMin(this.raiz, null);
    }

//obtener minimo
    public boolean obtenerMin(NodoABB nodo, NodoABB padreMin) {
        // Este metodo elimina el minimo elemento
        Comparable elemMin = null;
        NodoABB nodoMin = null;

        while (nodo != null) {
            elemMin = nodo.getElem();
            if (nodo.getIzquierdo() != null) {
                padreMin = nodo;
            }
            nodoMin = nodo;
            nodo = nodo.getIzquierdo();
        }

        return eliminarNodo(nodoMin, padreMin, elemMin);

    }

//eliminarNodo
    private boolean eliminarNodo(NodoABB nodoElem, NodoABB padre, Comparable elemento) {
        // Este metodo elimina un nodo dado por parametro, utilizando el elemento 
        boolean exito = true;
        if (nodoElem.getIzquierdo() == null && nodoElem.getDerecho() == null) {
            //CASO 1:
            eliminarHoja(elemento, padre);
        } else {
            if ((nodoElem.getDerecho() == null && nodoElem.getIzquierdo() != null)
                    || (nodoElem.getIzquierdo() == null && nodoElem.getDerecho() != null)) {
                //CASO 2: 
                eliminarCon1Hijo(padre, nodoElem);
            } else {
                //CASO 3:
                eliminarCon2Hijos(nodoElem);

            }

        }

        return exito;
    }

// CASO1: elimiminarHoja
    private void eliminarHoja(Comparable elemento, NodoABB padre) {
        //caso especial: cuando  elemento  es 1  hoja y tiene un unico elemento
        if (padre == null) {
            this.raiz = null;
        } else {
            //caso comun: cuando tiene muchos elementos y el elemento es 1 hoja   
            if (elemento.compareTo(padre.getElem()) > 0) {
                padre.setDerecho(null);
            } else {
                if (elemento.compareTo(padre.getElem()) < 0) {
                    padre.setIzquierdo(null);
                }
            }
        }
    }

    //CASO2: eliminarCon1Hijo
    private void eliminarCon1Hijo(NodoABB padre, NodoABB hijo) {
        //caso especial:
        if (padre == null) {
            if (hijo.getDerecho() == null) {
                hijo.setElem(hijo.getIzquierdo().getElem());
                hijo.setIzquierdo(null);
            } else {
                if (hijo.getIzquierdo() == null) {
                    hijo.setElem(hijo.getDerecho().getElem());
                    hijo.setDerecho(null);
                }
            }
        } else {
            //caso comun:
            if (hijo.getDerecho() == null && hijo.getIzquierdo() != null) {
                padre.setDerecho(hijo.getIzquierdo());
            } else {
                if (hijo.getIzquierdo() == null && hijo.getDerecho() != null) {
                    padre.setIzquierdo(hijo.getDerecho());
                }
            }
        }

    }

    //CASO3: eliminarCon2Hijos
    private void eliminarCon2Hijos(NodoABB nodo) {
        NodoABB candidato = nodo.getDerecho(), padreCandidato = nodo;
        while (candidato.getIzquierdo() != null) {
            padreCandidato = candidato;
            candidato = candidato.getIzquierdo();
        }
        nodo.setElem(candidato.getElem());
        //caso especial
        if (nodo.getDerecho() == candidato) {
            nodo.setDerecho(candidato.getDerecho());
        } else {
            //caso comun
            padreCandidato.setIzquierdo(candidato.getDerecho());
        }
    }

//vaciar
    public void vaciar() {
//Este método vacia por completo la estructura.
        this.raiz = null;
    }

    //esVacio
    public boolean esVacio() {
//Este método devuelve false si hay al menos un elemento en el árbol y true en caso contrario.
        boolean vacio = true;
        if (this.raiz != null) {
            vacio = false;
        }
        return vacio;
    }

//toString
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

    private String obtenerToString(NodoABB nodo) {
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
    //clone

    public ArbolBB clone() {
        // Este metodo genera y devuelve un arbol binario que es equivalente al arbol original
        ArbolBB clon = new ArbolBB();

        if (this.raiz != null) {
            clon.raiz = (obtenerClone(this.raiz));
        }
        return clon;

    }

    private NodoABB obtenerClone(NodoABB nodo) {
    

        NodoABB nodoNew = new NodoABB(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {

            nodoNew.setIzquierdo(obtenerClone(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nodoNew.setDerecho(obtenerClone(nodo.getDerecho()));
        }

        return nodoNew;
    }
    

}
