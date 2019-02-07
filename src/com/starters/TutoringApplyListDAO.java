package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TutoringApplyListDAO {
	private Connection conn;
	private int tutoringId;
	public TutoringApplyListDAO(){
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
	/**
	 * refundDeadline 기준
	 * 10월6일 신청기준(now()값)
		6,7일 환불불가
		8,9일 20%환불
		10,11일 40%환불
		12,13,14일 70%환불
		15일 이전 100%환불
	 * */
	public ArrayList<TutoringApplyListVO> getTutoringApply(String tuteeId, int start, int end){ 
		ArrayList<TutoringApplyListVO> tutoringApplyList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id, apply_date, payment_info, tutoring_apply_info.price,  "
					+ "start_date, end_date, times, "
					+ "case when now() < start_date THEN '진행전'  "
					+ "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'  "
					+ "when now() > end_date  THEN '완료'  "
					+ "END as 'progress', "
					+ "case  "
					+ "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능' "
					+ "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
					+ "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%' "
					+ "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%' "
					+ "when now() < date_sub(start_date, INTERVAL 7 day) then '100%' "
					+ "end as 'refundDeadline' "
					+ "from tutoring_info, tutoring_apply_info, member_info  "
					+ "where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and  "
					+ "tutoring_info.tutor_id = member_info.member_id and  "
					+ "tutoring_apply_info.tutee_id  = ? "
					+ "order by tutoring_apply_id desc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				tutoringApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
						,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
						,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringApplyList;
	}

	/**수강신청내역 개수*/
	public int applyListCount(String tuteeId){
		int applyListAllCount = 0;
		String sql = "select count(*)  "
				+ "from tutoring_apply_info "
				+ "where tutee_id = ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				applyListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applyListAllCount;
	}

	/** 수강신청 내역 취소 및 환불 
	 * 삭제가 되었으면 true, 
	 * 삭제 못했으면 false 
	 * delete
	 */
	public boolean deleteApplyList(int tutoringApplyId){
		boolean result = false;
		try{
			String sql="delete from tutoring_apply_info "
					+ "where tutoring_apply_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringApplyId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
		}
	
	/**튜터링 수강 등록 insert*/
//	public boolean registerTutoring(TutoringApplyListVO2 vo) {
//		return registerTutoring(vo.getTutoringId(),vo.getTuteeId(),vo.getPaymentInfo(),vo.getPrice());
//	}

	public boolean registerTutoring(int tutoringId, String tuteeId, String paymentInfo, int price, int times) {
		boolean result = false;
		try{
			String sql ="INSERT INTO tutoring_apply_info(tutoring_id,tutee_id,payment_info,price, times) "
					+ "VALUES (?,?,?,?,?)" ; 

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			pstmt.setString(2, tuteeId);
			pstmt.setString(3, paymentInfo);
			pstmt.setInt(4, price);
			pstmt.setInt(5, times);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	/**튜터링 날짜 검색 개수 select*/
	public int selectDateCount(String tuteeId, String selectstartDate, String selectEndDate){
		int selectApplyListAllCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_info, tutoring_apply_info, member_info "
				+ "where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id  "
				+ "and tutoring_info.tutor_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutee_id  = ? "
				+ "and apply_date between ? and  ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectApplyListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectApplyListAllCount;
	}

	/**튜터링 날짜 검색 리스트 select*/
	public ArrayList<TutoringApplyListVO> selectDateList(String tuteeId, String selectstartDate, String selectEndDate,int start, int end){ 
		ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id, apply_date, payment_info, tutoring_apply_info.price,   "
					+ "start_date, end_date, times, "
					+ "case when now() < start_date THEN '진행전' "
					+ "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중' "
					+ "when now() > end_date  THEN '완료'  "
					+ "END as 'progress', "
					+ "case "
					+ "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능'  "
					+ "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%'  "
					+ "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%'  "
					+ "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%'  "
					+ "when now() < date_sub(start_date, INTERVAL 7 day) then '100%'  "
					+ "end as 'refundDeadline'  "
					+ "from tutoring_info, tutoring_apply_info, member_info  "
					+ "where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and  "
					+ "tutoring_info.tutor_id = member_info.member_id and  "
					+ "tutoring_apply_info.tutee_id  = ? and "
					+ "apply_date between ? and  ? "
					+ "order by tutoring_apply_id desc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setString(2, selectstartDate);
			pstmt.setString(3, selectEndDate);
			pstmt.setInt(4, start-1);
			pstmt.setInt(5, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
						,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
						,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return searchApplyList;
	}
	
	/**등록된 1개월내역 검색 수강신청내역 select*/
	public int selectAmonthListCount(String tuteeId){
		int selectApplyListAllCount = 0;
		String sql = "select count(*)  "
				+ "from tutoring_info, tutoring_apply_info, member_info "
				+ "where apply_date > date_add(now(), interval -1 month) "
				+ "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id  "
				+ "and tutoring_info.tutor_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutee_id  = ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectApplyListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectApplyListAllCount;
	}

	/**수강신청내역 날짜 검색 1개월 전*/
	public ArrayList<TutoringApplyListVO> selectAmonthList(String tuteeId, int start, int end){ 
		ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id"
					+ ", apply_date, start_date, end_date,payment_info, tutoring_apply_info.price, times,  "
					+ "case when now() < start_date THEN '진행전'  "
					+ "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'  "
					+ "when now() > end_date  THEN '완료'  "
					+ "END as 'progress', "
					+ "case  "
					+ "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능' "
					+ "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
					+ "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%' "
					+ "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%' "
					+ "when now() < date_sub(start_date, INTERVAL 7 day) then '100%' "
					+ "end as 'refundDeadline' "
					+ "from tutoring_info, tutoring_apply_info, member_info  "
					+ "where apply_date > date_add(now(), interval -1 month) and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and  "
					+ "tutoring_info.tutor_id = member_info.member_id and  "
					+ "tutoring_apply_info.tutee_id  = ? "
					+ "order by tutoring_apply_id desc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
						,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
						,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return searchApplyList;
	}
	
	/**등록된 3개월내역 검색 수강신청내역 select*/
	public int selectThreeMonthListCount(String tuteeId){
		int selectApplyListAllCount = 0;
		String sql = "select count(*)  "
				+ "from tutoring_info, tutoring_apply_info, member_info "
				+ "where apply_date > date_add(now(), interval -3 month) "
				+ "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id  "
				+ "and tutoring_info.tutor_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutee_id  = ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectApplyListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectApplyListAllCount;
	}

	/**수강신청내역 날짜 검색 3개월 전*/
	public ArrayList<TutoringApplyListVO> selectThreeMonthList(String tuteeId, int start, int end){ 
		ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id"
					+ ", apply_date, start_date, end_date,payment_info, tutoring_apply_info.price, times,  "
					+ "case when now() < start_date THEN '진행전'  "
					+ "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'  "
					+ "when now() > end_date  THEN '완료'  "
					+ "END as 'progress', "
					+ "case  "
					+ "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능' "
					+ "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
					+ "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%' "
					+ "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%' "
					+ "when now() < date_sub(start_date, INTERVAL 7 day) then '100%' "
					+ "end as 'refundDeadline' "
					+ "from tutoring_info, tutoring_apply_info, member_info  "
					+ "where apply_date > date_add(now(), interval -3 month) and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and  "
					+ "tutoring_info.tutor_id = member_info.member_id and  "
					+ "tutoring_apply_info.tutee_id  = ? "
					+ "order by tutoring_apply_id desc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
						,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
						,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return searchApplyList;
	}
	
	/**등록된 6개월내역 검색 수강신청내역 select*/
	public int selectSixMonthListCount(String tuteeId){
		int selectApplyListAllCount = 0;
		String sql = "select count(*)  "
				+ "from tutoring_info, tutoring_apply_info, member_info "
				+ "where apply_date > date_add(now(), interval- 6 month) "
				+ "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id  "
				+ "and tutoring_info.tutor_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutee_id  = ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectApplyListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectApplyListAllCount;
	}

	/**수강신청내역 날짜 검색 6개월 전*/
	public ArrayList<TutoringApplyListVO> selectSixMonthList(String tuteeId, int start, int end){ 
		ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id"
					+ ", apply_date, start_date, end_date,payment_info, tutoring_apply_info.price, times,  "
					+ "case when now() < start_date THEN '진행전'  "
					+ "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'  "
					+ "when now() > end_date  THEN '완료'  "
					+ "END as 'progress', "
					+ "case  "
					+ "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능' "
					+ "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
					+ "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%' "
					+ "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%' "
					+ "when now() < date_sub(start_date, INTERVAL 7 day) then '100%' "
					+ "end as 'refundDeadline' "
					+ "from tutoring_info, tutoring_apply_info, member_info  "
					+ "where apply_date > date_add(now(), interval- 6 month) and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and  "
					+ "tutoring_info.tutor_id = member_info.member_id and  "
					+ "tutoring_apply_info.tutee_id  = ? "
					+ "order by tutoring_apply_id desc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
						,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
						,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return searchApplyList;
	}
	
	
	/**등록된 진행전 검색 수강신청내역 select*/
	   public int selectBeforeCount(String tuteeId){
	      int selectApplyListAllCount = 0;
	      String sql = "select count(*)  "
	            + "from tutoring_info, tutoring_apply_info, member_info  "
	            + "where now() < start_date "
	            + "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id   "
	            + "and tutoring_info.tutor_id = member_info.member_id   "
	            + "and tutoring_apply_info.tutee_id  = ? ";
	      try{
	         PreparedStatement pstmt =conn.prepareStatement(sql);
	         pstmt.setString(1, tuteeId);
	         ResultSet rs = pstmt.executeQuery();

	         if(rs.next())
	            selectApplyListAllCount = rs.getInt(1);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return selectApplyListAllCount;
	   }
	    
	   /**수강신청내역 날짜 검색 진행전*/
	   public ArrayList<TutoringApplyListVO> selectBefore(String tuteeId, int start, int end){ 
	      ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
	      try{
	         String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id "
	               + ", apply_date, start_date, end_date,payment_info, tutoring_apply_info.price, times,   "
	               + "case when now() < start_date THEN '진행전'   "
	               + "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'   "
	               + "when now() > end_date  THEN '완료' "
	               + "END as 'progress',  "
	               + "case   "
	               + "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능'  "
	               + "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
	               + "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%'  "
	               + "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%'  "
	               + "when now() < date_sub(start_date, INTERVAL 7 day) then '100%'  "
	               + "end as 'refundDeadline'  "
	               + "from tutoring_info, tutoring_apply_info, member_info   "
	               + "where now() < start_date and  "
	               + "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and   "
	               + "tutoring_info.tutor_id = member_info.member_id and   "
	               + "tutoring_apply_info.tutee_id  = ? "
	               + "order by tutoring_apply_id desc limit ?,? ";
	         PreparedStatement pstmt=conn.prepareStatement(sql);
	         pstmt.setString(1, tuteeId);
	         pstmt.setInt(2, start-1);
	         pstmt.setInt(3, end);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next())
	            searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
	                  ,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
	                  ,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
	         rs.close();
	         pstmt.close();
	      }catch(SQLException e){
	         e.printStackTrace();
	      } // catch 
	      return searchApplyList;
	   }
	   
	   /**등록된 진행중 검색 수강신청내역 select*/
	   public int selectIngCount(String tuteeId){
	      int selectApplyListAllCount = 0;
	      String sql = "select count(*)  "
	            + "from tutoring_info, tutoring_apply_info, member_info  "
	            + "where now() >= start_date "
	            + "and now() <= end_date "
	            + "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id   "
	            + "and tutoring_info.tutor_id = member_info.member_id   "
	            + "and tutoring_apply_info.tutee_id  = ? ";
	      try{
	         PreparedStatement pstmt =conn.prepareStatement(sql);
	         pstmt.setString(1, tuteeId);
	         ResultSet rs = pstmt.executeQuery();

	         if(rs.next())
	            selectApplyListAllCount = rs.getInt(1);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return selectApplyListAllCount;
	   }
	    
	   /**수강신청내역 날짜 검색 진행중*/
	   public ArrayList<TutoringApplyListVO> selectIng(String tuteeId, int start, int end){ 
	      ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
	      try{
	         String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id "
	               + ", apply_date, start_date, end_date,payment_info, tutoring_apply_info.price, times,   "
	               + "case when now() < start_date THEN '진행전'   "
	               + "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'   "
	               + "when now() > end_date  THEN '완료' "
	               + "END as 'progress',  "
	               + "case   "
	               + "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능'  "
	               + "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
	               + "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%'  "
	               + "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%'  "
	               + "when now() < date_sub(start_date, INTERVAL 7 day) then '100%'  "
	               + "end as 'refundDeadline'  "
	               + "from tutoring_info, tutoring_apply_info, member_info   "
	               + "where now() >= start_date and "
	               + "now() <= end_date and "
	               + "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and   "
	               + "tutoring_info.tutor_id = member_info.member_id and   "
	               + "tutoring_apply_info.tutee_id  = ? "
	               + "order by tutoring_apply_id desc limit ?,? ";
	         PreparedStatement pstmt=conn.prepareStatement(sql);
	         pstmt.setString(1, tuteeId);
	         pstmt.setInt(2, start-1);
	         pstmt.setInt(3, end);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next())
	            searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
	                  ,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
	                  ,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
	         rs.close();
	         pstmt.close();
	      }catch(SQLException e){
	         e.printStackTrace();
	      } // catch 
	      return searchApplyList;
	   }
	   
	   /**등록된 완료 검색 수강신청내역 select*/
	   public int selectAfterCount(String tuteeId){
	      int selectApplyListAllCount = 0;
	      String sql = "select count(*)  "
	            + "from tutoring_info, tutoring_apply_info, member_info  "
	            + "where now() > end_date "
	            + "and tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id   "
	            + "and tutoring_info.tutor_id = member_info.member_id   "
	            + "and tutoring_apply_info.tutee_id  = ? ";
	      try{
	         PreparedStatement pstmt =conn.prepareStatement(sql);
	         pstmt.setString(1, tuteeId);
	         ResultSet rs = pstmt.executeQuery();

	         if(rs.next())
	            selectApplyListAllCount = rs.getInt(1);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return selectApplyListAllCount;
	   }
	    
	   /**수강신청내역 날짜 검색 완료*/
	   public ArrayList<TutoringApplyListVO> selectAfter(String tuteeId, int start, int end){ 
	      ArrayList<TutoringApplyListVO> searchApplyList = new ArrayList();
	      try{
	         String sql = "select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id "
	               + ", apply_date, start_date, end_date,payment_info, tutoring_apply_info.price, times,   "
	               + "case when now() < start_date THEN '진행전'   "
	               + "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중'   "
	               + "when now() > end_date  THEN '완료' "
	               + "END as 'progress',  "
	               + "case   "
	               + "when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능'  "
	               + "when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%' "
	               + "when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%'  "
	               + "when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%'  "
	               + "when now() < date_sub(start_date, INTERVAL 7 day) then '100%'  "
	               + "end as 'refundDeadline'  "
	               + "from tutoring_info, tutoring_apply_info, member_info   "
	               + "where now() > end_date and "
	               + "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and   "
	               + "tutoring_info.tutor_id = member_info.member_id and   "
	               + "tutoring_apply_info.tutee_id  = ? "
	               + "order by tutoring_apply_id desc limit ?,? ";
	         PreparedStatement pstmt=conn.prepareStatement(sql);
	         pstmt.setString(1, tuteeId);
	         pstmt.setInt(2, start-1);
	         pstmt.setInt(3, end);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next())
	            searchApplyList.add(new TutoringApplyListVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("name")
	                  ,rs.getString("tutor_id"), rs.getString("title"),rs.getString("apply_date").substring(0,16),rs.getString("start_date"),rs.getString("end_date"),rs.getString("payment_info"),rs.getInt("price")
	                  ,rs.getString("progress"),rs.getString("refundDeadline"),rs.getString("times")));
	         rs.close();
	         pstmt.close();
	      }catch(SQLException e){
	         e.printStackTrace();
	      } // catch 
	      return searchApplyList;
	   }
	 
	/**sql 문 저장 두개를 합쳐서 리스트로 만듦
	 * 1. select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id, apply_date, payment_info, tutoring_apply_info.price, "
					+ "start_date, end_date, "
					+ "case when now() < start_date THEN '진행전' "
					+ "when apply_date < start_date and start_date < end_date and now() < end_date THEN '진행중' "
					+ "when now() > end_date  THEN '완료' "
					+ "END as 'progress' "
					+ "from tutoring_info, tutoring_apply_info, member_info "
					+ "where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
					+ "tutoring_info.tutor_id = member_info.member_id and "
					+ "tutoring_apply_info.tutee_id  = ?  "
					+ "order by tutoring_id asc limit ?,? "
	 *	2.select tutoring_info.tutoring_id, tutor_id, name, title, tutoring_apply_info.tutee_id, tutoring_apply_id, apply_date, payment_info, tutoring_apply_info.price, 
		start_date, end_date,
		case 
		when now() > date_sub(start_date, INTERVAL 1 day) then '환불 불가능'
		when date_sub(start_date, INTERVAL 3 day) < now() and now() < date_sub(start_date, INTERVAL 0 day) then '20%환불가능'
		when date_sub(start_date, INTERVAL 5 day) < now() and now() < date_sub(start_date, INTERVAL 2 day) then '40%환불가능'
		when date_sub(start_date, INTERVAL 8 day) < now() and now() < date_sub(start_date, INTERVAL 4 day) then '70%환불가능'
		when now() < date_sub(start_date, INTERVAL 7 day) then '100%환불가능'
		end as 'refundDeadline'
		from tutoring_apply_info, tutoring_info, member_info
		where tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and 
		tutoring_info.tutor_id = member_info.member_id and 
		tutoring_apply_info.tutee_id  = 'didwltjs324'
		order by tutoring_id asc limit 0,9		
	 * */

}
