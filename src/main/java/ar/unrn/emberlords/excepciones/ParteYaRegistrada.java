package ar.unrn.emberlords.excepciones;

/**
 * Esta excepción es lanzada cuando una carta no se encuentra en la colección global de cartas cargadas.
 */
public class ParteYaRegistrada extends AxieException {
    public ParteYaRegistrada(String message) {
        super(message);
    }
}
