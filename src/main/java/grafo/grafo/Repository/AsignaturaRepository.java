package grafo.grafo.Repository;

import grafo.grafo.Model.Asignatura;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends Neo4jRepository<Asignatura, String> {

    public Asignatura findByNombre(String nombre);

    public void deleteByNombre(String nombre);

    public void deleteAll();

    public boolean existsByNombre(String nombre);

    public List<Asignatura> findAll();


}
