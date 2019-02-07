package com.starters;

public class ReviewVO {
	private int reviewId;
	private int tutoringApplyId;
	private String tuteeId;
	private String reviewTitle;
	private String reviewContent;
	private int reviewCount;
	private int reviewLikeCount;
	private String reviewDate;

	
	public ReviewVO(){}
	public ReviewVO(int reviewId, int tutoringApplyId, String tuteeId,String reviewTitle, String reviewContent, int reviewCount,String reviewDate,int reviewLikeCount){
		super();
		this.reviewId = reviewId;
		this.tutoringApplyId = tutoringApplyId;
		this.tuteeId = tuteeId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewCount = reviewCount;
		this.reviewDate = reviewDate;
		this.reviewLikeCount = reviewLikeCount;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getReviewLikeCount() {
		return reviewLikeCount;
	}
	public void setReviewLikeCount(int reviewLikeCount) {
		this.reviewLikeCount = reviewLikeCount;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	@Override
	public String toString() {
		return "ReviewVO [reviewId=" + reviewId + ", tutoringApplyId=" + tutoringApplyId + ", tuteeId=" + tuteeId
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", reviewCount=" + reviewCount
				+ ", reviewLikeCount=" + reviewLikeCount + ", reviewDate=" + reviewDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reviewContent == null) ? 0 : reviewContent.hashCode());
		result = prime * result + reviewCount;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + reviewId;
		result = prime * result + reviewLikeCount;
		result = prime * result + ((reviewTitle == null) ? 0 : reviewTitle.hashCode());
		result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
		result = prime * result + tutoringApplyId;
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
		ReviewVO other = (ReviewVO) obj;
		if (reviewContent == null) {
			if (other.reviewContent != null)
				return false;
		} else if (!reviewContent.equals(other.reviewContent))
			return false;
		if (reviewCount != other.reviewCount)
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (reviewLikeCount != other.reviewLikeCount)
			return false;
		if (reviewTitle == null) {
			if (other.reviewTitle != null)
				return false;
		} else if (!reviewTitle.equals(other.reviewTitle))
			return false;
		if (tuteeId == null) {
			if (other.tuteeId != null)
				return false;
		} else if (!tuteeId.equals(other.tuteeId))
			return false;
		if (tutoringApplyId != other.tutoringApplyId)
			return false;
		return true;
	}
	
	
}