package ar.unrn.emberlords.game.criaturas;

/**
 * Interfaz que define un objeto que tiene estadísticas.
 * Permite obtener las estadísticas de un objeto que implementa esta interfaz.
 */
public interface TieneEstadisticas {
    /**
     * Retorna las estadísticas del objeto que implementa esta interfaz.
     * Esto puede ser utilizado por visitantes o para cálculos internos.
     *
     * @return Una instancia de las estadisticas.
     */
    Estadisticas obtenerEstadisticas();
}
