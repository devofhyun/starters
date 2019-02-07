package com.starters;

public class QnAVO2 {
	private int qnaId;       // 글번호
	private String qnaWriterId;        // 글 작성자
	private String qnaTitle;    // 글 제목
	private String qnaContent;     // 글 내용
	private String qanPasswd;     // 글 비밀번호
	private int qnaHits;    // 글 조회수
	private int ref;       // 글 그룹번호
	private int reStep;     // 답변글 깊이
	private int reLevel;         // 답변글 순서
	private String qnaDate;         // 글 작성일
	    
	public QnAVO2(){}
	public QnAVO2(int qnaId, String qnaWriterId, String qnaTitle, String qnaContent, String qanPasswd, int qnaHits, int ref, int reStep, int reLevel, String qnaDate){
		super();
		this.qnaId = qnaId;
		this.qnaWriterId = qnaWriterId;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qanPasswd = qanPasswd;
		this.qnaHits = qnaHits;
		this.ref = ref;
		this.reStep = reStep;
		this.reLevel = reLevel;
		this.qnaDate = qnaDate;
	}
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}
	public String getQnaWriterId() {
		return qnaWriterId;
	}
	public void setQnaWriterId(String qnaWriterId) {
		this.qnaWriterId = qnaWriterId;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getQanPasswd() {
		return qanPasswd;
	}
	public void setQanPasswd(String qanPasswd) {
		this.qanPasswd = qanPasswd;
	}
	public int getQnaHits() {
		return qnaHits;
	}
	public void setQnaHits(int qnaHits) {
		this.qnaHits = qnaHits;
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
	public String getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(String qnaDate) {
		this.qnaDate = qnaDate;
	}
	@Override
	public String toString() {
		return "QnAVO2 [qnaId=" + qnaId + ", qnaWriterId=" + qnaWriterId + ", qnaTitle=" + qnaTitle + ", qnaContent="
				+ qnaContent + ", qanPasswd=" + qanPasswd + ", qnaHits=" + qnaHits + ", ref=" + ref + ", reStep="
				+ reStep + ", reLevel=" + reLevel + ", qnaDate=" + qnaDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qanPasswd == null) ? 0 : qanPasswd.hashCode());
		result = prime * result + ((qnaContent == null) ? 0 : qnaContent.hashCode());
		result = prime * result + ((qnaDate == null) ? 0 : qnaDate.hashCode());
		result = prime * result + qnaHits;
		result = prime * result + qnaId;
		result = prime * result + ((qnaTitle == null) ? 0 : qnaTitle.hashCode());
		result = prime * result + ((qnaWriterId == null) ? 0 : qnaWriterId.hashCode());
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
		QnAVO2 other = (QnAVO2) obj;
		if (qanPasswd == null) {
			if (other.qanPasswd != null)
				return false;
		} else if (!qanPasswd.equals(other.qanPasswd))
			return false;
		if (qnaContent == null) {
			if (other.qnaContent != null)
				return false;
		} else if (!qnaContent.equals(other.qnaContent))
			return false;
		if (qnaDate == null) {
			if (other.qnaDate != null)
				return false;
		} else if (!qnaDate.equals(other.qnaDate))
			return false;
		if (qnaHits != other.qnaHits)
			return false;
		if (qnaId != other.qnaId)
			return false;
		if (qnaTitle == null) {
			if (other.qnaTitle != null)
				return false;
		} else if (!qnaTitle.equals(other.qnaTitle))
			return false;
		if (qnaWriterId == null) {
			if (other.qnaWriterId != null)
				return false;
		} else if (!qnaWriterId.equals(other.qnaWriterId))
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
