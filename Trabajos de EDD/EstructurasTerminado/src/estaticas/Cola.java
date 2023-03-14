package estaticas;
// COLA ESTATICA

public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;
    // Operaciones: 

    // Constructor vacio
    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    // poner
    public boolean poner(Object nuevoElem) {
        // Este metodo pone el elemento al final de la cola
        boolean exito = false;//Devuelve false si la cola se llena 
        if ((this.fin + 1) % TAMANIO != this.frente) {

            this.arreglo[this.fin] = nuevoElem;
            this.fin = (this.fin + 1) % TAMANIO;
            exito = true;//Devuelve true si el elemento se pudo agregar a la cola
        }
        return exito;
    }

    // sacar 
    public boolean sacar() {
        // Este metodo saca el elemnto que esta en el frente de la cola
        boolean exito = true;// Devuelve true si el elemento se pudo sacar(la estructura no estaba vacia)
        if (this.esVacia()) { // la cola esta vacia, reporta error
            exito = false;
        } else { // al menos hay 1 elemento: avanza frente (de manera circular)
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }
        return exito;

    }

    // obtener frente
    public Object obtenerFrente() {
        // Este metodo devuelve el elemento que esta en el frente
        Object sacaElem = null;

        if (this.frente != this.fin) { // Si la  cola no esta vacia

            sacaElem = this.arreglo[frente];

        }
        return sacaElem;
    }

    // esVacia
    public boolean esVacia() {
        // Este metodo determina si la cola esta vacia o no
        boolean exito = false; // Devuelve false  si la cola tiene elementos
        if (this.frente == this.fin) {
            exito = true;// Devuelve true si la cola no tiene elementos(cola vacia)
        }
        return exito;
    }

    // vaciar
    public void vaciar() {
        // Este metodo saca todos los elementos de la cola
        while (this.frente != this.fin) {// Si la cola no es vacia: 
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;

        }
    }
    //clone 

    public Cola clone() {
        // Este metodo devuelve una copia exacta de los datos de la cola original, y respetando 
        // el orden de los mismos, en otra cola del mismo tipo
        Cola colaCopia = new Cola();
       // int pos=0;
        colaCopia.fin = this.fin;
        colaCopia.frente = this.frente;
      /*  while(pos<=this.TAMANIO){
         colaCopia.arreglo[pos]= this.arreglo[pos];
         pos++;
       */ 
        colaCopia.arreglo = this.arreglo.clone();//se saca el while y colocamos esto
        return colaCopia;
    }
    // toString

    @Override
    public String toString() {
        // Este metodo crea y devueelve una cadena de caracteres formada por todos 
        // elementos de la cola para poder mostarala por pantalla
        String cadena = "";
        int auxFrente = this.frente;
        if (this.esVacia()) {
            cadena = "Cola vacia";
        } else {
            cadena = "[";
            while (auxFrente != this.fin) {

                cadena = cadena + " " + this.arreglo[auxFrente];
                auxFrente = (auxFrente + 1) % TAMANIO;
                if (auxFrente != fin) {
                    cadena = cadena + ",";

                }
            }

            cadena = cadena + " ]";
        }
        return cadena;
    }
}
