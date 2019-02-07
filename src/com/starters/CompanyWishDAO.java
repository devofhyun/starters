package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompanyWishDAO {
	private Connection conn;
	public CompanyWishDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://128.134.114.237:3306/db216230105";
			String id="216230105";
			String pw="fprtmf211";
			conn = DriverManager.getConnection(url, id, pw);
		}catch(Exception e){
			e.printStackTrace();
		}//catch
	}// 생성자
	
	/**포트폴리오 찜하기*/
	public boolean companyLikeBag(String companyId, int portfolioId) {
		boolean result = false;
		try{
			String sql ="insert into company_like(company_id, portfolio_id) "
					+ "values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, portfolioId);
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/** 찜하기 취소
	 * 삭제가 되었으면 true, 
	 * 삭제 못했으면 false 
	 * delete
	 */
	public boolean deleteCompanyLike(int portfolioId){
		boolean result = false;
		try{
			String sql="delete from company_like "
					+ "where portfolio_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, portfolioId);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
		}
	
	/**좋아요 목록*/
	public ArrayList<CompanyWishVO> getPortfolioWish(String companyId, int start, int end) {
		ArrayList<CompanyWishVO> portfolioWishList = new ArrayList();
		try{
			String sql = "select company_like.portfolio_id, company_info.company_id, portfolio_title, image "
					+ "from company_info, portfolio, portfolio_image, company_like "
					+ "where company_info.company_id = company_like.company_id and "
					+ "company_like.portfolio_id = portfolio.portfolio_id and "
					+ "portfolio.portfolio_id = portfolio_image.portfolio_id and "
					+ "company_info.company_id = ? "
					+ "order by company_id asc limit ?,?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, start-1);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				portfolioWishList.add(new CompanyWishVO(rs.getString("company_id"), rs.getInt("portfolio_id"), rs.getString("portfolio_title"),rs.getString("image")));
			
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} // catch 
		return portfolioWishList;
	}

	
	/**좋아요 갯수*/
	public int CompanywishListCount(String companyId){
		int wishListAllCount = 0;
		String sql = "select count(*) "
				+ "from company_like "
				+ "where company_id = ?";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
				wishListAllCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishListAllCount;
	}
	
	
	/**좋아요 전체 수*/
	public int portfolioWishListsCount(String companyId, int portfolioId){
		int wishListsCount = 0;
		String sql = "select count(*) "
				+ "from company_like, portfolio, company_info "
				+ "where company_info.company_id = company_like.company_id and "
				+ "company_like.portfolio_id = portfolio.portfolio_id and "
				+ "company_info.company_id = ? and "
				+ "company_like.portfolio_id = ? ";
		try{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, companyId);
			pstmt.setInt(2, portfolioId);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				wishListsCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishListsCount;
	}

	public ArrayList<IntImageVO> getCompanyWishImage(int portfolioNum) {
		ArrayList<IntImageVO> images = new ArrayList();
		try{
			String sql = "select portfolio.portfolio_id, image "
					+ "from portfolio, portfolio_image "
					+ "where portfolio.portfolio_id = portfolio_image.portfolio_id and "
					+ "portfolio.portfolio_id = ?";
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, portfolioNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//		for(int i = 1; i< rs.length(); i++){
				images.add(new IntImageVO(rs.getInt("portfolio.portfolio_id"), rs.getString("image")));
				//		images = rs.getString("image");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return images;
	}



}
