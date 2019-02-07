package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** 1. 객체는 선언, 생성, 초기화 혹은 선언, 생성, 할당으로 이루어져있다.
 *   2. 예외 관리
 *   	- 내 인터페이스에 throws와 try/catch가 있다면 try/catch로 간다.
 */

public class TutoringDAO {
	private Connection conn;
	private int tutoringId;
	public TutoringDAO(){
		try{
			//1 driver loading
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url,  id, pw);
		}catch(Exception e){
			e.printStackTrace();
		}//catch
	}// 생성자
	
	
	// 관리자 튜터링등록현황 부분
	public ArrayList<AdminTutoringVO> AdminTutoringList(int start, int end){ 
		ArrayList<AdminTutoringVO> tutorTutoringList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, upload_date "
					+ "from tutoring_info, member_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "order by tutoring_info.tutoring_id asc limit ?,? ";
			
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutorTutoringList.add(new AdminTutoringVO(rs.getInt("tutoring_info.tutoring_id"),rs.getString("tutor_id"), rs.getString("name"),rs.getString("title"),rs.getString("upload_date").substring(0,11)));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutorTutoringList;
	}
	
	// 특정 튜터링 듣는 인원
	public int tutoringCount(int tutoringId){
		int tutoringCount = 0;
		try{
			String sql = "select count(*) "
					+ "from tutoring_info, tutoring_apply_info "
					+ "where tutoring_info.tutoring_id = ? "
					+ "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,  tutoringId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				tutoringCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutoringCount;
	}
	
	// 직종
		public IntJobSelectVO AdminTutoringInfo(int id){
			PreparedStatement pstmt;
			ResultSet rs;
			IntJobSelectVO vo = null;

			try {
				String sql = "select tutoring_info.tutoring_id, tutoring_job_select.tutoring_id,middle_category.middle_categ_id, middle_category.middle_name  "
						+ "from tutoring_info, tutoring_job_select,middle_category  "
						+ "where tutoring_info.tutoring_id = tutoring_job_select.tutoring_id "
						+ "and tutoring_job_select.middle_categ_id = middle_category.middle_categ_id   "
						+ "and tutoring_job_select.tutoring_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,id);
				rs = pstmt.executeQuery();

				vo = new IntJobSelectVO();
				while(rs.next()){
					vo.setSelectId(rs.getInt("tutoring_info.tutoring_id"));
					vo.setThisId(rs.getInt("tutoring_job_select.tutoring_id"));
					vo.setMiddleCategId(rs.getInt("middle_category.middle_categ_id"));
					vo.setMiddleCategName(rs.getString("middle_category.middle_name"));
				}
				rs.close();
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vo;
		}
		
		/**튜터링 검색_전체*/
		public ArrayList<TutoringVO3> adminSelectTutoringList(String title, int start, int end ){
			PreparedStatement pstmt;				
			ResultSet rs;
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			try {
				String sql =  "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date, name "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "order by tutoring_id asc limit ?,? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + title + "%");
				pstmt.setString(2, "%" + title + "%");
				pstmt.setString(3, "%" + title + "%");
				pstmt.setInt(4, start-1);
				pstmt.setInt(5, end);
				rs = pstmt.executeQuery();

				while(rs.next()) {				
					list.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
				}   	
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return list;
		}
		
		/**튜터링 검색_튜터명*/
		public ArrayList<TutoringVO3> adminSelectTutorNameList(String tutorName, int start, int end ){
			PreparedStatement pstmt;				
			ResultSet rs;
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			try {
				String sql =  "select tutoring_id, tutoring_info.tutor_id, name, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and name like ? "
						+ "order by tutoring_id asc limit ?,? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + tutorName + "%");
				pstmt.setInt(2, start-1);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();

				while(rs.next()) {				
					list.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
				}   	
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return list;
		}
		
		// 튜터링 번호순
		public ArrayList<TutoringVO3> adminGetTutoring(int start, int end){ 
			ArrayList<TutoringVO3> tutoringList = new ArrayList();
			try{
				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date, name "
						+ " from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "order by tutoring_id asc limit ?,? ";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		// 날짜순 _1010수정
			public ArrayList<TutoringVO3> adminGetDateTutoring(int start, int end){ 
				ArrayList<TutoringVO3> tutoringList = new ArrayList();
				try{
					String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date,name "
							+ " from member_info, tutoring_info "
							+ "where member_info.member_id = tutoring_info.tutor_id "
							+ "order by upload_date desc limit ?,? ";
					PreparedStatement pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, start-1);
					pstmt.setInt(2, end);

					ResultSet rs = pstmt.executeQuery();
					// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
					while(rs.next())
						// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
						tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
					rs.close();
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				} // catch 
				return tutoringList;
			}
			/**1개월 리스트*/
			public ArrayList<TutoringVO3> adminGetAmonth(int start, int end){ 
				ArrayList<TutoringVO3> tutoringList = new ArrayList();
				try{
					String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date, name "
							+ "from tutoring_info, member_info "
							+ "where member_info.member_id = tutoring_info.tutor_id "
							+ "and	upload_date > date_add(now(), interval -1 month)"
							+ "order by upload_date desc limit ?,? ";
					PreparedStatement pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, start-1);
					pstmt.setInt(2, end);

					ResultSet rs = pstmt.executeQuery();
					// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
					while(rs.next())
						// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
						tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
					rs.close();
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				} // catch 
				return tutoringList;
			}
			public int adminGetAmonthCount(){
				int adminGetAmonthCount = 0;
				String sql = "select count(*) "
						+ "from tutoring_info, member_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and	upload_date > date_add(now(), interval -1 month)";
				try{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);

					while(rs.next())
						adminGetAmonthCount = rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return adminGetAmonthCount;
			}
			/**3개월 리스트*/
			public ArrayList<TutoringVO3> adminGetThreeMonth(int start, int end){ 
				ArrayList<TutoringVO3> tutoringList = new ArrayList();
				try{
					String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date, name "
							+ "from tutoring_info, member_info "
							+ "where member_info.member_id = tutoring_info.tutor_id "
							+ "and	upload_date > date_add(now(), interval -3 month)"
							+ "order by upload_date desc limit ?,? ";
					PreparedStatement pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, start-1);
					pstmt.setInt(2, end);

					ResultSet rs = pstmt.executeQuery();
					// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
					while(rs.next())
						// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
						tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
					rs.close();
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				} // catch 
				return tutoringList;
			}
			public int adminGetThreeMonthCount(){
				int adminGetThreeMonthCount = 0;
				String sql = "select count(*) "
						+ "from tutoring_info, member_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and	upload_date > date_add(now(), interval -3 month)";
				try{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);

					while(rs.next())
						adminGetThreeMonthCount = rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return adminGetThreeMonthCount;
			}
			/**6개월 리스트*/
			public ArrayList<TutoringVO3> adminGetSixMonth(int start, int end){ 
				ArrayList<TutoringVO3> tutoringList = new ArrayList();
				try{
					String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date, name "
							+ "from tutoring_info, member_info "
							+ "where member_info.member_id = tutoring_info.tutor_id "
							+ "and	upload_date > date_add(now(), interval -6 month)"
							+ "order by upload_date desc limit ?,? ";
					PreparedStatement pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, start-1);
					pstmt.setInt(2, end);

					ResultSet rs = pstmt.executeQuery();
					// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
					while(rs.next())
						// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
						tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
					rs.close();
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				} // catch 
				return tutoringList;
			}
			public int adminGetSixMonthCount(){
				int adminGetThreeMonthCount = 0;
				String sql = "select count(*) "
						+ "from tutoring_info, member_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and	upload_date > date_add(now(), interval -6 month)";
				try{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);

					while(rs.next())
						adminGetThreeMonthCount = rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return adminGetThreeMonthCount;
			}
			/**날짜조회 리스트*/
			public ArrayList<TutoringVO3> adminGetDate(String selectstartDate, String selectEndDate, int start, int end){ 
				ArrayList<TutoringVO3> tutoringList = new ArrayList();
				try{
					String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date, name "
							+ "from tutoring_info, member_info "
							+ "where member_info.member_id = tutoring_info.tutor_id "
							+ "and upload_date between ? and ? "
							+ "order by upload_date desc limit ?,? ";
					PreparedStatement pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, selectstartDate);
					pstmt.setString(2, selectEndDate);
					pstmt.setInt(3, start-1);
					pstmt.setInt(4, end);

					ResultSet rs = pstmt.executeQuery();
					// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
					while(rs.next())
						// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
						tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date").substring(0,11),rs.getString("name")));
					rs.close();
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				} // catch 
				return tutoringList;
			}
			public int adminGetDateCount(String selectstartDate, String selectEndDate){
				int adminGetDateCount = 0;
				String sql = "select count(*) "
						+ "from tutoring_info, member_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and upload_date between ? and ? ";
				try{
					PreparedStatement pstmt =conn.prepareStatement(sql);
					pstmt.setString(1, selectstartDate);
					pstmt.setString(2, selectEndDate);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next())
						adminGetDateCount = rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return adminGetDateCount;
			}
	
// ===========================================================	
	public ArrayList<TutoringVO2> getTutorTutoring(String tutorId,int start, int end){ 
		ArrayList<TutoringVO2> tutortutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
					+ "from tutoring_info "
					+ "where tutoring_info.tutor_id =? "
					+ "order by tutoring_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			pstmt.setString(1, tutorId);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutortutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents"), rs.getInt("count"), rs.getString("upload_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutortutoringList;
	}
	
	
	
	/**이미지*/
	public ArrayList<IntImageVO> getTutoringTuteeImage(int tutoringListNum){
		//		String images = null;
		ArrayList<IntImageVO> images = new ArrayList();
		try{
			String sql =  "select tutoring_info.tutoring_id, tutoring_image.image "
					+ "from tutoring_info, tutoring_image "
					+ "where tutoring_info.tutoring_id = tutoring_image.tutoring_id and "
					+ "tutoring_info.tutoring_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringListNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//		for(int i = 1; i< rs.length(); i++){
				images.add(new IntImageVO(rs.getInt("tutoring_info.tutoring_id"), rs.getString("image")));
				//		images = rs.getString("image");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return images;
	}
	

	// 1014
	public ArrayList<TutoringTuteeListVO2> getTutorTutoringTutee(String tutorId,int start, int end){ 
		ArrayList<TutoringTuteeListVO2> tutorTutoringtuteeList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutoring_info.tutor_id, tutoring_apply_info.tutee_id,name, title, subtitle, start_date, end_date, career, tutoring_info.price, contents, count, upload_date,tutoring_apply_id "
					+ "from tutoring_info, tutoring_apply_info, member_info "
					+ "where tutor_id = ? and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id "
					+ "order by name asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tutorId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);


			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutorTutoringtuteeList.add(new TutoringTuteeListVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("tutee_id"),rs.getString("name"),rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents"), rs.getInt("count"), rs.getString("upload_date"),rs.getInt("tutoring_apply_id")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutorTutoringtuteeList;
	}
	
	
	// 1014 수정
	/** 특정튜터가 쓴 튜터링 갯수*/
	public int tutorTutoringCount(String memberId){
		int tutortutoringCount = 0;
		try{
			String sql = "select count(*) "
					+ "from tutoring_info  "
					+ "where tutoring_info.tutor_id =? ";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,  memberId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				tutortutoringCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutortutoringCount;
	}
	
	// 1014 수정
	/** 특정튜터가 쓴 튜터링의 튜티 갯수*/
	public int tutorTutoringTuteeCount(String memberId){
		int tutortutoringtuteeCount = 0;
		try{
			String sql = "select count(*) "
					+ "from tutoring_info,member_info, tutoring_apply_info  "
					+ "where tutoring_info.tutor_id =? and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
					+ "tutoring_apply_info.tutee_id = member_info.member_id";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,  memberId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				tutortutoringtuteeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutortutoringtuteeCount;
	}
	
//	String sql = "select tutoring_apply_info.tutoring_apply_id, tutoring_info.tutoring_id, tutoring_info.tutor_id, tutoring_apply_info.tutee_id, title, subtitle, start_date, end_date, career, tutoring_info.price, contents, count, upload_date, start_time, end_time, "
//	+ "group_concat(case "
//	+ "when day = '일' then '0' "
//	+ "when day = '월' then '1' "
//	+ "when day = '화' then '2' "
//	+ "when day = '수' then '3' "
//	+ "when day = '목' then '4' "
//	+ "when day = '금' then '5' "
//	+ "else '6' "
//	+ "end) as 'day' "
//	+ "from tutoring_info, tutoring_apply_info,tutoring_time, tutoring_day "
//	+ "where tutor_id = ? and "
//	+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
//	+ "tutoring_info.tutoring_id = tutoring_time.tutoring_id and "
//	+ "tutoring_info.tutoring_id = tutoring_day.tutoring_id "
//	+ "GROUP BY tutoring_apply_info.tutoring_apply_id ";
	   public ArrayList<TutoringCalendarVO> getTutorTutoringCalendarList(String tutorId){ 
		      ArrayList<TutoringCalendarVO> tutorTutoringCalendarList = new ArrayList();
		      try{
		         String sql = "select tutoring_apply_info.tutoring_apply_id, tutoring_info.tutoring_id, tutoring_info.tutor_id, tutoring_apply_info.tutee_id, title, subtitle, start_date, end_date, career, tutoring_info.price, contents, count, upload_date, start_time, end_time, "
		               + "group_concat(DISTINCT day) as day,times, member_info.name  "
		               + "from tutoring_info, tutoring_apply_info,tutoring_time, tutoring_day,member_info "
		               + "where tutor_id = ? and "
		               + "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
		               + "tutoring_info.tutoring_id = tutoring_time.tutoring_id and "
		               + "tutoring_info.tutoring_id = tutoring_day.tutoring_id and "
		               + "member_info.member_id = tutoring_apply_info.tutee_id "
		               + "GROUP BY tutoring_apply_info.tutoring_apply_id ";
		         
		         PreparedStatement pstmt=conn.prepareStatement(sql);
		         pstmt.setString(1, tutorId);


		         ResultSet rs = pstmt.executeQuery();
		         // 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
		         while(rs.next())
		            // 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
		            tutorTutoringCalendarList.add(new TutoringCalendarVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("tutee_id"),rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"),rs.getString("start_time").substring(0, 5), rs.getString("end_time").substring(0, 5), rs.getString("career"), rs.getString("price"), rs.getString("contents"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("day"), rs.getString("times"), rs.getString("times"), rs.getString("member_info.name")));
		         rs.close();
		         pstmt.close();
		      }catch(SQLException e){
		         e.printStackTrace();
		      } // catch 
		      return tutorTutoringCalendarList;
		   }
	

	/**튜터링 목록 페이지에서 9개씩 튜터링을 조회 
	 * limit 시작 레코드 번호, 검색할 레코드의 개수 
	 * mysql에서 레코드 번호는 0부터 시작하는데, jsp페이지에서 글은 1부터 시작 
	 * select */
	// 튜터링 번호순
	public ArrayList<TutoringVO2> getTutoring(int start, int end){ 
		ArrayList<TutoringVO2> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
					+ " from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "order by upload_date desc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	/**튜터링 목록 페이지에서 9개씩 튜터링을 조회 
	 * limit 시작 레코드 번호, 검색할 레코드의 개수 
	 * mysql에서 레코드 번호는 0부터 시작하는데, jsp페이지에서 글은 1부터 시작 
	 * select */
	// 조회수순 _1010수정
	public ArrayList<TutoringVO2> getHitTutoring(int start, int end){ 
		ArrayList<TutoringVO2> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
					+ " from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "order by count desc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	
	// 날짜순 _1010수정
		public ArrayList<TutoringVO2> getDateTutoring(int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{
				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
						+ " from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "order by upload_date desc limit ?,? ";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		

	/**이미지*/
	public ArrayList<IntImageVO> getTutoringsImage(int tutoringListNum){
		//		String images = null;
		ArrayList<IntImageVO> images = new ArrayList();
		try{
			String sql =  "select tutoring_info.tutoring_id, tutoring_image.image "
					+ "from tutoring_info, tutoring_image "
					+ "where tutoring_info.tutoring_id = tutoring_image.tutoring_id and "
					+ "tutoring_info.tutoring_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringListNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//		for(int i = 1; i< rs.length(); i++){
				images.add(new IntImageVO(rs.getInt("tutoring_info.tutoring_id"), rs.getString("image")));
				//		images = rs.getString("image");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return images;
	}
	
//	/**시간 뷰만들어서 해야댐!!
//	 * create view tutoringTimes1 
//	as select tutoring_time.tutoring_time_id,tutoring_info.tutoring_id as tutoring_id, concat(substr(tutoring_time.start_time, 1, 5) ," ~ ", substr(tutoring_time.end_time, 1, 5)) as "times"
//	from tutoring_time, tutoring_info
//	where tutoring_info.tutoring_id = tutoring_time.tutoring_id
//
	// 수정
//	create view tutoringApplys2
//	as select tutoring_info.tutoring_id, tutoring_apply_info.tutoring_apply_id, tutee_id, times, tutoring_time.tutoring_time_id
//	from tutoring_info,tutoring_time left join tutoring_apply_info on tutoring_time.tutoring_time_id = tutoring_apply_info.times
//	where tutoring_info.tutoring_id = tutoring_time.tutoring_id 
//	 */
	/**시간*/
	// [수정]
	public ArrayList<TutoringTimeVO2> getTutoringsTime(int tutoringId){
		//		String images = null;
		ArrayList<TutoringTimeVO2> times = new ArrayList();
		try{
			String sql =  "select tutoringTimes1.tutoring_id, tutoringTimes1.times "
					+ "from tutoringTimes1 left join tutoringApplys2 "
					+ "on tutoringTimes1.tutoring_time_id = tutoringApplys2.tutoring_time_id  "
					+ "where tutoringTimes1.tutoring_id =? and "
					+ "tutoringApplys2.times is null";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//		for(int i = 1; i< rs.length(); i++){
				times.add(new TutoringTimeVO2(rs.getInt("tutoring_id"), rs.getString("times")));
				//		images = rs.getString("image");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return times;
	}
	
	public int getTutoringsTimeCount(int tutoringId){
		int getTutoringsTimeCount = 0;
		try{
			String sql =  "select count(*) "
					+ "from tutoringTimes1 left join tutoringApplys2 "
					+ "on tutoringTimes1.tutoring_time_id = tutoringApplys2.tutoring_time_id  "
					+ "where tutoringTimes1.tutoring_id =? and "
					+ "tutoringApplys2.times is null";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,  tutoringId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				getTutoringsTimeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getTutoringsTimeCount;
	}
	public int getTutoringsTimeId(int tutoringId, String startTime, String endTime){
		int getTutoringsTimeCount = 0;
		try{
			String sql =  "	select tutoring_time_id "
					+ "from tutoring_time "
					+ "where tutoring_id = ? and tutoring_time.start_time = ? "
					+ "and tutoring_time.end_time = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,  tutoringId);
			pstmt.setString(2,  startTime);
			pstmt.setString(3,  endTime);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				getTutoringsTimeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getTutoringsTimeCount;
	}
	/**요일*/
	public ArrayList<TutoringDayVO> getTutoringsDay(int tutoringId){
		//		String images = null;
		ArrayList<TutoringDayVO> days = new ArrayList();
		try{
			String sql =  "select tutoring_info.tutoring_id, tutoring_day.day "
					+ "from tutoring_info, tutoring_day "
					+ "where tutoring_info.tutoring_id = tutoring_day.tutoring_id and "
					+ "tutoring_info.tutoring_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//		for(int i = 1; i< rs.length(); i++){
				days.add(new TutoringDayVO(rs.getInt("tutoring_info.tutoring_id"), rs.getString("day")));
				//		images = rs.getString("image");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return days;
	}
	
	/**튜터링 상세*/
	public TutoringVO3 tutoringDetail(int tutoringId){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringVO3 vo = null;
		try {
			String sql = "select tutoring_id, tutor_id, name, title, subtitle, start_date, end_date, career, price, contents, count "
					+ "from tutoring_info, member_info "
					+ "where tutoring_id = ? and "
					+ "member_info.member_id = tutoring_info.tutor_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,tutoringId);
			rs = pstmt.executeQuery();

			vo = new TutoringVO3();

			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setTutorName(rs.getString("name"));
				vo.setTutorId(rs.getString("tutor_id"));
				vo.setTitle(rs.getString("title"));
				vo.setSubtitle(rs.getString("subtitle"));
				vo.setStartDate(rs.getString("start_date"));
				vo.setEndDate(rs.getString("end_date"));
//				vo.setDay(rs.getString("day"));
//				vo.setStartTime(rs.getString("start_time"));
//				vo.setEndTime(rs.getString("end_time"));
				vo.setCareer(rs.getString("career"));
				vo.setPrice(rs.getString("price"));
				vo.setContents(rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setCount(rs.getInt("count"));
//				vo.setImg(rs.getString("img"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	/**튜터링 수정을 위한 상세*/
	public TutoringVO3 tutoringDetails(int tutoringId){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringVO3 vo = null;
		try {
			String sql = "select tutoring_id, tutor_id, name, title, subtitle, start_date, end_date, career, price, contents, count "
					+ "from tutoring_info, member_info "
					+ "where tutoring_id = ? and "
					+ "member_info.member_id = tutoring_info.tutor_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,tutoringId);
			rs = pstmt.executeQuery();

			vo = new TutoringVO3();

			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setTutorName(rs.getString("name"));
				vo.setTutorId(rs.getString("tutor_id"));
				vo.setTitle(rs.getString("title"));
				vo.setSubtitle(rs.getString("subtitle"));
				vo.setStartDate(rs.getString("start_date"));
				vo.setEndDate(rs.getString("end_date"));
//				vo.setDay(rs.getString("day"));
//				vo.setStartTime(rs.getString("start_time"));
//				vo.setEndTime(rs.getString("end_time"));
				vo.setCareer(rs.getString("career"));
				vo.setPrice(rs.getString("price"));
				vo.setContents(rs.getString("contents"));
				vo.setCount(rs.getInt("count"));
//				vo.setImg(rs.getString("img"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}

	/**글쓴이*/
	public TutoringVO2 tutoringWriter(int tutoringId){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringVO2 vo = null;
		try {
			String sql = "select tutoring_id, tutor_id, title, subtitle, start_date, end_date, career, price, count, contents "
					+ "from tutoring_info, member_info, tutor_info "
					+ "where tutoring_id = ? "
					+ "and tutoring_info.tutor_id = member_info.member_id "
					+ "and member_info.member_id = tutor_info.member_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,tutoringId);
			rs = pstmt.executeQuery();

			vo = new TutoringVO2();

			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setTutorId(rs.getString("tutor_id"));
				vo.setTitle(rs.getString("title"));
				vo.setSubtitle(rs.getString("subtitle"));
				vo.setStartDate(rs.getString("start_date"));
				vo.setEndDate(rs.getString("end_date"));
//				vo.setDay(rs.getString("day"));
//				vo.setStartTime(rs.getString("start_time"));
//				vo.setEndTime(rs.getString("end_time"));
				vo.setCareer(rs.getString("career"));
				vo.setPrice(rs.getString("price"));
				vo.setContents(rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setCount(rs.getInt("count"));
//				vo.setImg(rs.getString("img"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}

	/**튜터링 검색_전체*/
	public ArrayList<TutoringVO2> selectTutoringList(String title, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		try {
			String sql =  "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "order by upload_date desc limit ?,? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			pstmt.setString(2, "%" + title + "%");
			pstmt.setString(3, "%" + title + "%");
			pstmt.setInt(4, start-1);
			pstmt.setInt(5, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
			}   	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**튜터링 검색_튜터명*/
	public ArrayList<TutoringVO2> selectTutorNameList(String tutorName, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		try {
			String sql =  "select tutoring_id, tutoring_info.tutor_id, name, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and name like ? "
					+ "order by upload_date desc limit ?,? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + tutorName + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
			}   	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**튜터링 조회수 증가
	 * update*/

	public boolean tutoringCountIncrease(TutoringVO2 vo) {
		return tutoringCountIncrease(vo.getTutoringId());
	}

	public boolean tutoringCountIncrease(int tutoringId) {
		boolean result = false;
		try{
			String sql = "update tutoring_info "
					+ "set count = count+1 "
					+"where tutoring_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);


			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	
	/**튜터링 등록 
	 * insert*/


	public boolean registerTutoring(String tutorId, String title, String subtitle, String startDate,
			String endDate, String career, String price, String contents, String uploadDate) {
		boolean result = false;
		try{
			String sql ="INSERT INTO tutoring_info(tutor_id, title, subtitle, start_date, end_date, career, price, contents, upload_date) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tutorId);
			pstmt.setString(2, title);
			pstmt.setString(3, subtitle);
			pstmt.setString(4, startDate);
			pstmt.setString(5, endDate);
			pstmt.setString(6, career);
			pstmt.setString(7, price);
			pstmt.setString(8, contents);
			pstmt.setString(9, uploadDate);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**튜터링등록을 위한 게시글 조회*/
	public TutoringVO2 getTutoringRegisterInfo(String id){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringVO2 vo = null;

		try {
			String sql = "select tutoring_id, tutor_id, title, subtitle, start_date, end_date, career, price, count, contents "
					+ "from tutoring_info "
					+ "where tutor_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();

			vo = new TutoringVO2();
			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setTutorId(rs.getString("tutor_id"));
				vo.setTitle(rs.getString("title"));
				vo.setSubtitle(rs.getString("subtitle"));
				vo.setStartDate(rs.getString("start_date"));
				vo.setEndDate(rs.getString("end_date"));
//				vo.setStartTime(rs.getString("start_time"));
				vo.setCount(rs.getInt("count"));
				vo.setCareer(rs.getString("career"));
				vo.setPrice(rs.getString("price"));
				vo.setContents(rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				vo.setImg(rs.getString("img"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 요일
		public boolean registerTutoringDay(int tutoringId, String day) {
			boolean result = false;
			try{
				String sql2 = "INSERT INTO tutoring_day(tutoring_id, day) "
						+ "VALUES (?, ?)";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);

				pstmt2.setInt(1, tutoringId);
				pstmt2.setString(2, day);

				if(pstmt2.executeUpdate()==1)
					result = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		//요일삭제
		public boolean deleteTutoringDay(int tutoringId){
			boolean result = false;
			try{
				String sql ="delete from tutoring_day where tutoring_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, tutoringId);
				pstmt.executeUpdate(); 
				result = true;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
		
	
	// 1번
	public boolean registerTutoringImage(int tutoringId, String img1) {
		boolean result = false;
		try{
			String sql2 = "INSERT INTO tutoring_image(tutoring_id, image) "
					+ "VALUES (?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setInt(1, tutoringId);
			pstmt2.setString(2, img1);

			if(pstmt2.executeUpdate()==1)
				result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//이미지삭제
			public boolean deleteTutoringImage(int tutoringId){
				boolean result = false;
				try{
					String sql ="delete from tutoring_image where tutoring_id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, tutoringId);
					pstmt.executeUpdate(); 
					result = true;
				}catch(SQLException e){
					e.printStackTrace();
				}
				return result;
			}
	

	
	public boolean deleteTutoringTime(int tutoringId){
		boolean result = false;
		try{
			String sql ="delete from tutoring_time where tutoring_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			pstmt.executeUpdate(); 
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	

	
	
	//1번
	public boolean registerTutoringTime(int tutoringId, String startTime, String endTime) {
		boolean result = false;
		try{
			String sql2 = "INSERT INTO tutoring_time(tutoring_id, start_time, end_time) "
					+ "VALUES (?, ?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setInt(1, tutoringId);
			pstmt2.setString(2, startTime);
			pstmt2.setString(3, endTime);

			if(pstmt2.executeUpdate()==1)
				result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

	/**튜터링수정을 위한 게시글 조회*/
	public TutoringVO2 getTutoringInfo(int id){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringVO2 vo = null;

		try {
			String sql = "select tutoring_id, tutor_id, title, subtitle, start_date, end_date, career, price, contents,count "
					+ "from tutoring_info "
					+ "where tutoring_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();

			vo = new TutoringVO2();
			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setTutorId(rs.getString("tutor_id"));
				vo.setTitle(rs.getString("title"));
				vo.setSubtitle(rs.getString("subtitle"));
				vo.setStartDate(rs.getString("start_date"));
				vo.setEndDate(rs.getString("end_date"));
//				vo.setStartTime(rs.getString("start_time"));
				vo.setCount(rs.getInt("count"));
				vo.setCareer(rs.getString("career"));
				vo.setPrice(rs.getString("price"));
				vo.setContents(rs.getString("contents"));
//				vo.setImg(rs.getString("img"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	/**수정을 위해 요일 조회*/
	public TutoringDayVO getTutoringDayInfo(int id){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringDayVO vo = null;

		try {
			String sql = "select tutoring_id, day "
					+ "from tutoring_day "
					+ "where tutoring_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();

			vo = new TutoringDayVO();
			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setDay(rs.getString("day"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	/**수정을 위한 시간 조회*/
	/**튜터링수정을 위한 게시글 조회*/
	public TutoringTimeVO getTutoringTimeInfo(int id){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringTimeVO vo = null;

		try {
			String sql = "select tutoring_id, start_time, end_time "
					+ "from tutoring_time "
					+ "where tutoring_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();

			vo = new TutoringTimeVO();
			while(rs.next()){
				vo.setTutoringId(rs.getInt("tutoring_id"));
				vo.setStartTime(rs.getString("start_time"));
				vo.setEndTime(rs.getString("end_time"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**튜터링수정을 위한 게시글 조회*/
	// 직종
	public IntJobSelectVO getTutoringJobSelect(int id){
		PreparedStatement pstmt;
		ResultSet rs;
		IntJobSelectVO vo = null;

		try {
			String sql = "select tutoring_job_select.tutoring_job_id, tutoring_info.tutoring_id, middle_category.middle_categ_id, middle_name "
					+ "from tutoring_job_select, middle_category, tutoring_info "
					+ "where tutoring_info.tutoring_id = tutoring_job_select.tutoring_id and "
					+ "tutoring_job_select.middle_categ_id = middle_category.middle_categ_id and "
					+ "tutoring_info.tutoring_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();

			vo = new IntJobSelectVO();
			while(rs.next()){
				vo.setSelectId(rs.getInt("tutoring_job_id"));
				vo.setThisId(rs.getInt("tutoring_id"));
				vo.setMiddleCategId(rs.getInt("middle_categ_id"));
				vo.setMiddleCategName(rs.getString("middle_name"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**튜터링 수정 
	 * 수정이 되었으면 true 
	 * update*/
	public boolean updateTutoring(String title, String subtitle, String startDate,
			String endDate, String career, String price, String contents, String uploadDate, int tutoringId){
		boolean result = false;
		try{
			String sql = "update tutoring_info "
					+ "set title = ?, subtitle = ?, start_date = ?, end_date = ?, "
					+ "career = ?, "
					+ "price = ?, contents = ?, upload_date = ? "
					+"where tutoring_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, subtitle);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
//			pstmt.setString(6, startTime);
//			pstmt.setString(7, endTime);
			pstmt.setString(5, career);
			pstmt.setString(6, price);
			pstmt.setString(7, contents);
//			pstmt.setString(11, img);
			pstmt.setString(8, uploadDate);
			pstmt.setInt(9, tutoringId);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean updateTutoringImage(String img1, int tutoringId) {
		boolean result = false;
		try{
			String sql2 = "update tutoring_image "
					+ "set image = ? "
					+ "where tutoring_id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, img1);
			pstmt2.setInt(2, tutoringId);

			if(pstmt2.executeUpdate()==1)
				result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateTutoringTime(String startTime, String endTime, int tutoringId) {
		boolean result = false;
		try{
			String sql2 = "update tutoring_time "
					+ "set start_time = ?, end_time = ? "
					+ "where tutoring_id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			
			pstmt2.setString(1, startTime);
			pstmt2.setString(2, endTime);
			pstmt2.setInt(3, tutoringId);

			if(pstmt2.executeUpdate()==1)
				result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**튜터링 삭제 삭제가 되었으면 true, 
	 * 삭제 못했으면 false 
	 * Q) 이미 로그인한 상태에서 튜터링을 삭제한다면 id는 필요가 없다.
	 * 튜터링의 일련번호가 존재하고 그 번호는 유일하기 때문에 tutoring번호로 삭제가능하다고 생각(tutorId가 필요없다)
	 * delete
	 */
	public boolean deleteTutoring(int tutoringId){
		boolean result = false;
		try{
			String sql1= "DELETE FROM tutoring_info "
					+ "WHERE tutoring_id=?"; 
//			String sql1= "DELETE FROM tutoring_info "
//					+ "tutoringlike ON tutoring_info.tutoring_id = tutoringlike.tutoring_id AND "
//					+ "tutoring_image ON tutoring_info.tutoring_id = tutoring_image.tutoring_id AND "
//					+ "tutoring_time ON tutoring_info.tutoring_id = tutoring_time.tutoring_id AND "
//					+ "WHERE tutoring_id=?"; 
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, tutoringId);
			pstmt1.executeUpdate();
			
//			String sql1= "DELETE FROM tutoringlike WHERE tutoring_id=?"; 
//			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
//			pstmt1.setInt(1, tutoringId);
//			pstmt1.executeUpdate();
//			
//			String sql2= "DELETE FROM tutoring_info WHERE tutoring_id NOT IN (SELECT tutoring_id FROM tutoringlike)";
//			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
//			pstmt2.executeUpdate();
//			
//			String sql3= "DELETE FROM tutoring_image WHERE tutoring_id NOT IN (SELECT tutoring_id FROM tutoring_info)";
//			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
//			pstmt3.executeUpdate();
//			
//			String sql4= "DELETE FROM tutoring_time WHERE tutoring_id NOT IN (SELECT tutoring_id FROM tutoring_info)";
//			PreparedStatement pstmt4 = conn.prepareStatement(sql4);
//			pstmt4.executeUpdate();
			
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}


	/**특정 튜티가 튜터링을 찜할 수 있거나, 찜을 삭제하거나, 찜 목록을 본다.
	 *  tuteeId와 tutoringId가 동시에 기본키이다. */

	/**튜티의 찜하기 목록 조회(9개씩)
	 *  특정 튜티에 해당하는 찜한 튜터링의 목록을 조회한다.
	 *  select*/
	public ArrayList<TutoringLikeVO> getTutoringLike(String tuteeId, int start, int end){ 
		ArrayList<TutoringLikeVO> tutoirngLikeLlist = new ArrayList();
		try{
			String sql =  "select tutoringlike.tutoring_id, tutoringlike.tutee_id "
					+ "from tutoring_info, member_info, tutoringlike "
					+ "where tutee_id = ? and "
					+ "tutoring_info.tutoring_id = tutoringlike.tutoring_id and "
					+ "member_info.member_id = tutoringlike.tutee_id "
					+ "order by tutee_id asc limit ?,?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,  tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				// 컬럼명으로 입력해주기
				tutoirngLikeLlist.add(new TutoringLikeVO(rs.getString("tutoring_id"), rs.getString("tutee_id")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tutoirngLikeLlist;
	}


	/**찜하기 추가
	 * 튜티가 튜터링을 찜한다. 
	 * insert*/
	public boolean insertTutoringLike(String tuteeId, String tutoringId){ 
		boolean result = false;
		try{
			String sql="insert into tutoringlike(tutee_id,tutoring_id) values (?, ?)";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setString(2, tutoringId);
			if(pstmt.executeUpdate() ==1)
				result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;

	}

	/**특정 튜티가 찜한 튜터링 중 특정 튜터링을 삭제한다.
	 * delete*/
	public boolean deleteTutoringLike(String tuteeId, String tutoringId){ 
		boolean result = false;
		try{
			String sql="delete from tutoringlike where tutee_id = ? and tutoring_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setString(2, tutoringId);
			pstmt.executeUpdate(); 
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}


	/**튜터링 신청(결제)
	 * insert*/
	//	public boolean applyTutoring(TutoringBuyVO vo){
	//		return applyTutoring(vo.getTutoringApplyId(),vo.getTutoringId(),vo.getTuteeId(),vo.getApplyDate(),vo.getPaymentInfo());
	//	}

	public boolean applyTutoring(String tutoringApplyId, String tutoringId, String tuteeId, String applyDate, String paymentInfo){ 
		boolean result = false;
		try{
			String sql = "insert into tutoring_apply_info(tutoring_apply_id, tutoring_id, tutee_id, apply_date, payment_info) "
					+ "values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tutoringApplyId);
			pstmt.setString(2, tutoringId);
			pstmt.setString(3, tuteeId);
			pstmt.setString(4, applyDate);
			pstmt.setString(5, paymentInfo);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}


	/**등록된 전체 튜터링의 수
	 * select*/
	public int tutoringCount(){
		int tutoringAllCount = 0;
		String sql = "select count(*) from tutoring_info";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				tutoringAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutoringAllCount;
	}


	/**등록된 전체 검색 튜터링의 수
	 * select*/
	public int selectTutoringCount(String title){
		int selectTutoringAllCount = 0;
		String sql = "select count(*) from tutoring_info where (title like ? or subtitle like ? or contents like ?)";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			pstmt.setString(2, "%" + title + "%");
			pstmt.setString(3, "%" + title + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectTutoringAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectTutoringAllCount;
	}
	
	/**등록된 전체 검색 튜터링의 수_튜터
	 * select*/
	public int selectTutorCount(String name){
		int selectTutoringAllCount = 0;
		String sql = "select count(*) from tutoring_info, member_info where name like ? and tutoring_info.tutor_id = member_info.member_id";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectTutoringAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectTutoringAllCount;
	}


	/**특정 튜티가 찜한 튜터링의 수
	 * select*/
	public int tuteeLikeCount(String memberId){
		int likeCount = 0;
		try{
			String sql = "select count(*) "
					+ "from member_info, tutoringlike, tutoring_info "
					+ "where member_info.member_id = ? "
					+ "and member_info.member_id = tutoringlike.tutee_id "
					+ "and tutoringlike.tutoring_id = tutoring_info.tutoring_id";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,  memberId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				likeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likeCount;
	}


	/**특정 튜터링을 찜한 튜티의 수
	 * select*/
	public int tuteeapplyCount(String tutoringId){
		int tutoringApplyCount = 0;
		try{
			String sql = "select count(*) "
					+ "from tutoring_info, tutoring_apply_info "
					+ "where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and tutoring_info.tutoring_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,  tutoringId);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				tutoringApplyCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutoringApplyCount;
	}




	/** 튜터 측면 - 자신이 등록한 튜터링을 신청한 튜티가 있는지 확인 
	 * select*/
	public int tutoringApply(int tutoringId){
		int tutoringApply = 0;
		try{
			String sql = "select count(tutoring_info.tutoring_id) "
					+ "from tutoring_apply_info, tutoring_info "
					+ "where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id "
					+ "and tutoring_info.tutoring_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				tutoringApply = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tutoringApply;
	}
	
	/**직종 선택*/
	public boolean tutoringJobSelects(int tutoringId, int middleCategId){
		boolean result = false;
		try{
			String sql = "insert into tutoring_job_select(tutoring_id, middle_categ_id) values(?,?)";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			pstmt.setInt(2, middleCategId);
			if(pstmt.executeUpdate() ==1)
				result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**직종가져오기*/
	public ArrayList<IntJobSelectVO> getTutoringsJob(int tutoringId){ // JobSelectVO에 보면 memberId를 tutoringId로 생각한다.
		//		String images = null;
		ArrayList<IntJobSelectVO> job = new ArrayList();
		try{
			String sql =  "select tutoring_job_select.tutoring_job_id, tutoring_job_select.tutoring_id, middle_category.middle_categ_id, middle_category.middle_name "
					+ "from tutoring_info, tutoring_job_select, middle_category  "
					+ "where tutoring_info.tutoring_id = tutoring_job_select.tutoring_id and "
					+ "tutoring_job_select.middle_categ_id = middle_category.middle_categ_id and "
					+ "tutoring_info.tutoring_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				job.add(new IntJobSelectVO(rs.getInt("tutoring_job_id"), rs.getInt("tutoring_id"), rs.getInt("middle_categ_id"), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return job;
	}

	// 직종수정(1008수정)
		public boolean deleteTutoringJobSelect(int tutoringId){
			boolean result = false;
			try{
				String sql ="delete from tutoring_job_select where tutoring_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, tutoringId);
				pstmt.executeUpdate(); 
				result = true;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
		
		
		
		
		
		

		// 1012_상세검색 추가

		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring1(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount1(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=1 and price between ? and ? ";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring2(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount2(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=3 and price between ? and ? ";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring3(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount3(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring4(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount4(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring5(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount5(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring6(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount6(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 전체검색 20대 성별N N년이상 
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring7(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount7(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring8(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount8(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring9(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount9(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	여	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring10(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount10(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring11(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount11(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring12(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount12(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}



		// 전체검색 30대 성별N N년이상 
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring13(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		public int getTutoringCount13(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring14(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount14(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring15(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		public int getTutoringCount15(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	여	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring16(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount16(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring17(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount17(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring18(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount18(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=5 and price between ? and ? ";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 전체검색 40대이상 성별N N년이상 
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring19(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}


		public int getTutoringCount19(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring20(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount20(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring21(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount21(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	여	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring22(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount22(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring23(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount23(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	여	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring24(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount24(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 전체검색	전체나이	전체성별	N년이상	시작금액	끝금액
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring25(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >0) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount25(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >0) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring26(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >0) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount26(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >0) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring27(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >0) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount27(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >0) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 전체검색	20대나이	전체성별	N년이상	시작금액	끝금액
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring28(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount28(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring29(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount29(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring30(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount30(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 전체검색	30대나이	전체성별	N년이상	시작금액	끝금액
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring31(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount31(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring32(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount32(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring33(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount33(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 전체검색	40대이상성별	N년이상	시작금액	끝금액
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring34(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount34(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring35(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount35(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring36(String SearchName, String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (title like ? or subtitle like ? or contents like ?) "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				pstmt.setInt(6, start-1);
				pstmt.setInt(7, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount36(String SearchName, String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (title like ? or subtitle like ? or contents like ?) "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%" + SearchName + "%");
				pstmt.setString(2, "%" + SearchName + "%");
				pstmt.setString(3, "%" + SearchName + "%");
				pstmt.setString(4, price1);
				pstmt.setString(5, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring48(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount48(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and (gender = 'M' or gender = 'F') and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring49(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount49(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring50(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount50(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		public ArrayList<TutoringVO2> getTutoring51(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount51(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0 and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring52(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount52(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring53(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount53(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) > 0) and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring54(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount54(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring55(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount55(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring56(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount56(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		public ArrayList<TutoringVO2> getTutoring57(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount57(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring58(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and "
						+ "gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount58(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and "
					+ "gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring59(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount59(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29) and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring60(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount60(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring61(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount61(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring62(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount62(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		public ArrayList<TutoringVO2> getTutoring63(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount63(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring64(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and "
						+ "gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount64(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and "
					+ "gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring65(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);
				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount65(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39) and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring66(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount66(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring67(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount67(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring68(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount68(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'M' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		public ArrayList<TutoringVO2> getTutoring69(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount69(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	3년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring70(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and "
						+ "gender = 'F' and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount70(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and "
					+ "gender = 'F' and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 튜터링 상세검색
		// 전체검색	전체나이	남	5년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring71(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount71(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40) and gender = 'F' and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}



		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring72(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);
				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount72(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}



		public ArrayList<TutoringVO2> getTutoring73(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount73(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		public ArrayList<TutoringVO2> getTutoring74(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount74(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and ((date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >= 0) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring75(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29)) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount75(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29)) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		public ArrayList<TutoringVO2> getTutoring76(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29)) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount76(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29)) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		public ArrayList<TutoringVO2> getTutoring77(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29)) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount77(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 20 and 29)) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring78(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39)) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount78(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39)) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		public ArrayList<TutoringVO2> getTutoring79(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39)) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);
				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		public int getTutoringCount79(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39)) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		public ArrayList<TutoringVO2> getTutoring80(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39)) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount80(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) between 30 and 39)) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
		// 새로시작
		// 튜터링 상세검색
		// 전체검색	전체나이	남	1년이상	시작금액	끝금액
		public ArrayList<TutoringVO2> getTutoring81(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40)) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		public int getTutoringCount81(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40)) and (gender = 'M' or gender = 'F')  and career >=1 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}

		public ArrayList<TutoringVO2> getTutoring82(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40)) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);
				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount82(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40)) and (gender = 'M' or gender = 'F')  and career >=3 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}


		public ArrayList<TutoringVO2> getTutoring83(String price1, String price2, int start, int end){ 
			ArrayList<TutoringVO2> tutoringList = new ArrayList();
			try{

				String sql = "select tutoring_id, tutoring_info.tutor_id, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
						+ "from member_info, tutoring_info "
						+ "where member_info.member_id = tutoring_info.tutor_id "
						+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40)) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
						+ "order by tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);
				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO2(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}

		public int getTutoringCount83(String price1, String price2){
			int tutoringAllCount = 0;
			String sql = "select count(*) "
					+ "from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and (date_format(now(),'%Y')-substring(BIRTH,1,4)+1) >=40)) and (gender = 'M' or gender = 'F')  and career >=5 and price between ? and ? "
					+ "";
			try{
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, price1);
				pstmt.setString(2, price2);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next())
					tutoringAllCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tutoringAllCount;
		}
	}





