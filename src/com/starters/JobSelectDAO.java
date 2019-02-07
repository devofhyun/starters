package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JobSelectDAO {
	private Connection conn;
	
	public JobSelectDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);
		}catch(Exception e){
			e.printStackTrace();
		}//catch
	}
	
	/**특정 대분류에 따른 중분류를 보이시오.*/
	public ArrayList<MiddleCategVO> middleCategNames(int mainCategId){
		ArrayList<MiddleCategVO> middleCategNames = new ArrayList<MiddleCategVO>();
		try{
			String sql = "select main_category.main_categ_id, main_name, middle_categ_id, middle_name "
					+ "from main_category, middle_category "
					+ "where main_category.main_categ_id = middle_category.main_categ_id "
					+ "and main_category.main_categ_id = ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mainCategId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
				// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
				middleCategNames.add(new MiddleCategVO(rs.getInt("main_categ_id"),  rs.getString("main_name"),rs.getInt("middle_categ_id"),rs.getString("middle_name")));
			rs.close();
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return middleCategNames;
	}
	
	/**대분류의 목록을 보이시오.*/
	public ArrayList<MainCategVO> mainCategNames(){
		ArrayList<MainCategVO> mainCategNames = new ArrayList<MainCategVO>();
		
		try{
			String sql = "select main_categ_id,main_name from main_category";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
				mainCategNames.add(new MainCategVO(rs.getInt("main_categ_id"), rs.getString("main_name")));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mainCategNames;
	}
	
	/**대분류, 중분류 전체의 목록을 보이시오.*/
	public ArrayList<MiddleCategVO> mainMiddleCategNames(){
		ArrayList<MiddleCategVO> mainMiddleCategNames = new ArrayList<MiddleCategVO>();
		
		try{
			String sql = "select main_category.main_categ_id, main_name, middle_categ_id, middle_name "
					+ "from main_category, middle_category "
					+ "where main_category.main_categ_id = middle_category.main_categ_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
				mainMiddleCategNames.add(new MiddleCategVO(rs.getInt("main_categ_id"),  rs.getString("main_name"),rs.getInt("middle_categ_id"),rs.getString("middle_name")));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mainMiddleCategNames;
	}
	

}
