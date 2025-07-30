package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.cards.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JugadorConsola extends JugadorAbstracto {
    private Scanner scanner;
    private List<CriaturaBatallando> equipoEnemigo;

    public JugadorConsola(GameServiceFactory servicios) {
        super(servicios);
        this.scanner = new Scanner(System.in);
    }
    
    public void setEquipoEnemigo(List<CriaturaBatallando> equipoEnemigo) {
        this.equipoEnemigo = equipoEnemigo;
    }

    @Override
    List<CartaJugada> jugarTurno() {
        List<CartaJugada> acciones = new ArrayList<>();
        
        System.out.println("\n=== TU TURNO ===");
        mostrarEstadoActual();
        
        boolean turnoTerminado = false;
        while (!turnoTerminado && energia > 0 && !mano.isEmpty()) {
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Jugar una carta");
            System.out.println("2. Terminar turno");
            System.out.print("Elige una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    CartaJugada accion = seleccionarYJugarCarta();
                    if (accion != null) {
                        acciones.add(accion);
                    }
                    break;
                case "2":
                    turnoTerminado = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    break;
            }
        }
        
        if (energia <= 0) {
            System.out.println("Te quedaste sin energía. Turno terminado.");
        }
        if (mano.isEmpty()) {
            System.out.println("No tienes más cartas. Turno terminado.");
        }
        
        return acciones;
    }
    
    private void mostrarEstadoActual() {
        System.out.println("Energía disponible: " + energia);
        System.out.println("\nTus criaturas:");
        for (int i = 0; i < criaturas.size(); i++) {
            var criatura = criaturas.get(i);
            String estado = criatura.estaViva() ? "VIVA" : "MUERTA";
            System.out.printf("  %d. %s [%d/%d HP] - %s%n", 
                i + 1, 
                criatura.getCriatura().getNombre(),
                criatura.getVidaActual(),
                criatura.getVidaMaxima(),
                estado);
        }
        
        System.out.println("\nCriaturas enemigas:");
        for (int i = 0; i < equipoEnemigo.size(); i++) {
            var criatura = equipoEnemigo.get(i);
            String estado = criatura.estaViva() ? "VIVA" : "MUERTA";
            System.out.printf("  %d. %s [%d/%d HP] - %s%n", 
                i + 1, 
                criatura.getCriatura().getNombre(),
                criatura.getVidaActual(),
                criatura.getVidaMaxima(),
                estado);
        }
        
        System.out.println("\nTus cartas en mano:");
        for (int i = 0; i < mano.size(); i++) {
            var carta = mano.get(i);
            String jugable = puedeJugarCarta(carta) ? "" : " (SIN ENERGÍA)";
            System.out.printf("  %d. %s - Energía: %d, Daño: %d%s%n", 
                i + 1, 
                carta.getNombre(),
                carta.getCostoEnergia(),
                carta.getDamage(),
                jugable);
        }
    }
    
    private CartaJugada seleccionarYJugarCarta() {
        System.out.print("Selecciona una carta (número): ");
        try {
            int indiceCarta = Integer.parseInt(scanner.nextLine().trim()) - 1;
            
            if (indiceCarta < 0 || indiceCarta >= mano.size()) {
                System.out.println("Carta inválida.");
                return null;
            }
            
            Carta carta = mano.get(indiceCarta);
            
            if (!puedeJugarCarta(carta)) {
                System.out.println("No tienes suficiente energía para jugar esta carta.");
                return null;
            }
            
            // Seleccionar criatura atacante
            var criaturasVivas = getCriaturasVivas();
            if (criaturasVivas.isEmpty()) {
                System.out.println("No tienes criaturas vivas.");
                return null;
            }
            
            System.out.println("Selecciona tu criatura atacante:");
            for (int i = 0; i < criaturasVivas.size(); i++) {
                System.out.printf("  %d. %s%n", i + 1, criaturasVivas.get(i).getCriatura().getNombre());
            }
            System.out.print("Criatura atacante: ");
            
            int indiceAtacante = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (indiceAtacante < 0 || indiceAtacante >= criaturasVivas.size()) {
                System.out.println("Criatura atacante inválida.");
                return null;
            }
            
            CriaturaBatallando atacante = criaturasVivas.get(indiceAtacante);
            
            // Seleccionar objetivo enemigo
            var enemigosVivos = equipoEnemigo.stream()
                    .filter(CriaturaBatallando::estaViva)
                    .collect(java.util.stream.Collectors.toList());
            
            if (enemigosVivos.isEmpty()) {
                System.out.println("No hay enemigos vivos.");
                return null;
            }
            
            System.out.println("Selecciona el objetivo enemigo:");
            for (int i = 0; i < enemigosVivos.size(); i++) {
                System.out.printf("  %d. %s [%d/%d HP]%n", 
                    i + 1, 
                    enemigosVivos.get(i).getCriatura().getNombre(),
                    enemigosVivos.get(i).getVidaActual(),
                    enemigosVivos.get(i).getVidaMaxima());
            }
            System.out.print("Objetivo: ");
            
            int indiceObjetivo = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (indiceObjetivo < 0 || indiceObjetivo >= enemigosVivos.size()) {
                System.out.println("Objetivo inválido.");
                return null;
            }
            
            CriaturaBatallando objetivo = enemigosVivos.get(indiceObjetivo);
            
            // Gastar energía y quitar carta de la mano
            gastarEnergia(carta.getCostoEnergia());
            mano.remove(indiceCarta);
            discardPile.add(carta);
            
            System.out.printf("Jugaste %s con %s atacando a %s.%n", 
                carta.getNombre(), 
                atacante.getCriatura().getNombre(),
                objetivo.getCriatura().getNombre());
            
            return new CartaJugada(carta, atacante, objetivo);
            
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }
}
