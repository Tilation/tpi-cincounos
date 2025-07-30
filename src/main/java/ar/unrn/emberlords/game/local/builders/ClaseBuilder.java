package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.enums.clases.Clase;
import ar.unrn.emberlords.game.local.csv.ClaseCSV;

/**
 * Builder que crea Clases a partir de datos CSV.
 */
public class ClaseBuilder implements EntityBuilder<ClaseCSV, Clase> {

    @Override
    public Clase buildEntity(ClaseCSV claseCSV) {
        var estadisticasBase = new Estadisticas(
            claseCSV.baseHp,
            claseCSV.baseSpeed,
            claseCSV.baseSkill,
            claseCSV.baseMorale
        );
        
        var estadisticasPorParte = new Estadisticas(
            claseCSV.parteHp,
            claseCSV.parteSpeed,
            claseCSV.parteSkill,
            claseCSV.parteMorale
        );
        return new Clase(claseCSV.nombre, estadisticasBase, estadisticasPorParte);
    }
}
