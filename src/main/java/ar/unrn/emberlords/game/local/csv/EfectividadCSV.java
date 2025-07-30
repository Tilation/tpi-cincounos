package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de una efectividad que fue cargada desde un archivo CSV.
 */
public class EfectividadCSV {
    public final String claseAtacante;
    public final String claseDebilContra;
    public final String claseFuerteContra;


    public EfectividadCSV(String[] fields) {
        this.claseAtacante = fields[Indices.EfectividadCSV.CLASEATACANTE];
        this.claseDebilContra = fields[Indices.EfectividadCSV.CLASEDEBILCONTRA];
        this.claseFuerteContra = fields[Indices.EfectividadCSV.CLASEFUERTECONTRA];
    }
}
