package ar.unrn.emberlords.game.visitors;

import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.criaturas.TieneEstadisticas;

/**
 * Interfaz para un visitante que calcula las estadísticas finales de algo que implementa ITieneEstadisticas.
 * Este visitante permite sumar las estadísticas de diferentes partes del cuerpo o criaturas
 */
public interface EstadisticasFinalesVisitor {
    /**
     * Deberia visitar un objeto que implementa ITieneEstadisticas y sumar sus estadísticas
     * a las estadísticas finales del visitante.
     *
     * @param estadisticas
     */
    void visitarEstadisticas(TieneEstadisticas estadisticas);

    /**
     * Deberia retornar las estadísticas finales calculadas por el visitante.
     *
     * @return
     */
    Estadisticas getEstadisticas();
}
