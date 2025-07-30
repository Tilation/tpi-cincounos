package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de una criatura que fue cargada desde un archivo CSV.
 */
public class CriaturaCSV {
    public final String nombre;
    public final String clase;
    public final String back;
    public final String horn;
    public final String mouth;
    public final String tail;
    public final String ear;
    public final String eye;

    public CriaturaCSV(String[] fields) {
        this.nombre = fields[Indices.CriaturaCSV.NOMBRE];
        this.clase = fields[Indices.CriaturaCSV.CLASE];
        this.back = fields[Indices.CriaturaCSV.BACK];
        this.horn = fields[Indices.CriaturaCSV.HORN];
        this.mouth = fields[Indices.CriaturaCSV.MOUTH];
        this.tail = fields[Indices.CriaturaCSV.TAIL];
        this.ear = fields[Indices.CriaturaCSV.EAR];
        this.eye = fields[Indices.CriaturaCSV.EYE];
    }
}
