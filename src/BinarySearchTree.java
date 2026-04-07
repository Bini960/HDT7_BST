/**
 * Implementacion del Arbol Binario de Busqueda.
 * Adaptada y basada en los conceptos teoricos de:
 * Bailey, D. A. (2007). Java Structures (7th ed.). Capitulo 12: Trees.
 *
 * @param <E> El tipo de dato que guardara el arbol (debe ser Comparable)
 */
public class BinarySearchTree<E extends Comparable<E>> {
    
    // La raiz es el punto de entrada a todo el arbol
    private TreeNode<E> root;

    //Construir un arbol binario vacio.
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Insertar un nuevo elemento en el arbol binario manteniendo el orden.
     * Metodo publico que llama al metodo recursivo privado.
     * * @param value El elemento a insertar
     */
    public void insert(E value) {
        root = insertRecursive(root, value);
    }

    /**
     * Metodo recursivo interno para encontrar la posicion correcta e insertar el nodo.
     * * @param current El nodo actual que se esta evaluando
     * @param value El valor a insertar
     * @return El nodo actual (modificado si se inserto un hijo)
     */
    private TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        // Caso base: si se llega a un espacio vacio, aqui va el nuevo nodo
        if (current == null) {
            return new TreeNode<>(value);
        }

        // Se compara el valor a insertar con el valor del nodo actual
        int comparison = value.compareTo(current.getValue());

        if (comparison < 0) {
            // Si el valor es menor, nos vamos por la rama izquierda
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (comparison > 0) {
            // Si el valor es mayor, nos vamos por la rama derecha
            current.setRight(insertRecursive(current.getRight(), value));
        } else {
            // Si la comparacion es 0, significa que la llave ya existe.
            // En un diccionario, si la palabra ya existe, actualizamos su traduccion (valor).
            current.setValue(value);
        }

        return current;
    }
}

