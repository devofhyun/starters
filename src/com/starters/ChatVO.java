package com.starters;

public class ChatVO {
	int chatId;
	String receptionId;
	String sentId;
	String content;
	String date;
	
public ChatVO(){}
	
	public ChatVO(int chatId, String receptionId, String sentId, String content, String date){
		super();
		this.chatId= chatId;
		this.receptionId= receptionId;
		this.sentId= sentId;
		this.content= content;
		this.date= date;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public String getReceptionId() {
		return receptionId;
	}

	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}

	public String getSentId() {
		return sentId;
	}

	public void setSentId(String sentId) {
		this.sentId = sentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ChatVO [chatId=" + chatId + ", receptionId=" + receptionId + ", sentId=" + sentId + ", content="
				+ content + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chatId;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((receptionId == null) ? 0 : receptionId.hashCode());
		result = prime * result + ((sentId == null) ? 0 : sentId.hashCode());
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
		ChatVO other = (ChatVO) obj;
		if (chatId != other.chatId)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (receptionId == null) {
			if (other.receptionId != null)
				return false;
		} else if (!receptionId.equals(other.receptionId))
			return false;
		if (sentId == null) {
			if (other.sentId != null)
				return false;
		} else if (!sentId.equals(other.sentId))
			return false;
		return true;
	}

	
}
