package ar.unrn.emberlords.game.cards;


import ar.unrn.emberlords.game.enums.ataques.TipoAtaque;
import ar.unrn.emberlords.game.enums.partes.Parte;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.util.Objects;

/**
 * Representa una carta en el juego. Las cartas pertenecen a diferentes partes del cuerpo de las criaturas.
 * Cada carta tiene un nombre, una parte asociada, un tipo de ataque, un costo de energía,
 * un daño, un escudo y una descripción.
 * TODO: Tienen un efecto algunas, que todavia no está implementado.
 */
public class Carta implements ClaveVisitable<String> {

    private final String nombre;
    private final Parte parte;
    private final TipoAtaque tipoAtaque;
    private final int costoEnergia;
    private final int damage;
    private final int escudo;
    private final String descripcion;

    public Carta(String nombre, Parte parte, TipoAtaque tipoAtaque, int costoEnergia, int damage, int escudo, String descripcion) {
        this.nombre = nombre;
        this.parte = parte;
        this.tipoAtaque = tipoAtaque;
        this.costoEnergia = costoEnergia;
        this.damage = damage;
        this.escudo = escudo;
        this.descripcion = descripcion;
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<String> visitor) {
        visitor.clave(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public Parte getParte() {
        return parte;
    }

    public TipoAtaque getTipoAtaque() {
        return tipoAtaque;
    }

    public int getCostoEnergia() {
        return costoEnergia;
    }

    public int getDamage() {
        return damage;
    }

    public int getEscudo() {
        return escudo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, parte);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
