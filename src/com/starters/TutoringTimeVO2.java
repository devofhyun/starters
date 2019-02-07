package com.starters;

public class TutoringTimeVO2 {
	private int tutoringId;
	private String times;
	
	public TutoringTimeVO2(){}
	
	public TutoringTimeVO2(int tutoringId, String times){
		super();
		this.tutoringId = tutoringId;
		this.times = times;
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

	@Override
	public String toString() {
		return "TutoringTimeVO2 [tutoringId=" + tutoringId + ", times=" + times + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		TutoringTimeVO2 other = (TutoringTimeVO2) obj;
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
