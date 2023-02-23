// cd Documents\TRABAJO\Natalia_Granados\Compilador_Libelula\Compilador\LIBELULA\dist
// java -jar LIBELULA.jar archivo.LID
package principal;

import analisis.Compilar;
import analisis.Dato;
import archivos.Administrador_Archivos;
import static archivos.Administrador_Archivos.ERRORES_DURANTE_COMPILACION;
import static archivos.Administrador_Archivos.nombreVerificado;
import java.nio.file.Paths;



public class LIBELULA {

    public static void main(String[] archivo, String nombre_archivo) throws InterruptedException {
        String path = Paths.get("").toAbsolutePath().toString(); //ruta de archivo
        
        
        
        Administrador_Archivos admin_archivo = new Administrador_Archivos(); //instancia
        
        boolean nombre_correcto = true; //var para saber si el nombre esta correcto o no
                     
        
        //Es cero si no se pasa el archivo
        if (archivo.length < 1 ) {
            System.out.println("Error no ha ingresado ningun archivo");
        } else {
            String nombre_Archivo = archivo[0];
            
            Compilar compilar = new Compilar();
            boolean verificar = true;
            boolean valido = true;
             int contador = 1;
            
              //si tiene extension diferente de LIBELULA o libelula
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
        
        
        // REVISARLO
        //si no tiene extension y es un nombre valido, le agregamos .MINGOL
        if ((valido == true) && ((verificar = compilar.expresion_regular("\\.$", nombre_archivo)) == false) && ((verificar = compilar.expresion_regular("\\.(?:lid$|LID$)", nombre_archivo)) == false)) {
            nombreVerificado = nombre_archivo + ".LID";
        }
        
        
        
        
        
            
            nombre_correcto = admin_archivo.verificar_nombre(nombre_Archivo);
       
            if ((nombre_correcto == true) ) {
              
      
              admin_archivo.leerArchivo(nombre_Archivo, path);
            }
            
            else{
              System.out.println("***  Archivo no valido  ***");
            }
        }
        
        Thread.sleep(2000);
        
    }
    
}
