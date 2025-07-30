package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.visitors.CalculadorEstadisticasFinalesFinales;
import ar.unrn.emberlords.game.criaturas.Criatura;
import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.criaturas.TieneEstadisticas;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper para una criatura que está participando en una batalla.
 * Esta clase encapsula la criatura y una lista de efectos de batalla (bufos) que se le aplican.
 * Deberia también manejar el estado de la criatura durante la batalla, como su salud, etc.
 */
public class CriaturaBatallando implements TieneEstadisticas {
    private Criatura criatura;
    private final List<Bufo> bufos;
    private int vidaActual;
    private int vidaMaxima;

    private CriaturaBatallando() {
        bufos = new ArrayList<>();
    }

    public static CriaturaBatallando crear(Criatura criatura) {
        var criaturaBatallando = new CriaturaBatallando();
        criaturaBatallando.criatura = criatura;
        criaturaBatallando.inicializarVida();
        return criaturaBatallando;
    }
    
    private void inicializarVida() {
        var estadisticas = obtenerEstadisticas();
        this.vidaMaxima = estadisticas.getHp() * 10;
        this.vidaActual = this.vidaMaxima;
    }
    
    public boolean estaViva() {
        return vidaActual > 0;
    }
    
    public void recibirDanio(int danio) {
        vidaActual = Math.max(0, vidaActual - danio);
    }
    
    public void curar(int curacion) {
        vidaActual = Math.min(vidaMaxima, vidaActual + curacion);
    }
    
    public int getVidaActual() {
        return vidaActual;
    }
    
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    
    public Criatura getCriatura() {
        return criatura;
    }

    @Override
    public Estadisticas obtenerEstadisticas() {
        var calculador = new CalculadorEstadisticasFinalesFinales();
        criatura.obtenerEstadisticasFinales(calculador);
        for (var bufo : bufos) {
            calculador.visitarEstadisticas(bufo);
        }
        return calculador.getEstadisticas();
    }
}
