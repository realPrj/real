package icia.project.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import icia.project.bean.BoardBean;
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
			mav = studentMainPage();
			break;

		case 3:	// 선생님 학습방 메인 페이지
			mav = teacherLearningMainPage(((LearningRoomBean)object));
			break;

		case 4:	//  학생 학습방 메인 페이지
			mav = studentLearningMainPage(((LearningRoomBean)object));
			break;

		case 5:	// 선생님 나의 정보 페이지
			mav = teacherInfoPage();

			break;

		case 6:	// 학생 나의 정보 페이지
			mav = studentInfoPage();
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

	private ModelAndView studentMainPage() {	// 학생 메인 페이지
		
		mav = new ModelAndView();
		LearningRoomBean room;
		ArrayList<LearningRoomBean> alCode = new ArrayList<LearningRoomBean>();
		ArrayList<LearningRoomBean> attendanceInCode = new ArrayList<LearningRoomBean>();
		ArrayList<LearningRoomBean> attendanceOutCode = new ArrayList<LearningRoomBean>();
		StringBuffer sb = new StringBuffer();
		boolean transaction = false;
		int isNotAttendance = 0;
		String time = null;
		int tardy = 0; // 지각
		int early = 0; // 조퇴

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			room = new LearningRoomBean();
			room.setStudentCode((String)session.getAttribute("stCode"));
			
			if(dao.stlearningRoomCheck(room) != 0) {	// 학습방 추출

				alCode = dao.stlearningRoomGet1(room);

				sb.append("<table>");
				for(int i =0; i < alCode.size(); i++) {
					
					room = new LearningRoomBean();
					room.setRoomCode(alCode.get(i).getRoomCode());
					room = dao.stlearningRoomGet2(room);
					
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<input type='button' value='"+room.getRoomName()+"' onClick=learningGo('"+room.getRoomCode()+"') />");
					sb.append("</td>");
					sb.append("</tr>");
					
				}
				sb.append("</table>");
				mav.addObject("content", sb.toString());
				mav.setViewName("studentMain");
				transaction = true;

			}else {
				mav.setViewName("studentMain");
				transaction = true;
			}
			
			sb = new StringBuffer();
			room = new LearningRoomBean();
			room.setStudentCode((String)session.getAttribute("stCode"));		
			room.setYyyymm(dao.yyyyMMGet());
			room.setAttendanceType("1");	
			attendanceInCode = dao.stIOHGet(room);
			room.setAttendanceType("2");
			attendanceOutCode = dao.stIOHGet(room);
			
			sb.append("<div id='attendance'>");
			sb.append("<h3>"+room.getYyyymm().substring(4)+"월</h3>");
			for(int i = 0; i < attendanceInCode.size(); i++) {
				time = "083000";
				isNotAttendance = Integer.parseInt(attendanceInCode.get(i).getAttendanceCode().substring(8)) - Integer.parseInt(time);
				
				if(isNotAttendance <= 0) {	// 정상출근
					time = "170000";
					isNotAttendance = Integer.parseInt(attendanceOutCode.get(i).getAttendanceCode().substring(8)) - Integer.parseInt(time);
					
					if(isNotAttendance >= 0) {	// 정상 퇴근	
						
						sb.append(attendanceInCode.get(i).getAttendanceCode().substring(0,8)+" : "+" 정상출결 <br>");

					}else {	// 조퇴
						
						sb.append(attendanceInCode.get(i).getAttendanceCode().substring(0,8)+" : "+" 조퇴 <br>");						
						early++;
					}
					
				}else {	// 지각
					
					sb.append(attendanceInCode.get(i).getAttendanceCode().substring(0,8)+" : "+" 지각 <br>");					
					tardy++;
					
				}
				
			}
			sb.append("이번달 지각 : "+ tardy+"<br>");
			sb.append("이번달 조퇴 : "+ early+"<br>");
			sb.append("<input type='button' value='접어두기' onClick='fold()' >");
			sb.append("</div>");
			mav.addObject("attendance",sb.toString());
			
		}catch(Exception ex) {

		}finally {

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
			sb.append("<input type=\"text\" name='id' value='"+member.getId()+"' readonly>");
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

	private ModelAndView studentInfoPage() {	// 학생 나의정보 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		MemberBean member = new MemberBean();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {	
			member.setStudentCode((String)session.getAttribute("stCode"));

			member = dao.stInformationGet(member);

			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("아이디");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type=\"text\" name='id' value='"+member.getId()+"' readonly>");
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
			mav.setViewName("studentInfo");
			setTransactionResult(transaction);
		}

		return mav;
	}	

	private ModelAndView teacherLearningMainPage(LearningRoomBean room) {	// 선생님 학습방 메인 페이지

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			room = dao.learningRoomGo(room);

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
	
	private ModelAndView studentLearningMainPage(LearningRoomBean room) {	// 학생 학습방 메인 페이지

		mav = new ModelAndView();
		BoardBean board=new BoardBean();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
		
			mav.addObject("code",room.getRoomCode());
			
			
		
			
			room = dao.learningRoomGo(room);
			
			session.setAttribute("roomCode", room.getRoomCode());
			board.setRoomCode(room.getRoomCode());
			
			mav.addObject("content",room.getRoomIntroduction());

			transaction = true;


		}catch(Exception ex) {

		}finally {
			mav.setViewName("studentLearningMain");
			setTransactionResult(transaction);
		}

		return mav;
	}


	public ModelAndView subjectCode(BoardBean board) {	// 코드 보여주기

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
		


			transaction = true;


		}catch(Exception ex) {

		}finally {
			mav.setViewName("learningSubjectCode");
			setTransactionResult(transaction);
		}
		
		return null;

	}
	

}
