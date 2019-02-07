package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ReviewDAO {
	private Connection conn;
	public ReviewDAO(){
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


	/**등록된 전체 튜터링의 수
	 * select*/
	public int reviewCount(){
		int reviewAllCount = 0;
		String sql = "select count(*) from review";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
				reviewAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewAllCount;
	}


	/**등록된 특정 검색 후기게시판 select*/
	public int selectReviewCount(String reviewTitle){
		int selectReivewAllCount = 0;
		String sql = "select count(*) from review where review_title like ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%" + reviewTitle + "%");
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				selectReivewAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectReivewAllCount;
	}

	
	/**후기게시판 등록 insert*/
	public boolean registerReview(ReviewVO vo) {
		return registerReview(vo.getTutoringApplyId(),vo.getReviewTitle(),vo.getReviewContent());
	}

	public boolean registerReview(int tutoringApplyId, String reviewTitle, String reviewContent) {
		boolean result = false;
		try{
			String sql ="INSERT INTO review(tutoring_apply_id, review_title,review_content)  "
					+ "VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringApplyId);
			pstmt.setString(2, reviewTitle);
			pstmt.setString(3, reviewContent);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();

		}
		return result;
	}

	//1017추가
	/**본인이 듣는 튜터링명, 튜터명 출력*/
	public int selectTutoringTitleCount(String tuteeId){
		PreparedStatement pstmt;            
		ResultSet rs;
		int titleList = 0;
		try {
			String sql = "select tutoring_apply_info.tutoring_apply_id, tutoring_apply_info.tutoring_id, title, tutoring_apply_info.tutee_id, apply_date, payment_info, tutoring_apply_info.price "
					+ "from tutoring_info,member_info, tutoring_apply_info "
					+ "left outer join review "
					+ "on(review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id) "
					+ "where member_info.member_id = ? and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id and "
					+ "review.tutoring_apply_id is null ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			rs = pstmt.executeQuery();

			while(rs.next()) {            
				titleList = rs.getInt(1);
				}      
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return titleList;
	}

	/**본인이 듣는 튜터링명, 튜터명 출력*/
	public ArrayList<TutoringBuyVO> selectTutoringTitle(String tuteeId){
		PreparedStatement pstmt;            
		ResultSet rs;
		ArrayList<TutoringBuyVO> titleList = new ArrayList<TutoringBuyVO>();
		try {
			String sql = "select tutoring_apply_info.tutoring_apply_id, tutoring_apply_info.tutoring_id, title, tutoring_apply_info.tutee_id, apply_date, payment_info, tutoring_apply_info.price "
					+ "from tutoring_info,member_info, tutoring_apply_info "
					+ "left outer join review "
					+ "on(review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id) "
					+ "where member_info.member_id = ? and "
					+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id and "
					+ "review.tutoring_apply_id is null ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			rs = pstmt.executeQuery();

			while(rs.next()) {            
				titleList.add(new TutoringBuyVO(rs.getInt("tutoring_apply_id"),rs.getInt("tutoring_id"),rs.getString("tutee_id"),rs.getString("title"),rs.getString("apply_date"),rs.getString("payment_info"),rs.getInt("price")));
			}      
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return titleList;
	}
	/**글쓴이*/
	public ReviewVO reviewWriter(int reviewId){
		PreparedStatement pstmt;
		ResultSet rs;
		ReviewVO vo = null;
		try {
			String sql = "select review_id, review.tutoring_apply_id, review_title, review_content, review_count, review_date "
					+ "from review, tutoring_apply_info, member_info "
					+ "where review_id = ?  and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id and "
					+ "tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,reviewId);
			rs = pstmt.executeQuery();
			vo = new ReviewVO();

			while(rs.next()){
				vo.setReviewId(rs.getInt("review_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setReviewTitle(rs.getString("review_title"));
				vo.setReviewContent(rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setReviewCount(rs.getInt("review_count"));
				vo.setReviewDate(rs.getString("review_date").substring(0, 11));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**특정 후기게시판 상세 내용 보이기*/
	public ReviewVO reviewDetail(int reviewId){
		PreparedStatement pstmt;
		ResultSet rs;
		ReviewVO vo = null;
		try {
			String sql = "select review_id, tutoring_apply_id, review_title, review_content, review_count, review_date "
					+ "from review	"
					+ "where review_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,reviewId);
			rs = pstmt.executeQuery();
			vo = new ReviewVO();

			while(rs.next()){
				vo.setReviewId(rs.getInt("review_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setReviewTitle(rs.getString("review_title"));
				vo.setReviewContent(rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setReviewCount(rs.getInt("review_count"));
				vo.setReviewDate(rs.getString("review_date").substring(0, 11));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**후기게시판 삭제 삭제가 되었으면 true, 
	 * 삭제 못했으면 false 
	 * Q) 이미 로그인한 상태에서 후기게시판 삭제한다면 id는 필요가 없다.
	 * 후기게시판 일련번호가 존재하고 그 번호는 유일하기 때문에 후기게시판번호로 삭제가능하다고 생각
	 * delete
	 */
	public boolean deleteReview(int reviewId){
		boolean result = false;
		try{
			String sql="delete from review "
					+ "where review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}


	public int reviewCountIncrease(int reviewId) {
		int result = 0;
		//		ResultSet rs = null;
		try{
			String sql = "update review "
					+ "set review_count = review_count+1 "
					+"where review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);


			if(pstmt.executeUpdate() == 1){
				result = 1;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}


	// 이거하면 한번에 바귐
	//	/**후기게시판 목록보이기(9개씩)*/
	public ReviewVO reviewDetailCount(int reviewId){ 
		PreparedStatement pstmt;
		ResultSet rs;
		ReviewVO vo = null;
		try {
			String sql = "select review_id, review.tutoring_apply_id, review_title, review_content, review_count, review_date "
					+ "from review, tutoring_apply_info, member_info "
					+ "where review_id = ?  and "
					+ "member_info.member_id = tutoring_apply_info.tutee_id and "
					+ "tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,reviewId);
			rs = pstmt.executeQuery();
			vo = new ReviewVO();

			while(rs.next()){
				vo.setReviewId(rs.getInt("review_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setReviewTitle(rs.getString("review_title"));
				vo.setReviewContent(rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setReviewCount(rs.getInt("review_count"));
				vo.setReviewDate(rs.getString("review_date").substring(0, 11));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**후기게시판 수정*/
	public boolean modifyReview(int tutoringApplyId, String reviewTitle, String reviewContent, int reviewId){
		boolean result = false;
		try{
			String sql="update review "
					+ "set tutoring_apply_id=?, review_title=?, review_content=?"
					+ "where review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringApplyId);
			pstmt.setString(2, reviewTitle);
			pstmt.setString(3, reviewContent);
			pstmt.setInt(4, reviewId);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	/**이미지 추가1*/	
	public boolean addImage(int reviewId, String image){
		boolean result = false;
		try{
			String sql="insert into review_image(review_id, image) "
					+ "values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			pstmt.setString(2, image);

			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**이미지 추가2*/	
	public ReviewVO getReviewList(String memberId){
		PreparedStatement pstmt;
		ResultSet rs;
		ReviewVO vo = null;
		try {
			String sql = "select review.review_id, review.tutoring_apply_id, review_title, review_content, review_count,review_date "
					+ "from review, tutoring_apply_info, member_info "
					+ "where member_id = ? and "
					+ "review.tutoring_apply_id = tutoring_apply_info.tutoring_apply_id and "
					+ "tutoring_apply_info.tutee_id = member_info.member_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberId);
			rs = pstmt.executeQuery();
			vo = new ReviewVO();

			while(rs.next()){
				vo.setReviewId(rs.getInt("review_id"));
				vo.setTutoringApplyId(rs.getInt("tutoring_apply_id"));
				vo.setReviewTitle(rs.getString("review_title"));
				vo.setReviewContent(rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				vo.setReviewCount(rs.getInt("review_count"));
				vo.setReviewDate(rs.getString("review_date"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**이미지 불러오기*/
	public ArrayList<IntImageVO> getReviewImage(int reviewId){
		ArrayList<IntImageVO> images = new ArrayList<IntImageVO>();
		try {
			String sql = "select review.review_id, review_image.image "
					+ "from review, review_image "
					+ "where review.review_id = review_image.review_id and "
					+ "review.review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {            
				images.add(new IntImageVO(rs.getInt("review_id"),rs.getString("image")));
			}      
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return images;
	}

	/**좋아요 하기*/
	public boolean reviewLike(int reviewId,String userId){
		boolean result = false;
		try{
			String sql = "INSERT INTO review_like_report(review_id, userId, review_like) "
					//					+ "VALUES (?, ?, (SELECT NVL(MAX(review_id),0)+1 as review_like from review_like_report where review_id = ?))";
					+ "SELECT ?, ?, IFNULL(MAX(review_like), 0)+1 as 'review_like_count'  FROM review_like_report WHERE review_id = ? ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, reviewId);

			if(pstmt.executeUpdate()==1)
				result = true;

		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	/**좋아요 삭제*/
	public boolean deleteReviewLike(int reviewId, String userId){
		boolean result = false;
		try{
			String sql="delete from review_like_report "
					+ "where review_id = ? and userId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	/**좋아요*/
	public int reviewLikeIncrease(int reviewId) {
		int result = 0;
		try{
			String sql = "update review "
					+ "set review_like_count = review_like_count + 1 "
					+"where review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			//			pstmt.setString(2, userId);
			if(pstmt.executeUpdate() == 1){
				result = 1;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	/**좋아요*/
	public int reviewUnLikeIncrease(int reviewId) {
		int result = 0;
		try{
			String sql = "update review "
					+ "set review_like_count = review_like_count-+ 1 "
					+"where review_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			//			pstmt.setString(2, userId);
			if(pstmt.executeUpdate() == 1){
				result = 1;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	/**전체 좋아요 개수*/
	public int selectReviewLikeCount(int reviewId){
		int selectReviewLikeCount = 0;
		String sql = "select count(review_like) from review_like_report  "
				+ "where review_id = ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("like :" + reviewId);

			if(rs.next())
				selectReviewLikeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectReviewLikeCount;
	}	
	/**내가 한 좋아요*/
	public int reviewLikeExist(int reviewId, String memberId){
		int review = 0;
		try{
			String sql = "select count(*) from review_like_report where review_id = ? and userId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			pstmt.setString(2, memberId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				review = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return review;
	}
	/**후기게시판 목록*/
//	public ArrayList<ReviewLikeVO> getReviewLikeListUser(String userId,int start, int end){
//		ArrayList<ReviewLikeVO> getReviewLikeList = new ArrayList<ReviewLikeVO>();
//		try{
//			String sql = "select review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  title, tutor_id "
//					+ ",review_count,review_date, count(review_like_report.review_id) as 'review_like_counts', review_like_count  "
//					+ "from review  "
//					+ "left outer join review_like_report  "
//					+ "on review.review_id = review_like_report.review_id and review_like_report.userId = ? "
//					+ ", tutoring_apply_info "
//					+ ", member_info  "
//					+ ",tutoring_info "
//					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id  "
//					+ "and tutoring_apply_info.tutee_id = member_info.member_id  "
//					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
//					+ "group by review.review_id  "
//					+ "order by review.review_id asc limit ?,? ";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, userId);
//			pstmt.setInt(2, start-1);
//			pstmt.setInt(3, end);
//			ResultSet rs = pstmt.executeQuery();
//
//			while(rs.next()){
//				getReviewLikeList.add(new ReviewLikeVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
//						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"), rs.getInt("review_like_counts")));
//			}
//
//			rs.close();
//			pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return getReviewLikeList;
//	}	
	
	// 뷰 꼭 해죠야댐
//	create view tutoringList
//	as select tutoring_info.tutoring_id, tutor_id, name, title, subtitle, start_date, end_date, career, tutoring_info.price, contents, count 
//	from tutoring_info, member_info, tutoring_apply_info 
//	where member_info.member_id = tutoring_info.tutor_id and
//	tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id
	public ArrayList<ReviewLikeVO2> getReviewLikeListUser(String userId,int start, int end){
		ArrayList<ReviewLikeVO2> getReviewLikeList = new ArrayList<ReviewLikeVO2>();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, count(review_like_report.review_id) as 'review_like_counts', review_like_count  "
					+ "from review  "
					+ "left outer join review_like_report  "
					+ "on review.review_id = review_like_report.review_id and review_like_report.userId = ? "
					+ ", tutoring_apply_info "
					+ ", member_info  "
					+ ",tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id  "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id  "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_date desc limit ?,? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				getReviewLikeList.add(new ReviewLikeVO2(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"), rs.getInt("review_like_counts"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getReviewLikeList;
	}	


	/**후기게시판 검색*/
	public ArrayList<ReviewLikeVO2> selectReviewList(String reviewTitle, String userId,int start, int end ){
		PreparedStatement pstmt;				
		ResultSet rs;
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		try {
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
					+ ",review_count,review_date, count(review_like_report.review_id) as 'review_like_counts', review_like_count  "
					+ "from review "
					+ "left outer join review_like_report "
					+ "on review.review_id = review_like_report.review_id and review_like_report.userId = ? "
					+ ", tutoring_apply_info "
					+ ", member_info "
					+ ",tutoring_info, tutoringList "
					+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id "
					+ "and tutoring_apply_info.tutee_id = member_info.member_id "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
					+ "and review_title like ? or review_content = ?  "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "order by review.review_date desc limit ?,? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, "%" + reviewTitle + "%");
			pstmt.setString(3, "%" + reviewTitle + "%");
			pstmt.setInt(4, start-1);
			pstmt.setInt(5, end);
			rs = pstmt.executeQuery();

			while(rs.next()){
				list.add(new ReviewLikeVO2(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutoring_info.tutor_id")
						,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"), rs.getInt("review_like_counts"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			} 	
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	/**베스트 후기게시글(좋아요 가장 높은 아이)*/
	public ArrayList<ReviewBestVO> bestReview(){
		ArrayList<ReviewBestVO> getBestReview = new ArrayList<ReviewBestVO>();
		try{
			String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title , review_content, tutoring_info.title, tutoring_info.tutor_id "
					+ ", review_count,review_date  "
					+ "FROM review, member_info, review_like_report ,tutoring_info, tutoringList  "
					+ "WHERE review_like_count = (SELECT MAX(review_like_count)AS 'best' FROM review) "
					+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
					+ "group by review.review_id "
					+ "LIMIT 1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				getBestReview.add(new ReviewBestVO(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id"),
						rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getBestReview;
	}	



/**좋아요 삭제*/
public boolean deleteReviewImage(int reviewId){
	boolean result = false;
	try{
		String sql="delete from review_image "
				+ "where review_id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, reviewId);
		pstmt.executeUpdate();
		result = true;
	}catch(SQLException e){
		e.printStackTrace();
	}
	return result;
}





//조회수, 최신순, 수정
//번호순은 위에 있음
/**게시글리스트_최신순*/
public ArrayList<ReviewLikeVO2> getReviewDateListUser(String userId,int start, int end){
	ArrayList<ReviewLikeVO2> getReviewLikeList = new ArrayList<ReviewLikeVO2>();
	try{
		String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
				+ ",review_count,review_date, count(review_like_report.review_id) as 'review_like_counts', review_like_count  "
				+ "from review  "
				+ "left outer join review_like_report  "
				+ "on review.review_id = review_like_report.review_id and review_like_report.userId = ? "
				+ ", tutoring_apply_info "
				+ ", member_info  "
				+ ",tutoring_info, tutoringList "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id  "
				+ "and tutoring_apply_info.tutee_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
				+ "group by review.review_id "
				+ "order by review.review_date desc limit ?,? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setInt(2, start-1);
		pstmt.setInt(3, end);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()){
			getReviewLikeList.add(new ReviewLikeVO2(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
					,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"), rs.getInt("review_like_counts"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
		} 	

		rs.close();
		pstmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return getReviewLikeList;
}	
/**게시글리스트_조회수순*/
public ArrayList<ReviewLikeVO2> getReviewHitListUser(String userId,int start, int end){
	ArrayList<ReviewLikeVO2> getReviewLikeList = new ArrayList<ReviewLikeVO2>();
	try{
		String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
				+ ",review_count,review_date, count(review_like_report.review_id) as 'review_like_counts', review_like_count  "
				+ "from review  "
				+ "left outer join review_like_report  "
				+ "on review.review_id = review_like_report.review_id and review_like_report.userId = ? "
				+ ", tutoring_apply_info "
				+ ", member_info  "
				+ ",tutoring_info, tutoringList "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id  "
				+ "and tutoring_apply_info.tutee_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
				+ "group by review.review_id "
				+ "order by review.review_count desc limit ?,? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setInt(2, start-1);
		pstmt.setInt(3, end);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()){
			getReviewLikeList.add(new ReviewLikeVO2(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
					,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"), rs.getInt("review_like_counts"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
		} 	
		rs.close();
		pstmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return getReviewLikeList;
}	
/**게시글리스트_좋아요*/
public ArrayList<ReviewLikeVO2> getReviewLikeCountListUser(String userId,int start, int end){
	ArrayList<ReviewLikeVO2> getReviewLikeList = new ArrayList<ReviewLikeVO2>();
	try{
		String sql = "select tutoringList.name, member_info.name, review.review_id, review.tutoring_apply_id, member_info.member_id, review_title, review_content,  tutoring_info.title, tutoring_info.tutor_id "
				+ ",review_count,review_date, count(review_like_report.review_id) as 'review_like_counts', review_like_count  "
				+ "from review  "
				+ "left outer join review_like_report  "
				+ "on review.review_id = review_like_report.review_id and review_like_report.userId = ? "
				+ ", tutoring_apply_info "
				+ ", member_info  "
				+ ",tutoring_info, tutoringList "
				+ "where tutoring_apply_info.tutoring_apply_id = review.tutoring_apply_id  "
				+ "and tutoring_apply_info.tutee_id = member_info.member_id  "
				+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id "
				+ "and tutoringList.tutor_id = tutoring_info.tutor_id "
				+ "group by review.review_id "
				+ "order by review.review_like_count desc limit ?,? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setInt(2, start-1);
		pstmt.setInt(3, end);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()){
			getReviewLikeList.add(new ReviewLikeVO2(rs.getString("member_id"),rs.getInt("review_id"),rs.getInt("tutoring_apply_id"),rs.getString("review_title"),rs.getString("title"),rs.getString("tutor_id")
					,rs.getString("review_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"),rs.getInt("review_count"),rs.getString("review_date").substring(0, 11), rs.getInt("review_like_count"), rs.getInt("review_like_counts"),rs.getString("tutoringList.name"), rs.getString("member_info.name")));
		} 	

		rs.close();
		pstmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return getReviewLikeList;
}	
} // end