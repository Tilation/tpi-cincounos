package ar.unrn.emberlords.pantallas.implementaciones;

import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaHelper;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public abstract class PantallaBasica implements Pantalla {

    @Override
    public final void render(Screen screen) {
        TextGraphics tg = screen.newTextGraphics();
        PantallaHelper helper = new PantallaHelper(tg);

        helper.dibujarMarco();
        helper.dibujarTitulo(buildTitulo(),true);
        helper.dibujarPie(buildPie(),true);

        subRender(screen, helper);
    }

    protected abstract String buildTitulo();
    protected abstract String buildPie();
    protected abstract void subRender(Screen screen, PantallaHelper helper);

    protected class PantallaBasicaParameters {
        
    }
}
