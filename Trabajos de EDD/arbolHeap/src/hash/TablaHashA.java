/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import dinamicas.Lista;

/**
 *
 * @author agust
 */
//HASH ABIERTO
public class TablaHashA {

    private final int TAMANIO;//el tamaño puede asignarse en el constructor o estar definido por defecto
    private final Nodo[] tabla;// la tabla es un arreglo
    private int cant;

    //Constructor vacio
    public TablaHashA(int tam) {
        this.TAMANIO = tam;
        this.tabla = new Nodo[TAMANIO];
        this.cant = 0;
    }

    //insertar 
    public boolean insertar(Object elem) {
        //Este metodo recibe un elemento e intenta insertarlo en la tabla. Si todo funcion OK
        //(no esta repetido y hay lugar suficente en la tabla) devuelve verdadero, si hay algun 
        //problema devuelve falso

        int pos = elem.hashCode() % this.TAMANIO;
        System.out.println("Pos: "+pos);
        Nodo aux, newNodo;
        boolean encontrado = false;
        newNodo = new Nodo(elem, null);
        if (this.tabla[pos] == null) {
            this.tabla[pos] = newNodo;

        } else {
            aux = this.tabla[pos];
            encontrado = aux.getElem().equals(elem);
            while (!encontrado && aux.getEnlace() != null) {
                encontrado = aux.getEnlace().getElem().equals(elem);

                aux = aux.getEnlace();
            }
            if (!encontrado) {
                aux.setEnlace(newNodo);
            }
        }
        if (!encontrado) {
            this.cant++;
            
        }
        
        ;
        return !encontrado;
    }

    //eliminar
    public boolean eliminar(Object elem) {
        //Este metodo recibe un elemento que se desea eliminar y se procede a quitarlo de la tabla.
        //Si todo funciona OK (el elemento estaba cargado previamente en la tabla) devuelve verdadero,
        // si hay algun problema devuelve falso
        boolean encontrado = false;
        int pos = elem.hashCode() % this.TAMANIO, p = 0;
        Nodo aux = this.tabla[pos], nodoAux = null;
        while (aux != null && !encontrado) {
            encontrado = aux.getElem().equals(elem);
            p++;
            if (encontrado) {
                this.cant--;
                if (p == 1) {
                    this.tabla[pos] = this.tabla[pos].getEnlace();// para las cabeceras
                } else {
                    // no hace diferencia si se encuentra con un enlace vacio
                    nodoAux.setEnlace(aux.getEnlace());
                }
            } else {
                nodoAux = aux;
                aux = aux.getEnlace();
            }

        }

        return encontrado;

    }

//listar
    public Lista listar() {
//Este metodo recorre la tabla completa y devuelve una lista con los elementos que se encuentran almacenados
//en la tabla
        Lista ls = new Lista();
        int posList = 1, pos = 0;

        while (pos < this.TAMANIO) {
            Nodo aux = this.tabla[pos];

            while (aux != null) {

                ls.insertar(aux.getElem(), posList);
                posList++;
                aux = aux.getEnlace();

            }

            pos++;

        }
        return ls;
    }

//pertenece
    public boolean pertenece(Object elem) {
//Este metodo recibe el elemento y devuelve verdadero si ya esta cargado en la tabla y falso en caso contrario
        boolean pertenece = false;
        int pos = elem.hashCode() % this.TAMANIO;
        Nodo aux = this.tabla[pos];
        while (aux != null && !pertenece) {
            pertenece = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        return pertenece;
    }

//esVacia
    public boolean esVacio() {
//Este metodo devuelve falso si hay al menos un elemento cargado en la tabla y verdadero en caso contrario
        boolean vacio = true;
        if (this.cant != 0) {
            vacio = false;
        }
        return vacio;
    }
   // Operaciones utiles 
    //vaciar
   public void vaciar(){
   //Este metodo vacia la tabla
    int pos=0;
    
    while(pos<this.TAMANIO){
    this.tabla[pos]=null; 
    pos++;
   }
   this.cant=0;
}

//clone
public TablaHashA clone(){
// Este metodo clona la tabla hash 
int tam=this.TAMANIO,pos=0;
Nodo clon1, nodoAux,clon2;
TablaHashA clon= new TablaHashA(tam);
clon.cant=this.cant;
while(pos<tam ){
    if(this.tabla[pos]!=null){
        Nodo nodoClon=new Nodo(this.tabla[pos].getElem(),null);
         clon.tabla[pos]=nodoClon;
         clon1=this.tabla[pos].getEnlace();
         clon2=nodoClon;
         while(clon1!=null){     
            nodoAux=new Nodo(clon1.getElem(),null);
            clon2.setEnlace(nodoAux);
            clon2=nodoAux;
            clon1=clon1.getEnlace();
         }
}
    pos++;
}
return clon;
}
   
   
}