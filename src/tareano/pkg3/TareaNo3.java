/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tareano.pkg3;

/**
 *
 * @author MARLON RAMIREZ 
 */
public class TareaNo3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //grado minimo del Arbol B
        //Cada nodo soporta 2 hijos y 2 grado-1 claves
        int grado = 3;
        //Se crea el arbol B segun t
        ArbolB arbolB = new ArbolB(grado);
        
        //Valores a ingresar primera
        int[] valoresUno = {20, 10, 50, 30, 40};
        System.out.println("-- INICIO --");
        System.out.println("INSERTANDO VALORES AL ARBOL B");
        for(int i=0; i<valoresUno.length; i++) {
            System.out.println("Insertando... valor " + valoresUno[i]);
            arbolB.insertar(valoresUno[i]);
        }
        
        //Mostrando arbol B por pantalla
        System.out.println("ESTADO ARBOL B");
        arbolB.EstadoActual();
        System.out.println("");
        
        //Valores a ingresar nuevos
        System.out.println("Insertando... valor 60 y 45");
        arbolB.insertar(60);
        arbolB.insertar(45);
        //Mostrando arbol 
        System.out.println("ESTADO ARBOL B");
        arbolB.EstadoActual();
        System.out.println("");
        // acualizar estado del arbol
        System.out.println("ESTADO ARBOL B");
        arbolB.EstadoActual();
        System.out.println("");

        //Buscar un nodo o llave
        System.out.println("\nBuscando el nodo con el valor 80 en el arbol B:");
        
        arbolB.buscarNodoPorClave(80);
        System.out.println("\nBuscando el nodo con el valor 60 en el arbol B:");
        arbolB.buscarNodoPorClave(60);
        // elminar un nodo o llave 
        System.out.println("\nElminar una llave o nodo ");
        arbolB.eliminar(19);
        arbolB.eliminar(40);
        System.out.println("\nEl nuevo Arbol es el siguiente: ");
        arbolB.EstadoActual();
        
        System.out.println("");
        System.out.println("-- FIN --");
    
        
    }
    
}
