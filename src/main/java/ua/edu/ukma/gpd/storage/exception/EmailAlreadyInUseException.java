package ua.edu.ukma.gpd.storage.exception;

public class EmailAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public EmailAlreadyInUseException(String email) {
		super();
		this.setEmail(email);
	}
	
	public EmailAlreadyInUseException(String email, String message) {
		super(message);
		this.setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
