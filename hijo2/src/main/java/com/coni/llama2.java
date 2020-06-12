package com.coni;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class llama2 {

        private String rutaExcelData;

        //Para colores en consola
        private String red="\033[31m";
        private String green="\033[32m";
        private String yellow="\033[33m";
        private String reset="\u001B[0m";
        private String cyan="\033[36m";

        public void leerExcel() {
            try{
                System.out.println(yellow+"Advertencia, leer el archivo LEER_IMPORTANTE.txt ubicado en la carpeta raíz del proyecto para más información respecto al siguiente programa"+reset+"\n");
                //Cargar rutas
                CargarRutas cr = new CargarRutas();
                cr.cargarRutas();
                this.rutaExcelData=cr.getExcelData();

                XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(rutaExcelData));
                XSSFSheet sheet = wb.getSheetAt(0);

                int rows = sheet.getLastRowNum()+1;
                int numColumnas = 4;
                String arrayData[][] = new String[rows][numColumnas];

                for (int i = 0; i < rows; ++i) {
                    XSSFRow row = sheet.getRow(i);
                    for(int j=0;j<numColumnas;j++){
                        arrayData[i][j]=String.valueOf(row.getCell(j));
                    }

                }
                System.out.println(green+"[Exito]"+cyan+" Excel leído correctamente."+reset+"\n");
                mostrarArrayData(arrayData, numColumnas);
            }catch(FileNotFoundException e) {
                System.out.println(red+"[Error] "+reset+"No se encontró el archivo excel");
            }catch (Exception e){
                System.out.println(red+"[Error] "+reset+e);
            }


        }

        public void mostrarArrayData(String arrayData[][], int numColumnas){
            try{
                System.out.println(green+"<----------------------[ Array del archivo Excel ]---------------------->"+reset);
                for(int i = 0; i<arrayData.length;i++){
                    for(int j = 0; j<numColumnas;j++){
                        System.out.print(arrayData[i][j]+"\t\t\t");
                    }
                    System.out.println("");
                }
            }catch(Exception e){
                System.out.println(red+"[Error] "+reset+e);
            }
    }
}
