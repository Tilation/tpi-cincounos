package ar.unrn;

import ar.unrn.emberlords.game.local.LocalGameServiceFactory;
import ar.unrn.emberlords.Game;

/**
 * Fuentes usadas para el proyecto:
 * https://loadedgamer.com/axie-infinity-beginners-guide/
 * https://loadedgamer.com/axie-body-parts-list/
 * https://app.calconic.com/public/calculator/61377138511e8a0021daa1e3?layouts=true
 * https://skymavis.notion.site/Axie-Infinity-Classic-Guides-1e1e5bd1dc564dbb933cab911717f656
 * https://skymavis.notion.site/How-to-use-Axies-cafb383fb2854f4da98d74716df221d8
 * https://gitbook.axiequest.com/game/axies/class
 * https://tacter.substack.com/p/how-to-calculate-damage-on-axie-infinity
 * https://docs.google.com/spreadsheets/d/1WnsEE3jG3P5gUxnqSH31c6r0ejXM5UkPITKZA8sJprA/edit?gid=0#gid=0
 */
public class GameApp {
    public static void main(String[] args) {
        try {
            LocalGameServiceFactory serviceFactory = new LocalGameServiceFactory();
            Game game = new Game(serviceFactory);
            game.run();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
