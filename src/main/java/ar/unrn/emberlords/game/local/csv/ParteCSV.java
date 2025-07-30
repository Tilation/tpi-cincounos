package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de una parte que fue cargada desde un archivo CSV.
 */
public class ParteCSV {
    public final String nombre;
    public final String clase;
    public final String carta;
    public final String tipoParte;

    public ParteCSV(String[] campos) {
        this.nombre = campos[Indices.ParteCSV.NOMBRE];
        this.carta = campos[Indices.ParteCSV.CARTA];
        this.tipoParte = campos[Indices.ParteCSV.TIPOPARTE];
        this.clase = campos[Indices.ParteCSV.CLASE];
    }
}
