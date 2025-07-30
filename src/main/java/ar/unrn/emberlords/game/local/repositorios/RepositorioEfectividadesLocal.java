package ar.unrn.emberlords.game.local.repositorios;

import ar.unrn.emberlords.Utils;
import ar.unrn.emberlords.game.builders.EntityBuilder;
import ar.unrn.emberlords.game.enums.clases.Clase;
import ar.unrn.emberlords.game.enums.EfectividadClase;
import ar.unrn.emberlords.game.local.builders.EfectividadClaseBuilder;
import ar.unrn.emberlords.game.local.csv.CSVParser;
import ar.unrn.emberlords.game.local.csv.EfectividadCSV;
import ar.unrn.emberlords.game.repositorios.ClasesRepositorio;
import ar.unrn.emberlords.game.repositorios.EfectividadesRepositorio;
import ar.unrn.emberlords.game.visitors.ClaveVisitable;
import ar.unrn.emberlords.game.visitors.ClaveVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RepositorioEfectividadesLocal extends RepositorioLocalCSV<Clase, EfectividadClase, EfectividadCSV> implements EfectividadesRepositorio {
    private final ClasesRepositorio clasesRepo;

    public RepositorioEfectividadesLocal(ClasesRepositorio clasesRepo) {
        this.clasesRepo = clasesRepo;
    }

    @Override
    public void cargarDesdeArchivo(Path ruta) throws IOException {
        // Carga personalizada para agrupar múltiples líneas por clase
        Map<String, List<EfectividadCSV>> efectividadesPorClase = new HashMap<>();
        
        var contenido = Files.readString(ruta);
        try (Scanner scanner = new Scanner(contenido)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.startsWith("#") && !line.isEmpty()) {
                    String[] camposCSV = Utils.splitTrimLower(line, ",");
                    EfectividadCSV efectividadCSV = new EfectividadCSV(camposCSV);
                    
                    efectividadesPorClase.computeIfAbsent(efectividadCSV.claseAtacante, k -> new ArrayList<>())
                        .add(efectividadCSV);
                }
            }
        }
        
        // Ahora procesamos cada clase y creamos una EfectividadClase con todas sus efectividades
        for (String nombreClase : efectividadesPorClase.keySet()) {
            Clase clase = clasesRepo.buscar(nombreClase);
            EfectividadClase efectividadClase = new EfectividadClase(clase);
            
            // Agregamos todas las efectividades de esta clase
            for (EfectividadCSV efectividadCSV : efectividadesPorClase.get(nombreClase)) {
                Clase claseDebil = clasesRepo.buscar(efectividadCSV.claseDebilContra);
                Clase claseFuerte = clasesRepo.buscar(efectividadCSV.claseFuerteContra);
                
                efectividadClase.cambiarEfectividad(claseFuerte, EfectividadClase.MULTIPLICADOR_CRITICO);
                efectividadClase.cambiarEfectividad(claseDebil, EfectividadClase.MULTIPLICADOR_RESISTIDO);
            }
            
            reemplazar(clase, efectividadClase);
        }
    }

    @Override
    protected EntityBuilder<EfectividadCSV, EfectividadClase> entityBuilderFactoryMethod() {
        return new EfectividadClaseBuilder(clasesRepo);
    }

    @Override
    protected CSVParser.ParseLineaCSV<EfectividadCSV> csvLineParser() {
        return new CSVParser.ParseLineaCSV<EfectividadCSV>() {
            @Override
            public EfectividadCSV parseCSV(String[] campos) {
                return new EfectividadCSV(campos);
            }
        };
    }

    @Override
    protected ClaveVisitor<Clase> claveVisitor() {
        return new ClaveVisitor<Clase>() {
            private Clase clave;

            @Override
            public Clase visitar(ClaveVisitable<Clase> visitable) {
                visitable.aceptarClaveVisitor(this);
                return clave;
            }

            @Override
            public void clave(Clase clave) {
                this.clave = clave;
            }
        };
    }

    @Override
    public void registrar(Clase clave, EfectividadClase valor) {
        // Si ya existe una efectividad para esta clase, simplemente reemplazamos
        // porque el builder debería haber combinado todas las efectividades
        reemplazar(clave, valor);
    }

    @Override
    public EfectividadClase buscar(Clase clave) {
        return super.buscar(clave);
    }
}
