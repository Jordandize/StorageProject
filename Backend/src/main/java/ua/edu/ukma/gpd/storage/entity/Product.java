package ua.edu.ukma.gpd.storage.entity;

public class Product {

    private Long prodId;

    private Long categoryId;

    private String name;

    private Integer amount;

    private String annotation;

    private Boolean isActive;

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

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

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String description) {
        this.annotation = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ProductDao{" +
                "prodId=" + prodId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", annotation='" + annotation + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
