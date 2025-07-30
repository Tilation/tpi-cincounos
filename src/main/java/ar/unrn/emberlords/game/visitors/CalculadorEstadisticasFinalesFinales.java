package ar.unrn.emberlords.game.visitors;

import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.criaturas.TieneEstadisticas;

/**
 * CalculadorEstadisticasFinalesFinales es un visitante que calcula las estadísticas finales de algo.
 * Las Criaturas usan esto para saber sus estadisticas finales segun sus Clases y partes.
 * Las Criaturas batallando usan esto para saber estas mismas estadisticas pero tambien aplicadas los
 * bufos y debufos que podrian llegar a tener.
 */
public class CalculadorEstadisticasFinalesFinales implements EstadisticasFinalesVisitor {
    Estadisticas estadisticas;

    public CalculadorEstadisticasFinalesFinales() {
        estadisticas = new Estadisticas(0, 0, 0, 0);
    }

    @Override
    public void visitarEstadisticas(TieneEstadisticas estadisticas) {
        this.estadisticas = this.estadisticas.Sumar(estadisticas.obtenerEstadisticas());
    }

    @Override
    public Estadisticas getEstadisticas() {
        return estadisticas;
    }
}
