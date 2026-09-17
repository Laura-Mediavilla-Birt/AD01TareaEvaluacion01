/*
 * El programa lee el archivo accesos.log línea a línea mediante BufferedReader
 * y selecciona las líneas cuyo resultado es ERROR. Estas líneas se escriben 
 * en errores.log utilizando BufferedWriter. Al finalizar, añade el total de 
 * errores encontrados y controla posibles errores de lectura o escritura. 
 */
package tarea01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FiltrarErrores {

    public static void main(String[] args) {

        String archivoEntrada = "src/tarea01/accesos.log";
        String archivoSalida = "src/tarea01/errores.log";

        int totalErrores = 0;

        try (
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))
        ) {

            String linea;

            // Leer el archivo línea a línea
            while ((linea = lector.readLine()) != null) {

                // Separar los datos de la línea
                String[] datos = linea.split(";");

                // Comprobar si el resultado es ERROR
                if (datos.length >= 3 && datos[2].equals("ERROR")) {

                    escritor.write(linea);
                    escritor.newLine();

                    totalErrores++;
                }
            }

            // Escribir el total de errores al final del archivo
            escritor.write("Total de errores: " + totalErrores);
            escritor.newLine();

            System.out.println("El archivo errores.log se ha creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer o escribir el archivo: " + e.getMessage());
        }
    }
}