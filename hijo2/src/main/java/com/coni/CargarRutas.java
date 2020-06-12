package com.coni;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CargarRutas {

    //Para colores en consola
    private String red = "\033[31m";
    private String green = "\033[32m";
    private String yellow = "\033[33m";
    private String reset = "\u001B[0m";
    private String cyan = "\033[36m";

    String rutaConfig = "resources\\rutas.config";
    private String excelData;


    Scanner entrada = null;

    public void cargarRutas() {
        String linea = "";
        int pos;
        try {

            File f = new File(rutaConfig);
            entrada = new Scanner(f);
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                pos = linea.indexOf("=");

                if (pos != -1 && linea.substring(0, pos).equals("data")) {
                    this.excelData = linea.substring(pos + 1, linea.length());
                }

            }
            System.out.println(green + "[Exito]" + cyan + " Rutas cargadas correctamente." + reset);

        } catch (FileNotFoundException e) {
            System.out.println(red + "[Error] " + reset + "No se encontró el archivo");
        } catch (NullPointerException e) {
            System.out.println(red + "[Error] " + reset + "No se seleccionó ningun archivo");
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Error " + e);
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }
    public String getExcelData() {
        return excelData;
    }

}
