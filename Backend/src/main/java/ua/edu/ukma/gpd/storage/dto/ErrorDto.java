package ua.edu.ukma.gpd.storage.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ErrorDto {
	
	private HttpStatus status;
	
	private String message;
	
	private Map<String, String> errors;
	
	private Map<String, String> global;

	public ErrorDto() {
		this(null, null, new HashMap<>(), new HashMap<>());
	}
	
	public ErrorDto(HttpStatus status, String message, Map<String, String> errors, Map<String, String> global) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.global = global;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Map<String, String> getGlobal() {
		return global;
	}

	public void setGlobal(Map<String, String> global) {
		this.global = global;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorDto [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", errors=");
		builder.append(errors);
		builder.append(", global=");
		builder.append(global);
		builder.append("]");
		return builder.toString();
	}
	
}
