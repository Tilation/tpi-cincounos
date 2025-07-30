package ar.unrn.emberlords.game.visitors;

public interface ClaveVisitable<TClave> {
    void aceptarClaveVisitor(ClaveVisitor<TClave> visitor);
}
