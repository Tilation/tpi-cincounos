package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.clases.Clase;
import ar.unrn.emberlords.game.local.builders.ClaseBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.csv.ClaseCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

public class RepositorioClasesLocal extends RepositorioLocalCSV<String, Clase, ClaseCSV> implements ClasesRepositorio {

    @Override
    protected EntityBuilder<ClaseCSV, Clase> entityBuilderFactoryMethod() {
        return new ClaseBuilder();
    }

    @Override
    protected CSVParser.ParseLineaCSV<ClaseCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<ClaseCSV>() {
            @Override
            public ClaseCSV parseCSV(String[] campos) {
                return new ClaseCSV(campos);
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
    public void registrar(String clave, Clase valor) {
        super.registrar(clave, valor);
    }

    @Override
    public Clase buscar(String clave) {
        return super.buscar(clave);
    }
}
