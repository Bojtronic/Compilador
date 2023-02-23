// cd Documents\TRABAJO\Natalia_Granados\Compilador_Libelula\Compilador\LIBELULA\dist
// java -jar LIBELULA.jar archivo.LID
package principal;

import archivos.Administrador_Archivos;
import java.nio.file.Paths;



public class LIBELULA {

    public static void main(String[] archivo) throws InterruptedException {
        String path = Paths.get("").toAbsolutePath().toString(); //El primer argumento enviado por el cmd se almacena en una variable
        
        Administrador_Archivos admin_archivo = new Administrador_Archivos();
        
        boolean nombre_correcto = true; /// ver linea 31 y 28
                     
        
        //Es cero si no se pasa el archivo
        if (archivo.length < 1 ) {
            System.out.println("Error no ha ingresado ningun archivo");
        } else {
            String nombre_Archivo = archivo[0];
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
