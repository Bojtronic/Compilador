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
package Analisis;

/**
 *
 * @author Natalia Granados
 */

//CLASE DE TIPO PUBLICA LLAMADA CLDATO
public class CLdato 
{
//SE DECLARAN LAS VARIABLES DE TIPO STRING , INT Y BOOLEAN
    
//ALMACENA LA IDENTIFICACION DE LA LINEA QUE ES LA REFERENCIA DL INDICE DE LA BUSQUEDA
    public String lineInfo;
//ALMACENA EL NUMERO DE LA LINEA QUE ES LA REFERENCIA DEL INDICE DE LA BUSQUEDA   
    public int lineNumber; 
//ALAMECENA EL NUMERO DE ERROR DE LA LINEA IDENTIFICADA
    public boolean hasError;
     
//ELEMENTO DE UNA CLASE QUE INICIALIZA UNA INSTANCIA 
    public CLdato(int lineNumber, boolean hasError, String lineInfo) 
    {
        this.lineInfo = lineInfo;
        this.lineNumber = lineNumber;
        this.hasError = hasError;
        
    }
//GETTERS QUE MUESTRAN EL VALOR DE UNA PROPIEDAD DE LA CLASE A LA QUE CORRESPONDE
    
//GETTER DE TIPO ERROR 
    public boolean hasError() 
    {
        return hasError;
    }
//GETTER DE TIPO LINEA DE NUMERO   
    public int getLineNumber() 
    {
        return lineNumber;
    }
// GETTER DE INFORMACION DE LA LINEA   
    public String getLineInfo() 
    {
        return lineInfo;
    }
    
    
}
