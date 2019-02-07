package com.starters;


//VO를 작성하는 규칙
//1. -member data선언, 2. 캡슐화 setXx, getXx() 작성, 3. 생성자, 4. overriding(상속이 전제 조건), 
//5. 업무에 필요한 매서드가 있다면 추가(DAO에서 구현)
//***3번까지 만들어야 캡슐화이다.***

public class TutoringDailyVO {
	
	// 1. -member data선언

	private int tutoringRecordId;
	private int tutoringApplyId;
	private String recordDate;
	private String tutoringFeedback;
	private String tutoringRecord;
	private String tutorFile;
	private String tuteeFile;
	private String name;
	private String title;
	
	
	
	// 3. 생성자 오버로딩
	public TutoringDailyVO(){}



	public int getTutoringRecordId() {
		return tutoringRecordId;
	}



	public void setTutoringRecordId(int tutoringRecordId) {
		this.tutoringRecordId = tutoringRecordId;
	}



	public int getTutoringApplyId() {
		return tutoringApplyId;
	}



	public void setTutoringApplyId(int tutoringApplyId) {
		this.tutoringApplyId = tutoringApplyId;
	}



	public String getRecordDate() {
		return recordDate;
	}



	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}



	public String getTutoringFeedback() {
		return tutoringFeedback;
	}



	public void setTutoringFeedback(String tutoringFeedback) {
		this.tutoringFeedback = tutoringFeedback;
	}



	public String getTutoringRecord() {
		return tutoringRecord;
	}



	public void setTutoringRecord(String tutoringRecord) {
		this.tutoringRecord = tutoringRecord;
	}



	public String getTutorFile() {
		return tutorFile;
	}



	public void setTutorFile(String tutorFile) {
		this.tutorFile = tutorFile;
	}



	public String getTuteeFile() {
		return tuteeFile;
	}



	public void setTuteeFile(String tuteeFile) {
		this.tuteeFile = tuteeFile;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((recordDate == null) ? 0 : recordDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tuteeFile == null) ? 0 : tuteeFile.hashCode());
		result = prime * result + ((tutorFile == null) ? 0 : tutorFile.hashCode());
		result = prime * result + tutoringApplyId;
		result = prime * result + ((tutoringFeedback == null) ? 0 : tutoringFeedback.hashCode());
		result = prime * result + ((tutoringRecord == null) ? 0 : tutoringRecord.hashCode());
		result = prime * result + tutoringRecordId;
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
		TutoringDailyVO other = (TutoringDailyVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recordDate == null) {
			if (other.recordDate != null)
				return false;
		} else if (!recordDate.equals(other.recordDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (tuteeFile == null) {
			if (other.tuteeFile != null)
				return false;
		} else if (!tuteeFile.equals(other.tuteeFile))
			return false;
		if (tutorFile == null) {
			if (other.tutorFile != null)
				return false;
		} else if (!tutorFile.equals(other.tutorFile))
			return false;
		if (tutoringApplyId != other.tutoringApplyId)
			return false;
		if (tutoringFeedback == null) {
			if (other.tutoringFeedback != null)
				return false;
		} else if (!tutoringFeedback.equals(other.tutoringFeedback))
			return false;
		if (tutoringRecord == null) {
			if (other.tutoringRecord != null)
				return false;
		} else if (!tutoringRecord.equals(other.tutoringRecord))
			return false;
		if (tutoringRecordId != other.tutoringRecordId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "TutoringDailyVO [tutoringRecordId=" + tutoringRecordId + ", tutoringApplyId=" + tutoringApplyId
				+ ", recordDate=" + recordDate + ", tutoringFeedback=" + tutoringFeedback + ", tutoringRecord="
				+ tutoringRecord + ", tutorFile=" + tutorFile + ", tuteeFile=" + tuteeFile + ", name=" + name
				+ ", title=" + title + "]";
	}



	public TutoringDailyVO(int tutoringRecordId, int tutoringApplyId, String recordDate, String tutoringFeedback,
			String tutoringRecord, String tutorFile, String tuteeFile, String name, String title) {
		super();
		this.tutoringRecordId = tutoringRecordId;
		this.tutoringApplyId = tutoringApplyId;
		this.recordDate = recordDate;
		this.tutoringFeedback = tutoringFeedback;
		this.tutoringRecord = tutoringRecord;
		this.tutorFile = tutorFile;
		this.tuteeFile = tuteeFile;
		this.name = name;
		this.title = title;
	}

	
}



