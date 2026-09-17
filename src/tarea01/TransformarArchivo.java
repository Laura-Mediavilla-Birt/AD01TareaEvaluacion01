/*
 * 
 * El programa lee un archivo de texto y crea otro transformando las letras a 
 * mayúsculas, sustituyendo los números por # y manteniendo el resto de 
 * caracteres. También controla posibles errores de lectura o escritura.
 * 
 */
package tarea01;

	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;

	public class TransformarArchivo {

	    public static void main(String[] args) {

	        String archivoEntrada = "src/tarea01/entrada.txt";
	        String archivoSalida = "src/tarea01/salida.txt";

	        try (
	            FileReader lector = new FileReader(archivoEntrada);
	            FileWriter escritor = new FileWriter(archivoSalida)
	        ) {

	            int caracter;

	            // Leer carácter a carácter hasta llegar al final del archivo
	            while ((caracter = lector.read()) != -1) {

	                char letra = (char) caracter;
	                char resultado;

	                // Si es una letra, convertirla a mayúscula
	                if (Character.isLetter(letra)) {
	                    resultado = Character.toUpperCase(letra);

	                // Si es un dígito, sustituirlo por #
	                } else if (Character.isDigit(letra)) {
	                    resultado = '#';

	                // El resto de caracteres se mantienen
	                } else {
	                    resultado = letra;
	                }

	                escritor.write(resultado);
	            }

	            System.out.println("El archivo salida.txt se ha creado correctamente.");

	        } catch (IOException e) {
	            System.out.println("Error al leer o escribir el archivo: " + e.getMessage());
	        }
	 }
}
