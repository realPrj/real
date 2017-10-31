package icia.project.services;


import java.util.Properties;

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
public class teacherManagement extends TransactionExe {

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

		case 2 : // 회원가입
			mav = join(((MemberBean)object));
			break;

		case 3 : // 아이디 중복체크	
			mav = idRedundancyCheck(((MemberBean)object));
			break;

		case 4:	// 아이디 찾기	
			mav = idFind(((MemberBean)object));
			break;

		case 5:	// 로그아웃
			mav = logout();
			break;

		case 6:	// 나의정보 수정
			mav = teacherInfoUpdate(((MemberBean)object));
			break;

		case 7:	// 나의정보 비밀번호 수정
			mav = teacherInfoPWDUpdate(((MemberBean)object));
			break;

		case 8:	// 회원탈퇴
			mav = memberDelete();
			break;

		case 9:	// 학습방 개설
			mav = learningOpen(((LearningRoomBean)object));
			break;
		case 10:	// 비밀번호 찾기
			mav = findPwd(((MemberBean)object));
			break;



		}

		return mav;

	}

	private ModelAndView login(MemberBean member) {	// 로그인

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.tcIdCheck(member) != 0) {	// 아이디 체크

				if(enc.matches(member.getPwd(),dao.tcPwdGet(member).getPwd())) {	// 비밀번호 체크
					member.setLogType(1);

					/*if(dao.tcLogHistory(member) != 0) {	// 로그히스토리
					 */						
					// 동적으로 학습방 쏴주기

					session.setAttribute("tcId", member.getId());
					session.setAttribute("identity", member.getIdentity());	
					mav = pm.entrance(1, null);
					transaction = true;
					/*	}else {
						page = "login";
						mav.addObject("identity", "1");
						mav.addObject("message", "alert('로그인 실패 하셨습니다.')");
						mav.addObject("id", member.getId());
					}
					 */
				}else {
					mav = new ModelAndView();
					mav.setViewName("login");
					mav.addObject("identity", "1");
					mav.addObject("message", "alert('비밀번호가 틀렸습니다.')");
					mav.addObject("id", member.getId());
				}

			}else {
				mav = new ModelAndView();
				mav.setViewName("login");
				mav.addObject("identity", "1");
				mav.addObject("message", "alert('아이디가 틀렸습니다.')");
				mav.addObject("id", member.getId());
			}

		}catch(Exception ex) {

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView join(MemberBean member) {	// 회원가입

		mav = new ModelAndView();

		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.tcIdCheck(member) == 0) {	// 아이디 체크
				member.setPwd(enc.encode(member.getPwd()));	// 보안비밀번호
				member.setStateCode("1");

				if(dao.tcJoin(member) != 0) {	// 인설트
					page ="login";
					mav.addObject("identity", "1");
					transaction = true;

					/*		member.setLogType(1);
					if(dao.tcLogHistory(member) != 0) {

						page ="login";
						mav.addObject("identity", "1");
						transaction = true;

					}else {
						page = "teacherjoin";
						mav.addObject("id", member.getId());
						mav.addObject("name", member.getName());
						mav.addObject("email", member.getEmail());
						mav.addObject("phone", member.getPhone());
						mav.addObject("identity", "1");
						mav.addObject("message", "alert('회원가입 실패 하셨습니다.')");
					}		*/		

				}else {

					page = "teacherjoin";
					mav.addObject("id", member.getId());
					mav.addObject("name", member.getName());
					mav.addObject("email", member.getEmail());
					mav.addObject("phone", member.getPhone());
					mav.addObject("identity", "1");
					mav.addObject("message", "alert('회원가입 실패 하셨습니다.')");

				}

			}else {
				page = "teacherjoin";
				mav.addObject("id", member.getId());
				mav.addObject("name", member.getName());
				mav.addObject("email", member.getEmail());
				mav.addObject("phone", member.getPhone());
				mav.addObject("identity", "1");
				mav.addObject("message", "alert('사용하실 수 없는 아이디입니다.')");
			}

		}catch(Exception ex) {

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView idRedundancyCheck(MemberBean member) {	// 아이디 중복체크

		mav = new ModelAndView();

		String page = null;

		try {

			if(dao.tcIdCheck(member) != 0) {	// 아이디 체크
				page = "teacherjoin";
				mav.addObject("id", member.getId());
				mav.addObject("identity", "1");
				mav.addObject("message", "alert('사용하실 수 없는 아이디입니다.')");

			}else {
				page = "teacherjoin";
				mav.addObject("id", member.getId());
				mav.addObject("identity", "1");
				mav.addObject("message", "alert('사용하실 수 있는 아이디입니다.')");
			}

		}catch(Exception ex) {

		}finally {
			mav.setViewName(page);
		}

		return mav;
	}	

	private ModelAndView idFind(MemberBean member) {	// 아이디 찾기

		mav = new ModelAndView();

		String page = null;
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			member = dao.tcIdFind(member);

			if(member.getId() == null) {

				page = "login";
				mav.addObject("identity", "1");
				mav.addObject("message", "alert('아이디가 없습니다.')");
				transaction = true;
			}else {
				page = "login";
				mav.addObject("identity", "1");
				mav.addObject("message", "alert('"+member.getId()+"')");
				transaction = true;
			}


		}catch(Exception ex) {

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView logout() {	// 로그아웃

		mav = new ModelAndView();

		boolean transaction = false;
		String page = null;
		MemberBean member = new MemberBean();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			member.setId((String)session.getAttribute("tcId"));

			member.setLogType(-1);

			if(dao.tcLogHistory(member) != 0) {
				session.removeAttribute("tcId");
				session.removeAttribute("identity");
				page = "home";
				mav.addObject("message", "alert('로그아웃 되셨습니다.')");
				transaction = true;
			}else {
				session.removeAttribute("tcId");
				session.removeAttribute("identity");
				page = "home";
				mav.addObject("message", "alert('로그아웃 실패하셨습니다.')");
				transaction = true;
			}

		}catch(Exception ex) {

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView memberDelete() {	// 회원탈퇴

		mav = new ModelAndView();

		boolean transaction = false;
		String page = null;
		MemberBean member = new MemberBean();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			member.setId((String)session.getAttribute("tcId"));

			if(dao.tcmemberDelete(member) != 0) {

				// 다른 테이블도 delete 시켜줘야함

				session.removeAttribute("tcId");
				session.removeAttribute("identity");
				page = "home";
				mav.addObject("message", "alert('회원탈퇴 되셨습니다.')");
				transaction = true;
			}else {
				session.removeAttribute("tcId");
				session.removeAttribute("identity");
				page = "home";
				mav.addObject("message", "alert('회원탈퇴 실패하셨습니다.')");
				transaction = true;
			}


		}catch(Exception ex) {

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView teacherInfoUpdate(MemberBean member) {	// 나의정보 수정

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.tcInformationChange(member) != 0) {
				mav = pm.entrance(5, null);
				mav.addObject("message","alert('나의정보 되셨습니다.')");
				transaction = true;
			}else {
				mav = pm.entrance(5, null);
				mav.addObject("message","alert('나의정보 실패되셨습니다.')");
				transaction = true;
			}


		}catch(Exception ex) {

		}finally {

			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView teacherInfoPWDUpdate(MemberBean member) {	// 비밀번호 수정

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			member.setId((String)session.getAttribute("tcId"));
			member.setPwd(enc.encode(member.getPwd()));

			if(dao.tcInformationPWDChange(member) != 0) {
				mav = pm.entrance(5, null);
				mav.addObject("message","alert('비밀번호 수정 되셨습니다.')");
				transaction = true;
			}else {
				mav = pm.entrance(5, null);
				mav.addObject("message","alert('비밀번호 수정 실패되셨습니다.')");
				transaction = true;
			}

		}catch(Exception ex) {

		}finally {

			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView learningOpen(LearningRoomBean room) {	// 학습방 개설

		boolean distinction = true;
		boolean transaction = false;
		int code = 0; 

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			while(distinction) {	// 학습코드 유무 체크

				code = (int)(Math.random() *1000);

				room.setRoomCode(Integer.toString(code));

				if(dao.tclearningCodeCheck(room) == 0) {				
					distinction = false;				
				}else {
					distinction = true;
				}

			}

			room.setId((String)session.getAttribute("tcId"));

			if(dao.tclearningOpen(room) != 0) {
				mav = pm.entrance(1, null);
				mav.addObject("message", "alert('학습방 개설 되셨습니다.')");
				transaction = true;
			}else {
				mav = pm.entrance(1, null);
				mav.addObject("message", "alert('학습방 개설 실패 되셨습니다.')");
				transaction = true;
			}

		}catch(Exception ex) {

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
	private ModelAndView findPwd(MemberBean member) {	// 비밀번호 찾기

		mav = new ModelAndView();

		String page = null;
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.findPwd(member) == 1) {
				int code1 = (int)(Math.random() *1000);
				int code2 = (int)(Math.random() *1000);
				String tempwd = Integer.toString(code1)+Integer.toString(code2);
				member.setPwd(tempwd); //임시비밀번호
				member.setPwd(enc.encode(member.getPwd()));
				if(dao.updatePwd(member) == 1) {
					member.setPwd(tempwd);
					mailSender(member);
					mav.addObject("identity", "1");
					page= "home";
					transaction = true;
					
				}
			}else {
				
				page = "findPWD";
				mav.addObject("identity", "1");
				mav.addObject("message", "해당하는 아이디/메일이 없습니다");
				transaction = true;
			}


		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			
			setTransactionResult(transaction);
		}
		mav.setViewName(page);
		
		
		return mav;
	}


	public ModelAndView mailSender(@ModelAttribute MemberBean member) throws AddressException, MessagingException 
	{    

		mav = new ModelAndView();   
		// 네이버일 경우 smtp.naver.com 을 입력합니다. 
		// Google일 경우 smtp.gmail.com 을 입력합니다. 
		String host = "smtp.naver.com"; 
		final String username = "ssmichael"; 
		//네이버 아이디를 입력해주세요. @nave.com은 입력하지 마시구요. 
		final String password = "zxc1473011989"; 
		//네이버 이메일 비밀번호를 입력해주세요. 
		int port=465; 
		//포트번호 
		// 메일 내용
		String recipient =member.getEmail(); 
		//받는 사람의 메일주소를 입력해주세요. 
		String content = "임시 비밀번호 입니다:";
		//메일 제목 입력해주세요.
		String context =member.getPwd(); 
		//메일 내용 입력해주세요. 
		Properties props = System.getProperties(); 
		// 정보를 담기 위한 객체 생성 
		// SMTP 서버 정보 설정 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.trust", host); 
		//Session 생성 
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un=username; 
			String pw=password; 
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
			{ return new javax.mail.PasswordAuthentication(un, pw); } }); 
		session.setDebug(true); //for debug 
		Message mimeMessage = new MimeMessage(session); //MimeMessage 생성 
		mimeMessage.setFrom(new InternetAddress("ssmichael@naver.com")); //발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요. 
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
		//수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음
		mimeMessage.setSubject(content); //제목셋팅
		mimeMessage.setText(context); //내용셋팅 
		Transport.send(mimeMessage); //javax.mail.Transport.send() 이용 }


		return mav;
	}


}
