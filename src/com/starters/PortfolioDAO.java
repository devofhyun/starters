package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PortfolioDAO {
   private Connection conn;
   public PortfolioDAO(){
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

	/**관리자 포트폴리오 관리*/
	// 뷰 
	//	create view portfolioList
	//	as select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count, name
	//	from portfolio, member_info
	//	where member_info.member_id = portfolio.member_id 
	public ArrayList<PortfolioLikeVO> adminGetPortfolio(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count "
					+ "from portfolio left outer join portfolio_like_report "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id "
					+ ", portfolioList "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id "
					+ "group by portfolio.portfolio_id "
					+ "order by portfolio.portfolio_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}


	/**포트폴리오 상세*/
	public PortfolioVO2 adminPortfolioDetail(int portfolioId){
		PreparedStatement pstmt;
		ResultSet rs;
		PortfolioVO2 vo = null;

		try {
			String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method"
					+ ", portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date"
					+ ", portfolio_like_count, name "
					+ "from portfolio, member_info  "
					+ "where member_info.member_id = portfolio.member_id "
					+ "and portfolio.portfolio_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,portfolioId);
			rs = pstmt.executeQuery();

			vo = new PortfolioVO2();
			while(rs.next()){
				vo.setPortfolioId(rs.getInt("portfolio_id"));
				vo.setPortfolioTitle(rs.getString("portfolio_title"));
				vo.setPortfolioMethod(rs.getString("portfolio_method"));
				vo.setPortfolioUrl(rs.getString("portfolio_url"));
				vo.setPortfolioFile(rs.getString("portfolio_file"));
				vo.setPortfolioText(rs.getString("portfolio_text").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setPortfolioCount(rs.getInt("portfolio_count"));
				vo.setUploadDate(rs.getString("upload_date").substring(0, 11));
				vo.setLikeCount(rs.getInt("portfolio_like_count"));
				vo.setName(rs.getString("name"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**포트폴리오 검색_전체 */
	public ArrayList<PortfolioLikeVO> adminSelectAllPortfolioList(String portfolioTitle, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		try {
			String sql = "select distinct portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
					+ "from portfolio left outer join portfolio_like_report  "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
					+ ", portfolioList  "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
					+ "and portfolio.portfolio_title like ? or portfolio.portfolio_text like ?  "
					+ "group by portfolio.portfolio_id  "
					+ "order by portfolio.portfolio_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + portfolioTitle + "%");
			pstmt.setString(2, "%" + portfolioTitle + "%");
			pstmt.setInt(3, start-1);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();

			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				list.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return list;
	}

	/**키워드 검색*/
	public ArrayList<PortfolioLikeVO> adminSelectPortfolioList(String portfolioTitle, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		try {
			String sql = "select distinct portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
					+ "from portfolio left outer join portfolio_like_report  "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
					+ ", portfolioList  "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
					+ "and portfolio.portfolio_title like ? "
					+ "group by portfolio.portfolio_id  "
					+ "order by portfolio.portfolio_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + portfolioTitle + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			}   	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	/**포트폴리오 검색_튜티명 */
	public ArrayList<PortfolioLikeVO> adminSelectTuteePortfolioList(String portfolioTitle, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		try {
			String sql = 
					"select distinct portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
							+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
							+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
							+ "from portfolio left outer join portfolio_like_report  "
							+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
							+ ", portfolioList  "
							+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
							+ "and portfolioList.name like ? "
							+ "group by portfolio.portfolio_id  "
							+ "order by portfolio.portfolio_id asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + portfolioTitle + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			}   	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	/**1개월 리스트*/
	public ArrayList<PortfolioLikeVO> adminGetPortfolioAmonth(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count "
					+ "from portfolio left outer join portfolio_like_report "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id "
					+ ", portfolioList "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id "
					+ "and	portfolio.upload_date > date_add(now(), interval -1 month) "
					+ "group by portfolio.portfolio_id "
					+ "order by portfolio.portfolio_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}
	/**1개월 갯수*/
	public int portfolioCountAmonth(){
		int portfolioAllCount = 0;
		String sql = "select count(*) "
				+ "from portfolio "
				+ "where portfolio.upload_date > date_add(now(), interval -1 month)";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				portfolioAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return portfolioAllCount;
	}

	/**3개월 리스트*/
	public ArrayList<PortfolioLikeVO> adminGetPortfolioThreeMonth(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count "
					+ "from portfolio left outer join portfolio_like_report "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id "
					+ ", portfolioList "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id "
					+ "and	portfolio.upload_date > date_add(now(), interval -3 month) "
					+ "group by portfolio.portfolio_id "
					+ "order by portfolio.portfolio_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}
	/**3개월 갯수*/
	public int portfolioCountThreeMonth(){
		int portfolioAllCount = 0;
		String sql = "select count(*) "
				+ "from portfolio "
				+ "where portfolio.upload_date > date_add(now(), interval -3 month)";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				portfolioAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return portfolioAllCount;
	}

	/**6개월 리스트*/
	public ArrayList<PortfolioLikeVO> adminGetPortfolioSixMonth(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count "
					+ "from portfolio left outer join portfolio_like_report "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id "
					+ ", portfolioList "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id "
					+ "and	portfolio.upload_date > date_add(now(), interval -6 month) "
					+ "group by portfolio.portfolio_id "
					+ "order by portfolio.portfolio_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}
	/**6개월 갯수*/
	public int portfolioCountSixMonth(){
		int portfolioAllCount = 0;
		String sql = "select count(*) "
				+ "from portfolio "
				+ "where portfolio.upload_date > date_add(now(), interval -6 month)";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				portfolioAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return portfolioAllCount;
	}
	/**날짜검색 리스트*/
	public ArrayList<PortfolioLikeVO> adminGetPortfolioDate(String selectstartDate, String selectEndDate, int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count "
					+ "from portfolio left outer join portfolio_like_report "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id "
					+ ", portfolioList "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id "
					+ "and portfolio.upload_date between ? and ?  "
					+ "group by portfolio.portfolio_id "
					+ "order by portfolio.portfolio_id asc limit ?,? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			pstmt.setInt(3, start-1);
			pstmt.setInt(4, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}
	/**날짜검색 갯수*/
	public int portfolioCountDate(String selectstartDate, String selectEndDate){
		int portfolioAllCount = 0;
		String sql = "select count(*) "
				+ "from portfolio "
				+ "where portfolio.upload_date between ? and ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, selectstartDate);
			pstmt.setString(2, selectEndDate);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				portfolioAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return portfolioAllCount;
	}

	// 좋아요, 조회수, 번호순....
	public ArrayList<PortfolioLikeVO>adminGetPortfolioId(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
					+ "from portfolio left outer join portfolio_like_report  "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
					+ ", portfolioList  "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
					+ "group by portfolio.portfolio_id  "
					+ "order by portfolio.portfolio_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}

	/** select_조회수순*/
	public ArrayList<PortfolioLikeVO> adminGetHitsPortfolio(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
					+ "from portfolio left outer join portfolio_like_report  "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
					+ ", portfolioList  "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
					+ "group by portfolio.portfolio_id  "
					+ "order by portfolio.portfolio_count desc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}

	/**select_최신순*/
	public ArrayList<PortfolioLikeVO> adminGetDatePortfolio(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
					+ "from portfolio left outer join portfolio_like_report  "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
					+ ", portfolioList  "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
					+ "group by portfolio.portfolio_id  "
					+ "order by portfolio.upload_date desc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}

	/**select_좋아요순*/
	public ArrayList<PortfolioLikeVO> adminGetLikePortfolio(int start, int end){
		ArrayList<PortfolioLikeVO> portfolioList = new ArrayList();
		try{
			String sql = "select portfolioList.name, portfolio.portfolio_id, portfolio.portfolio_title, portfolio.portfolio_method  "
					+ ", portfolio.portfolio_url, portfolio.portfolio_text, portfolio.portfolio_file,portfolio.portfolio_count, portfolio.upload_date  "
					+ ", count(portfolio_like_report.portfolio_id) as 'portfolio_like_counts', portfolio.portfolio_like_count  "
					+ "from portfolio left outer join portfolio_like_report  "
					+ "on portfolio.portfolio_id = portfolio_like_report.portfolio_id  "
					+ ", portfolioList  "
					+ "where portfolioList.portfolio_id = portfolio.portfolio_id  "
					+ "group by portfolio.portfolio_id  "
					+ "order by portfolio_like_counts desc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				portfolioList.add(new PortfolioLikeVO(rs.getInt("portfolio_id"), rs.getString("portfolio_title")
						, rs.getString("portfolio_method"), rs.getString("portfolio_url")
						, rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date").substring(0, 11)
						, rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count"), rs.getInt("portfolio_like_counts"),rs.getString("name")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioList;
	}


	// =====================================================
   public boolean JobSelects(int portfolioId,int middleCategId){
      boolean result = false;
      try{
         String sql = "insert into portfolio_job_selects(portfolio_id, middle_categ_id) values(?,?)";
         PreparedStatement pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.setInt(2, middleCategId);


         if(pstmt.executeUpdate()==1)
            result = true;

      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }


   

   public ArrayList<IntImageVO> getPortfolioImage(int portfolioId){
      //      String images = null;
      ArrayList<IntImageVO> images = new ArrayList();
      try{
         String sql =  "select portfolio.portfolio_id, portfolio_image.image "
               + "from portfolio, portfolio_image "
               + "where portfolio.portfolio_id = portfolio_image.portfolio_id and "
               + "portfolio.portfolio_id = ?";
         PreparedStatement pstmt =conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()){
            //      for(int i = 1; i< rs.length(); i++){
            images.add(new IntImageVO(rs.getInt("portfolio_id"), rs.getString("image")));
            //      images = rs.getString("image");
         }
         rs.close();
         pstmt.close();
      }catch(SQLException e){
         e.printStackTrace();
      }
      return images;
   }

   /**포트폴리오 상세*/
   public PortfolioVO portfolioDetail(int portfolioId){
      PreparedStatement pstmt;
      ResultSet rs;
      PortfolioVO vo = null;

      try {
         String sql = "select portfolio.portfolio_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
               + "from portfolio "
               + "where portfolio.portfolio_id = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1,portfolioId);
         rs = pstmt.executeQuery();

         vo = new PortfolioVO();
         while(rs.next()){
            vo.setPortfolioId(rs.getInt("portfolio_id"));
            vo.setPortfolioTitle(rs.getString("portfolio_title"));
            vo.setPortfolioMethod(rs.getString("portfolio_method"));
            vo.setPortfolioUrl(rs.getString("portfolio_url"));
            vo.setPortfolioFile(rs.getString("portfolio_file"));
            vo.setPortfolioText(rs.getString("portfolio_text").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
            vo.setPortfolioCount(rs.getInt("portfolio_count"));
            vo.setUploadDate(rs.getString("upload_date"));
            vo.setLikeCount(rs.getInt("portfolio_like_count"));
         }
         rs.close();
         pstmt.close();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return vo;
   }
   /**포트폴리오 수정을 위한상세*/
   public PortfolioVO portfolioDetails(int portfolioId){
      PreparedStatement pstmt;
      ResultSet rs;
      PortfolioVO vo = null;

      try {
         String sql = "select portfolio.portfolio_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
               + "from portfolio "
               + "where portfolio.portfolio_id = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1,portfolioId);
         rs = pstmt.executeQuery();

         vo = new PortfolioVO();
         while(rs.next()){
            vo.setPortfolioId(rs.getInt("portfolio_id"));
            vo.setPortfolioTitle(rs.getString("portfolio_title"));
            vo.setPortfolioMethod(rs.getString("portfolio_method"));
            vo.setPortfolioUrl(rs.getString("portfolio_url"));
            vo.setPortfolioFile(rs.getString("portfolio_file"));
            vo.setPortfolioText(rs.getString("portfolio_text"));
            vo.setPortfolioCount(rs.getInt("portfolio_count"));
            vo.setUploadDate(rs.getString("upload_date"));
            vo.setLikeCount(rs.getInt("portfolio_like_count"));
         }
         rs.close();
         pstmt.close();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return vo;
   }

   /**글쓴이 */
   public PortfolioVO portfolioWriter(int tutoringId){
      PreparedStatement pstmt;
      ResultSet rs;
      PortfolioVO vo = null;
      try {
         String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
               + "from portfolio, member_info "
               + "where portfolio_id = ? "
               + "and  member_info.member_id = portfolio.member_id ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1,tutoringId);
         rs = pstmt.executeQuery();

         vo = new PortfolioVO();

         while(rs.next()){
            vo.setPortfolioId(rs.getInt("portfolio_id"));
            vo.setMemberId(rs.getString("member_id"));
            vo.setPortfolioTitle(rs.getString("portfolio_title"));
            vo.setPortfolioMethod(rs.getString("portfolio_method"));
            vo.setPortfolioUrl(rs.getString("portfolio_url"));
            vo.setPortfolioFile(rs.getString("portfolio_file"));
            vo.setPortfolioText(rs.getString("portfolio_text"));
            vo.setUploadDate(rs.getString("upload_date"));
            vo.setPortfolioCount(rs.getInt("portfolio_count"));
            vo.setLikeCount(rs.getInt("portfolio_like_count"));
         }
         rs.close();
         pstmt.close();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return vo;


   }


   



   /**포트폴리오 등록 
    * insert*/
   public boolean registerPortfolio(PortfolioVO vo) {
      return registerPortfolio(vo.getMemberId(),vo.getPortfolioTitle(), vo.getPortfolioMethod(),vo.getPortfolioUrl(),vo.getPortfolioFile(),vo.getPortfolioText(),vo.getUploadDate());
   }

   public boolean registerPortfolio(String memberId,String portfolioTitle,String portfolioMethod,String portfolioUrl,
         String portfolioFile,String portfolioText,String uploadDate) {
      boolean result = false;
      try{
         String sql ="INSERT INTO portfolio(member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text, upload_date) "
               + "VALUES (?,?,?,?,?,?,?)";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memberId);
         pstmt.setString(2, portfolioTitle);
         pstmt.setString(3, portfolioMethod);
         pstmt.setString(4, portfolioUrl);
         pstmt.setString(5, portfolioFile);
         pstmt.setString(6, portfolioText);
         pstmt.setString(7, uploadDate);

         if(pstmt.executeUpdate()==1){
            result = true;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }

   public boolean addMemberPortfolioImage(int portfolioId, String image) {
      boolean result = false;
      try{
         String sql2 = "INSERT INTO portfolio_image(portfolio_id, image) "
               + "VALUES (?, ?)";
         PreparedStatement pstmt2 = conn.prepareStatement(sql2);

         pstmt2.setInt(1, portfolioId);
         pstmt2.setString(2, image);

         if(pstmt2.executeUpdate()==1)
            result = true;
      }catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }


   /**포트폴리오 수정 
    * 수정이 되었으면 true 
    * update*/


   public boolean updatePortfolio(String portfolioTitle, String portfolioMethod, String portfolioUrl, String portfolioFile, 
         String portfolioText, int portfolioId){
      boolean result = false;
      try{
         String sql = "update portfolio "
               + "set portfolio_title = ?, portfolio_method = ?, portfolio_url = ?, portfolio_file = ?, portfolio_text = ? "
               +"where portfolio_id = ? ";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, portfolioTitle);
         pstmt.setString(2, portfolioMethod);
         pstmt.setString(3, portfolioUrl);
         pstmt.setString(4, portfolioFile);
         pstmt.setString(5, portfolioText);
         pstmt.setInt(6, portfolioId);

         if(pstmt.executeUpdate() == 1){
            result = true;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }

/*
   public boolean updatePortfolioImage(String img,int portfolioId){
      boolean result = false;
      try{
         String sql = "update portfolio_image "
               + "set image = ? "
               +"where portfolio_id = ? ";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, img);
         pstmt.setInt(2, portfolioId);


         if(pstmt.executeUpdate() == 1){
            result = true;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }

*/

   /**포트폴리오 삭제 삭제가 되었으면 true, 
    * 삭제 못했으면 false 
    * Q) 이미 로그인한 상태에서 포트폴리오를 삭제한다면 id는 필요가 없다.
    * 포트폴리오 일련번호가 존재하고 그 번호는 유일하기 때문에 portfolio번호로 삭제가능하다고 생각(tutorId가 필요없다)
    * delete
    */
   public boolean deletePortfolio(int portfolioId){
      boolean result = false;
      try{
         String sql="delete from portfolio "
               + "where portfolio_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.executeUpdate();
         result = true;
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   
   public boolean deletePortfolioImage(int portfolioId){
      boolean result = false;
      try{
         String sql="delete from portfolio_image "
               + "where portfolio_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.executeUpdate();
         result = true;
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }


   /**등록된 전체 포트폴리오의 수
    * select*/
   public int portfolioCount(){
      int portfolioAllCount = 0;
      String sql = "select count(*) from portfolio";
      try{
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

         while(rs.next())
            portfolioAllCount = rs.getInt(1);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return portfolioAllCount;
   }




   public PortfolioVO getPortfolioRegisterInfo(String memberId){
      PreparedStatement pstmt;            
      ResultSet rs;
      PortfolioVO vo =null;

      try{
         String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, "
               + "portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
               + "from portfolio "
               + "where member_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memberId);
         rs = pstmt.executeQuery();

         vo = new PortfolioVO();
         while(rs.next()){
            vo.setPortfolioId(rs.getInt("portfolio_Id"));
            vo.setMemberId(rs.getString("member_id"));
            vo.setPortfolioTitle(rs.getString("portfolio_title"));
            vo.setPortfolioMethod(rs.getString("portfolio_method"));
            vo.setPortfolioUrl(rs.getString("portfolio_url"));
            vo.setPortfolioFile(rs.getString("portfolio_file"));
            vo.setPortfolioText(rs.getString("portfolio_text"));
            vo.setPortfolioCount(rs.getInt("portfolio_count"));
            vo.setUploadDate(rs.getString("upload_date"));
            vo.setLikeCount(rs.getInt("portfolio_like_count"));

         }

         rs.close();
         pstmt.close();

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return vo;
   }




   public int portfolioExist(int portfolioId, String memberId){
      int portfolio = 0;

      try{
         String sql = "select count(member_info.member_id) "
               + "from company_info, interview, portfolio, payment_info, member_info "
               + "where member_info.member_id = portfolio.member_id and "
               + "portfolio.portfolio_id = interview.portfolio_id and "
               + "interview.company_id = company_info.company_id and "
               + "interview.interview_id = payment_info.interview_id and "
               + "portfolio.portfolio_id = ? and member_info.member_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.setString(2, memberId);
         ResultSet rs = pstmt.executeQuery();
         if(rs.next())
            portfolio = rs.getInt(1);
      }catch (Exception e) {
         e.printStackTrace();
      }
      return portfolio;
   }

   /**좋아요 관련*/
   
   /**포트폴리오 조회수 증가
    * update*/

   public boolean portfolioCountIncrease(PortfolioVO vo) {
      return portfolioCountIncrease(vo.getPortfolioId());
   }

   public boolean portfolioCountIncrease(int portfolioId) {
      boolean result = false;
      try{
         String sql = "update portfolio "
               + "set portfolio_count = portfolio_count+1 "
               +"where portfolio_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);


         if(pstmt.executeUpdate() == 1){
            result = true;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   
   /**전제 조회수 업데이트_추가*/
   public int portfolioCountAllUp(int portfolioId) {
      int result = 0;
      try{
         String sql = "update portfolio "
               + "set portfolio_like_count = portfolio_like_count+1 "
               +"where portfolio_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);


         if(pstmt.executeUpdate() == 1){
            result = 1;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   /**전제 조회수 업데이트_삭제*/
   public int portfolioCountAllDown(int portfolioId) {
      int result = 0;
      try{
         String sql = "update portfolio "
               + "set portfolio_like_count = portfolio_like_count-+1 "
               +"where portfolio_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         //         pstmt.setString(2, userId);
         if(pstmt.executeUpdate() == 1){
            result = 1;
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }

   
   
   // 좋아요 insert
   /**좋아요 하기*/
   // [수정]
   public boolean portfolioLike(int portfolioId,String userId){
      boolean result = false;
      try{
         String sql = "INSERT INTO portfolio_like_report(portfolio_id, userId, portfolio_like) "
               + "SELECT ?, ?, IFNULL(MAX(portfolio_like), 0)+1 as 'portfolio_like_report'  FROM portfolio_like_report WHERE portfolio_id = ? ";
         PreparedStatement pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.setString(2, userId);
         pstmt.setInt(3, portfolioId);

         if(pstmt.executeUpdate()==1)
            result = true;

      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   // [기존]
//   public boolean portfolioLike(int portfolioId,String userId){
//      boolean result = false;
//      try{
//         String sql = "insert into portfolio_like_report(portfolio_id, userId) values(?,?)";
//         PreparedStatement pstmt=conn.prepareStatement(sql);
//         pstmt.setInt(1, portfolioId);
//         pstmt.setString(2, userId);
//         //         int insertedCount = pstmt.executeUpdate();
//         //         
//         //         if(insertedCount > 0){
//         //            Statement stmt = conn.createStatement();
//         //            ResultSet rs = stmt.executeQuery("select last_insert_id() from portfolio_like_report");
//         //         }
//
//         if(pstmt.executeUpdate()==1)
//            result = true;
//
//      }catch(SQLException e){
//         e.printStackTrace();
//      }
//      return result;
//   }
   /**전체 좋아요 개수_portfolioLike이용*/
   public int selectPortfolioLikeAllCount(int portfolioId){
      int selectPortfolioLikeAllCount = 0;
      String sql = "select count(portfolio_like) from portfolio_like_report  "
            + "where portfolio_id = ?";
      try{
         PreparedStatement pstmt =conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         ResultSet rs = pstmt.executeQuery();
         System.out.println("like :" + portfolioId);

         if(rs.next())
            selectPortfolioLikeAllCount = rs.getInt(1);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return selectPortfolioLikeAllCount;
   }   
   
   // 좋아요 select
   public LikeVO getPortfolioLikeList(String portfolioId){
      PreparedStatement pstmt;            
      ResultSet rs;
      LikeVO vo =null;

      try{
         String sql = "select portfolio.portfolio_id, userId, "
               + "from portfolio, portfolio_like_report "
               + "where portfolio.portfolio_id = ? "
               + "portfolio.portfolio_id = portfolio_like_report.portfolio_id";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, portfolioId);
         rs = pstmt.executeQuery();

         vo = new LikeVO();
         while(rs.next()){
            vo.setId(rs.getInt("portfolio_Id"));
            vo.setUserId(rs.getString("userId"));
         }

         rs.close();
         pstmt.close();

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return vo;
   }

   /**좋아요 삭제*/
   public boolean deletePortfolioLike(int portfolioId, String userId){
      boolean result = false;
      try{
         String sql="delete from portfolio_like_report "
               + "where portfolio_id = ? and userId = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.setString(2, userId);
         pstmt.executeUpdate();
         result = true;
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }

   /**좋아요 한 개수*/
   public int selectPortfolioLikeCount(int portfolioId){
      int selectPortfolioLikeCount = 0;
      String sql = "select count(portfolio_id) from portfolio_like_report "
            + "where portfolio_id = ?";
      try{
         PreparedStatement pstmt =conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         ResultSet rs = pstmt.executeQuery();

         if(rs.next())
            selectPortfolioLikeCount = rs.getInt(1);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return selectPortfolioLikeCount;
   }   


   public int portfolioLikeExist(int portfolioId, String memberId){
      int portfolio = 0;

      try{
//         String sql = "select count(member_info.member_id) "
//               + "from portfolio, member_info, portfolio_like_report "
//               + "where member_info.member_id = portfolio.member_id and "
//               + "portfolio.portfolio_id = portfolio_like_report.portfolio_id and "
//               + "portfolio.portfolio_id = ? and "
//               + "member_info.member_id = ?";
         String sql = "select count(*) from portfolio_like_report where portfolio_id = ? and userId = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.setString(2, memberId);
         ResultSet rs = pstmt.executeQuery();
         if(rs.next())
            portfolio = rs.getInt(1);
      }catch (Exception e) {
         e.printStackTrace();
      }
      return portfolio;
   }
   
   /**직종가져오기*/
   public ArrayList<IntJobSelectVO> getPortfoliosJob(int portfolioId){ // JobSelectVO에 보면 memberId를 tutoringId로 생각한다.
      //      String images = null;
      ArrayList<IntJobSelectVO> job = new ArrayList();
      try{
         String sql =  "select portfolio_job_selects.portfolio_select_id, portfolio_job_selects.portfolio_id, middle_category.middle_categ_id, middle_category.middle_name "
               + "from portfolio, portfolio_job_selects, middle_category  "
               + "where portfolio.portfolio_id = portfolio_job_selects.portfolio_id and "
               + "portfolio_job_selects.middle_categ_id = middle_category.middle_categ_id and "
               + "portfolio.portfolio_id = ?";
         PreparedStatement pstmt =conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()){
            job.add(new IntJobSelectVO(rs.getInt("portfolio_select_id"), rs.getInt("portfolio_id"), rs.getInt("middle_categ_id"), rs.getString("middle_name")));
         }
         rs.close();
         pstmt.close();
      }catch(SQLException e){
         e.printStackTrace();
      }
      return job;
   }
   
   /**직종 선택*/
   public boolean portfolioJobSelects(int portfolioId, int middleCategId){
      boolean result = false;
      try{
         String sql = "insert into portfolio_job_selects(portfolio_id, middle_categ_id) values(?,?)";
         PreparedStatement pstmt =conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.setInt(2, middleCategId);
         if(pstmt.executeUpdate() ==1)
            result = true;
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   /**포폴 잡셀렉트 삭제*/
   public boolean deletePortfolioJobSelects(int portfolioId){
      boolean result = false;
      try{
         String sql="delete from portfolio_job_selects "
               + "where portfolio_id = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, portfolioId);
         pstmt.executeUpdate();
         result = true;
      }catch(SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   
   public IntJobSelectVO getPortfolioJobSelect(int id){
      PreparedStatement pstmt;
      ResultSet rs;
      IntJobSelectVO vo = null;

      try {
         String sql = "select portfolio_job_selects.portfolio_job_id, portfolio.portfolio_id, middle_category.middle_categ_id, middle_name "
               + "from portfolio_job_selects, middle_category, portfolio "
               + "where portfolio.portfolio_id = portfolio_job_selects.portfolio_id and "
               + "portfolio_job_selects.middle_categ_id = middle_category.middle_categ_id and "
               + "portfolio.portfolio_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1,id);
         rs = pstmt.executeQuery();

         vo = new IntJobSelectVO();
         while(rs.next()){
            vo.setSelectId(rs.getInt("portfolio_job_id"));
            vo.setThisId(rs.getInt("portfolio_id"));
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
   
   
   
   
   
   /////////////////////////////////////////////////////////////////////
   // 1011포트폴리오_검색 추가
   /**포트폴리오 검색_타이틀(키워드) */
   public ArrayList<PortfolioVO> selectPortfolioList(String portfolioTitle, int start, int end ){
      PreparedStatement pstmt;            
      ResultSet rs;
      ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
      try {
         String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
               + "from portfolio, member_info "
               + "where portfolio_title like ? and "
               + "member_info.member_id = portfolio.member_id "
               + "order by upload_date desc limit ?,? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, "%" + portfolioTitle + "%");
         pstmt.setInt(2, start-1);
         pstmt.setInt(3, end);
         rs = pstmt.executeQuery();

         while(rs.next()) {            
            list.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                  rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                  rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
         }      
         rs.close();
         pstmt.close();
      } catch (SQLException e) {
         e.printStackTrace();
      } 
      return list;
   }
   
   /** 검색 포트폴리오의 수_타이틀(키워드)
    * select*/
   public int selectPortfolioCount(String title){
      int selectPortfolioAllCount = 0;
      String sql = "select count(*) from portfolio where portfolio_title like ?";
      try{
         PreparedStatement pstmt =conn.prepareStatement(sql);
         pstmt.setString(1, "%" + title + "%");
         ResultSet rs = pstmt.executeQuery();

         if(rs.next())
            selectPortfolioAllCount = rs.getInt(1);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return selectPortfolioAllCount;
   }   

   // 1011포트폴리오_검색 추가
      /**포트폴리오 검색_전체 */
      public ArrayList<PortfolioVO> selectAllPortfolioList(String portfolioTitle, int start, int end ){
         PreparedStatement pstmt;            
         ResultSet rs;
         ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
         try {
            String sql = "select distinct portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
                  + "from portfolio, member_info "
                  + "where (portfolio_title like ? or portfolio_text like ?) and "
                  + "member_info.member_id = portfolio.member_id "
                  + "order by upload_date desc limit ?,? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + portfolioTitle + "%");
            pstmt.setString(2, "%" + portfolioTitle + "%");
            pstmt.setInt(3, start-1);
            pstmt.setInt(4, end);
            rs = pstmt.executeQuery();

            while(rs.next()) {            
               list.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                     rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                     rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
            }      
            rs.close();
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         } 
         return list;
      }

      /** 검색 포트폴리오의 수_전체
       * select*/
      public int selectAllPortfolioCount(String title){
         int selectPortfolioAllCount = 0;
         String sql = "select count(*) from portfolio where portfolio_title like ? or portfolio_text like ?";
         try{
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
               selectPortfolioAllCount = rs.getInt(1);
         } catch (SQLException e) {
            e.printStackTrace();
         }
         return selectPortfolioAllCount;
      }   
      
      // 1011포트폴리오_검색 추가
      /**포트폴리오 검색_튜티명 */
      public ArrayList<PortfolioVO> selectTuteePortfolioList(String portfolioTitle, int start, int end ){
         PreparedStatement pstmt;            
         ResultSet rs;
         ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
         try {
            String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
                  + "from portfolio, member_info "
                  + "where name like ? and "
                  + "member_info.member_id = portfolio.member_id "
                  + "order by upload_date desc limit ?,? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + portfolioTitle + "%");
            pstmt.setInt(2, start-1);
            pstmt.setInt(3, end);
            rs = pstmt.executeQuery();

            while(rs.next()) {            
               list.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                     rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                     rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
            }      
            rs.close();
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         } 
         return list;
      }
      
      /** 검색 포트폴리오의 수_튜티명
       * select*/
      public int selectTuteePortfolioCount(String title){
         int selectPortfolioAllCount = 0;
         String sql = "select count(*) from portfolio, member_info where member_info.member_id = portfolio.member_id and name like ?";
         try{
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
               selectPortfolioAllCount = rs.getInt(1);
         } catch (SQLException e) {
            e.printStackTrace();
         }
         return selectPortfolioAllCount;
      }   
   
   //////////////////////////////////////////////////////
      // 좋아요, 조회수, 번호순....
      /**
       * 포트폴리오 목록 페이지에서 9개씩 포트폴리오를 조회
       * limit 시작 레코드 번호, 검색할 레코드의 개수
       * mysql에서 레코드 번호는 0부터 시작, jsp페이지에서 글은 1부터 시작
       * select_번호순*/
      public ArrayList<PortfolioVO>getPortfolio(int start, int end){
         ArrayList<PortfolioVO> portfolioList = new ArrayList();
         try{
            String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
                  + "from portfolio, member_info  "
                  + "where member_info.member_id = portfolio.member_id "
                  + "order by upload_date desc limit ?,?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, start-1);
            pstmt.setInt(2, end);

            ResultSet rs = pstmt.executeQuery();
            // 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
            while(rs.next())
               // 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
               portfolioList.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                     rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                     rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
            rs.close();
            pstmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         } // catch 
         return portfolioList;
      }
      
      /**
       * 포트폴리오 목록 페이지에서 9개씩 포트폴리오를 조회
       * limit 시작 레코드 번호, 검색할 레코드의 개수
       * mysql에서 레코드 번호는 0부터 시작, jsp페이지에서 글은 1부터 시작
       * select_조회수순*/
      public ArrayList<PortfolioVO>getHitsPortfolio(int start, int end){
         ArrayList<PortfolioVO> portfolioList = new ArrayList();
         try{
            String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
                  + "from portfolio, member_info  "
                  + "where member_info.member_id = portfolio.member_id "
                  + "order by portfolio.portfolio_count desc limit ?,?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, start-1);
            pstmt.setInt(2, end);

            ResultSet rs = pstmt.executeQuery();
            // 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
            while(rs.next())
               // 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
               portfolioList.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                     rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                     rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
            rs.close();
            pstmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         } // catch 
         return portfolioList;
      }
      
      /**
       * 포트폴리오 목록 페이지에서 9개씩 포트폴리오를 조회
       * limit 시작 레코드 번호, 검색할 레코드의 개수
       * mysql에서 레코드 번호는 0부터 시작, jsp페이지에서 글은 1부터 시작
       * select_최신순*/
      public ArrayList<PortfolioVO>getDatePortfolio(int start, int end){
         ArrayList<PortfolioVO> portfolioList = new ArrayList();
         try{
            String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
                  + "from portfolio, member_info  "
                  + "where member_info.member_id = portfolio.member_id "
                  + "order by portfolio.upload_date desc limit ?,?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, start-1);
            pstmt.setInt(2, end);

            ResultSet rs = pstmt.executeQuery();
            // 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
            while(rs.next())
               // 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
               portfolioList.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                     rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                     rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
            rs.close();
            pstmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         } // catch 
         return portfolioList;
      }
      
      /**
       * 포트폴리오 목록 페이지에서 9개씩 포트폴리오를 조회
       * limit 시작 레코드 번호, 검색할 레코드의 개수
       * mysql에서 레코드 번호는 0부터 시작, jsp페이지에서 글은 1부터 시작
       * select_좋아요순*/
      public ArrayList<PortfolioVO>getLikePortfolio(int start, int end){
         ArrayList<PortfolioVO> portfolioList = new ArrayList();
         try{
            String sql = "select portfolio.portfolio_id, portfolio.member_id, portfolio_title, portfolio_method, portfolio_url, portfolio_file, portfolio_text,portfolio_count, upload_date, portfolio_like_count "
                  + "from portfolio, member_info "
                  + "where member_info.member_id = portfolio.member_id "
                  + "order by portfolio.portfolio_like_count desc limit ?,?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, start-1);
            pstmt.setInt(2, end);

            ResultSet rs = pstmt.executeQuery();
            // 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
            while(rs.next())
               // 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
               portfolioList.add(new PortfolioVO(rs.getInt("portfolio_id"), rs.getString("member_id"), rs.getString("portfolio_title"), 
                     rs.getString("portfolio_method"), rs.getString("portfolio_url"),
                     rs.getString("portfolio_file"), rs.getString("portfolio_text"),rs.getString("upload_date"), rs.getInt("portfolio_count"), rs.getInt("portfolio_like_count")));
            rs.close();
            pstmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         } // catch 
         return portfolioList;
      }

}


