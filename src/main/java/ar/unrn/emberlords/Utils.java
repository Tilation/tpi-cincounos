package ar.unrn.emberlords;

import ar.unrn.emberlords.excepciones.ArchivoNoEncontradoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * Clase de utilidades para operaciones comunes en el juego.
 */
public class Utils {
    private Utils() {
    }

    /**
     * Intenta convertir una cadena a un entero.
     * Si la conversión falla, devuelve un valor por defecto.
     *
     * @param value        La cadena a convertir.
     * @param defaultValue El valor por defecto a devolver en caso de error.
     * @return El entero convertido o el valor por defecto si la conversión falla.
     */
    public static int tryParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            // Log exception.
            return defaultValue;
        }
    }

    /**
     * Intenta leer un archivo.
     *
     * @param archivo La ruta al archivo a leer.
     * @return El contenido del archivo.
     * @throws ArchivoNoEncontradoException Cuando no se pudo leer el archivo.
     */
    public static String intentarLeerArchivo(Path archivo) {
        try {
            return Files.readString(FilePaths.getClasesFile());
        } catch (IOException e) {
            throw new ArchivoNoEncontradoException(String.format("El archivo '%s' no se pudo leer.", archivo));
        }
    }

    /**
     * Divide una cadena en partes, mantiene los espacios en blanco, quita los espacios al inicio y al final de cada parte,
     * y convierte cada parte a minúsculas.
     *
     * @param text  La cadena de texto a dividir.
     * @param regex El patrón de expresión regular por el cual dividir la cadena.
     * @return Un arreglo de cadenas con las partes divididas, sin espacios al inicio y al final, y en minúsculas.
     */
    public static String[] splitTrimLower(String text, String regex) {
        String[] parts = text.split(regex, Integer.MAX_VALUE);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim().toLowerCase();
        }
        return parts;
    }

    /**
     * Convierte una tabla HTML a formato CSV. Use esto para importar los datos de las cartas a CSV.
     *
     * @param html
     * @return
     */
    public static String convertTableToCSV(String html) {
        if (html == null || html.trim().isEmpty()) {
            throw new IllegalArgumentException("Input HTML cannot be null or empty.");
        }

        StringBuilder csv = new StringBuilder();
        Pattern rowPattern = Pattern.compile("<tr>(.*?)</tr>", Pattern.DOTALL);
        Pattern cellPattern = Pattern.compile("<td>(.*?)</td>", Pattern.DOTALL);

        // Extract and process table rows
        String[] rows = html.split("</tr>");
        for (String row : rows) {
            if (row.trim().isEmpty()) continue;

            // Skip the header row if needed, or process it as well
            // For this implementation, we include all rows

            // Process cells in the current row
            StringBuilder rowCsv = new StringBuilder();
            String[] cells = row.split("</td>");
            for (String cell : cells) {
                String content = cell.replaceAll("<.*?>", "").trim(); // Remove HTML tags and trim whitespace
                if (!content.isEmpty()) {
                    rowCsv.append(content).append(",");
                }
            }

            // Remove trailing comma and add new line
            if (rowCsv.length() > 0) {
                rowCsv.setLength(rowCsv.length() - 1);
                csv.append(rowCsv).append("\n");
            }
        }

        return csv.toString();
    }
}
