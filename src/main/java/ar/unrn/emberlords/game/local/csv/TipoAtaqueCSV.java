package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de un tipo de ataque que fue cargada desde un archivo CSV.
 */
public class TipoAtaqueCSV {
    public final String nombre;

    public TipoAtaqueCSV(String[] campos) {
        this.nombre = campos[Indices.TIpoAtaqueCSV.NOMBRE];
    }
}
