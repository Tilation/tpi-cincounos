package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.ataques.TipoAtaque;
import ar.unrn.emberlords.game.local.csv.TipoAtaqueCSV;

/**
 * Builder que crea Tipos de ataque a partir de datos CSV.
 */
public class TipoAtaqueBuilder implements EntityBuilder<TipoAtaqueCSV, TipoAtaque> {

    @Override
    public TipoAtaque buildEntity(TipoAtaqueCSV tipoAtaqueCSV) {
        return new TipoAtaque(tipoAtaqueCSV.nombre);
    }
}
