package ar.unrn.emberlords.excepciones;

/**
 * Esta excepción es lanzada cuando una carta no se encuentra en la colección global de cartas cargadas.
 */
public class ParteNoEncontradaException extends AxieException {
    public ParteNoEncontradaException(String message) {
        super(message);
    }
}
