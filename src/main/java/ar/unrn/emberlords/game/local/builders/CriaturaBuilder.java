package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.criaturas.Criatura;
import ar.unrn.emberlords.game.local.csv.CriaturaCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.PartesRepositorio;

public class CriaturaBuilder implements EntityBuilder<CriaturaCSV, Criatura> {
    private final ClasesRepositorio clasesRepo;
    private final PartesRepositorio partesRepo;

    public CriaturaBuilder(ClasesRepositorio clasesRepo, PartesRepositorio partesRepo) {
        this.clasesRepo = clasesRepo;
        this.partesRepo = partesRepo;
    }

    @Override
    public Criatura buildEntity(CriaturaCSV criaturaCSV) {
        var clase = clasesRepo.buscar(criaturaCSV.clase);
        var back = partesRepo.buscar(criaturaCSV.back + "-back");
        var horn = partesRepo.buscar(criaturaCSV.horn + "-horn");
        var mouth = partesRepo.buscar(criaturaCSV.mouth + "-mouth");
        var tail = partesRepo.buscar(criaturaCSV.tail + "-tail");
        var ear = partesRepo.buscar(criaturaCSV.ear + "-ear");
        var eye = partesRepo.buscar(criaturaCSV.eye + "-eye");
        return new Criatura(criaturaCSV.nombre, clase, eye, ear, back, horn, mouth, tail);
    }
}
