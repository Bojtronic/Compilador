
package archivos;

import analisis.Lexico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Administrador_Archivos {
    
    public void leerArchivo(String ruta){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      
      Lexico lex = new Lexico();

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         String codigo_completo = "";
         while((linea=br.readLine())!=null){
           //System.out.println(linea);
           codigo_completo += linea + "\n";
         }
         
         System.out.println("\n"+"***  Esto es el resultado del analisis lexico que se usará para el analisis semantico  ***"+"\n");
         System.out.println(lex.lex(codigo_completo));
      }
      catch(IOException e){
          System.out.println(e);
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (IOException e2){ 
            System.out.println(e2);
         }
      }      
    }
    
    public void crearArchivo(String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (IOException e) {
            System.out.println(e);
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (IOException e2) {
              System.out.println(e2);
           }
        }
    }
    
    private boolean expresion_regular(String patron, String variable) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(variable);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }
    
    public boolean verificar_nombre(String nombre_archivo){
      boolean extension = expresion_regular("\\.LID$", nombre_archivo);
      if(extension == true){
        System.out.println("Extension .LID correcta");
      }
      else{
        System.out.println("La extension del archivo es incorrecta");
      }
      
      String[] nombre_sin_extension = nombre_archivo.split("\\.");
      boolean longitud = false;
      int longitud_del_nombre = nombre_sin_extension[0].length();
      if(longitud_del_nombre>1 && longitud_del_nombre<21){
        System.out.println("La longitud del nombre esta permitida");
        System.out.println("La longitud es: " + longitud_del_nombre);
        longitud = true;
      }
      else{
        System.out.println("La longitud es incumple el requerimiento de 20 caracteres como maximo");
      }
      
      boolean inicia_con_numero = expresion_regular("^[0-9]", nombre_archivo);
      if(inicia_con_numero == true){
        System.out.println("El nombre inicia con un numero");
      }
      
      boolean carateres_especiales = expresion_regular("[^\\w\\.]", nombre_archivo);
      if(carateres_especiales == true){
        System.out.println("El nombre del archivo no debe contener carateres especiales");
      }
      
      if(extension==true && longitud==true && inicia_con_numero==false && carateres_especiales==false){
        return true;
      }
      else{
        return false;
      }
    }
//    falta validar en el nombre del archivo y .LID
    
//  if ((verificar = regex("^_", nombre)) == true) {
//            Datos miError = new Datos(contador, true,Error, el nombre del archivo empieza con un _");
//            arregloConErrores.add(miError);
//            contador++;
//            valido = false; //bandera
//            //No terminar con guion bajo   
//        }
//        
//        if ((verificar = regex("_$", nombre)) == true) {
//            Datos miError = new Datos(contador, true, "Error, el nombre del archivo termina con un _");
//            arregloConErrores.add(miError);
//            contador++;
//            valido = false; //bandera
//            //verificar la extension del archivo
//        }
//        
//        if ((verificar = regex("\\.MINGOL$", nombre) == true) && (verificar = regex("\\.{2,}", nombre) == true)) {
//            Datos miError = new Datos(contador, true, "Error, el nombre del archivo tiene dos puntos o más");
//            arregloConErrores.add(miError);
//            contador++;
//            valido = false; //bandera
//        }
//        
//        if ((verificar = regex("\\.$", nombre)) == true) {
//            Datos miError = new Datos(contador, true, "Error, el nombre termina en punto y no tiene extension");
//            arregloConErrores.add(miError);
//            contador++;
//            valido = false; //bandera
//        }
//        
//        //si tiene extension diferente de MINGOL o mingol
//        if ((verificar = regex("\\.(?:mingol$|MINGOL$)", nombre) == false) && (verificar = regex("\\.(\\w+)", nombre) == true)) {
//            Datos miError = new Datos(contador, true, "Error, el nombre del archivo tiene una extensión inválida");
//            arregloConErrores.add(miError);
//            contador++;
//            valido = false; //bandera
//        }
//        
//        //si es valido y tiene extension correcta, solo lo asignamos a la variable estatica global
//        if ((valido == true) && ((verificar = regex("\\.MINGOL$", nombre)) == true)) {
//            nombreValidado = nombre;
//        }
//        
//        //si tiene un .mingol lo pasamos a .MINGOL
//        if ((verificar = regex("\\.mingol$", nombre) == true)) {
//            nombreValidado = nombre.replace(".mingol", ".MINGOL");
//        }
//        
//        //si no tiene extension y es un nombre valido, le agregamos .MINGOL
//        if ((valido == true) && ((verificar = regex("\\.$", nombre)) == false) && ((verificar = regex("\\.(?:mingol$|MINGOL$)", nombre)) == false)) {
//            nombreValidado = nombre + ".MINGOL";
//        }
//    
    
    
    
}
