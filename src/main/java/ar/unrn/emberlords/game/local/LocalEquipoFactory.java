package ar.unrn.emberlords.game.local;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.battles.Equipo;
import ar.unrn.emberlords.game.builders.EquipoFactory;
import ar.unrn.emberlords.game.repositorios.CriaturasRepositorio;


/**
 * Crea equipos para batallar teniendo en cuenta que los nombres de las criaturas
 * vienen por parámetros de la consola.
 */
public class LocalEquipoFactory implements EquipoFactory {
    private CriaturasRepositorio criaturas;
    private String[] nombresEquipo1;
    private String[] nombresEquipo2;

    @Override
    public void prepararEquipos(String[] args, GameServiceFactory gameServiceFactory) {
        criaturas = gameServiceFactory.repositorioCriaturas();
        nombresEquipo1 = new String[] { args[1],args[2],args[3] };
        nombresEquipo2 = new String[] { args[4],args[5],args[6] };
    }

    private Equipo buscarYCrear(String[] nombres) {
        var criatura1 = criaturas.buscar(nombres[0]);
        var criatura2 = criaturas.buscar(nombres[1]);
        var criatura3 = criaturas.buscar(nombres[2]);
        return new Equipo(criatura1, criatura2, criatura3);
    }

    @Override
    public Equipo crearEquipo1() {
        return buscarYCrear(nombresEquipo1);
    }

    @Override
    public Equipo crearEquipo2() {
        return buscarYCrear(nombresEquipo2);
    }
}
