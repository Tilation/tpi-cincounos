package ar.unrn.emberlords.pantallas;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.pantallas.implementaciones.*;

/**
 * Clase que se encarga de la fabricacion y configuracion de las pantallas para ser usadas.
 */
public class PantallaFactory {
    private final GameServiceFactory gameServiceFactory;

    /**
     * Construye la fabrica de pantalla y guarda una instancia del servicio de los recursos del juego
     * para embeber en las pantallas que se van a fabricar de ser necesario.
     * @param gameServiceFactory El servicio de recursos del juego donde estan las criaturas, cartas, partes, etc.
     */
    public PantallaFactory(GameServiceFactory gameServiceFactory)
    {
        this.gameServiceFactory = gameServiceFactory;
    }

    /**
     * Crea una pantalla que muestra el menu principal del juego.
     * @return Una instancia valida del menu principal.
     */
    public Pantalla crearMenuPrincipal() {
        return new PantallaMenuPrincipal(gameServiceFactory, this);
    }

    /**
     * Crea una pantalla que muestra el catalogo con las criaturas.
     * @param pantallaAnterior La pantalla a la cual se va a volver al salir.
     * @return Una instancia valida de la pantalla.
     */
    public Pantalla crearCatalogoCriaturas(Pantalla pantallaAnterior){
        return new PantallaCatalogoCriaturas(gameServiceFactory, pantallaAnterior, this);
    }

    /**
     * Crea una pantalla que muestra el catalogo con las cartas.
     * @param pantallaAnterior La pantalla a la cual se va a volver al salir.
     * @return Una instancia valida de la pantalla.
     */
    public Pantalla crearCatalogoCartas(Pantalla pantallaAnterior) {
        return new PantallaCatalogoCartas(gameServiceFactory, pantallaAnterior, this);
    }

    /**
     * Crea una pantalla que muestra el catalogo con las clases.
     * @param pantallaAnterior La pantalla a la cual se va a volver al salir.
     * @return Una instancia valida de la pantalla.
     */
    public Pantalla crearCatalogoClases(Pantalla pantallaAnterior){
        return new PantallaCatalogoClases(gameServiceFactory, pantallaAnterior, this);
    }

    /**
     * Crea una pantalla que muestra el catalogo con las partes.
     * @param pantallaAnterior La pantalla a la cual se va a volver al salir.
     * @return Una instancia valida de la pantalla.
     */
    public Pantalla crearCatalogoPartes(Pantalla pantallaAnterior){
        return new PantallaCatalogoPartes(gameServiceFactory, pantallaAnterior, this);
    }

    /**
     * Crea una pantalla que cierra el juego.
     * @return Retorna null.
     */
    public Pantalla crearSalidaDelJuego() {
        return null;
    }
}
