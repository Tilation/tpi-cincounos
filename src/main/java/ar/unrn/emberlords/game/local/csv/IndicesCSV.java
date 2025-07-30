package ar.unrn.emberlords.game.local.csv;

/**
 * Agrupa los indices de los campos de los archivos CSV
 * para proveer un tipado fuerte y evitar errores de indexación. Tambien para
 * poder hacer cambios en los indices de los campos de manera que rompa menos.
 * Cada campo de las clases contenidas representa una columna en el archivo CSV.
 */
public class IndicesCSV {
    /**
     * Constructor privado porque esta clase se usa de manera estatica para proveer tipado fuerte a
     * los indices de cada archivo CSV.
     */
    private IndicesCSV() { }

    /**
     * Indices de los campos de las cartas en el archivo cards.csv.
     */
    public static class CartasCSV {
        public static final int NOMBRE = 1;
        public static final int CLASE = 3;
        public static final int TIPOATAQUE = 4;
        public static final int COSTOENERGIA = 5;
        public static final int DAMAGE = 6;
        public static final int ESCUDO = 7;
        public static final int DESCRIPCION = 8;
    }

    /*
     * Indices de los campos de las clases en el archivo clases.csv.
     */
    public static class ClaseCSV {
        public static final int NOMBRE = 0;
        public static final int PARTEHP = 1;
        public static final int PARTEMORALE = 2;
        public static final int PARTESKILL = 3;
        public static final int PARTESPEED = 4;
        public static final int BASEHP = 5;
        public static final int BASEMORALE = 6;
        public static final int BASESKILL = 7;
        public static final int BASESPEED = 8;
    }

    /**
     * Indices de los campos de las criaturas en el archivo criatures.csv.
     */
    public static class CriaturaCSV {
        public static final int NOMBRE = 0;
        public static final int CLASE = 1;
        public static final int BACK = 2;
        public static final int HORN = 3;
        public static final int MOUTH = 4;
        public static final int TAIL = 5;
        public static final int EAR = 6;
        public static final int EYE = 7;
    }

    /**
     * Indices de los campos de las efectividades en el archivo efectividades.csv.
     */
    public static class EfectividadCSV {
        public static final int CLASEATACANTE = 0;
        public static final int CLASEDEBILCONTRA = 1;
        public static final int CLASEFUERTECONTRA = 2;
    }

    /**
     * Indicces de los campos de las partes en el archivo cards.csv.
     */
    public static class ParteCSV {
        public static final int NOMBRE = 0;
        public static final int CLASE = 1;
        public static final int CARTA = 2;
        public static final int TIPOPARTE = 3;
    }
    /**
     * Indices de los campos de los tipos de ataque en el archivo ataques.csv.
     */
    public static class TIpoAtaqueCSV {
        public static final int NOMBRE = 0;
    }
}
