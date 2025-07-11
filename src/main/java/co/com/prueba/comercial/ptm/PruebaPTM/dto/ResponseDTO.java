package co.com.prueba.comercial.ptm.PruebaPTM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private int codigo;
    private String mensaje;
    private T datos;
}