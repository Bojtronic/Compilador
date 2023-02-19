package analisis;



import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexico {

    
ArrayList<String> lista_OriginalArchivo = new ArrayList<String>();
ArrayList<String> lista_infolimpio = new ArrayList<String>();   
ArrayList<Dato> arregloConFallas = new ArrayList<Dato>(); 

 // LEXEMAS Y TOKENS   
//Léxico: errores de lexemas esperados y que no se encuentran o que son inválidos:
//ejemplo: QeadInt ( MaxAnos ); es error porque QeadInt no es un comando válido ni es palabra reservada de MODULA2.
    
//**********************************************************    
//eliminacion de espacios en blanco y comentarios
    
    public void revisar_comentarios() {
        ArrayList<String> contenidoOriginal = new ArrayList<String>();
        contenidoOriginal = (ArrayList<String>) lista_OriginalArchivo.clone();

        boolean bandera = false;
        String aux = "";
        int contador = 0;

        for (String linea : contenidoOriginal) {
            aux = "";
            linea = linea.replaceAll("\\s+", " ").trim();
            String[] palabras = dividirCadenaTokens(linea);
            int numeroComentarios = 0;

            // Conteo de cantidad de palabra reservada para comentario en una línea.
            for (int i = 0; i < palabras.length; i++) {
                if (regex("\\bCOMMENT\\b\\w*|\\bCO\\b\\w*|#\\w*", palabras[i])) {
                    numeroComentarios++;
                }
            }

            // Valida que haya palabra reservada de comentario.
            if (!bandera && regex("\\bCOMMENT\\b\\w*|\\bCO\\b\\w*|#\\w*", linea)) {
                // Valida que el comentario inicie y cierre en la misma línea.
                // Inicia un comentario de una única línea.
                if (!bandera && regex("^\\bCOMMENT\\b|^\\bCO\\b|^[#]", linea) && regex("\\bCOMMENT\\b$|\\bCO\\b$|[#]$", linea) && palabras.length > 1) {
                    // Inicia y termina un comentario de una única línea.
                    lista_infolimpio.add(aux);
                } else if (!bandera && regex("^\\bCOMMENT\\b|^\\bCO\\b|^[#]", linea)) {
                    // Inicia un comentario multilínea.
                    lista_infolimpio.add(aux);
                    bandera = !bandera;
                } else {
                    // Una palabra reservada de comentario tiene texto antes, ej: text COMMENT.
                    lista_infolimpio.add(linea);
                    Dato lineaError = new Dato(contador, true, "Error 00010: Comentario con texto antecedente.");
                    arregloConFallas.add(lineaError);
                }
            } else if (bandera && regex("\\bCOMMENT\\b$|\\bCO\\b$|[#]$", linea)) {
                if (numeroComentarios == 1) {
                    // Cierre correcto de comentario. 
                    bandera = !bandera;
                    lista_infolimpio.add(aux);
                } else {
                    // Cierre incorrecto de comentario. 
                    bandera = !bandera;
                    lista_infolimpio.add(linea);
                    Dato lineaError = new Dato(contador, true, "Error 00010: Mal cierre de comentario.");
                    arregloConFallas.add(lineaError);
                }
            } else if (bandera) {
                // Línea comentada con comentario de múltiple línea. 
                lista_infolimpio.add(aux);
            } else {
                // Línea regular sin comentario. 
                lista_infolimpio.add(linea);
            }
            contador++;
        }
        
   
    

    private boolean regex(String patrones, String variable) {
        Pattern pattern = Pattern.compile(patrones);
        Matcher matcher = pattern.matcher(variable);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;

    }

    //Método que divide una cadena en tokens
    public String[] dividirCadenaTokens(String cadena) {
        String[] tokens = cadena.split(" ");
        return tokens;
    }
}

  
//funcion elimina espacios en blanco 
// procesamiento, o sea que no hay que hacer nada con ellas, pero se debe respetar su
//presencia y mostrarlas en el archivo de errores.    

    
    
    
    
//identificadores y palabras clave   
    
//Tener una l ongitud máxima de 20 caracteres.
//Son sensibles a mayúsculas y minúsculas.
//Empezar con una letra.
//Solo tener letras o números.
//No usar otros caracteres especiales.
// No corresponder a una palabra reservada de LIBELULA o de MODULA2.
//    
    
  
//contruyen los lexemas que constituyen identificadores  
    
    
// reconoce las constantes  
//    tipos de constantes: caracter, texto, enteras y reales.
    

