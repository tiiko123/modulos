package com.coni;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CargarRutas2 {
        private String red="\033[31m";
        private String green="\033[32m";
        private String yellow="\033[33m";
        private String reset="\u001B[0m";
        private String cyan="\033[36m";

        String rutaConfig="src\\main\\resources\\rutas.config";
        private String rutaScriptLocal;

        private String puertoMySQL;
        private String nombreBD;
        private String user;
        private String password;

        Scanner entrada = null;

        public void cargarRutas(){
            String linea="";
            int pos;
            try {

                File f = new File(rutaConfig);
                entrada = new Scanner(f);
                while (entrada.hasNext()) {
                    linea=entrada.nextLine();
                    pos=linea.indexOf("=");

                    if(pos!=-1 && linea.substring(0,pos).equals("puertoMySQL")){
                        this.puertoMySQL=linea.substring(pos+1,linea.length());
                    }
                    if(pos!=-1 && linea.substring(0,pos).equals("nombreBD")){
                        this.nombreBD=linea.substring(pos+1,linea.length());
                    }
                    if(pos!=-1 && linea.substring(0,pos).equals("user")){
                        this.user=linea.substring(pos+1,linea.length());
                    }
                    if(pos!=-1 && linea.substring(0,pos).equals("password")){
                        this.password=linea.substring(pos+1,linea.length());
                    }
                    if(pos!=-1 && linea.substring(0,pos).equals("scriptLocal")){
                        this.rutaScriptLocal=linea.substring(pos+1,linea.length());
                    }

                }

            } catch (FileNotFoundException e) {
                System.out.println(red+"[Error] "+reset+"No se encontró el archivo");
            } catch (NullPointerException e) {
                System.out.println(red+"[Error] "+reset+"No se seleccionó ningun archivo");
            } catch (Exception e) {
                System.out.println(red+"[Error] "+reset+"Error "+e);
            } finally {
                if (entrada != null) {
                    entrada.close();
                }
            }
        }

        public String getPuertoMySQL() {
            return puertoMySQL;
        }

        public String getNombreBD() {
            return nombreBD;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }

        public String getRutaScriptLocal() {
            return rutaScriptLocal;
        }


    }
