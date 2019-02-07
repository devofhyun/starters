package com.starters;

public class BuyStatisticVO {
	private int tutoringApplyId;
	private String tuteeId;
	private String tuteeName; // 튜터 이름
	private int tutoringId;
	private String tutoringTitle;
	private String tutorId; // 튜터 아이디
	private String tutorName; // 튜터 이름
	private int tutoringPrice;
	private String paymentInfo;
	private String applyDate;
	
	
	public BuyStatisticVO(){}
	
	public BuyStatisticVO(int tutoringApplyId, String tuteeId, String tuteeName, int tutoringId, String tutoringTitle, 
			String tutorId, String tutorName, int tutoringPrice, String paymentInfo, String applyDate){
		super();
		this.tutoringApplyId = tutoringApplyId;
		this.tuteeId = tuteeId;
		this.tuteeName = tuteeName;
		this.tutoringId = tutoringId;
		this.tutoringTitle = tutoringTitle;
		this.tutorId = tutorId;
		this.tutorName = tutorName;
		this.tutoringPrice = tutoringPrice;
		this.paymentInfo = paymentInfo;
		this.applyDate = applyDate;
	}

	public int getTutoringApplyId() {
		return tutoringApplyId;
	}

	public void setTutoringApplyId(int tutoringApplyId) {
		this.tutoringApplyId = tutoringApplyId;
	}

	public String getTuteeId() {
		return tuteeId;
	}

	public void setTuteeId(String tuteeId) {
		this.tuteeId = tuteeId;
	}

	public String getTuteeName() {
		return tuteeName;
	}

	public void setTuteeName(String tuteeName) {
		this.tuteeName = tuteeName;
	}

	public int getTutoringId() {
		return tutoringId;
	}

	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}

	public String getTutoringTitle() {
		return tutoringTitle;
	}

	public void setTutoringTitle(String tutoringTitle) {
		this.tutoringTitle = tutoringTitle;
	}

	public String getTutorId() {
		return tutorId;
	}

	public void setTutorId(String tutorId) {
		this.tutorId = tutorId;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public int getTutoringPrice() {
		return tutoringPrice;
	}

	public void setTutoringPrice(int tutoringPrice) {
		this.tutoringPrice = tutoringPrice;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	@Override
	public String toString() {
		return "BuyStatisticVO [tutoringApplyId=" + tutoringApplyId + ", tuteeId=" + tuteeId + ", tuteeName="
				+ tuteeName + ", tutoringId=" + tutoringId + ", tutoringTitle=" + tutoringTitle + ", tutorId=" + tutorId
				+ ", tutorName=" + tutorName + ", tutoringPrice=" + tutoringPrice + ", paymentInfo=" + paymentInfo
				+ ", applyDate=" + applyDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applyDate == null) ? 0 : applyDate.hashCode());
		result = prime * result + ((paymentInfo == null) ? 0 : paymentInfo.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + ((tuteeName == null) ? 0 : tuteeName.hashCode());
		result = prime * result + ((tutorId == null) ? 0 : tutorId.hashCode());
		result = prime * result + ((tutorName == null) ? 0 : tutorName.hashCode());
		result = prime * result + tutoringApplyId;
		result = prime * result + tutoringId;
		result = prime * result + tutoringPrice;
		result = prime * result + ((tutoringTitle == null) ? 0 : tutoringTitle.hashCode());
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
		BuyStatisticVO other = (BuyStatisticVO) obj;
		if (applyDate == null) {
			if (other.applyDate != null)
				return false;
		} else if (!applyDate.equals(other.applyDate))
			return false;
		if (paymentInfo == null) {
			if (other.paymentInfo != null)
				return false;
		} else if (!paymentInfo.equals(other.paymentInfo))
			return false;
		if (tuteeId == null) {
			if (other.tuteeId != null)
				return false;
		} else if (!tuteeId.equals(other.tuteeId))
			return false;
		if (tuteeName == null) {
			if (other.tuteeName != null)
				return false;
		} else if (!tuteeName.equals(other.tuteeName))
			return false;
		if (tutorId == null) {
			if (other.tutorId != null)
				return false;
		} else if (!tutorId.equals(other.tutorId))
			return false;
		if (tutorName == null) {
			if (other.tutorName != null)
				return false;
		} else if (!tutorName.equals(other.tutorName))
			return false;
		if (tutoringApplyId != other.tutoringApplyId)
			return false;
		if (tutoringId != other.tutoringId)
			return false;
		if (tutoringPrice != other.tutoringPrice)
			return false;
		if (tutoringTitle == null) {
			if (other.tutoringTitle != null)
				return false;
		} else if (!tutoringTitle.equals(other.tutoringTitle))
			return false;
		return true;
	}

}
