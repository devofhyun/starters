package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TuteeMypageWishDAO {
	private Connection conn;
	private int tutoringId;
	public TuteeMypageWishDAO(){
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
	
	/**좋아요 리스트(group by로 중복제거(사진 여러 개일 때))*/
	public ArrayList<TuteeMypageWishVO> getTutornigWish(String tuteeId, int start, int end){ 
		ArrayList<TuteeMypageWishVO> tutoringwishList = new ArrayList();
		try{
			String sql = "select  tutoringlike.tutoring_id, tutee_id, title, image "
					+ "from member_info, tutoringlike, tutoring_info, tutoring_image  "
					+ "where member_info.member_id = tutoringlike.tutee_id  "
					+ "and tutoring_info.tutoring_id = tutoringlike.tutoring_id  "
					+ "and tutoring_info.tutoring_id = tutoring_image.tutoring_id  "
					+ "and tutee_id = ? "
					+ "group by tutoringlike.tutoring_id "
					+ "order by tutoring_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				tutoringwishList.add(new TuteeMypageWishVO(rs.getString("tutee_id"), rs.getInt("tutoring_id"), rs.getString("title"),rs.getString("image")));
			System.out.println("tutoringwishList" + tutoringwishList);
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return tutoringwishList;
	}
	
	/**좋아요 전체 수*/
	public int wishListCount(String tuteeId){
		int wishListAllCount = 0;
		String sql = "select count(*) "
				+ "from tutoringlike "
				+ "where tutee_id = ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				wishListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishListAllCount;
	}
	
	/** 찜하기 취소
	 * 삭제가 되었으면 true, 
	 * 삭제 못했으면 false 
	 * delete
	 */
	public boolean deleteWishList(int tutoringId){
		boolean result = false;
		try{
			String sql="delete from tutoringlike "
					+ "where tutoring_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tutoringId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
		}
	/**찜하기 등록 insert*/
	public boolean registerlike(String tuteeId, int tutoringId) {
		boolean result = false;
		try{
			String sql ="insert into tutoringlike(tutee_id, tutoring_id) "
					+ "values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringId);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**좋아요 전체 수*/
	public int wishListsCount(String tuteeId, int tutoringId){
		int wishListsCount = 0;
		String sql = "select count(*) "
				+ "from tutoringlike, tutoring_info, member_info "
				+ "where member_info.member_id = tutoringlike.tutee_id and "
				+ "tutoringlike.tutoring_id = tutoring_info.tutoring_id and "
				+ "member_info.member_id = ? and "
				+ "tutoringlike.tutoring_id = ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, tutoringId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				wishListsCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishListsCount;
	}
	
}
