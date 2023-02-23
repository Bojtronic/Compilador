package analisis;

import java.util.ArrayList;
import java.util.Collections;



//Falta los Arraylist de declaracion de comandos o caracteres especiales ademas de mayusculas


public class Palabras_Reservadas {
  
  public static ArrayList<String> PalabrasReservadas_ALGOL = new ArrayList<String>();
  public static ArrayList<String> PalabrasReservadas_Mayus = new ArrayList<String>(); //ArrayList donde estaran las palabras reservadas de ALGOL en mayuscula  
  public static ArrayList<String> Comandos = new ArrayList<>();
  public static ArrayList<String> TiposDeVariables = new ArrayList<String>();
  
  
  
  public Palabras_Reservadas(){
     Collections.addAll(PalabrasReservadas_ALGOL, "ABS", "AND", "ARG", "BEGIN", "BIN", "BITS", "BOOL", "BYTES", "CHANNEL", "CHAR", "COMMENT", "COMPL", "COMPLEX", "CONJ", "DET", "DIVAB", "DO", "DOUBLE", "DOWN", "DYAD", "ELEM", "ELSIF", "END", "ENTIER", "EQ", "FALSE",
                "FILE", "FLEX", "FORMAT", "GE", "GT", "IM", "INT", "INV", "LE", "LENG", "LIBRARY", "LONG", "LT", "LWB", "MINUSAB", "MOD", "MODAB", "MODE", "NE", "NIL", "NORM", "NOT", "ODD", "OP", "OR", "OVER", "OVERAB", "PIPE", "PLUSAB", "PLUSTO", "PR", "PRAGMAT", "PRELUDE",
                "PRIO", "PROC", "RE", "REAL", "REF", "REPEAT", "REPR", "ROUND", "ROW", "RETURN", "SEMA", "SHL", "SHORT", "SHORTEN", "SHR", "SIGN", "SKIP", "SOUND", "STANDARD", "STRING", "STRUCT", "TIMESAB", "TRACE", "TRUE", "TRANSPUT", "UP", "UPB", "VOID", "WHILE", "XOR", "a68g",
                "abend", "abort", "abs", "airy", "alnum", "alpha", "angle", "arccos", "arccosh", "arcsin", "arcsinh", "arctan", "arctan2", "arctanh", "argc", "argv", "aspect", "associate", "back", "background", "backspace", "bessel", "beta", "bin", "blank", "break", "channel",
                "cholesky", "circle", "clear", "clock", "close", "cntrl", "collect", "collections", "colour", "columns", "cos", "cosh", "cpu", "create", "curses", "curt", "debug", "digit", "draw", "elliptic", "eof", "eoln", "erase", "erf", "error", "establish", "evaluate", "execve",
                "exp", "factorial", "fft", "fill", "fixed", "flip", "float", "flop", "font", "fork", "format", "gamma", "garbage", "gc", "get", "getchar", "getf", "graph", "grep", "halt", "heap", "http", "idf", "in", "incomplete", "integral", "inverse", "is", "last",
                "lengths", "line", "linestyle", "linewidth", "ln", "local", "lock", "log", "lower", "lu", "make", "max", "min", "mksa", "modes", "move", "name", "new", "null", "num", "on", "open", "out", "pack", "page", "pi", "point", "pointer", "possible",
                "preemptive", "print", "printf", "punct", "put", "putchar", "putf", "qr", "random", "read", "rect", "refresh", "reidf", "reset", "resolution", "return", "routine", "scratch", "seconds", "set", "shorths", "show", "sin", "sinh", "size", "size = INT",
                "small", "sound", "space", "sqrt", "stack", "stand", "stop", "style", "sub", "sv", "sweep", "system", "tan", "tanh", "tcp", "term", "text", "time", "upper", "value", "wait", "whole", "width", "will", "window", "write", "xdigit");
        
    Collections.addAll(PalabrasReservadas_Mayus, "ABS", "AND", "ARG", "BEGIN", "BIN", "BITS", "BOOL", "BYTES", "CHANNEL", "CHAR", "COMMENT", "COMPL", "COMPLEX", "CONJ", "DET", "DIVAB", "DO", "DOUBLE", "DOWN", "DYAD", "ELEM", "ELSIF", "END", "ENTIER", "EQ", "FALSE",
                "FILE", "FLEX", "FORMAT", "GE", "GT", "IM", "INT", "INV", "LE", "LENG", "LIBRARY", "LONG", "LT", "LWB", "MINUSAB", "MOD", "MODAB", "MODE", "NE", "NIL", "NORM", "NOT", "ODD", "OP", "OR", "OVER", "OVERAB", "PIPE", "PLUSAB", "PLUSTO", "PR", "PRAGMAT", "PRELUDE",
                "PRIO", "PROC", "RE", "REAL", "REF", "REPEAT", "REPR", "ROUND", "ROW", "RETURN", "SEMA", "SHL", "SHORT", "SHORTEN", "SHR", "SIGN", "SKIP", "SOUND", "STANDARD", "STRING", "STRUCT", "TIMESAB", "TRACE", "TRUE", "TRANSPUT", "UP", "UPB", "VOID", "WHILE", "XOR");
    //Se llena arraylist con tipos de variables. 
    Collections.addAll(TiposDeVariables, "CHAR", "INT", "REAL");

    Collections.addAll(Comandos, "BEGIN", "read", "print", "FOR", "IF", "GOTO", "SKIP", "END", "DO", "OD", "FI", "ELSE");
  }
  
  

