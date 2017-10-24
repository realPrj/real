package icia.project.services;

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
			mav = learningWANPage(((BoardBean)object));
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
	
	private ModelAndView learningWANPage(BoardBean board) { // 오답노트 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
						
			session.getAttribute("roomCode");

			

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

		//	if(dao.referenceInsert =1)

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}
	
}
