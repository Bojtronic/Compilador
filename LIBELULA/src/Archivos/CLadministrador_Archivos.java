/*
   UNED
   Sede: Cartago 03
   Nombre del Curso:  Compiladores
   Código de la asignatura: 03307
   Instrumento: Tarea 1
   Tutora: Elluany Fiorella Calvo Rojas
   Número de Grupo: 08
   Estudiante: Natalia Granados Montenegro
   Número de Cédula: 03-0515-0301
   I Cuatrimestre 2023
   Ceu Cartago
 */
package Archivos;

import Analisis.CLcompilar;
import Analisis.CLdato;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author Natalia Granados
 */
//CLASE DE TIPO PUBLICA DE ADMINISTRADOR DE ARCHIVOS
public class CLadministrador_Archivos {
//LLAMADO DE LA CLAE CL COMPILAR     

    CLcompilar compilar = new CLcompilar();
//SE LLENA EL ARRAYLIST DE LOS DATOS DEL ARREGLO DE INFORMACION RECIBIDA  
    ArrayList<CLdato> INFORMACION_RECIBIDA = new ArrayList<CLdato>();
//SE LLENA EL ARRAYLIST DE LA CLASE DATOS CON LOS ERRORES DE LA COMPILACION
    public static ArrayList<CLdato> ERRORES_DURANTE_COMPILACION = new ArrayList<CLdato>();
//SE LLENA LOS ARRAYLIST DE TIPO STRING DEL CONTENIDO ORIGINAL
    public static ArrayList<String> CONTENIDO_ORIGINAL = new ArrayList<String>();
//UN ARRAYLIST QUE CONTIENE LOS ERRORES DE TIPO STRING
    ArrayList<String> CONTENIDO_CON_ERRORES = new ArrayList<String>();

//CLASE DE TIPO PUBLICA QUE VERIRICA EL NOMBRE DEL ARCHIVO   
    public static String nombreVerificado = "";
//SE DECLARAN LAS VARIABLES USADAS EN ESTA CLASE DE TIPO ARCHIVO  
    String nombre_archivo_verificado = "";
//VARIABLE USADA EN EL SISTEMA OPERATIVO DESDE LA LINA DE COMANDOS O TERMINAL
    String path = "";

//SETTERS QUE DA VALOR A LA PROPIEDAD QUE CONTIENE CON LA PALABRA PRIVATE 
    private void setNombreArchivo(String nombre_archivo) {
        this.nombre_archivo_verificado = nombre_archivo;
    }
//SETTERS QUE DA VALOR A LA PROPIEDAD QUE CONTIENE CON LA PALABRA PRIVATE  

    private void setPath(String ruta) {
        this.path = ruta;
    }

//METODO VOID QUE DEVUELVE UN VALOR DEL NOMBRE DEL ARCHIVO
    public void leerArchivo(String nombre_archivo) {
//SET QUE DA VALOR A LA PROPIEDAD DEL NOMBRE DEL ARCHIVO
        setNombreArchivo(nombre_archivo);
//FICHERO O DIRECTORIO DE LOS ARCHIVOS
        File archivo = null;
        FileReader fr = null;
//LEE EL TEEXTO DEL ARCHIVO
        BufferedReader br = null;
//COPIA EL ARCHIVO ORIGINAL Y LO QUE CONTIENE LO ANALIZA
        copiar_archivo_original(nombre_archivo);
        analiza_Archivo();

    }
// METODO VOID DE CREACION DE ARCHIVO RECIBE EL ARGUMENTO DE RUTA DEFINIDA 

