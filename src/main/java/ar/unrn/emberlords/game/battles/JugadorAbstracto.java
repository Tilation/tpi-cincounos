package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.cards.Carta;
import ar.unrn.emberlords.game.criaturas.Criatura;

import java.util.ArrayList;
import java.util.List;

public abstract class JugadorAbstracto {
    protected List<CriaturaBatallando> criaturas;
    protected int energia;
    protected List<Carta> mano;
    protected List<Carta> drawPile;
    protected List<Carta> discardPile;
    protected GameServiceFactory servicios;


    public JugadorAbstracto(GameServiceFactory servicios) {
        energia = 0;
        criaturas = new ArrayList<>();
        mano = new ArrayList<>();
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();
        this.servicios = servicios;
    }

    public void asignarCriaturas(List<Criatura> criaturas) {
        this.criaturas.clear();
        for(var criatura : criaturas) {
            this.criaturas.add(CriaturaBatallando.crear(criatura));
        }
    }
    
    public void prepararMazo() {
        drawPile.clear();
        mano.clear();
        discardPile.clear();
        
        // Obtener todas las cartas disponibles
        var todasLasCartas = servicios.repositorioCartas().obtenerTodos();
        
        // Para cada criatura, buscar cartas que correspondan a sus partes
        for (var criaturaBatallando : criaturas) {
            var criatura = criaturaBatallando.getCriatura();
            
            // Buscar cartas para cada parte de la criatura
            buscarYAgregarCarta(todasLasCartas, criatura.getOjos());
            buscarYAgregarCarta(todasLasCartas, criatura.getOrejas());
            buscarYAgregarCarta(todasLasCartas, criatura.getEspalda());
            buscarYAgregarCarta(todasLasCartas, criatura.getCuernos());
            buscarYAgregarCarta(todasLasCartas, criatura.getBoca());
            buscarYAgregarCarta(todasLasCartas, criatura.getCola());
        }
    }
    
    private void buscarYAgregarCarta(java.util.Collection<ar.unrn.emberlords.game.cards.Carta> todasLasCartas, ar.unrn.emberlords.game.enums.partes.Parte parte) {
        for (var carta : todasLasCartas) {
            if (carta.getParte().equals(parte)) {
                drawPile.add(carta);
                break; // Solo agregar una carta por parte
            }
        }
    }
    
    public void mezclarMazo() {
        java.util.Collections.shuffle(drawPile);
    }
    
    public void repartirCartas(int cantidad) {
        for (int i = 0; i < cantidad && !drawPile.isEmpty(); i++) {
            mano.add(drawPile.remove(0));
        }
    }
    
    public boolean puedeJugarCarta(Carta carta) {
        return energia >= carta.getCostoEnergia();
    }
    
    public void gastarEnergia(int cantidad) {
        energia = Math.max(0, energia - cantidad);
    }
    
    public void ganarEnergia(int cantidad) {
        energia += cantidad;
    }
    
    public List<CriaturaBatallando> getCriaturasVivas() {
        return criaturas.stream()
                .filter(CriaturaBatallando::estaViva)
                .collect(java.util.stream.Collectors.toList());
    }
    
    public boolean tieneConCriaturasVivas() {
        return getCriaturasVivas().size() > 0;
    }
    
    public List<Carta> getMano() {
        return new ArrayList<>(mano);
    }
    
    public int getEnergia() {
        return energia;
    }
    
    public List<CriaturaBatallando> getCriaturas() {
        return new ArrayList<>(criaturas);
    }
    
    public int getDrawPileSize() {
        return drawPile.size();
    }

    /**
     * Aca se van a tomar todas las decisiones del jugador y cuando el control sale de esta función
     * quiere decir que el turno acabó. Retorna la lista de Acciones que se van a hacer, en ese orden.
     */
    abstract List<CartaJugada> jugarTurno();
}
