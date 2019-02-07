package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NoticeDAO {
	private Connection conn;
	public NoticeDAO(){
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
	
	/**게시글등록을 위한 게시글 조회*/
	public NoticeVO getNoticeRegisterInfo(String id){
		PreparedStatement pstmt;
		ResultSet rs;
		NoticeVO vo = null;

		try {
			String sql = "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date "
					+ "from notice_info "
					+ "where notice_writer = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();

			vo = new NoticeVO();
			while(rs.next()){
				vo.setNoticeId(rs.getInt("notice_info.notice_id"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeIp(rs.getString("notice_ip"));
				vo.setNoticeHits(rs.getInt("notice_hits"));
				vo.setNoticeDate(rs.getString("notice_date"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	/**게시글 등록*/
	public boolean registerNotice(String noticeTitle, String noticeContent, String noticeWriter, String noticeIp, String noticeDate) {
		boolean result = false;
		try{
			String sql ="INSERT INTO notice_info(notice_title, notice_content, notice_writer, notice_ip,notice_date) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeTitle);
			pstmt.setString(2, noticeContent);
			pstmt.setString(3, noticeWriter);
			pstmt.setString(4, noticeIp);
			pstmt.setString(5, noticeDate);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**게시글 멤버 등록*/
	public boolean registerNoticeMemberCateg(int noticeId, String noticeMem) {
		boolean result = false;
		try{
			
			String sql2 ="INSERT INTO notice_categ(notice_id, notice_categ) "
					+ "VALUES (?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, noticeId);
			pstmt2.setString(2, noticeMem);

			if(pstmt2.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**게시글 삭제*/
	public boolean deleteNotice(int noticeId){
		boolean result = false;
		try{
			String sql="delete from notice_info "
					+ "where notice_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	// 대상 삭제
	public boolean deleteNoticeMem(int noticeId){
		boolean result = false;
		try{
			String sql="delete from notice_categ "
					+ "where notice_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**게시글 수정*/
	public boolean updateNotice(String noticeTitle, String noticeContent, int noticeId){
		boolean result = false;
		try{
			String sql = "update notice_info "
					+ "set notice_title = ?, notice_content = ? "
					+"where notice_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeTitle);
			pstmt.setString(2, noticeContent);
			pstmt.setInt(3, noticeId);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	
	/**게시글리스트_번호순*/
	public ArrayList<NoticeVO>getNotice(int start, int end){
	ArrayList<NoticeVO> noticeList = new ArrayList();
	try{
		String sql = " select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
				+ "FROM notice_categ, notice_info "
				+ "where notice_info.notice_id = notice_categ.notice_id "
				+ "GROUP BY notice_id "
				+ "order by notice_info.notice_id asc limit ?,?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, start-1);
		pstmt.setInt(2, end);

		ResultSet rs = pstmt.executeQuery();
		// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
		while(rs.next())
			// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
			noticeList.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
					rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
					rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
		rs.close();
		pstmt.close();
	}catch(SQLException e){
		e.printStackTrace();
	} // catch 
	return noticeList;
}
	
	/**게시글리스트_최신순*/
	public ArrayList<NoticeVO>getNoticDate(int start, int end){
	ArrayList<NoticeVO> noticeList = new ArrayList();
	try{
		String sql = " select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
				+ "FROM notice_categ, notice_info "
				+ "where notice_info.notice_id = notice_categ.notice_id "
				+ "GROUP BY notice_id "
				+ "order by notice_info.notice_date desc limit ?,?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, start-1);
		pstmt.setInt(2, end);

		ResultSet rs = pstmt.executeQuery();
		// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
		while(rs.next())
			// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
			noticeList.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
					rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
					rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
		rs.close();
		pstmt.close();
	}catch(SQLException e){
		e.printStackTrace();
	} // catch 
	return noticeList;
}
	/**게시글리스트_조회수순*/
	public ArrayList<NoticeVO>getNoticHits(int start, int end){
	ArrayList<NoticeVO> noticeList = new ArrayList();
	try{
		String sql = " select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
				+ "FROM notice_categ, notice_info "
				+ "where notice_info.notice_id = notice_categ.notice_id "
				+ "GROUP BY notice_id "
				+ "order by notice_info.notice_hits desc limit ?,?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, start-1);
		pstmt.setInt(2, end);

		ResultSet rs = pstmt.executeQuery();
		// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
		while(rs.next())
			// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
			noticeList.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
					rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
					rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
		rs.close();
		pstmt.close();
	}catch(SQLException e){
		e.printStackTrace();
	} // catch 
	return noticeList;
}
	
	/**총 개시글 수*/
	public int noticeCount(){
		int noticeAllCount = 0;
		String sql = "select count(*) from notice_info";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				noticeAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeAllCount;
	}
	/**게시글 상세*/
	public NoticeVO noticeDetail(int noticeId){
		PreparedStatement pstmt;
		ResultSet rs;
		NoticeVO vo = null;
		try {
			String sql = "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_info, notice_categ "
					+ "where notice_info.notice_id = ? and "
					+ "notice_info.notice_id = notice_categ.notice_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,noticeId);
			rs = pstmt.executeQuery();

			vo = new NoticeVO();

			while(rs.next()){
				vo.setNoticeId(rs.getInt("notice_info.notice_id"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeIp(rs.getString("notice_ip"));
				vo.setNoticeHits(rs.getInt("notice_hits"));
				vo.setNoticeDate(rs.getString("notice_date").substring(0, 11));
				vo.setNoticeMem(rs.getString("notice_categ"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	/**게시글 수정을 위한 상세*/
	public NoticeVO noticeDetails(int noticeId){
		PreparedStatement pstmt;
		ResultSet rs;
		NoticeVO vo = null;
		try {
			String sql = "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_info, notice_categ "
					+ "where notice_info.notice_id = ? and "
					+ "notice_info.notice_id = notice_categ.notice_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,noticeId);
			rs = pstmt.executeQuery();

			vo = new NoticeVO();

			while(rs.next()){
				vo.setNoticeId(rs.getInt("notice_info.notice_id"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeIp(rs.getString("notice_ip"));
				vo.setNoticeHits(rs.getInt("notice_hits"));
				vo.setNoticeDate(rs.getString("notice_date").substring(0, 11));
				vo.setNoticeMem(rs.getString("notice_categ"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	/**게시글 상세_이전*/
	public NoticeVO noticeDetailPre(int noticeId){
		PreparedStatement pstmt;
		ResultSet rs;
		NoticeVO vo = null;
		try {
			String sql = "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_info, notice_categ "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "notice_info.notice_id = (select max(notice_info.notice_id) from notice_info, notice_categ where notice_info.notice_id = notice_categ.notice_id and notice_info.notice_id < ?)"
					+ "group by notice_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,noticeId);
			rs = pstmt.executeQuery();

			vo = new NoticeVO();

			while(rs.next()){
				vo.setNoticeId(rs.getInt("notice_info.notice_id"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeIp(rs.getString("notice_ip"));
				vo.setNoticeHits(rs.getInt("notice_hits"));
				vo.setNoticeDate(rs.getString("notice_date"));
				vo.setNoticeMem(rs.getString("notice_categ"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	
	/**게시글 상세_다음*/
	public NoticeVO noticeDetailafter(int noticeId){
		PreparedStatement pstmt;
		ResultSet rs;
		NoticeVO vo = null;
		try {
			String sql = "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ  "
					+ "from notice_info, notice_categ "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "notice_info.notice_id = (select min(notice_info.notice_id) from notice_info, notice_categ where notice_info.notice_id = notice_categ.notice_id and notice_info.notice_id > ?)"
					+ "group by notice_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,noticeId);
			rs = pstmt.executeQuery();

			vo = new NoticeVO();

			while(rs.next()){
				vo.setNoticeId(rs.getInt("notice_info.notice_id"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeIp(rs.getString("notice_ip"));
				vo.setNoticeHits(rs.getInt("notice_hits"));
				vo.setNoticeDate(rs.getString("notice_date"));
				vo.setNoticeMem(rs.getString("notice_categ"));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	/**게시글 대상*/

	public ArrayList<NoticeMemVO> getNoticeMem(int noticeNum){
		//		String images = null;
		ArrayList<NoticeMemVO> noticeMem = new ArrayList();
		try{
			String sql =  "select notice_info.notice_id, notice_categ.notice_categ "
					+ "from notice_info, notice_categ "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "notice_info.notice_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//		for(int i = 1; i< rs.length(); i++){
				noticeMem.add(new NoticeMemVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_categ")));
				//		images = rs.getString("image");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return noticeMem;
	}
	/**게시글 검색리스트_대상*/
	public ArrayList<NoticeVO> selectNoticeMember(String member, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			String sql =  "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_categ, notice_info "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "notice_categ like ? "
					+ "group by notice_id "
					+ "order by notice_info.notice_id desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + member + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
						rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
						rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
			}   	
		
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	/**게시글 검색리스트_타이틀*/
	public ArrayList<NoticeVO> selectTitleList(String title, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			String sql =  "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_categ, notice_info "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "notice_title like ? "
					+ "group by notice_id "
					+ "order by notice_info.notice_id desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
						rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
						rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
			}   	
		
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**게시글 검색리스트_내용*/
	public ArrayList<NoticeVO> selectContentList(String content, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			String sql =  "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_categ, notice_info "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "notice_content like ? "
					+ "group by notice_id "
					+ "order by notice_info.notice_id desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + content + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
						rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
						rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
			}   	
		
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**게시글 검색리스트_타이틀+내용*/
	public ArrayList<NoticeVO> selectTContentList(String tcontent, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			String sql =  "select notice_info.notice_id, notice_title, notice_content, notice_writer, notice_ip, notice_hits, notice_date, group_concat(notice_categ) as notice_categ "
					+ "from notice_categ, notice_info "
					+ "where notice_info.notice_id = notice_categ.notice_id and "
					+ "(notice_content like ? or "
					+ "notice_title like ?) "
					+ "group by notice_id "
					+ "order by notice_info.notice_id desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + tcontent + "%");
			pstmt.setString(2, "%" + tcontent + "%");
			pstmt.setInt(3, start-1);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				list.add(new NoticeVO(rs.getInt("notice_info.notice_id"), rs.getString("notice_title"), rs.getString("notice_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
						rs.getString("notice_writer"), rs.getString("notice_ip"), rs.getInt("notice_hits"),
						rs.getString("notice_date").substring(0, 11), rs.getString("notice_categ")));
			}   	
		
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**검색게시글_대상
	 * select*/
	public int selectMemCount(String categMem){
		int selectNoticeAllCount = 0;
		String sql = "select count(*) "
				+ "from notice_info, notice_categ "
				+ "where notice_info.notice_id = notice_categ.notice_id and "
				+ "notice_categ like ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + categMem + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectNoticeAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectNoticeAllCount;
	}
	
	/**검색게시글_타이틀
	 * select*/
	public int selectNoticeCount(String title){
		int selectNoticeAllCount = 0;
		String sql = "select count(*) from notice_info where notice_title like ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectNoticeAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectNoticeAllCount;
	}
	
	/**검색게시글_내용
	 * select*/
	public int selectContentCount(String categContent){
		int selectNoticeAllCount = 0;
		String sql = "select count(*) from notice_info where notice_content like ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + categContent + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectNoticeAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectNoticeAllCount;
	}
	
	/**검색게시글_제목+내용
	 * select*/
	public int selectTContentCount(String categTContent){
		int selectNoticeAllCount = 0;
		String sql = "select count(*) from notice_info where (notice_content like ? or notice_title like ?)";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + categTContent + "%");
			pstmt.setString(2, "%" + categTContent + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectNoticeAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectNoticeAllCount;
	}
	

	/**조회수 증가*/
	public boolean noticeCountIncrease(int noticeId) {
		boolean result = false;
		try{
			String sql = "update notice_info "
					+ "set notice_hits = notice_hits+1 "
					+"where notice_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeId);


			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
}
