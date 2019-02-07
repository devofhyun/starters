package com.starters;

public class NoticeVO {
	private int noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter; // 관리자 이름
	private String noticeIp; // 관리
	private int noticeHits;
	private String noticeDate;
	
	private String noticeMem;
	
	public NoticeVO(){}
	public NoticeVO(int noticeId, String noticeTitle, String noticeContent, String noticeWriter, String noticeIp, int noticeHits, String noticeDate, String noticeMem){
		super();
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeIp = noticeIp;
		this.noticeHits = noticeHits;
		this.noticeDate = noticeDate;
		this.noticeMem = noticeMem;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public String getNoticeIp() {
		return noticeIp;
	}
	public void setNoticeIp(String noticeIp) {
		this.noticeIp = noticeIp;
	}
	public int getNoticeHits() {
		return noticeHits;
	}
	public void setNoticeHits(int noticeHits) {
		this.noticeHits = noticeHits;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getNoticeMem() {
		return noticeMem;
	}
	public void setNoticeMem(String noticeMem) {
		this.noticeMem = noticeMem;
	}
	@Override
	public String toString() {
		return "NoticeVO [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", noticeIp=" + noticeIp + ", noticeHits=" + noticeHits
				+ ", noticeDate=" + noticeDate + ", noticeMem=" + noticeMem + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noticeContent == null) ? 0 : noticeContent.hashCode());
		result = prime * result + ((noticeDate == null) ? 0 : noticeDate.hashCode());
		result = prime * result + noticeHits;
		result = prime * result + noticeId;
		result = prime * result + ((noticeIp == null) ? 0 : noticeIp.hashCode());
		result = prime * result + ((noticeMem == null) ? 0 : noticeMem.hashCode());
		result = prime * result + ((noticeTitle == null) ? 0 : noticeTitle.hashCode());
		result = prime * result + ((noticeWriter == null) ? 0 : noticeWriter.hashCode());
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
		NoticeVO other = (NoticeVO) obj;
		if (noticeContent == null) {
			if (other.noticeContent != null)
				return false;
		} else if (!noticeContent.equals(other.noticeContent))
			return false;
		if (noticeDate == null) {
			if (other.noticeDate != null)
				return false;
		} else if (!noticeDate.equals(other.noticeDate))
			return false;
		if (noticeHits != other.noticeHits)
			return false;
		if (noticeId != other.noticeId)
			return false;
		if (noticeIp == null) {
			if (other.noticeIp != null)
				return false;
		} else if (!noticeIp.equals(other.noticeIp))
			return false;
		if (noticeMem == null) {
			if (other.noticeMem != null)
				return false;
		} else if (!noticeMem.equals(other.noticeMem))
			return false;
		if (noticeTitle == null) {
			if (other.noticeTitle != null)
				return false;
		} else if (!noticeTitle.equals(other.noticeTitle))
			return false;
		if (noticeWriter == null) {
			if (other.noticeWriter != null)
				return false;
		} else if (!noticeWriter.equals(other.noticeWriter))
			return false;
		return true;
	}
	
	
	
	
}
