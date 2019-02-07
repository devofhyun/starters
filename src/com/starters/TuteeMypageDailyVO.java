package com.starters;

public class TuteeMypageDailyVO {

	private int tutoringRecordId;
	private int tutoringApplyId;
	private String tutoringRegisterDate;
	private String tutoringFeedback;
	private String tutoringRecord;
	private String tutorFile;
	private String tuteeFile;
	private String tutorName;
	private String tutoringTitle;
	
	public TuteeMypageDailyVO(){}
	public TuteeMypageDailyVO(int tutoringRecordId,int tutoringApplyId,String tutoringRegisterDate,String tutoringFeedback
			,String tutoringRecord, String tutorFile,String tuteeFile,String tutorName,String tutoringTitle){
		super();
		this.tutoringRecordId = tutoringRecordId;
		this.tutoringApplyId = tutoringApplyId;
		this.tutoringRegisterDate = tutoringRegisterDate;
		this.tutoringFeedback = tutoringFeedback;
		this.tutoringRecord = tutoringRecord;
		this.tutorFile = tutorFile;
		this.tuteeFile = tuteeFile;
		this.tutorName = tutorName;
		this.tutoringTitle = tutoringTitle;
	}
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
	public String getTutoringRegisterDate() {
		return tutoringRegisterDate;
	}
	public void setTutoringRegisterDate(String tutoringRegisterDate) {
		this.tutoringRegisterDate = tutoringRegisterDate;
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
	public String getTutorName() {
		return tutorName;
	}
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	public String getTutoringTitle() {
		return tutoringTitle;
	}
	public void setTutoringTitle(String tutoringTitle) {
		this.tutoringTitle = tutoringTitle;
	}
	@Override
	public String toString() {
		return "TuteeMypageDailyVO [tutoringRecordId=" + tutoringRecordId + ", tutoringApplyId=" + tutoringApplyId
				+ ", tutoringRegisterDate=" + tutoringRegisterDate + ", tutoringFeedback=" + tutoringFeedback
				+ ", tutoringRecord=" + tutoringRecord + ", tutorFile=" + tutorFile + ", tuteeFile=" + tuteeFile
				+ ", tutorName=" + tutorName + ", tutoringTitle=" + tutoringTitle + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tuteeFile == null) ? 0 : tuteeFile.hashCode());
		result = prime * result + ((tutorFile == null) ? 0 : tutorFile.hashCode());
		result = prime * result + ((tutorName == null) ? 0 : tutorName.hashCode());
		result = prime * result + tutoringApplyId;
		result = prime * result + ((tutoringFeedback == null) ? 0 : tutoringFeedback.hashCode());
		result = prime * result + ((tutoringRecord == null) ? 0 : tutoringRecord.hashCode());
		result = prime * result + tutoringRecordId;
		result = prime * result + ((tutoringRegisterDate == null) ? 0 : tutoringRegisterDate.hashCode());
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
		TuteeMypageDailyVO other = (TuteeMypageDailyVO) obj;
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
		if (tutorName == null) {
			if (other.tutorName != null)
				return false;
		} else if (!tutorName.equals(other.tutorName))
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
		if (tutoringRegisterDate == null) {
			if (other.tutoringRegisterDate != null)
				return false;
		} else if (!tutoringRegisterDate.equals(other.tutoringRegisterDate))
			return false;
		if (tutoringTitle == null) {
			if (other.tutoringTitle != null)
				return false;
		} else if (!tutoringTitle.equals(other.tutoringTitle))
			return false;
		return true;
	}
	
}
