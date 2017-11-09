package icia.project.services;

import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.MemberBean;
import icia.project.bean.BoardBean;
import icia.project.bean.LearningRoomBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;
import icia.project.key.Encryption;


@Service
public class adminManagement extends TransactionExe{
	@Autowired
	private IMybatis dao;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private Encryption enc;
	@Autowired
	private PageManagement pm;

	private ModelAndView mav;


	public ModelAndView entrance(int serviceCode,Object object) {
		switch(serviceCode) {

		case 1:	// 로그인
			mav = login(((MemberBean)object));
			break;

		case 2 : // 해당 학생 자세히 보기
			mav = studentCXT(((MemberBean)object));
			break;

		case 3 : // 해당 TEAHCER 자세히 보기
			mav = adminTeacherCXT(((MemberBean)object));
			break;

		case 4:	// 

			break;

		case 5:	// 

			break;
		}
		return mav;
	}
	private ModelAndView login(MemberBean member) {
		boolean transaction = false;
		String page =null;
		ArrayList<MemberBean> mb=null;
		String state =null;
		StringBuffer sb= new StringBuffer();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {
			if(dao.adminIdCheck(member) == 1) { // id check
				if(dao.adminPassowrd(member) == 1) { // pwd check	
					mav = new ModelAndView();
					page="adminMain";
				
					mav.addObject("studentcount",dao.allStudent(member)); // student count
					mav.addObject("teachercount",dao.allteacher(member)); // student count
		
					mb=dao.student(member);
					
					sb.append("<table class=\"table table-hover\">");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("번호");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("이름");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("ID");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("이메일/발송");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("핸드폰");
					sb.append("</td>");

					sb.append("<td>");
					sb.append("자세히");
					sb.append("</td>");
					sb.append("</tr>");
					for(int i=0; i<mb.size(); i++) {
						member.setId(mb.get(i).getId());
						state = dao.stateSTCodeCheck(member);
						String check =((Integer.parseInt(state) == 1)? "가입" : "탈퇴");
						sb.append("<tr>");	
						//sb.append("<input type=\"hidden\" name=\"boardTitle\" value='" + board.getBoardTitle() + "'/>");
						sb.append("<td>" + mb.get(i).getStudentCode() + "</td>");
						sb.append("<td>" + mb.get(i).getName()+"<br>"+check + "</td>");
						sb.append("<td>" + mb.get(i).getId() + "</td>");
						sb.append("<td>");
						sb.append("<button onClick=\"emailSend(\'"+  mb.get(i).getEmail() +"\')\" />" +  mb.get(i).getEmail() + "</button>");
						sb.append("</td>");
						sb.append("<td>" + mb.get(i).getPhone() + "</td>");
						sb.append("<td>"+"<input type=\"button\"  value=\"자세히\"class='btn-sm' onClick=\"cxt('"+ mb.get(i).getStudentCode() +"')\"/>"+"</td>");
					
						sb.append("</tr>");
					}
					sb.append("</table>");

					
					mav.addObject("allstudent", sb.toString());
					
					
					
					sb= new StringBuffer();
					mb=new ArrayList<MemberBean>();
					state =null;
					mb=dao.teacher(member);
					sb.append("<table class=\"table table-hover\">");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("NAME");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("ID");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("이메일/발송");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("핸드폰 번호");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("자세히");
					sb.append("</td>");
					sb.append("</tr>");
					for(int i=0; i<mb.size(); i++) {
						member.setId(mb.get(i).getId());
						state = dao.stateCodeCheck(member);
						String check =((Integer.parseInt(state) == 1)? "가입" : "탈퇴");
						sb.append("<tr>");
						sb.append("<td>");
						sb.append(mb.get(i).getName()+"<br>"+check);
						sb.append("</td>");
						sb.append("<td>"+mb.get(i).getId() + "</td>");
						sb.append("<td>");
						sb.append("<button onClick=\"emailSend(\'"+  mb.get(i).getEmail() +"\')\" />" +  mb.get(i).getEmail() + "</button>");
						sb.append("</td>");
						sb.append("<td>" + mb.get(i).getPhone() + "</td>");
						sb.append("<td>"+"<input type=\"button\" value=\"자세히\"class='btn-sm' onClick=\"teacherCxt('"+mb.get(i).getId() +"')\"/>"+"</td>");
						
				
						sb.append("</tr>");
					}
					sb.append("</table>");
					mav.addObject("allTeacher",sb.toString());

					transaction = true;
				}else {
					mav = new ModelAndView();
					mav.setViewName("login");
					mav.addObject("identity", "0");
					mav.addObject("message", "alert('비밀번호가 틀렸습니다.')");
					mav.addObject("id", member.getId());
				}
			}else {
				mav = new ModelAndView();
				mav.setViewName("login");
				mav.addObject("identity", "0");
				mav.addObject("message", "alert('no id sorry.')");

			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}
	private ModelAndView studentCXT(MemberBean member) {
		boolean transaction = false;


		mav = new ModelAndView();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {

			mav.addObject("wrongcount",dao.studnetWorngCount(member));
			mav.addObject("querycount",dao.studentQueryCount(member));
			mav.addObject("debatecount",dao.studentDebateCount(member));

			transaction = true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}finally {
		
			setTransactionResult(transaction);
		}
		return mav;
	}
	
	private ModelAndView adminTeacherCXT(MemberBean member) {
		boolean transaction = false;

		mav = new ModelAndView();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {
		
			mav.addObject("classCount",dao.classCount(member));
			mav.addObject("wrongTagcount",dao.wrongTagcount(member));
			mav.addObject("refercount",dao.refercount(member));
			mav.addObject("noticecount",dao.noticecount(member));
			mav.addObject("debatecount",dao.debatecount(member));
			mav.addObject("wrongecount",dao.wrongecount(member));

			
			transaction = true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}finally {
		
			setTransactionResult(transaction);
		}
		return mav;
	}
}
