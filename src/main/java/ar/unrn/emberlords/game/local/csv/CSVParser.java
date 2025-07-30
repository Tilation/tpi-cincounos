package ar.unrn.emberlords.game.local.csv;

import ar.unrn.emberlords.Utils;
import ar.unrn.emberlords.game.builders.EntityBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Permite leer un archivo CSV y construir entidades usando un tipo intermedio que representa
 * el conjunto de campos del csv.
 * Usa un EntityBuilder para convertir del TIntermedio al TSalida, y un ParseLineaCSV para convertir
 * las lineas del CSV al tipo intermedio.
 * @param <TIntermedio>
 * @param <TSalida>
 */
public class CSVParser<TIntermedio, TSalida> {
    private final EntityBuilder<TIntermedio, TSalida> builder;

    public CSVParser(EntityBuilder<TIntermedio, TSalida> builder) {
        this.builder = builder;
    }

    public List<TSalida> parse(Path archivo, ParseLineaCSV<TIntermedio> parser) throws IOException {
        List<TSalida> retorno = new ArrayList<>();

        var contenido = Files.readString(archivo);
        Scanner scanner = new Scanner(contenido);
        int linea = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.startsWith("#") && !line.isEmpty()) {
                String[] camposCSV = Utils.splitTrimLower(line, ",");
                TIntermedio representacionCSV = parser.parseCSV(camposCSV);
                if (representacionCSV != null)
                {
                    TSalida itemConcreto = builder.buildEntity(representacionCSV);
                    retorno.add(itemConcreto);
                }
            }
        }
        return retorno;
    }

    /**
     * Permite convertir la lista de campos de una linea de un archivo CSV a una entidad.
     * Está dentro de CSVParser porque el único proposito es ser usado por el metodo parse, para hacer
     * esa primera conversion.
     * @param <TSalida> El tipo de salida a usar por el EntityBuilder como tipo intermedio.
     */
    public static abstract class ParseLineaCSV<TSalida>{
        public abstract TSalida parseCSV(String[] campos);
    }
}
