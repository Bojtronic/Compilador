// cd Documents\TRABAJO\Natalia_Granados\Compilador_Libelula\Compilador\LIBELULA\dist
// java -jar LIBELULA.jar archivo.LID
package principal;

import archivos.Administrador_Archivos;
import java.nio.file.Paths;



public class LIBELULA {

    public static void main(String[] archivo) throws InterruptedException {
        String path = Paths.get("").toAbsolutePath().toString(); //El primer argumento enviado por el cmd se almacena en una variable
        String ruta = "/"+path;
        Administrador_Archivos admin_archivo = new Administrador_Archivos();
        
        boolean nombre_correcto = true; /// ver linea 31 y 28
                     
        
        //Si la persona ingresa espacios en blanco en el nombre, debe dar error porque args lo toma separado
        if (archivo.length < 1 ) {
            System.out.println("Error no ha ingresado ningun archivo");
        } else {
            String nombre_Archivo = archivo[0];
            nombre_correcto = admin_archivo.verificar_nombre(nombre_Archivo);
            //Validar el nombre del archivo  -> nombre_correcto = t/f ///////////////////////
            if ((nombre_correcto == true) ) {
              
      
              admin_archivo.leerArchivo(nombre_Archivo);
            }
            
            else{
              System.out.println("***  Archivo no valido  ***");
            }
        }
        
        Thread.sleep(2000);
        
    }
    
}
