package com.starters;

public class TuteeMypageRecoVO {
		
		private String tuteeId;
		private String name;
		private int tutoringId;
		private String title;
		private String tutorId;
		private int middleCategId;
		private String middleCategName;
		
		public TuteeMypageRecoVO(){}
		public TuteeMypageRecoVO(String tuteeId, String name, int tutoringId, String title,
				String tutorId, int middleCategId, String middleCategName){
			super();
			this.tuteeId = tuteeId;
			this.name = name;
			this.tutoringId = tutoringId;
			this.title = title;
			this.tutorId = tutorId;
			this.middleCategId = middleCategId;
			this.middleCategName =middleCategName;
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
		public String getTutorId() {
			return tutorId;
		}
		public void setTutorId(String tutorId) {
			this.tutorId = tutorId;
		}
		public int getMiddleCategId() {
			return middleCategId;
		}
		public void setMiddleCategId(int middleCategId) {
			this.middleCategId = middleCategId;
		}
		public String getMiddleCategName() {
			return middleCategName;
		}
		public void setMiddleCategName(String middleCategName) {
			this.middleCategName = middleCategName;
		}
		@Override
		public String toString() {
			return "TuteeMypageRecoVO [tuteeId=" + tuteeId + ", name=" + name + ", tutoringId=" + tutoringId
					+ ", title=" + title + ", tutorId=" + tutorId + ", middleCategId=" + middleCategId
					+ ", middleCategName=" + middleCategName + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + middleCategId;
			result = prime * result + ((middleCategName == null) ? 0 : middleCategName.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
			result = prime * result + ((tutorId == null) ? 0 : tutorId.hashCode());
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
			TuteeMypageRecoVO other = (TuteeMypageRecoVO) obj;
			if (middleCategId != other.middleCategId)
				return false;
			if (middleCategName == null) {
				if (other.middleCategName != null)
					return false;
			} else if (!middleCategName.equals(other.middleCategName))
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
			if (tutorId == null) {
				if (other.tutorId != null)
					return false;
			} else if (!tutorId.equals(other.tutorId))
				return false;
			if (tutoringId != other.tutoringId)
				return false;
			return true;
		}
	
	
	}

