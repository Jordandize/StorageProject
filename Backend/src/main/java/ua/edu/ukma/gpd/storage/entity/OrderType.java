package ua.edu.ukma.gpd.storage.entity;

public class OrderType {

    private Integer typeId;

    private String name;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
