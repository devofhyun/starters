package com.starters;

public class AdminTutoringVO {
	private int tutoringId;
	private String tutorId;
	private String name;
	private String title;
	private String uploadDate;
	
	public AdminTutoringVO() {}
	
	
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
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	@Override
	public String toString() {
		return "AdminTutoringVO [tutoringId=" + tutoringId + ", tutorId=" + tutorId + ", name=" + name + ", title="
				+ title + ", uploadDate=" + uploadDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		AdminTutoringVO other = (AdminTutoringVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
	public AdminTutoringVO(int tutoringId, String tutorId, String name, String title, String uploadDate) {
		super();
		this.tutoringId = tutoringId;
		this.tutorId = tutorId;
		this.name = name;
		this.title = title;
		this.uploadDate = uploadDate;
	}
	
	
}
