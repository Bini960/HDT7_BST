package test;
import diccionario.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de pruebas unitarias para el Arbol Binario de Busqueda.
 * Valida la correcta insercion y busqueda de elementos.
 */
public class BinarySearchTreeTest {

    @Test
    public void testInsertAndSearchExistente() {
        // 1. Preparar el entorno (Arrange)
        BinarySearchTree<Association<String, String>> arbol = new BinarySearchTree<>();
        arbol.insert(new Association<>("femme", "mujer"));
        arbol.insert(new Association<>("homme", "hombre"));

        // 2. Ejecutar la accion (Act)
        // Crear una asociacion temporal solo con la llave para buscar
        Association<String, String> busqueda = new Association<>("femme", null);
        Association<String, String> resultado = arbol.search(busqueda);

        // 3. Verificar el resultado (Assert)
        assertNotNull("El nodo no debe ser nulo si la palabra existe en el arbol.", resultado);
        assertEquals("La traduccion de 'femme' debe ser 'mujer'.", "mujer", resultado.getValue());
    }

    @Test
    public void testSearchInexistente() {
        // 1. Preparar el entorno (Arrange)
        BinarySearchTree<Association<String, String>> arbol = new BinarySearchTree<>();
        arbol.insert(new Association<>("maison", "casa"));

        // 2. Ejecutar la accion (Act)
        // Buscar una palabra que no ha sido insertada
        Association<String, String> busqueda = new Association<>("chien", null);
        Association<String, String> resultado = arbol.search(busqueda);

        // 3. Verificar el resultado (Assert)
        assertNull("El resultado debe ser estrictamente nulo al buscar una palabra inexistente.", resultado);
    }
}