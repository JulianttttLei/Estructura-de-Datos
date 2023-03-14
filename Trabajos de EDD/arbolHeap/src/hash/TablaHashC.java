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
public class TablaHashC {
    private int TAMANIO;
    private CeldaHash[] tabla;
    private int cant;
    private int VACIO=0;
    private int OCUPADO=1;
    private int BORRADO=-1;
    
    public TablaHashC(int tam){
        this.TAMANIO=tam;
        this.tabla=new CeldaHash[this.TAMANIO];
        this.cant=0;
         int pos=0;
        CeldaHash aux;
        aux=new CeldaHash(null,this.VACIO);
        while(pos<this.TAMANIO){
            this.tabla[pos]=aux;
            pos++;
        }
       
    }
//insertar
public boolean insertar(Object elemento){
//Este metodo recibe un elemento e intenta insertarlo en la tabla. Si todo funciona OK
//(no esta repetido y hay lugar suficente en la tabla) devuelve verdadero, si hay algun 
// problema devuelve falso
boolean exito=false,iguales=false;
CeldaHash aux;
   
int pos,incremento,elem,intento=1,posIni,p=0;
elem=elemento.hashCode();
pos=elem % this.TAMANIO;
posIni=pos; 
incremento=elem / this.TAMANIO;
aux=new CeldaHash(elemento,this.OCUPADO);

while(!exito  && intento<this.TAMANIO  ){
    if( this.tabla[pos].getEstado()!=this.OCUPADO){
       this.tabla[pos]=aux;
   this.cant++;
   exito=true;
    }else{
//Si esta OCUPADO , aplica rehashing doble
  
exito=(this.tabla[pos].getElem().equals(elemento));
iguales=exito;
      
if(!exito ){
 pos=(posIni+intento*incremento);
      if(pos>this.TAMANIO){
          pos=pos % this.TAMANIO;
      }
  intento++;
}
if(iguales){
    System.out.println("ERROR");
    exito=false;
}

    
}
}
if(iguales){
    System.out.println("ERROR");
    exito=false;
}
    System.out.println("p: "+pos);

    
return exito;
}
 //eliminar
 public boolean eliminar(Object buscado){
 //Este metodo recibe el elemento e intenta insertarlo en la tabla. Si todo funciona OK
 //(no esta repetido y hay lugar suficiente en la tabla) devuelve verdadero, si hay algun
 // problema devuelve falso
 int pos,incremento,elemBuscado,intento=1,posIni;
 boolean encontrado=false;
 elemBuscado=buscado.hashCode();
 pos=elemBuscado % this.TAMANIO;//posicion inicial
 posIni=pos;
 incremento = elemBuscado/this.TAMANIO;//devuelve un incremento fijo para cada elemento;
 
 //busca el elemento hasta encontrarlo o encontrar una celda vacia
 //o para despues de this.TAMANIO intentos
 while(!encontrado && intento<this.TAMANIO && this.tabla[pos].getEstado()!=this.VACIO){
      
     if(this.tabla[pos].getEstado()==this.OCUPADO){
         encontrado=(this.tabla[pos].getElem().equals(buscado));
         if(encontrado){
             //si lo encuentra lo marca y para el ciclo
            
             this.tabla[pos].setEstado(this.BORRADO);
             this.cant--;
         }
  
         
         
     }
     pos=(posIni+(intento*incremento)) % this.TAMANIO;
     intento++;
 }
 return encontrado;
 }
 
 //pertenece
 public boolean pertenece(Object elemento){
 //Este metodo recibe el elemento y devuelve verdadero si ya esta cargado en la tabla y falso en caso
 //contrario
 boolean pertenece=false;
 int pos,incremento,elem,intento=1,posIni;
 elem=elemento.hashCode();
 pos=elem % this.TAMANIO;
 posIni=pos;
 incremento=elem/this.TAMANIO;
  
 while(!pertenece && intento<this.TAMANIO && this.tabla[pos].getEstado()==this.OCUPADO){
     
    
      pertenece=(this.tabla[pos].getElem().equals(elemento));
      
      if(!pertenece ){
          pos=(posIni+intento*incremento);
          if(pos>this.TAMANIO){
              pos=pos % this.TAMANIO;
          }
          intento++;
      }
      
 }
 return pertenece;
 }
  
//esVacia
 public boolean esVacia(){
 //Este metodo devuelve falso si hay al menos un elemento cargado en la tabla y verdadero en caso
 //contrario
  boolean vacio=false;
 if(this.cant==0){
     vacio=true;
 }
 return vacio;
 }
 
//listar 
public Lista listar(){
 //Este metodo recorre la tabla completa y devuelve una lista con los elementos que se encuentran
 //almacenados en la tabla 
 Lista ls=new Lista();
 Object elemento;
 int pos=0,posLs=1,estado,est;
 while(pos<this.TAMANIO){
   
    
     if( this.tabla[pos].getEstado()==this.OCUPADO ){
          System.out.println("veo: "+this.tabla[pos].getElem());
         elemento=this.tabla[pos].getElem();
         ls.insertar(elemento, posLs);
         posLs++;
     }
     pos++;
 }
 return ls;
}
}
