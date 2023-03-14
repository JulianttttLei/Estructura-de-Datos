/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Administrador
 */
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    // insertar
    public boolean insertar(Object elemNew, Object elemPadre) {
        //En este metodo, dado un elemento elemNew y un elemento elemPadre, agrega elemNew como hijo de la pimera
        // aparicion de elemPadre. Para que la operacion termine con exito debe existir un nodo en el arbol con 
        // elemento= elemPadre. 
        boolean exito = true;//Devuelve true cuando se puede agregar elemNew al arbol
        if (this.raiz == null) {
            // si el arbol esta vacio, ponemos el elemNew en la raiz
            this.raiz = new NodoGen(elemNew, null, null);
        } else {
            // sino esta vacio, se busca el padre 
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {

                if (nodoPadre.getHijoIzquierdo() == null) {
                    //  si el padre existe y no tiene hijo izquierdo se lo agrega
                    nodoPadre.setHijoIzquierdo(new NodoGen(elemNew, null, null));

                } else {
                    // insertamos los hijos
                    NodoGen nodoHijo = nodoPadre.getHijoIzquierdo();
                    while (nodoHijo.getHermanoDerecho() != null) {
                        nodoHijo = nodoHijo.getHermanoDerecho();

                    }
                    nodoHijo.setHermanoDerecho(new NodoGen(elemNew, null, null));
                }

            } else {
                exito = false;// Devuelve false si el padre no existe
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
        // Metodo privado que busca un elemento y devuelve el nodo que lo contiene.
        // Si no se encuentra buscado devuelve null
        NodoGen res = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                res = nodo;
            } else {
                res = obtenerNodo(nodo.getHijoIzquierdo(), buscado);
                if (res == null) {
                    res = obtenerNodo(nodo.getHermanoDerecho(), buscado);
                }
            }

        }
        return res;
    }
    // pertenece

    public boolean pertenece(Object elem) {
        // Este metodo retorna true si el elemento pertenece a la estructura sino false en caso contrario
        return perteneceAux(elem, this.raiz);
    }

    private boolean perteneceAux(Object elemento, NodoGen nodo) {
        //Metodo privado que verifica si un elemento se encuentra en el arbol
        boolean pertenece = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                pertenece = true;
            } else {

                pertenece = perteneceAux(elemento, nodo.getHijoIzquierdo());
                if (!pertenece) {
                    pertenece = perteneceAux(elemento, nodo.getHermanoDerecho());

                }

            }
        }

        return pertenece;

    }
   
    public Lista ancestros1(Object e){
        return ancestrosAux1(e,this.raiz,new Lista(),new Lista());
    }
    private Lista ancestrosAux1(Object ancestro,NodoGen nodo, Lista ls,Lista lsAct){
        if(nodo!=null){
            lsAct.insertar(nodo.getElem(), lsAct.longitud()+1);
            if(nodo.getElem().equals(ancestro)){
                lsAct.eliminar(lsAct.longitud());
                ls=lsAct.clone();
            }else{
                NodoGen hijo=nodo.getHijoIzquierdo();
                while(hijo!=null){
                ls=ancestrosAux1(ancestro,hijo,ls,lsAct);
                hijo=hijo.getHermanoDerecho();
            }
            }
           lsAct.eliminar(lsAct.longitud());
        }
        return ls;
    }
    // ancestros
    public Lista ancestros(Object elem) {
        Lista lista = new Lista();
        ancestrosAux(elem, this.raiz, lista,false);
        return lista;
    }

    private boolean ancestrosAux(Object elem, NodoGen nodo, Lista lis,boolean rta) {
        //Metodo privado que devuelve una lista con los ancestros del elemento enviado por parametro.
        //Si el elemento no se encuentra en el arbol devuelve una lista vacia
       
     //  System.out.println("Rta inicio: "+rta);
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                rta = true;
            } else {
                rta = ancestrosAux(elem, nodo.getHijoIzquierdo(), lis,rta);
          //      System.out.println("RTA FIN: "+rta);
                if (rta) {//true

                    lis.insertar(nodo.getElem(), lis.longitud() + 1);
                } else {
                    rta = ancestrosAux(elem, nodo.getHermanoDerecho(), lis,rta);
                }
            }
        }
        
        System.out.println("Rta: "+rta);
        return rta;
    }
    
      public Lista ancestros3(Object elem) {
        Lista lista = new Lista();
        ancestrosAux3(elem, this.raiz, lista);
        return lista;
    }

    private boolean ancestrosAux3(Object elem, NodoGen nodo, Lista lis) {
        //Metodo privado que devuelve una lista con los ancestros del elemento enviado por parametro.
        //Si el elemento no se encuentra en el arbol devuelve una lista vacia
        boolean rta = false;
      
        if (nodo != null) {
            System.out.println("Elem: "+nodo.getElem());
            if (nodo.getElem().equals(elem)) {
                rta = true;
            } else {
                rta = ancestrosAux3(elem, nodo.getHijoIzquierdo(), lis);
             
                if (rta) {//true
                    System.out.println("Nodo: "+nodo.getElem());
                    
                    lis.insertar(nodo.getElem(), lis.longitud() + 1);
                } else {
                    System.out.println("Nododer: "+nodo.getElem());
                    rta = ancestrosAux3(elem, nodo.getHermanoDerecho(), lis);
                }
            }
        }
        System.out.println("Rta: "+rta);
        return rta;
    }


    // esVacio
    public boolean esVacio() {
        // Este metodo devuelve falso si hay al menos un elemento en el arbol y true en caso contrario
        boolean vacio = true;
        if (this.raiz != null) {
            vacio = false;
        }
        return vacio;
    }

    //altura
    public int altura() {
        // Este metodo retorna la altura del arbol
        return alturaAux(this.raiz, 0, -1);
    }

    private int alturaAux(NodoGen nodo, int nivel, int altura) {
        // Metodo privado que devuelve el camino mas largo desde la  raiz
        // hasta una hoja. Arbol vacio=-1.Una hoja devuelve altura 0.
        if (nodo != null) {
            altura = alturaAux(nodo.getHijoIzquierdo(), nivel + 1, altura);
            if (nivel > altura) {
                altura = nivel;
            }
            altura = alturaAux(nodo.getHermanoDerecho(), nivel, altura);
        }
        return altura;
    }

    //nivel
    public int nivel(Object elem) {
        //Este metodo retorna el nivel de un elemento
        return nivelAux(this.raiz, 0, elem);
    }

    private int nivelAux(NodoGen nodo, int nivel, Object buscado) {
        // Metodo privado que devuelve el nivel de un elemento en el arbol.
        //Si el elemento no existe en el arbol devuelve -1
        int niv = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                niv = nivel;
            } else {
                niv = nivelAux(nodo.getHijoIzquierdo(), nivel + 1, buscado);
                if (niv == -1) {
                    niv = nivelAux(nodo.getHermanoDerecho(), nivel, buscado);
                }
            }

        }
        return niv;
    }

    //padre
    public Object padre(Object elem) {
        // Este metodo retorna el padre de un elemento
        Object soyPadre;
        if ((esVacio()) || (this.raiz.getElem().equals(elem))) {
            soyPadre = null;
        } else {
            soyPadre = padreAux(this.raiz, elem);
        }
        return soyPadre;
    }

    private Object padreAux(NodoGen nodo, Object hijo) {
        Object padre = null;
        boolean exito = false;
        if (nodo != null) {
            NodoGen unHijo = nodo.getHijoIzquierdo();
            while (unHijo != null && !exito) {
                if (unHijo.getElem().equals(hijo)) {
                    padre = nodo.getElem();
                    exito = true;
                } else {
                    unHijo = unHijo.getHermanoDerecho();
                }
            }
            if (padre == null) {
                padre = padreAux(nodo.getHijoIzquierdo(), hijo);
            }
            if (padre == null) {
                padre = padreAux(nodo.getHermanoDerecho(), hijo);
            }
        }
        return padre;
    }
