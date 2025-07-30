package ar.unrn.emberlords.menu.screens;

public class GameScreenUtils {
    private static final int BOX_WIDTH = 79;
    private static final String BOX_TOP = "╔═══════════════════════════════════════════════════════════════════════════════╗";
    private static final String BOX_BOTTOM = "╚═══════════════════════════════════════════════════════════════════════════════╝";
    private static final String BOX_SEPARATOR = "╠═══════════════════════════════════════════════════════════════════════════════╣";
    private static final String BOX_SIDE = "║";

    /**
     * Limpia la pantalla y posiciona el cursor en la esquina superior izquierda
     */
    public static void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    /**
     * Dibuja un encabezado dentro de una caja.
     * @param titulo El titulo a mostrar dentro de la caja.
     */
    public static void dibujarTitulo(String titulo) {
        System.out.println(BOX_TOP);
        drawBoxLine(titulo, true);
        System.out.println(BOX_SEPARATOR);
    }

    /**
     * Dibuja el encabezado simple de una pantalla
     * @param screenTitle Título de la pantalla
     */
    public static void drawSimpleHeader(String screenTitle) {
        System.out.println(BOX_TOP);
        drawBoxLine(screenTitle, true);
        System.out.println(BOX_SEPARATOR);
    }

    /**
     * Dibuja una línea dentro de una caja con texto centrado o alineado a la izquierda
     * @param texto Texto a mostrar
     * @param centrar Si el texto debe estar centrado
     */
    public static void drawBoxLine(String texto, boolean centrar) {
        String formattedText;
        if (centrar) {
            int padding = (BOX_WIDTH - texto.length()) / 2;
            formattedText = " ".repeat(Math.max(0, padding)) + texto;
        } else {
            formattedText = " " + texto; // Un espacio de margen izquierdo
        }
        System.out.printf("%s %-77s %s%n", BOX_SIDE, formattedText.substring(0, Math.min(formattedText.length(), 77)), BOX_SIDE);
    }

    /**
     * Dibuja una línea dentro de una caja con texto alineado a la izquierda
     * @param text Texto a mostrar
     */
    public static void drawBoxLine(String text) {
        drawBoxLine(text, false);
    }

    /**
     * Dibuja una línea vacía dentro de una caja
     */
    public static void drawEmptyBoxLine() {
        drawBoxLine("");
    }

    /**
     * Dibuja un separador horizontal dentro de la caja
     */
    public static void drawBoxSeparator() {
        System.out.println(BOX_SEPARATOR);
    }

    /**
     * Dibuja el borde inferior de la caja
     */
    public static void drawBoxBottom() {
        System.out.println(BOX_BOTTOM);
    }

    /**
     * Dibuja las opciones de menú con indicador de selección
     * @param options Array de opciones a mostrar
     * @param showNumbers Si mostrar números de opción
     */
    public static void drawMenuOptions(String[] options, boolean showNumbers) {
        for (int i = 0; i < options.length; i++) {
            String numeroOpcion = showNumbers ? "(" + (i + 1) + ") " : "";
            String line = String.format("%s%s", numeroOpcion, options[i]);
            drawBoxLine(line);
        }
    }

    /**
     * Dibuja las opciones de menú con indicador de selección (sin números)
     * @param options Array de opciones a mostrar
     */
    public static void drawMenuOptions(String[] options) {
        drawMenuOptions(options, false);
    }

    /**
     * Dibuja información de estado (turno, energía, etc.)
     * @param leftInfo Información del lado izquierdo
     * @param rightInfo Información del lado derecho
     */
    public static void drawStatusLine(String leftInfo, String rightInfo) {
        String line = String.format("%-40s%37s", leftInfo, rightInfo);
        drawBoxLine(line);
    }

    /**
     * Dibuja una línea con dos columnas de información
     * @param leftColumn Información de la columna izquierda
     * @param rightColumn Información de la columna derecha
     */
    public static void drawTwoColumnLine(String leftColumn, String rightColumn) {
        String line = String.format("%-38s %-38s", leftColumn, rightColumn);
        drawBoxLine(line);
    }

    /**
     * Solicita confirmación del usuario para una acción
     * @param message Mensaje de confirmación a mostrar
     */
    public static void showConfirmationMessage(String message) {
        drawEmptyBoxLine();
        drawBoxLine(message);
        drawBoxLine("Presiona ENTER para continuar...");
    }
}
