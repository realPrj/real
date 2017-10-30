package icia.project.web;

import java.io.File;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.BoardBean;
import icia.project.bean.DbBoardBean;
import icia.project.bean.LearningRoomBean;
import icia.project.bean.MemberBean;
import icia.project.services.PageManagement;
import icia.project.services.learningStudentMM;
import icia.project.services.learningTeacherMM;


@Controller
public class HomeController  {

	@Autowired
	private PageManagement pm;
	@Autowired
	private learningTeacherMM ltmm;
	@Autowired
	private learningStudentMM lsmm;

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

		mav.addObject("id", member.getId());
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
	public ModelAndView tcMenu(@ModelAttribute BoardBean board) {

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

		case 11 : 

			break;

		case 12 : 

			break;

		case 13 : 

			break;

		case 14 : 

			break;
		}

		return mav;
	}

	// 학생 학습메뉴
	@RequestMapping(value = "/stmenu", method = RequestMethod.POST)
	public ModelAndView stMenu(@ModelAttribute  BoardBean board, MemberBean member,LearningRoomBean room) {

		int code = Integer.parseInt(board.getCaCode());

		switch(code) {
		case 1 : 

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

		case 6 : 

			break;

		case 7 : // 오답노트
			mav = lsmm.entrance(7, board);
			break;

		case 8 : 	

			break;

		case 9 : 

			break;

		case 10 :  // 자료실
			mav = ltmm.entrance(5, board);
			break;

		case 11 : 

			break;

		case 12 : 

			break;

		case 13 : 

			break;

		case 14 : 

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



}
