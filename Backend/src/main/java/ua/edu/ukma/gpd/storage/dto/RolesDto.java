package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

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
