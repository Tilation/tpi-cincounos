package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.cards.Carta;
import ar.unrn.emberlords.game.enums.ataques.TipoAtaque;
import ar.unrn.emberlords.game.local.builders.TipoAtaqueBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.csv.TipoAtaqueCSV;
import ar.unrn.emberlords.game.repositorios.CartasRepositorio;
import ar.unrn.emberlords.game.repositorios.TipoAtaqueRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.io.IOException;
import java.nio.file.Path;

public class RepositorioTipoAtaqueLocal extends RepositorioLocalCSV<String, TipoAtaque, TipoAtaqueCSV> implements TipoAtaqueRepositorio {

    @Override
    protected EntityBuilder<TipoAtaqueCSV, TipoAtaque> entityBuilderFactoryMethod() {
        return new TipoAtaqueBuilder();
    }

    @Override
    protected CSVParser.ParseLineaCSV<TipoAtaqueCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<TipoAtaqueCSV>() {
            @Override
            public TipoAtaqueCSV parseCSV(String[] campos) {
                return new TipoAtaqueCSV(campos);
            }
        };
    }

    @Override
    protected ClaveVisitor<String> claveVisitor() {
        return new ClaveVisitor<String>() {
            private String clave;

            @Override
            public String visitar(ClaveVisitable<String> visitable) {
                visitable.aceptarClaveVisitor(this);
                return clave;
            }

            @Override
            public void clave(String clave) {
                this.clave = clave;
            }
        };
    }
}
