package ar.unrn.emberlords;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * Clase que une los componentes graficos e interactivos del juego, administra la pantalla activa, le envia la entrada
 * del usuario y hace los cambios de pantalla si es necesario.
 */
public class LanternaGame {
    private final GameServiceFactory gameServiceFactory;
    private Pantalla pantallaActual;
    private Screen screen;
    private final PantallaFactory pantallaFactory;

    public LanternaGame(GameServiceFactory gameServiceFactory, PantallaFactory pantallaFactory) {
        this.gameServiceFactory = gameServiceFactory;
        this.pantallaFactory = pantallaFactory;
    }

    public void start() {
        try {
            // Configuracion de lanterna
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null); // Hide cursor

            // Inicio el juego en el menu principal
            pantallaActual = pantallaFactory.crearMenuPrincipal();

            // Loop del juego, logica de pantallas, dibujado y transiciones a otras pantallas.
            while (pantallaActual != null) {

                // Dibujo la pantalla
                screen.clear();
                pantallaActual.render(screen);
                screen.refresh();

                // Manejo de la entrada del usuario
                KeyStroke key = screen.readInput();
                if (key != null) {
                    Pantalla nextState = pantallaActual.handleInput(key);

                    // State Transition Logic
                    if (nextState != pantallaActual && nextState != null) {
                        pantallaActual.onHide();
                        pantallaActual = nextState;
                        pantallaActual.onShow();
                    } else if (nextState == null) {
                        // Null implies exit
                        break;
                    }
                }
            }

            // Cleanup
            screen.stopScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
