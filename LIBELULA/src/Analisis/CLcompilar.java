/*
   UNED
   Sede: Cartago 03
   Nombre del Curso: Compiladores
   Código de la asignatura: 03307
   Instrumento: Tarea 1
   Tutora: Fiorella Calvo Rojas
   Número de Grupo: 08
   Estudiante: Natalia Granados Montenegro
   Número de Cédula: 03-0515-0301
   I Cuatrimestre 2023
   Ceu Cartago
 */

package Analisis;

import static Analisis.CLpalabras_Reservadas.Comandos;
import static Analisis.CLpalabras_Reservadas.Palabras_ReservadaModula2;
import static Analisis.CLpalabras_Reservadas.Palabras_ReservadasLibelula;
import static Analisis.CLpalabras_Reservadas.TiposDeVariables;
import Archivos.CLadministrador_Archivos;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static Archivos.CLadministrador_Archivos.CONTENIDO_ORIGINAL;
import static Archivos.CLadministrador_Archivos.ERRORES_DURANTE_COMPILACION;
import static Archivos.CLadministrador_Archivos.contador;
import static Archivos.CLadministrador_Archivos.formato_Indice;

/**
 *
 * @author Natalia Granados
 */
public class CLcompilar {

//ARRAYLIST QUE CONTIENE DATOS DE TIPO STRING
    
    ArrayList<String> Contenido_Limpio = new ArrayList<String>();

//METODO BOOLEANO DE EXPRESION REGULAR TIPO PATRON Y MATCHER
    
    public boolean expresion_regular(String patron, String variable) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(variable);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }
    
    
    
