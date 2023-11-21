package mstopin.carsharing.common.infra;

import mstopin.carsharing.common.domain.BusinessRuleBrokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {
  @ExceptionHandler({ BusinessRuleBrokenException.class })
  public ResponseEntity<Map<String, String>> handleBusinessRuleBrokenException(BusinessRuleBrokenException e) {
    return ResponseEntity
      .badRequest()
      .body(Map.of("message", e.getMessage()));
  }
}
