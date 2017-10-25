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
			
		case 8: // 공지사항 내용확인
			mav = tclearningNoticeCTX((BoardBean)object);
			break;
		
		case 9: // 공지사항 내용확인
			mav = tclearningNoticeCTX((BoardBean)object);
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
			mav.addObject("content", tclearningNoticeList(board,ar));

			
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNotice");
			setTransactionResult(transaction);
		}
		return mav;
	}
	
	private String tclearningNoticeList(BoardBean board, ArrayList<BoardBean> ar) { // 공지사항 리스트
		StringBuffer sb = new StringBuffer();

		int count = 0;
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
		sb.append("<input type=\"button\" value=\"글쓰기\" onClick=\"noticeInsert()\"/>");
		
		return sb.toString();
	}
	
	
	private ModelAndView tclearningNoticeCTX(BoardBean board) { // 공지사항 내용확인
	    
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		 
		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			
			board = dao.tclearningNoticeConfirm(board);
		
			System.out.println("공지사항 내용확인 메서드 진입"+board.getBoardTitle()+board.getRoomCode());
		    mav.addObject("content", getTclearningNoticeCTX(board));
		   
			
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeCXT");
			setTransactionResult(transaction);
		}
		return mav;
	}
	
	private String getTclearningNoticeCTX(BoardBean board) { // 공지사항 내용 끌고오기
		
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
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('3')\"/>");
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
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		StringBuffer sb = new StringBuffer();
		boolean transaction = false;
		String page = null;
		
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board = new BoardBean();
			board.setRoomCode((String)session.getAttribute("roomCode"));

			boardList = dao.learningWANListGet(board);

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
				sb.append("<input type='button' value='선생님 코멘트' onClick='test("+boardList.get(i).getBoardCode()+")' />");
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
	

	
	
	private ModelAndView datahousemain(BoardBean board) { // 오답노트 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
						
			session.getAttribute("roomCode");

		if(dao.referenceInsert(board) == 1) {
			
		}else {
			
		}

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}
	

}
