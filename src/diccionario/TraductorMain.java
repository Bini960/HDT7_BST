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
        System.out.println("=== Traduccion del texto con Profiling ===");
            try {
                // Iniciar cronometro para el profiling
                long tiempoInicio = System.nanoTime();

                // Ejecutar la traduccion 500,000 veces para mantener el proceso activo y medir el tiempo total
                for (int i = 0; i < 500000; i++) {
                    File archivoTexto = new File("texto.txt");
                    Scanner lectorTexto = new Scanner(archivoTexto);
                    
                    while (lectorTexto.hasNextLine()) {
                        String linea = lectorTexto.nextLine();
                        String[] palabras = linea.split(" ");
                        
                        for (String palabra : palabras) {
                            String palabraLimpia = palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
                            
                            if (!palabraLimpia.isEmpty()) {
                                Association<String, String> busqueda = new Association<>(palabraLimpia, null);
                                Association<String, String> resultado = diccionario.search(busqueda);
                                
                                // Omitir impresiones en consola durante el estres para evitar saturacion
                                if (i == 0 && resultado != null) {
                                    System.out.print(resultado.getValue() + " ");
                                } else if (i == 0) {
                                    System.out.print("*" + palabra + "* ");
                                }
                            }
                        }
                        if (i == 0) System.out.println();
                    }
                    lectorTexto.close();
                }
                
                long tiempoFin = System.nanoTime();
                long duracion = tiempoFin - tiempoInicio;
                
                System.out.println("\n-------------------------------------");
                System.out.println("Tiempo total: " + duracion + " nanosegundos");
                
            } catch (FileNotFoundException e) {
                System.out.println("No se encontro el archivo texto.txt.");
            }
    }
}