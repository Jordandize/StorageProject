package ua.edu.ukma.gpd.storage.dto;

public class ShortageDto {
	
	private Long productId;
	
	private String productName;
	
	private Integer shortage;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getShortage() {
		return shortage;
	}

	public void setShortage(Integer shortage) {
		this.shortage = shortage;
	}
	
}
