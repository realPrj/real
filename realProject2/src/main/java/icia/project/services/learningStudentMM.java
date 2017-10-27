package icia.project.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.BoardBean;
import icia.project.bean.DbBoardBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;

@Service
public class learningStudentMM extends TransactionExe {


	@Autowired
	private IMybatis dao;
	@Autowired
	private ProjectUtils session;

	private ModelAndView mav;

	public ModelAndView entrance(int serviceCode,Object ...object)  {

		switch(serviceCode) {

		case 1:	

			break;

		case 2:

			break;

		case 3:	//공지사항 페이지
			mav = stlearningNoticeList((BoardBean)object[0]);
			break;

		case 4:	//질문게시판 페이지

			break;

		case 5:

			break;

		case 6:	

			break;

		case 7:	// 오답노트
			mav = learningWANPage();
			break;

		case 8: // 공지사항 내용확인
			mav = stlearningNoticeCTX((BoardBean)object[0]);
			break;

		case 9: // 공지사항 글쓰기 페이지 이동

			break;

		case 10: // 공지사항 글쓰기

			break;

		case 11: // 공지사항 수정 페이지

			break;

		case 12:	// 자료실 글쓰기

			break;

		case 13:	// 자료실

			break;

		case 14:	

			break;


		case 15:	

			break;

		case 16:	

			break;

		case 17:	// 오답노트 코멘트 페이지

			break;

		case 18:	// 오답노트 코멘트 등록 페이지


			break;
		case 19:	// 오답노트 코멘트 수정 페이지

			break;
		case 20:	// 오답노트 코멘트 수정

			break;
		case 21:	// 오답노트 코멘트 삭제

			break;



		}

		return mav;

	}


