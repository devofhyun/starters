package com.starters;

public class InterviewVO2 {
	private int interviewId;
	private String companyId;
	private int portfolioId;
	private String interviewStartDate;
	private String interviewEndDate;
//	private String interviewTime;
	private String interviewYearMoney;
	private String uploadDate;
	
	private String portfolioTitle;
	private String memberName;
	
	private String interviewResult;
	
	private String email;
	private String phoneNum;
	
	public InterviewVO2(){}

	public InterviewVO2(int interviewId, String companyId, int portfolioId, String interviewStartDate,
			String interviewEndDate, String interviewYearMoney, String uploadDate, String portfolioTitle,
			String memberName, String interviewResult, String email,String phoneNum) {
		super();
		this.interviewId = interviewId;
		this.companyId = companyId;
		this.portfolioId = portfolioId;
		this.interviewStartDate = interviewStartDate;
		this.interviewEndDate = interviewEndDate;
		this.interviewYearMoney = interviewYearMoney;
		this.uploadDate = uploadDate;
		this.portfolioTitle = portfolioTitle;
		this.memberName = memberName;
		this.interviewResult = interviewResult;
		this.email = email;
		this.phoneNum = phoneNum;
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

	public String getInterviewYearMoney() {
		return interviewYearMoney;
	}

	public void setInterviewYearMoney(String interviewYearMoney) {
		this.interviewYearMoney = interviewYearMoney;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getPortfolioTitle() {
		return portfolioTitle;
	}

	public void setPortfolioTitle(String portfolioTitle) {
		this.portfolioTitle = portfolioTitle;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getInterviewResult() {
		return interviewResult;
	}

	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "InterviewVO2 [interviewId=" + interviewId + ", companyId=" + companyId + ", portfolioId=" + portfolioId
				+ ", interviewStartDate=" + interviewStartDate + ", interviewEndDate=" + interviewEndDate
				+ ", interviewYearMoney=" + interviewYearMoney + ", uploadDate=" + uploadDate + ", portfolioTitle="
				+ portfolioTitle + ", memberName=" + memberName + ", interviewResult=" + interviewResult + ", email="
				+ email + ", phoneNum=" + phoneNum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((interviewEndDate == null) ? 0 : interviewEndDate.hashCode());
		result = prime * result + interviewId;
		result = prime * result + ((interviewResult == null) ? 0 : interviewResult.hashCode());
		result = prime * result + ((interviewStartDate == null) ? 0 : interviewStartDate.hashCode());
		result = prime * result + ((interviewYearMoney == null) ? 0 : interviewYearMoney.hashCode());
		result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + portfolioId;
		result = prime * result + ((portfolioTitle == null) ? 0 : portfolioTitle.hashCode());
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
		InterviewVO2 other = (InterviewVO2) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (interviewYearMoney == null) {
			if (other.interviewYearMoney != null)
				return false;
		} else if (!interviewYearMoney.equals(other.interviewYearMoney))
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (portfolioId != other.portfolioId)
			return false;
		if (portfolioTitle == null) {
			if (other.portfolioTitle != null)
				return false;
		} else if (!portfolioTitle.equals(other.portfolioTitle))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}

	
}
