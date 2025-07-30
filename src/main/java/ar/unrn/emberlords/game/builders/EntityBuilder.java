package ar.unrn.emberlords.game.builders;

public interface EntityBuilder<TEntrada, TSalida> {
    TSalida buildEntity(TEntrada entrada);
}
