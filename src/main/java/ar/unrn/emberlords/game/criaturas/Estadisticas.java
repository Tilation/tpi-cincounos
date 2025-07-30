package ar.unrn.emberlords.game.criaturas;

/**
 * Esta clase representa las estadísticas de una criatura en el juego o partes del cuerpo.
 */
public class Estadisticas {

    public Estadisticas(int hp, int morale, int skill, int speed) {
        this.hp = hp;
        this.morale = morale;
        this.skill = skill;
        this.speed = speed;
    }

    /// Determina cuanto daño puede tomar antes de morir.
    private final int hp;
    /// Chance de que un golpe sea crítico, y de entrar en instancia berserk y la cantidad de vidas berserk.
    private final int morale;
    /// Agrega daño bonus al combear, (ataque de la carta * skill) / 500
    private final int skill;
    /// Determina quien ataca primero, si tienen la misma velocidad se hace este cálculo: speed > menos hp > skill > morale
    private final int speed;

    public Estadisticas Sumar(Estadisticas otro) {
        return new Estadisticas(hp + otro.hp, morale + otro.morale, skill + otro.skill, speed + otro.speed);
    }

    public int getHp() {
        return hp;
    }

    public int getMorale() {
        return morale;
    }

    public int getSkill() {
        return skill;
    }

    public int getSpeed() {
        return speed;
    }
}
