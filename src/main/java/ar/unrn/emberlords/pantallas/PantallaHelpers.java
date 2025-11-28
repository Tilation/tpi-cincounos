package ar.unrn.emberlords.pantallas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

/**
 * Clase de ayuda para el dibujado de elementos de la interfaz grafica.
 */
public class PantallaHelpers {

    public static final String TITULO_CATALOGO_CARTAS =    "   Partes <- Cartas    -> Clases   ";
    public static final String TITULO_CATALOGO_CLASES =    "   Cartas <- Clases    -> Criaturas";
    public static final String TITULO_CATALOGO_CRIATURAS = "   Clases <- Criaturas -> Partes   ";
    public static final String TITULO_CATALOGO_PARTES =    "Criaturas <- Partes    -> Cartas   ";

    public static void dibujarMarco(Screen screen, TextGraphics tg) {
        TerminalSize size = screen.getTerminalSize();
        tg.drawRectangle(new TerminalPosition(0,0), size, '█');
    }

    public static void dibujarMarco(Screen screen, TextGraphics tg, char caracter) {
        TerminalSize size = screen.getTerminalSize();
        tg.drawRectangle(new TerminalPosition(0,0), size, caracter);
    }

    public static void dibujarTitulo(Screen screen, TextGraphics tg, String titulo) {
        dibujarTitulo(screen, tg, titulo, '█');
    }

    public static void dibujarTitulo(Screen screen, TextGraphics tg, String titulo, char separador) {
        TerminalSize size = screen.getTerminalSize();
        int screenWidth = size.getColumns() - 4;

        int startColumn = (screenWidth - titulo.length()) / 2;

        tg.drawLine(0,2,size.getColumns(), 2,separador);
        tg.putString(2 + startColumn,1,titulo);
    }
}
