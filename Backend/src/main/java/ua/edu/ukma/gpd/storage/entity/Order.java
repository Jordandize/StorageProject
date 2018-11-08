package ua.edu.ukma.gpd.storage.entity;

import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;

public class Order {

    private Long id;

    private Long parentId;

    private Integer orderType;

    private OrderStatus orderStatus;

    private String creationDateTime;

    private String modifiedDateTime;

    private String annotation;

    private Boolean isArchived;

    private Long createdBy;

    private Long assignedTo;

    // TO DO LARGE OBJECT


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(String modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", orderStatus=" + orderStatus +
                ", creationDateTime='" + creationDateTime + '\'' +
                ", modifiedDateTime='" + modifiedDateTime + '\'' +
                ", annotation='" + annotation + '\'' +
                ", isArchived=" + isArchived +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                '}';
    }
}
