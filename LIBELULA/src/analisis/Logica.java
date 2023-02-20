/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Natalia Granados
 */
public class Logica {
    
// LEXEMAS Y TOKENS   
//Léxico: errores de lexemas esperados y que no se encuentran o que son inválidos:
//ejemplo: QeadInt ( MaxAnos ); es error porque QeadInt no es un comando válido ni es palabra reservada de MODULA2.
//basarse en el codigo de karla y variarlo 

//recordar que se debe probar las funciones en el archivo CalcInflac.LID por lo que se debe declarar variables para abrir el archivo y validar el nombre como tal
        
public void inicio_Validaciones() {

revisar_Comentarios();
revisar_LargodeLinea();
revisar_BeginEnd();
RevisarPalabras_Reservadas();        
//verificar_Advertencias();
//        
//        verificar_Variables();
//        verificar_Multilineas();
//        if (!arregloConErrores.isEmpty()) {
//            if (revisar_Errores()) {
//                MostrarDatos();
//                crearArchivo();
//                abrirArchivoErrores();
//            } else {
//                MostrarDatos();
//                crearArchivo();
//                abrirArchivoErrores();
//                invocarCompiladorAlgol();
//            }
//        } else {
//            invocarCompiladorAlgol(); no se imvoca ya que es desde el cmd
//        }
//


}

public void revisar_Comentarios() {}

// AGREGAR CONTADOR 
// ARRAYLIST 


ArrayList<String> lista_OriginalArchivo = new ArrayList<String>();
ArrayList<String> lista_infolimpio = new ArrayList<String>();   
ArrayList<Dato> arregloConFallas = new ArrayList<Dato>(); 

//    contenidoOriginal  = (ArrayList<String>) lista_OriginalArchivo.clone();
//
//    boolean bandera = false;
//    String aux = "";
//    int contador = 0;
//
//    for (String linea : contenidoOriginal
//
//    
//        ) {
//            aux = "";
//        linea = linea.replaceAll("\\s+", " ").trim();
//        String[] palabras = dividirCadenaTokens(linea);
//        int numeroComentarios = 0;
//
//        // Conteo de cantidad de palabra reservada para comentario en una línea.
//        for (int i = 0; i < palabras.length; i++) {
//            if (regex("\\bCOMMENT\\b\\w*|\\bCO\\b\\w*|#\\w*", palabras[i])) {
//                numeroComentarios++;
//            }
//        }
//
//        // Valida que haya palabra reservada de comentario.
//        if (!bandera && regex("\\bCOMMENT\\b\\w*|\\bCO\\b\\w*|#\\w*", linea)) {
//            // Valida que el comentario inicie y cierre en la misma línea.
//            // Inicia un comentario de una única línea.
//            if (!bandera && regex("^\\bCOMMENT\\b|^\\bCO\\b|^[#]", linea) && regex("\\bCOMMENT\\b$|\\bCO\\b$|[#]$", linea) && palabras.length > 1) {
//                // Inicia y termina un comentario de una única línea.
//                lista_infolimpio.add(aux);
//            } else if (!bandera && regex("^\\bCOMMENT\\b|^\\bCO\\b|^[#]", linea)) {
//                // Inicia un comentario multilínea.
//                lista_infolimpio.add(aux);
//                bandera = !bandera;
//            } else {
//                // Una palabra reservada de comentario tiene texto antes, ej: text COMMENT.
//                lista_infolimpio.add(linea);
//                Dato lineaError = new Dato(contador, true, "Error 00010: Comentario con texto antecedente.");
//                arregloConFallas.add(lineaError);
//            }
//        } else if (bandera && regex("\\bCOMMENT\\b$|\\bCO\\b$|[#]$", linea)) {
//            if (numeroComentarios == 1) {
//                // Cierre correcto de comentario. 
//                bandera = !bandera;
//                lista_infolimpio.add(aux);
//            } else {
//                // Cierre incorrecto de comentario. 
//                bandera = !bandera;
//                lista_infolimpio.add(linea);
//                Dato lineaError = new Dato(contador, true, "Error 00010: Mal cierre de comentario.");
//                arregloConFallas.add(lineaError);
//            }
//        } else if (bandera) {
//            // Línea comentada con comentario de múltiple línea. 
//            lista_infolimpio.add(aux);
//        } else {
//            // Línea regular sin comentario. 
//            lista_infolimpio.add(linea);
//        }
    public void revisar_LargodeLinea() {
        // AGREGAR CONTADOR
        // considerar que la lineaError se usa casi en todo el codigo
//        int contador = 0;
//        for (String linea : lista_ContenidoLimpio) {
//            if (linea.length() > 80) {
//                Datos lineaError = new Datos(contador, true, "Error 00010: Línea excede cantidad máxima de caracteres.");
//                arregloConErrores.add(lineaError);
//            }
//            contador++;
//        }
        
        
    }
 
