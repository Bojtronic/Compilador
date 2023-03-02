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

import java.util.ArrayList;

/**
 *
 * @author Natalia Granados
 */

//CLASE DE TIPO PUBLICA DE PALABRAS RESERVADAS
public class CLpalabras_Reservadas {
//SE DECLARAN LOS ARRAYLIST DE COMANDOS
    public static ArrayList<String> Comandos = new ArrayList<>();
//SE DECLARAN LOS ARRAYLIST DE LOS TIPOS DE VARIABLES
    public static ArrayList<String> TiposDeVariables = new ArrayList<String>();
//SE DECLARAN LOS ARRAYLIST DE LAS PALABRAS RESERVADAS DE LIBELULA
    public static ArrayList<String> Palabras_ReservadasLibelula = new ArrayList<String>();
//SE DECLARAN LOS ARRAYLIST DE LAS PALABRAS RESERVADAS DE MODULA2
    public static ArrayList<String> Palabras_ReservadaModula2 = new ArrayList<String>();

//SE COMPLETAN LOS DIVERSOS ARRAYLIST CON PALABRAS DE LIBELULA
    public static String[] palabras_reservadasLibelula
            = {"BEGIN", "CHAR", "ELSE", "END ", "IF ", "INTEGER", " MODULE", "Read",
                "ReadInt ", "ReadReal", "REAL", "REPEAT", "RETURN", "THEN", "UNTIL", "VAR",
                "Write", "WriteInt", " WriteLn ", "WriteReal", "WriteString"};
//SE COMPLETAN LOS DIVERSOS ARRAYLIST CON PALABRAS DE MODULA2
    public static String[] palabras_reservadasModula2
            = {"ABS", "ABSTRACT ", "AND ", "ARRAY", "AS ", "BEGIN ", "BITSET", "BOOLEAN ",
                "BY ", "CAP ", "CARDINAL ", "CASE", "CHAR ", "CHR ", "CLASS ", "CMPLX ", "COMPLEX ", "CONST ", "DEC ", "DEFINITION ", "DISPOSE ", "DIV ",
                "DO ", "ELSE ", "ELSIF", "END ", "EXCEPT ", "EXCL", "EXIT", "EXPORT", "FALSE", "FINALLY ", "FLOAT ", "FOR", "FORWARD ", "FROM ", "GENERIC ",
                "GUARD ", "HALT", "HIGH ", "IF ", "IM ", "IMPLEMENTATION ", "IMPORT ", "IN ", "INC ", "INCL ", "INHERIT ", "INT", "INTEGER ", "INTERRUPTIBLE ",
                "LENGTH ", "LFLOAT ", "LONGCOMPLEX", "LONGREAL", "LOOP", "MAX", "MIN ", "MOD", "MODULE", "NEW", "NIL", "NOT ", "ODD", " OF", " OR", "ORD",
                "OVERRIDE", "PACKEDSET", "POINTER ", "PROC ", "PROCEDURE ", "PROTECTION ", "QUALIFIED", "RE ", "READONLY ", "REAL", " RECORD ", "REM ", "REPEAT",
                "RETRY", "RETURN ", "REVEAL", "SET ", "SIZE ", "THEN ", "TO ", "TRACED", " TRUE ", "TRUNC", " TYPE ", "UNINTERRUPTIBLE ", "UNSAFEGUARDED ", "UNTIL",
                "VAL", "VAR", " WHILE", "WITH"};
//SE COMPLETAN LOS DIVERSOS ARRAYLIST CON LOS TIPOS DE VARIABLES
    public static String[] tipos_variables
            = {"INTEGER", "REAL", "CHAR"};
//SE COMPLETAN LOS DIVERSOS ARRAYLIST CON LOS TIPOS DE COMANDOS
    public static String[] tipos_comandos
            = {"BEGIN", "read", "print", "FOR", "IF", "GOTO", "SKIP", "END", "DO", "OD", "FI", "ELSE"};

}
