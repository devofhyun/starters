package com.starters;

public class InterviewApplyVO {
	private int interviewId;
	private String companyId;
	private int portfolioId;
	private String interviewStartDate;
	private String interviewEndDate;
	private String interviewWork;
	private String interviewYearMoney;
	private String interviewResult;
	private String uploadDate;
	
	
	public InterviewApplyVO(){}
	public InterviewApplyVO(int interviewId, String companyId, int portfolioId, String interviewStartDate,
			String interviewEndDate, String interviewWork, String interviewYearMoney, String interviewResult,
			String uploadDate) {
		super();
		this.interviewId = interviewId;
		this.companyId = companyId;
		this.portfolioId = portfolioId;
		this.interviewStartDate = interviewStartDate;
		this.interviewEndDate = interviewEndDate;
		this.interviewWork = interviewWork;
		this.interviewYearMoney = interviewYearMoney;
		this.interviewResult = interviewResult;
		this.uploadDate = uploadDate;
	}
	
	
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public int getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getInterviewStartDate() {
		return interviewStartDate;
	}
	public void setInterviewStartDate(String interviewStartDate) {
		this.interviewStartDate = interviewStartDate;
	}
	public String getInterviewEndDate() {
		return interviewEndDate;
	}
	public void setInterviewEndDate(String interviewEndDate) {
		this.interviewEndDate = interviewEndDate;
	}
	public String getInterviewWork() {
		return interviewWork;
	}
	public void setInterviewWork(String interviewWork) {
		this.interviewWork = interviewWork;
	}
	public String getInterviewYearMoney() {
		return interviewYearMoney;
	}
	public void setInterviewYearMoney(String interviewYearMoney) {
		this.interviewYearMoney = interviewYearMoney;
	}
	public String getInterviewResult() {
		return interviewResult;
	}
	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
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
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((interviewEndDate == null) ? 0 : interviewEndDate.hashCode());
		result = prime * result + interviewId;
		result = prime * result + ((interviewResult == null) ? 0 : interviewResult.hashCode());
		result = prime * result + ((interviewStartDate == null) ? 0 : interviewStartDate.hashCode());
		result = prime * result + ((interviewWork == null) ? 0 : interviewWork.hashCode());
		result = prime * result + ((interviewYearMoney == null) ? 0 : interviewYearMoney.hashCode());
		result = prime * result + portfolioId;
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
		InterviewApplyVO other = (InterviewApplyVO) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (interviewEndDate == null) {
			if (other.interviewEndDate != null)
				return false;
		} else if (!interviewEndDate.equals(other.interviewEndDate))
			return false;
		if (interviewId != other.interviewId)
			return false;
		if (interviewResult == null) {
			if (other.interviewResult != null)
				return false;
		} else if (!interviewResult.equals(other.interviewResult))
			return false;
		if (interviewStartDate == null) {
			if (other.interviewStartDate != null)
				return false;
		} else if (!interviewStartDate.equals(other.interviewStartDate))
			return false;
		if (interviewWork == null) {
			if (other.interviewWork != null)
				return false;
		} else if (!interviewWork.equals(other.interviewWork))
			return false;
		if (interviewYearMoney == null) {
			if (other.interviewYearMoney != null)
				return false;
		} else if (!interviewYearMoney.equals(other.interviewYearMoney))
			return false;
		if (portfolioId != other.portfolioId)
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}
	
	
}
