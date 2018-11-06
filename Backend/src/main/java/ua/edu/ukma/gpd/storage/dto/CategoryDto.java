package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {

    @NotBlank(message = "Please, enter category name")
    @Size(max = 64, message = "Name must be no more than 64 characters long")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
