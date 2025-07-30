package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.cards.Carta;
import ar.unrn.emberlords.game.local.builders.CartaBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.csv.CartaCSV;
import ar.unrn.emberlords.game.local.csv.Indices;
import ar.unrn.emberlords.game.repositorios.CartasRepositorio;
import ar.unrn.emberlords.game.repositorios.PartesRepositorio;
import ar.unrn.emberlords.game.repositorios.TipoAtaqueRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.util.Objects;

/**
 * Repositorio local de Cartas, respaldado por un archivo CSV
 */
public class RepositorioCartasLocal extends RepositorioLocalCSV<String, Carta, CartaCSV> implements CartasRepositorio {
    private final PartesRepositorio partesRepo;
    private final TipoAtaqueRepositorio tipoAtaqueRepo;

    public RepositorioCartasLocal(PartesRepositorio partesRepo, TipoAtaqueRepositorio tipoAtaqueRepo) {
        this.partesRepo = partesRepo;
        this.tipoAtaqueRepo = tipoAtaqueRepo;
    }

    @Override
    protected EntityBuilder<CartaCSV, Carta> entityBuilderFactoryMethod() {
        return new CartaBuilder(partesRepo, tipoAtaqueRepo);
    }

    @Override
    protected CSVParser.ParseLineaCSV<CartaCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<CartaCSV>() {
            @Override
            public CartaCSV parseCSV(String[] campos) {
                if (Objects.equals(campos[Indices.CartasCSV.NOMBRE], "")) return null;
                return new CartaCSV(campos);
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
    public void registrar(String clave, Carta valor) {
        super.registrar(clave, valor);
    }

    @Override
    public Carta buscar(String clave) {
        return super.buscar(clave);
    }
}
