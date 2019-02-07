package com.starters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;

public class ChatDAO {
	private Connection conn;
	public ChatDAO(){
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
	
	public ArrayList<ChatVO> getChatListById(String receptionId, String sentId,int chatId){
		ArrayList<ChatVO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from chat_info "
				+ "where ((reception_id = ? and sent_id = ?) or (reception_id = ? and sent_id = ?)) and chat_id > ? "
				+ "order by date";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receptionId);
			pstmt.setString(2, sentId);
			pstmt.setString(3, sentId);
			pstmt.setString(4, receptionId);
			pstmt.setInt(5, chatId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatVO>();
			while(rs.next()){
				ChatVO chat = new ChatVO();
				chat.setChatId(rs.getInt("chat_id"));
				chat.setReceptionId(rs.getString("reception_id"));
				chat.setSentId(rs.getString("sent_id"));
				chat.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setDate(rs.getString("date"));
//				chat.setDate(rs.getString("date"));
				int chatTime = Integer.parseInt(rs.getString("date").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12){
					timeType = "오후";
					//chatTime = 12;
				}
////				String chatTime2 = Integer.toString(chatTime);
				chat.setDate(rs.getString("date").substring(0, 11) + " " + timeType + " " + chatTime + ":"+rs.getString("date").substring(14,16));
//				chat.setDate(rs.getString("date").substring(0, 11)+chatTime);

//				chat.setDate(rs.getString("date"));
				chatList.add(chat);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			return chatList;
		
	}
	
	// 대화 내용중 최근 것만을 가져온다.
	public ArrayList<ChatVO> getChatListByRecent(String receptionId, String sentId,int number){
		ArrayList<ChatVO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from chat_info "
				+ "where ((reception_id = ? and sent_id = ?) or (reception_id = ? and sent_id = ?)) and chat_id > (select max(chat_id) - ? from chat_info where (reception_id = ? and sent_id = ?) or (reception_id = ? and sent_id = ?)) "
				+ "order by date";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receptionId);
			pstmt.setString(2, sentId);
			pstmt.setString(3, sentId);
			pstmt.setString(4, receptionId);
			pstmt.setInt(5, number);
			pstmt.setString(6, receptionId);
			pstmt.setString(7, sentId);
			pstmt.setString(8, sentId);
			pstmt.setString(9, receptionId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatVO>();
			while(rs.next()){
				ChatVO chat = new ChatVO();
				chat.setChatId(rs.getInt("chat_id"));
				chat.setReceptionId(rs.getString("reception_id").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setSentId(rs.getString("sent_id").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("date").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12){
					timeType = "오후";
					//chatTime = 12;
				}
////				String chatTime2 = Integer.toString(chatTime);
				chat.setDate(rs.getString("date").substring(0, 11) + " " + timeType + " " + chatTime + ":"+rs.getString("date").substring(14,16));
//				chat.setDate(rs.getString("date").substring(0, 11)+chatTime);

//				chat.setDate(rs.getString("date"));
				chatList.add(chat);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			return chatList;
		
	}
	
	// 채팅을 보내는 기능
		public int submit(String receptionId, String sentId, String chatContent){
			PreparedStatement pstmt = null;
			
			String sql = "insert into chat_info(reception_id, sent_id, content, chat_read) values (?, ?, ?, 0)";
			
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, receptionId);
				pstmt.setString(2, sentId);
				pstmt.setString(3, chatContent);
				return pstmt.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
				return -1; // 데이터 베이스 오류
			
		}
//		String sql = "select * from member_info, tutor_info "
//		+ "where member_info.member_id = tutor_info.member_id "
//		+ "and member_info.member_id = ?";
		// 튜터찾기
		public String findCheck(String memberId){
			ResultSet rs1 = null;
			String id = "";
			try{
				String sql = "select member_info.member_id "
						+ "from member_info, tutor_info "
						+ "where name = ? and "
						+ "member_info.member_id = tutor_info.member_id";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs1 = pstmt.executeQuery();
				
				if(rs1.next()) // 아이디 있음
					id = rs1.getString("member_info.member_id");
			
			}catch(SQLException e){
				e.printStackTrace();
			}
			return id;
		}
		
//		public ArrayList<ChatRoomVO> findCheck(String memberId){
//			ResultSet rs = null;
//			ArrayList<ChatRoomVO> findCheck = new ArrayList();
//			try{
//				String sql = "select distinct member_info.member_id, name "
//						+ "from member_info, tutor_info "
//						+ "where name = ? and "
//						+ "member_info.member_id = tutor_info.member_id ";
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, memberId);
//				rs = pstmt.executeQuery();
//				
//				while(rs.next()) // 아이디 있음
//					findCheck.add(new ChatRoomVO( rs.getString("member_info.member_id"), rs.getString("name")));
//			
//			}catch(SQLException e){
//				e.printStackTrace();
//			}
//			return findCheck;
//		}
		
		
		// 튜티찾기
		public String findtuteeCheck(String memberId){
			ResultSet rs1 = null;
			String id = "";
			try{
				String sql = "select member_info.member_id "
						+ "from member_info left join tutor_info on member_info.member_id = tutor_info.member_id "
						+ "where resume is null and "
						+ "member_info.name = ? ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs1 = pstmt.executeQuery();
				
				if(rs1.next()) // 아이디 있음
					id = rs1.getString("member_info.member_id");
			}catch(SQLException e){
				e.printStackTrace();
			}
			return id;
		}
		
		// 튜터입장에서 튜티명 띄우기
		public ArrayList<ChatRoomVO> findtuteeCheckName(String memberId){
			ResultSet rs = null;
			ArrayList<ChatRoomVO> findtuteeCheckName = new ArrayList();
			try{
				String sql = "select distinct name, member_info.member_id "
						+ "from tutoring_info, tutoring_apply_info, member_info "
						+ "where tutor_id= ? and "
						+ "tutoring_apply_info.tutee_id = member_info.member_id and "
						+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();
				
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					findtuteeCheckName.add(new ChatRoomVO( rs.getString("member_info.member_id"), rs.getString("name")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return findtuteeCheckName;
		}
		
		public ArrayList<ChatRoomVO> findtutorCheckName(String memberId){
			ResultSet rs = null;
			ArrayList<ChatRoomVO> findtutorCheckName = new ArrayList();
			try{
				String sql = "select distinct name, member_info.member_id "
						+ "from tutoring_info, tutoring_apply_info, member_info "
						+ "where tutee_id= ? and "
						+ "tutoring_info.tutoring_id = tutoring_apply_info.tutoring_id and "
						+ "tutoring_info.tutor_id = member_info.member_id";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();
				
				while(rs.next())
					// 테이블에서 가져온 레코드를 하나씩 tutoringVO 객체로 생성해서 ArrayList 컬렉션에 넣는다.
					findtutorCheckName.add(new ChatRoomVO( rs.getString("member_info.member_id"), rs.getString("name")));
				rs.close();
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return findtutorCheckName;
		}

		
		/**특정한 채팅을 했을때 상대방이 읽었는지 안읽었는지 판별*/
		public int readChat(String fromID, String toID){
			PreparedStatement pstmt = null;

			String sql = "update chat_info set chat_read = 1 where (reception_id = ? and sent_id = ?)";

			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, toID);
				pstmt.setString(2, fromID);
				return pstmt.executeUpdate();

			}catch(Exception e){
				e.printStackTrace();
			}
			return -1; // 데이터 베이스 오류

		}
		
		/**특정한 채팅을 했을때 상대방이 읽었는지 안읽었는지 판별*/
		public int getAllUnreadChat(String userID){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(chat_id) from chat_info where sent_id = ? and chat_read = 0";

			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userID);
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getInt("count(chat_id)");
				}
				return 0;
			}catch(Exception e){
				e.printStackTrace();
			}
			return -1; // 데이터 베이스 오류

		}
		
		
		/**튜터튜티 회원분류*/
		public int memberCateg2(String memberId){
			int member = 1;

			try{
				String sql = "select * from member_info, tutor_info "
						+ "where member_info.member_id = tutor_info.member_id "
						+ "and member_info.member_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				ResultSet rs = pstmt.executeQuery();
				
				String sql2 ="select distinct company_id "
						+ "from member_info, company_info "
						+ "where company_id= ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, memberId);
				ResultSet rs2 = pstmt2.executeQuery();
				
				String sql3 ="select distinct id "
						+ "from startersManager "
						+ "where id= ?";
				
				
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setString(1, memberId);
				ResultSet rs3 = pstmt3.executeQuery();
				
				if(rs.next()){ // 튜터
					member = 2;
				}else{
					member = 50;
				} 
					
				if(rs2.next())
					member = 3;
				if(rs3.next())
					member = 999;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return member;
		}
		
		
		// 메시지함
		public ArrayList<ChatVO> getBox(String userID){
			ArrayList<ChatVO> chatList = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from chat_info where chat_id in (select max(chat_id) from chat_info where sent_id = ? or reception_id = ? group by reception_id, sent_id);";
			
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userID);
				pstmt.setString(2, userID);
				
				rs = pstmt.executeQuery();
				chatList = new ArrayList<ChatVO>();
				while(rs.next()){
					ChatVO chat = new ChatVO();
					chat.setChatId(rs.getInt("chat_id"));
					chat.setReceptionId(rs.getString("reception_id").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
					chat.setSentId(rs.getString("sent_id").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
					chat.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
					int chatTime = Integer.parseInt(rs.getString("date").substring(11, 13));
					String timeType = "오전";
					if(chatTime >= 12){
						timeType = "오후";
						//chatTime = 12;
					}
////					String chatTime2 = Integer.toString(chatTime);
					chat.setDate(rs.getString("date").substring(0, 11) + " " + timeType + " " + chatTime + ":"+rs.getString("date").substring(14,16));
//					chat.setDate(rs.getString("date").substring(0, 11)+chatTime);

//					chat.setDate(rs.getString("date"));
					chatList.add(chat);
				}
				for(int i =0; i < chatList.size(); i++){
					ChatVO x = chatList.get(i);
					for(int j = 0; j < chatList.size();j++){
						ChatVO y = chatList.get(j);
						if(x.getReceptionId().equals(y.getSentId()) && x.getSentId().equals(y.getReceptionId())){
							if(x.getChatId() < y.getChatId()){
								chatList.remove(x);
								i--;
								break;
							}else{
								chatList.remove(y);
								j--;
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
				return chatList;
			
		}
		
		
		/**특정한 채팅을 했을때 얼마나 안읽었는지*/
		public int getUnreadChat(String fromID, String toID){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(chat_id) from chat_info where reception_id = ? and sent_id = ? and chat_read = 0";

			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fromID);
				pstmt.setString(2, toID);
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getInt("count(chat_id)");
				}
				return 0;
			}catch(Exception e){
				e.printStackTrace();
			}
			return -1; // 데이터 베이스 오류

		}
		
	
}
