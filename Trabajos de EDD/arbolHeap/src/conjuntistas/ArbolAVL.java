/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

import dinamicas.Lista;

/**
 *
 * @author Administrador
 */
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    //pertenece
    public boolean pertenece(Comparable elemento) {
        //Este método devuelve true si el elemento recibido por parametro está en el arbol
        //y false en caso contrario.
        return perteneceAux(this.raiz, elemento);
    }

    private boolean perteneceAux(NodoAVL nodo, Comparable elemBuscado) {
        boolean pertenece = false;
        if (nodo != null) {
            // si  elemBuscado y nodo.getElem() son iguales entonces retorna true
            if (elemBuscado.compareTo(nodo.getElem()) == 0) {
                pertenece = true;
            } else {
                //si elemBuscado es menor que nodo.getElem() avanza por el nodo izquierdo
                if (elemBuscado.compareTo(nodo.getElem()) < 0) {
                    pertenece = perteneceAux(nodo.getIzquierdo(), elemBuscado);
                } else {
                    //si elemBuscado es mayor que nodo.getElem() avanza por el nodo derecho
                    pertenece = perteneceAux(nodo.getDerecho(), elemBuscado);
                }
            }
        }
        return pertenece;
    }

    //insertar
    public boolean insertar(Comparable elem) {
        //Este método recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento
        //ya se encuentra en el árbol no se realiza la inserción. Devuelve true si el elemento
        //se agrega a la estructura y false en caso contrario.

        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, null, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, NodoAVL padre, Comparable elemento) {
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
                    exito = insertarAux(nodo.getIzquierdo(), nodo, elemento);
                } else {
                    nodo.setIzquierdo(new NodoAVL(elemento, null, null));
                }
            } else {
                //elemento es mayor que nodo.getElem()
                //si tiene HD baja a la derecha, sino agrega elemento
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), nodo, elemento);
                } else {
                    nodo.setDerecho(new NodoAVL(elemento, null, null));
                }
            }
        }

        //Se analiza si el arbol hasta ahora creado presenta algun desbalance
        if (exito) {
           
            balancear(nodo, padre);
        }
        return exito;
    }
     private void balancear(NodoAVL nodo,NodoAVL padre){
        //Recalculo la altura del nodo
        nodo.recalcularAltura();
        NodoAVL nodoAux=aplicaRotacion(nodo);
        if(nodo==this.raiz){
            this.raiz=nodoAux;
        }else{
            if(padre.getElem().compareTo(nodoAux.getElem())>0){
                padre.setIzquierdo(nodoAux);
            }else{
                padre.setDerecho(nodoAux);
            }
        }
    }
    
    private NodoAVL aplicaRotacion(NodoAVL nodo){
        int balancePadre=calculaBalance(nodo),balanceHijo;
        NodoAVL nodoAux=nodo;
        //el nodo padre esta caido a la izquierda
        if(balancePadre==2){
            balanceHijo=calculaBalance(nodo.getIzquierdo());
            if(balanceHijo>=0){
                //Su hijo izquierdo esta caido hacia el mismo lado
                //Se aplica Rotacion simple a derecha
                nodoAux=rotarDerecha(nodo);
            }else{
                //Su hijo izquierdo esta caido hacia el lado contrario
                //Se aplica Rotacion doble izquierda-derecha
                nodoAux=rotarIzqDer(nodo);
            }
        }else{
            //el nodo padre esta caido a la derecha
            if(balancePadre==-2){
                balanceHijo=calculaBalance(nodo.getDerecho());
                if(balanceHijo<=0){
                    //Su hijo derecho esta caido hacia el mismo lado
                    //Se aplica Rotacion simple a izquierda
                    nodoAux=rotarIzquierda(nodo);
                }else{
                    //Su hijo derecho esta caido hacia el lado contrario
                    //Se aplica Rotacion doble derecha-izquierda
                    nodoAux=rotarDerIzq(nodo);
                    
                }
            }
        }
        return nodoAux;
        
    }

    //eliminar
    public boolean eliminar(Comparable elemento) {
        //Este método recibe el elemento que se desea eliminar y se procede a removerlo del árbol.
        //Si no se encuentra el elemento no se puede realizar la eliminación. Devuelve true si el elemento
        //se elimina de la estructura y false en caso contrario.
        return eliminarAux(this.raiz, elemento, null);
    }

    private boolean eliminarAux(NodoAVL nodo, Comparable elem, NodoAVL padre) {
        boolean elimina = false;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) < 0) {
                elimina = eliminarAux(nodo.getIzquierdo(), elem, nodo);
            } else {
                if (elem.compareTo(nodo.getElem()) > 0) {
                    elimina = eliminarAux(nodo.getDerecho(), elem, nodo);
                } else {
                    if (elem.compareTo(nodo.getElem()) == 0) {
                        
                        elimina = true;
                        if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                            
                            eliminarHoja(nodo, padre);     //CASO 1: si el elemento a eliminar es una HOJA  
                        } else {
                            if ((nodo.getDerecho() != null && nodo.getIzquierdo() == null) || (nodo.getDerecho() == null && nodo.getIzquierdo() != null)) {
                               
                                eliminarCon1Hijo(nodo, padre); //CASO 2: si el elemento tiene un hijo  
                            } else {
                                if (nodo.getDerecho() != null && nodo.getIzquierdo() != null) {

                                    eliminarCon2Hijos(nodo);// CASO 3: si el elemento tiene 2 hijos
                                }
                            }

                        }

                    }
                }
            } 
             
            //en esta si el elemento es eliminado se aplica una rotacion o si un nodo tiene balance padre 2 o -2 tambien se aplica la rotacion
            int balPadre=calculaBalance(nodo);   
            if(Math.abs(balPadre)>1 ){
              nodo.recalcularAltura();
              balancear(nodo,padre);          
            }
        }
        return elimina;
    }

    //caso1: eliminarHoja
    private void eliminarHoja(NodoAVL hijoHoja, NodoAVL padre) {
        //caso especial: cuando el arbol tiene un unico elemento
        if (padre == null) {
            this.raiz = null;
        } else {
// caso comun
            if (hijoHoja.getElem().compareTo(padre.getElem()) < 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
        
    }
    
    //CASO 2: eliminarConHijo
     private void eliminarCon1Hijo(NodoAVL hijo, NodoAVL padre) {
        //caso especial:
        if (padre == null) {
            if(hijo.getDerecho()!=null){
                this.raiz=hijo.getDerecho();
            }else{
                this.raiz=hijo.getIzquierdo();
            }      
     }else{
            if(hijo.getElem().compareTo(padre.getElem())<0){
                if(hijo.getIzquierdo()!=null){
                    padre.setIzquierdo(hijo.getIzquierdo());                  
                }else{
                    padre.setIzquierdo(hijo.getDerecho());
                }
                
            }else{
                if(hijo.getIzquierdo()!=null){
                    padre.setDerecho(hijo.getIzquierdo());
                }else{
                    padre.setDerecho(hijo.getDerecho());
                }
            }
        }
         
         
     }
    
    //CASO 3: eliminarCon2Hijos 
    private void eliminarCon2Hijos(NodoAVL nodo) {
        NodoAVL candidato = nodo.getDerecho(), padreCandidato = nodo;
        
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

    // Rotacion simple a izquierda 
    private NodoAVL rotarIzquierda(NodoAVL nodoPivote) {
        // Este metodo rota a la izquierda cuando el Balance padre=-2 y Balanche hijo=-1 o 0
        NodoAVL hijoDer, temp;

        hijoDer = nodoPivote.getDerecho();// Corresponde al hijo derecho de nodoPivote

        temp = hijoDer.getIzquierdo();//Corresponde al hijo izquierdo de hijoDer

        hijoDer.setIzquierdo(nodoPivote);//Cambiamos el hijo izquierdo de hijoDer por nodoPivote
        nodoPivote.setDerecho(temp);//Cambiamos al hijo derecho de nodoPivote por temp

        nodoPivote.recalcularAltura();

        hijoDer.recalcularAltura();

        return hijoDer;//Retorna la nueva raiz del subarbol
    }

    // Rotacion simple a derecha 
    private NodoAVL rotarDerecha(NodoAVL nodoPivote) {
        //Este metodo rota a la derecha cuando el Balance padre=2 y Balance hijo=1 o 0
        NodoAVL hijoIzq, temp;
        hijoIzq = nodoPivote.getIzquierdo();//Corresponde al hijo izquierdo de nodoPivote
        temp = hijoIzq.getDerecho();//Correesponde al hijo derecho de hijoIzq 
        hijoIzq.setDerecho(nodoPivote);//Cambiamos el hijo derecho de hijoIzq por nodoPivote
        nodoPivote.setIzquierdo(temp);//Cambiamos el hijo izquierdo de nodoPivote por temp

        nodoPivote.recalcularAltura();
        hijoIzq.recalcularAltura();

        return hijoIzq;//Retorna la nueva raiz del subarbol
    }

    // Rotacion doble derecha-izquierda
    private NodoAVL rotarDerIzq(NodoAVL nodo) {
        //Este metodo realiza una rotacion a la derecha y luego otra a izquierda cuadno el Balance padre=-2 
        // y Balance hijo=1
        NodoAVL nodoPivote, nodoAux2, nodoAux3;
        nodoPivote = nodo.getDerecho();//es el hijo derecho de nodo
        nodo.setDerecho(rotarDerecha(nodoPivote));//se realiza la primera rotacion a la derecha
        nodoAux2 = nodo;//es el nodo pivote de la segunda rotacion
        nodoAux3 = rotarIzquierda(nodoAux2);//queda como el nuevo nodo padre al realizar la segunda rotacion a la izquierda
        return nodoAux3;
    }

    // Rotacion doble izquierda-derecha
    private NodoAVL rotarIzqDer(NodoAVL nodo) {
        //Este metodo realiza una rotacion a izquierda y luego otra rotacion a derecha cuando el 
        //Balance padre=2 y Balance hijo==-1
        NodoAVL nodoPivote, nodoAux2, nodoAux3;

        nodoPivote = nodo.getIzquierdo();//es el hijo izquierdo de nodo

        nodo.setIzquierdo(rotarIzquierda(nodoPivote));//se realiza la primera rotacion a la izquierda
        nodoAux2 = nodo;//es el nodo pivote de la segunda rotacion
        nodoAux3 = rotarDerecha(nodoAux2);//queda como el nuevo nodo padre al realizar la segunda rotacion a la derecha
        return nodoAux3;

    }

    // Este metodo calcula el balance de un nodo
    public int calculaBalance(NodoAVL nodo) {
        //Este metodo calcula el balance de un nodo
        int balance, alturaHijoIzq, alturaHijoDer;
        alturaHijoIzq = -1;
        alturaHijoDer = -1;
        System.out.println("Nodo Balance: "+nodo.getElem());
        if (nodo.getIzquierdo() != null) {
            alturaHijoIzq = nodo.getIzquierdo().getAltura();//Altura del hijo izquierdo
        }

        if (nodo.getDerecho() != null) {
            alturaHijoDer = nodo.getDerecho().getAltura();//Altura del hijo derecho
        }
    
        balance = alturaHijoIzq - alturaHijoDer;//Calcula balance
        System.out.println("alt izq: "+alturaHijoIzq);
        System.out.println("alt der: "+alturaHijoDer);
         System.out.println("balance "+balance);
        return balance;
    }
    
    
    
  
    //listar
    public Lista listar() {
//Este método recorre el árbol completo y devuelve una lista ordenada con los elementos que se 
//encuentran almacenados en él.
        Lista lista = new Lista();
        listaAux(this.raiz, lista);
        return lista;
    }

    private void listaAux(NodoAVL nodo, Lista ls) {
        if (nodo != null) {   
             listaAux(nodo.getIzquierdo(), ls);        
             ls.insertar(nodo.getElem(), ls.longitud() + 1); 
              listaAux(nodo.getDerecho(), ls); 
        }
    }

//listarRango
    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
//Este método recorre parte del árbol(sólo lo necesario) y devuelve una lista
//ordenada con los elementos que se encuentran en el intervalo [elemMinimo,elemMaximo].
        Lista listaRan = new Lista();
        listarRangoAux(elemMinimo, elemMaximo, this.raiz, listaRan);
        return listaRan;
    }

    private void listarRangoAux(Comparable elemMin, Comparable elemMax, NodoAVL nodo, Lista ls) {
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

//minimoElem
    public Comparable minimoElem() {
        Comparable elemMin = null;
        NodoAVL nodo = this.raiz;
        while (nodo != null) {
            elemMin = nodo.getElem();
            nodo = nodo.getIzquierdo();
        }

        return elemMin;
    }

//maximoElem
    public Comparable maximoElem() {
//Este método recorre la rama correspondiente y devuelve el elemento más grande 
//almacenado en el árbol  
        Comparable elemMax = null;
        NodoAVL nodo = this.raiz;
        while (nodo != null) {
            elemMax = nodo.getElem();
            nodo = nodo.getDerecho();
        }
        return elemMax;
    }
//clone

    public ArbolAVL clone() {
// Este metodo genera y devuelve un arbol binario que es equivalente al arbol original
        ArbolAVL clon = new ArbolAVL();

        if (this.raiz != null) {
            clon.raiz = (cloneAux(this.raiz));
        }
        return clon;

    }

    private NodoAVL cloneAux(NodoAVL nodo) {

        NodoAVL nodoNew = new NodoAVL(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {

            nodoNew.setIzquierdo(cloneAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nodoNew.setDerecho(cloneAux(nodo.getDerecho()));
        }

        return nodoNew;
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

    private String obtenerToString(NodoAVL nodo) {
        String cadena = "";

        if (nodo != null) {

            cadena += " Nodo " + nodo.getElem();
            if (nodo.getIzquierdo() != null) {

                cadena += " HI: " + nodo.getIzquierdo().getElem();
            } else {
                cadena += " HI: " + null;
            }
            if (nodo.getDerecho() != null) {

                cadena += " | HD: " + nodo.getDerecho().getElem() + " \n";
            } else {
                cadena += " | HD: " + null + "\n";

            }

            cadena = cadena + obtenerToString(nodo.getIzquierdo());
            cadena = cadena + obtenerToString(nodo.getDerecho());

        }
        return cadena;

    }

}
