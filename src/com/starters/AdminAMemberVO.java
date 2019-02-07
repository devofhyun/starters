package com.starters;


//VO를 작성하는 규칙
//1. -member data선언, 2. 캡슐화 setXx, getXx() 작성, 3. 생성자, 4. overriding(상속이 전제 조건), 
//5. 업무에 필요한 매서드가 있다면 추가(DAO에서 구현)
//***3번까지 만들어야 캡슐화이다.***

public class AdminAMemberVO {
	
	// 1. -member data선언
	private String memberId;
	private String name; 
	private String registerDate;
	private String categ;

	
	// 3. 생성자 오버로딩
	public AdminAMemberVO(){}


	public AdminAMemberVO(String memberId, String name, String registerDate, String categ) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.registerDate = registerDate;
		this.categ = categ;
	}


	public String getCateg() {
		return categ;
	}


	public void setCateg(String categ) {
		this.categ = categ;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categ == null) ? 0 : categ.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
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
		AdminAMemberVO other = (AdminAMemberVO) obj;
		if (categ == null) {
			if (other.categ != null)
				return false;
		} else if (!categ.equals(other.categ))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AdminAMemberVO [memberId=" + memberId + ", name=" + name + ", registerDate=" + registerDate + ", categ="
				+ categ + "]";
	}




	
}