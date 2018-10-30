package ua.edu.ukma.gpd.storage.entity;

public class Role {
	
	public static final String ROLE_USER = "USER";
	
	public static final String ROLE_KEEPER = "KEEPER";
	
	public static final String ROLE_ADMIN = "ADMIN";
	
	private Byte id;
	
	private String name;
	
	public Role() {
		this(null, null);
	}
	
	public Role(Byte id, String name) {
		this.id = id;
		this.name = name;
	}

	public Byte getId() {
		return id;
	}
	
	public void setId(Byte id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
