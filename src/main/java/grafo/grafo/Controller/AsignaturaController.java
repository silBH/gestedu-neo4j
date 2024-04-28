package grafo.grafo.Controller;

import grafo.grafo.Model.Asignatura;
import grafo.grafo.Service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping("/txt")
    public String txt() {
        return "Hello World";
    }


    @PostMapping("/add")
    public ResponseEntity<Asignatura> addAsignatura(@RequestBody Asignatura asignatura) {
        asignaturaService.insertarAsignatura(asignatura);
        return ResponseEntity.ok(asignatura);
    }

    @GetMapping("/find/{nombre}")
    public ResponseEntity<Asignatura> findAsignatura(@PathVariable String nombre) {
        Asignatura asignatura = asignaturaService.buscarAsignaturaPorNombre(nombre);
        return ResponseEntity.ok(asignatura);
    }

    @DeleteMapping("/delete/{nombre}")
    public ResponseEntity<String> deleteAsignatura(@PathVariable String nombre) {
        asignaturaService.eliminarAsignaturaPorNombre(nombre);
        return ResponseEntity.ok("Asignatura eliminada");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllAsignaturas() {
        asignaturaService.eliminarTodasLasAsignaturas();
        return ResponseEntity.ok("Todas las asignaturas eliminadas");
    }

    @GetMapping("/exists/{nombre}")
    public ResponseEntity<Boolean> existsAsignatura(@PathVariable String nombre) {
        return ResponseEntity.ok(asignaturaService.existeAsignaturaPorNombre(nombre));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Asignatura>> allAsignaturas() {
        return ResponseEntity.ok(asignaturaService.obtenerTodasLasAsignaturas());
    }

    @PostMapping("/add/{nombreAsignatura}/{nombreAsignaturaPrevia}")
    public ResponseEntity<Asignatura> addAsignaturaPrevia(@PathVariable String nombreAsignatura, @PathVariable String nombreAsignaturaPrevia) {
        asignaturaService.agregarAsignaturasPrevias(nombreAsignatura, nombreAsignaturaPrevia);
        return ResponseEntity.ok(asignaturaService.buscarAsignaturaPorNombre(nombreAsignatura));
    }

    @GetMapping("/previas/{nombre}")
    public ResponseEntity<Iterable<Asignatura>> previas(@PathVariable String nombre) {
        return ResponseEntity.ok(asignaturaService.obtenerAsignaturasPrevias(nombre));
    }
}
