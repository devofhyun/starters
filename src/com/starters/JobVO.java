package com.starters;

public class JobVO {

	private int mainCategId;
	private String mainName;
	private int middleCategId;
	private String middleName;
	
	public int getMainCategId() {
		return mainCategId;
	}
	public void setMainCategId(int mainCategId) {
		this.mainCategId = mainCategId;
	}
	public String getMainName() {
		return mainName;
	}
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}
	public int getMiddleCategId() {
		return middleCategId;
	}
	public void setMiddleCategId(int middleCategId) {
		this.middleCategId = middleCategId;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Override
	public String toString() {
		return "JobVO [mainCategId=" + mainCategId + ", mainName=" + mainName + ", middleCategId=" + middleCategId
				+ ", middleName=" + middleName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mainCategId;
		result = prime * result + ((mainName == null) ? 0 : mainName.hashCode());
		result = prime * result + middleCategId;
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
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
		JobVO other = (JobVO) obj;
		if (mainCategId != other.mainCategId)
			return false;
		if (mainName == null) {
			if (other.mainName != null)
				return false;
		} else if (!mainName.equals(other.mainName))
			return false;
		if (middleCategId != other.middleCategId)
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		return true;
	}
	public JobVO(){}
	public JobVO(int mainCategId, String mainName, int middleCategId, String middleName) {
		super();
		this.mainCategId = mainCategId;
		this.mainName = mainName;
		this.middleCategId = middleCategId;
		this.middleName = middleName;
	}
	
	
}

