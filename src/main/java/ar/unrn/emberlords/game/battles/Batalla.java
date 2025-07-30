package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.cards.Carta;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una batalla entre dos equipos de criaturas.
 * Cada equipo está compuesto por tres criaturas: una en la posición de atrás,
 * una en la posición del medio y una en la posición de adelante.
 * La batalla se puede crear con dos equipos y asignar las criaturas a cada uno de ellos.
 * Inicio:
 * 1. Preparar y mezclar las cartas de los equipos.
 * Loop:
 *   1. Repartir cartas.
 *   2. Esperar a que ambos jugadores hayan terminado de elegir sus acciones o hayan pasado los 30 segundos.
 *   3. Resolver cartas y acciones de ambos equipos, dependiendo de los stats de las criaturas.
 *   4. Dañar y curar a las criaturas.
 *   5. Si alguna criatura murió, calcular el modo berserk.
 *   6. Si murió definitivamente, entonces recalcular el mazo y quitar sus cartas.
 *   7. Si murieron todas las criaturas de un equipo, terminar.
 * Fin:
 * 1. Ganó el equipo con criaturas vivas.
 *
 */
public class Batalla {
    private final static int ENERGIA_INICIAL = 3;
    private final static int CARTAS_INICIAL = 5;

    private final GameServiceFactory gameServiceFactory;
    private static final java.util.Scanner sharedScanner = new java.util.Scanner(System.in);
    private int turno = 0;

    private JugadorAbstracto jugador1;
    private JugadorAbstracto jugador2;


    public Batalla(GameServiceFactory gameServiceFactory){
        this.gameServiceFactory = gameServiceFactory;
    }


