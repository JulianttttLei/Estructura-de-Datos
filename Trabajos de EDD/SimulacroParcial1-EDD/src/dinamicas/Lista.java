/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinamicas;

/**
 *
 * @author Administrador
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }
    // Operaciones basicas para el TDA Lista

    //Insertar
    public boolean insertar(Object newElem, int pos) {
        // Este metodo agrega el elemento por paramentro en la posicion pos, de manera que la cantidad de elementos de la lista se incrementa en 1
        Nodo aux, nuevo;
        int p;
        boolean exito = true; //Devuelve verdadero si el elemento se pudo insertar
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false; //Devuelve false si el elementono se pudo insertar
        } else {
            if (pos == 1) { //crear el nuevo nodo y se enlaza en la cabecera
                this.cabecera = new Nodo(newElem, this.cabecera);
            } else { //avanza hasta el elemento en posicion pos-1
                aux = this.cabecera;
                p = 1;
                while (p < pos - 1) {
                    aux = aux.getEnlace();
                    p++;
                }
                //crea el nodo y lo enlaza
                nuevo = new Nodo(newElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longitud++;

        }
        // nunca hay error de lista llena, entonces devuelve true
        return exito;
    }

    //eliminar
    public boolean eliminar(int pos) {
        //Este metodo borra el elementos en la posicion pos

        int p;
        boolean eliminado = true;//Devuelve true si el elemento se pudo eliminar
        //Para una eliminacion exitosa la lista no debe estar vacia 
        if (pos < 1 || pos > this.longitud()) {//Error: posicion invalida
            eliminado = false;//Devuelve false si el elemento no se pudo eliminar

        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();

            } else {
                //2<=pos<=longitud
                Nodo aux = this.cabecera;
                p = 1;
                while (p < pos - 1) {
                    aux = aux.getEnlace();
                    p++;
                }

                aux.setEnlace(aux.getEnlace().getEnlace());
            }

            this.longitud--;

        }
        return eliminado;

    }
    // Recuperar

    public Object recuperar(int pos) {
        // Este metodo devuelve el elemento en la posicion pos
        Object elemento = null;//si no cumple con la precondicion entonces devuelve null
        int p;
        //Precondicion: la posicion debe ser valida:
        if (pos >= 1 && pos <= this.longitud()) {
            Nodo aux = this.cabecera;
            p = 0;
            while (p < pos - 1) {
                aux = aux.getEnlace();
                p++;
            }
            if (aux == null) {
                elemento = null;

            } else {
                elemento = aux.getElem();

            }

        }
        return elemento;
    }
    //localizar 

    public int localizar(Object elemBuscado) {
        //Este metodo devuelve la posicion en la que se encuentra la primera ocurrencia de elemento dentro de la lista.
        //En caso de no encontrarlo devuelve -1
        int pos = 1, p = -1;
        Nodo aux = this.cabecera;
        while (aux != null) {
            if (aux.getElem().equals(elemBuscado)) {
                p = pos;
                aux = null;
            } else {
                pos++;
                aux = aux.getEnlace();
            }

        }
        return p;
    }
    //vaciar

    public void vaciar() {
        //Este metodo quita todos los elementos de la lisra

        this.longitud = 0;
        this.cabecera = null;
    }

    //clone
    public Lista clone() {
        //Este metodo devuelve una copia exacta de los datos en la lista orignal, y respetando el orden de los mismos, en otra lista.
        Nodo aux1, aux2;
        // Creamos el clon
        Lista clon = new Lista();

        //Creo una copia del primer nodo de this
        if (this.cabecera != null) {
            clon.cabecera = new Nodo(this.cabecera.getElem(), null);
            aux1 = this.cabecera;//Apunto al primer nodo de this
            aux2 = clon.cabecera;//Apunto al primer nodo del clon
            aux1 = aux1.getEnlace();//avanzo al segundo nodo de la lista original
            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElem(), null));//Creo copia del segundo nodo y lo engancho al clon

                aux1 = aux1.getEnlace();
                aux2 = aux2.getEnlace();

            }
            clon.longitud = this.longitud;

        }

        return clon;
    }

    // es vacia
    public boolean esVacia() {
        //Este metodo determina si la lista es vacia o no
        boolean exito = false;//Devuelve false si la tiene tiene elementos
        if (this.cabecera == null) {
            exito = true;//Devuelve true si la lista no tiene elementos
        }
        return exito;
    }

    // longitud
    public int longitud() {
        //Este metodo devuelve la cantidad de elementos de la lista
        //Lo hacemos con la mejora de implementacion dinamica

        return this.longitud;

    }

    // toString 
    @Override
    public String toString() {
        //Este metodo crea y devuelve una cadena de caracteres formada por todos 
        //los elementos de la lista para poder mostarla por pantalla 
        String cadena = "";

        if (this.cabecera == null) {
            cadena = "Lista vacia";
        } else {
            Nodo aux = this.cabecera;
            cadena = "[";
            while (aux != null) {
                cadena +=  aux.getElem();
                aux = aux.getEnlace();

                if (aux != null) {
                    cadena += ",";

                }
            }
            cadena += "]";
        }
        return cadena;

    }

    

/*EJERCICIO TIPO 1: Implementar una operación de un TDA lineal
a) Agregar al TDA Lista la operación obtenerMultiplos(int num) que recibe un número y devuelve una lista nueva
que contiene todos los elementos de las posiciones múltiplos de num, en el mismo orden encontrado,
haciendo un único recorrido de las estructuras original y copia; y sin usar otras operaciones del TDA.
Ejemplo: si se invoca con la lista <A,B,C,D,E,F,G,H,I,J> y num=3, el método debe devolver la lista <C,F,I>*/
// ◦ Realizar la definición de tipos de todas las clases involucradas
// ◦ En todas las operaciones recorrer lo menos posible las estructuras*/
    public Lista obtenerMultiplos(int num) {
        Lista ls = new Lista();
       
        Nodo aux = this.cabecera;//recorre la lista original
        Nodo aux1=null;//recorre la nueva lista
        int pos = 0,i=1;
       while(i<=this.longitud ){
           if(i%num==0){
               pos++;
               if(pos==1){
                   ls.cabecera=new Nodo(aux.getElem(),null);
                   aux1=ls.cabecera;
               }else{
                   aux1.setEnlace(new Nodo(aux.getElem(),null));
                   aux1=aux1.getEnlace();
               } 
               ls.longitud++;
               System.out.println("es:" +ls.longitud);
           }
           
           i++;
           aux=aux.getEnlace();
       }
           
           
        return ls;
}
  //  b) Agregar al TDA Lista la operación eliminarApariciones(TipoElemento x) que elimine todas las apariciones de
