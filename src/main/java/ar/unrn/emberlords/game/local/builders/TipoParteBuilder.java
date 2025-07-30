package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.partes.TipoParte;
import ar.unrn.emberlords.game.local.csv.TipoParteCSV;

/**
 * Builder que crea Tipos de Partes a partir de datos CSV.
 */
public class TipoParteBuilder implements EntityBuilder<TipoParteCSV, TipoParte> {

    @Override
    public TipoParte buildEntity(TipoParteCSV tipoParteCSV) {
        return new TipoParte(tipoParteCSV.nombre);
    }
}
