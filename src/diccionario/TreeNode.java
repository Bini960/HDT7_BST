package diccionario;

/**
 * Clase para los nodos de un Arbol Binario.
 * Adaptada y basada en los conceptos teoricos de:
 * Bailey, D. A. (2007). Java Structures (7th ed.). Capitulo 12: Trees.
 *
 * @param <E> El tipo de dato que guardara el nodo (Elemento generico)
 */
public class TreeNode<E> {
    
    private E value;
    private TreeNode<E> left;
    private TreeNode<E> right;

    /**
     * Construir un nuevo nodo hoja (sin hijos) con un valor especifico.
     * * @param value El dato a almacenar en el nodo
     */
    public TreeNode(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Obtener el valor almacenado en este nodo.
     * * @return El valor actual del nodo
     */
    public E getValue() {
        return value;
    }

    /**
     * Modificar el valor almacenado en este nodo.
     * * @param value El nuevo valor a asignar
     */
    public void setValue(E value) {
        this.value = value;
    }

    /**
     * Obtener el hijo izquierdo de este nodo.
     * * @return El nodo hijo izquierdo (puede ser null)
     */
    public TreeNode<E> getLeft() {
        return left;
    }

    /**
     * Asignar un nodo como hijo izquierdo.
     * * @param left El nodo que sera el nuevo hijo izquierdo
     */
    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    /**
     * Obtener el hijo derecho de este nodo.
     * * @return El nodo hijo derecho (puede ser null)
     */
    public TreeNode<E> getRight() {
        return right;
    }

    /**
     * Asignar un nodo como hijo derecho.
     * * @param right El nodo que sera el nuevo hijo derecho
     */
    public void setRight(TreeNode<E> right) {
        this.right = right;
    }
}