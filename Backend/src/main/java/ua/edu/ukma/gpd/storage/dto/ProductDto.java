package ua.edu.ukma.gpd.storage.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductDto {

    @NotBlank(message = "Please, enter product name")
    @Size(max = 64, message = "Name must not be more than 64 characters long")
    private String name;

   // @NotBlank(message = "Enter amount")
   // @Range(min = 1, message = "Please, enter amount of products, min 1")
    private Integer amount;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 2000, message = "Not allowed more than 2000 characters")
    private String description;

    //@NotBlank(message = "must have activeness")
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
