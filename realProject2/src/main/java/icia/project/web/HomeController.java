package icia.project.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import icia.project.bean.BoardBean;
import icia.project.bean.Calendar;
import icia.project.bean.DbBoardBean;
import icia.project.bean.LearningRoomBean;
import icia.project.bean.MemberBean;
import icia.project.services.PageManagement;
import icia.project.services.StudentManagement;
import icia.project.services.adminManagement;
import icia.project.services.learningStudentMM;
import icia.project.services.learningTeacherMM;
import icia.project.services.teacherManagement;


@Controller
public class HomeController  {

	@Autowired
	private PageManagement pm;
	@Autowired
	private learningTeacherMM ltmm;
	@Autowired
	private learningStudentMM lsmm;
	@Autowired
	private StudentManagement stm;
	@Autowired 
	private adminManagement ad;
	@Autowired
	private teacherManagement ttmm;
	private ModelAndView mav;

	///////////////////////////////////////// 페이지  /////////////////////////////////////////	

	// 홈(처음 시작)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {

		mav = new ModelAndView();

		mav.setViewName("home");

		return mav;
	}

	// 신분 선택(로그인 페이지 이동)
	@RequestMapping(value = "/IdentificationCheck", method = RequestMethod.POST)
	public ModelAndView identity(@ModelAttribute MemberBean member) {

		mav.addObject("identity", member.getIdentity());
		mav.setViewName("login");

		return mav;
	}

	// 회원가입페이지
	@RequestMapping(value = "/joinPage", method = RequestMethod.POST)
	public ModelAndView joinPage(@ModelAttribute MemberBean member) {

		switch(Integer.parseInt(member.getIdentity())) {
		case 0: //관리자
			break;
		case 1:	// 선생님
			mav.addObject("identity", member.getIdentity());
			mav.setViewName("teacherjoin");
			break;

		case 2:	// 학생
			mav.addObject("identity", member.getIdentity());
			mav.setViewName("studentjoin");
			break;

		default :
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}

		return mav;
	}

	// 아이디찾기 페이지
	@RequestMapping(value = "/idFindPage", method = RequestMethod.POST)
	public ModelAndView idFindPage(@ModelAttribute MemberBean member) {

		mav.addObject("identity", member.getIdentity());
		mav.setViewName("findId");

		return mav;
	}

	// 선생님 나의 정보 페이지
	@RequestMapping(value = "/teacherInfoPage", method = RequestMethod.POST)
	public ModelAndView teacherInfoPage() {

		mav = pm.entrance(5, null);

		return mav;
	}

	// 학생 나의 정보 페이지
	@RequestMapping(value = "/studentInfoPage", method = RequestMethod.POST)
	public ModelAndView studentInfoPage() {

		mav = pm.entrance(6, null);

		return mav;
	}

	// 선생님 나의 정보 수정 페이지
	@RequestMapping(value = "/teacherInfoUpdatePage", method = RequestMethod.POST)
	public ModelAndView teacherInfoUpdatePage(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		mav.addObject("id", member.getId());
		mav.addObject("name", member.getName());
		mav.addObject("email", member.getEmail());
		mav.addObject("phone", member.getPhone());
		mav.setViewName("teacherInfoUpdate");

		return mav;
	}

	// 학생 나의 정보 수정 페이지
	@RequestMapping(value = "/studentInfoUpdatePage", method = RequestMethod.POST)
	public ModelAndView studentInfoUpdatePage(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		mav.addObject("studentCode", member.getStudentCode());
		mav.addObject("name", member.getName());
		mav.addObject("email", member.getEmail());
		mav.addObject("phone", member.getPhone());
		mav.setViewName("studentInfoUpdate");

		return mav;
	}

	// 선생님 나의 정보 비밀번호 수정 페이지
	@RequestMapping(value = "/teacherInfoPWDUpdatePage", method = RequestMethod.POST)
	public ModelAndView teacherInfoPWDUpdatePage(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		mav.addObject("id", member.getId());
		mav.setViewName("teacherInfoPWDUpdate");

		return mav;
	}

	// 학생 나의 정보 비밀번호 수정 페이지
	@RequestMapping(value = "/studentInfoPWDUpdatePage", method = RequestMethod.POST)
	public ModelAndView studentInfoPWDUpdatePage(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		mav.setViewName("studentInfoPWDUpdate");

		return mav;
	}



	// 자료실 글쓰러가기
	@RequestMapping(value = "/DataInsert", method = RequestMethod.POST)
	public ModelAndView dataInsert(@ModelAttribute BoardBean board) {

		mav = new ModelAndView();

		mav.setViewName("learningDataInsert");

		return mav;
	} 

	// 선생님 학습메뉴
	@RequestMapping(value = "/tcmenu", method = RequestMethod.POST)
	public ModelAndView tcMenu(@ModelAttribute BoardBean board, MemberBean member) {
		int code = Integer.parseInt(board.getCaCode());

		switch(code) {
		case 1 : 

			break;
		case 2 : 

			break;
		case 3 : 
			// 공지사항
			mav = ltmm.entrance(3, board);
			break;	
		case 4 :	
			// 질문게시판
			mav = ltmm.entrance(4, board);
			break;
		case 5 : 
			// 토론게시판
			mav = ltmm.entrance(32, board);
			break;

		case 6 : 
			// 과제
			mav = ltmm.entrance(27, board);
			break;

		case 7 : // 오답노트
			mav = ltmm.entrance(7, null);
			break;

		case 8 : 	

			break;
		case 9 : // 학생관리
			mav = ltmm.entrance(25, board);
			break;
		case 10 : // 자료실
			mav = ltmm.entrance(13, board);
			break;

		case 11 : // 쪽지 페이지
			mav = ltmm.entrance(42, board);
			break;

		case 12 : // 강의 계획서
			mav = ltmm.entrance(52, board);
			break;

		case 13 : // 과목코드
			mav = pm.entrance(7, board);
			break;

		case 14 :  // 로그아웃
			mav = ttmm.entrance(5, member);
			break;
		case 15 : // 마이페이지
			mav = pm.entrance(1, null);
			break;
		}

		return mav;
	}

	// 학생 학습메뉴
	@RequestMapping(value = "/stmenu", method = RequestMethod.POST)
	public ModelAndView stMenu(@ModelAttribute  BoardBean board, MemberBean member,LearningRoomBean room) {
		System.out.println("asdf");
		int code = Integer.parseInt(board.getCaCode());

		switch(code) {
		case 1 : 
			mav.setViewName("studentLearningMain");
			break;
		case 2 : 

			break;
		case 3 : 
			// 공지사항
			mav = lsmm.entrance(3, board);
			break;	
		case 4 :	// 질문사항
			mav = lsmm.entrance(4, board);
			break;

		case 5 : 
			// 토론게시판
			mav = lsmm.entrance(32, board);
			break;

		case 6 :  //과제
			mav = lsmm.entrance(14, board);
			break;

		case 7 : // 오답노트
			mav = lsmm.entrance(7, board);
			break;

		case 8 : 	

			break;

		case 9 : // 수강생
			mav = lsmm.entrance(9, board);
			break;

		case 10 :  // 자료실
			mav = ltmm.entrance(5, board);
			break;

		case 11 : // 쪽지 페이지
			mav = lsmm.entrance(36, board);
			break;

		case 12 : 

			break;

		case 13 : // 과목코드표
			mav = pm.entrance(8, board);
			break;

		case 14 : //로그아웃
			mav = stm.entrance(5, member);
			break;
		case 15 : //메인페이지
			mav = pm.entrance(2, null);
			break;
		}

		return mav;
	}


	// 선생님 학습개설 페이지
	@RequestMapping(value = "/learningOpenPage", method = RequestMethod.POST)
	public String learningOpenPage(@ModelAttribute MemberBean member) {

		return "teacherLearningInsert";
	}

	// 학생 학습참여 페이지
	@RequestMapping(value = "/learningJoinPage", method = RequestMethod.POST)
	public String learningJoinPage(@ModelAttribute MemberBean member) {

		return "studentLearningJoin";
	}

	// 선생님 학습방 메인 페이지
	@RequestMapping(value = "/teacherLearningMainPage", method = RequestMethod.POST)
	public ModelAndView teacherLearningMainPage(@ModelAttribute LearningRoomBean room) {

		mav = pm.entrance(3, room);

		return mav;
	}

	// 학생 학습방 메인 페이지
	@RequestMapping(value = "/studentLearningMainPage", method = RequestMethod.POST)
	public ModelAndView studentLearningMainPage(@ModelAttribute LearningRoomBean room) {

		mav = pm.entrance(4, room);

		return mav;
	}

	// 선생님 공지사항 내용 확인
	@RequestMapping(value = "/tcNoticeConfirm", method = RequestMethod.POST)
	public ModelAndView tclearningNoticeCTX(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(8, board);

		return mav;
	}

	// 학생 공지사항 내용 확인
	@RequestMapping(value = "/stNoticeConfirm", method = RequestMethod.POST)
	public ModelAndView stlearningNoticeCTX(@ModelAttribute BoardBean board) {

		mav = lsmm.entrance(8, board);

		return mav;
	}

	// 선생님 오답노트 코멘트 페이지
	@RequestMapping(value = "/learningWANCXTPage", method = RequestMethod.GET)
	public ModelAndView learningWANCXTPage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(17, board);
		return mav;
	}

	// 학생 오답노트 코멘트 페이지
	@RequestMapping(value = "/learningWANCMCXTPage", method = RequestMethod.GET)
	public ModelAndView learningWANCMCXTPage(@ModelAttribute BoardBean board) {

		mav = lsmm.entrance(17, board);
		return mav;
	}

	// 선생님 오답노트 코멘트 등록 페이지
	@RequestMapping(value = "/learningWANInsertPage", method = RequestMethod.POST)
	public ModelAndView learningWANInsertPage(@ModelAttribute BoardBean board) {

		mav = new ModelAndView();
		mav.addObject("boardCode", board.getBoardCode());
		mav.setViewName("learningWANCMInsert");

		return mav;
	}

	// 선생님 오답노트 코멘트 수정 페이지
	@RequestMapping(value = "/learningWANCMUpdatePage", method = RequestMethod.POST)
	public ModelAndView learningWANCMUpdatePage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(19, board);
		return mav;
	}

	// 공지사항 글쓰기
	@RequestMapping(value = "/NoticeInsert", method = RequestMethod.POST)
	public ModelAndView learningNoticeInsert(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(9, board);

		return mav;
	}

	// 공지사항 수정 페이지
	@RequestMapping(value = "/NoticeUpdatePage", method = RequestMethod.POST)
	public ModelAndView learningNoticeUpdatePage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(11, board);

		return mav;
	}
	// 자료실 수정 페이지
	@RequestMapping(value = "/learningDataUpdatePage", method = RequestMethod.POST)
	public ModelAndView learningDataUpdatePage(@ModelAttribute BoardBean board) {

		mav.addObject("boardTitle",board.getBoardTitle());
		mav.addObject("boardDate",board.getBoardDate());
		mav.addObject("boardId",board.getBoardId());
		mav.addObject("boardContent",board.getBoardContent());
		mav.addObject("roomCode",board.getRoomCode());

		mav.setViewName("learningDataUpdate");

		return mav;
	}

	// 다운로드 메소드
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download(@RequestParam("name") String file){

		ModelAndView mav = new ModelAndView();
		//파라미터를 이용하여 file객체 생성
		File f = new File("E:\\realTest\\realProject2\\src\\main\\webapp\\WEB-INF\\uploadFiles\\Notice\\"+file);
		//file 객체를 저장
		mav.addObject("download",f);
		//출력할 뷰이름 설정
		mav.setViewName("download");
		return mav;

	}

	// 선생님 학생별 정보 보기
	@RequestMapping(value = "/learningWANSTInformationPage", method = RequestMethod.POST)
	public ModelAndView learningWANSTInformationPage(){

		mav = ltmm.entrance(22, null);

		return mav;

	}

	// 선생님 학생별 자세히 보기
	@RequestMapping(value="/learningSTInformation", method = RequestMethod.POST)
	private ModelAndView learningSTInformation(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(23, board);

		return mav;
	}

	// 질문게시판 글 페이지 보내기
	@RequestMapping(value = "/questionInsert", method = RequestMethod.POST)
	public ModelAndView questionInsertPage(@ModelAttribute BoardBean board) {
		mav = new ModelAndView();
		mav.setViewName("learningQuestionInsert");

		return mav;
	} 

	// 질문게시판 글 수정 하기
	@RequestMapping(value = "/learningQuestionUpdatePage", method = RequestMethod.POST)
	public ModelAndView learningQuestionUpdatePage(@ModelAttribute BoardBean board) {


		mav.addObject("boardTitle",board.getBoardTitle());
		mav.addObject("boardDate",board.getBoardDate());

		mav.addObject("boardId",board.getBoardId());
		mav.addObject("boardContent",board.getBoardContent());
		mav.addObject("roomCode",board.getRoomCode());

		mav.setViewName("learningQuestionUpdate");

		return mav;
	}

	// 선생님 토론게시판 내용확인
	@RequestMapping(value = "/tcDebateConfirm", method = RequestMethod.POST)
	public ModelAndView tclearningDebateCTX(@ModelAttribute BoardBean board) {

		System.out.println("토론게시판 내용확인 컨트롤러" + board.getBoardDate());
		mav = ltmm.entrance(33, board);

		return mav;
	}

	// 선생님 토론게시판 글등록 페이지
	@RequestMapping(value = "/DebateInsert", method = RequestMethod.POST)
	public ModelAndView tclearningDebateInsert(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(37, board);

		return mav;
	}

	// 학생 토론게시판 내용확인
	@RequestMapping(value = "/stDebateConfirm", method = RequestMethod.POST)
	public ModelAndView stlearningDebateCTX(@ModelAttribute BoardBean board) {

		mav = lsmm.entrance(33, board);

		return mav;
	}

	// 이메일 보내기
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ModelAndView sendMail(@ModelAttribute BoardBean board) {

		mav.addObject("email",board.getEmail());


		mav.setViewName("teacherSendMail");

		return mav;
	}

	// 비밀번호찾기 페이지
	@RequestMapping(value = "/idPwdPage", method = RequestMethod.POST)
	public ModelAndView idPwdPage(@ModelAttribute MemberBean member) {

		mav.addObject("identity", member.getIdentity());
		mav.setViewName("findPWD");

		return mav;
	}

	// 과제 페이지
	@RequestMapping(value = "/learningTaskPage", method = RequestMethod.POST)
	public ModelAndView learningTaskPage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(27, board);

		return mav;
	}

	// 학생과제 페이지
	@RequestMapping(value = "/learningTaskCXTStudent", method = RequestMethod.POST)
	public ModelAndView learningTaskCXTStudent(@ModelAttribute BoardBean board) {

		mav = lsmm.entrance(14, board);

		return mav;
	}

	// 과제 등록 페이지
	@RequestMapping(value = "/learningTaskInsertPage", method = RequestMethod.POST)
	public ModelAndView learningTaskInsertPage(@ModelAttribute BoardBean board) {

		if(Integer.parseInt(board.getIdentity()) == 1) {	// 선생님

			mav = new ModelAndView();
			mav.setViewName("learningTaskInsert");

		}else {	// 학생

			mav = lsmm.entrance(1, board);
			mav.addObject("message", "alert('선생님만 과제 생성을 할 수 있습니다.')");
		}

		return mav;
	}


	// 과제 글 수정 페이지
	@RequestMapping(value = "/learningTaskUpdatePage", method = RequestMethod.POST)
	public ModelAndView learningTaskUpdatePage(@ModelAttribute BoardBean board) {

		mav = new ModelAndView();
		System.out.println( board.getBoardCode()+"asdf");

		mav.addObject("boardCode", board.getBoardCode());
		mav.addObject("roomCode", board.getRoomCode());
		mav.addObject("boardTitle", board.getBoardTitle());
		mav.addObject("boardContent", board.getBoardContent());

		mav.setViewName("learningTaskUpdate");

		return mav;
	}

	// 채팅(알림)
	@RequestMapping(value = "/testChat", method = RequestMethod.GET)
	public ModelAndView testChat() {

		mav = ltmm.entrance(40, null);

		return mav;
	}

	// 선생님 과제 인설트 팝업창
	@RequestMapping(value = "/learningSubjectMMinsert", method = RequestMethod.GET)
	public ModelAndView learningSubjectMMinsert(@ModelAttribute BoardBean board) {
		mav = new ModelAndView();
		mav.addObject("boardCode",board.getBoardCode());
		mav.addObject("roomCode",board.getRoomCode());

		mav.addObject("title", board.getBoardTitle());
		mav.setViewName("learningTaskSubmitInsert");
		return mav;
	}

	// 학생 회원탈퇴 페이지 이동
	@RequestMapping(value = "/WithdrawalPage", method = RequestMethod.POST)
	public ModelAndView WithdrawalPage(@ModelAttribute BoardBean board) {

		mav.setViewName("studentWithdrawalPage");

		return mav;
	}

	// 선생님 과제 파일 확인 팝업창
	@RequestMapping(value = "/learningTesk", method = RequestMethod.GET)
	public ModelAndView learningTesk(@ModelAttribute BoardBean board) {
		System.out.println("선생님 과제 파일 확인 팝업창");
		mav = ltmm.entrance(39, board);


		mav.setViewName("studentWithdrawalPage");

		return mav;
	}
	// 학생 회원탈퇴 페이지 이동
	@RequestMapping(value = "/WithdrawalTeacherPage", method = RequestMethod.POST)
	public ModelAndView WithdrawalTeacherPage(@ModelAttribute BoardBean board) {

		mav.setViewName("teacherWithdrawalPage");

		return mav;
	}

	/*// 쪽지보내기 팝업
	@RequestMapping(value = "/sendMessagePopUp", method = RequestMethod.POST)
	public ModelAndView learningSendMessagePopUp(@ModelAttribute BoardBean board) {
		System.out.println("쪽지보내기 팝업 컨트롤러");
		mav = ltmm.entrance(39, board);

		return mav;
	}*/

	// 학생 과제 파일 확인 팝업창
	@RequestMapping(value = "/checkFile", method = RequestMethod.GET)
	public ModelAndView checkFile(@ModelAttribute BoardBean board) {

		mav = lsmm.entrance(16, board);

		return mav;
	}

	// 쪽지 페이지
	@RequestMapping(value = "/Message", method = RequestMethod.POST)
	public ModelAndView message(@ModelAttribute BoardBean board) {
		int code = Integer.parseInt(board.getCaCode());
		System.out.println("컨트롤러 신분코드 : " + board.getIdentity());
		switch(code) {

		case 2: // 받은쪽지 리스트 페이지
			System.out.println("일로오냐.");
			if(board.getIdentity().equals("1")) {
				mav = ltmm.entrance(42, board);
				break;
			}else {
				mav = lsmm.entrance(36, board);
				break;
			}


		case 3: // 보낸쪽지 리스트 페이지
			if(board.getIdentity().equals("1")) {
				mav = ltmm.entrance(43, board);
				break;
			}else{
				mav = lsmm.entrance(38, board);
				break;
			}


		}

		return mav;
	}

	// 쪽지 보내기 페이지
	@RequestMapping(value = "/sendMessagePage", method = RequestMethod.POST)
	public ModelAndView sendMessagePage(@ModelAttribute BoardBean board) {
		System.out.println(board.getMessageOther());
		if(board.getIdentity().equals("1")) {
			mav = ltmm.entrance(46, board);
		}else {
			
			mav = lsmm.entrance(39, board);
		}


		return mav;
	}

	// 보낸쪽지 내용확인 페이지
	@RequestMapping(value = "/sentMessageCTX", method = RequestMethod.POST)
	public ModelAndView sentMessageCTX(@ModelAttribute BoardBean board) {

		if(board.getIdentity().equals("1")) {
			mav = ltmm.entrance(48, board);
		}else {
			mav = lsmm.entrance(41, board);
		}

		return mav;
	}

	// 받은쪽지 내용확인 페이지
	@RequestMapping(value = "/getMessageCTX", method = RequestMethod.POST)
	public ModelAndView getMessageCTX(@ModelAttribute BoardBean board) {

		if(board.getIdentity().equals("1")) {
			mav = ltmm.entrance(49, board);
		}else{
			mav = lsmm.entrance(37, board);
		}

		return mav;
	}

	// 문제 번호 보여주기
	@RequestMapping(value = "/subjectCCT1", method = RequestMethod.POST,produces = "application/text; charset=utf8")
	@ResponseBody public String subjectCCT1(@ModelAttribute BoardBean board) {

		ArrayList<BoardBean> code = null;

		// 문제번호
		code = pm.subjectCCT1(board);		

		Gson gson = new Gson();

		String test = gson.toJson(code);
	
		return test;
	}
	
	// 강의계획서(달력)
	@RequestMapping(value = "/calendar", method = RequestMethod.POST,produces = "application/text; charset=utf8")
	@ResponseBody public String calendar(@ModelAttribute Calendar cd) {
		ArrayList<Calendar> code = null;

		code = pm.calendar(cd);	
		
		Gson gson = new Gson();
		
		String test = gson.toJson(code);
		
		System.out.println(test);
		
		return test;
	}
	
	// 강의계획서 자세히 보기
	@RequestMapping(value = "/learningPlanCTXPage", method = RequestMethod.GET)
	public ModelAndView learningPlanCTXPage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(53, board);
		return mav;
		}

	// 해당 학생 자세히 보기
	@RequestMapping(value = "/studentCXT", method = RequestMethod.GET)
	public ModelAndView studentCXT(@ModelAttribute MemberBean member) {
		mav =ad.entrance(2, member);
		mav.setViewName("adminstudentCXT");
		return mav;
	}
	// 해당 선생님 자세히보기
	@RequestMapping(value = "/teacherCxt", method = RequestMethod.GET)
	public ModelAndView teacherCxt(@ModelAttribute MemberBean member) {
		mav =ad.entrance(3, member);
		mav.setViewName("adminTeacherCXT");
		return mav;
	}
	// 해당 메일보내기 
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ModelAndView email(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();
		mav.addObject("email",member.getEmail());
		mav.setViewName("adminEmail");
		return mav;
	}


	// 강의계획서 등록 페이지
	@RequestMapping(value = "/learningPlanInsertPage", method = RequestMethod.GET)
	public ModelAndView learningPlanInsertPage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(54, board);

		return mav;
	}
	
}
