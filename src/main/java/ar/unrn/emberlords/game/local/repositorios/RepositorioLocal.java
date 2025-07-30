package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.game.repositorios.Repositorio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Repositorio generico para el registro y consulta de datos con funcionalidad explicita para cargar los datos desde un archivo.
 * Permite guardar y buscar datos por una clave. No pueden haber dos datos guardados bajo la misma clave.
 * @param <K> La clave unica del conjunto de datos.
 * @param <V> El valor relacionado con la clave.
 */
public abstract class RepositorioLocal<K, V> implements Repositorio<K, V> {
    private final Map<K, V> items = new HashMap<>();

    /**
     * Carga los datos de un archivo.
     * @param ruta La ruta del archivo donde se encuentran los datos.
     * @throws IOException Cuando hay un problema al leer el archivo.
     */
    public abstract void cargarDesdeArchivo(Path ruta) throws IOException;

    @Override
    public void registrar(K clave, V valor) {
        if (items.containsKey(clave)) {
            throw new IllegalArgumentException(String.format("El item '%s' ya existe.", clave));
        }
        items.put(clave, valor);
    }

    @Override
    public V buscar(K clave) {
        return items.get(clave);
    }

    @Override
    public Collection<V> obtenerTodos() {
        return items.values();
    }

    protected boolean contiene(K clave) {
        return items.containsKey(clave);
    }

    protected void reemplazar(K clave, V valor) {
        items.put(clave, valor);
    }
}
