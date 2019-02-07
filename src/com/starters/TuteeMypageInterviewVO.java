package com.starters;

public class TuteeMypageInterviewVO {

	private int interviewId;
	private String askDate;
	private String startDate;
	private String endDate;
	private String companyName;
	private String money;
	private int interviewResult;
	private String companyId;
	private String email;
	private String phoneNum;
	private String address;
	private String companyUrl;
	
	public TuteeMypageInterviewVO(){}
	public TuteeMypageInterviewVO(int interviewId,String askDate,String startDate,String endDate
			,String companyName, String money, int interviewResult
			, String companyId, String email, String phoneNum, String address, String companyUrl){
		super();
		this.interviewId = interviewId;
		this.askDate = askDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.companyName = companyName;
		this.money = money;
		this.interviewResult = interviewResult;
		this.companyId = companyId;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.companyUrl = companyUrl;
	}
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public String getAskDate() {
		return askDate;
	}
	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public int getInterviewResult() {
		return interviewResult;
	}
	public void setInterviewResult(int interviewResult) {
		this.interviewResult = interviewResult;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompanyUrl() {
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	@Override
	public String toString() {
		return "TuteeMypageInterviewVO [interviewId=" + interviewId + ", askDate=" + askDate + ", startDate="
				+ startDate + ", endDate=" + endDate + ", companyName=" + companyName + ", money=" + money
				+ ", interviewResult=" + interviewResult + ", companyId=" + companyId + ", email=" + email
				+ ", phoneNum=" + phoneNum + ", address=" + address + ", companyUrl=" + companyUrl + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((askDate == null) ? 0 : askDate.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyUrl == null) ? 0 : companyUrl.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + interviewId;
		result = prime * result + interviewResult;
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		TuteeMypageInterviewVO other = (TuteeMypageInterviewVO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
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
		if (companyUrl == null) {
			if (other.companyUrl != null)
				return false;
		} else if (!companyUrl.equals(other.companyUrl))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (interviewId != other.interviewId)
			return false;
		if (interviewResult != other.interviewResult)
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
}
