package ar.unrn.emberlords.pantallas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

/**
 * Clase de ayuda para el dibujado de elementos de la interfaz grafica.
 */
public class PantallaHelper {
    private final TextGraphics tg;
    private final TerminalSize terminalSize;
    private final int columnas;
    private final int filas;

    /**
     * Sólo instanciar y dejar que el GC se lo recicle, no persistir.
     *
     * @param tg El textgraphics derivado de la Screen.
     */
    public PantallaHelper(TextGraphics tg) {
        this.tg = tg;
        this.terminalSize = tg.getSize();
        this.filas = terminalSize.getRows();
        this.columnas = terminalSize.getColumns();
    }


    public static class OpcionesPintadoMenu {
        public static OpcionesPintadoMenu defecto() {
            return new OpcionesPintadoMenu();
        }
    }

    public static class MenuItem {

    }

    //→←▁▂▃▄▅▆▇█ ◧◨◩◪⬒⬓⬔⬕⬜⬛
    public static final String TITULO_CATALOGO_CARTAS = "   Partes ← Cartas    → Clases   ";
    public static final String TITULO_CATALOGO_CLASES = "   Cartas ← Clases    → Criaturas";
    public static final String TITULO_CATALOGO_CRIATURAS = "   Clases ← Criaturas → Partes   ";
    public static final String TITULO_CATALOGO_PARTES = "Criaturas ← Partes    → Cartas   ";
    public static final String PIE_CATALOGOS = "←→: cambiar de catalogo | ↑↓: navegar | enter: seleccionar | esc: volver";

    /**
     * Dibuja un marco de ancho 1 en la terminal con el caracter por defecto: █.
     */
    public void dibujarMarco() {
        dibujarMarco('█');
    }

    /**
     * Dibuja un marco de ancho 1 en la terminal con el caracter pasador por parametro.
     */
    public void dibujarMarco(char caracter) {
        tg.drawRectangle(new TerminalPosition(0, 0), terminalSize, caracter);
    }

    /**
     * @param titulo
     * @param centradoHorizontalmente
     */
    public void dibujarTitulo(String titulo, boolean centradoHorizontalmente) {
        dibujarTitulo(titulo, '█', centradoHorizontalmente);
    }

    public void dibujarTitulo(String titulo, char separador, boolean centradoHorizontalmente) {
        int screenWidth = columnas - 4;
        int startColumn = 0;
        if (centradoHorizontalmente) {
            startColumn = (screenWidth - titulo.length()) / 2;
        }

        tg.drawLine(0, 2, columnas, 2, separador);
        tg.putString(2 + startColumn, 1, titulo);
    }


    public static void dibujarBarrita(Screen screen, TextGraphics tg, TerminalPosition posicion, float valor, float min, float max) {
        // segun el % dibuja _▁▂▃▄▅▆▇█ haciendo regla de 3 simple

    }


    public void dibujarPie(String pie, boolean centradoHorizontalmente) {
        dibujarPie(pie, '█', centradoHorizontalmente);
    }

    public void dibujarPie(String pie, char separador, boolean centradoHorizontalmente) {
        int separatorRow = filas - 3;
        int textRow = separatorRow + 1;
        int startColumn = 0;
        if (centradoHorizontalmente) {
            startColumn = (columnas - 4 - pie.length()) / 2;
        }
        tg.drawLine(0, separatorRow, columnas, separatorRow, separador);
        tg.putString(2 + startColumn, textRow, pie);
    }
}
