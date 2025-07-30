package ar.unrn.emberlords;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Esta clase organiza los archivos del juego locales, dando un tipado estatico a cada archivo.
 */
public class FilePaths {
    private FilePaths() {
    }

    private static final String root;

    static {
        root = System.getProperty("user.dir");
    }

    public static Path getRootDirectory() {
        return Paths.get(root);
    }

    /**
     * Este archivo tiene la definicion y referencia entre partes, y cartas.
     * Tiene la definicion de la parte, su tipo y clase; ademas de la definicion de la carta, con todas
     * sus caracteristicas.
     */
    public static Path getCardsFile() {
        return Paths.get(root, "cards.csv");
    }

    /**
     * Este archivo tiene las criaturas disponibles para usar, pre creadas, en una batalla local.
     */
    public static Path getAxiesFile() {
        return Paths.get(root, "criatures.csv");
    }

    /**
     * Este archivo tiene los diferentes tipos de partes que puede tener una criatura.
     */
    public static Path getTipoPartesFile() {
        return Paths.get(root, "tipo_partes.csv");
    }

    /**
     * Este archivo tiene la definicion de cada clase, sus Estadisticas base y Estadisticas de
     * cada parte de esa clase.
     */
    public static Path getClasesFile() {
        return Paths.get(root, "clases.csv");
    }

    /**
     * Este archivo tiene las efectividades de cada clase al atacar o defender contra otra.
     */
    public static Path getEfectividadClasesFile() {
        return Paths.get(root, "efectividades.csv");
    }

    /**
     * Este archivo tiene los diferentes tipos de ataque que puede tener una carta.
     */
    public static Path getTipoAtaquesFile() {
        return Paths.get(root, "ataques.csv");
    }
}
