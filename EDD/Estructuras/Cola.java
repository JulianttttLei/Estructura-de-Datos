package Estructuras;
//COLA DINAMICA
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }
    // poner

    public boolean poner(Object nuevoElem) {
        //Este metodo pone el elemento al final de la cola
        boolean exito = true;//Como la implementacion de la cola dinamica no tiene  restriccion de espacio, la cola nunca se llena por lo cual siempre devuelve true 
       
        Nodo newNodo = new Nodo(nuevoElem, null);
    
        if (this.frente==null) {// Si la cola esta vacia:
            this.frente = newNodo;
            
          
        } else {// si tiene al menos 1 elemento en la cola:
            if (this.frente.getEnlace()==null) {
                
                this.frente.setEnlace(newNodo);

            } else {
                
                this.fin.setEnlace(newNodo);

            }
           this.fin = newNodo;
        }
        return exito;
    }
    // sacar

    public boolean sacar() {
        //Este metodo saca el elemento que se encuentra al frente de la cola
        boolean exito = true;
        if (this.frente == null) {
            //Error Cola: Cola vacia
            exito = false;
        } else {
            // al menos hay 1 elemento en la cola
            // quita el primer elemento y actualiza frente(y fin si queda vacia)
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {//Si cada 1 unico nodo 
                this.fin = null;
            }
        }
        return exito;
    }

    // obtener frente
    public Object obtenerFrente() {
        //Este metodo devuelve el elemento que esta en el frente
        Object sacaElem=null;
        //Precondicion: si la cola no esta vacia
        if (this.frente != null) {
            sacaElem =this.frente.getElem();

        }

        return sacaElem;
    }
    //esVacia

    public boolean esVacia() {
        //Este metodo determina si la cola esta vacia o no
        boolean exito = false;// Devuelve false si la cola tiene elementos
        if (this.frente == null) {
            exito = true; // si la cola no tiene elementos osea la cola esta vacia
        }
        return exito;

    }
    // vaciar

    public void vaciar() {
        //Este metodo saca todos los elementos de la cola
        this.fin = null;
        this.frente = null;
    }

    public Cola clone() {
        //Este metodo devuelve una copia exacta de los datos de la cola original, y respetando el orden de los mismos, en otra cola del mismo tipo 
        Cola clon = new Cola();
        if (this.frente != null) {
            Nodo aux1 = this.frente;// Con este aux1 me voy a ir moviendo para ir sabiendo que elementos hay guardados en la cola original y esta va un nodo mas adelante que aux2
            clon.frente = new Nodo(aux1.getElem(), null);// le asigno el primer nodo
            aux1 = aux1.getEnlace();
            Nodo aux2 = clon.frente;
            // Con este aux2 copio el primer nodo y luego lo vamos a ir cambiando para llegar a los nodos siguientes
            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
                
                aux1 = aux1.getEnlace();
            }
          
            clon.fin = aux2;// Debo enganchar el fin al ultimo nodo
        }
        return clon;

    }
    // toString

    @Override
    public String toString() {
        //Este metodo crea y devuelve una cadena de caracteres formada por todos los elementos de la cola para poder mostarla por pantalla 
        String cadena = " ";
        if (this.frente==null) {
            cadena = "cola vacia";
        } else {
            Nodo aux1 = this.frente;
            cadena = "[";
            while (aux1 != null) {
                //agrega la cadena y avanza
                cadena += aux1.getElem();
                aux1 = aux1.getEnlace();
                if (aux1 != null) {
                    cadena += ",";
                }

            }
            cadena += "]";

        }
        return cadena;
    }
}
