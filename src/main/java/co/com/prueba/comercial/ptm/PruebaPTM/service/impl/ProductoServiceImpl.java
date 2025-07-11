package co.com.prueba.comercial.ptm.PruebaPTM.service.impl;

import co.com.prueba.comercial.ptm.PruebaPTM.entity.Producto;
import co.com.prueba.comercial.ptm.PruebaPTM.repository.ProductoRepository;
import co.com.prueba.comercial.ptm.PruebaPTM.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository repo;

    @Override
    public List<Producto> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Producto obtenerPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Producto crear(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = obtenerPorId(id);
        if (existente == null) return null;

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setCantidadEnStock(producto.getCantidadEnStock());

        return repo.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Double calcularValorInventario() {
        return repo.findAll().stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidadEnStock())
                .sum();
    }

    @Override
    public List<List<Object>> obtenerCombinaciones(Double valor) {
        List<Producto> productos = repo.findAll();
        List<List<Object>> resultado = new ArrayList<>();

        int n = productos.size();
        for (int i = 0; i < n; i++) {
            Producto a = productos.get(i);
            for (int j = i + 1; j < n; j++) {
                Producto b = productos.get(j);
                double suma2 = a.getPrecio() + b.getPrecio();
                if (suma2 <= valor)
                    resultado.add(Arrays.asList(a.getNombre(), b.getNombre(), suma2));

                for (int k = j + 1; k < n; k++) {
                    Producto c = productos.get(k);
                    double suma3 = a.getPrecio() + b.getPrecio() + c.getPrecio();
                    if (suma3 <= valor)
                        resultado.add(Arrays.asList(a.getNombre(), b.getNombre(), c.getNombre(), suma3));
                }
            }
        }

        resultado.sort((a, b) -> Double.compare((Double) b.get(b.size() - 1), (Double) a.get(a.size() - 1)));
        return resultado.stream().limit(5).toList();
    }
}