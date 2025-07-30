package ar.unrn.emberlords.game.enums.partes;

import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.criaturas.TieneEstadisticas;
import ar.unrn.emberlords.game.enums.clases.Clase;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.util.Objects;


/**
 * Parte representa una parte del cuerpo de una criatura en el juego.
 * Cada parte tiene un nombre, un tipo unicos.
 * Las partes están asociadas a una clase específica, que define sus estadísticas.
 * Esta clase implementa ITieneEstadisticas para permitir que sus estadísticas sean obtenidas.
 */
public class Parte implements TieneEstadisticas, ClaveVisitable<String> {
    /*
     *  Parte: Body Part,         ,Part Type,Class,
     *  Carta:           Card Name,         ,Class,Attack Type,Energy Cost,Damage,Shield,Card Ability
     */
    private final String nombre;
    private final TipoParte tipo;
    private final Clase clase;

    public Parte(String nombre, TipoParte tipo, Clase clase) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.clase = clase;
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<String> visitor) {
        visitor.clave(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public TipoParte getTipo() {
        return tipo;
    }

    public Clase getClase() {
        return clase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tipo, clase);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Parte parte) {
            return hashCode() == parte.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public Estadisticas obtenerEstadisticas() {
        return clase.getEstadisticasPorParte();
    }
}
