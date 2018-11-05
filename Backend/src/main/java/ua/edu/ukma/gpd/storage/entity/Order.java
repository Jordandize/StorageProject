package ua.edu.ukma.gpd.storage.entity;

public class Order {

    private Long orderId;

    private Long parentId;

    private Integer orderType;

    private Integer orderStatus;

    private String creationDateTime;

    private String modifiedDateTime;

    private String annotation;

    private Boolean isArchived;

    private Long createdBy;

    private Long assignedTo;

    // TO DO LARGE OBJECT


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
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
                "orderId=" + orderId +
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
