package ar.unrn.emberlords.pantallas.implementaciones;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaFactory;
import ar.unrn.emberlords.pantallas.PantallaHelpers;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class PantallaSeleccionTipoBatalla implements Pantalla {
    private final GameServiceFactory serviceFactory;
    private final PantallaFactory pantallaFactory;
    private int selectedOption = 0;

    private static final String[] options = {"Jugar Batalla", "Catalogos", "Ayuda", "Salir"};

    private static final int IND_JUGAR = 0;
    private static final int IND_CATALOGOS = 1;
    private static final int IND_AYUDA = 2;
    private static final int IND_SALIR = 3;

    public PantallaSeleccionTipoBatalla(GameServiceFactory serviceFactory, PantallaFactory pantallaFactory) {
        this.serviceFactory = serviceFactory;
        this.pantallaFactory = pantallaFactory;
    }

    @Override
    public void render(Screen screen) {
        TextGraphics tg = screen.newTextGraphics();

        PantallaHelpers.dibujarMarco(screen, tg);
        PantallaHelpers.dibujarTitulo(screen, tg, "███████████▓▒░ Menu principal ░▒▓███████████", true);

        PantallaHelpers.dibujarPie(screen, tg, "↑↓: navegar | enter: seleccionar | esc: salir", '█', true);

        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                // Highlight selected option
                tg.setForegroundColor(TextColor.ANSI.GREEN);
                tg.putString(12, 8 + i, "► " + options[i]);
            } else {
                // Normal option
                tg.setForegroundColor(TextColor.ANSI.WHITE);
                tg.putString(12, 8 + i, "   " + options[i]);
            }
        }
    }

    private void seleccionarOpcionAnterior(){
        selectedOption = selectedOption - 1;
        if (selectedOption < 0) selectedOption = options.length - 1;
    }

    public void seleccionarOpcionSiguiente(){
        selectedOption = selectedOption + 1;
        if (selectedOption >= options.length) selectedOption = 0;
    }

    @Override
    public Pantalla handleInput(KeyStroke key) {
        Pantalla retorno = this;

        if (key.getKeyType() == KeyType.ArrowUp) {
            seleccionarOpcionAnterior();
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
            seleccionarOpcionSiguiente();
        }
        else if (key.getKeyType() == KeyType.Enter) {
            retorno = ejecutarOpcion();
        }
        else if (key.getKeyType() == KeyType.Escape) {
            retorno = null; // Poniendo la salida
        }

        return retorno; // Stay in this state
    }

    private Pantalla ejecutarOpcion() {
        switch (selectedOption) {
            case IND_JUGAR:
                return this;

            case IND_CATALOGOS:
                return pantallaFactory.crearCatalogoCriaturas(this);

            case IND_AYUDA:
                return this;

            case IND_SALIR:
                return null;

            default:
                return this;
        }
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
