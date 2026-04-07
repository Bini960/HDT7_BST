package diccionario;

/**
 * Implementacion de Key-Value (K,V).
 * Adaptada y basada en los conceptos teoricos de:
 * Bailey, D. A. (2007). Java Structures (7th ed.). Capitulo 12: Trees.
 */
public class Association<K extends Comparable<K>, V> implements Comparable<Association<K, V>> {
    
    private K key;
    private V value;

    /**
     * Construir una nueva asociacion con su llave y valor correspondientes.
     * * @param key   La llave de busqueda (la palabra en frances)
     * @param value El valor asociado (la traduccion al espanol)
     */
    public Association(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Obtener la llave de la asociacion.
     * * @return La llave actual
     */
    public K getKey() {
        return key;
    }

    /**
     * Obtener el valor de la asociacion.
     * * @return El valor actual
     */
    public V getValue() {
        return value;
    }

    /**
     * Modificar el valor de la asociacion.
     * * @param value El nuevo valor a asignar
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Comparar esta asociacion contra otra basandose en la llave.
     * Esto es necesario para que el arbol binario sepa como ordenar los nodos.
     * * @param other La otra asociacion a comparar
     * @return un numero negativo, cero, o positivo segun el orden alfabetico
     */
    @Override
    public int compareTo(Association<K, V> other) {
        // Delegamos la comparacion al metodo compareTo de la llave (K)
        return this.key.compareTo(other.getKey());
    }
}