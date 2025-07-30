package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.menu.MenuResult;

/**
 * Pantalla de victoria de un jugador al finalizar la batalla.
 */
public class GanoJugador extends GameScreen {
    private final String nombre;
    public GanoJugador(String nombre){
        this.nombre = nombre;
    }

    @Override
    public boolean esVolatil() {
        return true;
    }

    @Override
    public void render() {
        GameScreenUtils.clearScreen();
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawSimpleHeader("Ganó el jugador " + nombre);
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawBoxBottom();
    }
    
    @Override
    public MenuResult handleInput(String input) {
        return MenuResult.salir();
    }

    @Override
    public void configurarPantalla() {

    }
}
