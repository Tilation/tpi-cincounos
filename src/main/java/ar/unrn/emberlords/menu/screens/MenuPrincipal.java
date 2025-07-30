package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.menu.MenuResult;

/**
 * Pantalla del menú principal del juego
 */
public class MenuPrincipal extends GameScreen {
    
    private final String[] opciones = {
        "Jugar contra IA",
        "Ayuda",
        "Salir"
    };

    @Override
    public void render() {
        GameScreenUtils.clearScreen();
        
        // Header
        GameScreenUtils.dibujarTitulo("Menu");
        
        // Descripción del juego
        GameScreenUtils.drawBoxLine("Un juego de combate por turnos inspirado en Axie Infinity.");
        GameScreenUtils.drawBoxLine("Controla equipos de 3 criaturas con diferentes partes.");
        GameScreenUtils.drawBoxLine("Cada parte otorga cartas únicas para la batalla.");
        GameScreenUtils.drawBoxSeparator();
        
        // Opciones del menú
        GameScreenUtils.drawMenuOptions(opciones, true);

        GameScreenUtils.drawBoxBottom();
    }

    @Override
    public boolean esVolatil() {
        return false;
    }

    @Override
    public MenuResult handleInput(String input) {
        switch (input) {
            case "1": // Jugar
                return MenuResult.cambiarA(new MenuSeleccionCriaturas());

            case "2": // Ayuda
                return MenuResult.cambiarA(new MenuAyuda());

            case "3": // Salir
                return MenuResult.salir();

            default:
                return MenuResult.continuar();
        }
    }

    @Override
    public void configurarPantalla() {
        // No hace falta configurar nada
    }
}
