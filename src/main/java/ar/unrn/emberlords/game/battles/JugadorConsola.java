package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.cards.Carta;

import java.util.*;

public class JugadorConsola extends JugadorAbstracto {
    public JugadorConsola(GameServiceFactory servicios) {
        super(servicios);
    }

    @Override
    protected List<CartaJugada> jugarTurnoInterno(List<CriaturaBatallando> equipoEnemigo) {
       return Collections.emptyList();
    }
}
