package ar.unrn.emberlords.excepciones;

/**
 * Esta excepción es lanzada cuando una carta no se encuentra en la colección global de cartas cargadas.
 */
public class AxieYaRegistrado extends AxieException {
    public AxieYaRegistrado(String message) {
        super(message);
    }
}
