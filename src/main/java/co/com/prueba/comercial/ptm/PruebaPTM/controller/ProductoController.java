package co.com.prueba.comercial.ptm.PruebaPTM.controller;

import co.com.prueba.comercial.ptm.PruebaPTM.dto.ResponseDTO;
import co.com.prueba.comercial.ptm.PruebaPTM.exception.RecursoNoEncontradoException;
import co.com.prueba.comercial.ptm.PruebaPTM.entity.Producto;
import co.com.prueba.comercial.ptm.PruebaPTM.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Producto>>> listar() {
        List<Producto> productos = service.listarTodos();
        return ResponseEntity.ok(new ResponseDTO<>(200, "Listado de productos", productos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Producto>> obtener(@PathVariable Integer id) {
        Producto prod = service.obtenerPorId(id);
        if (prod == null) {
            throw new RecursoNoEncontradoException("Producto con ID " + id + " no existe");
        }
        return ResponseEntity.ok(new ResponseDTO<>(200, "Producto encontrado", prod));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Producto>> crear(@RequestBody Producto producto) {
        Producto creado = service.crear(producto);
        return ResponseEntity.ok(new ResponseDTO<>(200, "Producto creado exitosamente", creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Producto>> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto actualizado = service.actualizar(id, producto);
        if (actualizado == null) {
            throw new RecursoNoEncontradoException("No se pudo actualizar. Producto con ID " + id + " no existe");
        }
        return ResponseEntity.ok(new ResponseDTO<>(200, "Producto actualizado exitosamente", actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable Integer id) {
        Producto existente = service.obtenerPorId(id);
        if (existente == null) {
            throw new RecursoNoEncontradoException("No se pudo eliminar. Producto con ID " + id + " no existe");
        }
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>(200, "Producto eliminado correctamente", null));
    }

    @GetMapping("/valor-inventario")
    public ResponseEntity<ResponseDTO<Double>> valorInventario() {
        Double total = service.calcularValorInventario();
        return ResponseEntity.ok(new ResponseDTO<>(200, "Valor total del inventario", total));
    }

    @GetMapping("/combinaciones")
    public ResponseEntity<ResponseDTO<List<List<Object>>>> combinaciones(@RequestParam Double valor) {
        List<List<Object>> resultado = service.obtenerCombinaciones(valor);
        return ResponseEntity.ok(new ResponseDTO<>(200, "Combinaciones encontradas", resultado));
    }
}