package com.starters;

public class NoticeMemVO {
	private int noticeId;
	private String noticeMem; // 대상
	
	public NoticeMemVO(){}
	public NoticeMemVO(int noticeId, String noticeMem){
		super();
		this.noticeId = noticeId;
		this.noticeMem = noticeMem;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeMem() {
		return noticeMem;
	}
	public void setNoticeMem(String noticeMem) {
		this.noticeMem = noticeMem;
	}
	@Override
	public String toString() {
		return "NoticeMemVO [noticeId=" + noticeId + ", noticeMem=" + noticeMem + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noticeId;
		result = prime * result + ((noticeMem == null) ? 0 : noticeMem.hashCode());
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
		NoticeMemVO other = (NoticeMemVO) obj;
		if (noticeId != other.noticeId)
			return false;
		if (noticeMem == null) {
			if (other.noticeMem != null)
				return false;
		} else if (!noticeMem.equals(other.noticeMem))
			return false;
		return true;
	}
	
	
}
