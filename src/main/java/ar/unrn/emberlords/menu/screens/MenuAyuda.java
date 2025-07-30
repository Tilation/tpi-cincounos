package ar.unrn.emberlords.menu.screens;

import ar.unrn.emberlords.menu.MenuResult;

/**
 * Pantalla del menú principal del juego
 */
public class MenuAyuda extends GameScreen {
    @Override
    public void render() {
        GameScreenUtils.clearScreen();
        GameScreenUtils.drawSimpleHeader("AYUDA");
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawBoxLine("EMBER LORDS");
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawBoxLine("OBJETIVO:");
        GameScreenUtils.drawBoxLine("  Derrota al equipo enemigo usando estrategia y las cartas de tus criaturas");
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawBoxLine("MECÁNICAS BÁSICAS:");
        GameScreenUtils.drawBoxLine("  • Cada criatura tiene 6 partes: Back, Horn, Mouth, Tail, Ear, Eye");
        GameScreenUtils.drawBoxLine("  • Cada parte otorga una carta única con diferentes habilidades");
        GameScreenUtils.drawBoxLine("  • Las estadísticas incluyen: HP, Speed, Skill, Morale");
        GameScreenUtils.drawBoxLine("  • El sistema de tipos afecta el daño (Aquatic/Plant/Beast/Bug/Bird/Reptile)");
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawBoxLine("ENERGÍA:");
        GameScreenUtils.drawBoxLine("  • Cada turno tienes energía limitada para usar cartas");
        GameScreenUtils.drawBoxLine("  • Algunas cartas otorgan energía adicional");
        GameScreenUtils.drawEmptyBoxLine();
        GameScreenUtils.drawBoxSeparator();
        GameScreenUtils.drawBoxLine("Presiona cualquier tecla para volver al menu anterior...");
        GameScreenUtils.drawBoxBottom();
    }
    
    @Override
    public MenuResult handleInput(String input) {
        return MenuResult.salir();
    }

    @Override
    public void configurarPantalla() {

    }
}
