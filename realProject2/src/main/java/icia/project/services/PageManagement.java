package icia.project.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import icia.project.bean.BoardBean;
import icia.project.bean.Calendar;
import icia.project.bean.DbBoardBean;
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
			
		case 7:	// 학습방 코드표
			mav = subjectCCT(((BoardBean)object));
			break;		
			
		case 8:	// 학습방 코드표
			mav = subjectCCTSTUDENT(((BoardBean)object));
			break;	

		case 9:	// 선생님 다시 메인
			mav = hometeacherLearningMainPage(((LearningRoomBean)object));
			break;	
			
		case 10:	// 학생 다시 메인
			mav = homestudentLearningMainPage(((LearningRoomBean)object));
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

				sb.append("</br><table>");
				sb.append("<tr>");
				for(int i =0; i < al.size(); i++) {

					sb.append("<td>");
					sb.append("<input type='button' class='btn' value='"+al.get(i).getRoomName()+"' onClick=learningGo('"+al.get(i).getRoomCode()+"') />");
					sb.append("</td>");

				}
				sb.append("</tr>");
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
			session.removeAttribute("roomCode");
			room = new LearningRoomBean();
			room.setStudentCode((String)session.getAttribute("stCode"));

			if(dao.stlearningRoomCheck(room) != 0) {	// 학습방 추출

				alCode = dao.stlearningRoomGet1(room);

				sb.append("</br><table>");
				sb.append("<tr>");
				for(int i =0; i < alCode.size(); i++) {

					room = new LearningRoomBean();
					room.setRoomCode(alCode.get(i).getRoomCode());
					room = dao.stlearningRoomGet2(room);


					sb.append("<td>");
					sb.append("<input type='button' class='btn' value='"+room.getRoomName()+"' onClick=learningGo('"+room.getRoomCode()+"') />");
					sb.append("</td>");


				}
				sb.append("</tr>");
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
			sb.append("<input type='button'  class='btn' value='접어두기' onClick='fold()' >");
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
		ArrayList<LearningRoomBean> ar = null;
		LearningRoomBean room = new  LearningRoomBean();
		StringBuffer sb = new StringBuffer();
		MemberBean member = new MemberBean();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {	
			member.setId((String)session.getAttribute("tcId"));

			member = dao.tcInformationGet(member);

			sb.append("<table class=\"table table-hover\">");
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
			
			sb = new StringBuffer();
			room.setId((String)session.getAttribute("tcId"));

			ar = dao.tcattendanceGet(room);

			sb.append("<table class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("날짜");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("로그인/로그아웃");
			sb.append("</td>");
			sb.append("</tr>");
			// 페이지
			int forI = 0; // 크게 한사람
			int forB = 0;	// 내용물
			int pageCount = 15; // 

			double sizeDouble = ar.size() / (double)pageCount;

			for(forI=0; forI < sizeDouble; forI++) {

				if(ar.size()< pageCount) {
					pageCount= ar.size();
				}

				sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

				for(forB=forB; forB<pageCount; forB++) {
					String check =((Integer.parseInt(ar.get(forB).getAttendanceType()) == 1)? "로그인" : "로그아웃");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append(ar.get(forB).getAttendDate());
					sb.append("</td>");
					sb.append("<td>");
					sb.append(check);
					sb.append("</td>");
					sb.append("</tr>");
				}
				sb.append("</tbody>");	

				pageCount+=pageCount;


			}

			sb.append("</table>");
			mav.addObject("attendance", sb.toString());
			sb = new StringBuffer();

			sb.append("<div class='text-center'>");
			sb.append("<ul class='pagination'>");


			for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

				sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");			
			}
			sb.append("</ul>");
			sb.append("</div>");

			mav.addObject("content2", sb.toString());
			mav.setViewName("teacherInfo");
		}catch(Exception ex) {
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}	
	private ModelAndView studentInfoPage() {	// 학생 나의정보 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		LearningRoomBean room = new  LearningRoomBean();
		MemberBean member = new MemberBean();
		ArrayList<LearningRoomBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {	
			member.setStudentCode((String)session.getAttribute("stCode"));

			member = dao.stInformationGet(member);

			sb.append("<table class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("아이디");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type=\"text\" name='id' class=\"box\" value='"+member.getId()+"' readonly>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("이름");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type=\"text\" name='name' class=\"box\" value='"+member.getName()+"' readonly>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("이메일");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type=\"text\" name='email' class=\"box\" value='"+member.getEmail()+"' readonly>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("핸드폰");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type=\"text\" name='phone' class=\"box\" value='"+member.getPhone()+"' readonly>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("학년/반/번호");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<input type=\"text\" name='studentCode' class=\"box\" value='"+member.getStudentCode()+"' readonly>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			mav.addObject("content", sb.toString());

			sb = new StringBuffer();
				
			room.setStudentCode(member.getStudentCode());

			ar = dao.attendanceGet(room);

			sb.append("<table class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("날짜");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("로그인/로그아웃");
			sb.append("</td>");
			sb.append("</tr>");
			// 페이지
			int forI = 0; // 크게 한사람
			int forB = 0;	// 내용물
			int pageCount = 15; // 

			double sizeDouble = ar.size() / (double)pageCount;

			for(forI=0; forI < sizeDouble; forI++) {

				if(ar.size()< pageCount) {
					pageCount= ar.size();
				}

				sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

				for(forB=forB; forB<pageCount; forB++) {
					String check =((Integer.parseInt(ar.get(forB).getAttendanceType()) == 0)? "로그인" : "로그아웃");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append(ar.get(forB).getAttendDate());
					sb.append("</td>");
					sb.append("<td>");
					sb.append(check);
					sb.append("</td>");
					sb.append("</tr>");
				}
				sb.append("</tbody>");	

				pageCount+=pageCount;


			}

			sb.append("</table>");
			mav.addObject("attendance", sb.toString());
			sb = new StringBuffer();

			sb.append("<div class='text-center'>");
			sb.append("<ul class='pagination'>");


			for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

				sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");			
			}
			sb.append("</ul>");
			sb.append("</div>");


			mav.addObject("content2", sb.toString());

		}catch(Exception ex) {
			ex.printStackTrace();
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


	private ModelAndView subjectCCT(BoardBean board) {	// 코드 보여주기

		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> al = null;
		StringBuffer sb=null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setRoomSB(dao.learningSBCodeGet(board));	// 과목 코드
			String sbCode = board.getRoomSB();
			
			board.setSubjectName(dao.subjectNameGet(board));	// 과목 이름
			
			mav.addObject("sbName", "<input class=\"btnn\" style=\"margin:30px\" type='button' value='"+board.getSubjectName()+"("+board.getRoomSB()+")' />");
			
			al = dao.learningYearCodeGet(board);	// 년도 코드		
			sb = new StringBuffer();
		
			sb.append("<table align=\"center\" style=\"margin:32px\" id='Click'>");
			
			for(int i = 0; i < al.size(); i++) {
				board = new BoardBean();
				board.setRoomSB(sbCode);
				board.setYearCode(al.get(i).getYearCode());
				board.setYearName(dao.learningYearNameGet(board));
				sb.append("<tbody id='"+board.getYearCode()+"'>");
				sb.append("<tr>");
				sb.append("<td><input class=\"btnn\" type='button' value='"+board.getYearName()+"("+board.getYearCode()+")' /></td>");
				sb.append("</tr>");
				sb.append("</tbody>");
			}
			
			sb.append("</table>");
			
			mav.addObject("yearCode", sb.toString());
			
			transaction = true;


		}catch(Exception ex) {

		}finally {
			mav.setViewName("subjectCCT");
			setTransactionResult(transaction);
		}


		return mav;

	}
	
	
	public ArrayList<BoardBean> subjectCCT1(BoardBean board) {	// 코드 보여주기

		boolean transaction = false;
		ArrayList<BoardBean> al = null;
		ArrayList<BoardBean> code = new ArrayList<BoardBean>();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setRoomSB(dao.learningSBCodeGet(board));	// 과목 코드
			String sbCode = board.getRoomSB();
			String yearCode = board.getYearCode();
			
			al = dao.learningWANNumberGet(board);	// 문제번호 추출
			System.out.println(al.size());
			System.out.println(sbCode);
			System.out.println(yearCode);
			for(int i = 0; i < al.size(); i++) {
				board = new BoardBean();
				board.setRoomSB(sbCode);
				board.setYearCode(yearCode);
				board.setNumberCode(al.get(i).getNumberCode());
				board.setTypeCode(dao.learningTypeCodeGet(board));
				board.setTypeName(dao.learningTypeNameGet(board));
				System.out.println(board.getTypeName());
				board.setAllSum(al.size());
				code.add(board);
			}
					
			transaction = true;

		}catch(Exception ex) {

		}finally {

			setTransactionResult(transaction);
		}
		
		return code;

	}
	
	public ArrayList<Calendar> calendar(Calendar cd){	// 달력 보여주기

		boolean transaction = false;
		ArrayList<Calendar> al = null;
		ArrayList<Calendar> code = new ArrayList<Calendar>();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			String roomcode = (String)session.getAttribute("roomCode");
			String month = cd.getMonth();	// 년월

			al = dao.calendarGet(cd);	// 달력 출력

			for(int i = 0; i < al.size(); i++) {
				cd = new Calendar();
				cd.setMonth(month);
				cd.setRoomCode(roomcode);
				
				cd.setSunday(al.get(i).getSunday());
				cd.setCheckSunday(month+al.get(i).getSunday());
				cd.setCheckSunday(Integer.toString(dao.CheckSunday(cd)));


				cd.setMonday(al.get(i).getMonday());
				cd.setCheckMonday(month+al.get(i).getMonday());
				cd.setCheckMonday(Integer.toString(dao.CheckMonday(cd)));
				
				cd.setTuesday(al.get(i).getTuesday());
				cd.setCheckTuesday(month+al.get(i).getTuesday());
				cd.setCheckTuesday(Integer.toString(dao.CheckTuesday(cd)));
				
				cd.setWednesday(al.get(i).getWednesday());
				cd.setCheckWednesday(month+al.get(i).getWednesday());
				cd.setCheckWednesday(Integer.toString(dao.CheckWednesday(cd)));
				
				cd.setThursday(al.get(i).getThursday());
				cd.setCheckThursday(month+al.get(i).getThursday());
				cd.setCheckThursday(Integer.toString(dao.CheckThursday(cd)));
				
				cd.setFriday(al.get(i).getFriday());
				cd.setCheckFriday(month+al.get(i).getFriday());
				cd.setCheckFriday(Integer.toString(dao.CheckFriday(cd)));
				
				cd.setSaturday(al.get(i).getSaturday());
				cd.setCheckSaturday(month+al.get(i).getSaturday());
				cd.setCheckSaturday(Integer.toString(dao.CheckSaturday(cd)));

				cd.setAllSize(al.size());

				code.add(cd);
			}
					
			transaction = true;

		}catch(Exception ex) {

		}finally {

			setTransactionResult(transaction);
		}
		
		return code;

	}
	private ModelAndView subjectCCTSTUDENT(BoardBean board) {	// 코드 보여주기

		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> al = null;
		StringBuffer sb=null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setRoomSB(dao.learningSBCodeGet(board));	// 과목 코드
			String sbCode = board.getRoomSB();
			
			board.setSubjectName(dao.subjectNameGet(board));	// 과목 이름
			
			mav.addObject("sbName", "<input class=\"btnn\" style=\"margin:30px\" type='button' value='"+board.getSubjectName()+"("+board.getRoomSB()+")' />");
			
			al = dao.learningYearCodeGet(board);	// 년도 코드		
			sb = new StringBuffer();
		
			sb.append("<table align=\"center\" style=\"margin:32px\" id='Click'>");
			
			for(int i = 0; i < al.size(); i++) {
				board = new BoardBean();
				board.setRoomSB(sbCode);
				board.setYearCode(al.get(i).getYearCode());
				board.setYearName(dao.learningYearNameGet(board));
				sb.append("<tbody id='"+board.getYearCode()+"'>");
				sb.append("<tr>");
				sb.append("<td><input class=\"btnn\" type='button' value='"+board.getYearName()+"("+board.getYearCode()+")' /></td>");
				sb.append("</tr>");
				sb.append("</tbody>");
			}
			
			sb.append("</table>");
			
			mav.addObject("yearCode", sb.toString());
			
			transaction = true;


		}catch(Exception ex) {

		}finally {
			mav.setViewName("subjectCCTSTUDENT");
			setTransactionResult(transaction);
		}


		return mav;

	}
	
	private ModelAndView hometeacherLearningMainPage(LearningRoomBean room) {	// 선생님 학습방 메인 페이지

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			
			room.setRoomCode((String)session.getAttribute("roomCode"));
			room = dao.learningRoomGo(room);
			
			session.setAttribute("roomCode", room.getRoomCode());

			mav.addObject("content",room.getRoomIntroduction());

			transaction = true;


		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			mav.setViewName("teacherLearningMain");
			setTransactionResult(transaction);
		}

		return mav;
	}
	
	private ModelAndView homestudentLearningMainPage(LearningRoomBean room) {	// 학생 학습방 메인 페이지

		mav = new ModelAndView();
		BoardBean board=new BoardBean();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			mav.addObject("code",room.getRoomCode());

			room.setRoomCode((String)session.getAttribute("roomCode"));


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
	
	
	public ArrayList<BoardBean> taskScoreShow(BoardBean board) {	// 코드 보여주기

		boolean transaction = false;
		ArrayList<BoardBean> al = null;
		ArrayList<BoardBean> score = new ArrayList<BoardBean>();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			
			ArrayList<BoardBean> bb = null;
			
			String roomcode = (String)session.getAttribute("roomCode");
			String stcode = board.getStudentCode();
			String ran = "확인불가";

			board.setRoomCode(roomcode);

			al = dao.learningTaskList(board);	// 게시 리스트
			
			if(dao.learningTaskScoreCheck(board) != 0) {
			
			bb = dao.learningTeskScoreRank(board);	// 등수
			
			for(int i = 0; i < bb.size(); i++) {
				
				if(bb.get(i).getStudentCode().equals(stcode)){
					
					ran = bb.get(i).getCaCode();
					break;
					
				}
				
			}

			for(int i = 0; i < al.size(); i++) {
				board = new BoardBean();
				board.setRoomCode(roomcode);
				board.setBoardTitle(al.get(i).getBoardTitle());
				board.setBoardCode(al.get(i).getBoardCode());
				board.setStudentCode(stcode);
				board.setStudentName(dao.stNameGet(board));
				if(dao.learningTeskSubmitCodeCheck(board) != 0) {
				board.setTagCode(dao.learningTeskSubmitCodeGet(board));	
				if(dao.taskScoreCheck(board)!= 0) {
				board.setTypeSum(dao.taskScoreGet(board));
				board.setAllSum(dao.learningTeskScoreAllSum(board));
				}
				else {board.setTypeSum("점수 미등록");}
				}
				else {
					board.setTypeSum("미제출");
				}
				board.setStNumber(Integer.toString(dao.learningRoomstAll(board)));
				board.setAllSum(dao.learningTeskScoreAllSum(board));
				board.setAverage(String.format("%.1f",board.getAllSum() / (double)dao.learningTeskScoreCount(board)));
				board.setRank(ran);
				
				board.setPercentage(String.format("%.2f",Integer.parseInt(board.getRank())/(double)Integer.parseInt(board.getStNumber())*100));
				
				score.add(board);
			}
			
			}else {
				
				for(int i = 0; i < al.size(); i++) {
					board = new BoardBean();
					board.setRoomCode(roomcode);
					board.setBoardTitle(al.get(i).getBoardTitle());
					board.setBoardCode(al.get(i).getBoardCode());
					board.setStudentCode(stcode);
					board.setStudentName(dao.stNameGet(board));
					if(dao.learningTeskSubmitCodeCheck(board) != 0) {
					board.setTagCode(dao.learningTeskSubmitCodeGet(board));	
					if(dao.taskScoreCheck(board)!= 0) {
					board.setTypeSum(dao.taskScoreGet(board));
					board.setAllSum(dao.learningTeskScoreAllSum(board));
					}
					else {board.setTypeSum("점수 미등록");}
					}
					else {
						board.setTypeSum("미제출");
					}
					
					board.setStNumber(Integer.toString(dao.learningRoomstAll(board)));
					board.setAverage("미제출");
					board.setRank(ran);
					board.setPercentage("미제출");

					score.add(board);
				}
				
			}
			
			transaction = true;

		}catch(Exception ex) {
		}finally {

			setTransactionResult(transaction);
		}
		
		return score;

	}
	
	public String mainCalendarGet() {	// 메인페이지 달력

		mav = new ModelAndView();
		Calendar cd = null;
		BoardBean board = null;
		DbBoardBean dbb = null;
		ArrayList<Calendar> al = null;
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			cd = new Calendar();
					
			String month = dao.nowYearGet();	// 년월
			
			String today = dao.nowYearMMGet();	// 현재 날짜
			
			String roomcode = (String)session.getAttribute("roomCode");

			cd.setRoomCode(roomcode);			
			cd.setMonth(month);
			
			al = dao.calendarGet(cd);	// 달력 출력

			sb.append("</br><table align=center id='"+month+"'>");
			sb.append("<tbody align=center>");
			sb.append("<tr style='margin-bottom:100px'><td  style='font-weight:bold;font-size: 20px;' colspan='7'>"+month.substring(0,4)+"년  "+month.substring(4)+"월</td></tr>");
			sb.append("<tr>");
			sb.append("<td><input type='button' style='color:#FF0000' class='btn' value='Sunday' /></td>");
			sb.append( "<td><input type='button' class='btn' value='Monday' /></td>");
			sb.append("<td><input type='button' class='btn' value='Tuesday' /></td>");
			sb.append("<td><input type='button' class='btn' value='Wednesday' /></td>");
			sb.append("<td><input type='button' class='btn' value='Thursday' /></td>");
			sb.append("<td><input type='button' class='btn' value='Friday' /></td>");
			sb.append("<td><input type='button' style='color:#0100FF' class='btn' value='Saturday' /></td>");
			sb.append("</tr>");
			sb.append("</tbody>");
			
			for(int i = 0; i < al.size(); i++) {
				
				cd = new Calendar();
				
				cd.setRoomCode(roomcode);				
				cd.setSunday(al.get(i).getSunday());				
				cd.setMonday(al.get(i).getMonday());							
				cd.setTuesday(al.get(i).getTuesday());			
				cd.setWednesday(al.get(i).getWednesday());				
				cd.setThursday(al.get(i).getThursday());						
				cd.setFriday(al.get(i).getFriday());							
				cd.setSaturday(al.get(i).getSaturday());

        		String Sunday =((today.substring(6).equals(cd.getSunday()))? "#F361DC" : "transparent");
        		String Monday =((today.substring(6).equals(cd.getMonday()))? "#F361DC" : "transparent");
        		String Tuesday =((today.substring(6).equals(cd.getTuesday()))? "#F361DC" : "transparent");
        		String Wednesday =((today.substring(6).equals(cd.getWednesday()))? "#F361DC" : "transparent");
        		String Thursday =((today.substring(6).equals(cd.getThursday()))? "#F361DC" : "transparent");
        		String Friday =((today.substring(6).equals(cd.getFriday()))? "#F361DC" : "transparent");
        		String Saturday =((today.substring(6).equals(cd.getSaturday()))? "#F361DC" : "transparent");

        		sb.append("<tbody align=center>");
        		sb.append("<tr>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getSunday()+"' style='color:#FF0000;background:"+Sunday+";' class='btn' value='"+al.get(i).getSunday()+"' /></br></td>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getMonday()+"' class='btn' style='color:#000000;background: "+Monday+";' value='"+al.get(i).getMonday()+"' /></br></td>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getTuesday()+"' class='btn' style='color:#000000;background: "+Tuesday+";' value='"+al.get(i).getTuesday()+"' /></br></td>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getWednesday()+"' class='btn' style='color:#000000;background: "+Wednesday+";' value='"+al.get(i).getWednesday()+"' /></br></td>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getThursday()+"' class='btn' style='color:#000000;background: "+Thursday+";' value='"+al.get(i).getThursday()+"' /></br></td>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getFriday()+"' class='btn' style='color:#000000;background: "+Friday+";' value='"+al.get(i).getFriday()+"' /></br></td>");
        		sb.append("<td><input type='button' name='day"+month+al.get(i).getSaturday()+"' style='color:#0100FF;background: "+Saturday+";' class='btn' value='"+al.get(i).getSaturday()+"' /></br></td>");
        		sb.append("</tr>");
        		sb.append("</tbody>");	
				
			}
			
			sb.append("</table>");
			
			board = new BoardBean();
			board.setBoardCode(today);
			board.setRoomCode(roomcode);
			
			if(dao.planCheck(board) != 0) {
				dbb = dao.planCTX(board);	// 내용 보기
				sb.append("<br/><table align=center id='"+month+"'>");
				sb.append("<tr>");
				sb.append("<td>굳굳");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}else {
				sb.append("<br/><table align=center id='"+month+"'>");
				sb.append("<tr>");
				sb.append("<td>강의계획이 없습니다.");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}

			transaction = true;

		}catch(Exception ex) {
		}finally {
			
			setTransactionResult(transaction);
		}

		return sb.toString();
	}
	
	
	
	
}

