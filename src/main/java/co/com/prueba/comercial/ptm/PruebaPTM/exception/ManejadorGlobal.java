package co.com.prueba.comercial.ptm.PruebaPTM.exception;

import co.com.prueba.comercial.ptm.PruebaPTM.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ManejadorGlobal {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<?> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(404).body(new ResponseDTO<>(404, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarErrorGeneral(Exception ex) {
        return ResponseEntity.status(500).body(new ResponseDTO<>(500, "Error interno del servidor", null));
    }
}