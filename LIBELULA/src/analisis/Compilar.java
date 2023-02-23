package analisis;

import static analisis.Palabras_Reservadas.Comandos;
import static analisis.Palabras_Reservadas.PalabrasReservadas_ALGOL;
import static analisis.Palabras_Reservadas.PalabrasReservadas_Mayus;
import static analisis.Palabras_Reservadas.TiposDeVariables;
import static archivos.Administrador_Archivos.CONTENIDO_ORIGINAL;
import static archivos.Administrador_Archivos.ERRORES_DURANTE_COMPILACION;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Compilar {
  
  ArrayList<String> Contenido_Limpio = new ArrayList<String>();
  
  public boolean expresion_regular(String patron, String variable) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(variable);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }
  
  
  //Método validar comentarios
    public void verificar_Comentarios() {
        //ArrayList<String> contenidoCopia = new ArrayList<String>();
        ArrayList<String> contenidoClonado = (ArrayList<String>) CONTENIDO_ORIGINAL.clone();

        boolean bandera = false;
        String aux = "";
        int contador = 0;

        for (String linea : contenidoClonado) {
            aux = "";
            linea = linea.replaceAll("\\s+", " ").trim();
            String[] palabras = obtener_tokens(linea);
            int cantidadComentarios = 0;

            // Conteo de cantidad de palabra reservada para comentario en una línea.
            for (int i = 0; i < palabras.length; i++) {
                if (expresion_regular("\\bCOMMENT\\b\\w*|\\bCO\\b\\w*|#\\w*", palabras[i])) {
                    cantidadComentarios++;
                }
            }

            // Valida que haya palabra reservada de comentario.
            if (!bandera && expresion_regular("\\bCOMMENT\\b\\w*|\\bCO\\b\\w*|#\\w*", linea)) {
                // Valida que el comentario inicie y cierre en la misma línea.
                // Inicia un comentario de una única línea.
                if (!bandera && expresion_regular("^\\bCOMMENT\\b|^\\bCO\\b|^[#]", linea) && expresion_regular("\\bCOMMENT\\b$|\\bCO\\b$|[#]$", linea) && palabras.length > 1) {
                    // Inicia y termina un comentario de una única línea.
                    Contenido_Limpio.add(aux);
                } else if (!bandera && expresion_regular("^\\bCOMMENT\\b|^\\bCO\\b|^[#]", linea)) {
                    // Inicia un comentario multilínea.
                    Contenido_Limpio.add(aux);
                    bandera = !bandera;
                } else {
                    // Una palabra reservada de comentario tiene texto antes, ej: text COMMENT.
                    Contenido_Limpio.add(linea);
                    Dato lineaError = new Dato(contador, true, "Error 00010: Comentario con texto antecedente.");
                    ERRORES_DURANTE_COMPILACION.add(lineaError);
                }
            } else if (bandera && expresion_regular("\\bCOMMENT\\b$|\\bCO\\b$|[#]$", linea)) {
                if (cantidadComentarios == 1) {
                    // Cierre correcto de comentario. 
                    bandera = !bandera;
                    Contenido_Limpio.add(aux);
                } else {
                    // Cierre incorrecto de comentario. 
                    bandera = !bandera;
                    Contenido_Limpio.add(linea);
                    Dato lineaError = new Dato(contador, true, "Error 00010: Mal cierre de comentario.");
                    ERRORES_DURANTE_COMPILACION.add(lineaError);
                }
            } else if (bandera) {
                // Línea comentada con comentario de múltiple línea. 
                Contenido_Limpio.add(aux);
            } else {
                // Línea regular sin comentario. 
                Contenido_Limpio.add(linea);
            }
            contador++;
        }
    }
    
    //Método que divide una cadena en tokens
    public String[] obtener_tokens(String cadena) {
        String[] tokens = cadena.split(" ");
        return tokens;
    }
    
    
    //Método validar BEGIN y END
    public void verificar_BeginEnd() {
        String aux = "";
        for (String linea : Contenido_Limpio) {
            if (linea != "") {
                aux = aux + linea + " ";
            }
        }
        aux = aux.replaceAll("\\s+", " ").trim();

        if (!expresion_regular("^\\bBEGIN\\b", aux) || !expresion_regular("\\bEND\\b$", aux)) {
            Dato lineaError = new Dato(Contenido_Limpio.size(), true, "Error 00101: Declaración erronea de BEGIN y END.");
            ERRORES_DURANTE_COMPILACION.add(lineaError);
        }
    }
    
    
    //Método para validar las palabras reservadas de ALGOL
    public void verificar_Advertencias() {
        int contador = 0;

        //Se recorre los elementos del ArrayList
        for (String texto : Contenido_Limpio) {
            String[] palabras = obtener_tokens(texto); //Para obtener los token del elemento que se esta leyendo
            out:
            for (String palabra : palabras) { //Se recorren las palabras que tienen cada linea
                //Se recorren las palabras reservadas que están en la lista para comparar
                for (String palabraReservada : PalabrasReservadas_ALGOL) {
                    //Se compara la cada palabra de cada linea con las palabras reservadas que están en la lista
                    if (palabra.equals(palabraReservada)) {
                       Dato lineaError = new Dato(contador, false, "Advertencia: " + "(" + palabra + ")" + " instrucción no es soportada por esta versión.");
                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                        break out; //Se rompe el ciclo al encontrar una palabra reservada
                    }
                }
            }
            contador++;
        }
    }
    
    
    //Método para verificar el largo de una línea
    public void verificar_LargoLinea() {
        int contador = 0;
        for (String linea : Contenido_Limpio) {
            if (linea.length() > 80) {
                Dato lineaError = new Dato(contador, true, "Error 00010: Línea excede cantidad máxima de caracteres.");
                ERRORES_DURANTE_COMPILACION.add(lineaError);
            }
            contador++;
        }
    }
    
    
    
    //Método para validación de palabras reservadas. 
    public void verificar_PalabrasReservadas() {
        int contador = 0;
        for (String linea : Contenido_Limpio) {
            String[] palabras = obtener_tokens(linea); //Para obtener los token del elemento que se esta leyendo
            for (String palabra : palabras) { //Se recorren las palabras que tienen cada linea
                //Indices
                int indice = PalabrasReservadas_ALGOL.indexOf(palabra);
                int indice2 = PalabrasReservadas_Mayus.indexOf(palabra.toUpperCase());
                int indice3 = PalabrasReservadas_ALGOL.indexOf(palabra.toLowerCase());

                if (indice != -1) {
                } else {
                    if (indice2 != -1) {
                       Dato lineaError = new Dato(contador, true, "Error 00010: " + "(" + palabra + ")" + " debe de ir en mayúscula.");
                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                    }
                    if (indice3 != -1) {
                      Dato lineaError = new Dato(contador, true, "Error 00011: " + "(" + palabra + ")" + " debe de ir en minúscula.");
                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                    }
                }
            }
            contador++;
        }
    }
    
    
    
    //Método para validación de variables y puntos y comas. 
    public void verificar_Variables() {
        int contador = 0;
        for (String linea : Contenido_Limpio) {
            if ((linea == "") || linea.isEmpty() || (linea == " ")) {
                //ignorar lineas en blanco
            } else {
                linea = linea.replace(" ;", "; ");
                linea = linea.replace(" ,", ", ");
                //tokenizar palabras
                StringTokenizer tokens = new StringTokenizer(linea, " ");
                String[] datos = obtener_tokens(linea);
                if (datos.length != 0) {
                    int indice1 = TiposDeVariables.indexOf(datos[0]); //la variable debe iniciar en posicion 0
                    int indice2 = 0;
                    String token = "";
                    if (indice1 != -1) {
                        for (int i = 1; i < datos.length; i++) { //iniciamos en 1 porque no ocupamos revisar INT|CHAR|REAL
                            //validamos que no sea linea vacia
                            if (datos[i].isEmpty() || datos[i] == "" || datos[i] == " ") {
                                //ignorar
                            } else {
                                //busqueda palabra reservada
                                token = datos[i].trim();
                                token = datos[i].replaceAll("\\p{Punct}", "");
                                indice2 = PalabrasReservadas_ALGOL.indexOf(token); //verificamos que no sea una variable que tenga formato de palabra reservada
                                if (indice2 != -1) {
                                    System.out.println();
                                    Dato lineaError = new Dato(contador, true, "Error 00101: " + token + " no puede ser variable porque es una palabra reservada.");
                                    ERRORES_DURANTE_COMPILACION.add(lineaError);
                                } else {
                                    //validamos el ultimo elemento:
                                    if (i == datos.length - 1) {
                                        //que sea variable con punto y coma y que no sea punto y coma suelto
                                        if (!(expresion_regular("^(\\s+|)((?<name>[\\da-z_]+)(\\s+|);(\\s+|))$", datos[i])) && !(expresion_regular("(^(\\s+|);(\\s+|)$)", datos[i]))) {
                                            Dato lineaError = new Dato(contador, true, "Error 00090: " + "(" + datos[i] + ")" + " debe terminar con punto y coma o debe tener un único punto y coma.");
                                            ERRORES_DURANTE_COMPILACION.add(lineaError);
                                        }
                                    } else {
                                        //que sea variable con coma, que no sea variable con punto y coma, que no sea coma suelta ni punto y coma suelto
                                        if (!(expresion_regular("^(\\s+|)((?<name>[\\da-z_]+)(\\s+|),(\\s+|))$", datos[i])) && !(expresion_regular("(^(\\s+|),(\\s+|)$)", datos[i])) && !(expresion_regular("(^(\\s+|);(\\s+|)$)", datos[i]))) {
                                            Dato lineaError = new Dato(contador, true, "Error 00091: " + "(" + datos[i] + ")" + " debe terminar con coma o debe tener una única coma.");
                                            ERRORES_DURANTE_COMPILACION.add(lineaError);
                                        }
                                    }
                                    
                                    //verificar comas sueltas
                                    if ((expresion_regular("(^(\\s+|),(\\s+|)$)", datos[i]))) {
                                        if (((datos.length - 2) % 0 == 1)) {
                                            //no tiene comas de más                   
                                        } else {
                                           Dato lineaError = new Dato(contador, true, "Error 00092: " + "(" + datos[i] + ")" + " no debe tener comas guindando.");
                                            ERRORES_DURANTE_COMPILACION.add(lineaError);
                                        }
                                    }
                                    
                                    //verificar puntos y comas sueltos
                                    if ((expresion_regular("(^(\\s+|);(\\s+|)$)", datos[i]))) {
                                        Dato lineaError = new Dato(contador, true, "Error 00093: " + "(" + datos[i] + ")" + " no debe tener puntos y comas guindando.");
                                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                                    }
                                    
                                    //verificar que no inicie CON _
                                    if ((expresion_regular("(^(_)([\\da-z_]+))", datos[i]))) {
                                        Dato lineaError = new Dato(contador, true, "Error 00094: " + "(" + datos[i] + ")" + " no debe iniciar con guión bajo.");
                                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                                    }
                                    
                                    //verificar que no termine con _
                                    if ((expresion_regular("(([\\da-z_]+)(_)$)", datos[i]))) {
                                     Dato lineaError = new Dato(contador, true, "Error 00095: " + "(" + datos[i] + ")" + " no debe terminar con guión bajo.");
                                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            contador++;
        }
    }
    
    
    
    
    
    
    //Método para validar Multilineas
    public void verificar_Multilineas() {
        int contador = 0;
        int comandos_encontrados = 0;

        //Mientras que la línea de texto que se va leyendo sea diferente de null
        for (String linea : Contenido_Limpio) {
            contador++;
            String[] palabras = obtener_tokens(linea); //Para obtener los token de la linea que se esta leyendo
            for (String palabra : palabras) {
                //Se recorre el arreglo de palabras
                for (String comandos : Comandos) {
                    if (palabra.equals(comandos)) {
                        comandos_encontrados++;
                    }
                }
                
                //Si el token es igual al COMANDO IF y lleva THEN + ;
                if (palabra.equals("IF") && linea.contains("THEN")) {
                    if (linea.contains(";")) {
                      Dato lineaError = new Dato(contador, false, "Error: " + "La instrucción  (" + palabra + ")" + " no debe llevar punto y coma.");
                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                    }
                }
                
                //Si el token es igual al COMANDO GOTO O ELSE y lleva ;
                if (palabra.contains("GOTO") || palabra.contains("ELSE")) {
                    if (linea.contains(";")) {
                     Dato lineaError = new Dato(contador, false, "Error: " + "El comando (" + palabra + ")" + " no debe llevar punto y coma.");
                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                    }
                }
                
                //Si el token es igual al COMANDO OD o FI y no lleva ;
                if (palabra.contains("OD") || palabra.contains("FI")) {
                    if (!linea.contains(";")) {
                       Dato lineaError = new Dato(contador, false, "Error: " + "El comando (" + palabra + ")" + " debe llevar punto y coma al final.");
                        ERRORES_DURANTE_COMPILACION.add(lineaError);
                    }
                }
                
                //Si el token es igual al COMANDO DO
                if (palabra.equals("DO") && !linea.equals("DO")) {
                 Dato lineaError = new Dato(contador, false, "Error: " + "En la línea el comando (" + palabra + ")" + " no debe llevar punto y coma o algo adicional.");
                    ERRORES_DURANTE_COMPILACION.add(lineaError);
                }
                
                //VALIDAMOS QUE SI SE ENCUENTRA AL COMANDO BEGIN NO DEBE CONTENER ; O ALGO ADICIONAL
                if (palabra.equals("BEGIN") && !linea.equals("BEGIN")) {
                  Dato lineaError = new Dato(contador, false, "Error: " + "En la línea el comando (" + palabra + ")" + " no debe llevar punto y coma o algo adicional.");
                    ERRORES_DURANTE_COMPILACION.add(lineaError);
                }
                
                //VALIDAMOS QUE SI SE ENCUENTRA AL COMANDO END NO DEBE CONTENER ; O ALGO ADICIONAL
                if (palabra.equals("END") && !linea.equals("END")) {
                   Dato lineaError = new Dato(contador, false, "Error: " + "En la línea el comando (" + palabra + ")" + " no debe llevar punto y coma o algo adicional.");
                    ERRORES_DURANTE_COMPILACION.add(lineaError);
                }
            }
            if (comandos_encontrados >= 2) {
                Dato lineaError = new Dato(contador, true, "Error, en la línea " + contador + " hay " + comandos_encontrados + " comandos repetidos");
                ERRORES_DURANTE_COMPILACION.add(lineaError);
            }
            comandos_encontrados = 0;
        }
    }
    
    
    
    
    
    //Método que ejecuta el compilador 
    public void invocarCompiladorAlgol(String nombreValidado, String ruta) {
        String nombreAlgol = nombreValidado.replace(".MINGOL", ".a68");
        String pathFile = ruta + "\\" + nombreAlgol; //Se le agrega a la ruta el nombre del archivo a abrir
        String rutaOriginal = ruta + "\\" + nombreValidado;
        Path copied = Paths.get(pathFile);
        
        Path originalPath = Paths.get(rutaOriginal);
        
        try {
            Files.copy(originalPath, copied, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (Exception e) {
        }

        String comando = ruta + "\\" + "a68g.exe " + pathFile; //Se guarda la ruta con el .exe que se va a abrir tomado en cuenta la ruta con el nombre del archivo
        try {
            //Se guarda el comando que se ejecutará en el cmd
            String[] comandoCmd = {"cmd.exe", "/c", "start", "cmd.exe", "/k", comando};
            //Se le pasa el comando del cmd al constructor de procesos
            ProcessBuilder pBuilder = new ProcessBuilder(comandoCmd);
            //Se crea un proceso para abrir el archivo recibido por parametro
            Process proceso = pBuilder.start();
        } catch (IOException e) {
            System.out.println("Se ha producido un error." + e);
        }
    }
    
    
    
}
