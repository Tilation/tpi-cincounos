package ar.unrn.emberlords.pantallas;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

/**
 * Interfaz de pantallas.
 * Define contratos que todas las pantallas concretas deben implementar para brindar una experiencia fluida.
 * Por ejemplo: mostrar por pantalla, escuchar pulsaciones del teclado.
 */
public interface Pantalla {

    /**
     * Muestra el estado de la pantalla usando Lanterna para que el usuario la pueda ver y despues interactuar.
     * @param screen La pantalla de lanterna sobre la cual se va a dibujar el estado.
     */
    void render(Screen screen);

    /**
     * Maneja la entrada del usuario.
     * Al momento de cambiar de pantalla se llama siempre primero .onHide() de la pantalla anterior y despues .onShow() de la siguiente.
     * @param key La tecla presionada por el usuario.
     * @return La siguiente pantalla, si no hay cambio de pantalla devuelve `this`. Si se devuelve `null` se termina la ejecucion del programa.
     */
    Pantalla handleInput(KeyStroke key);

    /**
     * Se llama cuando se cambia a esta pantalla. Sirve para resumir o iniciar procesos, cambiar estados, etc.
     * Durante la vida de una pantalla, este metodo puede ser llamado multiples veces.
     * Al momento de cambiar de pantalla se llama siempre primero .onHide() de la pantalla anterior y despues .onShow() de la siguiente.
     */
    void onShow();

    /**
     * Se llama cuando se cambia a otra pantalla. Sirve para pausar o detener procesos, cambiar estados, etc.
     * Durante la vida de una pantalla, este metodo puede ser llamado multiples veces.
     * Al momento de cambiar de pantalla se llama siempre primero .onHide() de la pantalla anterior y despues .onShow() de la siguiente.
     */
    void onHide();
}
