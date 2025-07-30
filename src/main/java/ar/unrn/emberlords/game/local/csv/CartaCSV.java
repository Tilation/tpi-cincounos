package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de una carta que fue cargada desde un archivo CSV.
 */
public class CartaCSV {
    public final String nombre;
    public final String clase;
    public final String tipoAtaque;
    public final String parteOwner;
    public final String tipoParteOwner;
    public final int costoEnergia;
    public final int damage;
    public final int escudo;
    public final String descripcion;

    /**
     * Constructor que recibe un array de String representando
     * los campos de una carta en un archivo CSV.
     * @param campos Arreglo de campos de cada linea del csv.
     */
    public CartaCSV(String[] campos) {
        this.parteOwner = campos[Indices.ParteCSV.NOMBRE];
        this.tipoParteOwner = campos[Indices.ParteCSV.TIPOPARTE];
        this.nombre = campos[Indices.CartasCSV.NOMBRE];
        this.clase = campos[Indices.CartasCSV.CLASE];
        this.tipoAtaque = campos[Indices.CartasCSV.TIPOATAQUE];
        this.costoEnergia = Integer.parseInt(campos[Indices.CartasCSV.COSTOENERGIA]);
        this.damage = Integer.parseInt(campos[Indices.CartasCSV.DAMAGE]);
        this.escudo = Integer.parseInt(campos[Indices.CartasCSV.ESCUDO]);
        this.descripcion = campos[Indices.CartasCSV.DESCRIPCION];
    }
}
