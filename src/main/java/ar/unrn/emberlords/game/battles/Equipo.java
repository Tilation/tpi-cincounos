package ar.unrn.emberlords.game.battles;

import ar.unrn.emberlords.game.criaturas.Criatura;

/**
 * Representa un equipo de criaturas en una batalla.
 * Cada equipo está compuesto por tres criaturas: una en la posición de atrás,
 * una en la posición del medio y una en la posición de adelante.
 */
public class Equipo {
    private final CriaturaBatallando atras;
    private final CriaturaBatallando medio;
    private final CriaturaBatallando adelante;

    public Equipo(Criatura atras, Criatura medio, Criatura adelante) {
        this.atras = CriaturaBatallando.crear(atras);
        this.medio = CriaturaBatallando.crear(medio);
        this.adelante = CriaturaBatallando.crear(adelante);
    }
}
