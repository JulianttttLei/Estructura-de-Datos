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
public class grafoEtiquetado {

    /**
     * @param args the command line arguments
     */
    private NodoVert inicio;

    public grafoEtiquetado() {
        this.inicio = null;
    }

    //insertarVertice LISTO
    public boolean insertarVertice(Object elemento) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(elemento);
        if (aux == null) {
            this.inicio = new NodoVert(elemento, this.inicio, null);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object elemento) {
        //Este metodo recibe un elemento de tipoVertice, se lo agrega a la estructura controlando que no se inserten
        // vertices repetidos. Si puede realizar la insercion devuelve verdadero, en caso contrario false   
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(elemento)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public Object buscaElemento(Object elem) {
        Object buscado = elem, encontrado = null;
        NodoVert aux = this.ubicarVertice(buscado);
        if (aux != null) {
            encontrado = aux.getElem();
        }
        return encontrado;
    }

    //eliminarVertice
    public boolean eliminarVertice(Object elemento) {
        //Este metodo recibe un elemento de tipoVertice se lo quita de la estructura. Si se encuentra el vertice,
        //tambien debe eliminarse todos los arcos que lo tengan como origen o destino. Si se puede realizar la
        //eliminacion con exito devuelve verdadero, en caso contrario devuelve falso.
        boolean eliminado, encontrado = false;
        NodoVert aux = this.inicio, auxAnterior = null;
        int pos = 1;

        while (aux != null && !encontrado) {
            encontrado = aux.getElem().equals(elemento);

            if (!encontrado) {
                pos = 2;
                auxAnterior = aux;

                aux = aux.getSigVertice();

            }
        }
        eliminado = eliminarArcoVerticeAux(aux, elemento);
        if (eliminado) {
            eliminarVerticeAux(auxAnterior, pos);
        }
        return eliminado;
    }

    private boolean eliminarArcoVerticeAux(NodoVert aux, Object elemento) {
        boolean eliminado = false, sigue = false;
        NodoAdy ady, nodoAdy;
        NodoVert nodoVert;

        if (aux != null) {
            eliminado = true;
            ady = aux.getPrimerAdy();

            while (ady != null) {
                nodoVert = ady.getVertice();

                nodoAdy = nodoVert.getPrimerAdy();
                //caso primera posicion

                if (nodoAdy.getVertice().getElem().equals(elemento)) {

                    nodoVert.setPrimerAdy(nodoAdy.getSigAdyacente());

                } else {

                    while (nodoAdy.getSigAdyacente() != null && !sigue) {

                        if (nodoAdy.getSigAdyacente().getVertice().getElem().equals(elemento)) {

                            nodoAdy.setSigAdyacente(nodoAdy.getSigAdyacente().getSigAdyacente());
                            sigue = true;

                        } else {
                            nodoAdy = nodoAdy.getSigAdyacente();
                        }
                    }
                }
                sigue = false;
                ady = ady.getSigAdyacente();
            }
        }
        return eliminado;

    }

    private void eliminarVerticeAux(NodoVert auxAnterior, int pos) {

        if (pos == 1) {
            this.inicio = this.inicio.getSigVertice();
        } else {
            if (auxAnterior != null) {
                auxAnterior.setSigVertice(auxAnterior.getSigVertice().getSigVertice());
            }
        }
    }

    private NodoVert[] busca2Vertices(Object origen, Object destino) {
        //Este metodo busca los nodos vertices de los elementos origen y destino, y los almacena en un arreglo
        NodoVert aux = this.inicio;
        NodoVert nodo[];

        nodo = new NodoVert[2];

        while (aux != null && (nodo[0] == null || nodo[1] == null)) {

            if (aux.getElem().equals(origen)) {
                nodo[0] = aux;
            }
            if (aux.getElem().equals(destino)) {
                nodo[1] = aux;
            }

            aux = aux.getSigVertice();

        }
        return nodo;
    }

    //insertarArco 
    public boolean insertarArco(Object origen, Object destino, int etiqueta) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino) agrega el arco en la estructura,
        //solo si ambos vertices ya  existen en el grafo. Si el arco existe y se puede realizar la insercion devuelve verdadero, en
        //caso contrario devuelve falso
        boolean exito = false;

