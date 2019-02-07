package com.starters;

public class InterviewDayVO {
	private int interviewId;
	private String day;
	
	
	public InterviewDayVO(){}
	public InterviewDayVO(int interviewId, String day) {
		super();
		this.interviewId = interviewId;
		this.day = day;
	}
	
	
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + interviewId;
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
		InterviewDayVO other = (InterviewDayVO) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (interviewId != other.interviewId)
			return false;
		return true;
	}

	
	
}
