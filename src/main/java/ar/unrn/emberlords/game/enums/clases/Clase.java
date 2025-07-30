package ar.unrn.emberlords.game.enums.clases;

import ar.unrn.emberlords.game.criaturas.Estadisticas;
import ar.unrn.emberlords.game.criaturas.TieneEstadisticas;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.util.Objects;

/**
 * Clase representa una clase de criatura en el juego.
 * Cada clase tiene un nombre, estadísticas base y estadísticas por parte.
 */
public class Clase implements TieneEstadisticas, ClaveVisitable<String> {
    private final String nombre;
    private final Estadisticas estadisticasPorParte;
    private final Estadisticas estadisticasBase;

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Clase clase) {
            return nombre.equals(clase.nombre);
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Clase(String nombre, Estadisticas estadisticasBase, Estadisticas estadisticasPorParte) {
        this.nombre = nombre;
        this.estadisticasBase = estadisticasBase;
        this.estadisticasPorParte = estadisticasPorParte;
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<String> visitor) {
        visitor.clave(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public Estadisticas getEstadisticasPorParte() {
        return estadisticasPorParte;
    }

    public Estadisticas obtenerEstadisticasBase() {
        return estadisticasBase;
    }

    @Override
    public Estadisticas obtenerEstadisticas() {
        return obtenerEstadisticasBase();
    }
}