        NodoVert nodoVertice[], nodoO, nodoD;

        //Verifica si ambos vertices existen en el grafo,EVITAR HACER DOS RECORRIDOS
        nodoVertice = busca2Vertices(origen, destino);
        nodoO = nodoVertice[0];
        nodoD = nodoVertice[1];

        if (nodoO != null && nodoD != null) {

            exito = insertarArcoAux(nodoO, nodoD, etiqueta)
                    && insertarArcoAux(nodoD, nodoO, etiqueta);

        }

        return exito;
    }

    private boolean insertarArcoAux(NodoVert verticeO, NodoVert verticeD, int etiq) {
        boolean exito = true;

        NodoAdy ady;
        //Agregamos el arco:
        //obtenemos el primer nodo adyacente 
        ady = verticeO.getPrimerAdy();
        // si el nodo adyacente es nulo entonces
        if (ady == null) {
            // se agrega el primer arco 
            verticeO.setPrimerAdy(new NodoAdy(verticeD, null, etiq));
        } else {
            while (ady.getSigAdyacente() != null) {
                ady = ady.getSigAdyacente();
            }
            ady.setSigAdyacente(new NodoAdy(verticeD, null, etiq));
        }

        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino, int etiqueta) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino) se quita de la estructura
        // el arco que une ambos vertices. Si el arco existe y se puede realizar la eliminacion con exito
        //devuelve verdadero, en caso contrario falso.
        boolean eliminado = false;

        NodoVert nodoVertice[], nodoO, nodoD;

        //Verifica si ambos vertices existen en el grafo,EVITAR HACER DOS RECORRIDOS
        nodoVertice = busca2Vertices(origen, destino);
        nodoO = nodoVertice[0];
        nodoD = nodoVertice[1];

        if (nodoO != null && nodoD != null) {
            eliminado = eliminarArcoAux(nodoO, destino, etiqueta)
                    && eliminarArcoAux(nodoD, origen, etiqueta);
        }
        return eliminado;
    }

    private boolean eliminarArcoAux(NodoVert nodo, Object elemento, int etiqueta) {
        boolean encontrado = false;

        NodoAdy nodoAdy;
        if (nodo != null) {
            nodoAdy = nodo.getPrimerAdy();
            //CASO ESPECIAL: 1era posicion
            if (nodoAdy.getVertice().getElem().equals(elemento)
                    && nodoAdy.getEtiqueta() == (etiqueta)) {

                encontrado = true;
                nodo.setPrimerAdy(nodoAdy.getSigAdyacente());

            } else {

                while (nodoAdy.getSigAdyacente() != null) {

                    if (nodoAdy.getSigAdyacente().getVertice().getElem().equals(elemento)
                            && nodoAdy.getSigAdyacente().getEtiqueta() == (etiqueta)) {

                        encontrado = true;
                        nodoAdy.setSigAdyacente(nodoAdy.getSigAdyacente().getSigAdyacente());
                    } else {
                        nodoAdy = nodoAdy.getSigAdyacente();
                    }
                }

            }

        }

        return encontrado;
    }

    //existeVertice 
    public boolean existeVertice(Object buscado) {
        //Este metodo recibe un elemento, devuelve verdadero si esta en la estructura y falso en caso contrario
        boolean existe = false;
        NodoVert aux = this.inicio;

        while (aux != null && !existe) {
            existe = aux.getElem().equals(buscado);

            if (!existe) {

                aux = aux.getSigVertice();
            }
        }
        return existe;
    }

    //existeArco LISTO
    public boolean existeArco(Object origen, Object destino) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve verdadero si existe un arco en la
        //estructura que los une y falso en caso contrario
        boolean existe = false;
        NodoVert nodoOrigen = this.ubicarVertice(origen);
        NodoAdy ady = nodoOrigen.getPrimerAdy();
        while (ady != null && !existe) {
            if (ady.getVertice().getElem().equals(destino)) {
                existe = true;
            } else {
                ady = ady.getSigAdyacente();
            }

        }
        return existe;
    }

    //existeCamino 
    public boolean existeCamino(Object origen, Object destino) {
        //Este metodo recibe dos elementos de tipoVertice (origen y destino), devuelve verdadero si existe al menos
        // un camino que permite llegar del  vertice origen al vertice destino y falso en caso contrario
        boolean existe = false;
        NodoVert nodoVert[], nodoOrig, nodoDest;
        Lista visitados = new Lista();
        //verifica si ambos vertices existen

        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        if (nodoOrig != null && nodoDest != null) {
            existe = existeCaminoAux(nodoOrig, destino, visitados);

        }
        return existe;
    }

    private boolean existeCaminoAux(NodoVert nodoVert, Object destino, Lista vis) {
        boolean exito = false;
        if (nodoVert != null) {
            //si el vertice nodo es el destino: HAY CAMINO
            if (nodoVert.getElem().equals(destino)) {
                exito = true;
            } else {
                // si no es el destino verifica si hay camino entre nodo y dest
                vis.insertar(nodoVert.getElem(), vis.longitud() + 1);
                NodoAdy ady = nodoVert.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), destino, vis);

                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    //caminoMasCorto 
    public Lista caminoMasCorto(Object origen, Object destino) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve un camino(lista de vertices) que
        // indique el camino que pasa por menos vertices que permite llegar del vertice origen al vertice destino.
        //Si hay mas de un camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si alguno de los vertices no
        //existe o no hay camino posible entre ellos devuelve una lista vacia
        Lista masCorto, masCortoAct;
        masCorto = new Lista();//es el camino mas corto, el cual vamos a retornar
        masCortoAct = new Lista();//es el camino mas corto actual en donde vamos visitando los nodos
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        if (nodoOrig != null && nodoDest != null) {

            masCorto = caminoMasCortoAux(nodoOrig, destino, masCortoAct, masCorto);

        }
        return masCorto;
    }

    private Lista caminoMasCortoAux(NodoVert nodoVert, Object destino, Lista masCortoAct, Lista masCorto) {
        if (nodoVert != null) {
            masCortoAct.insertar(nodoVert.getElem(), masCortoAct.longitud() + 1);

            if (nodoVert.getElem().equals(destino)) {

                System.out.println(masCortoAct.clone().toString());

                masCorto = masCortoAct.clone();

            } else {
                NodoAdy ady = nodoVert.getPrimerAdy();
                while (ady != null
                        && (masCorto.esVacia() || masCortoAct.longitud() < masCorto.longitud())) {

                    if (masCortoAct.localizar(ady.getVertice().getElem()) < 0) {

                        masCorto = caminoMasCortoAux(ady.getVertice(), destino, masCortoAct, masCorto);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            //eliminamos el ultimo nodo de la lista

            masCortoAct.eliminar(masCortoAct.longitud());

        }

        return masCorto;
    }

    //caminoMasLargo 
    public Lista caminoMasLargo(Object origen, Object destino) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve un camino(lista de vertices) que
        // indique el camino que pasa por mas vertices que permite llegar del vertice origen al vertice destino.
        //Si hay mas de un camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si alguno de los vertices no
        //existe o no hay camino posible entre ellos devuelve una lista vacia
        Lista masLargo, masLargoAct;
        masLargo = new Lista();//es el camino mas largo, el cual vamos a retornar
        masLargoAct = new Lista();//es el camino mas largo actual en donde vamos visitando los nodos
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        if (nodoOrig != null && nodoDest != null) {

            masLargo = caminoMasLargoAux(nodoOrig, destino, masLargoAct, masLargo);

        }
        return masLargo;
    }

    private Lista caminoMasLargoAux(NodoVert nodoVert, Object destino, Lista masLargoAct, Lista masLargo) {
        if (nodoVert != null) {
            masLargoAct.insertar(nodoVert.getElem(), masLargoAct.longitud() + 1);
            if (nodoVert.getElem().equals(destino)) {

                masLargo = masLargoAct.clone();

            } else {
                NodoAdy ady = nodoVert.getPrimerAdy();
                while (ady != null
                        && (masLargo.esVacia() || masLargoAct.longitud() > masLargo.longitud())) {
                    if (masLargoAct.localizar(ady.getVertice().getElem()) < 0) {

                        masLargo = caminoMasLargoAux(ady.getVertice(), destino, masLargoAct, masLargo);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            //eliminamos el ultimo nodo de la lista
            masLargoAct.eliminar(masLargoAct.longitud());

        }

        return masLargo;
    }

    //listarEnProdundidad 
    public Lista listarEnProfundidad() {
        //Este metodo devuelve una lista con los vertices del grafo visitados segun el recorrido en profundidad 
        //Arrancamos del vertice inicial
        NodoVert nodo = this.inicio;
        Lista visitados = new Lista();
        while (nodo != null) {
            if (visitados.localizar(nodo.getElem()) < 0) {
                //si el vertice no fue visitado aun, avanza en profundidad
                listarEnProfundidadAux(nodo, visitados);

            }
            nodo = nodo.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert nodoVert, Lista vis) {

        if (nodoVert != null) {
            //marca el vertice nodoVert como visitado
            vis.insertar(nodoVert.getElem(), vis.longitud() + 1);
            NodoAdy ady = nodoVert.getPrimerAdy();
            while (ady != null) {
                //visita en profundidad los adyacentes de nodoVert aun no visitados
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);

                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    //listarEnAnchura 
    public Lista listarEnAnchura() {
        //Este metodo devuelve una lista con los vertices del grafo visitados segun el recorrido en anchura
        Lista visitados = new Lista();
        //Arrancamos del verice inicial
        NodoVert nodo = this.inicio;
        while (nodo != null) {
            if (visitados.localizar(nodo.getElem()) < 0) {
                listarEnAnchuraAux(nodo, visitados);
            }
            nodo = nodo.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert nodoVert, Lista vis) {
        Cola cola = new Cola();
        cola.poner(nodoVert.getElem());
        while (!cola.esVacia()) {
            Object elem = cola.obtenerFrente();
            cola.sacar();
            vis.insertar(elem, vis.longitud() + 1);
            NodoAdy ady = nodoVert.getPrimerAdy();
            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    cola.poner(ady.getVertice().getElem());
                }
                ady = ady.getSigAdyacente();
            }
        }

    }

    @Override
    public String toString() {
        //Este metodo genera y devuelve una cadena String que muestra los vertices almacenados en el grafo
        //y que adyacentes tiene cada uno de ellos
        String cadena = "";
        if (this.inicio == null) {
            cadena = "Grafo vacio";
        } else {
            NodoVert nodoVert = this.inicio;
            System.out.println("Grafo: ");

            while (nodoVert != null) {
                cadena = cadena + "NODO VERTICE: " + nodoVert.getElem() + "\n";
                NodoAdy ady = nodoVert.getPrimerAdy();
                while (ady != null) {
                    cadena = cadena + "El nodo: " + nodoVert.getElem() + " y el nodo: " + ady.getVertice().getElem() + ""
                            + " tienen un arco, con etiqueta: " + ady.getEtiqueta() + "\n\n";
                    ady = ady.getSigAdyacente();
                }
                nodoVert = nodoVert.getSigVertice();
            }

        }
        return cadena;
    }

    public boolean vacio() {
        //Este metodo devuelve falso si hay al menos un vertice cargado en el grafo y verdadero en caso contrario
        boolean vacio = true;
        if (this.inicio != null) {
            vacio = false;
        }
        return vacio;
    }

    public Lista caminoComoMaxXVertice(Object aeroOrigen, Object aeroDestino, int cantVertice) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino) y un elemento de tipo int que indica la cantidad de vuelos maximo,
        //devuelve un camino(lista de vertices) que indique el camino que pasa por menos vertices que permite llegar del vertice origen al vertice destino.
        //Si hay mas de un camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si alguno de los vertices no
        //existe o no hay camino posible entre ellos devuelve una lista vacia
        Lista masCorto, masCortoAct;
        masCorto = new Lista();//es el camino mas corto, el cual vamos a retornar
        masCortoAct = new Lista();//es el camino mas corto actual en donde vamos visitando los nodos
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(aeroOrigen, aeroDestino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        if (nodoOrig != null && nodoDest != null) {

            masCorto = caminoComoMaxXVuelosAux(nodoOrig, aeroDestino, masCortoAct, masCorto, cantVertice);
        }
        return masCorto;
    }

    private Lista caminoComoMaxXVuelosAux(NodoVert nodoVert, Object aeroDestino, Lista masCortoAct, Lista masCorto, int cantVertice) {
        if (nodoVert != null) {
            masCortoAct.insertar(nodoVert.getElem(), masCortoAct.longitud() + 1);

            if (nodoVert.getElem().equals(aeroDestino)) {
                masCorto = masCortoAct.clone();
            } else {
                NodoAdy ady = nodoVert.getPrimerAdy();
                while (ady != null
                        && (masCortoAct.longitud() - 1 <= cantVertice)
                        && masCorto.esVacia()) {
                    if ((masCortoAct.localizar(ady.getVertice().getElem())) < 0) {
                        masCorto = caminoComoMaxXVuelosAux(ady.getVertice(), aeroDestino, masCortoAct, masCorto, cantVertice);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            //eliminamos el ultimo nodo de la lista
            masCortoAct.eliminar(masCortoAct.longitud());

        }
        return masCorto;
    }

    public Lista camMenorTiempoVuelo(Object aeroOrigen, Object aeroDestino) {
        Lista menorTiempo = new Lista(), menorTiempoAct = new Lista();
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(aeroOrigen, aeroDestino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        if (nodoOrig != null && nodoDest != null) {
            Minuto m = new Minuto(0, 0);
            menorTiempo = camMenorTiempoVueloAux(nodoOrig, aeroDestino, menorTiempoAct, menorTiempo, m);

        }
        return menorTiempo;
    }

    private Lista camMenorTiempoVueloAux(NodoVert nodo, Object aeroDestino, Lista menorTiempoAct, Lista menorTiempo, Minuto m) {
        if (nodo != null) {
            menorTiempoAct.insertar(nodo.getElem(), menorTiempoAct.longitud() + 1);
            if (nodo.getElem().equals(aeroDestino)) {
                m.setMin(m.getMinAct());
                menorTiempo = menorTiempoAct.clone();
            } else {
                NodoAdy ady = nodo.getPrimerAdy();
                while (ady != null
                        && (m.getMin() == 0 || m.getMinAct() < m.getMin())) {
                    if (menorTiempoAct.localizar(ady.getVertice().getElem()) < 0) {
                        int min = ady.getEtiqueta();
                        m.setMinAct(m.getMinAct() + min);
                        menorTiempo = camMenorTiempoVueloAux(ady.getVertice(), aeroDestino, menorTiempoAct, menorTiempo, m);
                        m.setMinAct(Math.abs(m.getMinAct() - min));
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            menorTiempoAct.eliminar(menorTiempoAct.longitud());

        }
        return menorTiempo;
    }

    public Lista caminoMinCantAero(Object origen, Object destino) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve un camino(lista de vertices) que
        // indique el camino que pasa por menos vertices que permite llegar del vertice origen al vertice destino.
        //Si hay mas de un camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si alguno de los vertices no
        //existe o no hay camino posible entre ellos devuelve una lista vacia
        Lista camRapido, camRapidoAct;
        camRapido = new Lista();
        camRapidoAct = new Lista();
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        if (nodoOrig != null && nodoDest != null) {

            camRapido = caminoMinCantAeroAux(nodoOrig, destino, camRapidoAct, camRapido);

        }
        return camRapido;
    }

    private Lista caminoMinCantAeroAux(NodoVert nodo, Object destino, Lista camRapidoAct, Lista camRapido) {
        if (nodo != null) {
            camRapidoAct.insertar(nodo.getElem(), camRapidoAct.longitud() + 1);
            if (nodo.getElem().equals(destino)) {

                camRapido = camRapidoAct.clone();

            } else {
                NodoAdy ady = nodo.getPrimerAdy();
                while (ady != null
                        && ((camRapidoAct.longitud() < camRapido.longitud()) || camRapido.esVacia())) {
                    if (camRapidoAct.localizar(ady.getVertice().getElem()) < 0) {

                        camRapido = caminoMinCantAeroAux(ady.getVertice(), destino, camRapidoAct, camRapido);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            //eliminamos el ultimo nodo de la lista
            camRapidoAct.eliminar(camRapidoAct.longitud());

        }

        return camRapido;
    }

    public Lista caminoMasRapido(Object origen, Object destino, Object aero) {
        Lista masRapido = new Lista(), masRapidoAct = new Lista();
        NodoVert nodoVert[], nodoOrig, nodoDest, aeroAux;
        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        aeroAux = this.ubicarVertice(aero);
        if (nodoOrig != null && nodoDest != null && aeroAux != null) {
            masRapido = caminoMasRapidoAux(nodoOrig, destino, masRapidoAct, masRapido, aero);
        }
        return masRapido;
    }

    private Lista caminoMasRapidoAux(NodoVert nodo, Object destino, Lista masRapidoAct, Lista masRapido, Object aero) {
        if (nodo != null) {
            masRapidoAct.insertar(nodo.getElem(), masRapidoAct.longitud() + 1);

            if (nodo.getElem().equals(destino)
                    && masRapidoAct.localizar(aero) > 0) {
                System.out.println(masRapidoAct);
                masRapido = masRapidoAct.clone();

            } else {
                NodoAdy ady = nodo.getPrimerAdy();
                while (ady != null
                        && (masRapido.esVacia() || masRapidoAct.longitud() < masRapido.longitud())) {
                    if (masRapidoAct.localizar(ady.getVertice().getElem()) < 0) {

                        masRapido = caminoMasRapidoAux(ady.getVertice(), destino, masRapidoAct, masRapido, aero);

                    }
                    ady = ady.getSigAdyacente();
                }
            }
            masRapidoAct.eliminar(masRapidoAct.longitud());

        }
        return masRapido;
    }

    public Lista caminoDePesoMenorA(Object origen, Object destino, int pesoMax) {
        // Este metodo recibe 2 vertices y una cantidad numerica entera y devuelve el primer camino
        // que encuentra que sale del vertice origen y llega al vertice destino y que tenga peso
        //(suma de las aristas que lo conforman) menor o igual a pesoMax
        Lista caminoPesoMenor = new Lista();
        Lista caminoPesoMenorAct = new Lista();
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        Peso peso = new Peso();
        if (nodoOrig != null && nodoDest != null) {
            caminoPesoMenor = caminoDePesoMenorAAux(nodoOrig, destino, pesoMax, caminoPesoMenor, caminoPesoMenorAct, peso);
        }

        return caminoPesoMenor;
    }

    private Lista caminoDePesoMenorAAux(NodoVert nodo, Object destino, int pesoMax, Lista caminoPesoMenor, Lista caminoPesoMenorAct, Peso p) {

        if (nodo != null) {
            caminoPesoMenorAct.insertar(nodo.getElem(), caminoPesoMenorAct.longitud() + 1);
            if (nodo.getElem().equals(destino)) {

                p.setPesoMenor(p.getPesoMenorAct());

                caminoPesoMenor = caminoPesoMenorAct.clone();
            } else {
                NodoAdy ady = nodo.getPrimerAdy();
                while (ady != null && (p.getPesoMenor() == 0 || p.getPesoMenorAct() <= pesoMax) && caminoPesoMenor.esVacia()) {
                    if (caminoPesoMenorAct.localizar(ady.getVertice().getElem()) < 0) {

                        int peso = ady.getEtiqueta();
                        p.setPesoMenorAct(p.getPesoMenorAct() + peso);
                        caminoPesoMenor = caminoDePesoMenorAAux(ady.getVertice(), destino, pesoMax, caminoPesoMenor, caminoPesoMenorAct, p);
                        p.setPesoMenorAct(Math.abs(p.getPesoMenorAct() - peso));
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            caminoPesoMenorAct.eliminar(caminoPesoMenorAct.longitud());
        }
        return caminoPesoMenor;

    }
    
     public Lista primerCaminoMenorPesoYLong(Object origen, Object destino, int pesoMax,int longMax) {
        // Este metodo recibe 2 vertices y 2  cantidades numericas y devuelve el primer camino
        // de origen a destino con peso < pesoMax y cantidad de vertices<longMax
        //(suma de las aristas que lo conforman) menor o igual a pesoMax
        Lista camMenorPesoYLong = new Lista();
        Lista camMenorPesoYLongAct = new Lista();
        NodoVert nodoVert[], nodoOrig, nodoDest;
        nodoVert = busca2Vertices(origen, destino);
        nodoOrig = nodoVert[0];
        nodoDest = nodoVert[1];
        Peso peso = new Peso();
        if (nodoOrig != null && nodoDest != null) {
            camMenorPesoYLong = primerCaminoMenorPesoYLongAux(nodoOrig, destino, pesoMax, 
                    longMax, camMenorPesoYLong, camMenorPesoYLongAct, peso);
        }

        return camMenorPesoYLong;
    }
     private Lista primerCaminoMenorPesoYLongAux(NodoVert nodo,Object destino,int pesoMax,int longMax,
             Lista camMenorPesoYLong, Lista camMenorPesoYLongAct,Peso p){
         if(nodo!=null){
             camMenorPesoYLongAct.insertar(nodo.getElem(), camMenorPesoYLong.longitud()+1);
             if(nodo.getElem().equals(destino)){
                 camMenorPesoYLong=camMenorPesoYLongAct.clone();
                 
             }else{
                 NodoAdy ady=nodo.getPrimerAdy();
                 while(ady!=null && 
                  (p.getPesoMenorAct()<pesoMax
                && camMenorPesoYLongAct.longitud()<longMax) && camMenorPesoYLong.esVacia()){
                     if(camMenorPesoYLongAct.localizar(ady.getVertice().getElem())<0){
                         int peso=ady.getEtiqueta();
                         p.setPesoMenorAct(p.getPesoMenorAct()+peso);
                         camMenorPesoYLong=primerCaminoMenorPesoYLongAux(ady.getVertice(),destino,pesoMax
                                                                      ,longMax,camMenorPesoYLong,camMenorPesoYLongAct,p);
                         p.setPesoMenorAct(Math.abs(p.getPesoMenorAct()-peso));
                     }
                     ady=ady.getSigAdyacente();
                 }
             }
             camMenorPesoYLongAct.eliminar(camMenorPesoYLong.longitud()+1);
         }
         return camMenorPesoYLong;
     }
}
