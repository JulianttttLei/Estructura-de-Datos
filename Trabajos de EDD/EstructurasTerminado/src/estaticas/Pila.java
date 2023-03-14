package estaticas;



public class Pila {

    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    // OPERACIONES BASICAS DEL TDA PILA
    //Constructor vacio
    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }
    //Apilar
public int getTope(){
   return this.tope;
}
        

    public boolean apilar(Object elemento) {
        boolean exito;
        if (this.tope + 1 >= this.TAMANIO) //ERROR: pila llena
        {
            exito = false;
        } else {
            // pone el elemento en el tope de la pila e incrementa tope
            this.tope++;
            this.arreglo[tope] = elemento;
            exito = true;
        }
        return exito;
    }
    //Desapilar

    public boolean desapilar() {
            boolean exito = false;// Devuelve false si la pila esta vacia
        if (this.tope >= 0) {
            //Devuelve true si la pila no estaba vacia al momento de desapilar  
            this.arreglo[tope] = null;// eliminamos el elemento
            this.tope--;
            exito = true;

        }
        return exito;
    }

    // Obtener tope
    public Object obtenerTope() {
        Object elem = null;
        if (this.tope > 0) {// Precondicion: la pila no esta vacia, por eso tomamos tope a partir de la posicion 1
            elem = this.arreglo[this.tope];//Devuelve el elemento en el tope de la pila
        }

        return elem;

    }
// es vacia
    public boolean esVacia() {
        boolean exito = false;
        if (this.tope < 0) {
            exito = true;// Devuelve true si la pila no tiene elementos
        }
        return exito;
    }
    //Vaciar
    public void vaciar() {
        // Este metodo saca todos los elementos de la pila
        while (tope >= 0) {
            this.arreglo[tope] = null;
            tope--;
        }
    }
// clone
    @Override
    public Pila clone() {
        // Este metodo devuelve una copia exacta de los datos de la estructura original, respetando el orden de los mismos,
        // en otra estructura del mismo tipo 
        Pila copiaPila;
        copiaPila = new Pila();
     // Inicializamos copiaTope.tope con el tope original
        copiaPila.tope = this.tope;
        while (copiaPila.tope >= 0) {
            copiaPila.arreglo[copiaPila.tope] = this.arreglo[copiaPila.tope];
            copiaPila.tope--;
        }
        copiaPila.tope = this.tope;
        return copiaPila;

    }
// toString
    @Override
    public String toString() {
        // Este metodo devuelve una cadena de caracteres formada por todos los elementos de la pila para
        // poder mostrarla por pantalla
        String cadena="";
        int pos=0;
        while(pos<=arreglo.length-1){
            cadena= cadena + this.arreglo[pos]+ " ,";
            pos++;
         
        }
        
        cadena= " [ "+ cadena +" ]  ";
        return cadena;
        
    }

}
