/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareano.pkg3;

/**
 *
 * @author MARLON RAMIREZ 
 */
public class ArbolB {
    NodoArbolB root;
    int grado;

    //Constructor
    public ArbolB(int t) {
        this.grado = t;
        root = new NodoArbolB(t);
    }

    public int buscarClaveMayor() {
        int claveMaxima = getClaveMayor(this.root);

        return claveMaxima;
    }

    private int getClaveMayor(NodoArbolB current) {
        if (current == null) {
            return 0;//Si es cero no existe
        }

        //Mientras no sea una hoja
        while (!current.leaf) {
            //Se accede al hijo mas a la derecha
            current = current.hijo[current.n];
        }

        return claveMayorPorNodo(current);
    }

    private int claveMayorPorNodo(NodoArbolB current) {
        //Devuelve el valor mayor, el que esta mas a la derecha
        return current.llave[current.n - 1];
    }

    public void mostrarClavesNodoMinimo() {
        NodoArbolB temp = buscarNodoMinimo(root);

        if (temp == null) {
            System.out.println("Sin minimo");
        } else {
            temp.imprimir();
        }
    }

    public NodoArbolB buscarNodoMinimo(NodoArbolB nodoActual) {
        if (root == null) {
            return null;
        }

        NodoArbolB aux = root;

        //Mientras no sea una hoja
        while (!aux.leaf) {
            //Se accede al primer hijo
            aux = aux.hijo[0];
        }

        //Devuelve el nodo menor, el que esta mas a la izquierda
        return aux;
    }

    //Busca el valor ingresado y muestra el contenido del nodo que contiene el valor
    public void buscarNodoPorClave(int num) {
        NodoArbolB temp = buscar(root, num);

        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            System.out.println("El nodo o clave es ");
            print(temp);
            
        }
    }

    //Search
    private NodoArbolB buscar(NodoArbolB actual, int llave3) {
        int i = 0;//se empieza a buscar siempre en la primera posicion

        //Incrementa el indice mientras el valor de la clave del nodo sea menor
        while (i < actual.n && llave3 > actual.llave[i]) {
            i++;
        }

        //Si la clave es igual, entonces retornamos el nodo
        if (i < actual.n && llave3 == actual.llave[i]) {
            return actual;
        }

        //Si llegamos hasta aqui, entonces hay que buscar los hijos
        //Se revisa primero si tiene hijos
        if (actual.leaf) {
            return null;
        } else {
            //Si tiene hijos, hace una llamada recursiva
            return buscar(actual.hijo[i], llave3);
        }
    }

    public void insertar(int llave2) {
        NodoArbolB aux2 = root;

        //Si el nodo esta lleno lo debe separar antes de insertar
        if (aux2.n == ((2 * grado) - 1)) {
            NodoArbolB s = new NodoArbolB(grado);
            root = s;
            s.leaf = false;
            s.n = 0;
            s.hijo[0] = aux2;
            split(s, 0, aux2);
            nonFullInsert(s, llave2);
        } else {
            nonFullInsert(aux2, llave2);
        }
    }
    

    private void split(NodoArbolB x, int i, NodoArbolB y) {
        //Nodo temporal que sera el hijo i + 1 de x
        NodoArbolB z = new NodoArbolB(grado);
        z.leaf = y.leaf;
        z.n = (grado - 1);

        //Copia las ultimas (t - 1) claves del nodo y al inicio del nodo z      
        for (int j = 0; j < (grado - 1); j++) {
            z.llave[j] = y.llave[(j + grado)];
        }

        //Si no es hoja hay que reasignar los nodos hijos
        if (!y.leaf) {
            for (int k = 0; k < grado; k++) {
                z.hijo[k] = y.hijo[(k + grado)];
            }
        }

        //nuevo tamanio de y                                                   
        y.n = (grado - 1);                                                         
                                                                               
        //Mueve los hijos de x para darle espacio a z
        for (int j = x.n; j > i; j--) {
            x.hijo[(j + 1)] = x.hijo[j];
        }
        //Reasigna el hijo (i+1) de x                                           
        x.hijo[(i + 1)] = z;                                                   
                                                                                
        //Mueve las claves de x
        for (int j = x.n; j > i; j--) {
            x.llave[(j + 1)] = x.llave[j];
        }

        //Agrega la clave situada en la mediana                                 
        x.llave[i] = y.llave[(grado - 1)];                                              
        x.n++;                                                                  
    }

    private void nonFullInsert(NodoArbolB x, int key) {
        //Si es una hoja
        if (x.leaf) {
            int i = x.n; //cantidad de valores del nodo
            //busca la posicion i donde asignar el valor
            while (i >= 1 && key < x.llave[i - 1]) {
                x.llave[i] = x.llave[i - 1];//Desplaza los valores mayores a key
                i--;
            }

            x.llave[i] = key;//asigna el valor al nodo
            x.n++; //aumenta la cantidad de elementos del nodo
        } else {
            int j = 0;
            //Busca la posicion del hijo
            while (j < x.n && key > x.llave[j]) {
                j++;
            }

            //Si el nodo hijo esta lleno lo separa
            if (x.hijo[j].n == (2 * grado - 1)) {
                split(x, j, x.hijo[j]);

                if (key > x.llave[j]) {
                    j++;
                }
            }

            nonFullInsert(x.hijo[j], key);
        }
    }

    public void EstadoActual() {
        print(root);
    }

    //Print en preorder
    private void print(NodoArbolB n) {
        n.imprimir();

        //Si no es hoja
        if (!n.leaf) {
            //recorre los nodos para saber si tiene hijos
            for (int j = 0; j <= n.n; j++) {
                if (n.hijo[j] != null) {
                    System.out.println();
                    print(n.hijo[j]);
                }
            }
        }
    }
    
    public void eliminar(int llave3){
        if(root == null){
            System.out.println("El arbol esta vacio ");
            return;
        }
        root = eliminar(root, llave3);
    }
    
    private NodoArbolB eliminar (NodoArbolB z, int llave3){
        
        int aux3 = finLlave(z, llave3);
        
        if (aux3 < z.n && z.llave[aux3] == llave3){
            if(z.leaf){
                removeFromLeaf(z, aux3);
            } else{
                removeFromNonLeaf(z, aux3);
        }
     }else{
        if(z.leaf){
                System.out.println("la clave " + llave3 + " no esta presente en el arbol ");
                return z;
            }
        
        boolean flag = (aux3 == z.n);
        
        if(z.hijo[aux3].n < grado){
            fill(z, aux3);
        }
        
        if(flag && aux3 > z.n ){
            eliminar(z.hijo[aux3 - 1], llave3);
        } else {
            eliminar(z.hijo[aux3], llave3);
            System.out.println("\nLa lleve fue eliminada ");
        }
        }
        return  z;
    }
         
    private int finLlave(NodoArbolB node, int key) {
    int idx = 0;
    while (idx < node.n && node.llave[idx] < key) {
        idx++;
    }
    return idx;
}

