package ar.unrn.emberlords.game.visitors;

/**
 * Visitor que permite obtener una clave unica de una entidad.
 */
public interface ClaveVisitor<TClave> {
    TClave visitar(ClaveVisitable<TClave> visitable);
    void clave(TClave clave);
}
