package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Repositorio abstracto que permite cargar los datos desde un archivo csv y usar un builder / parser
 * para construir las entidades.
 */
public abstract class RepositorioLocalCSV<TClave, TValor extends ClaveVisitable<TClave>, TCSV> extends RepositorioLocal<TClave, TValor> {

    /**
     * Crea un EntityBuilder hecho para transformar la entidad CSV a la entidad final.
     * @return Un EntityBuilder nuevo configurado.
     */
    protected abstract EntityBuilder<TCSV, TValor> entityBuilderFactoryMethod();

    protected abstract CSVParser.ParseLineaCSV<TCSV> csvLineParser();

    protected abstract ClaveVisitor<TClave> claveVisitor();

    @Override
    public void cargarDesdeArchivo(Path ruta) throws IOException {
        CSVParser<TCSV, TValor> parser = new CSVParser<TCSV, TValor>(entityBuilderFactoryMethod());
        List<TValor> items = parser.parse(ruta,csvLineParser());
        ClaveVisitor<TClave> visitor = claveVisitor();
        for(var item : items) {
            var clave = visitor.visitar(item);
            registrar(clave, item);
        }
    }
}
