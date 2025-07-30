package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.partes.TipoParte;
import ar.unrn.emberlords.game.local.builders.TipoParteBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.csv.TipoParteCSV;
import ar.unrn.emberlords.game.repositorios.TipoPartesRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

/**
 * Repositorio local de Tipos de parte, respaldado por un archivo CSV
 */
public class RepositorioTipoPartesLocal extends RepositorioLocalCSV<String, TipoParte, TipoParteCSV> implements TipoPartesRepositorio {

    @Override
    protected EntityBuilder<TipoParteCSV, TipoParte> entityBuilderFactoryMethod() {
        return new TipoParteBuilder();
    }

    @Override
    protected CSVParser.ParseLineaCSV<TipoParteCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<TipoParteCSV>() {
            @Override
            public TipoParteCSV parseCSV(String[] campos) {
                return new TipoParteCSV(campos);
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

    @Override
    public void registrar(String clave, TipoParte valor) {
        super.registrar(clave, valor);
    }

    @Override
    public TipoParte buscar(String clave) {
        return super.buscar(clave);
    }
}
