package ar.unrn.emberlords.game.local;

import ar.unrn.emberlords.game.GameServiceFactory;
import ar.unrn.emberlords.game.battles.Batalla;
import ar.unrn.emberlords.game.builders.EquipoFactory;
import ar.unrn.emberlords.game.local.repositorios.*;
import ar.unrn.emberlords.game.repositorios.*;
import ar.unrn.emberlords.menu.screens.GameScreen;
import ar.unrn.emberlords.menu.screens.MenuPrincipal;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Service factory que utiliza repositorios locales que estan respaldados por archivos CSV.
 */
public class LocalGameServiceFactory implements GameServiceFactory {
    private final CartasRepositorio cartasRepositorio;
    private final ClasesRepositorio clasesRepositorio;
    private final CriaturasRepositorio criaturasRepositorio;
    private final PartesRepositorio partesRepositorio;
    private final TipoPartesRepositorio tipoPartesRepositorio;
    private final TipoAtaqueRepositorio tipoAtaqueRepositorio;
    private final EfectividadesRepositorio efectividadesRepositorio;
    private final LocalEquipoFactory equipoFactory;

    private <K,V> void mostrarCargados(String tipoItem, Repositorio<K,V> repo) {
        System.out.printf("Se cargaron %d %s%n", repo.obtenerTodos().size(), tipoItem);
    }

    public LocalGameServiceFactory() throws IOException {
        var tipoAtaqueRepositorio = new RepositorioTipoAtaqueLocal();
        tipoAtaqueRepositorio.cargarDesdeArchivo(Path.of(".", "ataques.csv"));
        this.tipoAtaqueRepositorio = tipoAtaqueRepositorio;
        mostrarCargados("Tipo Ataque", tipoAtaqueRepositorio);

        var clasesRepositorio = new RepositorioClasesLocal();
        clasesRepositorio.cargarDesdeArchivo(Path.of(".", "clases.csv"));
        this.clasesRepositorio = clasesRepositorio;
        mostrarCargados("Clases", clasesRepositorio);

        var tipoPartesRepositorio = new RepositorioTipoPartesLocal();
        tipoPartesRepositorio.cargarDesdeArchivo(Path.of(".", "tipo_partes.csv"));
        this.tipoPartesRepositorio = tipoPartesRepositorio;
        mostrarCargados("Tipo Partes", tipoPartesRepositorio);

        var partesRepositorio = new RepositorioPartesLocal(clasesRepositorio, tipoPartesRepositorio);
        partesRepositorio.cargarDesdeArchivo(Path.of(".", "cards.csv"));
        this.partesRepositorio = partesRepositorio;
        mostrarCargados("Partes", partesRepositorio);

        var efectividadesRepositorio = new RepositorioEfectividadesLocal(clasesRepositorio);
        efectividadesRepositorio.cargarDesdeArchivo(Path.of(".", "efectividades.csv"));
        this.efectividadesRepositorio = efectividadesRepositorio;
        mostrarCargados("Efectividades", efectividadesRepositorio);

        var cartasRepositorio = new RepositorioCartasLocal(partesRepositorio, tipoAtaqueRepositorio);
        cartasRepositorio.cargarDesdeArchivo(Path.of(".", "cards.csv"));
        this.cartasRepositorio = cartasRepositorio;
        mostrarCargados("Cartas", cartasRepositorio);

        var criaturasRepositorio = new RepositorioCriaturasLocal(clasesRepositorio, partesRepositorio);
        criaturasRepositorio.cargarDesdeArchivo(Path.of(".", "criatures.csv"));
        this.criaturasRepositorio = criaturasRepositorio;
        mostrarCargados("Criaturas", criaturasRepositorio);

        equipoFactory = new LocalEquipoFactory();
    }

    @Override
    public CartasRepositorio repositorioCartas() {
        return cartasRepositorio;
    }

    @Override
    public ClasesRepositorio repositorioClases() {
        return clasesRepositorio;
    }

    @Override
    public CriaturasRepositorio repositorioCriaturas() {
        return criaturasRepositorio;
    }

    @Override
    public PartesRepositorio repositorioPartes() {
        return partesRepositorio;
    }

    @Override
    public TipoAtaqueRepositorio repositorioTipoAtaques() {
        return tipoAtaqueRepositorio;
    }

    @Override
    public TipoPartesRepositorio repositorioTipoPartes() {
        return tipoPartesRepositorio;
    }

    @Override
    public EfectividadesRepositorio repositorioEfectividades() {
        return efectividadesRepositorio;
    }

    @Override
    public EquipoFactory equipoFactory() {
        return equipoFactory;
    }
}
