import grafo.grafo.Model.Asignatura;
import grafo.grafo.Repository.AsignaturaRepository;
import grafo.grafo.Service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.verify;


@SpringBootTest(classes = AsignaturaService.class)
public class AsignaturaServiceTest {

    @Autowired
    private AsignaturaService asignaturaService;

    @MockBean
    private AsignaturaRepository asignaturaRepository;

    @Test
    public void testInsertarAsignatura() {
        Asignatura asignatura = new Asignatura("Matemáticas");
        asignaturaService.insertarAsignatura(asignatura);
        verify(asignaturaRepository).save(asignatura);
    }
}
