package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de un tipo de parte que fue cargada desde un archivo CSV.
 */
public class TipoParteCSV {
    public final String nombre;

    public TipoParteCSV(String[] campos) {
        this.nombre = campos[Indices.TipoParteCSV.NOMBRE];
    }
}
