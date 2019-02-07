package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TutoringDailyDAO {
	private Connection conn;
	public TutoringDailyDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);}
		catch(Exception e){
			e.printStackTrace();
		}//catch
	}// 생성자

	
	
	
	//1027
	
	
	public TutoringDailyVO tutoringDailyDetail(int tutoringRecordNum){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringDailyVO vo = null;

		try {
			String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date, tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title "
					+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info "
					+ "where tutoring_record_id = ? and "
					+ "tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
					+ "tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringRecordNum);
			rs = pstmt.executeQuery();

			vo = new TutoringDailyVO();
			while(rs.next()){
				vo.setTutoringRecordId(rs.getInt("tutoring_record_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setRecordDate(rs.getString("tutoring_register_date"));
				vo.setTutoringFeedback(rs.getString("tutoring_feedback"));
				vo.setTutoringRecord(rs.getString("tutoring_record"));
				vo.setTutorFile(rs.getString("tutor_file"));
				vo.setTuteeFile(rs.getString("tutee_file"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}


	/**튜터링일지리스트 상세*/
	public TutoringDailyVO tutoringDailyListDetail(String tutorId, int tutoringApplyNum){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringDailyVO vo = null;

		try {
			String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date, tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title "
					+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info "
					+ "where tutor_id = ? and "
					+ "tutoring_record.tutoring_apply_id = ? and "
					+ "tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
					+ "tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,tutorId);
			pstmt.setInt(2, tutoringApplyNum);
			rs = pstmt.executeQuery();

			vo = new TutoringDailyVO();
			while(rs.next()){
				vo.setTutoringRecordId(rs.getInt("tutoring_record_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setRecordDate(rs.getString("tutoring_register_date"));
				vo.setTutoringFeedback(rs.getString("tutoring_feedback"));
				vo.setTutoringRecord(rs.getString("tutoring_record"));
				vo.setTutorFile(rs.getString("tutor_file"));
				vo.setTuteeFile(rs.getString("tutee_file"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}



/**포트폴리오 수정 
	 * 수정이 되었으면 true 
	 * update*/


	public boolean updateTutoringDaily(String tutoringFeedback, String tutorFile, int tutoringRecordId){
		boolean result = false;
		try{
			String sql = "update tutoring_record "
					+ "set tutoring_feedback = ?, tutor_file = ? "
					+"where tutoring_record_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tutoringFeedback);
			pstmt.setString(2, tutorFile);
			pstmt.setInt(3, tutoringRecordId);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}



	public int tutorDailyCount(String tutorId, int DailyApplyNum){
	      int tutoringDailyCount = 0;
	      try{
	      String sql = "select count(*) from tutoring_record, tutoring_apply_info, tutoring_info, member_info "
	            + "where tutor_id = ? and "
	            + "tutoring_record.tutoring_apply_id = ? and "
	            + "tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
	            + "tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id and "
	            + "member_info.member_id = tutoring_apply_info.tutee_id ";
	      PreparedStatement pstmt =conn.prepareStatement(sql);
	      pstmt.setString(1,  tutorId);
	      pstmt.setInt(2,  DailyApplyNum);
	      ResultSet rs = pstmt.executeQuery();

	      if (rs.next())
	         tutoringDailyCount = rs.getInt(1);
	      rs.close();
	      pstmt.close();
	   } catch (SQLException e) {
	      e.printStackTrace();
	   }
	   return tutoringDailyCount;
	}

		public ArrayList<TutoringDailyVO>getTutoringDaily(String tutorId, int DailyApplyNum){
			ArrayList<TutoringDailyVO> tutoringdailyList = new ArrayList();
			try{
				String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date, tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title "
						+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info "
						+ "where tutor_id = ? and "
						+ "tutoring_record.tutoring_apply_id = ? and "
						+ "tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
						+ "tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id and "
						+ "member_info.member_id = tutoring_apply_info.tutee_id ";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,tutorId);
				pstmt.setInt(2, DailyApplyNum);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringdailyList.add(new TutoringDailyVO(rs.getInt("tutoring_record_id"), rs.getInt("tutoring_apply_id"), rs.getString("tutoring_register_date"), 
							rs.getString("tutoring_feedback"), rs.getString("tutoring_record"),
							rs.getString("tutor_file"), rs.getString("tutee_file"),rs.getString("name"), rs.getString("title")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringdailyList;
		}
		


}



