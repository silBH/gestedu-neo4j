package grafo.grafo.Model;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node
public class Asignatura {

    @Id
    private String nombre;

    @Relationship(type = "PREVIA", direction = Relationship.Direction.OUTGOING)
    private List<Asignatura> asignaturasPrevias;

    public Asignatura() {
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

}