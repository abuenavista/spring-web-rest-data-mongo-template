package net.eanlr.sandbox.web.controller.config;

import net.eanlr.sandbox.web.common.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<String> notFound(Exception e) {
		return ResponseEntity.notFound().build();
	}
}
