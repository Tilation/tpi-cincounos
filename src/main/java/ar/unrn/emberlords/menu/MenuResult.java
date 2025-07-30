package ar.unrn.emberlords.menu;

import ar.unrn.emberlords.menu.screens.GameScreen;

/**
 * Esta clase indica un cambio de estado al realizar una accion en un menu, puede ser para cambiar a otro menu,
 * para ir al menu anterior, o para seguir en este mismo menu.
 */
public class MenuResult {

    /**
     * Los diferentes tipos de acciones.
     */
    public static enum AccionMenu {
        CONTINUAR,
        SALIR,
        CAMBIAR
    }

    private AccionMenu action;
    private GameScreen nextMenu; // If switching

    public AccionMenu getTipoResultado() {
        return action;
    }

    public GameScreen getNextMenu() {
        return nextMenu;
    }

    /**
     * Crea un resultado que indica al sistema que no hay que hacer ningun cambio y que continue ejecutando el menu actual.
     * @return El MenuResult configurado para continuar en el menu actual.
     */
    public static MenuResult continuar() {
        MenuResult result = new MenuResult();
        result.action = AccionMenu.CONTINUAR;
        return result;
    }

    /**
     * Crea un resultado que indica al sistema que tiene que salir al menu anterior.
     * @return El MenuResult configurado para esta salida al menu anterior.
     */
    public static MenuResult salir() {
        MenuResult result = new MenuResult();
        result.action = AccionMenu.SALIR;
        return result;
    }

    /**
     * Crea un resultado que indica al sistema que tiene que cambiar a otro menu inmediatamente.
     * @param menu El menu al que se tiene que cambiar.
     * @return El MenuResult configurado para este cambio.
     */
    public static MenuResult cambiarA(GameScreen menu) {
        MenuResult result = new MenuResult();
        result.action = AccionMenu.CAMBIAR;
        result.nextMenu = menu;
        return result;
    }
}
