package ar.unrn.emberlords.game.builders;

/**
 * Builder que a partir de una TEntrada crea una instancia de TSalida.
 */
public interface EntityBuilder<TEntrada, TSalida> {
    /**
     * Crea una instancia de TSalida a partir de una TEntrada.
     * Puede devolver NULL si la entrada no es válida.
     * @param entrada Los datos de entrada.
     */
    TSalida buildEntity(TEntrada entrada);
}
