package financeflow.exception;

import financeflow.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TransacaoNaoEncontradaException.class)
    public ResponseEntity<ErrorResponseDTO> handleTransacaoNaoEncontradaException( TransacaoNaoEncontradaException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsuarioNaoEncontradoException ( UsuarioNaoEncontradoException ex ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now()));
    }
}
