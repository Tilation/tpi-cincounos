package ar.unrn.emberlords.pantallas.implementaciones;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaFactory;
import ar.unrn.emberlords.pantallas.PantallaHelpers;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class PantallaCatalogoPartes implements Pantalla {
    private final GameServiceFactory gameServiceFactory;
    private final Pantalla pantallaAnterior;
    private final PantallaFactory pantallaFactory;

    public PantallaCatalogoPartes(GameServiceFactory gameServiceFactory, Pantalla pantallaAnterior, PantallaFactory pantallaFactory) {
        this.gameServiceFactory = gameServiceFactory;
        this.pantallaAnterior = pantallaAnterior;
        this.pantallaFactory = pantallaFactory;
    }

    @Override
    public void render(Screen screen) {
        TextGraphics tg = screen.newTextGraphics();

        PantallaHelpers.dibujarMarco(screen, tg);
        PantallaHelpers.dibujarTitulo(screen, tg, PantallaHelpers.TITULO_CATALOGO_PARTES);

        // TODO: mostrar listado de partes, navegacion paginada, opcion para crear, editar y eliminar partes.
        //       los nombres de las partes tienen que estar coloreados segun la clase.
    }

    @Override
    public Pantalla handleInput(KeyStroke key) {
        Pantalla retorno = this;

        switch (key.getKeyType()){
            case KeyType.Escape: {
                retorno = pantallaAnterior;
                break;
            }
            case KeyType.ArrowLeft: {
                retorno = pantallaFactory.crearCatalogoCriaturas(pantallaAnterior);
                break;
            }
            case KeyType.ArrowRight: {
                retorno = pantallaFactory.crearCatalogoCartas(pantallaAnterior);
            }
        }

        return retorno;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
