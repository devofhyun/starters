package com.starters;

public class MainJobVO {
	
	private int mainCategId;
	private String mainName;
	
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
	@Override
	public String toString() {
		return "MainJobVO [mainCategId=" + mainCategId + ", mainName=" + mainName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mainCategId;
		result = prime * result + ((mainName == null) ? 0 : mainName.hashCode());
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
		MainJobVO other = (MainJobVO) obj;
		if (mainCategId != other.mainCategId)
			return false;
		if (mainName == null) {
			if (other.mainName != null)
				return false;
		} else if (!mainName.equals(other.mainName))
			return false;
		return true;
	}
	public MainJobVO(){}
	public MainJobVO(int mainCategId, String mainName) {
		super();
		this.mainCategId = mainCategId;
		this.mainName = mainName;
	}
	
}
