package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class InterviewDAO {
	private Connection conn;
	public InterviewDAO(){
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

	
	
	/**면접신청내역 조회*/
	public ArrayList<InterviewVO2> getInterviews(String companyId, int start, int end){
		ArrayList<InterviewVO2> interviewLists = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, "
					+ "interview_start_date, interview_end_date, interview_year_money, "
					+ "interview.upload_date, portfolio.portfolio_title, member_info.name, "
					+ "interview_result, member_info.email, member_info.phone_num "
					+ "from interview, portfolio, member_info "
					+ "where interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				/*InterviewVO vo =new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"));
				interviewLists.add(vo);*/
				interviewLists.add(new InterviewVO2(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result"), rs.getString("member_info.email"), rs.getString("member_info.phone_num")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewLists;
	}

	/**등록된 전체 면접신청의 수
	 * select*/
	public int interviewCount(String companyId){
		int interviewAllCount = 0;
		String sql = "select count(*) "
				+ "from interview "
				+ "where company_id = ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				interviewAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewAllCount;
	}
	
	
	/**등록된 1개월 면접신청내역 갯수 select*/
	public int selectAmonthListCount(String companyId) {
		int selectInterviewListAllCount = 0;
			String sql = "select count(*) "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -1 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				selectInterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectInterviewListAllCount;
	}

	public ArrayList<InterviewVO2> selectAmonthLists(String companyId, int start, int end){
		ArrayList<InterviewVO2> interviewLists = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, "
					+ "interview_start_date, interview_end_date, interview_year_money, "
					+ "interview.upload_date, portfolio.portfolio_title, member_info.name, "
					+ "interview_result, member_info.email, member_info.phone_num "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -1 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				/*InterviewVO vo =new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"));
				interviewLists.add(vo);*/
				interviewLists.add(new InterviewVO2(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result"), rs.getString("member_info.email"), rs.getString("member_info.phone_num")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewLists;
	}
	/**면접신청내역 날짜 검색 1개월 조회*/
	public ArrayList<InterviewVO> selectAmonthList(String companyId, int start, int end){
		ArrayList<InterviewVO> searchInterviewList = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview.upload_date, portfolio.portfolio_title, member_info.name, interview_result "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -1 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				searchInterviewList.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchInterviewList;
	}
	
	
	/**등록된 3개월 면접신청내역 갯수 select*/
	public int selectThreeMonthListCount(String companyId) {
		int selectInterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from interview, portfolio, member_info "
				+ "where interview.upload_date >=date_add(now(), interval -3 month) and "
				+ "interview.company_id = ? and "
				+ "interview.portfolio_id = portfolio.portfolio_id and "
				+ "portfolio.member_id = member_info.member_id ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				selectInterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectInterviewListAllCount;
	}

	public ArrayList<InterviewVO2> selectThreeMonthLists(String companyId, int start, int end){
		ArrayList<InterviewVO2> interviewLists = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, "
					+ "interview_start_date, interview_end_date, interview_year_money, "
					+ "interview.upload_date, portfolio.portfolio_title, member_info.name, "
					+ "interview_result, member_info.email, member_info.phone_num "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -3 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				/*InterviewVO vo =new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"));
				interviewLists.add(vo);*/
				interviewLists.add(new InterviewVO2(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result"), rs.getString("member_info.email"), rs.getString("member_info.phone_num")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewLists;
	}
	/**면접신청내역 날짜 검색 3개월 조회*/
	public ArrayList<InterviewVO> selectThreeMonthList(String companyId, int start, int end){
		ArrayList<InterviewVO> searchInterviewList = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview.upload_date, portfolio.portfolio_title, member_info.name, interview_result "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -3 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				searchInterviewList.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchInterviewList;
	}
	
	
	/**등록된 6개월 면접신청내역 갯수 select*/
	public int selectSixMonthListCount(String companyId) {
		int selectInterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from interview, portfolio, member_info "
				+ "where interview.upload_date >=date_add(now(), interval -6 month) and "
				+ "interview.company_id = ? and "
				+ "interview.portfolio_id = portfolio.portfolio_id and "
				+ "portfolio.member_id = member_info.member_id ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				selectInterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectInterviewListAllCount;
	}

	public ArrayList<InterviewVO2> selectSixMonthLists(String companyId, int start, int end){
		ArrayList<InterviewVO2> interviewLists = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, "
					+ "interview_start_date, interview_end_date, interview_year_money, "
					+ "interview.upload_date, portfolio.portfolio_title, member_info.name, "
					+ "interview_result, member_info.email, member_info.phone_num "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -6 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				/*InterviewVO vo =new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"));
				interviewLists.add(vo);*/
				interviewLists.add(new InterviewVO2(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result"), rs.getString("member_info.email"), rs.getString("member_info.phone_num")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewLists;
	}
	/**면접신청내역 날짜 검색 6개월 조회*/
	public ArrayList<InterviewVO> selectSixMonthList(String companyId, int start, int end){
		ArrayList<InterviewVO> searchInterviewList = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview.upload_date, portfolio.portfolio_title, member_info.name, interview_result "
					+ "from interview, portfolio, member_info "
					+ "where interview.upload_date >=date_add(now(), interval -6 month) and "
					+ "interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				searchInterviewList.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchInterviewList;
	}
	


	/**면접신청내역 날짜 검색 개수 select*/
	public int selectDateCount(String companyId, String selectstartDate, String selectEndDate) {
		int selectInterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from interview, portfolio, member_info "
				+ "where interview.company_id = ? and "
				+ "interview.portfolio_id = portfolio.portfolio_id and "
				+ "portfolio.member_id = member_info.member_id and "
				+ "interview.upload_date between ? and ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectInterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectInterviewListAllCount;
	}
	
	public ArrayList<InterviewVO2> selectDateLists(String companyId, String selectstartDate, String selectEndDate,int start, int end) { 
ArrayList<InterviewVO2> searchInterviewList = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, "
					+ "interview.portfolio_id, interview_start_date, "
					+ "interview_end_date, interview_year_money, interview.upload_date, "
					+ "portfolio.portfolio_title, member_info.name, interview_result, "
					+ "member_info.email, member_info.phone_num "
					+ "from interview, portfolio, member_info "
					+ "where interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id and "
					+ "interview.upload_date between ? and ? "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			pstmt.setInt(4, start-1);
			pstmt.setInt(5, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				searchInterviewList.add(new InterviewVO2(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), 
						rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), 
						rs.getString("interview_result"), rs.getString("member_info.email"), rs.getString("member_info.phone_num")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchInterviewList;
	}
	/**면접신청내역 날짜 검색 리스트*/
	public ArrayList<InterviewVO> selectDateList(String companyId, String selectstartDate, String selectEndDate,int start, int end) { 
ArrayList<InterviewVO> searchInterviewList = new ArrayList();
		
		try{
			String sql = "select interview.interview_id, interview.company_id, interview.portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview.upload_date, portfolio.portfolio_title, member_info.name, interview_result "
					+ "from interview, portfolio, member_info "
					+ "where interview.company_id = ? and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.member_id = member_info.member_id and "
					+ "interview.upload_date between ? and ? "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			pstmt.setInt(4, start-1);
			pstmt.setInt(5, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				searchInterviewList.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name"), rs.getString("interview_result")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchInterviewList;
	}
	
	/**면접 결과 분류*/
	public int interviewResult(String companyId) {
		int interviewResult = 0;
		
		try{
			// 0은 미응답
			String sql = "select company_info.company_id, interview_result "
					+ "from interview, company_info "
					+ "where interview.company_id = company_info.company_id and "
					+ "company_info.company_id = ? and "
					+ "interview_result = 0";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			
			// 1은 수락
			String sql2 = "select company_info.company_id, interview_result "
					+ "from interview, company_info "
					+ "where interview.company_id = company_info.company_id and "
					+ "company_info.company_id = ? and "
					+ "interview_result = 1";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, companyId);
			ResultSet rs2 = pstmt2.executeQuery();
			
			// 2는 거절
			String sql3 = "select company_info.company_id, interview_result "
					+ "from interview, company_info "
					+ "where interview.company_id = company_info.company_id and "
					+ "company_info.company_id = ? and "
					+ "interview_result = 2";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, companyId);
			ResultSet rs3 = pstmt3.executeQuery();
			
			// 3은 완료
			String sql4 = "select company_info.company_id, interview_result "
					+ "from interview, company_info "
					+ "where interview.company_id = company_info.company_id and "
					+ "company_info.company_id = ? and "
					+ "interview_result = 3";
			PreparedStatement pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setString(1, companyId);
			ResultSet rs4 = pstmt4.executeQuery();
			
		if(rs.next())
			interviewResult = 0;
		if(rs2.next())
			interviewResult = 1;
		if(rs3.next())
			interviewResult = 2;
		if(rs4.next())
			interviewResult = 3;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return interviewResult;
	}
	
	
/*10/05추가*/
	/**면접 가능 시간*/
	public ArrayList<InterviewTimeVO> getInterviewsTime(int interviewId) {
		ArrayList<InterviewTimeVO> times = new ArrayList();
		try {
			String sql = "select interview.interview_id, interview_time.start_time, interview_time.end_time "
					+ "from interview, interview_time "
					+ "where interview.interview_id = interview_time.interview_id and "
					+ "interview.interview_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				times.add(new InterviewTimeVO(rs.getInt("interview.interview_id"), rs.getString("start_time"), rs.getString("end_time")));
/*				times.add(new InterviewTimeVO(rs.getInt("interview.interview_id"), rs.getString("start_time").substring(0, 5), rs.getString("end_time").substring(0, 5)));*/
			}
			rs.close();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return times;
	}
	
/*	*//**면접신청내역 1개월 전 조회*//*
	public ArrayList<InterviewVO> getOneMonthInterviews(String companyId, int start, int end){
		ArrayList<InterviewVO> interviewLists = new ArrayList();
		
		try{
			String sql = "select sum(pay_price) as '한달 전 면접신청 총 금액' "
					+ "from payment_info "
					+ "where interview.company_id = ? and "
					+ "ask_date >=date_add(now(), interval -1 month) "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				interviewLists.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("interview.company_id"), rs.getInt("interview.portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
						rs.getString("interview_year_money"),rs.getString("interview.upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name")));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewLists;
	}
	*/
	
	/**
	 * 면접신청 목록 페이지에서 9개씩 면접신청를 조회
	 * limit 시작 레코드 번호, 검색할 레코드의 개수
	 * mysql에서 레코드 번호는 0부터 시작, jsp페이지에서 글은 1부터 시작
	 * select */
/*	public ArrayList<InterviewVO> getInterview(int start, int end){
		ArrayList<InterviewVO> interviewList = new ArrayList();
		try{
			String sql = "select interview_id,interview.portfolio_id,interview.company_id,interview_date,interview_time,interview_year_money,upload_date  "
					+ "from interview,company_info,portfolio "
					+ "where interview.portfolio_id = portfolio.portfolio_id and "
					+ "interview.company_id = company_info.company_id "
					+ "order by interview_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				interviewList.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"),
						rs.getString("interview_year_money"),rs.getString("upload_date")));
				interviewList.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"), 
					rs.getString("interview_year_money"),rs.getString("upload_date"), rs.getString("portfolio.portfolio_title"), rs.getString("member_info.name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return interviewList;
	}
	*/
	
	
	   public int portfolioExist(int portfolioId){
		      int portfolio = 0;
		      
		      try{
		         String sql = "select count(member_info.member_id) "
		         		+ "from company_info, interview, portfolio, payment_info, member_info "
		         		+ "where member_info.member_id = portfolio.member_id and "
		         		+ "portfolio.portfolio_id = interview.portfolio_id and "
		         		+ "interview.company_id = company_info.company_id and "
		         		+ "interview.interview_id = payment_info.interview_id and "
		         		+ "portfolio.portfolio_id = ?";
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, portfolioId);
		         ResultSet rs = pstmt.executeQuery();
		         if(rs.next())
		            portfolio = rs.getInt(1);
		      }catch (Exception e) {
		         e.printStackTrace();
		      }
		      return portfolio;
		   }

	/**면접신청 상세*/
	public InterviewVO interviewDetail(int interviewId){
		PreparedStatement pstmt;
		ResultSet rs;
		InterviewVO vo = null;

		try {
			String sql = "select interview_id, interview.company_id, interview.portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview.upload_date "
					+ "from interview, company_info, portfolio "
					+ "where interview.company_id = company_info.company_id and "
					+ "interview.portfolio_id = portfolio.portfolio_id and "
					+ "interview_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,interviewId);
			rs = pstmt.executeQuery();

			vo = new InterviewVO();

			while(rs.next()){
				vo.setInterviewId(rs.getInt("interview_id"));
				vo.setCompanyId(rs.getString("company_id"));
				vo.setPortfolioId(rs.getInt("portfolio_id"));
				vo.setInterviewStartDate(rs.getString("interview_start_date"));
				vo.setInterviewEndDate(rs.getString("interview_end_date"));
				vo.setInterviewYearMoney(rs.getString("interview_year_money"));
				vo.setUploadDate(rs.getString("upload_date"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**면접신청 기업이름 */
	public InterviewVO interviewCompany(int interviewId){
		PreparedStatement pstmt;
		ResultSet rs;
		InterviewVO vo = null;
		try {
			String sql = "select interview_id, interview.company_id, interview.portfolio_id, interview_start_date, interview_end_date, interview_year_money, upload_date "
					+ "from interview, company_info "
					+ "where interview_id = ? and "
					+ "interview.company_id = company_info.company_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,interviewId);
			rs = pstmt.executeQuery();

			vo = new InterviewVO();

			while(rs.next()){
				vo.setInterviewId(rs.getInt("interview_id"));
				vo.setCompanyId(rs.getString("company_id"));
				vo.setPortfolioId(rs.getInt("portfolio_id"));
				vo.setInterviewStartDate(rs.getString("interview_start_date"));
				vo.setInterviewEndDate(rs.getString("interview_end_date"));
				vo.setInterviewYearMoney(rs.getString("interview_year_money"));
				vo.setUploadDate(rs.getString("upload_date"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;


	}


	/**면접신청*/
	// 기업명, 이메일, 전화번호, 주소, 홈페이지 주소, 규모, 기업 설립일, 기업소개, 튜티명, 제안업무, 면접가능요일, 면접가능시작시간, 면접가능종료시간, 제안연봉
	// interview_id, company_id, portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview_result, upload_date
	public boolean registerInterview(String companyId, int portfolioId, String interviewStartDate, String interviewEndDate, String interviewYearMoney, String uploadDate) {
		boolean result = false;
		try{
			String sql ="INSERT INTO interview(company_id, portfolio_id, interview_start_date, interview_end_date, interview_year_money, upload_date) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, portfolioId);
			pstmt.setString(3, interviewStartDate);
			pstmt.setString(4, interviewEndDate);
			pstmt.setString(5, interviewYearMoney);
			pstmt.setString(6, uploadDate);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
		}
	
	
	/*public boolean registerInterview(String companyId, int interviewId, int portfolioId, String interviewStartDate, String interviewEndDate, String interviewYearMoney, int interviewResult, String uploadDate) {
	boolean result = false;
	try{
		String sql ="INSERT INTO inverview(company_id, interview_id, portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview_result, upload_date) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, companyId);
		pstmt.setInt(2, interviewId);
		pstmt.setInt(3, portfolioId);
		pstmt.setString(4, interviewStartDate);
		pstmt.setString(5, interviewEndDate);
		pstmt.setString(6, interviewYearMoney);
		pstmt.setInt(7, interviewResult);
		pstmt.setString(8, uploadDate);

		if(pstmt.executeUpdate() == 1){
			result = true;
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	return result;
	}*/
	
	
	/**면접신청 시간*/
	public boolean registerInterviewTime(int interviewId, String startTime, String endTime) {
		boolean result = false;
		try{
			String sql2 = "INSERT INTO interview_time(interview_id, start_time, end_time) "
					+ "VALUES (?, ?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setInt(1, interviewId);
			pstmt2.setString(2, startTime);
			pstmt2.setString(3, endTime);

			if(pstmt2.executeUpdate()==1)
				result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public InterviewApplyVO getInterviewRegisterInfo(String id) {
		PreparedStatement pstmt;
		ResultSet rs;
		InterviewApplyVO vo = null;

		try {
			String sql = "select interview_id, company_id, portfolio_id, interview_start_date, interview_end_date, interview_year_money, interview_result "
					+ "from interview "
					+ "where company_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();

			vo = new InterviewApplyVO();
			while(rs.next()){
				vo.setInterviewId(rs.getInt("interview_id"));
				vo.setCompanyId(rs.getString("company_id"));
				vo.setPortfolioId(rs.getInt("portfolio_id"));
				vo.setInterviewStartDate(rs.getString("interview_start_date"));
				vo.setInterviewEndDate(rs.getString("interview_end_date"));
				vo.setInterviewYearMoney(rs.getString("interview_year_money"));
				vo.setInterviewResult(rs.getString("interview_result"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}



	public boolean registerInterviewDay(int interviewId, String day) {
		boolean result = false;
		try{
			String sql2 = "INSERT INTO interview_day(interview_id, day) "
					+ "VALUES (?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setInt(1, interviewId);
			pstmt2.setString(2, day);

			if(pstmt2.executeUpdate()==1)
				result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	public int interviewApplyListsCount(String companyId, int portfolioId){
		int wishListsCount = 0;
		String sql = "select count(*) "
				+ "from interview, portfolio, company_info "
				+ "where company_info.company_id = interview.company_id and "
				+ "interview.portfolio_id = portfolio.portfolio_id and "
				+ "company_info.company_id = ? and "
				+ "interview.portfolio_id = ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, portfolioId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				wishListsCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishListsCount;
	}


// 10/29 추가
	/**직종 선택*/
	public boolean JobSelects(int interviewId, int middleCategId){
		boolean result = false;
		try{
			String sql = "insert into interview_job_selects(interview_id, middle_categ_id) values(?,?)";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			pstmt.setInt(2, middleCategId);
			if(pstmt.executeUpdate() ==1)
				result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	// 11/06 추가
		public boolean insertPayMoney(int payMoney, String uploadDate) {
			boolean result = false;
			try{
				String sql ="INSERT INTO companyInerv_info(price, date) "
						+ "VALUES (?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, payMoney);
				pstmt.setString(2, uploadDate);

				if(pstmt.executeUpdate() == 1){
					result = true;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
			
		public boolean updatetPayMoney(int payMoney) {
			boolean result = false;
			try{
				String sql ="update companyInerv_info "
						+ "set price = ? ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, payMoney);
				if(pstmt.executeUpdate() == 1){
					result = true;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}


		/**면접결제부분에서 가격 불러오기*/
		public CompanyInervVO interviewPrice() {
			PreparedStatement pstmt;
			ResultSet rs;
			CompanyInervVO companypricevo = null;

			try {
				String sql = "select price "
						+ "from companyInerv_info";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				companypricevo = new CompanyInervVO();
				if(rs.next()){ 
					companypricevo.setPrice(rs.getInt("price"));
				}
				rs.close();
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return companypricevo;
		}

		

	
	}
	
	
	/*public boolean registerInterview(String companyName, String email, String phoneNum, String address, String companyUrl, String companyRange, String companyBirth, String companyIntroduce,
			String tuteeName, String work, String day, String interviewStartDate, String interviewEndDate, String interviewYearMoney) {
	boolean result = false;
	try{
		String sql ="INSERT INTO inverview(company_info.name, company_info.email, company_info.phone_num, company_info.address, company_info.company_url, company_info.company_range, company_info.company_birth, company_info.company_introduce, "
				+ "member_info.name, interview.work, interview.day, interview.interview_start_date, interview.interview_end_date, interview.interview_year_money) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, companyName);
		pstmt.setString(2, email);
		pstmt.setString(3, phoneNum);
		pstmt.setString(4, address);
		pstmt.setString(5, companyUrl);
		pstmt.setString(6, companyRange);
		pstmt.setString(7, companyBirth);
		pstmt.setString(8, companyIntroduce);
		pstmt.setString(9, tuteeName);
		pstmt.setString(10, work);
		pstmt.setString(11, day);
		pstmt.setString(12, interviewStartDate);
		pstmt.setString(13, interviewEndDate);
		pstmt.setString(14, interviewYearMoney);

		if(pstmt.executeUpdate() == 1){
			result = true;
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	return result;
	}*/




	/**면접신청 검색 */
/*	public ArrayList<InterviewVO> selectPortfolioList(String portfolioTitle, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<InterviewVO> list = new ArrayList<InterviewVO>();
		try {
			String sql = "select portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_image, portfolio_file, portfolio_text, portfolio_like, upload_date "
					+ "from portfolio, member_info "
					+ "where portfolio_title like ? and "
					+ "member_info.member_id = portfolio.member_id "
					+ "order by portfolio_id asc limit ?,? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + portfolioTitle + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new InterviewVO(rs.getInt("interview_id"), rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("interview_start_date"), rs.getString("interview_end_date"),
						rs.getString("interview_year_money"),rs.getString("upload_date")));
			}   	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}*/


	/**면접신청 등록 
	 * insert*/

//	public boolean registerPortfolio(InterviewVO vo) {
//		return registerPortfolio(vo.getMemberId(),vo.getPortfolioTitle(), vo.getPortfolioMethod(),vo.getPortfolioUrl(),vo.getPortfolioImage(),vo.getPortfolioFile(),vo.getPortfolioText(),vo.getUploadDate());
//	}
//
//	public boolean registerPortfolio(String memberId,String portfolioTitle,String portfolioMethod,String portfolioUrl,
//			String portfolioImage,String portfolioFile,String portfolioText,String uploadDate) {
//		boolean result = false;
//		try{
//			String sql ="INSERT INTO portfolio(member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_image, portfolio_file, portfolio_text, upload_date) "
//					+ "VALUES (?,?,?,?,?,?,?,?)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memberId);
//			pstmt.setString(2, portfolioTitle);
//			pstmt.setString(3, portfolioMethod);
//			pstmt.setString(4, portfolioUrl);
//			pstmt.setString(5, portfolioImage);
//			pstmt.setString(6, portfolioFile);
//			pstmt.setString(7, portfolioText);
//			pstmt.setString(8, uploadDate);
//
//			if(pstmt.executeUpdate() == 1){
//				result = true;
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return result;
//	}


	/**면접신청 수정 
	 * 수정이 되었으면 true 
	 * update*/
//	public boolean updatePortfolio(InterviewVO portfolio){
//		boolean result = false;
//		try{
//			String sql = "update portfolio "
//					+ "set portfolio_title = ?, portfolio_method = ?, portfolio_url = ?, "
//					+ "portfolio_image = ?, portfolio_file = ?, portfolio_text = ?, upload_date =? "
//					+"where portfolio_id = ? ";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, portfolio.getPortfolioTitle());
//			pstmt.setString(2, portfolio.getPortfolioMethod());
//			pstmt.setString(3, portfolio.getPortfolioUrl());
//			pstmt.setString(4, portfolio.getPortfolioImage());
//			pstmt.setString(5, portfolio.getPortfolioFile());
//			pstmt.setString(6, portfolio.getPortfolioText());
//			pstmt.setString(7, portfolio.getUploadDate());
//			pstmt.setInt(8, portfolio.getPortfolioId());
//
//			if(pstmt.executeUpdate() == 1){
//				result = true;
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return result;
//	}


	/**면접신청 삭제 삭제가 되었으면 true, 
	 * 삭제 못했으면 false 
	 * Q) 이미 로그인한 상태에서 면접신청를 삭제한다면 id는 필요가 없다.
	 * 면접신청 일련번호가 존재하고 그 번호는 유일하기 때문에 portfolio번호로 삭제가능하다고 생각(tutorId가 필요없다)
	 * delete
	 */
//	public boolean deletePortfolio(int portfolioId){
//		boolean result = false;
//		try{
//			String sql="delete from portfolio "
//					+ "where portfolio_id = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, portfolioId);
//			pstmt.executeUpdate();
//			result = true;
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return result;
//	}



	/**등록된 특정 검색 면접신청의 수
	 * select*/
//	public int selectPortfolioCount(String title){
//		int selectPortfolioAllCount = 0;
//		String sql = "select count(*) from portfolio where portfolio_title like ?";
//		try{
//			PreparedStatement pstmt =conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + title + "%");
//			ResultSet rs = pstmt.executeQuery();
//
//			if(rs.next())
//				selectPortfolioAllCount = rs.getInt(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return selectPortfolioAllCount;
//	}	


	
	
	
	
	


