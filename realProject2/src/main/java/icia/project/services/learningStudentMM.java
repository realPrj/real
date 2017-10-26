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
			
		case 30:	// 선생님 공지사항 수정

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
				System.out.println(yearCode.get(i).getYearCode());
				System.out.println(boardList.get(0).getRoomCode());
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

			}

			sb.append("</select>");
			mav.addObject("size", yearCode.size());
			mav.addObject("yearSelect", sb.toString());
			mav.addObject("typeSumb", sum.toString());
			
			/////////////////////////////////////////////////////////////////////////
			
			mav.addObject("content", "");
			
			page = "learningStuentWAN";
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}



}
