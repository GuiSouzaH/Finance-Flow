package financeflow.exception;

import financeflow.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(TransacaoNaoEncontradaException.class)
    public ResponseEntity<ErrorResponseDTO> handleTransacaoNaoEncontradaException( TransacaoNaoEncontradaException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsuarioNaoEncontradoException ( UsuarioNaoEncontradoException ex ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsuarioJaCadastradoException( UsuarioJaCadastradoException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException ( MethodArgumentNotValidException ex ) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), mensagem, LocalDateTime.now()));
    }


}
