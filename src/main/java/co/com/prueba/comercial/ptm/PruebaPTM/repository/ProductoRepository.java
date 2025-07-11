package co.com.prueba.comercial.ptm.PruebaPTM.repository;

import co.com.prueba.comercial.ptm.PruebaPTM.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}