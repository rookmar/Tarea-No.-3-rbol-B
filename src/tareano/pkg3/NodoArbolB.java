/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareano.pkg3;

/**
 *
 * @author MARLON RAMIREZ
 */
public class NodoArbolB {
    int n; //numero de claves almacenadas en el nodo
    boolean leaf; //Si el nodo es hoja (nodo hoja=true; nodo interno=false)
    int llave[];  //almacena las claves en el nodo
    NodoArbolB hijo[]; //arreglo con referencias a los hijos

    //Constructores
    public NodoArbolB(int t) {
        n = 0;
        leaf = true;
        llave = new int[((2 * t) - 1)];
        hijo = new NodoArbolB[(2 * t)];
    }

    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                System.out.print(llave[i] + " | ");
            } else {
                System.out.print(llave[i]);
            }
        }
        System.out.print("]");
    }

    public int find(int k) {
        for (int i = 0; i < n; i++) {
            if (llave[i] == k) {
                return i;
            }
        }
        return -1;
    }
}