    public static String[] palabras_reservadasLibelula
            = {"BEGIN", "CHAR", "ELSE", "END ", "IF ", "INTEGER", " MODULE", "Read",
                "ReadInt ", "ReadReal", "REAL", "REPEAT", "RETURN", "THEN", "UNTIL", "VAR",
                "Write", "WriteInt", " WriteLn ", "WriteReal", "WriteString"};

    public static String[] palabras_reservadasmodula2
            = {"ABS", "ABSTRACT ", "AND ", "ARRAY", "AS ", "BEGIN ", "BITSET", "BOOLEAN ",
                "BY ", "CAP ", "CARDINAL ", "CASE", "CHAR ", "CHR ", "CLASS ", "CMPLX ", "COMPLEX ", "CONST ", "DEC ", "DEFINITION ", "DISPOSE ", "DIV ",
                "DO ", "ELSE ", "ELSIF", "END ", "EXCEPT ", "EXCL", "EXIT", "EXPORT", "FALSE", "FINALLY ", "FLOAT ", "FOR", "FORWARD ", "FROM ", "GENERIC ",
                "GUARD ", "HALT", "HIGH ", "IF ", "IM ", "IMPLEMENTATION ", "IMPORT ", "IN ", "INC ", "INCL ", "INHERIT ", "INT", "INTEGER ", "INTERRUPTIBLE ",
                "LENGTH ", "LFLOAT ", "LONGCOMPLEX", "LONGREAL", "LOOP", "MAX", "MIN ", "MOD", "MODULE", "NEW", "NIL", "NOT ", "ODD", " OF", " OR", "ORD",
                "OVERRIDE", "PACKEDSET", "POINTER ", "PROC ", "PROCEDURE ", "PROTECTION ", "QUALIFIED", "RE ", "READONLY ", "REAL", " RECORD ", "REM ", "REPEAT",
                "RETRY", "RETURN ", "REVEAL", "SET ", "SIZE ", "THEN ", "TO ", "TRACED", " TRUE ", "TRUNC", " TYPE ", "UNINTERRUPTIBLE ", "UNSAFEGUARDED ", "UNTIL",
                "VAL","VAR", " WHILE", "WITH"};

    public static String[] tipos_variables
            = {"INTEGER", "REAL", "CHAR"};

    public static String[] tipos_comandos
            ={"BEGIN", "read", "print", "FOR", "IF", "GOTO", "SKIP", "END", "DO", "OD", "FI", "ELSE"};
    
    }


   
    

//pendientes por hacer
//1-Validaciones del archivo
//Tener una longitud máxima de 20 caracteres.
//Es sensible a mayúsculas/minúsculas.
//Empezar con una letra.
//Tener solo letras y números.
//No usar caracteres especiales.


////2-INDEPENDENCIA FÍSICA DEL COMPILADOR
//es el .jar que ya esta creado con clean and build

//3- EJEMPLO DE USO DEL COMPILADOR
//REVISION \\> java jar LI BELULA .jar CalcInflac.LID 
//calcInflac en moodle
//Su compilador de LIBELULA debe abrir el archivo CalcInflac.LID y empezar a leerlo línea por
//línea; cada una de esas líneas debe ser analizada sintáctica y semánticamente, a fin de
//detectar errores.

