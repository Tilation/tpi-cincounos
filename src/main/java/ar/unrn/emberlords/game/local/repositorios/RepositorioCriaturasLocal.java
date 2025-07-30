package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.criaturas.Criatura;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.builders.CriaturaBuilder;
import ar.unrn.emberlords.game.local.csv.CriaturaCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.CriaturasRepositorio;
import ar.unrn.emberlords.game.repositorios.PartesRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Repositorio local de Criaturas, respaldado por un archivo CSV
 */
public class RepositorioCriaturasLocal extends RepositorioLocalCSV<String,Criatura,CriaturaCSV> implements CriaturasRepositorio {
    private final ClasesRepositorio clasesRepositorio;
    private final PartesRepositorio partesRepositorio;

    public RepositorioCriaturasLocal(ClasesRepositorio clasesRepositorio, PartesRepositorio partesRepositorio) {
        this.clasesRepositorio = clasesRepositorio;
        this.partesRepositorio = partesRepositorio;
    }

    @Override
    protected EntityBuilder entityBuilderFactoryMethod() {
        return new CriaturaBuilder(clasesRepositorio, partesRepositorio);
    }

    @Override
    protected CSVParser.ParseLineaCSV<CriaturaCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<CriaturaCSV>() {
            @Override
            public CriaturaCSV parseCSV(String[] campos) {
                return new CriaturaCSV(campos);
            }
        };
    }

    @Override
    protected ClaveVisitor<String> claveVisitor() {
        return new ClaveVisitor<String>() {
            String clave = "";
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
