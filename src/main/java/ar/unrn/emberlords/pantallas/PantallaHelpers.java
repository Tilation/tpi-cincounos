package ar.unrn.emberlords.pantallas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;

/**
 * Clase de ayuda para el dibujado de elementos de la interfaz grafica.
 */
public class PantallaHelpers {

    public static class OpcionesPintadoMenu {
        public static OpcionesPintadoMenu defecto() {
            return new OpcionesPintadoMenu();
        }
    }

    public static class MenuItem {

    }

    //→←
    public static final String TITULO_CATALOGO_CARTAS =    "   Partes ← Cartas    → Clases   ";
    public static final String TITULO_CATALOGO_CLASES =    "   Cartas ← Clases    → Criaturas";
    public static final String TITULO_CATALOGO_CRIATURAS = "   Clases ← Criaturas → Partes   ";
    public static final String TITULO_CATALOGO_PARTES =    "Criaturas ← Partes    → Cartas   ";
    public static final String PIE_CATALOGOS = "←→: cambiar de catalogo | ↑↓: navegar | enter: seleccionar | esc: volver";
    public static void dibujarMarco(Screen screen, TextGraphics tg) {
        TerminalSize size = screen.getTerminalSize();
        tg.drawRectangle(new TerminalPosition(0,0), size, '█');
    }

    public static void dibujarMenu(TerminalPosition posicion, MenuItem[] items, int itemSeleccionado) {
        dibujarMenu(posicion, items, itemSeleccionado, OpcionesPintadoMenu.defecto());
    }

    public static void dibujarMenu(TerminalPosition posicion, MenuItem[] items, int itemSeleccionado, OpcionesPintadoMenu opcionesPintadoMenu) {

    }

    public static void dibujarMarco(Screen screen, TextGraphics tg, char caracter) {
        TerminalSize size = screen.getTerminalSize();
        tg.drawRectangle(new TerminalPosition(0,0), size, caracter);
    }

    public static void dibujarPie(Screen screen, TextGraphics tg, String pie, char separador, boolean centradoHorizontalmente) {
        TerminalSize size = screen.getTerminalSize();
        int ancho = size.getColumns();
        int alto = size.getRows();

        int startRow = size.getRows() - 2;
        int separatorRow = alto - 3;
        int textRow = separatorRow + 1;
        int startColumn = 0;
        if (centradoHorizontalmente) {
            startColumn = (ancho - 4 - pie.length()) / 2;
        }

        tg.drawLine(0, separatorRow, size.getColumns(), separatorRow, separador);
        tg.putString(2 + startColumn,textRow, pie);
    }

    public static void dibujarTitulo(Screen screen, TextGraphics tg, String titulo, boolean centradoHorizontalmente) {
        dibujarTitulo(screen, tg, titulo, '█', centradoHorizontalmente);
    }

    public static void dibujarTitulo(Screen screen, TextGraphics tg, String titulo, char separador, boolean centradoHorizontalmente) {
        TerminalSize size = screen.getTerminalSize();
        int screenWidth = size.getColumns() - 4;

        int startColumn = 0;
        if (centradoHorizontalmente) {
            startColumn = (screenWidth - titulo.length()) / 2;
        }

        tg.drawLine(0,2,size.getColumns(), 2,separador);
        tg.putString(2 + startColumn,1,titulo);
    }
}
