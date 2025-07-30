package ar.unrn.emberlords.excepciones;

/**
 * Esta excepción es lanzada cuando un repositorio no se pudo cargar correctamente.
 */
public class CargaRepositorioException extends AxieException {
    public CargaRepositorioException(String message) {
        super(message);
    }
}
