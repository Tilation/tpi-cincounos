package ar.unrn.emberlords.game.repositorios;

import java.util.Collection;

/**
 * Repositorio generico para el registro y consulta de datos.
 * Permite guardar y buscar datos por una clave.
 * No pueden haber dos datos guardados bajo la misma clave.
 */
public interface Repositorio<K, V> {
    /**
     * Registra un valor de tipo {@link V}
     * @param clave La clave que se va a usar luego para obtener el dato.
     * @param valor El valor a guardar.
     */
    public void registrar(K clave, V valor);

    /**
     * Busca un valor por una clave, y devuelve una instancia no nula si existe.
     * @param clave La clave con la que buscar.
     * @return Una instancia o null si no se encuentra.
     */
    public V buscar(K clave);

    /**
     * Obtiene todos los valores almacenados en el repositorio.
     * @return Una colección con todos los valores del repositorio.
     */
    public Collection<V> obtenerTodos();
}
