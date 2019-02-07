package com.starters;

public class BuyStatisticVO2 {
	private int payId;
	private String companyId;
	private String companyName; // 튜터 이름
	private String tuteeId;
	private String tuteeName;
	private int portfolioId; // 튜터 아이디
	private String portfolioTitle; // 튜터 이름
	private int payPrice;
	private String payInfo;
	private String askDate;
	
	public BuyStatisticVO2(){}
	
	public BuyStatisticVO2(int payId, String companyId, String companyName, String tuteeId, String tuteeName, int portfolioId, 
			String portfolioTitle, int payPrice, String payInfo, String askDate){
		super();
		this.payId = payId;
		this.companyId = companyId;
		this.tuteeName = tuteeName;
		this.tuteeId = tuteeId;
		this.tuteeName = tuteeName;
		this.portfolioId = portfolioId;
		this.portfolioTitle = portfolioTitle;
		this.payPrice = payPrice;
		this.payInfo = payInfo;
		this.askDate = askDate;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTuteeId() {
		return tuteeId;
	}

	public void setTuteeId(String tuteeId) {
		this.tuteeId = tuteeId;
	}

	public String getTuteeName() {
		return tuteeName;
	}

	public void setTuteeName(String tuteeName) {
		this.tuteeName = tuteeName;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioTitle() {
		return portfolioTitle;
	}

	public void setPortfolioTitle(String portfolioTitle) {
		this.portfolioTitle = portfolioTitle;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getAskDate() {
		return askDate;
	}

	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}

	@Override
	public String toString() {
		return "BuyStatisticVO2 [payId=" + payId + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", tuteeId=" + tuteeId + ", tuteeName=" + tuteeName + ", portfolioId=" + portfolioId
				+ ", portfolioTitle=" + portfolioTitle + ", payPrice=" + payPrice + ", payInfo=" + payInfo
				+ ", askDate=" + askDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((askDate == null) ? 0 : askDate.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + payId;
		result = prime * result + ((payInfo == null) ? 0 : payInfo.hashCode());
		result = prime * result + payPrice;
		result = prime * result + portfolioId;
		result = prime * result + ((portfolioTitle == null) ? 0 : portfolioTitle.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + ((tuteeName == null) ? 0 : tuteeName.hashCode());
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
		BuyStatisticVO2 other = (BuyStatisticVO2) obj;
		if (askDate == null) {
			if (other.askDate != null)
				return false;
		} else if (!askDate.equals(other.askDate))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (payId != other.payId)
			return false;
		if (payInfo == null) {
			if (other.payInfo != null)
				return false;
		} else if (!payInfo.equals(other.payInfo))
			return false;
		if (payPrice != other.payPrice)
			return false;
		if (portfolioId != other.portfolioId)
			return false;
		if (portfolioTitle == null) {
			if (other.portfolioTitle != null)
				return false;
		} else if (!portfolioTitle.equals(other.portfolioTitle))
			return false;
		if (tuteeId == null) {
			if (other.tuteeId != null)
				return false;
		} else if (!tuteeId.equals(other.tuteeId))
			return false;
		if (tuteeName == null) {
			if (other.tuteeName != null)
				return false;
		} else if (!tuteeName.equals(other.tuteeName))
			return false;
		return true;
	}

	
}
