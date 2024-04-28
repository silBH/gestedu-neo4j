package grafo.grafo.Service;

import grafo.grafo.Model.Asignatura;
import grafo.grafo.Repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AsignaturaService {

    AsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public void insertarAsignatura(Asignatura asignatura) {
        asignaturaRepository.save(asignatura);
    }

    public Asignatura buscarAsignaturaPorNombre(String nombre) {
        return asignaturaRepository.findByNombre(nombre);
    }

    public void eliminarAsignaturaPorNombre(String nombre) {
        asignaturaRepository.deleteByNombre(nombre);
    }

    public void eliminarTodasLasAsignaturas() {
        asignaturaRepository.deleteAll();
    }

    public boolean existeAsignaturaPorNombre(String nombre) {
        return asignaturaRepository.existsByNombre(nombre);
    }

    public Iterable<Asignatura> obtenerTodasLasAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public void agregarAsignaturasPrevias(String nombreAsignatura, String nombreAsignaturaPrevia) {
        Asignatura asignatura = asignaturaRepository.findByNombre(nombreAsignatura);
        Asignatura asignaturaPrevia = asignaturaRepository.findByNombre(nombreAsignaturaPrevia);

        if(asignatura != null && asignaturaPrevia != null) {

            if(existeCiclo(asignatura, asignaturaPrevia)) {
                throw new RuntimeException("No se puede agregar la asignatura previa porque se forma un ciclo");
            }

            List<Asignatura> asignaturasPrevias = asignatura.getAsignaturasPrevias();
            if (asignaturasPrevias == null) {
                asignaturasPrevias = new ArrayList<>();
            }
            asignaturasPrevias.add(asignaturaPrevia);
            asignatura.setAsignaturasPrevias(asignaturasPrevias);
            asignaturaRepository.save(asignatura);
        }
    }

    private boolean existeCiclo(Asignatura asignatura, Asignatura asignaturaPrevia) {
        if(asignaturaPrevia.equals(asignatura)) {
            return true;
        }
        List<Asignatura> previas = asignaturaPrevia.getAsignaturasPrevias();
        if(previas == null) {
            return false;
        }
        for(Asignatura previa : previas) {
            if(existeCiclo(asignatura, previa)) {
                return true;
            }
        }
        return false;
    }

    public List<Asignatura> obtenerAsignaturasPrevias(String nombreAsignatura) {
        Asignatura asignatura = asignaturaRepository.findByNombre(nombreAsignatura);
        if (asignatura != null) {
            return asignatura.getAsignaturasPrevias();
        } else {
            throw new RuntimeException("La asignatura con el nombre " + nombreAsignatura + " no existe");
        }
    }
}
