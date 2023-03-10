/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author agust
 */
public class Diccionario {

    private NodoAVLDicc raiz;

    public Diccionario() {
        this.raiz = null;
    }

    // insertar
    public boolean insertar(Comparable clave, Object dato) {
        //Este metodo recibe la clave que es única y el dato (informacion asociada a ella). Si no existe en la
        // estructura un elemento con igual clave, agrega el par (clave, dato) a la estructura. Si la operacion
        // termina con exito devuelve verdadero y falso en caso contrario
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(clave, dato, null, null);
        } else {

            exito = insertarAux(this.raiz, null, clave, dato);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVLDicc nodo, NodoAVLDicc padre, Comparable clave, Object dato) {
        //Precondicion: nodo no es nulo
        boolean exito = true;
        if (clave.compareTo(nodo.getClave()) == 0) {
            //Error: clave repetida (la clave es unica)
            exito = false;
        } else {
            if (clave.compareTo(nodo.getClave()) < 0) {
                //clave es menor que nodo.getClave()
                //Si tiene HI baja a la izquierda, sino agrega clave
                if (nodo.getIzquierdo() != null) {
                    exito = insertarAux(nodo.getIzquierdo(), nodo, clave, dato);
                } else {
                    nodo.setIzquierdo(new NodoAVLDicc(clave, dato, null, null));
                }
            } else {
                //clave es mayor que nodo.getClave()
                // si tiene HD baja a la derecha, sino agrega clave
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), nodo, clave, dato);
                } else {
                    nodo.setDerecho(new NodoAVLDicc(clave, dato, null, null));
                }
            }
        }
        //Se analiza si la estructura hasta ahora creado presenta algun desbalance
        if (exito) {

            balancear(nodo, padre);

        }

        return exito;
    }

    private void balancear(NodoAVLDicc nodo, NodoAVLDicc padre) {
        //Recalculo la altura del nodo
        nodo.recalcularAltura();
        NodoAVLDicc nodoAux = chequeaRotacion(nodo);
        if (nodo == this.raiz) {
            this.raiz = nodoAux;
        } else {
            if (padre.getClave().compareTo(nodoAux.getClave()) > 0) {
                padre.setIzquierdo(nodoAux);
            } else {
                padre.setDerecho(nodoAux);
            }

        }
    }

    private NodoAVLDicc chequeaRotacion(NodoAVLDicc nodo) {
        int balancePadre = calculaBalance(nodo), balanceHijo;
        NodoAVLDicc nodoAux = nodo;
        //el nodo padre esta caido a la izquierda
        if (balancePadre == 2) { //VER LO DE NODO.IZQUIERDO
            balanceHijo = calculaBalance(nodo.getIzquierdo());
            if (balanceHijo >= 0) {
                //Su hijo izquierdo esta caido hacia el mismo lado
                //Se aplica Rotacion simple a derecha
                nodoAux = rotarDerecha(nodo);
            } else {
                //Su hijo izquierdo esta caido hacia el lado contrario
                //Se aplica Rotacion doble izquierda-derecha
                nodoAux = rotarIzqDer(nodo);
            }
        } else {
            //el nodo padre esta caido a la derecha
            if (balancePadre == -2) {
                balanceHijo = calculaBalance(nodo.getDerecho());
                if (balanceHijo <= 0) {
                    //Su hijo derecho esta caido hacia el mismo lado
                    //Se aplica Rotacion simple a izquierda
                    nodoAux = rotarIzquierda(nodo);
                } else {
                    //Su hijo derecho esta caido hacia el lado contrario
                    //Se aplica Rotacion doble derecha-izquierda
                    nodoAux = rotarDerIzq(nodo);
                }
            }
        }
        return nodoAux;
    }

    //calculaBalance
    private int calculaBalance(NodoAVLDicc nodo) {
        //Este metodo calcula en balance de un nodo
        int balance, alturaHijoIzq, alturaHijoDer;
        alturaHijoIzq = -1;
        alturaHijoDer = -1;

        if (nodo.getIzquierdo() != null) {

            alturaHijoIzq = nodo.getIzquierdo().getAltura();
        }
        if (nodo.getDerecho() != null) {

            alturaHijoDer = nodo.getDerecho().getAltura();
        }
        balance = alturaHijoIzq - alturaHijoDer;//Calcula el balance
        return balance;
    }

    // Rotacion simple a izquierda
    private NodoAVLDicc rotarIzquierda(NodoAVLDicc nodoPivote) {
        //Este metodo rota a la izquierda cuando el balance padre es -2 
        // y balance hijo es -1 o 0
        NodoAVLDicc hijoDer, temp;
        hijoDer = nodoPivote.getDerecho();//Corresponde al hijo derecho de nodoPivote

        temp = hijoDer.getIzquierdo(); //Corresponde al hijo izquierdo de hijoDer

        hijoDer.setIzquierdo(nodoPivote);//Cambiamos el hijo izquierdo de hijoDer por nodoPivote

        nodoPivote.setDerecho(temp);//Cambiamos al hijo derecho de nodoPivote por temp

        nodoPivote.recalcularAltura();

        hijoDer.recalcularAltura();

        return hijoDer;//Retorna la nueva raiz del subarbol
    }

    //Rotacion simple a la derecha
    private NodoAVLDicc rotarDerecha(NodoAVLDicc nodoPivote) {
        //Este metodo rota a la derecha cuando el balance padre es 2 
        //y balance hijo 1 o 0
        NodoAVLDicc hijoIzq, temp;

        hijoIzq = nodoPivote.getIzquierdo(); //Corresponde al hijo izquierdo de nodoPivote

        temp = hijoIzq.getDerecho();//Corresponde al hijo derecho de hijoIzq

        hijoIzq.setDerecho(nodoPivote);//Cambiamos el hijo derecho de hijoIzq por nodoPivote

        nodoPivote.setIzquierdo(temp);//Cambiamos el hijo izquierdo de nodoPivote por temp

        nodoPivote.recalcularAltura();

        hijoIzq.recalcularAltura();

        return hijoIzq;//Retorna la nueva raiz del subarbol        
    }

    //Rotacion doble derecha-izquierda
    private NodoAVLDicc rotarDerIzq(NodoAVLDicc nodo) {
        //Este metodo realiza una rotacion a la derecha y luego otra a izquierda cuando el
        //balance padre es -2 y balance hijo es 1
        NodoAVLDicc nodoPivote, nodoAux2, nodoAux1;

        nodoPivote = nodo.getDerecho();//es el hijo derecho de nodo

        nodo.setDerecho(rotarDerecha(nodoPivote));//se realiza la primera rotacion a la derecha

        nodoAux1 = nodo; //es el nodo pivote de la segunda rotacion

        nodoAux2 = rotarIzquierda(nodoAux1);//queda como el nuevo nodo padre al realizar
        //la segunda rotacion a la izquierda         
        return nodoAux2;
    }

    // Rotacion doble izquierda-derecha
    private NodoAVLDicc rotarIzqDer(NodoAVLDicc nodo) {
        //Este metodo realiza una rotacion a izquierda y luego otra rotacion a derecha cuando el
        //balance padre es 2 y balance hijo es -1
        NodoAVLDicc nodoPivote, nodoAux2, nodoAux1;

        nodoPivote = nodo.getIzquierdo(); //es el hijo izquierdo de nodo

        nodo.setIzquierdo(rotarIzquierda(nodoPivote)); //se realiza la primera rotacion a la izquierda

        nodoAux1 = nodo;//es el nodo pivote de la segunda rotacion

        nodoAux2 = rotarDerecha(nodoAux1); //queda como el nuevo nodo padre al realizar la segunda rotacion
        // a la derecha
        return nodoAux2;
    }

    //eliminar
    public boolean eliminar(Comparable clave) {
        //Este metodo elimina el elemento cuya clave sea la recibida por parametro. 
        //Si lo encuentra y la operacion de eliminacion termina con exito devuelve
        // verdadero y falso en caso contrario
        return eliminarAux(this.raiz, clave, null);
    }

    private boolean eliminarAux(NodoAVLDicc nodo, Comparable clave, NodoAVLDicc padre) {
        boolean elimina = false;
        if (nodo != null) {
            if (clave.compareTo(nodo.getClave()) < 0) {
                elimina = eliminarAux(nodo.getIzquierdo(), clave, nodo);
            } else {
                if (clave.compareTo(nodo.getClave()) > 0) {
                    elimina = eliminarAux(nodo.getDerecho(), clave, nodo);
                } else {
                    if (clave.compareTo(nodo.getClave()) == 0) {
                        elimina = true;
                        if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                            //CASO 1: si el elemento a eliminar es una HOJA
                            eliminarHoja(nodo, padre);
                        } else {
                            if ((nodo.getDerecho() != null && nodo.getIzquierdo() == null)
                                    || (nodo.getDerecho() == null && nodo.getIzquierdo() != null)) {
                                //CASO 2: si el elemento tiene un hijo
                                eliminarCon1Hijo(nodo, padre);
                            } else {
                                if (nodo.getDerecho() != null && nodo.getIzquierdo() != null) {
                                    //CASO 3: si el elemento tiene 2 hijos
                                    NodoAVLDicc candidato = nodo.getDerecho(), padreCandidato = nodo;
                                    eliminarCon2Hijos(candidato, padreCandidato, nodo);
                                }
                            }
                        }
                    }
                }
            }
            //Si el elemento es eliminado, en algunos casos se aplica una rotacion o si un nodo tiene balance
            //padre -2 o 2 tambien se aplica la rotacion
            nodo.recalcularAltura();
            int balance = calculaBalance(nodo);
            if (Math.abs(balance) == 2) {
                balancear(nodo, padre);
            }
        }
        return elimina;
    }

    //CASO 1: eliminarHoja
    private void eliminarHoja(NodoAVLDicc hijoHoja, NodoAVLDicc padre) {
        //caso especial: cuando la estructura tiene 1 solo elemento
        if (padre == null) {
            this.raiz = null;
        } else {
            //caso comun: 
            if (hijoHoja.getClave().compareTo(padre.getClave()) < 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    //CASO 2: eliminarCon1Hijo        
    private void eliminarCon1Hijo(NodoAVLDicc hijo, NodoAVLDicc padre) {
        //caso especial:
        if (padre == null) {
            if (hijo.getDerecho() != null) {
                this.raiz = hijo.getDerecho();
            } else {
                this.raiz = hijo.getIzquierdo();
            }
        } else {
            NodoAVLDicc nieto = null;
            if (nieto == hijo.getDerecho()) {
                nieto = hijo.getIzquierdo();

            } else {
                nieto = hijo.getDerecho();
            }
            if (padre.getDerecho().getClave().compareTo(hijo.getClave()) == 0) {
                padre.setDerecho(nieto);
            } else {

                padre.setIzquierdo(nieto);
            }

        }
    }

    //CASO 3: eliminarCon2Hijos 
  
    private void eliminarCon2Hijos(NodoAVLDicc candidato, NodoAVLDicc padreCandidato, NodoAVLDicc nodo) {

        if (candidato.getIzquierdo() != null) {
            padreCandidato = candidato;
            candidato = candidato.getIzquierdo();
            eliminarCon2Hijos(candidato, padreCandidato, nodo);

            NodoAVLDicc padre = padreCandidato, nodoAux = candidato;

            int balance = calculaBalance(nodoAux);
            if (Math.abs(balance) == 2) {
                balancear(nodoAux, padre);
            }
        } else {

            nodo.setClave(candidato.getClave());
            nodo.setDato(candidato.getDato());
            //caso especial:
            if (nodo.getDerecho() == candidato) {

                nodo.setDerecho(candidato.getDerecho());

            } else {
                //caso comun
                padreCandidato.setIzquierdo(candidato.getDerecho());

            }

        }

    }

    public boolean existeClave(Comparable clave) {
        //Este metodo devuelve verdadero si en la estructura se encuentra almacenado
        //un elemento con la clave recibida por parametro, caso contrario 
        //devuelve falso
        return existeClaveAux(this.raiz, clave);
    }

    private boolean existeClaveAux(NodoAVLDicc nodo, Comparable clave) {
        boolean existe = false;
        if (nodo != null) {
            if (clave.compareTo(nodo.getClave()) == 0) {
                existe = true;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    existe = existeClaveAux(nodo.getIzquierdo(), clave);
                } else {
                    existe = existeClaveAux(nodo.getDerecho(), clave);
                }
            }
        }
        return existe;
    }

    public Object obtenerInformacion(Comparable clave) {
        //Si la estructura se encuentra almacenado un elemento con la clave recibida por 
        //parametro, devuelve la informacion asociada a ella. Precondicion: si no existe un
        //elemento con esa clave no se puede asegurar el funcionamiento de la operacion
        Object dato;
        dato = obtenerInfoAux(this.raiz, clave);
        if (dato == null) {
            System.out.println("No se puede asegurar el funcionamiento de la operacion");
        }
        return dato;
    }

    private Object obtenerInfoAux(NodoAVLDicc nodo, Comparable clave) {
        Object dato = null;
        if (nodo != null) {
            if (clave.compareTo(nodo.getClave()) == 0) {
                dato = nodo.getDato();
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    dato = obtenerInfoAux(nodo.getIzquierdo(), clave);
                } else {
                    dato = obtenerInfoAux(nodo.getDerecho(), clave);
                }
            }
        }
        return dato;
    }

    //listarClaves
    public Lista listarClaves() {
        //Este metodo recorre la estructura completa y devuelve una lista de ordenada 
        //con las claves de los elementos que se encuentran almacenados en ella
        Lista ls = new Lista();
        listarClavesAux(ls, this.raiz);
        return ls;
    }

    private void listarClavesAux(Lista lista, NodoAVLDicc nodo) {
        if (nodo != null) {
            listarClavesAux(lista, nodo.getIzquierdo());
            lista.insertar(nodo.getClave(), lista.longitud() + 1);
            listarClavesAux(lista, nodo.getDerecho());
        }
    }

    //listarDatos
    public Lista listarDatos() {
        //Este metodo recorre la estructura completa y devuelve una lista ordenada
        // con la informacion asociada de los elementos que se encuentran almacenados en ella
        Lista ls = new Lista();
        listarDatosAux(ls, this.raiz);
        return ls;
    }

    private void listarDatosAux(Lista lista, NodoAVLDicc nodo) {
        if (nodo != null) {
            listarDatosAux(lista, nodo.getIzquierdo());
            lista.insertar(nodo.getDato(), lista.longitud() + 1);
            listarDatosAux(lista, nodo.getDerecho());
        }
    }

    //esVacio
    public boolean esVacio() {
        //Este metodo devuelve falso si hay al menos un elemento cargado en la estructura
        // y verdadero en caso contrario
        boolean vacio = false;
        if (this.raiz == null) {
            vacio = true;
        }
        return vacio;
    }

    //vaciar
    public void vaciar() {
        //Este metodo vacia por completo la estructura
        this.raiz = null;
    }

    //clonar
    public Diccionario clonar() {
// Este metodo genera y devuelve un arbol binario que es equivalente al arbol original
        Diccionario clon = new Diccionario();
        if (this.raiz != null) {
            clon.raiz = (cloneAux(this.raiz));
        }
        return clon;
    }

    private NodoAVLDicc cloneAux(NodoAVLDicc nodo) {
        NodoAVLDicc nodoNew = new NodoAVLDicc(nodo.getClave(), nodo.getDato(), null, null);
        if (nodo.getIzquierdo() != null) {
            nodoNew.setIzquierdo(cloneAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nodoNew.setDerecho(cloneAux(nodo.getDerecho()));
        }
        return nodoNew;
    }

    //toString 
    @Override
    public String toString() {
        //Este metodo genera y devuelve una cadena de caracteres que indica cual 
        // es la raiz del arbol y quienes son los hijos del diccionario
        String cadena = "Diccionario Vacio";
        if (!esVacio()) {
            cadena = obtenerToString(this.raiz);
        }
        return cadena;
    }

    private String obtenerToString(NodoAVLDicc nodo) {
        String cadena = "";

        if (nodo != null) {

            cadena += " Clave: " + nodo.getClave();
            if (nodo.getIzquierdo() != null) {

                cadena += " HI: " + nodo.getIzquierdo().getClave();

            } else {
                cadena += " HI: " + null;
            }
            if (nodo.getDerecho() != null) {

                cadena += "  HD:  " + nodo.getDerecho().getClave() + "\n";
            } else {
                cadena += "  HD: " + null + "\n";
            }
            cadena = cadena + obtenerToString(nodo.getIzquierdo());
            cadena = cadena + obtenerToString(nodo.getDerecho());
        }
        return cadena;
    }

    public Lista listarRango(Comparable codClave1, Comparable codClave2) {
        //Este método recorre parte del árbol(sólo lo necesario) y devuelve una lista
        //ordenada con los elementos que se encuentran en el intervalo [elemMinimo,elemMaximo].
        Lista listaRan = new Lista();
        if (codClave1.compareTo(codClave2) <= 0) {
            listarRangoAux(codClave1, codClave2, this.raiz, listaRan);
        } else {
            listarRangoAux(codClave2, codClave1, this.raiz, listaRan);
        }
        return listaRan;
    }

    private void listarRangoAux(Comparable elemMin, Comparable elemMax, NodoAVLDicc nodo, Lista ls) {
        if (nodo != null) {
            if (elemMin.compareTo(nodo.getClave()) < 0) {
                listarRangoAux(elemMin, elemMax, nodo.getIzquierdo(), ls);
            }
            if (elemMax.compareTo(nodo.getClave()) >= 0 && elemMin.compareTo(nodo.getClave()) <= 0) {
                ls.insertar(nodo.getClave(), ls.longitud() + 1);
            }
            if (elemMax.compareTo(nodo.getClave()) > 0) {
                listarRangoAux(elemMin, elemMax, nodo.getDerecho(), ls);
            }
        }
    }
}
