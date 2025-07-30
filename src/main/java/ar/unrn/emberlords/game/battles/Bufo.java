package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.criaturas.TieneEstadisticas;

/**
 * Esta clase no se usa aún, pero representa un efecto de batalla que puede aplicarse a una criatura.
 * El efecto Bufo puede tener estadísticas asociadas y una duración en turnos.
 * Obtener las estadísticas de un bufo chequea que no haya expirado, sino devuelve estadísticas en 0,
 * que no deberían afectar la jugabilidad en caso de pasarse de largo.
 * Usar el metodo obtenerEstadisticas de esta clase, reduce sus turnos de uso, hasta que llega a 0 y expira.
 */
public class Bufo implements TieneEstadisticas {
    private Estadisticas estadisticas;
    private int turnosRestantes;

    public Bufo(int turnosRestantes, Estadisticas estadisticas) {
        this.turnosRestantes = turnosRestantes;
        this.estadisticas = estadisticas;
    }

    /**
     * Devuelve si el bufo está expirado y debe ser removido.
     *
     * @return true si está expirado, false de lo contrario.
     */
    public boolean estaExpirado() {
        return turnosRestantes <= 0;
    }

    @Override
    public Estadisticas obtenerEstadisticas() {
        var retorno = new Estadisticas(0, 0, 0, 0);
        if (!estaExpirado()) {
            retorno = estadisticas;
            turnosRestantes = turnosRestantes - 1;
        }
        return retorno;
    }
}
