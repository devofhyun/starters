package com.starters;

public class TutoringDayVO {
	private int tutoringId;
	private String day;

	
	public TutoringDayVO(){}
	public TutoringDayVO(int tutoringId, String day){
		super();
		this.tutoringId = tutoringId;
		this.day = day;
	}
	public int getTutoringId() {
		return tutoringId;
	}
	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "TutoringDayVO [tutoringId=" + tutoringId + ", day=" + day + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
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
		TutoringDayVO other = (TutoringDayVO) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (tutoringId != other.tutoringId)
			return false;
		return true;
	}
	
	
}
