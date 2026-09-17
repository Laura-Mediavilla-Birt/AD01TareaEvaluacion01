/*
 * El programa recibe una categoría por argumentos, recorre los registros del fichero y 
 * muestra los equipos que pertenecen a ella. La comparación no distingue entre mayúsculas 
 * y minúsculas y, al finalizar, indica el número de coincidencias encontradas.
 */

package tarea01;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ListarCategoria {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Uso: java ListarCategoria <categoria>");
            return;
        }

        String categoriaBuscada = args[0];

        int longitudCodigo = 8;
        int longitudNombre = 20;
        int longitudCategoria = 12;

        int tamRegistro = 4
                + longitudCodigo * 2
                + longitudNombre * 2
                + longitudCategoria * 2
                + 4
                + 8;

        int encontrados = 0;

        try (RandomAccessFile fichero =
                     new RandomAccessFile("inventario.dat", "r")) {

            long numeroRegistros = fichero.length() / tamRegistro;

            for (int i = 0; i < numeroRegistros; i++) {

                int id = fichero.readInt();

                String codigo = leerCadena(fichero, longitudCodigo);
                String nombre = leerCadena(fichero, longitudNombre);
                String categoria = leerCadena(fichero, longitudCategoria);

                int stock = fichero.readInt();
                double precio = fichero.readDouble();

                if (categoria.trim().equalsIgnoreCase(categoriaBuscada)) {

                    System.out.println("Id: " + id);
                    System.out.println("Código: " + codigo.trim());
                    System.out.println("Nombre: " + nombre.trim());
                    System.out.println("Categoría: " + categoria.trim());
                    System.out.println("Stock: " + stock);
                    System.out.println("Precio: " + precio + " €");
                    System.out.println("-------------------------");

                    encontrados++;
                }
            }

            if (encontrados == 0) {
                System.out.println("No se han encontrado equipos de esa categoría.");
            } else {
                System.out.println("Total de equipos encontrados: " + encontrados);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el inventario: "
                    + e.getMessage());
        }
    }

    private static String leerCadena(
            RandomAccessFile fichero,
            int longitud) throws IOException {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            texto.append(fichero.readChar());
        }

        return texto.toString();
    }
}