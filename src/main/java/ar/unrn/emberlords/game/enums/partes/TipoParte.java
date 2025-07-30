package ar.unrn.emberlords.game.enums.partes;

import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.util.Objects;


/**
 * Representa un tipo de parte del cuerpo de una criatura en el juego.
 */
public class TipoParte implements ClaveVisitable<String> {
    private final String nombre;

    public TipoParte(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<String> visitor) {
        visitor.clave(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof TipoParte parte) {
            return nombre.equals(parte.nombre);
        }
        return false;
    }
}
