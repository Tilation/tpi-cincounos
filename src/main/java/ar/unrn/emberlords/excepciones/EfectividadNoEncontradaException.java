package ar.unrn.emberlords.excepciones;

/**
 * Esta excepción es lanzada cuando no se encuentra la efectividad de una clase contra otra.
 * Al momento de atacar, si no se encuentra la efectividad, se lanza esta excepción.
 */
public class EfectividadNoEncontradaException extends AxieException {
    public EfectividadNoEncontradaException(String message) {
        super(message);
    }
}
