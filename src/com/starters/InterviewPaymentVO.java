package com.starters;

public class InterviewPaymentVO {
	private int payId;
	private int interviewId;
	private String payInfo;
	private String payPrice;
	private String askDate;
	
	public InterviewPaymentVO(){}
	
	public InterviewPaymentVO(int payId, int interviewId, String payInfo, String payPrice, String askDate) {
		super();
		this.payId = payId;
		this.interviewId = interviewId;
		this.payInfo = payInfo;
		this.payPrice = payPrice;
		this.askDate = askDate;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}

	public String getAskDate() {
		return askDate;
	}

	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((askDate == null) ? 0 : askDate.hashCode());
		result = prime * result + interviewId;
		result = prime * result + payId;
		result = prime * result + ((payInfo == null) ? 0 : payInfo.hashCode());
		result = prime * result + ((payPrice == null) ? 0 : payPrice.hashCode());
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
		InterviewPaymentVO other = (InterviewPaymentVO) obj;
		if (askDate == null) {
			if (other.askDate != null)
				return false;
		} else if (!askDate.equals(other.askDate))
			return false;
		if (interviewId != other.interviewId)
			return false;
		if (payId != other.payId)
			return false;
		if (payInfo == null) {
			if (other.payInfo != null)
				return false;
		} else if (!payInfo.equals(other.payInfo))
			return false;
		if (payPrice == null) {
			if (other.payPrice != null)
				return false;
		} else if (!payPrice.equals(other.payPrice))
			return false;
		return true;
	}
	
	

}
