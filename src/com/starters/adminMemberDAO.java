package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminMemberDAO {
	
	private Connection conn;

	public adminMemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://128.134.114.237:3306/db216230105";
			String id = "216230105";
			String pw = "fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int tutee() {
		int tutee = 0;

		try {
			String sql = "select count(member_info.member_id) "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is null";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				tutee = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tutee;
	}
	
	public int tutor() {
		int tutor = 0;

		try {
			String sql = "select count(member_info.member_id) "
					+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
					+ "where resume is not null";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				tutor = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tutor;
	}
	
	public int company() {
		int company = 0;

		try {
			String sql = "select count(*) from company_info";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				company = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}
	
	public int tutoring() {
		int tutoring = 0;

		try {
			String sql = "select count(*) from tutoring_info";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				tutoring = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tutoring;
	}
	
	public int portfolio() {
		int portfolio = 0;

		try {
			String sql = "select count(*) from portfolio";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				portfolio = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return portfolio;
	}
	
	public int tutoringBuy() {
		int tutoringBuy = 0;

		try {
			String sql = "select count(*) from tutoring_apply_info";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				tutoringBuy = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tutoringBuy;
	}
	
	public int interview() {
		int interview = 0;

		try {
			String sql = "select count(*) from interview";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				interview = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interview;
	}


}
