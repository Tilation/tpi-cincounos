package ar.unrn.emberlords.pantallas.implementaciones.editores;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaFactory;
import ar.unrn.emberlords.pantallas.PantallaHelper;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class PantallaEditorEquipos implements Pantalla {
    private final GameServiceFactory gameServiceFactory;
    private final Pantalla pantallaAnterior;
    private final PantallaFactory pantallaFactory;

    public PantallaEditorEquipos(GameServiceFactory gameServiceFactory, Pantalla pantallaAnterior, PantallaFactory pantallaFactory) {
        this.gameServiceFactory = gameServiceFactory;
        this.pantallaAnterior = pantallaAnterior;
        this.pantallaFactory = pantallaFactory;
    }

    @Override
    public void render(Screen screen) {
        TextGraphics tg = screen.newTextGraphics();
        PantallaHelper helper = new PantallaHelper(tg);
        helper.dibujarMarco();
        helper.dibujarTitulo( PantallaHelper.TITULO_CATALOGO_CARTAS, true);
        helper.dibujarPie( PantallaHelper.PIE_CATALOGOS, '█', true);
        // TODO: mostrar listado de cartas, navegacion paginada, opcion para crear, editar y eliminar cartas.
        //       los nombres de las cartas tienen que estar coloreados segun la clase.
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
                retorno = pantallaFactory.crearCatalogoPartes(pantallaAnterior);
                break;
            }
            case KeyType.ArrowRight: {
                retorno = pantallaFactory.crearCatalogoClases(pantallaAnterior);
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
