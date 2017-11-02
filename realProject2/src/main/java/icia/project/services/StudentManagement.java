package icia.project.services;

import java.util.ArrayList;
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
import icia.project.bean.LearningRoomBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;
import icia.project.key.Encryption;

@Service
public class StudentManagement extends TransactionExe {

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

		case 2: // 회원가입
			mav = join(((MemberBean)object));
			break;

		case 3: // 아이디 중복체크
			mav = idRedundancyCheck(((MemberBean)object));
			break;

		case 4:	// 아이디 찾기
			System.out.println("여긴 오니444?");
			mav = idFind(((MemberBean)object));
			break;

		case 5:	// 로그아웃
			mav = logout(((MemberBean)object));
			break;

		case 6:	// 나의 정보 수정
			mav = lnformationChange(((MemberBean)object));
			break;

		case 7:	// 비밀번호 수정
			mav = studentInfoPWDUpdate(((MemberBean)object));
			break;

		case 8:	// 회원탈퇴
			mav = memberDelete(((MemberBean)object));
			break;

		case 9:	// 학습방 참여 및 조회
			mav = learningJoin(((LearningRoomBean)object));
			break;
		case 10:	// 학습방 참여 및 조회
			mav = findPwd(((MemberBean)object));
			break;

		}

		return mav;

	}

	private ModelAndView login(MemberBean member) {	// 로그인

		boolean transaction = false;
		String page =null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.stIdCheck(member) != 0) {	// 아이디 체크

				if(enc.matches(member.getPwd(),dao.stPwdGet(member).getPwd())) {	// 비밀번호 체크
					member.setLogType(1);
					member = dao.stCodeGet(member);	// 학생코드 추출
					if(dao.stLogHistory(member) != 0) {	// 로그히스토리

						// 동적으로 학습방 쏴주기

						session.setAttribute("stCode", member.getStudentCode());
						session.setAttribute("identity", member.getIdentity());


						mav = pm.entrance(2, null);
						transaction = true;
					}else {
						page = "login";
						mav.addObject("identity", "2");
						mav.addObject("message", "alert('로그인 실패 하셨습니다.')");
						mav.addObject("id", member.getId());
					}

				}else {
					mav = new ModelAndView();
					mav.setViewName("login");
					mav.addObject("identity", "2");
					mav.addObject("message", "alert('비밀번호가 틀렸습니다.')");
					mav.addObject("id", member.getId());
				}

			}else {
				mav = new ModelAndView();
				mav.setViewName("login");
				mav.addObject("identity", "2");
				mav.addObject("message", "alert('아이디가 틀렸습니다.')");
				mav.addObject("id", member.getId());
			}		

		}catch(Exception ex) {
			ex.printStackTrace();
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


			if(dao.stIdCheck(member) == 0) {	// 아이디 체크
				member.setPwd(enc.encode(member.getPwd()));	// 보안비밀번호
				member.setStateCode("1");

				if(dao.stCodeCheck(member) == 0) {	// 학생코드 유무
					System.out.println("sdafsadf");
					if(dao.stJoin(member) != 0) {	// 인설트
						System.out.println("여기옴");
						page ="login";
						mav.addObject("message", "alert('회원가입 되셨습니다.')");
						mav.addObject("identity", "2");
						transaction = true;

					}else {

						page = "studentjoin";
						mav.addObject("id", member.getId());
						mav.addObject("name", member.getName());
						mav.addObject("email", member.getEmail());
						mav.addObject("phone", member.getPhone());
						mav.addObject("identity", "2");
						mav.addObject("message", "alert('회원가입 실패 하셨습니다.')");

					}

				}else {	// 학생코드 있음
					page ="login";
					mav.addObject("identity", "2");
					mav.addObject("message", "alert('이미 존재하는 회원입니다.')");
				}

			}else {
				page = "studentjoin";
				mav.addObject("id", member.getId());
				mav.addObject("name", member.getName());
				mav.addObject("email", member.getEmail());
				mav.addObject("phone", member.getPhone());
				mav.addObject("identity", "2");
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

			if(dao.stIdCheck(member) != 0) {	// 아이디 체크
				page = "studentjoin";
				mav.addObject("id", member.getId());
				mav.addObject("identity", "2");
				mav.addObject("message", "alert('사용하실 수 없는 아이디입니다.')");
			}else {
				page = "studentjoin";
				mav.addObject("id", member.getId());
				mav.addObject("identity", "2");
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
		MemberBean mb;

		try {
			System.out.println("여긴 오니44455?");
			System.out.println(member.getEmail());
			mb = dao.stIdFind(member);

			if(mb.getId() == null) {
				page = "login";
				System.out.println("여긴 오니null?");
				mav.addObject("identity", "2");
				mav.addObject("message", "alert('아이디가 없습니다.')");
			}else {
				page = "login";
				System.out.println("여긴 오니suc?");
				mav.addObject("identity", "2");
				mav.addObject("message", "alert('"+mb.getId()+"')");
				System.out.println("여긴 오니?");
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			mav.setViewName(page);
		}

		return mav;
	}

	private ModelAndView logout(MemberBean member) {	// 로그아웃

		mav = new ModelAndView();

		boolean transaction = false;
		String page = null;


		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			member.setStudentCode(((String)session.getAttribute("stCode")));

			member.setLogType(-1);

			if(dao.stLogHistory(member) != 0) {
				session.removeAttribute("stCode");
				session.removeAttribute("identity");
				page = "home";
				mav.addObject("message", "alert('로그아웃 되셨습니다.')");
				transaction = true;
			}else {
				session.removeAttribute("stCode");
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

	private ModelAndView lnformationChange(MemberBean member) {	// 나의 정보 수정

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			member.setStudentCode(((String)session.getAttribute("stCode")));

			if(dao.stInformationChange(member) != 0) {

				mav = pm.entrance(6, null);
				mav.addObject("message","alert('나의정보 되셨습니다.')");
				transaction = true;
			}else {
				mav = pm.entrance(6, null);
				mav.addObject("message","alert('나의정보 실패되셨습니다.')");
				transaction = true;
			}


		}catch(Exception ex) {

		}finally {

			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView memberDelete(MemberBean member) {	// 회원탈퇴

		mav = new ModelAndView();

		boolean transaction = false;



		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			member.setStudentCode((String)session.getAttribute("stCode"));
			
			// 회원정보와 로그 기록은 남긴다.(단 회원정보에 상태코드 update 하여 탈퇴로 변경해주자!)

			// 다른 테이블도 delete 시켜줘야함
			if(enc.matches(member.getPwd(),dao.checkPwd(member).getPwd())) {
		
				dao.tasksubmitdelete(member);

				dao.WRONGANSWERNOTES(member);
				dao.DEBATETAGdelete(member);
				dao.QUERYdelete(member);
				dao.MESSAGEdelete(member);
				dao.learningstudentdelete(member);
				if(dao.updateState(member) != 0) {
	
					session.removeAttribute("stCode");
					session.removeAttribute("identity");

					mav.setViewName("home");
					mav.addObject("message", "alert('회원탈퇴 되었습니다.')");
					transaction = true;
				}


			}	else {
				
				mav.setViewName("studentMain");
				mav.addObject("message", "alert('비밀번호가 틀리셨습니다.')");

			}




		}catch(Exception ex) {
			ex.printStackTrace();

		}finally {

			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView studentInfoPWDUpdate(MemberBean member) {	// 비밀번호 수정

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			member.setStudentCode((String)session.getAttribute("stCode"));
			member.setPwd(enc.encode(member.getPwd()));

			if(dao.stInformationPWDChange(member) != 0) {
				mav = pm.entrance(6, null);
				mav.addObject("message","alert('비밀번호 수정 되셨습니다.')");
				transaction = true;
			}else {
				mav = pm.entrance(6, null);
				mav.addObject("message","alert('비밀번호 수정 실패되셨습니다.')");
				transaction = true;
			}

		}catch(Exception ex) {

		}finally {

			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView learningJoin(LearningRoomBean room) {	// 학습방 참여 및 조회

		boolean transaction = false;
		ArrayList<LearningRoomBean> al = new ArrayList<LearningRoomBean>();
		StringBuffer sb = new StringBuffer();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			room.setStudentCode((String)session.getAttribute("stCode"));

			if(room.getRoomCode() == null) {	// 조회

				if(dao.tclearningRoomCheck(room) != 0) {
					mav = new ModelAndView();

					al = dao.learningGet(room);

					for(int i=0; i < al.size(); i++) {
						sb.append("<input type='button' value='"+al.get(i).getRoomName()+"' "
								+ "onClick=learningJoin('"+al.get(i).getRoomCode()+"') />");
					}

					mav.addObject("id", room.getId());
					mav.addObject("content", sb.toString());
					mav.setViewName("studentLearningJoin");
				}else {
					mav = new ModelAndView();
					mav.addObject("message", "alert('선생님의 학습방이 없거나 잘못된 아이디 입니다..')");
					mav.setViewName("studentLearningJoin");
				}

			}else if(dao.stlearningRoomJoinCheck(room) == 0) {	// 인설트



				if(dao.stLearningJoin(room) != 0) {
					mav = pm.entrance(2, null);
					mav.addObject("message", "alert('학습방에 참여 되셨습니다.')");
					transaction = true;

				}else {
					mav = pm.entrance(2, null);
					mav.addObject("message", "alert('학습방에 참여 하실 수 없습니다.')");
					transaction = true;
				}

			}else if(dao.stlearningRoomJoinCheck(room) != 0){
				mav = new ModelAndView();
				mav.setViewName("studentLearningJoin");
				mav.addObject("message", "alert('이미 참여하신 학습방 입니다.')");
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

			System.out.println("여긴 왓니??");
			System.out.println(member.getEmail());
			System.out.println(member.getId());

			if(dao.findstPwd(member) == 1) {
				int code1 = (int)(Math.random() *1000);
				int code2 = (int)(Math.random() *1000);
				String tempwd = Integer.toString(code1)+Integer.toString(code2);
				member.setPwd(tempwd); //임시비밀번호
				member.setPwd(enc.encode(member.getPwd()));
				if(dao.updatestPwd(member) == 1) {
					member.setPwd(tempwd);
					mailSender(member);
					page= "home";
					transaction = true;

				}
			}else {

				page = "findPWD";

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
