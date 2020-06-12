package com.coni;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class Conexion {
    private CargarRutas2 cr = new CargarRutas2();
    private Connection conect = null;
    private String nombreBD;
    private String user;
    private String password;
    private String puertoMySQL;
    private String rutaScriptLocal;
    private String linea;

    //Para colores en consola
    private String red = "\033[31m";
    private String green = "\033[32m";
    private String yellow = "\033[33m";
    private String reset = "\u001B[0m";
    private String cyan = "\033[36m";

    public Connection conexion() {
        this.limpiarConsola();
        this.cargarRutas();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:" + puertoMySQL + "/", user, password);
            if (conect != null) {
                System.out.println(green + "[Conexion establecida con el Servidor]" + reset);

                Statement s = conect.createStatement();
                ResultSet a = s.executeQuery("use " + nombreBD);

                System.out.println(green + "[Conexion establecida con la BD]\n\n" + reset);

                //Mostrar tablas
                this.mostrarAulas(conect);
                this.mostrarAula_equipo(conect);
                this.mostrarCarrera(conect);
                this.mostrarCategorias_equipo(conect);
                this.mostrarDisponibilidad(conect);

            }else{
                throw new ExceptionNoDataBaseFound(yellow+"ADVERTENCIA: "+reset+"No se encontró una base de datos. Creando una nueva...\n\n");
            }
        } catch (IllegalThreadStateException e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);

        } catch (SQLSyntaxErrorException e) {
            if (e.toString().indexOf("Unknow database") == -1) {
                System.out.println(yellow + "ADVERTENCIA: " + reset + "No se encontró una base de datos. Creando una nueva...\n\n");
                esperar();
                this.crearBD();
            } else {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "No se pudo establecer la conexion al servidor de la BD: " + e);

        }
        return conect;
    }

    Scanner entrada = null;

    public Connection crearBD() {
        limpiarConsola();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:" + puertoMySQL + "/", user, password);
            if (conect != null) {

                Statement s = conect.createStatement();
                System.out.println(green + "[Conexion establecida con el Servidor para la creación]" + reset);

                File f = new File(rutaScriptLocal);
                entrada = new Scanner(f);
                linea=entrada.nextLine();
                while (!linea.equals("--<FIN>")) {
                    if(!linea.equals("")){
                        s.execute(linea);
                    }
                    linea=entrada.nextLine();

                }

                System.out.println(green+"[Exito] "+cyan+"Base de datos creada correctamente."+reset+"\n");

                //Mostrar tablas
                this.mostrarAulas(conect);
                this.mostrarAula_equipo(conect);
                this.mostrarCarrera(conect);
                this.mostrarCategorias_equipo(conect);
                this.mostrarDisponibilidad(conect);
            }
        } catch (IllegalThreadStateException e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);

        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "No se pudo establecer la conexion al servidor de la BD: " + e);

        }
        return conect;
    }

    public void mostrarAulas(Connection conect) {
        try {
            Statement s = conect.createStatement();
            s.execute("use selesnyadb");
            ResultSet rs = s.executeQuery("select * from aulas");
            System.out.println(yellow+"[TABLA AULAS]\n\n"+reset);
            System.out.println("id_aula\t\tnombre\t\ttipo\t\tcapacidad\n");
            while (!String.valueOf(rs.next()).equals("false")) {
                System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2)+ "\t\t"+ rs.getString(3)+ "\t\t"+ rs.getInt(4));
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);
        }
    }

    public void mostrarAula_equipo(Connection conect) {
        try {
            Statement s = conect.createStatement();
            s.execute("use selesnyadb");
            ResultSet rs = s.executeQuery("select * from aula_equipo");
            System.out.println(yellow+"[TABLA AULA EQUIPOS]\n\n"+reset);
            System.out.println("id_equipo\t\tid_aula\t\tcantidad\n");
            while (!String.valueOf(rs.next()).equals("false")) {
                System.out.println(rs.getInt(1) + "\t\t" + rs.getInt(2)+ "\t\t" +rs.getInt(3));
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);
        }
    }

    public void mostrarCarrera(Connection conect) {
        try {
            Statement s = conect.createStatement();
            s.execute("use selesnyadb");
            ResultSet rs = s.executeQuery("select * from carrera");
            System.out.println(yellow+"[TABLA CARRERA]\n\n"+reset);
            System.out.println("idcarrera\t\tnombre_carrera\n");
            while (!String.valueOf(rs.next()).equals("false")) {
                System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2));
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);
        }
    }

    public void mostrarCategorias_equipo(Connection conect) {
        try {
            Statement s = conect.createStatement();
            s.execute("use selesnyadb");
            ResultSet rs = s.executeQuery("select * from categorias_equipo");
            System.out.println(yellow+"[TABLA CATEGORIAS EQUIPO]\n\n"+reset);
            System.out.println("id_categoria\t\tnombre_categoria\t\tdescripcion_categoria\n");
            while (!String.valueOf(rs.next()).equals("false")) {
                System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2)+ "\t\t" + rs.getString(3));
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);
        }
    }

    public void mostrarDisponibilidad(Connection conect) {
        try {
            Statement s = conect.createStatement();
            s.execute("use selesnyadb");
            ResultSet rs = s.executeQuery("select * from disponibilidad");
            System.out.println(yellow+"[TABLA DISPONIBILIDAD]\n\n"+reset);
            System.out.println("dia\t\tespacio_tiempo\t\tclv_usuario\n");
            while (!String.valueOf(rs.next()).equals("false")) {
                System.out.println(rs.getInt(1) + "\t\t" + rs.getInt(2)+ "\t\t" + rs.getString(3));
            }
            System.out.println("\n\n");
        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Se ha provocado una excepcion: " + e);
        }
    }
    public void cargarRutas() {
        try {
            cr.cargarRutas();
            this.puertoMySQL = cr.getPuertoMySQL();
            this.nombreBD = cr.getNombreBD();
            this.user = cr.getUser();
            this.password = cr.getPassword();
            this.rutaScriptLocal = cr.getRutaScriptLocal();

        } catch (Exception e) {
            System.out.println(red + "[Error] " + reset + "Problema al cargar rutas: " + e);
        }
    }

    //Extras
    public void limpiarConsola() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        }

    }

    public void esperar() {
        try {
            Thread.sleep(5 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
