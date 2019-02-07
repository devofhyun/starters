package com.starters;

public class TuteeMypageInterviewJobSelectVO {

		private int interviewSelectId;
		private int interviewId;
		private int middleCategId;
		private String middleName;

		public TuteeMypageInterviewJobSelectVO() {}

		public TuteeMypageInterviewJobSelectVO(int interviewSelectId, int interviewId, int middleCategId, String middleName) {
			super();
			this.interviewSelectId = interviewSelectId;
			this.interviewId = interviewId;
			this.middleCategId = middleCategId;
			this.middleName = middleName;
		}

		public int getInterviewSelectId() {
			return interviewSelectId;
		}

		public void setInterviewSelectId(int interviewSelectId) {
			this.interviewSelectId = interviewSelectId;
		}

		public int getInterviewId() {
			return interviewId;
		}

		public void setInterviewId(int interviewId) {
			this.interviewId = interviewId;
		}

		public int getMiddleCategId() {
			return middleCategId;
		}

		public void setMiddleCategId(int middleCategId) {
			this.middleCategId = middleCategId;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		@Override
		public String toString() {
			return "TuteeMypageInterviewJobSelectVO [interviewSelectId=" + interviewSelectId + ", interviewId="
					+ interviewId + ", middleCategId=" + middleCategId + ", middleName=" + middleName + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + interviewId;
			result = prime * result + interviewSelectId;
			result = prime * result + middleCategId;
			result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
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
			TuteeMypageInterviewJobSelectVO other = (TuteeMypageInterviewJobSelectVO) obj;
			if (interviewId != other.interviewId)
				return false;
			if (interviewSelectId != other.interviewSelectId)
				return false;
			if (middleCategId != other.middleCategId)
				return false;
			if (middleName == null) {
				if (other.middleName != null)
					return false;
			} else if (!middleName.equals(other.middleName))
				return false;
			return true;
		}

		
	}

