package analisis;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Semantico {

//Semántico: lexemas bien escritos pero que no corresponden a objetos definidos:
//ejemplo: ReadInt ( MaxAn os ); es error si MaxAnos no ha sido definida. 
    
    
    ArrayList<String> lista_infolimpio = new ArrayList<String>();   
     ArrayList<String> lista_TiposDeVariables = new ArrayList<String>();
        ArrayList<String> palabras_reservadasLibelula = new ArrayList<String>(); //ArrayList donde estaran las palabras reservadas de ALGOL   
ArrayList<Dato> arregloConFallas = new ArrayList<Dato>(); 

    public void verificar_Variables() {
        int contador = 0;
        for (String linea : lista_infolimpio) {
            if ((linea == "") || linea.isEmpty() || (linea == " ")) {
                //ignorar lineas en blanco
            } else {
                linea = linea.replace(" ;", "; ");
                linea = linea.replace(" ,", ", ");
                //tokenizar palabras
                StringTokenizer tokens = new StringTokenizer(linea, " ");
                String[] datos = dividirCadenaTokens(linea);
                if (datos.length != 0) {
                    int indice1 = lista_TiposDeVariables.indexOf(datos[0]); //la variable debe iniciar en posicion 0
                    int indice2 = 0;
                    String token = "";
                    if (indice1 != -1) {
                        for (int i = 1; i < datos.length; i++) { //iniciamos en 1 porque no ocupamos revisar INT|CHAR|REAL
                            //validamos que no sea linea vacia
                            if (datos[i].isEmpty() || datos[i] == "" || datos[i] == " ") {
                                //ignorar
                            } else {
                                //busqueda palabra reservada
                                token = datos[i].trim();
                                token = datos[i].replaceAll("\\p{Punct}", "");
                                indice2 = palabras_reservadasLibelula.indexOf(token); //verificamos que no sea una variable que tenga formato de palabra reservada
                                if (indice2 != -1) {
                                    System.out.println();
                                    Dato lineaError = new Dato(contador, true, "Error 00101: " + token + " no puede ser variable porque es una palabra reservada.");
                                    arregloConFallas.add(lineaError);
                                } else {
                                    //validamos el ultimo elemento:
                                    if (i == datos.length - 1) {
                                        //que sea variable con punto y coma y que no sea punto y coma suelto
                                        if (!(regex("^(\\s+|)((?<name>[\\da-z_]+)(\\s+|);(\\s+|))$", datos[i])) && !(regex("(^(\\s+|);(\\s+|)$)", datos[i]))) {
                                            Dato lineaError = new Dato(contador, true, "Error 00090: " + "(" + datos[i] + ")" + " debe terminar con punto y coma o debe tener un único punto y coma.");
                                            arregloConFallas.add(lineaError);
                                        }
                                    } else {
                                        //que sea variable con coma, que no sea variable con punto y coma, que no sea coma suelta ni punto y coma suelto
                                        if (!(regex("^(\\s+|)((?<name>[\\da-z_]+)(\\s+|),(\\s+|))$", datos[i])) && !(regex("(^(\\s+|),(\\s+|)$)", datos[i])) && !(regex("(^(\\s+|);(\\s+|)$)", datos[i]))) {
                                            Dato lineaError = new Dato(contador, true, "Error 00091: " + "(" + datos[i] + ")" + " debe terminar con coma o debe tener una única coma.");
                                            arregloConFallas.add(lineaError);
                                        }
                                    }

                                    //verificar comas sueltas
                                    if ((regex("(^(\\s+|),(\\s+|)$)", datos[i]))) {
                                        if (((datos.length - 2) % 0 == 1)) {
                                            //no tiene comas de más                   
                                        } else {
                                            Dato lineaError = new Dato(contador, true, "Error 00092: " + "(" + datos[i] + ")" + " no debe tener comas guindando.");
                                            arregloConFallas.add(lineaError);
                                        }
                                    }

                                    //verificar puntos y comas sueltos
                                    if ((regex("(^(\\s+|);(\\s+|)$)", datos[i]))) {
                                        Dato lineaError = new Dato(contador, true, "Error 00093: " + "(" + datos[i] + ")" + " no debe tener puntos y comas guindando.");
                                        arregloConFallas.add(lineaError);
                                    }

                                    //verificar que no inicie CON _
                                    if ((regex("(^(_)([\\da-z_]+))", datos[i]))) {
                                        Dato lineaError = new Dato(contador, true, "Error 00094: " + "(" + datos[i] + ")" + " no debe iniciar con guión bajo.");
                                        arregloConFallas.add(lineaError);
                                    }

                                    //verificar que no termine con _
                                    if ((regex("(([\\da-z_]+)(_)$)", datos[i]))) {
                                        Dato lineaError = new Dato(contador, true, "Error 00095: " + "(" + datos[i] + ")" + " no debe terminar con guión bajo.");
                                        arregloConFallas.add(lineaError);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            contador++;
        }
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
