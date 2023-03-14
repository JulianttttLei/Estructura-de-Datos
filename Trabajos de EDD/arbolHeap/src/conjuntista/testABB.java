/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntista;

/**
 *
 * @author Administrador
 */
public class testABB {

    public static void main(String[] args) {
        System.out.println("********************************************************");
        System.out.println("Test Arbol Binario de Busqueda");
        System.out.println("*******************************************************");

        ArbolBB a = new ArbolBB();
        System.out.println("Vacio? " + a.esVacio() + "\n");
        System.out.println("TIENE 1 SOLO NODO");
        System.out.println("Inserta 32,debe decir true: " + a.insertar(32));
        System.out.println("Elimina la raiz: " + a.eliminarMax());
        System.out.println("Deberia mostrar: Arbol vacio ");
        System.out.println("Muestra: " + a.toString() + "\n");
        System.out.println("Inserta 32,debe decir true: " + a.insertar(32));
        System.out.println("Elimina la raiz: " + a.eliminarMin());
        System.out.println("Deberia mostrar: Arbol vacio ");
        System.out.println("Muestra: " + a.toString() + "\n");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("TIENE 1 HIJO IZQUIERDO, ELIMINAMOS EL ELEMENTO MINIMO");
        System.out.println("Inserta 32, debe decir true: " + a.insertar(32));
        System.out.println("Inserta 9, debe decir true: " + a.insertar(9));
        System.out.println("Deberia mostrar:" + "\n"
                + "Nodo 32 HI: 9 HD: null" + "\n"
                + "Nodo 9 HI: null HD: null");
        System.out.println("Muestra: " + "\n" + a.toString());
        System.out.println("Elimina elemento minimo(9): " + a.eliminarMin());
        System.out.println("Deberia mostrar: " + "\n" + "Nodo 32 HI: null HD: null ");
        System.out.println("Muestra:" + "\n" + a.toString() + "\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        a.vaciar();
        System.out.println("Vaciamos el arbol, debe decir true: " + a.esVacio());
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("TIENE 1 HIJO IZQUIERDO, ELIMINAMOS EL ELEMENTO MAXIMO(SE ELIMINA LA RAIZ)");
        System.out.println("Inserta 32, debe decir true: " + a.insertar(32));
        System.out.println("Inserta 9, debe decir true: " + a.insertar(9));
        System.out.println("Deberia mostrar:" + "\n"
                + "Nodo 32 HI: 9 HD: null" + "\n"
                + "Nodo 9 HI: null HD: null");
        System.out.println("Muestra: " + "\n" + a.toString());
        System.out.println("Elimina elemento maximo(32): " + a.eliminarMax());
        System.out.println("Deberia mostrar: " + "\n" + "Nodo 9 HI: null HD: null ");
        System.out.println("Muestra:" + "\n" + a.toString() + "\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        a.vaciar();
        System.out.println("Vaciamos el arbol, debe decir true: " + a.esVacio());
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("TIENE  1 HIJO DERECHO, ELIMINAMOS EL ELEMENTO MAXIMO");
        System.out.println("Inserta 32, debe decir true: " + a.insertar(32));
        System.out.println("Inserta 56, debe decir true: " + a.insertar(56));
        System.out.println("Deberia mostrar:" + "\n"
                + "Nodo 32   HI: null HD: 56" + "\n"
                + "Nodo 56   HI: null HD: null");
        System.out.println("Muestra: " + "\n" + a.toString());
        System.out.println("Elimina elemento maximo(56): " + a.eliminarMax());
        System.out.println("Deberia mostrar:" + "\n"
                + "Nodo 32   HI: null HD: null");
        System.out.println("Muestra: " + "\n" + a.toString());
        System.out.println("---------------------------------------------------------------------------------------------");
        a.vaciar();
        System.out.println("Vaciamos el arbol, debe decir true: " + a.esVacio());
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("TIENE 1 HIJO DERECHO, ELIMINAMOS EL ELEMENTO MINIMO(SE ELIMINA LA RAIZ)");
        System.out.println("Inserta 32, debe decir true: " + a.insertar(32));
        System.out.println("Inserta 56, debe decir true: " + a.insertar(56));
        System.out.println("Deberia mostrar:" + "\n"
                + "Nodo 32   HI: null HD: 56" + "\n"
                + "Nodo 56   HI: null HD: null");
        System.out.println("Muestra: " + "\n" + a.toString());
        System.out.println("Elimina elemento minimo(32): " + a.eliminarMin());
        System.out.println("Deberia mostrar:" + "\n"
                + "Nodo 56   HI: null HD: null");
        System.out.println("Muestra: " + "\n" + a.toString());
        System.out.println("----------------------------------------------------------------------------------------------");
        a.vaciar();
        System.out.println("Vaciamos el arbol, debe decir true: " + a.esVacio());
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Inserta 9,debe decir true: " + a.insertar(9));
        System.out.println("Inserta 56, debe decir true: " + a.insertar(56));
        System.out.println("Inserta 5,debe decir true: " + a.insertar(5));
        System.out.println("Inserta 19,debe decir true: " + a.insertar(19));
        System.out.println("Inserta 43,debe decir true: " + a.insertar(43));
        System.out.println("Inserta 72,debe decir true: " + a.insertar(72));
        System.out.println("Inserta 1,debe decir true: " + a.insertar(1));
        System.out.println("Inserta 8,debe decir true: " + a.insertar(8));
        System.out.println("Inserta 17,debe decir true: " + a.insertar(17));
        System.out.println("Inserta 23,debe decir true: " + a.insertar(23));
        System.out.println("Inserta 41,debe decir true: " + a.insertar(41));
        System.out.println("Inserta 53,debe decir true: " + a.insertar(53));
        System.out.println("Inserta 64,debe decir true: " + a.insertar(64));
        System.out.println("Inserta 80,debe decir true: " + a.insertar(80));
        System.out.println("\n");
        System.out.println("Deberia mostrar: " + "\n"
                + " Nodo 32 HI: 9 HD: 56" + "\n"
                + " Nodo 9 HI: 5 HD: 19" + "\n"
                + " Nodo 5 HI: 1 HD: 8" + "\n"
                + " Nodo 1 HI: null HD: null" + "\n"
                + " Nodo 8 HI: null HD: null" + "\n"
                + " Nodo 19 HI: 17 HD: 23" + "\n"
                + " Nodo 17 HI: null HD: null" + "\n"
                + " Nodo 23 HI: null HD: null" + "\n"
                + " Nodo 56 HI: 43 HD: 72" + "\n"
                + " Nodo 43 HI: 41 HD: 53" + "\n"
                + " Nodo 41 HI: null HD: null" + "\n"
                + " Nodo 53 HI: null HD: null" + "\n"
                + " Nodo 72 HI: 64 HD: 80" + "\n"
                + " Nodo 64 HI: null HD: null" + "\n"
                + " Nodo 80 HI: null HD: null" + "\n");
        System.out.println("Muestra:  " + "\n" + a.toString());
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Rango entre 5 y 56, debe mostrar: 5,8,9,17,19,23,32,41,43,53,56 ");
        System.out.println("Muestra: " + a.listarRango(5, 56));
        System.out.println("Rango entre 1 y 8, debe mostrar: 1,5,8 ");
        System.out.println("Muestra: " + a.listarRango(1, 8));
        System.out.println("Rango entre 43 y 64, debe mostrar: 43,53,56,64 ");
        System.out.println("Muestra: " + a.listarRango(43, 64));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("\n");
        System.out.println("EL ELEMENTO MAXIMO (80) TIENE HIJO DERECHO Y HIJO IZQUIERDO NULL");
        System.out.println("Elimina elemento maximo(80), debe dar true " + a.eliminarMax());
        System.out.println("Deberia mostrar: " + "\n"
                + " Nodo 32 HI: 9 HD: 56" + "\n"
                + " Nodo 9 HI: 5 HD: 19" + "\n"
                + " Nodo 5 HI: 1 HD: 8" + "\n"
                + " Nodo 1 HI: null HD: null" + "\n"
                + " Nodo 8 HI: null HD: null" + "\n"
                + " Nodo 19 HI: 17 HD: 23" + "\n"
                + " Nodo 17 HI: null HD: null" + "\n"
                + " Nodo 23 HI: null HD: null" + "\n"
                + " Nodo 56 HI: 43 HD: 72" + "\n"
                + " Nodo 43 HI: 41 HD: 53" + "\n"
                + " Nodo 41 HI: null HD: null" + "\n"
                + " Nodo 53 HI: null HD: null" + "\n"
                + " Nodo 72 HI: 64 HD: null" + "\n"
                + " Nodo 64 HI: null HD: null" + "\n");
        System.out.println("Muestra:  " + "\n" + a.toString());
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("\n");
        System.out.println("EL ELEMENTO MINIMO (1) TIENE HIJO DERECHO Y HIJO IZQUIERDO NULL");
        System.out.println("Elimina elemento minimo(1),debe dar true: " + a.eliminarMin());
        System.out.println("Deberia mostrar: " + "\n"
                + " Nodo 32 HI: 9 HD: 56" + "\n"
                + " Nodo 9 HI: 5 HD: 19" + "\n"
                + " Nodo 5 HI: null HD: 8" + "\n"
                + " Nodo 8 HI: null HD: null" + "\n"
                + " Nodo 19 HI: 17 HD: 23" + "\n"
                + " Nodo 17 HI: null HD: null" + "\n"
                + " Nodo 23 HI: null HD: null" + "\n"
                + " Nodo 56 HI: 43 HD: 72" + "\n"
                + " Nodo 43 HI: 41 HD: 53" + "\n"
                + " Nodo 41 HI: null HD: null" + "\n"
                + " Nodo 53 HI: null HD: null" + "\n"
                + " Nodo 72 HI: 64 HD: null" + "\n"
                + " Nodo 64 HI: null HD: null" + "\n");
        System.out.println("Muestra:  " + "\n" + a.toString());
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Inserta 8(elemento repetido),debe dar false: " + a.insertar(8));
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("EL ELEMENTO MAXIMO(72) TIENE  HIJO IZQUIERDO (64) Y HIJO DERECHO NULL (1 SOLO HIJO)");
        System.out.println("Elimina elemento maximo(72),debe dar true: " + a.eliminarMax());
        System.out.println("Deberia mostrar: " + "\n"
                + " Nodo 32 HI: 9 HD: 56" + "\n"
                + " Nodo 9 HI: 5 HD: 19" + "\n"
                + " Nodo 5 HI: null HD: 8" + "\n"
                + " Nodo 8 HI: null HD: null" + "\n"
                + " Nodo 19 HI: 17 HD: 23" + "\n"
                + " Nodo 17 HI: null HD: null" + "\n"
                + " Nodo 23 HI: null HD: null" + "\n"
                + " Nodo 56 HI: 43 HD: 64" + "\n"
                + " Nodo 43 HI: 41 HD: 53" + "\n"
                + " Nodo 41 HI: null HD: null" + "\n"
                + " Nodo 53 HI: null HD: null" + "\n"
                + " Nodo 64 HI: null HD: null" + "\n");
        System.out.println("Muestra:  " + "\n" + a.toString());
        System.out.println("-------------------------------------------------------------------------------------------------");
         System.out.println("EL ELEMENTO MINIMO(5) TIENE  HIJO DERECHO (8) Y HIJO IZQUIERDO NULL (1 SOLO HIJO)");
        System.out.println("Elimina elemento minimo(5),debe dar true: " + a.eliminarMin());
        System.out.println("Deberia mostrar: " + "\n"
                + " Nodo 32 HI: 9    HD: 56" + "\n"
                + " Nodo 9  HI: 8    HD: 19" + "\n"
                + " Nodo 8  HI: null HD: null" + "\n"
                + " Nodo 19 HI: 17   HD: 23" + "\n"
                + " Nodo 17 HI: null HD: null" + "\n"
                + " Nodo 23 HI: null HD: null" + "\n"
                + " Nodo 56 HI: 43   HD: 64" + "\n"
                + " Nodo 43 HI: 41   HD: 53" + "\n"
                + " Nodo 41 HI: null HD: null" + "\n"
                + " Nodo 53 HI: null HD: null" + "\n"
                + " Nodo 64 HI: null HD: null" + "\n");
        System.out.println("Muestra:  " + "\n" + a.toString());
        System.out.println("Arbol Clon:"+"\n" +a.clone().toString());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Fin");
       
    }
}

