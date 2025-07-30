package ar.unrn.emberlords.excepciones;

/**
 * Esta excepción es lanzada cuando una carta no se encuentra en la colección global de cartas cargadas.
 */
public class ArchivoNoEncontradoException extends AxieException {
    public ArchivoNoEncontradoException(String message) {
        super(message);
    }
}
