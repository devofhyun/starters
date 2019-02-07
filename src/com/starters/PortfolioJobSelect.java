package com.starters;

public class PortfolioJobSelect {
	
	private int portfolioSelectId;
	private int portfolioId;
	private int middleCategId;
	
	
	
	
	
	public PortfolioJobSelect(int portfolioSelectId, int portfolioId, int middleCategId) {
		super();
		this.portfolioSelectId = portfolioSelectId;
		this.portfolioId = portfolioId;
		this.middleCategId = middleCategId;
	}
	public int getPortfolioSelectId() {
		return portfolioSelectId;
	}
	public void setPortfolioSelectId(int portfolioSelectId) {
		this.portfolioSelectId = portfolioSelectId;
	}
	public int getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}
	public int getMiddleCategId() {
		return middleCategId;
	}
	public void setMiddleCategId(int middleCategId) {
		this.middleCategId = middleCategId;
	}
	@Override
	public String toString() {
		return "PortfolioJobSelect [portfolioSelectId=" + portfolioSelectId + ", portfolioId=" + portfolioId
				+ ", middleCategId=" + middleCategId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + middleCategId;
		result = prime * result + portfolioId;
		result = prime * result + portfolioSelectId;
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
		PortfolioJobSelect other = (PortfolioJobSelect) obj;
		if (middleCategId != other.middleCategId)
			return false;
		if (portfolioId != other.portfolioId)
			return false;
		if (portfolioSelectId != other.portfolioSelectId)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
