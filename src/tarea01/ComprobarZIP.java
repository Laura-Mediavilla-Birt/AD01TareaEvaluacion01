package tarea01;

import java.io.FileInputStream;
import java.io.IOException;

public class ComprobarZIP {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Debes indicar la ruta del fichero.");
            return;
        }

        String ruta = args[0];

        try (FileInputStream entrada = new FileInputStream(ruta)) {

            byte[] cabecera = new byte[4];

            int bytesLeidos = entrada.read(cabecera);

            if (bytesLeidos < 4) {
                System.out.println("El fichero contiene menos de cuatro bytes.");
            } else if (
                (cabecera[0] & 0xFF) == 80 &&
                (cabecera[1] & 0xFF) == 75 &&
                (cabecera[2] & 0xFF) == 3 &&
                (cabecera[3] & 0xFF) == 4
            ) {
                System.out.println("La cabecera es compatible con un fichero ZIP.");
            } else {
                System.out.println("La cabecera no corresponde a la firma esperada.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}