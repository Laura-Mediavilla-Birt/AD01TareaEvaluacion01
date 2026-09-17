/*
 * El programa crea el fichero inventario.dat con los datos de los arrays
 * utilizando RandomAccessFile. Guarda los registros con campos de longitud fija
 * y muestra el número de registros y su tamaño total.
 */

package tarea01;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearInventario {

    public static void main(String[] args) {

        // Datos iniciales proporcionados en ArraysInventario.txt
        int[] ids = {1, 2, 3, 4, 5, 6, 7, 8};

        String[] codigos = {
            "EQ0001", "EQ0002", "EQ0003", "EQ0004",
            "EQ0005", "EQ0006", "EQ0007", "EQ0008"
        };

        String[] nombres = {
            "Portatil Lenovo",
            "Monitor Dell 24",
            "Teclado Logitech",
            "Raton Inalambrico",
            "Webcam Logitech",
            "Proyector Epson",
            "Dock USB-C",
            "Auriculares Jabra"
        };

        String[] categorias = {
            "portatil",
            "monitor",
            "periferico",
            "periferico",
            "periferico",
            "proyector",
            "accesorio",
            "audio"
        };

        int[] stocks = {6, 12, 18, 25, 9, 4, 14, 11};

        double[] precios = {
            899.90, 189.95, 49.90, 24.50,
            79.00, 549.99, 129.00, 159.90
        };

        // Longitud fija de los campos String
        int longitudCodigo = 8;
        int longitudNombre = 20;
        int longitudCategoria = 12;

        // Cálculo del tamaño total de cada registro
        int tamRegistro = 4
                + longitudCodigo * 2
                + longitudNombre * 2
                + longitudCategoria * 2
                + 4
                + 8;

        try (RandomAccessFile fichero =
                     new RandomAccessFile("inventario.dat", "rw")) {

            // El número de registros depende de la longitud de los arrays
            for (int i = 0; i < ids.length; i++) {

                fichero.writeInt(ids[i]);

                escribirCadena(fichero, codigos[i], longitudCodigo);
                escribirCadena(fichero, nombres[i], longitudNombre);
                escribirCadena(fichero, categorias[i], longitudCategoria);

                fichero.writeInt(stocks[i]);
                fichero.writeDouble(precios[i]);
            }

            System.out.println("Registros guardados: " + ids.length);
            System.out.println("Tamaño de cada registro: "
                    + tamRegistro + " bytes");

        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    /**
     * Escribe una cadena con una longitud fija.
     * Si es más corta, se rellena con espacios.
     * Si es más larga, se trunca.
     */
    private static void escribirCadena(
            RandomAccessFile fichero,
            String texto,
            int longitud) throws IOException {

        for (int i = 0; i < longitud; i++) {

            if (i < texto.length()) {
                fichero.writeChar(texto.charAt(i));
            } else {
                fichero.writeChar(' ');
            }
        }
    }
}