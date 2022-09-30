package br.com.princesinhadoalho.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	// Status code 500
		@ExceptionHandler(ServiceException.class)
		public ResponseEntity<StandardError> exception(Exception e, HttpServletRequest request) {

			Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
			String error = "ERRO";

			StandardError standardError = new StandardError(Instant.now(), status, error, e.getMessage(),
					request.getRequestURI());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
		}

		// Status code 400
		@ExceptionHandler(BadRequestException.class)
		public ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request) {

			Integer status = HttpStatus.BAD_REQUEST.value();
			String error = "ERRO";

			StandardError standardError = new StandardError(Instant.now(), status, error, e.getMessage(),
					request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
		}

		// Status code 404
		@ExceptionHandler(EntityNotFoundException.class)
		public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {

			Integer status = HttpStatus.NOT_FOUND.value();
			String error = "ERRO";

			StandardError standardError = new StandardError(Instant.now(), status, error, e.getMessage(),
					request.getRequestURI());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
		}

		// Mensagens de validação
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			List<ErrorObject> errors = getErrors(ex);
			ValidationError validationError = getValidationError(ex, status, errors);
			return new ResponseEntity<>(validationError, status);
		}

		private ValidationError getValidationError(MethodArgumentNotValidException ex, HttpStatus status,
				List<ErrorObject> errors) {

			String message = "Requisição possui campos inválidos";
			int code = status.value();
			String error = status.getReasonPhrase();
			String objectName = ex.getBindingResult().getObjectName();

			return new ValidationError(message, code, error, objectName, errors);
		}

		private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
			return ex.getBindingResult().getFieldErrors().stream()
					.map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
					.collect(Collectors.toList());
		}
}
