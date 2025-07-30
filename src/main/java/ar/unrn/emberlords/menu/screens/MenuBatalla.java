package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.game.battles.Batalla;
import ar.unrn.emberlords.menu.MenuResult;

/**
 * Pantalla de batalla del juego
 */
public class MenuBatalla extends GameScreen {
    private final Batalla batalla;

    public MenuBatalla(Batalla batalla){
        this.batalla = batalla;
    }

    @Override
    public void render() {
        GameScreenUtils.clearScreen();
        
        // Header
        GameScreenUtils.drawSimpleHeader("Pelea");
        GameScreenUtils.drawEmptyBoxLine();
        mostrarEstadoPelea();
        GameScreenUtils.drawBoxBottom();
    }
    
    private void mostrarEstadoPelea() {
        var criaturasJ1 = batalla.getCriaturasJugador1();
        var criaturasJ2 = batalla.getCriaturasJugador2();

        GameScreenUtils.drawEmptyBoxLine();
        
        GameScreenUtils.drawTwoColumnLine("JUGADOR 1", "JUGADOR 2 (PC)");
        GameScreenUtils.drawBoxSeparator();
        
        int maxSize = Math.max(criaturasJ1.size(), criaturasJ2.size());
        for (int i = 0; i < maxSize; i++) {
            String j1Info = "";
            String j2Info = "";
            
            if (i < criaturasJ1.size()) {
                var criatura = criaturasJ1.get(i);
                String estado = criatura.estaViva() ? "[v]" : "[m]";
                j1Info = String.format("%s %s [%d/%d HP]", 
                    estado,
                    criatura.getCriatura().getNombre(),
                    criatura.getVidaActual(),
                    criatura.getVidaMaxima());
            }
            
            if (i < criaturasJ2.size()) {
                var criatura = criaturasJ2.get(i);
                String estado = criatura.estaViva() ? "[v]" : "[m]";
                j2Info = String.format("%s %s [%d/%d HP]", 
                    estado,
                    criatura.getCriatura().getNombre(),
                    criatura.getVidaActual(),
                    criatura.getVidaMaxima());
            }
            
            GameScreenUtils.drawTwoColumnLine(j1Info, j2Info);
        }
    }
    
    @Override
    public MenuResult handleInput(String input) {
        if (batalla.estaTerminada()) {
            return MenuResult.salir(); // Salir cuando la batalla esté terminada
        } else {
            switch (input.trim()) {
                case "1": // Ver estado
                case "2": // Salir
                case "0":
                    return MenuResult.salir();
                default:
                    return MenuResult.continuar();
            }
        }
    }

    @Override
    public void configurarPantalla() {

    }
}
