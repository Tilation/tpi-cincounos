package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.partes.Parte;
import ar.unrn.emberlords.game.local.builders.ParteBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.csv.ParteCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.PartesRepositorio;
import ar.unrn.emberlords.game.repositorios.TipoPartesRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

/**
 * Repositorio local de Partes, respaldado por un archivo CSV
 */
public class RepositorioPartesLocal extends RepositorioLocalCSV<String, Parte, ParteCSV> implements PartesRepositorio {
    private final ClasesRepositorio clasesRepo;
    private final TipoPartesRepositorio tipoPartesRepo;

    public RepositorioPartesLocal(ClasesRepositorio clasesRepo, TipoPartesRepositorio tipoPartesRepo) {
        this.clasesRepo = clasesRepo;
        this.tipoPartesRepo = tipoPartesRepo;
    }

    @Override
    protected EntityBuilder<ParteCSV, Parte> entityBuilderFactoryMethod() {
        return new ParteBuilder(clasesRepo, tipoPartesRepo);
    }

    @Override
    protected CSVParser.ParseLineaCSV<ParteCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<ParteCSV>() {
            @Override
            public ParteCSV parseCSV(String[] campos) {
                return new ParteCSV(campos);
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
    public void registrar(String clave, Parte valor) {
        String clave2 = String.format("%s-%s", clave,valor.getTipo().getNombre());
        super.registrar(clave2, valor);
    }

    @Override
    public Parte buscar(String clave) {
        System.out.printf("Buscando %s...%n", clave);
        var resultado = super.buscar(clave);
        if (resultado == null) {
            throw new IllegalArgumentException(String.format("No se pudo encontrar la parte con la clave '%s'%n", clave));
        }

        return resultado;
    }
}
