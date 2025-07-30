package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.menu.MenuResult;

/**
 * Pantalla base para todas las pantallas/ menues del juego
 * Incluye funciones helper para reutilizar codigo para dibujar y de entrada
 * para ser usado por los demas menus y pantallas.
 */
public abstract class GameScreen {
    protected GameServiceFactory gameServiceFactory;

    /**
     * Muestra  esta pantalla por consola.
     *
     * Se llama justo antes de llamar a la funcion que captura la entrada por consola.
     */
    public abstract void render();

    /**
     * Indica si esta pantalla deberia guardarse en el historial para cuando se haga una vuelta a la pantalla anterior.
     * @return un booleano indicando si deberia ser volatil, true indica que no se guarda en el historial.
     */
    public abstract boolean esVolatil();

    /**
     * Permite a la pantalla ejecutar sus comportamientos reaccionando a una entrada del usuario.
     *
     * Handle input se llama cuando se detecta una entrada del teclado por parte del usuario.
     * Esta funcion debe retornar el comportamiento segun la entrada que dio el usuario.
     * Ya sea cambiar de pantalla, modificar su propio estado, etc.
     * @param input La cadena con la entrada del usuario, puede ser un numero, una letra, o texto, siempre en mayusculas.
     * @return Un resultado de la accion, MenuResult tiene metodos factory para crear estos estados.
     */
    public abstract MenuResult handleInput(String input);

    /**
     * Permite a la pantalla hacer una configuracion inicial justo despues de ser creada.
     *
     * Esta funcion se llama al momento de cambiar a esta pantalla, en este momento el gameServiceFactory
     * ya está asignado y ahora se deberia obtener la informacion para mostrar y ejecutar esta pantalla.
     */
    public abstract void configurarPantalla();

    /**
     * Configura el servicefactory para la pantalla recientemente creada y llama al metodo abstracto
     * para notificar a la pantalla que ya puede configurarse.
     * @param gameServiceFactory La entidad factory de servicios del juego.
     */
    public void alCrearPantalla(GameServiceFactory gameServiceFactory) {
        this.gameServiceFactory = gameServiceFactory;
        configurarPantalla();
    }
}
