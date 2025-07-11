package co.com.prueba.comercial.ptm.PruebaPTM.controller;

import co.com.prueba.comercial.ptm.PruebaPTM.entity.Producto;
import co.com.prueba.comercial.ptm.PruebaPTM.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService service;

    private Producto producto;

    @BeforeEach
    void setup() {
        producto = Producto.builder()
                .id(1)
                .nombre("Producto Test")
                .descripcion("Descripción de prueba")
                .precio(10.0)
                .cantidadEnStock(5)
                .build();
    }

    @Test
    void testCrearProducto() throws Exception {
        Mockito.when(service.crear(any())).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(producto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.mensaje").value("Producto creado exitosamente"))
                .andExpect(jsonPath("$.datos.nombre").value("Producto Test"));
    }

    @Test
    void testObtenerProductoPorId() throws Exception {
        Mockito.when(service.obtenerPorId(1)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.datos.id").value(1));
    }

    @Test
    void testListarProductos() throws Exception {
        Mockito.when(service.listarTodos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/productos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.datos", hasSize(1)))
                .andExpect(jsonPath("$.datos[0].nombre").value("Producto Test"));
    }

    @Test
    void testActualizarProducto() throws Exception {
        Mockito.when(service.actualizar(eq(1), any())).thenReturn(producto);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(producto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Producto actualizado exitosamente"));
    }

    @Test
    void testEliminarProducto() throws Exception {
        Mockito.when(service.obtenerPorId(1)).thenReturn(producto);

        mockMvc.perform(delete("/api/productos/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Producto eliminado correctamente"));
    }

    @Test
    void testValorTotalInventario() throws Exception {
        Mockito.when(service.calcularValorInventario()).thenReturn(250.0);

        mockMvc.perform(get("/api/productos/valor-inventario"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Valor total del inventario"))
                .andExpect(jsonPath("$.datos").value(250.0));
    }

    @Test
    void testCombinaciones() throws Exception {
        List<List<Object>> combinaciones = Arrays.asList(
                Arrays.asList("A", "D", 10.0),
                Arrays.asList("B", "C", 10.0),
                Arrays.asList("A", "C", 8.0),
                Arrays.asList("A", "B", 6.0)
        );

        Mockito.when(service.obtenerCombinaciones(10.0)).thenReturn(combinaciones);

        mockMvc.perform(get("/api/productos/combinaciones").param("valor", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(200))
                .andExpect(jsonPath("$.datos", hasSize(4)))
                .andExpect(jsonPath("$.datos[0][0]").value("A"))
                .andExpect(jsonPath("$.datos[0][1]").value("D"))
                .andExpect(jsonPath("$.datos[0][2]").value(10.0))
                .andExpect(jsonPath("$.datos[1][0]").value("B"))
                .andExpect(jsonPath("$.datos[1][1]").value("C"))
                .andExpect(jsonPath("$.datos[1][2]").value(10.0))
                .andExpect(jsonPath("$.datos[2][0]").value("A"))
                .andExpect(jsonPath("$.datos[2][1]").value("C"))
                .andExpect(jsonPath("$.datos[2][2]").value(8.0))
                .andExpect(jsonPath("$.datos[3][0]").value("A"))
                .andExpect(jsonPath("$.datos[3][1]").value("B"))
                .andExpect(jsonPath("$.datos[3][2]").value(6.0));
    }
}