//elementos iguales a x, haciendo un único recorrido de la estructura y sin usar otras operaciones del TDA.
    // ◦ Realizar la definición de tipos de todas las clases involucradas
// ◦ En todas las operaciones recorrer lo menos posible las estructuras
    
    //elimina apariciones 

    public void eliminarApariciones(Object buscado) {
        // Este metodo eliminar todas las apariciones de un elemento en  la lista
       
        int pos = 1;

        Nodo aux = this.cabecera;
        while (aux != null) {
            if (aux.getElem().equals(buscado)) {
                if (pos<=this.longitud) {
                    this.cabecera = this.cabecera.getEnlace();
                }
                this.longitud--;
            }
            pos++;
            aux = aux.getEnlace();

        }
    }
        // ejercicio adicional: Clonar invertido
        
        public Lista clonarInvertido(){
         Lista lsInv=new Lista();
        Nodo original;
         if(this.cabecera!=null){
             
             
             lsInv.cabecera=new Nodo(this.cabecera.getElem(),null);
             original=this.cabecera.getEnlace();
             while(original!=null){
                Nodo clon=lsInv.cabecera;
                lsInv.cabecera=new Nodo(original.getElem(),clon);
                original=original.getEnlace();
             }
             lsInv.longitud=this.longitud;
        }
         return lsInv;
    
    }
  
    public void agregarElem(Object nuevo, int x) {
    //Este metodo recorre la lista una sola vez y agrega al elemento nuevo en la primera posicion
    // y luego lo repite cada x posiciones 
    //Ejemplo: sea la lista [1,2,3,4,5,6,7] , nuevo=0 y x=2 modifica la lista para que quede
    // [0,1,2,0,3,4,0,5,6,0,7]
        if (this.longitud > 0) {
            Nodo aux = new Nodo(nuevo, this.cabecera);
            this.cabecera = aux;
           
            int valor = 0;
            while (aux != null) {
                if (valor == x ) {
                    Nodo aux2 = new Nodo(nuevo, aux.getEnlace());
                    aux.setEnlace(aux2);
                    this.longitud++;
                    valor = 0;
                } else {
                    valor++;
                }
                aux = aux.getEnlace();
            }
        }
    }

}
  
  
