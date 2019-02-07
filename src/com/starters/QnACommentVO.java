package com.starters;

public class QnACommentVO {
	private int commentId;       // 댓글번호
	private int qnaId;       // 글번호
	private String commentWriterId;        // 글 작성자
	private String commentContent;     // 글 내용
	private String commentDate;         // 글 작성일
	private int ref;
	private int reStep;
	private int reLevel;
	
	public QnACommentVO(){}
	public QnACommentVO(int commentId, int qnaId, String commentWriterId, String commentContent, String commentDate, int ref, int reStep, int reLevel){
		super();
		this.commentId = commentId;
		this.qnaId = qnaId;
		this.commentWriterId = commentWriterId;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.ref = ref;
		this.reStep = reStep;
		this.reLevel = reLevel;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}
	public String getCommentWriterId() {
		return commentWriterId;
	}
	public void setCommentWriterId(String commentWriterId) {
		this.commentWriterId = commentWriterId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getReStep() {
		return reStep;
	}
	public void setReStep(int reStep) {
		this.reStep = reStep;
	}
	public int getReLevel() {
		return reLevel;
	}
	public void setReLevel(int reLevel) {
		this.reLevel = reLevel;
	}
	@Override
	public String toString() {
		return "QnACommentVO [commentId=" + commentId + ", qnaId=" + qnaId + ", commentWriterId=" + commentWriterId
				+ ", commentContent=" + commentContent + ", commentDate=" + commentDate + ", ref=" + ref + ", reStep="
				+ reStep + ", reLevel=" + reLevel + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentContent == null) ? 0 : commentContent.hashCode());
		result = prime * result + ((commentDate == null) ? 0 : commentDate.hashCode());
		result = prime * result + commentId;
		result = prime * result + ((commentWriterId == null) ? 0 : commentWriterId.hashCode());
		result = prime * result + qnaId;
		result = prime * result + reLevel;
		result = prime * result + reStep;
		result = prime * result + ref;
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
		QnACommentVO other = (QnACommentVO) obj;
		if (commentContent == null) {
			if (other.commentContent != null)
				return false;
		} else if (!commentContent.equals(other.commentContent))
			return false;
		if (commentDate == null) {
			if (other.commentDate != null)
				return false;
		} else if (!commentDate.equals(other.commentDate))
			return false;
		if (commentId != other.commentId)
			return false;
		if (commentWriterId == null) {
			if (other.commentWriterId != null)
				return false;
		} else if (!commentWriterId.equals(other.commentWriterId))
			return false;
		if (qnaId != other.qnaId)
			return false;
		if (reLevel != other.reLevel)
			return false;
		if (reStep != other.reStep)
			return false;
		if (ref != other.ref)
			return false;
		return true;
	}
	
	
}
