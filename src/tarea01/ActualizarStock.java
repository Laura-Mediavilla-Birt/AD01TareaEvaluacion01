/*
 * El programa recibe el identificador y el nuevo stock mediante los argumentos 
 * de main. Accede directamente al registro con seek(), lee el nombre y el stock 
 * anterior, y modifica únicamente el campo del stock. Finalmente, muestra los 
 * datos actualizados y la variación.
 */

package tarea01;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ActualizarStock {

    public static void main(String[] args) {

        // Comprobar que se reciben dos argumentos
        if (args.length != 2) {
            System.out.println("Uso: java ActualizarStock <id> <nuevoStock>");
            return;
        }

        int id;
        int nuevoStock;

        try {
            id = Integer.parseInt(args[0]);
            nuevoStock = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("El id y el stock deben ser números enteros.");
            return;
        }

        // Validar valores
        if (id < 1) {
            System.out.println("El id debe ser mayor o igual que 1.");
            return;
        }

        if (nuevoStock < 0) {
            System.out.println("El stock no puede ser negativo.");
            return;
        }

        // Tamaños de los campos
        int longitudCodigo = 8;
        int longitudNombre = 20;
        int longitudCategoria = 12;

        // Tamaño de cada registro: 96 bytes
        int tamRegistro = 4
                + longitudCodigo * 2
                + longitudNombre * 2
                + longitudCategoria * 2
                + 4
                + 8;

        // El stock aparece después del id, código y nombre y categoría
        int desplazamientoStock = 4
                + longitudCodigo * 2
                + longitudNombre * 2
                + longitudCategoria * 2;

        try (RandomAccessFile fichero =
                     new RandomAccessFile("inventario.dat", "rw")) {

            // Comprobar que el registro existe
            long numeroRegistros = fichero.length() / tamRegistro;

            if (id > numeroRegistros) {
                System.out.println("El registro indicado no existe.");
                return;
            }

            // Acceder directamente al registro mediante seek()
            long posicionRegistro = (long) (id - 1) * tamRegistro;
            fichero.seek(posicionRegistro);

            // Leer el nombre del equipo
            fichero.skipBytes(4);

            StringBuilder nombre = new StringBuilder();

            for (int i = 0; i < longitudCodigo; i++) {
                fichero.readChar();
            }

            for (int i = 0; i < longitudNombre; i++) {
                nombre.append(fichero.readChar());
            }

            for (int i = 0; i < longitudCategoria; i++) {
                fichero.readChar();
            }

            // Situarse en el campo stock
            fichero.seek(posicionRegistro + desplazamientoStock);

            int stockAnterior = fichero.readInt();

            // Volver a la posición del stock y escribir el nuevo valor
            fichero.seek(posicionRegistro + desplazamientoStock);
            fichero.writeInt(nuevoStock);

            int variacion = nuevoStock - stockAnterior;

            System.out.println("Nombre: " + nombre.toString().trim());
            System.out.println("Stock anterior: " + stockAnterior);
            System.out.println("Stock nuevo: " + nuevoStock);
            System.out.println("Variación: " + variacion);

        } catch (IOException e) {
            System.out.println("Error al actualizar el stock: "
                    + e.getMessage());
        }
    }
}
