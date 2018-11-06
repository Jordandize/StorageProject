package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderDto {

    // TODO: make correct validation

    private String annotation;

    @NotNull()
    private Integer orderType;

    @NotNull()
    private Long createdBy;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
