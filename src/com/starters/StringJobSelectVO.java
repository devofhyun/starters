package com.starters;
// 포트폴리오, 튜터링, 면접신청 등 일련번호가 int인것에만 사용!!
public class StringJobSelectVO {
	private int selectId;
	private String thisId;
	private int middleCategId;
	private String middleCategName;
	
	public StringJobSelectVO(){}

	public int getSelectId() {
		return selectId;
	}

	public void setSelectId(int selectId) {
		this.selectId = selectId;
	}

	public String getThisId() {
		return thisId;
	}

	public void setThisId(String thisId) {
		this.thisId = thisId;
	}

	public int getMiddleCategId() {
		return middleCategId;
	}

	public void setMiddleCategId(int middleCategId) {
		this.middleCategId = middleCategId;
	}

	public String getMiddleCategName() {
		return middleCategName;
	}

	public void setMiddleCategName(String middleCategName) {
		this.middleCategName = middleCategName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + middleCategId;
		result = prime * result + ((middleCategName == null) ? 0 : middleCategName.hashCode());
		result = prime * result + selectId;
		result = prime * result + ((thisId == null) ? 0 : thisId.hashCode());
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
		StringJobSelectVO other = (StringJobSelectVO) obj;
		if (middleCategId != other.middleCategId)
			return false;
		if (middleCategName == null) {
			if (other.middleCategName != null)
				return false;
		} else if (!middleCategName.equals(other.middleCategName))
			return false;
		if (selectId != other.selectId)
			return false;
		if (thisId == null) {
			if (other.thisId != null)
				return false;
		} else if (!thisId.equals(other.thisId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StringJobSelectVO [selectId=" + selectId + ", thisId=" + thisId + ", middleCategId=" + middleCategId
				+ ", middleCategName=" + middleCategName + "]";
	}

	public StringJobSelectVO(int selectId, String thisId, int middleCategId, String middleCategName) {
		super();
		this.selectId = selectId;
		this.thisId = thisId;
		this.middleCategId = middleCategId;
		this.middleCategName = middleCategName;
	}

	
}
