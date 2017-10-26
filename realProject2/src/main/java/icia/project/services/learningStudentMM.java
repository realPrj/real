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
			


		}

		return mav;

	}
	
	
	private ModelAndView learningWANPage() { // 오답노트 페이지

		mav = new ModelAndView();
		BoardBean board;
		ArrayList<BoardBean> boardList = null;
		StringBuffer sb;
		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board = new BoardBean();
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("tcId"));
			
			
			
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
