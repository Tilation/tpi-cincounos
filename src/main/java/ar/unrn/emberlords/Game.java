package ar.unrn.emberlords;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.menu.MenuResult;
import ar.unrn.emberlords.menu.screens.GameScreen;
import ar.unrn.emberlords.menu.screens.MenuPrincipal;

import java.util.Scanner;
import java.util.Stack;

/**
 * El sistema del juego en si, maneja la entrada por consola, las pantallas activas y la navegación.
 * Cada pantalla tiene su propia logica.
 */
public class Game {
    private final GameServiceFactory gameServiceFactory;
    private final Scanner scanner;
    private final Stack<GameScreen> historialPantallas;
    private GameScreen currentScreen;
    private boolean running = true;
    
    public Game(GameServiceFactory gameServiceFactory) {
        this.gameServiceFactory = gameServiceFactory;
        this.scanner = new Scanner(System.in);
        this.historialPantallas = new Stack<>();
        this.currentScreen = new MenuPrincipal();
    }
    
    public void run() {
        try {
            while (running && currentScreen != null) {
                currentScreen.render();
                String input = leerEntrada();
                MenuResult resultado = currentScreen.handleInput(input);
                handleResult(resultado);
            }
        } finally {
            scanner.close();
        }
    }
    
    private void handleResult(MenuResult result) {
        switch (result.getTipoResultado()) {
            case CONTINUAR: break;
            case SALIR:
                volverAlMenuAnterior();
                break;
            case CAMBIAR: 
                irAOtroMenu(result.getNextMenu());
                break;
        }
    }
    
    private String leerEntrada() {
        String input = scanner.nextLine().trim().toUpperCase();
        return input;
    }
    
    private void volverAlMenuAnterior() {
        if (!historialPantallas.isEmpty()) {
            currentScreen = historialPantallas.pop();
        } else {
            running = false;
            currentScreen = null;
        }
    }
    
    private void irAOtroMenu(GameScreen siguiente) {
        if (currentScreen != null && !currentScreen.esVolatil()) {
            historialPantallas.push(currentScreen);
        }
        siguiente.alCrearPantalla(gameServiceFactory);
        currentScreen = siguiente;
    }
}
