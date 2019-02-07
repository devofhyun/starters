package com.starters;

public class JobSelectVO {
	private int selectId;
	private String memberId;
	private int middleCategId;
	
	public JobSelectVO(){}

	public JobSelectVO(int selectId, String memberId, int middleCategId){
		super();
		this.selectId = selectId;
		this.memberId = memberId;
		this.middleCategId = middleCategId;
	}

	public int getSelectId() {
		return selectId;
	}

	public void setSelectId(int selectId) {
		this.selectId = selectId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getMiddleCategId() {
		return middleCategId;
	}

	public void setMiddleCategId(int middleCategId) {
		this.middleCategId = middleCategId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + middleCategId;
		result = prime * result + selectId;
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
		JobSelectVO other = (JobSelectVO) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (middleCategId != other.middleCategId)
			return false;
		if (selectId != other.selectId)
			return false;
		return true;
	}

	
	
}
