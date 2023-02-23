
package archivos;

import analisis.Compilar;
import analisis.Dato;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;

public class Administrador_Archivos {
    
  Compilar compilar = new Compilar();
  
  ArrayList<Dato> INFORMACION_RECIBIDA = new ArrayList<Dato>();
  public static ArrayList<Dato> ERRORES_DURANTE_COMPILACION = new ArrayList<Dato>();
  public static ArrayList<String> CONTENIDO_ORIGINAL = new ArrayList<String>();
   ArrayList<String> CONTENIDO_CON_ERRORES = new ArrayList<String>();
  
  public static String nombreVerificado = "";
  
  String nombre_archivo_verificado = "";
  String path = "";
  
  private void setNombreArchivo(String nombre_archivo){
    this.nombre_archivo_verificado = nombre_archivo;
  }
  
  private void setPath(String ruta){
    this.path = ruta;
  }
      
    public void leerArchivo(String nombre_archivo, String ruta){
      
      setNombreArchivo(nombre_archivo);
      setPath(ruta);
      
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
            
      
        copiar_archivo_original(ruta, nombre_archivo);
        analizar_archivo();

    }
    
    public void crearArchivo(String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (IOException e) {
            System.out.println(e);
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (IOException e2) {
              System.out.println(e2);
           }
        }
    }
    
    
    
    
    public void copiar_archivo_original(String pRuta, String nombreVerificado) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
        String texto;
        try {
            String aux = pRuta + "\\" + nombreVerificado;
            archivo = new File(aux); //Se le pasa la ruta al objeto File
            //Se crea el bufferedReader
            br = new BufferedReader(new FileReader(archivo));
            while ((texto = br.readLine()) != null) { //Se recorre el archivo linea por linea
                CONTENIDO_ORIGINAL.add(texto); //Se agrega el contenido del archivo a un arrayList
            }
            br.close(); //Se cierra la lectura
        } catch (IOException e) {
            System.out.println("Error al leer archivo." + e);
        }
    }
    
    
    
    public boolean verificar_nombre(String nombre_archivo){
      int contador = 1;
        String[] dividirNombre = new String[10];
        boolean verificar = true; 
        boolean valido = true;
        
        if (verificar) {
            dividirNombre = nombre_archivo.split("\\.");
            if (dividirNombre[0].length() > 20) {
                Dato miError = new Dato(contador, true, "Error, el nombre del archivo contiene más de 20 caracteres");
                ERRORES_DURANTE_COMPILACION.add(miError);
                contador++;
                valido = false; //bandera
            }
        }
        
        if ((verificar = compilar.expresion_regular("^[0-9]", nombre_archivo)) == true) {
            Dato miError = new Dato(contador, true, "Error, el nombre del archivo inicia con número");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
            //No usar otros caracteres especiales, si acepta puntos
        }
        
        if ((verificar = compilar.expresion_regular("[^\\w\\.]", nombre_archivo)) == true) {
            Dato miError = new Dato(contador, true, "Error, el nombre del archivo tiene caracteres especiales");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
            
        }
        
        if ((verificar = compilar.expresion_regular("^_", nombre_archivo)) == true) {
            Dato miError = new Dato(contador, true, "Error, el nombre del archivo empieza con un _");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
            //No terminar con guion bajo   
        }
        
        if ((verificar = compilar.expresion_regular("_$", nombre_archivo)) == true) {
            Dato miError = new Dato(contador, true, "Error, el nombre del archivo termina con un _");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
            //verificar la extension del archivo
        }
        
        if ((verificar = compilar.expresion_regular("\\.LID$", nombre_archivo) == true) && (verificar = compilar.expresion_regular("\\.{2,}", nombre_archivo) == true)) {
            Dato miError = new Dato(contador, true, "Error, el nombre del archivo tiene dos puntos o más");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
        }
        
        if ((verificar = compilar.expresion_regular("\\.$", nombre_archivo)) == true) {
            Dato miError = new Dato(contador, true, "Error, el nombre termina en punto y no tiene extension");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
        }
        
        //si tiene extension diferente de MINGOL o mingol
        if ((verificar = compilar.expresion_regular("\\.(?:lid$|LID$)", nombre_archivo) == false) && (verificar = compilar.expresion_regular("\\.(\\w+)", nombre_archivo) == true)) {
            Dato miError = new Dato(contador, true, "Error, el nombre del archivo tiene una extensión inválida");
            ERRORES_DURANTE_COMPILACION.add(miError);
            contador++;
            valido = false; //bandera
        }
        
        //si es valido y tiene extension correcta, solo lo asignamos a la variable estatica global
        if ((valido == true) && ((verificar = compilar.expresion_regular("\\.LID$", nombre_archivo)) == true)) {
            nombreVerificado = nombre_archivo;
        }
        
        //si tiene un .mingol lo pasamos a .MINGOL
        if ((verificar = compilar.expresion_regular("\\.lid$", nombre_archivo) == true)) {
            nombreVerificado = nombre_archivo.replace(".lid", ".LID");
        }
        
        //si no tiene extension y es un nombre valido, le agregamos .MINGOL
        if ((valido == true) && ((verificar = compilar.expresion_regular("\\.$", nombre_archivo)) == false) && ((verificar = compilar.expresion_regular("\\.(?:lid$|LID$)", nombre_archivo)) == false)) {
            nombreVerificado = nombre_archivo + ".LID";
        }
        
        return valido;
    }
    
    
    // funciones de clase compilar
    public void analizar_archivo() {
        compilar.verificar_Comentarios();
        compilar.verificar_BeginEnd();
        compilar.verificar_Advertencias();
        compilar.verificar_LargoLinea();
        compilar.verificar_PalabrasReservadas();
        compilar.verificar_Variables();
        compilar.verificar_Multilineas();
        
        
        if (!ERRORES_DURANTE_COMPILACION.isEmpty()) {
            if (revisar_Errores()) {
                MostrarDatos();
                crearArchivo();
                abrirArchivoErrores();
            } else {
                MostrarDatos();
                crearArchivo();
                abrirArchivoErrores();
                compilar.invocarCompiladorAlgol(nombre_archivo_verificado, this.path);
            }
        } else {
            compilar.invocarCompiladorAlgol(nombre_archivo_verificado, this.path);
        }
    }
    
    
    //Método para validar si hay algún error_Linea registrado. 
    public boolean revisar_Errores() {
        for (Dato linea : ERRORES_DURANTE_COMPILACION) {
            if (linea.hasError) {
                return linea.hasError;
            }
        }
        return false;
    }

    
    
    
    
    
    
    //Impresión de datos. 
    public void MostrarDatos() {
        int contador = 0;
        System.out.println("Nombre de archivo: " + nombre_archivo_verificado);
        //imprimimos en pantalla arreglo original
        System.out.println("\nContenido Original");
        for (String miLinea : CONTENIDO_ORIGINAL) {
            System.out.println(formatoInt(contador) + " " + miLinea);
            contador++;
        }
        //imprimimos en pantalla arreglo con errores
        System.out.println("\nErrores encontrados:");
        for (Dato lineaError : ERRORES_DURANTE_COMPILACION) {
            System.out.println(formatoInt(lineaError.getLineNumber()) + " " + lineaError.getLineInfo());
        }

        //imprimimos en pantalla arreglo con errores
        System.out.println("\n\nCombinación de Contenido Original y Errores:");
        contador = 0;
        for (String miLinea : CONTENIDO_ORIGINAL) {
            System.out.println(formatoInt(contador) + " " + miLinea);
            CONTENIDO_CON_ERRORES.add(formatoInt(contador) + " " + miLinea);
            //mandamos a buscar la linea por el indice
            for (int i = 0; i < ERRORES_DURANTE_COMPILACION.size(); i++) {
                if (ERRORES_DURANTE_COMPILACION.get(i).lineNumber == contador) {
                    //encontro un error_Linea, imprimalo
                    //si tiene muchos errores esa linea imprimiria todos los que encuentre
                    System.out.println(ERRORES_DURANTE_COMPILACION.get(i).lineInfo);
                    CONTENIDO_CON_ERRORES.add(ERRORES_DURANTE_COMPILACION.get(i).lineInfo);
                }
            }
            contador++;
        }
    }
    
    
    
    //Formato con ceros para número de índice
    public static String formatoInt(int num) {
        Formatter obj = new Formatter();
        return String.valueOf(obj.format("%05d", num));
    }
    
    
    
    
    //Método para crear el archivo
    public void crearArchivo() {
        File archivoErrores = new File(nombre_archivo_verificado.substring(0, nombre_archivo_verificado.indexOf(".")) + "-errores.txt");
        try {
            FileWriter guardar = new FileWriter(archivoErrores);
            for (String linea : CONTENIDO_CON_ERRORES) {
                guardar.write(linea + "\n");
            }
            guardar.close();
        } catch (Exception e) {
        }
    }
    
    
    
    //Método para abrir el archivo
    public void abrirArchivoErrores() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("notepad " + Paths.get("").toAbsolutePath().toString() + "\\" + nombre_archivo_verificado.substring(0, nombre_archivo_verificado.indexOf(".")) + "-errores.txt");
        } catch (IOException e) {
            System.err.println("\n¡Se ha presentado un error!: " + e);
        }
    }
        
}
