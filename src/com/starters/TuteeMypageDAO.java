package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TuteeMypageDAO {
	private Connection conn;
	public TuteeMypageDAO(){
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
	
	public ArrayList<TuteeMypageVO> getMypageList(String tuteeId, int start, int end){ 
		ArrayList<TuteeMypageVO> mypageList = new ArrayList();
		try{
			String sql = "select member_id, name, tutoring_info.tutoring_id, title, image, tutoring_apply_id "
					+ "from member_info, tutoring_info, tutoring_apply_info, tutoring_image "
					+ "where tutoring_apply_info.tutee_id = ? "
					+ "and tutoring_image.tutoring_id = tutoring_info.tutoring_id "
					+ "and member_info.member_id = tutoring_apply_info.tutee_id  "
					+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id  "
					+ "order by tutoring_info.tutoring_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				mypageList.add(new TuteeMypageVO(rs.getString("member_id"),rs.getString("name"),rs.getInt("tutoring_id"),rs.getString("title"),rs.getString("image"),rs.getInt("tutoring_apply_id")));
//			System.out.println("출력확인");
//			System.out.println("mypageList" + mypageList);
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return mypageList;
	}
	
	/**수강신청내역 개수*/
	public int tutoringListCount(String tuteeId){
		int tutoringListAllCount = 0;
		String sql = "select count(*) "
				+ "from member_info, tutoring_info, tutoring_apply_info "
				+ "where tutoring_apply_info.tutee_id = ? "
				+ "and member_info.member_id = tutoring_apply_info.tutee_id "
				+ "and tutoring_apply_info.tutoring_id = tutoring_info.tutoring_id";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				tutoringListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tutoringListAllCount;
	}
	
//	-- 한 튜터링에서 튜터링에 신청한 사람 숫자
//	create view tutoringApplyCount
//	as SELECT tutoring_info.tutoring_id AS "tutoring_num", 
//	title,
//	COUNT(tutoring_apply_info.tutoring_id) AS "apply_count"
//	FROM tutoring_info LEFT OUTER JOIN tutoring_apply_info ON tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id
//	GROUP BY tutoring_info.tutoring_id
	
	public ArrayList<TuteeMypageRecoVO> recommendTutoring (String tuteeId){ 
		ArrayList<TuteeMypageRecoVO> mypageList = new ArrayList();
		try{
			String sql = "select tutoring_info.tutoring_id, tutoring_info.title, tutor_id ,member_info.member_id, name, middle_category.middle_categ_id, middle_name "
					+ "from middle_category, job_select, member_info, tutoring_info,tutoring_job_select, tutoringApplyCount "
					+ "where member_info.member_id = ? "
					+ "and member_info.member_id = job_select.member_id "
					+ "and job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "and tutoring_info.tutoring_id = tutoring_job_select.tutoring_id "
					+ "and tutoring_job_select.middle_categ_id = middle_category.middle_categ_id "
					+ "and start_date > now() "
					+ "and tutoringApplyCount.tutoring_num = tutoring_info.tutoring_id "
					+ "group by tutoring_info.tutoring_id "
					+ "order by tutoringApplyCount.apply_count desc, tutoring_info.count desc "
					+ "limit 3 ";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tuteeId);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				mypageList.add(new TuteeMypageRecoVO(rs.getString("member_id"),rs.getString("name"),rs.getInt("tutoring_id")
						,rs.getString("title"),rs.getString("member_id"),rs.getInt("middle_categ_id"),rs.getString("middle_name")));
			//System.out.println("출력확인");
			//System.out.println("mypageList" + mypageList);
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return mypageList;
	}
	
}
