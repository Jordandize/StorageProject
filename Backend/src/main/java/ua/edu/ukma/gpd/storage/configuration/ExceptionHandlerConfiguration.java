package ua.edu.ukma.gpd.storage.configuration;

import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ua.edu.ukma.gpd.storage.dto.ErrorDto;

@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto respBody = new ErrorDto();
		Map<String, String> errors = respBody.getErrors(),
				global = respBody.getGlobal();
		for(FieldError error: ex.getBindingResult().getFieldErrors())
			errors.put(error.getField(), error.getDefaultMessage());
		for(ObjectError error: ex.getBindingResult().getGlobalErrors())
			global.put(error.getObjectName(), error.getDefaultMessage());
		respBody.setStatus(status);
		respBody.setMessage(ex.getLocalizedMessage());
		return handleExceptionInternal(ex, respBody, headers, status, request);
	}
	
}
