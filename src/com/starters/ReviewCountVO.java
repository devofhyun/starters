package com.starters;

public class ReviewCountVO {
	private int reviewId;
	private int likeCount;
	
public ReviewCountVO(){}
	
	public ReviewCountVO(int reviewId, int likeCount){
		super();
		this.reviewId = reviewId;
		this.likeCount = likeCount;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "ReviewCountVO [reviewId=" + reviewId + ", likeCount=" + likeCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + likeCount;
		result = prime * result + reviewId;
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
		ReviewCountVO other = (ReviewCountVO) obj;
		if (likeCount != other.likeCount)
			return false;
		if (reviewId != other.reviewId)
			return false;
		return true;
	}
	
	
}
