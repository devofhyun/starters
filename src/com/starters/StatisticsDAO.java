package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsDAO {
	private Connection conn;
	public StatisticsDAO(){
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
	
	// 매출시작
	// 연도별 매출현황
//	create view allYear
//	as  select YEAR(apply_date) AS 'dates'
//	from tutoring_apply_info
//	union
//	SELECT YEAR(ask_date) AS 'dates'
//	FROM payment_info
//	order by dates
//
//	create view tutoringYear
//	as SELECT  YEAR(apply_date)  AS BuyDateYYYY
//	,SUM(price) AS 'tutoringBuyAmout'       
//	,SUM(price*0.3) AS 'startersAmout'      
//	,SUM(price*0.7) AS 'tutorAmout' 
//	FROM tutoring_apply_info
//	GROUP BY YEAR(apply_date);
//
//	create view interviewYear
//	as  SELECT YEAR(ask_date) AS 'ask_date'
//	,SUM(pay_price) AS 'interviewBuyAmout' 
//	FROM payment_info
//	GROUP BY YEAR(ask_date);
	public int getBuyYearCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(dates) "
					+ "from allYear";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Map<String, Object>> getBuyYear(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select allYear.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
					+ "from allYear left join tutoringYear on tutoringYear.BuyDateYYYY = allYear.dates "
					+ "left join interviewYear on interviewYear.ask_date = allYear.dates "
					+ "group by allYear.dates "
					+ "order by allYear.dates asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("allYear.dates"));
				row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
				row.put("startersAmout", rs.getLong("startersAmout"));
				row.put("tutorAmout", rs.getLong("tutorAmout"));
				row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
				row.put("total", rs.getLong("total"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
	// 연도별 매출현황끝
	
	// 월별 매출현황
//	create view allMONTH
//	as  select MONTH(apply_date) AS 'dates'
//	from tutoring_apply_info
//	union
//	SELECT MONTH(ask_date) AS 'dates'
//	FROM payment_info
//	order by dates
//
//	create view tutoringMonth
//	as SELECT  MONTH(apply_date)  AS BuyDateMM
//	,SUM(price) AS 'tutoringBuyAmout'       
//	,SUM(price*0.3) AS 'startersAmout'      
//	,SUM(price*0.7) AS 'tutorAmout' 
//	FROM tutoring_apply_info
//	GROUP BY MONTH(apply_date);
//
//	create view interviewMonth
//	as  SELECT MONTH(ask_date) AS 'ask_date'
//	,SUM(pay_price) AS 'interviewBuyAmout' 
//	FROM payment_info
//	GROUP BY MONTH(ask_date);
	
	// 수정 뷰
//	create view allDay
//	as  select DATE(apply_date) AS 'dates'
//	from tutoring_apply_info
//	union
//	SELECT DATE(ask_date) AS 'dates'
//	FROM payment_info
//	order by dates
//
//	create view tutoringDay
//	as SELECT DATE(apply_date) AS 'apply_date'
//	,SUM(price) AS tutoringBuyAmout            
//	,SUM(price*0.3) AS startersAmout      
//	,SUM(price*0.7) AS tutorAmout
//	FROM tutoring_apply_info
//	GROUP BY apply_date;
//
//	create view interviewDay
//	as  SELECT DATE(ask_date) AS 'ask_date'
//	,SUM(pay_price) AS interviewBuyAmout 
//	FROM payment_info
//	GROUP BY ask_date;
	// **********전체**********
	public List<Map<String, Object>> getBuyMonth(String yyyy) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
//			String sql = "select allMONTH.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
//					+ "from allMONTH left join tutoringMonth on tutoringMonth.BuyDateMM = allMONTH.dates "
//					+ "left join interviewMonth on interviewMonth.ask_date = allMONTH.dates "
//					+ "group by allMONTH.dates ";
			String sql = "select Month(allDay.dates) as 'date', ifnull(sum(ifnull(tutoringBuyAmout,0)),0) as 'tutoringBuyAmout', ifnull(sum(ifnull(startersAmout,0)),0) as 'startersAmout', ifnull(sum(ifnull(tutorAmout,0)),0) as 'tutorAmout', ifnull(SUM(ifnull(interviewBuyAmout,0)),0) AS 'interviewBuyAmoutSum', SUM((ifnull(startersAmout,0)+ifnull(interviewBuyAmout,0))) as 'total' "
					+ "from allDay left join tutoringDay on tutoringDay.apply_date = allDay.dates "
					+ "left join interviewDay on interviewDay.ask_date = allDay.dates "
					+ "where DATE_FORMAT(allDay.dates,'%Y') = ? "
					+ "group by Month(allDay.dates), year(allDay.dates) ";
			pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("date"));
				row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
				row.put("startersAmout", rs.getLong("startersAmout"));
				row.put("tutorAmout", rs.getLong("tutorAmout"));
				row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
				row.put("total", rs.getLong("total"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
	// 월별 매출현황 끝
	
	// 일별별 매출현황
//	create view tutoringDay
//	as SELECT DATE(apply_date) AS 'apply_date'
//	      ,SUM(price) AS tutoringBuyAmout            
//	    ,SUM(price*0.3) AS startersAmout      
//	    ,SUM(price*0.7) AS tutorAmout
//	  FROM tutoring_apply_info
//	 GROUP BY apply_date;
//	 
//	create view interviewDay
//	as  SELECT DATE(ask_date) AS 'ask_date'
//	         ,SUM(pay_price) AS interviewBuyAmout 
//	  FROM payment_info
//	 GROUP BY ask_date;
//	 
//
//	as SELECT DATE(apply_date) AS 'apply_date'
//	      ,SUM(price) AS tutoringBuyAmout            
//	    ,SUM(price*0.3) AS startersAmout      
//	    ,SUM(price*0.7) AS tutorAmout
//	  FROM tutoring_apply_info
//	 GROUP BY apply_date;
//	union
//	SELECT DATE(ask_date) AS 'ask_date'
//	         ,SUM(pay_price) AS interviewBuyAmout 
//	  FROM payment_info
//	 GROUP BY ask_date;
//	 
//	 create view allDay
//	as  select DATE(apply_date) AS 'dates'
//	 from tutoring_apply_info
//	 union
//	SELECT DATE(ask_date) AS 'dates'
//	  FROM payment_info
//	  order by dates
	
	// **********날짜 검색**********
	public int getBuyDaySelectDateCount(String selectstartDate, String selectEndDate) throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(dates) "
					+ "from allDay "
					+ "where allDay.dates between ? and ? ";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
		public List<Map<String, Object>> getBuyDaySelectDate(String selectstartDate, String selectEndDate, int start, int end) throws Exception{
			PreparedStatement pstmt = null;
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			try{
				String sql = "select allDay.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
						+ "from allDay left join tutoringDay on tutoringDay.apply_date = allDay.dates "
						+ "left join interviewDay on interviewDay.ask_date = allDay.dates "
						+ "where allDay.dates between ? and ? "
						+ "group by allDay.dates "
						+ "order by allDay.dates asc limit ?,?";
				pstmt =conn.prepareStatement(sql);
				pstmt.setString(1, selectstartDate);
				pstmt.setString(2, selectEndDate);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					Map<String, Object> row = new HashMap<String, Object>();
					// ResultSet에 있는 값을 row에 채운다.
					row.put("date", rs.getString("allDay.dates"));
					row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
					row.put("startersAmout", rs.getLong("startersAmout"));
					row.put("tutorAmout", rs.getLong("tutorAmout"));
					row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
					row.put("total", rs.getLong("total"));
					result.add(row);
				}
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return result;
		}
	// **********1개월**********
	public int getBuyDayOneCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(dates) "
					+ "from allDay "
					+ "where allDay.dates > date_add(now(), interval -1 month) ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Map<String, Object>> getBuyDayOne(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select allDay.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
					+ "from allDay left join tutoringDay on tutoringDay.apply_date = allDay.dates "
					+ "left join interviewDay on interviewDay.ask_date = allDay.dates "
					+ "where allDay.dates > date_add(now(), interval -1 month) "
					+ "group by allDay.dates "
					+ "order by allDay.dates asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("allDay.dates"));
				row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
				row.put("startersAmout", rs.getLong("startersAmout"));
				row.put("tutorAmout", rs.getLong("tutorAmout"));
				row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
				row.put("total", rs.getLong("total"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
	// **********3개월**********
	public int getBuyDayThreeCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(dates) "
					+ "from allDay "
					+ "where allDay.dates > date_add(now(), interval -3 month) ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<Map<String, Object>> getBuyDayThree(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select allDay.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
					+ "from allDay left join tutoringDay on tutoringDay.apply_date = allDay.dates "
					+ "left join interviewDay on interviewDay.ask_date = allDay.dates "
					+ "where allDay.dates > date_add(now(), interval -3 month) "
					+ "group by allDay.dates "
					+ "order by allDay.dates asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("allDay.dates"));
				row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
				row.put("startersAmout", rs.getLong("startersAmout"));
				row.put("tutorAmout", rs.getLong("tutorAmout"));
				row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
				row.put("total", rs.getLong("total"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
	// **********6개월**********
	public int getBuyDaySixCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(dates) "
					+ "from allDay "
					+ "where allDay.dates > date_add(now(), interval - 6 month) ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<Map<String, Object>> getBuyDaySix(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select allDay.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
					+ "from allDay left join tutoringDay on tutoringDay.apply_date = allDay.dates "
					+ "left join interviewDay on interviewDay.ask_date = allDay.dates "
					+ "where allDay.dates > date_add(now(), interval - 6 month) "
					+ "group by allDay.dates "
					+ "order by allDay.dates asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("allDay.dates"));
				row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
				row.put("startersAmout", rs.getLong("startersAmout"));
				row.put("tutorAmout", rs.getLong("tutorAmout"));
				row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
				row.put("total", rs.getLong("total"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
	// **********전체**********
	public int getBuyDayCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(dates) "
					+ "from allDay ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Map<String, Object>> getBuyDay(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select allDay.dates, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum',  ifnull((ifnull(startersAmout,0)+ifnull(SUM(ifnull(interviewBuyAmout,0)),0)),0) as 'total' "
					+ "from allDay left join tutoringDay on tutoringDay.apply_date = allDay.dates "
					+ "left join interviewDay on interviewDay.ask_date = allDay.dates "
					+ "group by allDay.dates "
					+ "order by allDay.dates asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("allDay.dates"));
				row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
				row.put("startersAmout", rs.getLong("startersAmout"));
				row.put("tutorAmout", rs.getLong("tutorAmout"));
				row.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
				row.put("total", rs.getLong("total"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
			 
	// 분기별 매출현황
//	-- 연별 분기별 튜터링 결제금액, 수수료, 튜터 지급 금액
//	create view tutoringQuarter2018
//	as SELECT 
//	    YEAR(apply_date)  AS BuyDateYYYY
//	    ,QUARTER(apply_date) AS BuyDateQuarter
//	    ,SUM(price) AS tutoringBuyAmout            
//	    ,SUM(price*0.3) AS startersAmout      
//	    ,SUM(price*0.7) AS tutorAmout   
//	FROM 
//	    tutoring_apply_info
//	where YEAR(apply_date) = 2018
//	GROUP BY
//	    YEAR(apply_date)            
//	    ,QUARTER(apply_date)
//	-- 연별 분기별 면접 결제금액, 수수료
//	create view interviewQuarter2018
//	as SELECT 
//	    YEAR(ask_date)  AS BuyDateYYYY
//	    ,QUARTER(ask_date) AS BuyDateQuarter
//	    ,SUM(pay_price) AS interviewBuyAmout  
//	FROM 
//	    payment_info
//	where YEAR(ask_date) = 2018
//	GROUP BY
//	    YEAR(ask_date)            
//	    ,QUARTER(ask_date)
			 public List<Map<String, Object>> getBuyQuarter() throws Exception{
			    	PreparedStatement pstmt = null;
			    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			    	try{
			    		String sql = "select tutoringQuarter2018.BuyDateQuarter, ifnull(tutoringBuyAmout,0) as 'tutoringBuyAmout', ifnull(startersAmout,0) as 'startersAmout', ifnull(tutorAmout,0) as 'tutorAmout', ifnull(interviewBuyAmout,0) as 'interviewBuyAmount', (startersAmout+ifnull(interviewBuyAmout,0)) as 'total' "
			    				+ "from tutoringQuarter2018 left JOIN interviewQuarter2018 on tutoringQuarter2018.BuyDateQuarter = interviewQuarter2018.BuyDateQuarter ";
			    		Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
			    		 while(rs.next()){
			    			 Map<String, Object> row = new HashMap<String, Object>();
			    			 // ResultSet에 있는 값을 row에 채운다.
			    			 row.put("quarter", rs.getString("tutoringQuarter2018.BuyDateQuarter"));
			    			 row.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
			    			 row.put("startersAmout", rs.getLong("startersAmout"));
			    			 row.put("tutorAmout", rs.getLong("tutorAmout"));
			    			 row.put("interviewBuyAmount", rs.getLong("interviewBuyAmount"));
			    			 row.put("total", rs.getLong("total"));
			    			 result.add(row);
			    		 }
			    	}catch(SQLException e){
						e.printStackTrace();
					} // catch 
			    	 return result;
			    }
			 
			 // 총합
			 public Map<String, Object> getPartAllCount() throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
			    	PreparedStatement pstmt = null;
			    	Map<String, Object> result = new HashMap<String, Object>();
			    	try{
//			    		String sql = "select SUM(tutoringBuyAmout) AS 'tutoringBuyAmout', SUM(startersAmout) AS 'startersAmout', SUM(tutorAmout) AS 'tutorAmout', ifnull(SUM(interviewBuyAmout),0) AS 'interviewBuyAmoutSum', SUM((startersAmout+ifnull(interviewBuyAmout,0))) as 'total' "
//			    				+ "from tutoringQuarter2018 left JOIN interviewQuarter2018 on tutoringQuarter2018.BuyDateQuarter = interviewQuarter2018.BuyDateQuarter";
			    		String sql = "select SUM(ifnull(startersAmout,0)) AS 'startersAmout', ifnull(SUM(ifnull(interviewBuyAmout,0)),0) AS 'interviewBuyAmoutSum' "
			    				+ "from tutoringQuarter2018 left JOIN interviewQuarter2018 on tutoringQuarter2018.BuyDateQuarter = interviewQuarter2018.BuyDateQuarter";

			    		Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
			    		
			    		if(rs.next()){
			    			//result.put("tutoringBuyAmout", rs.getLong("tutoringBuyAmout"));
			    			result.put("startersAmout", rs.getLong("startersAmout"));
			    			//result.put("tutorAmout", rs.getLong("tutorAmout"));
			    			result.put("interviewBuyAmoutSum", rs.getLong("interviewBuyAmoutSum"));
			    			//result.put("total", rs.getLong("total"));
			    		}else{
//			    			result.put("tutoringBuyAmout", 0L); // L은 타입
			    			result.put("startersAmout", 0L);
//			    			result.put("tutorAmout", 0L);
			    			result.put("interviewBuyAmoutSum", 0L);
//			    			result.put("total", 0L);
			    		}
			    		
			    	}catch(SQLException e){
						e.printStackTrace();
					} // catch 
			    	 return result;
			    }
			 // 분기별 매출현황 끝
	// 인터뷰 매출 시작
	
	//인터뷰 매출 상세
			public BuyStatisticVO2 interviewBuyDetail(int interviewPayId){
				PreparedStatement pstmt;
				ResultSet rs;
				BuyStatisticVO2 vo = null;
				try {
					String sql = "select pay_id, company_info.company_id, company_info.name, tuteeMembers.member_id, tuteeMembers.name, portfolio.portfolio_id, portfolio.portfolio_title, pay_price, pay_info, ask_date "
							+ "from payment_info, interview, tuteeMembers, company_info, portfolio "
							+ "where company_info.company_id = interview.company_id "
							+ "and payment_info.interview_id = interview.interview_id "
							+ "and  interview.portfolio_id = portfolio.portfolio_id "
							+ "and portfolio.member_id = tuteeMembers.member_id and "
							+ "pay_id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,interviewPayId);
					rs = pstmt.executeQuery();

					vo = new BuyStatisticVO2();

					while(rs.next()){
						vo.setPayId(rs.getInt("pay_id"));
						vo.setCompanyId(rs.getString("company_info.company_id"));
						vo.setCompanyName(rs.getString("company_info.name"));
						vo.setTuteeId(rs.getString("tuteeMembers.member_id"));
						vo.setTuteeName(rs.getString("tuteeMembers.name"));
						vo.setPortfolioId(rs.getInt("portfolio.portfolio_id"));
						vo.setPortfolioTitle(rs.getString("portfolio.portfolio_title"));
						vo.setPayPrice(rs.getInt("pay_price"));
						vo.setPayInfo(rs.getString("pay_info"));
						vo.setAskDate(rs.getString("ask_date").substring(0, 16));
					}
					rs.close();
					pstmt.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return vo;

			}
		public int interviewBuyListCount(){
			int interviewBuyListCount = 0;
			String sql = "select count(*) "
					+ "from payment_info, interview "
					+ "where payment_info.interview_id = interview.interview_id ";
			try{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				if(rs.next())
					interviewBuyListCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return interviewBuyListCount;
		}
		
		public ArrayList<BuyStatisticVO2> interviewBuyList( int start, int end){ 
			ArrayList<BuyStatisticVO2> interviewBuyList = new ArrayList();
			try{
				String sql = "select pay_id, company_info.company_id, company_info.name, tuteeMembers.member_id, tuteeMembers.name, portfolio.portfolio_id, portfolio.portfolio_title, pay_price, pay_info, ask_date "
						+ "from payment_info, interview, tuteeMembers, company_info, portfolio "
						+ "where company_info.company_id = interview.company_id "
						+ "and payment_info.interview_id = interview.interview_id "
						+ "and  interview.portfolio_id = portfolio.portfolio_id "
						+ "and portfolio.member_id = tuteeMembers.member_id "
						+ "order by pay_id asc limit ?, ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					interviewBuyList.add(new BuyStatisticVO2(rs.getInt("pay_id"), rs.getString("company_info.company_id"), rs.getString("company_info.name"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("portfolio.portfolio_id"), rs.getString("portfolio.portfolio_title"), rs.getInt("pay_price"), rs.getString("pay_info"), rs.getString("ask_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return interviewBuyList;
		}
		// 인터뷰 매출 통계 1개월내역 검색 select
		public int allInterviewOneListCount(){
			int allInterviewOneListCount = 0;
			String sql = "select count(*) "
					+ "from payment_info, interview "
					+ "where payment_info.interview_id = interview.interview_id "
					+ "and ask_date > date_add(now(), interval -1 month)";
			try{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				if(rs.next())
					allInterviewOneListCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return allInterviewOneListCount;
		}
		
		public ArrayList<BuyStatisticVO2> interviewBuyOneList( int start, int end){ 
			ArrayList<BuyStatisticVO2> interviewBuyList = new ArrayList();
			try{
				String sql = "select pay_id, company_info.company_id, company_info.name, tuteeMembers.member_id, tuteeMembers.name, portfolio.portfolio_id, portfolio.portfolio_title, pay_price, pay_info, ask_date "
						+ "from payment_info, interview, tuteeMembers, company_info, portfolio "
						+ "where company_info.company_id = interview.company_id "
						+ "and payment_info.interview_id = interview.interview_id "
						+ "and  interview.portfolio_id = portfolio.portfolio_id "
						+ "and portfolio.member_id = tuteeMembers.member_id "
						+ "and ask_date > date_add(now(), interval -1 month)"
						+ "order by pay_id asc limit ?, ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					interviewBuyList.add(new BuyStatisticVO2(rs.getInt("pay_id"), rs.getString("company_info.company_id"), rs.getString("company_info.name"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("portfolio.portfolio_id"), rs.getString("portfolio.portfolio_title"), rs.getInt("pay_price"), rs.getString("pay_info"), rs.getString("ask_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return interviewBuyList;
		}
		
		// 인터뷰 매출 통계 3개월내역 검색 select
		public int allInterviewThreeListCount(){
			int allInterviewThreeListCount = 0;
			String sql = "select count(*) "
					+ "from payment_info, interview "
					+ "where payment_info.interview_id = interview.interview_id "
					+ "and ask_date > date_add(now(), interval -3 month)";
			try{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				if(rs.next())
					allInterviewThreeListCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return allInterviewThreeListCount;
		}
		
		public ArrayList<BuyStatisticVO2> interviewBuyThreeList( int start, int end){ 
			ArrayList<BuyStatisticVO2> interviewBuyList = new ArrayList();
			try{
				String sql = "select pay_id, company_info.company_id, company_info.name, tuteeMembers.member_id, tuteeMembers.name, portfolio.portfolio_id, portfolio.portfolio_title, pay_price, pay_info, ask_date "
						+ "from payment_info, interview, tuteeMembers, company_info, portfolio "
						+ "where company_info.company_id = interview.company_id "
						+ "and payment_info.interview_id = interview.interview_id "
						+ "and  interview.portfolio_id = portfolio.portfolio_id "
						+ "and portfolio.member_id = tuteeMembers.member_id "
						+ "and ask_date > date_add(now(), interval -3 month)"
						+ "order by pay_id asc limit ?, ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					interviewBuyList.add(new BuyStatisticVO2(rs.getInt("pay_id"), rs.getString("company_info.company_id"), rs.getString("company_info.name"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("portfolio.portfolio_id"), rs.getString("portfolio.portfolio_title"), rs.getInt("pay_price"), rs.getString("pay_info"), rs.getString("ask_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return interviewBuyList;
		}
		
		// 인터뷰 매출 통계 6개월내역 검색 select
		// 인터뷰 매출 통계 3개월내역 검색 select
		public int allInterviewSixListCount(){
			int allInterviewThreeListCount = 0;
			String sql = "select count(*) "
					+ "from payment_info, interview "
					+ "where payment_info.interview_id = interview.interview_id "
					+ "and ask_date > date_add(now(), interval -6 month)";
			try{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				if(rs.next())
					allInterviewThreeListCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return allInterviewThreeListCount;
		}
		
		public ArrayList<BuyStatisticVO2> interviewBuySixList( int start, int end){ 
			ArrayList<BuyStatisticVO2> interviewBuyList = new ArrayList();
			try{
				String sql = "select pay_id, company_info.company_id, company_info.name, tuteeMembers.member_id, tuteeMembers.name, portfolio.portfolio_id, portfolio.portfolio_title, pay_price, pay_info, ask_date "
						+ "from payment_info, interview, tuteeMembers, company_info, portfolio "
						+ "where company_info.company_id = interview.company_id "
						+ "and payment_info.interview_id = interview.interview_id "
						+ "and  interview.portfolio_id = portfolio.portfolio_id "
						+ "and portfolio.member_id = tuteeMembers.member_id "
						+ "and ask_date > date_add(now(), interval -6 month)"
						+ "order by pay_id asc limit ?, ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					interviewBuyList.add(new BuyStatisticVO2(rs.getInt("pay_id"), rs.getString("company_info.company_id"), rs.getString("company_info.name"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("portfolio.portfolio_id"), rs.getString("portfolio.portfolio_title"), rs.getInt("pay_price"), rs.getString("pay_info"), rs.getString("ask_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return interviewBuyList;
		}
		// 인터뷰 날짜로 검색
		public int interviewBuyDateCount(String selectstartDate, String selectEndDate){
			int selectDateListCount = 0;
			String sql = "select count(*) "
					+ "from payment_info, interview "
					+ "where payment_info.interview_id = interview.interview_id "
					+ "and ask_date between ? and ? ";
			try{
				PreparedStatement pstmt =conn.prepareStatement(sql);
				pstmt.setString(1, selectstartDate);
				pstmt.setString(2, selectEndDate);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					selectDateListCount = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return selectDateListCount;
		}
		public ArrayList<BuyStatisticVO2> selectInterviewBuyDateList(String selectstartDate, String selectEndDate,int start, int end){ 
			ArrayList<BuyStatisticVO2> selectInterviewBuyDateList = new ArrayList();
			try{
				String sql = "select pay_id, company_info.company_id, company_info.name, tuteeMembers.member_id, tuteeMembers.name, portfolio.portfolio_id, portfolio.portfolio_title, pay_price, pay_info, ask_date "
						+ "from payment_info, interview, tuteeMembers, company_info, portfolio "
						+ "where company_info.company_id = interview.company_id "
						+ "and payment_info.interview_id = interview.interview_id "
						+ "and  interview.portfolio_id = portfolio.portfolio_id "
						+ "and portfolio.member_id = tuteeMembers.member_id "
						+ "and ask_date between ? and ? "
						+ "order by pay_id asc limit ?, ?";
			
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, selectstartDate);
				pstmt.setString(2, selectEndDate);
				pstmt.setInt(3, start-1);
				pstmt.setInt(4, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					selectInterviewBuyDateList.add(new BuyStatisticVO2(rs.getInt("pay_id"), rs.getString("company_info.company_id"), rs.getString("company_info.name"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("portfolio.portfolio_id"), rs.getString("portfolio.portfolio_title"), rs.getInt("pay_price"), rs.getString("pay_info"), rs.getString("ask_date")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return selectInterviewBuyDateList;
		}
		// 인터뷰 매출 끝
	// 튜터링 매출 시작
	//튜터링 매출 상세
		public BuyStatisticVO tutoringBuyDetail(int tutoringApplyId){
			PreparedStatement pstmt;
			ResultSet rs;
			BuyStatisticVO vo = null;
			try {
				String sql = "select tutoring_apply_id, tuteeMembers.member_id, tuteeMembers.name, tutoring_info.tutoring_id, tutoring_info.title, tutoring_info.tutor_id, member_info.name, tutoring_apply_info.price, payment_info, tutoring_apply_info.apply_date "
						+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
						+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
						+ "and member_info.member_id = tutoring_info.tutor_id and "
						+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id and "
						+ "tutoring_apply_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,tutoringApplyId);
				rs = pstmt.executeQuery();

				vo = new BuyStatisticVO();

				while(rs.next()){
					vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
					vo.setTuteeId(rs.getString("tuteeMembers.member_id"));
					vo.setTuteeName(rs.getString("tuteeMembers.name"));
					vo.setTutoringId(rs.getInt("tutoring_info.tutoring_id"));
					vo.setTutoringTitle(rs.getString("tutoring_info.title"));
					vo.setTutorId(rs.getString("tutoring_info.tutor_id"));
					vo.setTutorName(rs.getString("member_info.name"));
					vo.setTutoringPrice(rs.getInt("tutoring_apply_info.price"));
					vo.setPaymentInfo(rs.getString("payment_info"));
					vo.setApplyDate(rs.getString("tutoring_apply_info.apply_date").substring(0, 16));
				}
				rs.close();
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vo;

		}
	public int tutoringBuyListCount(){
		int tutoringBuyListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
				+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and member_info.member_id = tutoring_info.tutor_id and "
				+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				tutoringBuyListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutoringBuyListCount;
	}
	
	public ArrayList<BuyStatisticVO> tutoringBuyList( int start, int end){ 
		ArrayList<BuyStatisticVO> tutoringBuyList = new ArrayList();
		try{
			String sql = "select tutoring_apply_id, tuteeMembers.member_id, tuteeMembers.name, tutoring_info.tutoring_id, tutoring_info.title, tutoring_info.tutor_id, member_info.name, tutoring_apply_info.price, payment_info, tutoring_apply_info.apply_date "
					+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
					+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and member_info.member_id = tutoring_info.tutor_id and "
					+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
					+ "order by tutoring_apply_id asc limit ?, ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringBuyList.add(new BuyStatisticVO(rs.getInt("tutoring_apply_id"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("tutoring_info.tutoring_id"), rs.getString("tutoring_info.title"), rs.getString("tutoring_info.tutor_id"), rs.getString("member_info.name"), rs.getInt("tutoring_apply_info.price"), rs.getString("payment_info"), rs.getString("tutoring_apply_info.apply_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringBuyList;
	}
	
	// 튜터링 매출 통계 1개월내역 검색 select
	public int allTutoringOneListCount(){
		int allTutoringOneListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
				+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and member_info.member_id = tutoring_info.tutor_id and "
				+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
				+ "and apply_date > date_add(now(), interval -1 month)";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				allTutoringOneListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTutoringOneListCount;
	}
	
	public ArrayList<BuyStatisticVO> allTutoringOneList( int start, int end){ 
		ArrayList<BuyStatisticVO> allTutoringOneList = new ArrayList();
		try{
			String sql = "select tutoring_apply_id, tuteeMembers.member_id, tuteeMembers.name, tutoring_info.tutoring_id, tutoring_info.title, tutoring_info.tutor_id, member_info.name, tutoring_apply_info.price, payment_info, tutoring_apply_info.apply_date "
					+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
					+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and member_info.member_id = tutoring_info.tutor_id and "
					+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
					+ "and apply_date > date_add(now(), interval -1 month)"
					+ "order by tutoring_apply_id asc limit ?, ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				allTutoringOneList.add(new BuyStatisticVO(rs.getInt("tutoring_apply_id"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("tutoring_info.tutoring_id"), rs.getString("tutoring_info.title"), rs.getString("tutoring_info.tutor_id"), rs.getString("member_info.name"), rs.getInt("tutoring_apply_info.price"), rs.getString("payment_info"), rs.getString("tutoring_apply_info.apply_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return allTutoringOneList;
	}
	
	// 튜터링 매출 통계 3개월내역 검색 select
	public int allTutoringThreeListCount(){
		int allTutoringOneListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
				+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and member_info.member_id = tutoring_info.tutor_id and "
				+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
				+ "and apply_date > date_add(now(), interval -3 month)";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				allTutoringOneListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTutoringOneListCount;
	}
	
	public ArrayList<BuyStatisticVO> allTutoringThreeList( int start, int end){ 
		ArrayList<BuyStatisticVO> allTutoringOneList = new ArrayList();
		try{
			String sql = "select tutoring_apply_id, tuteeMembers.member_id, tuteeMembers.name, tutoring_info.tutoring_id, tutoring_info.title, tutoring_info.tutor_id, member_info.name, tutoring_apply_info.price, payment_info, tutoring_apply_info.apply_date "
					+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
					+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and member_info.member_id = tutoring_info.tutor_id and "
					+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
					+ "and apply_date > date_add(now(), interval - 3 month)"
					+ "order by tutoring_apply_id asc limit ?, ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				allTutoringOneList.add(new BuyStatisticVO(rs.getInt("tutoring_apply_id"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("tutoring_info.tutoring_id"), rs.getString("tutoring_info.title"), rs.getString("tutoring_info.tutor_id"), rs.getString("member_info.name"), rs.getInt("tutoring_apply_info.price"), rs.getString("payment_info"), rs.getString("tutoring_apply_info.apply_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return allTutoringOneList;
	}
	
	// 튜터링 매출 통계 6개월내역 검색 select
	public int allTutoringSixListCount(){
		int allTutoringOneListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
				+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and member_info.member_id = tutoring_info.tutor_id and "
				+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
				+ "and apply_date > date_add(now(), interval - 6 month)";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				allTutoringOneListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTutoringOneListCount;
	}
	
	public ArrayList<BuyStatisticVO> allTutoringSixList( int start, int end){ 
		ArrayList<BuyStatisticVO> allTutoringOneList = new ArrayList();
		try{
			String sql = "select tutoring_apply_id, tuteeMembers.member_id, tuteeMembers.name, tutoring_info.tutoring_id, tutoring_info.title, tutoring_info.tutor_id, member_info.name, tutoring_apply_info.price, payment_info, tutoring_apply_info.apply_date "
					+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
					+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and member_info.member_id = tutoring_info.tutor_id and "
					+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
					+ "and apply_date > date_add(now(), interval - 6 month)"
					+ "order by tutoring_apply_id asc limit ?, ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				allTutoringOneList.add(new BuyStatisticVO(rs.getInt("tutoring_apply_id"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("tutoring_info.tutoring_id"), rs.getString("tutoring_info.title"), rs.getString("tutoring_info.tutor_id"), rs.getString("member_info.name"), rs.getInt("tutoring_apply_info.price"), rs.getString("payment_info"), rs.getString("tutoring_apply_info.apply_date")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return allTutoringOneList;
	}
	// 튜터링 날짜로 검색
			public int tutoringBuyDateCount(String selectstartDate, String selectEndDate){
				int selectDateListCount = 0;
				String sql = "select count(*) "
						+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
						+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
						+ "and member_info.member_id = tutoring_info.tutor_id and "
						+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
						+ "and apply_date between ? and ? ";
				try{
					PreparedStatement pstmt =conn.prepareStatement(sql);
					pstmt.setString(1, selectstartDate);
					pstmt.setString(2, selectEndDate);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next())
						selectDateListCount = rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return selectDateListCount;
			}
			public ArrayList<BuyStatisticVO> selectTutoringBuyDateList(String selectstartDate, String selectEndDate,int start, int end){ 
				ArrayList<BuyStatisticVO> allTutoringList = new ArrayList();
				try{
					String sql = "select tutoring_apply_id, tuteeMembers.member_id, tuteeMembers.name, tutoring_info.tutoring_id, tutoring_info.title, tutoring_info.tutor_id, member_info.name, tutoring_apply_info.price, payment_info, tutoring_apply_info.apply_date "
							+ "from tutoring_apply_info, tutoring_info, member_info, tuteeMembers "
							+ "where tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
							+ "and member_info.member_id = tutoring_info.tutor_id and "
							+ "tuteeMembers. member_id = tutoring_apply_info.tutee_id "
							+ "and apply_date between ? and ? "							
							+ "order by tutoring_apply_id asc limit ?, ?";
				
					PreparedStatement pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, selectstartDate);
					pstmt.setString(2, selectEndDate);
					pstmt.setInt(3, start-1);
					pstmt.setInt(4, end);

					ResultSet rs = pstmt.executeQuery();
					// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
					while(rs.next())
						// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
						allTutoringList.add(new BuyStatisticVO(rs.getInt("tutoring_apply_id"), rs.getString("tuteeMembers.member_id"), rs.getString("tuteeMembers.name"), rs.getInt("tutoring_info.tutoring_id"), rs.getString("tutoring_info.title"), rs.getString("tutoring_info.tutor_id"), rs.getString("member_info.name"), rs.getInt("tutoring_apply_info.price"), rs.getString("payment_info"), rs.getString("tutoring_apply_info.apply_date")));
					rs.close();
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				} // catch 
				return allTutoringList;
			}
			
	// 튜터링 매출 끝
	// 매출 끝
	// 튜터링 통계 시작
	// 튜터링 통계에서 필요한 뷰
//	create view tutoringInfo 
//	as select tutoring_id, tutoring_info.tutor_id, name,title, subtitle, start_date, end_date, career, price, contents, count, upload_date
//	from member_info, tutoring_info 
//	where member_info.member_id = tutoring_info.tutor_id
	// 튜터별 등록 튜터링 수
		 public List<Map<String, Object>> getTutorTutoring() throws Exception{
		    	PreparedStatement pstmt = null;
		    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		    	try{
		    		String sql = "select distinct tutoringTutor.name, count(tutoring_info.title) as tutoring_count "
		    				+ "from tutoring_info,  tutoringTutor "
		    				+ "where "
		    				+ "tutoring_info.tutoring_id = tutoringTutor.tutoring_id "
		    				+ "group by tutoringTutor.tutor_id "
		    				+ "order by tutoring_count desc "
		    				+ "limit 10";
		    		Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
		    		 while(rs.next()){
		    			 Map<String, Object> row = new HashMap<String, Object>();
		    			 // ResultSet에 있는 값을 row에 채운다.
		    			 row.put("name", rs.getString("name"));
		    			 row.put("tutoring_count", rs.getLong("tutoring_count"));
		    			 result.add(row);
		    		 }
		    	}catch(SQLException e){
					e.printStackTrace();
				} // catch 
		    	 return result;
		    }
		// 튜터별  튜터링 신청 수
				 public List<Map<String, Object>> getTutorTutoringBuy() throws Exception{
				    	PreparedStatement pstmt = null;
				    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				    	try{
				    		String sql = "select distinct tutoringTutor.name, count(tutoring_apply_info.tutoring_apply_id) as tutoring_apply_count "
				    				+ "from tutoring_apply_info, tutoringTutor "
				    				+ "where  "
				    				+ "tutoring_apply_info.tutoring_id = tutoringTutor.tutoring_id "
				    				+ "group by tutoringTutor.tutor_id "
				    				+ "order by tutoring_apply_count desc "
				    				+ "limit 10";
				    		Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(sql);
				    		 while(rs.next()){
				    			 Map<String, Object> row = new HashMap<String, Object>();
				    			 // ResultSet에 있는 값을 row에 채운다.
				    			 row.put("name", rs.getString("name"));
				    			 row.put("tutoring_apply_count", rs.getLong("tutoring_apply_count"));
				    			 result.add(row);
				    		 }
				    	}catch(SQLException e){
							e.printStackTrace();
						} // catch 
				    	 return result;
				    }
	// 분기별 튜터링 등록 현황
	 // 분기별
    public Map<String, Object> getPartRevenueTutoring(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select SUM(IF(DATE_FORMAT(upload_date, '%m') between '01' and '03', 1, 0)) as count_01,  "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') between '04' and '06', 1, 0)) as count_02,  "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') between '07' and '09', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') between '10' and '12', 1, 0)) as count_04 "
    				+ "from tutoring_info "
    				+ "where YEAR(upload_date) = ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    // 월별 튜터링 신청 현황
    public Map<String, Object> getPartRevenueTutoringApply(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select SUM(IF(DATE_FORMAT(apply_date, '%m') between '01' and '03', 1, 0)) as count_01,  "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') between '04' and '06', 1, 0)) as count_02,  "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') between '07' and '09', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') between '10' and '12', 1, 0)) as count_04 "
    				+ "from tutoring_apply_info "
    				+ "where YEAR(apply_date) = ?";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		ResultSet rs = pstmt.executeQuery();
    		
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
	// 월별 튜터링 신청 현황
//	select 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '01', 1, 0)) as count_01, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '02', 1, 0)) as count_02, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '03', 1, 0)) as count_03, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '04', 1, 0)) as count_04, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '05', 1, 0)) as count_05, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '06', 1, 0)) as count_06, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '07', 1, 0)) as count_07, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '08', 1, 0)) as count_08, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '09', 1, 0)) as count_09, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '10', 1, 0)) as count_10, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '11', 1, 0)) as count_11, 
//	SUM(IF(DATE_FORMAT(apply_date, '%m') = '12', 1, 0)) as count_12 
//	from tutoring_apply_info 
//	where DATE_FORMAT(apply_date,'%Y') = 2018
	// 월별 튜터링 등록현황
    public Map<String, Object> getAnnualRevenueTutoring(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '01', 1, 0)) as count_01, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '02', 1, 0)) as count_02, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '03', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '04', 1, 0)) as count_04, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '05', 1, 0)) as count_05, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '06', 1, 0)) as count_06, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '07', 1, 0)) as count_07, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '08', 1, 0)) as count_08, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '09', 1, 0)) as count_09, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '10', 1, 0)) as count_10, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '11', 1, 0)) as count_11, "
    				+ "SUM(IF(DATE_FORMAT(upload_date, '%m') = '12', 1, 0)) as count_12 "
    				+ "from tutoring_info "
    				+ "where DATE_FORMAT(upload_date,'%Y') = ? ";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    			result.put("count_05", rs.getLong("count_05"));
    			result.put("count_06", rs.getLong("count_06"));
    			result.put("count_07", rs.getLong("count_07"));
    			result.put("count_08", rs.getLong("count_08"));
    			result.put("count_09", rs.getLong("count_09"));
    			result.put("count_10", rs.getLong("count_10"));
    			result.put("count_11", rs.getLong("count_11"));
    			result.put("count_12", rs.getLong("count_12"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    			result.put("count_05", 0L);
    			result.put("count_06", 0L);
    			result.put("count_07", 0L);
    			result.put("count_08", 0L);
    			result.put("count_09", 0L);
    			result.put("count_10", 0L);
    			result.put("count_11", 0L);
    			result.put("count_12", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
 // 월별 튜터링 등록현황
    public Map<String, Object> getAnnualRevenueTutoringApply(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '01', 1, 0)) as count_01, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '02', 1, 0)) as count_02, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '03', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '04', 1, 0)) as count_04, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '05', 1, 0)) as count_05, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '06', 1, 0)) as count_06, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '07', 1, 0)) as count_07, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '08', 1, 0)) as count_08, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '09', 1, 0)) as count_09, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '10', 1, 0)) as count_10, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '11', 1, 0)) as count_11, "
    				+ "SUM(IF(DATE_FORMAT(apply_date, '%m') = '12', 1, 0)) as count_12 "
    				+ "from tutoring_apply_info "
    				+ "where DATE_FORMAT(apply_date,'%Y') = ? ";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    			result.put("count_05", rs.getLong("count_05"));
    			result.put("count_06", rs.getLong("count_06"));
    			result.put("count_07", rs.getLong("count_07"));
    			result.put("count_08", rs.getLong("count_08"));
    			result.put("count_09", rs.getLong("count_09"));
    			result.put("count_10", rs.getLong("count_10"));
    			result.put("count_11", rs.getLong("count_11"));
    			result.put("count_12", rs.getLong("count_12"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    			result.put("count_05", 0L);
    			result.put("count_06", 0L);
    			result.put("count_07", 0L);
    			result.put("count_08", 0L);
    			result.put("count_09", 0L);
    			result.put("count_10", 0L);
    			result.put("count_11", 0L);
    			result.put("count_12", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
	// 월간 튜터링 신청 통계
	// 튜터링 전체에서 나타낸거
//	select tutoringInfo.title as title, ifnull(count(tutoring_apply_id),0) as tutoring_apply_count 
//	from tutoringInfo left join tutoring_apply_info on (tutoring_apply_info.tutoring_id = tutoringInfo.tutoring_id 
//	and DATE_FORMAT(apply_date, '%Y-%m') = '2018-11')
//	group by tutoringInfo.tutoring_id 
//	order by tutoring_apply_count desc 
//	limit 10
    public List<Map<String, Object>> getRevenueTutoring(String yyyyMM) throws Exception{
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	try{
    		String sql = "select tutoringInfo.title as title, ifnull(count(tutoring_apply_id),0) as tutoring_apply_count "
    				+ "from tutoringInfo, tutoring_apply_info "
    				+ "where DATE_FORMAT(apply_date, '%Y-%m') = ? and "
    				+ "tutoring_apply_info.tutoring_id = tutoringInfo.tutoring_id "
    				+ "group by tutoringInfo.tutoring_id "
    				+ "order by tutoring_apply_count desc "
    				+ "limit 10 ";
    		 pstmt = conn.prepareStatement(sql);
    		 pstmt.setString(1, yyyyMM);
    		 rs = pstmt.executeQuery();
    		 while(rs.next()){
    			 Map<String, Object> row = new HashMap<String, Object>();
    			 // ResultSet에 있는 값을 row에 채운다.
    			 row.put("title", rs.getString("title"));
    			 row.put("tutoring_apply_count", rs.getLong("tutoring_apply_count"));
    			 result.add(row);
    		 }
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
	// 튜터링 통계 1개월내역 검색 select
	public int selectTutoringAmonthListCount(){
		int selectTutoringAmonthListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_info "
				+ "where upload_date > date_add(now(), interval -1 month) ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectTutoringAmonthListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectTutoringAmonthListCount;
	}
	
	public ArrayList<TutoringVO3> selectTutoringAmonthList( int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, name,title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
					+ " from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and 	upload_date > date_add(now(), interval -1 month)"
					+ "order by tutoring_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	
	/**후기게시판 통계 3개월내역 검색 select*/
	public int selectTutoringThreeListCount(){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_info "
				+ "where upload_date > date_add(now(), interval -3 month) ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}
	
	public ArrayList<TutoringVO3> selectTutoringThreeList( int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, name,title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
					+ " from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and 	upload_date > date_add(now(), interval -3 month)"
					+ "order by tutoring_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	
	/**후기게시판 통계 6개월내역 검색 select*/
	public int selectTutoringSixListCount(){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_info "
				+ "where upload_date > date_add(now(), interval -6 month) ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}
	
	public ArrayList<TutoringVO3> selectTutoringSixList( int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, name,title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
					+ " from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and 	upload_date > date_add(now(), interval -6 month)"
					+ "order by tutoring_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	
	
	public int selectTutoringDateListCount(String selectstartDate, String selectEndDate){
		int selectDateListCount = 0;
		String sql = "select count(*) "
				+ "from tutoring_info "
				+ "where upload_date between ? and ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				selectDateListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectDateListCount;
	}
	/**튜터링 날짜 검색 리스트 select*/
	public ArrayList<TutoringVO3> selectTutoringDateList(String selectstartDate, String selectEndDate,int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, name,title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
					+ " from member_info, tutoring_info "
					+ "where member_info.member_id = tutoring_info.tutor_id "
					+ "and upload_date between ? and ? "
					+ "order by tutoring_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			pstmt.setInt(3, start-1);
			pstmt.setInt(4, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	
	// 1개월, 3개월, 6개월, 전체보기, 날짜 끝
	
	// 튜터링 신청순 
		public ArrayList<TutoringVO3> getTutoringBuyAsc(int start, int end){ 
			ArrayList<TutoringVO3> tutoringList = new ArrayList();
			try{
				String sql = "select tutoringInfo.tutoring_id, tutoringInfo.tutor_id, name,title, subtitle, start_date, end_date, career, contents, count, upload_date, ifnull(count(tutoring_apply_info.tutoring_id),0) as tutoring_apply_count "
						+ "from tutoringInfo left join tutoring_apply_info on tutoringInfo.tutoring_id= tutoring_apply_info.tutoring_id "
						+ "left join review on review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id "
						+ "group by tutoringInfo.tutoring_id "
						+ "order by tutoring_apply_count asc, tutoringInfo.tutoring_id asc  limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		public ArrayList<TutoringVO3> getTutoringBuyDesc(int start, int end){ 
			ArrayList<TutoringVO3> tutoringList = new ArrayList();
			try{
				String sql = "select tutoringInfo.tutoring_id, tutoringInfo.tutor_id, name,title, subtitle, start_date, end_date, career, contents, count, upload_date, ifnull(count(tutoring_apply_info.tutoring_id),0) as tutoring_apply_count "
						+ "from tutoringInfo left join tutoring_apply_info on tutoringInfo.tutoring_id= tutoring_apply_info.tutoring_id "
						+ "left join review on review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id "
						+ "group by tutoringInfo.tutoring_id "
						+ "order by tutoring_apply_count desc, tutoringInfo.tutoring_id desc  limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
	// 후기 갯수순 
	public ArrayList<TutoringVO3> getTutoringReviewAsc(int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoringInfo.tutoring_id, tutoringInfo.tutor_id, name,title, subtitle, start_date, end_date, career, contents, count, upload_date, ifnull(sum(tutoring_apply_info.price),0) as price "
					+ "from tutoringInfo left join tutoring_apply_info on tutoringInfo.tutoring_id= tutoring_apply_info.tutoring_id "
					+ "left join review on review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id "
					+ "group by tutoringInfo.tutoring_id "
					+ "order by count(review.review_id) asc, tutoringInfo.tutoring_id asc  limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	public ArrayList<TutoringVO3> getTutoringReviewDesc(int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoringInfo.tutoring_id, tutoringInfo.tutor_id, name,title, subtitle, start_date, end_date, career, contents, count, upload_date, ifnull(sum(tutoring_apply_info.price),0) as price "
					+ "from tutoringInfo left join tutoring_apply_info on tutoringInfo.tutoring_id= tutoring_apply_info.tutoring_id "
					+ "left join review on review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id "
					+ "group by tutoringInfo.tutoring_id "
					+ "order by count(review.review_id) desc, tutoringInfo.tutoring_id desc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
		// 튜터링 수입순
		public ArrayList<TutoringVO3> getTutoringMoneyAsc(int start, int end){ 
			ArrayList<TutoringVO3> tutoringList = new ArrayList();
			try{
				String sql = "select tutoringInfo.tutoring_id, tutoringInfo.tutor_id, name,title, subtitle, start_date, end_date, career, contents, count, upload_date, ifnull(sum(tutoring_apply_info.price),0) as price "
						+ "from tutoringInfo left join tutoring_apply_info on tutoringInfo.tutoring_id= tutoring_apply_info.tutoring_id "
						+ "group by tutoringInfo.tutoring_id "
						+ "order by price asc, tutoringInfo.tutoring_id asc limit ?,?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start-1);
				pstmt.setInt(2, end);

				ResultSet rs = pstmt.executeQuery();
				// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			} // catch 
			return tutoringList;
		}
		// 튜터링 수입순
				public ArrayList<TutoringVO3> getTutoringMoneyDesc(int start, int end){ 
					ArrayList<TutoringVO3> tutoringList = new ArrayList();
					try{
						String sql = "select tutoringInfo.tutoring_id, tutoringInfo.tutor_id, name,title, subtitle, start_date, end_date, career, contents, count, upload_date, ifnull(sum(tutoring_apply_info.price),0) as price "
								+ "from tutoringInfo left join tutoring_apply_info on tutoringInfo.tutoring_id= tutoring_apply_info.tutoring_id "
								+ "group by tutoringInfo.tutoring_id "
								+ "order by price desc, tutoringInfo.tutoring_id desc limit ?,?";
						PreparedStatement pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, start-1);
						pstmt.setInt(2, end);

						ResultSet rs = pstmt.executeQuery();
						// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
						while(rs.next())
							// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
							tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
						rs.close();
						pstmt.close();
					}catch(SQLException e){
						e.printStackTrace();
					} // catch 
					return tutoringList;
				}
	// 튜터링 번호순
	public ArrayList<TutoringVO3> getTutoring(int start, int end){ 
		ArrayList<TutoringVO3> tutoringList = new ArrayList();
		try{
			String sql = "select tutoring_id, tutoring_info.tutor_id, name,title, subtitle, start_date, end_date, career, price, contents, count, upload_date"
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
				tutoringList.add(new TutoringVO3(rs.getInt("tutoring_id"), rs.getString("tutor_id"), rs.getString("title"), rs.getString("subtitle"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("career"), rs.getString("price"), rs.getString("contents").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), rs.getInt("count"), rs.getString("upload_date"), rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringList;
	}
	
	// 등록된 튜터링 수
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

	// 튜터링 상세
	public TutoringVO3 tutoringDetail(int tutoringId){
		PreparedStatement pstmt;
		ResultSet rs;
		TutoringVO3 vo = null;
		try {
			String sql = "select tutoring_id, tutor_id, name, title, subtitle, start_date, end_date, career, price, contents, count, upload_date "
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
				vo.setUploadDate(rs.getString("upload_date").substring(0, 16));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	
	// 시간
	public ArrayList<TutoringTimeVO2> getTutoringsTime(int tutoringId){
		//		String images = null;
		ArrayList<TutoringTimeVO2> times = new ArrayList();
		try{
			String sql =  "select tutoringTimes.tutoring_id, tutoringTimes.times "
					+ "from tutoringTimes "
					+ "where tutoringTimes.tutoring_id =?";
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
	
	// 직종
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
	
	// 요일
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
	// 튜터링 통계 끝
	// 후기 통계 상세
	public StatisticVO statisticDetail(int reviewId){
		PreparedStatement pstmt;
		ResultSet rs;
		StatisticVO vo = null;
		try {
			String sql = "select member_info.member_id,review.review_id, review.tutoring_apply_id, review_title, tutoring_info.title,review_content, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count,tutoringList.name, member_info.name "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "and review.review_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,reviewId);
			rs = pstmt.executeQuery();

			vo = new StatisticVO();

			while(rs.next()){
				vo.setMemberId(rs.getString("member_info.member_id"));
				vo.setReviewId(rs.getInt("review.review_id"));
				vo.setTutoringApplyId(rs.getInt("review.tutoring_apply_id"));
				vo.setReviewTitle(rs.getString("review_title"));
				vo.setTutoringTitle(rs.getString("tutoring_info.title"));
				vo.setTutorName(rs.getString("tutor_id"));
				vo.setReviewContent(rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setReviewCount(rs.getInt("review_count"));
				vo.setReviewDate(rs.getString("review_date").substring(0, 11));
				vo.setLikeCount(rs.getInt("review_like_count"));
				vo.setTutorRealName(rs.getString("tutoringList.name"));
				vo.setTuteeRealName(rs.getString("member_info.name"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	// 좋아요한 멤버 아이디
	/**후기게시판 통계 좋아요 사람 아이디*/
	public ArrayList<reviewLikeReportVO> ReviewLikeUser(int reviewId){
		ArrayList<reviewLikeReportVO> ReviewLikeUser = new ArrayList<reviewLikeReportVO>();
		try{
			String sql = "select review.review_id, review_like_report.userId "
					+ "from review_like_report, review "
					+ "where review_like_report.review_id = review.review_id "
					+ "and review.review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				ReviewLikeUser.add(new reviewLikeReportVO(rs.getInt("review_id"), rs.getString("userId")));
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReviewLikeUser;
	}	
	
	// 뷰 꼭 해죠야댐
//	create view tutoringList
//	as select tutoring_info.tutoring_id, tutor_id, name, title, subtitle, start_date, end_date, career, tutoring_info.price, contents, count 
//	from tutoring_info, member_info, tutoring_apply_info 
//	where member_info.member_id = tutoring_info.tutor_id and
//	tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id
	/**후기게시판 갯수  */
	public int selectReivewCount(){
		int selectReivewCount = 0;
		try{
			String sql = "select count(*) from review";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectReivewCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectReivewCount;
	}
	
	/**후기게시판 통계 목록*/
	public ArrayList<StatisticVO> getReviewLikeList(int start, int end){
		ArrayList<StatisticVO> getReviewLikeList = new ArrayList<StatisticVO>();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_id asc limit ?,? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				getReviewLikeList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getReviewLikeList;
	}	
	
	/**후기게시판 통계 목록 내림차순*/
	public ArrayList<StatisticVO> getReviewLikeListDesc(int start, int end){
		ArrayList<StatisticVO> getReviewLikeList = new ArrayList<StatisticVO>();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_like_count desc limit ?,? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				getReviewLikeList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getReviewLikeList;
	}	
	
	/**후기게시판 통계 목록 올림차순*/
	public ArrayList<StatisticVO> getReviewLikeListAsc(int start, int end){
		ArrayList<StatisticVO> getReviewLikeList = new ArrayList<StatisticVO>();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_like_count asc limit ?,? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				getReviewLikeList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getReviewLikeList;
	}	
	
	/**후기게시판 통계 1개월내역 검색 select*/
	public int selectAmonthListCount(){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from review, tutoring_apply_info "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
				+ "and 	review_date > date_add(now(), interval -1 month) ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}
	
	public ArrayList<StatisticVO> selectAmonthList( int start, int end){ 
		ArrayList<StatisticVO> selectAmonthList = new ArrayList();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and 	review_date > date_add(now(), interval -1 month)  "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				selectAmonthList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return selectAmonthList;
	}
	
	/**후기게시판 통계 3개월내역 검색 select*/
	public int selectThreeListCount(){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from review, tutoring_apply_info "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
				+ "and 	review_date > date_add(now(), interval -3 month) ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}
	
	public ArrayList<StatisticVO> selectThreeList( int start, int end){ 
		ArrayList<StatisticVO> selectAmonthList = new ArrayList();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and 	review_date > date_add(now(), interval -3 month)  "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				selectAmonthList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return selectAmonthList;
	}
	
	/**후기게시판 통계 6개월내역 검색 select*/
	public int selectSixListCount(){
		int selectinterviewListAllCount = 0;
		String sql = "select count(*) "
				+ "from review, tutoring_apply_info "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
				+ "and 	review_date > date_add(now(), interval -6 month) ";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next())
				selectinterviewListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectinterviewListAllCount;
	}
	
	public ArrayList<StatisticVO> selectSixList( int start, int end){ 
		ArrayList<StatisticVO> selectAmonthList = new ArrayList();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and 	review_date > date_add(now(), interval -6 month)  "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				selectAmonthList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return selectAmonthList;
	}
	
	
	public int selectDateListCount(String selectstartDate, String selectEndDate){
		int selectDateListCount = 0;
		String sql = "select count(*) "
				+ "from review, tutoring_apply_info "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
				+ "and review_date between ? and ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				selectDateListCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectDateListCount;
	}
	/**튜터링 날짜 검색 리스트 select*/
	public ArrayList<StatisticVO> selectDateList(String selectstartDate, String selectEndDate,int start, int end){ 
		ArrayList<StatisticVO> selectdateList = new ArrayList();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, review_like_count "
					+ "from review, tutoring_apply_info, member_info,tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and review_date between ? and ? "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			pstmt.setInt(3, start-1);
			pstmt.setInt(4, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				selectdateList.add(new StatisticVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return selectdateList;
	}
	
	// 튜터별 후기현황
	 public List<Map<String, Object>> getTutorReview() throws Exception{
	    	PreparedStatement pstmt = null;
	    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	    	try{
	    		String sql = "select distinct tutoringTutor.name, count(review_id) as review_count "
	    				+ "from review, tutoring_apply_info,  tutoringTutor "
	    				+ "where "
	    				+ "review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
	    				+ "tutoring_apply_info.tutoring_id = tutoringTutor.tutoring_id "
	    				+ "group by tutor_id "
	    				+ "order by review_count desc "
	    				+ "limit 10";
	    		Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
	    		 while(rs.next()){
	    			 Map<String, Object> row = new HashMap<String, Object>();
	    			 // ResultSet에 있는 값을 row에 채운다.
	    			 row.put("name", rs.getString("name"));
	    			 row.put("review_count", rs.getLong("review_count"));
	    			 result.add(row);
	    		 }
	    	}catch(SQLException e){
				e.printStackTrace();
			} // catch 
	    	 return result;
	    }
	 // 수정
//	 create view tutoringList2
//	 as select distinct tutoring_info.tutoring_id, tutor_id, name, title, subtitle, start_date, end_date, career, tutoring_info.price, contents, count 
//	 	from tutoring_info, member_info, tutoring_apply_info 
//	 	where member_info.member_id = tutoring_info.tutor_id and
//	 	tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id
	// 월간에서
    public List<Map<String, Object>> getRevenue(String yyyyMM) throws Exception{
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	try{
    		String sql = "select distinct tutoringList2.title as title, count(review_id) as review_count "
    				+ "from review, tutoring_apply_info,  tutoringList2 "
    				+ "where DATE_FORMAT(review_date, '%Y-%m') = ? "
    				+ "and review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
    				+ "tutoring_apply_info.tutoring_id = tutoringList2.tutoring_id "
    				+ "group by tutoringList2.title  "
    				+ "order by review_count desc "
    				+ "limit 10";
    		 pstmt = conn.prepareStatement(sql);
    		 pstmt.setString(1, yyyyMM);
    		 rs = pstmt.executeQuery();
    		 while(rs.next()){
    			 Map<String, Object> row = new HashMap<String, Object>();
    			 // ResultSet에 있는 값을 row에 채운다.
    			 row.put("title", rs.getString("title"));
    			 row.put("review_count", rs.getLong("review_count"));
    			 result.add(row);
    		 }
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
	// 월별
    public Map<String, Object> getAnnualRevenue(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '01', 1, 0)) as count_01, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '02', 1, 0)) as count_02, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '03', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '04', 1, 0)) as count_04, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '05', 1, 0)) as count_05, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '06', 1, 0)) as count_06, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '07', 1, 0)) as count_07, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '08', 1, 0)) as count_08, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '09', 1, 0)) as count_09, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '10', 1, 0)) as count_10, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '11', 1, 0)) as count_11, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') = '12', 1, 0)) as count_12 "
    				+ "from review "
    				+ "where DATE_FORMAT(review_date,'%Y') = ? ";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    			result.put("count_05", rs.getLong("count_05"));
    			result.put("count_06", rs.getLong("count_06"));
    			result.put("count_07", rs.getLong("count_07"));
    			result.put("count_08", rs.getLong("count_08"));
    			result.put("count_09", rs.getLong("count_09"));
    			result.put("count_10", rs.getLong("count_10"));
    			result.put("count_11", rs.getLong("count_11"));
    			result.put("count_12", rs.getLong("count_12"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    			result.put("count_05", 0L);
    			result.put("count_06", 0L);
    			result.put("count_07", 0L);
    			result.put("count_08", 0L);
    			result.put("count_09", 0L);
    			result.put("count_10", 0L);
    			result.put("count_11", 0L);
    			result.put("count_12", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
 // 분기별
    public Map<String, Object> getYearRevenue(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select SUM(IF(DATE_FORMAT(review_date, '%m') between '01' and '03', 1, 0)) as count_01,  "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') between '04' and '06', 1, 0)) as count_02,  "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') between '07' and '09', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(review_date, '%m') between '10' and '12', 1, 0)) as count_04 "
    				+ "from review "
    				+ "where YEAR(review_date) =?";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
	// 후기게시판 좋아요 누른 사람들의 직종 --> 대분류 사용
//    create view reviewMainMiddle
//    as select AllMembers.name as name, AllMembers.member_id as memberId, middle_name, main_category.main_name
//    from AllMembers left join job_select on AllMembers.member_id = job_select.member_id 
//    left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id
//    left join main_category on middle_category.main_categ_id = main_category.main_categ_id
	//----------------------------------
//    create view reviewMainMiddles
//    as select distinct AllMembers.name as name, AllMembers.member_id as memberId,  main_category.main_name
//    from AllMembers left join job_select on AllMembers.member_id = job_select.member_id 
//    left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id
//    left join main_category on middle_category.main_categ_id = main_category.main_categ_id
//    public List<Map<String, Object>> getTutorReviewJob(int ReviewStatisticnum) throws Exception{
//	    	PreparedStatement pstmt = null;
//	    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//	    	try{
//	    		String sql = "select reviewMainMiddles.main_name, count(reviewMainMiddles.main_name) as mainCount "
//	    				+ "from review_like_report, reviewMainMiddles "
//	    				+ "where review_like_report.userId = reviewMainMiddles.memberId and "
//	    				+ "review_like_report.review_id =?";
//	    		pstmt = conn.prepareStatement(sql);
//	    		pstmt.setInt(1, ReviewStatisticnum);
//	    		ResultSet rs = pstmt.executeQuery();
//	    		 while(rs.next()){
//	    			 Map<String, Object> row = new HashMap<String, Object>();
//	    			 // ResultSet에 있는 값을 row에 채운다.
//	    			 row.put("main_name", rs.getString("reviewMainMiddles.main_name"));
//	    			 row.put("mainCount", rs.getLong("mainCount"));
//	    			 result.add(row);
//	    		 }
//	    	}catch(SQLException e){
//				e.printStackTrace();
//			} // catch 
//	    	 return result;
//	    }
 // 후기게시판 좋아요 누른 사람들의 직종 --> 중분류 사용
//    -- 중분류를 가지고 통계
//    create view reviewMiddle
//    as select AllMembers.name as name, AllMembers.member_id as memberId, middle_name
//    from AllMembers left join job_select on AllMembers.member_id = job_select.member_id 
//    left join middle_category on job_select.middle_categ_id = middle_category.middle_categ_id

    public List<Map<String, Object>> getTutorReviewJob(int ReviewStatisticnum) throws Exception{
    	PreparedStatement pstmt = null;
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	try{
    		String sql = "select reviewMiddle.middle_name, count(reviewMiddle.middle_name) as middle_count "
    				+ "from review_like_report, reviewMiddle "
    				+ "where review_like_report.userId = reviewMiddle.memberId and "
    				+ "review_like_report.review_id =? "
    				+ "group by reviewMiddle.middle_name";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, ReviewStatisticnum);
    		ResultSet rs = pstmt.executeQuery();
    		 while(rs.next()){
    			 Map<String, Object> row = new HashMap<String, Object>();
    			 // ResultSet에 있는 값을 row에 채운다.
    			 row.put("middle_name", rs.getString("reviewMiddle.middle_name"));
    			 row.put("middleCount", rs.getLong("middle_count"));
    			 result.add(row);
    		 }
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
	//1106
	public Map<String, Object> ageMember() throws Exception{
		PreparedStatement pstmt = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String sql = "select "
					+ "sum(if(date_format(now(),'%Y')-substring(BIRTH,1,4)+1 between 10 and 19 , 1, 0)) as age10, "
					+ "sum(if(date_format(now(),'%Y')-substring(BIRTH,1,4)+1 between 20 and 29 , 1, 0)) as age20, "
					+ "sum(if(date_format(now(),'%Y')-substring(BIRTH,1,4)+1 between 30 and 39 , 1, 0)) as age30, "
					+ "sum(if(date_format(now(),'%Y')-substring(BIRTH,1,4)+1 between 40 and 49 , 1, 0)) as age40, "
					+ "sum(if(date_format(now(),'%Y')-substring(BIRTH,1,4)+1 between 50 and 59 , 1, 0)) as age50, "
					+ "sum(if(date_format(now(),'%Y')-substring(BIRTH,1,4)+1 between 60 and 69 , 1, 0)) as age60 "
					+ "from member_info";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				result.put("age10", rs.getLong("age10"));
				result.put("age20", rs.getLong("age20"));
				result.put("age30", rs.getLong("age30"));
				result.put("age40", rs.getLong("age40"));
				result.put("age50", rs.getLong("age50"));
				result.put("age60", rs.getLong("age60"));
				
			}else{
				result.put("age10", 0L);
				result.put("age20", 0L);
				result.put("age30", 0L);
				result.put("age40", 0L);
				result.put("age50", 0L);
				result.put("age60", 0L);
				
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Map<String, Object> genderMember() throws Exception{
		PreparedStatement pstmt = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String sql = "select "
					+ "count(CASE WHEN gender='M' THEN 1 END ) as male, "
					+ "count(CASE WHEN gender='F' THEN 1 END ) as female "
					+ "from member_info";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				result.put("male", rs.getLong("male"));
				result.put("female", rs.getLong("female"));
				
			}else{
				result.put("male", 0L);
				result.put("female", 0L);			
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	 // 분기별
    public Map<String, Object> RegisterDateMember(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select SUM(IF(DATE_FORMAT(register_date, '%m') between '01' and '03', 1, 0)) as count_01,  "
    				+ "SUM(IF(DATE_FORMAT(register_date, '%m') between '04' and '06', 1, 0)) as count_02,  "
    				+ "SUM(IF(DATE_FORMAT(register_date, '%m') between '07' and '09', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(register_date, '%m') between '10' and '12', 1, 0)) as count_04 "
    				+ "from member_info "
    				+ "where YEAR(register_date) = ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
    
    public Map<String, Object> RegisterDateCompany(String yyyy) throws Exception{ // 한건데이터이기 때문에 list를 쓰지 않는다.
    	PreparedStatement pstmt = null;
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String sql = "select SUM(IF(DATE_FORMAT(register_date, '%m') between '01' and '03', 1, 0)) as count_01,  "
    				+ "SUM(IF(DATE_FORMAT(register_date, '%m') between '04' and '06', 1, 0)) as count_02,  "
    				+ "SUM(IF(DATE_FORMAT(register_date, '%m') between '07' and '09', 1, 0)) as count_03, "
    				+ "SUM(IF(DATE_FORMAT(register_date, '%m') between '10' and '12', 1, 0)) as count_04 "
    				+ "from company_info "
    				+ "where YEAR(register_date) = ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, yyyy);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			result.put("count_01", rs.getLong("count_01"));
    			result.put("count_02", rs.getLong("count_02"));
    			result.put("count_03", rs.getLong("count_03"));
    			result.put("count_04", rs.getLong("count_04"));
    		}else{
    			result.put("count_01", 0L); // L은 타입
    			result.put("count_02", 0L);
    			result.put("count_03", 0L);
    			result.put("count_04", 0L);
    		}
    		
    	}catch(SQLException e){
			e.printStackTrace();
		} // catch 
    	 return result;
    }
	public int getMemberYearCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(year) "
					+ "from memberYearCount";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
    
	public List<Map<String, Object>> getMemberYear(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select memberYearCount.year, ifnull(count,0) as count "
					+ "from memberYearCount "
					+ "group by memberYearCount.year "
					+ "order by memberYearCount.year asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("memberYearCount.year"));
				row.put("memberR", rs.getLong("count"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
	
	
	public int getCompanyYearCount() throws Exception{
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "select count(year) "
					+ "from companyYearCount";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
    
	public List<Map<String, Object>> getCompanyYear(int start, int end) throws Exception{
		PreparedStatement pstmt = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			String sql = "select companyYearCount.year, ifnull(count,0) as count "
					+ "from companyYearCount "
					+ "group by companyYearCount.year "
					+ "order by companyYearCount.year asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				// ResultSet에 있는 값을 row에 채운다.
				row.put("date", rs.getString("companyYearCount.year"));
				row.put("memberR", rs.getLong("count"));
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return result;
	}
}