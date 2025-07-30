package ar.unrn.emberlords.game.visitors;

public interface ClaveVisitor<TClave> {
    TClave visitar(ClaveVisitable<TClave> visitable);
    void clave(TClave clave);
}
