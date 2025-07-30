package ar.unrn.emberlords.game.criaturas;

import ar.unrn.emberlords.game.enums.clases.Clase;
import ar.unrn.emberlords.game.enums.partes.Parte;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;
import ar.unrn.emberlords.game.visitors.EstadisticasFinalesVisitor;
import ar.unrn.emberlords.game.visitors.EstadisticasVisitables;

/**
 * Representa una criatura en el juego.
 * Cada criatura tiene un nombre, una clase y varias partes del cuerpo.
 * Esta clase implementa EstadisticasVisitables para permitir
 * que sus estadísticas sean visitadas por un visitante de estadísticas finales.
 */
public class Criatura implements EstadisticasVisitables, ClaveVisitable<String> {
    private final String nombre;
    private final Clase clase;
    private final Parte ojos;
    private final Parte orejas;
    private final Parte espalda;
    private final Parte cuernos;
    private final Parte boca;
    private final Parte cola;

    public Criatura(String nombre, Clase clase, Parte ojos, Parte orejas, Parte espalda, Parte cuernos, Parte boca, Parte cola) {
        if (nombre == null) throw new IllegalArgumentException("Nombre no puede ser null");
        if (clase == null) throw new IllegalArgumentException("clase no puede ser null, criatura: " + nombre);
        if (ojos == null) throw new IllegalArgumentException("ojos no puede ser null, criatura: " + nombre);
        if (orejas == null) throw new IllegalArgumentException("orejas no puede ser null, criatura: " + nombre);
        if (espalda == null) throw new IllegalArgumentException("espalda no puede ser null, criatura: " + nombre);
        if (cuernos == null) throw new IllegalArgumentException("cuernos no puede ser null, criatura: " + nombre);
        if (boca == null) throw new IllegalArgumentException("boca no puede ser null, criatura: " + nombre);
        if (cola == null) throw new IllegalArgumentException("cola no puede ser null, criatura: " + nombre);
        this.nombre = nombre;
        this.clase = clase;
        this.ojos = ojos;
        this.orejas = orejas;
        this.espalda = espalda;
        this.cuernos = cuernos;
        this.boca = boca;
        this.cola = cola;
    }

    @Override
    public Estadisticas obtenerEstadisticasFinales(EstadisticasFinalesVisitor visitor) {
        visitor.visitarEstadisticas(clase);
        visitor.visitarEstadisticas(ojos);
        visitor.visitarEstadisticas(orejas);
        visitor.visitarEstadisticas(espalda);
        visitor.visitarEstadisticas(cuernos);
        visitor.visitarEstadisticas(boca);
        visitor.visitarEstadisticas(cola);
        return visitor.getEstadisticas();
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<String> visitor) {
        visitor.clave(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public Clase getClase() {
        return clase;
    }

    public Parte getOjos() {
        return ojos;
    }

    public Parte getOrejas() {
        return orejas;
    }

    public Parte getEspalda() {
        return espalda;
    }

    public Parte getCuernos() {
        return cuernos;
    }

    public Parte getBoca() {
        return boca;
    }

    public Parte getCola() {
        return cola;
    }
}
