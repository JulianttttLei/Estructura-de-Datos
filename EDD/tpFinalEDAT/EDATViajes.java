/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpFinalEDAT2020;

import Estructuras.Diccionario;
import Estructuras.HeapMaximo;
import Estructuras.Lista;


import Estructuras.grafoEtiquetado;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;


/**
 *
 * @author agust
 */
public class EDATViajes {
    
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String NEGRO = "\u001B[30m";
    public static final String RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";    
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String ruta,rutaAux;
        boolean continua = true;
        rutaAux=System.getProperty("user.dir");
        ruta = rutaAux+"\\src\\cargaInicial\\log.txt";
        BufferedWriter escribe = new BufferedWriter(new FileWriter(ruta));
        int opcion;      
        //Creo el grafo etiquetado de los aeropuertos
        grafoEtiquetado aeropuertos = new grafoEtiquetado();
        //Creo la Tabla de Busqueda o Diccionario de los clientes
        Diccionario clientes = new Diccionario();        
        //Creo la Tabla de Busqueda o Diccionario de los vuelos
        Diccionario vuelos = new Diccionario();
        //Creamos un mapeo de uno a muchos
        HashMap<Cliente, Lista> mapeo = new HashMap<Cliente, Lista>();
       agregarCargasIni(aeropuertos,mapeo,vuelos,clientes,escribe);
        while (continua) {
            menuOpciones();
            System.out.println("Elija una opcion: ");
            opcion = TecladoIn.readInt();
            switch (opcion) {
                //ABM (Altas-Bajas-Modificaciones) de aeropuertos
                case 1:
                    ABM_Aeropuertos(aeropuertos, escribe);
                    break;
                case 2:
                    //ABM (Altas-Bajas-Modificaciones) de clientes
                    ABM_Clientes(clientes, mapeo, escribe);
                    break;
                case 3:
                    //ABM (Altas-Bajas-Modificaciones) de vuelos
                    ABM_Vuelos(vuelos, escribe, aeropuertos, mapeo);
                    break;
                case 4:
                    //ABM(Altas-Bajas-Modificaciones) de pasajes
                    ABM_Pasajes(vuelos, escribe, mapeo, clientes);
                    break;
                case 5:
                    //Consultas sobre clientes
                    consultasSobreClientes(clientes,mapeo,aeropuertos);
                   break;
                case 6:
                    //Consultas sobre Vuelos
                    consultasSobreVuelos(vuelos);
                    break;
                case 7:
                    //Consultas sobre tiempos de viaje
                    consultasSobreTiemposViaje(aeropuertos);
                    break;
                case 8:
                    // Promocion a clientes fieles
                    promocionClientesFieles(clientes,mapeo);
                 break;   
                case 9:     //Mostrar sistema
                 muestraSistema(aeropuertos,mapeo,vuelos,clientes);
                 break;           
                     default:
                    continua = false;
                    break;
            }
            
        }
        System.out.println("\n\n");   
        estadoSistema(aeropuertos,mapeo,vuelos,clientes,escribe);
        System.out.println("CONSULTA Y VENTA DE VIAJES AEREOS FINALIZADA");
    }
    public static void agregarCargasIni(grafoEtiquetado a,HashMap mapeo,Diccionario v,Diccionario c,BufferedWriter escribe) throws IOException{
         //Cargamos las cargas iniciales con su respectiva clase   
        agregaCargasIniciales.agregaAeropuertos(escribe, a);
        
        agregaCargasIniciales.agregaClientes (escribe, c, mapeo);
        
        agregaCargasIniciales.agregaVuelos(escribe, v, a);
       
        agregaCargasIniciales.agregaPasajes(escribe, mapeo, v);  
    
       
    }
    
    public static void muestraSistema(grafoEtiquetado a,HashMap hashPasaje,Diccionario vuelo,Diccionario cliente){
        System.out.println("AEROPUERTOS: ");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(a.toString());
        System.out.println("--------------------------------------------------------------------------"+"\n");
       
        System.out.println("VUELOS: ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(vuelo.toString());
        System.out.println("------------------------------------------------------------------------------"+"\n");
        
        System.out.println("CLIENTES: ");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(cliente.toString());
        System.out.println("----------------------------------------------------------------------------------"+"\n");
        
        System.out.println("PASAJES: ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(hashPasaje.toString());
        System.out.println("--------------------------------------------------------------------------------------"+"\n");
            
        
    }
    public static void estadoSistema(grafoEtiquetado a, HashMap hashPasaje,Diccionario vuelo,Diccionario cliente,BufferedWriter escribe) throws IOException{
        int i=1,longCliente,longAero,longVuelo,t,longHash,p,longViaje;
        Lista lsPasaje,lsCliente,lsVuelo,lsAeros,lsViaje;
      agregaCargasIniciales.archivoLOG(escribe,"ESTADO DEL SISTEMA: ");
      agregaCargasIniciales.archivoLOG(escribe,"---------------------------------------------------------------------------------------------------"+"\n");
      agregaCargasIniciales.archivoLOG(escribe,"                                   AEROPUERTOS");    
        lsAeros=a.listarEnProfundidad();
        longAero=lsAeros.longitud();
        while(i<=longAero){
            Aeropuerto aero=(Aeropuerto) lsAeros.recuperar(i);   
          agregaCargasIniciales.archivoLOG(escribe,"Aeropuerto: "+aero.toString());
          i++;
        }
        agregaCargasIniciales.archivoLOG(escribe,"--------------------------------------------------------------------------------------------"+"\n");
        agregaCargasIniciales.archivoLOG(escribe,"                                    CLIENTES");
        i=1;
        lsCliente=cliente.listarDatos();
        longCliente=lsCliente.longitud();
        while(i<=longCliente){
            Cliente c=(Cliente) lsCliente.recuperar(i);
            agregaCargasIniciales.archivoLOG(escribe,"Cliente: "+c.muestraDatosCliente());
            i++;
        }
        agregaCargasIniciales.archivoLOG(escribe,"---------------------------------------------------------------------------------------"+"\n");
        agregaCargasIniciales.archivoLOG(escribe,"                                     VUELOS");
        i=1;
        lsVuelo=vuelo.listarDatos();
        longVuelo=lsVuelo.longitud();
        while(i<=longVuelo){
            Vuelo v=(Vuelo) lsVuelo.recuperar(i);
            lsViaje=v.getViajes();
            p=1;
            longViaje=lsViaje.longitud();
            agregaCargasIniciales.archivoLOG(escribe,"                                 Viajes");
            while(p<=longViaje){
                Viaje viaje=(Viaje)lsViaje.recuperar(p);
                agregaCargasIniciales.archivoLOG(escribe,"fecha:  "+viaje.getFecha()+ " y asientos vendidos: "+viaje.getAsientoVendido());
                p++;
            }
            agregaCargasIniciales.archivoLOG(escribe,"Vuelo: "+v.muestraDatosVuelo());
            i++;
        }
       agregaCargasIniciales.archivoLOG(escribe,"------------------------------------------------------------------------------------------"+"\n");
        agregaCargasIniciales.archivoLOG(escribe,"                                    PASAJES");
        Set claves = hashPasaje.keySet(); 
        Object codClienteArr[];
        codClienteArr = claves.toArray();//Paso las claves a un arreglo 
        longHash = codClienteArr.length;//Almaceno el tamaño de codClienteArr
        for (t = 0; t < longHash; t++) {
            Cliente claveCliente = (Cliente) codClienteArr[i];         
            lsPasaje =(Lista) hashPasaje.get(claveCliente);//lista de pasajes del cliente
            p=1;
            
            if(!lsPasaje.esVacia()){
                while(p<=lsPasaje.longitud()){
                    Pasaje pasaje=(Pasaje)lsPasaje.recuperar(p);
            agregaCargasIniciales.archivoLOG(escribe,"El cliente con tipo: "+claveCliente.getTipo()+ " y numero: "+claveCliente.getNum()+" de documento"
                    + " compro el pasaje: "+"\n"+" con vuelo = "+pasaje.getVuelo().muestraDatosVuelo()+" , fecha: "+pasaje.getFecha()+ " , estado: "+pasaje.getEstado()
            +" y numero de asiento: "+pasaje.getNumAsiento());
            p++;
                }
        }else{
                agregaCargasIniciales.archivoLOG(escribe,"El cliente con tipo: "+claveCliente.getTipo()+ " y numero: "+claveCliente.getNum()+" de documento"
                    + " no compro ningun pasaje");
            }
        }
        agregaCargasIniciales.archivoLOG(escribe,"--------------------------------------------------------------------------------------------------------------");
        escribe.flush();
    }
    public static void muestraEstructuras(grafoEtiquetado a, HashMap hashPasaje,Diccionario vuelo,Diccionario cliente){
       
               
    }
    public static void promocionClientesFieles(Diccionario cliente,HashMap hashPasaje){
      // Este metodo muestra un listado de clientes que han comprado más pasajes, para ofrecerles
      // un descuento especial
      
        System.out.println("Para el mes de  "+mesActual()+ " tenemos el siguiente listado de "
         + "clientes que han comprado mas pasajes, para ofrecerles un descuento especial: ");
        HeapMaximo hM=new HeapMaximo(),cloneHM;
        Set claves = hashPasaje.keySet(); 
        promoCliente pC,elemento;
        int i, longitud,numPasajes;
        Lista lsPasaje;
        Object codClienteArr[];
        codClienteArr = claves.toArray();//Paso las claves a un arreglo 
        longitud = codClienteArr.length;//Almaceno el tamaño de codClienteArr
        for (i = 0; i < longitud; i++) {
            Cliente claveCliente = (Cliente) codClienteArr[i];         
            lsPasaje =(Lista) hashPasaje.get(claveCliente);//lista de pasajes del cliente
            numPasajes=lsPasaje.longitud();//cantidad de pasajes del cliente     
            pC=new promoCliente(claveCliente,numPasajes);
            hM.insertar(pC);        
        }
        cloneHM=hM.clone();
        while(!cloneHM.esVacio()){
            elemento=(promoCliente)cloneHM.recuperarCima();
            System.out.println("El cliente: "+elemento.getCliente().toString()+ 
                    "compro: "+elemento.getPasajesComprados()+ " pasajes");
         cloneHM.eliminarCima();
        }
    }
      
    private static String mesActual(){
     // Este metodo retorna el mes actual
     Month mes = LocalDate.now().getMonth();
     String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
     return nombre;
      }
    
    public static void consultasSobreTiemposViaje(grafoEtiquetado aeropuerto){
      // Este metodo realiza consultas sobre tiempos de viaje
     int opcion;
     menuConsultasTiemposViaje();
     System.out.println("Elija una opcion: ");
     opcion=TecladoIn.readInt();      
     switch(opcion){
         case 1: posibleVueloEnXVuelos(aeropuerto);
             break;
         case 2: obtCaminoMenorTiempoVuelo(aeropuerto);
             break;
         case 3: obtCaminoMinCantAeros(aeropuerto);
             break;
         case 4: obtCaminoMasRapido(aeropuerto);
             break;
         default:System.out.println("No se ha realizo ninguna consulta sobre tiempos de viaje");
             break;
     }
    }
    
    public static void obtCaminoMasRapido(grafoEtiquetado aeropuerto){
      // Este metodo muestra el camino que llegue mas rápido del aeropuerto A a B y
      // que pase por el aeropuerto C
     Aeropuerto a,b,aero[],c;
     String nombre;
     aero=dosAeropuertos(aeropuerto); 
     a=aero[0];
     b=aero[1];
     System.out.println("Ingrese el nombre del aeropuerto C");
        nombre=TecladoIn.readLine();
        c=new Aeropuerto(nombre);
         if(aeropuerto.existeVertice(a) && aeropuerto.existeVertice(b) && aeropuerto.existeVertice(c)){
        Lista lsCamMasRapido=aeropuerto.caminoMasRapido(a, b, c);
        if(!lsCamMasRapido.esVacia()){
            System.out.println("El camino que llega mas rapido de "+a.getNombre()+ " a "+b.getNombre()+
                    " y pasa por el aeropuerto "+c.getNombre()+ " es: ");
            System.out.println(lsCamMasRapido.toString());
        }else{
             System.out.println("No hay camino de " +a.getNombre()+ " a "+b.getNombre()+ " y que pase por " +c.getNombre());
        }
    }else{
             System.out.println("Los aeropuertos no existen");
         }
    }
    
    public static void obtCaminoMinCantAeros(grafoEtiquetado aeropuerto){
        // Este metodo muestra el camino que llega del aeropuerto  A al B pasando por la mínima cantidad de aeropuertos
        Aeropuerto a,b,aero[];
        aero=dosAeropuertos(aeropuerto); 
        a=aero[0];
        b=aero[1];
         if(aeropuerto.existeVertice(a) && aeropuerto.existeVertice(b)){
        Lista lsCamMinCantA=aeropuerto.caminoMinCantAero(a, b);
            
         if(!lsCamMinCantA.esVacia()){
             System.out.println("El camino que llega de "+ a.getNombre()+ " a " +b.getNombre()
                     + " pasando por la minima cantidad de aeropuertos es:  ");      
                System.out.println(lsCamMinCantA.toString());         
         }else{
              System.out.println("No hay camino de "+a.getNombre()+ " a "+b.getNombre());
         }       
    }else{
            System.out.println("Los aeropuertos no existen");  
         }
    }
   
   public static void  obtCaminoMenorTiempoVuelo(grafoEtiquetado aeropuerto){
       // Este metodo muestra el camino que llega del aeropuerto A al B de menor tiempo de vuelo
       Aeropuerto a,b,aero[];
       aero=dosAeropuertos(aeropuerto); 
       a=aero[0];
       b=aero[1];
       if(aeropuerto.existeVertice(a) && aeropuerto.existeVertice(b)){
       Lista lsCamMenorTiempo=aeropuerto.camMenorTiempoVuelo(a, b);     
       if(!lsCamMenorTiempo.esVacia()){
       System.out.println("El camino que llega de " + a.getNombre() + " a " + b.getNombre() + " de menor tiempo es: ");
       System.out.println(lsCamMenorTiempo.toString());  
       }else{
           System.out.println("No hay camino de "+a.getNombre()+ " a "+b.getNombre());
       }
   }else{
           System.out.println("Los aeropuertos no existen"); 
       }
   }
    public static void posibleVueloEnXVuelos(grafoEtiquetado aeropuerto){
       // Este metodo muestra si es posible que el cliente parta por el aeropuerto A y llegue al B
       // como máximo X vuelos
       Aeropuerto a,b,aero[];
       int cantVertice;
       Lista lsCamMaxXVuelos;
       aero=dosAeropuertos(aeropuerto); 
       a=aero[0];
       b=aero[1];
       if(aeropuerto.existeVertice(a) && aeropuerto.existeVertice(b)){
       System.out.println("Ingrese la cantidad de vuelos que debe hacer como maximo el aeropuerto: "+a.getNombre()+" y : "+b.getNombre());
       cantVertice=TecladoIn.readInt();
       lsCamMaxXVuelos=aeropuerto.caminoComoMaxXVertice(a,b,cantVertice);    
       if(!lsCamMaxXVuelos.esVacia() && lsCamMaxXVuelos.longitud()-1<=cantVertice){
             System.out.println("Es posible que parta de " + a.getNombre() + " y llegue a " + b.getNombre() 
                         + " en: " + (lsCamMaxXVuelos.longitud()-1) + "  vuelos");         
            
       }else{
            System.out.println("No es posible que parta de " + a.getNombre() + " y llegue a " + b.getNombre() );
       }
    }else{
           System.out.println("Los aeropuertos no existen");  
       }
    }
           
    
    public static void menuConsultasTiemposViaje(){
      //Este metodo muestra el menu de consultas sobre tiempos de viaje
        System.out.println("***************************************************************");
        System.out.println("*         Menu de Consultas sobre Tiempos de Viaje            *");
        System.out.println("***************************************************************" + "\n\n" ); 
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Dados dos aeropuertos 'A' y 'B', muestra si es posible que el cliente parta de 'A'  a"
                + " 'B' en como maximo X vuelos ");
        System.out.println("OPCION 2: Obtenga el camino que llegue del aeropuerto 'A' a 'B' de menor tiempo de vuelo" );
        System.out.println("OPCION 3: Obtenga el camino que llegue del aeropuerto 'A' a 'B' pasando por la minima cantidad de aeropuertos ");
        System.out.println("OPCION 4: Dados 3 aeropuertos 'A','B'y 'C', obtenga el camino que llegue mas "
                + "rapido de 'A' a 'B' y que pase por el aeropuerto 'C'");
        System.out.println("OPCION DISTINTA DE 1, 2, 3 y 4: Salir");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        
    }
    public static Aeropuerto[] dosAeropuertos(grafoEtiquetado a){
     // Este metodo pide 2 nombres de aeropuertos
     String aero1,aero2;
     Aeropuerto aero[]=new Aeropuerto[2],origen,destino;   
     System.out.println("Ingrese el nombre del aeropuerto A");
     aero1=TecladoIn.readLine();
     System.out.println("Ingrese el nombre del aeropuerto B");
     aero2=TecladoIn.readLine();
     origen=new Aeropuerto(aero1);
     destino=new Aeropuerto(aero2);
     aero[0]=origen;
     aero[1]=destino;   
       return aero;
    }
    
    public static void consultasSobreVuelos(Diccionario vuelo){
     // Este metodo realiza consultas sobre vuelos
     int opcion;
     menuConsultasVuelos();
     opcion=TecladoIn.readInt();
     switch(opcion){
         case 1:
             muestraInfoCodVueloYFecha(vuelo);
             break;
         case 2:muestraRangoDe2CodVuelo(vuelo);
             break;
         default:
             System.out.println("No se realizo ninguna consulta sobre vuelos");
             break;
     }
    }
    
    public static void muestraRangoDe2CodVuelo(Diccionario vuelo){
        // Este metodo muestra todos los códigos existentes que están en un rango entre el menor
        // y el mayor de ambos códigos
        String codVuelo1,codVuelo2;
        int pos=1;
        Vuelo claveVuelo1,claveVuelo2,claveVuelo;
        System.out.println("Ingrese el primer codigo de vuelo");
        codVuelo1=TecladoIn.readLine();
        System.out.println("Ingrese el segundo codigo de vuelo");
        codVuelo2=TecladoIn.readLine();
        claveVuelo1=new Vuelo(codVuelo1);
        claveVuelo2=new Vuelo(codVuelo2);
        if(vuelo.existeClave(claveVuelo1) && vuelo.existeClave(claveVuelo2)){
              Lista lsCodVuelos=vuelo.listarRango(claveVuelo1,claveVuelo2);
              System.out.println("Los codigos de vuelo que se encuentran en el rango entre el menor y el mayor son: "+"\n");
               while(pos<=lsCodVuelos.longitud()){
                   claveVuelo=(Vuelo)lsCodVuelos.recuperar(pos);
                   System.out.println(claveVuelo.toString());      
                   pos++;
               }                                     
        }else{
            System.out.println("Los codigos de vuelos no existen");
        }   
    }
        
    public static void muestraInfoCodVueloYFecha(Diccionario vuelo){
        //Este metodo muestra toda la informacion relacionada con un determinado vuelo
        String codVuelo,fecha;
        Vuelo claveVuelo,datosVuelo;
        Viaje viaje;
        System.out.println("Ingrese el codigo de vuelo");
        codVuelo=TecladoIn.readLine();
        claveVuelo=new Vuelo(codVuelo);
        if(vuelo.existeClave(claveVuelo)){
            datosVuelo=(Vuelo)vuelo.obtenerInformacion(claveVuelo);
            System.out.println("Te mostraremos las fechas de los viajes del vuelo "+codVuelo);
            System.out.println(datosVuelo.getViajes());        
            System.out.println("Ingrese alguna fecha de las que vio anteriormente");
            fecha=TecladoIn.readLine();
            viaje=datosVuelo.buscaFechaViaje(fecha);
           
            if(viaje!=null){
                System.out.println("INFORMACION DEL VUELO: "+"\n"
                        +datosVuelo.toString());
                System.out.println("INFORMACION DEL VIAJE: "+"\n"
                        +viaje.getFecha() + " y cantidad de asientos "
                                + "vendidos: " +viaje.getAsientoVendido());
            }else{
                System.out.println("La fecha ingresada no existe en la lista de viajes del vuelo");
            }       
        }else{
            System.out.println("El codigo de vuelo: "+codVuelo+ " no existe");
        }       
    }
    
    public static void menuConsultasVuelos(){
        //Este metodo muestra el menu de consultas sobre vuelos
        System.out.println("***************************************************************");
        System.out.println("*            Menu de Consultas sobre Vuelos                   *");
        System.out.println("***************************************************************" + "\n\n" ); 
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Ver toda la informacion teniendo el codigo de vuelo y una fecha");
        System.out.println("OPCION 2: Ver todos los codigos existentes que estan en un rango entre el menor y mayor de ambos códigos"
                + "(incluyendo ambos extremos), dado dos códigos de vuelo");
        System.out.println("OPCION DISTINTA DE 1 y 2: Salir");
        System.out.println("------------------------------------------------------------------------------------------------------------------");    
    }
    
    public static void consultasSobreClientes(Diccionario cliente,HashMap hashPasaje,grafoEtiquetado a){
        //Este metodo  realiza consulta sobre clientes
        Cliente datosCliente,codCliente;
        String tipo,numero;
        int opcion;
        Lista lsPasajesPend;
        System.out.println("Ingrese el tipo de documento del cliente");
        tipo=TecladoIn.readLine();
        System.out.println("Ingrese el numero de documento del cliente");
        numero=TecladoIn.readLine();
        codCliente=new Cliente(tipo,numero);
        if(cliente.existeClave(codCliente)){
            datosCliente=(Cliente) cliente.obtenerInformacion(codCliente);
            menuConsultasClientes();
            System.out.println("Elija una opcion: ");
            opcion=TecladoIn.readInt();
            switch(opcion){     
                case  1: 
                    System.out.println();
                         System.out.println("INFORMACION DEL CLIENTE: "+"\n"
                                 +datosCliente.toString());
                         lsPasajesPend=lsPasajePend(codCliente,hashPasaje);
                         if(!lsPasajesPend.esVacia()){
                         System.out.println("Listado de los pasajes comprados pendientes de volar: ");
                         System.out.println(lsPasajesPend.toString());     
                         }else{
                             System.out.println("No ha comprado ningun pasaje");
                         }
                break;
                case 2:
                    ciudadVisitada(codCliente,hashPasaje,a);             
                    break;
                default:
                     System.out.println("No realizo ninguna consulta sobre el cliente");
                break;   
            }          
        }else{
            System.out.println("El cliente con tipo "+tipo + " y numero "+numero + " de documento no existe"+"\n");
        }   
    }
   
   public static void ciudadVisitada(Cliente codCliente,HashMap hashPasaje,grafoEtiquetado a){
        //Este metodo muestra las ciudades que ha visitado un cliente, según su historial de vuelos
        Lista lsPasajes=(Lista) hashPasaje.get(codCliente),lsCiudad=new Lista();
        int pos=1,p1 ,p2 ; 
        String ciudad1,ciudad2,ciudad[] ;
        while(pos<=lsPasajes.longitud() ){
           Pasaje pasaje=(Pasaje) lsPasajes.recuperar(pos);
            Vuelo vuelo=pasaje.getVuelo();
            //Tambien tomamos en cuenta el aeropuerto de origen ya que no sabemos de que ciudad es el cliente
            ciudad1=vuelo.getAeroOrigen();
            ciudad2=vuelo.getAeroDestino();
             ciudad=buscarCiudad(a,ciudad1,ciudad2);
            p1=lsCiudad.localizar(ciudad[0]);
            p2=lsCiudad.localizar(ciudad[1]);  
            if(p1==-1){
                lsCiudad.insertar(ciudad[0],lsCiudad.longitud()+1);
            }
            if(p2==-1){
                lsCiudad.insertar(ciudad[1], lsCiudad.longitud()+1);
            }          
             pos++;          
        }
        if(!lsCiudad.esVacia()){
        System.out.println("Las ciudades que visito el cliente con tipo: "+codCliente.getTipo() +  
                " y numero "+codCliente.getNum()+ " de documento son:");
        System.out.println(lsCiudad.toString());
   }else{
            System.out.println("El cliente con tipo: "+codCliente.getTipo() +  
                " y numero "+codCliente.getNum()+ " de documento no compro nimgun pasaje"); 
        }
   }
   
   public static String[] buscarCiudad(grafoEtiquetado a,String nombre1,String nombre2){
      //Este metodo segun el nombre del aeropuerto, busca el nombre de la ciudad
      Aeropuerto c1,c2;
      String ciudad[]=new String[2];
      c1=new Aeropuerto(nombre1);
      c2= new Aeropuerto(nombre2);
        if(a.existeVertice(c1) && a.existeVertice(c2)){
            ciudad[0]=((Aeropuerto)a.buscaElemento(c1)).getCiudad();
            ciudad[1]= ((Aeropuerto)a.buscaElemento(c2)).getCiudad();
        }else{
             System.out.println("Las ciudades no existen");
         }       
         return ciudad;
   }
   
    public static Lista lsPasajePend(Cliente codCliente,HashMap hashPasaje){
        // Este metodo crea una lista de pasajes con estado pendiente
        Lista lsPasajes,lsPasajesPend=new Lista();  
        lsPasajes=(Lista) hashPasaje.get(codCliente);
        int pos=1; 
        while(pos<=lsPasajes.longitud() ){
            Pasaje pasaje=(Pasaje) lsPasajes.recuperar(pos);
            boolean pasajePend= pasaje.getEstado().equals("pendiente");
            if(pasajePend){
             lsPasajesPend.insertar(pasaje,lsPasajesPend.longitud()+1);
            }
            pos++;
        }
       return lsPasajesPend;     
    }
    
    public static void menuConsultasClientes(){
        // Este metodo muesta el menu de consultas sobre clientes  
        System.out.println("***************************************************************");
        System.out.println("*            Menu de Consultas sobre Clientes                 *");
        System.out.println("***************************************************************" + "\n\n" ); 
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Ver los datos del cliente y el listado de los pasajes comprados pendientes de volar");
        System.out.println("OPCION 2: Ver las ciudades que ha visitado");
        System.out.println("OPCION DISTINTA DE 1 y 2: Salir");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }   
 
    public static void menuOpciones() {
        // Este metodo muestra todas las opciones disponibles,  para desarrollar algun viaje
        System.out.println(ANSI_YELLOW_BACKGROUND + "*************************************************************");
        System.out.println(NEGRO +                  "*                     Menu                                  *");
        System.out.println(ANSI_YELLOW_BACKGROUND + "*************************************************************" + "\n\n" + RESET);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: ABM (Altas-Bajas-Modificaciones) de aeropuertos");
        System.out.println("OPCION 2: ABM (Altas-Bajas-Modificaciones) de clientes");
        System.out.println("OPCION 3: ABM (Altas-Bajas-Modificaciones) de vuelos");
        System.out.println("OPCION 4: ABM (Altas-Bajas-Modificaciones) de pasajes");
        System.out.println("OPCION 5: Consultas sobre clientes");
        System.out.println("OPCION 6: Consultas sobre vuelos");
        System.out.println("OPCION 7: Consultas sobre tiempos de viaje");
        System.out.println("OPCION 8: Muestra una tabla de mayor a menor de los clientes fieles(para ofrecerles un descuento especial)");
        System.out.println("OPCION 9: Muestra todas las estructuras utilizadas");
        System.out.println("OPCION DISINTA DE 1, 2, 3, 4, 5, 6, 7 , 8 y 9, usted NO desea continuar");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }
    
    public static void ABM_Aeropuertos(grafoEtiquetado aeropuerto, BufferedWriter escribe) throws IOException {
        // Este metodo nos permite elegir una opcion para poder realizar la tarea que cumpla dicha opcion para el caso de AEROPUERTOS
        agregaCargasIniciales.archivoLOG(escribe, "AEROPUERTOS: ");
        int opcion;
        boolean continua = true, exito;
        while (continua) {
            menuAeropuerto();
            System.out.println("Elija una opcion: ");
            opcion = TecladoIn.readLineInt();
            switch (opcion) {
                case 1:
                    exito = insertaAeropuerto(aeropuerto, escribe);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + " Se ha agregado con exito el aeropuerto " + RESET+"\n");
                    } else {
                        System.out.println(ROJO + " No se ha agregado el aeropuerto " + RESET+"\n");
                    }
                    break;
                case 2:
                    exito = cambiaTelAeropuerto(aeropuerto, escribe);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Los datos del aeropuerto se han cambiado " + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "Los datos del aeropuerto no se han cambiado " + RESET+"\n");
                    }
                    break;
                case 3:
                    exito = eliminaAeropuerto(aeropuerto, escribe);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Se ha eliminado con exito el aeropuerto " + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se ha eliminado el aeropuerto " + RESET+"\n");
                    }
                    break;
                default:
                    continua = false;
                    break;
            }
        }
    }

    public static boolean insertaAeropuerto(grafoEtiquetado aeropuerto, BufferedWriter escribe) throws IOException {
        // Este metodo inserta el aeropuerto si es que no existe en el grafoEtiquetado. Si esto se verifica devuelve true,
        // en caso contrario, devuelve false
        boolean inserta = false;
        String nombreAero, ciudad, telefono;
        Aeropuerto a, aero;
        System.out.println("Ingrese el nombre aeronáutico: (FORMADO POR 3 LETRAS)");
        nombreAero = TecladoIn.readLine();
        if (nombreAero(nombreAero)) {
            a = new Aeropuerto(nombreAero);
            if (!aeropuerto.existeVertice(a)) {
                System.out.println("Ingrese la ciudad del aeropuerto ");
                ciudad = TecladoIn.readLine();
                System.out.println("Ingrese el numero de teléfono del aeropuerto");
                telefono = TecladoIn.readLine();
                aero = new Aeropuerto(nombreAero, ciudad, telefono);
                aeropuerto.insertarVertice(aero);
                inserta = true;
                agregaCargasIniciales.archivoLOG(escribe, " Se ha agregado el aeropuerto con nombre aeronáutico: " + nombreAero
                +" , ciudad: "+ciudad+ " y numero de telefono: "+telefono);
            } else {
                System.out.println(" Nombre Aeronautico REPETIDO, INGRESO NO VALIDO" + "\n");
            }
        } else {
            System.out.println(" Nombre Aeronautico NO VALIDO" + "\n");
        }
        return inserta;
    }

    private static boolean nombreAero(String nombre) {
        // Este metodo verifica si el nombre aeronautico del aeropuerto esta compuesto por 3 letras 
        // y si cada letra esta en mayuscula. Si esto se verifica devuelve true sino en caso contrario,
        // devuelve false
        boolean esLetra = true;
        char c;
        int pos = 0;
        if (nombre.length() == 3) {
            while (pos < nombre.length() && esLetra) {
                c = nombre.charAt(pos);
                pos++;
                esLetra = (c >= 'A' && c <= 'Z');
            }
        } else {
            esLetra = false;
        }
        return esLetra;
    }

    public static boolean cambiaTelAeropuerto(grafoEtiquetado aeropuerto, BufferedWriter escribe) throws IOException {
        // Este metodo cambia el numero de telefono del aeropuerto en el caso que el aeropuerto exista en el grafoEtiquetado.
        // Si esto se verifica devuelve true, en caso contrario, devuelve false
        String telefono, nombreAero,telAnt;
        boolean cambia = false;
        Aeropuerto aero,a;
        System.out.println("Ingrese el nombre aeronautico: (FORMADO POR 3 LETRAS)");
        nombreAero = TecladoIn.readLine();
        aero = new Aeropuerto(nombreAero);
        if (nombreAero(nombreAero)) {
            if (aeropuerto.existeVertice(aero)) {
                a=(Aeropuerto)aeropuerto.buscaElemento(aero);
                telAnt=a.getNumTelefono();
                System.out.println("Ingrese el nuevo numero de telefono del cliente");
                telefono = TecladoIn.readLine();
                a.setNumTelefono(telefono);
                cambia = true;
                agregaCargasIniciales.archivoLOG(escribe, " Se ha cambiado el numero de telefono: " + telAnt + " por: " +telefono
                        + " del aeropuerto: " + nombreAero);
            } else {
                System.out.println("El nombre aeronautico: " + nombreAero + " no existe"+"\n");
            }
        } else {
            System.out.println("Nombre Aeronautico NO VALIDO" + "\n");
        }
        return cambia;
    }

    public static boolean eliminaAeropuerto(grafoEtiquetado aeropuerto, BufferedWriter escribe) throws IOException {
        // Este metodo elimina un aeropuerto que exista en el grafoEtiquetado. Si esto se verifica devuelve true, en caso 
        // contrario, devuelve false
        String nombreAero;
        boolean eliminado = false;
        Aeropuerto aero,a;
        System.out.println("Ingrese el nombre aeronautico: (FORMADO POR 3 LETRAS)");
        nombreAero = TecladoIn.readLine();
        aero = new Aeropuerto(nombreAero);
        if (aeropuerto.existeVertice(aero)) {
            a=(Aeropuerto)aeropuerto.buscaElemento(aero);
            aeropuerto.eliminarVertice(a);
            eliminado = true;
            agregaCargasIniciales.archivoLOG(escribe, "Se ha eliminado el aeropuerto con nombre: " + nombreAero);
        } else {
            System.out.println("El nombre aeronautico: " + nombreAero + " no existe");
        }
        return eliminado;
    }

    public static void menuAeropuerto() {
        // Este metodo muestra el menu del aeropuerto
        System.out.println(ANSI_CYAN_BACKGROUND + "**************************************************************");
        System.out.println(NEGRO +                "*                      Menu de Aeropuerto                    *");
        System.out.println(ANSI_CYAN_BACKGROUND + "**************************************************************" + "\n\n" + RESET);
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Insertar el aeropuerto");
        System.out.println("OPCION 2: Cambiar el numero de telefono del aeropuerto");
        System.out.println("OPCION 3: Eliminar el aeropuerto");
        System.out.println("OPCION DISTINTA de 1 y 2, usted NO desea continuar");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public static void ABM_Clientes(Diccionario cliente, HashMap hashCliente, BufferedWriter escribe) throws IOException {
        //Este metodo nos permite elegir una opcion para poder realizar la tarea que cumpla dicha opcion para el caso de CLIENTES
        agregaCargasIniciales.archivoLOG(escribe, " CLIENTES: ");
        int opcion;
        boolean continua = true, exito;
        while (continua) {
            menuCliente();
            System.out.println("Elija una opcion: ");
            opcion = TecladoIn.readLineInt();
            switch (opcion) {
                case 1:
                    exito = insertaCliente(cliente, hashCliente, escribe);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Se agrego con exito el cliente " + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se agrego el cliente" + RESET+"\n");
                    }
                    break;
                case 2:
                    exito = cambiaDatosCliente(cliente, escribe);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Los datos del cliente se han cambiado con exito" + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "Los datos del cliente no se han cambiado" + RESET+"\n");
                    }
                    break;
                case 3:
                    exito = eliminaCliente(cliente, hashCliente, escribe);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Se ha eliminado con exito el cliente" + RESET);
                    } else {
                        System.out.println(ROJO + "No se ha eliminado el cliente" + RESET);
                    }
                    break;
                default:
                    continua = false;
                    break;
            }
        }
    }

    public static boolean insertaCliente(Diccionario cliente, HashMap hashMap, BufferedWriter escribe) throws IOException {
        // Este metodo inserta un cliente, si es que no existe en el Diccionario y tambien lo agrega al hashMap con una lista vacia.
        // Si esto se verifica  devuelve, caso contra devuelve false
        String tipo, num, nombre, apellido, fechaNacimiento, telefono, domicilio;
        boolean inserta = false;
        System.out.println("Ingrese el tipo de documento del cliente");
        tipo = TecladoIn.readLine();
        System.out.println("Ingrese el numero de documento del cliente");
        num = TecladoIn.readLine();
        Cliente codCliente = new Cliente(tipo, num);
        if (!cliente.existeClave(codCliente)) {
            System.out.println("Ingrese el nombre del cliente");
            nombre = TecladoIn.readLine();
            System.out.println("Ingrese el apellido del cliente");
            apellido = TecladoIn.readLine();
            System.out.println("Ingrese la fecha de nacimiento del cliente (FORMATO: dd/mm/yyyy)");
            fechaNacimiento = TecladoIn.readLine();
            System.out.println("Ingrese el numero de telefono del cliente");
            telefono = TecladoIn.readLine();
            System.out.println("Ingrese el domicilio del cliente");
            domicilio = TecladoIn.readLine();
            Cliente c = new Cliente(tipo, num, nombre, apellido, fechaNacimiento, telefono, domicilio);
            cliente.insertar(codCliente, c);
            hashMap.put(c, new Lista());
            inserta = true;
            agregaCargasIniciales.archivoLOG(escribe, "Se ha agregado el cliente con tipo: "
                    + tipo + " y numero: " + num + " de documento , nombre: "+nombre+ " , apellido: "
            +apellido+" , fecha de nacimiento: "+fechaNacimiento + " , telefono: "+telefono + " y domicilio: "+domicilio);
        } else {
            System.out.println("El cliente con tipo: " + tipo +  " y  numero: " + num + " de documento repetido, INGRESO NO VALIDO"+"\n");
        }
        return inserta;
    }

    public static boolean cambiaDatosCliente(Diccionario cliente, BufferedWriter escribe) throws IOException {
        // Este metodo cambia los datos del cliente, en particular los que desea cambiar. Si esto se verfica devuelve true,
        // en caso contrario, devuelve false 
        String tipo, num;
        boolean cambia=true;
        int opcion;
        System.out.println("Ingrese el tipo de documento del cliente");
        tipo = TecladoIn.readLine();
        System.out.println("Ingrese el numero de documento del cliente");
        num = TecladoIn.readLine();
        Cliente codCliente = new Cliente(tipo, num);
        if (cliente.existeClave(codCliente)) {
           Cliente datosCliente = (Cliente) cliente.obtenerInformacion(codCliente);
           menuCambioDatosCliente();
           System.out.println("Elija una opcion");
           opcion=TecladoIn.readInt();
           cambia=true;
          switch(opcion){
              case 1:   cambiaNumTelCliente(datosCliente,escribe);
                break;
              case 2:cambiaDomCliente(datosCliente,escribe);     
                break;
              case 3: cambiaNumTelCliente(datosCliente,escribe);
                      cambiaDomCliente(datosCliente,escribe);     
                break;      
              default: cambia=false;               
                  break;
          } 
            //Del hashMap no cambiamos nada ya que solo estan formado por la clave unica del cliente que es tipo y numero de documento
        }else{  
            System.out.println("El cliente con tipo: " + tipo + " y numero " + num + "de documento  no existe"+"\n");     
        }
        return cambia;
    }
    
    public static void cambiaNumTelCliente(Cliente datosCliente,BufferedWriter escribe) throws IOException{
        String telefono,telAnt;
        telAnt=datosCliente.getNumTelefono();
        System.out.println("Ingrese el nuevo numero de telefono del cliente");
         telefono = TecladoIn.readLine();
         datosCliente.setNumTelefono(telefono);
         agregaCargasIniciales.archivoLOG(escribe,"Se ha cambiado el numero de telofono: "+telAnt+ " por: "+telefono+
                 " del cliente de tipo:  "+datosCliente.getTipo()+ " y numero : "+datosCliente.getNum());    
    }
    
    public static void cambiaDomCliente(Cliente datosCliente,BufferedWriter escribe) throws IOException{
          String domicilio,domAnt;
          domAnt=datosCliente.getDomicilio();
          System.out.println("Ingrese el nuevo domicilio del cliente");
          domicilio = TecladoIn.readLine();
          datosCliente.setDomicilio(domicilio);
          agregaCargasIniciales.archivoLOG(escribe,"Se ha cambiado el domicilio: "+domAnt+ " por: "+domicilio+
                 " del cliente de tipo:  "+datosCliente.getTipo()+ " y numero : "+datosCliente.getNum());
    }
    
    public static void menuCambioDatosCliente(){
         // Este metodo muestra el menu de cambio de datos del cliente 
        System.out.println("***************************************************************");
        System.out.println("*            Menu de Cambio de datos del Cliente              *");
        System.out.println("***************************************************************" + "\n\n" ); 
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Cambiar el numero del telefono del cliente");
        System.out.println("OPCION 2: Cambiar el domicilio del cliente");
        System.out.println("OPCION 3: Cambiar el numero de telefono y el domicilio");
        System.out.println("OPCION DISTINTO DE 1, 2 Y 3: Salir");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }   

    public static boolean eliminaCliente(Diccionario cliente, HashMap hashCliente, BufferedWriter escribe) throws IOException {
        boolean eliminado = false;
        String tipo, num;
        System.out.println("Ingrese el tipo de documento del cliente");
        tipo = TecladoIn.readLine();
        System.out.println("Ingrese el numero de documento del cliente");
        num = TecladoIn.readLine();
        Cliente codCliente = new Cliente(tipo, num);
        if (cliente.existeClave(codCliente)) {          
            hashCliente.remove(codCliente);
            cliente.eliminar(codCliente);
            eliminado = true;
            agregaCargasIniciales.archivoLOG(escribe, "Se ha eliminado el cliente con tipo: " + tipo + " y numero: " + num+ " de documento");  
        } else {
            System.out.println("El cliente con tipo: " + tipo + " y numero " + num + "de documento no existe"+"\n");
        }
        return eliminado;
    }

    public static void menuCliente() {
        System.out.println(ANSI_PURPLE_BACKGROUND + "***************************************************************");
        System.out.println(NEGRO +                  "*                        Menu de Cliente                      *");
        System.out.println(ANSI_PURPLE_BACKGROUND + "***************************************************************" + "\n\n" + RESET);
        
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Insertar el cliente");
        System.out.println("OPCION 2: Cambiar los datos del cliente");
        System.out.println("OPCION 3: Eliminar el cliente");
        System.out.println("OPCION DISTINTA de 1,2 y 3, usted NO desea continuar");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public static void ABM_Vuelos(Diccionario vuelos, BufferedWriter escribe, grafoEtiquetado aero, HashMap hashPasaje) throws IOException {
        // Este metodo nos permite elegir una opcion para poder realizar la tarea que cumpla dicha opcion para el caso de VUELOS
        agregaCargasIniciales.archivoLOG(escribe, " VUELOS: ");
        int opcion;
        boolean continua = true, exito;
        while (continua) {
            menuVuelo();
            System.out.println("");
            System.out.println("Elija una opcion: ");
            opcion = TecladoIn.readLineInt();
            switch (opcion) {
                case 1:
                    exito = insertaVuelo(vuelos, escribe, aero);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Se agrego con exito el vuelo " + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se agrego el vuelo" + RESET+"\n");
                    }
                    break;
                case 2:
                    exito = cambiaDatosVuelo(vuelos, escribe, aero, hashPasaje);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Se han cambiado con exito los datos del vuelo " + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se han cambiado los datos del vuelo"+RESET+"\n");
                    }
                    break;
                case 3:
                    exito = cancelaVuelo(vuelos, escribe, aero, hashPasaje);
                    if (exito) {
                        System.out.println("\n");
                        System.out.println(VERDE + "Se ha cancelado con exito el vuelo" + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se ha cancelado el vuelo"+RESET+"\n");
                    }
                
                    break;
                default:
                    continua = false;
                    break;
            }
        }
    }

    public static boolean insertaVuelo(Diccionario vuelo, BufferedWriter escribe, grafoEtiquetado aero) throws IOException {
        // Este metodo inserta el vuelo sino existe en el grafoEtiquetado. Si esto se verifica devuelve true, en caso
        // contrario, devuelve false
        String codVuelo, aeroOrigen, aeroDestino, horaSalida, horaLlegada, salida[], llegada[];
        int asientosTotal, minutos;
        Vuelo vueloClave, datosVuelo;
        boolean inserta = false, arco;
        System.out.println("Ingrese el codigo de vuelo");
        codVuelo = TecladoIn.readLine();
        vueloClave = new Vuelo(codVuelo);
        if (verificaCodVuelo(codVuelo)) {
            if (!vuelo.existeClave(vueloClave)) {
                System.out.println("Ingrese el aeropuerto de origen del aeropuerto");
                aeroOrigen = TecladoIn.readLine();
                System.out.println("Ingrese el aeropuerto de destino del aeropuerto");
                aeroDestino = TecladoIn.readLine();
                Aeropuerto a1 = new Aeropuerto(aeroOrigen);
                Aeropuerto a2 = new Aeropuerto(aeroDestino);
                if (aero.existeVertice(a1) && aero.existeVertice(a2)) {
                    System.out.println("Ingrese la hora de salida del vuelo");
                    horaSalida = TecladoIn.readLine();
                    System.out.println("Ingrese la hora de llegada del vuelo");
                    horaLlegada = TecladoIn.readLine();
                    System.out.println("Ingrese la cantidad de asientos totales  del vuelo");
                    asientosTotal = TecladoIn.readInt();
                    salida = horaSalida.split(":");
                    llegada = horaLlegada.split(":");
                    minutos = agregaCargasIniciales.pasaHoraMinAMin(salida, llegada);
                    arco = aero.insertarArco(a1, a2, minutos);
                    if (arco) {
                        datosVuelo = new Vuelo(codVuelo, aeroOrigen, aeroDestino, horaSalida, horaLlegada, asientosTotal);
                        vuelo.insertar(vueloClave, datosVuelo);
                        
                        agregaCargasIniciales.archivoLOG(escribe, "Se ha agregado el vuelo con codigo: " + codVuelo+
                                " aeropuerto origen: "+aeroOrigen+ " , aeropuerto destino: "+aeroDestino + 
                                " , hora de salida: "+horaSalida+ " , hora de llegada: "+horaLlegada+ " y cantidad de "
                                        +" asientos: "+asientosTotal);                                       
                        //Agregamos la o las fechas
                        insertaViaje(datosVuelo, escribe);
                        inserta = true;
                    } else {
                        inserta = false;
                    }
                } else {
                    System.out.println("Los aeropuertos de origen y de destino no existen" + "\n");
                }
            } else {
                System.out.println("Codigo de vuelo REPETIDO, ingreso NO VALIDO" + "\n");
            }
        } else {
            System.out.println("Codigo de vuelo NO VALIDO" + "\n");
        }
        return inserta;
    }

    public static void insertaViaje(Vuelo datosVuelo, BufferedWriter escribe) throws IOException {
        String continua = "si", fecha;
        boolean aceptada, encontrado;
        System.out.println("Ahora debera agregar la o las fechas para el vuelo: " + datosVuelo.getClave());
        System.out.println("La fecha debe ser del formato: dd/mm/yyyy , sino no sera aceptada");
        while (continua.equalsIgnoreCase("si")) {
            System.out.println("Ingrese la fecha:");
            fecha = TecladoIn.readLine();
            //Se verifica la fecha si es que cumple con el formato deseado
            aceptada = verificaFecha(fecha);
            if (aceptada) {  
                Viaje viaje = new Viaje(fecha);
                encontrado = datosVuelo.fechaEnViaje(viaje);
                if (!encontrado) {
                    Lista ls = (Lista) datosVuelo.getViajes();
                    ls.insertar(viaje, ls.longitud() + 1);
                    agregaCargasIniciales.archivoLOG(escribe, "Se ha agregado la fecha de viaje: " + fecha + " para el vuelo: " 
                            + datosVuelo.getClave());
                } else {
                    System.out.println("La fecha ingresada ya se encuentra en la lista de viajes"+"\n");
                }
            }
            if (!datosVuelo.getViajes().esVacia()) {
                System.out.println("Desea agregar alguna otra fecha al vuelo? ");
                System.out.println("Responda si o no");
                continua = TecladoIn.readLine();
            }
        }
    }

    private static boolean verificaFecha(String fecha) {
        boolean rta;
        rta = verificaFechaAux(fecha);
        if (rta) {
            System.out.println("La fecha es valida");
        } else {
            System.out.println("La fecha no es valida");
        }
        return rta;
    }

    private static boolean verificaFechaAux(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private static boolean verificaCodVuelo(String codVuelo) {
        // Este metodo verifica si el codigo vuelo esta formado por dos letras mayusculas al principio (identifica la compañia) 
        // y cuatro numeros (identifica el vuelo)
        boolean verifica = true;
        int pos = 0;
        char c;
        if (codVuelo.length() == 6) {
            while (pos < codVuelo.length() && verifica) {
                c = codVuelo.charAt(pos);
                if (pos < 2) {
                    verifica = (c >= 'A' && c <= 'Z');
                } else {
                    verifica = (c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
                            || c == '5' || c == '6' || c == '7' || c == '8' || c == '9');
                }
                pos++;
            }
        } else {
            verifica = false;
        }
        return verifica;
    }

    public static boolean cambiaDatosVuelo(Diccionario vuelo, BufferedWriter escribe, grafoEtiquetado aero, HashMap hashPasaje) throws IOException {
        String codVuelo;
        Vuelo datosVuelo, vueloClave;
        int opcion;
        boolean cambia = true;
        System.out.println("Ingrese el codigo de vuelo");
        codVuelo = TecladoIn.readLine();
        vueloClave = new Vuelo(codVuelo);
        if (verificaCodVuelo(codVuelo)) {
            datosVuelo = (Vuelo) vuelo.obtenerInformacion(vueloClave);
            if (vuelo.existeClave(vueloClave)) {
                menuVueloCambioDatos();
                System.out.println("Elija una opcion: ");
                opcion = TecladoIn.readInt();
                switch (opcion) {
                    case 1:
                        cambia = cambiaDatoHora(datosVuelo, aero,escribe);
                        break;
                    case 2:
                        cambia = cambiaHoraSalYLleg(datosVuelo, aero,escribe);
                        break;
                    case 3:
                        cambia = cambiaFechaVuelo(datosVuelo, hashPasaje, escribe);
                        break;
                    default:
                        cambia=false;
                        break;
                }
            } else {
                System.out.println(" El codigo de vuelo: " + codVuelo + " no existe " + "\n");
            }
        } else {
            System.out.println("Codigo de vuelo NO VALIDO" + "\n");
        }
        return cambia;
    }

    
    public static boolean cambiaFechaVuelo(Vuelo datos, HashMap hashPasaje, BufferedWriter escribe) throws IOException {
        // Este metodo cambia la fecha de un determinado vuelo
        String fecha, fechaBuscada;
        boolean aceptada, cambia = false;
        int pos;
        Viaje viaje, elemFecha,encontrado;
        Lista lsViajes = (Lista) datos.getViajes();      
        System.out.println("Las fechas que tenemos del vuelo : "+datos.getClave()+" son: ");
        System.out.println(lsViajes);
        System.out.println("Ingrese la nueva fecha, distinta a la que vio anteriormente: (FORMATO dd/mm/yyyy)");
        fecha = TecladoIn.readLine();
        aceptada = (verificaFecha(fecha) && !datos.fechaEnViaje(new Viaje(fecha)));
        if (aceptada) {
            viaje = new Viaje(fecha);
            System.out.println("Ingrese la fecha que desea cambiar");
            fechaBuscada = TecladoIn.readLine();
            encontrado=datos.buscaFechaViaje(fechaBuscada);  
            pos = lsViajes.localizar(encontrado);
            if (pos == -1) {
                System.out.println("La fecha que desea cambiar no existe");
            } else {
                elemFecha = (Viaje) lsViajes.recuperar(pos);             
                //Recuperamos la lista de los numeros de asiento y la vaciamos
                Lista lsNumAsientos= (Lista)encontrado.getLsNumAsiento();              
                lsNumAsientos.vaciar();
                agregaCargasIniciales.archivoLOG(escribe, "Se ha eliminado la fecha: " + fechaBuscada + 
                        " y se ha cambiado por: " + fecha + " del vuelo" + datos.getClave());
                cancelaPasajeVuelo(datos, hashPasaje, escribe, elemFecha);                
                lsViajes.eliminar(pos);
                lsViajes.insertar(viaje, lsViajes.longitud() + 1);
                cambia = true;               
            }
        }else{
            System.out.println("La fecha: "+fecha+ " ya se encuentra en la lista de viajes, INGRESO NO VALIDO"+"\n");
        }
        return cambia;
    }

   

    public static boolean cambiaHoraSalYLleg(Vuelo datos, grafoEtiquetado aero,BufferedWriter escribe) throws IOException {
        // Este metodo cambia la hora de salida y llegada de un vuelo
        String horaSal, horaLleg;
        boolean rta = true;
        System.out.println("Ingrese la nueva hora del vuelo de salida");
        horaSal = TecladoIn.readLine();
        System.out.println("Ingrese la nueva hora del vuelo de llegada");
        horaLleg = TecladoIn.readLine();
        String salida[], llegada[], origen, destino;
        int minutosAux, minutosNew;
        origen = datos.getAeroOrigen();
        destino = datos.getAeroDestino();
        Aeropuerto a1 = new Aeropuerto(origen);
        Aeropuerto a2 = new Aeropuerto(destino);
        //Obtenemos los minutos de ese vuelo
        minutosAux=datos.obtenerMinutos();
        agregaCargasIniciales.archivoLOG(escribe," Se ha cambiado la hora de salida: "+datos.getHoraSalida()+ " por: "+horaSal+
        " y la hora de llegada: "+datos.getHoraLlegada()+ " por: "+horaLleg+ " del vuelo: "+datos.getClave());
        datos.setHoraSalida(horaSal);
        datos.setHoraLlegada(horaLleg);
        //Armamos un arreglo separando la hora y minutos en llegada y salida de los nuevos datos
        llegada = horaLleg.split(":");
        salida = horaSal.split(":"); 
        //Calculamos los minutos con los nuevos datos
        minutosNew = agregaCargasIniciales.pasaHoraMinAMin(salida, llegada);    
        agregaCargasIniciales.archivoLOG(escribe,"Se cambio la etiqueta: "+minutosAux+ " de los aeropuertos: "+origen+ " y "+
                destino+" por: "+minutosNew+ " del vuelo: "+datos.getClave());
        //Eliminamos el arco actual 
        aero.eliminarArco(a1, a2, minutosAux);
        //Insertamos el arco nuevo
        aero.insertarArco(a1, a2, minutosNew);    
        return rta;
    }

    

    public static boolean cambiaDatoHora(Vuelo datos, grafoEtiquetado aero,BufferedWriter escribe) throws IOException {
        // Este metodo cambio la hora de salida o llegada de un determinado vuelo
        boolean rta = false;
        String horaNew[],origen, destino,hora,llegada[],salida[];
        System.out.println("Ingrese la nueva hora del vuelo de salida o llegada");
        hora = TecladoIn.readLine();
        int opcion, minutosAux, minutosNew;
        origen = datos.getAeroOrigen();
        destino = datos.getAeroDestino();
        Aeropuerto a1 = new Aeropuerto(origen);
        Aeropuerto a2 = new Aeropuerto(destino);
        //h es un arreglo donde almacena la hora y minutos
        horaNew = hora.split(":");
        //Calculamos los minutos con los viejos datos
        minutosAux=datos.obtenerMinutos();
        //Eliminamos el arco actual 
        agregaCargasIniciales.archivoLOG(escribe," Se ha eliminado el arco del aeropuerto: "+origen+ 
                " y "+destino+" con etiqueta: "+minutosAux );
         aero.eliminarArco(a1, a2, minutosAux);
        System.out.println("OPCION 1: Cambiar la hora de salida");
        System.out.println("OPCION 2: Cambiar la hora de llegada");
        System.out.println("Elija una opcion: ");
        opcion = TecladoIn.readInt();
        if(opcion==1 || opcion==2){
            rta=true;
        }
        switch (opcion) {
            case 1:
                agregaCargasIniciales.archivoLOG(escribe," Se ha cambiado la hora de salida: "+datos.getHoraSalida()+
                        "por: "+hora+" del vuelo: "+datos.getClave());
                 //Cambiamos la hora de salida de Vuelo por la hora nueva almacenada en el arreglo 
                datos.setHoraSalida(hora);
                //Calculamos los minutos con los nuevos datos
                llegada=datos.getHoraLlegada().split(":");
                minutosNew = agregaCargasIniciales.pasaHoraMinAMin(horaNew, llegada);
                //insertamos el arco ya con los minutos cambiados
                aero.insertarArco(a1, a2, minutosNew);
                agregaCargasIniciales.archivoLOG(escribe, "Se ha creado el arco del aeropuerto: "+origen+" y "
                +destino+" con etiqueta: "+minutosNew);
                break;
            case 2:
                agregaCargasIniciales.archivoLOG(escribe," Se ha cambiado la hora de llegada: "+datos.getHoraLlegada()+
                        " por: "+hora+" del vuelo: "+datos.getClave());
                //Cambiamos la hora de llegada de Vuelo por la hora nueva almacenada en el arreglo 
                datos.setHoraLlegada(hora);
                //Calculamos los minutos con los nuevos datos
                salida=datos.getHoraSalida().split(":");
                minutosNew = agregaCargasIniciales.pasaHoraMinAMin(salida, horaNew);
                //insertamos el arco ya con los minutos cambiados
                aero.insertarArco(a1, a2, minutosNew);
                agregaCargasIniciales.archivoLOG(escribe, "Se ha creado el arco del aeropuerto: "+origen+" y "
                +destino+" con etiqueta: "+minutosNew);
                break;
            default:
                System.out.println("No se cambio la hora de salida, ni la hora de llegada");
                break;
        }
        return rta;
    }

    public static void menuVueloCambioDatos() {
        // Este metodo muestra el menu de vuelo de cambios de datos
        System.out.println("*********************************************************************");
        System.out.println("*               Menu de Vuelo de Cambio de Datos                    *");
        System.out.println("*********************************************************************");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Cambiar  la hora de salida o llegada");
        System.out.println("OPCION 2: Cambiar los datos de la hora de salida y llegada");
        System.out.println("OPCION 3: Cambiar la fecha de vuelo");     
        System.out.println("OPCION DISTINTA DE 1, 2 y 3 : Salir");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public static boolean cancelaVuelo(Diccionario vuelo, BufferedWriter escribe, grafoEtiquetado aero, HashMap hashPasaje) throws IOException {
      // Este metodo cancela el vuelo. Si esto se verifica devuelve true, en caso contrario, devuelve false
        boolean elimina = false;
        String codVuelo;
        int p;
        Vuelo clave, datosVuelo;
        System.out.println("Ingrese el codido de vuelo");
        codVuelo = TecladoIn.readLine();
        if (verificaCodVuelo(codVuelo)) {
            clave = new Vuelo(codVuelo);
            if (vuelo.existeClave(clave)) {
                datosVuelo = (Vuelo) vuelo.obtenerInformacion(clave);
               Lista lsViajes = (Lista) datosVuelo.getViajes();
                 for ( p = 1; p <= lsViajes.longitud(); p++) {
                    Viaje elemFecha = (Viaje) lsViajes.recuperar(p);
                    cancelaPasajeVuelo(datosVuelo, hashPasaje, escribe, elemFecha);
                }
                lsViajes.vaciar();
                //Elimina el arco del aeorpuerto de origen y destino
                eliminaArcoVuelo(datosVuelo, aero,escribe);//FUNCIONA
                //Elimina el vuelo segun la clave
                vuelo.eliminar(clave);              
                elimina = true;
            } else {
                System.out.println(" El codigo de vuelo: " + codVuelo + " no existe " + "\n");
            }
        } else {
            System.out.println("Codigo de vuelo NO VALIDO" + "\n");
        }
        return elimina;
    }

    public static void cancelaPasajeVuelo(Vuelo datos, HashMap hashPasaje, BufferedWriter escribe, Viaje fecha) throws IOException {
        // Este metodo cancela el paseje de un determinado vuelo, cambiado el estado a cancelado 
        //Armo un Set con todas las claves de hashPasaje
        Set claves = hashPasaje.keySet();
        int i, j, longitud;
        Pasaje pasaje;
        Lista lsPasaje;
        Object codClienteArr[];
        //Paso las claves a un arreglo
        codClienteArr = claves.toArray();
        //Almaceno el tamaño de codClienteArr
        longitud = codClienteArr.length;
        for (i = 0; i < longitud; i++) {
            //Creo una lista para que me devuelva la lista de pasajes de un cliente 
            Cliente claveCliente = (Cliente) codClienteArr[i];
            lsPasaje = (Lista) hashPasaje.get(claveCliente);
           
            for (j = 1; j <= lsPasaje.longitud(); j++) {
                //Recupero el pasaje
                pasaje = (Pasaje) lsPasaje.recuperar(j);
                
                if (pasaje.getVuelo().equals(datos)
                        && fecha.getFecha().equals(pasaje.getFecha())) {
                    pasaje.setEstado("cancelado");
                   
                     agregaCargasIniciales.archivoLOG(escribe, "El pasaje del cliente con numero: " + claveCliente.getNum()
                    + " y tipo: " + claveCliente.getTipo() + " de documento,  del vuelo:  " + datos.getClave() + " ha sido cancelado ");
                }
            
            }
        }
    }

    public static void eliminaArcoVuelo(Vuelo datos, grafoEtiquetado aero,BufferedWriter escribe) throws IOException {
        // Este metodo elimina el arco de vuelo entre dos aeropuertos
        String origen, destino;
        int minutos;
        Aeropuerto a1,a2,o,d;
        origen = datos.getAeroOrigen();
        destino = datos.getAeroDestino();
        minutos = datos.obtenerMinutos();
         a1 = new Aeropuerto(origen);
         a2 = new Aeropuerto(destino);
         o=(Aeropuerto)aero.buscaElemento(a1);
         d=(Aeropuerto)aero.buscaElemento(a2);
       agregaCargasIniciales.archivoLOG(escribe, "Se ha eliminado el arco de vuelo entre el aeropuerto "+origen+ " y el aeropuerto: "
       +destino+ " con etiqueta: "+minutos+ " del vuelo: "+datos.getClave());
        aero.eliminarArco(o, d, minutos);
    }

    public static void menuVuelo() {
        // Este metodo muestra el menu de vuelo
        System.out.println(ANSI_BLUE_BACKGROUND + "***************************************************************");
        System.out.println(NEGRO +                "*                        Menu de Vuelo                        *");
        System.out.println(ANSI_BLUE_BACKGROUND + "***************************************************************" + "\n\n" + RESET);
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Insertar el vuelo");
        System.out.println("OPCION 2: Cambiar los datos del vuelo");
        System.out.println("OPCION 3: Cancela el vuelo");
        System.out.println("OPCION DISTINTA de 1,2 y 3, usted NO desea continuar");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public static boolean ABM_Pasajes(Diccionario vuelo, BufferedWriter escribe, HashMap pasaje, Diccionario cliente) throws IOException {
        // Este metodo nos permite elegir una opcion para poder realizar la tarea que cumpla dicha opcion para el caso de PASAJES
        agregaCargasIniciales.archivoLOG(escribe, " PASAJES: ");
        boolean continua = true, exito=false;
        int opcion;
        while (continua) {
            menuPasaje();
            System.out.println("Elija una opcion");
            opcion = TecladoIn.readInt();
            switch (opcion) {
                case 1:
                    exito = compraPasaje(vuelo, escribe, pasaje, cliente);
                    if (exito) {
                        System.out.println(VERDE + "Se ha comprado el pasaje con exito" + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se ha comprado el pasaje" + RESET+"\n");
                    }
                    break;
                case 2:
                    exito=cambiaDatosPasaje(cliente,escribe,pasaje,vuelo);
                    if(exito){
                         System.out.println(VERDE + "Se han cambiado los datos del pasaje con exito" + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se han  cambiado los datos  del pasaje" + RESET+"\n");
                    }
                    break;
                case 3: 
                   exito= cancelaPasaje(vuelo,escribe,pasaje,cliente);
                   if(exito){
                        System.out.println(VERDE + "Se ha cancelado el pasaje con exito" + RESET+"\n");
                    } else {
                        System.out.println(ROJO + "No se ha cancelado el pasaje" + RESET+"\n");               
                   }
                   break;
                default:
                    continua = false;
                    break;
            }
        }
        return exito;
    }
    
     public static boolean cancelaPasaje(Diccionario vuelo,BufferedWriter escribe,HashMap hashPasaje,Diccionario cliente) throws IOException{
       // Este metodo elimina un pasaje. Si esto se cumple devuelve true, caso contrario, devuelve false
        String tipo,numero,fechaPasaje,clave;
         boolean cancela=false,buscado=false;
         Cliente codCliente;
         int pos=1;
         Viaje viaje;
         Pasaje pasaje = null;
         Vuelo codVuelo,datosVuelo;
         Lista lsPasajes;
         //Pedimos los datos clave del cliente
         System.out.println("Ingrese el tipo de documento del cliente");
         tipo=TecladoIn.readLine();
         System.out.println("Ingrese el numero de documento del cliente ");
         numero=TecladoIn.readLine();
         codCliente=new Cliente(tipo,numero);
         if(cliente.existeClave(codCliente)){
             System.out.println("Ingrese el codigo de vuelo: ");
             clave=TecladoIn.readLine();
             codVuelo=new Vuelo(clave);
             if(vuelo.existeClave(codVuelo)){
                 datosVuelo=(Vuelo)vuelo.obtenerInformacion(codVuelo);
             //Pedimos la lista de pasajes
             lsPasajes=(Lista)hashPasaje.get(codCliente);
                 System.out.println("Te mostraremos las fechas de viaje: ");
                 System.out.println(datosVuelo.getViajes());
             System.out.println("Ingrese la fecha del pasaje(alguna que vio anteriormente)");
             fechaPasaje=TecladoIn.readLine();      
             while( !buscado  ){                 
                  pasaje=(Pasaje)lsPasajes.recuperar(pos);
                 buscado=pasaje.getFecha().equals(fechaPasaje) 
                        && pasaje.getVuelo().equals(datosVuelo); 
                 pos++;
             }
             if(pasaje!=null){
                 cancela=true;       
             viaje=datosVuelo.buscaFechaViaje(fechaPasaje);
             //Eliminamos el numero de asiento de la lista
            eliminaNumAsientoLs( pasaje, viaje);
             //Sacamos 1 asiento vendido de ese vuelo
                datosVuelo.sacaVendidosFecha(viaje);
                pasaje.setEstado("cancelado");
                agregaCargasIniciales.archivoLOG(escribe,"Se ha cancelado el pasaje del cliente con tipo: "+tipo+ " y numero de documento: "
                +numero+" del vuelo:  "+clave);               
             }else{
                 System.out.println("El pasaje no existe "+"\n");
             }      
         }else{
              System.out.println("El codigo de vuelo: "+clave+" no existe"+"\n");
         }     
     }else{     
             System.out.println("El cliente con tipo: "+tipo+ " y numero de documento: "+numero+ " no existe"+"\n");
         }
         return cancela;
     }
     
    public static boolean compraPasaje(Diccionario vuelo, BufferedWriter escribe, HashMap hashPasaje, Diccionario cliente) throws IOException {
        // Este metodo compra un pasaje. Si esto se verifica devuelve true, en caso contrario, devuelve false
        String codVuelo, fecha;
        boolean buscado, verifica, compra = false;
        Viaje viaje;
        Vuelo datosVuelo, clave;
        System.out.println("Ingrese el codigo de vuelo para identificar el pasaje que desea");
        codVuelo = TecladoIn.readLine();
        clave = new Vuelo(codVuelo);
        if (vuelo.existeClave(clave)) {
            datosVuelo = (Vuelo) vuelo.obtenerInformacion(clave);
            System.out.println("Te mostraremos las fechas de viajes disponibles: ");
            System.out.println(datosVuelo.getViajes());
            System.out.println("Ingrese la fecha de viaje (FORMATO dd/mm/yyyy, alguna que vio anteriormente)");
            fecha = TecladoIn.readLine();
            //Verificamos si la fecha ingresada, respeta el formato deseado
            verifica = verificaFecha(fecha);
            viaje = new Viaje(fecha);
            if (verifica) {
                //Si la fecha se encuentra en la lista de viajes de vuelos
                buscado = datosVuelo.fechaEnViaje(viaje);
                if (buscado) {
                    // si encuentra la fecha en la lista de viajes
                    compra = creaPasaje(cliente, hashPasaje, escribe, datosVuelo, fecha);
                } else {
                    System.out.println("No se encontro la fecha ingresada en la lista de viajes" + "\n");
                }
            }
            } else {
                System.out.println("El codigo de vuelo no existe, INGRESO NO VALIDO" + "\n");
        }
        return compra;
    }

    public static boolean creaPasaje(Diccionario cliente, HashMap hashPasaje, BufferedWriter escribe, Vuelo datos, String fecha) throws IOException {
        // Este metodo crea un pasaje
        String numero, tipo;
        Lista lsPasaje, lsNumAsiento;
        int numAsiento, p;
        Pasaje pasaje;
        boolean compra = false; 
        Viaje  fechaViaje=datos.buscaFechaViaje(fecha);
        lsNumAsiento = (Lista) fechaViaje.getLsNumAsiento();
        System.out.println("Te mostraremos los  asientos que se encuentran ocupados");
        System.out.println(lsNumAsiento);
        System.out.println("Ingrese el numero de asiento, distinto al que vio anteriormente");
        numAsiento = TecladoIn.readInt();
        //Buscamos la posicion del numAsiento para comprobar su existencia
        p = lsNumAsiento.localizar(numAsiento);
        if (p == -1 && numAsiento <= datos.getAsientoTotal()) {      
            //Pedimos los datos unicos del cliente
            System.out.println("Ingrese el tipo de documento del cliente:");
            tipo = TecladoIn.readLine();
            System.out.println("Ingrese el numero de documento del cliente");
            numero = TecladoIn.readLine();
            Cliente codCliente = new Cliente(tipo, numero);
            if (cliente.existeClave(codCliente)) {
                //Insertamos el numAsiento es la lista
                lsNumAsiento.insertar(numAsiento, lsNumAsiento.longitud() + 1);
                //Creamos el pasaje
                pasaje = new Pasaje(datos, fecha, numAsiento, "pendiente");
                //Usando el codCliente en el hashPasaje , obtenemos la lista de pasajes
                lsPasaje = (Lista) hashPasaje.get(codCliente);
                lsPasaje.insertar(pasaje, lsPasaje.longitud() + 1);
                 //Aumentamos la cant de asientos vendidos + 1
              datos.vendXFechaVuelo(fechaViaje);
                agregaCargasIniciales.archivoLOG(escribe, "Se ha agregado el pasaje con codigo vuelo: " + datos.getClave() 
                        + " para el cliente con numero:"  + numero+ " y tipo: "+tipo+ " de documento , fecha: "+fecha+" y"
                        + " numero de asiento: "+numAsiento );
                compra = true;
            } else {
                System.out.println("El cliente con tipo: " + tipo + " y numero: " + numero + " de documento no existe" + "\n");
            }
        } else {
            System.out.println("El numero de asiento  " + numAsiento + " no se encuentra disponible" + "\n");
        }
        return compra;
    }

    public static boolean cambiaDatosPasaje(Diccionario cliente, BufferedWriter escribe, HashMap hashPasaje, Diccionario vuelo) throws IOException {
        //Este metodo cambia los datos del pasaje de un cliente.Si cambia dichos datos devuelve true, en caso contrario, devuelve false
        boolean cambia = false;
        int opcion,i,numAsiento;
        Viaje viaje;
        String numero, tipo, codVuelo, fecha;
        Cliente codCliente;
        Pasaje pasaje;
        System.out.println("Ingrese el codigo del vuelo");
        codVuelo = TecladoIn.readLine();
        Vuelo clave = new Vuelo(codVuelo);
        if (vuelo.existeClave(clave)) {
            Vuelo datosVuelo = (Vuelo) vuelo.obtenerInformacion(clave);
            System.out.println("Ingrese el numero de documento");
            numero = TecladoIn.readLine();
            System.out.println("Ingrese el tipo de documento");
            tipo = TecladoIn.readLine();
            codCliente = new Cliente(tipo, numero);
            if (cliente.existeClave(codCliente)){
                Lista lsPasajes = (Lista) hashPasaje.get(codCliente);                
                 System.out.println("Ingresa la fecha del pasaje");
                    fecha = TecladoIn.readLine();
                    System.out.println("Ingresa el numero de asiento de su pasaje");
                    numAsiento=TecladoIn.readInt();
                    //Buscamos el pasaje que tenga la fecha y numero de asiento
                    pasaje= buscaPasaje(lsPasajes, fecha,numAsiento);
                    //Buscamos el viaje que tenga la fecha
                   viaje= datosVuelo.buscaFechaViaje(fecha);    
                     if (pasaje!=null ) {
                    menuPasajeCambioDatos();
                System.out.println("Elija una opcion: ");
                opcion = TecladoIn.readInt();              
                    switch (opcion) {
                        case 1:
                           cambia=cambiaTodo(viaje,datosVuelo,pasaje,escribe);
                                                 
                          agregaCargasIniciales.archivoLOG(escribe, "El pasaje del cliente con tipo " + tipo + " y numero " + numero
                        + " y vuelo " + codVuelo + " ha cambiado los datos de la fecha a: "+pasaje.getFecha()+" , numero de asiento a :"
                               +pasaje.getNumAsiento()+ " y estado a: "+pasaje.getEstado());                     
                            break;
                        case 2:          
                               eliminaNumAsientoLs(pasaje,viaje);
                            cambia = newNumAsiento(pasaje, escribe,viaje);                          
                            break;
                        case 3:
                            cambia=cambiaEstadoPasaje(pasaje,escribe);      
                        break;
                    }
                  
                } else {
                    System.out.println("No existe el pasaje "+"\n");
                }
            } else {
                 System.out.println("El cliente con tipo: " + tipo + " y numero " + numero + " no existe" + "\n");
               
            }
        }else{
             System.out.println("El codido de vuelo ingresado : " + codVuelo + " no existe " + "\n");
        }
        return cambia;

    }
    public static Pasaje buscaPasaje(Lista lsPasaje,String fecha,int asiento){
       int longitud = lsPasaje.longitud(), pos = 1;
        boolean buscado = false;
        Pasaje pasaje=null;
     
        while (pos <= longitud && !buscado) {
            pasaje = (Pasaje) lsPasaje.recuperar(pos);
           
            buscado = pasaje.getFecha().equals(fecha) &&
                    (pasaje.getNumAsiento()== asiento);
            pos++;
        }
     
       if(!buscado){
           pasaje=null;
       }
    return pasaje;   
    }

    public static boolean cambiaTodo(Viaje viaje,Vuelo datosVuelo,Pasaje pasaje,BufferedWriter escribe) throws IOException {
        String newFecha;
        Viaje auxFecha;
        boolean cambia=false,rta;
        //sacamos un asiento vendido ya que vamos a cambiar la fecha y esa persona no va a viajar ese dia       
        viaje.sacaAsientosVend();
        System.out.println("Te mostraremos las fechas de viajes disponibles para el vuelo: "+datosVuelo.getClave());
        System.out.println(datosVuelo.getViajes());
        System.out.println("Ingrese la fecha nueva: (FORMATO dd/mm/yyyy) ");
        newFecha = TecladoIn.readLine();
     if(verificaFecha(newFecha)){
          auxFecha=datosVuelo.buscaFechaViaje(newFecha);
        if (auxFecha!=null) {
            eliminaNumAsientoLs(pasaje,viaje);
            rta = newNumAsiento(pasaje, escribe, auxFecha);
            if (rta) {
                  agregaCargasIniciales.archivoLOG(escribe,"Se ha cambiado la fecha de pasaje:"+pasaje.getFecha()+ " por: "+newFecha
                  +" del vuelo"+pasaje.getVuelo().getClave()); 
                //Cambiamos fecha
                pasaje.setFecha(newFecha);                           
                //Aumentamos la cantidad de asientos vendidos + 1 
                auxFecha.vendXFechaViaje();
                //Cambiamos el estado del pasaje       
                 cambiaEstadoPasaje(pasaje,escribe);
                cambia = true;
            }
        }else{
              System.out.println("No se encontro la nueva fecha ingresada en la lista de viajes");
        }
     }
        return cambia;
    }
   
    public static Pasaje buscaFechaPasaje(Lista lsPasaje, String fecha) {
        int longitud = lsPasaje.longitud(), pos = 1;
        boolean buscado = false;
        Pasaje pasaje=null;
        while (pos <= longitud && !buscado) {
            pasaje = (Pasaje) lsPasaje.recuperar(pos);
            buscado = pasaje.getFecha().equals(fecha);
            pos++;
        }
        if(!buscado){
            pasaje=null;
        }
        return pasaje;

    }
  private static void eliminaNumAsientoLs(Pasaje pasaje,Viaje fechaPasaje) throws IOException{
      // Este metodo elimina el numero de asiento 
        //Debemos eliminar el numero de asiento de ese pasaje ya que no se va a usar
        Lista lsNumAsiento = (Lista) fechaPasaje.getLsNumAsiento();
        //Obtenemos el numero de asiento de ese pasaje
        int numAsiento = pasaje.getNumAsiento();
        //Buscamos la posicion de ese numero asiento asi podemos eliminarlo de la lista
        int posAsiento =  lsNumAsiento.localizar(numAsiento);
        lsNumAsiento.eliminar(posAsiento);
  }
    public static boolean newNumAsiento(Pasaje pasaje, BufferedWriter escribe, Viaje auxFecha) throws IOException {
        // Este metodo cambia el numero de asiento de un pasaje
          int i,newNumAsiento;
          boolean cambia=false;
         Lista lsNumAsiento = (Lista) auxFecha.getLsNumAsiento();
        //Ahora debe agregar el nuevo numero de asiento
        System.out.println("Ingrese el nuevo numero de asiento");
        newNumAsiento = TecladoIn.readInt();
        i = lsNumAsiento.localizar(newNumAsiento);
        if (i == -1) {
            lsNumAsiento.insertar(newNumAsiento, lsNumAsiento.longitud() + 1);     
            agregaCargasIniciales.archivoLOG(escribe, "Se ha cambiado el numero del asiento: "+pasaje.getNumAsiento()
                    + " por: " + newNumAsiento+" del"
                    + " pasaje con vuelo: "+pasaje.getVuelo().getClave()+" Y fecha: "+pasaje.getFecha());
            //Cambiamos numero asiento
            pasaje.setNumAsiento(newNumAsiento);
            cambia=true;
        } else {
            System.out.println("El nuevo numero de asiento: " + newNumAsiento + " se encuentra ocupado");
        }
        return cambia;
    }

    public static boolean cambiaEstadoPasaje(Pasaje pasaje, BufferedWriter escribe) throws IOException {
        boolean cambia=true;
         System.out.println("Ahora debera cambiar el estado del pasaje");
        System.out.println("-------------------------------------------");
        System.out.println("OPCION 1: cancelado");
        System.out.println("OPCION 2: volado");
        System.out.println("OPCION 3: pendiente");
        System.out.println("OPCION DISTINTA DE 1, 2 Y 3: Salir");
        System.out.println("---------------------------------------------");
        int opcion;
        System.out.println("Elija una opcion");
        opcion=TecladoIn.readInt();
        switch(opcion){
            case 1: 
                pasaje.setEstado("cancelado");
            break;
            case 2:
                pasaje.setEstado("volado");
            break;
            case 3:
                pasaje.setEstado("pendiente");
            break;
            default:
                cambia=false;
                break;
        }
        if(cambia){
        agregaCargasIniciales.archivoLOG(escribe, "El pasaje con vuelo: "+pasaje.getVuelo().getClave()
                + " y fecha: "+pasaje.getFecha()+" cambio el estado por: " +pasaje.getEstado());
        }
        return cambia;
    }

    public static void menuPasajeCambioDatos() {
        // Este metodo muestra el menu de pasaje de cambios de datos
        System.out.println("*********************************************************************");
        System.out.println("*               Menu de Pasaje de Cambio de Datos                   *");
        System.out.println("*********************************************************************");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Cambiar Todo(fecha de pasaje,numero asiento y estado)");
        System.out.println("OPCION 2: Cambiar el numero de asiento");
        System.out.println("OPCION 3: Cambiar el estado del pasaje");
        System.out.println("OPCION DISTINTA DE 1, 2 Y 3: Salir");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }

    public static void menuPasaje() {
        // Esye metodo muestra el menu del pasaje
        System.out.println(ANSI_RED_BACKGROUND + "***************************************************************");
        System.out.println(NEGRO +               "*                     Menu de Pasaje                          *");
        System.out.println(ANSI_RED_BACKGROUND + "***************************************************************" + "\n\n" + RESET);
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("OPCION 1: Comprar el pasaje");
        System.out.println("OPCION 2: Cambiar los datos del pasaje");
        System.out.println("OPCION 3: Cancelar el pasaje");
        System.out.println("OPCION DISTINTA de 1,2 y 3, usted NO desea continuar");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

}
