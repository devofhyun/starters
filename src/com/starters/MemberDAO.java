package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** null값 모두 빈문자열로 바꾸기. " " */
public class MemberDAO {

	private Connection conn;

	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	/** 튜터기업관리자 회원분류 */
	// 일반:1, 튜터:2, 기업:3, 튜티: 4, 관리자999
	public int memberCateg4(String memberId) {
		int member = 1;

		try {
			String sql0 = "select count(member_info.member_id) "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null and " + "member_info.member_id = ?";
			PreparedStatement pstmt0 = conn.prepareStatement(sql0);
			pstmt0.setString(1, memberId);
			ResultSet rs0 = pstmt0.executeQuery();

			String sql = "select tutor_info.member_id from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id " + "and member_info.member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();

			String sql2 = "select distinct company_id " + "from member_info, company_info " + "where company_id= ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			ResultSet rs2 = pstmt2.executeQuery();

			String sql3 = "select distinct id " + "from startersManager " + "where id= ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, memberId);
			ResultSet rs3 = pstmt3.executeQuery();

			if (rs0.next())
				member = 4;
			if (rs.next())
				member = 2;
			if (rs2.next())
				member = 3;
			if (rs3.next())
				member = 999;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	/** 직종 삭제 - delete */
	public boolean deleteJobSelects(String memberId) {
		boolean result = false;

		try {
			String sql = "delete from job_select" + " where member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 포폴용 튜티 확인 */
	public int tuteememberCateg(String memberId) {
		int tuteemember = 0;

		try {
			String sql = "select count(member_info.member_id) "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null and " + "member_info.member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				tuteemember = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tuteemember;
	}

	/** 튜터튜티 회원분류 */
	public int memberCateg(String memberId) {
		int member = 0;

		try {
			String sql = "select count(resume) from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id " + "and member_info.member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				member = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	/** 튜터튜티기업 회원분류 */
	public int memberCateg2(String memberId) {
		int member = 1;

		try {
			String sql = "select tutor_info.member_id from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id " + "and member_info.member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();

			String sql2 = "select distinct company_id " + "from member_info, company_info " + "where company_id= ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			ResultSet rs2 = pstmt2.executeQuery();

			String sql3 = "select distinct id " + "from startersManager " + "where id= ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, memberId);
			ResultSet rs3 = pstmt3.executeQuery();

			if (rs.next())
				member = 2;
			if (rs2.next())
				member = 3;
			if (rs3.next())
				member = 999;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	/** 튜터튜티기업 회원분류 */
	public int memberCateg3(String memberId) {
		int member = 1;

		try {
			String sql = "select member_id from member_info " + "where member_info.member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();

			String sql2 = "select distinct company_id " + "from member_info, company_info " + "where company_id= ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			ResultSet rs2 = pstmt2.executeQuery();

			String sql3 = "select distinct id " + "from startersManager " + "where id= ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, memberId);
			ResultSet rs3 = pstmt3.executeQuery();

			if (rs.next())
				member = 2;
			if (rs2.next())
				member = 3;
			if (rs3.next())
				member = 999;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	/** 기업 회원분류 */
	public int companyCateg(String memberId) {
		int member = 0;

		try {
			String sql = "select distinct company_id " + "from member_info, company_info " + "where company_id= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				member = 3;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	/** 직종 선택 */
	public boolean JobSelects(String memberId, int middleCategId) {
		boolean result = false;
		try {
			String sql = "insert into job_select(member_id, middle_categ_id) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, middleCategId);
			if (pstmt.executeUpdate() == 1)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 튜티회원가입 - insert */
	public boolean addMemberTutee(MemberVO vo) {
		return addMemberTutee(vo.getMemberId(), vo.getName(), vo.getPasswd(), vo.getBirth(), vo.getGender(),
				vo.getEmail(), vo.getPhoneNum(), vo.getIntro(), vo.getregisterDate());
	}

	public boolean addMemberTutee(String memberId, String name, String passwd, String birth, String gender,
			String email, String phoneNum, String intro, String registerDate) {
		boolean result = false;
		try {
			String sql = "INSERT INTO member_info(member_id,name,passwd,birth,gender,email,phone_num,intro,register_date) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, name);
			pstmt.setString(3, passwd);
			pstmt.setString(4, birth);
			pstmt.setString(5, gender);
			pstmt.setString(6, email);
			pstmt.setString(7, phoneNum);
			pstmt.setString(8, intro);
			pstmt.setString(9, registerDate);

			if (pstmt.executeUpdate() == 1)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 튜터회원가입 - insert */
	public boolean addMemberTutor(String memberId, String name, String passwd, String birth, String gender,
			String email, String phoneNum, String intro, String resume, String portfolio, String registerDate) {
		boolean result = false;

		try {
			String sql = "INSERT INTO member_info(member_id,name,passwd,birth,gender,email,phone_num,intro,register_date) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, name);
			pstmt.setString(3, passwd);
			pstmt.setString(4, birth);
			pstmt.setString(5, gender);
			pstmt.setString(6, email);
			pstmt.setString(7, phoneNum);
			pstmt.setString(8, intro);
			pstmt.setString(9, registerDate);

			String sql1 = "INSERT INTO tutor_info(member_id, resume, portfolio) values(?, ?, ?)";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, memberId);
			pstmt1.setString(2, resume);
			pstmt1.setString(3, portfolio);

			if (pstmt.executeUpdate() == 1 && pstmt1.executeUpdate() == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 기업회원가입 - insert */
	public boolean addMemberCompany(String companyId, String name, String passwd, String email, String phoneNum,
			String address, String companyUrl, String companyRange, String companyBirth, String companyIntroduce,
			String registerDate) {
		boolean result = false;
		try {
			String sql1 = "INSERT INTO company_info(company_id, name, passwd, email, phone_num, address, company_url, company_range, company_birth, company_introduce, register_date) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, companyId);
			pstmt1.setString(2, name);
			pstmt1.setString(3, passwd);
			pstmt1.setString(4, email);
			pstmt1.setString(5, phoneNum);
			pstmt1.setString(6, address);
			pstmt1.setString(7, companyUrl);
			pstmt1.setString(8, companyRange);
			pstmt1.setString(9, companyBirth);
			pstmt1.setString(10, companyIntroduce);
			pstmt1.setString(11, registerDate);

			// String sql2 = "INSERT INTO company_image(company_id, image) "
			// + "VALUES (?, ?)";
			// PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			//
			// pstmt2.setString(1, companyId);
			// pstmt2.setString(1, img1);
			//
			// if(pstmt1.executeUpdate()==1 && pstmt2.executeUpdate()==1)
			if (pstmt1.executeUpdate() == 1)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addMemberCompanyImage(String companyId, String img1) {
		boolean result = false;
		try {
			String sql2 = "INSERT INTO company_image(company_id, image) " + "VALUES (?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, companyId);
			pstmt2.setString(2, img1);

			if (pstmt2.executeUpdate() == 1)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 검색 기업 리스트
	public ArrayList<CompanyVO> selectCompanyList(String name, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		try {
			String sql = "select distinct company_info.company_id, name, passwd, email, phone_num, address, company_url, company_range, company_birth, company_introduce, register_date  "
					+ "from company_info, company_image "
					+ "where company_info.company_id = company_image.company_id and " + "name like ? "
					+ "order by company_id asc limit ?, ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, start - 1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new CompanyVO(rs.getString("company_id"), rs.getString("name"), rs.getString("passwd"),
						rs.getString("email"), rs.getString("phone_num"), rs.getString("address"),
						rs.getString("company_url"), rs.getString("company_range"), rs.getString("company_birth"),
						rs.getString("company_introduce"), rs.getString("register_date").substring(0, 10)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 검색 기업 갯수 조회
	public int selectCompanyCount(String name) {
		int selectCompanyAllCount = 0;
		String sql = "select count(*) from company_info where name like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectCompanyAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectCompanyAllCount;
	}

	/**
	 * 회원가입(튜터, 튜티 공통)시 아이디 중복 체크 - select 아이디가 중복안되면 false 출력 아이디가 중복되면 true 출력
	 */
	public boolean memberIdCheck(String memberId) {
		boolean result = false; // 같은 아이디 없음
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try {
			String sql1 = "select member_id " + "from member_info " + "where member_id= ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, memberId);
			rs1 = pstmt1.executeQuery();

			String sql2 = "select distinct company_id " + "from member_info, company_info " + "where company_id= ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			rs2 = pstmt2.executeQuery();

			String sql3 = "select distinct id " + "from startersManager " + "where id= ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, memberId);
			rs3 = pstmt3.executeQuery();

			if (rs1.next() || rs2.next() || rs3.next()) // 아이디 있음
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean memberIdCheck2(String memberId) {
		boolean result = false; // 같은 아이디 없음
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try {
			String sql1 = "select member_id " + "from member_info " + "where member_id= ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, memberId);
			rs1 = pstmt1.executeQuery();

			String sql2 = "select distinct company_id " + "from member_info, company_info " + "where company_id= ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			rs2 = pstmt2.executeQuery();

			String sql3 = "select distinct id " + "from startersManager " + "where id= ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, memberId);
			rs3 = pstmt3.executeQuery();

			if (rs1.next() || rs2.next() || rs3.next()) // 아이디 있음
				result = false;
			else // 아이디 없음
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 로그인 - select */ // 수정
	public String getLogin(String memberId, String passwd) {
		String name = "";
		try {
			String sql = "select member_info.name " + " from member_info" + " where member_info.member_id = ? and"
					+ " passwd = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, passwd);

			String sql2 = "select company_info.name " + " from company_info" + " where company_info.company_id = ? and"
					+ " passwd = ? ";

			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			pstmt2.setString(2, passwd);

			String sql3 = "select id " + " from startersManager" + " where id = ? and" + " passwd = ? ";

			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, memberId);
			pstmt3.setString(2, passwd);

			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
			ResultSet rs3 = pstmt3.executeQuery();

			if (rs.next())
				name = rs.getString("member_info.name");
			if (rs2.next())
				name = rs2.getString("company_info.name");

			if (rs3.next())
				name = rs3.getString("id");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 아이디 찾기 - select 이메일과 이름으로 아이디를 찾는다.
	 */
	public String findId(String name, String email) {
		String id = null;
		try {
			String sql = "select member_id" + " from member_info" + " where name = ? and" + " email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);

			ResultSet rs = pstmt.executeQuery();

			String sql2 = "select company_id" + " from company_info" + " where name = ? and" + " email = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, name);
			pstmt2.setString(2, email);
			ResultSet rs2 = pstmt2.executeQuery();

			if (rs.next())
				id = rs.getString("member_info.member_id");
			if (rs2.next())
				id = rs2.getString("company_info.company_id");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 비밀번호 찾기 - select 아이디, 이메일, 전화번호로 비밀번호를 찾았는지를 확인
	 */
	public String findPw(String memberId, String email, String name) {
		String pw = null;
		try {
			String sql = "select passwd" + " from member_info" + " where member_id = ? and" + " email = ? and"
					+ " name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, email);
			pstmt.setString(3, name);

			ResultSet rs = pstmt.executeQuery();

			String sql2 = "select passwd" + " from company_info" + " where company_id = ? and" + " email = ? and"
					+ " name = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, memberId);
			pstmt2.setString(2, email);
			pstmt2.setString(3, name);

			ResultSet rs2 = pstmt2.executeQuery();

			if (rs.next())
				pw = rs.getString("member_info.passwd");
			if (rs2.next())
				pw = rs2.getString("company_info.passwd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pw;
	}

	/** 비밀번호 수정 - update */
	public boolean updateMemberPasswd(String memberId, String passwd, String email) {
		boolean result = false;
		try {
			String sql = "update member_info " + "set passwd = ? " + "where member_id = ? and" + " email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, passwd);
			pstmt.setString(3, email);
			if (pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 튜티회원 정보 수정 - update */
	public boolean updateTuteeMember(String memberId, String passwd, String email, String phoneNum, String intro) {
		boolean result = false;
		try {
			String sql = "update member_info " + "set passwd = ?, email = ? , phone_num = ?, intro = ? "
					+ "where member_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			pstmt.setString(3, phoneNum);
			pstmt.setString(4, intro);
			pstmt.setString(5, memberId);

			if (pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 튜터회원 정보 수정 - update */
	public boolean updateTutorMember(String memberId, String passwd, String email, String phoneNum, String intro,
			String resume, String portfolio) {
		boolean result = false;
		try {
			String sql = "update member_info, tutor_info "
					+ "set passwd = ?, email = ? , phone_num = ?, intro = ?, resume = ?, portfolio = ? "
					+ "where member_info.member_id = ? and " + "member_info.member_id = tutor_info.member_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			pstmt.setString(3, phoneNum);
			pstmt.setString(4, intro);
			pstmt.setString(7, memberId);
			pstmt.setString(5, resume);
			pstmt.setString(6, portfolio);

			// String sql1 = "update tutor_info "
			// + "set resume = ?, portfolio = ? "
			// +"where member_id = ? ";
			// PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			// pstmt1.setString(1, resume);
			// pstmt1.setString(2, portfolio);
			// pstmt1.setString(3, memberId);

			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 회원 탈퇴 - delete */
	public int deleteMember(String memberId, String passwd) {
		ResultSet rs = null;

		String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
		int x = -1;

		try {
			// 1. 비밀번호 조회
			String sql = "select passwd from member_info where member_id = ?";

			// 2. 회원 삭제
			String sql2 = "delete from member_info" + " where member_id = ?";

			// 1. 아이디에 해당하는 비밀번호를 조회한다.
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, memberId);
			rs = pstmt1.executeQuery();

			// 2. 회원 삭제
			// PreparedStatement pstmt2=conn.prepareStatement(sql);
			// pstmt2.setString(1, companyId);
			// pstmt2.executeUpdate();

			if (rs.next()) {
				dbpw = rs.getString("passwd");
				if (dbpw.equals(passwd)) // 입력된 비밀번호와 DB비번 비교
				{
					// 같을경우 회원삭제 진행
					// 2. 회원 삭제
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, memberId);
					pstmt2.executeUpdate();
					x = 1; // 삭제 성공
				} else {
					x = 0; // 비밀번호 비교결과 - 다름
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	} // end deleteMember

	/** 회원 탈퇴 - delete */
	public int deleteCompany(String companyId, String pw) {
		ResultSet rs = null;

		String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
		int x = -1;

		try {
			// 1. 비밀번호 조회
			String sql = "select passwd from company_info where company_id = ?";

			// 2. 회원 삭제
			String sql2 = "delete from company_info" + " where company_id = ?";

			// 1. 아이디에 해당하는 비밀번호를 조회한다.
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, companyId);
			rs = pstmt1.executeQuery();

			// 2. 회원 삭제
			// PreparedStatement pstmt2=conn.prepareStatement(sql);
			// pstmt2.setString(1, companyId);
			// pstmt2.executeUpdate();

			if (rs.next()) {
				dbpw = rs.getString("passwd");
				if (dbpw.equals(pw)) // 입력된 비밀번호와 DB비번 비교
				{
					// 같을경우 회원삭제 진행
					// 2. 회원 삭제
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, companyId);
					pstmt2.executeUpdate();
					x = 1; // 삭제 성공
				} else {
					x = 0; // 비밀번호 비교결과 - 다름
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	} // end deleteMember

	/**
	 * 10명의 회원목록 아이디랑 이름만 - 튜터
	 */
	public ArrayList<MemberVO> getMemberTen(int start, int end) {
		ArrayList<MemberVO> getMemberTenList = new ArrayList();
		try {
			String sql = "select member_info.member_id, name, birth, gender, email, phone_num, intro, resume, portfolio, register_date  "
					+ "from member_info,tutor_info " + "where member_info.member_id = tutor_info.member_id "
					+ "order by member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				getMemberTenList.add(new MemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"),
						rs.getString("register_date").substring(0, 10)));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getMemberTenList;
	}

	/**
	 * 10명의 회원목록 아이디랑 이름만 - 기업
	 */

	public ArrayList<CompanyVO> getCompanys(int start, int end) {
		ArrayList<CompanyVO> getCompanys = new ArrayList();
		try {
			String sql = "select company_info.company_id, name, email, phone_num, address, company_url, company_range, company_birth, company_introduce, register_date "
					+ "from company_info " + "order by company_info.company_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
				getCompanys.add(new CompanyVO(rs.getString("company_id"), rs.getString("name"), rs.getString("email"),
						null, rs.getString("phone_num"), rs.getString("address"), rs.getString("company_url"),
						rs.getString("company_range"), rs.getString("company_birth"), rs.getString("company_introduce"),
						rs.getString("register_date").substring(0, 10)));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCompanys;
	}

	public ArrayList<ImageVO> getCompanysImage(String companyId) {
		// String images = null;
		ArrayList<ImageVO> images = new ArrayList();
		try {
			String sql = "select company_info.company_id, company_image.image " + "from company_info, company_image "
					+ "where company_info.company_id = company_image.company_id and " + "company_info.company_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// for(int i = 1; i< rs.length(); i++){
				images.add(new ImageVO(rs.getString("company_info.company_id"), rs.getString("image")));
				// images = rs.getString("image");
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}

	/**
	 * 등록된 전체 기업의 수 select
	 */
	public int companyCount() {
		int companyAllCount = 0;
		String sql = "select count(*) " + "from company_info ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				companyAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyAllCount;
	}

	/** 멤버 잡셀렉트 */
	public ArrayList<StringJobSelectVO> getMemberJob(String memberId) { // JobSelectVO에
																		// 보면
																		// memberId를
																		// tutoringId로
																		// 생각한다.
		// String images = null;
		ArrayList<StringJobSelectVO> sjob = new ArrayList();
		try {
			String sql = "select job_select.select_id, job_select.member_id, middle_category.middle_categ_id, middle_category.middle_name "
					+ "from member_info, job_select, middle_category  "
					+ "where member_info.member_id = job_select.member_id and "
					+ "job_select.middle_categ_id = middle_category.middle_categ_id and " + "member_info.member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sjob.add(new StringJobSelectVO(rs.getInt("select_id"), rs.getString("member_id"),
						rs.getInt("middle_categ_id"), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sjob;
	}

	public MemberVO getTutorDetail(String memberId) {
		PreparedStatement pstmt;
		ResultSet rs;
		MemberVO vo = null;

		try {
			String sql = "select member_info.member_id, name, birth, gender, email, phone_num, intro, resume, portfolio, register_date "
					+ "from member_info, tutor_info " + "where member_info.member_id = ? and "
					+ "member_info.member_id = tutor_info.member_id ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			vo = new MemberVO();

			while (rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoneNum(rs.getString("phone_num"));
				vo.setIntro(rs.getString("intro"));
				vo.setResume(rs.getString("resume"));
				vo.setPortfolio(rs.getString("portfolio"));
				vo.setregisterDate(rs.getString("register_date"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/** 특정 튜터 회원 정보목록 보기 */
	public MemberVO getTutor(String memberId) {
		MemberVO member = new MemberVO();
		try {
			String sql = "select member_info.member_id, name, birth, gender, email, phone_num, intro, resume, portfolio, register_date "
					+ "from member_info, tutor_info " + "where member_info.member_id = ? and "
					+ "member_info.member_id = tutor_info.member_id ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setMemberId(rs.getString("member_id"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setPhoneNum(rs.getString("phone_num"));
				member.setIntro(rs.getString("intro"));
				member.setResume(rs.getString("resume"));
				member.setPortfolio(rs.getString("portfolio"));
				member.setregisterDate(rs.getString("register_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	// 1101 추가
	/**
	 * 등록된 전체 튜터의 수 select
	 */
	public int tuteeCount() {
		int tuteeAllCount = 0;
		String sql = "select count(*) " + "from member_info left join tutor_info "
				+ "on member_info.member_id = tutor_info.member_id " + "where resume is null ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				tuteeAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tuteeAllCount;
	}

	/**
	 * 등록된 전체 튜터의 수 select
	 */
	public int tutorCount() {
		int tutorAllCount = 0;
		String sql = "select count(*) " + "from member_info,tutor_info "
				+ "where member_info.member_id = tutor_info.member_id ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				tutorAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutorAllCount;
	}

	/** 회원정보리스트 보기(튜티) */
	public ArrayList<MemberVO> getTuteeLists(int start, int end) {
		ArrayList<MemberVO> getTuteeList = new ArrayList();
		try {
			String sql = "select member_info.member_id, name, birth, gender, email, phone_num, intro, register_date "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null " + "order by member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				getTuteeList.add(new MemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getTuteeList;
	}

	/** 특정회원정보 보기(튜티) */
	public MemberVO getTutee(String memberId) {
		MemberVO member = new MemberVO();
		try {
			String sql = "select member_info.member_id, name, birth, gender, email, phone_num, intro, register_date "
					+ "from member_info left join tutor_info " + "on member_info.member_id = tutor_info.member_id "
					+ "where member_info.member_id = ? and " + "resume is null";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setMemberId(rs.getString("member_id"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setPhoneNum(rs.getString("phone_num"));
				member.setIntro(rs.getString("intro"));
				member.setregisterDate(rs.getString("register_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	/** 관리자 */
	public int adminCateg(String memberId) {
		int member = 0;

		try {
			String sql = "select distinct id " + "from startersManager " + "where id= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				member = 999;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public MemberVO getTuteeDetail(String memberId) {
		PreparedStatement pstmt;
		ResultSet rs;
		MemberVO vo = null;
		try {
			String sql = "select member_info.member_id, name, birth, gender, email, phone_num, intro, register_date "
					+ "from member_info " + "where member_info.member_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			vo = new MemberVO();

			while (rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoneNum(rs.getString("phone_num"));
				vo.setIntro(rs.getString("intro"));
				vo.setregisterDate(rs.getString("register_date"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/** 기업 마이페이지에서 사용 */

	/** 특정 기업 회원 정보 불러오기 */
	public CompanyVO getComapnyInfoDetail(String companyId) {
		PreparedStatement pstmt;
		ResultSet rs;
		CompanyVO vo = null;

		try {
			String sql = "select company_id, name, email, phone_num, address, company_url, company_range, company_birth, company_introduce, register_date "
					+ "from company_info " + "where company_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			rs = pstmt.executeQuery();

			vo = new CompanyVO();

			while (rs.next()) {
				vo.setCompanyId(rs.getString("company_id"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoneNum(rs.getString("phone_num"));
				vo.setAddress(rs.getString("address"));
				vo.setCompanyUrl(rs.getString("company_url"));
				vo.setCompanyRange(rs.getString("company_range"));
				vo.setCompanyBirth(rs.getString("company_birth"));
				vo.setCompanyIntroduce(rs.getString("company_introduce"));
				vo.setRegisterDate(rs.getString("register_date"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/** 특정 기업 회원 정보목록 보기 */
	public CompanyVO getComapny(String companyId) {
		CompanyVO company = new CompanyVO();
		try {
			String sql = "select company_id, name, email, phone_num, address, company_url, company_range, company_birth, company_introduce, register_date "
					+ "from company_info " + "where company_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company.setCompanyId(rs.getString("company_id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPhoneNum(rs.getString("phone_num"));
				company.setAddress(rs.getString("address"));
				company.setCompanyUrl(rs.getString("company_url"));
				company.setCompanyRange(rs.getString("company_range"));
				company.setCompanyBirth(rs.getString("company_birth"));
				company.setCompanyIntroduce(rs.getString("company_introduce"));
				company.setRegisterDate(rs.getString("register_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	/** 기업회원 정보 수정 - update */

	public boolean updateCompanyMember(String passwd, String email, String phoneNum, String address, String companyUrl,
			String companyRange, String companyBirth, String companyIntroduce, String companyId) {
		boolean result = false;
		try {
			String sql = "update company_info "
					+ "set passwd=? ,email = ? , phone_num = ?, address = ?, company_url = ?, company_range = ?, company_birth = ?, company_introduce = ? "
					+ "where company_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			pstmt.setString(3, phoneNum);
			pstmt.setString(4, address);
			pstmt.setString(5, companyUrl);
			pstmt.setString(6, companyRange);
			pstmt.setString(7, companyBirth);
			pstmt.setString(8, companyIntroduce);
			pstmt.setString(9, companyId);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 기업 이미지 불러오기 */
	public ArrayList<ImageVO> getCompanyImage(String companyId) {
		ArrayList<ImageVO> image = new ArrayList();
		try {
			String sql = "select company_info.company_id, image " + "from company_info, company_image "
					+ "where company_info.company_id = company_image.company_id and " + "company_info.company_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				image.add(new ImageVO(rs.getString("company_id"), rs.getString("image")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return image;
	}

	/** 기업 이미지 등록 */
	public boolean registerCompanyImage(String companyId, String image) {
		boolean result = false;
		try {
			String sql2 = "INSERT INTO company_image(company_id, image) " + "VALUES (?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, companyId);
			pstmt2.setString(2, image);

			if (pstmt2.executeUpdate() == 1)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 기업 이미지 삭제 */
	public boolean deleteCompanyImage(String companyId) {
		boolean result = false;
		try {
			String sql = "delete from company_image where company_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 면접신청서안에 불러온 튜티명 */
	public InterviewPortfolioVO interviewPortfolioDetail(int portfolioId) {
		PreparedStatement pstmt;
		ResultSet rs;
		InterviewPortfolioVO portfoliovo = null;

		try {
			String sql = "select member_info.name, portfolio.portfolio_id, member_info.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text, portfolio.upload_date, portfolio_count "
					+ "from portfolio, member_info " + "where portfolio.member_id = member_info.member_id and "
					+ "portfolio.portfolio_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, portfolioId);
			rs = pstmt.executeQuery();

			portfoliovo = new InterviewPortfolioVO();
			if (rs.next()) {
				/*
				 * portfoliovo.add(new
				 * InterviewPortfolioVO(rs.getString("member_info.name"),
				 * rs.getInt("portfolio.portfolio_id"),
				 * rs.getString("member_info.member_id"),
				 * rs.getString("portfolio_title"),
				 * rs.getString("portfolio_method"),
				 * rs.getString("portfolio_url"),
				 * rs.getString("portfolio_file"),
				 * rs.getString("portfolio_text"),
				 * rs.getString("portfolio.upload_date"),
				 * rs.getInt("portfolio_count")));
				 */
				portfoliovo.setTuteeName(rs.getString("member_info.name"));
				portfoliovo.setPortfolioId(rs.getInt("portfolio.portfolio_id"));
				portfoliovo.setMemberId(rs.getString("member_info.member_id"));
				portfoliovo.setPortfolioTitle(rs.getString("portfolio_title"));
				portfoliovo.setPortfolioMethod(rs.getString("portfolio_method"));
				portfoliovo.setPortfolioUrl(rs.getString("portfolio_url"));
				portfoliovo.setPortfolioFile(rs.getString("portfolio_file"));
				portfoliovo.setPortfolioText(rs.getString("portfolio_text"));
				portfoliovo.setPortfolioCount(rs.getInt("portfolio_count"));
				portfoliovo.setUploadDate(rs.getString("portfolio.upload_date"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return portfoliovo;
	}

	// 1101 추가

	/** 회원정보리스트 보기(튜티) */
	public ArrayList<AdminTMemberVO> getAdminTutee(int start, int end) {
		ArrayList<AdminTMemberVO> getTuteeList = new ArrayList();
		try {
			String sql = "select tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				getTuteeList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getTuteeList;
	}

	/** 튜터아이디 검색 */
	public ArrayList<AdminTMemberVO> selectAdminMemberTuteeIdList(String memberId, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		try {
			String sql = "select distinct tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "where tuteeMembers.member_id like ? "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.register_date asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			pstmt.setInt(2, start - 1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 검색 튜터아이디 select
	 */
	public int selectAdminMemberTuteeIdCount(String memberId) {
		int selectAdminMemberTuteeIdCount = 0;
		String sql = "select count(*) "
				+ "from tuteeMembers "
				+ "where tuteeMembers.member_id like ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAdminMemberTuteeIdCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAdminMemberTuteeIdCount;
	}

	// 회원 전체 검색
	public ArrayList<AdminTMemberVO> selectAllAdminMemberTuteeList(String memberId, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		try {
			String sql = "select distinct tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "where (tuteeMembers.member_id like ? or "
					+ "tuteeMembers.name like ?) "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.register_date asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			pstmt.setString(2, "%" + memberId + "%");
			pstmt.setInt(3, start - 1);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 회원전체검색수
	 */
	public int selectAllAdminMemberTuteeCount(String memberId) {
		int selectAllAdminMemberTuteeCount = 0;
		String sql = "select count(*) "
				+ "from tuteeMembers "
				+ "where (tuteeMembers.name like ? or "
				+ "tuteeMembers.member_id like ?) ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			pstmt.setString(2, "%" + memberId + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAllAdminMemberTuteeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAllAdminMemberTuteeCount;
	}

	/** 회원관리 검색_튜티이름명 */
	public ArrayList<AdminTMemberVO> selectAdminMemberTuteeNameList(String name, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		try {
			String sql = "select distinct tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "where tuteeMembers.name like ? "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.register_date asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, start - 1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 검색 포트폴리오의 수_튜티명 select
	 */
	public int selectAdminMemberTuteeNameListCount(String name) {
		int selectAdminMemberTuteeNameListCount = 0;
		String sql = "select count(*) "
				+ "from tuteeMembers "
				+ "where tuteeMembers.name like ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAdminMemberTuteeNameListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAdminMemberTuteeNameListCount;
	}

	// 좋아요, 조회수, 번호순....
	/**
	 * select_아이디순
	 */
	public ArrayList<AdminTMemberVO> getTuteeMemberId(int start, int end) {
		ArrayList<AdminTMemberVO> adminMemberTuteeList = new ArrayList();
		try {
			String sql = "select distinct tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				adminMemberTuteeList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberTuteeList;
	}

	/**
	 * select_이름순
	 */
	public ArrayList<AdminTMemberVO> getTuteeMemberName(int start, int end) {
		ArrayList<AdminTMemberVO> adminMemberTuteeList = new ArrayList();
		try {
			String sql = "select distinct tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.name asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberTuteeList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberTuteeList;
	}

	/**
	 * select_가입날짜순
	 */
	public ArrayList<AdminTMemberVO> getTuteeMemberRegisterDate(int start, int end) {
		ArrayList<AdminTMemberVO> adminMemberTuteeList = new ArrayList();
		try {
			String sql = "select distinct tuteeMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tuteeMembers.name, tuteeMembers.birth, tuteeMembers.gender, tuteeMembers.email, tuteeMembers.phone_num,"
					+ "tuteeMembers.intro, tuteeMembers.register_date "
					+ "from tuteeMembers left join job_select on tuteeMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tuteeMembers.member_id " 
					+ "order by tuteeMembers.register_date asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberTuteeList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), null, null, rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberTuteeList;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// 좋아요, 조회수, 번호순....
	/**
	 * select_아이디순
	 */
	public ArrayList<CompanyVO> getCompanyMemberId(int start, int end) {
		ArrayList<CompanyVO> adminMemberCompanyList = new ArrayList();
		try {
			String sql = "select company_info.company_id, name, email, phone_num, address, company_url, company_range, company_birth, "
					+ "company_introduce, register_date "
					+ "from company_info " 
					+ "order by company_info.company_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberCompanyList.add(new CompanyVO(rs.getString("company_id"), rs.getString("name"), rs.getString("email"),
						null, rs.getString("phone_num"), rs.getString("address"), rs.getString("company_url"),
						rs.getString("company_range"), rs.getString("company_birth"), rs.getString("company_introduce"),
						rs.getString("register_date").substring(0,10)));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberCompanyList;
	}

	/**
	 * select_이름순
	 */
	public ArrayList<CompanyVO> getCompanyMemberName(int start, int end) {
		ArrayList<CompanyVO> adminMemberCompanyList = new ArrayList();
		try {
			String sql = "select company_info.company_id, name, email, phone_num, address, company_url, company_range, company_birth, "
					+ "company_introduce, register_date "
					+ "from company_info " 
					+ "order by company_info.name asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberCompanyList.add(new CompanyVO(rs.getString("company_id"), rs.getString("name"), rs.getString("email"),
						null, rs.getString("phone_num"), rs.getString("address"), rs.getString("company_url"),
						rs.getString("company_range"), rs.getString("company_birth"), rs.getString("company_introduce"),
						rs.getString("register_date").substring(0,10)));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberCompanyList;
	}
	/**
	 * select_가입날짜순
	 */
	public ArrayList<CompanyVO> getCompanyMemberRegisterDate(int start, int end) {
		ArrayList<CompanyVO> adminMemberCompanyList = new ArrayList();
		try {
			String sql = "select company_info.company_id, name, email, phone_num, address, company_url, company_range, company_birth, "
					+ "company_introduce, register_date "
					+ "from company_info " 
					+ "order by company_info.register_date asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberCompanyList.add(new CompanyVO(rs.getString("company_id"), rs.getString("name"), rs.getString("email"),
						null, rs.getString("phone_num"), rs.getString("address"), rs.getString("company_url"),
						rs.getString("company_range"), rs.getString("company_birth"), rs.getString("company_introduce"),
						rs.getString("register_date").substring(0,10)));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberCompanyList;
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	/** 회원정보리스트 보기(튜터) */
	public ArrayList<AdminTMemberVO> getAdminTutor(int start, int end) {
		ArrayList<AdminTMemberVO> getTutorList = new ArrayList();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				getTutorList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getTutorList;
	}

	/** 튜터아이디 검색 */
	public ArrayList<AdminTMemberVO> selectAdminMemberTutorIdList(String memberId, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "where tutorMembers.member_id like ? "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.member_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			pstmt.setInt(2, start - 1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 검색 튜터아이디 select
	 */
	public int selectAdminMemberTutorIdCount(String memberId) {
		int selectAdminMemberTutorIdCount = 0;
		String sql = "select count(*) "
				+ "from tutorMembers "
				+ "where tutorMembers.member_id like ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAdminMemberTutorIdCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAdminMemberTutorIdCount;
	}

	// 회원 전체 검색
	public ArrayList<AdminTMemberVO> selectAllAdminMemberTutorList(String memberId, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "where (tutorMembers.member_id like ? or "
					+ "tutorMembers.name like ?) "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.member_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			pstmt.setString(2, "%" + memberId + "%");
			pstmt.setInt(3, start - 1);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 회원전체검색수
	 */
	public int selectAllAdminMemberTutorCount(String memberId) {
		int selectAllAdminMemberTutorCount = 0;
		String sql = "select count(*) "
				+ "from tutorMembers "
				+ "where (tutorMembers.name like ? or "
				+ "tutorMembers.member_id like ?) ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + memberId + "%");
			pstmt.setString(2, "%" + memberId + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAllAdminMemberTutorCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAllAdminMemberTutorCount;
	}

	/** 회원관리 검색_튜티이름명 */
	public ArrayList<AdminTMemberVO> selectAdminMemberTutorNameList(String name, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "where tutorMembers.name like ? "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.member_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, start - 1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 검색 포트폴리오의 수_튜티명 select
	 */
	public int selectAdminMemberTutorNameListCount(String name) {
		int selectAdminMemberTutorNameListCount = 0;
		String sql = "select count(*) "
				+ "from tutorMembers "
				+ "where tutorMembers.name like ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAdminMemberTutorNameListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAdminMemberTutorNameListCount;
	}

	// 좋아요, 조회수, 번호순....
	/**
	 * select_아이디순
	 */
	public ArrayList<AdminTMemberVO> getTutorMemberId(int start, int end) {
		ArrayList<AdminTMemberVO> adminMemberTutorList = new ArrayList();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				adminMemberTutorList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberTutorList;
	}

	/**
	 * select_이름순
	 */
	public ArrayList<AdminTMemberVO> getTutorMemberName(int start, int end) {
		ArrayList<AdminTMemberVO> adminMemberTutorList = new ArrayList();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.name asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberTutorList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberTutorList;
	}

	/**
	 * select_가입날짜순
	 */
	public ArrayList<AdminTMemberVO> getTutorMemberRegisterDate(int start, int end) {
		ArrayList<AdminTMemberVO> adminMemberTutorList = new ArrayList();
		try {
			String sql = "select tutorMembers.member_id, ifnull(group_concat(middle_name), '선택 x') as middle_name,"
					+ "tutorMembers.name, tutorMembers.birth, tutorMembers.gender, tutorMembers.email, tutorMembers.phone_num,"
					+ "tutorMembers.intro,tutorMembers.resume, tutorMembers.portfolio, tutorMembers.register_date "
					+ "from tutorMembers left join job_select on tutorMembers.member_id = job_select.member_id "
					+ "left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "group by tutorMembers.member_id "
					+ "order by tutorMembers.register_date asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberTutorList.add(new AdminTMemberVO(rs.getString("member_id"), rs.getString("name"), null,
						rs.getString("birth"), rs.getString("gender"), rs.getString("email"), rs.getString("phone_num"),
						rs.getString("intro"), rs.getString("resume"), rs.getString("portfolio"), rs.getString("register_date").substring(0,10), rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberTutorList;
	}

	
	// -------------------------------------------------------------
	
	/** 회원정보리스트 보기(튜티) */
	public ArrayList<AdminAMemberVO> getAllMemberList(int start, int end) {
		ArrayList<AdminAMemberVO> getAllMemberList = new ArrayList();
		try {
			String sql = "select member_info.member_id, name, register_date, '튜티' as categ "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null "
					+ "union "
					+ "select member_info.member_id, name, register_date, '튜터' as categ "
					+ "from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id "
					+ "union "
					+ "select company_id, name,register_date, '기업' as categ "
					+ "from company_info " 
					+ "order by member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				getAllMemberList.add(new AdminAMemberVO(rs.getString("member_id"), rs.getString("name"), rs.getString("register_date").substring(0,10),rs.getString("categ")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getAllMemberList;
	}
	
	
	
	public int adminAllMemberCount() {
		int adminAllMemberCount = 0;
		String sql = "select count(*) c "
				+ "from ("
				+ "select member_info.member_id, name, register_date "
				+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
				+ "where resume is null "
				+ "union "
				+ "select tutor_info.member_id, name, register_date "
				+ "from member_info, tutor_info  "
				+ "where member_info.member_id = tutor_info.member_id "
				+ "union "
				+ "select company_id as member_id, name, register_date "
				+ "from company_info "
				+ ") c";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				adminAllMemberCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminAllMemberCount;
	}
	
	public int adminAllMemberSearchCount(String name) {
		int selectAdminMemberListCount = 0;
		String sql = "select count(*) c "
				+ "from ("
				+ "select distinct member_info.member_id, name, register_date, '튜티' as categ "
				+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
				+ "where resume is null and "
				+ "(name like ? or "
				+ "member_info.member_id like ?) "
				+ "union "
				+ "select distinct tutor_info.member_id, name, register_date , '튜터' as categ "
				+ "from member_info, tutor_info "
				+ "where member_info.member_id = tutor_info.member_id and "
				+ "(name like ? or "
				+ "tutor_info.member_id like ?) "
				+ "union "
				+ "select distinct company_id as member_id, name, register_date , '기업' as categ "
				+ "from company_info "
				+ "where (name like ? or "
				+ "company_id like ?) "
				+ ") c";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");
			pstmt.setString(3, "%" + name + "%");
			pstmt.setString(4, "%" + name + "%");
			pstmt.setString(5, "%" + name + "%");
			pstmt.setString(6, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				selectAdminMemberListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAdminMemberListCount;
	}
	
	
	// 검색 기업 리스트
	public ArrayList<AdminAMemberVO> selectAllMemberList(String name, int start, int end) {
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();
		try {
			String sql = "select distinct member_info.member_id, name, register_date, '튜티' as categ "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null and "
					+ "(name like ? or "
					+ "member_info.member_id like ?) "
					+ "union "
					+ "select distinct tutor_info.member_id, name, register_date , '튜터' as categ "
					+ "from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id and "
					+ "(name like ? or "
					+ "tutor_info.member_id like ?) "
					+ "union "
					+ "select distinct company_id as member_id, name, register_date , '기업' as categ "
					+ "from company_info "
					+ "where (name like ? or "
					+ "company_id like ?) "
					+ "order by member_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");
			pstmt.setString(3, "%" + name + "%");
			pstmt.setString(4, "%" + name + "%");
			pstmt.setString(5, "%" + name + "%");
			pstmt.setString(6, "%" + name + "%");
			pstmt.setInt(7, start - 1);
			pstmt.setInt(8, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new AdminAMemberVO(rs.getString("member_id"), rs.getString("name"), 
						rs.getString("register_date").substring(0,10),rs.getString("categ")));}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 좋아요, 조회수, 번호순....
	/**
	 * select_아이디순
	 */
	public ArrayList<AdminAMemberVO> getMemberId(int start, int end) {
		ArrayList<AdminAMemberVO> adminMemberList = new ArrayList();
		try {
			String sql = "select member_info.member_id, name, register_date, '튜티' as categ "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null "
					+ "union "
					+ "select member_info.member_id, name, register_date, '튜터' as categ "
					+ "from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id "
					+ "union "
					+ "select company_id, name,register_date, '기업' as categ "
					+ "from company_info " 
					+ "order by member_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberList.add(new AdminAMemberVO(rs.getString("member_id"), rs.getString("name"), 
						rs.getString("register_date").substring(0,10),rs.getString("categ")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberList;
	}

	/**
	 * select_이름순
	 */
	public ArrayList<AdminAMemberVO> getMemberName(int start, int end) {
		ArrayList<AdminAMemberVO> adminMemberList = new ArrayList();
		try {
			String sql = "select member_info.member_id, name, register_date, '튜티' as categ "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null "
					+ "union "
					+ "select member_info.member_id, name, register_date, '튜터' as categ "
					+ "from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id "
					+ "union "
					+ "select company_id, name,register_date, '기업' as categ "
					+ "from company_info " 
					+ "order by name asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberList.add(new AdminAMemberVO(rs.getString("member_id"), rs.getString("name"), 
						rs.getString("register_date").substring(0,10),rs.getString("categ")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberList;
	}
	/**
	 * select_가입날짜순
	 */
	public ArrayList<AdminAMemberVO> getMemberRegisterDate(int start, int end) {
		ArrayList<AdminAMemberVO> adminMemberList = new ArrayList();
		try {
			String sql = "select member_info.member_id, name, register_date, '튜티' as categ "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null "
					+ "union "
					+ "select member_info.member_id, name, register_date, '튜터' as categ "
					+ "from member_info, tutor_info "
					+ "where member_info.member_id = tutor_info.member_id "
					+ "union "
					+ "select company_id, name,register_date, '기업' as categ "
					+ "from company_info " 
					+ "order by register_date asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while (rs.next())
				adminMemberList.add(new AdminAMemberVO(rs.getString("member_id"), rs.getString("name"), 
						rs.getString("register_date").substring(0,10),rs.getString("categ")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return adminMemberList;
	}
	
	
}
