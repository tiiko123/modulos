package com.coni;
public class App
{
    public static void main( String[] args )
    {
        //Modulo Sqlite
        llama1 oo = new llama1();
        oo.lista();
        oo.closeConnection();
        //Modulo Excel
        llama2 xl = new llama2();
        xl.leerExcel();
        //Modulo Instalacion de BD
        Conexion kk = new Conexion();
        kk.conexion();

    }
}
