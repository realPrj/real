package icia.project.web;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



import icia.project.bean.MemberBean;
import icia.project.bean.BoardBean;
import icia.project.bean.LearningRoomBean;
import icia.project.services.ProjectUtils;
import icia.project.services.StudentManagement;
import icia.project.services.learningStudentMM;
import icia.project.services.learningTeacherMM;
import icia.project.services.teacherManagement;


@Controller
public class FunctionController {

	///////////////////////////////////////// 기능  /////////////////////////////////////////	

	@Autowired
	private StudentManagement sm;
	@Autowired
	private teacherManagement tm;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private learningTeacherMM ltm;
	@Autowired
	private learningStudentMM stm;

	private ModelAndView mav;

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute MemberBean member) {

		switch(Integer.parseInt(member.getIdentity())) {

		case 1:	// 선생님
			mav = tm.entrance(1, member);
			break;

		case 2:	// 학생
			mav = sm.entrance(1, member);
			break;

		default :
			mav = new ModelAndView();
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}

		return mav;
	}

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute MemberBean member) {

		switch(Integer.parseInt(member.getIdentity())) {

		case 1:	// 선생님
			mav = tm.entrance(2, member);
			break;

		case 2:	// 학생
			member.setStudentCode(member.getStGrade()+member.getStclass()+member.getStNumber());
			mav = sm.entrance(2, member);
			break;

		default :
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}

		return mav;
	}

	// 아이디 중복확인
	@RequestMapping(value = "/idRedundancyCheck", method = RequestMethod.POST)
	public ModelAndView idRedundancyCheck(@ModelAttribute MemberBean member) {

		switch(Integer.parseInt(member.getIdentity())) {

		case 1:	// 선생님
			mav = tm.entrance(3, member);
			break;

		case 2:	// 학생
			mav = sm.entrance(3, member);
			break;

		default :
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}

		return mav;
	}

	// 아이디 찾기
	@RequestMapping(value = "/idFind", method = RequestMethod.POST)
	public ModelAndView idFind(@ModelAttribute MemberBean member) {

		switch(Integer.parseInt(member.getIdentity())) {

		case 1:	// 선생님
			mav = tm.entrance(4, member);
			break;

		case 2:	// 학생
			mav = sm.entrance(4, member);
			break;

		default :
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}

		return mav;
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout() throws Exception {

		String identity = null;

		identity = (String)session.getAttribute("identity");	// 신분	

		switch(Integer.parseInt(identity)) {

		case 1:	// 선생님
			mav = tm.entrance(5, null);
			break;

		case 2:	// 학생
			mav = sm.entrance(5, null);
			break;

		default :
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}


		return mav;
	}

	// 선생님 나의정보 수정
	@RequestMapping(value = "/teacherInfoUpdate", method = RequestMethod.POST)
	public ModelAndView  teacherInfoUpdate(MemberBean member) {

		mav = tm.entrance(6, member);

		return mav;
	}

	// 학생 나의정보 수정
	@RequestMapping(value = "/studentInfoUpdate", method = RequestMethod.POST)
	public ModelAndView  studentInfoUpdate(MemberBean member) {

		mav = sm.entrance(6, member);

		return mav;
	}

	// 선생님 비밀번호 수정
	@RequestMapping(value = "/teacherInfoPWDUpdate", method = RequestMethod.POST)
	public ModelAndView  teacherInfoPWDUpdate(MemberBean member) {

		mav = tm.entrance(7, member);

		return mav;
	}

	// 학생 비밀번호 수정
	@RequestMapping(value = "/studentInfoPWDUpdate", method = RequestMethod.POST)
	public ModelAndView  studentInfoPWDUpdate(MemberBean member) {

		mav = sm.entrance(7, member);

		return mav;
	}

	// 선생님 학습방 개설
	@RequestMapping(value = "/learningOpen", method = RequestMethod.POST)
	public ModelAndView  learningOpen(LearningRoomBean room) {

		mav = tm.entrance(9, room);

		return mav;
	}

	// 학생 학습방 참여 및 조회
	@RequestMapping(value = "/learningJoin", method = RequestMethod.POST)
	public ModelAndView  learningJoin(LearningRoomBean room) {

		if(room.getRoomCode() == null) {	// 학습방 조회
			mav = sm.entrance(9, room);
		}else {	// 학습방 인설트
			mav = sm.entrance(9, room);
		}

		return mav;
	}

	// 선생님 학습방 공지사항 글 등록
	@RequestMapping(value = "/learningBoardNoticeInsert", method = RequestMethod.POST)
	public ModelAndView  learningBoardNoticeInsert(BoardBean board,MultipartHttpServletRequest mtfRequest)throws Exception {

		ltm.entrance(10, board, mtfRequest);

		mav = ltm.entrance(3, board);

		return mav;
	}

	// 토론 게시판 글 등록
	@RequestMapping(value = "/learningDebateInsert", method = RequestMethod.POST)
	public ModelAndView tclearningDebateInsert(@ModelAttribute BoardBean board) {

		ltm.entrance(36, board);
		mav = ltm.entrance(32, board);
		return mav;
	}

	// 과제 게시판 글 등록
	@RequestMapping(value = "/learningTaskInsert", method = RequestMethod.POST)
	public ModelAndView  learningTaskInsert(BoardBean board) {

		mav = new ModelAndView();		



		return mav;
	}

	// 과제제출 게시판 글 등록
	@RequestMapping(value = "/learningTaskSBInsert", method = RequestMethod.POST)
	public ModelAndView  learningTaskSBInsert(BoardBean board) {

		mav = new ModelAndView();		



		return mav;
	}

	// 성적 게시판 글 등록
	@RequestMapping(value = "/learningGradeInsert", method = RequestMethod.POST)
	public ModelAndView  learningGradeInsert(BoardBean board) {

		mav = new ModelAndView();		



		return mav;
	}

	// 강의계획서  글 등록
	@RequestMapping(value = "/learningPlanInsert", method = RequestMethod.POST)
	public ModelAndView  learningPlanInsert(BoardBean board) {

		mav = new ModelAndView();		

		return mav;
	}

	// 오답노트 코멘트(선생님) 글 등록
	@RequestMapping(value = "/learningWANCommentInsert", method = RequestMethod.POST)
	public ModelAndView  learningWANCommentInsert(BoardBean board) {

		ltm.entrance(18, board);
		mav = ltm.entrance(17, board);
		mav.addObject("message", "alert('글 등록 되셨습니다.');");
		return mav;
	}

	// 오답노트 코멘트(선생님) 글 수정
	@RequestMapping(value = "/learningWANCMUpdate", method = RequestMethod.POST)
	public ModelAndView  learningWANCMUpdate(BoardBean board) {

		ltm.entrance(20, board);
		mav = ltm.entrance(17, board);
		mav.addObject("message", "alert('수정 되셨습니다.');");
		return mav;
	}

	// 오답노트 코멘트(선생님) 글 삭제
	@RequestMapping(value = "/learningWANCMDelete", method = RequestMethod.POST)
	public ModelAndView  learningWANCMDelete(BoardBean board) {

		ltm.entrance(21, board);
		mav = ltm.entrance(17, board);
		mav.addObject("message", "alert('삭제 되셨습니다.');");
		return mav;
	}



	// 자료실 글쓰기
	@RequestMapping(value = "/learningDataInsert", method = RequestMethod.POST)
	public ModelAndView learningDataInsert(@ModelAttribute BoardBean board,MultipartHttpServletRequest mtfRequest)throws Exception{      
		board.setId((String)session.getAttribute("tcId"));

		mav = ltm.entrance(12, board, mtfRequest);

		return mav;
	}

	// 자료실 자세히보기
	@RequestMapping(value = "/learningDataCXT", method = RequestMethod.POST)
	public ModelAndView learningDataCXT(@ModelAttribute BoardBean board)throws Exception{      


		mav = ltm.entrance(14, board);

		return mav;
	}

	// 공지사항 수정 완료
	@RequestMapping(value = "/NoticeUpdate", method = RequestMethod.POST)
	public ModelAndView tclearningNoticeUpdateOk(@ModelAttribute BoardBean board,MultipartHttpServletRequest mtfRequest)throws Exception{      

		System.out.println("공지사항 수정 컨트롤러 :" + board.getBoardTitle());
		ltm.entrance(30, board, mtfRequest);

		mav = ltm.entrance(3, board);

		return mav;
	}

	// 공지사항 삭제
	@RequestMapping(value = "/NoticeDelete", method = RequestMethod.POST)
	public ModelAndView tclearningNoticeDelete(@ModelAttribute BoardBean board){      

		System.out.println("공지사항 삭제 컨트롤러 :" + board.getRoomCode());
		ltm.entrance(31, board);

		mav = ltm.entrance(3, board);

		return mav;
	}

	// 자료실 삭제
	@RequestMapping(value = "/learningDataDelete", method = RequestMethod.POST)
	public ModelAndView learningDataDelete(@ModelAttribute BoardBean board){      

		ltm.entrance(15, board);

		mav = ltm.entrance(13, board);

		return mav;
	}

	// 자료실 업데이트
	@RequestMapping(value = "/learningDataUpdate", method = RequestMethod.POST)
	public ModelAndView learningDataUpdate(@ModelAttribute BoardBean board){     

		ltm.entrance(16, board);

		mav = ltm.entrance(13, board); // 다시 메뉴로 가기

		return mav;
	}

	// 자료실 자세히보기
	@RequestMapping(value = "/learningDataCXTStudent", method = RequestMethod.POST)
	public ModelAndView learningDataCXTStudent(@ModelAttribute BoardBean board)throws Exception{      


		mav = ltm.entrance(6, board);

		return mav;
	}


	// 선생님 토론게시판 내용수정 페이지
	@RequestMapping(value = "/DebateUpdatePage", method = RequestMethod.POST)
	public ModelAndView tclearningDebateUpdatePage(@ModelAttribute BoardBean board) {

		mav = ltm.entrance(34, board);

		return mav;
	}

	// 학생 질문게시판 글쓰기
	@RequestMapping(value = "/learningQuestionInsert", method = RequestMethod.POST)
	public ModelAndView learningQuestionInsert(@ModelAttribute BoardBean board,MultipartHttpServletRequest mtfRequest)throws Exception{      

		board.setId((String)session.getAttribute("id"));

		mav = stm.entrance(5, board, mtfRequest);

		return mav;
	}

	// 질문게시판 자세히보기
	@RequestMapping(value = "/questionBoardCXT", method = RequestMethod.POST)
	public ModelAndView questionBoardCXT(@ModelAttribute BoardBean board)throws Exception{      

		mav = stm.entrance(6, board);

		return mav;
	}

	//질문게시판 삭제
	@RequestMapping(value = "/learningQuestionDelete", method = RequestMethod.POST)
	public ModelAndView learningQuestionDelete(@ModelAttribute BoardBean board){      

		stm.entrance(12, board);

		mav = stm.entrance(4, board);

		return mav;
	}

	// 질문게시판 업데이트
	@RequestMapping(value = "/learningQuUpdate", method = RequestMethod.POST)
	public ModelAndView learningQuUpdate(@ModelAttribute BoardBean board){     
		stm.entrance(13, board);

		mav = stm.entrance(4, board); // 다시 메뉴로 가기

		return mav;
	}

	// 질문게시판 자세히보기
	@RequestMapping(value = "/learningDataCXTPage", method = RequestMethod.POST)
	public ModelAndView learningDataCXTPage(@ModelAttribute BoardBean board)throws Exception{      

		mav = ltm.entrance(2, board);

		return mav;
	}

	// 질문게시판 댓글 쓰기
	@RequestMapping(value = "/learningQuestionTag", method = RequestMethod.POST)
	public ModelAndView learningQuestionTag(@ModelAttribute BoardBean board)throws Exception{      

		ltm.entrance(1, board);

		mav = ltm.entrance(2, board);
		return mav;
	}

	// 질문게시판 댓글 삭제
	@RequestMapping(value = "/learningQuestionTagDelete", method = RequestMethod.POST)
	public ModelAndView learningQuestionTagDelete(@ModelAttribute BoardBean board)throws Exception{      

		ltm.entrance(24, board);

		mav = ltm.entrance(2, board);
		return mav;
	}
	// 선생님 학생 자세히보기
	@RequestMapping(value = "/teacherLearningSTadminCXT", method = RequestMethod.POST)
	public ModelAndView teacherLearningSTadminCXT(@ModelAttribute BoardBean board)throws Exception{      

		mav =ltm.entrance(26, board);

		mav.setViewName("teacherLearningSTadminCXT");
		return mav;
	}

	/** 자바 메일 발송 * @throws MessagingException * @throws AddressException **/
	@RequestMapping(value = "/mailSender", method=RequestMethod.POST) 
	public ModelAndView mailSender(@ModelAttribute BoardBean board) throws AddressException, MessagingException 
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
		String recipient =board.getEmail(); 
		//받는 사람의 메일주소를 입력해주세요. 
		String content = board.getBoardTitle(); 
		//메일 제목 입력해주세요.
		String context =board.getBoardContent(); 
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

		mav =ltm.entrance(25, board);
		return mav;
	}


	// 선생님 토론게시판 내용수정
	@RequestMapping(value = "/DebateUpdate", method = RequestMethod.POST)
	public ModelAndView tclearningDebateUpdate(@ModelAttribute BoardBean board) {

		System.out.println("토론게시판 내용수정 컨트롤러" + board.getBoardDate());
		ltm.entrance(35, board);

		mav = ltm.entrance(32, board);
		return mav;
	}

	// 선생님 토론게시판 삭제
	@RequestMapping(value = "/DebateDelete", method = RequestMethod.POST)
	public ModelAndView tclearningDebateDelete(@ModelAttribute BoardBean board) {

		ltm.entrance(38, board);

		mav = ltm.entrance(32, board);
		return mav;
	}







}
