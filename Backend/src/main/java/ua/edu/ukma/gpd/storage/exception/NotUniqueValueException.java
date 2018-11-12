package ua.edu.ukma.gpd.storage.exception;

public class NotUniqueValueException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String className;
	
	private String field;
	
	private String value;
	
	public NotUniqueValueException(String className, String field, String value) {
		this(className, field, value, null);
	}
	
	public NotUniqueValueException(String className, String field, String value, Exception exception) {
		super("Duplicated '" + field + "' entity with '" + value + "' value in class '"
			  + className + "'. It must be unique for each entity", exception);
		this.className = className;
		this.field = field;
		this.value = value;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
