package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.game.battles.Batalla;
import ar.unrn.emberlords.game.battles.CartaJugada;
import ar.unrn.emberlords.game.battles.CriaturaBatallando;
import ar.unrn.emberlords.game.cards.Carta;
import ar.unrn.emberlords.game.enums.partes.Parte;
import ar.unrn.emberlords.menu.MenuResult;

import java.util.List;

/**
 * Pantalla de batalla del juego
 */
public class MenuBatalla extends GameScreen {
    private final Batalla batalla;

    /// Indica el estado del turno del jugador, si es true significa que tiene una carta seleccionada y esta buscando un objetivo
    private boolean seleccionandoObjetivo = false;
    /// Indica la carta que el usuario selecciono
    private Carta cartaSeleccionada = null;

    public MenuBatalla(Batalla batalla) {
        this.batalla = batalla;
        batalla.inicializar();
    }

    @Override
    public boolean esVolatil() {
        return true;
    }

    @Override
    public void render() {
        GameScreenUtils.clearScreen();
        GameScreenUtils.drawSimpleHeader("Pelea");
        GameScreenUtils.drawEmptyBoxLine();
        mostrarEstadoPelea();
        GameScreenUtils.drawBoxBottom();
    }

    private void mostrarEstadoPelea() {
        var criaturasJ1 = batalla.getCriaturasJugador1();
        var criaturasJ2 = batalla.getCriaturasJugador2();

        GameScreenUtils.drawEmptyBoxLine();

        GameScreenUtils.drawTwoColumnLine("JUGADOR 1", "JUGADOR 2 (PC)");
        GameScreenUtils.drawBoxSeparator();

        int maxSize = Math.max(criaturasJ1.size(), criaturasJ2.size());
        for (int i = 0; i < maxSize; i++) {
            String j1Info = "";
            String j2Info = "";

            if (i < criaturasJ1.size()) {
                var criatura = criaturasJ1.get(i);
                String estado = criatura.estaViva() ? "[v]" : "[m]";
                j1Info = String.format("(%d) %s %s [%d/%d HP]",
                        i,
                        estado,
                        criatura.getCriatura().getNombre(),
                        criatura.getVidaActual(),
                        criatura.getVidaMaxima());
            }

            if (i < criaturasJ2.size()) {
                var criatura = criaturasJ2.get(i);
                String estado = criatura.estaViva() ? "[v]" : "[m]";
                j2Info = String.format("(%d) %s %s [%d/%d HP]",
                        i + criaturasJ1.size(),
                        estado,
                        criatura.getCriatura().getNombre(),
                        criatura.getVidaActual(),
                        criatura.getVidaMaxima());
            }

            GameScreenUtils.drawTwoColumnLine(j1Info, j2Info);
        }

        if (batalla.esJugador1Humano()) {
            if (seleccionandoObjetivo) {
                mostrarDetalleCartaSeleccionada();
            } else {
                mostrarCartasJugador();
            }
            mostrarCartasSeleccionadas();
            if (batalla.getEnergiaJugador1() == 0) {
                GameScreenUtils.drawBoxSeparator();
                GameScreenUtils.drawBoxLine("NO QUEDA MAS ENERGIA:");
            }
        }
    }

    private void mostrarDetalleCartaSeleccionada() {
        var cartas = batalla.getCartasJugador1();
        var criaturas = batalla.getCriaturasJugador1();

        if (cartaSeleccionada != null) {
            var carta = cartaSeleccionada;
            var criaturaPropietaria = encontrarCriaturaPropietaria(carta, criaturas);

            GameScreenUtils.drawBoxSeparator();
            GameScreenUtils.drawBoxLine("DETALLE DE CARTA SELECCIONADA:");
            GameScreenUtils.drawBoxLine("Nombre: " + carta.getNombre());
            GameScreenUtils.drawBoxLine("Daño: " + carta.getDamage());
            GameScreenUtils.drawBoxLine("Costo de energía: " + carta.getCostoEnergia());
            GameScreenUtils.drawBoxLine("Escudo: " + carta.getEscudo());
            GameScreenUtils.drawBoxLine("Descripción: " + carta.getDescripcion());
            GameScreenUtils.drawBoxLine("Criatura: " + criaturaPropietaria.getCriatura().getNombre());
            GameScreenUtils.drawBoxLine("Ahora debe seleccionar un indice de criatura");
        }
    }

