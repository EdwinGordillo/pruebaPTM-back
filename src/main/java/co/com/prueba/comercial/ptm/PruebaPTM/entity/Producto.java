package co.com.prueba.comercial.ptm.PruebaPTM.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    private Double precio;

    @Column(name = "cantidad_en_stock")
    private Integer cantidadEnStock;
    
}