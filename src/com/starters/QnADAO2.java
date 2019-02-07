package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QnADAO2 {
	private Connection conn;
	public QnADAO2(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static QnADAO2 instance = new QnADAO2();
	public static QnADAO2 getInstance() {
		return instance;
	}
	
	/**qna등록*/
	public boolean registerQna(String qnaWriterId, String qnaTitle, String qnaContent, String qanPasswd) {
		boolean result = false;
		try{
			String sql ="INSERT INTO qna_info(qna_writer_id, qna_content,qna_title , qna_passwd, qna_ref, qna_re_step, qna_re_level)  "
					+ "VALUES (?, ?, ?, IFNULL(?, ''), IFNULL((SELECT MAX(a.qna_ref)+1  as 'qna_ref' FROM qna_info a), 0), 0, 0)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaWriterId);
			pstmt.setString(2, qnaContent);
			pstmt.setString(3, qnaTitle);
			pstmt.setString(4, qanPasswd);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**qna수정*/
	public boolean updateQna(int qnaId, String qnaTitle, String qnaContent) {
		boolean result = false;
		try{
			String sql = "update qna_info "
					+ "set qna_title = ?, qna_content = ? "
					+"where qna_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qnaTitle);
			pstmt.setString(2, qnaContent);
			pstmt.setInt(3, qnaId);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	/**비밀번호 확인*/
	public boolean passwdCheck(int qnaId, String Passwd){
		boolean result = false;
		String pw = "";
		try{
			String sql1= "select qna_passwd from qna_info where qna_id = ?"; 
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, qnaId);
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()){
				pw = rs.getString("qna_passwd");
				if(pw.equals(Passwd)){
					result = true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**qna삭제*/
	public boolean deleteQna(int qnaId){
		boolean result = false;
		try{
			String sql1= "DELETE FROM qna_info "
					+ "WHERE qna_id=?"; 
//			String sql1= "UPDATE qna_info SET qna_available = 0 "
//					+ "WHERE qna_id=?"; 
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, qnaId);
			pstmt1.executeUpdate();
			
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	/**qna갯수*/
	public int qnaCount(){
		int qnaAllCount = 0;
		String sql = "select count(*) from qna_info";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				qnaAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaAllCount;
	}
	/**qna조회수*/
	public boolean qnaCountIncrease(QnAVO2 vo) {
		return qnaCountIncrease(vo.getQnaId());
	}

	public boolean qnaCountIncrease(int qnaId) {
		boolean result = false;
		try{
			String sql = "update qna_info "
					+ "set qna_hits = qna_hits+1 "
					+"where qna_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaId);


			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**qna리스트*/
	public ArrayList<QnAVO2> getQnAList(int start, int end){ 
		ArrayList<QnAVO2> qnaList = new ArrayList();
		try{
			String sql = "select qna_id, qna_title, qna_content, qna_writer_id, qna_passwd, qna_hits, qna_ref, qna_re_step, qna_re_level, qna_date "
					+ "from qna_info "
					+ "order by qna_ref desc, qna_re_step asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				qnaList.add(new QnAVO2(rs.getInt("qna_id"), rs.getString("qna_writer_id"), rs.getString("qna_title"), rs.getString("qna_content"), rs.getString("qna_passwd"), rs.getInt("qna_hits"), rs.getInt("qna_ref"), rs.getInt("qna_re_step"), rs.getInt("qna_re_level"), rs.getString("qna_date").substring(0, 11)));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return qnaList;
	}
	/**qna검색 갯수*/
	public int qnaSearchCount(String qnaWriterId){
		int qnaSearchAllCount = 0;
		String sql = "select count(*) from qna_info where qna_writer_id like ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + qnaWriterId + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				qnaSearchAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaSearchAllCount;
	}
	/**qna검색*/	
	public ArrayList<QnAVO2> selectQnAList(String qnaWriterId, int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<QnAVO2> qnaList = new ArrayList<QnAVO2>();
		try {
			String sql = "select qna_id, qna_title, qna_content, qna_writer_id, qna_passwd, qna_hits, qna_ref, qna_re_step, qna_re_level, qna_date "
					+ "from qna_info "
					+ "where qna_writer_id like ? "
					+ "order by qna_id asc limit ?,? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + qnaWriterId + "%");
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while(rs.next()) {				
				qnaList.add(new QnAVO2(rs.getInt("qna_id"), rs.getString("qna_writer_id"), rs.getString("qna_title"), rs.getString("qna_content"), rs.getString("qna_passwd"), rs.getInt("qna_hits"), rs.getInt("qna_ref"), rs.getInt("qna_re_step"), rs.getInt("qna_re_level"), rs.getString("qna_date").substring(0, 11)));
			}   	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return qnaList;
	}
	/**qna상세*/
	public QnAVO2 qnaDetail(int qnaId){
		PreparedStatement pstmt;
		ResultSet rs;
		QnAVO2 vo = null;
		try {
			String sql = "select qna_id, qna_title, qna_content, qna_writer_id, qna_passwd, qna_hits, qna_ref, qna_re_step, qna_re_level, qna_date "
					+ "from qna_info "
					+ "where qna_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,qnaId);
			rs = pstmt.executeQuery();

			vo = new QnAVO2();

			while(rs.next()){
				vo.setQnaId(rs.getInt("qna_id"));
				vo.setQnaTitle(rs.getString("qna_title").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setQnaContent(rs.getString("qna_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setQnaWriterId(rs.getString("qna_writer_id"));
				vo.setQanPasswd(rs.getString("qna_passwd"));
				vo.setQnaHits(rs.getInt("qna_hits"));
				vo.setRef(rs.getInt("qna_ref"));
				vo.setReStep(rs.getInt("qna_re_step"));
				vo.setReLevel(rs.getInt("qna_re_level"));
//				vo.setAvailable(rs.getInt("qna_available"));
				vo.setQnaDate(rs.getString("qna_date").substring(0, 11));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	// 1027
	// 수정을 위한 게시글 조회
	public QnAVO2 qnaDetails(int qnaId){
		PreparedStatement pstmt;
		ResultSet rs;
		QnAVO2 vo = null;
		try {
			String sql = "select qna_id, qna_title, qna_content, qna_writer_id, qna_passwd, qna_hits, qna_ref, qna_re_step, qna_re_level, qna_date "
					+ "from qna_info "
					+ "where qna_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,qnaId);
			rs = pstmt.executeQuery();

			vo = new QnAVO2();

			while(rs.next()){
				vo.setQnaId(rs.getInt("qna_id"));
				vo.setQnaTitle(rs.getString("qna_title"));
				vo.setQnaContent(rs.getString("qna_content"));
				vo.setQnaWriterId(rs.getString("qna_writer_id"));
				vo.setQanPasswd(rs.getString("qna_passwd"));
				vo.setQnaHits(rs.getInt("qna_hits"));
				vo.setRef(rs.getInt("qna_ref"));
				vo.setReStep(rs.getInt("qna_re_step"));
				vo.setReLevel(rs.getInt("qna_re_level"));
//				vo.setAvailable(rs.getInt("qna_available"));
				vo.setQnaDate(rs.getString("qna_date").substring(0, 11));

			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	
	/**글쓴이*/
	public QnAVO2 qnaWriter(int qnaId){
		PreparedStatement pstmt;
		ResultSet rs;
		QnAVO2 vo = null;
		try {
			String sql = "select qna_id, qna_title, qna_content, qna_writer_id, qna_passwd, qna_hits, qna_ref, qna_re_step, qna_re_level, qna_date "
					+ "from qna_info "
					+ "where qna_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,qnaId);
			rs = pstmt.executeQuery();

			vo = new QnAVO2();

			while(rs.next()){
				vo.setQnaId(rs.getInt("qna_id"));
				vo.setQnaTitle(rs.getString("qna_title").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setQnaContent(rs.getString("qna_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setQnaWriterId(rs.getString("qna_writer_id"));
				vo.setQanPasswd(rs.getString("qna_passwd"));
				vo.setQnaHits(rs.getInt("qna_hits"));
				vo.setRef(rs.getInt("qna_ref"));
				vo.setReStep(rs.getInt("qna_re_step"));
				vo.setReLevel(rs.getInt("qna_re_level"));
//				vo.setAvailable(rs.getInt("qna_available"));
				vo.setQnaDate(rs.getString("qna_date").substring(0, 11));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}
	
	
	///////////////////////////////////////////////////////////
	/**qna 답변 리스트*/
	public ArrayList<QnACommentVO> getQnACommentList(int qnaId){ 
		ArrayList<QnACommentVO> getQnACommentList = new ArrayList();
		try{
			String sql = "select qna_id, comment_id, comment_writer, comment_content, comment_date, comment_ref, comment_step, comment_level "
					+ "from qna_comment "
					+ "where qna_id = ? "
					+ "order by comment_ref, comment_step asc";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qnaId);

			ResultSet rs = pstmt.executeQuery();
			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				getQnACommentList.add(
						new QnACommentVO(
								rs.getInt("comment_id"), 
								rs.getInt("qna_id"),
								rs.getString("comment_writer"), 
								rs.getString("comment_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
								rs.getString("comment_date").substring(0, 11), 
								rs.getInt("comment_ref"),
								rs.getInt("comment_step"),
								rs.getInt("comment_level")));
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return getQnACommentList;
	}
	/**qna답변*/
	// [수정]
	public boolean qnaReply(int qnaId, String commentWriterId, String commentContent) {
		boolean result = false;
		try{
			String sql ="INSERT INTO qna_comment(qna_id, comment_writer, comment_content, comment_ref, comment_step, comment_level)  "
					+ "VALUES (?,?,?,IFNULL((SELECT MAX(a.comment_ref)+1  as 'comment_ref' FROM qna_comment a), 0),0, 0)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaId);
			pstmt.setString(2, commentWriterId);
			pstmt.setString(3, commentContent);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	/**qna대댓글 등록*/
	// [수정]
	public boolean commentReplyInsert(int qnaId, String commentWriterId, String commentContent, QnACommentVO parent) {
		boolean result = false;
		try{
			String sql ="INSERT INTO qna_comment(qna_id, comment_writer, comment_content, comment_ref, comment_step, comment_level)  "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaId);
			pstmt.setString(2, commentWriterId);
			pstmt.setString(3, commentContent);
			pstmt.setInt(4, parent.getRef());
			pstmt.setInt(5, parent.getReStep()+1);
			pstmt.setInt(6, parent.getReLevel()+1);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	
	//[기존]
//	public boolean qnaReply(String qnaWriterId, String qnaTitle, String qnaContent, String qanPasswd, QnAVO2 parent) {
//		boolean result = false;
//		try{
//			String sql ="INSERT INTO qna_info(qna_writer_id, qna_content,qna_title , qna_passwd, qna_ref, qna_re_step, qna_re_level, qna_available)  "
//					+ "VALUES (?, ?, ?, IFNULL(?, -1000), ?, ?, ?, 1)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, qnaWriterId);
//			pstmt.setString(2, qnaTitle);
//			pstmt.setString(3, qnaContent);
//			pstmt.setString(4, qanPasswd);
//			pstmt.setInt(5, parent.getRef());
//			pstmt.setInt(6, parent.getReStep()+1);
//			pstmt.setInt(7, parent.getReLevel()+1);
//			if(pstmt.executeUpdate() == 1){
//				result = true;
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//
//		}
//		return result;
//	}
//	
	/**qna 답변수정*/
//	public boolean updateQnaReply(QnAVO parent) {
//		boolean result = false;
//		try{
//			String sql = "update qna_info "
//					+ "set qna_re_step = qna_re_step+1 "
//					+"where qna_ref = ? and qna_re_step > ? ";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setInt(1, parent.getRef());
//			pstmt.setInt(2, parent.getReStep());
//			if(pstmt.executeUpdate() == 1){
//				result = true;
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//
//		}
//		return result;
//	}
	/**답글 삭제*/
	public boolean DeleteQnaReply(int commentId) {
		boolean result = false;
		try{
			String sql = "delete from qna_comment "
					+ "where comment_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentId);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	
	/**등록을 위한 게시글 조회*/
	public QnACommentVO getQnACommentInfo(int qnaId){
		PreparedStatement pstmt;
		ResultSet rs;
		QnACommentVO vo = null;

		try {
			String sql = "select qna_id, comment_id, comment_writer, comment_content, comment_date, comment_ref, comment_step, comment_level "
					+ "from qna_comment "
					+ "where qna_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,qnaId);
			rs = pstmt.executeQuery();

			vo = new QnACommentVO();
			while(rs.next()){
				vo.setCommentId(rs.getInt("comment_id"));
				vo.setQnaId(rs.getInt("qna_id"));
				vo.setCommentWriterId(rs.getString("comment_writer"));
				vo.setCommentContent(rs.getString("comment_content"));
				vo.setCommentDate(rs.getString("comment_date"));
				vo.setRef(rs.getInt("comment_ref"));
				vo.setReStep(rs.getInt("comment_step"));
				vo.setReLevel(rs.getInt("comment_level"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	/**수정을 위한 게시글 조회*/
	public QnACommentVO getCommentInfo(int commentId){
		PreparedStatement pstmt;
		ResultSet rs;
		QnACommentVO vo = null;

		try {
			String sql = "select qna_id, comment_id, comment_writer, comment_content, comment_date, comment_ref, comment_step, comment_level "
					+ "from qna_comment "
					+ "where comment_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,commentId);
			rs = pstmt.executeQuery();

			vo = new QnACommentVO();
			while(rs.next()){
				vo.setCommentId(rs.getInt("comment_id"));
				vo.setQnaId(rs.getInt("qna_id"));
				vo.setCommentWriterId(rs.getString("comment_writer"));
				vo.setCommentContent(rs.getString("comment_content"));
				vo.setCommentDate(rs.getString("comment_date"));
				vo.setRef(rs.getInt("comment_ref"));
				vo.setReStep(rs.getInt("comment_step"));
				vo.setReLevel(rs.getInt("comment_level"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**답글 수정*/
	public boolean UpdateCommentReply(QnACommentVO parent) {
		boolean result = false;
		try{
			String sql = "update qna_comment "
					+ "set comment_step = comment_step+1 "
					+"where comment_ref = ? and comment_step > ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parent.getRef());
			pstmt.setInt(2, parent.getReStep());
			
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}
	
	/**대댓글 입력*/
	// 댓글 등록
//    public boolean commentReplyInsert(int commentNum, int qnaNum, String commentUserId, String commentContent)
//    {
//        boolean result = false;
//        
//        try {
//        	String sql ="INSERT INTO qna_comment(qna_id, comment_writer, comment_content, comment_parent)  "
//					+ "VALUES (?,?,?,?)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, qnaNum);
//			pstmt.setString(2, commentUserId);
//			pstmt.setString(3, commentContent);
//			pstmt.setInt(4, commentNum);
//			if(pstmt.executeUpdate() == 1){
//				result = true;
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//
//		}
//		return result;
//	}
//    
	/**댓글수정*/
	public boolean updateComment(int commentId, String commentContent) {
		boolean result = false;
		try{
			String sql = "update qna_comment "
					+ "set comment_content = ? "
					+"where comment_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commentContent);
			pstmt.setInt(2, commentId);
			
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}

//    /**대댓글 목록*/
//    public ArrayList<QnACommentVO> getQnACommentReplyList(int qnaId){ 
//		ArrayList<QnACommentVO> getQnACommentList = new ArrayList();
//		try{
//			String sql = "SELECT @R AS _ID, comment_id, qna_id,comment_writer, comment_date,comment_content, comment_parent "
//					+ ",(SELECT @R:= comment_parent FROM qna_comment WHERE comment_id = _ID) AS comment_parent "
//					+ ", @L:=@L+1 AS LEVEL "
//					+ "FROM ( SELECT @R:= ?) VARS, qna_comment H, (SELECT @L:=0) LV "
//					+ "WHERE @R <> 0;";
//			PreparedStatement pstmt=conn.prepareStatement(sql);
//			pstmt.setInt(1, qnaId);
//
//			ResultSet rs = pstmt.executeQuery();
//			// 레코드 여러개가 나온다. --> 조건문을 while문을 사용한다. 여러개지만 몇개 들어오는지 알 수가 없기 때문이다.
//			while(rs.next())
//				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
//				getQnACommentList.add(
//						new QnACommentVO(
//								rs.getInt("comment_id"), 
//								rs.getInt("qna_id"),
//								rs.getString("comment_writer"), 
//								rs.getString("comment_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"), 
//								rs.getString("comment_date").substring(0, 11), 
//								rs.getInt("comment_parent"), 
//								rs.getInt("_ID")));
//			rs.close();
//			pstmt.close();
//		}catch(SQLException e){
//			e.printStackTrace();
//		} // catch 
//		return getQnACommentList;
//	}
	
	
}