    private void mostrarCartasSeleccionadas() {
        var cartas = batalla.getCartasReservadasJugador1();
        if (!cartas.isEmpty()) {
            GameScreenUtils.drawBoxSeparator();
            GameScreenUtils.drawBoxLine("CARTAS SELECCIONADAS PARA JUGAR:");
            for (CartaJugada jugada : cartas) {
                GameScreenUtils.drawBoxLine(
                        String.format("- %s (Criatura: %s) -> Objetivo: %s",
                                jugada.getCarta().getNombre(),
                                jugada.getAtacante().getCriatura().getNombre(),
                                jugada.getObjetivo().getCriatura().getNombre()
                        )
                );
            }
        }
    }

    private void mostrarCartasJugador() {
        var cartas = batalla.getCartasJugador1();
        var energia = batalla.getEnergiaJugador1();
        var criaturas = batalla.getCriaturasJugador1();

        GameScreenUtils.drawBoxSeparator();
        GameScreenUtils.drawBoxLine(String.format("CARTAS EN MANO | ENERGIA: %d", energia));
        GameScreenUtils.drawEmptyBoxLine();

        if (cartas.isEmpty()) {
            GameScreenUtils.drawBoxLine("No tienes cartas en la mano.");
        } else {
            for (int i = 0; i < cartas.size(); i++) {
                var carta = cartas.get(i);
                var criaturaPropietaria = encontrarCriaturaPropietaria(carta, criaturas);
                String estado = (energia >= carta.getCostoEnergia()) ? "" : " (SIN ENERGÍA)";

                String cartaInfo = String.format("%d. %s | Daño: %d | Costo: %d | Criatura: %s%s",
                        i + 1,
                        carta.getNombre(),
                        carta.getDamage(),
                        carta.getCostoEnergia(),
                        criaturaPropietaria.getCriatura().getNombre(),
                        estado);

                GameScreenUtils.drawBoxLine(cartaInfo);
            }
        }
    }

    /**
     * Busca una criatura en la lista de criaturas a partir de una carta.
     *
     * @param carta     La carta que pertenece a alguna de las criaturas
     * @param criaturas La lista de criaturas a la que una es dueña de la carta.
     * @return null o una criatura
     */
    private CriaturaBatallando encontrarCriaturaPropietaria(Carta carta, List<CriaturaBatallando> criaturas) {
        var parteCarta = carta.getParte();
        CriaturaBatallando retorno = null;

        for (var criaturaBatallando : criaturas) {
            var criatura = criaturaBatallando.getCriatura();

            // Verificar cada parte de la criatura
            if (parteCoincide(parteCarta, criatura.getOjos()) ||
                    parteCoincide(parteCarta, criatura.getOrejas()) ||
                    parteCoincide(parteCarta, criatura.getEspalda()) ||
                    parteCoincide(parteCarta, criatura.getCuernos()) ||
                    parteCoincide(parteCarta, criatura.getBoca()) ||
                    parteCoincide(parteCarta, criatura.getCola())) {
                retorno = criaturaBatallando;
            }
        }

        return retorno;
    }

    private boolean parteCoincide(Parte parte1, Parte parte2) {
        return parte1.equals(parte2);
    }

    @Override
    public MenuResult handleInput(String input) {
        MenuResult resultado = MenuResult.continuar();

        if (batalla.esJugador1Humano()) {
            if (batalla.getEnergiaJugador1() == 0) {
                batalla.ejecutarTurno();
            } else {
                try {
                    var opcion = Integer.parseInt(input);
                    if (opcion == 0) {
                        batalla.ejecutarTurno();
                    } else if (seleccionandoObjetivo) {
                        if (opcion >= batalla.getCriaturasJugador1().size()) {
                            if (opcion < batalla.getCriaturasJugador1().size() + batalla.getCriaturasJugador2().size()) {
                                var objetivo = batalla.getCriaturasJugador2().get(opcion - batalla.getCriaturasJugador1().size());
                                if (batalla.getCriaturasVivasJ2().contains(objetivo)) {
                                    var criatura = encontrarCriaturaPropietaria(cartaSeleccionada, batalla.getCriaturasVivasJ1());
                                    var cartaJugada = new CartaJugada(cartaSeleccionada, criatura, objetivo);
                                    batalla.forzarJugadaJ1(cartaJugada);
                                    seleccionandoObjetivo = false;
                                    cartaSeleccionada = null;
                                }
                            }
                        }
                    } else {
                        cartaSeleccionada = batalla.getCartasJugador1().get(opcion - 1);
                        if (cartaSeleccionada != null) {
                            seleccionandoObjetivo = true;
                        }
                    }
                } catch (NumberFormatException e) {
                    // dejar que continue y vuelva a pedir entrada. No es error fatal.
                }
            }
        }

        if (batalla.estaTerminada()) {
            resultado = MenuResult.cambiarA(new GanoJugador(batalla.getGanador()));
        }

        return resultado;
    }

    @Override
    public void configurarPantalla() {

    }
}