	private ModelAndView learningWANPage() { // 오답노트 페이지

		mav = new ModelAndView();
		BoardBean board;
		ArrayList<BoardBean> boardList = null;
		ArrayList<BoardBean> typeSum = null;
		ArrayList<BoardBean> yearCode = null;
		StringBuffer sb;
		StringBuffer sum;
		boolean transaction = false;
		String page = null;
		double questionCount = 0;
		double peopleCount = 0;
		double value1 = 0;
		double value2 = 0;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board = new BoardBean();
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));
			board.setStudentName(dao.stNameGet(board));	// 학생 이름 추출
			board.setAllSum(dao.allWANSum(board));		// 학생이 물어본 총 문제 수

			mav.addObject("studentName", board.getStudentName());
			mav.addObject("stHalf", board.getStudentCode().substring(2, 4));
			mav.addObject("stNumber", board.getStudentCode().substring(4, 6));
			mav.addObject("allSum", board.getAllSum());

			/////////////////////////////////////////////////////////////////////////

			boardList = dao.learningWANstListGet(board);
			yearCode = dao.learningWANstYearCodeOneGet(board);

			sb = new StringBuffer();
			sum = new StringBuffer();
			sb.append("<select id = 'yearSelect'>");
			sb.append("<option></option>");

			for(int i =0; i < yearCode.size(); i++) {
				board = new BoardBean();
				board.setRoomCode(boardList.get(0).getRoomCode());
				board.setYearCode(yearCode.get(i).getYearCode());
				board.setStudentCode((String)session.getAttribute("stCode"));
				sb.append("<option>"+yearCode.get(i).getYearCode()+"</option>");

				typeSum = dao.learningWANstTypeSum(board);

				sum.append("<br><biv id='"+yearCode.get(i).getYearCode().substring(0, 4)+"' >");

				for(int y = 0; y < typeSum.size(); y++) {
					board = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setYearCode(yearCode.get(i).getYearCode());
					board.setRoomSB(dao.learningSBCodeGet(board));
					board.setTypeCode(typeSum.get(y).getTypeCode());
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setTypeSum(typeSum.get(y).getTypeSum());	
					sum.append(board.getTypeName()+" : "+ board.getTypeSum()+"<br>");

				}
				sum.append("</biv>");

				if(i == 0) {
					mav.addObject("lowest", yearCode.get(i).getYearCode());
				}
			}

			sb.append("</select>");
			mav.addObject("size", yearCode.size());
			mav.addObject("yearSelect", sb.toString());
			mav.addObject("typeSumb", sum.toString());

			/////////////////////////////////////////////////////////////////////////

			sb = new StringBuffer();
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("게시글 번호");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("년도");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("문제유형");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("문제 번호");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("선생님 코멘트");
			sb.append("</td>");
			sb.append("</tr>");

			for(int i = 0; i < boardList.size(); i++ ) {

				board = new BoardBean();
				board.setRoomCode(boardList.get(0).getRoomCode());
				board.setYearCode(boardList.get(i).getYearCode());
				board.setTypeCode(boardList.get(i).getTypeCode());
				board.setRoomSB(dao.learningSBCodeGet(board));
				board.setYearName(dao.learningYearNameGet(board));
				board.setTypeName(dao.learningTypeNameGet(board));

				sb.append("<tr>");
				sb.append("<td>");
				sb.append(i+1);
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getYearName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getTypeName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(boardList.get(i).getNumberCode());
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input type='button' value='선생님 코멘트' onClick='commentCheck("+boardList.get(i).getBoardCode()+")' />");
				sb.append("</td>");
				sb.append("</tr>");
			}

			sb.append("</table>");

			mav.addObject("content", sb.toString());

			////////////////////////////////////////////////////////////////////////
			
			board = new BoardBean();

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));
			
			boardList = dao.learningWANstListGetOverlap(board);
			yearCode = dao.learningWANstYearCodeOneGet(board);
			
			sb = new StringBuffer();
			
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("모의고사 기출년월");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("문제유형");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("문제 번호");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("전국 평균 질문률");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("학습방 평균 질문률");
			sb.append("</td>");
			sb.append("</tr>");

			for(int i =0; i < boardList.size(); i++) {
				board = new BoardBean();
				board.setRoomCode(boardList.get(0).getRoomCode());
				board.setRoomSB(boardList.get(0).getRoomSB());
				board.setTypeCode(boardList.get(i).getTypeCode());
				board.setNumberCode(boardList.get(i).getNumberCode());
				board.setYearCode(boardList.get(i).getYearCode());
				board.setStudentCode((String)session.getAttribute("stCode"));
				board.setStudentCode(board.getStudentCode().substring(0, 2)+"%");
				board.setTypeName(dao.learningTypeNameGet(board));
				board.setYearName(dao.learningYearNameGet(board));
				
				questionCount = dao.learningWANstAverage1(board);	// 학년 총 질문수				
				peopleCount = dao.learningWANstAverage2(board);	// 학년 총 인원수

				value1 = (questionCount / peopleCount) * 100;

				questionCount = dao.learningWANstAverage3(board);	// 학습방 학년 총 질문수
				peopleCount = dao.learningWANstAverage4(board);	// 학습방 학년 총 인원수
				
				value2 = (questionCount / peopleCount) * 100;
			
				sb.append("<tr>");
				
				sb.append("<td>");
				sb.append(board.getYearName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getTypeName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getNumberCode());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(value1+"%");
				sb.append("</td>");
				sb.append("<td>");
				sb.append(value2+"%");
				sb.append("</td>");	
				sb.append("</tr>");
				
			}
			
			sb.append("</table>");

			mav.addObject("average", sb.toString());


			////////////////////////////////////////////////////////////////////////

			page = "learningStuentWAN";
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}
	
	private ModelAndView stlearningNoticeList(BoardBean board) { // 공지사항 리스트
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			ar = dao.tclearningNoticeList(board);
			mav.addObject("content", stlearningNoticeList(board,ar));


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNotice");
			setTransactionResult(transaction);
		}
		return mav;
	}
	
	private String stlearningNoticeList(BoardBean board, ArrayList<BoardBean> ar) { // 공지사항 리스트
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>제목</td>");
		sb.append("<td>날짜</td>");
		sb.append("<td>작성자</td>");
		sb.append("</tr>");
		for(int i=0; i<ar.size(); i++) {
			sb.append("<tr>");	
			//sb.append("<input type=\"hidden\" name=\"boardTitle\" value='" + board.getBoardTitle() + "'/>");
			sb.append("<td onClick=\"confirm('"+ ar.get(i).getBoardTitle() +"','" + ar.get(i).getBoardDate() + "')\">" + ar.get(i).getBoardTitle() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardDate() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardId() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");

		return sb.toString();
	}
	
	private ModelAndView stlearningNoticeCTX(BoardBean board) { // 공지사항 내용확인

		mav = new ModelAndView();
		boolean transaction = false;
		DbBoardBean bb = new DbBoardBean();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));
			board = dao.tclearningNoticeConfirm(board);

			System.out.println("공지사항 내용확인 메서드 진입"+board.getBoardTitle()+board.getRoomCode());
			mav.addObject("content", getStlearningNoticeCTX(board));


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeCXT");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getStlearningNoticeCTX(BoardBean board) { // 공지사항 내용 끌고오기

		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardTitle() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardDate() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardId() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardContent() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardRoute() + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('3','"+ board.getId() +"')\"/>");
		return sb.toString();
	}



}
