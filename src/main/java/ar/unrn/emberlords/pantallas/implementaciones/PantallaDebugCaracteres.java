package ar.unrn.emberlords.pantallas.implementaciones;

import ar.unrn.emberlords.pantallas.Pantalla;
import ar.unrn.emberlords.pantallas.PantallaFactory;
import ar.unrn.emberlords.pantallas.PantallaHelper;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class PantallaDebugCaracteres implements Pantalla {


    private final PantallaFactory pantallaFactory;
    private int offset = 0;
    public PantallaDebugCaracteres(PantallaFactory pantallaFactory) {
        this.pantallaFactory = pantallaFactory;
    }

    @Override
    public void render(Screen screen) {
        TextGraphics tg = screen.newTextGraphics();
        PantallaHelper helper = new PantallaHelper(tg);

        helper.dibujarMarco();
        helper.dibujarTitulo("Debug", true);

        helper.dibujarPie("esc: salir", '█', true);

        var terminalSize = screen.getTerminalSize();
        int columns = terminalSize.getColumns() - 4;

        for(int i = 0; i < 1024; i++){
            int col = 2 + i % columns;
            int row = 4 + (i / columns);
            try{

            tg.putString(col, row,String.valueOf((char)(offset + i)));
            } catch (RuntimeException e) {

            }
        }
    }

    void nextPage(){
        offset = offset + 1024;
    }

    void previousPage() {
        offset = offset - 1024;
        if (offset < 0) offset = 0;
    }

    @Override
    public Pantalla handleInput(KeyStroke key) {
        Pantalla retorno = this;

        switch (key.getKeyType()){
            case KeyType.ArrowRight -> nextPage();
            case KeyType.ArrowLeft -> previousPage();
            case KeyType.Escape -> retorno = pantallaFactory.crearMenuPrincipal();
        }

        return retorno; // Stay in this state
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
