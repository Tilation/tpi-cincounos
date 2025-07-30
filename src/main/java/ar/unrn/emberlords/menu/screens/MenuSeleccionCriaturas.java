package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.battles.Batalla;
import ar.unrn.emberlords.game.battles.JugadorConsola;
import ar.unrn.emberlords.game.battles.JugadorIA;
import ar.unrn.emberlords.game.criaturas.Criatura;
import ar.unrn.emberlords.menu.MenuResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Pantalla del menú principal del juego
 */
public class MenuSeleccionCriaturas extends GameScreen {
    private Random random;
    private List<Criatura> seleccionados;
    private List<Criatura> disponibles;

    public MenuSeleccionCriaturas() {
        seleccionados = new ArrayList<>();
        disponibles = new ArrayList<>();
        random = new Random();
    }

    @Override
    public void render() {
        GameScreenUtils.clearScreen();

        GameScreenUtils.dibujarTitulo("Seleccionar criaturas");
        GameScreenUtils.drawBoxLine("Selecciona 3 criaturas diferentes:");

        if (seleccionados.size() > 0) {

            GameScreenUtils.drawBoxLine("Seleccionadas: ");
            for(var criatura : seleccionados) {
                GameScreenUtils.drawBoxLine(String.format("[ %s ]", criatura.getNombre()));
            }
        }

        int opcion = 1;
        for (var criatura : disponibles) {
            GameScreenUtils.drawBoxLine(String.format("(%d) - %s", opcion, criatura.getNombre()));
            opcion = opcion + 1;
        }

        GameScreenUtils.drawBoxBottom();
    }
    
    @Override
    public MenuResult handleInput(String input) {
        MenuResult resultado = MenuResult.continuar();;
        try {
            var opcion = Integer.parseInt(input);
            if (opcion > 0 && opcion <= disponibles.size()) {
                seleccionados.add(disponibles.remove(opcion-1));
            }

            if (seleccionados.size() == 3) {
                var batalla = new Batalla(gameServiceFactory);
                var jugador1 = new JugadorConsola(gameServiceFactory);
                jugador1.asignarCriaturas(seleccionados);

                var jugador2 = new JugadorIA(gameServiceFactory);
                while(disponibles.size() > 3) {
                    disponibles.remove(random.nextInt(disponibles.size()));
                }
                jugador2.asignarCriaturas(disponibles);

                batalla.asignarJugadores(jugador1,jugador2);
                return MenuResult.cambiarA(new MenuBatalla(batalla));
            }
        } catch (NumberFormatException e) {
            resultado = MenuResult.continuar();
        }

        return resultado;
    }


    @Override
    public void configurarPantalla() {
        // Agrego todas las criaturas para que esten disponibles
        disponibles.addAll(gameServiceFactory.repositorioCriaturas().obtenerTodos());
    }
}
