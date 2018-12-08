package ua.edu.ukma.gpd.storage.entity;

import java.sql.Timestamp;

public class Order {

    private Long id;

    private Long parentId;

    private Integer orderType;

    private String orderStatus;

    private Timestamp creationDateTime;

    private Timestamp modifiedDateTime;

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Timestamp getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Timestamp modifiedDateTime) {
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
