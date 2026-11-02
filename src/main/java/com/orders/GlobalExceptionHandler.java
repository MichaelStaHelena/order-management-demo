package com.orders;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(OrderNotFoundException.class)
  ResponseEntity<ApiError> notFound(OrderNotFoundException exception) {
    return error(HttpStatus.NOT_FOUND, exception.getMessage(), Map.of());
  }

  @ExceptionHandler(OrderCancellationNotAllowedException.class)
  ResponseEntity<ApiError> cancellationNotAllowed(OrderCancellationNotAllowedException exception) {
    return error(HttpStatus.CONFLICT, exception.getMessage(), Map.of());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ApiError> invalid(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new LinkedHashMap<>();
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return error(HttpStatus.BAD_REQUEST, "Validation failed", errors);
  }

  private ResponseEntity<ApiError> error(
      HttpStatus status, String message, Map<String, String> fields) {
    return ResponseEntity.status(status)
        .body(new ApiError(Instant.now(), status.value(), message, fields));
  }
}
