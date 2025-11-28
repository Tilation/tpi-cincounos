package ar.unrn.emberlords.pantallas.implementaciones;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaFactory;
import ar.unrn.emberlords.pantallas.PantallaHelpers;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class PantallaCatalogoCriaturas implements Pantalla {

    private final GameServiceFactory gameServiceFactory;
    private final Pantalla pantallaAnterior;
    private final PantallaFactory pantallaFactory;

    public PantallaCatalogoCriaturas(GameServiceFactory gameServiceFactory, Pantalla pantallaAnterior, PantallaFactory pantallaFactory) {
        this.gameServiceFactory = gameServiceFactory;
        this.pantallaAnterior = pantallaAnterior;
        this.pantallaFactory = pantallaFactory;
    }

    @Override
    public void render(Screen screen) {
        TextGraphics tg = screen.newTextGraphics();

        PantallaHelpers.dibujarMarco(screen, tg);
        PantallaHelpers.dibujarTitulo(screen, tg, PantallaHelpers.TITULO_CATALOGO_CRIATURAS, true);

        // TODO: mostrar listado de criaturas, navegacion paginada, opcion para crear, editar y eliminar criaturas.
        //       los nombres de las criaturas tienen que estar coloreados segun la clase.
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
                retorno = pantallaFactory.crearCatalogoClases(pantallaAnterior);
                break;
            }
            case KeyType.ArrowRight: {
                retorno = pantallaFactory.crearCatalogoPartes(pantallaAnterior);
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
