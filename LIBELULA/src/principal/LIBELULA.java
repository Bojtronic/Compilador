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
package Principal;

import Analisis.CLcompilar;
import Archivos.CLadministrador_Archivos;
import java.io.FileNotFoundException;

/**
 *
 * @author Natalia Granados
 */

//CLASE PUBLICA TIPO LIBELULA DEL NOMBRE DEL ENTREGABLE 
public class LIBELULA {

//CLASE ESTATICA DEL MAIN DE LA RUTA , ENTRADA DEL PROGRAMA QUE SE EJECUTA
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
//INSTANCIA DE LA CLASE ADMINISTRADOR DE LOS ARCHIVOS        
        CLadministrador_Archivos admin_archivo = new CLadministrador_Archivos();
//VARIBEL TIPO BOOLEANO DEVUELVE TRUE PARA VERIFICAR SI EL NOMBRE ESTA CORRECTO    
        boolean nombre_correcto;
        nombre_correcto = true;

        System.out.println("INDICADOR: ");
//SINO SE PASA EL ARCHIVO ES CERO        
        if (args.length < 1) {
            System.out.println("ERROR NO HAY NINGUN ARCHIVO AGREGADO");
        } else {
            String nombre_Archivo = args[0];
//INSTANCIA DE LA CLASE DE COMPILAR        
            CLcompilar compilar = new CLcompilar();

//VARIABLES A USAR EN EL METODO    
//VERIFICAR EL NOMBRE DEL ARCHIVO
            boolean comprobar;
            comprobar = true;
//VALIDAR EL NOMBRE ARCHIVO         
            boolean aprobar;
            aprobar = true;
//CONTDOR DE LAS VECES QUE SE CUMPLE LA CONDICION         
            int contador;
            contador = 1;
//         
            //********************************************************************************        
//Variables booleanas para verificar condiciones dentro del código
            boolean validar_Archivo = false;
            boolean indicador;
            String nombre_Archivo_Revisado;
         
//variables de la extension del archivo
            String nombre_Archivo_Sinextension = "";
// valida la extension       
            if (nombre_Archivo.endsWith(".") || !nombre_Archivo.endsWith(".LID")) 
            
            {
//revisa que finalice en .LID
                if (nombre_Archivo.endsWith(".LID")) {
                    nombre_Archivo_Sinextension = nombre_Archivo_Sinextension.replace(".LID", "");
                } else {
                    
// En caso de no terminar en .LID 
// y tener alguna otra extensión, 
// realiza el cambio para que la extensión sea siempre .LID        
                    String[] extension = nombre_Archivo.split("\\.");
                    if (extension.length == 1) {
                        nombre_Archivo_Sinextension = nombre_Archivo;
                    } else {
                        nombre_Archivo_Sinextension = extension[0];
                    }
                }
//LONGITUD DEL ARCHIVO
                if (nombre_Archivo_Sinextension.length() > 20) {
                    System.out.println("La longitud máxima es de 20 caractéres");
                    validar_Archivo = true;
                }
//INICIE con una letra y no con un número o dígito SI FUNCIONA
                char letras[] = nombre_Archivo.toCharArray();
                if (!Character.isLetter(letras[0])) {
                    System.out.println("El nombre del archivo debe comenzar con una letra");
                    validar_Archivo = true;
                    
                 if ((indicador = compilar.expresion_regular("[^\\w\\.]", nombre_Archivo_Sinextension)) == true) {
                    System.out.println("El nombre del archivo no puede contener caractéres especiales");
                    validar_Archivo = true;
                    
//                 SI FUNCIONA
            if (nombre_Archivo_Sinextension.contains("_")) {
                System.out.println("El nombre del archivo no puede contener caractéres especiales.");
                validar_Archivo = true;

            } else {

                System.out.println("LA EXPRESION DEL ARCHIVO NO ES CORRECTA");

            }
            validar_Archivo = true;    
                    
                    
                }

            }
              
                    
                    
                } 

//CARACTERES ESPECIALES
//Valida que no contenga caractéres especiales
               

//DE LA INSTANCIA CREADA SE VERIFICA EL NOMBRE DEL ARCHIVO
            nombre_correcto = admin_archivo.verificar_nombre(nombre_Archivo);
//INSTRUCCION DEL NOMBRE CORRECTO ES TRUE       
            if ((nombre_correcto == true)) {
                admin_archivo.leerArchivo(nombre_Archivo);
            } //SI NO CUMPLE LA INSTRUCCION EL ARCHIVO ES INVALIDO             
            else {
                System.out.println("*** SU ARCHIVO NO ES VALIDO REVISE LA EXTENSION***");
            }
        }
Thread.sleep(2000);//PAUSA LA EJECUCION EN MILISEGUNDOS       

    }

}
