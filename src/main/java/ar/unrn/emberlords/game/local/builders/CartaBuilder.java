package ar.unrn.emberlords.game.local.builders;

import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.cards.Carta;
import ar.unrn.emberlords.game.local.csv.CartaCSV;
import ar.unrn.emberlords.game.repositorios.PartesRepositorio;
import ar.unrn.emberlords.game.repositorios.TipoAtaqueRepositorio;

/**
 * Builder que crea Cartas a partir de datos CSV.
 */
public class CartaBuilder implements EntityBuilder<CartaCSV, Carta> {
    private final PartesRepositorio partesRepo;
    private final TipoAtaqueRepositorio tipoAtaqueRepo;

    public CartaBuilder(PartesRepositorio partesRepo, TipoAtaqueRepositorio tipoAtaqueRepo) {
        this.partesRepo = partesRepo;
        this.tipoAtaqueRepo = tipoAtaqueRepo;
    }

    @Override
    public Carta buildEntity(CartaCSV cartaCSV) {
        var parte = partesRepo.buscar(cartaCSV.parteOwner + "-" + cartaCSV.tipoParteOwner.toLowerCase());
        var tipoAtaque = tipoAtaqueRepo.buscar(cartaCSV.tipoAtaque);
        
        return new Carta(
            cartaCSV.nombre,
            parte,
            tipoAtaque,
            cartaCSV.costoEnergia,
            cartaCSV.damage,
            cartaCSV.escudo,
            cartaCSV.descripcion
        );
    }
}
