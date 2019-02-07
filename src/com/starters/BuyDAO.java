package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyDAO {
	private Connection conn;
	public BuyDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static BuyDAO instance = new BuyDAO();
	public static BuyDAO getInstance() {
		return instance;
	}
    // bank테이블에 있는 전체 레코드를 얻어내는 메소드
    public List<String> getAccount(){
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<String> accountList = new ArrayList<String>();
    	try {
    		pstmt = conn.prepareStatement("select * from bank");
    		rs = pstmt.executeQuery();

    		while(rs.next()) {
    			String account = new String(rs.getString("account")
    					+ " " + rs.getString("bank")
    					+ " " + rs.getString("name"));
    			accountList.add(account);
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return accountList;
    }
    
    // 튜터링 결제
	/**요일*/
	public ArrayList<TutoringDayVO> getTutoringsDay(int tutoringId){
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
				days.add(new TutoringDayVO(rs.getInt("tutoring_info.tutoring_id"), rs.getString("day")));
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
				vo.setCareer(rs.getString("career"));
				vo.setPrice(rs.getString("price"));
				vo.setContents(rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setCount(rs.getInt("count"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	/**시간*/
	public ArrayList<TutoringTimeVO2> getTutoringsTime(int tutoringId){
		ArrayList<TutoringTimeVO2> times = new ArrayList();
		try{
			String sql =  "select tutoringTimes.tutoring_id, tutoringTimes.times "
					+ "from tutoringTimes left join tutoringApplys "
					+ "on tutoringTimes.times = tutoringApplys.times "
					+ "where tutoringTimes.tutoring_id =? and "
					+ "tutoringApplys.times is null";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				times.add(new TutoringTimeVO2(rs.getInt("tutoring_id"), rs.getString("times")));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return times;
	}
	
	// 면접신청내역 삭제
	public boolean deleteInterviewInfo(int interviewId){ 
		boolean result = false;

		try{
			String sql = "delete from interview"
					+ " where interview_id = ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);

			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	// 면접신청내역 추가(payment_info)
	/**직종 선택*/
	public boolean paymentInsert(int interviewId, String payInfo, String payPrice){
		boolean result = false;
		try{
			String sql = "insert into payment_info(interview_id, pay_info, pay_price) values(?,?,?)";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			pstmt.setString(2, payInfo);
			pstmt.setString(3, payPrice);
			if(pstmt.executeUpdate() ==1)
				result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
