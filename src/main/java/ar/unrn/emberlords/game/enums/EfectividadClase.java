package ar.unrn.emberlords.game.enums;

import ar.unrn.emberlords.excepciones.EfectividadNoEncontradaException;
import ar.unrn.emberlords.game.enums.clases.Clase;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa la efectividad de un tipo de clase contra otro al momento de atacar.
 */
public class EfectividadClase implements ClaveVisitable<Clase> {
    public static final double MULTIPLICADOR_CRITICO = 1.15;
    public static final double MULTIPLICADOR_RESISTIDO = 0.85;

    private final Clase atacante;
    private final Map<Clase, Double> efectividades;

    /**
     * Crea una efectividad de clase para un atacante.
     * @param atacante La clase del atacante.
     */
    public EfectividadClase(Clase atacante) {
        this.atacante = atacante;
        efectividades = new HashMap<>();
    }

    @Override
    public void aceptarClaveVisitor(ClaveVisitor<Clase> visitor) {
        visitor.clave(this.atacante);
    }

    public Clase getAtacante() {
        return atacante;
    }

    /**
     * Cambia el valor de efectividad de una clase que ataca contra otra.
     * @param clase La clase que defiende contra el ataque.
     * @param efectividad El valor de efectividad del ataque contra la clase defensora.
     * Si es mayor a 1 entonces el ataque es más efectivo, si es menor a 1 entonces el ataque
     * es menos efectivo.
     */
    public void cambiarEfectividad(Clase clase, double efectividad) {
        efectividades.put(clase, efectividad);
    }

    /**
     * Combina dos efectividades de la misma clase atacante en una sola, agregando las efectividades
     * de la otra a la actual.
     * @param otraEfectividad La otra efectividad de clase que se quiere combinar.
     * @throws IllegalArgumentException Si el atacante de la otra efectividad no es el mismo que el actual.
     */
    public void combinarEfectividad(EfectividadClase otraEfectividad) {
        if (!this.atacante.equals(otraEfectividad.atacante)) {
            throw new IllegalArgumentException("No se pueden combinar efectividades de diferentes atacantes.");
        }
        for (var entry : otraEfectividad.efectividades.keySet()) {
            var efectividad = otraEfectividad.efectividades.get(entry);
            this.efectividades.put(entry, efectividad);
        }
    }

    /**
     * Calcula el daño que hace un atacante a un defensor.
     * @param defensor La clase que defiende contra el ataque.
     * @param danio El daño base que hace el atacante.
     * @return El daño final que hace el atacante al defensor, multiplicado por la efectividad.
     * @throws EfectividadNoEncontradaException Si no se encuentra la efectividad contra la clase defensora.
     */
    public int afectarDanio(Clase defensor, int danio) {
        if (!efectividades.containsKey(defensor)) {
            throw new EfectividadNoEncontradaException(
                String.format("La clase %s no tiene una efectividad contra la clase %s.",
                    atacante, defensor));
        }
        var multiplicador = efectividades.get(defensor);
        return (int) (multiplicador * danio);
    }
}