//METODO DE TIPO VOID QUE VERIFICA LOS COMENTARIOS
    
    public void validar_Comentarios() 
    {
//ARRAYLIST DONDE SE ENCUENTRA EL CONTENIDO ORIGINAL DE TIPO STRING      
        ArrayList<String> contenidoClonado = (ArrayList<String>) CONTENIDO_ORIGINAL.clone();
//VARIABLES DECLARADAS QUE SE USAN EN EL METODO DE COMENTARIO
        
        String aux;
        aux = "";  
        boolean indicador;
        indicador = false;
        int contador;
        contador = 0;
        
//ESTRUCTURA QUE EVALUA VARIABLES DE LA INSTRUCCION
        for (String renglon : contenidoClonado) 
        {
            aux = "";
//METODO .TRIM QUE ELIMINA LOS ESPACIOS EN BLANCO DEL RENGLON           
            renglon = renglon.replaceAll("\\s+", " ").trim();
            
//OBTIENE LOS TOKENS DE LOS CARACTERES EN SU CONTENIDO
            String[] caracteres = obtener_tokens(renglon);
            int total_Comentarios = 0;
            
//INSTRUCCION DE CONTAR LA CANTIDAD DE PALABRAS PARA LOS COMENTARIOS POR LINEA   
            for (int i = 0; i < caracteres.length; i++) 
            {
                if (expresion_regular("^\\(\\*[a-zA-Z0-9].*\\*\\)$", caracteres[i])) {
                    total_Comentarios++;
                }
            }
//VERIFICA QUE EXISTAN PALABRAS DE COMENTARIO RESERVADAS           
            if (!indicador && expresion_regular("^\\(\\*[a-zA-Z0-9].*\\*\\)$", renglon)) 
            {
//VERIFICA QUE EL INICIO Y CIERRE DEL COMENTARIO SEA EN LA MISMA LINEA             
                if (!indicador && expresion_regular("^\\(\\*[a-zA-Z0-9].*\\*\\)$", renglon) && expresion_regular("^\\(\\*[a-zA-Z0-9].*\\*\\)$", renglon) && caracteres.length > 1) {
                   
//COMENTARIO DE UNA SOLA LINEA QUE INICIA Y FINALIZA                  
                } else if (!indicador && expresion_regular("^\\(\\*[a-zA-Z0-9].*\\*\\)$", renglon)) {
//COMENTARIO MULTILINEA       
                    Contenido_Limpio.add(aux);
                    indicador = !indicador;
                } else {
//text COMMENT ANTES DE LA PALABRA RESERVADA DEL COMENTARIO               
                    Contenido_Limpio.add(renglon);
                    CLdato renglonFalla = new CLdato(contador, true,CLadministrador_Archivos.formato_Indice(contador)+ "        ERROR 00200: Comentario  de tipo text Comment.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
            } else if (indicador && expresion_regular("^\\(\\*[a-zA-Z0-9].*\\*\\)$", renglon)) {     
//SE CIERRAN LOS COMENTARIOS DE FORMA CORRECTA                
                if (total_Comentarios == 1) {
     
                    indicador = !indicador;
                    Contenido_Limpio.add(aux);
                } else {
//SE CIERRAN LOS COMENTARIO DE FORMA INCORRECTA 
                    indicador = !indicador;
//SE AÑADE AL CONTENIDO ORIGINAL DEL RENGLON DE TIPO STRING
                    Contenido_Limpio.add(renglon);
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+ "        ERROR 00201: El cierre del comentario es incorrecto.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                    
                   
                }
//LINEAS DE COMENTARIOS COMENTADAS CON MULTILINEA  
            } else if (indicador) {
                Contenido_Limpio.add(aux);
//LINEA SIN COMENTARIOS                
            } else {
                Contenido_Limpio.add(renglon);
            }
            contador++;
        }
    }

//METODO QUE DIVIDE LA CADENA EN TOKENS
    public String[] obtener_tokens(String cadenas) 
    {
        String[] tokens = cadenas.split(" ");
        return tokens;
    }

//METODO QUE VERIFICA EL COMANDO BEGIN Y EL END
    public void validar_BeginEnd() 
    {
//VARIABLES USADAS EN EL METODO BEGIN Y END       
        String aux;
        aux = "";   
//SINTAXIS DE DOS PUNTOS COMO DELIMITADOR DEL RENGLON Y CONTENIDO LIMPIO
        for (String renglon : Contenido_Limpio) 
        {
            if (renglon != "") {
                aux = aux + renglon + " ";
            }
        }
//METODO DE REPLACE ALL QUE DEVULVE LA CADENA REEMPLAZA POR LOS CARACTERES EN LA COINCIDENCIA DE UNA EXPRESION REGULAR
        aux = aux.replaceAll("\\s+", " ").trim();
//LA SINTAXIS DE LA INSTRUCCION EJECUTA UN VALOR DE LA EXPRESION REGULAR
        if (!expresion_regular("^\\bBEGIN\\b", aux) || !expresion_regular("\\bEND\\b$", aux)) 
        {
            CLdato renglonFalla = new CLdato(Contenido_Limpio.size(), true,CLadministrador_Archivos.formato_Indice(contador)+ "        ERROR 00202: El BEGIN y END estan declarados de forma erronea.");
            ERRORES_DURANTE_COMPILACION.add(renglonFalla);
        }
        

    }
    
 
//METODO QUE VALIDE LAS PALABRAS DE MODULA2
    public void validar_Advertencias() 
    {     
//VARIABLES UTILIZADAS EN EL METODO VALIDAR LAS ADVERTENCIAS
        int contador;
        contador=0;
        
//LA INSTRUCCION HACE UN RECORRIDO POR LOS ELEMENTOS DEL ARRAYLIST
        for (String textual : Contenido_Limpio) 
        {
//SE OBTIENEN LOS TOKENS DE LOS ELEMENTOS QUE ENTRAN DE LECTURA
            String[] caracteres = obtener_tokens(textual);
//CICLO DE SALIDA            
            salida:     
//SE HACE UN RECORRIEDO DE LAS PALABRAS QUE CONTIENEN LA LINEA
            for (String caracter : caracteres) 
            { 
//LAS PALABRAS RESERVADAS SE RECORREN Y SE COMPARAN CON LA LISTA                
                
                for (String caracteres_Reservadas : Palabras_ReservadasLibelula) 
                {
//CADA PALABRA EN CADA LÍNEA SE COMPARA CON LAS PALABRAS RESERVADAS EN LA LISTA
                    
                    if (caracter.equals(caracteres_Reservadas)) 
                    {
                        CLdato renglonFalla = new CLdato(contador, false, "***ADVERTENCIA***: " + "(" + caracter + ")" + "la instrucción presentada no se soporta en esta versión.");
                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
//CUANDO SE ENCUENTRA UNA PALABRA RESERVADA EL BUCLE SE INTERRUMPE
                        break salida; 
                    }
                }
            }
            contador++;
        }
    }

//METODO QUE VERIFICA LA LONGITUD DE LA LINEA
    public void validar_LargodeLinea() 
    {
//VARIABLES USADAS EN EL METODO VALIDAR EL LARGO DE LA LINEA
        int contador;
        contador=0;
//INSTRUCCION QUE LIMITA A LA LINEA CON EL CONTENIDO LIMPIO       
        for (String renglon : Contenido_Limpio) 
        {
//CONDICION QUE VALIDA EL LARGO DE LA LINEA EN CARACTERES 
            if (renglon.length() > 100) 
            {
//SE PRESENTA EL ERROR QUE SOBREPASA LA CANTIDAD DE CARACTERES ESPERADA
                
                CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00203: La línea sobrepasa los 100 caractéres esperados.");
                ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                CLadministrador_Archivos.contador++;
            }
//VARIABLE QUE LLEVA LAS VECES QUE SE CUMPLE LA CONDICION           
            contador++;
        }
    }
    
//METODO QUE VERIFICA LAS PALABARAS RESERVADAS
    public void validar_PalabrasReservadas() 
    {
//VARIABLES USADAS EN EL METODO VALIDAR PALABRAS RESERVADAS 
        int contador;
        contador=0;
//INSTRUCCION QUE LIMITA A LA LINEA CON EL CONTENIDO LIMPIO       
        for (String renglon : Contenido_Limpio) 
        {
//SE OBTIENEN LOS TOKENS DE LOS ELEMENTOS QUE ENTRAN DE LECTURA
            String[] caracteres = obtener_tokens(renglon);
            
//LAS PALABRAS DE CADA LINEA SON RECORRIDAS
           for (String caracter : caracteres) 
           {
//INDICES DE LAS PALABRAS RESERVADAS
                int indice = Palabras_ReservadaModula2.indexOf(caracter);
                int indice2 = Palabras_ReservadasLibelula .indexOf(caracter.toUpperCase());
                int indice3 = Palabras_ReservadaModula2.indexOf(caracter.toLowerCase());
                
/*  PARA HACER REFERENCIA A UN ELEMENTO ESPECÍFICO EN UN ARREGLO
    SE ESPECIFICA EL NOMBRE QUE LLEVARÁ LA REFERENCIA DEL ARREGLO 
    Y EL NÚMERO DE POSICIÓN DEL ELEMENTO DE ANALISIS
*/

//ESTRUCTURA CONDICIONAL DEL INDICE DE IGUALDAD DE OPERANDOS
                if (indice != -1) 
                {
//SE TOMA UNA DECISION DEL VALOR PUESTO EN ANALISIS                    
                } else {
                    
//ESTRUCTURA CONDICIONAL DEL INDICE2 DE IGUALDAD DE OPERANDOS
                    
                    if (indice2 != -1) 
                    {
                       CLdato renglonFalla = new CLdato(contador, true,CLadministrador_Archivos.formato_Indice(contador)+ "        ERROR 00204: " + "(" + caracter + ")" + " obligatoriamente debe de ser MAYUSCULA.");
                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                    }
//ESTRUCTURA CONDICIONAL DEL INDICE3 DE IGUALDAD DE OPERANDOS

                    if (indice3 != -1) 
                    {
                      CLdato renglonFalla = new CLdato(contador, true, "        ERROR 00205: " + "(" + caracter + ")" + " obligatoriamente debe de ser minúscula.");
                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                    }
                }
            }
//VARIABLE QUE LLEVA LAS VECES QUE SE CUMPLE LA CONDICION           

            contador++;
        }
    }

//METODO PARA VERIFICAR LAS VARIABLES Y PARA VERIFICAR PUNTOS Y COMA 
    public void validar_Variables() 
    {
//VARIABLES USADAS EN EL METODO DE VERIFICACION DE VARIABLES        
        int contador;
        
        contador=0;
//INSTRUCCION DE OPERADOR IGUALITARIO DEL RENGLON Y EL CONTENIDO ORIGINAL        
        for (String renglon : Contenido_Limpio) 
        {
//CONDICION QUE IGNORA LOS RENGLONES EN BLANCO           
            if ((renglon == "") || renglon.isEmpty() || (renglon == " ")) 
            {
//SE PUEDE REEMPLAZAR EL RENGLON CON PUNTO Y COMA Y/O COMA             
            } else {
                renglon = renglon.replace(" ;", "; ");
                renglon = renglon.replace(" ,", ", ");
//SEPARAR LAS PALABRAS Y/O CARACTERES POR TOKENS                 
                StringTokenizer tokens = new StringTokenizer(renglon, " ");
//ARREGLO TIPO STRING DE INFO EN LAS LIENAS POR TOKENS               
                String[] info = obtener_tokens(renglon);
//INSTRUCCION DE LA LONGITUD DE LOS DATOS                
                if (info.length != 0) 
//SE POSICIONA LA VARIABLE EN CERO                    
                {int indice1 = TiposDeVariables.indexOf(info[0]);
                 int indice2 = 0;
//VARIABLE TOKEN DE TIPO STRING VACIA                   
                    String token;
                    
                    token="";
//INICIO DEL INDICE                    
                    if (indice1 != -1) 
                    {
//SE COMIENZA EN LA POSICION UNO YA QUE LAS PALABRAS INT-CHAR-REAL                        
                        for (int i = 1; i < info.length; i++) 
                    {
//VERIFICAMOS QUE NO ESTE VACIA LA LINEA
//CONDICION QUE IGNORA LOS RENGLONES EN BLANCO           
                       
                            if (info[i].isEmpty() || info[i] == "" || info[i] == " ") 
                            {
                            } else {
//SE EMPIENZAN A BUSCAR LAS PALABRAS RESERVADDAS
//DIVIDIR INFO EN TOKENS Y ELIMINA CARACTERES EN BLANCO
                                token = info[i].trim();
//BUSCA EL STRING INFO PARA REEMPLAZARLO CON OTRO CARACTER                                
                                token = info[i].replaceAll("\\p{Punct}", "");
//SE REVISA QUE LA VARIABLE NO TENGA RELACION CON LA PALABRA RESERVADA                               
                                if (indice2 != -1) 
                                { System.out.println();
                                    CLdato renglonFalla = new CLdato(contador, true, "        ERROR 00206: " + token + "NO ES POSIBLE QUE SEA UNA VARIABLE YA QUE SE CONSIDERA PALABRA RESERVADA.");
                                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                                }   
                                 else {
//SE VALIDA EL ULTIMO ELEMENTO QUE PASA POR LA CONSTRUCCION
                                    if (i == info.length - 1) 
                                    {
//LA VARIABLE DEBE CONTENER PUNTO Y COMA Y NO PUNTO Y COMA SEPARADO
                                        if (!(expresion_regular("^(\\s+|)((?<name>[\\da-z_]+)(\\s+|);(\\s+|))$", info[i])) && !(expresion_regular("(^(\\s+|);(\\s+|)$)", info[i]))) 
                                        {CLdato renglonFalla = new CLdato(contador, true, "     ERROR 00207: " + "(" + info[i] + ")" + "DEBE CONTENER Y TERMINAR CON PUNTO Y COMA ,NO DEBE PRESENTAR EL PUNTO Y LA COMA POR SEPARADO.");
                                            ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                                        }
                                    } else 
//LA VARIABLE DEBE CONTENER UNA COMA , NO PUNTO Y COMA , NO PUNTO Y COMA POR SEPARAD0                                    
                                    {if (!(expresion_regular("^(\\s+|)((?<name>[\\da-z_]+)(\\s+|),(\\s+|))$", info[i])) && !(expresion_regular("(^(\\s+|),(\\s+|)$)", info[i])) && !(expresion_regular("(^(\\s+|);(\\s+|)$)", info[i]))) 
                                    {CLdato renglonFalla = new CLdato(contador, true, "        ERROR 00208: " + "(" + info[i] + ")" + "DEBE CONTENER Y TERMINAR CON COMA O CON UNA SOLA COMA.");
                                            ERRORES_DURANTE_COMPILACION.add(renglonFalla);   
                                        }
                                        
                                    }
//LAS VARIABLES NO DEBEN PRESENTAR COMAS GUINDANDO O SUELTAS
//VERIFICAR LAS COMAS                                    
                                    if ((expresion_regular("(^(\\s+|),(\\s+|)$)", info[i]))) 
//NO TIENE COMAS QUE SOBREN                                        
                                    {if (((info.length - 2) % 0 == 1)) 
                                    {              
                                    } else 
                                    {CLdato renglonFalla = new CLdato(contador, true, "        ERROR 00209: " + "(" + info[i] + ")" + "NO DEBE CONTENER COMAS SUELTAS O GUINDANDO.");
                                            ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                                    }    
                                    }   
                                    
//LAS VARIABLES NO DEBEN PRESENTAR PUNTOS Y COMAS SUELTAS
                                    if ((expresion_regular("(^(\\s+|);(\\s+|)$)", info[i]))) 
                                    { 
                                        CLdato renglonFalla = new CLdato(contador, true, "        ERROR 00210: " + "(" + info[i] + ")" + "NO DEBE CONTENER PUNTOS Y/O COMAS SUELTAS O GUINDANDO.");
                                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                                    }
//LAS VARIABLES NO DEBEN PRESENTAR GUION BAJO _                                       
                                    if ((expresion_regular("(^(_)([\\da-z_]+))", info[i]))) 
                                    {  CLdato renglonFalla = new CLdato(contador, true, "        ERROR 00211: " + "(" + info[i] + ")" + "NO DEBE INICIAR CON GUION BAJO _.");
                                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                                    }  
//LAS VARIABLES NO DEBEN FINALIZAR CON EL CARACTER GUION BEJO _                                   
                                    if ((expresion_regular("(([\\da-z_]+)(_)$)", info[i]))) 
                                    {CLdato renglonFalla = new CLdato(contador, true,  CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00212: " + "(" + info[i] + ")" + "NO DEBE FINALIZAR CON GUION BAJO_.");
                                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                                        
//                                    
                                    }   
                                }
                            }
                        }
                    }
                }
            }
            
//VARIABLE QUE CUENTA LAS VECES DE LA CONDICION            
            contador++;
        }
    }

//METODO QUE VERIFICA LAS MULTILINEAS
    public void validar_Multilineas() 
    {  
        
     
//VARIABLES QUE SE NECESITAN EN EL METODO DE VALIDAR MULTILINEAS
        
        int contador ;
        contador=0;
      
        int COINCIDENCIA_COMANDOS;
        COINCIDENCIA_COMANDOS = 0;

//CONDICION DE LINEA EN EL TEXTO QUE CUANDO SE LEA SEA DISTINTO A NULL
        for (String renglon : Contenido_Limpio) 
        {
//INDICA LAS VECES QUE OCURRE UNA CONDICION
            contador++;
//LOS TOKENS DE LAS LINEA QUE PASAN POR LA LECRURA            
            String[] caracteres = obtener_tokens(renglon); 
//INSTRUCCION QUE TRABAJA CON CARACTERES DE STRING           
            for (String caracter : caracteres) 
//INICIA EL RECORRIDO DEL ARREGLO DE LAS PALABRAS DEFINIDAS            
            {
//COMIENZA LA ESTRUCTURA DE CONDICION DE TIPO COMANDOS EN EL ARREGLO COMANDOS
                for (String COMANDOS : Comandos) 
                {
//COMPARACION DE DOS STRINGS CON EQUALS DE COMANDOS                     
                    if (caracter.equals(COMANDOS)) 
                    {
                        COINCIDENCIA_COMANDOS=0;
                    }
                }
//LA INSTRUCCION SE EJECUTA SI EL COMANDO: IF ES IGUAL AL TOKEN Y TIENE THEN  
              
                if (caracter.equals("IF") && renglon.contains("THEN")) 
//SI EL CARACTER SE COMPARA CON IF  Y CONIIENE THEN              
                {
                    
//SE DA LA INSTRUCCION DE QUE NO DEBE LLEVAR PUNTO Y COMA
                    if (renglon.contains(";")) 
                    {
                        CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00213: " + "La siguiente instrucción  (" + caracter + ")" + " NO puede contener el punto y coma.");
                        ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                    }
                }

//SI EL TOKEN SE IGUALA AL COMANDO DO               
                if (renglon.contains("DO") && !renglon.equals("DO"))
//SE CREA UN ERROR EN LA LINEA DE COMANDO DEL PUNTO Y COMA Y DEMAS  
                {
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+ "        ERROR 00214: " + "La línea , el comando (" + caracter + ")" + " NO puede contener punto y coma o algo adicional.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
//SE DA LA INSTRUCCION DE VALIDAR QUE EL COMANDO BEGIN NO CONTENGA UN PUNTO Y COMA Y ALGO MAS

                if (renglon.contains("BEGIN") && !renglon.equals("BEGIN")) 
                {
                   
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00215: " + "El comando (" + caracter + ")" + " NO puede llevar punto y coma o algo adicional.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
//SE DA LA INSTRUCCION DE VALIDAR QUE EL COMANDO RETURN CONTENGA UN PUNTO Y COMA              
                if (caracter.trim().startsWith("RETURN") && !renglon.trim().endsWith(";")) 
                {
                   
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00218: " + "El comando (" + caracter + ")" + " debe terminar con punto y coma.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
//SE DA LA INSTRUCCION DE VALIDAR QUE EL COMANDO REPEAT CONTENGA UN PUNTO Y COMA              
                if (caracter.trim().startsWith("REPEAT") && renglon.trim().endsWith(";")) 
                {
                   
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00219: " + "El comando (" + caracter + ")" + " no debe terminar con punto y coma.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
//SE DA LA INSTRUCCION DE VALIDAR QUE EL COMANDO UNTIL CONTENGA UN PUNTO Y COMA              
                if (caracter.trim().startsWith("UNTIL") && !renglon.trim().endsWith(";")) 
                {
                   
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"        ERROR 00220: " + "El comando (" + caracter + ")" + " debe terminar con punto y coma.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
//SE DA LA INSTRUCCION DE VALIDAR QUE SI SE LOCALIZA EL COMANDO END ESTE NO CONTENGA PUNTO Y COMA Y ALGO ADICIONAL
                if (renglon.contains("END") && !renglon.equals("END")) 
                {
                    CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+ "       ERROR 00216: " + "El comando (" + caracter + ")" + " NO puede contener punto y coma o algo adicional.");
                    ERRORES_DURANTE_COMPILACION.add(renglonFalla);
                }
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
//SE SA LA INSTRUCCION DE VARIOS ERRORES DE LA LINEA CON CONTADOR Y COMANDOS          
            if (COINCIDENCIA_COMANDOS >=2) 
            {
                CLdato renglonFalla = new CLdato(contador, true, CLadministrador_Archivos.formato_Indice(contador)+"ERROR 00217, en la línea " + contador + " hay " + COINCIDENCIA_COMANDOS + " COMANDDOS QUE SE REPITEN");
                ERRORES_DURANTE_COMPILACION.add(renglonFalla);
            }
            COINCIDENCIA_COMANDOS =0;
        }
    }
    
    
    public void analizador(){
        this.validar_Comentarios();
        this.validar_BeginEnd();
        this.validar_Advertencias();
        this.validar_LargodeLinea();
        this.validar_PalabrasReservadas();
        this.validar_Variables();
        this.validar_Multilineas();
    }

}
