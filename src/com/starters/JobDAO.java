package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JobDAO {
	private Connection conn;
	public JobDAO(){
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

	// 메인카테고리 출력
	public ArrayList<MainJobVO> adminGetMainJob(){
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<MainJobVO> mainJobList = new ArrayList();
		try{
			String sql = "select main_categ_id, main_name "
					+ "from main_category ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				mainJobList.add(new MainJobVO(rs.getInt("main_categ_id"), rs.getString("main_name")));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return mainJobList;
	}
	
	// 해당 대분류의 중분류들
	public ArrayList<JobVO> adminGetMiddleJob(int mainCategId){
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<JobVO> list = new ArrayList();
		try{
			String sql = "select main_category.main_categ_id, main_name, middle_categ_id, middle_name "
					+ "from main_category, middle_category "
					+ "where main_category.main_categ_id = ? "
					+ "and main_category.main_categ_id =  middle_category.main_categ_id";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainCategId);
			rs = pstmt.executeQuery();

			while(rs.next()){
				list.add(new JobVO(rs.getInt("main_category.main_categ_id"), rs.getString("main_name")
						,rs.getInt("middle_categ_id"),rs.getString("middle_name")));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return list;
	}
	
	// 대분류 추가
	 public boolean insertMainJob(String mainName){
	      boolean result = false;
	      try{
	         String sql = "insert into main_category(main_name) "
	         		+ "values(?)";
	         PreparedStatement pstmt=conn.prepareStatement(sql);
	         pstmt.setString(1, mainName);

	         if(pstmt.executeUpdate()==1)
	            result = true;

	      }catch(SQLException e){
	         e.printStackTrace();
	      }
	      return result;
	   }
	 // 대분류 수정
	 public boolean updateMainJob(String mainName, int mainCategId){
	      boolean result = false;
	      try{
	         String sql = "update main_category "
	               + "set main_name = ? "
	               +"where main_categ_id = ? ";
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mainName);
	         pstmt.setInt(2, mainCategId);

	         if(pstmt.executeUpdate() == 1){
	            result = true;
	         }
	      }catch(SQLException e){
	         e.printStackTrace();
	      }
	      return result;
	   }
	// 대분류 삭제
	   public boolean deleteMainJob(int mainCategId){
		      boolean result = false;
		      try{
		         String sql="delete from main_category "
		               + "where main_categ_id = ?";
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, mainCategId);
		         pstmt.executeUpdate();
		         result = true;
		      }catch(SQLException e){
		         e.printStackTrace();
		      }
		      return result;
		   }
	   // 중분류 추가
	   public boolean insertMiddleJob(String middleName, int mainCategId){
		      boolean result = false;
		      try{
		         String sql = "insert into middle_category(middle_name, main_categ_id) "
		         		+ "values(?,?)";
		         PreparedStatement pstmt=conn.prepareStatement(sql);
		         pstmt.setString(1, middleName);
		         pstmt.setInt(2, mainCategId);

		         if(pstmt.executeUpdate()==1)
		            result = true;

		      }catch(SQLException e){
		         e.printStackTrace();
		      }
		      return result;
		   }
	   // 증분류 수정
	   public boolean updateMiddleJob(String middleName, int mainCategId, int middleCategId){
		      boolean result = false;
		      try{
		         String sql = "update middle_category "
		               + "set middle_name = ?,  main_categ_id = ? "
		               +"where middle_categ_id = ? ";
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, middleName);
		         pstmt.setInt(2, mainCategId);
		         pstmt.setInt(3, middleCategId);

		         if(pstmt.executeUpdate() == 1){
		            result = true;
		         }
		      }catch(SQLException e){
		         e.printStackTrace();
		      }
		      return result;
		   }
	// 중분류 삭제
	   public boolean deleteMiddleJob(int middleCategId){
		      boolean result = false;
		      try{
		         String sql="delete from middle_category "
		               + "where middle_categ_id = ? ";
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, middleCategId);
		         pstmt.executeUpdate();
		         result = true;
		      }catch(SQLException e){
		         e.printStackTrace();
		      }
		      return result;
		   }
}
