package com.starters;

public class CompanyVO {

	private String companyId;
	private String name;
	private String passwd;
	private String email;
	private String phoneNum;
	private String address;
	private String companyUrl;
	private String companyRange;
	private String companyBirth;
	private String companyIntroduce;
	private String registerDate;
//	private String img1;
//	private String img2;
//	private String img3;
	
	public CompanyVO(){}
	
	public CompanyVO(String companyId, String name, String passwd, String email, String phoneNum, String address,
			String companyUrl, String companyRange, String companyBirth, String companyIntroduce, String registerDate){
		super();
		this.companyId= companyId;
		this.name= name;
		this.passwd= passwd;
		this.email= email;
		this.phoneNum= phoneNum;
		this.address= address;
		this.companyUrl= companyUrl;
		this.companyRange= companyRange;
		this.companyBirth= companyBirth;
		this.companyIntroduce= companyIntroduce;
		this.registerDate= registerDate;
//		this.img1= img1;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyRange() {
		return companyRange;
	}

	public void setCompanyRange(String companyRange) {
		this.companyRange = companyRange;
	}

	public String getCompanyBirth() {
		return companyBirth;
	}

	public void setCompanyBirth(String companyBirth) {
		this.companyBirth = companyBirth;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "CompanyVO [companyId=" + companyId + ", name=" + name + ", passwd=" + passwd + ", email=" + email
				+ ", phoneNum=" + phoneNum + ", address=" + address + ", companyUrl=" + companyUrl + ", companyRange="
				+ companyRange + ", companyBirth=" + companyBirth + ", companyIntroduce=" + companyIntroduce
				+ ", registerDate=" + registerDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((companyBirth == null) ? 0 : companyBirth.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyIntroduce == null) ? 0 : companyIntroduce.hashCode());
		result = prime * result + ((companyRange == null) ? 0 : companyRange.hashCode());
		result = prime * result + ((companyUrl == null) ? 0 : companyUrl.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
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
		CompanyVO other = (CompanyVO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (companyBirth == null) {
			if (other.companyBirth != null)
				return false;
		} else if (!companyBirth.equals(other.companyBirth))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyIntroduce == null) {
			if (other.companyIntroduce != null)
				return false;
		} else if (!companyIntroduce.equals(other.companyIntroduce))
			return false;
		if (companyRange == null) {
			if (other.companyRange != null)
				return false;
		} else if (!companyRange.equals(other.companyRange))
			return false;
		if (companyUrl == null) {
			if (other.companyUrl != null)
				return false;
		} else if (!companyUrl.equals(other.companyUrl))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		return true;
	}

	
}