//listaPreorden

    public Lista listarPreorden() {
        // Este metodo retorna una lista con los elementos del arbol en el recorrido en preorden
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoGen nodo, Lista l) {
        if (nodo != null) {
            //visita del nodo   
            l.insertar(nodo.getElem(), l.longitud() + 1);
            //llamados recursivos con los otros hijos de nodo
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, l);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    //listarInorden
    public Lista listarInorden() {
        // Este metodo retorna una lista con los elementos del arbol en el recorrido en inorden
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoGen nodo, Lista l) {
        if (nodo != null) {
            // llamado recursivo con primer hijo de nodo
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenAux(nodo.getHijoIzquierdo(), l);
            }
            //visita del nodo
            l.insertar(nodo.getElem(), l.longitud() + 1);
            // llamados recursivos con los otros hijos de nodo
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, l);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    //listarPosorden
    public Lista listarPosorden() {
        // Este metodo retorna una lista con los elementos del arbol en el recorrido en posorden
        Lista lista = new Lista();
        listarPosordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPosordenAux(NodoGen nodo, Lista l) {
        if (nodo != null) {
            // llamados recursivos con los otros hijos de nodo 
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, l);
                hijo = hijo.getHermanoDerecho();
            }
            //visita del nodo
            l.insertar(nodo.getElem(), l.longitud() + 1);
        }
    }

    //listarNiveles
    public Lista listarNiveles() {
        // Este metodo retorna una lista con los elementos del arbol en el recorrido por niveles
        Lista l = new Lista();
        Cola c = new Cola();
        int pos = 0;
        NodoGen nodo, hijo;
        c.poner(this.raiz);
        while (!c.esVacia()) {
            pos++;
            nodo = (NodoGen) c.obtenerFrente();
            c.sacar();

            l.insertar(nodo.getElem(), pos);
            if (nodo.getHijoIzquierdo() != null) {
                hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    c.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return l;
    }

    //clone
    public ArbolGen clone() {
        // Este metodo retorna una arbol generico equivalente al original
        ArbolGen clon = new ArbolGen();
        if (this.raiz != null) {
            clon.raiz = (cloneAux(this.raiz));
        }
        return clon;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nodoClon = new NodoGen(nodo.getElem(), null, null);
        if (nodo.getHijoIzquierdo() != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            nodoClon.setHijoIzquierdo(cloneAux(hijo));
        }
        if (nodo.getHermanoDerecho() != null) {
            nodoClon.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nodoClon;
    }

    // vaciar
    public void vaciar() {
        // Este metodo quita todos los elementos de la estructura
        this.raiz = null;
    }

    //toString
    @Override
    public String toString() {
        // Metodo que retorna una cadena de caracteres de un arbol generico
        return obtenerToString(this.raiz);
    }

    private String obtenerToString(NodoGen nodo) {
        //Es un metodo privado que genera y crea una cadena de caracteres que inidica cual es 
        // la raiz del arbol y quienes son los hijos de cada nodo
        String cad = " ";
        if (nodo != null) {
            // visita del nodo
            cad += nodo.getElem().toString() + "->";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cad += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            //comienza recorrido de los hijos de nodo llamado recursivamente
            // para que cada hijo agregue su subcadena a la general
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cad += "\n" + obtenerToString(hijo);
                hijo = hijo.getHermanoDerecho();
            }

        }
        return cad;

    }
    //Metodo agregado para reentrega de arbol generico: frontera

    public Lista frontera() {
        Lista ls = new Lista();

        fronteraAux(this.raiz, ls);
        return ls;
    }

    private void fronteraAux(NodoGen nodo, Lista ls) {
        // Este es un metodo que genera una lista con todas las hojas del arbol generico de izquierda a derecha
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                ls.insertar(nodo.getElem(), ls.longitud() + 1);
            }
            fronteraAux(nodo.getHijoIzquierdo(), ls);
            fronteraAux(nodo.getHermanoDerecho(), ls);
        }
    }

    public Lista listaQueJustificaLaAltura() {
        Lista caminoMasAlejado = new Lista();
        Lista caminoMasAlejadoAct = new Lista();
       
        if (this.raiz != null) {
            caminoMasAlejado = caminoMasAlejadoAux(this.raiz, caminoMasAlejadoAct, caminoMasAlejado);
       
        }
        return caminoMasAlejado;
    }

    private Lista caminoMasAlejadoAux(NodoGen nodo, Lista caminoMasAlejadoAct, Lista caminoMasAlejado) {

        if (nodo != null) {
            caminoMasAlejadoAct.insertar(nodo.getElem(), caminoMasAlejadoAct.longitud() + 1);
            if (nodo.getHijoIzquierdo() == null) {

                if (caminoMasAlejadoAct.longitud() > caminoMasAlejado.longitud()) {
                    caminoMasAlejado = caminoMasAlejadoAct.clone();
                }
            } else {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null ) {
                    caminoMasAlejado = caminoMasAlejadoAux(aux, caminoMasAlejadoAct, caminoMasAlejado);
                    aux = aux.getHermanoDerecho();

                }

            }
            caminoMasAlejadoAct.eliminar(caminoMasAlejadoAct.longitud());
        }
        System.out.println("Camino: "+caminoMasAlejado);
        return caminoMasAlejado;

    }

    public int descendienteMasCercano(Object buscado) {
        // Este metodo devuelve el nivel del elemento con valor buscado que esta mas cerca de la raiz

        return descendienteMasCercanoAux(this.raiz, 0, -1, buscado);

    }

    private int descendienteMasCercanoAux1(NodoGen nodo, int nivel, int nivelMin, Object buscado) {

        Cola c = new Cola();
        Cola colaAux = new Cola();
        c.poner(nodo);
        boolean encontrado = false;
        while (!encontrado && !c.esVacia()) {

            nodo = (NodoGen) c.obtenerFrente();
            c.sacar();
            encontrado = nodo.getElem().equals(buscado);

            if (encontrado) {

                nivelMin = nivel;

            } else {

                NodoGen hijo = nodo.getHijoIzquierdo();
                if (hijo != null) {

                    while (hijo != null) {

                        c.poner(hijo);
                        hijo = hijo.getHermanoDerecho();

                    }
                }
                if (colaAux.esVacia()) {

                    nivel++;
                    colaAux = c.clone();
                    colaAux.sacar();

                } else {

                    colaAux.sacar();
                }
            }
        }
        return nivelMin;
    }
     private int descendienteMasCercanoAux(NodoGen nodo, int nivel, int nivelAux, Object buscado) {

        if (nodo != null) {

            if (nodo.getElem().equals(buscado)) {

                if (nivelAux==-1 || nivel<nivelAux) {

                    nivelAux = nivel;

                }

            }

            nivelAux = descendienteMasCercanoAux(nodo.getHijoIzquierdo(), nivel + 1, nivelAux, buscado);
            while (nodo.getHermanoDerecho() != null) {
                nivelAux = descendienteMasCercanoAux(nodo.getHermanoDerecho(), nivel, nivelAux, buscado);
                nodo = nodo.getHermanoDerecho();
            
        }
        }
         System.out.println("Nivel: "+nivelAux);
        return nivelAux;
    }

    public int descendienteMasLejano(Object buscado) {
        // Este metodo devuelve el nivel del elemento con valor buscado que esta a mas distancia de la raiz 

        return descendienteMasLejanoAux(this.raiz, 0, -1, buscado);
    }

    private int descendienteMasLejanoAux(NodoGen nodo, int nivel, int nivelMax, Object buscado) {

        if (nodo != null) {

            if (nodo.getElem().equals(buscado)) {

                if (nivel > nivelMax) {

                    nivelMax = nivel;

                }

            }

            nivelMax = descendienteMasLejanoAux(nodo.getHijoIzquierdo(), nivel + 1, nivelMax, buscado);
            while (nodo.getHermanoDerecho() != null) {
                nivelMax = descendienteMasLejanoAux(nodo.getHermanoDerecho(), nivel, nivelMax, buscado);
                nodo = nodo.getHermanoDerecho();
            }
        }
        
         System.out.println("NivelMax: "+nivelMax);
        return nivelMax;
    }

    public boolean verificarPatron(Lista lis) {
        Lista ls = lis.clone();
        boolean res = verifAux(ls, this.raiz, false);
        return res;
    }

    private boolean verifAux(Lista ls, NodoGen nodo, boolean res) {
        if (nodo != null) {
            if (nodo.getElem().equals(ls.recuperar(1))) {
                ls.eliminar(1);
                if (nodo.getHijoIzquierdo() == null && ls.esVacia()) {
                    res = true;
                } else {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null && res == false) {
                        res = verifAux(ls, hijo, res);
                        hijo = hijo.getHermanoDerecho();
                    }

                }
            }
        }
        System.out.println("Res: "+res);
        return res;
    }
}