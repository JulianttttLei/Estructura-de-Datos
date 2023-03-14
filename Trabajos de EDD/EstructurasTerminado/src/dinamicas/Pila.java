package dinamicas;




public class Pila {

  private Nodo tope;
  
  // constructor 
  public Pila(){
      this.tope=null;
  }
  
  // Apilar
  public boolean apilar(Object element){
      
      // crea un nuevo nodo delante de la antigua cabecera
      Nodo nuevo= new Nodo(element,this.tope);
      
      //actualiza el tope para que apunte al nodo nuevo
      this.tope=nuevo;
      
      //nunca hay error de pila llena, entonces devuelve true;
      return true;
  }
  
  // Desapilar
  public boolean desapilar(){
     boolean exito=false; // Devuelve falso si la pila esta vacia 
    Nodo aux;
     if(this.tope!=null){
      aux=this.tope.getEnlace();
      this.tope=aux;
      //Devuelve true si la pila no estaba vacia al momento de desapilar
         exito=true;
        }
     return exito;
     }
  
  // obtener tope
 public Object obtenerTope(){
     Object sacarElem=null;
    
     
     // Precondicion: la pila no esta vacia
     if(this.tope!=null){
         
         sacarElem=this.tope.getElem();
       
     }
     return sacarElem;
 }
 // es vacia
 public boolean esVacia(){
     boolean vacio=false;//Devuelve false si la pila tiene elementos
     if(this.tope==null){
         vacio=true; //Devuelve true si la pila no tiene elementos
         
     }
     
     
 return vacio;    
 }
 
 //vaciar
 public void vaciar(){ 
       // Este metodo saca todo los elementos de la pila
        tope=null;
             
         }
         
     
 
 public Pila clone(){
     // Este metodo devuelve una copia exacta de los datos de la estructura original, respetando el orden de los mismos,
       // en otra estructura del mismo tipo 
    Pila pilaClon=new Pila();
     return pilaCopia(pilaClon,this.tope); 
 }
     private Pila pilaCopia(Pila pilaClon,Nodo aux){
         
     
      
      if(aux!=null){
           
         pilaClon=pilaCopia(pilaClon,aux.getEnlace());
         Nodo nodoClon=new Nodo(aux.getElem(),pilaClon.tope);
   
          pilaClon.tope=nodoClon;
          
      }
      return pilaClon;
   
      
      
      
      
     
 }
  @Override
 public String toString(){
     // Este metodo devuelve una cadena de caracteres formada por todos los elementos de la pila para
       // poder mostarla por pantalla
    String texto="";
    if(this.tope==null)
        texto="Pila vacia";
    else{
        //se ubica para recorrer la pila
        Nodo aux=this.tope;
        texto = "[";
        
        while(aux!=null){
            //agrega el texto del elemento y avanza
            texto += aux.getElem().toString();
            aux=aux.getEnlace();
            if(aux!=null)
                texto += ",";
        }
            texto+= "]";
        }
        
        return texto;
        
            
        
   
   
    
 }
 
}
 
  
   
  
    

