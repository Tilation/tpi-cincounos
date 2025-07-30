package ar.unrn.emberlords.game.enums.ataques;

import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

/**
 * Representa un tipo de ataque en el juego.
 * Cada tipo de ataque tiene un nombre único.
 * Estan guardados en un archivo csv y se cargan al inicio del juego.
 * Esta clase implementa métodos para comparar tipos de ataque, obtener su nombre y crear una colección de tipos de ataque.
 * Funciona como un enumerador de tipos de ataque, permitiendo registrar y obtener ataques por su nombre.
 */
public class TipoAtaque implements ClaveVisitable<String> {
    private final String nombre;

    public TipoAtaque(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof TipoAtaque tipo) {
            return nombre.equals(tipo.nombre);
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<String> visitor) {
        visitor.clave(nombre);
    }
}
