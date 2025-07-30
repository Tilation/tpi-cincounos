package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.cards.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JugadorIA extends JugadorAbstracto {
    private Random random;
    private List<CriaturaBatallando> equipoEnemigo;
    
    public JugadorIA(GameServiceFactory servicios) {
        super(servicios);
        this.random = new Random();
    }
    
    public void setEquipoEnemigo(List<CriaturaBatallando> equipoEnemigo) {
        this.equipoEnemigo = equipoEnemigo;
    }

    @Override
    List<CartaJugada> jugarTurno() {
        List<CartaJugada> acciones = new ArrayList<>();
        
        System.out.println("\n=== TURNO DE LA IA ===");
        
        // Jugar cartas mientras tenga energía y cartas
        while (energia > 0 && !mano.isEmpty()) {
            // Buscar cartas que se puedan jugar
            List<Carta> cartasJugables = new ArrayList<>();
            for (Carta carta : mano) {
                if (puedeJugarCarta(carta)) {
                    cartasJugables.add(carta);
                }
            }
            
            if (cartasJugables.isEmpty()) {
                break; // No puede jugar más cartas
            }
            
            // Seleccionar carta aleatoria
            Carta cartaSeleccionada = cartasJugables.get(random.nextInt(cartasJugables.size()));
            
            // Seleccionar criatura atacante aleatoria
            var criaturasVivas = getCriaturasVivas();
            if (criaturasVivas.isEmpty()) {
                break;
            }
            CriaturaBatallando atacante = criaturasVivas.get(random.nextInt(criaturasVivas.size()));
            
            // Seleccionar objetivo enemigo aleatorio
            var enemigosVivos = equipoEnemigo.stream()
                    .filter(CriaturaBatallando::estaViva)
                    .collect(java.util.stream.Collectors.toList());
            
            if (enemigosVivos.isEmpty()) {
                break;
            }
            CriaturaBatallando objetivo = enemigosVivos.get(random.nextInt(enemigosVivos.size()));
            
            // Crear acción
            CartaJugada accion = new CartaJugada(cartaSeleccionada, atacante, objetivo);
            acciones.add(accion);
            
            // Gastar energía y quitar carta de la mano
            gastarEnergia(cartaSeleccionada.getCostoEnergia());
            mano.remove(cartaSeleccionada);
            discardPile.add(cartaSeleccionada);
            
            System.out.printf("IA jugó %s con %s atacando a %s.%n", 
                cartaSeleccionada.getNombre(),
                atacante.getCriatura().getNombre(),
                objetivo.getCriatura().getNombre());
        }
        
        if (acciones.isEmpty()) {
            System.out.println("La IA no pudo jugar ninguna carta.");
        }
        
        return acciones;
    }
}
