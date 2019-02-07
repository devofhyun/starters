package com.starters;

public class TutoringApplyListVO {
	private int tutoringApplyId;
	private int tutoringId;
	private String tuteeId;
	private String name;
	private String tutorId;
	private String tutoringTitle;
	private String applyDate;
	private String startDate;
	private String endDate;
	private String paymentInfo;
	private int price;
	private String progress;
	private String refundDeadline;
	private String time;
	
	public TutoringApplyListVO(){}
	public TutoringApplyListVO(int tutoringApplyId, int tutoringId, String tuteeId, String name, String tutorId,String tutoringTitle,String applyDate,String startDate, String endDate, String paymentInfo, int price, String progress, String refundDeadline, String time){
		super();
		this.tutoringApplyId = tutoringApplyId;
		this.tutoringId = tutoringId;
		this.tuteeId = tuteeId;
		this.name = name;
		this.tutorId = tutorId;
		this.tutoringTitle = tutoringTitle;
		this.applyDate = applyDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paymentInfo = paymentInfo;
		this.price = price;
		this.progress = progress;
		this.refundDeadline = refundDeadline;
		this.time = time;
	}
	public int getTutoringApplyId() {
		return tutoringApplyId;
	}
	public void setTutoringApplyId(int tutoringApplyId) {
		this.tutoringApplyId = tutoringApplyId;
	}
	public int getTutoringId() {
		return tutoringId;
	}
	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}
	public String getTuteeId() {
		return tuteeId;
	}
	public void setTuteeId(String tuteeId) {
		this.tuteeId = tuteeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTutorId() {
		return tutorId;
	}
	public void setTutorId(String tutorId) {
		this.tutorId = tutorId;
	}
	public String getTutoringTitle() {
		return tutoringTitle;
	}
	public void setTutoringTitle(String tutoringTitle) {
		this.tutoringTitle = tutoringTitle;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getRefundDeadline() {
		return refundDeadline;
	}
	public void setRefundDeadline(String refundDeadline) {
		this.refundDeadline = refundDeadline;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TutoringApplyListVO [tutoringApplyId=" + tutoringApplyId + ", tutoringId=" + tutoringId + ", tuteeId="
				+ tuteeId + ", name=" + name + ", tutorId=" + tutorId + ", tutoringTitle=" + tutoringTitle
				+ ", applyDate=" + applyDate + ", startDate=" + startDate + ", endDate=" + endDate + ", paymentInfo="
				+ paymentInfo + ", price=" + price + ", progress=" + progress + ", refundDeadline=" + refundDeadline
				+ ", time=" + time + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applyDate == null) ? 0 : applyDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((paymentInfo == null) ? 0 : paymentInfo.hashCode());
		result = prime * result + price;
		result = prime * result + ((progress == null) ? 0 : progress.hashCode());
		result = prime * result + ((refundDeadline == null) ? 0 : refundDeadline.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + ((tutorId == null) ? 0 : tutorId.hashCode());
		result = prime * result + tutoringApplyId;
		result = prime * result + tutoringId;
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
		TutoringApplyListVO other = (TutoringApplyListVO) obj;
		if (applyDate == null) {
			if (other.applyDate != null)
				return false;
		} else if (!applyDate.equals(other.applyDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (paymentInfo == null) {
			if (other.paymentInfo != null)
				return false;
		} else if (!paymentInfo.equals(other.paymentInfo))
			return false;
		if (price != other.price)
			return false;
		if (progress == null) {
			if (other.progress != null)
				return false;
		} else if (!progress.equals(other.progress))
			return false;
		if (refundDeadline == null) {
			if (other.refundDeadline != null)
				return false;
		} else if (!refundDeadline.equals(other.refundDeadline))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (tuteeId == null) {
			if (other.tuteeId != null)
				return false;
		} else if (!tuteeId.equals(other.tuteeId))
			return false;
		if (tutorId == null) {
			if (other.tutorId != null)
				return false;
		} else if (!tutorId.equals(other.tutorId))
			return false;
		if (tutoringApplyId != other.tutoringApplyId)
			return false;
		if (tutoringId != other.tutoringId)
			return false;
		if (tutoringTitle == null) {
			if (other.tutoringTitle != null)
				return false;
		} else if (!tutoringTitle.equals(other.tutoringTitle))
			return false;
		return true;
	}
	
}
