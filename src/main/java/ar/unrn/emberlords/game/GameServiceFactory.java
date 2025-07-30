package ar.unrn.emberlords.game;

import ar.unrn.emberlords.game.battles.Batalla;
import ar.unrn.emberlords.game.builders.EquipoFactory;
import ar.unrn.emberlords.game.repositorios.*;
import ar.unrn.emberlords.menu.screens.GameScreen;

/**
 * Permite definir cómo se obtienen los servicios y repositorios necesarios para
 * el juego.
 * Permite intercambiar diferentes fuentes de datos al momento de crear una
 * instancia de Game.
 * La naturaleza de los métodos es de siempre retornar la misma instancia del servicio.
 */
public interface GameServiceFactory {

    /**
     * Obtiene el repositorio de cartas.
     * @return Una instancia de CartasRepositorio (siempre la misma)
     */
    CartasRepositorio repositorioCartas();

    /**
     * Obtiene el repositorio de clases.
     * @return Una instancia de ClasesRepositorio (siempre la misma)
     */
    ClasesRepositorio repositorioClases();

    /**
     * Obtiene el repositorio de criaturas.
     * @return Una instancia de CriaturasRepositorio (siempre la misma)
     */
    CriaturasRepositorio repositorioCriaturas();

    /**
     * Obtiene el repositorio de partes.
     * @return Una instancia de PartesRepositorio (siempre la misma)
     */
    PartesRepositorio repositorioPartes();

    /**
     * Obtiene el repositorio de tipos de ataques.
     * @return Una instancia de TipoAtaqueRepositorio (siempre la misma)
     */
    TipoAtaqueRepositorio repositorioTipoAtaques();

    /**
     * Obtiene el repositorio de tipos de partes.
     * @return Una instancia de TipoPartesRepositorio (siempre la misma)
     */
    TipoPartesRepositorio repositorioTipoPartes();

    /**
     * Obtiene el repositorio de las efectividades entre clases.
     * @return Una instancia de EfectividadesRepositorio (siempre la misma)
     */
    EfectividadesRepositorio repositorioEfectividades();

    /**
     * Obtiene el builder de equipos.
     * @return Una instancia de EquipoFactory (siempre la misma)
     */
    EquipoFactory equipoFactory();
}
