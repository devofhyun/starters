package com.starters;

public class TutoringTimeVO3 {
	private int tutoringId;
	private String times;
	private int timeId;
	
	public TutoringTimeVO3(){}
	
	public TutoringTimeVO3(int tutoringId, String times, int timeId){
		super();
		this.tutoringId = tutoringId;
		this.times = times;
		this.timeId = timeId;
	}

	public int getTutoringId() {
		return tutoringId;
	}

	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	@Override
	public String toString() {
		return "TutoringTimeVO3 [tutoringId=" + tutoringId + ", times=" + times + ", timeId=" + timeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + timeId;
		result = prime * result + ((times == null) ? 0 : times.hashCode());
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
		TutoringTimeVO3 other = (TutoringTimeVO3) obj;
		if (timeId != other.timeId)
			return false;
		if (times == null) {
			if (other.times != null)
				return false;
		} else if (!times.equals(other.times))
			return false;
		if (tutoringId != other.tutoringId)
			return false;
		return true;
	}

	

}
