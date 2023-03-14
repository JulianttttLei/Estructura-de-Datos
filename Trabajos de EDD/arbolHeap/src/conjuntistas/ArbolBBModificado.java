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
public class ArbolBBModificado {

    private NodoABB raiz;

    public ArbolBBModificado() {
        this.raiz = null;
    }

    //insertar
    public boolean insertar(Comparable elem) {
        //Este método recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento
        //ya se encuentra en el árbol no se realiza la inserción. Devuelve true si el elemento
        //se agrega a la estructura y false en caso contrario.

        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elemento) {
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
                    exito = insertarAux(nodo.getIzquierdo(), elemento);
                } else {
                    nodo.setIzquierdo(new NodoABB(elemento, null, null));
                }
            } else {
                //elemento es mayor que nodo.getElem()
                //si tiene HD baja a la derecha, sino agrega elemento
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), elemento);
                } else {
                    nodo.setDerecho(new NodoABB(elemento, null, null));
                }

            }
        }
        return exito;
    }

//listarRango
 public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
//Este método recorre parte del árbol(sólo lo necesario) y devuelve una lista
//ordenada con los elementos que se encuentran en el intervalo [elemMinimo,elemMaximo].
        Lista listaRan = new Lista();
        listarRangoAux(elemMinimo, elemMaximo, this.raiz, listaRan);
        return listaRan;
    }

    private void listarRangoAux(Comparable elemMin, Comparable elemMax, NodoABB nodo, Lista ls) {
        if (nodo != null) {

            if (elemMin.compareTo(nodo.getElem()) < 0) {
                listarRangoAux(elemMin, elemMax, nodo.getIzquierdo(), ls);
            }
            System.out.println("veo: "+nodo.getElem());
            if (elemMax.compareTo(nodo.getElem()) >= 0 && elemMin.compareTo(nodo.getElem()) <= 0) {
                System.out.println("otro: "+nodo.getElem());
                ls.insertar(nodo.getElem(), ls.longitud()+1);
            }
            if (elemMax.compareTo(nodo.getElem()) > 0) {
                listarRangoAux(elemMin, elemMax, nodo.getDerecho(), ls);
            }

        }
    }
//eliminarMax
   public boolean eliminarMax(){
       return obtenerMax(this.raiz,null);
   }
    private boolean obtenerMax(NodoABB nodo,NodoABB padreMax) {
        //Este metodo elimina el maximo elemento
        Comparable elemMax = null;
        NodoABB nodoMax=null;
        while (nodo != null) {
            elemMax = nodo.getElem();
            if(nodo.getDerecho()!=null){
                padreMax=nodo;
            }
            nodoMax=nodo;
            nodo = nodo.getDerecho();
        }
        return eliminarNodo(nodoMax,padreMax, elemMax);
    }
    public  boolean eliminarMin(){
        
       return obtenerMin(this.raiz,null);
    }
   

//obtener minimo
    public boolean obtenerMin(NodoABB nodo,NodoABB padreMin) {
       // Este metodo elimina el minimo elemento
        Comparable elemMin=null;
      NodoABB nodoMin = null;
      
       while(nodo!=null){
           elemMin=nodo.getElem();
         if(nodo.getIzquierdo()!=null){
             padreMin=nodo;
         }
                  nodoMin=nodo;
                  nodo=nodo.getIzquierdo();
         }   
       

        return eliminarNodo(nodoMin,padreMin,elemMin);
        
    }
   
    

//eliminarNodo
    private boolean eliminarNodo(NodoABB nodoElem, NodoABB padre, Comparable elemento) {
        // Este metodo elimina un nodo dado por parametro, utilizando el elemento 
        boolean exito = true;//exito=false;
                      
                        if (nodoElem.getIzquierdo() == null && nodoElem.getDerecho() == null) {
                            //CASO 1:
                            eliminarHoja(elemento, padre);
                        } else {
                            if ((nodoElem.getDerecho() == null && nodoElem.getIzquierdo() != null) || 
                                    (nodoElem.getIzquierdo() == null && nodoElem.getDerecho() != null)) {
                                //CASO 2: 
                                eliminarCon1Hijo(padre, nodoElem);
                            } else {
                                //CASO 3:
                                eliminarCon2Hijos(nodoElem);

                            }

                        }
                        
                
            

        
        return exito;
}
    
