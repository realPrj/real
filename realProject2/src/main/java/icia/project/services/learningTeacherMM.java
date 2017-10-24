package icia.project.services;

import java.util.ArrayList;

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
			mav = learningNoticePage(((BoardBean)object));
			break;

		case 4:	

			break;

		case 5:

			break;

		case 6:	

			break;

		case 7:	// 오답노트
			mav = learningWANPage();
			break;
		case 12:	// 자료실
			mav = datahousemain(((BoardBean)object));
			break;

		}

		return mav;

	}
	

	private ModelAndView learningNoticePage(BoardBean board) { // 공지사항 페이지

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNotice");
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
				sb.append(i);
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getYearName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getTypeName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getNumberCode());
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

		if(dao.referenceInsert != 0) {
			
		}
		System.out.println("test");
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}
	
}
