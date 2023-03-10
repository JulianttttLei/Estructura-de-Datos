/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpFinalEDAT2020;

import Estructuras.Diccionario;
import Estructuras.Lista;
import Estructuras.grafoEtiquetado;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 *
 * @author agust
 */
public class agregaCargasIniciales {
    
    
   public static void agregaAeropuertos(BufferedWriter escribe, grafoEtiquetado aeropuertos) 
           throws FileNotFoundException, IOException{
       // Este metodo agrega los aeropuertos al fichero de texto, llamado log
       String nombre,ciudad,numTelefono,bfRead,direccion,palabra[],ruta;
       ruta=System.getProperty("user.dir");
       direccion=ruta+"\\src\\cargaInicial\\Aeropuerto.txt";
       BufferedReader lectura=new BufferedReader(new FileReader(direccion));   
       boolean exito;     
       archivoLOG(escribe,"AGREGAMOS LOS AEROPUERTOS: ");
       while((bfRead = lectura.readLine())!=null){
            palabra=bfRead.split(",");
            nombre=palabra[0].trim();
            ciudad=palabra[1];
            numTelefono=palabra[2];
            Aeropuerto aero=new Aeropuerto(nombre,ciudad,numTelefono);         
           exito= aeropuertos.insertarVertice(aero);       
           if(exito){
               archivoLOG(escribe,"Se ha agregado el aeropuerto con nombre aeronautico: "+nombre+" , ciudad: "+ciudad+ " y numero de telefono"
                       + " "+numTelefono);
           }else{
               archivoLOG(escribe,"No se ha agregado aeropuerto con nombre aeronautico: "+nombre);
           }      
   }
       archivoLOG(escribe,"---------------------------------------------------------------");
   }
   
   public static void agregaClientes(BufferedWriter escribe, Diccionario clientes, HashMap hashCliente )
           throws FileNotFoundException, IOException{
       // Este metodo agrega los aeropuertos al fichero de texto, llamado log
       String tipo,num,nom,ape,fechaNct,dom,tel,direccion,palabra[],bfRead,ruta;
       ruta=System.getProperty("user.dir");
       direccion=ruta+"\\src\\cargaInicial\\Cliente.txt\\";    
       BufferedReader lectura=new BufferedReader(new FileReader(direccion));
       boolean exito;    
       archivoLOG(escribe,"AGREGAMOS LOS CLIENTES: ");
       while((bfRead = lectura.readLine())!=null){
           palabra=bfRead.split(",");
           tipo=palabra[0].trim();
           num=palabra[1].trim();
           nom=palabra[2];
           ape=palabra[3];
           fechaNct=palabra[4];
           dom=palabra[5];
           tel=palabra[6];       
           Cliente client=new Cliente(tipo,num,nom,ape,fechaNct,dom,tel);
           Cliente claveClient= new Cliente(tipo,num);//ponen claveCliente implements Comparable
           //Insertamos los datos del cliente en el Diccionario
           exito=clientes.insertar(claveClient,client );        
           if(exito){
             hashCliente.put(claveClient, new Lista());
             archivoLOG(escribe,"Se ha agregado el cliente con tipo: " +tipo+ " y numero: "+num+" de documento, nombre: "+nom+
                     " , apellido: "+ape+" ,fecha de nacimiento: "+fechaNct+ " , domicilio: "+dom+ " y numero de telefono: "+tel);
           }else{
               archivoLOG(escribe, "No se ha agregado el cliente con tipo: " +tipo + " y numero: "+num+" de documento");
           }
   }
    archivoLOG(escribe,"-------------------------------------------------------");   
   }
    
   public static void agregaVuelos(BufferedWriter escribe,Diccionario vuelos, grafoEtiquetado aeropuerto) 
           throws FileNotFoundException, IOException{
       // Este metodo agrega los vuelos y viajes al fichero de texto, llamado log
       String claveVuelo,origen,destino,horaSalida,horaLlegada,direccion,palabra[],bfRead,horaMinS[]
       ,horaMinL[],direccionAux,nextLine,ruta;
       Aeropuerto aeroOrigen,aeroDestino;
        archivoLOG(escribe,"AGREGAMOS LOS VUELOS Y LOS VIAJES: ");
       int minutos,cantAsientos; 
       ruta=System.getProperty("user.dir");
       direccionAux=ruta+"\\src\\cargaInicial\\Viaje.txt";
       direccion=ruta+"\\src\\cargaInicial\\Vuelo.txt";
       BufferedReader lectura=new BufferedReader(new FileReader(direccion));
       BufferedReader lecturaAux=new BufferedReader(new FileReader(direccionAux));
       boolean exitoAero,exito;
       while((bfRead = lectura.readLine())!=null && (nextLine=lecturaAux.readLine())!=null){
           palabra=bfRead.split(",");        
           claveVuelo=palabra[0];
           origen=palabra[1].trim();
           destino=palabra[2].trim();          
           horaSalida=palabra[3].trim();
           horaLlegada=palabra[4].trim(); 
           cantAsientos=Integer.parseInt(palabra[5].trim());
           horaMinS=horaSalida.split(":");
           horaMinL=horaLlegada.split(":");          
           minutos=pasaHoraMinAMin(horaMinS,horaMinL);       
           aeroOrigen=new Aeropuerto(origen);
           aeroDestino= new Aeropuerto(destino); 
          //Insertamos el arco entre el aeropuerto origen y destino
          exitoAero= aeropuerto.insertarArco(aeroOrigen, aeroDestino, minutos);
          if(exitoAero){
           Vuelo vuelo= new Vuelo(claveVuelo,origen,destino,horaSalida,horaLlegada,cantAsientos);   
              
           //Se agregan las fechas del vuelo
          agregaFechaViaje(escribe,vuelo,nextLine);          
          Vuelo codVuelo=new Vuelo(claveVuelo);
           exito=vuelos.insertar(codVuelo, vuelo);
           if(exito){              
               archivoLOG(escribe, "Se ha agregado el vuelo con clave: "+claveVuelo+" , aeropuerto de origen: "+origen+
                       " y destino: "+destino+" , hora de salida: "+horaSalida+ " y llegada: "+horaLlegada+
                       " y cantidad de asientos: "+cantAsientos);
           }else{      
              archivoLOG(escribe, "No se ha agregado el vuelo con clave "+claveVuelo);    
           }   
       }else{
             archivoLOG(escribe,"No existe el aeropuerto por ende no hay vuelo");
          }
       }
       archivoLOG(escribe,"---------------------------------------------------------------------------------");
   
       
   }
   public static void agregaFechaViaje(BufferedWriter escribe,Vuelo vuelo,String nextLine) 
       throws FileNotFoundException, IOException{
       String fecha[];
       int pos=0;
       Viaje viaje;
       Lista lsViaje;
       fecha=nextLine.split(",");
       lsViaje=(Lista) vuelo.getViajes();       
           while(pos<fecha.length){        
               archivoLOG(escribe,"Se ha agregado la fecha de viaje " +fecha[pos]+" para el vuelo: "+vuelo.getClave());
               viaje=new Viaje(fecha[pos].trim());
               lsViaje.insertar(viaje,lsViaje.longitud()+1);
               pos++;             
           }          
   }
   
