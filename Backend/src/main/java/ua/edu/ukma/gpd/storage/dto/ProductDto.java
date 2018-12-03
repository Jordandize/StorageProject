package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class ProductDto {

    @NotBlank(message = "Please, enter product name")
    @Size(max = 64, message = "Name must not be more than 64 characters long")
    private String name;

    
    @Range(min = 1, message = "Enter correct amount")
    private Integer amount;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 2000, message = "Not allowed more than 2000 characters")
    private String description;
    
    @NotBlank(message = "Enter amount of product")
    private String image;
    
    private String icon;
    
    @NotNull()
    private Long categoryId;

    @NotNull()
    private boolean isActive;

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
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("categoryId=");
		builder.append(categoryId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", description=");
		builder.append(description);
		builder.append(", image=");
		builder.append(image);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}
}
