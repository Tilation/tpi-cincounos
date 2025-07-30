package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.partes.Parte;
import ar.unrn.emberlords.game.local.csv.ParteCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.TipoPartesRepositorio;

public class ParteBuilder implements EntityBuilder<ParteCSV, Parte> {
    private final ClasesRepositorio clasesRepo;
    private final TipoPartesRepositorio tipoPartesRepo;

    public ParteBuilder(ClasesRepositorio clasesRepo, TipoPartesRepositorio tipoPartesRepo) {
        this.clasesRepo = clasesRepo;
        this.tipoPartesRepo = tipoPartesRepo;
    }

    @Override
    public Parte buildEntity(ParteCSV parteCSV) {
        var clase = clasesRepo.buscar(parteCSV.clase);
        var tipoParte = tipoPartesRepo.buscar(parteCSV.tipoParte);
        return new Parte(parteCSV.nombre,tipoParte, clase);
    }
}
