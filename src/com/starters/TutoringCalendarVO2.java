package com.starters;

// 튜터링 VO: 튜터링 정보 관리 시스템에 필요한 정보 저장



public class TutoringCalendarVO2 {
	private int tutoringId;
	private String tutorId;
	private String tuteeId;
	private String title;
	private String subtitle;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String career;
	private String price;
	private String contents;
	private int count;
	private String uploadDate;

	

	public TutoringCalendarVO2(){}



	public int getTutoringId() {
		return tutoringId;
	}



	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}



	public String getTutorId() {
		return tutorId;
	}



	public void setTutorId(String tutorId) {
		this.tutorId = tutorId;
	}



	public String getTuteeId() {
		return tuteeId;
	}



	public void setTuteeId(String tuteeId) {
		this.tuteeId = tuteeId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getSubtitle() {
		return subtitle;
	}



	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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



	public String getCareer() {
		return career;
	}



	public void setCareer(String career) {
		this.career = career;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getContents() {
		return contents;
	}



	public void setContents(String contents) {
		this.contents = contents;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public String getUploadDate() {
		return uploadDate;
	}



	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((career == null) ? 0 : career.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + count;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((subtitle == null) ? 0 : subtitle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + ((tutorId == null) ? 0 : tutorId.hashCode());
		result = prime * result + tutoringId;
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
		TutoringCalendarVO2 other = (TutoringCalendarVO2) obj;
		if (career == null) {
			if (other.career != null)
				return false;
		} else if (!career.equals(other.career))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (count != other.count)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (subtitle == null) {
			if (other.subtitle != null)
				return false;
		} else if (!subtitle.equals(other.subtitle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		if (tutoringId != other.tutoringId)
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "TutoringCalendarVO2 [tutoringId=" + tutoringId + ", tutorId=" + tutorId + ", tuteeId=" + tuteeId
				+ ", title=" + title + ", subtitle=" + subtitle + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", career=" + career + ", price=" + price
				+ ", contents=" + contents + ", count=" + count + ", uploadDate=" + uploadDate + "]";
	}



	public TutoringCalendarVO2(int tutoringId, String tutorId, String tuteeId, String title, String subtitle,
			String startDate, String endDate, String startTime, String endTime, String career, String price,
			String contents, int count, String uploadDate) {
		super();
		this.tutoringId = tutoringId;
		this.tutorId = tutorId;
		this.tuteeId = tuteeId;
		this.title = title;
		this.subtitle = subtitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.career = career;
		this.price = price;
		this.contents = contents;
		this.count = count;
		this.uploadDate = uploadDate;
	}


}
	