   public static void agregaPasajes(BufferedWriter escribe,HashMap pasaje,Diccionario vuelo)
       throws FileNotFoundException, IOException{
       // Este metodo agrega los pasajes al fichero de texto, llamado log
       int numAsiento;
       Lista listaPasaje; 
       Viaje buscaFecha;
       String fecha,estado,direccion,bfRead,palabra[],tipo,num,codVuelo,ruta;
       estado="pendiente";
       ruta=System.getProperty("user.dir");
       direccion=ruta+"\\src\\cargaInicial\\Pasaje.txt";
       BufferedReader lectura=new BufferedReader(new FileReader(direccion));
       archivoLOG(escribe,"AGREGAMOS LOS PASAJES: ");
       while((bfRead = lectura.readLine())!=null){
            palabra=bfRead.split(",");
            tipo=palabra[0].trim();
            num=palabra[1].trim();
            codVuelo=palabra[2].trim();
            fecha=palabra[3].trim();
            numAsiento=Integer.parseInt(palabra[4].trim());        
            Cliente codCliente=new Cliente(tipo,num);  
            Vuelo  claveVuelo =new Vuelo(codVuelo);
            Vuelo vueloAux= (Vuelo)vuelo.obtenerInformacion(claveVuelo);
           if(vueloAux!=null){
               //Buscamos el Viaje de esa fecha
                buscaFecha=vueloAux.buscaFechaViaje(fecha);
                //Aumentamos la cantidad de asientos vendidios de esa fecha de viaje
               vueloAux.vendXFechaVuelo(buscaFecha);
               //Obtenemos la lista de numero de asiento con la fecha de ese Viaje
               Lista lsNumAsiento= (Lista) buscaFecha.getLsNumAsiento();     
               lsNumAsiento.insertar(numAsiento, lsNumAsiento.longitud()+1);
            //Creamos el pasaje
            Pasaje p=new Pasaje(vueloAux,fecha,numAsiento,estado);        
            listaPasaje=(Lista) pasaje.get(codCliente);           
            //Agregamos el pasaje a la listaPasaje
            listaPasaje.insertar(p, listaPasaje.longitud()+1);       
            archivoLOG(escribe, "Se ha agregado el pasaje con vuelo:"+vueloAux+" , fecha: "+fecha+
                    ", numero de asiento: "+numAsiento+ " y estado: "+estado);      
       }else{
               archivoLOG(escribe, " No se ha agregado el pasaje  del vuelo:  "+codVuelo+ " y fecha: "+fecha);
           }
   }    
   }
   public static int pasaHoraMinAMin(String horaSalida[],String horaLlegada[]){  
       // Este metodo pasamos hora y minutos a minutos
        int salHora,salMin,llegHora,llegMin,minutos,horas,min,hora1;  
        //Pasamos la hora y minutos de salida y llegada a int
        salHora=Integer.parseInt(horaSalida[0]);
        salMin=Integer.parseInt(horaSalida[1]);   
        llegHora=Integer.parseInt(horaLlegada[0]);
        llegMin=Integer.parseInt(horaLlegada[1]);       
        hora1=llegHora-salHora;
        horas=(24+(hora1))%24;       
          if(salMin==0 && llegMin!=0){        
            min=Math.abs(llegMin-60);
          }else{            
             if(salMin!=0 && llegMin==0){
                min=Math.abs(60-salMin);
            }else{
                  min= Math.abs(llegMin-salMin);
        }
        }  
        minutos=(horas*60)+min;       
        return minutos;      
   }
   
   public static void archivoLOG(BufferedWriter texto,String palabra ) throws IOException{
          // Este metodo almacena el texto en el archivo de texto
         SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/YYYY HH:mm:ss ");
         String formatoFecha=sdf.format(new Date()),datoFecha;
         datoFecha="[" + formatoFecha + "]";
         texto.write(datoFecha);
         texto.newLine();
         texto.write(palabra);
         texto.newLine();
         texto.flush();      
   }  
}
