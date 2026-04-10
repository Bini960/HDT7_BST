package diccionario;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * Lee los archivos de texto, construye el arbol binario de busqueda,
 * imprime el diccionario en orden y traduce un texto midiendo el tiempo de ejecucion.
 */
public class TraductorMain {

    /**
     * Normalizar y limpiar una palabra para garantizar la asimetria en la busqueda.
     * Separa las marcas diacriticas (tildes, eñes, cedillas), las elimina y conserva solo letras.
     * * @param palabra La palabra original a limpiar
     * @return La palabra normalizada en minusculas
     */
    public static String limpiarPalabra(String palabra) {
        if (palabra == null) return "";
        
        // 1. Separar las letras base de sus marcas diacriticas
        String textoNormalizado = Normalizer.normalize(palabra, Normalizer.Form.NFD);
        
        // 2. Eliminar todas las marcas diacriticas (el patron \\p{M} atrapa los acentos flotantes)
        String sinAcentos = textoNormalizado.replaceAll("\\p{M}", "");
        
        // 3. Eliminar signos de puntuacion y pasar a minusculas con seguridad
        return sinAcentos.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    public static void main(String[] args) {
        
        // Instanciar el arbol binario para almacenar objetos Association
        BinarySearchTree<Association<String, String>> diccionario = new BinarySearchTree<>();
        Scanner teclado = new Scanner(System.in);

        // CONFIGURACION DE ENTORNO 
        System.out.println("-------------------------------------");
        System.out.print("¿Desea ejecutar en Modo Estres para VisualVM? (s/n): ");
        String opcion = teclado.nextLine().trim().toLowerCase();
        int iteraciones = opcion.equals("s") ? 500000 : 1;
        System.out.println("-------------------------------------\n");

        // 1. Carga de datos desde diccionario.txt
        try {
            File archivoDiccionario = new File("diccionario.txt");
            // Implementar codificacion UTF-8 para garantizar la lectura correcta del archivo
            Scanner lector = new Scanner(archivoDiccionario, "UTF-8");
            
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                
                // Limpiar caracteres innecesarios y separar por coma
                linea = linea.replace("(", "").replace(")", "").trim();
                String[] partes = linea.split(",");
                
                if (partes.length == 2) {
                    // Normalizar la llave en frances para guardarla plana en el arbol
                    String frances = limpiarPalabra(partes[0].trim());
                    // Mantener el valor en español con su ortografia original para la impresion visual
                    String espanol = partes[1].trim().toLowerCase();
                    
                    // Insertar la asociacion en el arbol
                    diccionario.insert(new Association<>(frances, espanol));
                }
            }
            lector.close(); // Liberar recursos de memoria
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo diccionario.txt.");
            teclado.close();
            return; 
        }

        // 2. Imprimir el diccionario ordenado (In-Order)
        System.out.println("--- Diccionario ordenado In-Order ---");
        diccionario.inOrder();

        // 3. Traduccion y Profiling
        System.out.println("\n=== Traduccion del texto ===");
        
        try {
            // Iniciar cronometro para medir el rendimiento
            long tiempoInicio = System.nanoTime();

            // Ejecutar la cantidad de iteraciones seleccionadas por el usuario
            for (int i = 0; i < iteraciones; i++) {
                File archivoTexto = new File("texto.txt");
                // Especificar UTF-8 para la lectura del texto a traducir
                Scanner lectorTexto = new Scanner(archivoTexto, "UTF-8");
                
                while (lectorTexto.hasNextLine()) {
                    String linea = lectorTexto.nextLine();
                    String[] palabras = linea.split(" ");
                    
                    for (String palabra : palabras) {
                        // Aplicar la normalizacion a cada palabra leida del texto
                        String palabraLimpia = limpiarPalabra(palabra);
                        
                        if (!palabraLimpia.isEmpty()) {
                            Association<String, String> busqueda = new Association<>(palabraLimpia, null);
                            Association<String, String> resultado = diccionario.search(busqueda);
                            
                            // Omitir impresiones grandes en consola durante el modo estres (con visualVM)
                            if (i == 0) {
                                if (resultado != null) {
                                    System.out.print(resultado.getValue() + " ");
                                } else {
                                    System.out.print("*" + palabra + "* ");
                                }
                            }
                        }
                    }
                    if (i == 0) System.out.println();
                }
                lectorTexto.close(); // Liberar recursos del archivo
            }
            
            long tiempoFin = System.nanoTime();
            long duracion = tiempoFin - tiempoInicio;
            
            System.out.println("\n-------------------------------------");
            if (iteraciones == 1) {
                System.out.println("Tiempo de busqueda y traduccion: " + duracion + " nanosegundos");
            } else {
                System.out.println("Tiempo total bajo estres (" + iteraciones + " repeticiones): " + duracion + " nanosegundos");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo texto.txt.");
        }

        teclado.close(); // Liberar el recurso del teclado
    }
}