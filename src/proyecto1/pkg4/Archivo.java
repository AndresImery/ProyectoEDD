package proyecto1.pkg4;

import java.io.*;

/**
 *
 * @author alecuna
 */
public class Archivo {

    String text;
    String[] arreglo;

    FileInputStream input;
    FileOutputStream output;
    File file;
    
    public String openFile(File file) {
        String contenido = "";
        try {
            input = new FileInputStream(file);
            int ascci;
            while ((ascci = input.read()) != -1) {
                char caracter = (char) ascci;
                contenido += caracter;
            }
        } catch (Exception e) {

        }
        return contenido;
    }

    public String saveFile(File file, String content) {
        String respuesta = null;
        try {
            output = new FileOutputStream(file);
            byte[] bytesTxt = content.getBytes();
            output.write(bytesTxt);
            respuesta = "Se Guardo exitosamente el Archivo";

        } catch (Exception e) {

        }
        return respuesta;
    }

  
}
