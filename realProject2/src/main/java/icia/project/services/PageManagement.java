package icia.project.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.LearningRoomBean;
import icia.project.bean.MemberBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;

@Service
public class PageManagement extends TransactionExe {
	
	@Autowired
	private IMybatis dao;
	@Autowired
	private ProjectUtils session;
	
	private ModelAndView mav;
	
	public ModelAndView entrance(int serviceCode,Object object)  {

	
		switch(serviceCode) {

		case 1:	// 선생님 메인 페이지
			mav = teacherMainPage();
			break;

		case 2: // 학생 메인 페이지
			
			break;

		case 3:	// 선생님 학습방 메인 페이지
			mav = teacherLearningMainPage(((LearningRoomBean)object));
			break;

		case 4:	// 학생 학습방 메인 페이지
			
			break;

		case 5:	// 선생님 나의 정보 페이지
			mav = teacherInfoPage();
			break;

		case 6:	// 학생 나의 정보 페이지
			
			break;


		}

		return mav;

	}
	
	private ModelAndView teacherMainPage() {	// 선생님 메인 페이지
		
		mav = new ModelAndView();
		LearningRoomBean room;
		ArrayList<LearningRoomBean> al = new ArrayList<LearningRoomBean>();
		StringBuffer sb = new StringBuffer();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		
		try {
			room = new LearningRoomBean();
			room.setId((String)session.getAttribute("tcId"));
			if(dao.tclearningRoomCheck(room) != 0) {
				
				al = dao.tclearningRoomGet(room);
				
				sb.append("<table>");
				for(int i =0; i < al.size(); i++) {
					
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<input type='button' value='"+al.get(i).getRoomName()+"' onClick=learningGo('"+al.get(i).getRoomCode()+"') />");
					sb.append("</td>");
					sb.append("</tr>");
					
				}
				sb.append("</table>");
				mav.addObject("content", sb.toString());
				mav.setViewName("teacherMain");
				transaction = true;
				
			}else {
				mav.setViewName("teacherMain");
				transaction = true;
			}
			
		}catch(Exception ex) {
			
		}finally {
			
			setTransactionResult(transaction);
		}
		
		return mav;
	}
	
	
	private ModelAndView teacherLearningMainPage(LearningRoomBean room) {	// 선생님 학습방 메인 페이지
		
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		
		try {
			
				room = dao.telearningRoomGo(room);
	
				session.setAttribute("roomCode", room.getRoomCode());
				
				mav.addObject("content",room.getRoomIntroduction());
			
				transaction = true;
				
			
		}catch(Exception ex) {
			
		}finally {
			mav.setViewName("teacherLearningMain");
			setTransactionResult(transaction);
		}
		
		return mav;
	}
	
	private ModelAndView teacherInfoPage() {	// 선생님 나의정보 페이지
		
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		MemberBean member = new MemberBean();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		
		try {	
				member.setId((String)session.getAttribute("tcId"));
			
				member = dao.tcInformationGet(member);
				
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("아이디");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input type=\"text\" value='"+member.getId()+"' readonly>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("이름");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input type=\"text\" name='name' value='"+member.getName()+"' readonly>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("이메일");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input type=\"text\" name='email' value='"+member.getEmail()+"' readonly>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("핸드폰");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input type=\"text\" name='phone' value='"+member.getPhone()+"' readonly>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				mav.addObject("content", sb.toString());
			
				transaction = true;
				
			
		}catch(Exception ex) {
			
		}finally {
			mav.setViewName("teacherInfo");
			setTransactionResult(transaction);
		}
		
		return mav;
	}	
	

}
