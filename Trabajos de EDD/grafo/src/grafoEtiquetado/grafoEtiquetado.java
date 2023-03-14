/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafoEtiquetado;

import dinamicas.Cola;
import dinamicas.Lista;

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
    public boolean insertarVertice(Object elemento){
        boolean exito=false;
        NodoVert aux=this.ubicarVertice(elemento);
        if(aux == null){         
            this.inicio=new NodoVert(elemento,this.inicio,null);
            exito=true;
        }
        return exito;
    }
    
    


    private NodoVert ubicarVertice(Object elemento) {
        //Este metodo recibe un elemento de tipoVertice, se lo agrega a la estructura controlando que no se inserten
        // vertices repetidos. Si puede realizar la insercion devuelve verdadero, en caso contrario false   
         NodoVert aux=this.inicio;
         while(aux!=null && !aux.getElem().equals(elemento)){
             aux=aux.getSigVertice();
         }
        return aux;
    }
    

    
    
    
    //eliminarVertice
    public boolean eliminarVertice(Object elemento) {
        //Este metodo recibe un elemento de tipoVertice se lo quita de la estructura. Si se encuentra el vertice,
        //tambien debe eliminarse todos los arcos que lo tengan como origen o destino. Si se puede realizar la
        //eliminacion con exito devuelve verdadero, en caso contrario devuelve falso.
        boolean eliminado = false, encontrado;
        NodoVert aux = this.inicio, nodoVert;
        NodoAdy ady,nodoAdy;
        int pos = 0;
        while (aux != null && !eliminado) {
            pos++;
          
            encontrado = aux.getElem().equals(elemento);//A A
            if (encontrado) {
                
              ady=aux.getPrimerAdy();//es el primer nodo adyacente 12
              //Elimina arco o los arcos
              while(ady!=null){
                  
                   nodoVert=ady.getVertice();//es el vertice B
               nodoAdy=nodoVert.getPrimerAdy();//es el primer nodoAdy del vertice B=8
              if(nodoAdy.getVertice().getElem().equals(aux.getElem())){
               nodoVert.setPrimerAdy(nodoAdy.getSigAdyacente());
              }else{
                  while(nodoAdy.getSigAdyacente()!=null){
                      if(nodoAdy.getSigAdyacente().getVertice().getElem().equals(aux.getElem())){
                          
                      nodoAdy.setSigAdyacente(nodoAdy.getSigAdyacente());
                  }else{
                          nodoAdy=nodoAdy.getSigAdyacente();
                          }
              }
                  
              }
              ady=ady.getSigAdyacente();
              }
              
                if (pos == 1) {

                  this.inicio=this.inicio.getSigVertice();//eliminamos el vertice
                 
                   
                    
                } else {
                    if (pos > 1) {
                        aux.setSigVertice(aux.getSigVertice());
                       

                    }

                }
              
                  
                   
               
                eliminado = true;

            }else{
            aux = aux.getSigVertice();
            }

        }
        return eliminado;
    }
    
    
    private NodoVert[] busca2Vertices(Object origen, Object destino) {
    //Este metodo busca los nodos vertices de los elementos origen y destino, y los almacena en un arreglo
        NodoVert aux = this.inicio;
        NodoVert nodo[];
      
        nodo = new NodoVert[2];
  
        while (aux != null &&(nodo[0]==null || nodo[1]==null)) {

            if(aux.getElem().equals(origen)) nodo[0]=aux;
             if(aux.getElem().equals(destino)) nodo[1]=aux; 
               
                aux = aux.getSigVertice();
            
            
        }
        return nodo;
    }

    
    //insertarArco LISTO
    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
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
            
           
            exito=insertarArcoAux(nodoO, nodoD, etiqueta);
            
            
        }

        return exito;
    }


    private boolean insertarArcoAux(NodoVert verticeO, NodoVert verticeD, Object etiq) {
        boolean exito=true; 
      
        NodoAdy adyO,adyD;
        //Agregamos el arco:
         //obtenemos el primer nodo adyacente 
                adyO= verticeO.getPrimerAdy();
        // si el nodo adyacente es nulo entonces
        if (adyO == null) {
            // se agrega el primer arco 
            verticeO.setPrimerAdy(new NodoAdy(verticeD,null, etiq));     
        } else {
            while (adyO.getSigAdyacente() != null  ) {          
                adyO = adyO.getSigAdyacente();    
            } 
            adyO.setSigAdyacente(new NodoAdy(verticeD, null, etiq));
        }
        
        // Agregamos el arco en sentido inverso:
        adyD=verticeD.getPrimerAdy();
        if(adyD == null){
            verticeD.setPrimerAdy(new NodoAdy(verticeO,null,etiq));
        }else{
            while(adyD.getSigAdyacente()!=null){
                adyD= adyD.getSigAdyacente();
            }
            adyD.setSigAdyacente(new NodoAdy(verticeO,null,etiq));
        }
        
        
        return exito;
    }
   
   
   

    //eliminarArco
    public boolean eliminarArco(Object origen, Object destino) {
        //Este metodo recibe dos elementos de tipoVertice(origen y destino) se quita de la estructura
        // el arco que une ambos vertices. Si el arco existe y se puede realizar la eliminacion con exito
        //devuelve verdadero, en caso contrario falso.
        boolean eliminado = false;
        
        NodoVert nodoOrigen=this.ubicarVertice(origen);
        if(nodoOrigen!=null){//seria lo mismo sacar este if ya que en el elimininarArcoAux preguntamos lo mismo
           
            eliminado=eliminarArcoAux(nodoOrigen,destino,0);
        }
        return eliminado;
    }
    private boolean eliminarArcoAux(NodoVert nodoOrigen,Object destino,int i){
         boolean encontrado=false;
        
        NodoVert nodoDest=null;
        NodoAdy nodoAdy;
    
          nodoAdy=nodoOrigen.getPrimerAdy();
          //CASO ESPECIAL: 1era posicion
           if(nodoAdy.getVertice().getElem().equals(destino)){
               nodoDest=nodoAdy.getVertice();
               encontrado=true;
               nodoOrigen.setPrimerAdy(nodoAdy.getSigAdyacente());

           }else{
         
            while(nodoAdy.getSigAdyacente()!=null  && !encontrado  ){
               
             
                if(nodoAdy.getSigAdyacente().getVertice().getElem().equals(destino)){
                     nodoDest=nodoAdy.getSigAdyacente().getVertice();   
                     
                    encontrado=true;
                      nodoAdy.setSigAdyacente(nodoAdy.getSigAdyacente());
                }      
                nodoAdy=nodoAdy.getSigAdyacente();
                
            }
           
           }   
              //Lo eliminamos en sentido inverso ahora
                if(i!=1){  
                   // System.out.println("veces");
                      eliminarArcoAux(nodoDest,nodoOrigen.getElem(),2);
                     
                
                    
       }
                
         return encontrado;  
        }
    //existeVertice LISTO
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
    public boolean existeArco(Object origen,Object destino){
    //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve verdadero si existe un arco en la
    //estructura que los une y falso en caso contrario
    boolean existe=false;
    NodoVert nodoOrigen=this.ubicarVertice(origen);
     NodoAdy ady=nodoOrigen.getPrimerAdy();
     while(ady!=null && !existe){
         if(ady.getVertice().getElem().equals(destino)){
             existe=true;
         }else{
             ady=ady.getSigAdyacente();
         }
         
     }
     return existe;
    }
    
    //existeCamino LISTO
    public boolean existeCamino(Object origen,Object destino){
    //Este metodo recibe dos elementos de tipoVertice (origen y destino), devuelve verdadero si existe al menos
    // un camino que permite llegar del  vertice origen al vertice destino y falso en caso contrario
    boolean existe=false;
     NodoVert nodoVert[],nodoOrig,nodoDest;
     Lista visitados=new Lista();
    //verifica si ambos vertices existen
   
    nodoVert=busca2Vertices(origen,destino);
    nodoOrig=nodoVert[0];
    nodoDest=nodoVert[1];
    if(nodoOrig!=null && nodoDest!=null){
       existe=existeCaminoAux(nodoOrig,destino,visitados); 
        
    }
    return existe;
    }
    
    private boolean existeCaminoAux(NodoVert nodoVert,Object destino, Lista vis){
     boolean exito=false;
     if(nodoVert!=null){
         //si el vertice nodo es el destino: HAY CAMINO
         if(nodoVert.getElem().equals(destino)){
             exito=true;
         }else{
             // si no es el destino verifica si hay camino entre nodo y dest
             vis.insertar(nodoVert.getElem(),vis.longitud()+1 );
             NodoAdy ady=nodoVert.getPrimerAdy();
             while(!exito && ady!=null){
                 if(vis.localizar(ady.getVertice().getElem())<0){
                     exito=existeCaminoAux(ady.getVertice(),destino,vis);
                     
                 }
                 ady=ady.getSigAdyacente();
             }
         }
     }
     return exito;
    }
    
  //caminoMasCorto LISTO
   public Lista caminoMasCorto(Object origen,Object destino){
   //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve un camino(lista de vertices) que
   // indique el camino que pasa por menos vertices que permite llegar del vertice origen al vertice destino.
   //Si hay mas de un camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si alguno de los vertices no
   //existe o no hay camino posible entre ellos devuelve una lista vacia
   Lista masCorto,masCortoAct;
   masCorto=new Lista();//es el camino mas corto, el cual vamos a retornar
   masCortoAct=new Lista();//es el camino mas corto actual en donde vamos visitando los nodos
   NodoVert nodoVert[],nodoOrig,nodoDest;
   nodoVert=busca2Vertices(origen,destino);
    nodoOrig=nodoVert[0];
    nodoDest=nodoVert[1];
    if(nodoOrig!=null && nodoDest!=null){
       
       masCorto=caminoMasCortoAux(nodoOrig,destino,masCortoAct,masCorto);
     
   } 
   return masCorto;
   }
   
   private Lista caminoMasCortoAux(NodoVert nodoVert, Object destino, Lista masCortoAct, Lista masCorto){      
       if(nodoVert!=null){  
         masCortoAct.insertar(nodoVert.getElem(),masCortoAct.longitud()+1 );     
       if(nodoVert.getElem().equals(destino)){
         if(masCorto.esVacia()){
             masCorto=masCortoAct.clone();
         }else{
             if(masCortoAct.longitud() < masCorto.longitud()){
                 masCorto.vaciar();
                 masCorto=masCortoAct.clone();
             }
         }       
       }else{ 
             NodoAdy ady=nodoVert.getPrimerAdy();
             while( ady!=null ){
                 if(masCortoAct.localizar(ady.getVertice().getElem())<0 ){
                    
                   masCorto=caminoMasCortoAux(ady.getVertice(),destino,masCortoAct,masCorto);     
                 }  
                 ady=ady.getSigAdyacente();
             }            
         }
       //eliminamos el ultimo nodo de la lista
       masCortoAct.eliminar(masCortoAct.longitud());
          
       
       }
   
        return masCorto;
   }
   
   //caminoMasLargo 
    public Lista caminoMasLargo(Object origen,Object destino){
   //Este metodo recibe dos elementos de tipoVertice(origen y destino), devuelve un camino(lista de vertices) que
   // indique el camino que pasa por mas vertices que permite llegar del vertice origen al vertice destino.
   //Si hay mas de un camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si alguno de los vertices no
   //existe o no hay camino posible entre ellos devuelve una lista vacia
   Lista masLargo,masLargoAct;
   masLargo=new Lista();//es el camino mas largo, el cual vamos a retornar
   masLargoAct=new Lista();//es el camino mas largo actual en donde vamos visitando los nodos
   NodoVert nodoVert[],nodoOrig,nodoDest;
   nodoVert=busca2Vertices(origen,destino);
    nodoOrig=nodoVert[0];
    nodoDest=nodoVert[1];
    if(nodoOrig!=null && nodoDest!=null){
       
       masLargo=caminoMasLargoAux(nodoOrig,destino,masLargoAct,masLargo);
     
   } 
   return masLargo;
   }
    
    private Lista caminoMasLargoAux(NodoVert nodoVert, Object destino, Lista masLargoAct, Lista masLargo){      
       if(nodoVert!=null){  
         masLargoAct.insertar(nodoVert.getElem(),masLargoAct.longitud()+1 );     
       if(nodoVert.getElem().equals(destino)){
         if(masLargo.esVacia()){
             masLargo=masLargoAct.clone();
         }else{
             System.out.println("masLArgo: "+masLargo.longitud());
             System.out.println("masLArgoAct: "+masLargoAct.longitud());
             if(masLargoAct.longitud() > masLargo.longitud()){
                 System.out.println("veo: ");
                 masLargo.vaciar();
                 masLargo=masLargoAct.clone();
             }
         }       
       }else{ 
             NodoAdy ady=nodoVert.getPrimerAdy();
             while( ady!=null ){
                 if(masLargoAct.localizar(ady.getVertice().getElem())<0 ){
                    
                   masLargo=caminoMasLargoAux(ady.getVertice(),destino,masLargoAct,masLargo);     
                 }  
                 ady=ady.getSigAdyacente();
             }            
         }
       //eliminamos el ultimo nodo de la lista
       masLargoAct.eliminar(masLargoAct.longitud());
          
       
       }
   
        return masLargo;
   }
   
   
   //listarEnProdundidad LISTO
   public Lista listarEnProfundidad(){ 
   //Este metodo devuelve una lista con los vertices del grafo visitados segun el recorrido en profundidad 
   //Arrancamos del vertice inicial
    NodoVert nodo=this.inicio;
    Lista visitados=new Lista();
    while(nodo!=null){
        if(visitados.localizar(nodo.getElem())<0){
            //si el vertice no fue visitado aun, avanza en profundidad
            listarEnProfundidadAux(nodo,visitados);
            
        }
        nodo=nodo.getSigVertice();
    }
    return visitados;
   }
   
   private void listarEnProfundidadAux(NodoVert nodoVert,Lista vis){
   
    if(nodoVert!=null){
        //marca el vertice nodoVert como visitado
        vis.insertar(nodoVert.getElem(), vis.longitud()+1);
        NodoAdy ady=nodoVert.getPrimerAdy();
        while(ady!=null){
            //visita en profundidad los adyacentes de nodoVert aun no visitados
            if(vis.localizar(ady.getVertice().getElem())<0){
                listarEnProfundidadAux(ady.getVertice(),vis);
                
            }
            ady=ady.getSigAdyacente();
        }
    }
   }
   
   //listarEnAnchura LISTO
   public Lista listarEnAnchura(){
       //Este metodo devuelve una lista con los vertices del grafo visitados segun el recorrido en anchura
       Lista visitados=new Lista();
       //Arrancamos del verice inicial
       NodoVert nodo=this.inicio;
       while(nodo!=null){
           if(visitados.localizar(nodo.getElem())<0){
               listarEnAnchuraAux(nodo,visitados);
           }
           nodo=nodo.getSigVertice();
       }
       return visitados;
   }
   
   private void listarEnAnchuraAux(NodoVert nodoVert,Lista vis){
    Cola cola =new Cola();
    cola.poner(nodoVert.getElem());
    while(!cola.esVacia()){
        Object elem=cola.obtenerFrente();
        cola.sacar();
        vis.insertar(elem, vis.longitud()+1);
        NodoAdy ady=nodoVert.getPrimerAdy();
        while(ady!=null){
            if(vis.localizar(ady.getVertice().getElem())<0){
                cola.poner(ady.getVertice().getElem());
            }
            ady=ady.getSigAdyacente();
        }
    }
    
   }
    
   
   //clonar VER
   
  public grafoEtiquetado clonar(){
  //Este metodo genera y devuelve un grafo que es equivalente(igual estructura y contenido de los
  // nodos) al original
  grafoEtiquetado clon=new grafoEtiquetado();
  NodoVert nodoVert ,vertSig;
  NodoAdy ady1,ady2;
  if(this.inicio!=null){
       clon.inicio=new NodoVert(this.inicio.getElem(),null,null);
        ady1=this.inicio.getPrimerAdy();
      if(ady1!=null){
          System.out.println("Veo: "+ady1.getVertice().getElem());
          clon.inicio.setPrimerAdy(new NodoAdy(ady1.getVertice(),null,ady1.getEtiqueta()));
          System.out.println("etiqueta: "+ady1.getEtiqueta());
          System.out.println("Clon: "+clon.inicio.getPrimerAdy().getVertice().getElem());
          ady2=clon.inicio.getPrimerAdy();
          while(ady1.getSigAdyacente()!=null){
              ady1=ady1.getSigAdyacente();
              System.out.println("es: "+ady1.getVertice().getElem());
              ady2.setSigAdyacente(new NodoAdy (ady1.getVertice(),null,ady1.getEtiqueta()));
           
          }
          
  }
      while(this.inicio.getSigVertice()!=null){
          //Se asume que el siguiente nodo de clonVert es nulo
           vertSig=this.inicio.getSigVertice();
          
             clon.inicio.setSigVertice(new NodoVert(vertSig.getElem(),null,null));
           
             ady1=vertSig.getPrimerAdy();
             if(ady1!=null){
               clon.inicio.setPrimerAdy(new NodoAdy(vertSig,null,ady1.getEtiqueta()));
               ady2=clon.inicio.getPrimerAdy();
               while(ady1.getSigAdyacente()!=null){
                   ady1=ady1.getSigAdyacente();
                   ady2.setSigAdyacente(new NodoAdy(ady1.getVertice(),null,ady1.getEtiqueta()));
               }
               
                 
          }
             this.inicio=this.inicio.getSigVertice();
      } 
             
  }
  return clon;
  }
  public grafoEtiquetado clonar1(){
      grafoEtiquetado clone=new grafoEtiquetado();
      NodoVert vertice=this.inicio;
      NodoAdy  ady1,ady2;
      while(vertice!=null){
          System.out.println("Nodo: "+vertice.getElem());
          //Clonamos el nodo vertice
          clone.inicio=new NodoVert(vertice.getElem(),null,null);
          //Guardamos el primer nodo adyacente del vertice
          ady1=vertice.getPrimerAdy();
          if(ady1!=null){
              clone.inicio.setPrimerAdy(new NodoAdy(ady1.getVertice(),null,ady1.getEtiqueta()));
              //Almacenamos el primer nodo adyacente clone en ady2
              ady2=clone.inicio.getPrimerAdy();
              while(ady1.getSigAdyacente()!=null){
                  //Como el siguiente ady1 no es nulo, vamos al siguiente
                  ady1=ady1.getSigAdyacente();
                  ady2.setSigAdyacente(new NodoAdy(ady1.getVertice(),null,ady1.getEtiqueta()));
               
              }
             clone.inicio=vertice;
          }
          
          vertice=vertice.getSigVertice();
      }
      return clone;
      
  }
  public grafoEtiquetado clonar2(){
      grafoEtiquetado g= new grafoEtiquetado();
      if(this.inicio!=null){
          g.inicio=(cloneAux(this.inicio));
      }
      return g;
  }
  private NodoVert cloneAux(NodoVert vertice){
      NodoAdy ady, adyClon;
      NodoVert nodoVert;
       
    
         nodoVert=new NodoVert(vertice.getElem(),null,null);
          ady=vertice.getPrimerAdy();
          if(ady!=null){
              nodoVert.setPrimerAdy(new NodoAdy(ady.getVertice(),null,ady.getEtiqueta()));
              System.out.println("NodoOrigen: "+vertice.getElem() + " NodoDestino: " +ady.getVertice().getElem()+ " con etiqueta: "+ady.getEtiqueta());
          adyClon=nodoVert.getPrimerAdy();
          while(ady.getSigAdyacente()!=null){
                ady=ady.getSigAdyacente();
              System.out.println("es: "+ady.getVertice().getElem());
              System.out.println("eti: "+ady.getEtiqueta());
              adyClon.setSigAdyacente(new NodoAdy(ady.getVertice(),null,ady.getEtiqueta()));
              System.out.println(" NodoOrigen: " +vertice.getElem()+ " NodoDestino: " +ady.getVertice().getElem()+ " con etiqueta: " +ady.getEtiqueta());
          }
               NodoVert nodo=vertice.getSigVertice();
         if(nodo!=null){ 
        cloneAux(nodo);
      }
         
         }
      return nodoVert;
  }
  
  //toString LISTO
   
    @Override
  public String toString(){
  //Este metodo genera y devuelve una cadena String que muestra los vertices almacenados en el grafo
  //y que adyacentes tiene cada uno de ellos
  String cadena="";
  
  if(this.inicio==null){
      cadena="Grafo vacio";
      
  }else{
      NodoVert nodoVert=this.inicio;
      System.out.println("Grafo: ");
      while(nodoVert!=null){
          cadena=cadena+"Nodo Vertice: "+nodoVert.getElem()+"\n";
          NodoAdy ady=nodoVert.getPrimerAdy();
          while(ady!=null){
            cadena=cadena+"El nodo: "+nodoVert.getElem()+" y el nodo: "+ady.getVertice().getElem()+" tienen un arco, con etiqueta: "+ady.getEtiqueta()+"\n";
            ady=ady.getSigAdyacente();
          }
        
          nodoVert=nodoVert.getSigVertice();
      }
  }
  return cadena;
  }
  

    //vacio LISTO
    public boolean vacio() {
        //Este metodo devuelve falso si hay al menos un vertice cargado en el grafo y verdadero en caso contrario
        boolean vacio = true;
        if (this.inicio != null) {
            vacio = false;
        }
        return vacio;
    }
    
  


}