    public void crearArchivo(String ruta) {

//ACCESO DE LOS ATRIBUTOS DE LOS ARCHIVOS NULL        
        FileWriter fichero = null;
        PrintWriter pw = null;
//SENTENCIA LLAMADA DENTRO DEL BLOQUE CATCH        

        try {
// CLASES DE ESCRITURA Y LECTURA DEL CONTENIDO DEL ARCHIVO
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
//INICIALIZACION DE VARIABLE QUE COMIENZA EN CERO 
            for (int i = 0; i < 10; i++) {
                pw.println("Linea " + i);
            }
        } catch (IOException e) //SE IMPRIME EL MENSAJE DE LA EXCEPTION DEL CATCH      
        {
            System.out.println(e);
        } finally {
            try {
//NUEVAMENTE APROVECHAMOS EL FINALLY PARA ASEURAR QUE EL FICHERO CIERRE 
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                System.out.println(e2);
            }
        }
    }

//METODO VOID DE COPIAR EL ARCHIVO ORG   
    public void copiar_archivo_original(String nombreVerificado) {

//FICHERO O DIRECTORIO QUE ACCEDEN A LAS PROPIEDADES DE LOS ARCHIVOS     
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String textual;
        try {
            String aux = nombreVerificado;
            System.out.println(aux);
            fr = new FileReader(aux);
            //Se crea el bufferedReader
            br = new BufferedReader(fr);
//INICIA EL RECORRIDO DEL ARCHIVO EN CADA LINEA            
            while ((textual = br.readLine()) != null) {
////EL CONTENIDO ES AÑADIDO AL ARCHIVO POR EL ARRAYLIST              
                CONTENIDO_ORIGINAL.add(textual);
            }
//LA LECTURA DEL ARCHIVO SE CIERRA           
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR DE LECTURA DE ARCHIVO." + e);
        }
    }

//METODO DE VALOR DE BOOLEANO    
    public boolean verificar_nombre(String nombre_archivo) {
//VARIABLES QUE SE USAN EN EL METODO DE VERIFICAR NOMBRE QUE RECIBE UN ARGUMENTO        
        int contador;
        contador = 1;
//ARREGLO DE OBJETOS QUE REPRESENTA UN NUMERO DE VALORES      
        String[] separarNombre = new String[10];
//VARIBLES BOOLEANAS DE VERIFICAR Y VALIDAR EL NOMBRE DEL ARCHIVO : VERIFICAR Y VALIDAR       
        boolean comprobar = true;
        boolean aprobar = true;
// INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO Y SU LONGITUD
//        if (comprobar) {
//            separarNombre = nombre_archivo.split("\\.");
//            if (separarNombre[0].length() > 20) {
//                CLdato Falta = new CLdato(contador, true, "ERROR, el nombre del archivo: sobrepasa los 20 caracteres.");
//                ERRORES_DURANTE_COMPILACION.add(Falta);
//                contador++;
//                aprobar = false;
//            }
//        }
//INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO QUE NO INICIE CON NUMERO
//        if ((comprobar = compilar.expresion_regular("^[0-9]", nombre_archivo)) == true) 
//        {CLdato Falta = new CLdato(contador, true, "ERROR, el nombre del archivo: comienza con un número.");
//         ERRORES_DURANTE_COMPILACION.add(Falta);
//         contador++;
//         aprobar = false;
//        }
//INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO QUE NO CONTENGA CARACTERES ESPECIALES
//        if ((comprobar = compilar.expresion_regular("[^\\w\\.]", nombre_archivo)) == true) 
//        {CLdato Falta = new CLdato(contador, true, "ERROR, el nombre del archivo: contiene caracteres de tipo especial.");
//         ERRORES_DURANTE_COMPILACION.add(Falta);
//         contador++;
//          aprobar = false;
//        }
//INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO QUE NO INICIE CON BARRA BAJA        
//        if ((comprobar = compilar.expresion_regular("^_", nombre_archivo)) == true) 
//        {
//            CLdato falta = new CLdato(contador, true, "ERROR, el nombre del archivo: comienza con un guion bajo _.");
//            ERRORES_DURANTE_COMPILACION.add(falta);
//            contador++;
//            aprobar = false;
//        }
//INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO Y SU EXTENSION
        if ((comprobar = compilar.expresion_regular("_$", nombre_archivo)) == true) 
        { CLdato falta = new CLdato(contador, true, "ERROR, el nombre del archivo: termina con una extension de _.");
            ERRORES_DURANTE_COMPILACION.add(falta);
            contador++;
            aprobar = false;
        }    
//INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO QUE NO CONTENGA DOS PUNTOS O MAS DE DOS PUNTOS 
        if ((comprobar = compilar.expresion_regular("\\.LID$", nombre_archivo) == true) && (comprobar = compilar.expresion_regular("\\.{2,}", nombre_archivo) == true)) 
        { CLdato falta = new CLdato(contador, true, "ERROR, el nombre del archivo: contiene dos puntos o más de dos puntos.");
            ERRORES_DURANTE_COMPILACION.add(falta);
            contador++;
            aprobar = false;
        }
//INSTRUCCION DE VALIDAR EL NOMBRE DEL ARCHIVO QUE NO INICIE CON NUMERO
        if ((comprobar = compilar.expresion_regular("\\.$", nombre_archivo)) == true) 
        {CLdato falta = new CLdato(contador, true, "ERROR, el nombre del archivo: finaliza con un punto y no contiene la extension");
            ERRORES_DURANTE_COMPILACION.add(falta);
            contador++;
            aprobar = false;
        }
        return aprobar;
    }
//LAS FUNCIONES DE LA CLASE COMPILAR

//METODO VOID DE ANALIZAR EL ARCHIVO   
    public void analiza_Archivo() {
      /*
        compilar.validar_Comentarios();
        compilar.validar_BeginEnd();
        compilar.validar_Advertencias();
        compilar.validar_LargodeLinea();
        compilar.validar_PalabrasReservadas();
        compilar.validar_Variables();
        compilar.validar_Multilineas();
      */        
        compilar.analizador();

//VALIDA QUE SE REVISEN , CREE Y MUESTRE EL ARCHIVO DE ERRORES        
        if (!ERRORES_DURANTE_COMPILACION.isEmpty()) {
//INSTRUCCION DE VERIFICAR LOS ERRORES Y LOS CREA Y LOS MUESTRE            
            if (verificar_Errores()) {
                DemostrarDatos();
                generarArchivo();
      
            } else {
                DemostrarDatos();
                generarArchivo();

            }
      
        }   
    }
    
//METODO QUE VALIDA SI EXISTE ALGUN ERROR O TIENE ERROR EN LA LINEA IDENTIFICADO   
    public boolean verificar_Errores() //OPERADOR DE IGUALDAD ENTRE LA LINEA Y LOS ERRORES DEL COMPILADOR           
    {
        for (CLdato renglon : ERRORES_DURANTE_COMPILACION) {
//SI ENCUENTRA UN ERROR EN LA LINEA QUE LO RETORNE        
            if (renglon.hasError()) {
                return renglon.hasError;
            }
        }
//RETORNA FALSO    
        return false;
    }
//METODO QUE MUESTRA LOS DATOS 
    public static int contador;
    public void DemostrarDatos() {    
        
    
//VARIABLES QUE USA EL METODO        
      
       contador = 0;
//IMPRIME EN PANTALLA EL NOMBRE DEL ARCHIVO VALIDADO        
        System.out.println("NOMBRE DEL ARCHIVO: " + nombre_archivo_verificado);
        
//SE USA EL ARREGLO QUE SE IMPRIME EN LA PANTALLA        
        System.out.println("\nCONTENIDO DE ORIGEN");
        
//INSTRUCCION DEL OPERADOR IGUALITARIO DEL CONTENIDO ORIGINAL   


        for (String miRenglon : CONTENIDO_ORIGINAL) {
            //System.out.println(miRenglon);
//           contador ++;
        }
        
//IMPRIME EN LA PANTALLA EL ARREGLO CON LOS ERRORES RESPECTIVOS 

        //System.out.println("\nFALLAS QUE SE ENCONTRARON:");
        
//INSTRUCCION DEL OPERADOR IGUALITARIO DE LA LINEA DEL ERROR

//        for (CLdato renglonFalla : ERRORES_DURANTE_COMPILACION) {
//            System.out.println(formato_Indice(renglonFalla.getLineNumber()) + " " + renglonFalla.getLineInfo());
//        }

//IMPRIME EN LA PANTALLA EL ARREGLO CON LOS ERRORES RESPECTIVOS 
//SE USA EL CONTADOR EN CERO  


        System.out.println("\n\nCONJUNTO DEL CONTENIDO DE ORIGEN Y DE ERRORES:");
        contador = 0;
//INSTRUCCION DEL OPERADOR IGUALITARIO DE LA LINEA   
        for (String renglon : CONTENIDO_ORIGINAL) {
//            System.out.println(formato_Indice(contador) + " " + renglon);
//PRESENTACION DE LOS NUMEROS EN FORMATO DE LA CONSOLA       
            CONTENIDO_CON_ERRORES.add(formato_Indice(contador) + " " + renglon);
//SE BUSCA LA LINEA POR EL INDICE SEGUN LA INSTRUCCION         
            for (int i = 0; i < ERRORES_DURANTE_COMPILACION.size(); i++) 
            {if (ERRORES_DURANTE_COMPILACION.get(i).lineNumber == contador) 
/*SI ENCUENTRA FALLA EN LINEA SE IMPRIME                
 *               
 *SI LA LINEA CONTIENE ERRORES QUE LOS IMPRIMA Y LOS MUESTRE                 
 */                  
                    {
//                        System.out.println(ERRORES_DURANTE_COMPILACION.get(i).lineInfo);
                        CONTENIDO_CON_ERRORES.add(ERRORES_DURANTE_COMPILACION.get(i).lineInfo);
                    }    
            }
            contador++;
        }
    }
    
    
    
//EL METODO QUE TRABAJA EL FORMATO DEL INDICE EN EL ARGUMENTO DE LOS NUMEROS
//CLASE STRING DE FORMATO DE ERORRES
    public static String formato_Indice(int numero) 
//INDICA EL FORMATO DE LOS NUMEROS EN EL ARGUMENTO QUE INCLUYE            
    { Formatter obj = new Formatter(); 
    return String.valueOf(obj.format("%05d", numero));}

    
    
    
    
//METODO DE CREACION DEL ARCHIVO
    public void generarArchivo()
//CONTIENE LOS ATRIBUTOS DEL ARCHIVO , INFORMACION Y LOS CREA           
    {File archivoErrores = new File(nombre_archivo_verificado.substring(0, nombre_archivo_verificado.indexOf(".")) + "-errores.txt");
//ATRIBUTOS DE GUARDADO DE LOS ERRORES DEL ARCHIVO
        try 
        {//INDICACION DE IGUALDAD DEL OPERADOR DE LA LINEA CON EL CONTENIDO DE LOS ERRORES
      try (FileWriter guardado = new FileWriter(archivoErrores)) {
//INDICACION DE IGUALDAD DEL OPERADOR DE LA LINEA CON EL CONTENIDO DE LOS ERRORES
        for (String renglon : CONTENIDO_CON_ERRORES) {
          //SE ESCRIBE TEXTO O CONTENIDO EN EL ARCHIVI
          guardado.write(renglon + "\n");
        }
//SE CIERRA EN DONDE ESTA LEYENDO EL ARCHIVO
//ATRAPA LA EXCEPCION
      }
        } catch (IOException e) {
        }
    }

}
