package com.starters;

public class CompanyInervVO {
	private int companyPriceId;
	private int price;
	private String uploadDate;
	
	public CompanyInervVO(){}
	public CompanyInervVO(int companyPriceId, int price, String uploadDate) {
		super();
		this.companyPriceId = companyPriceId;
		this.price = price;
		this.uploadDate = uploadDate;
	}
	
	
	public int getCompanyPriceId() {
		return companyPriceId;
	}
	public void setCompanyPriceId(int companyPriceId) {
		this.companyPriceId = companyPriceId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyPriceId;
		result = prime * result + price;
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyInervVO other = (CompanyInervVO) obj;
		if (companyPriceId != other.companyPriceId)
			return false;
		if (price != other.price)
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}
	
	
}
