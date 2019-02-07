package com.starters;

public class TutoringTimeVO {
	private int tutoringId;
	private String startTime;
	private String endTime;
	
	public TutoringTimeVO(){}
	
	public TutoringTimeVO(int tutoringId, String startTime, String endTime){
		super();
		this.tutoringId = tutoringId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getTutoringId() {
		return tutoringId;
	}

	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TutoringTimeVO [tutoringId=" + tutoringId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + tutoringId;
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
		TutoringTimeVO other = (TutoringTimeVO) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (tutoringId != other.tutoringId)
			return false;
		return true;
	}




}
