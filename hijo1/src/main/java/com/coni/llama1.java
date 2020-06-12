package com.coni;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class llama1 {
        Connection c = null;
        Statement stmt = null;
        llama1(){
            try{
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:resources\\mydatabase.db");
            }catch (Exception e){
                System.out.println("Error"+e.getMessage());
            }
        }


    public void aulaequipo(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM aula_equipo");
            while(rs.next()){
                int id_equipo = rs.getInt("id_equipo");
                String id_aula = rs.getString("id_aula");
                int cantidad = rs.getInt("cantidad");
                System.out.println(id_equipo + " " + id_aula + " " + " " + cantidad);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void aulas(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM aulas");
            while(rs.next()){
                String id_aula = rs.getString("id_aula");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                int capacidad = rs.getInt("capacidad");
                System.out.println(id_aula + " " + nombre + " "+ tipo + " " + capacidad);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void carrera(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM carrea");
            while(rs.next()){
                int idcarrera = rs.getInt("idcarrera");
                String nombre_carrera = rs.getString("firstname");
                System.out.println(idcarrera + " " + nombre_carrera);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void categoriasequipo(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias_equipo");
            while(rs.next()){
                int id_categoria = rs.getInt("id_categoria");
                String nombre_categoria = rs.getString("nombre_categoria");
                String descripcion_categoria = rs.getString("descripcion_categoria");
                System.out.println(id_categoria + " " + nombre_categoria + " "+ descripcion_categoria);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void disponibilidad(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM disponibilidad");
            while(rs.next()){
                int dia = rs.getInt("dia");
                int espacio_tiempo = rs.getInt("espacio_tiempo");
                String clv_usuario = rs.getString("clv_usuario");
                System.out.println(dia + " " + espacio_tiempo + " "+ clv_usuario);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void equipo(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM equipo");
            while(rs.next()){
                int id_equipo = rs.getInt("id_equipo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int id_categoria = rs.getInt("id_categoria");
                System.out.println(id_equipo + " " + nombre + " "+ descripcion + " " + id_categoria);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }



    public void grupomateriprofesor(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM grupo_materia_profesor");
            while(rs.next()){
                String clv_grupo = rs.getString("clv_grupo");
                String clv_materia = rs.getString("clv_materia");
                String clv_usuario = rs.getString("clv_usuario");
                System.out.println(clv_grupo + " " + clv_materia + " "+ clv_usuario);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }



    public void grupo(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM grupo");
            while(rs.next()){
                String clv_turno = rs.getString("clv_turno");
                boolean turno = rs.getBoolean("turno");
                System.out.println(clv_turno + " " + turno);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }




    public void login(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM login");
            while(rs.next()){
                String clv_usuario = rs.getString("clv_usuario");
                String pass_usuario = rs.getString("pass_usuario");
                String tipo_usuario = rs.getString("tipo_usuario");
                System.out.println(clv_usuario + " " + pass_usuario + " "+ tipo_usuario);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }




    public void materiausuario(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM materiausuario");
            while(rs.next()){
                String clv_materia = rs.getString("clv_materia");
                String clv_plan = rs.getString("clv_plan");
                String clv_usuario = rs.getString("clv_usuario");
                int puntos_confianza = rs.getInt("puntos_confianza");
                int puntos_director = rs.getInt("puntos_director");
                System.out.println(clv_materia + " " + clv_plan + " "+ clv_usuario + " " + puntos_confianza + " "+ puntos_director);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }
    public void materias(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM materias");
            while(rs.next()){
                String nombre_maeria = rs.getString("nombre_maeria");
                String clv_materia = rs.getString("clv_materia");
                int creditos = rs.getInt("creditos");
                int cuatrimestre = rs.getInt("cuatrimestre");
                int posicion = rs.getInt("posicion");
                String clv_plan = rs.getString("clv_plan");
                int horas_x_semana = rs.getInt("horas_x_semana");
                String tipo_materia = rs.getString("tipo_materia");
                System.out.println(nombre_maeria + " " + clv_materia + " "+ creditos + " " + cuatrimestre + " " + posicion + " "+ clv_plan + " " + horas_x_semana+ " "+ tipo_materia);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void planestudios(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM plan_estudios");
            while(rs.next()){
                String cvl_plan = rs.getString("cvl_plan");
                String nombre_plan = rs.getString("nombre_plan");
                String nivel = rs.getString("nivel");
                int idcarrera = rs.getInt("idcarrera");
                System.out.println(cvl_plan + " " + nombre_plan + " "+ nivel + " " + idcarrera);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void usoaulagrupo(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM uso_aula_grupo");
            while(rs.next()){
                int dia = rs.getInt("dia");
                int  espacio_tiempo = rs.getInt("espacio_tiempo");
                String id_aula = rs.getString("id_aula");
                String clv_grupo = rs.getString("clv_grupo");
                String clv_materia = rs.getString("clv_materia");
                System.out.println(dia + " " + espacio_tiempo + " "+ id_aula + " " + clv_grupo + " "+ clv_materia);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }


    public void usuarios(){
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usarios");
            while(rs.next()){

                String clv_usuario = rs.getString("clv_usuario");
                int idcarrera = rs.getInt("idcarrera");
                String nombre_usuario = rs.getString("nombre_usuario");
                String nivel_ads = rs.getString("nivel_ads");
                String contrato = rs.getString("contrato");
                System.out.println(clv_usuario + " " + idcarrera + " "+ nombre_usuario + " " + nivel_ads + " " + contrato);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }



        public void closeConnection()
        {
            try {
                c.close();
                System.out.println("La bd cerro correctamente");
            }catch (Exception e){
                System.out.println("Error al cerrar");
            }
        }
        public void executeQuery(String query){
            try{
                this.stmt = c.createStatement();
                stmt.executeQuery(query);
            }catch (Exception e){
                //error
            }
        }
    }
