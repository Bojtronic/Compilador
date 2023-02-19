/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;


public class Dato {

    public int numero_Linea;
    public boolean falla_Linea;
    public String info_Linea;

    public Dato(int numero_Linea, boolean falla_Linea, String info_Linea) {
        this.numero_Linea = numero_Linea;
        this.falla_Linea = falla_Linea;
        this.info_Linea = info_Linea;
    }
    
    public int getNumero_Linea() {
        return numero_Linea;
    }

    public boolean getError_Linea() {
        return falla_Linea;
    }

    public String getInformacion_Linea() {
        return info_Linea;
    }


    
}
