package diccionario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Clase principal del programa.
 * Lee los archivos de texto, construye el arbol binario de busqueda,
 * imprime el diccionario en orden y traduce un texto midiendo el tiempo de ejecucion.
 */
public class TraductorMain {

    public static void main(String[] args) {
        
        // Instanciar el arbol binario para almacenar objetos Association
        BinarySearchTree<Association<String, String>> diccionario = new BinarySearchTree<>();

        // 1. Carga de datos desde diccionario.txt
        try {
            File archivoDiccionario = new File("diccionario.txt");
            Scanner lector = new Scanner(archivoDiccionario);
            
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                
                // Limpiar caracteres innecesarios y separar por coma
                linea = linea.replace("(", "").replace(")", "").trim();
                String[] partes = linea.split(",");
                
                if (partes.length == 2) {
                    String frances = partes[0].trim().toLowerCase();
                    String espanol = partes[1].trim().toLowerCase();
                    
                    // Insertar la asociacion en el arbol
                    diccionario.insert(new Association<>(frances, espanol));
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo diccionario.txt.");
            return; 
        }

        // 2. Imprimir el diccionario ordenado (Recorrido In-Order)
        System.out.println("--- Diccionario ordenado In-Order ---");
        diccionario.inOrder();
        System.out.println("-------------------------------------\n");

        // 3. Traduccion y Profiling
        System.out.println("=== Traduccion del texto ===");
        try {
            File archivoTexto = new File("texto.txt");
            Scanner lectorTexto = new Scanner(archivoTexto);
            
            // Iniciar cronometro para el profiling
            long tiempoInicio = System.nanoTime();
            
            while (lectorTexto.hasNextLine()) {
                String linea = lectorTexto.nextLine();
                String[] palabras = linea.split(" ");
                
                for (String palabra : palabras) {
                    // Limpiar signos de puntuacion para evaluar unicamente letras
                    String palabraLimpia = palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    
                    if (!palabraLimpia.isEmpty()) {
                        // Crear asociacion temporal requerida para la busqueda
                        Association<String, String> busqueda = new Association<>(palabraLimpia, null);
                        Association<String, String> resultado = diccionario.search(busqueda);
                        
                        if (resultado != null) {
                            // Imprimir la traduccion obtenida del arbol
                            System.out.print(resultado.getValue() + " ");
                        } else {
                            // Imprimir la palabra original rodeada de asteriscos al no encontrar coincidencia
                            System.out.print("*" + palabra + "* ");
                        }
                    }
                }
                System.out.println(); // Mantener el formato de parrafos del archivo original
            }
            lectorTexto.close();
            
            // Finalizar cronometro y calcular el tiempo total
            long tiempoFin = System.nanoTime();
            long duracion = tiempoFin - tiempoInicio;
            
            System.out.println("\n-------------------------------------");
            System.out.println("Tiempo de busqueda y traduccion: " + duracion + " nanosegundos");
            
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo texto.txt.");
        }
    }
}