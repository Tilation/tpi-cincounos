package ar.unrn.mecanicas;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.local.LocalGameServiceFactory;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.EfectividadesRepositorio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Test planeados:
 *      Combate:
 *          - Daños criticos y resistidos segun clases.
 *          - Daño fatal que mate a la criatura.
 *          - Criaturas mueras que quiten sus cartas del deck.
 *          - Combos aplicando el daño extra del skill.
 *          - Chance de criticos.
 *          - Ganar cuando el enemigo se queda sin criaturas.
 *          - Gasto y obtencion de energia.
 *          - Targeting de las criaturas segun estados del equipo defensor y tipo de carta del atacante.
 *          - Funcionalidad de escudo para proteger la vida de la criatura.
 *      Criaturas:
 *          - Creacion de criaturas con partes ilegales, partes duplicadas.
 *          - Calculo de estadisticas.
 *      Cartas:
 *          - Valores en regla, cartas duplicadas.
 *          - Efectos de las cartas.
 *      Carga de datos correctamente: para el proveedor de datos locales.
 *      Igualdades y comparaciones para los tipos base: TipoParte, Parte, Clase, TipoAtaque, etc.
 */
@DisplayName("TPI")
class EfectividadesTest {
    static GameServiceFactory gameServiceFactory;
    static EfectividadesRepositorio repositorioEfectividades;
    static ClasesRepositorio repositorioClases;

    @BeforeAll
    public static void cargarDatos() throws IOException {
        gameServiceFactory = new LocalGameServiceFactory();
        repositorioEfectividades = gameServiceFactory.repositorioEfectividades();
    }



    @Test
    @DisplayName("Criticos")
    void testCriticos() {

    }
}
