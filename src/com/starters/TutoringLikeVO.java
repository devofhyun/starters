package com.starters;

// 튜터링 찜하기 VO: 튜터링 찜하기 정보 관리 시스템에 필요한 정보 저장

public class TutoringLikeVO {
	private String tutoringId;
	private String tuteeId;

	public TutoringLikeVO(){}
	public TutoringLikeVO(String tutoringId, String tuteeId){
		super();
		this.tutoringId = tutoringId;
		this.tuteeId = tuteeId;
	}
	public String getTutoringId() {
		return tutoringId;
	}
	public void setTutoringId(String tutoringId) {
		this.tutoringId = tutoringId;
	}
	public String getTuteeId() {
		return tuteeId;
	}
	public void setTuteeId(String tuteeId) {
		this.tuteeId = tuteeId;
	}
	@Override
	public String toString() {
		return "TutoringVO2 [tutoringId=" + tutoringId + ", tuteeId=" + tuteeId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + ((tutoringId == null) ? 0 : tutoringId.hashCode());
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
		TutoringLikeVO other = (TutoringLikeVO) obj;
		if (tuteeId == null) {
			if (other.tuteeId != null)
				return false;
		} else if (!tuteeId.equals(other.tuteeId))
			return false;
		if (tutoringId == null) {
			if (other.tutoringId != null)
				return false;
		} else if (!tutoringId.equals(other.tutoringId))
			return false;
		return true;
	}
	
	
	
	
}
	