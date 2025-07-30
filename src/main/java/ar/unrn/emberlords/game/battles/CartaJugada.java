package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.cards.Carta;

/**
 * Representa una accion que hace un jugador en su turno, por ejemplo, usar
 * una carta en una criatura.
 */
public class CartaJugada {
    private final Carta carta;
    private final CriaturaBatallando objetivo;
    private final CriaturaBatallando atacante;
    
    public CartaJugada(Carta carta, CriaturaBatallando atacante, CriaturaBatallando objetivo) {
        this.carta = carta;
        this.atacante = atacante;
        this.objetivo = objetivo;
    }
    
    public Carta getCarta() {
        return carta;
    }
    
    public CriaturaBatallando getObjetivo() {
        return objetivo;
    }
    
    public CriaturaBatallando getAtacante() {
        return atacante;
    }
    
    public int getVelocidad() {
        return atacante.obtenerEstadisticas().getSpeed();
    }
}