// CASO1: elimiminarHoja
    private void eliminarHoja(Comparable elemento, NodoABB padre) {
        //caso especial: cuando  elemento  es 1  hoja y tiene un unico elemento
        if (padre == null) {
            this.raiz = null;
        } else {
            //caso comun: cuando tiene muchos elementos y el elemento es 1 hoja   
            if (elemento.compareTo(padre.getElem()) > 0) {
                padre.setDerecho(null);
            } else {
                if (elemento.compareTo(padre.getElem()) < 0) {
                    padre.setIzquierdo(null);
                }
            }
        }
    }

    //CASO2: eliminarCon1Hijo
    private void eliminarCon1Hijo(NodoABB padre, NodoABB hijo) {
        //caso especial:
        if (padre == null) {
            if (hijo.getDerecho() == null) {
                hijo.setElem(hijo.getIzquierdo().getElem());
                hijo.setIzquierdo(null);
            } else {
                if (hijo.getIzquierdo() == null) {
                    hijo.setElem(hijo.getDerecho().getElem());
                    hijo.setDerecho(null);
                }
            }
        } else {
            //caso comun:
            
            if (hijo.getDerecho() == null && hijo.getIzquierdo() != null) {
                padre.setDerecho(hijo.getIzquierdo());
            } else {
                if (hijo.getIzquierdo() == null && hijo.getDerecho() != null) {
                    padre.setIzquierdo(hijo.getDerecho());
                }
            }
        }

    }
    
    //CASO3: eliminarCon2Hijos-Modificar (esta bien en el package de trabajoFinalEDD2020)
    private void eliminarCon2Hijos(NodoABB nodo) {
        NodoABB candidato = nodo.getDerecho(), padreCandidato = nodo;
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

    private String obtenerToString(NodoABB nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena += "\n" +" Nodo " + nodo.getElem();
            
            if (nodo.getIzquierdo() != null) {
               
                cadena += " HI: " + nodo.getIzquierdo().getElem().toString();
                obtenerToString(nodo.getIzquierdo());
            } else {
                cadena += " HI: " + null;
            }
            if (nodo.getDerecho() != null) {
                cadena += " HD: " +nodo.getDerecho().getElem().toString();
                obtenerToString(nodo.getDerecho());
            } else {
                cadena += " HD: " + null ;
            }
            
           cadena+=obtenerToString(nodo.getIzquierdo());
           cadena+=obtenerToString(nodo.getDerecho());
           
            
        }
        return cadena;
    }
     //clone
    public ArbolBBModificado clone() {
        // Este metodo genera y devuelve un arbol binario que es equivalente al arbol original
        ArbolBBModificado clon = new ArbolBBModificado();

        if (this.raiz != null) {
            clon.raiz = (obtenerClone(this.raiz));
        }
        return clon;

    }

    private NodoABB obtenerClone(NodoABB nodo) {
        // System.out.println("El nodo es: "+nodo.getElem());

        NodoABB nodoNew = new NodoABB(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {

            nodoNew.setIzquierdo(obtenerClone(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nodoNew.setDerecho(obtenerClone(nodo.getDerecho()));
        }

        return nodoNew;
    }
 //Metodos para practicar para el simulacro parcial 2
 
  /*Implementar el método clonarParteInvertida(elem) que devuelve un nuevo
árbol que es una copia del subárbol original, cuya raíz es el elemento dado
y cada hijo está cambiado de lugar . Si el elemento no existe, el árbol que devuelve es vacío. Ejemplo: si
tiene el árbol A de la derecha y elem=13, el resultado debe ser el árbol B
REALIZAR LA TRAZA CON EL ARBOL DEL EJEMPLO. PROPONGA AL MENOS OTRO CASO
DE PRUEBA QUE TENGA RESULTADO DISTINTO*/
    
// clonarParteInvertida
 
 // Este metodo devuelve un nuevo arbo que es una copia del subarbol original, cuya raiz es el elemento dado y cada
 //hijo esta cambiado de lugar. Si el elemento no existe 
 public ArbolBBModificado clonarInvertido(Comparable elemento){
    ArbolBBModificado clonNodo = new ArbolBBModificado();
    NodoABB auxNodo=obtenerNodo(this.raiz,elemento);
        if (this.raiz!=null && auxNodo!=null) {
            clonNodo.raiz=new NodoABB(elemento,null,null);
            clonarInvertidoAux(clonNodo.raiz,auxNodo);
        }
        return clonNodo;
    }
 
private void clonarInvertidoAux(NodoABB nodoClon,NodoABB n){
       //Recorre los nodos izquierdos copiando de forma de espejo en el clon los nodos derecho
       NodoABB newNodo;
        if (n.getIzquierdo()!=null) {
            newNodo= new NodoABB(n.getIzquierdo().getElem(),null,null);
            nodoClon.setDerecho(newNodo);
            clonarInvertidoAux(nodoClon.getDerecho(),n.getIzquierdo());
        }
        //Recorre los nodos derechos copiando de forma de espejo en el clon los nodos izqiuerdos
 
        if (n.getDerecho()!=null) {
            newNodo= new NodoABB(n.getDerecho().getElem(),null,null);
            nodoClon.setIzquierdo(newNodo);
            clonarInvertidoAux(nodoClon.getIzquierdo(),n.getDerecho());
        }
}        
 private NodoABB obtenerNodo(NodoABB n, Comparable elemBuscado) {
        //Motodo privado que busca un elemento y devuelve el nodo que lo contiene.
        //Si no se encuentra buscado devuelve null.
 
        NodoABB res = null;
        if (n != null) {
            if (n.getElem().compareTo(elemBuscado)==0) {
                //si el buscado es n, lo devuelve
                res = n;
            } else {
                //no es el buscado: busca primero en el HI              
                res = obtenerNodo(n.getIzquierdo(), elemBuscado);
 
                //si no lo encuetra en el HI, busca en HD
                if (res == null) {
                    res = obtenerNodo(n.getDerecho(), elemBuscado);
                }
            }
        }
        return res;
    }
 //  clonar  rango invertido
 public ArbolBBModificado clonarInvertidoPorRango(Comparable elemMin,Comparable elemMax){
  // Este metodo clona e invierte un determinado rango de arbol binario de busqueda
     ArbolBBModificado clon=new ArbolBBModificado();
     NodoABB auxNodo;
     if(this.raiz!=null){
        auxNodo=verificarRaiz(this.raiz,elemMin,elemMax);
         System.out.println("auxNodo: "+auxNodo.getElem());
        clon.raiz=clonarInvertidoAux(auxNodo,elemMin,elemMax);
     }
     return clon;
     
 }
 private NodoABB verificarRaiz(NodoABB n,Comparable elementoMin,Comparable elementoMax){
     while(n.getElem().compareTo(elementoMin)<0 || n.getElem().compareTo(elementoMax)>0){
         if(n.getElem().compareTo(elementoMin)<0){
             n=n.getDerecho();
         }else{
             if(n.getElem().compareTo(elementoMax)>0){
                 n=n.getIzquierdo();
             }
         }
     }
     return n;
 }
 private NodoABB clonarInvertidoAux(NodoABB nodo,Comparable elementoMin,Comparable elementoMax){
     NodoABB nodoAux = null;
     if(nodo!=null){
         if(nodo.getElem().compareTo(elementoMin)>=0 && nodo.getElem().compareTo(elementoMax)<=0){
             nodoAux=new NodoABB(nodo.getElem(),null,null);
             if(nodo.getIzquierdo()!=null && nodo.getElem().compareTo(elementoMin)>=0){
                 nodoAux.setDerecho(nodo.getIzquierdo());
                 clonarInvertidoAux(nodo.getIzquierdo(),elementoMin,elementoMax);
             }
             if(nodo.getDerecho()!=null && nodo.getElem().compareTo(elementoMax)<=0){
                 nodoAux.setIzquierdo(nodo.getDerecho());
                 clonarInvertidoAux(nodo.getDerecho(),elementoMin,elementoMax);
             }
             
                     
         }
     }
     return nodoAux;
     
 }
 //Metodo lista mayor igual
 
 /*En el TDA árbol binario de búsqueda, agregar el método listarMayorIgual(elem) que dado un elemento
devuelve una lista con los elementos mayores o iguales que elem ordenados de mayor a menor. El método
debe recorrer lo mínimo indispensable del árbol. 
 */
 public Lista listarMayorIgual(Comparable elem){
  //Este metodo devuelve una lista con los elementos mayores  o iguales que elem ordenados de mayor a menor   
    Lista lista=new Lista();
 
    listarMayorIgualAux(this.raiz,elem,lista);
    return lista;
 }
 private void listarMayorIgualAux(NodoABB nodo,Comparable elemento,Lista ls){
     if(nodo!=null){
     
           listarMayorIgualAux(nodo.getDerecho(),elemento,ls);
     
     if(elemento.compareTo(nodo.getElem())<=0){
         ls.insertar(nodo.getElem(), ls.longitud()+1);//colocando el 1 tendremos de menor a mayor sino al reves
     }
     if(elemento.compareTo(nodo.getElem())<0){
           listarMayorIgualAux(nodo.getIzquierdo(),elemento,ls);
     }
 }
//listarMayores es lo mismo pero sin el igual
 }
 
 // Metodo lista menor igual
 public Lista listarMenorIgual(Comparable elem){
  //Este metodo devuelve una lista con los elementos menores  o iguales que elem ordenados de menor a mayor   
    Lista lista=new Lista();
 
    listarMenorIgualAux(this.raiz,elem,lista);
    return lista;
 }
 private void listarMenorIgualAux(NodoABB nodo,Comparable elemento,Lista ls){
     if(nodo!=null){
           listarMenorIgualAux(nodo.getIzquierdo(),elemento,ls);
           if(elemento.compareTo(nodo.getElem())>=0){
               ls.insertar(nodo.getElem(), ls.longitud()+1);
           }
           if(elemento.compareTo(nodo.getElem())>0){
               listarMenorIgualAux(nodo.getDerecho(),elemento,ls);
           }
     
 }}
 //listar menores es lo mismo pero sin el igual 
 
 //METODO FRONTERA
public Lista frontera(){
  // Este metodo lista las hojas de un ABB  
  Lista listaHojas=new Lista();
  fronteraAux(this.raiz,listaHojas);
  return listaHojas;
}
private void fronteraAux(NodoABB nodo,Lista lsHojas){
    if(nodo!=null){
        fronteraAux(nodo.getIzquierdo(),lsHojas);
        fronteraAux(nodo.getDerecho(),lsHojas);
        
        
        if(nodo.getIzquierdo()==null && nodo.getDerecho()==null){
            
            lsHojas.insertar(nodo.getElem(), lsHojas.longitud()+1);
        }
    }
}
//METODO FRONTERA MAYOR
public Lista fronteraMayor(Comparable elem) {
        Lista lista = new Lista();
        fronteraMayorAux(this.raiz, lista, elem);
        return lista;
    }
private void fronteraMayorAux(NodoABB nodo,Lista ls,Comparable elemento){
    if(nodo!=null){
        fronteraMayorAux(nodo.getIzquierdo(),ls,elemento);
        fronteraMayorAux(nodo.getDerecho(),ls,elemento);
        if(nodo.getIzquierdo()==null && nodo.getDerecho()==null){
            if(nodo.getElem().compareTo(elemento)>0){
                ls.insertar(nodo.getElem(), ls.longitud()+1);
            }
        }
        
    }
}
//METODO FRONTERA MENOR
public Lista fronteraMenor(Comparable elem) {
        Lista lista = new Lista();
        fronteraMenorAux(this.raiz, lista, elem);
        return lista;
    }
private void fronteraMenorAux(NodoABB nodo,Lista ls,Comparable elemento){
    if(nodo!=null){
        fronteraMenorAux(nodo.getIzquierdo(),ls,elemento);
        fronteraMenorAux(nodo.getDerecho(),ls,elemento);
        if(nodo.getIzquierdo()==null && nodo.getDerecho()==null){
            if(nodo.getElem().compareTo(elemento)<0){
                ls.insertar(nodo.getElem(), ls.longitud()+1);
            }
        }
        
    }  
}
public Lista fronteraRango(Comparable minimo, Comparable maximo) {
        Lista lista = new Lista();
        fronteraRangoAux(this.raiz, lista, minimo, maximo);
        return lista;
    }

    private void fronteraRangoAux(NodoABB nodo, Lista lista, Comparable minimo, Comparable maximo) {
        if (nodo != null) {
           
            if (maximo.compareTo(nodo.getElem()) > 0) {
                fronteraRangoAux(nodo.getDerecho(), lista, minimo, maximo);
            }
            if (minimo.compareTo(nodo.getElem()) < 0) {
                fronteraRangoAux(nodo.getIzquierdo(), lista, minimo, maximo);
            }
            if (minimo.compareTo(nodo.getElem()) <= 0 && maximo.compareTo(nodo.getElem()) >= 0 &&
                    nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                lista.insertar(nodo.getElem(), 1);
            }
            
        }
    }
        
        //METODO CLONAR MAYOR
         public ArbolBBModificado cloneMayor(Comparable minimo) {
        ArbolBBModificado arbol = new ArbolBBModificado();
        arbol.raiz = cloneMayorAux(this.raiz, minimo);
        return arbol;
    }

    private NodoABB cloneMayorAux(NodoABB nodo, Comparable minimo) {
        NodoABB clon = null;
        if (nodo != null) {
            Comparable elemento = nodo.getElem();
            if (elemento.compareTo(minimo) >= 0) {
                NodoABB izquierdo = cloneMayorAux(nodo.getIzquierdo(), minimo);
                NodoABB derecho = cloneMayorAux(nodo.getDerecho(), minimo);
                clon = new NodoABB(elemento, izquierdo, derecho);
            } else {
                clon = cloneMayorAux(nodo.getDerecho(), minimo);
            }
        }
        return clon;
    }
    /*
    Implementar el método listarMayoresQue(valor,elem) que devuelve una
Lista con los elementos mayores que valor del subárbol con raíz elem.
Si no existe elem en el árbol, el método deberá devolver una lista vacía.
Ejemplo: En el árbol de la figura, si valor es 9, y elem es 12, deberá
devolver la lista con los elementos [10,12,14,17]
    */
    public Lista listarMayoresQue(Comparable valor,Comparable elem){
        Lista ls=new Lista();
       
        if(this.raiz!=null){
             NodoABB nodoNew=obtenerNodo(this.raiz,elem);
              listarMayorIgualAux1(nodoNew,valor,ls);       
        }
        return ls;
            
        }
    
    private void listarMayorIgualAux1(NodoABB nodo,Comparable elemento,Lista ls){
     if(nodo!=null){
     
           listarMayorIgualAux(nodo.getDerecho(),elemento,ls);
     
     if(elemento.compareTo(nodo.getElem())<=0){
         ls.insertar(nodo.getElem(), ls.longitud()+1);//colocando el 1 tendremos de menor a mayor sino al reves
     }
     if(elemento.compareTo(nodo.getElem())<0){
           listarMayorIgualAux(nodo.getIzquierdo(),elemento,ls);
     }
 }
    
    
    
}
   
    
    }

    

