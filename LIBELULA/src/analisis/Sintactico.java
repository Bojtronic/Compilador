package analisis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;

public class Sintactico {
    
    
    public static String nombreValido = "";
    
    ArrayList<Dato> arregloConFallas = new ArrayList<Dato>(); 
    
    //Array  para imprimir en file_Archivo de errores
    ArrayList<String> lista_ContienelasFallas = new ArrayList<String>();
    ArrayList<String> lista_OriginalArchivo = new ArrayList<String>();
    ArrayList<String> lista_infolimpio = new ArrayList<String>();


   

    
// Sintáctico: lexemas mal escritos según los patrones de definición:
//ejemplo: ReadInt ( 1MaxAnos ); es error porque el identificador empieza con número.
    
// ********************************************************************************  
    
    //  Indicar los errores de forma clara y precisa 
//Aclarar el tipo de error y su localización
//Recuperarse del error para seguir examinando la entrada
 
//Los caracteres o componentes léxicos o tokens recibidos por A. Léxico se agrupan en frases gramaticales 
//        de forma que tomen un significado para ser interpretados y generar un árbol sintáctico.    
   
//   no vamos a verificar la precedencia de operadores ni si la expresión aritmética es válida ya que ese es trabajo del analizador sintáctico

//Método para validar si hay algún error_Linea registrado. 
    public boolean verificar_Errores() {
        for (Dato linea : arregloConFallas) {
            if (linea.falla_Linea) {
                return linea.falla_Linea;
            }
        }
        return false;
    }

    //Método para crear el archivo
    public void crea_Archivo() {
        File archivoErrores = new File(nombreValido.substring(0, nombreValido.indexOf(".")) + "-errores.txt");
        try {
            FileWriter guarda = new FileWriter(archivoErrores);
            for (String linea : lista_ContienelasFallas) {
                guarda.write(linea + "\n");
            }
            guarda.close();
        } catch (Exception e) {
        }
    }

    //Método para abrir el archivo
    public void abrirArchivocpconErrores() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("notepad " + Paths.get("").toAbsolutePath().toString() + "\\" + nombreValido.substring(0, nombreValido.indexOf(".")) + "-errores.txt");
        } catch (IOException e) {
            System.err.println("\n¡Atención se ha presentado un error!: " + e);
        }
    }
    
    
    public void MuestraDatos() {
        int contador = 0;
        System.out.println("Nombre de archivo: " + nombreValido);
        //imprimimos en pantalla arreglo original
        System.out.println("\nContenido Original");
        for (String miLinea : lista_OriginalArchivo) {
            System.out.println(formatoIndice(contador) + " " + miLinea);
            contador++;
        }
        //imprimimos en pantalla arreglo con errores
        System.out.println("\nErrores encontrados:");
        for (Dato lineaError : arregloConFallas) {
            System.out.println(formatoIndice(lineaError.getNumero_Linea()) + " " + lineaError.getInformacion_Linea());
        }

        //imprimimos en pantalla arreglo con errores
        System.out.println("\n\nCombinación de Contenido Original y Errores:");
        contador = 0;
        for (String miLinea : lista_OriginalArchivo) {
            System.out.println(formatoIndice(contador) + " " + miLinea);
            lista_ContienelasFallas.add(formatoIndice(contador) + " " + miLinea);
            //mandamos a buscar la linea por el indice
            for (int i = 0; i < arregloConFallas.size(); i++) {
                if (contador != lista_ContienelasFallas.get(i).numero_Linea) {
                } else {
                    //encontro un error_Linea, imprimalo
                    //si tiene muchos errores esa linea imprimiria todos los que encuentre
                    System.out.println(lista_ContienelasFallas.get(i).info_Linea);
                    lista_ContienelasFallas.add(arregloConFallas.get(i).info_Linea);
                }
            }
            contador++;
        }
    }
    
  
    //Formato con ceros para número de índice
    public static String formatoIndice(int num) {
        Formatter obj = new Formatter();
        return String.valueOf(obj.format("%05d", num));
    }
    
    
    
    
    
}
