package icia.project.services;

import java.util.ArrayList;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.BoardBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;

@Service
public class learningTeacherMM extends TransactionExe {

	@Autowired
	private IMybatis dao;
	@Autowired
	private ProjectUtils session;

	private ModelAndView mav;


	public ModelAndView entrance(int serviceCode,Object object)  {

		switch(serviceCode) {

		case 1:	

			break;

		case 2:

			break;

		case 3:	//공지사항 페이지
			mav = learningNoticePage((BoardBean)object);
			break;

		case 4:	//질문게시판 페이지
			mav = learningQuestion((BoardBean)object);
			break;

		case 5:

			break;

		case 6:	

			break;

		case 7:	// 오답노트
			mav = learningWANPage();
			break;

		case 10:	// 자료실
			mav = datahousemain((BoardBean)object);
			break;
			
		case 13:	// 자료실
			mav = dataview((BoardBean)object);
			break;
			
			
			
			
			
			
			
			
			
			
		case 17:	// 오답노트 인설트 페이지
			mav = learningWANInsertPage((BoardBean)object);
			break;

		}

		return mav;

	}


	private ModelAndView learningNoticePage(BoardBean board) { // 공지사항 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			ar = dao.tclearningNoticeList(board);
			mav.addObject("content", tclearningNoticeList(ar));


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNotice");
			setTransactionResult(transaction);
		}
		return mav;
	}
	private String tclearningNoticeList(ArrayList<BoardBean> ar) { // 공지사항 리스트
		StringBuffer sb = new StringBuffer();

		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>제목</td>");
		sb.append("<td>날짜</td>");
		sb.append("<td>작성자</td>");
		sb.append("</tr>");
		for(int i=0; i<ar.size(); i++) {
			sb.append("<tr>");
			sb.append("<td>" + ar.get(i).getBoardTitle() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardDate() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardId() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");

		return sb.toString();
	}

	private ModelAndView learningQuestion(BoardBean board) { // 질문게시판 페이지
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			mav.addObject("content",session.getAttribute("roomCode") + "의 질문게시판");

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningQuestion");
			setTransactionResult(transaction);
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

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board = new BoardBean();
			board.setRoomCode((String)session.getAttribute("roomCode"));

			boardList = dao.learningWANListGet(board);
			
			yearCode = dao.learningWANYearCodeOneGet(board);
			
			sb = new StringBuffer();
			sum = new StringBuffer();
			sb.append("<select id = 'yearSelect'>");
			sb.append("<option></option>");
			
			for(int i =0; i < yearCode.size(); i++) {
				board = new BoardBean();
				board.setRoomCode(boardList.get(0).getRoomCode());
				board.setYearCode(yearCode.get(i).getYearCode());

				sb.append("<option>"+yearCode.get(i).getYearCode()+"</option>");
				
				typeSum = dao.learningWANTypeSum(board);
				
				sum.append("<br><biv id='"+yearCode.get(i).getYearCode().substring(0, 4)+"'>");
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
				
			}

			sb.append("</select>");
			mav.addObject("size", yearCode.size());
			mav.addObject("yearSelect", sb.toString());
			mav.addObject("typeSumb", sum.toString());
			
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
			page = "learningWAN";
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView datahousemain(BoardBean board) { // 자료실 인설트

		mav = new ModelAndView();
		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {

			session.getAttribute("roomCode");


			if(dao.referenceInsert(board) != 0) {
				System.out.println("나 성공햇다 ");
				page = "learningData";
				transaction = true;
			}else {
				System.out.println("실패");
			}



		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}
	
	private ModelAndView dataview(BoardBean board) { // 공지사항 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> bb = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setId((String)session.getAttribute("tcId"));
			board.setRoomCode((String)session.getAttribute("roomCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			bb = dao.datalist(board);
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>제목</td>");
			sb.append("<td>날짜</td>");
			sb.append("<td>아이디</td>");
			sb.append("</tr>");
			for(int i=0; i<bb.size(); i++) {
				sb.append("<tr>");
				sb.append("<td>" + bb.get(i).getBoardTitle() + "</td>");
				sb.append("<td>" + bb.get(i).getBoardDate() + "</td>");
				sb.append("<td>" + bb.get(i).getBoardId() + "</td>");
				sb.append("</tr>");

			}
			sb.append("</table>");

			mav.addObject("datalist", sb.toString());
			mav.setViewName("learningData");

			transaction = true;

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningWANInsertPage(BoardBean board) { // 오답노트 코멘트 페이지 이동

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> bb = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			
			board.setRoomCode((String)session.getAttribute("roomCode"));
			
			if(dao.learningWANCommentCheck(board) != 0) {	// 코멘트 있음
				
				
			}else {	// 코멘트 없음
				
				sb.append("<input type='button' value='코멘트 등록' onClick='commentInsert'  />");
				
			}
			
			transaction = true;

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		return mav;
	}




}