//4-ARCHIVO DE ERRORES
//LIBELULA debe crear un nuevo archivo de salida de errores, con el mismo nombre solo que
//con el sufijo que dice “ errores” y extensión txt se debe generar un CalcInflac errores.txt

//5 -SINTAXIS DE LIBELULA
//es sensible a mayusculas y minusculas

//6-***PENDIENTE PREGUNTAR FORMATO DE ARCHIVO

//7- COMENTARIOS
//Se debe tener claro que los comentarios como tales son un comando también.
//Los comentarios se indican con para el inicio y para el fin; debe validarse que en ambos
//casos los dos caracteres que conforman la apertura y el cierre vengan “pegados”; los
//comentarios pueden ser multilíneas, o sea abarcar varias líneas físicas hasta un máximo de
//10 líneas. Si después de 10 líneas físicas no se ha cerrado un comenta rio entonces es error y
//las siguientes líneas a partir de esa décima se deben procesar como si fueran otro posible
//comando

//8-PENDIENTE PREGUNTAR ESTRUCTURA DE ARCHIVO
//9-MODULE NOMBRE_PROGRAMA;
//Todo programa LIBELULA debe empezar con la palabra reservada MODULE (solo debe
//aparecer una vez y debe ser una aparición válida), llevar un NOMBRE_PROGRAMA (que debe
//ser un identificador válido, ver el punto 2.5.5) y finalizar esa línea con punto y coma.
//Antes de MODULE solo pueden aparecer líneas en blanco, comentarios o líneas con
//comandos de MODULA2 (ver el punto 2.4.3.5).
//Se puede asumir que los 3 lexemas ( MODULE NOMBRE_PROGRAMA;) vendrán en una
//misma línea lógica y no incluirán más comandos.
// validar que empiece con la palabra MODULA O MODULO (REVISAR TXT.)
//10- VAR
//Sección donde se definen las variables que utiliza el programa; es opcional.
//Más adelante (ver el punto
//2.5.6) se explica cómo se declaran.
//Empieza luego de que aparece MODULE.
//Termina donde aparece BEGIN.
//Fuera de esta sección no deben permitirse más declaraciones de variables.
//Se puede asumir que aparte de VAR no vendrán otros comandos en la misma línea l
//ógica.
//11-BEGIN
//Comando con el que se se inicia el programa; siempre debe ser el primero y solo debe aparecer
//una vez y debe ser una aparición válida; no se deben permitir otros comandos LIBELULA
//mientras no haya aparecido BEGIN. Nótese que antes de BEGIN sí pueden venir líneas en
//blanco, comentarios o comandos MODULA2.
////BEGIN y END 
//12-SECCIÓN DE COMANDOS
//Sección donde se indican los
//comandos LIBELULA que utiliza el programa; es obligatoria.
//Empieza luego de BEGIN.
//Termina con END NOMBRE_PROGRAMA. ← ojo al punto final.
//El NOMBRE_PROGRAMA debe ser el mismo que se puso en el comando MODULE,
//literalmente en cuanto a mayúsculas y minúsculas.
//No pueden indicarse comandos LIBELULA o MODULA2 después de END
//NOMBRE_PROGRAMA. aunque sí pueden aparecer líneas en blanco o comentarios.
//13-PALABRAS RESERVADAS DE MODULA2
//Cualquier comando que no se reconozca como válido de LIBELULA (pero sí de MODULA2) se
//ignorará, pero en el archivo de errores saldrá el mensaje:
//las palabras reservadas de MODULA2). Cualquier otra cosa que no esté en esta lista y que no
//correspondan a comandos de LIBELULA de berá aparecer un mensaje como el siguiente:
//ERROR: "xxxx" no es un comando válido de LIBELULA ni de MODULA2.
//palabras reservadas de libelula y modula2
// falta metodo de palabras reservadas en mayus , minuscu 
//14-PALABRAS RESERVADAS DE LIBELULA
//Al final de este documento en el
//ANEXO 2 se adjuntan todas las palabras reservadas de
//LIBELULA, las cuale s corresponden a los comandos explicados en este documento.
////15-END NOMBRE_PROGRAMA. <ojo al punto final
//Todo programa LIBELULA debe finalizar con la palabra reservada END, llevar el
//NOMBRE_PROGRAMA (que debe ser un identificador válido, ver el pu nto 2.5.5; debe ser el
//mismo que apareció en el comando MODULE) y terminar con un punto.
//Estos tres lexemas solo deben aparecer una vez y debe ser una aparición válida.
//Como ya se indicó, deben terminar con punto.
//Se puede asumir que los 3 lexemas (END NOM BRE_PROGRAMA. vendrán en una misma
//línea lógica.
//No se deben permitir otros
//comandos después de que aparecen, aunque sí puede haber líneas
//en blanco o comentarios.
//Se reitera que NOMBRE_PROGRAMA debe ser el mismo que se utilizó en MODULE; no es el
//nombre del archivo que contiene el código fuente en LIBELULA.
//16-MAYÚSCULAS/MINÚSCULAS
//LIBELULA es sensible a mayúsculas y minúsculas en cuanto a los elementos del lenguaje.
//Además, lo que vaya dentro de las comillas simples (que se usan como delimitadores de
//textos) se debe respetar literalmente.
//Ejemplos:
//REPEAT es válida y se refiere al
//comando REPEAT.
//FACTOR, factor, FACtor, facTOR, FaCtOr son válidas y se refieren a diferentes
//identificadores.
//17-VARIOS COMANDOS POR LÍNEA
//LIBELULA permite varios comandos por línea lógica los cuales se separan con punto y coma,
//con las excepciones que ya se han explicado a lo largo de este documento.
//18-ELEMENTOS DEL LENGUAJE
//Se exponen a continuación los principales elementos del lenguaje LIBELULA.
//2.5.1 AGRUPACIÓN
//Los caracteres de agrupación válidos para LIBELULA son:
//( paréntesis izquierdo
//) paréntesis derecho
//19-CONSTANTES
//En LIBELULA hay cuatro tipos de constantes: caracter, texto, enteras y reales.
//Las de caracter son de un solo caracter y van delimitadas por comillas simples
//Las de texto tienen hasta 60 caracteres y van delimitadas por comillas simples
//20 -OPERADORES ARITMÉTICOS
//Los siguientes son los operadores aritméticos que maneja LIBELULA:
//VER PDF PAG 20
//21-OPERADORES RELACIONALES
//Los siguientes son los operadores relacionales que maneja
//LIBELULA.
//N
//ótese que no deben llevar blancos entre los dos caracteres especiales (si es del caso):
//=
//Igual.
//#
//No igual o diferente.
//<
//Menor que.
//>
//Mayor que.
//<=
//Menor o igual.
//>=
//Mayor o igual.
//
//22-IDENTIFICADORES
//En LIBELULA los identificadores se definen siguiendo las siguientes reglas:
//◦ Tener una l ongitud máxima de 20 caracteres.
//◦ Son sensibles a mayúsculas y minúsculas.
//◦ Empezar con una letra.
//◦ Solo tener letras o números.
//◦ No usar otros caracteres especiales.
//◦ No corresponder a una palabra reservada de LIBELULA o de MODULA2.
//Ejemplos:
//Correctos
//MaxAnos
//ANO
//factor1
//respUESTA
//Incorrectos
//max-anos porque lleva un carácter especial en el medio. 
//1factor porque no empieza con letra. 
//Factor2% porque lleva un carácter especial al final.
//_no_se_usa porque no empieza con letra. 
//REAL porque es palabra reservada de LIBELULA. 
//TRUE porque es palabra reservada de MODULA2.
//23-DEFINICIÓN DE VARIABLES
//En LIBELULA las variables se definen siguiendo la siguiente sintaxis: identificadores (uno o más separados por coma) : TIPO-DE-DATOS en dónde:
//TIPO DE DATOS puede ser INTEGER, REAL o CHAR;
//• El caracter : (dos puntos) es obligatorio y debe aparecer solo una vez antes del TIPO
//DE DATOS.
//INTEGER se usará para almacenar números enteros;
//REAL se usará par a almacenar números reales;
//CHAR se usará para almacenar un caracter;
//Los identificadores de variables deben ser válidos conforme a las reglas indicadas anteriormente; 
//si hay más de un identificador de variable se deben separar por comas;
//pero no deben quedar comas “guindando” (al principio, en el medio o al final);
//tampoco deben permitirse identificadores repetidos ni siquiera con tipos de datos diferentes.
//24- CALIDAD DE LOS ERRORES
//LIBELULA debe reportar al usuario los errores que detecta cuando analiza las hileras de
//caracter es que conforman el archivo fuente que corresponde al programa.
//Los diversos mensajes de error deben tener este formato:
//ERROR 999: texto del error.
//Todos los errores que se vayan a manejar deben ser identificados por un código y un texto. La
//enumeració n de los errores queda a criterio del estudiante. Los errores deben ser claros y
//concisos y referirse a solo una situación de error por vez, de manera que un texto como éste:
//Variable no definida o de tipo inválido.
//no es correcto pues son dos errores di ferentes en un mismo mensaje.
//El mensaje de error debe indicar entre paréntesis cuadrados (corchetes) cuál es el lexema que
//provoca el error.
//// LOS ERRORES EMPIEZAN DESDE LA LINEA 8 PREGUNTAR SI LAS LINEAS EN BLANCO CUENTAN COMO ERRORES
//VER PAGI 24 DEL PDF LIBELULA RESUMIDO
//25-ASIGNACIÓN DE VARIABLES
//La asignación de valores a las variables se hace mediante el operador o “dos puntos igual”.
//Ambos caracteres deben ir pegados sin blanco intermedio entre ellos.
//Ver el comando ASIGNAR en el punto 2.6.3.
////26-ETIQUETAS/GOTO
//A diferencia de PASCA L y ADA, LIBELULA no usa etiquetas y en consecuencia tampoco el
//comando GOTO.
//No se van a implementar. En este punto no tienes que hacer nada, es solo informativo.
//27- COMANDOS
//Se describen a continuación cada uno de los comandos que maneja LIBELULA.
//28-.1 Read
//Sintaxis: Read ( Variable de tipo CHAR );
//ReadInt ( Variable de tipo INTEGER );
//ReadReal ( Variable de tipo REAL );
//Permite ingresar valores a las variables desde el teclado.
//Se puede asumir que solo se pide una varia ble a la vez.
//Los paréntesis son obligatorios.
//La variable debe haber sido definida previamente.
//El tipo de datos de la variable debe corresponder al comando que se invoca.
//28-Read
//Sintaxis: Read ( Variable de tipo CHAR );
//ReadInt ( Variable de tipo INTEGER );
//ReadReal ( Variable de tipo REAL );
//Permite ingresar valores a las variables desde el teclado.
//Se puede asumir que solo se pide una varia ble a la vez.
//Los paréntesis son obligatorios.
//La variable debe haber sido definida previamente.
//El tipo de datos de la variable debe corresponder al comando que se invoca.
//29-ASIGNAR
//Sintaxis: variable := expresión
//Para asignar el valor de una expresión a una variable.
//La variable debe haber sido definida previamente.
//Se puede asumir que la expresión siempre es correcta y no se debe validar en cuanto a
//correctitud de los paréntesis, uso de los operadores aritméticos o relacionales, posición de los
//operandos, etc.
//Sin embargo, se debe analizar cada uno de los identificadores y palabras reservadas que
//aparecen en la expresión y confirmar que todos correspondan a:
//• var iables previamente definidas; o
//• palabras reservadas de MODULA2.
//30-2.6.4 Write
//Sintaxis: Write ( Variable de tipo CHAR )
//WriteInt ( Variable de tipo INTEGER, tam año )
//WriteReal ( Variable de tipo REAL, tamaño )
//WriteString ( 'Texto' )
//31- RETURN
//Sintaxis: RETURN
//Termina la ejecución del programa y regresa el control al sistema operativo.
//Ejemplo:
////RETURN;
//32- REPEAT
////Sintaxis:
////REPEAT
////comandos
////UNTIL condición

//32- END NOMBRE_MODULO. 
//Indicador de que los comandos del programa terminaron. 
//Siempre debe ser el último y solo debe aparecer una vez; 
//no se deben permitir otros comandos después de que aparece, aunque, como ya se indicó, sí puede haber líneas en blanco o comentarios. 
//Véase el punto 2.4.3.7 para la sintaxis exacta.

//33-ARCHIVO PARA PRUEBAS FEO QUE VIENE EN EL ARCHIVO : LIBELULA-ARCHIVOS
//de que no es muy ordenado; es importante que las pruebas de s u compilador la haga usando
//este programa "feo", ya que así serán los archivos de prueba que usará el Tutor. Toma r nota,
//de que las primeras líneas tienen desde 0 y hasta muchos caracteres en blanco, antes de que
//aparezca MODULE; los mismo las líneas intermedias y las finales luego de END
//NOMBRE_PROGRAMA.; además, hay blancos redundantes por todo lado: antes y después
//de lexemas; todo eso tu programa debe procesarlo, permitirlo e ignorarlo (según sea el caso

