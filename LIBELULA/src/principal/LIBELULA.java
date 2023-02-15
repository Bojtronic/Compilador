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
                
        
        System.out.println(ruta);
        
        
        //Si la persona ingresa espacios en blanco en el nombre, debe dar error porque args lo toma separado
        if (archivo.length > 1) {
            System.out.println("Error1 el nombre debe contener más de 1 caracter");
        } else {
            String nombre_Archivo = archivo[0];
            
            //Validar el nombre del archivo  -> nombre_correcto = t/f ///////////////////////
            if ((nombre_correcto = admin_archivo.expresion_regular("\\.LID$", nombre_Archivo) == true) ) {
              
              System.out.println("extension LID correcta");
              admin_archivo.leerArchivo(nombre_Archivo);
            }
            
            else{
              System.out.println("extension incorrecta");
            }
        }
        
        Thread.sleep(2000);
        
    }
    
}
