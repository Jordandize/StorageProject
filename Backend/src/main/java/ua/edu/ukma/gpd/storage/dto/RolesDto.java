package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotNull;

public class RolesDto {
	 @NotNull(message = "Please, enter roles names")
	    private String[] name;

	    public String[] getName() {
	        return name;
	    }

	    public void setName(String[] name) {
	        this.name = name;
	    }

}
