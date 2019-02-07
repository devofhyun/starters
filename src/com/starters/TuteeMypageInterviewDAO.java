package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TuteeMypageInterviewDAO {
	private Connection conn;
	public TuteeMypageInterviewDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);
		}catch(Exception e){
			e.printStackTrace();
		}//catch
	}// 생성자
	
	/**면접 리스트*/
	public ArrayList<TuteeMypageInterviewVO> geInterview(String tuteeId, int start, int end){ 
		ArrayList<TuteeMypageInterviewVO> interviewList = new ArrayList();
		try{
			String sql = "select  interview.interview_id,ask_date, interview_start_date, interview_end_date,company_info.name,interview_year_money,interview_result "
					+ ",company_info.company_id, email, phone_num, address, company_url "
					+ "from portfolio, interview, payment_info,company_info   "
					+ "where portfolio.member_id = ? "
					+ "and portfolio.portfolio_id = interview.portfolio_id  "
					+ "and interview.interview_id = payment_info.interview_id    "
					+ "and company_info.company_id = interview.company_id "
					+ "order by ask_date asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				interviewList.add(new TuteeMypageInterviewVO(rs.getInt("interview_id"),rs.getString("ask_date").substring(0,11),rs.getString("interview_start_date"),rs.getString("interview_end_date"),rs.getString("name")
						,rs.getString("interview_year_money"),rs.getInt("interview_result"),rs.getString("company_id"),rs.getString("email"),rs.getString("phone_num"),rs.getString("address"),rs.getString("company_url")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return interviewList;
	}
	
	/**면접리스트 전체 수*/
	public int interviewListCount(String tuteeId){
		int interviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from portfolio, interview, payment_info "
				+ "where portfolio.member_id = ? "
				+ "and portfolio.portfolio_id = interview.portfolio_id "
				+ "and interview.interview_id = payment_info.interview_id ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				interviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewListAllCount;
	}
	
	/**면접신청 1개월내역 검색 select*/
	public int selectAmonthListCount(String tuteeId){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*)  "
				+ "from portfolio, interview, payment_info  "
				+ "where portfolio.member_id = ? "
				+ "and ask_date > date_add(now(), interval -1 month) "
				+ "and portfolio.portfolio_id = interview.portfolio_id  "
				+ "and interview.interview_id = payment_info.interview_id ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}

	/**면접신청 날짜 검색 1개월 전*/
	public ArrayList<TuteeMypageInterviewVO> selectAmonthList(String tuteeId, int start, int end){ 
		ArrayList<TuteeMypageInterviewVO> interviewListAmonth = new ArrayList();
		try{
			String sql = "select  interview.interview_id,ask_date, interview_start_date, interview_end_date,company_info.name,interview_year_money,interview_result "
					+ ",company_info.company_id, email, phone_num, address, company_url "
					+ "from portfolio, interview, payment_info,company_info   "
					+ "where portfolio.member_id = ? "
					+ "and 	ask_date > date_add(now(), interval -1 month)  "
					+ "and portfolio.portfolio_id = interview.portfolio_id  "
					+ "and interview.interview_id = payment_info.interview_id    "
					+ "and company_info.company_id = interview.company_id "
					+ "order by ask_date asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				interviewListAmonth.add(new TuteeMypageInterviewVO(rs.getInt("interview_id"),rs.getString("ask_date").substring(0,11),rs.getString("interview_start_date"),rs.getString("interview_end_date"),rs.getString("name")
						,rs.getString("interview_year_money"),rs.getInt("interview_result"),rs.getString("company_id"),rs.getString("email"),rs.getString("phone_num"),rs.getString("address"),rs.getString("company_url")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return interviewListAmonth;
	}
	/**면접신청 3개월내역 검색 select*/
	public int selectThreeMonthListCount(String tuteeId){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*)  "
				+ "from portfolio, interview, payment_info  "
				+ "where portfolio.member_id = ? "
				+ "and ask_date > date_add(now(), interval -3 month) "
				+ "and portfolio.portfolio_id = interview.portfolio_id  "
				+ "and interview.interview_id = payment_info.interview_id ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}

	/**면접신청 날짜 검색 3개월 전*/
	public ArrayList<TuteeMypageInterviewVO> selectThreeMonthList(String tuteeId, int start, int end){ 
		ArrayList<TuteeMypageInterviewVO> interviewListThreeMonth = new ArrayList();
		try{
			String sql = "select  interview.interview_id,ask_date, interview_start_date, interview_end_date,company_info.name,interview_year_money,interview_result "
					+ ",company_info.company_id, email, phone_num, address, company_url "
					+ "from portfolio, interview, payment_info,company_info   "
					+ "where portfolio.member_id = ? "
					+ "and 	ask_date > date_add(now(), interval -3 month)  "
					+ "and portfolio.portfolio_id = interview.portfolio_id  "
					+ "and interview.interview_id = payment_info.interview_id    "
					+ "and company_info.company_id = interview.company_id "
					+ "order by ask_date asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				interviewListThreeMonth.add(new TuteeMypageInterviewVO(rs.getInt("interview_id"),rs.getString("ask_date").substring(0,11),rs.getString("interview_start_date"),rs.getString("interview_end_date"),rs.getString("name")
						,rs.getString("interview_year_money"),rs.getInt("interview_result"),rs.getString("company_id"),rs.getString("email"),rs.getString("phone_num"),rs.getString("address"),rs.getString("company_url")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return interviewListThreeMonth;
	}
	
	/**면접신청 6개월내역 검색 select*/
	public int selectSixMonthListCount(String tuteeId){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*)  "
				+ "from portfolio, interview, payment_info  "
				+ "where portfolio.member_id = ? "
				+ "and ask_date > date_add(now(), interval -6 month) "
				+ "and portfolio.portfolio_id = interview.portfolio_id  "
				+ "and interview.interview_id = payment_info.interview_id ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}

	/**면접신청 날짜 검색 6개월 전*/
	public ArrayList<TuteeMypageInterviewVO> selectSixMonthList(String tuteeId, int start, int end){ 
		ArrayList<TuteeMypageInterviewVO> interviewListSixMonth = new ArrayList();
		try{
			String sql = "select  interview.interview_id,ask_date, interview_start_date, interview_end_date,company_info.name,interview_year_money,interview_result "
					+ ",company_info.company_id, email, phone_num, address, company_url "
					+ "from portfolio, interview, payment_info,company_info   "
					+ "where portfolio.member_id = ? "
					+ "and 	ask_date > date_add(now(), interval -6 month)  "
					+ "and portfolio.portfolio_id = interview.portfolio_id  "
					+ "and interview.interview_id = payment_info.interview_id    "
					+ "and company_info.company_id = interview.company_id "
					+ "order by ask_date asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				interviewListSixMonth.add(new TuteeMypageInterviewVO(rs.getInt("interview_id"),rs.getString("ask_date").substring(0,11),rs.getString("interview_start_date"),rs.getString("interview_end_date"),rs.getString("name")
						,rs.getString("interview_year_money"),rs.getInt("interview_result"),rs.getString("company_id"),rs.getString("email"),rs.getString("phone_num"),rs.getString("address"),rs.getString("company_url")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return interviewListSixMonth;
	}
	
	/**면접신청 날짜 검색 select*/
	public int selectDateListCount(String tuteeId,String selectstartDate, String selectEndDate){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*)  "
				+ "from portfolio, interview, payment_info  "
				+ "where portfolio.member_id = ? "
				+ "and ask_date between ? and ? "
				+ "and portfolio.portfolio_id = interview.portfolio_id  "
				+ "and interview.interview_id = payment_info.interview_id ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}
	/**튜터링 날짜 검색 리스트 select*/
	public ArrayList<TuteeMypageInterviewVO> selectDateList(String tuteeId, String selectstartDate, String selectEndDate,int start, int end){ 
		ArrayList<TuteeMypageInterviewVO> selectdateList = new ArrayList();
		try{
			String sql = "select  interview.interview_id,ask_date, interview_start_date, interview_end_date,company_info.name,interview_year_money,interview_result "
					+ ",company_info.company_id, email, phone_num, address, company_url "
					+ "from portfolio, interview, payment_info,company_info   "
					+ "where portfolio.member_id = ? "
					+ "and ask_date between ? and ? "
					+ "and portfolio.portfolio_id = interview.portfolio_id "
					+ "and interview.interview_id = payment_info.interview_id   "
					+ "and company_info.company_id = interview.company_id  "
					+ "order by ask_date asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			pstmt.setInt(4, start-1);
			pstmt.setInt(5, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				selectdateList.add(new TuteeMypageInterviewVO(rs.getInt("interview_id"),rs.getString("ask_date").substring(0,11),rs.getString("interview_start_date"),rs.getString("interview_end_date"),rs.getString("name")
						,rs.getString("interview_year_money"),rs.getInt("interview_result"),rs.getString("company_id"),rs.getString("email"),rs.getString("phone_num"),rs.getString("address"),rs.getString("company_url")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return selectdateList;
	}
	
	/**인터뷰 신청내역 상세보기*/
	public TuteeMypageInterviewVO2 interviewInfo(int interviewId){ 
		TuteeMypageInterviewVO2 selectdateList = null;
		try{
			String sql = "select member_info.name,interview.interview_id, company_info.company_id, company_info.name "
					+ ", company_info.email, company_url,company_info.phone_num, company_range,company_birth,company_info.address,company_introduce "
					+ ", middle_name, day, start_time, end_time , interview_year_money,interview_start_date,interview_end_date "
					+ "from member_info, portfolio, interview, interview_job_selects, company_info, interview_time, interview_day, middle_category "
					+ "where interview.interview_id = ? "
					+ "and member_info.member_id = portfolio.member_id "
					+ "and portfolio.portfolio_id = interview.portfolio_id "
					+ "and interview.interview_id = interview_job_selects.interview_id "
					+ "and interview_job_selects.middle_categ_id = middle_category.middle_categ_id "
					+ "and interview.interview_id = interview_time.interview_id "
					+ "and interview.interview_id = interview_day.interview_id "
					+ "and interview.company_id = company_info.company_id";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			ResultSet rs = pstmt.executeQuery();
			selectdateList = new TuteeMypageInterviewVO2();
			while(rs.next()){
				selectdateList.setMemberName(rs.getString("member_info.name"));
				selectdateList.setInterviewId(rs.getInt("interview.interview_id"));
				selectdateList.setCompanyId(rs.getString("company_info.company_id"));
				selectdateList.setCompanyName(rs.getString("company_info.name"));
				selectdateList.setEmail(rs.getString("company_info.email"));
				selectdateList.setUrl(rs.getString("company_url"));
				selectdateList.setPhoneNum(rs.getString("company_info.phone_num"));
				selectdateList.setRange(rs.getString("company_range"));
				selectdateList.setBirth(rs.getString("company_birth"));
				selectdateList.setAddress(rs.getString("company_info.address"));
				selectdateList.setIntroduce(rs.getString("company_introduce"));
				selectdateList.setMiddleName(rs.getString("middle_name"));
				selectdateList.setDay(rs.getString("day"));
				selectdateList.setStartDate(rs.getString("interview_start_date"));
				selectdateList.setEndDate(rs.getString("interview_end_date"));
				selectdateList.setStartTime(rs.getString("start_time"));
				selectdateList.setEndTime(rs.getString("end_time"));
				selectdateList.setMoney(rs.getString("interview_year_money"));
		}
		rs.close();
		pstmt.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return selectdateList;
}
	
	/**시간가져오기*/
	public ArrayList<InterviewTimeVO> getInterviewTime(int interviewId){
		//		String images = null;
		ArrayList<InterviewTimeVO> times = new ArrayList();
		try{
			String sql =  "select interview_time.interview_id, start_time, end_time "
					+ "from interview_time left join interview "
					+ "on interview.interview_id = interview_time.interview_id "
					+ "where interview_time.interview_id = ? "
					+ "and interview_time.interview_id is not null";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				times.add(new InterviewTimeVO(rs.getInt("interview_id"), rs.getString("start_time").substring(0,5),rs.getString("end_time").substring(0,5)));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return times;
	}
	/**직종가져오기*/
	public ArrayList<TuteeMypageInterviewJobSelectVO> getInterviewJob(int interviewId){ // JobSelectVO에 보면 memberId를 tutoringId로 생각한다.
		ArrayList<TuteeMypageInterviewJobSelectVO> job = new ArrayList();
		try{
			String sql =  "select interview_job_selects.interview_select_id, interview_job_selects.interview_id, middle_category.middle_categ_id, middle_category.middle_name  "
					+ "from interview, interview_job_selects,middle_category "
					+ "where interview.interview_id = interview_job_selects.interview_id "
					+ "and interview_job_selects.middle_categ_id = middle_category.middle_categ_id  "
					+ "and interview.interview_id = ? ";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				job.add(new TuteeMypageInterviewJobSelectVO(rs.getInt("interview_select_id"), rs.getInt("interview_id"), rs.getInt("middle_categ_id"), rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return job;
	}
	/**요일*/
	public ArrayList<InterviewDayVO> getInterviewDay(int interviewId){
		//		String images = null;
		ArrayList<InterviewDayVO> days = new ArrayList();
		try{
			String sql =  "select interview.interview_id, interview_day.day "
					+ "from interview, interview_day "
					+ "where interview.interview_id = interview_day.interview_id "
					+ "and interview.interview_id = ? ";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				days.add(new InterviewDayVO(rs.getInt("interview.interview_id"), rs.getString("day")));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return days;
	}
	
	/**면접 수락 insert*/
	public boolean okInterview(int interviewId) {
		boolean result = false;
		//		ResultSet rs = null;
		try{
			String sql = "update interview "
					+ "set interview_result = interview_result+1 "
					+ "where interview_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);


			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**면접 거절 insert*/
	public boolean noInterview(int interviewId) {
		boolean result = false;
		//		ResultSet rs = null;
		try{
			String sql = "update interview "
					+ "set interview_result = interview_result+2 "
					+ "where interview_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, interviewId);


			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	

}
