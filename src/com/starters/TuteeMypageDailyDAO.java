package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TuteeMypageDailyDAO {
	private Connection conn;
	public TuteeMypageDailyDAO(){
		try{
			//1 driver loading
			Class.forName("com.mysql.jdbc.Driver");
			//2 DB연결
			String url="jdbc:mysql://128.134.114.237:3306/db216230105";
			String id="216230105";
			String pw="fprtmf211";
			conn = DriverManager.getConnection(url,  id, pw);
		}catch(Exception e){
			e.printStackTrace();
		}//catch
	}// 생성자

	/**일지리스트*/
	public ArrayList<TuteeMypageDailyVO> getTuteeTutoringDaily(String tuteeId, int tutoringApplyId){ 
		ArrayList<TuteeMypageDailyVO> getTutoringDaily = new ArrayList();
		try{
			String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date, tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title "
					+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info "
					+ "where tutee_id = ? and "
					+ "tutoring_record.tutoring_apply_id = ? and "
					+ "tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
					+ "tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id and "
					+ "member_info.member_id = tutoring_info.tutor_id";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringApplyId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				getTutoringDaily.add(new TuteeMypageDailyVO(rs.getInt("tutoring_record_id"),rs.getInt("tutoring_apply_id")
						,rs.getString("tutoring_register_date"),rs.getString("tutoring_feedback"),rs.getString("tutoring_record")
						,rs.getString("tutor_file"),rs.getString("tutee_file"),rs.getString("name"),rs.getString("title")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return getTutoringDaily;
	}
	public TuteeMypageDailyVO getTitleName(String tuteeId,int tutoringApplyId){ 
		PreparedStatement pstmt;
		ResultSet rs;
		TuteeMypageDailyVO vo = null;
		try{
			String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date, tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title "
					+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info "
					+ "where tutee_id = ? "
					+ "and tutoring_apply_info.tutoring_apply_id = ? "
					+ "and tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and member_info.member_id = tutoring_info.tutor_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringApplyId);
			rs = pstmt.executeQuery();
			vo = new TuteeMypageDailyVO();
			while(rs.next()){
				vo.setTutoringRecordId(rs.getInt("tutoring_record_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setTutoringRegisterDate(rs.getString("tutoring_register_date"));
				vo.setTutoringFeedback(rs.getString("tutoring_feedback"));
				vo.setTutoringRecord(rs.getString("tutoring_record"));
				vo.setTutorFile(rs.getString("tutor_file"));
				vo.setTuteeFile(rs.getString("tutee_file"));
				vo.setTutorName(rs.getString("name"));
				vo.setTutoringTitle(rs.getString("title"));
		}
		rs.close();
		pstmt.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return vo;
}
	
	/**일지 상세*/
	public ArrayList<TuteeMypageDailyVO> getDailyDetail(String tuteeId, int tutoringRecordId){ 
		ArrayList<TuteeMypageDailyVO> getTutoringDaily = new ArrayList();
		try{
			String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date"
					+ ", tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title  "
					+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info  "
					+ "where tutee_id = ? "
					+ "and tutoring_record_id = ? "
					+ "and  tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id  "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id  "
					+ "and member_info.member_id = tutoring_apply_info.tutee_id ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringRecordId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				getTutoringDaily.add(new TuteeMypageDailyVO(rs.getInt("tutoring_record_id"),rs.getInt("tutoring_apply_id")
						,rs.getString("tutoring_register_date"),rs.getString("tutoring_feedback"),rs.getString("tutoring_record")
						,rs.getString("tutor_file"),rs.getString("tutee_file"),rs.getString("name"),rs.getString("title")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return getTutoringDaily;
	}
	
	/**일지등록*/
	public boolean registerDaily(int tutoringApplyId,String tutoringRegisterDate,String tutoringRecord, String tuteeFile) {
		boolean result = false;
		try{
			String sql ="INSERT INTO tutoring_record(tutoring_apply_id,tutoring_register_Date"
					+ ", tutoring_record,tutee_file) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringApplyId);
			pstmt.setString(2, tutoringRegisterDate);
			pstmt.setString(3, tutoringRecord);
			pstmt.setString(4, tuteeFile);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	/**일지 수정*/
	public boolean updateDaily(String tutoringRecord,String tuteeFile,int tutoringRecordId){
		boolean result = false;
		try{
			String sql = "update tutoring_record  "
					+ "set tutoring_record = ? , tutee_file = ? "
					+ "where tutoring_record_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tutoringRecord);
			pstmt.setString(2, tuteeFile);
			pstmt.setInt(3, tutoringRecordId);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	public TuteeMypageDailyVO getPage(String tuteeId,int tutoringRecordId){ 
		PreparedStatement pstmt;
		ResultSet rs;
		TuteeMypageDailyVO vo = null;
		try{
			String sql = "select tutoring_record_id, tutoring_record.tutoring_apply_id, tutoring_register_date"
					+ ", tutoring_feedback, tutoring_record, tutor_file, tutee_file, name, title  "
					+ "from tutoring_record, tutoring_apply_info, tutoring_info, member_info  "
					+ "where tutee_id = ? "
					+ "and tutoring_record_id = ? "
					+ "and  tutoring_record.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id  "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id  "
					+ "and member_info.member_id = tutoring_apply_info.tutee_id ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringRecordId);
			rs = pstmt.executeQuery();
			vo = new TuteeMypageDailyVO();
			while(rs.next()){
				vo.setTutoringRecordId(rs.getInt("tutoring_record_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setTutoringRegisterDate(rs.getString("tutoring_register_date"));
				vo.setTutoringFeedback(rs.getString("tutoring_feedback"));
				vo.setTutoringRecord(rs.getString("tutoring_record"));
				vo.setTutorFile(rs.getString("tutor_file"));
				vo.setTuteeFile(rs.getString("tutee_file"));
				vo.setTutorName(rs.getString("name"));
				vo.setTutoringTitle(rs.getString("title"));
		}
		rs.close();
		pstmt.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return vo;
}
	
	/**튜티 튜터링 일지 리스트 개수*/
	public int dailyListCount(String tuteeId, int tutoringApplyId){
		int dailyListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_apply_info,tutoring_record "
				+ "where tutee_id = ? "
				+ "and tutoring_record.tutoring_apply_id = ? "
				+ "and tutoring_apply_info.tutoring_apply_id = tutoring_record.tutoring_apply_id";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringApplyId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				dailyListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dailyListCount;
	}
}