private void removeFromLeaf(NodoArbolB node, int idx) {
    for (int i = idx + 1; i < node.n; ++i) {
        node.llave[i - 1] = node.llave[i];
    }
    node.n--;
}

private void removeFromNonLeaf(NodoArbolB node, int idx) {
    int key = node.llave[idx];

    if (node.hijo[idx].n >= grado) {
        int pred = getPred(node, idx);
        node.llave[idx] = pred;
        eliminar(node.hijo[idx], pred);
    } else if (node.hijo[idx + 1].n >= grado) {
        int succ = getSucc(node, idx);
        node.llave[idx] = succ;
        eliminar(node.hijo[idx + 1], succ);
    } else {
        merge(node, idx);
        eliminar(node.hijo[idx], key);
    }
}

private int getPred(NodoArbolB node, int idx) {
    NodoArbolB curr = node.hijo[idx];
    while (!curr.leaf) {
        curr = curr.hijo[curr.n];
    }
    return curr.llave[curr.n - 1];
}

private int getSucc(NodoArbolB node, int idx) {
    NodoArbolB curr = node.hijo[idx + 1];
    while (!curr.leaf) {
        curr = curr.hijo[0];
    }
    return curr.llave[0];
}

private void fill(NodoArbolB node, int idx) {
    if (idx != 0 && node.hijo[idx - 1].n >= grado) {
        borrowFromPrev(node, idx);
    } else if (idx != node.n && node.hijo[idx + 1].n >= grado) {
        borrowFromNext(node, idx);
    } else {
        if (idx != node.n) {
            merge(node, idx);
        } else {
            merge(node, idx - 1);
        }
    }
}

private void borrowFromPrev(NodoArbolB node, int idx) {
    NodoArbolB child = node.hijo[idx];
    NodoArbolB sibling = node.hijo[idx - 1];

    for (int i = child.n - 1; i >= 0; --i) {
        child.llave[i + 1] = child.llave[i];
    }

    if (!child.leaf) {
        for (int i = child.n; i >= 0; --i) {
            child.hijo[i + 1] = child.hijo[i];
        }
    }

    child.llave[0] = node.llave[idx - 1];

    if (!child.leaf) {
        child.hijo[0] = sibling.hijo[sibling.n];
    }

    node.llave[idx - 1] = sibling.llave[sibling.n - 1];

    child.n += 1;
    sibling.n -= 1;
}

private void borrowFromNext(NodoArbolB node, int idx) {
    NodoArbolB child = node.hijo[idx];
    NodoArbolB sibling = node.hijo[idx + 1];

    child.llave[child.n] = node.llave[idx];

    if (!child.leaf) {
        child.hijo[child.n + 1] = sibling.hijo[0];
    }

    node.llave[idx] = sibling.llave[0];

    for (int i = 1; i < sibling.n; ++i) {
        sibling.llave[i - 1] = sibling.llave[i];
    }

    if (!sibling.leaf) {
        for (int i = 1; i <= sibling.n; ++i) {
            sibling.hijo[i - 1] = sibling.hijo[i];
        }
    }

    child.n += 1;
    sibling.n -= 1;
}

private void merge(NodoArbolB node, int idx) {
    NodoArbolB child = node.hijo[idx];
    NodoArbolB sibling = node.hijo[idx + 1];

    child.llave[grado - 1] = node.llave[idx];

    for (int i = 0; i < sibling.n; ++i) {
        child.llave[i + grado] = sibling.llave[i];
    }

    if (!child.leaf) {
        for (int i = 0; i <= sibling.n; ++i) {
            child.hijo[i + grado] = sibling.hijo[i];
        }
    }

    for (int i = idx + 1; i < node.n; ++i) {
        node.llave[i - 1] = node.llave[i];
    }

    for (int i = idx + 2; i <= node.n; ++i) {
        node.hijo[i - 1] = node.hijo[i];
    }

    child.n += sibling.n + 1;
    node.n--;
}
    
}
