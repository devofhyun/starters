package com.starters;

public class TuteeMypageVO {
	
	private String tuteeId;
	private String name;
	private int tutoringId;
	private String title;
	private String image;
	private int tutoringApplyId;
	
	public TuteeMypageVO(){}
	public TuteeMypageVO(String tuteeId, String name, int tutoringId, String title, String image, int tutoringApplyId){
		super();
		this.tuteeId = tuteeId;
		this.name = name;
		this.tutoringId = tutoringId;
		this.title = title;
		this.image = image;
		this.tutoringApplyId = tutoringApplyId;
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
	public int getTutoringId() {
		return tutoringId;
	}
	public void setTutoringId(int tutoringId) {
		this.tutoringId = tutoringId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getTutoringApplyId() {
		return tutoringApplyId;
	}
	public void setTutoringApplyId(int tutoringApplyId) {
		this.tutoringApplyId = tutoringApplyId;
	}
	@Override
	public String toString() {
		return "TuteeMypageVO [tuteeId=" + tuteeId + ", name=" + name + ", tutoringId=" + tutoringId + ", title="
				+ title + ", image=" + image + ", tutoringApplyId=" + tutoringApplyId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + tutoringApplyId;
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
		TuteeMypageVO other = (TuteeMypageVO) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
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
		if (tuteeId == null) {
			if (other.tuteeId != null)
				return false;
		} else if (!tuteeId.equals(other.tuteeId))
			return false;
		if (tutoringApplyId != other.tutoringApplyId)
			return false;
		if (tutoringId != other.tutoringId)
			return false;
		return true;
	}

	
}