    public void asignarJugadores(JugadorAbstracto jugador1, JugadorAbstracto jugador2){
        if (this.jugador1 != null && this.jugador2 != null) {
            throw new IllegalStateException("Los jugadores ya se habían asignado");
        }
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    /**
     * Inicializa las cartas y la energia de los jugadores, reparte 5 cartas a cada jugador.
     */
    public void inicializar() {
        jugador1.gastarEnergia(jugador1.getEnergia());
        jugador1.ganarEnergia(ENERGIA_INICIAL);
        jugador1.prepararMazo();
        jugador1.mezclarMazo();
        jugador1.repartirCartas(CARTAS_INICIAL);

        jugador2.gastarEnergia(jugador2.getEnergia());
        jugador2.ganarEnergia(ENERGIA_INICIAL);
        jugador2.prepararMazo();
        jugador2.mezclarMazo();
        jugador2.repartirCartas(CARTAS_INICIAL);

        turno = 1;
    }

    public void ejecutarTurno() {
        var cartasJugadasJ1 = jugador1.jugarTurno(jugador2.getCriaturas());
        var cartasJugadasJ2 = jugador2.jugarTurno(jugador1.getCriaturas());
        resolverAcciones(cartasJugadasJ1, cartasJugadasJ2);
        turno = turno + 1;
        jugador1.repartirCartas(2);
        jugador2.repartirCartas(2);
        jugador1.ganarEnergia(2);
        jugador2.ganarEnergia(2);
    }

    public List<CriaturaBatallando> getCriaturasVivasJ1() {
        return jugador1.getCriaturasVivas();
    }

    public List<CriaturaBatallando> getCriaturasVivasJ2() {
        return jugador2.getCriaturasVivas();
    }

    public void forzarJugadaJ1(CartaJugada jugada) {
        jugador1.forzarJugada(jugada);
    }

    public void forzarJugadaJ2(CartaJugada jugada) {
        jugador2.forzarJugada(jugada);
    }

    private void resolverAcciones(List<CartaJugada> cartasJugadasJ1, List<CartaJugada> cartasJugadasJ2) {
        // Ordenar las acciones en una sola lista por velocidad (mayor velocidad va primero)
        List<CartaJugada> todasLasAcciones = new ArrayList<>();
        todasLasAcciones.addAll(cartasJugadasJ1);
        todasLasAcciones.addAll(cartasJugadasJ2);
        
        // Ordenar por velocidad descendente
        todasLasAcciones.sort((a1, a2) -> Integer.compare(a2.getVelocidad(), a1.getVelocidad()));
        
        System.out.println("\n--- RESOLVIENDO ACCIONES ---");
        
        for(var accion : todasLasAcciones) {
            // Solo ejecutar si el atacante sigue vivo
            if (accion.getAtacante().estaViva()) {
                ejecutarAccion(accion);
            } else {
                System.out.printf("%s no puede atacar porque está muerta.%n", 
                    accion.getAtacante().getCriatura().getNombre());
            }
        }
    }

    /**
     * Deberia ejecutar la accion de la carta.
     * @param accion
     */
    private void ejecutarAccion(CartaJugada accion) {
        var carta = accion.getCarta();
        var atacante = accion.getAtacante();
        var objetivo = accion.getObjetivo();
        
        System.out.printf("%s usa %s contra %s!%n", 
            atacante.getCriatura().getNombre(),
            carta.getNombre(),
            objetivo.getCriatura().getNombre());
        
        // Calcular daño base
        int danioBase = carta.getDamage();
        
        // Aplicar bonus de skill (daño * skill / 500)
        var estadisticasAtacante = atacante.obtenerEstadisticas();
        int bonusSkill = (danioBase * estadisticasAtacante.getSkill()) / 500;
        int danioTotal = danioBase + bonusSkill;
        
        // Aplicar efectividad de clase si existe
        try {
            var efectividades = gameServiceFactory.repositorioEfectividades().obtenerTodos();
            var claseAtacante = atacante.getCriatura().getClase();
            var claseObjetivo = objetivo.getCriatura().getClase();
            
            for (var efectividad : efectividades) {
                if (efectividad.getAtacante().equals(claseAtacante)) {
                    danioTotal = efectividad.afectarDanio(claseObjetivo, danioTotal);
                    break;
                }
            }
        } catch (Exception e) {
            // Si no hay efectividad, usar daño normal
        }
        
        // Aplicar daño
        objetivo.recibirDanio(danioTotal);
        
        System.out.printf("¡%s recibe %d de daño! [%d/%d HP]%n", 
            objetivo.getCriatura().getNombre(),
            danioTotal,
            objetivo.getVidaActual(),
            objetivo.getVidaMaxima());
        
        if (!objetivo.estaViva()) {
            System.out.printf("💀 %s ha sido derrotada!%n", objetivo.getCriatura().getNombre());
        }
    }

    /**
     * TODO: Deberia preparar las cartas de los jugadores, iniciar una instancia de randomizer para cada uno
     * mezclar las cartas y dejar todo listo para poder empezar a agarrar cartas del mazo a la mano del jugador.
     */
    private void prepararMazos(){
        System.out.println("Preparando mazos...");

        
        System.out.printf("Jugador 1 tiene %d cartas en su mazo.%n", jugador1.getDrawPileSize());
        System.out.printf("Jugador 2 tiene %d cartas en su mazo.%n", jugador2.getDrawPileSize());
    }

    /**
     * TODO: debería repartir las cartas a los jugadores, de sus mazos a la mano.
     * Tiene que chequear que hayan cartas suficientes en el draw deck, y si no hay entonces tiene que
     * tomar las cartas del deck de descarte, mezclarlas y pasarlas al draw deck y luego continuar repartiendo las cartas.
     */
    private void repartirCartas(){
        // Repartir 5 cartas a cada jugador
        jugador1.repartirCartas(5);
        jugador2.repartirCartas(5);
    }
    
    private void mostrarResultadoFinal() {
        System.out.println("\n═══════════════ RESULTADO FINAL ═══════════════");
        
        System.out.println("\nJugador 1:");
        for (var criatura : jugador1.getCriaturas()) {
            String estado = criatura.estaViva() ? "VIVA" : "MUERTA";
            System.out.printf("  %s [%d/%d HP] - %s%n", 
                criatura.getCriatura().getNombre(),
                criatura.getVidaActual(),
                criatura.getVidaMaxima(),
                estado);
        }
        
        System.out.println("\nJugador 2 (IA):");
        for (var criatura : jugador2.getCriaturas()) {
            String estado = criatura.estaViva() ? "VIVA" : "MUERTA";
            System.out.printf("  %s [%d/%d HP] - %s%n", 
                criatura.getCriatura().getNombre(),
                criatura.getVidaActual(),
                criatura.getVidaMaxima(),
                estado);
        }
        
        System.out.println("\n¡Gracias por jugar!");
    }
    
    public boolean estaTerminada() {
        return !jugador1.tieneConCriaturasVivas() || !jugador2.tieneConCriaturasVivas();
    }
    
    public String getGanador() {
        if (jugador1.tieneConCriaturasVivas()) {
            return "Jugador 1";
        } else if (jugador2.tieneConCriaturasVivas()) {
            return "Jugador 2 (IA)";
        } else {
            return "Empate";
        }
    }
    
    public List<CriaturaBatallando> getCriaturasJugador1() {
        return jugador1 != null ? jugador1.getCriaturas() : new ArrayList<>();
    }
    
    public List<CriaturaBatallando> getCriaturasJugador2() {
        return jugador2 != null ? jugador2.getCriaturas() : new ArrayList<>();
    }
    
    public List<Carta> getCartasJugador1() {
        return jugador1 != null ? jugador1.getMano() : new ArrayList<>();
    }

    public List<CartaJugada> getCartasReservadasJugador1() {
        return jugador1.getCartasReservadas();
    }

    public List<CartaJugada> getCartasReservadasJugador2() {
        return jugador2.getCartasReservadas();
    }

    public int getEnergiaJugador1() {
        return jugador1 != null ? jugador1.getEnergia() : 0;
    }
    
    public boolean esJugador1Humano() {
        return jugador1 instanceof JugadorConsola;
    }
}
