package ar.unrn.emberlords.game.builders;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.battles.Equipo;

/**
 * Prepara los datos y crea los equipos para la batalla.
 */
public interface EquipoFactory {

    /**
     * Hace una configuración inicial pre-creación de los equipos, teniendo en cuenta los parámetros de la aplicación
     * y los servicios del juego.
     * @param args Los parametros pasados a la ejecución del programa.
     * @param gameServiceFactory El origen de datos del juego.
     */
    void prepararEquipos(String[] args, GameServiceFactory gameServiceFactory);

    /**
     * Crea y devuelve el equipo numero 1.
     * @return Una instancia de un equipo con las criaturas del equipo 1.
     */
    Equipo crearEquipo1();

    /**
     * Crea y devuelve el equipo numero 2.
     * @return Una instancia de un equipo con las criaturas del equipo 2.
     */
    Equipo crearEquipo2();
}
