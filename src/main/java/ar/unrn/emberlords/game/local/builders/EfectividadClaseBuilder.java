package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.criaturas.Criatura;
import ar.unrn.emberlords.game.enums.EfectividadClase;
import ar.unrn.emberlords.game.local.csv.CriaturaCSV;
import ar.unrn.emberlords.game.local.csv.EfectividadCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.EfectividadesRepositorio;
import ar.unrn.emberlords.game.repositorios.PartesRepositorio;

public class EfectividadClaseBuilder implements EntityBuilder<EfectividadCSV, EfectividadClase> {
    private final ClasesRepositorio clasesRepo;

    public EfectividadClaseBuilder(ClasesRepositorio clasesRepo) {
        this.clasesRepo = clasesRepo;
    }

    @Override
    public EfectividadClase buildEntity(EfectividadCSV criaturaCSV) {
        var claseAtacante = clasesRepo.buscar(criaturaCSV.claseAtacante);
        var claseAtacanteFuerteContra = clasesRepo.buscar(criaturaCSV.claseFuerteContra);
        var claseAtacanteDebilContra = clasesRepo.buscar(criaturaCSV.claseDebilContra);

        var atacante = new EfectividadClase(claseAtacante);
        atacante.cambiarEfectividad(claseAtacanteFuerteContra, EfectividadClase.MULTIPLICADOR_CRITICO);
        atacante.cambiarEfectividad(claseAtacanteDebilContra, EfectividadClase.MULTIPLICADOR_RESISTIDO);

        return atacante;
    }
}