 public void revisar_BeginEnd(){
 
  //usar .trimp
     
     
//     String aux = "";
//     for (String linea : lista_ContenidoLimpio) {
//         if (linea != "") {
//             aux = aux + linea + " ";
//         }
//     }
//     aux = aux.replaceAll("\\s+", " ").trim();
//
//     if (!regex("^\\bBEGIN\\b", aux) || !regex("\\bEND\\b$", aux)) {
//         Datos lineaError = new Datos(lista_ContenidoLimpio.size(), true, "Error 00101: Declaración erronea de BEGIN y END.");
//         arregloConErrores.add(lineaError);
//     }
//

 
 
 }
 
    
   public void RevisarPalabras_Reservadas(){
   
   //AGREGAR CONTADOR
   //Arraylist de las diferentes palabras
   
//    int contador = 0;
//        for (String linea : lista_ContenidoLimpio) {
//            String[] palabras = dividirCadenaTokens(linea); //Para obtener los token del elemento que se esta leyendo
//            for (String palabra : palabras) { //Se recorren las palabras que tienen cada linea
//                //Indices
//                int indice = lista_PalabrasReservadas.indexOf(palabra);
//                int indice2 = lista_PalabrasReservadasMayus.indexOf(palabra.toUpperCase());
//                int indice3 = lista_PalabrasReservadas.indexOf(palabra.toLowerCase());
//
//                if (indice != -1) {
//                } else {
//                    if (indice2 != -1) {
//                       Datos lineaError = new Datos(contador, true, "Error 00010: " + "(" + palabra + ")" + " debe de ir en mayúscula.");
//                        arregloConErrores.add(lineaError);
//                    }
//                    if (indice3 != -1) {
//                      Datos lineaError = new Datos(contador, true, "Error 00011: " + "(" + palabra + ")" + " debe de ir en minúscula.");
//                        arregloConErrores.add(lineaError);
//                    }
//                }
//            }
//            contador++;
//        }
   
   
   
   } 
    
    // metodo para guardar el archivo y almacenar la info en un arraylist
   //definir varibles del nombre valido del archivo
    // getter y setter de archivo
     public void guardarLecturaArchivo(String pRuta) {
//        String texto;
//        try {
//            String aux = pRuta + "\\" + nombreValidado;
//            archivo = new File(aux); //Se le pasa la ruta al objeto File
//            //Se crea el bufferedReader
//            archivoLeer = new BufferedReader(new FileReader(archivo));
//            while ((texto = archivoLeer.readLine()) != null) { //Se recorre el archivo linea por linea
//                lista_ContenidoOriginal.add(texto); //Se agrega el contenido del archivo a un arrayList
//            }
//            archivoLeer.close(); //Se cierra la lectura
//        } catch (IOException e) {
//            System.out.println("Error al leer archivo." + e);
//        }
    }
   
   
   
   
    
 
 //metodos que se usan para los comentarios
    private boolean regex(String patron, String variable) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(variable);

        return false;
    }

   
//Metodo token
    public String[] dividir_CadenadeTokens() {
        return null;
    }


}




 