package co.com.prueba.comercial.ptm.PruebaPTM.service;

import co.com.prueba.comercial.ptm.PruebaPTM.entity.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> listarTodos();

    Producto obtenerPorId(Integer id);

    Producto crear(Producto producto);

    Producto actualizar(Integer id, Producto producto);

    void eliminar(Integer id);

    Double calcularValorInventario();

    List<List<Object>> obtenerCombinaciones(Double valor);
    
}