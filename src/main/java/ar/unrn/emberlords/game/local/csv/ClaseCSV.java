package ar.unrn.emberlords.game.local.csv;

/**
 * Se encarga de dar una representación con tipado fuerte de
 * los campos de una clase que fue cargada desde un archivo CSV.
 */
public class ClaseCSV {
    public final String nombre;
    public final int parteHp;
    public final int parteMorale;
    public final int parteSkill;
    public final int parteSpeed;

    public final int baseHp;
    public final int baseMorale;
    public final int baseSkill;
    public final int baseSpeed;

    public ClaseCSV(String[] campos) {
        this.nombre = campos[Indices.ClaseCSV.NOMBRE];
        this.parteHp = Integer.parseInt(campos[Indices.ClaseCSV.PARTEHP]);
        this.parteMorale= Integer.parseInt(campos[Indices.ClaseCSV.PARTEMORALE]);
        this.parteSkill= Integer.parseInt(campos[Indices.ClaseCSV.PARTESKILL]);
        this.parteSpeed= Integer.parseInt(campos[Indices.ClaseCSV.PARTESPEED]);
        this.baseHp= Integer.parseInt(campos[Indices.ClaseCSV.BASEHP]);
        this.baseMorale= Integer.parseInt(campos[Indices.ClaseCSV.BASEMORALE]);
        this.baseSkill= Integer.parseInt(campos[Indices.ClaseCSV.BASESKILL]);
        this.baseSpeed= Integer.parseInt(campos[Indices.ClaseCSV.BASESPEED]);
    }
}
