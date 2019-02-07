package com.starters;

public class TuteeMypageWishVO {
	private String tuteeId;
	private int tutoringId;
	private String title;
	private String image;
	
	public TuteeMypageWishVO(){}
	public TuteeMypageWishVO(String tuteeId, int tutoringId, String title, String image){
		super();
		this.tuteeId = tuteeId;
		this.tutoringId = tutoringId;
		this.title = title;
		this.image = image;
	}
	public String getTuteeId() {
		return tuteeId;
	}
	public void setTuteeId(String tuteeId) {
		this.tuteeId = tuteeId;
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
	@Override
	public String toString() {
		return "TuteeMypageWishVO [tuteeId=" + tuteeId + ", tutoringId=" + tutoringId + ", title=" + title + ", image="
				+ image + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
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
		TuteeMypageWishVO other = (TuteeMypageWishVO) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
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
		if (tutoringId != other.tutoringId)
			return false;
		return true;
	}
	
}
