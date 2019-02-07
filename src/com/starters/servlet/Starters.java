package com.starters.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.starters.AdminTutoringVO;
import com.starters.BuyDAO;
import com.starters.BuyStatisticVO;
import com.starters.BuyStatisticVO2;
import com.starters.ChatDAO;
import com.starters.ChatRoomVO;
import com.starters.ChatVO;
import com.starters.CompanyInervVO;
import com.starters.CompanyVO;
import com.starters.CompanyWishDAO;
import com.starters.CompanyWishVO;
import com.starters.ImageVO;
import com.starters.IntImageVO;
import com.starters.IntJobSelectVO;
import com.starters.InterviewApplyVO;
import com.starters.InterviewDAO;
import com.starters.InterviewDayVO;
import com.starters.InterviewPortfolioVO;
import com.starters.InterviewTimeVO;
import com.starters.InterviewVO;
import com.starters.InterviewVO2;
import com.starters.JobDAO;
import com.starters.JobSelectDAO;
import com.starters.JobVO;
import com.starters.MainCategVO;
import com.starters.MainJobVO;
import com.starters.MemberDAO;
import com.starters.MemberVO;
import com.starters.MiddleCategVO;
import com.starters.NoticeDAO;
import com.starters.NoticeMemVO;
import com.starters.NoticeVO;
import com.starters.PortfolioDAO;
import com.starters.PortfolioLikeVO;
import com.starters.PortfolioVO;
import com.starters.PortfolioVO2;
import com.starters.QnACommentVO;
import com.starters.QnADAO2;
import com.starters.QnAVO2;
import com.starters.ReviewBestVO;
import com.starters.ReviewCountVO;
import com.starters.ReviewDAO;
import com.starters.ReviewLikeVO;
import com.starters.ReviewLikeVO2;
//import com.starters.ReviewDAO;
import com.starters.ReviewVO;
import com.starters.StatisticVO;
import com.starters.StatisticsDAO;
import com.starters.StringJobSelectVO;
import com.starters.TuteeMypageDAO;
import com.starters.TuteeMypageDailyDAO;
import com.starters.TuteeMypageDailyVO;
import com.starters.TuteeMypageInterviewDAO;
import com.starters.TuteeMypageInterviewJobSelectVO;
import com.starters.TuteeMypageInterviewVO;
import com.starters.TuteeMypageInterviewVO2;
import com.starters.TuteeMypageRecoVO;
import com.starters.TuteeMypageVO;
import com.starters.TuteeMypageWishDAO;
import com.starters.TuteeMypageWishVO;
import com.starters.TutoringDAO;
import com.starters.TutoringDailyDAO;
import com.starters.TutoringDailyVO;
import com.starters.TutoringDayVO;
import com.starters.TutoringTimeVO;
import com.starters.TutoringTimeVO2;
import com.starters.TutoringTuteeListVO;
import com.starters.TutoringTuteeListVO2;
import com.starters.TutoringVO2;
import com.starters.TutoringVO3;
import com.starters.adminMemberDAO;
import com.starters.reviewLikeReportVO;
import com.starters.AdminAMemberVO;
import com.starters.AdminTMemberVO;
import com.starters.TutoringApplyListDAO;
import com.starters.TutoringApplyListVO;
import com.starters.TutoringBuyVO;
import com.starters.TutoringCalendarVO;
import com.starters.TutoringCalendarVO2;

//@WebServlet("/starters")
public class Starters extends HttpServlet {
	// 회원정보
	MemberDAO dao;

	// 튜터링 정보
	TutoringDAO dao2;

	// 직종 정보
	JobSelectDAO dao3;

	// 후기게시판
	ReviewDAO dao4;

	// 포트폴리오
	PortfolioDAO dao5;

	// 인터뷰
	InterviewDAO dao6;

	// 기업마이페이지 - 찜하기
	CompanyWishDAO company1;

	// 공지사항
	NoticeDAO dao7;

	// 튜티마이페이지 - 마이페이지
	TuteeMypageDAO tutee1;
	// 튜티마이페이지 - 수강신청내역
	TutoringApplyListDAO tutee2;
	// 튜티마이페이지 - 찜하기
	TuteeMypageWishDAO tutee3;
	// 튜티마이페이지 - 면접신청내역
	TuteeMypageInterviewDAO tutee4;
	// 튜티마이페이지 - 일지
	TuteeMypageDailyDAO tutee5;

	// qna
	QnADAO2 dao9;

	// chat
	ChatDAO dao10;

	// daily
	TutoringDailyDAO dao11;

	// 1028추가
	BuyDAO buy;

	// 통계
	StatisticsDAO statis;
	
	// 직종편집
	JobDAO job;
	// 관리자페이지
	adminMemberDAO admin;

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String cmd = request.getParameter("cmd");
		if (cmd == null)
			cmd = " ";
		String page = "main.jsp";
		switch (cmd) {
		case "adminMain":
			page = adminMain(request);
//			page = "adminMain.jsp";
			break;
		/**관리자- 통계*/
		// 후기 통계 상세
		case "reviewStatiscticsDetail":
			try {
				page = reviewStatiscticsDetail(request);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		// 후기통계 목록
		case "reviewStatisctics":
			page = reviewStatisctics(request);
			break;
		// 후기 통계 페이징 
		case "reviewStatiscticsPaging":
			page = reviewStatiscticsPaging(request);
			break;
			// 후기통계 전체 검색 목록
		case "reviewStatiscticsAll":
			page = reviewStatiscticsAll(request);
			break;
		// 후기 통계 전체 검색 페이징 
		case "reviewStatiscticsAllPaging":
			page = reviewStatiscticsAllPaging(request);
			break;
			// 후기통계 1개월 검색 목록
		case "reviewStatiscticsOne":
			page = reviewStatiscticsOne(request);
			break;
		// 후기 통계 1개월 검색 페이징 
		case "reviewStatiscticsOnePaging":
			page = reviewStatiscticsOnePaging(request);
			break;
			// 후기통계 3개월 검색 목록
		case "reviewStatiscticsThree":
			page = reviewStatiscticsThree(request);
			break;
		// 후기 통계 3개월 검색 페이징 
		case "reviewStatiscticsThreePaging":
			page = reviewStatiscticsThreePaging(request);
			break;
			// 후기통계 6개월 검색 목록
		case "reviewStatiscticsSix":
			page = reviewStatiscticsSix(request);
			break;
		// 후기 통계 6개월 검색 페이징 
		case "reviewStatiscticsSixPaging":
			page = reviewStatiscticsSixPaging(request);
			break;
		// 후기 통계 날짜 조회
		case "reviewDateSelectAction":
			page = reviewDateSelectAction(request);
			break;
			// 후기 통계 날짜 조회 페이징
		case "reviewDateSelectActionPaging":
			page = reviewDateSelectActionPaging(request);
			break;
			// 후기통계 좋아요 올림차순 내림차순
		case "reviewStatiscticsLike":
			page = reviewStatiscticsLike(request);
			break;
			// 후기통계 좋아요 올림차순 내림차순 페이징 
		case "reviewStatiscticsLikePaging":
			page = reviewStatiscticsLikePaging(request);
			break;
			// 후기통계 해당 월
		case "reviewMonthStatis":
			try {
				page = reviewMonthStatis(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "reviewPartStatisPrev":
			try {
				page = reviewPartStatisPrev(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "reviewPartStatisAfter":
			try {
				page = reviewPartStatisAfter(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			// 후기통계 끝
			
			// 튜터링 통계 시작
			// 튜터링 통계 목록
		case "tutoringStatisctics":
			page = tutoringStatisctics(request);
			break;
			// 튜터링 통계 페이징 
		case "tutoringStatiscticsPaging":
			page = tutoringStatiscticsPaging(request);
			break;
		case "tutoringStatiscticsDetail":
			page = tutoringStatiscticsDetail(request);
			break;
			// 튜터링 통계 수입, 후기, 튜터링 횟수 올림차순, 내림차순
		case "tutoringStatiscticsAscDesc":
			page = tutoringStatiscticsAscDesc(request);
			break;
			// 튜터링 통계 수입, 후기, 튜터링 횟수 올림차순, 내림차순 페이징 
		case "tutoringStatiscticsAscDescPaging":
			page = tutoringStatiscticsAscDescPaging(request);
			break;
			// 튜터링 통계 전체 검색 목록
		case "tutoringStatiscticsAll":
			page = tutoringStatiscticsAll(request);
			break;
		// 튜터링 통계 전체 검색 페이징 
		case "tutoringStatiscticsAllPaging":
			page = tutoringStatiscticsAllPaging(request);
			break;
			// 튜터링 통계 1개월 검색 목록
		case "tutoringStatiscticsOne":
			page = tutoringStatiscticsOne(request);
			break;
		// 튜터링 통계 1개월 검색 페이징 
		case "tutoringStatiscticsOnePaging":
			page = tutoringStatiscticsOnePaging(request);
			break;
			// 튜터링 통계 3개월 검색 목록
		case "tutoringStatiscticsThree":
			page = tutoringStatiscticsThree(request);
			break;
		// 튜터링 통계 3개월 검색 페이징 
		case "tutoringStatiscticsThreePaging":
			page = tutoringStatiscticsThreePaging(request);
			break;
			// 튜터링 통계 6개월 검색 목록
		case "tutoringStatiscticsSix":
			page = tutoringStatiscticsSix(request);
			break;
		// 튜터링 통계 6개월 검색 페이징 
		case "tutoringStatiscticsSixPaging":
			page = tutoringStatiscticsSixPaging(request);
			break;
		// 튜터링 통계 날짜 조회
		case "tutoringDatesSelectAction":
			page = tutoringDatesSelectAction(request);
			break;
			// 튜터링 통계 날짜 조회 페이징
		case "tutoringDatesSelectActionPaging":
			page = tutoringDatesSelectActionPaging(request);
			break;
			// 튜터링 셀렉 박스 통계
		case "tutoringSelectStatis":
			try {
				page = tutoringSelectStatis(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "tutoringYearStatisPrev":
			try {
				page = tutoringYearStatisPrev(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "tutoringYearStatisAfter":
			try {
				page = tutoringYearStatisAfter(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			// 튜터링 통계 끝
			
			// 전체 통계 시작
			// 분기별, 일별, 월별... 시작
		case "allstatistic":
			try {
				page = allstatistic(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "selectAllstatistic":
			try {
				page = selectAllstatistic(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		// 작년도 월별 통계
		case "MonthAllstatisticPrev":
			try {
				page = MonthAllstatisticPrev(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		// 작년도 월별 통계
		case "MonthAllstatisticAfter":
			try {
				page = MonthAllstatisticAfter(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		// 연도별 페이징
		case "getBuyYearAllPaging":
			try {
				page = getBuyYearAllPaging(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		// 일별에서 1개월, 3개월, 6개월, 날짜 검색 시작
		// 1개월
		case "getBuyDayOne":
			try {
				page = getBuyDayOne(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "getBuyDayOnePaging":
			try {
				page = getBuyDayOnePaging(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
			// 3개월
		case "getBuyDayThree":
			try {
				page = getBuyDayThree(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "getBuyDayThreePaging":
			try {
				page = getBuyDayThreePaging(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			// 6개월
		case "getBuyDaySix":
			try {
				page = getBuyDaySix(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "getBuyDaySixPaging":
			try {
				page = getBuyDaySixPaging(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			// 전체
		case "getBuyDayAll":
			try {
				page = getBuyDayAll(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "getBuyDayAllPaging":
			try {
				page = getBuyDayAllPaging(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			// 날짜 검색
		case "getBuyDaySelectDate":
			try {
				page = getBuyDaySelectDate(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "getBuyDaySelectDatePaging":
			try {
				page = getBuyDaySelectDatePaging(request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		// 일별에서 1개월, 3개월, 6개월, 날짜 검색 끝
			// 분기별, 일별, 월별... 끝
			// 전체 튜터링 통계 목록
		case "tutoringStatistic":
			page = tutoringStatistic(request);
			break;
		// 전체 튜터링 통계 페이징 
		case "tutoringStatisticPaging":
			page = tutoringStatisticPaging(request);
			break;
		// 전체 튜터링 통계 상세
		case "tutoringBuyStatiscticsDetail":
			page = tutoringBuyStatiscticsDetail(request);
			break;
			// 튜터링 통계 전체 검색 목록
		case "tutoringBuyStatiscticsAll":
			page = tutoringBuyStatiscticsAll(request);
			break;
		// 튜터링 통계 전체 검색 페이징 
		case "tutoringBuyStatiscticsAllPaging":
			page = tutoringBuyStatiscticsAllPaging(request);
			break;
			// 튜터링 통계 1개월 검색 목록
		case "tutoringBuyStatiscticsOne":
			page = tutoringBuyStatiscticsOne(request);
			break;
		// 튜터링 통계 1개월 검색 페이징 
		case "tutoringBuyStatiscticsOnePaging":
			page = tutoringBuyStatiscticsOnePaging(request);
			break;
			// 튜터링 통계 3개월 검색 목록
		case "tutoringBuyStatiscticsThree":
			page = tutoringBuyStatiscticsThree(request);
			break;
		// 튜터링 통계 3개월 검색 페이징 
		case "tutoringBuyStatiscticsThreePaging":
			page = tutoringBuyStatiscticsThreePaging(request);
			break;
			// 튜터링 통계 6개월 검색 목록
		case "tutoringBuyStatiscticsSix":
			page = tutoringBuyStatiscticsSix(request);
			break;
		// 튜터링 통계 6개월 검색 페이징 
		case "tutoringBuyStatiscticsSixPaging":
			page = tutoringBuyStatiscticsSixPaging(request);
			break;	
			// 튜터링 통계 달력 검색 목록
		case "tutoringBuyStatiscticsDates":
			page = tutoringBuyStatiscticsDates(request);
			break;
		// 튜터링 통계 달력 검색 페이징 
		case "tutoringBuyStatiscticsDatesPaging":
			page = tutoringBuyStatiscticsDatesPaging(request);
			break;	
			// 전체 인터뷰 통계 목록
		case "interviewStatistic":
			page = interviewStatistic(request);
			break;
		// 전체 인터뷰 통계 페이징 
		case "interviewStatisticPaging":
			page = interviewStatisticPaging(request);
			break;
			// 인터뷰 통계 상세
		case "interviewBuyStatiscticsDetail":
			page = interviewBuyStatiscticsDetail(request);
			break;
			// 인터뷰 통계 전체 검색 목록
			case "interviewBuyStatiscticsAll":
				page = interviewBuyStatiscticsAll(request);
				break;
			// 인터뷰 통계 전체 검색 페이징 
			case "interviewBuyStatiscticsAllPaging":
				page = interviewBuyStatiscticsAllPaging(request);
				break;
			// 인터뷰 통계 1개월 검색 목록
			case "interviewBuyStatiscticsOne":
				page = interviewBuyStatiscticsOne(request);
				break;
			// 인터뷰 통계 1개월 검색 페이징 
			case "interviewBuyStatiscticsOnePaging":
				page = interviewBuyStatiscticsOnePaging(request);
				break;
				// 인터뷰 통계 3개월 검색 목록
			case "interviewBuyStatiscticsThree":
				page = interviewBuyStatiscticsThree(request);
				break;
			// 인터뷰 통계 3개월 검색 페이징 
			case "interviewBuyStatiscticsThreePaging":
				page = interviewBuyStatiscticsThreePaging(request);
				break;
				// 인터뷰 통계 6개월 검색 목록
			case "interviewBuyStatiscticsSix":
				page = interviewBuyStatiscticsSix(request);
				break;
			// 인터뷰 통계 6개월 검색 페이징 
			case "interviewBuyStatiscticsSixPaging":
				page = interviewBuyStatiscticsSixPaging(request);
				break;	
				// 인터뷰 통계 달력 검색 목록
			case "interviewBuyStatiscticsDates":
				page = interviewBuyStatiscticsDates(request);
				break;
			// 인터뷰 통계 달력 검색 페이징 
			case "interviewBuyStatiscticsDatesPaging":
				page = interviewBuyStatiscticsDatesPaging(request);
				break;	
			// 전체 통계 끝
		/**관리자-통계 끝*/
		// ----------------------------------------------------------------------------------------
			// ----------------------------------------------------------------------------------------
			case "MemberStatistic":
				try {
					page = MemberStatistic(request);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;		
				
			case "companyStatistic":
				try {
					page = companyStatistic(request);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;	
			case "selectMemberStatistic":
			try {
				page = selectMemberStatistic(request);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				break;
			
			case "getMemberYearAllPaging":
			try {
				page = getMemberYearAllPaging(request);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				break;
				
			case "selectCompanyStatistic":
			try {
				page = selectCompanyStatistic(request);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				break;
				
			case "getCompanyYearAllPaging":
			try {
				page = getCompanyYearAllPaging(request);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				break;
				
			case "companyYearStatisPrev":
				try {
					page = companyYearStatisPrev(request);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "companyYearStatisAfter":
				try {
					page = companyYearStatisAfter(request);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			case "memberYearStatisPrev":
				try {
					page = memberYearStatisPrev(request);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "memberYearStatisAfter":
				try {
					page = memberYearStatisAfter(request);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			
			case "memberAgeStatistic":
				try {
					page = memberAgeStatistic(request);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "memberGenderStatistic":
				try {
					page = memberGenderStatistic(request);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
				
/*			case "memberRegisterDateStatistic":
				try {
					page = memberRegisterDateStatistic(request);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;*/
				
/*			case "companyRegisterDateStatistic":
				try {
					page = companyRegisterDateStatistic(request);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;*/
				
				/** 관리자 회원관리 - 전체 */
			case "adminMemberList":
				// 관리자 회원관리 튜티 리스트 목록
				page = adminMemberList(request);
				page = adminMemberListNumber(request);
				break;

			// 관리자 회원관리 튜티 페이징
			case "adminMemberListPagingAction":
				page = adminMemberListPagingAction(request);
				break;
			// 관리자 회원관리 검색

			case "adminMemberListAction":
				page = adminMemberListAction(request);
				break;
			case "adminMemberListSearchPaging":
				page = adminMemberListSearchPaging(request);
				break;

			/** 이름순, 아이디순 튜티 리스트 */
			case "adminMemberListSelectAction":
				page = adminMemberListSelectAction(request);
				break;
			case "adminMemberListSelectPaging":
				page = adminMemberListSelectPaging(request);
				break;

			/** 관리자 회원관리 - 전체 끝 */
			/** 관리자 회원관리 - 튜티 */
			case "adminMemberTuteeList":
				// 관리자 회원관리 튜티 리스트 목록
				page = adminMemberTuteeList(request);
				page = adminMemberTuteeListNumber(request);
				break;

			// 관리자 회원관리 튜티 페이징
			case "adminMemberTuteeListPagingAction":
				page = adminMemberTuteeListPagingAction(request);
				break;
			// 관리자 회원관리 검색
			case "adminMemberTuteeListAction":
				page = adminMemberTuteeListAction(request);
				break;
			case "adminMemberTuteeListSearchPaging":
				page = adminMemberTuteeListSearchPaging(request);
				break;

			/** 이름순, 아이디순 튜티 리스트 */
			case "adminMemberTuteeListSelectAction":
				page = adminMemberTuteeListSelectAction(request);
				break;
			case "adminMemberTuteeListSelectPaging":
				page = adminMemberTuteeListSelectPaging(request);
				break;

			/** 관리자 회원관리 - 튜티 끝 */

			/** 관리자 회원관리 - 튜터 */
			case "adminMemberTutorList":
				// 관리자 회원관리 튜터 리스트 목록
				page = adminMemberTutorList(request);
				page = adminMemberTutorListNumber(request);
				break;

			// 관리자 회원관리 튜터 페이징
			case "adminMemberTutorListPagingAction":
				page = adminMemberTutorListPagingAction(request);
				break;
			// 관리자 회원관리 검색
			case "adminMemberTutorListAction":
				page = adminMemberTutorListAction(request);
				break;
			case "adminMemberTutorListSearchPaging":
				page = adminMemberTutorListSearchPaging(request);
				break;

			/** 이름순, 아이디순 튜터 리스트 */
			case "adminMemberTutorListSelectAction":
				page = adminMemberTutorListSelectAction(request);
				break;
			case "adminMemberTutorListSelectPaging":
				page = adminMemberTutorListSelectPaging(request);
				break;

			/** 관리자 회원관리 - 튜터 끝 */

			/** 관리자 회원관리 - 기업 시작 */
			case "adminMemberCompanyList":
				page = adminMemberCompanyList(request);
				break;

			/*
			 * case "adminMemberCompanyListModal": page =
			 * adminMemberCompanyListModal(request); break;
			 */

			case "adminMemberCompanyListPagingAction":
				page = adminMemberCompanyListPagingAction(request);
				break;

			case "adminMemberCompanyListAction":
				page = adminMemberCompanyListAction(request);
				break;

			case "adminMemberCompanySearchListPaging":
				page = adminMemberCompanySearchListPaging(request);
				break;

			/** 이름순, 아이디순 튜터 리스트 */
			case "adminMemberCompanyListSelectAction":
				page = adminMemberCompanyListSelectAction(request);
				break;
			case "adminMemberCompanyListSelectPaging":
				page = adminMemberCompanyListSelectPaging(request);
				break;

			/** 관리자 회원관리 - 기업 끝 */

			/** 튜터마이페이지 */
			case "adminTutorDetailAction":
				page = adminTutorDetailAction(request);
				break;

			/** 튜티마이페이지 */
			case "adminTuteeDetailAction":
				page = adminTuteeDetailAction(request);
				break;

			/** 기업마이페이지 */
			case "adminCompanyDetailAction":
				page = adminCompanyDetailAction(request);
				break;

			/** 전체 회원관리 페이지 */
			/** 튜터마이페이지 */
			case "adminAllTutorDetailAction":
				page = adminAllTutorDetailAction(request);
				break;

			/** 튜티마이페이지 */
			case "adminAllTuteeDetailAction":
				page = adminAllTuteeDetailAction(request);
				break;

			/** 기업마이페이지 */
			case "adminAllCompanyDetailAction":
				page = adminAllCompanyDetailAction(request);
				break;
		// ----------------------------------------------------------------------------------------
		/** 관리자 튜터링 등록현황 시작*/
			/** 튜터링 리스트 목록*/
		case "adminTutoringList":
			page = adminTutoringList(request);
			break;
			/** 튜터링 리스트 페이징*/
		case "adminTutoringListPagingAction":
			page =adminTutoringListPagingAction(request);
			break;
			/**튜터링 상세*/
		case "adminTutoringListDetailAction":
			page = adminTutoringListDetailAction(request);
			break;
			/**튜터링 삭제*/
		case "adminTutoringDeleteAction":
			page = adminTutoringDeleteAction(request);
			break;
			/**튜터링 검색*/
		case "adminTutoringSearch":
			page = adminTutoringSearch(request);
			break;
		case "adminTutoringSearchPaging":
			page = adminTutoringSearchPaging(request);
			break;
			/**번호순, 최신순*/
		case "adminTutoringListSelectAction":
			page = adminTutoringListSelectAction(request);
			break;
		case "adminTutoringListSelectPaging":
			page = adminTutoringListSelectPaging(request);
			break;
			/**1개월*/
		case "adminTutoringListAmonth":
			page = adminTutoringListAmonth(request);
			break;
		case "adminTutoringListAmonthPagingAction":
			page = adminTutoringListAmonthPagingAction(request);
			break;
			/**3개월*/
		case "adminTutoringListThreeMonth":
			page = adminTutoringListThreeMonth(request);
			break;
		case "adminTutoringListThreeMonthPagingAction":
			page = adminTutoringListThreeMonthPagingAction(request);
			break;
			/**6개월*/
		case "adminTutoringListSixMonth":
			page = adminTutoringListSixMonth(request);
			break;
		case "adminTutoringListSixMonthPagingAction":
			page = adminTutoringListSixMonthPagingAction(request);
			break;
			/**날짜조회*/
		case "adminTutoringDateSelectAction":
			page = adminTutoringDateSelectAction(request);
			break;
		case "adminTutoringDateSelectPaging":
			page = adminTutoringDateSelectPaging(request);
			break;
			/**전체보기*/
		case "adminTutoringListAll":
			page = adminTutoringListAll(request);
			break;
		/** 관리자 튜터링 등록현황 끝*/	
		// ------------------------------------------------------------------------------------------	
		/**관리자 포트폴리오관리 시작*/	
			// 관리자 포트폴리오 관리	
			case "adminPortfolioList":
				page = adminPortfolioList(request);
				break;
			case "adminPortfolioListPaging":
				page = adminPortfolioListPaging(request);
				break;
			case "adminPortfolioListDetailAction":
				page = adminPortfolioListDetailAction(request);
				break;
			case "adminPortfolioDeleteAction":
				page = adminPortfolioDeleteAction(request);
				break;
			case "adminPortfolioSearch":
				page = adminPortfolioSearch(request);
				break;
			case "adminPortfolioSearchPaging":
				page = adminPortfolioSearchPaging(request);
				break;
			case "adminPortfolioListAmonth":
				page = adminPortfolioListAmonth(request);
				break;
			case "adminPortfolioListAmonthPaging":
				page = adminPortfolioListAmonthPaging(request);
				break;
			case "adminPortfolioListThreeMonth":
				page = adminPortfolioListThreeMonth(request);
				break;
			case "adminPortfolioListThreeMonthPaging":
				page = adminPortfolioListThreeMonthPaging(request);
				break;
			case "adminPortfolioListSixMonth":
				page = adminPortfolioListSixMonth(request);
				break;
			case "adminPortfolioListSixMonthPaging":
				page = adminPortfolioListSixMonthPaging(request);
				break;	
			case "adminPortfolioListAll":
				page = adminPortfolioListAll(request);
				break;
			case "adminPortfolioDateSelectAction":
				page = adminPortfolioDateSelectAction(request);
				break;
			case "adminPortfolioDateSelectPaging":
				page = adminPortfolioDateSelectPaging(request);
				break;
			case "adminPortfolioListSelectAction":
				page = adminPortfolioListSelectAction(request);
				break;
			case "adminPortfolioListSelectPaging":
				page = adminPortfolioListSelectPaging(request);
				break;
		/**관리자 포트폴리오관리 끝*/		
		// ------------------------------------------------------------------------------------------				
		/**관리자 직종 편집 시작*/	
			case "adminGetJobList":
				page = adminGetJobList(request);
				break;
			case "adminGetMiddleJobList":
				page = adminGetMiddleJobList(request);
				break;
			case "mainCategRegister":
				page = mainCategRegister(request);
				response.sendRedirect("/starters?cmd=adminGetJobList");
				return;
			case "middleCategRegister":
				page = middleCategRegister(request);
				response.sendRedirect("/starters?cmd=adminGetJobList");
				return;
			case "updateMainJob":
				page = updateMainJob(request);
				response.sendRedirect("/starters?cmd=adminGetJobList");
				return;
			case "selectMainJob":
				page = selectMainJob(request);
				break;
			case "updateMiddleJob":
				page = updateMiddleJob(request);
				response.sendRedirect("/starters?cmd=adminGetJobList");
				return;
			case "deleteMainJob":
				page = deleteMainJob(request);
				response.sendRedirect("/starters?cmd=adminGetJobList");
				return;
			case "deleteMiddleJob":
				page = deleteMiddleJob(request);
				response.sendRedirect("/starters?cmd=adminGetJobList");
				return;
			case "deleteSelectMainJob":
				page = deleteSelectMainJob(request);
				break;
				/**관리자 직종 편집 끝*/	
		// ------------------------------------------------------------------------------------------	
		/**관리자 후기게시판 시작*/		
				/**후기게시판 */
			case "adminReviewList":
				// 메인에서 회원구분
				page = adminTuteeMembersCategAction2(request);
				// 디테일에서 회원구분
				page = adminTuteeMemberCategAction2(request);
				/// 후기게시판 목록
				page = adminReviewList(request);
				// 후기게시판 게시글 수
				page = adminReviewListNumbers(request);
				// 후기게시판 튜터링명 선택
				page = adminSelectReviewTitle(request);
				page = adminModifySelectReviewTitle(request);
				// 베스트 후기
				page = adminBestReviewAction(request);
				break;
				/**좋아요*/
			case "adminReviewLikeAction":
				page = reviewLikeAction(request);
				break;		
			case "adminReviewLikeCount":
				page = reviewLikeCount(request);
				break;
				/**후기게시판 이미지*/
			case "adminReviewListImage":
				page = reviewListImage(request);
				break;
				/** 후기게시판 등록*/
			case "adminReviewRegisterAction":
				page =  adminReviewRegisterAction(request);
				response.sendRedirect("/starters?cmd=adminReviewList");
				return;
				/**후기게시판 수정*/
			case "adminReviewModified":
				page = adminReviewModifyAction(request);
				response.sendRedirect("/starters?cmd=adminReviewList");
				return;
				/**후기게시판 삭제*/
			case "adminReviewDeleteAction":
				page = reviewDeleteAction(request);
				response.sendRedirect("/starters?cmd=adminReviewList");
				return;
				/**후기게시판 id 값 가져오기*/
				/**조회수*/
			case "adminReviewNum":
				page = adminReviewNum(request);
				page = adminReviewCountIncrease(request);
				break;
				/** 후기게시판 리스트 페이징*/
			case "adminReviewListPagingAction":
				page =adminReviewListPagingAction(request);
				break;
				/** 검색 후기게시판 목록*/
			case "adminReviewListSearch":
				page =adminReviewListSearch(request);
				page = adminSelectReviewTitle(request);
				page = adminModifySelectReviewTitle(request);
				page = adminBestReviewAction(request);
				break;
			case "adminReviewListSearchPaging":
				page =adminReviewListSearchPaging(request);
				break;
				/** 조회수순, 좋아요순 튜터링 리스트*/
			case "adminReviewListSelectAction":
				page =adminReviewListSelectAction(request);
				break;
			case "adminReviewListSelectPaging":
				page =adminReviewListSelectPaging(request);
				break;
		/**관리자 후기게시판 끝*/		
				
		/**관리자 공지사항 시작*/
				// 공지사항
			case "adminNotice":
				page = adminNotice(request);
				// 튜터링 튜티, 튜터 등록 표시
				page = adminCategAction2(request);
				break;
			case "adminNoticeListPaging":
				page =adminNoticeListPaging(request);
				break;
			case "adminNoticeSearchAction":
				page =adminNoticeSearchAction(request);
				break;
			case "adminNoticeSearchPagingAction":
				page =adminNoticeSearchPagingAction(request);
				break;
				/**공지사항 상세*/
			case "adminNoticeListDetailAction":
				// 공지사항 상세
				page =adminNoticeListDetailAction(request);
				//			page =noticeListDetailPreAction(request);
				//			page =noticeListDetailAfterAction(request);
				page = adminNoticeCountIncrease(request, response);
				page = adminNoticeCateg2(request);
				break;				
			/**공지사항 삭제*/
			case "adminNoticeDelete":
				page = adminNoticeDelete(request);
				break;				
				/**공지사항 여러개삭제*/
			case "adminNoticeDeleteMany":
				page = adminNoticeDeleteMany(request);
				break;				
				/**공지사항 수정*/
			case "adminNoticeModify":
				page = adminNoticeModify(request);
				break;				
				/**포트폴리오 수정action*/
			case "adminNoticeModifyAction":
				page = adminNoticeModifyAction(request);
				break;
				/**이전글*/
			case "adminNoticeListDetailPreAjax":
				// 공지사항 상세
				page =adminNoticeListDetailPreAjax(request);
				break;
				/**다음글*/
			case "adminNoticeListDetailAfterAjax":
				// 공지사항 상세
				page =adminNoticeListDetailAfterAjax(request);
				break;
				/**공지사항 등록*/
			case "adminNoticeRegister":
				page = "adminCommunity/notice/noticeWrite.jsp";
				break;
			case "adminNoticeRegisterAction":
				page =adminNoticeRegisterAction(request);
				break;
				/** 조회수순, 좋아요순 공지사항 리스트*/
			case "adminNoticeListSelectAction":
				page =adminNoticeListSelectAction(request);
				break;
			case "adminNoticeListSelectPaging":
				page =adminNoticeListSelectPaging(request);
				break;
		/**관리자 공지사항 끝*/
				
		/**관리자 qna 시작*/
			case "adminQna":
				//			page = "community/qna/qnaListMain.jsp";
				page = adminQna(request);
				// 튜티, 튜터, 기업 등록 표시
				page = adminQnamemberCategAction2(request);
				break;
			case "adminQnaListPaging":
				page =adminQnaListPaging(request);
				page = adminQnamemberCategActionPaging(request);
				break;
				/**qna 등록*/
			case "adminQnaRegister":
				page = "adminCommunity/qna2/qnaWrite.jsp";
				break;
			case "adminQnaRegisterAction":
				page =  adminQnaRegisterAction(request, response);
				break;
				//			/**qna 상세*/
			case "adminQnaListDetailAction":
				// qna 상세
				//			page = qnaPasswdDetailAction(request);
				page =adminQnaListDetailAction(request);
				page = adminQnaCountIncrease(request, response);
				page = adminQnaWriter(request);
				page = adminComment(request);
				break;
			case "adminQnaSearchAction":
				page =adminQnaSearchAction(request);
				break;
			case "adminQnaSearchPagingAction":
				page =adminQnaSearchPagingAction(request);
				break;
				/**qna 수정*/
			case "adminQnaModify":
				page = adminQnaModify(request);
				break;
				/**qna 수정action*/
			case "adminQnaModifyAction":
				page = adminQnaModifyAction(request);
				break;				
				/**qna 삭제*/
			case "adminQnaDeleteAction":
				page = adminQnaDeleteAction(request);
				break;
			case "adminCommentRegister":
				page = adminCommentRegister(request, response);
				break;
			case "adminCommentDelete":
				page = adminCommentDelete(request);
				break;
				/**qna 수정_답글*/
			case "adminQnaCommentReply":
				page = adminQnaCommentReply(request);
				break;
				/**qna 수정_답글*/
			case "adminQnaCommentReplyAction":
				page = adminQnaCommentReplyAction(request);
				break;
				/**qna 수정_답글*/
			case "adminQnaCommentUpdate":
				page = adminQnaCommentUpdate(request);
				break;
				/**qna 수정_답글*/
			case "adminQnaCommentUpdateAction":
				page = adminQnaCommentUpdateAction(request);
				break;	
		/**관리자 qna 끝*/

		/**관리자 면접신청가격 변경 시작*/
			case "adminInterviewPay":
				page = "adminInterviewPay/adminInterviewPay.jsp";
				page = adminInterviewPay(request);
				break;
			case "registerInterviewPayAction":
				page = registerInterviewPayAction(request);
				break;
		/**관리자 면접신청가격 변경 끝*/				
				
		// ------------------------------------------------------------------------------------------	
			case "admin":
			page = "admin/admin.jsp";
			break;
		case "sitemap":
			page = "sitemap.jsp";
			page = memberCategFinal(request);
			break;
		case "intro":
			page = "introduce.jsp";
			break;
		case "chat": /** 입력값 */
			// page = "chatting/chat.jsp";
			page = "chatting/find.jsp";
			page = chatMembers(request);
			page = chatMembers2(request);
			page = chatMemberCheck(request);
			break;
		case "chatStart": /** 입력값 */
			page = "chatting/chat.jsp";
			break;
		// 튜터찾기
		case "chatfindId": /** 입력값 */
			page = chatfindId(request, response);

			break;
		// 튜티찾기
		case "chatfindTuteeId": /** 입력값 */
			page = chatfindTuteeId(request, response);
			break;
		case "chatList": /** 입력값 */
			page = chatList(request, response);
			break;
		case "chatAction": /** 입력값 */
			page = chat(request, response);
			break;
		case "chatUnread":
			page = chatUnread(request, response);
			break;

		case "messageBoxMain":
			page = "chatting/messageBox.jsp";
			break;
		case "messageBox":
			page = messageBox(request, response);
			break;
		case "login": /** 입력값 */
			page = "login/login.jsp";
			break;
		case "loginAction":
			page = loginAction(request, response);
			// page = nav(request);
			break;
		/** 로그아웃 */
		case "logoutAction":
			page = logoutAction(request);
			break;
		/** 튜터 멤버 목록 */
		case "tutorMembers":
			page = tutorMembers(request);
			break;
		case "tuteeMembers":
			page = tuteeMembers(request);
			break;
		/** 아이디 찾기 */
		case "findId":
			page = "login/findId.jsp";
			break;
		case "findIdAction":
			page = findIdAction(request);
			break;
		/** 비밀번호 찾기 */
		case "findpw":
			page = "login/findpw.jsp";
			break;
		case "findPasswordAction":
			page = findPasswordAction(request);
			break;
		case "findAfter":
			page = "main.jsp";
			break;

		case "deleteTutorMemberAction":
			page = deleteTutorMemberAction(request);
			break;

		case "deleteCompanyMemberAction":
			page = deleteCompanyMemberAction(request);
			break;
		/** 회원가입 */
		/** 회원 카테고리 */
		case "registerCateg":
			page = "register/registercateg.jsp";
			break;

		/** 튜티 회원 */
		case "registerTutee":
			page = "register/registerTutee.jsp";
			/** 튜티회원가입 대분류 */
			page = TuteeLookJobCateg(request);
			break;
		// /**튜티회원가입 중분류*/
		// case "TuteeLookJobMiddleCateg":
		// page = TuteeLookJobMiddleCateg(request);
		// break;
		case "registerTuteeAction":
			page = registerTuteeAction(request);
			// page = JobSelectAction(request);
			break;

		/** 튜터회원 */
		case "registerTutor":
			page = "register/registerTutor.jsp";
			/** 튜터회원가입 대분류 */
			page = TutorLookJobCateg(request);
			break;
		// 대분류 중분류 통합할려보니 회원가입 화면이 분리되어있어 분류코드도 분리하기
		/** 튜터회원가입 중분류 */
		// case "TutorLookJobMiddleCateg":
		// /**튜터회원가입 중분류*/
		// page = TutorLookJobMiddleCateg(request);
		// break;
		case "registerTutorAction":
			page = registerTutorAction(request);
			break;

		/** 기업회원 */
		case "registerCompany":
			page = "register/registerCompany.jsp";
			break;
		case "registerCompanyAction":
			try {
				page = registerCompanyAction(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		/** 아이디 중복 확인 */
		// case "memberIdCheck":
		// page = memberIdCheck(request);
		// break;

		case "memberIdCheck2":
			page = "register/idCheckForm.jsp";
			break;

		case "memberIdCheck3":
			page = memberIdCheck3(request, response);
			break;

		/** 튜티 튜터링 리스트 */
		case "tutoringListImage":
			// 튜터링 리스트 목록
			page = tutoringListImage(request);
			break;

		/** 튜티 튜터링 리스트 */
		case "tutoringList":
			// 튜터링 리스트 목록
			page = tutoringList(request);
			// 튜터링 리스트 목록
			// page = tutoringListImage(request);
			// 튜터링 리스트 게시글 수
			page = tutoringListNumber(request);
			// 튜터링 튜티, 튜터 등록 표시
			page = memberCategAction(request);
			break;
		/** 튜터링 리스트 페이징 */
		case "TutoringListPagingAction":
			page = TutoringListPagingAction(request);
			break;
		/** 검색 튜터링 리스트 */
		case "TutoringListAction":
			page = TutoringListAction(request);
			break;
		case "TutoringListSearchPaging":
			page = TutoringListSearchPaging(request);
			break;
		/** 튜터링 삭제 */
		case "tutoringDeleteAction":
			page = tutoringDeleteAction(request);
			break;
		/** 튜터링 상세 */
		case "tutoringListDetailAction":
			// 튜터링 상세
			page = tutoringListDetailAction(request);
			// 튜티 측면 - 튜터링 찜하기, 신청하기
			page = membersCategAction(request);
			// 튜터 측면 - 튜터링 삭세하기, 수정하기
			page = tutoringWriter(request); // 글쓴이 체크
			// 튜터 측면 - 튜티가 신청한 튜터링인지 아닌지 확인
			page = tutoringApplyAction(request);
			// 조회수 증가
			page = tutoringCountIncrease(request, response);
			break;
		/** 튜터링 등록 */
		case "tutoringRegister":
			page = "tutoring/tutoringRegister.jsp";
			page = tutoringLookJobCateg(request);
			break;
		case "tutoringRegisterAction":
			page = tutoringRegisterAction(request);
			break;
		// 수정페이지
		case "tutoringModify":
			page = tutoringModify(request);
			page = tutoringModeifyLookJobCateg(request);
			break;
		// 수정액션
		case "tutoringModifyAction":
			page = tutoringModifyAction(request);
			break;

		// 1010 추가
		/** 조회수순, 좋아요순 튜터링 리스트 */
		case "TutoringListSelectAction":
			page = TutoringListSelectAction(request);
			break;
		case "TutoringListSelectPaging":
			page = TutoringListSelectPaging(request);
			break;

		/** 회사 리스트 */
		case "companyList":
			page = companyList(request);
			// page = companyListDetailAction(request);
			// page = companyListModal(request);

			break;
		/** 회사 리스트 */
		case "companyListModal":
			// page = companyList(request);
			// page = companyListDetailAction(request);
			page = companyListModal(request);
			break;
		// case "compnayDetailAction":
		// page = companyListDetailAction(request);
		// break;
		// case "compnayModalDetailAction":
		// page = compnayModalDetailAction(request);
		// break;
		/** 회사 리스트 페이징 */
		case "companyListPagingAction":
			page = companyListPagingAction(request);
			break;
		/** 검색 회사 리스트 */
		case "companySearchListAction":
			page = companySearchListAction(request);
			break;
		/** 검색 회사 리스트 페이징 */
		case "companySearchListPaging":
			page = companySearchListPaging(request);
			break;

		/** 포트폴리오 */

		/** 포트폴리오 리스트 */
		case "portfolioList":
			// 포트폴리오 리스트 목록
			page = portfolioList(request);
			// 포트폴리오 리스트 게시글 수
			page = portfolioListNumber(request);
			// 포트폴리오 튜티, 튜터 등록 표시
			page = portfolioMemberCategAction(request);
			break;

		case "portfolioListImage":
			page = portfolioListImage(request);
			break;

		/** 포트폴리오 리스트 페이징 */
		case "PortfolioListPagingAction":
			page = PortfolioListPagingAction(request);
			break;

		/** 검색 포트폴리오 리스트 */
		case "PortfolioListAction":
			page = PortfolioListAction(request);
			break;
		case "PortfolioListSearchPaging":
			page = PortfolioListSearchPaging(request);
			break;

		/** 포트폴리오 삭제 */
		case "portfolioDeleteAction":
			page = portfolioDeleteAction(request);
			break;

		/** 포트폴리오 상세 */
		case "portfolioListDetailAction":
			// 포트폴리오 상세
			page = portfolioListDetailAction(request);
			// 기업 측면 - 포트폴리오 찜하기, 면접 신청하기
			// page = membersCategAction(request);
			// 튜티 측면 - 포트폴리오 삭세하기, 수정하기
			page = portfolioWriter(request); // 글쓴이 체크
			page = portfolioExistAction(request);
			// 조회수 증가
			page = portfolioCountIncrease(request, response);
			page = portfolioLike(request);
			break;

		case "portfolioLikeAction":
			page = portfolioLikeAction(request);
			break;
		/** 포트폴리오 등록 */
		case "portfolioRegister":
			page = "portfolio/portfolioRegister.jsp";
			page = PortfolioLookJobCateg(request);
			break;

		case "portfolioRegisterAction":
			page = portfolioRegisterAction(request);
			break;

		/** 포트폴리오 수정 */
		case "portfolioModify":
			page = portfolioModify(request);
			page = portfolioModifyLookJobCateg(request);
			break;

		/** 포트폴리오 수정action */
		case "portfolioModifyAction":
			page = portfolioModifyAction(request);
			break;

		// 1012 추가
		/** 조회수순, 좋아요순 튜터링 리스트 */
		case "portfolioListSelectAction":
			page = portfolioListSelectAction(request);
			break;
		case "portfolioListSelectPaging":
			page = portfolioListSelectPaging(request);
			break;

		/** 커뮤니티 */

		/** 후기게시판 */
		case "reviewList":
			// 메인에서 회원구분
			page = tuteeMembersCategAction(request);
			// 디테일에서 회원구분
			page = tuteeMemberCategAction(request);
			/// 후기게시판 목록
			page = reviewList(request);
			// 후기게시판 게시글 수
			page = reviewListNumbers(request);
			// 후기게시판 튜터링명 선택
			page = selectReviewTitle(request);
			page = modifySelectReviewTitle(request);
			// 베스트 후기
			page = bestReviewAction(request);
			break;

		/** 좋아요 */
		case "reviewLikeAction":
			page = reviewLikeAction(request);
			break;
		case "reviewLikeCount":
			page = reviewLikeCount(request);
			break;
		/** 후기게시판 이미지 */
		case "reviewListImage":
			page = reviewListImage(request);

			break;
		/** 후기게시판 등록 */
		case "reviewRegisterAction":
			page = reviewRegisterAction(request);
			response.sendRedirect("/starters?cmd=reviewList");
			return;
		/** 후기게시판 수정 */
		case "reviewModified":
			page = reviewModifyAction(request);
			response.sendRedirect("/starters?cmd=reviewList");
			return;
		/** 후기게시판 삭제 */
		case "reviewDeleteAction":
			page = reviewDeleteAction(request);
			response.sendRedirect("/starters?cmd=reviewList");
			return;
		/** 후기게시판 id 값 가져오기 */
		/** 조회수 */
		case "reviewNum":
			page = reviewNum(request);
			page = reviewCountIncrease(request);
			break;

		/** 후기게시판 리스트 페이징 */
		case "reviewListPagingAction":
			page = reviewListPagingAction(request);
			break;
		/** 검색 후기게시판 목록 */
		case "reviewListSearch":
			page = reviewListSearch(request);
			page = selectReviewTitle(request);
			page = modifySelectReviewTitle(request);
			page = bestReviewAction(request);
			break;
		case "reviewListSearchPaging":
			page = reviewListSearchPaging(request);
			break;

		// 1012 추가
		/** 조회수순, 좋아요순 튜터링 리스트 */
		case "reviewListSelectAction":
			page = reviewListSelectAction(request);
			break;
		case "reviewListSelectPaging":
			page = reviewListSelectPaging(request);
			break;

		///////////// 리뷰 끝///////////////////

			/** 공지사항 시작//////////////////// */
			// 공지사항
			case "notice":
				page = notice(request);
				// 튜터링 튜티, 튜터 등록 표시
				page = adminCategAction(request);
				break;

			case "noticeListPaging":
				page = noticeListPaging(request);
				page = adminCategActionPaging111(request);
				break;


			case "noticeSearchAction":
				page = noticeSearchAction(request);
				page = adminCategAction(request);
				break;

			case "noticeSearchPagingAction":
				page = noticeSearchPagingAction(request);
				page = adminCategActionPaging(request);
				break;

			/** 공지사항 상세 */
			case "noticeListDetailAction":
				// 공지사항 상세
				page = noticeListDetailAction(request);
				// page =noticeListDetailPreAction(request);
				// page =noticeListDetailAfterAction(request);
				page = noticeCountIncrease(request, response);
				page = noticeCateg(request);
				break;

			// 1026추가
			/** 공지사항 삭제 */
			case "noticeDelete":
				page = noticeDelete(request);
				break;

			/** 공지사항 여러개삭제 */
			case "noticeDeleteMany":
				page = noticeDeleteMany(request);
				break;

			/** 공지사항 수정 */
			case "noticeModify":
				page = noticeModify(request);
				break;

			/** 포트폴리오 수정action */
			case "noticeModifyAction":
				page = noticeModifyAction(request);
				break;

			/** 이전글 */
			case "noticeListDetailPreAjax":
				// 공지사항 상세
				page = noticeListDetailPreAjax(request);
				break;
			/** 다음글 */
			case "noticeListDetailAfterAjax":
				// 공지사항 상세
				page = noticeListDetailAfterAjax(request);
				break;

			/** 공지사항 등록 */
			case "noticeRegister":
				page = "community/notice/noticeWrite.jsp";
				break;

			case "noticeRegisterAction":
				page = noticeRegisterAction(request);
				break;

			// 1010 추가
			/** 조회수순, 좋아요순 공지사항 리스트 */
			case "noticeListSelectAction":
				page = noticeListSelectAction(request);
				page = adminCategActionSelect(request);
				break;
			case "noticeListSelectPaging":
				page = noticeListSelectPaging(request);
				page = adminCategActionSelect(request);
				break;

		// qna시작/////////////////////////
		case "qna":
			// page = "community/qna/qnaListMain.jsp";
			page = qna(request);
			// 튜티, 튜터, 기업 등록 표시
			page = qnamemberCategAction(request);
			break;
		//
		case "qnaListPaging":
			page = qnaListPaging(request);
			break;

		/** qna 등록 */
		case "qnaRegister":
			page = "community/qna2/qnaWrite.jsp";
			break;

		case "qnaRegisterAction":
			page = qnaRegisterAction(request, response);
			break;

		// /**qna 상세*/
		case "qnaListDetailAction":
			// qna 상세
			// page = qnaPasswdDetailAction(request);
			page = qnaListDetailAction(request);
			page = qnaCountIncrease(request, response);
			page = qnaWriter(request);
			page = comment(request);

			break;

		case "qnaSearchAction":
			page = qnaSearchAction(request);
			break;

		case "qnaSearchPagingAction":
			page = qnaSearchPagingAction(request);
			break;

		/** qna 수정 */
		case "qnaModify":
			page = qnaModify(request);
			break;

		/** qna 수정action */
		case "qnaModifyAction":
			page = qnaModifyAction(request);
			break;
		/** qna 삭제 */
		case "qnaDeleteAction":
			page = qnaDeleteAction(request);
			break;

		case "commentRegister":
			page = commentRegister(request, response);
			break;

		case "commentDelete":
			page = commentDelete(request);
			break;

		/** qna 수정_답글 */
		case "qnaCommentReply":
			page = qnaCommentReply(request);
			break;

		/** qna 수정_답글 */
		case "qnaCommentReplyAction":
			page = qnaCommentReplyAction(request);
			break;

		/** qna 수정_답글 */
		case "qnaCommentUpdate":
			page = qnaCommentUpdate(request);
			break;

		/** qna 수정_답글 */
		case "qnaCommentUpdateAction":
			page = qnaCommentUpdateAction(request);
			break;

		/** 튜터마이페이지 */
		case "tutorDetailAction":
			page = tutorDetailAction(request);
			break;

		/** 튜터 수정 */
		case "tutorModify":
			page = tutorModify(request);
			page = tutorModifyLookJobCateg(request);
			break;

		/** 튜터 수정action */
		case "tutorModifyAction":
			page = tutorModifyAction(request);
			break;

		case "tutortutoringListImage":
			page = tutortutoringListImage(request);
			break;

		/** 튜터 튜터링 리스트 */
		case "tutorTutoringList":
			// 튜터링 리스트 목록
			page = tutorTutoringList(request);
			// 튜터링 리스트 게시글 수
			page = tutorTutoringListNumber(request);

			break;
		/** 튜터링 리스트 페이징 */
		case "tutorTutoringListPagingAction":
			page = tutorTutoringListPagingAction(request);
			break;
		case "tutorTutoringCalenderList1":
			page = tutorTutoringCalenderList1(request);
			break;

		// 1028 수정
		/** 튜터 튜터링 튜티 리스트 */
		case "tutorTutoringTuteeList":
			// 튜터링 리스트 목록
			page = tutorTutoringTuteeList(request);
			// 튜터링 리스트 게시글 수
			page = tutorTutoringTuteeListNumber(request);

			break;
		/** 튜터링 리스트 페이징 */
		case "tutorTutoringTuteeListPagingAction":
			page = tutorTutoringTuteeListPagingAction(request);
			break;
		case "tutortutoringtuteeListImage":
			page = tutortutoringtuteeListImage(request);
			break;

		case "applyDailyList":
			page = applyDailyList(request);
			page = DailyListTitleName(request);
			break;

		case "tutortutoringDailyDetail":
			page = tutortutoringDailyDetail(request);
			break;

		case "tutoringDailyModifyAction":
			page = tutoringDailyModifyAction(request);
			break;
		case "tutoringDailyModify":
			page = tutoringDailyModify(request);
			break;

		/** 수강신청내역 */
		case "tutoringInfo":
			// 수강신청 내역 리스트
			page = tutoringInfo(request);
			// 수강신청 내역 리스트 글 수
			page = reviewListNumber(request);
			break;
		// 수강신청 내역 페이징
		case "tutoringInfoListPagingAction":
			page = tutoringInfoListPagingAction(request);
			break;
		case "applyNum":
			page = applyNum(request);
			break;
		// 수강신청 내역 삭제하기
		case "applyListDeleteAction":
			page = applyListDeleteAction(request);
			break;
		// 튜터링 수업 신청 후 수강내역에 등록
		case "tutoringApplyRegisterAction":
			page = tutoringApplyRegisterAction(request);
			break;

		// 전체보기 검색
		case "tutoringInfoListAmonth":
			page = tutoringInfoListAmonth(request);
			break;
		// 전체보기 검색 페이징 필요없음 원래 list로 넘어가면서 자연스레 페이징 됨
		/*
		 * case "tutoringInfoPagingAction": page =
		 * tutoringInfoPagingAction(request); break;
		 */
		// 튜터링 날짜 조회
		case "tutoringDateSelectAction":
			page = tutoringDateSelectAction(request);
			break;
		// 튜터링 날짜 조회 페이징
		case "tutoringDateSelectPaging":
			page = tutoringDateSelectPaging(request);
			break;
		// 1개월 검색
		case "tutoringInfoList":
			page = tutoringInfoList(request);
			break;

		// 1개월 검색 페이징
		case "tutoringInfoListAmonthSearchPaging":
			page = tutoringInfoListAmonthSearchPaging(request);
			break;
		// 3 개월 검색
		case "tutoringInfoListThreeMonth":
			page = tutoringInfoListThreeMonth(request);
			break;
		// 3개월 검색 페이징
		case "tutoringInfoListThreeMonthSearchPaging":
			page = tutoringInfoListThreeMonthSearchPaging(request);
			break;
		// 3 개월 검색
		case "tutoringInfoListSixMonth":
			page = tutoringInfoListSixMonth(request);
			break;
		// 3개월 검색 페이징
		case "tutoringInfoListSixMonthSearchPaging":
			page = tutoringInfoListSixMonthSearchPaging(request);
			break;

		// 진행전 //1101 추가
		case "tutoringInfoBefore":
			page = tutoringInfoBefore(request);
			break;
		// 진행전 페이징
		case "tutoringInfoBeforePaging":
			page = tutoringInfoBeforePaging(request);
			break;
		// 진행중
		case "tutoringInfoIng":
			page = tutoringInfoIng(request);
			break;
		// 진행중 페이징
		case "tutoringInfoIngPaging":
			page = tutoringInfoIngPaging(request);
			break;
		// 완료
		case "tutoringInfoAfter":
			page = tutoringInfoAfter(request);
			break;
		// 완료 페이징
		case "tutoringInfoAfterPaging":
			page = tutoringInfoAfterPaging(request);
			break;

		/** 튜티 정보 수정 */
		case "tuteeDetailAction":
			page = tuteeDetailAction(request);
			break;
		case "tuteeModify":
			page = tuteeModify(request);
			page = tuteeModifyLookJobCateg(request);
			break;
		case "tuteeModifyAction":
			page = tuteeModifyAction(request);
			break;
		// 회원 탈퇴
		case "deleteTuteeMemberAction":
			page = deleteTuteeMemberAction(request);
			break;

		/** 찜하기 */
		// 목록
		case "tutoringLike":
			page = tutoringLike(request);
			break;
		case "wishNum":
			page = wishNum(request);
			break;
		// 목록 페이징
		case "tutoringLikePaging":
			page = tutoringLikePaging(request);
			break;
		// 찜하기 취소
		case "wishListDeleteAction":
			page = wishListDeleteAction(request);
			break;
		// 찜하기
		case "tutoringlikeRegisterAction":
			page = tutoringlikeRegisterAction(request);
			break;

		/** 튜티마이페이지 - 마이페이지 */
		case "tuteeMypageList":
			page = tuteeMypageList(request);
			break;
		case "tuteeMypageListPaging":
			page = tuteeMypageListPaging(request);
			break;
		case "tuteetutoringListImage":
			page = tuteetutoringListImage(request);
			break;

		/** 면접신청 내역 */
		case "interviewList":
			page = interviewList(request);
			break;
		case "interviewListPaging":
			page = interviewListPaging(request);
			break;
		case "interviewNum":
			page = interviewNum(request);
			break;
		// 기업정보 더보기
		case "moreInfoCompany":
			page = moreInfoCompany(request);
			break;
		// 면접 거절
		case "noInterview":
			page = noInterview(request);
			break;
		// 면접 수락
		case "okInterview":
			page = okInterview(request);
			break;
		// 1개월 검색 리스트
		case "interviewInfoListAmonth":
			page = interviewInfoListAmonth(request);
			break;
		// 1개월 검색 페이징
		case "interviewInfoListAmonthSearchPaging":
			page = interviewInfoListAmonthSearchPaging(request);
			break;
		// 3개월 검색 리스트
		case "interviewInfoListThreeMonth":
			page = interviewInfoListThreeMonth(request);
			break;
		// 3개월 검색 페이징
		case "interviewInfoListThreeMonthSearchPaging":
			page = interviewInfoListThreeMonthSearchPaging(request);
			break;
		// 3개월 검색 리스트
		case "interviewInfoListSixMonth":
			page = interviewInfoListSixMonth(request);
			break;
		// 3개월 검색 페이징
		case "interviewInfoListSixMonthSearchPaging":
			page = interviewInfoListSixMonthSearchPaging(request);
			break;
		// 전체보기
		case "interviewAll":
			page = interviewAll(request);
			break;
		// 날짜 검색 리스트
		case "interviewDateSelectAction":
			page = interviewDateSelectAction(request);
			break;
		// 날짜 검색 페이징
		case "interviewDateSelectPaging":
			page = interviewDateSelectPaging(request);
			break;
		// 면접신청정보 더보기
		case "interviewMoreInfo":
			page = interviewMoreInfo(request);
			break;

		/** 튜티일지 */
		// 리스트
		case "tuteeDailyList":
			page = tuteeDailyList(request);
			page = dailyInfo(request);
			break;
		// 상세
		case "tuteeDailyDetail":
			page = tuteeDailyDetail(request);
			page = dailyInfo4(request);
			break;
		// 등록
		case "tuteeDailyRegister":
			page = tuteeDailyRegister(request);
			page = dailyInfo3(request);
			break;
		case "tuteeDailyRegisterAction":
			page = tuteeDailyRegisterAction(request);
			break;
		// 수정
		case "tuteeDailyModify":
			page = tuteeDailyModify(request);
			page = dailyInfo2(request);
			break;
		case "tuteeDailyModifyAction":
			page = tuteeDailyModifyAction(request);
			break;

		/** 기업 마이페이지 */

		/** 포트폴리오 찜한 목록 */
		case "companyLikeList":
			page = companyLike(request);
			break;
		// 찜한 목록 페이징
		case "companyLikePaging":
			page = companyLikePaging(request);
			break;
		case "companyLikeAction":
			page = companyLikeAction(request);
			break;
		// 찜하기 취소
		case "companyLikeDeleteAction":
			page = companyLikeDeleteAction(request);
			break;
		case "companywishNum":
			page = companywishNum(request);
			break;
		case "companywishImage":
			page = companywishImage(request);
			break;

		/** 포트폴리오 상세페이지에서 면접신청하기버튼 클릭해서 면접신청하기 */
		case "companyInterviewApply":
			page = companyInterviewApply(request);
			page = companyInterviewCateg(request);
			break;
		// 면접신청할 때 포트폴리오 id 끌어오기
		case "interviewPortfolioId":
			page = interviewPortfolioId(request);
			break;
		// 면접신청하기
		case "interviewApplyAction":
			page = interviewApplyAction(request);
			break;

		/** 면접 신청 내역 */
		case "companyInterview":
			// 면접 신청 내역 리스트
			page = companyInterview(request);
			// 면접 신청 내역 리스트 글 수
			page = interviewListNumber(request);
			break;
		// 면접 신청 내역 리스트 페이징
		case "interviewListPagingAction":
			page = interviewListPagingAction(request);
			break;
		// 전체보기 검색
		case "interviewListAll":
			page = interviewListAll(request);
			break;
		// 1개월 검색
		case "interviewListAmonth":
			page = interviewListAmonth(request);
			break;
		// 1개월 검색 페이징
		case "interviewListAmonthSearchPaging":
			page = interviewListAmonthSearchPaging(request);
			break;
		// 3 개월 검색
		case "interviewListThreeMonth":
			page = interviewListThreeMonth(request);
			break;
		// 3개월 검색 페이징
		case "interviewListThreeMonthSearchPaging":
			page = interviewListThreeMonthSearchPaging(request);
			break;
		// 6 개월 검색
		case "interviewListSixMonth":
			page = interviewListSixMonth(request);
			break;
		// 6개월 검색 페이징
		case "interviewListSixMonthSearchPaging":
			page = interviewListSixMonthSearchPaging(request);
			break;
		// 면접 신청 내역 날짜 조회
		case "companyinterviewDateSelectAction":
			page = companyinterviewDateSelectAction(request);
			break;
		// 면접 신청 내역 날짜 조회 페이징
		case "companyinterviewDateSelectPaging":
			page = companyinterviewDateSelectPaging(request);
			break;

		/** 기업 정보 */
		case "companyInfo":
			page = companyInfo(request);
			break;
		// 기업 정보
		case "companyInfoDetailAction":
			page = companyInfoDetailAction(request);
			break;
		// 기업 정보 수정
		case "companyInfoModifyAction":
			page = companyInfoModifyAction(request);
			break;
		// 1028 수정
		case "companyInterviewPay":
			page = companyInterviewPay(request);
			break;
		case "interviewPayCancel":
			page = interviewPayCancel(request);
			break;
		case "interviewPayMoney":
			page = interviewPayMoneys(request);
			break;
		case "companyInterviewApplySuccess":
			page = "companyMypage/companyInterviewApplySuccess.jsp";
			break;

		// 튜터링 신청
		case "tutoringApply":
			page = tutoringApply(request);
			break;

		}

		request.getRequestDispatcher("/" + page).forward(request, response); // 4.실제
																				// 전송은
																				// 해당
																				// html,
																				// jsp
																				// 페이지가
																				// 담당한다.
	}
	
	private String adminMain(HttpServletRequest request) {
		admin = new adminMemberDAO();
		int tutee = admin.tutee();
		request.setAttribute("tutee", tutee);
		
		int tutor =  admin.tutor();
		request.setAttribute("tutor", tutor);
		
		int company =  admin.company();
		request.setAttribute("company", company);
		
		int tutoring =  admin.tutoring();
		request.setAttribute("tutoring", tutoring);
		
		int portfolio =  admin.portfolio();
		request.setAttribute("portfolio", portfolio);
		
		int tutoringBuy =  admin.tutoringBuy();
		request.setAttribute("tutoringBuy", tutoringBuy);
		
		int interview =  admin.interview();
		request.setAttribute("interview", interview);
		
		return "adminMain.jsp";
	}


	/**관리자 직종편집 시작*/
	// 중분류 삭제
			private String deleteMiddleJob(HttpServletRequest request) throws ServletException, IOException{
				job = new JobDAO(); 
				System.out.println("미들카테고리 삭제!!!!!!!");
				
				int middleCategId = Integer.parseInt(request.getParameter("selectBox5"));
				System.out.println("middleCategId"+middleCategId);
				boolean result = job.deleteMiddleJob(middleCategId);
				if (result)
					return "starters?cmd=adminGetJobList";
				return "admin/jobCategory/jobList.jsp"; 
			}
			
			// 중분류 삭제 위해 메인select
			private String deleteSelectMainJob(HttpServletRequest request) throws ServletException, IOException{
				job = new JobDAO(); 
				System.out.println("삭제를 위해 메인카테고리의 중분류 들고오기!!!!!!!");
				
				int deleteMiddleMainId=Integer.parseInt(request.getParameter("deleteMiddleCategId"));
				
				ArrayList<JobVO> selectMiddleModi =job.adminGetMiddleJob(deleteMiddleMainId);
				request.setAttribute("selectMiddleModi", selectMiddleModi);
				System.out.println("selectMiddleModi"+selectMiddleModi);

				return "admin/jobCategory/selectMiddleJob.jsp";
			}
		
		// 대분류 삭제
		private String deleteMainJob(HttpServletRequest request) throws ServletException, IOException{
			job = new JobDAO(); 
			System.out.println("메인카테고리 삭제!!!!!!!");
		
			int deleteMainId = Integer.parseInt(request.getParameter("deleteMainId"));
			
			boolean result = job.deleteMainJob(deleteMainId);

			if (result)
				return "starters?cmd=adminGetJobList";
			return "admin/jobCategory/jobList.jsp"; 
		}
		
		// 중분류 수정
		private String updateMiddleJob(HttpServletRequest request) throws ServletException, IOException{
			job = new JobDAO(); 
			System.out.println("미들카테고리 수정!!!!!!!");
			
			HttpSession session = request.getSession();
			int mainId = (int)session.getAttribute("mainId");
			System.out.println("mainId" + mainId);
			
			int middleCategId = Integer.parseInt(request.getParameter("selectBox4"));
			String middleName = request.getParameter("middleNameModify");
			
			boolean result = job.updateMiddleJob(middleName,mainId,middleCategId);

			if (result)
				return "starters?cmd=adminGetJobList";
			return "admin/jobCategory/jobList.jsp"; 
		}
		
		// 중분류 수정을 위해 메인select
		private String selectMainJob(HttpServletRequest request) throws ServletException, IOException{
			job = new JobDAO(); 
			System.out.println("메인카테고리의 중분류 들고오기!!!!!!!");

			
			int mainId=Integer.parseInt(request.getParameter("selectMainCategId"));
			
			HttpSession session = request.getSession(true);
			session.setAttribute("mainId", mainId);
			System.out.println("mainId"+mainId);
			
			ArrayList<JobVO> selectMiddleModi =job.adminGetMiddleJob(mainId);
			request.setAttribute("selectMiddleModi", selectMiddleModi);
			System.out.println("selectMiddleModi"+selectMiddleModi);

			return "admin/jobCategory/selectMiddleJob.jsp";
		}
		
		// 대분류 수정
		private String updateMainJob(HttpServletRequest request) throws ServletException, IOException{
			job = new JobDAO(); 
			System.out.println("메인카테고리 수정!!!!!!!");

			int mainId = Integer.parseInt(request.getParameter("selectBox2"));
			String mainName = request.getParameter("mainNameModify");
			
			boolean result = job.updateMainJob(mainName,mainId);

			if (result)
				return "starters?cmd=adminGetJobList";
			return "admin/jobCategory/jobList.jsp"; 
		}
		
		// 중분류 추가
		private String middleCategRegister(HttpServletRequest request) throws ServletException, IOException{
			job = new JobDAO(); 
			System.out.println("미들카테고리 등록!!!!!!!");

			int mainId = Integer.parseInt(request.getParameter("selectBox"));
			String middleName = request.getParameter("middleName");
			
			boolean result = job.insertMiddleJob(middleName,mainId);

			if (result)
				return "starters?cmd=adminGetJobList";
			return "admin/jobCategory/jobList.jsp"; 
		}
		
		// 대분류 추가
		private String mainCategRegister(HttpServletRequest request) throws ServletException, IOException{
			job = new JobDAO(); 
			System.out.println("메인카테고리 등록!!!!!!!");

			String mainName = request.getParameter("mainName");
			
			boolean result = job.insertMainJob(mainName);

			if (result)
				return "starters?cmd=adminGetJobList";
			return "admin/jobCategory/jobList.jsp"; 
		}
		
		// 관리자 직종 편집 리스트
		 private String adminGetJobList(HttpServletRequest request) {
			job = new JobDAO(); 
			System.out.println("관리자 직종편집 리스트==============");
			//메인만 출력
			ArrayList<MainJobVO> mainJob = job.adminGetMainJob();
			request.setAttribute("mainJob", mainJob); 
			System.out.println("mainJob"+mainJob);
			
			return"admin/jobCategory/jobList.jsp"; 
		 }
		 // 중분류 가져오기
		 private String adminGetMiddleJobList(HttpServletRequest request) {
			job = new JobDAO(); 
			System.out.println("관리자 직종편집 중분류==============");
			// 해당 메인카테고리의 중분류들
			int num=Integer.parseInt(request.getParameter("mainCategId"));
			System.out.println("num"+num);
			ArrayList<JobVO> selectMain =job.adminGetMiddleJob(num);
			request.setAttribute("selectMain", selectMain);
			System.out.println("selectMain"+selectMain);
		
			return"admin/jobCategory/jobMiddleList.jsp"; 
		 }
	/**관리자 직종편집 끝*/
	
//===================================================
/**관리자 면접신청가격 변경 시작*/	
	// 면접 신청 가격 변경
	private String registerInterviewPayAction(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		
		int payMoney = Integer.parseInt(request.getParameter("payMoney"));
		boolean result = dao6.updatetPayMoney(payMoney);
//		System.out.println("result/////////"+result);
		
		if (result)
			return "adminInterviewPay/adminInterviewPaySuccess.jsp";
		return "adminInterviewPay/adminInterviewPay.jsp";
	}

	// 면접 신청 가격 변경
	private String adminInterviewPay(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		
// 11/06 추가
		CompanyInervVO companypricevo = dao6.interviewPrice();
		request.setAttribute("companypricevo", companypricevo);
//		System.out.println("00000"+companypricevo);
		
		return "adminInterviewPay/adminInterviewPay.jsp";
	}
//-----------------------------------------------------------------------	
/**관리자 qna 시작*/ 	
	private String adminQnaDeleteAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		HttpSession session = request.getSession();
		int qnaNum = (int) session.getAttribute("qnaNum");

		boolean result = dao9.deleteQna(qnaNum);
		//		System.out.println("삭제 성공?"+ result);
		if (result)
			return "starters?cmd=adminQna";
		return "starters?cmd=adminQna";
	}

	private String adminQnaModifyAction(HttpServletRequest request) {
//		System.out.println("//////1. qna등록시작////////////////////");
		dao9 = new QnADAO2();
		String qnaTitle = request.getParameter("qnaTitle");
		String qna = request.getParameter("qna");
		HttpSession session = request.getSession();
		int qnaNum = (int) session.getAttribute("qnaNum");

		boolean result = dao9.updateQna(qnaNum, qnaTitle, qna);
		//		System.out.println(result);

		if (result == false){
			//			return "starters?cmd=tutoringListDetailAction&num="+tutoringRegisterId; 
			//			response.sendRedirect("community/qna/qnaWrite.jsp");
			return "community/qna2/qnaModify.jsp";
		}
		session.setAttribute("messageType", "성공메시지");
		session.setAttribute("messageContent", "성공적으로 게시물이 작성되었습니다.");
		//			response.sendRedirect("starters?cmd=qna");
		//			return "starters?cmd=qna"; // 등록완료
		
		return "starters?cmd=adminQnaListDetailAction&qnaNum="+ qnaNum;
	}

	private String adminQnaModify(HttpServletRequest request) {
//		System.out.println("///////////qna수정");
		dao9 = new QnADAO2();

		QnAVO2 qnaInfo = null;

		HttpSession session = request.getSession();
		int qnaNum = (int) session.getAttribute("qnaNum");
//		System.out.println(qnaNum);
		qnaInfo = dao9.qnaDetails(qnaNum);

		request.setAttribute("qnaInfo", qnaInfo);

		return "adminCommunity/qna2/qnaModify.jsp";
	}

	private String adminQnaWriter(HttpServletRequest request) {
		dao9 = new QnADAO2();

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		int qnaNum=Integer.parseInt(request.getParameter("qnaNum"));
		QnAVO2 qnavo = dao9.qnaWriter(qnaNum);
		request.setAttribute("qnavo", qnavo);
		request.setAttribute("id", id);

		return "adminCommunity/qna2/qnaListDetail.jsp";
	}

	private String adminQnaSearchPagingAction(HttpServletRequest request) {
//		System.out.println("qna 검색");
		dao9 = new QnADAO2();
		
		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();

		HttpSession session = request.getSession();
		String qnaSearchName = (String)session.getAttribute("qnaSearchName");
		String id = (String)session.getAttribute("id");
		request.setAttribute("userId", id);
//		System.out.println("qnaListNumber@@@@@"+qnaListNumber);

		qnaListNumber = dao9.qnaSearchCount(qnaSearchName);
		if(qnaListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(qnaListNumber>0){
			list = dao9.selectQnAList(qnaSearchName, startRow, pageSize);
			pageCount = qnaListNumber/pageSize + (qnaListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			qnaListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		request.setAttribute("qnaSearchName", qnaSearchName);
		
		return "adminCommunity/qna2/qnaListSearch.jsp";
	}

	private String adminQnaSearchAction(HttpServletRequest request) {
//		System.out.println("qna 검색");
		dao9 = new QnADAO2();
		
		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();

		String qnaSearchName = request.getParameter("searchName");
		HttpSession session1 = request.getSession();
		String id = (String)session1.getAttribute("id");
		request.setAttribute("userId", id);
//		System.out.println("qnaListNumber@@@@@"+qnaListNumber);

		qnaListNumber = dao9.qnaSearchCount(qnaSearchName);
		if(qnaListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(qnaListNumber>0){
			list = dao9.selectQnAList(qnaSearchName, startRow, pageSize);
			pageCount = qnaListNumber/pageSize + (qnaListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			qnaListNumber = 0;

		HttpSession session = request.getSession(true);
		session.setAttribute("qnaSearchName", qnaSearchName);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		request.setAttribute("qnaSearchName", qnaSearchName);
		
		return "adminCommunity/qna2/qnaListMain.jsp";
	}

	private String adminQnaCountIncrease(HttpServletRequest request, HttpServletResponse response) {
		dao9 = new QnADAO2();

		int qnaNum=Integer.parseInt(request.getParameter("qnaNum"));

		dao9.qnaCountIncrease(qnaNum); // 조회수 증가

		return "adminCommunity/qna2/qnaListDetail.jsp";
	}

	private String adminQnaListDetailAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		//		System.out.println("//////////qna디테일시작/////////");

		int qnaNum=Integer.parseInt(request.getParameter("qnaNum"));

		QnAVO2 qvo = dao9.qnaDetail(qnaNum);
		request.setAttribute("qvo", qvo);

		HttpSession session = request.getSession(true);
		session.setAttribute("qnaNum", qnaNum);

		return "adminCommunity/qna2/qnaListDetail.jsp";
	}

	private String adminComment(HttpServletRequest request) {
		dao9 = new QnADAO2();
//		System.out.println("//////////qna답변리스트/////////");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		request.setAttribute("userId", id);

		int qnaNum=Integer.parseInt(request.getParameter("qnaNum"));
//				System.out.println("qnaNum========="+qnaNum);
		//		ArrayList<QnACommentVO> getQnAList = dao9.getQnACommentReplyList(qnaNum);
		//		System.out.println("getQnAList@@@@@"+getQnAList);
		ArrayList<QnACommentVO> getQnAList2 = dao9.getQnACommentList(qnaNum);
//				System.out.println("getQnAList2@@@@@"+getQnAList2);
		//		if(getQnAList2.size() > 0){
		//			for (int i =0; i>getQnAList2.size(); i++){
		////				System.out.println(getQnAList2.get(i).getCommentId());
		//				int commnetNums = getQnAList2.get(i).getCommentId();
		//				ArrayList<QnACommentVO> getQnAList = dao9.getQnACommentReplyList(commnetNums);
		//				if(getQnAList.size() > 0)
		//					request.setAttribute("getQnAList", getQnAList);
		//				return "community/qna2/qnaListDetail.jsp";
		//			}
		//		}
		if(getQnAList2.size() > 0)
			request.setAttribute("getQnAList", getQnAList2);

		return "adminCommunity/qna2/qnaListDetail.jsp";
	}

	private String adminCommentRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("//////1. qna답변시작////////////////////");
		dao9 = new QnADAO2();
		int qnaId = Integer.parseInt(request.getParameter("comment_board"));
		//		int commentId = Integer.parseInt(`request.getParameter("commentId"));
		String comment_content = request.getParameter("comment_content");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		//		System.out.println("qnaId"+qnaId+"comment_content"+comment_content+"id"+id);

		//		QnACommentVO parent = dao9.getQnACommentInfo(qnaId);
		//		System.out.println("parent"+parent);

		//		boolean result1 = dao9.UpdateCommentReply(parent);
		//		System.out.println("result1"+result1);

		boolean result = dao9.qnaReply(qnaId, id, comment_content);
		//		System.out.println(result);

		//		return "starters?cmd=qnaListDetailAction&qnaNum="+ qnaNum;

		if(result){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;
	}
	
	private String adminCommentDelete(HttpServletRequest request) {
		//		System.out.println("//////1. qna답글삭제////////////////////");
		dao9 = new QnADAO2();
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		//		System.out.println("commentId"+commentId);
		boolean result = dao9.DeleteQnaReply(commentId);

		HttpSession session = request.getSession();
		int qnaNumber = (int)session.getAttribute("qnaNum");
		//		System.out.println("qnaNumber@@@@@@@"+qnaNumber);

		return "starters?cmd=adminQnaListDetailAction&qnaNum="+ qnaNumber;
	}

	private String adminQnaCommentUpdate(HttpServletRequest request) {
		dao9 = new QnADAO2();
//		System.out.println("수정작업");
		int qnaNumber = Integer.parseInt(request.getParameter("commentId"));
		//		System.out.println("qnaCommentUpdate////"+qnaNumber);
		QnACommentVO commentvo = dao9.getCommentInfo(qnaNumber);
		request.setAttribute("commentvo", commentvo);
		return "adminCommunity/qna2/qnaUpdateForm.jsp";
	}

	private String adminQnaCommentUpdateAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
//		System.out.println("수정작업Action////");

		int comment = Integer.parseInt(request.getParameter("comment_num"));
//		System.out.println("comment///"+comment);

		String comment_content = request.getParameter("comment_content");

		int qnaNumber = Integer.parseInt(request.getParameter("qna_num"));
		//		System.out.println("qnaNumber///"+qnaNumber);

		boolean result = dao9.updateComment(comment, comment_content);
		//		System.out.println("result"+result);

		return "starters?cmd=adminQnaListDetailAction&qnaNum="+ qnaNumber;
	}
	private String adminQnamemberCategActionPaging(HttpServletRequest request) {
		dao = new MemberDAO();
		int allMembers = 0;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		allMembers = dao.memberCateg3(id);
		request.setAttribute("allMembers", new Integer(allMembers));
		return "adminCommunity/qna2/qnaList.jsp";
	}
	private String adminQnaListPaging(HttpServletRequest request) {
//		System.out.println("2. qna 리스트페이징 시작");
		dao9 = new QnADAO2();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		request.setAttribute("userId", id);
		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();
		qnaListNumber = dao9.qnaCount();
		if(qnaListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(qnaListNumber>0){
			list = dao9.getQnAList(startRow, pageSize);
			pageCount = qnaListNumber/pageSize + (qnaListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			qnaListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		
		return "adminCommunity/qna2/qnaList.jsp";
	}

	private String adminQnamemberCategAction2(HttpServletRequest request) {
		dao = new MemberDAO();
		int allMembers = 0;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		allMembers = dao.memberCateg3(id);
		request.setAttribute("allMembers", new Integer(allMembers));
		return "adminCommunity/qna2/qnaListMain.jsp";
	}

	private String adminQna(HttpServletRequest request) {
//		System.out.println("1. qna 리스트 시작");
		dao9 = new QnADAO2();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		request.setAttribute("userId", id);
		
		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();
		qnaListNumber = dao9.qnaCount();
		if(qnaListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(qnaListNumber>0){
			list = dao9.getQnAList(startRow, pageSize);
			pageCount = qnaListNumber/pageSize + (qnaListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			qnaListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		
		return "adminCommunity/qna2/qnaListMain.jsp";
	}
	// 대댓글 부분
	private String adminQnaCommentReply(HttpServletRequest request) {
		//			System.out.println("//////1. qna대댓글시작////////////////////");
		dao9 = new QnADAO2();

		// 부모글의 글번호를 가져온다.
		int comment_num = Integer.parseInt(request.getParameter("commentId"));
		QnACommentVO comment = dao9.getCommentInfo(comment_num);
		//	        System.out.println("comment///////"+comment);
		// 댓글 정보를 request에 세팅한다.
		request.setAttribute("comment", comment);

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		request.setAttribute("userId", id);

		return "adminCommunity/qna2/qnaCommentReply.jsp";
	}

	private String adminQnaCommentReplyAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		//			System.out.println("대댓글작업Action////");

		int comment_num = Integer.parseInt(request.getParameter("comment_num")); // 부모 글 번호
		int qnaNumber = Integer.parseInt(request.getParameter("qna_num")); // qna 글번호
		String comment_user_id = request.getParameter("commentUserId"); // 사용자 번호
		String comment_content = request.getParameter("comment_content"); // 게시글
		//			System.out.println("/////qnaNumber///////////////////////////////////////"+qnaNumber);
		QnACommentVO parent = dao9.getCommentInfo(comment_num);
//		System.out.println("parent"+parent);
		boolean result1 = dao9.UpdateCommentReply(parent);
		//			System.out.println("result1"+result1);
		boolean result = dao9.commentReplyInsert(qnaNumber, comment_user_id, comment_content, parent);
		//			System.out.println("result"+result);
		//			boolean result = dao9.commentReplyInsert(comment_num,qnaNumber, comment_user_id,   comment_content);
		//			System.out.println("result"+result);

		//			return "starters?cmd=qnaListDetailAction&qnaNum="+ qnaNumber;
		return null;
	}

	private String adminQnaRegisterAction(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		//		System.out.println("//////1. qna등록시작////////////////////");
		dao9 = new QnADAO2();
		String qnaTitle = request.getParameter("qnaTitle");
		String qna = request.getParameter("qna");
		String qnaPasswd = request.getParameter("qnaPasswd");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		boolean result = dao9.registerQna(id, qnaTitle, qna, qnaPasswd);
//		System.out.println(result);

		if (result == false){
			//			return "starters?cmd=tutoringListDetailAction&num="+tutoringRegisterId; 
			//			response.sendRedirect("community/qna/qnaWrite.jsp");
			return "adminCommunity/qna2/qnaWrite.jsp";
		}
		session.setAttribute("messageType", "성공메시지");
		session.setAttribute("messageContent", "성공적으로 게시물이 작성되었습니다.");
		//			response.sendRedirect("starters?cmd=qna");
		//			return "starters?cmd=qna"; // 등록완료
		return "starters?cmd=adminQna";
	}
//-----------------------------------------------------------------------
/**관리자 공지사항 시작*/ 	
	private String adminNoticeModifyAction(HttpServletRequest request) {
//		System.out.println("공지사항 등록시작");
		dao7 = new NoticeDAO();

		HttpSession session = request.getSession();
		int noticeNum = (int) session.getAttribute("noticeNum");

		String title = request.getParameter("noticeTitle");
		String memCateg1 = request.getParameter("memberCateg1");
		String memCateg2 = request.getParameter("memberCateg2");
		String memCateg3 = request.getParameter("memberCateg3");
		String noticeContent = request.getParameter("notice");
		
		dao7.deleteNoticeMem(noticeNum);
		boolean result = dao7.updateNotice(title, noticeContent, noticeNum);
//		System.out.println("result" + result);
	
		String[] memCateg = {memCateg1, memCateg2, memCateg3};
		for(int i =0; i < memCateg.length; i++){
			if(memCateg[i] != null){
		//		System.out.println("memCateg[i]"+memCateg[i]);
				dao7.registerNoticeMemberCateg(noticeNum,memCateg[i]);
			}
		}
		if (result)
			return "starters?cmd=adminNoticeListDetailAction&noticeNum="+noticeNum; 
		return "adminCommunity/notice/noticeModify.jsp";
	}
	
	private String adminNoticeModify(HttpServletRequest request) {
		dao7 = new NoticeDAO();
		NoticeVO noticeInfo = null;
//		System.out.println("///////////공지사항수정");
		HttpSession session = request.getSession();
		int noticeNum = (int) session.getAttribute("noticeNum");
//		System.out.println(noticeNum);
		noticeInfo = dao7.noticeDetails(noticeNum);

		request.setAttribute("noticeInfo", noticeInfo);

		return "adminCommunity/notice/noticeModify.jsp";
	}

	// 공지사항 관리자 구분
	private String adminNoticeCateg2(HttpServletRequest request) {
		dao = new MemberDAO();
		int admin = 0;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		admin = dao.memberCateg2(id);
		request.setAttribute("admin", new Integer(admin));
		//	//		System.out.println(members);
		return "adminCommunity/notice/noticeDetail.jsp";
	}
	// 공지사항 여러개삭제
		private String adminNoticeDeleteMany(HttpServletRequest request) {
			dao7 = new NoticeDAO();
			String[] deleteNoticeIds = request.getParameterValues("deleteNoticeId");
	//		System.out.println("deleteNoticeIds"+deleteNoticeIds);
	//		System.out.println("deleteNoticeIds.length"+deleteNoticeIds.length);
			for(int i=0; i<deleteNoticeIds.length; i++){
				int deleteNoticeId = Integer.parseInt(deleteNoticeIds[i]);
				boolean result = dao7.deleteNotice(deleteNoticeId);
			}
			
			
			return "starters?cmd=adminNotice"; 
		}
	// 공지사항 삭제
	private String adminNoticeDelete(HttpServletRequest request) {
		dao7 = new NoticeDAO();
		HttpSession session = request.getSession();
		int noticeNum = (int) session.getAttribute("noticeNum");

		boolean result = dao7.deleteNotice(noticeNum);
		if (result)
			return "starters?cmd=adminNotice"; 
		return "adminCommunity/notice/noticeDetail.jsp";
	}

	// 공지사항조회수
	private String adminNoticeCountIncrease(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		dao7 = new NoticeDAO();

		int noticeNum=Integer.parseInt(request.getParameter("noticeNum"));

		dao7.noticeCountIncrease(noticeNum); // 조회수 증가

		return "adminCommunity/notice/noticeDetail.jsp";

	}

	private String adminNoticeRegisterAction(HttpServletRequest request) {
//		System.out.println("공지사항 등록시작");
		dao7 = new NoticeDAO();

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		String title = request.getParameter("noticeTitle");
		String memCateg1 = request.getParameter("memberCateg1");
		String memCateg2 = request.getParameter("memberCateg2");
		String memCateg3 = request.getParameter("memberCateg3");
		String noticeContent = request.getParameter("notice");

		String ip = request.getRemoteAddr();
//		System.out.println(id+"/ "+ title+ "/ " + noticeContent + "/ "+ ip);
		boolean result = dao7.registerNotice(title, noticeContent, id, ip, null);
//		System.out.println("result" + result);
		NoticeVO result1 = dao7.getNoticeRegisterInfo(id);
//		System.out.println("result1" + result1);
		int NoticeRegisterId = result1.getNoticeId();
//		System.out.println("NoticeRegisterId" + NoticeRegisterId);
		//			dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg1);
		//			dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg2);
		//			dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg3);
		String[] memCateg = {memCateg1, memCateg2, memCateg3};
		for(int i =0; i < memCateg.length; i++){
			if(memCateg[i] != null){
		//		System.out.println("memCateg[i]"+memCateg[i]);
				dao7.registerNoticeMemberCateg(NoticeRegisterId,memCateg[i]);
			}
		}
		if (result)
			return "starters?cmd=adminNotice"; 
		return "adminCommunity/notice/noticeWriter.jsp";
	}

	private String adminCategAction2(HttpServletRequest request) {
		//			System.out.println("admin 글쓰기 시작");
		dao = new MemberDAO();
		int adminmembers = 0;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		adminmembers = dao.adminCateg(id);
		//			System.out.println("adminmembers@@@@@@@@@@@"+adminmembers);
		request.setAttribute("adminmembers", new Integer(adminmembers));
		return "adminCommunity/notice/noticeListMain.jsp";
	}

	private String adminNoticeListDetailAfterAjax(HttpServletRequest request) {
//		System.out.println("1. noticeListDetailPreAjax///////시작");
		dao7 = new NoticeDAO();
		int noticeNum=Integer.parseInt(request.getParameter("DetailNum"));
		NoticeVO noticevo = dao7.noticeDetail(noticeNum);
		request.setAttribute("noticevo", noticevo);

//		System.out.println("noticevo" + noticevo);

//		System.out.println("이전글");
		NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
		request.setAttribute("noticevobefore", noticevobefore);

//		System.out.println("noticevobefore/////////////이전글" + noticevobefore);

//		System.out.println("이후글");
		NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
		request.setAttribute("noticevoafter", noticevoafter);

//		System.out.println("noticevoafter" + noticevoafter);

		return "adminCommunity/notice/noticeDetailAfterAjax.jsp";
	}

	//		private String noticeListDetailAfterAction(HttpServletRequest request) {
	//			dao7 = new NoticeDAO();
	//			int noticeNum=Integer.parseInt(request.getParameter("noticeNum"));
	//			NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
	//			request.setAttribute("noticevoafter", noticevoafter);
	//			
	//			System.out.println("noticevoafter" + noticevoafter);
	//			
	//			HttpSession session = request.getSession(true);
	//			session.setAttribute("noticeNumAfter", noticeNum);
	////			
	//			return "community/notice/noticeDetail.jsp";
	//		}

	private String adminNoticeListDetailPreAjax(HttpServletRequest request) {
//		System.out.println("1. noticeListDetailPreAjax///////시작");
		dao7 = new NoticeDAO();
		int noticeNum=Integer.parseInt(request.getParameter("DetailNum"));
		NoticeVO noticevo = dao7.noticeDetail(noticeNum);
		request.setAttribute("noticevo", noticevo);

//		System.out.println("noticevo" + noticevo);

//		System.out.println("이전글");
		NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
		request.setAttribute("noticevobefore", noticevobefore);

//		System.out.println("noticevobefore/////////////이전글" + noticevobefore);

//		System.out.println("이후글");
		NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
		request.setAttribute("noticevoafter", noticevoafter);

//		System.out.println("noticevoafter" + noticevoafter);

		return "adminCommunity/notice/noticeDetailBeforeAjax.jsp";
	}

	//		private String noticeListDetailPreAction(HttpServletRequest request) {
	//			System.out.println("이전글//////////");
	//			dao7 = new NoticeDAO();
	//			int noticeNum=Integer.parseInt(request.getParameter("noticeNum"));
	//			NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
	//			request.setAttribute("noticevobefore", noticevobefore);
	//			
	//			System.out.println("noticevobefore/////////////이전글" + noticevobefore);
	//			
	//			HttpSession session = request.getSession(true);
	//			session.setAttribute("noticeNumBefore", noticeNum);
	//			
	//			return "community/notice/noticeDetail.jsp";
	//		}

	private String adminNoticeListDetailAction(HttpServletRequest request) {
		dao7 = new NoticeDAO();
//		System.out.println("디테일시작///////////");
		int noticeNum=Integer.parseInt(request.getParameter("noticeNum"));
		NoticeVO noticevo = dao7.noticeDetail(noticeNum);
		request.setAttribute("noticevo", noticevo);


		ArrayList<NoticeMemVO> noticememvo = dao7.getNoticeMem(noticeNum);
		request.setAttribute("noticememvo", noticememvo);

//		System.out.println("이전글");
		NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
		request.setAttribute("noticevobefore", noticevobefore);

//		System.out.println("noticevobefore/////////////이전글" + noticevobefore);

//		System.out.println("이후글");
		NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
		request.setAttribute("noticevoafter", noticevoafter);

//		System.out.println("noticevoafter" + noticevoafter);

		HttpSession session = request.getSession(true);
		session.setAttribute("noticeNum", noticeNum);

		return "adminCommunity/notice/noticeDetail.jsp";
	}

	private String adminNoticeSearchPagingAction(HttpServletRequest request) {
//		System.out.println("공지사항 검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		HttpSession session = request.getSession();
		String noticeSearchName = (String) session.getAttribute("noticeSearchName");
		String searchCateg = (String)session.getAttribute("searchCateg");
//		System.out.println("noticeSearchName@@@@@"+noticeSearchName);

		if(searchCateg.equals("1")){
			noticeListNumber = dao7.selectNoticeCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectTitleList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

		}else if(searchCateg.equals("0")){
	//		System.out.println("멤버로//////////");
			noticeListNumber = dao7.selectMemCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectNoticeMember(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;


			//				System.out.println("list"+list);
		}else if(searchCateg.equals("2")){
			noticeListNumber = dao7.selectContentCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

		}else{
	//		System.out.println("이외로//////////");
			noticeListNumber = dao7.selectTContentCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectTContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		request.setAttribute("searchName", noticeListNumber);
		
		return "adminCommunity/notice/noticeListSearch.jsp";
	}

	private String adminNoticeSearchAction(HttpServletRequest request) {
//		System.out.println("공지사항 검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		String searchCateg = request.getParameter("searchCateg");
//		System.out.println("searchCateg@@@@@"+searchCateg);
		String noticeSearchName = request.getParameter("searchName");
//		System.out.println("noticeSearchName@@@@@"+noticeSearchName);
		if(searchCateg.equals("1")){
			noticeListNumber = dao7.selectNoticeCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectTitleList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);
		}else if(searchCateg.equals("0")){
	//		System.out.println("멤버로//////////");
			noticeListNumber = dao7.selectMemCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectNoticeMember(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);

			//				System.out.println("list"+list);
		}else if(searchCateg.equals("2")){
			noticeListNumber = dao7.selectContentCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);
		}else{
	//		System.out.println("이외로//////////");
			noticeListNumber = dao7.selectTContentCount(noticeSearchName);
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.selectTContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		request.setAttribute("searchName", noticeSearchName);
		
		return "adminCommunity/notice/noticeListMain.jsp";
	}

	private String adminNoticeListPaging(HttpServletRequest request) {
//		System.out.println("2. 공지사항 리스트 페이징 시작");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		noticeListNumber = dao7.noticeCount();
		if(noticeListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(noticeListNumber>0){
			list = dao7.getNoticDate(startRow, pageSize);
			pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			noticeListNumber = 0;

//		System.out.println("startRow"+startRow);

//		System.out.println("pageNum"+pageNum);
//		System.out.println("currentPage"+currentPage);
//		System.out.println("pageSize"+pageSize);
//		System.out.println("startPage"+startPage);
//		System.out.println("pageBlock"+pageBlock);
//		System.out.println("endPage"+endPage);
//		System.out.println("pageCount"+pageCount);
//		System.out.println(list);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		
		return "adminCommunity/notice/noticeList.jsp";
	}

	private String adminNotice(HttpServletRequest request) {
//		System.out.println("1. 공지사항 리스트 시작검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		//				String subSelect = request.getParameter("subSelect");
		//				System.out.println("subSelect"+subSelect);

		noticeListNumber = dao7.noticeCount();
		if(noticeListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(noticeListNumber>0){
			list = dao7.getNoticDate(startRow, pageSize);
			pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			noticeListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		
		return "adminCommunity/notice/noticeListMain.jsp";
	}

	private String adminNoticeListSelectPaging(HttpServletRequest request) {
//		System.out.println("1. 공지사항 리스트 시작 검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		String select = request.getParameter("select");
		if(select.equals("1")){ // 번호순
			noticeListNumber = dao7.noticeCount();
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.getNotice(startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;
		}else if(select.equals("2")){ // 최신순
			noticeListNumber = dao7.noticeCount();
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.getNoticDate(startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;
		}else{ // 조회수순
			noticeListNumber = dao7.noticeCount();
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.getNoticHits(startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		
		return "adminCommunity/notice/noticeListSelect.jsp";
	}

	private String adminNoticeListSelectAction(HttpServletRequest request) {
//		System.out.println("1. 공지사항 리스트 시작aaaaa");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		String select = request.getParameter("select");
//		System.out.println("select@@@@@@@@@@2"+select);
		if(select.equals("1")){ // 번호순
			noticeListNumber = dao7.noticeCount();
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.getNotice(startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;
		}else if(select.equals("2")){ // 최신순
	//		System.out.println("최신순");
	//		System.out.println("select"+select);
			noticeListNumber = dao7.noticeCount();
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.getNoticDate(startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;
	//		System.out.println("list"+list);
		}else{ // 조회수순
			noticeListNumber = dao7.noticeCount();
			if(noticeListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(noticeListNumber>0){
				list = dao7.getNoticHits(startRow, pageSize);
				pageCount = noticeListNumber/pageSize + (noticeListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				noticeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		
		return "adminCommunity/notice/noticeListSelect.jsp";
	}
//-----------------------------------------------------------------------
/**관리자 후기게시판 시작*/ 
	// 후기 회원구분
	private String adminTuteeMembersCategAction2(HttpServletRequest request) {
		dao = new MemberDAO();
		int tuteemembers = 0;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		tuteemembers = dao.memberCateg4(id);
		request.setAttribute("tuteemembers", new Integer(tuteemembers));
		//			System.out.println(members);
		return "adminCommunity/review/reviewListMain.jsp";
	}
	// 후기 회원구분
	private String adminTuteeMemberCategAction2(HttpServletRequest request) {
		dao = new MemberDAO();
		int tuteemembers = 0;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		tuteemembers = dao.memberCateg4(id);
		request.setAttribute("tuteemembers", new Integer(tuteemembers));
		//			System.out.println(members);
		return "adminCommunity/review/reviewList.jsp";
	}

	/**베스트 리뷰*/
	private String adminBestReviewAction(HttpServletRequest request) {
//		System.out.println("********best Review 시작********");
		dao4 = new ReviewDAO();	
		ArrayList<ReviewBestVO> best = dao4.bestReview();
		request.setAttribute("best", best);
//		System.out.println("best리뷰------"+ best);
		return "adminCommunity/review/reviewListMain.jsp";
	}

	/**후기게시판 좋아요*/
	private String adminReviewLikeCount(HttpServletRequest request) {
		dao4 = new ReviewDAO();
//		System.out.println("/////////////좋아요액션(색변화!!)/////////////////////");
		int reviewDetailNum = Integer.parseInt(request.getParameter("reviewDetailNum"));
//		System.out.println("reviewDetailNum 좋아요 액션---------"+ reviewDetailNum);
		HttpSession session = request.getSession();
		String memberId =(String)session.getAttribute("id");
//		System.out.println(memberId);

		int reviewLikeCount = dao4.selectReviewLikeCount(reviewDetailNum);
		request.setAttribute("reviewLikeCount", new Integer(reviewLikeCount));
//		System.out.println("reviewLikeCount"+reviewLikeCount);

		return "adminCommunity/review/reviewListLikeCount.jsp"; 
	}

	/**후기게시판 좋아요*/
	private String adminReviewLikeAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();
//		System.out.println("/////////////좋아요액션(색변화!!)/////////////////////");
		int reviewDetailNum = Integer.parseInt(request.getParameter("reviewDetailNum"));
//		System.out.println("reviewDetailNum 좋아요 액션---------"+ reviewDetailNum);
		HttpSession session = request.getSession();
		String memberId =(String)session.getAttribute("id");
//		System.out.println(memberId);

		int likeExist = dao4.reviewLikeExist(reviewDetailNum, memberId);

//		System.out.println("likeExist"+likeExist);

		if(likeExist == 0){ // 좋아요 한게 없으면 insert하고
			boolean result1 = dao4.reviewLike(reviewDetailNum, memberId);
	//		System.out.println("result1 true" + result1);
			request.setAttribute("result1", result1);

			int reviewLikeCount = dao4.selectReviewLikeCount(reviewDetailNum);
			request.setAttribute("reviewLikeCount", new Integer(reviewLikeCount));
//			System.out.println("reviewLikeCount"+reviewLikeCount);

			int reviewLikeAll = dao4.reviewLikeIncrease(reviewDetailNum);
//			System.out.println("reviewLikeAll////////////////"+reviewLikeAll);
			request.setAttribute("reviewLikeAll", reviewLikeAll);

		}else{ // 좋아요 한게 있으면 delete하고
			boolean result2 = dao4.deleteReviewLike(reviewDetailNum, memberId);
			request.setAttribute("result2", result2);

			int reviewLikeCount = dao4.selectReviewLikeCount(reviewDetailNum);
			request.setAttribute("reviewLikeCount", new Integer(reviewLikeCount));
//			System.out.println("reviewLikeCount"+reviewLikeCount);

			int reviewLikeAll = dao4.reviewUnLikeIncrease(reviewDetailNum);
			//request.setAttribute("reviewUnlike", reviewUnlike);

		}
		return "adminCommunity/review/reviewListLike.jsp";
	}

	/**후기게시판 이미지*/
	private String adminReviewListImage(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		int reviewId = Integer.parseInt(request.getParameter("reviewListNum"));
		HttpSession session = request.getSession(true);
		session.setAttribute("reviewId2", reviewId);
		//			System.out.println("reviewId"+reviewId);
		request.setAttribute("reviewId", reviewId);
		ArrayList<IntImageVO> images = dao4.getReviewImage(reviewId);
		request.setAttribute("images", images);

		return "adminCommunity/review/reviewListImage.jsp";
	}

	/**후기게시판 수정 시 선택하는 튜터링명*/
	private String adminModifySelectReviewTitle(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		//			System.out.println("//////////ModifySelectReviewTitle시작");
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ArrayList<TutoringBuyVO> ModifyTutoringTitleList = dao4.selectTutoringTitle(id);
		int count = dao4.selectTutoringTitleCount(id);
		request.setAttribute("ModifyTutoringTitleList", ModifyTutoringTitleList);
		request.setAttribute("count", count);
		String tutoringApplyId = request.getParameter("tutoringApplyId");
		return "adminCommunity/review/reviewListMain.jsp";
	}
	/**수정을 위해 게시물 상세 내용 가져오기*/
	/**후기게시판 수정*/
	private String adminReviewModifyAction(HttpServletRequest request) throws ServletException, IOException {
		dao4 = new ReviewDAO();
		//			System.out.println("//////////reviewModifyAction시작");
		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1*1024*1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		String savePath = request.getServletContext().getRealPath("/assets/reviewImage"); // 저장경로
		//			System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일 업로드 하는 객체 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를 불러옴

		//		String reviewImage=(String)files.nextElement(); // 자료가 많을 경우엔 while 문을 사용
		String image1=imageUp.getFilesystemName("reviewImage");  // 서버에 저장될 파일이름

		HttpSession session = request.getSession();
		String number = (String)session.getAttribute("reviewNum");
		int realNumber = Integer.parseInt(number);
		//			
//		System.out.println("나와야함"+realNumber);

		String id =(String)session.getAttribute("id");

		int tutoringApplyId =Integer.parseInt(imageUp.getParameter("selectBox2"));
		String reviewTitle = imageUp.getParameter("reviewTitle");
		String reviewContent = imageUp.getParameter("reviewContent");  

		boolean result = dao4.modifyReview(tutoringApplyId, reviewTitle, reviewContent,realNumber);
		ReviewVO result1 = dao4.getReviewList(id);
//		System.out.println("result1@@@@@@@@@@"+result1);
		//			int reivewListId = result1.getReviewId();
		//			System.out.println("reivewListId@@@@@@@@@@"+reivewListId);
		boolean result5 = dao4.deleteReviewImage(realNumber);
//		System.out.println("result5@@@@@@@@@@@@@@@" + result5);
		boolean result2 = dao4.addImage(realNumber,image1);
//		System.out.println("result2@@@@@@@@@@@@@@@" + result2);

		if (result)
			return "starters?cmd=adminReviewList";
		return "adminCommunity/review/reviewListMain.jsp"; 
	}

	/**조회수*/
	private String adminReviewCountIncrease(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		//			System.out.println("/////////////증가시작");
		String reviewNum = request.getParameter("reviewid");
		//			System.out.println(reviewNum);
		int realNumber = Integer.parseInt(reviewNum);
		//			System.out.println(realNumber);
		int increase = dao4.reviewCountIncrease(realNumber);
		ReviewVO increases = dao4.reviewDetailCount(realNumber);
		//		ReviewVO list = dao5.reviewDetailCount();
		//			System.out.println(increase);
		request.setAttribute("increase", increase);
		request.setAttribute("increases", increases);

		return "adminCommunity/review/reviewListCount.jsp";
	}

	/**후기게시판 삭제를 위한 게시글 조회 */
	private String adminReviewNum(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		//			System.out.println("//////////reviewNum시작");
		String reviewNum = request.getParameter("reviewid");
		//		int reviewNumber = Integer.parseInt(reviewNum);
		//			System.out.println("reviewNum"+reviewNum);
		HttpSession session = request.getSession(true);
		session.setAttribute("reviewNum", reviewNum);
		return null;

	}	
	/**후기게시판 삭제*/
	private String adminReviewDeleteAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		//			System.out.println("//////////reviewDeleteAction시작");
		HttpSession session = request.getSession();
		String number = (String)session.getAttribute("reviewNum");
		int realNumber = Integer.parseInt(number);
		//			System.out.println("나와야함"+realNumber);
		boolean result = dao4.deleteReview(realNumber);
		//			System.out.println("deleteReview 결과"+result);
		if (result)
			return "starters?cmd=adminReviewList";
		return "starters?cmd=adminReviewList";
	}

	/**후기게시판 상세 모달로 띄워서 필요없음*/

	/**등록 시 선택하는 튜터링명과 튜터명*/
	private String adminSelectReviewTitle(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		//			System.out.println("//////////selectReviewTitle시작");

		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ArrayList<TutoringBuyVO> tutoringTitleList = dao4.selectTutoringTitle(id);
		request.setAttribute("tutoringTitleList", tutoringTitleList);
		//			System.out.println(tutoringTitleList);
		String tutoringApplyId = request.getParameter("tutoringApplyId");
		return "adminCommunity/review/reviewListMain.jsp";
	}

	/**후기게시판 등록*/
	private String adminReviewRegisterAction(HttpServletRequest request) throws ServletException, IOException{
		dao4 = new ReviewDAO();
		//			System.out.println("//////////reviewRegisterAction시작");
		request.setCharacterEncoding("UTF-8");
		//      String filename = null; 
		//      String realFolder = "";
		//      String saveFolder = "/assets/companyImage";
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1*1024*1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		//      ServletContext context = request.getSession().getServletContext();
		String savePath = request.getServletContext().getRealPath("/assets/reviewImage"); // 저장경로
//		System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일 업로드 하는 객체 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를 불러옴

		String reviewImage=(String)files.nextElement(); // 자료가 많을 경우엔 while 문을 사용
		String image1=imageUp.getFilesystemName("reviewImage");  // 서버에 저장될 파일이름

		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");

		int tutoringApplyId =Integer.parseInt(imageUp.getParameter("selectBox"));
		String reviewTitle = imageUp.getParameter("reviewTitle");
		String reviewContent = imageUp.getParameter("reviewContent");   
		//			System.out.println(tutoringApplyId+"/"+reviewTitle+"/"+image1+"/"+reviewContent);
		boolean result = dao4.registerReview(tutoringApplyId,reviewTitle, reviewContent);
		ReviewVO result1 = dao4.getReviewList(id);
		int reivewListId = result1.getReviewId();
		boolean result2 = dao4.addImage(reivewListId,image1);

		if (result||result2)
			return "starters?cmd=adminReviewList";
		return "adminCommunity/review/reviewListMain.jsp"; 
	}

	/** 검색 후기게시판 목록*/
	private String adminReviewListSearchPaging(HttpServletRequest request) {
		dao4 = new ReviewDAO();
//		System.out.println("//////////reviewListSearch시456작");
		HttpSession session = request.getSession();
		String usersId =(String)session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =6;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();

		String searchName =(String)session.getAttribute("searchName");


		reviewAllCount = dao4.selectReviewCount(searchName);
		if(reviewAllCount == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(reviewAllCount>0){
			list = dao4.selectReviewList(searchName, usersId,startRow, pageSize);
			pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			reviewAllCount = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "adminCommunity/review/reviewListSearch.jsp";
	}

	private String adminReviewListSearch(HttpServletRequest request) throws ServletException, IOException {
		dao4 = new ReviewDAO();
//		System.out.println("reviewListSearch시작123");
		HttpSession session = request.getSession();
		String usersId =(String)session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =6;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();

		String searchName =request.getParameter("searchName");
//		System.out.println("@@@@@@@@@searchName@@@@@@@@@@@@@@"+searchName);

		reviewAllCount = dao4.selectReviewCount(searchName);
		if(reviewAllCount == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(reviewAllCount>0){
			list = dao4.selectReviewList(searchName, usersId,startRow, pageSize);
			pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			reviewAllCount = 0;

		HttpSession session1 = request.getSession(true);
		session1.setAttribute("searchName", searchName);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "adminCommunity/review/reviewListMain.jsp";

	}

	/**후기게시판 리스트 게시글 수*/
	private String adminReviewListNumbers(HttpServletRequest request)  throws ServletException, IOException{
		dao4 = new ReviewDAO();
		int reviewAllCount = dao4.reviewCount();
		request.setAttribute("reviewAllCount", reviewAllCount);
		return "adminCommunity/review/reviewListMain.jsp";
	}
	/**후기게시판 리스트 페이징*/
	private String adminReviewListPagingAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId =(String)session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		//			ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		reviewAllCount = dao4.reviewCount();
		if(reviewAllCount == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(reviewAllCount>0){
			list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
			pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}

		if(list.isEmpty())
			reviewAllCount = 0;

		//			HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ArrayList<TutoringBuyVO> ModifyTutoringTitleList = dao4.selectTutoringTitle(id);
		request.setAttribute("ModifyTutoringTitleList", ModifyTutoringTitleList);
		String tutoringApplyId = request.getParameter("tutoringApplyId");

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "adminCommunity/review/reviewList.jsp";
	}

	/**후기게시판 목록*/
	private String adminReviewList(HttpServletRequest request) {
//		System.out.println("//////////reviewList시작");
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId =(String)session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		reviewAllCount = dao4.reviewCount();
		if(reviewAllCount == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(reviewAllCount>0){
			list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
			pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			reviewAllCount = 0;
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "adminCommunity/review/reviewListMain.jsp";
	}


	private String adminReviewListSelectPaging(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId =(String)session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =6;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		String select = request.getParameter("select");
//		System.out.println("select@@@@@@@@@@"+select);
		if(select.equals("1")){ // 번호순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}else if(select.equals("2")){ // 최신순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewDateListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}else if(select.equals("3")){ // 조회수순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewHitListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}else{//좋아요순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewLikeCountListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
		
		return "adminCommunity/review/reviewListSelect.jsp";
	}

	private String adminReviewListSelectAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId =(String)session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =6;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		String select = request.getParameter("select");
//		System.out.println("select@@@@@@@@@@"+select);
		if(select.equals("1")){ // 번호순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}else if(select.equals("2")){ // 최신순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewDateListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}else if(select.equals("3")){ // 조회수순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewHitListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}else{//좋아요순
			reviewAllCount = dao4.reviewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = dao4.getReviewLikeCountListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
		
		return "adminCommunity/review/reviewListSelect.jsp";
	}
//-----------------------------------------------------------------------

	
	// 관리자 포트폴리오 관리
	private String adminPortfolioListSelectPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();

		//// 여기서부터 하기

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();

		HttpSession session = request.getSession();
		String select = (String)session.getAttribute("select");
		// System.out.println("조회번호"+select);
		if(select.equals("1")){ // 번호순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetPortfolioId(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else if(select.equals("2")){// 조회수순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetHitsPortfolio(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else if(select.equals("3")){// 최신순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetDatePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else{// 좋아요순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetLikePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}



		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListSelect.jsp";
	}
	
	private String adminPortfolioListSelectAction(HttpServletRequest request) {
		dao5 = new PortfolioDAO();

		//// 여기서부터 하기

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();

		String select = request.getParameter("select");
//		System.out.println("select//////////"+select);
		if(select.equals("1")){ // 번호순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetPortfolioId(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else if(select.equals("2")){// 조회수순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetHitsPortfolio(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else if(select.equals("3")){// 최신순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetDatePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else{// 좋아요순
			portfolioListNumber = dao5.portfolioCount();
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminGetLikePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("select", select);		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		return "admin/portfolio/portfolioListSelect.jsp";
	}
	
	/**날짜검색*/
	private String adminPortfolioDateSelectPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		System.out.println("날짜 조회 페이징 시작==============");
		HttpSession session = request.getSession();
		
		String date = (String)session.getAttribute("date");
		System.out.println("date" + date);
		String from = (String)session.getAttribute("from");
		System.out.println("from" + from);
		String to = (String)session.getAttribute("to");
		System.out.println("to" + to);
		
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountDate(from,to);
		if(portfolioListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(portfolioListNumber>0){
			list = dao5.adminGetPortfolioDate(from,to,startRow,pageSize);
			pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			portfolioListNumber = 0;

		System.out.println(pageNum);
		System.out.println(currentPage);
		System.out.println(startPage);
		System.out.println(endPage);
		System.out.println(list);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));

		return "admin/portfolio/portfolioListSelectDateSearch.jsp";
	}
	private String adminPortfolioDateSelectAction(HttpServletRequest request) throws ServletException, IOException{
		dao5 = new PortfolioDAO();
		System.out.println("날짜 조회==============");
		HttpSession session = request.getSession();

		String date = request.getParameter("date");
		System.out.println("date" + date);
		String from = date.substring(0,10);
		System.out.println("from" + from);
		String to = date.substring(10,20);
		System.out.println("to" + to);
		
//		HttpSession session = request.getSession(true);
		session.setAttribute("date", date);
		session.setAttribute("from", from);
		session.setAttribute("to", to);
		
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountDate(from, to);
		if(portfolioListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(portfolioListNumber>0){
			list = dao5.adminGetPortfolioDate(from,to,startRow,pageSize);
			pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			portfolioListNumber = 0;

		System.out.println(pageNum);
		System.out.println(currentPage);
		System.out.println(startPage);
		System.out.println(endPage);
		System.out.println(list);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));

		return "admin/portfolio/portfolioListSelectDateSearch.jsp";
	}
	
	/**전체보기*/
	private String adminPortfolioListAll(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCount();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolio(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioList.jsp";
	}
	
	/**6개월*/
	private String adminPortfolioListSixMonthPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		System.out.println("3개월시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountSixMonth();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolioSixMonth(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListSixMonth.jsp";
	}
	private String adminPortfolioListSixMonth(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		System.out.println("3개월시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountSixMonth();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolioSixMonth(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListSixMonth.jsp";
	}
	
	/**3개월*/
	private String adminPortfolioListThreeMonthPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		System.out.println("3개월페이징시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountThreeMonth();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolioThreeMonth(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListThreeMonth.jsp";
	}
	private String adminPortfolioListThreeMonth(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		System.out.println("3개월시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountThreeMonth();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolioThreeMonth(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListThreeMonth.jsp";
	}
	
	/**1개월*/
	private String adminPortfolioListAmonthPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		System.out.println("1개월페이징시작");
		
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountAmonth();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolioAmonth(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListAmonth.jsp";
	}
	private String adminPortfolioListAmonth(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		System.out.println("1개월시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCountAmonth();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolioAmonth(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListAmonth.jsp";
	}
	
	private String adminPortfolioSearchPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		System.out.println("검색페이징 시작~~~~~~~~~~");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list= new ArrayList<PortfolioLikeVO>();

		HttpSession session = request.getSession();
		String searchName = (String)session.getAttribute("searchName");
		String searchCateg = (String)session.getAttribute("searchCateg");

		if(searchCateg.equals("0")){
			//			System.out.println("전체로 검색");

			portfolioListNumber = dao5.selectAllPortfolioCount(searchName);
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminSelectAllPortfolioList(searchName,startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else if(searchCateg.equals("1")){
			//			System.out.println("키워드로 검색");

			portfolioListNumber = dao5.selectPortfolioCount(searchName);
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminSelectPortfolioList(searchName,startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else{
			//			System.out.println("튜티명으로 검색");

			portfolioListNumber = dao5.selectTuteePortfolioCount(searchName);
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminSelectTuteePortfolioList(searchName,startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}


		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		

		return "admin/portfolio/portfolioListSearch.jsp";
	}

	private String adminPortfolioSearch(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		//		System.out.println("///////포트폴리오검색시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;


		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list= new ArrayList<PortfolioLikeVO>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		String searchName = request.getParameter("searchName");
		if(searchCateg.equals("0")){
			//			System.out.println("전체로 검색");

			portfolioListNumber = dao5.selectAllPortfolioCount(searchName);
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminSelectAllPortfolioList(searchName,startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else if(searchCateg.equals("1")){
			//			System.out.println("키워드로 검색");

			portfolioListNumber = dao5.selectPortfolioCount(searchName);
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminSelectPortfolioList(searchName,startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}else{
			//			System.out.println("튜티명으로 검색");

			portfolioListNumber = dao5.selectTuteePortfolioCount(searchName);
			if(portfolioListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(portfolioListNumber>0){
				list = dao5.adminSelectTuteePortfolioList(searchName,startRow, pageSize);
				pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				portfolioListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));


		return "admin/portfolio/portfolioListMain.jsp";
	}
	
	private String adminPortfolioDeleteAction(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		HttpSession session1 = request.getSession();
		int num = (int) session1.getAttribute("num");

		boolean result = dao5.deletePortfolio(num);
		if (result)
			return "starters?cmd=adminPortfolioList";
		return "admin/portfolio/portfolioDetail.jsp";
	}
	
	private String adminPortfolioListDetailAction(HttpServletRequest request) throws ServletException, IOException{
		dao5 = new PortfolioDAO();

		int num=Integer.parseInt(request.getParameter("num"));

		PortfolioVO2 pvo = dao5.adminPortfolioDetail(num);
		request.setAttribute("pvo", pvo);

		ArrayList<IntImageVO> intimagevo = dao5.getPortfolioImage(num);
		request.setAttribute("intimagevo", intimagevo);
		System.out.println("intimagevo"+intimagevo);
		
		
		ArrayList<IntJobSelectVO> job = dao5.getPortfoliosJob(num);
		request.setAttribute("job", job);
		System.out.println("job"+job);
		
		HttpSession session1 = request.getSession(true);
		session1.setAttribute("num", num);

		return "admin/portfolio/portfolioDetail.jsp";
	}

	
	private String adminPortfolioListPaging(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;
		
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCount();
		if(portfolioListNumber == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(portfolioListNumber>0){
			list = dao5.adminGetPortfolio(startRow, pageSize);
			pageCount = portfolioListNumber/pageSize + (portfolioListNumber % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			portfolioListNumber = 0;
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioList.jsp";
	}

	
	private String adminPortfolioList(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize = 9;
		
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioLikeVO> list = new ArrayList<PortfolioLikeVO>();
		portfolioListNumber = dao5.portfolioCount();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.adminGetPortfolio(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		
		return "admin/portfolio/portfolioListMain.jsp";
	}
//===============================================================	
	
	// 관리자 통계 시작
		private String getBuyDaySelectDatePaging(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();
			request.setAttribute("type", new Integer(0));
			
			String from = (String) session.getAttribute("from");
			String to = (String) session.getAttribute("to");
			
			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDaySelectDateCount(from, to);
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDaySelectDate(from, to, startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));

			return "admin/statis/allStatis/allStatisticsDaySelectDate.jsp";
		}







		private String getBuyDaySelectDate(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = request.getParameter("date");
			String from = date.substring(0,10);
			String to = date.substring(10,20);
			
//			System.out.println(from+"//////////"+to);
			session.setAttribute("date", date);
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			
			request.setAttribute("type", new Integer(0));
			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDaySelectDateCount(from, to);
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDaySelectDate(from, to, startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));

			return "admin/statis/allStatis/allStatisticsDaySelectDate.jsp";
		}



		private String getBuyDayAllPaging(HttpServletRequest request) throws Exception {		
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDayCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDay(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDayAll.jsp";
		}



		private String getBuyDayAll(HttpServletRequest request) throws Exception {		
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDayCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDay(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDayAll.jsp";
		}




		private String getBuyDaySixPaging(HttpServletRequest request) throws Exception {		
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDaySixCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDaySix(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDaySix.jsp";
		}


		private String getBuyDaySix(HttpServletRequest request) throws Exception {		
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDaySixCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDaySix(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDaySix.jsp";
		}





		private String getBuyDayThreePaging(HttpServletRequest request) throws Exception {
			
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDayThreeCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDayThree(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDayThree.jsp";
		}


		private String getBuyDayThree(HttpServletRequest request) throws Exception {
			
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDayThreeCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDayThree(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDayThree.jsp";
		}





		private String getBuyDayOnePaging(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDayOneCount();
			System.out.println("count"+count);
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDayOne(startRow,pageSize);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDayOne.jsp";
		}


		private String getBuyDayOne(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 6;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> one = null;
			count = statis.getBuyDayOneCount();
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				one = statis.getBuyDayOne(startRow,pageSize);
//				System.out.println(quarter+"///"+quarter);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(one.isEmpty())
				count = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("one", one); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
			return "admin/statis/allStatis/allStatisticsDayOne.jsp";
		}




		private String getBuyYearAllPaging(HttpServletRequest request) throws Exception {
			System.out.println("연도별 페이징");
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 3;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> year = null;
			count = statis.getBuyYearCount();
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				year = statis.getBuyYear(startRow,pageSize);
//				System.out.println(quarter+"///"+quarter);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(year.isEmpty())
				count = 0;

			System.out.println("count///"+count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("year", year); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			
		return "admin/statis/allStatis/allStatisticsYear.jsp";
		}

		private String MonthAllstatisticAfter(HttpServletRequest request) throws Exception {
			System.out.println("이후년도");
			request.setAttribute("type", new Integer(0));
			String yyyy = request.getParameter("yearNum");
			
			System.out.println(yyyy);
			
			List<Map<String, Object>> month = statis.getBuyMonth(yyyy);
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("month", month); // 차트의 제목
			request.setAttribute("yyyy", yyyy); // 차트의 제목
			return "admin/statis/allStatis/allStatisticsMonthAfter.jsp";
		}

		private String MonthAllstatisticPrev(HttpServletRequest request) throws Exception {
			System.out.println("이전년도");
			request.setAttribute("type", new Integer(0));
			String yyyy = request.getParameter("yearNum");
			System.out.println(yyyy);
			List<Map<String, Object>> month = statis.getBuyMonth(yyyy);
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("month", month); // 차트의 제목
			request.setAttribute("yyyy", yyyy); // 차트의 제목
			return "admin/statis/allStatis/allStatisticsMonthPrev.jsp";
		}


		private String selectAllstatistic(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			String select = request.getParameter("select");
//			request.setAttribute("type", new Integer(0));
			
			if(select.equals("0")){
				System.out.println("분기별");
				request.setAttribute("type", new Integer(0));


				List<Map<String, Object>> quarter = statis.getBuyQuarter();
//				System.out.println(quarter+"///"+quarter);
				request.setAttribute("quarter", quarter); // 차트의 제목
				
				Map<String, Object> quarterAll = statis.getPartAllCount();
//				System.out.println(quarterAll+"///"+quarterAll);
				request.setAttribute("quarterAll", quarterAll); // 차트의 제목
			return "admin/statis/allStatis/allBuyStatistics.jsp";
			}else if(select.equals("1")){
				System.out.println("일별");
				
				statis = new StatisticsDAO();
				request.setAttribute("type", new Integer(0));

				int count = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 6;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				List<Map<String, Object>> day = null;
				count = statis.getBuyDayCount();
				if(count == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(count>0){
					day = statis.getBuyDay(startRow,pageSize);
//					System.out.println(quarter+"///"+quarter);
					pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(day.isEmpty())
					count = 0;

				System.out.println("count///"+count);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("day", day); // 차트의 제목
				request.setAttribute("count", new Integer(count));
				return "admin/statis/allStatis/allStatisticsDayMain.jsp";
			}else if(select.equals("2")){
				System.out.println("월별");
				request.setAttribute("type", new Integer(0));
				Date today2 = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
				String yyyyMM2 = format2.format(today2);
				format2 = new SimpleDateFormat("yyyy");
				String yyyy = format2.format(today2);
				
				List<Map<String, Object>> month = statis.getBuyMonth(yyyy);
//				System.out.println(quarter+"///"+quarter);
				request.setAttribute("month", month); // 차트의 제목
				return "admin/statis/allStatis/allStatisticsMonthMain.jsp";
			}else if(select.equals("3")){
				
				statis = new StatisticsDAO();
				request.setAttribute("type", new Integer(0));

				int count = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 3;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				List<Map<String, Object>> year = null;
				count = statis.getBuyYearCount();
				if(count == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(count>0){
					year = statis.getBuyYear(startRow,pageSize);
//					System.out.println(quarter+"///"+quarter);
					pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(year.isEmpty())
					count = 0;

				System.out.println("count///"+count);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("year", year); // 차트의 제목
				request.setAttribute("count", new Integer(count));
				

			}
			return "admin/statis/allStatis/allStatisticsYearMain.jsp";

	}





		private String allstatistic(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));


			List<Map<String, Object>> quarter = statis.getBuyQuarter();
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("quarter", quarter); // 차트의 제목
			
			Map<String, Object> quarterAll = statis.getPartAllCount();
//			System.out.println(quarterAll+"///"+quarterAll);
			request.setAttribute("quarterAll", quarterAll); // 차트의 제목
			return "admin/statis/allStatis/allStatisticsMain.jsp";
		}






		private String interviewBuyStatiscticsDetail(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int payStatisnum=Integer.parseInt(request.getParameter("num"));

			BuyStatisticVO2 interviewBuyVO = statis.interviewBuyDetail(payStatisnum);
			request.setAttribute("interviewBuyVO", interviewBuyVO);

		
			HttpSession session = request.getSession(true);
			session.setAttribute("payStatisnum", payStatisnum);

			return "admin/statis/allStatis/interviewBuyDetail.jsp";
		}
		
		private String interviewBuyStatiscticsAllPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.interviewBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyList.jsp";
		}



		private String interviewBuyStatiscticsAll(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.interviewBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyList.jsp";
		}
		private String interviewStatisticPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.interviewBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyList.jsp";
		}
		private String interviewStatistic(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.interviewBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListMain.jsp";
		}
		private String interviewBuyStatiscticsSixPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.allInterviewSixListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuySixList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListSix.jsp";
		}

		private String interviewBuyStatiscticsSix(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.allInterviewSixListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuySixList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListSix.jsp";
		}

		private String interviewBuyStatiscticsThreePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.allInterviewThreeListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyThreeList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListThree.jsp";
		}

		private String interviewBuyStatiscticsThree(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.allInterviewThreeListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyThreeList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListThree.jsp";
		}

		private String interviewBuyStatiscticsOnePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.allInterviewOneListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyOneList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListOne.jsp";
		}

		private String interviewBuyStatiscticsOne(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			
			tutoringAllCount = statis.allInterviewOneListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.interviewBuyOneList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/interviewBuyListOne.jsp";
		}
		private String interviewBuyStatiscticsDatesPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = (String)session.getAttribute("date");
			String from = (String)session.getAttribute("from");
			String to = (String)session.getAttribute("to");

			
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;


			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			tutoringAllCount = statis.interviewBuyDateCount(from, to);
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectInterviewBuyDateList(from,to,startRow,pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
			
			return "admin/statis/allStatis/interviewBuyListDate.jsp";
		}




		private String interviewBuyStatiscticsDates(HttpServletRequest request) {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = request.getParameter("date");
			String from = date.substring(0,10);
			String to = date.substring(10,20);
			
			session.setAttribute("date", date);
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<BuyStatisticVO2> list = new ArrayList<BuyStatisticVO2>();
			tutoringAllCount = statis.interviewBuyDateCount(from, to);
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectInterviewBuyDateList(from,to,startRow,pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
			return "admin/statis/allStatis/interviewBuyListDate.jsp";
		}
		// 인터뷰 매출 끝
		private String tutoringBuyStatiscticsDetail(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringApplyStatisnum=Integer.parseInt(request.getParameter("num"));

			BuyStatisticVO tutoringBuyVO = statis.tutoringBuyDetail(tutoringApplyStatisnum);
			request.setAttribute("tutoringBuyVO", tutoringBuyVO);

		
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringApplyStatisnum", tutoringApplyStatisnum);

			return "admin/statis/allStatis/tutoringBuyDetail.jsp";
		}
		
		private String tutoringStatisticPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.tutoringBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.tutoringBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyList.jsp";
		}
		private String tutoringStatistic(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.tutoringBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.tutoringBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListMain.jsp";
		}
		private String tutoringBuyStatiscticsSixPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.allTutoringSixListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.allTutoringSixList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListSix.jsp";
		}
		private String tutoringBuyStatiscticsSix(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.allTutoringSixListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.allTutoringSixList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListSix.jsp";
		}
		private String tutoringBuyStatiscticsThreePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.allTutoringThreeListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.allTutoringThreeList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListThree.jsp";
		}
		private String tutoringBuyStatiscticsThree(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.allTutoringThreeListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.allTutoringThreeList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListThree.jsp";
		}
		private String tutoringBuyStatiscticsOnePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.allTutoringOneListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.allTutoringOneList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListOne.jsp";
		}
		private String tutoringBuyStatiscticsOne(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.allTutoringOneListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.allTutoringOneList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListOne.jsp";
		}
		private String tutoringBuyStatiscticsAllPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.tutoringBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.tutoringBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListAll.jsp";
		}
		private String tutoringBuyStatiscticsAll(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			
			tutoringAllCount = statis.tutoringBuyListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.tutoringBuyList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/allStatis/tutoringBuyListAll.jsp";
		}
		private String tutoringBuyStatiscticsDatesPaging(HttpServletRequest request) {
		statis = new StatisticsDAO();
		HttpSession session = request.getSession();

		String date = (String)session.getAttribute("date");
		String from = (String)session.getAttribute("from");
		String to = (String)session.getAttribute("to");

		
		int tutoringAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;


		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
		tutoringAllCount = statis.tutoringBuyDateCount(from, to);
		if(tutoringAllCount == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAllCount>0){
			list = statis.selectTutoringBuyDateList(from,to,startRow,pageSize);
			pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(list.isEmpty())
			tutoringAllCount = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
		return "admin/statis/allStatis/tutoringBuyListDate.jsp";
		}
		private String tutoringBuyStatiscticsDates(HttpServletRequest request) {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = request.getParameter("date");
			String from = date.substring(0,10);
			String to = date.substring(10,20);
			
			session.setAttribute("date", date);
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<BuyStatisticVO> list = new ArrayList<BuyStatisticVO>();
			tutoringAllCount = statis.tutoringBuyDateCount(from, to);
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringBuyDateList(from,to,startRow,pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
			return "admin/statis/allStatis/tutoringBuyListDate.jsp";
		}


		// 전체 통계 끝
		private String tutoringYearStatisAfter(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			String yyyy = request.getParameter("yearNum");
			request.setAttribute("type", new Integer(0));
			Map<String, Object> annualRevenue = statis.getPartRevenueTutoring(yyyy);
			Map<String, Object> annualRevenue2 = statis.getPartRevenueTutoringApply(yyyy);
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("annualRevenue", annualRevenue); // 차트의 제목
			request.setAttribute("yyyy", yyyy); // 차트의 제목
			request.setAttribute("annualRevenue2", annualRevenue2); // 차트의 제목
			
			return "admin/statis/tutoringStatis/tutoringYearStatisAfter.jsp";
		}
		private String tutoringYearStatisPrev(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			String yyyy = request.getParameter("yearNum");
			request.setAttribute("type", new Integer(0));
			Map<String, Object> annualRevenue = statis.getPartRevenueTutoring(yyyy);
			Map<String, Object> annualRevenue2 = statis.getPartRevenueTutoringApply(yyyy);
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("annualRevenue", annualRevenue); // 차트의 제목
			request.setAttribute("yyyy", yyyy); // 차트의 제목
			request.setAttribute("annualRevenue2", annualRevenue2); // 차트의 제목
			
			return "admin/statis/tutoringStatis/tutoringYearStatisPrev.jsp";
		}
		private String tutoringSelectStatis(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			String select = request.getParameter("select");
			request.setAttribute("type", new Integer(0));
			
			if(select.equals("4")){
//				System.out.println("월");
				String yyyyMM = request.getParameter("yyyyMM");
				System.out.println(yyyyMM);
				if(yyyyMM == null){
					// getRevenu에서 년도월이 필요하기때문에 객체 생성
					Date today = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
					yyyyMM = format.format(today);
					// 여기까지 dao 생성

					List<Map<String, Object>> revenue = statis.getRevenueTutoring(yyyyMM);
					System.out.println(revenue+"///"+yyyyMM);
					request.setAttribute("yyyyMM", yyyyMM); // 차트의 제목
					request.setAttribute("revenue", revenue);
				}
				return "admin/statis/tutoringStatis/barStatistics.jsp";
			}
			else if(select.equals("3")){
//				System.out.println("월");
				String yyyyMM = request.getParameter("yyyyMM");
				System.out.println(yyyyMM);
				if(yyyyMM == null){
					Date today2 = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
					String yyyyMM2 = format2.format(today2);
					format2 = new SimpleDateFormat("yyyy");
					String yyyy = format2.format(today2);
					// 여기까지 dao 생성

					Map<String, Object> annualRevenue = statis.getAnnualRevenueTutoring(yyyy);
					Map<String, Object> annualRevenue2 = statis.getAnnualRevenueTutoringApply(yyyy);
					// getAnnualRevenue
					request.setAttribute("yyyy", yyyy);
					request.setAttribute("yyyyMM2", yyyyMM2);
					request.setAttribute("annualRevenue", annualRevenue);
					request.setAttribute("annualRevenue2", annualRevenue2);
				}
				return "admin/statis/tutoringStatis/monthsStatistics.jsp";
			}else if(select.equals("2")){
////				System.out.println("월");
//				System.out.println(yyyyMM);
					Date today2 = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
					String yyyyMM2 = format2.format(today2);
					format2 = new SimpleDateFormat("yyyy");
					String yyyy = format2.format(today2);
					// 여기까지 dao 생성

					Map<String, Object> annualRevenue = statis.getPartRevenueTutoring(yyyy);
					Map<String, Object> annualRevenue2 = statis.getPartRevenueTutoringApply(yyyy);
					
					// getAnnualRevenue
					request.setAttribute("yyyy", yyyy);
					request.setAttribute("yyyyMM2", yyyyMM2);
					request.setAttribute("annualRevenue", annualRevenue);
					request.setAttribute("annualRevenue2", annualRevenue2);
					return "admin/statis/tutoringStatis/yearStatistics2.jsp";
			}
			else if(select.equals("1")){
					List<Map<String, Object>> tutortutoring = statis.getTutorTutoring();
					request.setAttribute("tutortutoring", tutortutoring);	
					
					List<Map<String, Object>> tutortutoring2 = statis.getTutorTutoringBuy();
					request.setAttribute("tutortutoring2", tutortutoring2);		
					return "admin/statis/tutoringStatis/tutorStatistics.jsp";
			}
			else{
				
			}
			return "starters?cmd=tutoringStatiscticsAll";
		}
		// 날짜로 검색
		private String tutoringDatesSelectActionPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = (String)session.getAttribute("date");
			String from = (String)session.getAttribute("from");
			String to = (String)session.getAttribute("to");

			
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;


			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			tutoringAllCount = statis.selectTutoringDateListCount(from, to);
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringDateList(from,to,startRow,pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
			
			return "admin/statis/tutoringStatis/tutoringAscDescDate.jsp";
		}
		
		private String tutoringDatesSelectAction(HttpServletRequest request) throws ServletException, IOException{
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = request.getParameter("date");
			String from = date.substring(0,10);
			String to = date.substring(10,20);
			
			session.setAttribute("date", date);
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			tutoringAllCount = statis.selectTutoringDateListCount(from, to);
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringDateList(from,to,startRow,pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/tutoringStatis/tutoringAscDescDate.jsp";
		}
		
		// 6개월 검색
			private String tutoringStatiscticsSixPaging(HttpServletRequest request) {
				statis = new StatisticsDAO();

				int tutoringAllCount = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 9;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
				ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
				
				tutoringAllCount = statis.selectTutoringSixListCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.selectTutoringThreeList(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("list", list);
				request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));


				return "admin/statis/tutoringStatis/tutoringAscDescSix.jsp";
			}


			private String tutoringStatiscticsSix(HttpServletRequest request) {
				statis = new StatisticsDAO();

				int tutoringAllCount = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 9;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
				ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
				
				tutoringAllCount = statis.selectTutoringSixListCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.selectTutoringThreeList(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("list", list);
				request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

				return "admin/statis/tutoringStatis/tutoringAscDescSix.jsp";
			}
			// 6개월 검색 끝
			
		// 3개월 검색
		private String tutoringStatiscticsThreePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.selectTutoringThreeListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringThreeList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));


			return "admin/statis/tutoringStatis/tutoringAscDescThree.jsp";
		}

		private String tutoringStatiscticsThree(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.selectTutoringThreeListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringThreeList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/tutoringStatis/tutoringAscDescThree.jsp";
		}
		// 3개월 검색 끝
		
		// 1개월 검색
		private String tutoringStatiscticsOnePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.selectTutoringAmonthListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringAmonthList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));


			return "admin/statis/tutoringStatis/tutoringAscDescOne.jsp";
		}

		private String tutoringStatiscticsOne(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.selectTutoringAmonthListCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.selectTutoringAmonthList(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/tutoringStatis/tutoringAscDescOne.jsp";
		}
		// 1개월 검색 끝
		
		// 전체검색
		private String tutoringStatiscticsAllPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.tutoringCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.getTutoring(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));


			return "admin/statis/tutoringStatis/tutoringAscDescAll.jsp";
		}

		private String tutoringStatiscticsAll(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.tutoringCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.getTutoring(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/tutoringStatis/tutoringAscDescAll.jsp";
		}
		private String tutoringStatiscticsAscDescPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();
//			System.out.println("////////////조회수순");
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize =9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			HttpSession session = request.getSession();
			String select = (String)session.getAttribute("tutoringselect");
			if(select.equals("1")){ // 수입 내림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringMoneyDesc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("2")){ // 수입 올림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringMoneyAsc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("3")){ // 튜터링 횟수 내림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringBuyDesc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("4")){ // 튜터링 횟수 올림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringBuyAsc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("5")){ // 후기 내림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringReviewDesc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("6")){ // 후기 올림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringReviewAsc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}



			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
			return "admin/statis/tutoringStatis/tutoringAscDescLike.jsp";
		}
		private String tutoringStatiscticsAscDesc(HttpServletRequest request) {
			statis = new StatisticsDAO();
//			System.out.println("////////////조회수순");
			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize =9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			String select = request.getParameter("select");
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringselect", select);
			if(select.equals("1")){ // 수입 내림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringMoneyDesc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("2")){ // 수입 올림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringMoneyAsc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("3")){ // 튜터링 횟수 내림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringReviewDesc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("4")){ // 튜터링 횟수 올림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringReviewAsc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("5")){ // 후기 내림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringReviewDesc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}else if(select.equals("6")){ // 후기 올림차순
				tutoringAllCount = statis.tutoringCount();
				if(tutoringAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(tutoringAllCount>0){
					list = statis.getTutoringReviewAsc(startRow, pageSize);
					pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					tutoringAllCount = 0;
			}



			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));
			return "admin/statis/tutoringStatis/tutoringAscDescLike.jsp";
		}
		// 튜터링 통계 상세
		private String tutoringStatiscticsDetail(HttpServletRequest request) {
			statis = new StatisticsDAO();
			
			int tutoringStatisticnum=Integer.parseInt(request.getParameter("num"));

			TutoringVO3 tutoringVO = statis.tutoringDetail(tutoringStatisticnum);
			request.setAttribute("tutoringVO", tutoringVO);

//			System.out.println("/////"+tutoringVO);
			
//			ArrayList<IntImageVO> images = dao2.getTutoringsImage(tutoringStatisticnum);
//			request.setAttribute("images", images);

			ArrayList<TutoringTimeVO2> times = statis.getTutoringsTime(tutoringStatisticnum);
			request.setAttribute("times", times);
//			System.out.println("times"+times);
			
//			int timeCount = dao2.getTutoringsTimeCount(tutoringStatisticnum);
//			request.setAttribute("timeCount", timeCount);
//			System.out.println("timeCount////////"+timeCount);
			
			ArrayList<IntJobSelectVO> job = statis.getTutoringsJob(tutoringStatisticnum);
			request.setAttribute("job", job);
//			System.out.println("job"+job);

			ArrayList<TutoringDayVO> days = statis.getTutoringsDay(tutoringStatisticnum);
			request.setAttribute("days", days);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringStatisticnum", tutoringStatisticnum);

			return "admin/statis/tutoringStatis/tutoringDetail.jsp";
		}
		private String tutoringStatiscticsPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.tutoringCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.getTutoring(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/tutoringStatis/tutoringStatistics.jsp";
		}

		private String tutoringStatisctics(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int tutoringAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
			
			tutoringAllCount = statis.tutoringCount();
			if(tutoringAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringAllCount>0){
				list = statis.getTutoring(startRow, pageSize);
				pageCount = tutoringAllCount/pageSize + (tutoringAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

			return "admin/statis/tutoringStatis/tutoringStatisticsMain.jsp";
		}

		private String reviewStatiscticsDetail(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			
			int ReviewStatisticnum=Integer.parseInt(request.getParameter("num"));

			StatisticVO reviewVO = statis.statisticDetail(ReviewStatisticnum);
			request.setAttribute("reviewVO", reviewVO);

			ArrayList<reviewLikeReportVO> list = new ArrayList<reviewLikeReportVO>();
			list = statis.ReviewLikeUser(ReviewStatisticnum);
			request.setAttribute("list", list);
//			System.out.println("/////"+list);
			
			List<Map<String, Object>> memberJob = statis.getTutorReviewJob(ReviewStatisticnum);
//			System.out.println(memberJob+"///"+memberJob);
			request.setAttribute("memberJob", memberJob); // 차트의 제목
			
			HttpSession session = request.getSession(true);
			session.setAttribute("ReviewStatisticnum", ReviewStatisticnum);


				return "admin/statis/reviewStatis/reviewDetail.jsp";
		}

		private String reviewPartStatisPrev(HttpServletRequest request) throws Exception {
			System.out.println("이전년도");
			request.setAttribute("type", new Integer(0));
			String yyyy = request.getParameter("yearNum");
			Map<String, Object> annualRevenue = statis.getYearRevenue(yyyy);
			
			// getAnnualRevenue
			request.setAttribute("annualRevenue", annualRevenue);
			request.setAttribute("yyyy", yyyy);
			return "admin/statis/reviewStatis/reviewPartStatisPrev.jsp";
		}
		private String reviewPartStatisAfter(HttpServletRequest request) throws Exception {
			System.out.println("이전년도");
			request.setAttribute("type", new Integer(0));
			String yyyy = request.getParameter("yearNum");
			Map<String, Object> annualRevenue = statis.getYearRevenue(yyyy);
			
			// getAnnualRevenue
			request.setAttribute("annualRevenue", annualRevenue);
			request.setAttribute("yyyy", yyyy);
			return "admin/statis/reviewStatis/reviewPartStatisAfter.jsp";
		}
		
		private String reviewMonthStatis(HttpServletRequest request) throws Exception {
			statis = new StatisticsDAO();
			String select = request.getParameter("select");
			request.setAttribute("type", new Integer(0));
			
			if(select.equals("4")){
				System.out.println("월");
				String yyyyMM = request.getParameter("yyyyMM");
				System.out.println(yyyyMM);
				if(yyyyMM == null){
					// getRevenu에서 년도월이 필요하기때문에 객체 생성
					Date today = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
					yyyyMM = format.format(today);
					// 여기까지 dao 생성

					List<Map<String, Object>> revenue = statis.getRevenue(yyyyMM);
					System.out.println(revenue+"///"+yyyyMM);
					request.setAttribute("yyyyMM", yyyyMM); // 차트의 제목
					request.setAttribute("revenue", revenue);
				}
				return "admin/statis/reviewStatis/barStatistics.jsp";
			}else if(select.equals("3")){
//				System.out.println("월");
				String yyyyMM = request.getParameter("yyyyMM");
				System.out.println(yyyyMM);
				if(yyyyMM == null){
					Date today2 = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
					String yyyyMM2 = format2.format(today2);
					format2 = new SimpleDateFormat("yyyy");
					String yyyy = format2.format(today2);
					// 여기까지 dao 생성

					Map<String, Object> annualRevenue = statis.getAnnualRevenue(yyyy);
					
					// getAnnualRevenue
					request.setAttribute("yyyy", yyyy);
					request.setAttribute("yyyyMM2", yyyyMM2);
					request.setAttribute("annualRevenue", annualRevenue);
				}
				return "admin/statis/reviewStatis/monthsStatistics.jsp";
			}else if(select.equals("2")){
//				System.out.println("월");
//				System.out.println(yyyyMM);
					Date today2 = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
					String yyyyMM2 = format2.format(today2);
					format2 = new SimpleDateFormat("yyyy");
					String yyyy = format2.format(today2);
					// 여기까지 dao 생성

					Map<String, Object> annualRevenue = statis.getYearRevenue(yyyy);
					
					// getAnnualRevenue
					request.setAttribute("yyyy", yyyy);
					request.setAttribute("yyyyMM2", yyyyMM2);
					request.setAttribute("annualRevenue", annualRevenue);
					
					return "admin/statis/reviewStatis/yearStatistics2.jsp";
			}else if(select.equals("1")){
//				System.out.println("월");
//				System.out.println(yyyyMM);
//					Date today2 = new Date(); // 아규먼트가 없으면 현재 시각으로 인지한다.
//					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 정의
//					String yyyyMM2 = format2.format(today2);
//					format2 = new SimpleDateFormat("yyyy");
//					String yyyy = format2.format(today2);
					// 여기까지 dao 생성

					List<Map<String, Object>> tutorReview = statis.getTutorReview();
//					System.out.println(tutorReview);
//					// getAnnualRevenue
//					request.setAttribute("yyyy", yyyy);
//					request.setAttribute("yyyyMM2", yyyyMM2);
					request.setAttribute("tutorReview", tutorReview);				
					return "admin/statis/reviewStatis/tutorStatistics.jsp";
			}else{
			
			}
			return "starters?cmd=reviewStatiscticsAll";
		}


		// 통계시작
		// 좋아요 올림차순 내림차순
		private String reviewStatiscticsLikePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();
//			System.out.println("////////////조회수순");
			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize =9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			String select = request.getParameter("select");
//			System.out.println("select//////////"+select);
			if(select.equals("1")){
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeListDesc(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
			}else if(select.equals("2")){
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeListAsc(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
			}else{
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeList(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
			}



			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
			return "admin/statis/reviewStatis/reviewStatisticsLike.jsp";
		}
		private String reviewStatiscticsLike(HttpServletRequest request) {
			statis = new StatisticsDAO();
//			System.out.println("////////////조회수순");
			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize =9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			String select = request.getParameter("select");
//			System.out.println("select//////////"+select);
			if(select.equals("1")){
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeListDesc(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
			}else if(select.equals("2")){
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeListAsc(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
			}else{
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeList(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
			}



			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
			return "admin/statis/reviewStatis/reviewStatisticsLike.jsp";
		}

		// 날짜로 검색
		private String reviewDateSelectActionPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = (String)session.getAttribute("date");
			String from = (String)session.getAttribute("from");
			String to = (String)session.getAttribute("to");

			
			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;


			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			reviewAllCount = statis.selectDateListCount(from, to);
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.selectDateList(from,to,startRow,pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
			
			return "admin/statis/reviewStatis/reviewStatisticsDate.jsp";
		}
		
		private String reviewDateSelectAction(HttpServletRequest request) throws ServletException, IOException{
			statis = new StatisticsDAO();
			HttpSession session = request.getSession();

			String date = request.getParameter("date");
			String from = date.substring(0,10);
			String to = date.substring(10,20);
			
			session.setAttribute("date", date);
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			
			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			reviewAllCount = statis.selectDateListCount(from, to);
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.selectDateList(from,to,startRow,pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;


			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

			return "admin/statis/reviewStatis/reviewStatisticsDate.jsp";
		}
		
		// 6개월 검색
			private String reviewStatiscticsSixPaging(HttpServletRequest request) {
				statis = new StatisticsDAO();

				int reviewAllCount = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 9;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
				ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
				
				reviewAllCount = statis.selectSixListCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.selectSixList(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("list", list);
				request.setAttribute("reviewAllCount", new Integer(reviewAllCount));


				return "admin/statis/reviewStatis/reviewStatisticsSix.jsp";
			}


			private String reviewStatiscticsSix(HttpServletRequest request) {
				statis = new StatisticsDAO();

				int reviewAllCount = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 9;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
				ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
				
				reviewAllCount = statis.selectSixListCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.selectSixList(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("list", list);
				request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

				return "admin/statis/reviewStatis/reviewStatisticsSix.jsp";
			}
			// 6개월 검색 끝
			
		// 3개월 검색
		private String reviewStatiscticsThreePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			
			reviewAllCount = statis.selectThreeListCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.selectThreeList(startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));


			return "admin/statis/reviewStatis/reviewStatisticsThree.jsp";
		}

		private String reviewStatiscticsThree(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			
			reviewAllCount = statis.selectThreeListCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.selectThreeList(startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

			return "admin/statis/reviewStatis/reviewStatisticsThree.jsp";
		}
		// 3개월 검색 끝
		
		// 1개월 검색
		private String reviewStatiscticsOnePaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			
			reviewAllCount = statis.selectAmonthListCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.selectAmonthList(startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));


			return "admin/statis/reviewStatis/reviewStatisticsOne.jsp";
		}

		private String reviewStatiscticsOne(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			
			reviewAllCount = statis.selectAmonthListCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.selectAmonthList(startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

			return "admin/statis/reviewStatis/reviewStatisticsOne.jsp";
		}
		// 1개월 검색 끝
		
		// 전체검색
		private String reviewStatiscticsAllPaging(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			
			reviewAllCount = statis.selectReivewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.getReviewLikeList(startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));


			return "admin/statis/reviewStatis/reviewStatisticsAll.jsp";
		}

		private String reviewStatiscticsAll(HttpServletRequest request) {
			statis = new StatisticsDAO();

			int reviewAllCount = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 9;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
			ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
			
			reviewAllCount = statis.selectReivewCount();
			if(reviewAllCount == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(reviewAllCount>0){
				list = statis.getReviewLikeList(startRow, pageSize);
				pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				reviewAllCount = 0;
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("list", list);
			request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

			return "admin/statis/reviewStatis/reviewStatisticsAll.jsp";
		}
		// 처음 목록
			private String reviewStatiscticsPaging(HttpServletRequest request) {
				statis = new StatisticsDAO();

				int reviewAllCount = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 9;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
				ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
				
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeList(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("list", list);
				request.setAttribute("reviewAllCount", new Integer(reviewAllCount));


				return "admin/statis/reviewStatis/reviewStatistics.jsp";
			}

			private String reviewStatisctics(HttpServletRequest request) {
				statis = new StatisticsDAO();

				int reviewAllCount = 0;
				int pageCount = 0;
				int startPage = 0;
				int pageBlock = 0;
				int endPage = 0;

				int pageSize = 9;
				String pageNum = request.getParameter("pageNum");
				if(pageNum == null)
					pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				//			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
				ArrayList<StatisticVO> list = new ArrayList<StatisticVO>();
				
				reviewAllCount = statis.selectReivewCount();
				if(reviewAllCount == (currentPage-1) * pageSize)
					currentPage -= 1;
				int startRow = (currentPage - 1) * pageSize + 1;
				if(reviewAllCount>0){
					list = statis.getReviewLikeList(startRow, pageSize);
					pageCount = reviewAllCount/pageSize + (reviewAllCount % pageSize ==0 ? 0 : 1);
					startPage = 1;

					if(currentPage % 5 != 0)
						startPage = (int)(currentPage/5) * 5 + 1;
					else
						startPage = ((int)(currentPage/5)-1) * 5 + 1;
					pageBlock = 5;
					endPage = startPage + pageBlock -1;
					if(endPage > pageCount) 
						endPage = pageCount;
				}
				if(list.isEmpty())
					reviewAllCount = 0;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("list", list);
				request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

				return "admin/statis/reviewStatis/reviewStatisticsMain.jsp";
			}
		// 통계 끝
	/**관리자 튜터링 등록현황 시작 ==================================*/
	/**기간조회페이징*/
	private String adminTutoringDateSelectPaging(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("날짜 조회 페이징 시작==============");
		HttpSession session = request.getSession();
		
		String date = (String)session.getAttribute("date");
		System.out.println("date" + date);
		String from = (String)session.getAttribute("from");
		System.out.println("from" + from);
		String to = (String)session.getAttribute("to");
		System.out.println("to" + to);
		
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetDateCount(from,to);
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetDate(from,to,startRow,pageSize);
			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;

		System.out.println(pageNum);
		System.out.println(currentPage);
		System.out.println(startPage);
		System.out.println(endPage);
		System.out.println(tutoringList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListSelectDateSearch.jsp";
	}
	/**기간조회*/
	private String adminTutoringDateSelectAction(HttpServletRequest request) throws ServletException, IOException{
		dao2 = new TutoringDAO();
		System.out.println("날짜 조회==============");
		HttpSession session = request.getSession();

		String date = request.getParameter("date");
		System.out.println("date" + date);
		String from = date.substring(0,10);
		System.out.println("from" + from);
		String to = date.substring(10,20);
		System.out.println("to" + to);
		
//		HttpSession session = request.getSession(true);
		session.setAttribute("date", date);
		session.setAttribute("from", from);
		session.setAttribute("to", to);
		
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetDateCount(from, to);
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetDate(from,to,startRow,pageSize);
			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;

		System.out.println(pageNum);
		System.out.println(currentPage);
		System.out.println(startPage);
		System.out.println(endPage);
		System.out.println(tutoringList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListSelectDateSearch.jsp";
	}
	
	/**6개월검색*/
	private String adminTutoringListSixMonthPagingAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("6개월페이징시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetSixMonthCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetSixMonth(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListSixMonth.jsp";
	}

	private String adminTutoringListSixMonth(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("6개월시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetSixMonthCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetSixMonth(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListSixMonth.jsp";
	}
	
	/**3개월검색*/
	private String adminTutoringListThreeMonthPagingAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("3개월페이징시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetSixMonthCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetSixMonth(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListThreeMonth.jsp";
	}

	private String adminTutoringListThreeMonth(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("3개월시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetThreeMonthCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetThreeMonth(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListThreeMonth.jsp";
	}
	
	/**1개월검색*/
	private String adminTutoringListAmonthPagingAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("1개월페이징시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetAmonthCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetAmonth(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListAmonth.jsp";
	}

	private String adminTutoringListAmonth(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("1개월시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> tutoringList = new ArrayList<TutoringVO3>();
		tutoringAll = dao2.adminGetAmonthCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.adminGetAmonth(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListAmonth.jsp";
	}
	
	/**번호순,최신순*/
	private String adminTutoringListSelectPaging(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("////////////번호순최신순페이징");
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
		
		HttpSession session = request.getSession();
		String select = (String)session.getAttribute("select");
		
		
		if(select.equals("1")){
			tutoringListNumber = dao2.tutoringCount();
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminGetTutoring(startRow, pageSize);
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringListNumber = 0;
		}else if(select.equals("2")){
			tutoringListNumber = dao2.tutoringCount();
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminGetDateTutoring(startRow, pageSize);
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));
		
		return "admin/tutoring/tutoringListSelect.jsp";
	}
	
	private String adminTutoringListSelectAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("////////////번호순최신순");
		
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> list = new ArrayList<TutoringVO3>();
		String select = request.getParameter("select");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("select", select);
		
		System.out.println("select//////////"+select);
		if(select.equals("1")){
			tutoringListNumber = dao2.tutoringCount();
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminGetTutoring(startRow, pageSize);
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringListNumber = 0;
		}else if(select.equals("2")){
			tutoringListNumber = dao2.tutoringCount();
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminGetDateTutoring(startRow, pageSize);
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));
		
		return "admin/tutoring/tutoringListSelect.jsp";
	}
	// 검색
	private String adminTutoringSearchPaging(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;
		//		String searchName = request.getParameter("searchName");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		System.out.println("currentPage가1이 나와야함" + currentPage);
		ArrayList<TutoringVO3> list= new ArrayList<TutoringVO3>();

		HttpSession session = request.getSession();
		String tutoringSearchName = (String)session.getAttribute("adminTutoringSearchName");
		String searchCateg = (String)session.getAttribute("adminSearchCateg");


		if(searchCateg.equals("0")){
			System.out.println("페이징 설마여기>..");
			tutoringListNumber = dao2.selectTutoringCount(tutoringSearchName);
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminSelectTutoringList(tutoringSearchName,startRow, pageSize);
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringListNumber = 0;

		}else if(searchCateg.equals("1")){
			System.out.println("튜터명으로 검색");
			tutoringListNumber = dao2.selectTutorCount(tutoringSearchName);
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminSelectTutorNameList(tutoringSearchName,startRow, pageSize);
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
	
			if(list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("tutoringSearchName", tutoringSearchName);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));

		return "admin/tutoring/tutoringListSearch.jsp";
	}
	// 검색
	private String adminTutoringSearch(HttpServletRequest request) {
		dao2 = new TutoringDAO();

		System.out.println("///////튜터링검색시작");
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO3> list= new ArrayList<TutoringVO3>();
		// 검색 카테고리
		String adminSearchCateg = request.getParameter("searchCateg");
		System.out.println("adminSearchCateg"+adminSearchCateg);

		String adminTutoringSearchName = request.getParameter("searchName");
		System.out.println("adminTutoringSearchName"+adminTutoringSearchName);

		if(adminSearchCateg.equals("0")){
			System.out.println("설마여기>..");
			tutoringListNumber = dao2.selectTutoringCount(adminTutoringSearchName);
			
			System.out.println("tutoringListNumber"+tutoringListNumber);
			
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminSelectTutoringList(adminTutoringSearchName,startRow, pageSize);
				
				System.out.println("list"+list);
				
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("adminTutoringSearchName", adminTutoringSearchName);
			session.setAttribute("adminSearchCateg", adminSearchCateg);

		}else if(adminSearchCateg.equals("1")){
			System.out.println("튜터명으로 검색");
			tutoringListNumber = dao2.selectTutorCount(adminTutoringSearchName);
			
			System.out.println("tutoringListNumber"+tutoringListNumber);
			
			if(tutoringListNumber == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(tutoringListNumber>0){
				list = dao2.adminSelectTutorNameList(adminTutoringSearchName,startRow, pageSize);
				
				System.out.println("list"+list);
				
				pageCount = tutoringListNumber/pageSize + (tutoringListNumber % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
	
			if(list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", adminTutoringSearchName);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));

		return "admin/tutoring/tutoringListMain.jsp";
	}

	// 디테일
	private String adminTutoringListDetailAction(HttpServletRequest request) throws ServletException, IOException{
		System.out.println("관리자튜터링 디테일시작~~~~");
		dao2 = new TutoringDAO();
	
		int tutoringnum=Integer.parseInt(request.getParameter("num"));

		// 상세
		TutoringVO3 tvo2 = dao2.tutoringDetail(tutoringnum);
		request.setAttribute("tvo2", tvo2);
		// 특정 튜터링 듣는 인원
		int tutoringCount = dao2.tutoringCount(tutoringnum);
		request.setAttribute("tutoringCount", tutoringCount);
		// 직종
		IntJobSelectVO job = dao2.AdminTutoringInfo(tutoringnum);
		request.setAttribute("job", job);
		
		HttpSession session1 = request.getSession(true);
		session1.setAttribute("tutoringnum", tutoringnum);
		
		return "admin/tutoring/tutoringDetail.jsp";
	}
	
	private String adminTutoringDeleteAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		HttpSession session1 = request.getSession();
		int number = (int) session1.getAttribute("tutoringnum");
		// System.out.println("number"+"/" + number);
		boolean result = dao2.deleteTutoring(number);
		// System.out.println("number"+"/" + number);
		// System.out.println("result"+result);
		// System.out.println("tutoringNumber"+number);
		if (result)
			return "starters?cmd=adminTutoringList";
		return "admin/tutoring/tutoringDetail.jsp";
	}
	
	private String adminTutoringListPagingAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();

		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTutoringVO> tutoringList = new ArrayList<AdminTutoringVO>();
		tutoringAll = dao2.tutoringCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.AdminTutoringList(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringList.jsp";
	}

	// 전체보기
	private String adminTutoringListAll(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		System.out.println("전체보기시작");
		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTutoringVO> tutoringList = new ArrayList<AdminTutoringVO>();
		tutoringAll = dao2.tutoringCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.AdminTutoringList(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringList.jsp";
	}
	
	private String adminTutoringList(HttpServletRequest request) {
		dao2 = new TutoringDAO();

		int tutoringAll = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;
		int pageSize =9;

		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTutoringVO> tutoringList = new ArrayList<AdminTutoringVO>();
		tutoringAll = dao2.tutoringCount();
		if(tutoringAll == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(tutoringAll>0){
			tutoringList = dao2.AdminTutoringList(startRow, pageSize);

			pageCount = tutoringAll/pageSize + (tutoringAll % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(tutoringList.isEmpty())
			tutoringAll = 0;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("tutoringAll", new Integer(tutoringAll));

		return "admin/tutoring/tutoringListMain.jsp";
	}
	

//------------------------------------------------------------------------------------------------------------------------	
/*
	private String companyRegisterDateStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		request.setAttribute("type", new Integer(0));
		System.out.println("분기별");
		request.setAttribute("type", new Integer(0));
		
		Date today2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
		String yyyy = format2.format(today2);
		// 여기까지 dao 생성
		Map<String, Object> registerDateCompany = statis.RegisterDateCompany(yyyy);
		
		// getAnnualRevenue
		request.setAttribute("yyyy", yyyy);
		request.setAttribute("annualRevenue", annualRevenue);
		request.setAttribute("registerDateCompany", registerDateCompany); // 차트의 제목
		return "adminMember/CompanyYearStatistics.jsp";
	}*/
/*	private String memberRegisterDateStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		request.setAttribute("type", new Integer(0));
		Map<String, Object> registerDateMember = statis.RegisterDateMember();
		request.setAttribute("registerDateMember", registerDateMember);
		return "adminMember/registerDateMemberS.jsp";
	}*/
	
	private String memberAgeStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		request.setAttribute("type", new Integer(0));
		Map<String, Object> ageMember = statis.ageMember();
		request.setAttribute("ageMember", ageMember);
		return "adminMember/ageMemberS.jsp";
	}

	
	private String memberGenderStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		request.setAttribute("type", new Integer(0));
		Map<String, Object> genderMember = statis.genderMember();
		request.setAttribute("genderMember", genderMember);
		return "adminMember/genderMemberS.jsp";
	}
	
	
	private String companyStatistic(HttpServletRequest request) throws Exception {

		statis = new StatisticsDAO();
		request.setAttribute("type", new Integer(0));
/*		System.out.println("분기별");
		request.setAttribute("type", new Integer(0));*/
		
		Date today2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
		String yyyy = format2.format(today2);
		// 여기까지 dao 생성
		Map<String, Object> registerDateCompany = statis.RegisterDateCompany(yyyy);
		
		// getAnnualRevenue
		request.setAttribute("yyyy", yyyy);
/*		request.setAttribute("annualRevenue", annualRevenue);*/
		request.setAttribute("registerDateCompany", registerDateCompany); // 차트의 제목

	
		return "adminMember/CompanyStatisticsMain.jsp";
	}
	
	private String MemberStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		request.setAttribute("type", new Integer(0));


		Map<String, Object> ageMember = statis.ageMember();
//		System.out.println(quarter+"///"+quarter);
		request.setAttribute("ageMember", ageMember); // 차트의 제목
	
		return "adminMember/allStatisticsMain.jsp";
	}

	
	private String selectMemberStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		String select = request.getParameter("select");
//		request.setAttribute("type", new Integer(0));
		
		if(select.equals("0")){
			System.out.println("연령별");
			request.setAttribute("type", new Integer(0));


			Map<String, Object> ageMember = statis.ageMember();
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("ageMember", ageMember); // 차트의 제목
		return "adminMember/ageMemberS.jsp";
		}else if(select.equals("1")){
			System.out.println("성별");
			request.setAttribute("type", new Integer(0));
			Map<String, Object> genderMember = statis.genderMember();
//			System.out.println(quarter+"///"+quarter);
			request.setAttribute("genderMember", genderMember); // 차트의 제목
		return "adminMember/genderMemberS.jsp";

		}else if(select.equals("2")){
			System.out.println("분기별");
			request.setAttribute("type", new Integer(0));
			
			Date today2 = new Date();
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
			String yyyy = format2.format(today2);
			// 여기까지 dao 생성
			Map<String, Object> registerDateMember = statis.RegisterDateMember(yyyy);
			
			// getAnnualRevenue
			request.setAttribute("yyyy", yyyy);
			request.setAttribute("registerDateMember", registerDateMember);
		return "adminMember/MemberYearStatistics.jsp";
		}else if(select.equals("3")){
			
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 3;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> year = null;
			count = statis.getMemberYearCount();
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				year = statis.getMemberYear(startRow,pageSize);
//				System.out.println(quarter+"///"+quarter);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(year.isEmpty())
				count = 0;

			System.out.println("count///"+count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("year", year); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			

		}
		return "adminMember/allStatisticsMemberMain.jsp";

}
	
	private String memberYearStatisAfter(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		String yyyy = request.getParameter("yearNum");
		request.setAttribute("type", new Integer(0));
		Map<String, Object> registerDateMember = statis.RegisterDateMember(yyyy);
//		System.out.println(quarter+"///"+quarter);
		request.setAttribute("registerDateMember", registerDateMember); // 차트의 제목
		request.setAttribute("yyyy", yyyy); // 차트의 제목

		return "adminMember/memberYearStatisAfter.jsp";
	}
	private String memberYearStatisPrev(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		String yyyy = request.getParameter("yearNum");
		request.setAttribute("type", new Integer(0));
		Map<String, Object> registerDateMember = statis.RegisterDateMember(yyyy);
//		System.out.println(quarter+"///"+quarter);
		request.setAttribute("registerDateMember", registerDateMember); // 차트의 제목
		request.setAttribute("yyyy", yyyy); // 차트의 제목
		
		return "adminMember/memberYearStatisPrev.jsp";
	}
	
	private String getMemberYearAllPaging(HttpServletRequest request) throws Exception {
		System.out.println("연도별 페이징");
		statis = new StatisticsDAO();


		int count = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 3;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		List<Map<String, Object>> year = null;
		count = statis.getMemberYearCount();
		if(count == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(count>0){
			year = statis.getMemberYear(startRow,pageSize);
//			System.out.println(quarter+"///"+quarter);
			pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(year.isEmpty())
			count = 0;

		System.out.println("count///"+count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("year", year); // 차트의 제목
		request.setAttribute("count", new Integer(count));
		
	return "adminMember/allStatisticsMember.jsp";
	}

	
	private String selectCompanyStatistic(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		String select = request.getParameter("select");
//		request.setAttribute("type", new Integer(0));
		
		if(select.equals("0")){
			System.out.println("분기별");
			request.setAttribute("type", new Integer(0));
			
			Date today2 = new Date();
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
			String yyyy = format2.format(today2);
			// 여기까지 dao 생성
			Map<String, Object> registerDateCompany = statis.RegisterDateCompany(yyyy);
			
			// getAnnualRevenue
			request.setAttribute("yyyy", yyyy);
			request.setAttribute("registerDateCompany", registerDateCompany);
			return "adminMember/CompanyYearStatistics.jsp";
		}else if(select.equals("1")){
			
			statis = new StatisticsDAO();
			request.setAttribute("type", new Integer(0));

			int count = 0;
			int pageCount = 0;
			int startPage = 0;
			int pageBlock = 0;
			int endPage = 0;

			int pageSize = 3;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			List<Map<String, Object>> year = null;
			count = statis.getCompanyYearCount();
			if(count == (currentPage-1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if(count>0){
				year = statis.getCompanyYear(startRow,pageSize);
//				System.out.println(quarter+"///"+quarter);
				pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
				startPage = 1;

				if(currentPage % 5 != 0)
					startPage = (int)(currentPage/5) * 5 + 1;
				else
					startPage = ((int)(currentPage/5)-1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock -1;
				if(endPage > pageCount) 
					endPage = pageCount;
			}
			if(year.isEmpty())
				count = 0;

			System.out.println("count///"+count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("year", year); // 차트의 제목
			request.setAttribute("count", new Integer(count));
			

		}
		return "adminMember/allStatisticsCompanyMain.jsp";

}		// 전체 통계 끝
	private String companyYearStatisAfter(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		String yyyy = request.getParameter("yearNum");
		request.setAttribute("type", new Integer(0));
		Map<String, Object> registerDateCompany = statis.RegisterDateCompany(yyyy);
//		System.out.println(quarter+"///"+quarter);
		request.setAttribute("registerDateCompany", registerDateCompany); // 차트의 제목
		request.setAttribute("yyyy", yyyy); // 차트의 제목

		return "adminMember/companyYearStatisAfter.jsp";
	}
	private String companyYearStatisPrev(HttpServletRequest request) throws Exception {
		statis = new StatisticsDAO();
		String yyyy = request.getParameter("yearNum");
		request.setAttribute("type", new Integer(0));
		Map<String, Object> registerDateCompany = statis.RegisterDateCompany(yyyy);
//		System.out.println(quarter+"///"+quarter);
		request.setAttribute("registerDateCompany", registerDateCompany); // 차트의 제목
		request.setAttribute("yyyy", yyyy); // 차트의 제목
		
		return "adminMember/companyYearStatisPrev.jsp";
	}
	
	private String getCompanyYearAllPaging(HttpServletRequest request) throws Exception {
		System.out.println("연도별 페이징");
		statis = new StatisticsDAO();


		int count = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 3;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		List<Map<String, Object>> year = null;
		count = statis.getCompanyYearCount();
		if(count == (currentPage-1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if(count>0){
			year = statis.getCompanyYear(startRow,pageSize);
//			System.out.println(quarter+"///"+quarter);
			pageCount = count/pageSize + (count % pageSize ==0 ? 0 : 1);
			startPage = 1;

			if(currentPage % 5 != 0)
				startPage = (int)(currentPage/5) * 5 + 1;
			else
				startPage = ((int)(currentPage/5)-1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount) 
				endPage = pageCount;
		}
		if(year.isEmpty())
			count = 0;

		System.out.println("count///"+count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("year", year); // 차트의 제목
		request.setAttribute("count", new Integer(count));
		
	return "adminMember/allStatisticsCompany.jsp";
	}

	

	
	// 관리자 전체
	private String adminMemberListNumber(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		int adminMemberListNumber = dao.adminAllMemberCount();
		request.setAttribute("adminMemberTuteeListNumber", adminMemberListNumber);
		return "adminMember/adminMemberListMain.jsp";
	}
	
	
	// 이름순 아이디순
	private String adminMemberListSelectPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();

		HttpSession session = request.getSession();
		String select = (String)session.getAttribute("adminMemberSelect");
		if (select.equals("1")) { // 아이디순
			adminMemberListNumber = dao.adminAllMemberCount();
			if (adminMemberListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberListNumber > 0) {
				list = dao.getMemberId(startRow, pageSize);
				pageCount = adminMemberListNumber / pageSize
						+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberListNumber = dao.adminAllMemberCount();
			if (adminMemberListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberListNumber > 0) {
				list = dao.getMemberName(startRow, pageSize);
				pageCount = adminMemberListNumber / pageSize
						+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberListNumber = 0;
		} else {// 가입날짜순
			adminMemberListNumber = dao.adminAllMemberCount();
			if (adminMemberListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberListNumber > 0) {
				list = dao.getMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberListNumber / pageSize
						+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberListNumber", new Integer(adminMemberListNumber));
		return "adminMember/adminMemberListSelect.jsp";
	}

	private String adminMemberListSelectAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();

		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminMemberSelect", select);
		if (select.equals("1")) { // 아이디순
			adminMemberListNumber = dao.adminAllMemberCount();
			if (adminMemberListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberListNumber > 0) {
				list = dao.getMemberId(startRow, pageSize);
				pageCount = adminMemberListNumber / pageSize
						+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberListNumber = dao.adminAllMemberCount();
			if (adminMemberListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberListNumber > 0) {
				list = dao.getMemberName(startRow, pageSize);
				pageCount = adminMemberListNumber / pageSize
						+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberListNumber = 0;
		} else {// 가입날짜순
			adminMemberListNumber = dao.adminAllMemberCount();
			if (adminMemberListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberListNumber > 0) {
				list = dao.getMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberListNumber / pageSize
						+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberListNumber", new Integer(adminMemberListNumber));
		return "adminMember/adminMemberListSelect.jsp";
	}

	
	

	private String adminMemberListSearchPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();

		HttpSession session = request.getSession();
		String searchName = (String) session.getAttribute("adminMemberSearchName");
		String searchCateg = (String) session.getAttribute("adminMembersearchCateg");

		adminMemberListNumber = dao.adminAllMemberSearchCount(searchName);

		if (adminMemberListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberListNumber > 0) {
			list = dao.selectAllMemberList(searchName, startRow, pageSize);
			pageCount = adminMemberListNumber / pageSize
					+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberListNumber", new Integer(adminMemberListNumber));
		return "adminMember/adminMemberListSearch.jsp";

	}

	private String adminMemberListAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		String searchName = request.getParameter("searchName");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminMemberSearchName", searchName);
		session.setAttribute("adminMembersearchCateg", searchCateg);

		adminMemberListNumber = dao.adminAllMemberSearchCount(searchName);

		if (adminMemberListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberListNumber > 0) {
			list = dao.selectAllMemberList(searchName, startRow, pageSize);
			pageCount = adminMemberListNumber / pageSize
					+ (adminMemberListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberListNumber = 0;


		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberListNumber", new Integer(adminMemberListNumber));

		return "adminMember/adminMemberListMain.jsp";
	}
	
	
	private String adminMemberListPagingAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();
		adminMemberListNumber = dao.adminAllMemberCount();
		if (adminMemberListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberListNumber > 0) {
			list = dao.getAllMemberList(startRow, pageSize);

			pageCount = adminMemberListNumber / pageSize + (adminMemberListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberListNumber", new Integer(adminMemberListNumber));

		return "adminMember/adminMemberList.jsp";
	}

	// 회원관리튜티리스트
	private String adminMemberList(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminAMemberVO> list = new ArrayList<AdminAMemberVO>();
		adminMemberListNumber = dao.adminAllMemberCount();
		if (adminMemberListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberListNumber > 0) {
			list = dao.getAllMemberList(startRow, pageSize);

			pageCount = adminMemberListNumber / pageSize + (adminMemberListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberListNumber", new Integer(adminMemberListNumber));

		return "adminMember/adminMemberListMain.jsp";
	}



	// 전체회원
	private String adminAllTutorDetailAction(HttpServletRequest request) {
		dao = new MemberDAO();

		String id = request.getParameter("id");
		// System.out.println(id);
		MemberVO tutorvo = dao.getTutorDetail(id);
		request.setAttribute("tutorvo", tutorvo);

		ArrayList<StringJobSelectVO> sjob = dao.getMemberJob(id);
		request.setAttribute("sjob", sjob);

		// System.out.println(id+tutorvo);

		return "adminMember/tutorAllDetail.jsp";
	}

	private String adminAllTuteeDetailAction(HttpServletRequest request) {
		dao = new MemberDAO();

		// System.out.println("tuteeDetailAction시작/////////////");

		String id = request.getParameter("id");
		// System.out.println(id);
		MemberVO tuteevo = dao.getTuteeDetail(id);
		request.setAttribute("tuteevo", tuteevo);

		ArrayList<StringJobSelectVO> sjob = dao.getMemberJob(id);
		request.setAttribute("sjob", sjob);

		// System.out.println(id+tutorvo);

		return "adminMember/tuteeAllDetail.jsp";
	}

	private String adminAllCompanyDetailAction(HttpServletRequest request) {
		dao = new MemberDAO();

		String id = request.getParameter("id");
		// System.out.println(id);

		CompanyVO companyvo = dao.getComapnyInfoDetail(id);
		request.setAttribute("companyvo", companyvo);

		ArrayList<ImageVO> image = dao.getCompanyImage(id);
		request.setAttribute("image", image);

		return "adminMember/companyAllDetail.jsp";
	}

	// 디테일
	private String adminTutorDetailAction(HttpServletRequest request) {
		dao = new MemberDAO();

		String id = request.getParameter("id");
		// System.out.println(id);
		MemberVO tutorvo = dao.getTutorDetail(id);
		request.setAttribute("tutorvo", tutorvo);

		ArrayList<StringJobSelectVO> sjob = dao.getMemberJob(id);
		request.setAttribute("sjob", sjob);

		// System.out.println(id+tutorvo);

		return "adminMember/tutorDetail.jsp";
	}

	private String adminTuteeDetailAction(HttpServletRequest request) {
		dao = new MemberDAO();

		// System.out.println("tuteeDetailAction시작/////////////");

		String id = request.getParameter("id");
		// System.out.println(id);
		MemberVO tuteevo = dao.getTuteeDetail(id);
		request.setAttribute("tuteevo", tuteevo);

		ArrayList<StringJobSelectVO> sjob = dao.getMemberJob(id);
		request.setAttribute("sjob", sjob);

		// System.out.println(id+tutorvo);

		return "adminMember/tuteeDetail.jsp";
	}

	private String adminCompanyDetailAction(HttpServletRequest request) {
		dao = new MemberDAO();

		String id = request.getParameter("id");
		// System.out.println(id);

		CompanyVO companyvo = dao.getComapnyInfoDetail(id);
		request.setAttribute("companyvo", companyvo);

		ArrayList<ImageVO> image = dao.getCompanyImage(id);
		request.setAttribute("image", image);

		return "adminMember/companyDetail.jsp";
	}

	/** 기업시작 */
	private String adminMemberCompanySearchListPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberCompanyListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String companyId = request.getParameter("companyId");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();

		HttpSession session = request.getSession();
		String searchName = (String) session.getAttribute("adminMemberCompanySearchName");
		String searchCateg = (String) session.getAttribute("adminMemberCompanysearchCateg");

		adminMemberCompanyListNumber = dao.selectCompanyCount(searchName);

		if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberCompanyListNumber > 0) {
			list = dao.selectCompanyList(searchName, startRow, pageSize);
			pageCount = adminMemberCompanyListNumber / pageSize
					+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberCompanyListNumber = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberCompanyListNumber", new Integer(adminMemberCompanyListNumber));
		request.setAttribute("images", images);
		return "adminMember/adminMemberCompanyListSearch.jsp";

	}

	private String adminMemberCompanyListAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberCompanyListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		String companyId = request.getParameter("companyId");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		String searchName = request.getParameter("searchName");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminMemberCompanySearchName", searchName);
		session.setAttribute("adminMemberCompanysearchCateg", searchCateg);

		adminMemberCompanyListNumber = dao.selectCompanyCount(searchName);

		if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberCompanyListNumber > 0) {
			list = dao.selectCompanyList(searchName, startRow, pageSize);
			pageCount = adminMemberCompanyListNumber / pageSize
					+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberCompanyListNumber = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("images", images);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberCompanyListNumber", new Integer(adminMemberCompanyListNumber));

		return "adminMember/adminMemberCompanyListMain.jsp";
	}

	private String adminMemberCompanyListPagingAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberCompanyListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String companyId = request.getParameter("companyId");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		adminMemberCompanyListNumber = dao.companyCount();
		if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberCompanyListNumber > 0) {
			list = dao.getCompanys(startRow, pageSize);

			pageCount = adminMemberCompanyListNumber / pageSize
					+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberCompanyListNumber = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		// request.setAttribute("savePath", savePath);
		request.setAttribute("adminMemberCompanyListNumber", new Integer(adminMemberCompanyListNumber));

		return "adminMember/adminMemberCompanyList.jsp";
	}

	private String adminMemberCompanyListModal(HttpServletRequest request) {
		String companyId = request.getParameter("companyId");
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);
		// System.out.println("companyId"+ companyId);
		// System.out.println("images" +images );
		request.setAttribute("images", images);
		return "adminMember/companyImg.jsp";
	}

	private String adminMemberCompanyList(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberCompanyListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String companyId = request.getParameter("companyId");

		// HttpSession session = request.getSession();
		// String companyId = (String)session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		adminMemberCompanyListNumber = dao.companyCount();
		if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberCompanyListNumber > 0) {
			list = dao.getCompanys(startRow, pageSize);

			pageCount = adminMemberCompanyListNumber / pageSize
					+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberCompanyListNumber = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		// request.setAttribute("savePath", savePath);
		request.setAttribute("adminMemberCompanyListNumber", new Integer(adminMemberCompanyListNumber));

		return "adminMember/adminMemberCompanyListMain.jsp";
	}

	// 이름순 아이디순
	private String adminMemberCompanyListSelectPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberCompanyListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		String companyId = request.getParameter("companyId");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();

//		String select = request.getParameter("select");
		HttpSession session = request.getSession();
		String select = (String)session.getAttribute("adminCompanySelect");
		if (select.equals("1")) { // 아이디순
			adminMemberCompanyListNumber = dao.companyCount();
			if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberCompanyListNumber > 0) {
				list = dao.getCompanyMemberId(startRow, pageSize);
				pageCount = adminMemberCompanyListNumber / pageSize
						+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberCompanyListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberCompanyListNumber = dao.companyCount();
			if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberCompanyListNumber > 0) {
				list = dao.getCompanyMemberName(startRow, pageSize);
				pageCount = adminMemberCompanyListNumber / pageSize
						+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberCompanyListNumber = 0;
		} else {// 가입날짜순
			adminMemberCompanyListNumber = dao.companyCount();
			if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberCompanyListNumber > 0) {
				list = dao.getCompanyMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberCompanyListNumber / pageSize
						+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberCompanyListNumber = 0;
		}
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		request.setAttribute("adminMemberCompanyListNumber", new Integer(adminMemberCompanyListNumber));
		return "adminMember/adminMemberCompanyListSelect.jsp";
	}

	private String adminMemberCompanyListSelectAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberCompanyListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();

		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminCompanySelect", select);
		if (select.equals("1")) { // 아이디순
			adminMemberCompanyListNumber = dao.companyCount();
			if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberCompanyListNumber > 0) {
				list = dao.getCompanyMemberId(startRow, pageSize);
				pageCount = adminMemberCompanyListNumber / pageSize
						+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberCompanyListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberCompanyListNumber = dao.companyCount();
			if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberCompanyListNumber > 0) {
				list = dao.getCompanyMemberName(startRow, pageSize);
				pageCount = adminMemberCompanyListNumber / pageSize
						+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberCompanyListNumber = 0;
		} else {// 가입날짜순
			adminMemberCompanyListNumber = dao.companyCount();
			if (adminMemberCompanyListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberCompanyListNumber > 0) {
				list = dao.getCompanyMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberCompanyListNumber / pageSize
						+ (adminMemberCompanyListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberCompanyListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberCompanyListNumber", new Integer(adminMemberCompanyListNumber));
		return "adminMember/adminMemberCompanyListSelect.jsp";
	}

	// 관리자 튜터
	private String adminMemberTutorListNumber(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		int adminMemberTutorListNumber = dao.tutorCount();
		request.setAttribute("adminMemberTutorListNumber", adminMemberTutorListNumber);
		return "adminMember/adminMemberTutorListMain.jsp";
	}

	private String adminMemberTutorListPagingAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberTutorListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		adminMemberTutorListNumber = dao.tutorCount();
		if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberTutorListNumber > 0) {
			list = dao.getAdminTutor(startRow, pageSize);

			pageCount = adminMemberTutorListNumber / pageSize + (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberTutorListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTutorListNumber", new Integer(adminMemberTutorListNumber));

		return "adminMember/adminMemberTutorList.jsp";
	}

	private String adminMemberTutorList(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberTutorListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		adminMemberTutorListNumber = dao.tutorCount();
		if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberTutorListNumber > 0) {
			list = dao.getAdminTutor(startRow, pageSize);

			pageCount = adminMemberTutorListNumber / pageSize + (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberTutorListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTutorListNumber", new Integer(adminMemberTutorListNumber));

		return "adminMember/adminMemberTutorListMain.jsp";
	}

	private String adminMemberTutorListAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberTutorListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		String searchName = request.getParameter("searchName");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminMemberTutorSearchName", searchName);
		session.setAttribute("adminMemberTutorsearchCateg", searchCateg);
		if (searchCateg.equals("0")) {
			// System.out.println("전체로 검색");

			adminMemberTutorListNumber = dao.selectAllAdminMemberTutorCount(searchName);

			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.selectAllAdminMemberTutorList(searchName, startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else if (searchCateg.equals("1")) {
			// System.out.println("키워드로 검색");

			adminMemberTutorListNumber = dao.selectAdminMemberTutorIdCount(searchName);
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.selectAdminMemberTutorIdList(searchName, startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else {
			// System.out.println("튜티명으로 검색");

			adminMemberTutorListNumber = dao.selectAdminMemberTutorNameListCount(searchName);
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.selectAdminMemberTutorNameList(searchName, startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTutorListNumber", new Integer(adminMemberTutorListNumber));

		return "adminMember/adminMemberTutorListMain.jsp";
	}

	private String adminMemberTutorListSearchPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberTutorListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();

		HttpSession session = request.getSession();
		String searchName = (String) session.getAttribute("adminMemberTutorSearchName");
		String searchCateg = (String) session.getAttribute("adminMemberTutorsearchCateg");

		if (searchCateg.equals("0")) {
			// System.out.println("전체로 검색");

			adminMemberTutorListNumber = dao.selectAllAdminMemberTutorCount(searchName);

			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.selectAllAdminMemberTutorList(searchName, startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else if (searchCateg.equals("1")) {
			// System.out.println("키워드로 검색");

			adminMemberTutorListNumber = dao.selectAdminMemberTutorIdCount(searchName);
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.selectAdminMemberTutorIdList(searchName, startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else {
			// System.out.println("튜티명으로 검색");

			adminMemberTutorListNumber = dao.selectAdminMemberTutorNameListCount(searchName);
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.selectAdminMemberTutorNameList(searchName, startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTutorListNumber", new Integer(adminMemberTutorListNumber));

		return "adminMember/adminMemberTutorListSearch.jsp";
	}

	// 이름순 아이디순
	private String adminMemberTutorListSelectPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberTutorListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();

		HttpSession session = request.getSession();
		String select = (String)session.getAttribute("adminTutorSelect");
		if (select.equals("1")) { // 아이디순
			adminMemberTutorListNumber = dao.tutorCount();
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.getTutorMemberId(startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberTutorListNumber = dao.tutorCount();
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.getTutorMemberName(startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else {// 가입날짜순
			adminMemberTutorListNumber = dao.tutorCount();
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.getTutorMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTutorListNumber", new Integer(adminMemberTutorListNumber));
		return "adminMember/adminMemberTutorListSelect.jsp";
	}

	private String adminMemberTutorListSelectAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberTutorListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();

		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminTutorSelect", select);
		if (select.equals("1")) { // 아이디순
			adminMemberTutorListNumber = dao.tutorCount();
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.getTutorMemberId(startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberTutorListNumber = dao.tutorCount();
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.getTutorMemberName(startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		} else {// 가입날짜순
			adminMemberTutorListNumber = dao.tutorCount();
			if (adminMemberTutorListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTutorListNumber > 0) {
				list = dao.getTutorMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberTutorListNumber / pageSize
						+ (adminMemberTutorListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTutorListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTutorListNumber", new Integer(adminMemberTutorListNumber));
		return "adminMember/adminMemberTutorListSelect.jsp";
	}

	// 관리자 튜티
	private String adminMemberTuteeListNumber(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		int adminMemberTuteeListNumber = dao.tuteeCount();
		request.setAttribute("adminMemberTuteeListNumber", adminMemberTuteeListNumber);
		return "adminMember/adminMemberTuteeListMain.jsp";
	}

	private String adminMemberTuteeListPagingAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberTuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		adminMemberTuteeListNumber = dao.tuteeCount();
		if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberTuteeListNumber > 0) {
			list = dao.getAdminTutee(startRow, pageSize);

			pageCount = adminMemberTuteeListNumber / pageSize + (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberTuteeListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTuteeListNumber", new Integer(adminMemberTuteeListNumber));

		return "adminMember/adminMemberTuteeList.jsp";
	}

	// 회원관리튜티리스트
	private String adminMemberTuteeList(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberTuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		adminMemberTuteeListNumber = dao.tuteeCount();
		if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (adminMemberTuteeListNumber > 0) {
			list = dao.getAdminTutee(startRow, pageSize);

			pageCount = adminMemberTuteeListNumber / pageSize + (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			adminMemberTuteeListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTuteeListNumber", new Integer(adminMemberTuteeListNumber));

		return "adminMember/adminMemberTuteeListMain.jsp";
	}

	private String adminMemberTuteeListAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		int adminMemberTuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		String searchName = request.getParameter("searchName");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminMemberTuteeSearchName", searchName);
		session.setAttribute("adminMemberTuteesearchCateg", searchCateg);
		if (searchCateg.equals("0")) {
			// System.out.println("전체로 검색");

			adminMemberTuteeListNumber = dao.selectAllAdminMemberTuteeCount(searchName);

			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.selectAllAdminMemberTuteeList(searchName, startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else if (searchCateg.equals("1")) {
			// System.out.println("키워드로 검색");

			adminMemberTuteeListNumber = dao.selectAdminMemberTuteeIdCount(searchName);
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.selectAdminMemberTuteeIdList(searchName, startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else {
			// System.out.println("튜티명으로 검색");

			adminMemberTuteeListNumber = dao.selectAdminMemberTuteeNameListCount(searchName);
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.selectAdminMemberTuteeNameList(searchName, startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTuteeListNumber", new Integer(adminMemberTuteeListNumber));

		return "adminMember/adminMemberTuteeListMain.jsp";
	}

	private String adminMemberTuteeListSearchPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberTuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();

		HttpSession session = request.getSession();
		String searchName = (String) session.getAttribute("adminMemberTuteeSearchName");
		String searchCateg = (String) session.getAttribute("adminMemberTuteesearchCateg");

		if (searchCateg.equals("0")) {
			// System.out.println("전체로 검색");

			adminMemberTuteeListNumber = dao.selectAllAdminMemberTuteeCount(searchName);

			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.selectAllAdminMemberTuteeList(searchName, startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else if (searchCateg.equals("1")) {
			// System.out.println("키워드로 검색");

			adminMemberTuteeListNumber = dao.selectAdminMemberTuteeIdCount(searchName);
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.selectAdminMemberTuteeIdList(searchName, startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else {
			// System.out.println("튜티명으로 검색");

			adminMemberTuteeListNumber = dao.selectAdminMemberTuteeNameListCount(searchName);
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.selectAdminMemberTuteeNameList(searchName, startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTuteeListNumber", new Integer(adminMemberTuteeListNumber));

		return "adminMember/adminMemberTuteeListSearch.jsp";
	}

	// 이름순 아이디순
	private String adminMemberTuteeListSelectPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberTuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();

		HttpSession session = request.getSession();
		String select = (String)session.getAttribute("adminTuteeSelect");
		if (select.equals("1")) { // 아이디순
			adminMemberTuteeListNumber = dao.tuteeCount();
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.getTuteeMemberId(startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberTuteeListNumber = dao.tuteeCount();
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.getTuteeMemberName(startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else {// 가입날짜순
			adminMemberTuteeListNumber = dao.tuteeCount();
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.getTuteeMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTuteeListNumber", new Integer(adminMemberTuteeListNumber));
		return "adminMember/adminMemberTuteeListSelect.jsp";
	}

	private String adminMemberTuteeListSelectAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int adminMemberTuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<AdminTMemberVO> list = new ArrayList<AdminTMemberVO>();

		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		session.setAttribute("adminTuteeSelect", select);
		if (select.equals("1")) { // 아이디순
			adminMemberTuteeListNumber = dao.tuteeCount();
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.getTuteeMemberId(startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else if (select.equals("2")) {// 이름순
			adminMemberTuteeListNumber = dao.tuteeCount();
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.getTuteeMemberName(startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		} else {// 가입날짜순
			adminMemberTuteeListNumber = dao.tuteeCount();
			if (adminMemberTuteeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (adminMemberTuteeListNumber > 0) {
				list = dao.getTuteeMemberRegisterDate(startRow, pageSize);
				pageCount = adminMemberTuteeListNumber / pageSize
						+ (adminMemberTuteeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				adminMemberTuteeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("adminMemberTuteeListNumber", new Integer(adminMemberTuteeListNumber));
		return "adminMember/adminMemberTuteeListSelect.jsp";
	}

	// --------------------------------------------------------------------------------------------------------------------------
	


	/** 튜터링 등록 후 수강신청내역으로 나오게 하기 */
	private String tutoringApplyRegisterAction(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		dao2 = new TutoringDAO();
		// System.out.println("//튜터링 수강 신청 후 마이페이지에 수강신청 내역 등록 시작");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		// System.out.println("튜터링을 신청하는 tuteeId " + tuteeId);

		// int tutoringnumber = (int)session.getAttribute("tutoringnum");
		// System.out.println("신청되는 tutoringnumber " + tutoringnumber);

		String tutoringId = request.getParameter("tutoringId");
		int tutoringNumber = Integer.parseInt(tutoringId);
		String time = request.getParameter("time");
		String time1 = time.substring(0, 5);
		String time2 = time.substring(8, 13);
		System.out.println(time1 + "/" + time2);
		int tutoringtimeId = dao2.getTutoringsTimeId(tutoringNumber, time1, time2);
		
		String price = request.getParameter("price");
		int prices = Integer.parseInt(price);
		String account = request.getParameter("account");
		// System.out.println("결제 방법 payInfo " + payInfo);

		// dao2 = new TutoringDAO();
		// TutoringVO3 list = dao2.tutoringDetail(tutoringNumber);
		// System.out.println("list"+ list);
		// int price = Integer.parseInt(list.getPrice());
		// System.out.println(list.getPrice());
		//
		// String firstTime = request.getParameter("timeSelect");
		// System.out.println("firstTime " + firstTime);

		// System.out.println(tutoringnumber+"/"+tuteeId+"/"+payInfo+"/"+price);
		boolean result = tutee2.registerTutoring(tutoringNumber, tuteeId, account, prices, tutoringtimeId);
		request.setAttribute("result", result);
		// System.out.println(result);
		if (result)
			return "tutoring/tutoringApplyResult.jsp";
		return "tutoring/tutoringDetail.jsp";
	}

	private String tutoringApply(HttpServletRequest request) {
		dao = new MemberDAO();
		dao2 = new TutoringDAO();
		buy = new BuyDAO();

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("id");

		String tutoringId = request.getParameter("tutoringId");
		int tutoringNumber = Integer.parseInt(tutoringId);
		String time = request.getParameter("time");
		System.out.println(time);
		

		TutoringVO3 tutoringList = dao2.tutoringDetail(tutoringNumber);
		int price = Integer.parseInt(tutoringList.getPrice());

		List<String> accountLists = null;
		accountLists = buy.getAccount();
		request.setAttribute("accountLists", accountLists);

		MemberVO membervo = dao.getTutee(userId);
		request.setAttribute("membervo", membervo);

		request.setAttribute("tutoringNumber", tutoringNumber);
		request.setAttribute("tutoringList", tutoringList);
		request.setAttribute("price", price);
		request.setAttribute("time", time);
		request.setAttribute("userId", userId);

		return "tutoring/tutoringBuy.jsp";
	}

	/** 면접신청할 때 제안업무 */
	private String companyInterviewCateg(HttpServletRequest request) {
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "companyMypage/companyInterviewApply.jsp";
	}

	private String interviewPayMoneys(HttpServletRequest request) {
		buy = new BuyDAO();
		String payInfo = request.getParameter("payInfo"); // 무통장입금
		String account = request.getParameter("account"); // 계좌번호
		String payMoney = request.getParameter("payMoney"); // 3000원

		HttpSession session = request.getSession();
		String interviewApplyId = (String) session.getAttribute("interviewApplyId");
		int interviewIds = Integer.parseInt(interviewApplyId);

		boolean result = buy.paymentInsert(interviewIds, payInfo, payMoney);
		request.setAttribute("result", result);
		return "companyMypage/companyInterviewApplyResult.jsp";
	}

	private String interviewPayCancel(HttpServletRequest request) {
		// System.out.println("취소");
		dao6 = new InterviewDAO();
		dao = new MemberDAO();
		buy = new BuyDAO();

		HttpSession session = request.getSession();
		int portfolioId = (int) session.getAttribute("portfolioNumber");
		String interviewApplyId = (String) session.getAttribute("interviewApplyId");
		int interviewIds = Integer.parseInt(interviewApplyId);
		boolean result = buy.deleteInterviewInfo(interviewIds);
		// System.out.println(portfolioId+"/"+interviewApplyId+"/"+result);

		return "starters?cmd=portfolioListDetailAction&num=" + portfolioId;
	}

	private String companyInterviewPay(HttpServletRequest request) {
		// System.out.println("결제시작");
		dao6 = new InterviewDAO();
		dao = new MemberDAO();
		buy = new BuyDAO();
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");

		// int portfolioId =
		// Integer.parseInt(request.getParameter("portfolioId"));
		int portfolioId = (int) session.getAttribute("portfolioNumber");
		InterviewPortfolioVO portfoliovo = dao.interviewPortfolioDetail(portfolioId);
		request.setAttribute("portfoliovo", portfoliovo);

		CompanyVO companyvo = dao.getComapnyInfoDetail(userID);
		request.setAttribute("companyvo", companyvo);

		List<String> accountLists = null;
		accountLists = buy.getAccount();
		request.setAttribute("accountLists", accountLists);

		String interviewApplyId = request.getParameter("interviewApplyId");
		request.setAttribute("interviewApplyId", interviewApplyId);
		session.setAttribute("interviewApplyId", interviewApplyId);
		// 11/06 추가
		CompanyInervVO companypricevo = dao6.interviewPrice();
		request.setAttribute("companypricevo", companypricevo);
//		System.out.println("00000"+companypricevo);
				
		return "companyMypage/companyInterviewPay.jsp";
	}

	/** 튜티마이페이지- 면접내역 */

	// ====================면접신청서 작성

	private String memberCategFinal(HttpServletRequest request) {
		dao = new MemberDAO();
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");

		int allMembers = 0;
		allMembers = dao.memberCateg4(userID);
		request.setAttribute("allMembers", new Integer(allMembers));
		return "sitemap.jsp";
	}

	/** 면접신청하기 */
	private String interviewApplyAction(HttpServletRequest request) throws ServletException, IOException {
		dao6 = new InterviewDAO();
		dao = new MemberDAO();

		// System.out.println("면접시작///////");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println("companyId////////interviewApplyAction////" + id);
		// (int) interview_id, company_id, (int) portfolio_id,
		// interview_start_date,
		// interview_end_date, interview_year_money, (int) interview_result,
		// upload_date

		/*
		 * int interviewId =
		 * Integer.parseInt(request.getParameter("interviewId"));
		 */
		/* String companyId = request.getParameter("company_id"); */

		int portfolioId = Integer.parseInt(request.getParameter("portfolioId"));

		HttpSession session1 = request.getSession(true);
		session1.setAttribute("portfolioNumber", portfolioId);

		InterviewPortfolioVO portfoliovo = dao.interviewPortfolioDetail(portfolioId);
		request.setAttribute("portfoliovo", portfoliovo);
		// System.out.println("////////"+portfoliovo);

		String interviewStartDate = request.getParameter("from");
		String interviewEndDate = request.getParameter("to");
		String interviewWork = request.getParameter("work");
		String interviewYearMoney = request.getParameter("money");
		/*
		 * int interviewResult =
		 * Integer.parseInt(request.getParameter("interviewResult"));
		 */
		// System.out.println(interviewStartDate+interviewEndDate+interviewWork+interviewYearMoney);

		// 첫번째
		/* 시작시간 형변환 */
		String s1Time1 = request.getParameter("s1Time1");
		// System.out.println("s1Time1////////" + s1Time1);
		String[] splits = s1Time1.split(":");
		int hour = Integer.parseInt(splits[0]);
		// System.out.println("hour"+hour);
		int min = Integer.parseInt(splits[1].substring(0, 2));
		// int min=Integer.parseInt(splits[1]);
		// System.out.println("min"+min);
		String ampm = splits[1].substring(splits[1].length() - 2, splits[1].length());
		// String ampm = splits[1].substring(5, 6);
		// System.out.println(ampm);
		if (ampm.equals("PM")) {
			hour = hour + 12;
		}
		if (hour == 12 && ampm.equals("AM")) {
			hour = 0;
		}
		if (hour == 24 && ampm.equals("PM")) {
			hour = 12;
		}
		String startTime = String.format("%02d:%02d", hour, min);

		/* 끝나는 시간 형변환 */
		String s1Time2 = request.getParameter("s1Time2");
		String[] splits2 = s1Time2.split(":");
		int hour2 = Integer.parseInt(splits2[0]);
		int min2 = Integer.parseInt(splits2[1].substring(0, 2));
		String ampm2 = splits2[1].substring(splits2[1].length() - 2, splits2[1].length());
		if (ampm2.equals("PM")) {
			hour2 = hour2 + 12;
		}
		if (hour2 == 12 && ampm2.equals("AM")) {
			hour2 = 0;
		}
		if (hour2 == 24 && ampm2.equals("PM")) {
			hour2 = 12;
		}
		String endTime = String.format("%02d:%02d", hour2, min2);

		boolean result1 = dao6.registerInterview(id, portfolioId, interviewStartDate, interviewEndDate,
				interviewYearMoney, null);
		// System.out.println("result1" + result1);

		InterviewApplyVO result2 = dao6.getInterviewRegisterInfo(id);
		// TutoringVO2 result2 = dao2.getTutoringRegisterInfo(id);
		// System.out.println("result2.getTutoringId()"+result2.getTutoringId());

		int interviewApplyId = result2.getInterviewId();

		boolean result3 = dao6.registerInterviewTime(interviewApplyId, startTime, endTime);
		// System.out.println("result3" + result3);

		// 두번째 시간
		/* 시작시간 형변환 */
		String s1Time3 = request.getParameter("s1Time3");
		String s1Time4 = request.getParameter("s1Time4");
		if (s1Time3 != null && s1Time4 != null) {
			String[] splits3 = s1Time3.split(":");
			int hour3 = Integer.parseInt(splits3[0]);
			int min3 = Integer.parseInt(splits3[1].substring(0, 2));
			String ampm3 = splits3[1].substring(splits3[1].length() - 2, splits3[1].length());
			if (ampm3.equals("PM")) {
				hour3 = hour3 + 12;
			}
			if (hour3 == 12 && ampm3.equals("AM")) {
				hour3 = 0;
			}
			if (hour3 == 24 && ampm3.equals("PM")) {
				hour3 = 12;
			}
			String startTime3 = String.format("%02d:%02d", hour3, min3);
			/* 끝나는 시간 형변환 */

			String[] splits4 = s1Time4.split(":");
			int hour4 = Integer.parseInt(splits4[0]);
			int min4 = Integer.parseInt(splits4[1].substring(0, 2));
			String ampm4 = splits4[1].substring(splits4[1].length() - 2, splits4[1].length());
			if (ampm4.equals("PM")) {
				hour4 = hour4 + 12;
			}
			if (hour4 == 12 && ampm4.equals("AM")) {
				hour4 = 0;
			}
			if (hour4 == 24 && ampm4.equals("PM")) {
				hour4 = 12;
			}
			String endTime4 = String.format("%02d:%02d", hour4, min4);

			dao6.registerInterviewTime(interviewApplyId, startTime3, endTime4);
		}

		// 세번째
		/* 시작시간 형변환 */
		String s1Time5 = request.getParameter("s1Time5");
		String s1Time6 = request.getParameter("s1Time6");
		if (s1Time5 != null && s1Time6 != null) {
			String[] splits5 = s1Time5.split(":");
			int hour5 = Integer.parseInt(splits5[0]);
			int min5 = Integer.parseInt(splits5[1].substring(0, 2));
			String ampm5 = splits5[1].substring(splits5[1].length() - 2, splits5[1].length());
			if (ampm5.equals("PM")) {
				hour5 = hour5 + 12;
			}
			if (hour5 == 12 && ampm5.equals("AM")) {
				hour5 = 0;
			}
			if (hour5 == 24 && ampm5.equals("PM")) {
				hour5 = 12;
			}
			String startTime5 = String.format("%02d:%02d", hour5, min5);

			/* 끝나는 시간 형변환 */

			String[] splits6 = s1Time6.split(":");
			int hour6 = Integer.parseInt(splits6[0]);
			int min6 = Integer.parseInt(splits6[1].substring(0, 2));
			String ampm6 = splits6[1].substring(splits6[1].length() - 2, splits6[1].length());
			if (ampm6.equals("PM")) {
				hour6 = hour6 + 12;
			}
			if (hour6 == 12 && ampm6.equals("AM")) {
				hour6 = 0;
			}
			if (hour6 == 24 && ampm6.equals("PM")) {
				hour6 = 12;
			}
			String endTime6 = String.format("%02d:%02d", hour6, min6);

			dao6.registerInterviewTime(interviewApplyId, startTime5, endTime6);
		}

		// System.out.println("요일//// test");
		String daySelects = request.getParameter("daySelect");
		String[] daySelectArray = daySelects.split("");
		// System.out.println(daySelectArray.length);
		for (int i = 0; i < daySelectArray.length; i++) {
			dao6.registerInterviewDay(interviewApplyId, daySelectArray[i]);
			// System.out.println("확인");
		}

		// 10/29 추가
		// System.out.println("////////////직종선택부분////////////////////");
		String[] mainSelect = request.getParameterValues("mainSelect");

		// System.out.println("길이"+ mainSelect.length);
		// if(result1 == true && result3 == true) {
		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			// boolean result4 = dao6.JobSelects(interviewId, realmainSelect);
			dao6.JobSelects(interviewApplyId, realmainSelect);
			// System.out.println("///////////"+result4);
			// System.out.println("직종넣기 성공");
		}
		// }

		if (result1 && result3)
			// return "companyMypage/companyInterviewApplySuccess.jsp";
			return "starters?cmd=companyInterviewPay&interviewApplyId=" + interviewApplyId;
		return "companyMypage/companyInterviewApply.jsp";
	}

	/** 면접신청할 때 포트폴리오 id 끌어오기 */
	private String interviewPortfolioId(HttpServletRequest request) {
		dao = new MemberDAO();
		String portfolioId = request.getParameter("number");
		HttpSession session = request.getSession(true);
		session.setAttribute("portfolioId", portfolioId);
		// System.out.println("portfolioId////" + portfolioId);

		return "companyMypage/companyInterviewApply.jsp";
	}

	/** 포트폴리오에서 면접신청하기 */
	private String companyInterviewApply(HttpServletRequest request) {
		dao = new MemberDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println(id);

		CompanyVO companyvo = dao.getComapnyInfoDetail(id);
		request.setAttribute("companyvo", companyvo);

		int portfolioId = Integer.parseInt(request.getParameter("portfolioId"));
		InterviewPortfolioVO portfoliovo = dao.interviewPortfolioDetail(portfolioId);
		request.setAttribute("portfoliovo", portfoliovo);
		// System.out.println("////////"+portfoliovo);

		return "companyMypage/companyInterviewApply.jsp";
	}

	/** 면접신청내역 */
	/** 날짜 검색 리스트 페이징 */
	private String companyinterviewDateSelectPaging(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 날짜 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		String date = (String) session.getAttribute("date");
		// System.out.println("date" + date);
		String from = (String) session.getAttribute("from");
		// System.out.println("from" + from);
		String to = (String) session.getAttribute("to");
		// System.out.println("to" + to);

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewDateList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectDateCount(companyId, from, to);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewDateList = dao6.selectDateLists(companyId, from, to, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewDateList.isEmpty())
			interviewAllCount = 0;

		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewDateList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewDateList", interviewDateList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewSelectDateSearch.jsp";
	}

	/** 날짜 검색 리스트 */
	private String companyinterviewDateSelectAction(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 날짜 검색 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		String date = request.getParameter("date");
		// System.out.println("date" + date);
		String from = date.substring(0, 10);
		// System.out.println("from" + from);
		String to = date.substring(10, 20);
		// System.out.println("to" + to);

		// HttpSession session = request.getSession(true);
		session.setAttribute("date", date);
		session.setAttribute("from", from);
		session.setAttribute("to", to);

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewDateList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectDateCount(companyId, from, to);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewDateList = dao6.selectDateLists(companyId, from, to, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewDateList.isEmpty())
			interviewAllCount = 0;

		// 미응답, 수락, 거절, 완료 표시 시작
		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);
		// int interviewResult = dao6.interviewResult(companyId);
		// System.out.println("interviewResult:" + interviewResult);
		// if(interviewResult == 0){ // 미응답
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 1){ // 수락
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 2){ // 거절
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 3){ // 완료
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// 미응답, 수락, 거절, 완료 표시 끝

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewDateList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewDateList", interviewDateList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewSelectDateSearch.jsp";
	}

	// 여기서부터 수정
	/** 6개월 검색 리스트 페이징 */
	private String interviewListSixMonthSearchPaging(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 6개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewSixMonthList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectSixMonthListCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewSixMonthList = dao6.selectSixMonthLists(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewSixMonthList.isEmpty())
			interviewAllCount = 0;

		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewSixMonthList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewSixMonthList", interviewSixMonthList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewSixMonthSearch.jsp";
	}

	/** 6개월 검색 리스트 */
	private String interviewListSixMonth(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 6개월 검색 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewSixMonthList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectSixMonthListCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewSixMonthList = dao6.selectSixMonthLists(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewSixMonthList.isEmpty())
			interviewAllCount = 0;

		// 미응답, 수락, 거절, 완료 표시 시작
		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);
		// int interviewResult = dao6.interviewResult(companyId);
		// System.out.println("interviewResult:" + interviewResult);
		// if(interviewResult == 0){ // 미응답
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 1){ // 수락
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 2){ // 거절
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 3){ // 완료
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// 미응답, 수락, 거절, 완료 표시 끝

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewSixMonthList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewSixMonthList", interviewSixMonthList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewSixMonthSearch.jsp";
	}

	/** 3개월 검색 리스트 페이징 */
	private String interviewListThreeMonthSearchPaging(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 3개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewThreeMonthList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectThreeMonthListCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewThreeMonthList = dao6.selectThreeMonthLists(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewThreeMonthList.isEmpty())
			interviewAllCount = 0;

		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewThreeMonthList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewThreeMonthList", interviewThreeMonthList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewThreeMonthSearch.jsp";
	}

	/** 3개월 검색 리스트 */
	private String interviewListThreeMonth(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 3개월 검색 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewThreeMonthList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectThreeMonthListCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewThreeMonthList = dao6.selectThreeMonthLists(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewThreeMonthList.isEmpty())
			interviewAllCount = 0;

		// 미응답, 수락, 거절, 완료 표시 시작
		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);
		// int interviewResult = dao6.interviewResult(companyId);
		// System.out.println("interviewResult:" + interviewResult);
		// if(interviewResult == 0){ // 미응답
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 1){ // 수락
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 2){ // 거절
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 3){ // 완료
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// 미응답, 수락, 거절, 완료 표시 끝

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewThreeMonthList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewThreeMonthList", interviewThreeMonthList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewThreeMonthSearch.jsp";
	}

	/** 1개월 검색 리스트 페이징 */
	private String interviewListAmonthSearchPaging(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 1개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewAmonthList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectAmonthListCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewAmonthList = dao6.selectAmonthLists(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewAmonthList.isEmpty())
			interviewAllCount = 0;

		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewAmonthList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewAmonthList", interviewAmonthList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewAmonthSearch.jsp";
	}

	/** 1개월 검색 리스트 */
	private String interviewListAmonth(HttpServletRequest request) throws ServletException, IOException {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 1개월 검색 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewAmonthList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.selectAmonthListCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewAmonthList = dao6.selectAmonthLists(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewAmonthList.isEmpty())
			interviewAllCount = 0;

		// 미응답, 수락, 거절, 완료 표시 시작
		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);
		// int interviewResult = dao6.interviewResult(companyId);
		// System.out.println("interviewResult:" + interviewResult);
		// if(interviewResult == 0){ // 미응답
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 1){ // 수락
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 2){ // 거절
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 3){ // 완료
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// 미응답, 수락, 거절, 완료 표시 끝

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewAmonthList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewAmonthList", interviewAmonthList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewAmonthSearch.jsp";
	}

	/** 전체보기 검색 리스트 */
	private String interviewAll(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 전체보기 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.interviewCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewList = dao6.getInterviews(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewList.isEmpty())
			interviewAllCount = 0;

		// // 미응답, 수락, 거절, 완료 표시 시작
		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);
		// int interviewResult = dao6.interviewResult(companyId);
		// System.out.println("interviewResult:" + interviewResult);
		// if(interviewResult == 0){ // 미응답
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 1){ // 수락
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 2){ // 거절
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 3){ // 완료
		// HttpSession session2 = request.getSession(true);
		// session2.setAttribute("interviewResult", interviewResult);
		// }
		// 미응답, 수락, 거절, 완료 표시 끝

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewList);
		// System.out.println("/////"+interviewAllCount);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewList", interviewList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterview.jsp";
		/* return "tuteeMypage/tutoringInfoList.jsp"; */
	}

	/** 기업 면접 신청 내역 페이징 */
	private String interviewListPagingAction(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("2.면접신청내역 전체보기//// 페이징 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.interviewCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewList = dao6.getInterviews(companyId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewList.isEmpty())
			interviewAllCount = 0;

		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewList", interviewList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterview.jsp";
	}

	/** 면접 신청 내역 글 수 */
	private String interviewListNumber(HttpServletRequest request) throws ServletException, IOException {
		dao6 = new InterviewDAO();
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");
		int applyAllCount = dao6.interviewCount(companyId);
		request.setAttribute("applyAllCount", applyAllCount);
		return "companyMypage/companyInterviewMain.jsp";
	}

	/** 기업 면접 신청 내역 */
	private String companyInterview(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		// System.out.println("면접신청내역 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id"); // 여기로 기업id 값을
																// 가져오기
		// System.out.println("companyId:" + companyId);

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<InterviewVO2> interviewList = new ArrayList<InterviewVO2>();
		interviewAllCount = dao6.interviewCount(companyId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewList = dao6.getInterviews(companyId, startRow, pageSize); // 나중에
																				// 페이징
																				// 할때
																				// 1과9는
																				// startRow,
																				// pageSize
																				// 로
																				// 변경
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewList.isEmpty())
			interviewAllCount = 0;

		// 미응답, 수락, 거절, 완료 표시 시작
		int interviewResult = dao6.interviewResult(companyId);
		request.setAttribute("interviewResult", interviewResult);
		// System.out.println("interviewResult:" + interviewResult);
		// if(interviewResult == 0){ // 미응답
		// request.setAttribute("interviewResult", interviewResult);
		//// HttpSession session2 = request.getSession(true);
		//// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 1){ // 수락
		//// HttpSession session2 = request.getSession(true);
		//// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 2){ // 거절
		//// HttpSession session2 = request.getSession(true);
		//// session2.setAttribute("interviewResult", interviewResult);
		// }
		// if(interviewResult == 3){ // 완료
		//// HttpSession session2 = request.getSession(true);
		//// session2.setAttribute("interviewResult", interviewResult);
		// }
		// 미응답, 수락, 거절, 완료 표시 끝

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewList", interviewList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "companyMypage/companyInterviewMain.jsp";
	}

	/** 기업 찜하기 이미지 */
	private String companywishImage(HttpServletRequest request) {
		company1 = new CompanyWishDAO();
		int portfolioId = Integer.parseInt(request.getParameter("portfolioNum"));
		ArrayList<IntImageVO> images = company1.getCompanyWishImage(portfolioId);
		// System.out.println("images" + images);
		request.setAttribute("images", images);

		return "companyMypage/companyLikeImage.jsp";
	}

	/** 기업 찜하기 */
	private String companywishNum(HttpServletRequest request) {
		company1 = new CompanyWishDAO();
		String portfolioId = request.getParameter("portfolioNum");
		HttpSession session = request.getSession(true);
		session.setAttribute("portfolioId", portfolioId);
		// System.out.println("portfolioId이다" + portfolioId);

		return "companyMypage/companyLike.jsp";
	}

	/** 기업 찜하기 취소 */
	private String companyLikeDeleteAction(HttpServletRequest request) {
		company1 = new CompanyWishDAO();
		// System.out.println("3.찜하기내역 취소!!");
		HttpSession session = request.getSession();

		String portfolioId = (String) session.getAttribute("portfolioId");
		int portfolioIds = Integer.parseInt(portfolioId);
		// System.out.println("삭제되는 portfolioIds " + portfolioIds);

		boolean result = company1.deleteCompanyLike(portfolioIds);
		// System.out.println("deleteApplyList 결과"+result);
		if (result)
			return "starters?cmd=companyLikeList";
		return "starters?cmd=companyLikeList";
	}

	/** 기업 찜하기 등록 */
	private String companyLikeAction(HttpServletRequest request) {
		company1 = new CompanyWishDAO();
		// System.out.println("3.찜하기 등록!!!!!!!");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");

		int portfolionumber = (int) session.getAttribute("num");
		// System.out.println("등록 portfolionumber " + portfolionumber);

		boolean result = company1.companyLikeBag(companyId, portfolionumber);

		if (result)
			return "starters?cmd=companyLikeList";
		return "portfolio/portfolioDetail.jsp";
	}

	/** 기업이 찜한 목록 페이징 */
	private String companyLikePaging(HttpServletRequest request) {
		company1 = new CompanyWishDAO();
		// System.out.println("기업 찜하기 페이징 시작=======");
		// System.out.println("3.찜하기내역 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");
		// System.out.println("companyId:" + companyId);

		int wishAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyWishVO> companyWishList = new ArrayList<CompanyWishVO>();
		// System.out.println("//////"+wishAllCount);
		wishAllCount = company1.CompanywishListCount(companyId);
		// System.out.println("//////1333333"+wishAllCount);
		if (wishAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (wishAllCount > 0) {

			companyWishList = company1.getPortfolioWish(companyId, startRow, pageSize);
			// System.out.println("//////1333333"+companyWishList);
			pageCount = wishAllCount / pageSize + (wishAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (companyWishList.isEmpty())
			wishAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println("//////"+wishAllCount);
		//// System.out.println(companyWishList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("companyWishList", companyWishList);
		request.setAttribute("wishAllCount", new Integer(wishAllCount));

		return "companyMypage/companyLike.jsp";
	}

	/** 기업이 찜한 목록 */
	private String companyLike(HttpServletRequest request) {
		company1 = new CompanyWishDAO();
		// System.out.println("3.찜하기내역 시작==============");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");
		// System.out.println("companyId:" + companyId);

		int wishAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyWishVO> companyWishList = new ArrayList<CompanyWishVO>();
		// System.out.println("//////"+wishAllCount);
		wishAllCount = company1.CompanywishListCount(companyId);
		// System.out.println("//////1333333"+wishAllCount);
		if (wishAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (wishAllCount > 0) {

			companyWishList = company1.getPortfolioWish(companyId, startRow, pageSize);
			// System.out.println("//////1333333"+companyWishList);
			pageCount = wishAllCount / pageSize + (wishAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (companyWishList.isEmpty())
			wishAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println("//////"+wishAllCount);
		// System.out.println(companyWishList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("companyWishList", companyWishList);
		request.setAttribute("wishAllCount", new Integer(wishAllCount));

		return "companyMypage/companyLikeMain.jsp";
	}

	/** 기업 정보 수정 */
	private String companyInfoModifyAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기
		// System.out.println("기업정보수정시작////////////////////");
		MultipartRequest modify = null;

		String savePath = request.getServletContext().getRealPath("/assets/companyImage"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		modify = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = modify.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String image1 = modify.getFilesystemName("comimage1");
		String image2 = modify.getFilesystemName("comimage2");
		String image3 = modify.getFilesystemName("comimage3");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String name = modify.getParameter("name");
		String passwd = modify.getParameter("passwd");
		String email = modify.getParameter("email");
/*		String emailBox = modify.getParameter("emailBoxSelect");*/
		String email3 = modify.getParameter("emailBox1");
		String phoneNum1 = modify.getParameter("phoneNum1");
		String phoneNum2 = modify.getParameter("phoneNum2");
		String phoneNum3 = modify.getParameter("phoneNum3");
		String postcode = modify.getParameter("postcode");
		String address1 = modify.getParameter("address1");
		String address2 = modify.getParameter("address2");
		String companyUrl = modify.getParameter("homepage");
		String companyRange = modify.getParameter("range");
		String companyBirth = modify.getParameter("comBirth");
		String companyIntroduce = modify.getParameter("intro");

		String gol = "@";
		String emailBox = gol.concat(email3);

		boolean result = dao.updateCompanyMember(passwd, email + emailBox, phoneNum1 + phoneNum2 + phoneNum3,
				postcode + address1 + address2, companyUrl, companyRange, companyBirth, companyIntroduce, id);

		boolean result2 = dao.deleteCompanyImage(id);

		String[] images = { image1, image2, image3 };
		if (result == true)
			for (int i = 0; i < images.length; i++) {
				dao.addMemberCompanyImage(id, images[i]);
			}
		if (result)
			return "starters?cmd=companyInfoDetailAction";
		return "starters?cmd=companyInfo";
	}

	/** 기업 정보 수정 */
	private String companyInfo(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		CompanyVO companyInfo = null;
		// System.out.println("======기업수정======");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println("아이디"+id);
		companyInfo = dao.getComapny(id);

		request.setAttribute("companyInfo", companyInfo);

		return "companyMypage/companyInfoModify.jsp";
	}

	/** 기업 정보 */
	private String companyInfoDetailAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println(id);

		CompanyVO companyvo = dao.getComapnyInfoDetail(id);
		request.setAttribute("companyvo", companyvo);

		ArrayList<ImageVO> image = dao.getCompanyImage(id);
		request.setAttribute("image", image);

		return "companyMypage/companyInfoDetail.jsp";
	}
	// ================================================

	/** 튜티마이페이지 - 일지 수정 */
	private String tuteeDailyModifyAction(HttpServletRequest request) throws ServletException, IOException {
		tutee5 = new TuteeMypageDailyDAO();
		// System.out.println("0.일지 수정!!!!!!!");

		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기
		// System.out.println("수정시작////////////////////");
		MultipartRequest fileUP = null;

		String savePath = request.getServletContext().getRealPath("/assets/tuteeFile"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		fileUP = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = fileUP.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		HttpSession session = request.getSession();
		int dailyRecordId = (int) session.getAttribute("dailyRecordId");
		// System.out.println("dailyRecordId"+dailyRecordId);

		String tutoringRecord = fileUP.getParameter("recordText");

		String tuteeFile = (String) files.nextElement();
		String file = fileUP.getFilesystemName("tuteeFile");

		boolean result = tutee5.updateDaily(tutoringRecord, file, dailyRecordId);

		if (result)
			return "starters?cmd=tuteeDailyDetail&num=" + dailyRecordId;
		return "starters?cmd=tuteeDailyModify";
	}

	private String tuteeDailyModify(HttpServletRequest request) {
		tutee5 = new TuteeMypageDailyDAO();
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		// System.out.println("수정을 위한 dailyRecordId끌고오기");
		HttpSession session1 = request.getSession();
		int dailyRecordId = (int) session1.getAttribute("dailyRecordId");
		// System.out.println("수정을 위한 dailyRecordId");
		// System.out.println("dailyRecordId"+dailyRecordId);
		request.setAttribute("dailyRecordId", dailyRecordId);

		ArrayList<TuteeMypageDailyVO> detailDaily = tutee5.getDailyDetail(tuteeId, dailyRecordId);
		// System.out.println("detailDaily"+detailDaily);

		request.setAttribute("detailDaily", detailDaily);

		return "tuteeMypage/tuteeDailyModify.jsp";
	}

	private String dailyInfo2(HttpServletRequest request) throws ServletException, IOException {
		tutee5 = new TuteeMypageDailyDAO();

		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		HttpSession session1 = request.getSession();
		int dailyRecordId = (int) session1.getAttribute("dailyRecordId");
		TuteeMypageDailyVO detailvo = tutee5.getPage(tuteeId, dailyRecordId);
		request.setAttribute("detailvo", detailvo);
		// System.out.println("detailvo"+detailvo);

		return "tuteeMypage/tuteeDailyModify.jsp";
	}

	/** 튜티마이페이지 - 일지 등록 */
	private String tuteeDailyRegister(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int tutoringApplynum = (int) session.getAttribute("DailyApplyNum");
		// System.out.println("tutoringApplynum"+tutoringApplynum);
		request.setAttribute("tutoringApplynum", tutoringApplynum);
		return "tuteeMypage/tuteeDailyRegister.jsp";
	}

	private String dailyInfo3(HttpServletRequest request) throws ServletException, IOException {
		tutee5 = new TuteeMypageDailyDAO();

		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int DailyApplyNum = (int) session.getAttribute("DailyApplyNum");
		// System.out.println("DailyApplyNum야"+DailyApplyNum);

		TuteeMypageDailyVO detailvo = tutee5.getTitleName(tuteeId, DailyApplyNum);
		request.setAttribute("detailvo", detailvo);
		// System.out.println("detailvo야"+detailvo);

		return "tuteeMypage/tuteeDailyRegister.jsp";
	}

	private String tuteeDailyRegisterAction(HttpServletRequest request) throws ServletException, IOException {
		tutee5 = new TuteeMypageDailyDAO();
		// System.out.println("0.일지 등록!!!!!!!");

		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기
		// System.out.println("등록시작////////////////////");
		MultipartRequest fileUP = null;

		String savePath = request.getServletContext().getRealPath("/assets/tuteeFile"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		fileUP = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = fileUP.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		HttpSession session = request.getSession();
		int tutoringApplynum = (int) session.getAttribute("DailyApplyNum");
		// System.out.println("tutoringApplynum"+tutoringApplynum);

		String tutoringRegisterDate = fileUP.getParameter("tutoringdate");
		String tutoringRecord = fileUP.getParameter("recordText");

		String tuteeFile = (String) files.nextElement();
		String file = fileUP.getFilesystemName("tuteeFile");

		boolean result = tutee5.registerDaily(tutoringApplynum, tutoringRegisterDate, tutoringRecord, file);

		if (result)
			return "starters?cmd=tuteeDailyList&num=" + tutoringApplynum;
		return "starters?cmd=tuteeDailyRegister";
	}

	/** 튜티마이페이지 - 일지 상세 */
	private String tuteeDailyDetail(HttpServletRequest request) {
		tutee5 = new TuteeMypageDailyDAO();
		// System.out.println("0. 튜티일지 상세!! 시작==============");

		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		int dailyRecordId = Integer.parseInt(request.getParameter("num"));

		HttpSession session1 = request.getSession(true);
		session1.setAttribute("dailyRecordId", dailyRecordId);

		ArrayList<TuteeMypageDailyVO> dailyDetail = new ArrayList<TuteeMypageDailyVO>();
		dailyDetail = tutee5.getDailyDetail(tuteeId, dailyRecordId);
		// System.out.println("dailyDetail"+dailyDetail);

		request.setAttribute("dailyRecordId", dailyRecordId);
		request.setAttribute("dailyDetail", dailyDetail);

		return "tuteeMypage/tuteeDailyDetail.jsp";
	}

	private String dailyInfo4(HttpServletRequest request) throws ServletException, IOException {
		tutee5 = new TuteeMypageDailyDAO();

		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int DailyApplyNum = (int) session.getAttribute("DailyApplyNum");
		// System.out.println("DailyApplyNum야"+DailyApplyNum);

		TuteeMypageDailyVO detailvo = tutee5.getTitleName(tuteeId, DailyApplyNum);
		request.setAttribute("detailvo", detailvo);
		// System.out.println("detailvo야"+detailvo);

		return "tuteeMypage/tuteeDailyDetail.jsp";
	}

	private String dailyInfo(HttpServletRequest request) throws ServletException, IOException {
		tutee5 = new TuteeMypageDailyDAO();

		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int DailyApplyNum = Integer.parseInt(request.getParameter("num"));
		TuteeMypageDailyVO detailvo = tutee5.getTitleName(tuteeId, DailyApplyNum);
		request.setAttribute("detailvo", detailvo);
		// System.out.println("detailvo"+detailvo);

		return "tuteeMypage/tuteeDailyList.jsp";
	}

	/** 튜티마이페이지 - 일지 */
	private String tuteeDailyList(HttpServletRequest request) {
		tutee5 = new TuteeMypageDailyDAO();
		// System.out.println("0. 튜티일지시작==============");
		int DailyListNum = 0;

		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		int DailyApplyNum = Integer.parseInt(request.getParameter("num"));

		HttpSession session1 = request.getSession(true);
		session1.setAttribute("DailyApplyNum", DailyApplyNum);

		ArrayList<TuteeMypageDailyVO> tutoringDailyList = new ArrayList<TuteeMypageDailyVO>();
		DailyListNum = tutee5.dailyListCount(tuteeId, DailyApplyNum);

		tutoringDailyList = tutee5.getTuteeTutoringDaily(tuteeId, DailyApplyNum);
		if (tutoringDailyList.isEmpty())
			DailyListNum = 0;
		// System.out.println("DailyApplyNum"+DailyApplyNum);
		// System.out.println("tutoringDailyList"+tutoringDailyList);

		request.setAttribute("tutoringDailyList", tutoringDailyList);
		request.setAttribute("DailyListNum", new Integer(DailyListNum));

		return "tuteeMypage/tuteeDailyList.jsp";

	}
	// ==================================

	/** 면접 정보 상세보기 */
	private String interviewMoreInfo(HttpServletRequest request) throws ServletException, IOException {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청정보 더보기==============");
		int interviewId = Integer.parseInt(request.getParameter("num"));
		TuteeMypageInterviewVO2 info = tutee4.interviewInfo(interviewId);

		ArrayList<TuteeMypageInterviewJobSelectVO> sjob = tutee4.getInterviewJob(interviewId);
		request.setAttribute("sjob", sjob);

		ArrayList<InterviewDayVO> day = tutee4.getInterviewDay(interviewId);
		request.setAttribute("day", day);

		ArrayList<InterviewTimeVO> time = tutee4.getInterviewTime(interviewId);
		request.setAttribute("time", time);

		request.setAttribute("info", info);
		return "tuteeMypage/interviewCompanyInfo.jsp";

	}

	/** 면접 거절 */
	private String noInterview(HttpServletRequest request) throws ServletException, IOException {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("면접거절했다!!!!!!");
		int interviewId = Integer.parseInt(request.getParameter("interviewId"));
		// System.out.println("interviewId야"+interviewId);
		boolean result = tutee4.noInterview(interviewId);

		if (result)
			return "starters?cmd=interviewList";
		return "tuteeMypage/interviewInfoList.jsp";
	}

	/** 면접 수락 */
	private String okInterview(HttpServletRequest request) throws ServletException, IOException {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("면접수락했다!!!!!!");
		int interviewId = Integer.parseInt(request.getParameter("interviewId"));
		// System.out.println("interviewId야"+interviewId);
		boolean result = tutee4.okInterview(interviewId);

		if (result)
			return "starters?cmd=interviewList";
		return "tuteeMypage/interviewInfoList.jsp";
	}

	/** 면접신청 interview_id 값 가져온 것 이거 혜리언니 interviewId에서 끌어오자 */
	private String interviewNum(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		String id = request.getParameter("interviewId");
		int interviewId = Integer.parseInt(id);
		// System.out.println("interviewId이다" + interviewId);
		return "tuteeMypage/interviewInfoList.jsp";
		/*
		 * dao11 = new TuteeMypageInterviewDAO(); String id =
		 * request.getParameter("interviewId"); int interviewId =
		 * Integer.parseInt(id); System.out.println("interviewId이다" +
		 * interviewId); return "tuteeMypage/interviewInfoList.jsp";
		 */
	}

	/** 기업 정보 더보기 company_id 값 가져온 것 */
	private String moreInfoCompany(HttpServletRequest request) {
		// HttpSession session = request.getSession();
		// String companyId =(String)session.getAttribute("companyId");
		String companyId = request.getParameter("companyId");
		// System.out.println("companyId야" + companyId);
		// 어레이리스트에서 에러남
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);
		// System.out.println("어레이리스트 한 후 companyId"+ companyId);
		// System.out.println("images" +images );
		request.setAttribute("images", images);
		return "tuteeMypage/tuteeCompanyImage.jsp";
	}

	/** 튜티마이페이지-기간조회페이징 */
	private String interviewDateSelectPaging(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 날짜 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		String date = request.getParameter("date");
		// System.out.println("date" + date);
		String from = date.substring(0, 10);
		// System.out.println("from" + from);
		String to = date.substring(10, 20);
		// System.out.println("to" + to);

		int dateAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewDateList = new ArrayList<TuteeMypageInterviewVO>();
		dateAllCount = tutee4.selectDateListCount(tuteeId, from, to);
		if (dateAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (dateAllCount > 0) {
			interviewDateList = tutee4.selectDateList(tuteeId, from, to, startRow, pageSize);
			pageCount = dateAllCount / pageSize + (dateAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewDateList.isEmpty())
			dateAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewDateList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewDateList", interviewDateList);
		request.setAttribute("dateAllCount", new Integer(dateAllCount));

		return "tuteeMypage/interviewInfoListSelectDateSearch.jsp";
	}

	/** 튜티마이페이지-기간조회 */
	private String interviewDateSelectAction(HttpServletRequest request) throws ServletException, IOException {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 날짜 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		String date = request.getParameter("date");
		// System.out.println("date" + date);
		String from = date.substring(0, 10);
		// System.out.println("from" + from);
		String to = date.substring(10, 20);
		// System.out.println("to" + to);

		// HttpSession session = request.getSession(true);
		session.setAttribute("date", date);
		session.setAttribute("from", from);
		session.setAttribute("to", to);

		int dateAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewDateList = new ArrayList<TuteeMypageInterviewVO>();
		dateAllCount = tutee4.selectDateListCount(tuteeId, from, to);
		if (dateAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (dateAllCount > 0) {
			interviewDateList = tutee4.selectDateList(tuteeId, from, to, startRow, pageSize);
			pageCount = dateAllCount / pageSize + (dateAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewDateList.isEmpty())
			dateAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewDateList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewDateList", interviewDateList);
		request.setAttribute("dateAllCount", new Integer(dateAllCount));

		return "tuteeMypage/interviewInfoListSelectDateSearch.jsp";
	}

	private String interviewInfoListSixMonthSearchPaging(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 6개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int interviewSixMonthCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewSixMontList = new ArrayList<TuteeMypageInterviewVO>();
		interviewSixMonthCount = tutee4.selectSixMonthListCount(tuteeId);
		if (interviewSixMonthCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewSixMonthCount > 0) {
			interviewSixMontList = tutee4.selectSixMonthList(tuteeId, startRow, pageSize);
			pageCount = interviewSixMonthCount / pageSize + (interviewSixMonthCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewSixMontList.isEmpty())
			interviewSixMonthCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewSixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewSixMontList", interviewSixMontList);
		request.setAttribute("interviewSixMonthCount", new Integer(interviewSixMonthCount));

		return "tuteeMypage/interviewInfoListSixMonthSearch.jsp";
	}

	private String interviewInfoListSixMonth(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 6개월 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int interviewSixMonthCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewSixMontList = new ArrayList<TuteeMypageInterviewVO>();
		interviewSixMonthCount = tutee4.selectSixMonthListCount(tuteeId);
		if (interviewSixMonthCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewSixMonthCount > 0) {
			interviewSixMontList = tutee4.selectSixMonthList(tuteeId, startRow, pageSize);
			pageCount = interviewSixMonthCount / pageSize + (interviewSixMonthCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewSixMontList.isEmpty())
			interviewSixMonthCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewSixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewSixMontList", interviewSixMontList);
		request.setAttribute("interviewSixMonthCount", new Integer(interviewSixMonthCount));

		return "tuteeMypage/interviewInfoListSixMonthSearch.jsp";
	}

	private String interviewInfoListThreeMonthSearchPaging(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 3개월 검색 페이징==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int interviewThreeMonthCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewThreeMontList = new ArrayList<TuteeMypageInterviewVO>();
		interviewThreeMonthCount = tutee4.selectThreeMonthListCount(tuteeId);
		if (interviewThreeMonthCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewThreeMonthCount > 0) {
			interviewThreeMontList = tutee4.selectThreeMonthList(tuteeId, startRow, pageSize);
			pageCount = interviewThreeMonthCount / pageSize + (interviewThreeMonthCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewThreeMontList.isEmpty())
			interviewThreeMonthCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewThreeMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewThreeMontList", interviewThreeMontList);
		request.setAttribute("interviewThreeMonthCount", new Integer(interviewThreeMonthCount));

		return "tuteeMypage/interviewInfoListThreeMonthSearch.jsp";
	}

	private String interviewInfoListThreeMonth(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 3개월 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int interviewThreeMonthCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewThreeMontList = new ArrayList<TuteeMypageInterviewVO>();
		interviewThreeMonthCount = tutee4.selectThreeMonthListCount(tuteeId);
		if (interviewThreeMonthCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewThreeMonthCount > 0) {
			interviewThreeMontList = tutee4.selectThreeMonthList(tuteeId, startRow, pageSize);
			pageCount = interviewThreeMonthCount / pageSize + (interviewThreeMonthCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewThreeMontList.isEmpty())
			interviewThreeMonthCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewThreeMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewThreeMontList", interviewThreeMontList);
		request.setAttribute("interviewThreeMonthCount", new Integer(interviewThreeMonthCount));

		return "tuteeMypage/interviewInfoListThreeMonthSearch.jsp";
	}

	/** 면접 1개월 검색 페이징 */
	private String interviewInfoListAmonthSearchPaging(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 1개월 검색 페이징==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int interviewAMonthCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewAmontList = new ArrayList<TuteeMypageInterviewVO>();
		interviewAMonthCount = tutee4.selectAmonthListCount(tuteeId);
		if (interviewAMonthCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAMonthCount > 0) {
			interviewAmontList = tutee4.selectAmonthList(tuteeId, startRow, pageSize);
			pageCount = interviewAMonthCount / pageSize + (interviewAMonthCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewAmontList.isEmpty())
			interviewAMonthCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewAmontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewAmontList", interviewAmontList);
		request.setAttribute("interviewAMonthCount", new Integer(interviewAMonthCount));

		return "tuteeMypage/interviewInfoListAmonthSearch.jsp";
	}

	/** 면접 1개월 검색 리스트 */
	private String interviewInfoListAmonth(HttpServletRequest request) throws ServletException, IOException {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접신청 1개월 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int interviewAMonthCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewAmontList = new ArrayList<TuteeMypageInterviewVO>();
		interviewAMonthCount = tutee4.selectAmonthListCount(tuteeId);
		if (interviewAMonthCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAMonthCount > 0) {
			interviewAmontList = tutee4.selectAmonthList(tuteeId, startRow, pageSize);
			pageCount = interviewAMonthCount / pageSize + (interviewAMonthCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewAmontList.isEmpty())
			interviewAMonthCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewAmontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewAmontList", interviewAmontList);
		request.setAttribute("interviewAMonthCount", new Integer(interviewAMonthCount));

		return "tuteeMypage/interviewInfoListAmonthSearch.jsp";

	}

	// 전체보기
	private String interviewListAll(HttpServletRequest request) {
		// System.out.println("4.면접내역 전체보기 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		// System.out.println("tuteeId:" + tuteeId);

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewList = new ArrayList<TuteeMypageInterviewVO>();
		interviewAllCount = tutee4.interviewListCount(tuteeId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewList = tutee4.geInterview(tuteeId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewList.isEmpty())
			interviewAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewList", interviewList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "tuteeMypage/interviewInfoList.jsp";
	}

	/** 면접 페이징 */
	private String interviewListPaging(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접내역 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		// System.out.println("tuteeId:" + tuteeId);

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewList = new ArrayList<TuteeMypageInterviewVO>();
		interviewAllCount = tutee4.interviewListCount(tuteeId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewList = tutee4.geInterview(tuteeId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewList.isEmpty())
			interviewAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewList", interviewList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "tuteeMypage/interviewInfoList.jsp";
	}

	/** 면접리스트 */
	private String interviewList(HttpServletRequest request) {
		tutee4 = new TuteeMypageInterviewDAO();
		// System.out.println("4.면접내역 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		// System.out.println("tuteeId:" + tuteeId);

		int interviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageInterviewVO> interviewList = new ArrayList<TuteeMypageInterviewVO>();
		interviewAllCount = tutee4.interviewListCount(tuteeId);
		if (interviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (interviewAllCount > 0) {
			interviewList = tutee4.geInterview(tuteeId, startRow, pageSize);
			pageCount = interviewAllCount / pageSize + (interviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (interviewList.isEmpty())
			interviewAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(interviewList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("interviewList", interviewList);
		request.setAttribute("interviewAllCount", new Integer(interviewAllCount));

		return "tuteeMypage/interviewInfoListMain.jsp";
	}

	// =========================================
	/** 튜티마이페이지 - 튜터링 이미지 */
	private String tuteetutoringListImage(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringId = Integer.parseInt(request.getParameter("tutoringListNum"));
		ArrayList<IntImageVO> images = dao2.getTutoringsImage(tutoringId);
		// System.out.println("images" + images);
		request.setAttribute("images", images);

		return "tuteeMypage/tuteetutoringListImage.jsp";
	}

	/** 튜티마이페이지 - 마이페이지 페이징 */
	private String tuteeMypageListPaging(HttpServletRequest request) throws ServletException, IOException {
		tutee1 = new TuteeMypageDAO();
		// System.out.println("1.튜티마이페이지 수업 리스트 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int tutoringAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageVO> tutoringMypageList = new ArrayList<TuteeMypageVO>();
		tutoringAllCount = tutee1.tutoringListCount(tuteeId);
		if (tutoringAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (tutoringAllCount > 0) {
			tutoringMypageList = tutee1.getMypageList(tuteeId, startRow, pageSize);
			pageCount = tutoringAllCount / pageSize + (tutoringAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringMypageList.isEmpty())
			tutoringAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringMypageList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringMypageList", tutoringMypageList);
		request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

		return "tuteeMypage/mypageList.jsp";

	}

	/** 튜티마이페이지 - 마이페이지 */
	private String tuteeMypageList(HttpServletRequest request) throws ServletException, IOException {
		tutee1 = new TuteeMypageDAO();
		// System.out.println("1.튜티마이페이지 수업 리스트 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int tutoringAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageVO> tutoringMypageList = new ArrayList<TuteeMypageVO>();
		tutoringAllCount = tutee1.tutoringListCount(tuteeId);
		if (tutoringAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (tutoringAllCount > 0) {
			tutoringMypageList = tutee1.getMypageList(tuteeId, startRow, pageSize);
			pageCount = tutoringAllCount / pageSize + (tutoringAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringMypageList.isEmpty())
			tutoringAllCount = 0;

		// System.out.println("추천 튜터링==============");
		ArrayList<TuteeMypageRecoVO> recommendTutoring = tutee1.recommendTutoring(tuteeId);
		request.setAttribute("recommendTutoring", recommendTutoring);

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringMypageList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringMypageList", tutoringMypageList);
		request.setAttribute("tutoringAllCount", new Integer(tutoringAllCount));

		return "tuteeMypage/mypageListMain.jsp";

	}

	// =========================================================
	/** 튜티마이페이지 - 튜티정보수정 */
	private String tuteeModifyLookJobCateg(HttpServletRequest request) throws ServletException, IOException {
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "tuteeMypage/tuteeModify.jsp";
	}

	private String tuteeModify(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		MemberVO tuteeInfo = null;
		// System.out.println("======튜티수정======");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println("아이디"+id);
		tuteeInfo = dao.getTutee(id);
		request.setAttribute("tuteeInfo", tuteeInfo);

		return "tuteeMypage/tuteeModify.jsp";
	}

	private String tuteeDetailAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		// System.out.println("tuteeDetailAction시작/////////////");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println(id);
		MemberVO tuteevo = dao.getTuteeDetail(id);
		request.setAttribute("tuteevo", tuteevo);

		ArrayList<StringJobSelectVO> sjob = dao.getMemberJob(id);
		request.setAttribute("sjob", sjob);

		// System.out.println(id+tutorvo);

		return "tuteeMypage/tuteeDetail.jsp";
	}

	private String tuteeModifyAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		// System.out.println("5. 나의 정보 수정 시작////////////////////");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String passwd = request.getParameter("passwd");
		// System.out.println("passwd="+passwd);
		String email = request.getParameter("email");
		// System.out.println("email="+email);
		// String emailBox = request.getParameter("emailBoxSelect");
		String email3 = request.getParameter("emailBox");
		// System.out.println("emailBox="+emailBox);
		String phoneNum1 = request.getParameter("tel1");
		// System.out.println("phoneNum1="+phoneNum1);
		String phoneNum2 = request.getParameter("tel2");
		// System.out.println("phoneNum2="+phoneNum2);
		String phoneNum3 = request.getParameter("tel3");
		// System.out.println("phoneNum3="+phoneNum3);
		String intro = request.getParameter("intro");
		// System.out.println("intro="+intro);
		
		String gol = "@";
		String emailBox = gol.concat(email3);
		
		boolean result = dao.updateTuteeMember(id, passwd, email + emailBox, phoneNum1 + phoneNum2 + phoneNum3, intro);

		dao.deleteJobSelects(id);
		String[] mainSelect = request.getParameterValues("mainSelect");
		// System.out.println("////////////직종선택부분////////////////////");

		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			dao.JobSelects(id, realmainSelect);
			// System.out.println("직종이 변경!!");

		}
		if (result)
			return "starters?cmd=tuteeDetailAction";
		return "starters?cmd=tuteeModify";

	}

	// =====================================================
	/** 튜티마이페이지 - 찜하기내역 */
	/** 튜티마이페이지 - 찜하기 */
	private String tutoringlikeRegisterAction(HttpServletRequest request) throws ServletException, IOException {
		tutee3 = new TuteeMypageWishDAO();
		// System.out.println("3.찜하기 등록!!!!!!!");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int tutoringnumber = (int) session.getAttribute("tutoringnum");
		// System.out.println("등록 tutoringnumber " + tutoringnumber);

		boolean result = tutee3.registerlike(tuteeId, tutoringnumber);
		System.out.println(result);

		if (result)
			return "starters?cmd=tutoringLike";
		return "tuteeMypage/tutoringInfoLikeList.jsp";
	}

	/** 튜티마이페이지 - 찜하기 취소 */
	private String wishListDeleteAction(HttpServletRequest request) {
		tutee3 = new TuteeMypageWishDAO();
		// System.out.println("3.찜하기내역 취소!!");
		HttpSession session = request.getSession();

		String tutoringnumber = (String) session.getAttribute("tutoringId");
		int tutoringNum = Integer.parseInt(tutoringnumber);
		// System.out.println("삭제되는 tutoringNum " + tutoringNum);

		boolean result = tutee3.deleteWishList(tutoringNum);
		// System.out.println("deleteApplyList 결과"+result);
		if (result)
			return "starters?cmd=tutoringLike";
		return "starters?cmd=tutoringLike";
	}

	/** 찜하기 tutoring_id 값 가져온 것 */
	private String wishNum(HttpServletRequest request) {
		tutee3 = new TuteeMypageWishDAO();
		String tutoringId = request.getParameter("tutoringnum");
		HttpSession session = request.getSession(true);
		session.setAttribute("tutoringId", tutoringId);
		// System.out.println("tutoringId이다" + tutoringId);
		return "tuteeMypage/tutoringInfoLikeList.jsp";
	}

	/** 튜티마이페이지 - 찜하기 리스트 페이징 */
	private String tutoringLikePaging(HttpServletRequest request) {
		tutee3 = new TuteeMypageWishDAO();
		// System.out.println("3.찜하기내역 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int wishAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageWishVO> tutoringWishList = new ArrayList<TuteeMypageWishVO>();
		wishAllCount = tutee3.wishListCount(tuteeId);
		if (wishAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (wishAllCount > 0) {
			tutoringWishList = tutee3.getTutornigWish(tuteeId, startRow, pageSize);
			pageCount = wishAllCount / pageSize + (wishAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringWishList.isEmpty())
			wishAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringWishList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringWishList", tutoringWishList);
		request.setAttribute("wishAllCount", new Integer(wishAllCount));

		return "tuteeMypage/tutoringInfoLikeList.jsp";
	}

	/** 튜티마이페이지- 찜하기 리스트 */
	private String tutoringLike(HttpServletRequest request) {
		tutee3 = new TuteeMypageWishDAO();
		// System.out.println("3.찜하기내역 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		// System.out.println("tuteeId:" + tuteeId);

		int wishAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TuteeMypageWishVO> tutoringWishList = new ArrayList<TuteeMypageWishVO>();
		wishAllCount = tutee3.wishListCount(tuteeId);
		if (wishAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (wishAllCount > 0) {
			tutoringWishList = tutee3.getTutornigWish(tuteeId, startRow, pageSize);
			pageCount = wishAllCount / pageSize + (wishAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringWishList.isEmpty())
			wishAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringWishList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringWishList", tutoringWishList);
		request.setAttribute("wishAllCount", new Integer(wishAllCount));

		return "tuteeMypage/tutoringInfoLikeListMain.jsp";
	}

	// =========================================================

	/** 수강신청내역 환불 처리 */
	private String applyListDeleteAction(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 삭제");
		HttpSession session = request.getSession();
		String applyListNum = (String) session.getAttribute("applyNum");
		// System.out.println("applyListNum 결과"+applyListNum);
		int realNumber = Integer.parseInt(applyListNum);
		// System.out.println("나와야함"+realNumber);
		boolean result = tutee2.deleteApplyList(realNumber);
		// System.out.println("deleteApplyList 결과"+result);
		if (result)
			return "starters?cmd=tutoringInfo";
		return "starters?cmd=tutoringInfo";
	}

	/** 수강신청내역 tutoring_apply_id 값 가져온 것 */
	private String applyNum(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		String applyNum = request.getParameter("tutoringApplyId");
		HttpSession session = request.getSession(true);
		session.setAttribute("applyNum", applyNum);
		// System.out.println("applyNum이다" + applyNum);
		return "tuteeMypage/tutoringInfoList.jsp";
	}

	/** 튜티마이페이지-기간조회페이징 */
	private String tutoringDateSelectPaging(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 튜터링 날짜 조회 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		/*
		 * String date = request.getParameter("date"); System.out.println("date"
		 * + date); String from = date.substring(0,11);
		 * System.out.println("from" + from); String to = date.substring(11,20);
		 * System.out.println("to" + to);
		 */

		String date = (String) session.getAttribute("date");
		// System.out.println("date" + date);
		String from = (String) session.getAttribute("from");
		// System.out.println("from" + from);
		String to = (String) session.getAttribute("to");
		// System.out.println("to" + to);

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyDateList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectDateCount(tuteeId, from, to);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyDateList = tutee2.selectDateList(tuteeId, from, to, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyDateList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyDateList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyDateList", tutoringApplyDateList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListSelectDateSearch.jsp";
	}

	/** 튜티마이페이지-기간조회 */
	private String tutoringDateSelectAction(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 튜터링 날짜 조회==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		String date = request.getParameter("date");
		// System.out.println("date" + date);
		String from = date.substring(0, 10);
		// System.out.println("from" + from);
		String to = date.substring(10, 20);
		// System.out.println("to" + to);

		// HttpSession session = request.getSession(true);
		session.setAttribute("date", date);
		session.setAttribute("from", from);
		session.setAttribute("to", to);

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyDateList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectDateCount(tuteeId, from, to);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyDateList = tutee2.selectDateList(tuteeId, from, to, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyDateList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyDateList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyDateList", tutoringApplyDateList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListSelectDateSearch.jsp";
	}

	/** 완료 리스트 페이징 */
	private String tutoringInfoAfterPaging(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 완료 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyAfter = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectAfterCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyAfter = tutee2.selectAfter(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyAfter.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyAfter", tutoringApplyAfter);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListAfter.jsp";

	}

	/** 완료 리스트 */
	private String tutoringInfoAfter(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 완료 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyAfter = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectAfterCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyAfter = tutee2.selectAfter(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyAfter.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyAfter", tutoringApplyAfter);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListAfter.jsp";

	}

	/** 진행중 리스트 페이징 */
	private String tutoringInfoIngPaging(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 진행중 검색페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyBefore = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectBeforeCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyBefore = tutee2.selectBefore(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyBefore.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyBefore", tutoringApplyBefore);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListIng.jsp";

	}

	/** 진행중 리스트 */
	private String tutoringInfoIng(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 진행중 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyIng = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectIngCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyIng = tutee2.selectIng(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyIng.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyIng", tutoringApplyIng);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListIng.jsp";

	}

	/** 진행전 리스트 페이징 */
	private String tutoringInfoBeforePaging(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 진행전 검색페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyBefore = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectBeforeCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyBefore = tutee2.selectBefore(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyBefore.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyBefore", tutoringApplyBefore);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListBefore.jsp";

	}

	/** 진행전 리스트 */
	private String tutoringInfoBefore(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 진행전 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyBefore = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectBeforeCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyBefore = tutee2.selectBefore(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyBefore.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyBefore", tutoringApplyBefore);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListBefore.jsp";

	}

	/** 6개월 검색 페이징 */
	private String tutoringInfoListSixMonthSearchPaging(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 6개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplySixMontList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectSixMonthListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplySixMontList = tutee2.selectSixMonthList(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplySixMontList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplySixMontList", tutoringApplySixMontList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListSixMonthSearch.jsp";
	}

	/** 3개월 검색 리스트 */
	private String tutoringInfoListSixMonth(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 6개월 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplySixMontList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectSixMonthListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplySixMontList = tutee2.selectSixMonthList(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplySixMontList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplySixMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplySixMontList", tutoringApplySixMontList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListSixMonthSearch.jsp";

	}

	/** 3개월 검색 페이징 */
	private String tutoringInfoListThreeMonthSearchPaging(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 3개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyThreeMontList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectThreeMonthListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyThreeMontList = tutee2.selectThreeMonthList(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyThreeMontList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyThreeMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyThreeMontList", tutoringApplyThreeMontList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListThreeMonthSearch.jsp";
	}

	/** 3개월 검색 리스트 */
	private String tutoringInfoListThreeMonth(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 3개월 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyThreeMontList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectThreeMonthListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyThreeMontList = tutee2.selectThreeMonthList(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyThreeMontList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyThreeMontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyThreeMontList", tutoringApplyThreeMontList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListThreeMonthSearch.jsp";

	}

	/** 1개월 검색 페이징 */
	private String tutoringInfoListAmonthSearchPaging(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 1개월 검색 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyAmontList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectAmonthListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyAmontList = tutee2.selectAmonthList(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyAmontList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyAmontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyAmontList", tutoringApplyAmontList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListAmonthSearch.jsp";
	}

	/** 1개월 검색 리스트 */
	private String tutoringInfoListAmonth(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 1개월 검색 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyAmontList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.selectAmonthListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyAmontList = tutee2.selectAmonthList(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyAmontList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyAmontList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyAmontList", tutoringApplyAmontList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListAmonthSearch.jsp";

	}

	/** 전체보기 페이징 필요없음 기본 리스트 페이징 사용 가능 */
	/*
	 * private String tutoringInfoPagingAction(HttpServletRequest request) {
	 * tutee2 = new TutoringApplyListDAO(); System.out.println(
	 * "2.수강신청내역 전체보기 페이징 시작=============="); HttpSession session =
	 * request.getSession(); String tuteeId =
	 * (String)session.getAttribute("id");
	 * 
	 * int applyAllCount = 0; int pageCount = 0; int startPage = 0; int
	 * pageBlock = 0; int endPage = 0;
	 * 
	 * int pageSize = 9; String pageNum = request.getParameter("pageNum");
	 * if(pageNum == null) pageNum = "1"; int currentPage =
	 * Integer.parseInt(pageNum); ArrayList<TutoringApplyListVO>
	 * tutoringApplyList = new ArrayList<TutoringApplyListVO>(); applyAllCount =
	 * tutee2.applyListCount(tuteeId); if(applyAllCount == (currentPage-1) *
	 * pageSize) currentPage -= 1; int startRow = (currentPage - 1) * pageSize +
	 * 1; if(applyAllCount>0){ tutoringApplyList =
	 * tutee2.getTutoringApply(tuteeId,startRow,pageSize); pageCount =
	 * applyAllCount/pageSize + (applyAllCount % pageSize ==0 ? 0 : 1);
	 * startPage = 1;
	 * 
	 * if(currentPage % 5 != 0) startPage = (int)(currentPage/5) * 5 + 1; else
	 * startPage = ((int)(currentPage/5)-1) * 5 + 1; pageBlock = 5; endPage =
	 * startPage + pageBlock -1; if(endPage > pageCount) endPage = pageCount; }
	 * if(tutoringApplyList.isEmpty()) applyAllCount = 0;
	 * 
	 * System.out.println(pageNum); System.out.println(currentPage);
	 * System.out.println(startPage); System.out.println(endPage);
	 * System.out.println(tutoringApplyList);
	 * 
	 * request.setAttribute("pageNum", pageNum);
	 * request.setAttribute("startPage", startPage);
	 * request.setAttribute("endPage", endPage);
	 * request.setAttribute("pageCount", pageCount);
	 * request.setAttribute("pageSize", pageSize);
	 * request.setAttribute("currentPage", currentPage);
	 * request.setAttribute("pageBlock", pageBlock);
	 * request.setAttribute("tutoringApplyList", tutoringApplyList);
	 * request.setAttribute("applyAllCount", new Integer(applyAllCount));
	 * 
	 * return "tuteeMypage/tutoringInfoList.jsp";
	 * 
	 * }
	 */
	/** 전체보기 위한 리스트 */
	private String tutoringInfoList(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 전체보기 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.applyListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyList = tutee2.getTutoringApply(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyList", tutoringApplyList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoList.jsp";
	}

	/** 튜터마이페이지-수강신청내역(튜터링) 페이징 */
	private String tutoringInfoListPagingAction(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 페이징 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.applyListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyList = tutee2.getTutoringApply(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyList", tutoringApplyList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoList.jsp";

	}

	/** 튜티마이페이지-수강신청내역(튜터링) 글 수 */
	private String reviewListNumber(HttpServletRequest request) throws ServletException, IOException {
		tutee2 = new TutoringApplyListDAO();
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");
		int applyAllCount = tutee2.applyListCount(tuteeId);
		request.setAttribute("applyAllCount", applyAllCount);
		return "tuteeMypage/tutoringInfoListMain.jsp";
	}

	/** 튜티마이페이지-수강신청내역(튜터링) */
	/*
	 * private String tutoringInfo(HttpServletRequest request) { tutee2 = new
	 * TutoringApplyListDAO(); System.out.println("2.수강신청내역 시작==============");
	 * HttpSession session = request.getSession(); String tuteeId =
	 * (String)session.getAttribute("id"); System.out.println("tuteeId:" +
	 * tuteeId); ArrayList<TutoringApplyListVO> tutoringApplyList =
	 * tutee2.getTutoringApply(tuteeId,1, 9);
	 * request.setAttribute("tutoringApplyList", tutoringApplyList); return
	 * "tutoringInfoList.jsp"; }
	 */
	private String tutoringInfo(HttpServletRequest request) {
		tutee2 = new TutoringApplyListDAO();
		// System.out.println("2.수강신청내역 시작==============");
		HttpSession session = request.getSession();
		String tuteeId = (String) session.getAttribute("id");

		int applyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringApplyListVO> tutoringApplyList = new ArrayList<TutoringApplyListVO>();
		applyAllCount = tutee2.applyListCount(tuteeId);
		if (applyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (applyAllCount > 0) {
			tutoringApplyList = tutee2.getTutoringApply(tuteeId, startRow, pageSize);
			pageCount = applyAllCount / pageSize + (applyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (tutoringApplyList.isEmpty())
			applyAllCount = 0;

		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(tutoringApplyList);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("tutoringApplyList", tutoringApplyList);
		request.setAttribute("applyAllCount", new Integer(applyAllCount));

		return "tuteeMypage/tutoringInfoListMain.jsp";
	}

	// ======================================================
	private String chatMembers2(HttpServletRequest request) {
		dao10 = new ChatDAO();
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");
		ArrayList<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
		list = dao10.findtutorCheckName(userID);

		request.setAttribute("list", list);
		// System.out.println("@@@@@@@@@@@@@"+list);
		return "chatting/find.jsp";
	}

	private String chatMembers(HttpServletRequest request) {

		dao10 = new ChatDAO();
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");
		ArrayList<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
		list = dao10.findtuteeCheckName(userID);

		request.setAttribute("list1", list);
		// System.out.println("///////////"+list);
		return "chatting/find.jsp";
	}

	   private String messageBox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		      response.setContentType("text/html; charset=UTF-8"); 
		      dao10 = new ChatDAO();
		      String userID = request.getParameter("userID");
		      //      System.out.println("userID"+userID);
		      String chatLists ="";
		      if(userID == null || userID.equals("")) {
		         response.getWriter().write("");
		      }else{
		         try{
		            //            response.getWriter().write(dao10.getBox(userID)+"");
		            chatLists = getBox(userID);
		            request.setAttribute("chatLists", chatLists);
		            //            System.out.println("chatLists@@@@@@@@@@@"+chatLists);
		         }catch(Exception e){
		            //            System.out.println("오류발생");
		            response.getWriter().write("");
		         }

		      }
		      return "chatting/messageBoxAjax.jsp";
		   }

		   public String getBox(String userID){
		      //      System.out.println("여긴");
		      dao10 = new ChatDAO();
		       // dao 호출 끝
		      JSONObject message = new JSONObject();
		      JSONArray rst = new JSONArray();
		      message.put("result", rst);
		         // 데이터 변환
		      
		      ArrayList<ChatVO> result = dao10.getBox(userID);
		      for(int i = result.size()-1; i>=0; i--){
		         String unread = "";
		         if(userID.equals(result.get(i).getSentId())){
		            unread = dao10.getUnreadChat(result.get(i).getReceptionId(), userID) + "";
		            if(unread.equals("0")) unread = "";
		         }
		         JSONObject data = new JSONObject();
		         data.put("value1", result.get(i).getReceptionId());
		         data.put("value2", result.get(i).getSentId());
		         data.put("value3", result.get(i).getContent());
		         data.put("value4", result.get(i).getDate());
		         data.put("value5", unread);
		         
		         rst.put(data);
		      }
		      return message.toString();
		   }

	private String chatUnread(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		dao10 = new ChatDAO();
		String userID = request.getParameter("userID");
		String chatLists = "";
		if (userID == null || userID.equals("")) {
			response.getWriter().write("-1");
		} else {
			// response.getWriter().write(dao10.getAllUnreadChat(userID)+"");
			chatLists = dao10.getAllUnreadChat(userID) + "";
			request.setAttribute("chatLists", chatLists);
		}
		return "chatting/chatUnread.jsp";
	}

	private String chatMemberCheck(HttpServletRequest request) {
		dao10 = new ChatDAO();
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");

		int allMembers = 0;
		allMembers = dao10.memberCateg2(userID);
		request.setAttribute("allMembers", new Integer(allMembers));
		return "chatting/find.jsp";
	}

	private String chatfindTuteeId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		dao10 = new ChatDAO();
		String chatLists = "";
		String userID = request.getParameter("userID");
		if (userID == null || userID.equals(""))
			response.getWriter().write("-1");
		// response.getWriter().write(dao10.registerCheck(userID)+""));
		chatLists = dao10.findtuteeCheck(userID) + "";
		// System.out.println("chatLists/////////////"+chatLists);
		request.setAttribute("chatLists", chatLists);
		return "chatting/chatfindId.jsp";
	}

	private String chatfindId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		dao10 = new ChatDAO();
		String chatLists = "";
		String userID = request.getParameter("userID");
		if (userID == null || userID.equals(""))
			response.getWriter().write("-1");
		// response.getWriter().write(dao10.registerCheck(userID)+""));
		chatLists = dao10.findCheck(userID) + "";
		// System.out.println("chatLists/////////////"+chatLists);
		request.setAttribute("chatLists", chatLists);
		return "chatting/chatfindId.jsp";
	}

	private String chatList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		dao10 = new ChatDAO();
		// System.out.println("chatList갱신시작");
		// HttpSession session = request.getSession();
		// String fromID = (String) session.getAttribute("id");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");
		String chatLists = "";
		// System.out.println("chatList의 "+listType);
		// int listType = Integer.parseInt(request.getParameter("listType"));
		// System.out.println("/////////////////"+fromID+toID);
		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || listType == null
				|| listType.equals(""))
			response.getWriter().write("0");
		else if (listType.equals("10")) {
			// response.getWriter().write(getTen(fromID, toID));
			chatLists = getTen(fromID, toID);
			request.setAttribute("chatLists", chatLists);
		} else {
			try {
				// System.out.println("여기까지옴");
				int listType2 = Integer.parseInt(listType);
				// response.getWriter().write(getID(fromID, toID, listType2));
				chatLists = getID(fromID, toID, listType2);
				// System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇ"+chatLists);
				request.setAttribute("chatLists", chatLists);
				// System.out.println("여기까지오면 내가 해야지...");
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
		return "chatting/chatList.jsp";
	}

	public String getTen(String fromID, String toID) {
		dao10 = new ChatDAO();
	    // dao 호출 끝
		ArrayList<ChatVO> result = dao10.getChatListByRecent(fromID, toID, 100);
		
		JSONObject message = new JSONObject();
		JSONArray rst = new JSONArray();
		message.put("result", rst);
		   // 데이터 변환
		if(result.size() ==0) return "";
		 for(int i = 0; i<result.size(); i++){
		JSONObject data = new JSONObject();
		data.put("value1", result.get(i).getReceptionId());
		data.put("value2", result.get(i).getSentId());
		data.put("value3", result.get(i).getContent());
		data.put("value4", result.get(i).getDate());
	
		rst.put(data);
		 }
//	  System.out.println("rst.toString()2"+rst.toString());
		 message.put("last",Integer.toString(result.get(result.size()-1).getChatId()));
		 dao10.readChat(fromID, toID);
		return message.toString();

	}

	public String getID(String fromID, String toID, int chatID) {
		dao10 = new ChatDAO();
	    // dao 호출 끝
		ArrayList<ChatVO> result = dao10.getChatListById(fromID, toID, chatID);
		
		JSONObject message = new JSONObject();
		JSONArray rst = new JSONArray();
		message.put("result", rst);
		   // 데이터 변환
		if(result.size() ==0) return "";
		 for(int i = 0; i<result.size(); i++){
		JSONObject data = new JSONObject();
		data.put("value1", result.get(i).getReceptionId());
		data.put("value2", result.get(i).getSentId());
		data.put("value3", result.get(i).getContent());
		data.put("value4", result.get(i).getDate());
		
		rst.put(data);
		 }
		message.put("last",Integer.toString(result.get(result.size()-1).getChatId()));

		 dao10.readChat(fromID, toID);
		return message.toString();
		
	}


	private String chat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		dao10 = new ChatDAO();
		// HttpSession session = request.getSession();
		// String fromID = (String) session.getAttribute("id");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");
		// System.out.println(fromID+toID+chatContent);
		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || chatContent == null
				|| chatContent.equals("")) {
			response.getWriter().write("0");
		} else {
			toID = URLDecoder.decode(toID, "UTF-8");
			// response.getWriter().write(dao10.submit(fromID, toID,
			// chatContent)+"");
			int result = dao10.submit(fromID, toID, chatContent);
			// System.out.println(result);
			request.setAttribute("result", result);
			// System.out.println("result/////////////////"+result);
		}
		return "chatting/chatResult.jsp";
	}

	// 채팅 끝
	// 커뮤니티 시작
	private String qnaDeleteAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		HttpSession session = request.getSession();
		int qnaNum = (int) session.getAttribute("qnaNum");

		boolean result = dao9.deleteQna(qnaNum);
		// System.out.println("삭제 성공?"+ result);
		if (result)
			return "starters?cmd=qna";
		return "starters?cmd=qna";
	}

	private String qnaModifyAction(HttpServletRequest request) {
		// System.out.println("//////1. qna등록시작////////////////////");
		dao9 = new QnADAO2();
		String qnaTitle = request.getParameter("qnaTitle");
		String qna = request.getParameter("qna");
		HttpSession session = request.getSession();
		int qnaNum = (int) session.getAttribute("qnaNum");

		boolean result = dao9.updateQna(qnaNum, qnaTitle, qna);
		// System.out.println(result);

		if (result == false) {
			// return
			// "starters?cmd=tutoringListDetailAction&num="+tutoringRegisterId;
			// response.sendRedirect("community/qna/qnaWrite.jsp");
			return "community/qna2/qnaModify.jsp";
		}
		session.setAttribute("messageType", "성공메시지");
		session.setAttribute("messageContent", "성공적으로 게시물이 작성되었습니다.");
		// response.sendRedirect("starters?cmd=qna");
		// return "starters?cmd=qna"; // 등록완료
		return "starters?cmd=qnaListDetailAction&qnaNum=" + qnaNum;
	}

	private String qnaModify(HttpServletRequest request) {
		// System.out.println("///////////qna수정");
		dao9 = new QnADAO2();

		QnAVO2 qnaInfo = null;

		HttpSession session = request.getSession();
		int qnaNum = (int) session.getAttribute("qnaNum");
		// System.out.println(qnaNum);
		qnaInfo = dao9.qnaDetails(qnaNum);

		request.setAttribute("qnaInfo", qnaInfo);

		return "community/qna2/qnaModify.jsp";
	}

	//
	private String qnaWriter(HttpServletRequest request) {
		dao9 = new QnADAO2();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
		QnAVO2 qnavo = dao9.qnaWriter(qnaNum);
		request.setAttribute("qnavo", qnavo);
		request.setAttribute("id", id);

		return "community/qna2/qnaListDetail.jsp";
	}

	private String qnaSearchPagingAction(HttpServletRequest request) {
		 System.out.println("qna 검색");
		dao9 = new QnADAO2();

		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();

		HttpSession session = request.getSession();
		String qnaSearchName = (String) session.getAttribute("qnaSearchName");
		System.out.println("qnaSearchName"+qnaSearchName);
		String id = (String) session.getAttribute("id");
		request.setAttribute("userId", id);
		// System.out.println("qnaListNumber@@@@@"+qnaListNumber);

		qnaListNumber = dao9.qnaSearchCount(qnaSearchName);
		if (qnaListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (qnaListNumber > 0) {
			list = dao9.selectQnAList(qnaSearchName, startRow, pageSize);
			pageCount = qnaListNumber / pageSize + (qnaListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			qnaListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		request.setAttribute("qnaSearchName", qnaSearchName);
		return "community/qna2/qnaListSearch.jsp";
	}

	private String qnaSearchAction(HttpServletRequest request) {
		// System.out.println("qna 검색");
		dao9 = new QnADAO2();

		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();
		HttpSession session1 = request.getSession();
		HttpSession session = request.getSession(true);
		String qnaSearchName = request.getParameter("searchName");
		session.setAttribute("qnaSearchName", qnaSearchName);
		System.out.println("qnaSearchName"+qnaSearchName);
		String id = (String) session1.getAttribute("id");
		request.setAttribute("userId", id);
		// System.out.println("qnaListNumber@@@@@"+qnaListNumber);

		qnaListNumber = dao9.qnaSearchCount(qnaSearchName);
		if (qnaListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (qnaListNumber > 0) {
			list = dao9.selectQnAList(qnaSearchName, startRow, pageSize);
			pageCount = qnaListNumber / pageSize + (qnaListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			qnaListNumber = 0;



		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		request.setAttribute("qnaSearchName", qnaSearchName);
		request.setAttribute("searchName", qnaSearchName);
		return "community/qna2/qnaListMain.jsp";
	}

	private String qnaCountIncrease(HttpServletRequest request, HttpServletResponse response) {
		dao9 = new QnADAO2();

		int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));

		dao9.qnaCountIncrease(qnaNum); // 조회수 증가

		return "community/qna2/qnaListDetail.jsp";
	}

	private String qnaListDetailAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		// System.out.println("//////////qna디테일시작/////////");

		int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));

		QnAVO2 qvo = dao9.qnaDetail(qnaNum);
		request.setAttribute("qvo", qvo);

		HttpSession session = request.getSession(true);
		session.setAttribute("qnaNum", qnaNum);

		return "community/qna2/qnaListDetail.jsp";
	}

	private String comment(HttpServletRequest request) {
		dao9 = new QnADAO2();
		// System.out.println("//////////qna답변리스트/////////");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		request.setAttribute("userId", id);

		int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
		// System.out.println("qnaNum========="+qnaNum);
		// ArrayList<QnACommentVO> getQnAList =
		// dao9.getQnACommentReplyList(qnaNum);
		// System.out.println("getQnAList@@@@@"+getQnAList);
		ArrayList<QnACommentVO> getQnAList2 = dao9.getQnACommentList(qnaNum);
		// System.out.println("getQnAList2@@@@@"+getQnAList2);
		// if(getQnAList2.size() > 0){
		// for (int i =0; i>getQnAList2.size(); i++){
		//// System.out.println(getQnAList2.get(i).getCommentId());
		// int commnetNums = getQnAList2.get(i).getCommentId();
		// ArrayList<QnACommentVO> getQnAList =
		// dao9.getQnACommentReplyList(commnetNums);
		// if(getQnAList.size() > 0)
		// request.setAttribute("getQnAList", getQnAList);
		// return "community/qna2/qnaListDetail.jsp";
		// }
		// }
		if (getQnAList2.size() > 0)
			request.setAttribute("getQnAList", getQnAList2);

		return "community/qna2/qnaListDetail.jsp";

	}

	private String commentRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("//////1. qna답변시작////////////////////");
		dao9 = new QnADAO2();
		int qnaId = Integer.parseInt(request.getParameter("comment_board"));
		// int commentId = Integer.parseInt(`request.getParameter("commentId"));
		String comment_content = request.getParameter("comment_content");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println("qnaId"+qnaId+"comment_content"+comment_content+"id"+id);

		// QnACommentVO parent = dao9.getQnACommentInfo(qnaId);
		// System.out.println("parent"+parent);

		// boolean result1 = dao9.UpdateCommentReply(parent);
		// System.out.println("result1"+result1);

		boolean result = dao9.qnaReply(qnaId, id, comment_content);
		// System.out.println(result);

		// return "starters?cmd=qnaListDetailAction&qnaNum="+ qnaNum;

		if (result) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("1");
			out.close();
		}

		return null;

	}

	private String commentDelete(HttpServletRequest request) {

		// System.out.println("//////1. qna답글삭제////////////////////");
		dao9 = new QnADAO2();
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		// System.out.println("commentId"+commentId);
		boolean result = dao9.DeleteQnaReply(commentId);

		HttpSession session = request.getSession();
		int qnaNumber = (int) session.getAttribute("qnaNum");
		// System.out.println("qnaNumber@@@@@@@"+qnaNumber);

		return "starters?cmd=qnaListDetailAction&qnaNum=" + qnaNumber;
	}

	private String qnaCommentUpdate(HttpServletRequest request) {
		dao9 = new QnADAO2();
		// System.out.println("수정작업");
		int qnaNumber = Integer.parseInt(request.getParameter("commentId"));
		// System.out.println("qnaCommentUpdate////"+qnaNumber);
		QnACommentVO commentvo = dao9.getCommentInfo(qnaNumber);
		request.setAttribute("commentvo", commentvo);
		return "community/qna2/qnaUpdateForm.jsp";
	}

	private String qnaCommentUpdateAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		// System.out.println("수정작업Action////");

		int comment = Integer.parseInt(request.getParameter("comment_num"));
		// System.out.println("comment///"+comment);

		String comment_content = request.getParameter("comment_content");

		int qnaNumber = Integer.parseInt(request.getParameter("qna_num"));
		// System.out.println("qnaNumber///"+qnaNumber);

		boolean result = dao9.updateComment(comment, comment_content);
		// System.out.println("result"+result);

		return "starters?cmd=qnaListDetailAction&qnaNum=" + qnaNumber;
	}

	private String qnaListPaging(HttpServletRequest request) {
		// System.out.println("2. qna 리스트페이징 시작");
		dao9 = new QnADAO2();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		request.setAttribute("userId", id);
		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();
		qnaListNumber = dao9.qnaCount();
		if (qnaListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (qnaListNumber > 0) {
			list = dao9.getQnAList(startRow, pageSize);
			pageCount = qnaListNumber / pageSize + (qnaListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			qnaListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		return "community/qna2/qnaList.jsp";
	}

	private String qnamemberCategAction(HttpServletRequest request) {
		dao = new MemberDAO();
		int allMembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		allMembers = dao.memberCateg3(id);
		request.setAttribute("allMembers", new Integer(allMembers));
		return "community/qna2/qnaListMain.jsp";
	}

	private String qna(HttpServletRequest request) {
		// System.out.println("1. qna 리스트 시작");
		dao9 = new QnADAO2();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		request.setAttribute("userId", id);

		int qnaListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<QnAVO2> list = new ArrayList<QnAVO2>();
		qnaListNumber = dao9.qnaCount();
		if (qnaListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (qnaListNumber > 0) {
			list = dao9.getQnAList(startRow, pageSize);
			pageCount = qnaListNumber / pageSize + (qnaListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			qnaListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("qnaListNumber", new Integer(qnaListNumber));
		return "community/qna2/qnaListMain.jsp";

	}

	// 대댓글 부분
	private String qnaCommentReply(HttpServletRequest request) {
		// System.out.println("//////1. qna대댓글시작////////////////////");
		dao9 = new QnADAO2();

		// 부모글의 글번호를 가져온다.
		int comment_num = Integer.parseInt(request.getParameter("commentId"));
		QnACommentVO comment = dao9.getCommentInfo(comment_num);
		// System.out.println("comment///////"+comment);
		// 댓글 정보를 request에 세팅한다.
		request.setAttribute("comment", comment);

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		request.setAttribute("userId", id);

		return "community/qna2/qnaCommentReply.jsp";
	}

	private String qnaCommentReplyAction(HttpServletRequest request) {
		dao9 = new QnADAO2();
		// System.out.println("대댓글작업Action////");

		int comment_num = Integer.parseInt(request.getParameter("comment_num")); // 부모
																					// 글
																					// 번호
		int qnaNumber = Integer.parseInt(request.getParameter("qna_num")); // qna
																			// 글번호
		String comment_user_id = request.getParameter("commentUserId"); // 사용자
																		// 번호
		String comment_content = request.getParameter("comment_content"); // 게시글
		// System.out.println("/////qnaNumber///////////////////////////////////////"+qnaNumber);
		QnACommentVO parent = dao9.getCommentInfo(comment_num);
		// System.out.println("parent"+parent);
		boolean result1 = dao9.UpdateCommentReply(parent);
		// System.out.println("result1"+result1);
		boolean result = dao9.commentReplyInsert(qnaNumber, comment_user_id, comment_content, parent);
		// System.out.println("result"+result);
		// boolean result = dao9.commentReplyInsert(comment_num,qnaNumber,
		// comment_user_id, comment_content);
		// System.out.println("result"+result);

		// return "starters?cmd=qnaListDetailAction&qnaNum="+ qnaNumber;
		return null;
	}

	private String qnaRegisterAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("//////1. qna등록시작////////////////////");
		dao9 = new QnADAO2();
		String qnaTitle = request.getParameter("qnaTitle");
		String qna = request.getParameter("qna");
		String qnaPasswd = request.getParameter("qnaPasswd");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		boolean result = dao9.registerQna(id, qnaTitle, qna, qnaPasswd);
		// System.out.println(result);

		if (result == false) {
			// return
			// "starters?cmd=tutoringListDetailAction&num="+tutoringRegisterId;
			// response.sendRedirect("community/qna/qnaWrite.jsp");
			return "community/qna2/qnaWrite.jsp";
		}
		session.setAttribute("messageType", "성공메시지");
		session.setAttribute("messageContent", "성공적으로 게시물이 작성되었습니다.");
		// response.sendRedirect("starters?cmd=qna");
		// return "starters?cmd=qna"; // 등록완료
		return "starters?cmd=qna";
	}

	////////////////// qna 끝/////////////////////////
	////////////////// 리뷰 시작/////////////////////////
	// 후기 회원구분
	private String tuteeMembersCategAction(HttpServletRequest request) {
		dao = new MemberDAO();
		int tuteemembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		tuteemembers = dao.memberCateg4(id);
		request.setAttribute("tuteemembers", new Integer(tuteemembers));
		// System.out.println(members);
		return "community/review/reviewListMain.jsp";
	}

	// 후기 회원구분
	private String tuteeMemberCategAction(HttpServletRequest request) {
		dao = new MemberDAO();
		int tuteemembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		tuteemembers = dao.memberCateg4(id);
		request.setAttribute("tuteemembers", new Integer(tuteemembers));
		// System.out.println(members);
		return "community/review/reviewList.jsp";
	}

	/** 베스트 리뷰 */
	private String bestReviewAction(HttpServletRequest request) {
		// System.out.println("********best Review 시작********");
		dao4 = new ReviewDAO();
		ArrayList<ReviewBestVO> best = dao4.bestReview();
		request.setAttribute("best", best);
		// System.out.println("best리뷰------"+ best);
		return "community/review/reviewListMain.jsp";
	}

	/** 후기게시판 좋아요 */
	private String reviewLikeCount(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("/////////////좋아요액션(색변화!!)/////////////////////");
		int reviewDetailNum = Integer.parseInt(request.getParameter("reviewDetailNum"));
		// System.out.println("reviewDetailNum 좋아요 액션---------"+
		// reviewDetailNum);
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		// System.out.println(memberId);

		int reviewLikeCount = dao4.selectReviewLikeCount(reviewDetailNum);
		request.setAttribute("reviewLikeCount", new Integer(reviewLikeCount));
		// System.out.println("reviewLikeCount"+reviewLikeCount);

		return "community/review/reviewListLikeCount.jsp";
	}

	/** 후기게시판 좋아요 */
	private String reviewLikeAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("/////////////좋아요액션(색변화!!)/////////////////////");
		int reviewDetailNum = Integer.parseInt(request.getParameter("reviewDetailNum"));
		// System.out.println("reviewDetailNum 좋아요 액션---------"+
		// reviewDetailNum);
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		// System.out.println(memberId);

		int likeExist = dao4.reviewLikeExist(reviewDetailNum, memberId);

		// System.out.println("likeExist"+likeExist);

		if (likeExist == 0) { // 좋아요 한게 없으면 insert하고
			boolean result1 = dao4.reviewLike(reviewDetailNum, memberId);
			// System.out.println("result1 true" + result1);
			request.setAttribute("result1", result1);

			int reviewLikeCount = dao4.selectReviewLikeCount(reviewDetailNum);
			request.setAttribute("reviewLikeCount", new Integer(reviewLikeCount));
			// System.out.println("reviewLikeCount"+reviewLikeCount);

			int reviewLikeAll = dao4.reviewLikeIncrease(reviewDetailNum);
			// System.out.println("reviewLikeAll////////////////"+reviewLikeAll);
			request.setAttribute("reviewLikeAll", reviewLikeAll);

		} else { // 좋아요 한게 있으면 delete하고
			boolean result2 = dao4.deleteReviewLike(reviewDetailNum, memberId);
			request.setAttribute("result2", result2);

			int reviewLikeCount = dao4.selectReviewLikeCount(reviewDetailNum);
			request.setAttribute("reviewLikeCount", new Integer(reviewLikeCount));
			// System.out.println("reviewLikeCount"+reviewLikeCount);

			int reviewLikeAll = dao4.reviewUnLikeIncrease(reviewDetailNum);
			// request.setAttribute("reviewUnlike", reviewUnlike);

		}
		return "community/review/reviewListLike.jsp";
	}

	/** 후기게시판 이미지 */
	private String reviewListImage(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		int reviewId = Integer.parseInt(request.getParameter("reviewListNum"));
		HttpSession session = request.getSession(true);
		session.setAttribute("reviewId2", reviewId);
		// System.out.println("reviewId"+reviewId);
		request.setAttribute("reviewId", reviewId);
		ArrayList<IntImageVO> images = dao4.getReviewImage(reviewId);
		request.setAttribute("images", images);

		return "community/review/reviewListImage.jsp";
	}

	/** 후기게시판 수정 시 선택하는 튜터링명 */
	private String modifySelectReviewTitle(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("//////////ModifySelectReviewTitle시작");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ArrayList<TutoringBuyVO> ModifyTutoringTitleList = dao4.selectTutoringTitle(id);
		int count = dao4.selectTutoringTitleCount(id);
		request.setAttribute("ModifyTutoringTitleList", ModifyTutoringTitleList);
		request.setAttribute("count", count);
		String tutoringApplyId = request.getParameter("tutoringApplyId");
		return "community/review/reviewListMain.jsp";
	}

	/** 수정을 위해 게시물 상세 내용 가져오기 */
	/** 후기게시판 수정 */
	private String reviewModifyAction(HttpServletRequest request) throws ServletException, IOException {
		dao4 = new ReviewDAO();
		// System.out.println("//////////reviewModifyAction시작");
		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		String savePath = request.getServletContext().getRealPath("/assets/reviewImage"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		// String reviewImage=(String)files.nextElement(); // 자료가 많을 경우엔 while
		// 문을 사용
		String image1 = imageUp.getFilesystemName("reviewImage"); // 서버에 저장될
																	// 파일이름

		HttpSession session = request.getSession();
		String number = (String) session.getAttribute("reviewNum");
		int realNumber = Integer.parseInt(number);
		//
		// System.out.println("나와야함"+realNumber);

		String id = (String) session.getAttribute("id");

		int tutoringApplyId = Integer.parseInt(imageUp.getParameter("selectBox2"));
		String reviewTitle = imageUp.getParameter("reviewTitle");
		String reviewContent = imageUp.getParameter("reviewContent");

		boolean result = dao4.modifyReview(tutoringApplyId, reviewTitle, reviewContent, realNumber);
		ReviewVO result1 = dao4.getReviewList(id);
		// System.out.println("result1@@@@@@@@@@"+result1);
		// int reivewListId = result1.getReviewId();
		// System.out.println("reivewListId@@@@@@@@@@"+reivewListId);
		boolean result5 = dao4.deleteReviewImage(realNumber);
		// System.out.println("result5@@@@@@@@@@@@@@@" + result5);
		boolean result2 = dao4.addImage(realNumber, image1);
		// System.out.println("result2@@@@@@@@@@@@@@@" + result2);

		if (result)
			return "starters?cmd=reviewList";
		return "community/review/reviewListMain.jsp";
	}

	/** 조회수 */
	private String reviewCountIncrease(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("/////////////증가시작");
		String reviewNum = request.getParameter("reviewid");
		// System.out.println(reviewNum);
		int realNumber = Integer.parseInt(reviewNum);
		// System.out.println(realNumber);
		int increase = dao4.reviewCountIncrease(realNumber);
		ReviewVO increases = dao4.reviewDetailCount(realNumber);
		// ReviewVO list = dao5.reviewDetailCount();
		// System.out.println(increase);
		request.setAttribute("increase", increase);
		request.setAttribute("increases", increases);

		return "community/review/reviewListCount.jsp";
	}

	/** 후기게시판 삭제를 위한 게시글 조회 */
	private String reviewNum(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("//////////reviewNum시작");
		String reviewNum = request.getParameter("reviewid");
		// int reviewNumber = Integer.parseInt(reviewNum);
		// System.out.println("reviewNum"+reviewNum);
		HttpSession session = request.getSession(true);
		session.setAttribute("reviewNum", reviewNum);
		return null;

	}

	/** 후기게시판 삭제 */
	private String reviewDeleteAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("//////////reviewDeleteAction시작");
		HttpSession session = request.getSession();
		String number = (String) session.getAttribute("reviewNum");
		int realNumber = Integer.parseInt(number);
		// System.out.println("나와야함"+realNumber);
		boolean result = dao4.deleteReview(realNumber);
		// System.out.println("deleteReview 결과"+result);
		if (result)
			return "starters?cmd=reviewList";
		return "starters?cmd=reviewList";
	}

	/** 후기게시판 상세 모달로 띄워서 필요없음 */

	/** 등록 시 선택하는 튜터링명과 튜터명 */
	private String selectReviewTitle(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("//////////selectReviewTitle시작");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ArrayList<TutoringBuyVO> tutoringTitleList = dao4.selectTutoringTitle(id);
		request.setAttribute("tutoringTitleList", tutoringTitleList);
		// System.out.println(tutoringTitleList);
		String tutoringApplyId = request.getParameter("tutoringApplyId");
		return "community/review/reviewListMain.jsp";
	}

	/** 후기게시판 등록 */
	private String reviewRegisterAction(HttpServletRequest request) throws ServletException, IOException {
		dao4 = new ReviewDAO();
		// System.out.println("//////////reviewRegisterAction시작");
		request.setCharacterEncoding("UTF-8");
		// String filename = null;
		// String realFolder = "";
		// String saveFolder = "/assets/companyImage";
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		// ServletContext context = request.getSession().getServletContext();
		String savePath = request.getServletContext().getRealPath("/assets/reviewImage"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String reviewImage = (String) files.nextElement(); // 자료가 많을 경우엔 while
															// 문을 사용
		String image1 = imageUp.getFilesystemName("reviewImage"); // 서버에 저장될
																	// 파일이름

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int tutoringApplyId = Integer.parseInt(imageUp.getParameter("selectBox"));
		String reviewTitle = imageUp.getParameter("reviewTitle");
		String reviewContent = imageUp.getParameter("reviewContent");
		// System.out.println(tutoringApplyId+"/"+reviewTitle+"/"+image1+"/"+reviewContent);
		boolean result = dao4.registerReview(tutoringApplyId, reviewTitle, reviewContent);
		ReviewVO result1 = dao4.getReviewList(id);
		int reivewListId = result1.getReviewId();
		boolean result2 = dao4.addImage(reivewListId, image1);

		if (result || result2)
			return "starters?cmd=reviewList";
		return "community/review/reviewListMain.jsp";
	}

	/** 검색 후기게시판 목록 */
	private String reviewListSearchPaging(HttpServletRequest request) {
		dao4 = new ReviewDAO();
		// System.out.println("//////////reviewListSearch시456작");
		HttpSession session = request.getSession();
		String usersId = (String) session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();

		String searchName = (String) session.getAttribute("searchName");

		reviewAllCount = dao4.selectReviewCount(searchName);
		if (reviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (reviewAllCount > 0) {
			list = dao4.selectReviewList(searchName, usersId, startRow, pageSize);
			pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			reviewAllCount = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "community/review/reviewListSearch.jsp";
	}

	private String reviewListSearch(HttpServletRequest request) throws ServletException, IOException {
		dao4 = new ReviewDAO();
		// System.out.println("reviewListSearch시작123");
		HttpSession session = request.getSession();
		String usersId = (String) session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();

		String searchName = request.getParameter("searchName");
		// System.out.println("@@@@@@@@@searchName@@@@@@@@@@@@@@"+searchName);

		reviewAllCount = dao4.selectReviewCount(searchName);
		if (reviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (reviewAllCount > 0) {
			list = dao4.selectReviewList(searchName, usersId, startRow, pageSize);
			pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			reviewAllCount = 0;

		HttpSession session1 = request.getSession(true);
		session1.setAttribute("searchName", searchName);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "community/review/reviewListMain.jsp";

	}

	/** 후기게시판 리스트 게시글 수 */
	private String reviewListNumbers(HttpServletRequest request) throws ServletException, IOException {
		dao4 = new ReviewDAO();
		int reviewAllCount = dao4.reviewCount();
		request.setAttribute("reviewAllCount", reviewAllCount);
		return "community/review/reviewListMain.jsp";
	}

	/** 후기게시판 리스트 페이징 */
	private String reviewListPagingAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId = (String) session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		// ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		// ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		reviewAllCount = dao4.reviewCount();
		if (reviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (reviewAllCount > 0) {
			list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
			pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}

		if (list.isEmpty())
			reviewAllCount = 0;

		// HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ArrayList<TutoringBuyVO> ModifyTutoringTitleList = dao4.selectTutoringTitle(id);
		request.setAttribute("ModifyTutoringTitleList", ModifyTutoringTitleList);
		String tutoringApplyId = request.getParameter("tutoringApplyId");

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "community/review/reviewList.jsp";
	}

	/** 후기게시판 목록 */
	private String reviewList(HttpServletRequest request) {
		// System.out.println("//////////reviewList시작");
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId = (String) session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		// ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		reviewAllCount = dao4.reviewCount();
		if (reviewAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (reviewAllCount > 0) {
			list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
			pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			reviewAllCount = 0;
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));

		return "community/review/reviewListMain.jsp";
	}

	private String reviewListSelectPaging(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId = (String) session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

//		String select = request.getParameter("select");
//		session1.setAttribute("reviewSelect", select);
		String select = (String) session.getAttribute("reviewSelect");
		// System.out.println("select@@@@@@@@@@"+select);
		if (select.equals("1")) { // 번호순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		} else if (select.equals("2")) { // 최신순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewDateListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		} else if (select.equals("3")) { // 조회수순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewHitListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		} else {// 좋아요순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewLikeCountListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
		return "community/review/reviewListSelect.jsp";
	}

	private String reviewListSelectAction(HttpServletRequest request) {
		dao4 = new ReviewDAO();

		HttpSession session = request.getSession();
		String usersId = (String) session.getAttribute("id");

		int reviewAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 6;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<ReviewLikeVO2> list = new ArrayList<ReviewLikeVO2>();
		ArrayList<ReviewCountVO> listcount = new ArrayList<ReviewCountVO>();

		String select = request.getParameter("select");
		HttpSession session1 = request.getSession(true);
		session1.setAttribute("reviewSelect", select);
//		int noticeNum = (int) session.getAttribute("noticeNum");
		// System.out.println("select@@@@@@@@@@"+select);
		if (select.equals("1")) { // 번호순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewLikeListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		} else if (select.equals("2")) { // 최신순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewDateListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		} else if (select.equals("3")) { // 조회수순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewHitListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		} else {// 좋아요순
			reviewAllCount = dao4.reviewCount();
			if (reviewAllCount == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (reviewAllCount > 0) {
				list = dao4.getReviewLikeCountListUser(usersId, startRow, pageSize);
				pageCount = reviewAllCount / pageSize + (reviewAllCount % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				reviewAllCount = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("reviewAllCount", new Integer(reviewAllCount));
		return "community/review/reviewListSelect.jsp";
	}
	///////////////// 리뷰 끝 ///////////////////////////

	////////////////// 공지사항 시작//////////////////////////
	private String noticeModifyAction(HttpServletRequest request) {
		// System.out.println("공지사항 등록시작");
		dao7 = new NoticeDAO();

		HttpSession session = request.getSession();
		int noticeNum = (int) session.getAttribute("noticeNum");

		String title = request.getParameter("noticeTitle");
		String memCateg1 = request.getParameter("memberCateg1");
		String memCateg2 = request.getParameter("memberCateg2");
		String memCateg3 = request.getParameter("memberCateg3");
		String noticeContent = request.getParameter("notice");

		dao7.deleteNoticeMem(noticeNum);
		boolean result = dao7.updateNotice(title, noticeContent, noticeNum);
		// System.out.println("result" + result);

		String[] memCateg = { memCateg1, memCateg2, memCateg3 };
		for (int i = 0; i < memCateg.length; i++) {
			if (memCateg[i] != null) {
				// System.out.println("memCateg[i]"+memCateg[i]);
				dao7.registerNoticeMemberCateg(noticeNum, memCateg[i]);
			}
		}
		if (result)
			return "starters?cmd=noticeListDetailAction&noticeNum=" + noticeNum;
		return "community/notice/noticeModify.jsp";
	}

	private String noticeModify(HttpServletRequest request) {
		dao7 = new NoticeDAO();
		NoticeVO noticeInfo = null;
		// System.out.println("///////////공지사항수정");
		HttpSession session = request.getSession();
		int noticeNum = (int) session.getAttribute("noticeNum");
		// System.out.println(noticeNum);
		noticeInfo = dao7.noticeDetails(noticeNum);

		request.setAttribute("noticeInfo", noticeInfo);

		return "community/notice/noticeModify.jsp";
	}

	// 공지사항 관리자 구분
	private String noticeCateg(HttpServletRequest request) {
		dao = new MemberDAO();
		int admin = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		admin = dao.memberCateg2(id);
		request.setAttribute("admin", new Integer(admin));
		// // System.out.println(members);
		return "community/notice/noticeDetail.jsp";
	}

	// 공지사항 여러개삭제
	private String noticeDeleteMany(HttpServletRequest request) {
		dao7 = new NoticeDAO();
		String[] deleteNoticeIds = request.getParameterValues("deleteNoticeId");
//		 System.out.println("deleteNoticeIds"+deleteNoticeIds);
		// System.out.println("deleteNoticeIds.length"+deleteNoticeIds.length);
		for (int i = 0; i < deleteNoticeIds.length; i++) {
			int deleteNoticeId = Integer.parseInt(deleteNoticeIds[i]);
//			System.out.println("deleteNoticeId"+deleteNoticeId);
			boolean result = dao7.deleteNotice(deleteNoticeId);
		}

		return "starters?cmd=notice";
	}

	// 공지사항 삭제
	private String noticeDelete(HttpServletRequest request) {
		dao7 = new NoticeDAO();
		HttpSession session = request.getSession();
		int noticeNum = (int) session.getAttribute("noticeNum");

		boolean result = dao7.deleteNotice(noticeNum);
		if (result)
			return "starters?cmd=notice";
		return "community/notice/noticeDetail.jsp";
	}

	// 공지사항조회수
	private String noticeCountIncrease(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao7 = new NoticeDAO();

		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));

		dao7.noticeCountIncrease(noticeNum); // 조회수 증가

		return "community/notice/noticeDetail.jsp";

	}

	private String noticeRegisterAction(HttpServletRequest request) {
		// System.out.println("공지사항 등록시작");
		dao7 = new NoticeDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String title = request.getParameter("noticeTitle");
		String memCateg1 = request.getParameter("memberCateg1");
		String memCateg2 = request.getParameter("memberCateg2");
		String memCateg3 = request.getParameter("memberCateg3");
		String noticeContent = request.getParameter("notice");

		String ip = request.getRemoteAddr();
		// System.out.println(id+"/ "+ title+ "/ " + noticeContent + "/ "+ ip);
		boolean result = dao7.registerNotice(title, noticeContent, id, ip, null);
		// System.out.println("result" + result);
		NoticeVO result1 = dao7.getNoticeRegisterInfo(id);
		// System.out.println("result1" + result1);
		int NoticeRegisterId = result1.getNoticeId();
		// System.out.println("NoticeRegisterId" + NoticeRegisterId);
		// dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg1);
		// dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg2);
		// dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg3);
		String[] memCateg = { memCateg1, memCateg2, memCateg3 };
		for (int i = 0; i < memCateg.length; i++) {
			if (memCateg[i] != null) {
				// System.out.println("memCateg[i]"+memCateg[i]);
				dao7.registerNoticeMemberCateg(NoticeRegisterId, memCateg[i]);
			}
		}
		if (result)
			return "starters?cmd=notice";
		return "community/notice/noticeWriter.jsp";
	}

	private String adminCategAction(HttpServletRequest request) {
		// System.out.println("admin 글쓰기 시작");
		dao = new MemberDAO();
		int adminmembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		adminmembers = dao.adminCateg(id);
		// System.out.println("adminmembers@@@@@@@@@@@"+adminmembers);
		request.setAttribute("adminmembers", new Integer(adminmembers));
		return "community/notice/noticeListMain.jsp";
	}
	
	private String adminCategActionPaging111(HttpServletRequest request) {
		// System.out.println("admin 글쓰기 시작");
		dao = new MemberDAO();
		int adminmembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		adminmembers = dao.adminCateg(id);
		// System.out.println("adminmembers@@@@@@@@@@@"+adminmembers);
		request.setAttribute("adminmembers", new Integer(adminmembers));

return "community/notice/noticeList.jsp";
	}
	
	private String adminCategActionSelect(HttpServletRequest request) {
		// System.out.println("admin 글쓰기 시작");
		dao = new MemberDAO();
		int adminmembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		adminmembers = dao.adminCateg(id);
		// System.out.println("adminmembers@@@@@@@@@@@"+adminmembers);
		request.setAttribute("adminmembers", new Integer(adminmembers));
		return "community/notice/noticeListSelect.jsp";
		}
	
	private String adminCategActionPaging(HttpServletRequest request) {
		// System.out.println("admin 글쓰기 시작");
		dao = new MemberDAO();
		int adminmembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		adminmembers = dao.adminCateg(id);
		// System.out.println("adminmembers@@@@@@@@@@@"+adminmembers);
		request.setAttribute("adminmembers", new Integer(adminmembers));

return "community/notice/noticeListSearch.jsp";
	}

	private String noticeListDetailAfterAjax(HttpServletRequest request) {
		// System.out.println("1. noticeListDetailPreAjax///////시작");
		dao7 = new NoticeDAO();
		int noticeNum = Integer.parseInt(request.getParameter("DetailNum"));
		NoticeVO noticevo = dao7.noticeDetail(noticeNum);
		request.setAttribute("noticevo", noticevo);

		// System.out.println("noticevo" + noticevo);

		// System.out.println("이전글");
		NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
		request.setAttribute("noticevobefore", noticevobefore);

		// System.out.println("noticevobefore/////////////이전글" +
		// noticevobefore);

		// System.out.println("이후글");
		NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
		request.setAttribute("noticevoafter", noticevoafter);

		// System.out.println("noticevoafter" + noticevoafter);

		return "community/notice/noticeDetailAfterAjax.jsp";
	}

	// private String noticeListDetailAfterAction(HttpServletRequest request) {
	// dao7 = new NoticeDAO();
	// int noticeNum=Integer.parseInt(request.getParameter("noticeNum"));
	// NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
	// request.setAttribute("noticevoafter", noticevoafter);
	//
	// System.out.println("noticevoafter" + noticevoafter);
	//
	// HttpSession session = request.getSession(true);
	// session.setAttribute("noticeNumAfter", noticeNum);
	////
	// return "community/notice/noticeDetail.jsp";
	// }

	private String noticeListDetailPreAjax(HttpServletRequest request) {
		// System.out.println("1. noticeListDetailPreAjax///////시작");
		dao7 = new NoticeDAO();
		int noticeNum = Integer.parseInt(request.getParameter("DetailNum"));
		NoticeVO noticevo = dao7.noticeDetail(noticeNum);
		request.setAttribute("noticevo", noticevo);

		// System.out.println("noticevo" + noticevo);

		// System.out.println("이전글");
		NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
		request.setAttribute("noticevobefore", noticevobefore);

		// System.out.println("noticevobefore/////////////이전글" +
		// noticevobefore);

		// System.out.println("이후글");
		NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
		request.setAttribute("noticevoafter", noticevoafter);

		// System.out.println("noticevoafter" + noticevoafter);

		return "community/notice/noticeDetailBeforeAjax.jsp";
	}

	// private String noticeListDetailPreAction(HttpServletRequest request) {
	// System.out.println("이전글//////////");
	// dao7 = new NoticeDAO();
	// int noticeNum=Integer.parseInt(request.getParameter("noticeNum"));
	// NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
	// request.setAttribute("noticevobefore", noticevobefore);
	//
	// System.out.println("noticevobefore/////////////이전글" + noticevobefore);
	//
	// HttpSession session = request.getSession(true);
	// session.setAttribute("noticeNumBefore", noticeNum);
	//
	// return "community/notice/noticeDetail.jsp";
	// }

	private String noticeListDetailAction(HttpServletRequest request) {
		dao7 = new NoticeDAO();
		// System.out.println("디테일시작///////////");
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		NoticeVO noticevo = dao7.noticeDetail(noticeNum);
		request.setAttribute("noticevo", noticevo);

		ArrayList<NoticeMemVO> noticememvo = dao7.getNoticeMem(noticeNum);
		request.setAttribute("noticememvo", noticememvo);

		// System.out.println("이전글");
		NoticeVO noticevobefore = dao7.noticeDetailPre(noticeNum);
		request.setAttribute("noticevobefore", noticevobefore);

		// System.out.println("noticevobefore/////////////이전글" +
		// noticevobefore);

		// System.out.println("이후글");
		NoticeVO noticevoafter = dao7.noticeDetailafter(noticeNum);
		request.setAttribute("noticevoafter", noticevoafter);

		// System.out.println("noticevoafter" + noticevoafter);

		HttpSession session = request.getSession(true);
		session.setAttribute("noticeNum", noticeNum);

		return "community/notice/noticeDetail.jsp";
	}

	private String noticeSearchPagingAction(HttpServletRequest request) {
		// System.out.println("공지사항 검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		HttpSession session = request.getSession();
		String noticeSearchName = (String) session.getAttribute("noticeSearchName");
		String searchCateg = (String) session.getAttribute("searchCateg");
		// System.out.println("noticeSearchName@@@@@"+noticeSearchName);

		if (searchCateg.equals("1")) {
			noticeListNumber = dao7.selectNoticeCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectTitleList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

		} else if (searchCateg.equals("0")) {
			// System.out.println("멤버로//////////");
			noticeListNumber = dao7.selectMemCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectNoticeMember(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

			// System.out.println("list"+list);
		} else if (searchCateg.equals("2")) {
			noticeListNumber = dao7.selectContentCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

		} else {
			// System.out.println("이외로//////////");
			noticeListNumber = dao7.selectTContentCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectTContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		request.setAttribute("searchName", noticeListNumber);
		return "community/notice/noticeListSearch.jsp";
	}

	private String noticeSearchAction(HttpServletRequest request) {
		// System.out.println("공지사항 검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		String searchCateg = request.getParameter("searchCateg");
		// System.out.println("searchCateg@@@@@"+searchCateg);
		String noticeSearchName = request.getParameter("searchName");
		// System.out.println("noticeSearchName@@@@@"+noticeSearchName);
		if (searchCateg.equals("1")) {
			noticeListNumber = dao7.selectNoticeCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectTitleList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);
		} else if (searchCateg.equals("0")) {
			// System.out.println("멤버로//////////");
			noticeListNumber = dao7.selectMemCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectNoticeMember(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);

			// System.out.println("list"+list);
		} else if (searchCateg.equals("2")) {
			noticeListNumber = dao7.selectContentCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);
		} else {
			// System.out.println("이외로//////////");
			noticeListNumber = dao7.selectTContentCount(noticeSearchName);
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.selectTContentList(noticeSearchName, startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;

			HttpSession session = request.getSession(true);
			session.setAttribute("noticeSearchName", noticeSearchName);
			session.setAttribute("searchCateg", searchCateg);
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		request.setAttribute("searchName", noticeSearchName);
		return "community/notice/noticeListMain.jsp";
	}

	private String noticeListPaging(HttpServletRequest request) {
		// System.out.println("2. 공지사항 리스트 페이징 시작");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		noticeListNumber = dao7.noticeCount();
		if (noticeListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (noticeListNumber > 0) {
			list = dao7.getNoticDate(startRow, pageSize);
			pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			noticeListNumber = 0;

		// System.out.println("startRow"+startRow);

		// System.out.println("pageNum"+pageNum);
		// System.out.println("currentPage"+currentPage);
		// System.out.println("pageSize"+pageSize);
		// System.out.println("startPage"+startPage);
		// System.out.println("pageBlock"+pageBlock);
		// System.out.println("endPage"+endPage);
		// System.out.println("pageCount"+pageCount);
		// System.out.println(list);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		return "community/notice/noticeList.jsp";
	}

	private String notice(HttpServletRequest request) {
		// System.out.println("1. 공지사항 리스트 시작검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		// String subSelect = request.getParameter("subSelect");
		// System.out.println("subSelect"+subSelect);

		noticeListNumber = dao7.noticeCount();
		if (noticeListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (noticeListNumber > 0) {
			list = dao7.getNoticDate(startRow, pageSize);
			pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			noticeListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		return "community/notice/noticeListMain.jsp";
	}

	private String noticeListSelectPaging(HttpServletRequest request) {
		// System.out.println("1. 공지사항 리스트 시작 검색");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

//		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		String select = (String) session.getAttribute("noticeSelect");
		if (select.equals("1")) { // 번호순
			noticeListNumber = dao7.noticeCount();
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.getNotice(startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;
		} else if (select.equals("2")) { // 최신순
			noticeListNumber = dao7.noticeCount();
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.getNoticDate(startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;
		} else { // 조회수순
			noticeListNumber = dao7.noticeCount();
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.getNoticHits(startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		return "community/notice/noticeListSelect.jsp";
	}

	private String noticeListSelectAction(HttpServletRequest request) {
		// System.out.println("1. 공지사항 리스트 시작aaaaa");
		dao7 = new NoticeDAO();
		int noticeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();

		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		session.setAttribute("noticeSelect", select);
		// String id = (String) session.getAttribute("id");
		if (select.equals("1")) { // 번호순
			noticeListNumber = dao7.noticeCount();
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.getNotice(startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;
		} else if (select.equals("2")) { // 최신순
			// System.out.println("최신순");
			// System.out.println("select"+select);
			noticeListNumber = dao7.noticeCount();
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.getNoticDate(startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;
			// System.out.println("list"+list);
		} else { // 조회수순
			noticeListNumber = dao7.noticeCount();
			if (noticeListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (noticeListNumber > 0) {
				list = dao7.getNoticHits(startRow, pageSize);
				pageCount = noticeListNumber / pageSize + (noticeListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				noticeListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("noticeListNumber", new Integer(noticeListNumber));
		return "community/notice/noticeListSelect.jsp";
	}

	///////////////////////////// 공지사항 끝//////////////////////////////////


	// 커뮤니티 끝

	// ======================================================

	/** 일지 */

	private String tutoringDailyModify(HttpServletRequest request) {
		dao11 = new TutoringDailyDAO();
		System.out.println("수정폼");
		TutoringDailyVO dailyInfo = null;
		HttpSession session = request.getSession();
		int dailyNumber = (int) session.getAttribute("tutoringRecordNum");
		dailyInfo = dao11.tutoringDailyDetail(dailyNumber);

		request.setAttribute("dailyInfo", dailyInfo);

		return "tutorMypage/tutorDailyModify.jsp";
	}

	private String tutoringDailyModifyAction(HttpServletRequest request) throws IOException {
		dao11 = new TutoringDailyDAO();
		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기
		// System.out.println("튜터링데일수정시작////////////////////");
		MultipartRequest fileUp = null;

		String savePath = request.getServletContext().getRealPath("/assets/tutoringDaily"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		fileUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = fileUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		HttpSession session = request.getSession();
		int tutoringRecordNum = (int) session.getAttribute("tutoringRecordNum");

		String tutorFile = fileUp.getFilesystemName("tutorFile");

		String tutoringFeedback = fileUp.getParameter("tutoringFeedback");

		boolean result = dao11.updateTutoringDaily(tutoringFeedback, tutorFile, tutoringRecordNum);
		// System.out.println(tutoringFeedback+tutorFile+tutoringRecordNum+result);
		// System.out.println("starters?cmd=tutortutoringDailyDetail&num="+tutoringRecordNum);
		// System.out.println(result);
		if (result)
			return "starters?cmd=tutortutoringDailyDetail&num=" + tutoringRecordNum;
		return "starters?cmd=tutoringDailyModify";
		// return "starters?cmd=intro";

	}

	private String tutortutoringDailyDetail(HttpServletRequest request) throws ServletException, IOException {
		dao11 = new TutoringDailyDAO();
		// System.out.println("디테일로 가나 확인");
		int tutoringRecordNum = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession(true);
		session.setAttribute("tutoringRecordNum", tutoringRecordNum);
		TutoringDailyVO detailvo = dao11.tutoringDailyDetail(tutoringRecordNum);
		request.setAttribute("detailvo", detailvo);

		return "tutorMypage/tutorDailyDetail.jsp";
	}

	private String applyDailyList(HttpServletRequest request) throws ServletException, IOException {
		dao11 = new TutoringDailyDAO();
		// System.out.println("////////////튜터링리스트");
		int DailyListNumber = 0;

		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("id");
		int DailyApplyNum = Integer.parseInt(request.getParameter("num"));
		// System.out.println(DailyApplyNum);
		// System.out.println("////"+id);
		ArrayList<TutoringDailyVO> list = new ArrayList<TutoringDailyVO>();
		DailyListNumber = dao11.tutorDailyCount(id, DailyApplyNum);
		list = dao11.getTutoringDaily(id, DailyApplyNum);
		if (list.isEmpty())
			DailyListNumber = 0;
		request.setAttribute("list", list);
		 System.out.println(list);
		request.setAttribute("DailyListNumber", new Integer(DailyListNumber));

		return "tutorMypage/tutorDailyList.jsp";
	}

	private String DailyListTitleName(HttpServletRequest request) throws ServletException, IOException {
		dao11 = new TutoringDailyDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int DailyApplyNum = Integer.parseInt(request.getParameter("num"));
		TutoringDailyVO detailvo = dao11.tutoringDailyListDetail(id, DailyApplyNum);
		request.setAttribute("detailvo", detailvo);

		return "tutorMypage/tutorDailyList.jsp";
	}

	/** 튜터 튜터링 리스트 */
	private String tutorTutoringCalenderList1(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ArrayList<TutoringCalendarVO> getTutorTutoringCalendarList = dao2.getTutorTutoringCalendarList(id);
		request.setAttribute("getTutorTutoringCalendarList", getTutorTutoringCalendarList);
		
		System.out.println("getTutorTutoringCalendarList"+getTutorTutoringCalendarList);
		
		return "tutorMypage/tutorCalendar.jsp";
	}

	private String tutortutoringListImage(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringId = Integer.parseInt(request.getParameter("tutoringListNum"));
		ArrayList<IntImageVO> images = dao2.getTutoringsImage(tutoringId);
		request.setAttribute("images", images);

		return "tutorMypage/tutortutoringListImage.jsp";

	}

	private String tutorTutoringListPagingAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();

		int tutortutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		tutortutoringListNumber = dao2.tutorTutoringCount(id);
		if (tutortutoringListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1; // 맞음
		if (tutortutoringListNumber > 0) {
			list = dao2.getTutorTutoring(id, startRow, pageSize);
			pageCount = tutortutoringListNumber / pageSize + (tutortutoringListNumber % pageSize == 0 ? 0 : 1); // 맞음
			startPage = 1;

			if (currentPage % 5 != 0) // 배수이면
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			tutortutoringListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutortutoringListNumber", new Integer(tutortutoringListNumber));

		return "tutorMypage/tutorTutoringList.jsp";
	}

	private String tutorTutoringListNumber(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int tutortutoringListNumber = dao2.tutorTutoringCount(id);
		request.setAttribute("tutortutoringListNumber", tutortutoringListNumber);
		return "tutorMypage/tutortutoringListMain.jsp";
	}

	private String tutorTutoringList(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();
		// System.out.println("////////////튜터링리스트");
		int tutortutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		tutortutoringListNumber = dao2.tutorTutoringCount(id);
		if (tutortutoringListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (tutortutoringListNumber > 0) {
			list = dao2.getTutorTutoring(id, startRow, pageSize);
			pageCount = tutortutoringListNumber / pageSize + (tutortutoringListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			tutortutoringListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutortutoringListNumber", new Integer(tutortutoringListNumber));

		return "tutorMypage/tutorTutoringList.jsp";
	}

	private String tutorModifyLookJobCateg(HttpServletRequest request) throws ServletException, IOException {
		// 수정
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "tutorMypage/tutorModify.jsp";
	}

	private String tutorModify(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		MemberVO tutorInfo = null;
		// System.out.println("======튜터수정======");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println("아이디"+id);
		tutorInfo = dao.getTutor(id);

		request.setAttribute("tutorInfo", tutorInfo);

		return "tutorMypage/tutorModify.jsp";
	}

	private String tutorDetailAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// System.out.println(id);
		MemberVO tutorvo = dao.getTutorDetail(id);
		request.setAttribute("tutorvo", tutorvo);

		ArrayList<StringJobSelectVO> sjob = dao.getMemberJob(id);
		request.setAttribute("sjob", sjob);

		// System.out.println(id+tutorvo);

		return "tutorMypage/tutorDetail.jsp";
	}

	private String tutorModifyAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기
		// System.out.println("포폴수정시작////////////////////");
		MultipartRequest modify = null;

		String savePath = request.getServletContext().getRealPath("/assets/member"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		modify = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = modify.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String file2 = modify.getFilesystemName("portfolio");
		String file1 = modify.getFilesystemName("resume");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String passwd = modify.getParameter("passwd");
		String email = modify.getParameter("email");
/*		String emailBox = modify.getParameter("emailBoxSelect");*/
		String email3 = modify.getParameter("emailBox1");
		String phoneNum1 = modify.getParameter("phoneNum1");
		String phoneNum2 = modify.getParameter("phoneNum2");
		String phoneNum3 = modify.getParameter("phoneNum3");
		String intro = modify.getParameter("intro");
		
		String gol = "@";
		String emailBox = gol.concat(email3);
		
		boolean result = dao.updateTutorMember(id, passwd, email + emailBox, phoneNum1 + phoneNum2 + phoneNum3, intro,
				file1, file2);

		dao.deleteJobSelects(id);
		String[] mainSelect = modify.getParameterValues("mainSelect");
		// System.out.println("////////////직종선택부분////////////////////");

		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			dao.JobSelects(id, realmainSelect);
			// System.out.println("직종이 변경!!");

		}
		if (result)
			return "starters?cmd=tutorDetailAction";
		return "starters?cmd=tutorModify";

	}

	// ------------------------------튜터튜터링수업튜티내역

	private String tutorTutoringTuteeListPagingAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();

		int tutortutoringtuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringTuteeListVO2> list = new ArrayList<TutoringTuteeListVO2>();
		tutortutoringtuteeListNumber = dao2.tutorTutoringTuteeCount(id);
		if (tutortutoringtuteeListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1; // 맞음
		if (tutortutoringtuteeListNumber > 0) {
			list = dao2.getTutorTutoringTutee(id, startRow, pageSize);
			pageCount = tutortutoringtuteeListNumber / pageSize
					+ (tutortutoringtuteeListNumber % pageSize == 0 ? 0 : 1); // 맞음
			startPage = 1;

			if (currentPage % 5 != 0) // 배수이면
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			tutortutoringtuteeListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutortutoringtuteeListNumber", new Integer(tutortutoringtuteeListNumber));

		return "tutorMypage/tutorTutoringTuteeList.jsp";
	}

	private String tutorTutoringTuteeListNumber(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int tutortutoringtuteeListNumber = dao2.tutorTutoringTuteeCount(id);
		request.setAttribute("tutortutoringtuteeListNumber", tutortutoringtuteeListNumber);
		return "tutorMypage/tutortutoringTuteeListMain.jsp";
	}

	private String tutorTutoringTuteeList(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();
		// System.out.println("////////////튜터링리스트");
		int tutortutoringtuteeListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringTuteeListVO2> list = new ArrayList<TutoringTuteeListVO2>();
		tutortutoringtuteeListNumber = dao2.tutorTutoringTuteeCount(id);
		if (tutortutoringtuteeListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (tutortutoringtuteeListNumber > 0) {
			list = dao2.getTutorTutoringTutee(id, startRow, pageSize);
			pageCount = tutortutoringtuteeListNumber / pageSize
					+ (tutortutoringtuteeListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			tutortutoringtuteeListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutortutoringtuteeListNumber", new Integer(tutortutoringtuteeListNumber));

		return "tutorMypage/tutorTutoringTuteeList.jsp";
	}

	private String tutortutoringtuteeListImage(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringId = Integer.parseInt(request.getParameter("tutoringListNum"));
		ArrayList<IntImageVO> images = dao2.getTutoringTuteeImage(tutoringId);
		request.setAttribute("images", images);

		return "tutorMypage/tutortutoringtuteeListImage.jsp";

	}

	// =======================================================

	/** 포트폴리오 시작 */

	private String portfolioModifyLookJobCateg(HttpServletRequest request) {
		// 수정
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "portfolio/portfolioModify.jsp";
	}

	private String portfolioModifyAction(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기
		// System.out.println("포폴수정시작////////////////////");
		MultipartRequest imageUp = null;

		String savePath = request.getServletContext().getRealPath("/assets/portfolioImage"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String portfolioImage = (String) files.nextElement(); // 자료가 많을 경우엔
																// while 문을 사용
		String image1 = imageUp.getFilesystemName("portfolioImage"); // 서버에 저장될
																		// 파일이름

		String file = imageUp.getFilesystemName("portfolioFile");

		HttpSession session = request.getSession();
		int number = (int) session.getAttribute("num");

		String portfolioTitle = imageUp.getParameter("portfolioTitle");
		String portfolioMethod = imageUp.getParameter("portfolioMethod");
		String portfolioUrl = imageUp.getParameter("portfolioUrl");
		String portfolioText = imageUp.getParameter("portfolioText");

		boolean result = dao5.updatePortfolio(portfolioTitle, portfolioMethod, portfolioUrl, file, portfolioText,
				number);

		boolean result2 = dao5.deletePortfolioImage(number);
		// System.out.println("이미지 삭제");
		boolean result3 = dao5.addMemberPortfolioImage(number, image1);
		// System.out.println("이미지 변경");

		dao5.deletePortfolioJobSelects(number);
		String[] mainSelect = imageUp.getParameterValues("mainSelect");
		// System.out.println("////////////직종선택부분////////////////////");
		// System.out.println("길이"+ mainSelect.length);
		// if(result1 == true){
		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			dao5.portfolioJobSelects(number, realmainSelect);
			// System.out.println("직종이 변경!!");

		}

		if (result)
			return "starters?cmd=portfolioListDetailAction&num=" + number; // 회원가입
																			// 완료
		return "starters?cmd=portfolioModify";

	}

	private String portfolioModify(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		ArrayList<IntImageVO> intImageInfo = null;
		PortfolioVO portfolioInfo = null;
		// System.out.println("///////////포트폴리오수정");
		HttpSession session = request.getSession();
		int number = (int) session.getAttribute("num");
		// System.out.println(number);
		portfolioInfo = dao5.portfolioDetails(number);
		intImageInfo = dao5.getPortfolioImage(number);

		request.setAttribute("portfolioInfo", portfolioInfo);
		request.setAttribute("intImageInfo", intImageInfo);

		return "portfolio/portfolioModify.jsp";
	}

	private String portfolioListImage(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		int portfolioId = Integer.parseInt(request.getParameter("protfolioListNum"));
		// System.out.println("portfolioId "+portfolioId );
		ArrayList<IntImageVO> images = dao5.getPortfolioImage(portfolioId);
		// System.out.println(images.get(0));
		request.setAttribute("images", images);

		return "portfolio/portofolioListImage.jsp";
	}

	private String PortfolioLookJobCateg(HttpServletRequest request) {
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "portfolio/portfolioRegister.jsp";
	}

	private String portfolioCountIncrease(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		int num = Integer.parseInt(request.getParameter("num"));

		dao5.portfolioCountIncrease(num); // 조회수 증가

		return "portfolio/portfolioDetail.jsp";

	}

	private String portfolioMemberCategAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		int portfoliomembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		portfoliomembers = dao.tuteememberCateg(id);
		request.setAttribute("portfoliomembers", new Integer(portfoliomembers));
		return "portfolio/portfolioListMain.jsp";
	}

	private String PortfolioListPagingAction(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		//// 여기서부터 하기

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
		portfolioListNumber = dao5.portfolioCount();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.getPortfolio(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		return "portfolio/portfolioList.jsp";
	}

	private String portfolioDeleteAction(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		HttpSession session = request.getSession();
		int num = (int) session.getAttribute("num");

		boolean result = dao5.deletePortfolio(num);
		if (result)
			return "starters?cmd=portfolioList";
		return "portfolio/portfolioDetail.jsp";
	}

	private String portfolioWriter(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int num = Integer.parseInt(request.getParameter("num"));
		PortfolioVO pvo = dao5.portfolioWriter(num);
		request.setAttribute("pvo", pvo);
		return "portfolio/portfolioDetail.jsp";
	}

	/** 이해하고 넘어가기 */
	private String portfolioListDetailAction(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		company1 = new CompanyWishDAO();
		dao6 = new InterviewDAO();

		int num = Integer.parseInt(request.getParameter("num"));

		PortfolioVO pvo = dao5.portfolioDetail(num);
		request.setAttribute("pvo", pvo);

		ArrayList<IntImageVO> intimagevo = dao5.getPortfolioImage(num);
		request.setAttribute("intimagevo", intimagevo);
		HttpSession session = request.getSession(true);
		session.setAttribute("num", num);

		HttpSession session1 = request.getSession();
		String companyId = (String) session1.getAttribute("id");

		ArrayList<IntJobSelectVO> job = dao5.getPortfoliosJob(num);
		request.setAttribute("job", job);

		// 기업 마이페이지때문에 추가
		int wishListsCount = company1.portfolioWishListsCount(companyId, num);
		request.setAttribute("wishListsCount", wishListsCount);
		// System.out.println("wishListsCount//////////"+wishListsCount);

		int applyListsCount = dao6.interviewApplyListsCount(companyId, num);
		request.setAttribute("applyListsCount", applyListsCount);
		// System.out.println("applyListsCount====="+applyListsCount);

		return "portfolio/portfolioDetail.jsp";
	}

	private String portfolioLike(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		int portfoliolike = 0;
		int portfolioLikeCount = 0;

		// 주소 세션
		HttpSession session = request.getSession(true); // 지워보기
		int num = (int) session.getAttribute("num");
		String id = (String) session.getAttribute("id");

		portfoliolike = dao5.portfolioLikeExist(num, id);
		request.setAttribute("portfoliolike", new Integer(portfoliolike));

		portfolioLikeCount = dao5.selectPortfolioLikeCount(num);
		request.setAttribute("portfolioLikeCount", new Integer(portfolioLikeCount));
		// System.out.println("portfolioLikeCount"+portfolioLikeCount);

		return "portfolio/portfolioDetail.jsp";
	}

	private String portfolioLikeAction(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		// System.out.println("/////////////좋아요액션(색변화!!)/////////////////////");
		int portfolioDetailNum = Integer.parseInt(request.getParameter("portfolioDetailNum"));
		// System.out.println("좋아요를 위한 portfolioDetailNum"+portfolioDetailNum);

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		// System.out.println(memberId);

		int likeExist = dao5.portfolioLikeExist(portfolioDetailNum, memberId);
		if (likeExist == 0) { // 좋아요 한게 없으면 insert하고
			boolean result = dao5.portfolioLike(portfolioDetailNum, memberId);
			request.setAttribute("result", result);
			//
			int portfolioLikeCount = dao5.selectPortfolioLikeCount(portfolioDetailNum);
			request.setAttribute("portfolioLikeCount", new Integer(portfolioLikeCount));
			// System.out.println("portfolioLikeCount"+portfolioLikeCount);
		} else { // 좋아요 한게 있으면 delete하고
			boolean result2 = dao5.deletePortfolioLike(portfolioDetailNum, memberId);
			request.setAttribute("result2", result2);

			int portfolioLikeCount = dao5.selectPortfolioLikeCount(portfolioDetailNum);
			request.setAttribute("portfolioLikeCount", new Integer(portfolioLikeCount));
			// System.out.println("portfolioLikeCount"+portfolioLikeCount);
		}

		return "portfolio/portfolioDetailLike.jsp";
	}

	private String portfolioExistAction(HttpServletRequest request) {
		dao6 = new InterviewDAO();
		int portfolio = 0;

		// 주소 세션
		HttpSession session = request.getSession(); // 지워보기
		int num = (int) session.getAttribute("num");

		portfolio = dao6.portfolioExist(num);
		request.setAttribute("portfolio", new Integer(portfolio));
		// System.out.println(portfolio);
		return "portfolio/portfolioDetail.jsp";

	}

	private String PortfolioListAction(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		// System.out.println("///////포트폴리오검색시작");
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		String searchName = request.getParameter("searchName");
		HttpSession session = request.getSession(true);
		session.setAttribute("portfolioSearchName", searchName);
		session.setAttribute("portfoliosearchCateg", searchCateg);
		if (searchCateg.equals("0")) {
			// System.out.println("전체로 검색");

			portfolioListNumber = dao5.selectAllPortfolioCount(searchName);

			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.selectAllPortfolioList(searchName, startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else if (searchCateg.equals("1")) {
			// System.out.println("키워드로 검색");

			portfolioListNumber = dao5.selectPortfolioCount(searchName);
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.selectPortfolioList(searchName, startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else {
			// System.out.println("튜티명으로 검색");

			portfolioListNumber = dao5.selectTuteePortfolioCount(searchName);
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.selectTuteePortfolioList(searchName, startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));

		return "portfolio/portfolioListMain.jsp";
	}

	private String PortfolioListSearchPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();
		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();

		HttpSession session = request.getSession();
		String searchName = (String) session.getAttribute("portfolioSearchName");
		String searchCateg = (String) session.getAttribute("portfoliosearchCateg");

		if (searchCateg.equals("0")) {
			// System.out.println("전체로 검색");

			portfolioListNumber = dao5.selectAllPortfolioCount(searchName);

			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.selectAllPortfolioList(searchName, startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else if (searchCateg.equals("1")) {
			// System.out.println("키워드로 검색");

			portfolioListNumber = dao5.selectPortfolioCount(searchName);
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.selectPortfolioList(searchName, startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else {
			// System.out.println("튜티명으로 검색");

			portfolioListNumber = dao5.selectTuteePortfolioCount(searchName);
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.selectTuteePortfolioList(searchName, startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));

		return "portfolio/portfolioListSearch.jsp";
	}
	private String portfolioRegisterAction(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();

		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		String savePath = request.getServletContext().getRealPath("/assets/portfolioImage"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String portfolioImage = (String) files.nextElement(); // 자료가 많을 경우엔
																// while 문을 사용
		String image1 = imageUp.getFilesystemName("portfolioImage"); // 서버에 저장될
																		// 파일이름

		String portfolioFile = (String) files.nextElement(); // 자료가 많을 경우엔 while
																// 문을 사용
		String file = imageUp.getFilesystemName("portfolioFile");

		// int
		// portfolioId=Integer.parseInt(request.getParameter("portfolioId"));

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String portfolioTitle = imageUp.getParameter("portfolioTitle");
		String portfolioMethod = imageUp.getParameter("portfolioMethod");
		String portfolioUrl = imageUp.getParameter("portfolioUrl");
		String portfolioText = imageUp.getParameter("portfolioText");

		boolean result = dao5.registerPortfolio(id, portfolioTitle, portfolioMethod, portfolioUrl, file, portfolioText,
				null);
		PortfolioVO result1 = dao5.getPortfolioRegisterInfo(id);
		int portfolioRegisterId = result1.getPortfolioId();

		String[] mainSelect = imageUp.getParameterValues("mainSelect");
		// System.out.println("////////////직종선택부분////////////////////");
		// System.out.println("길이"+ mainSelect.length);
		// if(result1 == true){
		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			dao5.portfolioJobSelects(portfolioRegisterId, realmainSelect);
			// System.out.println("직종이 넣어졌대!!");
		}

		boolean result2 = dao5.addMemberPortfolioImage(portfolioRegisterId, image1);

		if (result && result2)
			return "starters?cmd=portfolioListDetailAction&num=" + portfolioRegisterId; // 회원가입
																						// 완료
		return "portfolio/portfolioRegister.jsp";
	}

	private String portfolioListNumber(HttpServletRequest request) throws ServletException, IOException {
		dao5 = new PortfolioDAO();
		int portfolioListNumber = dao5.portfolioCount();
		request.setAttribute("portfolioListNumber", portfolioListNumber);
		return "portfolio/portfolioListMain.jsp";
	}

	private String portfolioList(HttpServletRequest request) {
		dao5 = new PortfolioDAO();

		//// 여기서부터 하기

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
		portfolioListNumber = dao5.portfolioCount();
		if (portfolioListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (portfolioListNumber > 0) {
			list = dao5.getPortfolio(startRow, pageSize);
			pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			portfolioListNumber = 0;

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		return "portfolio/portfolioList.jsp";
	}

	private String portfolioListSelectPaging(HttpServletRequest request) {
		dao5 = new PortfolioDAO();

		//// 여기서부터 하기

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();
		HttpSession session = request.getSession();
		String select = (String) session.getAttribute("Portfolioselect");
		// System.out.println("select//////////"+select);
		if (select.equals("1")) { // 번호순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getPortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else if (select.equals("2")) {// 조회수순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getHitsPortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else if (select.equals("3")) {// 최신순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getDatePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else {// 좋아요순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getLikePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		return "portfolio/portfolioListSelect.jsp";
	}

	private String portfolioListSelectAction(HttpServletRequest request) {
		dao5 = new PortfolioDAO();

		//// 여기서부터 하기

		int portfolioListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<PortfolioVO> list = new ArrayList<PortfolioVO>();

		String select = request.getParameter("select");
		HttpSession session = request.getSession(true);
		session.setAttribute("Portfolioselect", select);
		// System.out.println("select//////////"+select);
		if (select.equals("1")) { // 번호순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getPortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else if (select.equals("2")) {// 조회수순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getHitsPortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else if (select.equals("3")) {// 최신순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getDatePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		} else {// 좋아요순
			portfolioListNumber = dao5.portfolioCount();
			if (portfolioListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (portfolioListNumber > 0) {
				list = dao5.getLikePortfolio(startRow, pageSize);
				pageCount = portfolioListNumber / pageSize + (portfolioListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				portfolioListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("portfolioListNumber", new Integer(portfolioListNumber));
		return "portfolio/portfolioListSelect.jsp";
	}

	//////////////////////////////// 포폴끝////////////////

	/** 기업시작 */
	private String companySearchListPaging(HttpServletRequest request) {
		dao = new MemberDAO();

		int companyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String companyId = request.getParameter("companyId");

		String pageNum = request.getParameter("pageNum");

		HttpSession session = request.getSession();
		String CompanysearchName = (String) session.getAttribute("CompanysearchName");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();

		// String tutoringSearchName = request.getParameter("searchName");

		companyAllCount = dao.selectCompanyCount(CompanysearchName);
		if (companyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (companyAllCount > 0) {
			list = dao.selectCompanyList(CompanysearchName, startRow, pageSize);
			pageCount = companyAllCount / pageSize + (companyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			companyAllCount = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);

		// System.out.println("companyId"+ companyId);
		// System.out.println("images" +images );

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		request.setAttribute("companyAllCount", new Integer(companyAllCount));

		return "company/companyListSearch.jsp";
	}

	private String companySearchListAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int companyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String companyId = request.getParameter("companyId");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();

		String CompanysearchName = request.getParameter("searchName");

		companyAllCount = dao.selectCompanyCount(CompanysearchName);
		if (companyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (companyAllCount > 0) {
			list = dao.selectCompanyList(CompanysearchName, startRow, pageSize);
			pageCount = companyAllCount / pageSize + (companyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			companyAllCount = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);
		HttpSession session = request.getSession(true);
		session.setAttribute("CompanysearchName", CompanysearchName);

		// System.out.println("companyId"+ companyId);
		// System.out.println("images" +images );

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		request.setAttribute("companyAllCount", new Integer(companyAllCount));

		return "company/companyListMain.jsp";
	}

	private String companyListPagingAction(HttpServletRequest request) {
		dao = new MemberDAO();

		int companyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String companyId = request.getParameter("companyId");

		// HttpSession session = request.getSession();
		// String companyId = (String)session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		companyAllCount = dao.companyCount();
		if (companyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (companyAllCount > 0) {
			list = dao.getCompanys(startRow, pageSize);

			pageCount = companyAllCount / pageSize + (companyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			companyAllCount = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);
		// HttpSession session = request.getSession();
		// String savePath = (String) session.getAttribute("savePath");
		// dao = new MemberDAO();
		// // int num=Integer.parseInt(request.getParameter("num"));
		// String id = request.getParameter("id");
		// System.out.println("기업 id"+id);
		//
		// CompanyVO cvo = dao.companyDetail(id);
		//
		// request.setAttribute("cvo", cvo);
		// if(id !=null){
		// return "companyModal.jsp";
		// }
		// System.out.println("companyId"+ companyId);
		// System.out.println("images" +images );
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		// request.setAttribute("savePath", savePath);
		request.setAttribute("companyAllCount", new Integer(companyAllCount));

		return "company/companyList.jsp";
	}

	private String companyListModal(HttpServletRequest request) {
		String companyId = request.getParameter("companyId");
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);
		// System.out.println("companyId"+ companyId);
		// System.out.println("images" +images );
		request.setAttribute("images", images);
		return "company/companyImg.jsp";
	}

	private String companyList(HttpServletRequest request) {
		dao = new MemberDAO();

		int companyAllCount = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String companyId = request.getParameter("companyId");

		// HttpSession session = request.getSession();
		// String companyId = (String)session.getAttribute("id");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		companyAllCount = dao.companyCount();
		if (companyAllCount == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (companyAllCount > 0) {
			list = dao.getCompanys(startRow, pageSize);

			pageCount = companyAllCount / pageSize + (companyAllCount % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			companyAllCount = 0;
		ArrayList<ImageVO> images = dao.getCompanysImage(companyId);
		// HttpSession session = request.getSession();
		// String savePath = (String) session.getAttribute("savePath");
		// dao = new MemberDAO();
		// // int num=Integer.parseInt(request.getParameter("num"));
		// String id = request.getParameter("id");
		// System.out.println("기업 id"+id);
		//
		// CompanyVO cvo = dao.companyDetail(id);
		//
		// request.setAttribute("cvo", cvo);
		// if(id !=null){
		// return "companyModal.jsp";
		// }
		// System.out.println("companyId"+ companyId);
		// System.out.println("images" +images );
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("images", images);
		// request.setAttribute("savePath", savePath);
		request.setAttribute("companyAllCount", new Integer(companyAllCount));

		return "company/companyListMain.jsp";
	}

	private String TuteeLookJobCateg(HttpServletRequest request) {
		// 원래
		// dao3 = new JobSelectDAO();
		// ArrayList<MainCategVO> mainCategNamesList = dao3.mainCategNames();
		//
		// request.setAttribute("mainCategNamesList", mainCategNamesList);
		// System.out.println(mainCategNamesList);

		// 수정
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "register/registerTutee.jsp";
	}

	// 튜터 회원가입 대분류,중분류
	private String TutorLookJobCateg(HttpServletRequest request) throws ServletException, IOException {
		// dao3 = new JobSelectDAO();
		// ArrayList<MainCategVO> mainCategNamesList = dao3.mainCategNames();
		//
		// request.setAttribute("mainCategNamesList", mainCategNamesList);
		// System.out.println(mainCategNamesList);

		// 수정
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "register/registerTutor.jsp";
	}

	private String membersCategAction(HttpServletRequest request) {
		dao = new MemberDAO();
		int tuteemembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		tuteemembers = dao.memberCateg4(id);
		request.setAttribute("tuteemembers", new Integer(tuteemembers));
		// System.out.println(members);
		return "tutoring/tutoringDetail.jsp";
	}

	private String memberCategAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		int tutoringmembers = 0;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		tutoringmembers = dao.memberCateg(id);
		request.setAttribute("tutoringmembers", new Integer(tutoringmembers));
		// System.out.println(members);
		return "tutoring/tutoringListMain.jsp";
	}

	private String registerTuteeAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		String memberId = request.getParameter("memberId");
		String passwd = request.getParameter("passwd_check");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
//		String email2 = request.getParameter("emailBoxSelect");
		String email3 = request.getParameter("emailBox1");
		String birth = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String phoneNum1 = request.getParameter("tel1");
		String phoneNum2 = request.getParameter("tel2");
		String phoneNum3 = request.getParameter("tel3");
		String intro = request.getParameter("intro");

		String gol = "@";
		String email2 = gol.concat(email3);
//		System.out.println("//////튜티회원가입시작////////////////////2"+email2);
//		 System.out.println("//////튜티회원가입시작////////////////////3"+email4);

		// 튜터 정보 추가
		boolean result1 = dao.addMemberTutee(memberId, name, passwd, birth, gender, email + email2,
				phoneNum1 + phoneNum2 + phoneNum3, intro, null);
		// System.out.println(result1);
		String[] mainSelect = request.getParameterValues("mainSelect");

		// System.out.println("////////////직종선택부분////////////////////");
		// System.out.println("길이"+ mainSelect.length);
		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			boolean result2 = dao.JobSelects(memberId, realmainSelect);
			// System.out.println("설마 true?"+result2);
		}
		if (result1)
			return "login/login.jsp"; // 회원가입 완료

		return "register/registerTutee.jsp";

	}

	private String registerTutorAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		String memberId = request.getParameter("memberId");
		String passwd = request.getParameter("passwd_check");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
//		String emailBox = request.getParameter("emailBoxSelect");
		String email3 = request.getParameter("emailBox1");
		String birth = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String phoneNum1 = request.getParameter("tel1");
		String phoneNum2 = request.getParameter("tel2");
		String phoneNum3 = request.getParameter("tel3");
		String intro = request.getParameter("intro");
		String resume = request.getParameter("resume");
		String portfolio = request.getParameter("portfolio");
		
		String gol = "@";
		String emailBox = gol.concat(email3);
//		System.out.println("//////튜티회원가입시작////////////////////2"+email2);

		boolean result3 = dao.memberIdCheck2(memberId);
		// 튜터 정보 추가
		boolean result1 = dao.addMemberTutor(memberId, name, passwd, birth, gender, email + emailBox,
				phoneNum1 + phoneNum2 + phoneNum3, intro, resume, portfolio, null);
		// System.out.println("///////////튜터 회원가입");
		String[] mainSelect = request.getParameterValues("mainSelect");
		if (result1 == true) {
			for (int i = 0; i < mainSelect.length; i++) {
				// System.out.println(mainSelect[i]+"<br/>");
				int realmainSelect = Integer.parseInt(mainSelect[i]);
				boolean result2 = dao.JobSelects(memberId, realmainSelect);
				// System.out.println(result2);
			}
		}
		// String mainSelect = request.getParameter("mainSelect");
		// String middleSelect = request.getParameter("middleSelect");
		// int jobSelects = Integer.parseInt(middleSelect);

		// System.out.println("mainSelect는"+ mainSelect+ "이다");
		// System.out.println("middleSelect는"+ middleSelect+ "이다");
		// System.out.println("jobSelects"+ memberId+jobSelects);
		System.out.println("//////////////여기까지");

		System.out.println(result3);
		// if (result1&&result3)
		if (result1)
			return "login/login.jsp"; // 회원가입 완료

		return "register/registerTutor.jsp";
	}

	private String registerCompanyAction(HttpServletRequest request) throws Exception {
		dao = new MemberDAO();
		request.setCharacterEncoding("UTF-8");
		// String filename = null;
		// String realFolder = "";
		// String saveFolder = "/assets/companyImage";
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		// ServletContext context = request.getSession().getServletContext();
		String savePath = request.getServletContext().getRealPath("/assets/companyImage"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		// HttpSession session = request.getSession(true);
		// session.setAttribute("savePath", savePath);

		// String comimage1=(String)files.nextElement(); // 자료가 많을 경우엔 while 문을
		// 사용
		String image1 = imageUp.getFilesystemName("comimage1"); // 서버에 저장될 파일이름
		String comimage2 = imageUp.getFilesystemName("comimage2");
		String comimage3 = imageUp.getFilesystemName("comimage3");
		// System.out.println("image1"+image1);
		String companyId = imageUp.getParameter("companyId");
		String name = imageUp.getParameter("name");
		String passwd = imageUp.getParameter("passwd_check");
		String email = imageUp.getParameter("email");
//		String emailBox = imageUp.getParameter("emailBoxSelect");
		String email3 = imageUp.getParameter("emailBox1");
		String phoneNum1 = imageUp.getParameter("tel1");
		String phoneNum2 = imageUp.getParameter("tel2");
		String phoneNum3 = imageUp.getParameter("tel3");
		String homepage = imageUp.getParameter("homepage");
		String postcode = imageUp.getParameter("postcode");
		String address1 = imageUp.getParameter("address1");
		String address2 = imageUp.getParameter("address2");
		String range = imageUp.getParameter("range");
		String comBirth = imageUp.getParameter("comBirth");
		String companyintro = imageUp.getParameter("companyintro");
		
		String gol = "@";
		String emailBox = gol.concat(email3);
		
		
//System.out.println("@@"+emailBox);
		boolean result1 = dao.addMemberCompany(companyId, name, passwd, email + emailBox,
				phoneNum1 + phoneNum2 + phoneNum3, postcode + address1 + address2, homepage, range, comBirth, companyintro, null);
		// boolean result = dao.addMemberCompany(companyvo);
		// System.out.println(companyId+"/"+name+"/"+passwd+"/"+(email+emailBox)+(phoneNum1+phoneNum2+phoneNum3)+(address1+address2)+
		// homepage+comBirth+range+companyintro+image1+comimage2+comimage3);

		String[] images = { image1, comimage2, comimage3 };
		if (result1 == true)
			for (int i = 0; i < images.length; i++) {
				// System.out.println(images[i]);
				dao.addMemberCompanyImage(companyId, images[i]);
			}

		if (result1)
			return "login/login.jsp"; // 회원가입 완료
		return "register/registerCompany.jsp";
	}

	private String tutoringLookJobCateg(HttpServletRequest request) {
		// 수정
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "tutoring/tutoringRegister.jsp";
	}

	private String tutoringRegisterAction(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();
		// System.out.println("////////////여긴가");
		request.setCharacterEncoding("UTF-8");
		// String filename = null;
		// String realFolder = "";
		// String saveFolder = "/assets/companyImage";
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		// ServletContext context = request.getSession().getServletContext();
		String savePath = request.getServletContext().getRealPath("/assets/tutoringImg"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String tutoringImg = (String) files.nextElement(); // 자료가 많을 경우엔 while
															// 문을 사용
		String image1 = imageUp.getFilesystemName("tutoringImg"); // 서버에 저장될
																	// 파일이름

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String mainTitle = imageUp.getParameter("mainTitle");
		String subTitle = imageUp.getParameter("subTitle");
		String from = imageUp.getParameter("from");
		String to = imageUp.getParameter("to");
		// String daySelect1 = imageUp.getParameter("daySelect");
		// System.out.println("요일////" + daySelect1);

		// 첫번째
		/* 시작시간 형변환 */
		String s1Time1 = imageUp.getParameter("s1Time1");
		String[] splits = s1Time1.split(":");
		int hour = Integer.parseInt(splits[0]);
		// System.out.println("hour"+hour);
		int min = Integer.parseInt(splits[1].substring(0, 2));
		// int min=Integer.parseInt(splits[1]);
		// System.out.println("min"+min);
		String ampm = splits[1].substring(splits[1].length() - 2, splits[1].length());
		// String ampm = splits[1].substring(5, 6);
		// System.out.println(ampm);
		if (ampm.equals("PM")) {
			hour = hour + 12;
		}
		if (hour == 12 && ampm.equals("AM")) {
			hour = 0;
		}
		if (hour == 24 && ampm.equals("PM")) {
			hour = 12;
		}
		String startTime = String.format("%02d:%02d", hour, min);

		/* 끝나는 시간 형변환 */
		String s1Time2 = imageUp.getParameter("s1Time2");
		String[] splits2 = s1Time2.split(":");
		int hour2 = Integer.parseInt(splits2[0]);
		int min2 = Integer.parseInt(splits2[1].substring(0, 2));
		String ampm2 = splits2[1].substring(splits2[1].length() - 2, splits2[1].length());
		if (ampm2.equals("PM")) {
			hour2 = hour2 + 12;
		}
		if (hour2 == 12 && ampm2.equals("AM")) {
			hour2 = 0;
		}
		if (hour2 == 24 && ampm2.equals("PM")) {
			hour2 = 12;
		}
		String endTime = String.format("%02d:%02d", hour2, min2);

		////////////////////////////////////////////////////////////////////////////////////////////////
		String jobCareer = imageUp.getParameter("jobCareer");
		String tutoringMoney = imageUp.getParameter("tutoringMoney");
		String tutoringPlan = imageUp.getParameter("tutoringPlan");
		// System.out.println(id+"그리고"+mainTitle+"그리고"+ subTitle+"그리고"
		// +from+"그리고"+ to+"그리고"+ daySelect1+"그리고"+
		// startTime+"그리고"+endTime+"그리고"+jobCareer+"그리고"+
		// tutoringMoney+"그리고"+tutoringPlan+"그리고"+image1 );

		boolean result1 = dao2.registerTutoring(id, mainTitle, subTitle, from, to, jobCareer, tutoringMoney,
				tutoringPlan, null);
		// System.out.println("result1" + result1);
		TutoringVO2 result2 = dao2.getTutoringRegisterInfo(id);
		// System.out.println("result2.getTutoringId()"+result2.getTutoringId());

		int tutoringRegisterId = result2.getTutoringId();

		boolean result3 = dao2.registerTutoringImage(tutoringRegisterId, image1);
		// System.out.println("result3" + result3);

		boolean result4 = dao2.registerTutoringTime(tutoringRegisterId, startTime, endTime);
		// 두번째 시간
		/* 시작시간 형변환 */
		String s1Time3 = imageUp.getParameter("s1Time3");
		String s1Time4 = imageUp.getParameter("s1Time4");
		if (s1Time3 != null && s1Time4 != null) {
			String[] splits3 = s1Time3.split(":");
			int hour3 = Integer.parseInt(splits3[0]);
			int min3 = Integer.parseInt(splits3[1].substring(0, 2));
			String ampm3 = splits3[1].substring(splits3[1].length() - 2, splits3[1].length());
			if (ampm3.equals("PM")) {
				hour3 = hour3 + 12;
			}
			if (hour3 == 12 && ampm3.equals("AM")) {
				hour3 = 0;
			}
			if (hour3 == 24 && ampm3.equals("PM")) {
				hour3 = 12;
			}
			String startTime3 = String.format("%02d:%02d", hour3, min3);
			/* 끝나는 시간 형변환 */

			String[] splits4 = s1Time4.split(":");
			int hour4 = Integer.parseInt(splits4[0]);
			int min4 = Integer.parseInt(splits4[1].substring(0, 2));
			String ampm4 = splits4[1].substring(splits4[1].length() - 2, splits4[1].length());
			if (ampm4.equals("PM")) {
				hour4 = hour4 + 12;
			}
			if (hour4 == 12 && ampm4.equals("AM")) {
				hour4 = 0;
			}
			if (hour4 == 24 && ampm4.equals("PM")) {
				hour4 = 12;
			}
			String endTime4 = String.format("%02d:%02d", hour4, min4);

			dao2.registerTutoringTime(tutoringRegisterId, startTime3, endTime4);
		}

		// 세번째
		/* 시작시간 형변환 */
		String s1Time5 = imageUp.getParameter("s1Time5");
		String s1Time6 = imageUp.getParameter("s1Time6");
		if (s1Time5 != null && s1Time6 != null) {
			String[] splits5 = s1Time5.split(":");
			int hour5 = Integer.parseInt(splits5[0]);
			int min5 = Integer.parseInt(splits5[1].substring(0, 2));
			String ampm5 = splits5[1].substring(splits5[1].length() - 2, splits5[1].length());
			if (ampm5.equals("PM")) {
				hour5 = hour5 + 12;
			}
			if (hour5 == 12 && ampm5.equals("AM")) {
				hour5 = 0;
			}
			if (hour5 == 24 && ampm5.equals("PM")) {
				hour5 = 12;
			}
			String startTime5 = String.format("%02d:%02d", hour5, min5);

			/* 끝나는 시간 형변환 */

			String[] splits6 = s1Time6.split(":");
			int hour6 = Integer.parseInt(splits6[0]);
			int min6 = Integer.parseInt(splits6[1].substring(0, 2));
			String ampm6 = splits6[1].substring(splits6[1].length() - 2, splits6[1].length());
			if (ampm6.equals("PM")) {
				hour6 = hour6 + 12;
			}
			if (hour6 == 12 && ampm6.equals("AM")) {
				hour6 = 0;
			}
			if (hour6 == 24 && ampm6.equals("PM")) {
				hour6 = 12;
			}
			String endTime6 = String.format("%02d:%02d", hour6, min6);

			dao2.registerTutoringTime(tutoringRegisterId, startTime5, endTime6);
		}

		// System.out.println("result4" + result4);

		String[] mainSelect = imageUp.getParameterValues("mainSelect");
		// System.out.println("////////////직종선택부분////////////////////");
		// System.out.println("길이"+ mainSelect.length);
		// if(result1 == true){
		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			dao2.tutoringJobSelects(tutoringRegisterId, realmainSelect);
			// System.out.println("직종이 넣어졌대!!");
		}
		// System.out.println("요일//// test");
		String daySelects = imageUp.getParameter("daySelect");
		String[] daySelectArray = daySelects.split("");
		// System.out.println(daySelectArray.length);
		for (int i = 0; i < daySelectArray.length; i++) {
			dao2.registerTutoringDay(tutoringRegisterId, daySelectArray[i]);
			// System.out.println("확인");
		}
		if (result1 && result3 && result4)
			return "starters?cmd=tutoringListDetailAction&num=" + tutoringRegisterId;
		return "tutoring/tutoringRegister.jsp";
	}

	private String tutoringListImage(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringId = Integer.parseInt(request.getParameter("tutoringListNum"));
		// System.out.println("tutoringId나와야지"+tutoringId);
		ArrayList<IntImageVO> images = dao2.getTutoringsImage(tutoringId);
		// System.out.println(images.get(0));
		request.setAttribute("images", images);

		return "tutoring/tutoringListImage.jsp";
		// 2번
		// System.out.println("//////////////이미지 나와야지");
		// String tutoringId = request.getParameter("tutoringListNum");
		// String[] tutoringIds = tutoringId.split(",");
		//
		// System.out.println("tutoringId"+tutoringId);
		// System.out.println("tutoringIds"+tutoringIds);
		// System.out.println("tutoringId길이"+tutoringIds.length);
		// for(int i = 0; i < tutoringIds.length; i++){
		// System.out.println(tutoringIds[i]);
		// int realTutoringId = Integer.parseInt(tutoringIds[i]);
		// System.out.println("realTutoringId"+realTutoringId);
		// ArrayList<IntImageVO> images =
		// dao2.getTutoringsImage(realTutoringId);
		// request.setAttribute("images", images);
		// }

	}

	private String tutoringList(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		// System.out.println("////////////튜터링리스트");
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		tutoringListNumber = dao2.tutoringCount();
		if (tutoringListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		if (tutoringListNumber > 0) {
			list = dao2.getTutoring(startRow, pageSize);
			pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
			startPage = 1;

			if (currentPage % 5 != 0)
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			tutoringListNumber = 0;
		// int tutoringId =
		// Integer.parseInt(request.getParameter("tutoringListNum"));
		// System.out.println(tutoringId);
		// System.out.println(pageNum);
		// System.out.println(currentPage);
		// System.out.println(startPage);
		// System.out.println(endPage);
		// System.out.println(list);
		// System.out.println(images);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));

		return "tutoring/tutoringList.jsp";
	}

	// 1010추가_좋아요_조회수순
	private String TutoringListSelectPaging(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		// System.out.println("////////////튜터링리스트");
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		
		//String select = request.getParameter("select");
		HttpSession session1 = request.getSession();
		String select = (String) session1.getAttribute("tutoringSelect");
		if (select.equals("1")) {
			tutoringListNumber = dao2.tutoringCount();
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring(startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		} else if (select.equals("2")) {
			tutoringListNumber = dao2.tutoringCount();
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getHitTutoring(startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		} else {
			tutoringListNumber = dao2.tutoringCount();
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getDateTutoring(startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));
		return "tutoring/tutoringListSelect.jsp";
	}

	private String TutoringListSelectAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		// System.out.println("////////////조회수순");
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		String select = request.getParameter("select");
		HttpSession session1 = request.getSession();
		session1.setAttribute("tutoringSelect",select);
		// System.out.println("select//////////"+select);
		if (select.equals("1")) {
			tutoringListNumber = dao2.tutoringCount();
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring(startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		} else if (select.equals("2")) {
			tutoringListNumber = dao2.tutoringCount();
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getHitTutoring(startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		} else {
			tutoringListNumber = dao2.tutoringCount();
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getDateTutoring(startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));
		return "tutoring/tutoringListSelect.jsp";
	}

	private String tutoringListNumber(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();
		int tutoringListNumber = dao2.tutoringCount();
		request.setAttribute("tutoringListNumber", tutoringListNumber);
		// System.out.println(tutoringListNumber);
		return "tutoring/tutoringListMain.jsp";
	}

	private String tutoringListDetailAction(HttpServletRequest request) throws ServletException, IOException {
		// System.out.println("////////////디테일 시작/////////////////");
		dao2 = new TutoringDAO();
		tutee3 = new TuteeMypageWishDAO();
		HttpSession session1 = request.getSession();
		String memberId = (String) session1.getAttribute("id");

		int tutoringnum = Integer.parseInt(request.getParameter("num"));
		// String num1=request.getParameter("num");
		// System.out.println("num1"+ num1);
		// int num=Integer.parseInt(num1);
		// System.out.println("num"+ tutoringnum);

		TutoringVO3 tvo2 = dao2.tutoringDetail(tutoringnum);
		request.setAttribute("tvo2", tvo2);

		ArrayList<IntImageVO> images = dao2.getTutoringsImage(tutoringnum);
		request.setAttribute("images", images);

		ArrayList<TutoringTimeVO2> times = dao2.getTutoringsTime(tutoringnum);
		request.setAttribute("times", times);
//		 System.out.println("times"+times);

		int timeCount = dao2.getTutoringsTimeCount(tutoringnum);
		request.setAttribute("timeCount", timeCount);
//		System.out.println("tutoringnum////////"+tutoringnum);
//		 System.out.println("timeCount////////"+timeCount);

		ArrayList<IntJobSelectVO> job = dao2.getTutoringsJob(tutoringnum);
		request.setAttribute("job", job);

		ArrayList<TutoringDayVO> days = dao2.getTutoringsDay(tutoringnum);
		request.setAttribute("days", days);

		HttpSession session = request.getSession(true);
		session.setAttribute("tutoringnum", tutoringnum);
		int tutoringLikeCount = tutee3.wishListsCount(memberId, tutoringnum);
		request.setAttribute("tutoringLikeCount", tutoringLikeCount);
		// System.out.println("tutoringLikeCount//////////"+tutoringLikeCount);
		return "tutoring/tutoringDetail.jsp";
	}

	private String tutoringCountIncrease(HttpServletRequest request, HttpServletResponse response) {
		dao2 = new TutoringDAO();
		int tutoringnum = Integer.parseInt(request.getParameter("num"));

		dao2.tutoringCountIncrease(tutoringnum); // 조회수 증가

		return "tutoring/tutoringDetail.jsp";
	}

	private String TutoringListAction(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();

		// System.out.println("///////튜터링검색시작");
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		// 검색 카테고리
		String searchCateg = request.getParameter("searchCateg");
		// System.out.println("searchCateg"+searchCateg);
		// 나이
		String all = request.getParameter("all");
		// String two = request.getParameter("two"); // 20대 이상
		// String three = request.getParameter("three"); // 30대 이상
		// String four = request.getParameter("four"); // 30대 이상

		// 성별
		String allPerson = request.getParameter("allPerson");
		// String woman = request.getParameter("woman"); // 여자

		// 경력
		String year = request.getParameter("year"); // 3년
		// String fiveYear = request.getParameter("5year"); // 5년
		// String tenYear = request.getParameter("10year"); // 10년

		// System.out.println("all"+all+"allPerson"+allPerson+"year"+year);

		// 금액
		// int price1 = Integer.parseInt(request.getParameter("price1")); // 첫번째
		// 금액
		// int price2 = Integer.parseInt(request.getParameter("price2")); // 두번째
		// 금액
		String price1 = request.getParameter("price1"); // 첫번째 금액
		// int price1 = Integer.parseInt(price11);
		String price2 = request.getParameter("price2"); // 두번째 금액
		// int price2 = Integer.parseInt(price21);
		// System.out.println("price1"+price1+"price2"+price2);
		String tutoringSearchName = request.getParameter("searchName");

		// if(searchCateg.equals("0") && all.equals(null) &&
		// allPerson.equals(null) && year.equals(null)){
		// return "tutoring/tutoringList.jsp";}else
		if (searchCateg.equals("0") && all == null && allPerson == null && year == null) {
			// System.out.println("설마여기>..");
			tutoringListNumber = dao2.selectTutoringCount(tutoringSearchName);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.selectTutoringList(tutoringSearchName, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("1")) {
			// System.out.println("튜터명으로 검색");
			tutoringListNumber = dao2.selectTutorCount(tutoringSearchName);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.selectTutorNameList(tutoringSearchName, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("남") && year.equals("1년이상")) {
			// System.out.println("1");
			tutoringListNumber = dao2.getTutoringCount1(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring1(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("남") && year.equals("3년이상")) {
			// System.out.println("2");
			tutoringListNumber = dao2.getTutoringCount2(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring2(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("남") && year.equals("5년이상")) {
			// System.out.println("3");
			tutoringListNumber = dao2.getTutoringCount3(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring3(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount4(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring4(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount5(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring5(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount6(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring6(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount7(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring7(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount8(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring8(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount9(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring9(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount10(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring10(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount11(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring11(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount12(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring12(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount13(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring13(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount14(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring14(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount15(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring15(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount16(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring16(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount17(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring17(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount18(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring18(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount19(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring19(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount20(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring20(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount21(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring21(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount22(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring22(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount23(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring23(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount24(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring24(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount25(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring25(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount26(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring26(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount27(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring27(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount28(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring28(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount29(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring29(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount30(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring30(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount31(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring31(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			// System.out.println("여기로 들어와야됨");
			tutoringListNumber = dao2.getTutoringCount32(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring32(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount33(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring33(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount34(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring34(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount35(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring35(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount36(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring36(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount48(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring48(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount49(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring49(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount50(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring50(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount51(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring51(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount52(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring52(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount53(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring53(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount54(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring54(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount55(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring55(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount56(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring56(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount57(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring57(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount58(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring58(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount59(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring59(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount60(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring60(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount61(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring61(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount62(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring62(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount63(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring63(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount64(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring64(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount65(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring65(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount66(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring66(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount67(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring67(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount68(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring68(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount69(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring69(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount70(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring70(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount71(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring71(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount72(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring72(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount73(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring73(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount74(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring74(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount75(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring75(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount76(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring76(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("20대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount77(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring77(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount78(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring78(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount79(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring79(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("30대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount80(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring80(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount81(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring81(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount82(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring82(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		} else if (all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount83(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring83(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
			HttpSession session = request.getSession(true);
			session.setAttribute("tutoringSearchName", tutoringSearchName);
			session.setAttribute("searchCateg", searchCateg);
			session.setAttribute("all", all);
			session.setAttribute("allPerson", allPerson);
			session.setAttribute("year", year);
			session.setAttribute("price1", price1);
			session.setAttribute("price2", price2);
		}

		// ArrayList<TutoringVO2> list =
		// dao2.selectTutoringList(searchName,1,9);
		// tutoringListNumber = dao2.selectTutoringCount(searchName);
		// System.out.println("////////////검색페이징////////////");
		// System.out.println("startRow"+startRow);
		//
		// System.out.println("pageNum"+pageNum);
		// System.out.println("currentPage"+currentPage);
		// System.out.println("pageSize"+pageSize);
		// System.out.println("startPage"+startPage);
		// System.out.println("pageBlock"+pageBlock);
		// System.out.println("endPage"+endPage);
		// System.out.println("pageCount"+pageCount);
		// System.out.println(list);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("searchName", tutoringSearchName);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));

		return "tutoring/tutoringListMain.jsp";

	}

	private String TutoringListSearchPaging(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;
		// String searchName = request.getParameter("searchName");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		// System.out.println("currentPage가1이 나와야함" + currentPage);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();

		HttpSession session = request.getSession();
		String tutoringSearchName = (String) session.getAttribute("tutoringSearchName");
		String searchCateg = (String) session.getAttribute("searchCateg");
		String all = (String) session.getAttribute("all");
		String allPerson = (String) session.getAttribute("allPerson");
		String year = (String) session.getAttribute("year");
		String price1 = (String) session.getAttribute("price1");
		String price2 = (String) session.getAttribute("price2");

		// HttpSession session = request.getSession(true);
		// session.setAttribute("tutoringSearchName", searchName);

		// if(searchCateg.equals("0") && all.equals(null) &&
		// allPerson.equals(null) && year.equals(null)){
		// return "tutoring/tutoringList.jsp";}else
		if (searchCateg.equals("0") && all == null && allPerson == null && year == null) {
			// System.out.println("설마여기>..");
			tutoringListNumber = dao2.selectTutoringCount(tutoringSearchName);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.selectTutoringList(tutoringSearchName, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("1")) {
			// System.out.println("튜터명으로 검색");
			tutoringListNumber = dao2.selectTutorCount(tutoringSearchName);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.selectTutorNameList(tutoringSearchName, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("남") && year.equals("1년이상")) {
			// System.out.println("1");
			tutoringListNumber = dao2.getTutoringCount1(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring1(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("남") && year.equals("3년이상")) {
			// System.out.println("2");
			tutoringListNumber = dao2.getTutoringCount2(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring2(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("남") && year.equals("5년이상")) {
			// System.out.println("3");
			tutoringListNumber = dao2.getTutoringCount3(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring3(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount4(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring4(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount5(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring5(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount6(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring6(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount7(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring7(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount8(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring8(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount9(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring9(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount10(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring10(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount11(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring11(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount12(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring12(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount13(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring13(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount14(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring14(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount15(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring15(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount16(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring16(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount17(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring17(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount18(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring18(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount19(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring19(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount20(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring20(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount21(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring21(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount22(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring22(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount23(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring23(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount24(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring24(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount25(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring25(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount26(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring26(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount27(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring27(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount28(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring28(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount29(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring29(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("20대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount30(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring30(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount31(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring31(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			// System.out.println("여기로 들어와야됨");
			tutoringListNumber = dao2.getTutoringCount32(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring32(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("30대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount33(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring33(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount34(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring34(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount35(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring35(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (searchCateg.equals("0") && all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount36(tutoringSearchName, price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring36(tutoringSearchName, price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount48(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring48(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount49(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring49(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount50(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring50(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount51(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring51(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount52(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring52(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount53(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring53(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount54(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring54(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount55(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring55(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount56(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring56(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount57(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring57(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount58(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring58(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount59(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring59(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount60(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring60(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount61(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring61(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount62(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring62(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount63(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring63(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount64(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring64(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount65(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring65(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("남") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount66(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring66(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("남") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount67(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring67(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("남") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount68(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring68(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("여") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount69(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring69(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("여") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount70(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring70(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("여") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount71(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring71(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount72(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring72(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount73(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring73(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("전체나이") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount74(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring74(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount75(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring75(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount76(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring76(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("20대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount77(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring77(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount78(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring78(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount79(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring79(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("30대") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount80(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring80(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		} else if (all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("1년이상")) {
			tutoringListNumber = dao2.getTutoringCount81(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring81(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;

		} else if (all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("3년이상")) {
			tutoringListNumber = dao2.getTutoringCount82(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring82(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		} else if (all.equals("40대이상") && allPerson.equals("전체성별") && year.equals("5년이상")) {
			tutoringListNumber = dao2.getTutoringCount83(price1, price2);
			if (tutoringListNumber == (currentPage - 1) * pageSize)
				currentPage -= 1;
			int startRow = (currentPage - 1) * pageSize + 1;
			if (tutoringListNumber > 0) {
				list = dao2.getTutoring83(price1, price2, startRow, pageSize);
				pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1);
				startPage = 1;

				if (currentPage % 5 != 0)
					startPage = (int) (currentPage / 5) * 5 + 1;
				else
					startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
				pageBlock = 5;
				endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;
			}
			if (list.isEmpty())
				tutoringListNumber = 0;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);

		request.setAttribute("tutoringSearchName", tutoringSearchName);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));

		return "tutoring/tutoringListSearch.jsp";
	}

	private String tutoringModeifyLookJobCateg(HttpServletRequest request) {
		// 수정
		dao3 = new JobSelectDAO();
		ArrayList<MiddleCategVO> mainMiddleCategNames = dao3.mainMiddleCategNames();

		request.setAttribute("mainMiddleCategNames", mainMiddleCategNames);
		// System.out.println("mainMiddleCategNames은"+mainMiddleCategNames);

		return "tutoring/tutoringModify.jsp";
	}

	// 직종
	// 1008수정
	private String tutoringModifyAction(HttpServletRequest request) throws IOException {
		dao2 = new TutoringDAO();
		// System.out.println("////////////수정 액션");
		request.setCharacterEncoding("UTF-8");
		String encType = "utf-8"; // 인코딩 정보
		int maxSize = 1 * 1024 * 1024; // 최대 업로드될 파일 크기

		MultipartRequest imageUp = null;

		String savePath = request.getServletContext().getRealPath("/assets/tutoringImg"); // 저장경로
		// System.out.println(savePath); // 저장경로 확인
		imageUp = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy()); // 파일
																											// 업로드
																											// 하는
																											// 객체
																											// 생성
		Enumeration<?> files = imageUp.getFileNames(); // input타입이 file인 파라미터를
														// 불러옴

		String image1 = imageUp.getFilesystemName("tutoringImg"); // 서버에 저장될
																	// 파일이름

		HttpSession session = request.getSession();
		int tutoringnum = (int) session.getAttribute("tutoringnum");
		String mainTitle = imageUp.getParameter("mainTitle");
		String subTitle = imageUp.getParameter("subTitle");
		String from = imageUp.getParameter("from");
		String to = imageUp.getParameter("to");

		/* 시작시간 형변환 */
		String s1Time1 = imageUp.getParameter("s1Time1");
		String[] splits = s1Time1.split(":");
		int hour = Integer.parseInt(splits[0]);
		int min = Integer.parseInt(splits[1].substring(0, 2));
		// int min=Integer.parseInt(splits[1]);
		String ampm = splits[1].substring(splits[1].length() - 2, splits[1].length());
		// System.out.printf("ampm",ampm);
		if (ampm.equals("PM")) {
			hour = hour + 12;
		}
		if (hour == 12 && ampm.equals("AM")) {
			hour = 0;
		}
		if (hour == 24 && ampm.equals("PM")) {
			hour = 12;
		}
		String startTime = String.format("%02d:%02d", hour, min);

		/* 끝나는 시간 형변환 */
		String s1Time2 = imageUp.getParameter("s1Time2");
		String[] splits2 = s1Time2.split(":");
		int hour2 = Integer.parseInt(splits2[0]);
		int min2 = Integer.parseInt(splits2[1].substring(0, 2));
		String ampm2 = splits2[1].substring(splits2[1].length() - 2, splits2[1].length());
		if (ampm2.equals("PM")) {
			hour2 = hour2 + 12;
		}
		if (hour2 == 12 && ampm2.equals("AM")) {
			hour2 = 0;
		}
		if (hour2 == 24 && ampm2.equals("PM")) {
			hour2 = 12;
		}
		String endTime = String.format("%02d:%02d", hour2, min2);

		String jobCareer = imageUp.getParameter("jobCareer");
		String tutoringMoney = imageUp.getParameter("tutoringMoney");
		String tutoringPlan = imageUp.getParameter("tutoringPlan");

		boolean result = dao2.updateTutoring(mainTitle, subTitle, from, to, jobCareer, tutoringMoney, tutoringPlan,
				null, tutoringnum);
		// System.out.println("result" + result);

		boolean result32 = dao2.deleteTutoringImage(tutoringnum);

		boolean result3 = dao2.registerTutoringImage(tutoringnum, image1);
		// System.out.println("result3" + result3);

		boolean result5 = dao2.deleteTutoringTime(tutoringnum);

		boolean result4 = dao2.registerTutoringTime(tutoringnum, startTime, endTime);
		// System.out.println("result4" + result4);
		// 두번째 시간
		/* 시작시간 형변환 */
		String s1Time3 = imageUp.getParameter("s1Time3");
		String s1Time4 = imageUp.getParameter("s1Time4");
		if (s1Time3 != null && s1Time4 != null) {
			String[] splits3 = s1Time3.split(":");
			int hour3 = Integer.parseInt(splits3[0]);
			int min3 = Integer.parseInt(splits3[1].substring(0, 2));
			String ampm3 = splits3[1].substring(splits3[1].length() - 2, splits3[1].length());
			if (ampm3.equals("PM")) {
				hour3 = hour3 + 12;
			}
			if (hour3 == 12 && ampm3.equals("AM")) {
				hour3 = 0;
			}
			if (hour3 == 24 && ampm3.equals("PM")) {
				hour3 = 12;
			}
			String startTime3 = String.format("%02d:%02d", hour3, min3);
			/* 끝나는 시간 형변환 */

			String[] splits4 = s1Time4.split(":");
			int hour4 = Integer.parseInt(splits4[0]);
			int min4 = Integer.parseInt(splits4[1].substring(0, 2));
			String ampm4 = splits4[1].substring(splits4[1].length() - 2, splits4[1].length());
			if (ampm4.equals("PM")) {
				hour4 = hour4 + 12;
			}
			if (hour4 == 12 && ampm4.equals("AM")) {
				hour4 = 0;
			}
			if (hour4 == 24 && ampm4.equals("PM")) {
				hour4 = 12;
			}
			String endTime4 = String.format("%02d:%02d", hour4, min4);

			dao2.registerTutoringTime(tutoringnum, startTime3, endTime4);
		}
		// 세번째
		/* 시작시간 형변환 */
		String s1Time5 = imageUp.getParameter("s1Time5");
		String s1Time6 = imageUp.getParameter("s1Time6");
		if (s1Time5 != null && s1Time6 != null) {
			String[] splits5 = s1Time5.split(":");
			int hour5 = Integer.parseInt(splits5[0]);
			int min5 = Integer.parseInt(splits5[1].substring(0, 2));
			String ampm5 = splits5[1].substring(splits5[1].length() - 2, splits5[1].length());
			if (ampm5.equals("PM")) {
				hour5 = hour5 + 12;
			}
			if (hour5 == 12 && ampm5.equals("AM")) {
				hour5 = 0;
			}
			if (hour5 == 24 && ampm5.equals("PM")) {
				hour5 = 12;
			}
			String startTime5 = String.format("%02d:%02d", hour5, min5);

			/* 끝나는 시간 형변환 */

			String[] splits6 = s1Time6.split(":");
			int hour6 = Integer.parseInt(splits6[0]);
			int min6 = Integer.parseInt(splits6[1].substring(0, 2));
			String ampm6 = splits6[1].substring(splits6[1].length() - 2, splits6[1].length());
			if (ampm6.equals("PM")) {
				hour6 = hour6 + 12;
			}
			if (hour6 == 12 && ampm6.equals("AM")) {
				hour6 = 0;
			}
			if (hour6 == 24 && ampm6.equals("PM")) {
				hour6 = 12;
			}
			String endTime6 = String.format("%02d:%02d", hour6, min6);

			dao2.registerTutoringTime(tutoringnum, startTime5, endTime6);
		}
		dao2.deleteTutoringJobSelect(tutoringnum);
		String[] mainSelect = imageUp.getParameterValues("mainSelect");
		// System.out.println("////////////직종선택부분////////////////////");
		// System.out.println("길이"+ mainSelect.length);
		// if(result1 == true){
		for (int i = 0; i < mainSelect.length; i++) {
			// System.out.println(mainSelect[i]+"<br/>");
			int realmainSelect = Integer.parseInt(mainSelect[i]);
			dao2.tutoringJobSelects(tutoringnum, realmainSelect);
			// System.out.println("직종이 넣어졌대!!");
		}

		dao2.deleteTutoringDay(tutoringnum);
		String daySelects = imageUp.getParameter("daySelect");
		String[] daySelectArray = daySelects.split("");
		// System.out.println(daySelectArray.length);
		for (int i = 0; i < daySelectArray.length; i++) {
			dao2.registerTutoringDay(tutoringnum, daySelectArray[i]);
			// System.out.println("확인");
		}

		// System.out.println(result);
		if (result && result3)
			// return "starters?cmd=tutoringList";
			return "starters?cmd=tutoringListDetailAction&num=" + tutoringnum;
		return "starters?cmd=tutoringModify";
	}

	private String tutoringModify(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		TutoringVO2 tutoringInfo = null;
		TutoringTimeVO tutoringTimeInfo = null;
		IntJobSelectVO tutoringJob = null;

		HttpSession session = request.getSession();
		int num = (int) session.getAttribute("tutoringnum");
		tutoringInfo = dao2.getTutoringInfo(num);
		request.setAttribute("tutoringInfo", tutoringInfo);

		tutoringTimeInfo = dao2.getTutoringTimeInfo(num);
		request.setAttribute("tutoringTimeInfo", tutoringTimeInfo);

		ArrayList<TutoringDayVO> tutoringDayInfo = dao2.getTutoringsDay(num);
		request.setAttribute("tutoringDayInfo", tutoringDayInfo);


		tutoringJob = dao2.getTutoringJobSelect(num);
		request.setAttribute("tutoringJob", tutoringJob);

		return "tutoring/tutoringModify.jsp";
	}

	private String tutoringApplyAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		int tutoringApply = 0;
		HttpSession session = request.getSession();
		// int num=Integer.parseInt(request.getParameter("num"));
		int num = (int) session.getAttribute("tutoringnum");
		tutoringApply = dao2.tutoringApply(num);
		request.setAttribute("tutoringApply", new Integer(tutoringApply));
		return "tutoring/tutoringDetail.jsp";
	}

	private String TutoringListPagingAction(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();

		int tutoringListNumber = 0;
		int pageCount = 0;
		int startPage = 0;
		int pageBlock = 0;
		int endPage = 0;

		int pageSize = 9;

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		ArrayList<TutoringVO2> list = new ArrayList<TutoringVO2>();
		tutoringListNumber = dao2.tutoringCount();
		if (tutoringListNumber == (currentPage - 1) * pageSize)
			currentPage -= 1;
		int startRow = (currentPage - 1) * pageSize + 1; // 맞음
		if (tutoringListNumber > 0) {
			list = dao2.getTutoring(startRow, pageSize);
			pageCount = tutoringListNumber / pageSize + (tutoringListNumber % pageSize == 0 ? 0 : 1); // 맞음
			startPage = 1;

			if (currentPage % 5 != 0) // 배수이면
				startPage = (int) (currentPage / 5) * 5 + 1;
			else
				startPage = ((int) (currentPage / 5) - 1) * 5 + 1;
			pageBlock = 5;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
		}
		if (list.isEmpty())
			tutoringListNumber = 0;


		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", list);
		request.setAttribute("tutoringListNumber", new Integer(tutoringListNumber));

		return "tutoring/tutoringList.jsp";
	}

	private String tutoringDeleteAction(HttpServletRequest request) {
		dao2 = new TutoringDAO();
		HttpSession session = request.getSession();
		int number = (int) session.getAttribute("tutoringnum");
		// System.out.println("number"+"/" + number);
		boolean result = dao2.deleteTutoring(number);
		// System.out.println("number"+"/" + number);
		// System.out.println("result"+result);
		// System.out.println("tutoringNumber"+number);
		if (result)
			return "starters?cmd=tutoringList";
		return "tutoring/tutoringDetail.jsp";
	}

	private String tutoringWriter(HttpServletRequest request) throws ServletException, IOException {
		dao2 = new TutoringDAO();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int num = Integer.parseInt(request.getParameter("num"));
		TutoringVO2 tvo2 = dao2.tutoringWriter(num);
		request.setAttribute("tvo3", tvo2);
		// System.out.println(tvo2);
		return "tutoring/tutoringDetail.jsp";
	}

	private String memberIdCheck3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		dao = new MemberDAO();
		String id = request.getParameter("id");
		// System.out.println("id값"+id);

		boolean result = dao.memberIdCheck(id);
		// System.out.println("결과값"+result);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (result) // 아이디 있음
			out.println("0");
		else // 아이디없음
			out.println("1");
		out.close();

		return null;
	}

	private String logoutAction(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();
		return "login/logout.jsp";
	}

	/** 비밀번호 찾기 */
	private String findPasswordAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pw = dao.findPw(id, email, name);
		request.setAttribute("pw", pw);
		if (pw == null)
			return "login/findpw.jsp";
		return "login/findpwAfter.jsp";

	}

	/** 아이디찾기 관련된 DAO 메소드를 호출하고 결과 페이지 전달 */
	private String findIdAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = dao.findId(name, email);
		request.setAttribute("id", id);
		if (id == null)
			return "login/findId.jsp";
		return "login/findIdAfter.jsp";

	}

	/** 튜터 회원 리스트 */
	private String tutorMembers(HttpServletRequest request) {
		dao = new MemberDAO();
		ArrayList<MemberVO> getMemberTenList = dao.getMemberTen(1, 9);
		request.setAttribute("getMemberTenList", getMemberTenList);
		return "tutorLists.jsp";
	}

	/** 튜티 회원 리스트 */
	private String tuteeMembers(HttpServletRequest request) {
		dao = new MemberDAO();
		ArrayList<MemberVO> tuteeList = dao.getTuteeLists(1, 9);
		request.setAttribute("tuteeList", tuteeList);
		return "tuteeLists.jsp";
	}

	/** 로그인과 관련된 DAO메서드를 호출하고 결과 페이지 전달 */
	private String loginAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		dao = new MemberDAO();
		// id, pw는 name을 지칭한다.
		// login.jsp에서 사용
		String id = request.getParameter("userID");
		String pw = request.getParameter("userPasswd");
		// System.out.println("id"+id+"pw"+pw);
		String name = dao.getLogin(id, pw);
		int members = dao.memberCateg2(id);

		int allMembers = 0;
		allMembers = dao.memberCateg4(id);

		if (members == 1) { // 튜티
			HttpSession session = request.getSession(true);
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("membersCateg", members);
			request.setAttribute("allMembers", new Integer(allMembers));
		} else if (members == 3) { // 기업
			HttpSession session = request.getSession(true);
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("membersCateg", members);
			request.setAttribute("allMembers", new Integer(allMembers));
		} else { // 튜터
			HttpSession session = request.getSession(true);
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("membersCateg", members);
			request.setAttribute("allMembers", new Integer(allMembers));
		}
		// HttpSession session = request.getSession(true);
		// session.setAttribute("membersCateg", members);

		// request.setAttribute("membersCateg", members);
		// System.out.println("membersCateg : " + members);

		return "login/loginAction.jsp";
		// }

	}

	/** 튜티회원탈퇴 */
	private String deleteTuteeMemberAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		// System.out.println("탈퇴시작");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		// System.out.println("아이디"+memberId);
		String passwd = request.getParameter("passwd");
		int result = dao.deleteMember(memberId, passwd);
		request.setAttribute("result", result);
		// System.out.println("비번"+passwd+"/"+result);
		if (result == 1) {
			session.invalidate(); // 삭제했다면 세션정보를 삭제한다.
		}
		return "InfoDetailResult.jsp";
	}

	private String deleteTutorMemberAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		// System.out.println("탈퇴시작");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		// System.out.println("아이디"+memberId);
		String passwd = request.getParameter("passwd");
		int result = dao.deleteMember(memberId, passwd);
		// System.out.println("비번"+passwd);
		request.setAttribute("result", result);
		// System.out.println("비번"+passwd+"/"+result);
		if (result == 1) {
			session.invalidate(); // 삭제했다면 세션정보를 삭제한다.
		}
		return "InfoDetailResult.jsp";
	}

	private String deleteCompanyMemberAction(HttpServletRequest request) throws ServletException, IOException {
		dao = new MemberDAO();
		// System.out.println("탈퇴시작");
		HttpSession session = request.getSession();
		String companyId = (String) session.getAttribute("id");
		// System.out.println("아이디"+companyId);
		String passwd = request.getParameter("passwd");
		int result = dao.deleteCompany(companyId, passwd);
		request.setAttribute("result", result);
		// System.out.println("비번"+passwd+"/"+result);
		if (result == 1) {
			session.invalidate(); // 삭제했다면 세션정보를 삭제한다.
		}
		return "InfoDetailResult.jsp";
	}


}

