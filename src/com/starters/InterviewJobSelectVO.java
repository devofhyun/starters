package com.starters;

public class InterviewJobSelectVO {
	private int interviewSelectId;
	private int interviewId;
	private int middleCategId;

	public InterviewJobSelectVO() {}

	public InterviewJobSelectVO(int interviewSelectId, int interviewId, int middleCategId) {
		super();
		this.interviewSelectId = interviewSelectId;
		this.interviewId = interviewId;
		this.middleCategId = middleCategId;
	}

	public int getInterviewSelectId() {
		return interviewSelectId;
	}

	public void setInterviewSelectId(int interviewSelectId) {
		this.interviewSelectId = interviewSelectId;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
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
		result = prime * result + interviewId;
		result = prime * result + interviewSelectId;
		result = prime * result + middleCategId;
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
		InterviewJobSelectVO other = (InterviewJobSelectVO) obj;
		if (interviewId != other.interviewId)
			return false;
		if (interviewSelectId != other.interviewSelectId)
			return false;
		if (middleCategId != other.middleCategId)
			return false;
		return true;
	}
	
	
}
