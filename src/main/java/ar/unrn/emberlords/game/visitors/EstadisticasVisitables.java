package ar.unrn.emberlords.game.visitors;

import ar.unrn.emberlords.game.criaturas.Estadisticas;

/**
 * Interfaz que define un objeto que puede ser visitado por un visitante de estadísticas finales.
 * Permite obtener las estadísticas finales de un objeto que implementa esta interfaz
 */
public interface EstadisticasVisitables {
    /**
     * Deberia aceptar un visitante de estadísticas finales y permitirle calcular las estadísticas finales
     * del objeto que implementa esta interfaz.
     *
     * @param visitor El visitante que calculará las estadísticas finales.
     * @return Las estadísticas finales calculadas por el visitante.
     */
    Estadisticas obtenerEstadisticasFinales(EstadisticasFinalesVisitor visitor);
}
