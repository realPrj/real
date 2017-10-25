package icia.project.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.BoardBean;
import icia.project.bean.LearningRoomBean;
import icia.project.bean.MemberBean;
import icia.project.services.PageManagement;
import icia.project.services.ProjectUtils;
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
	
	// 자료실 메인페이지로 가기
	@RequestMapping(value = "/Datamain", method = RequestMethod.POST)
	public ModelAndView daoaMaid(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(13, board);
	
		
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
		case 3 : 	// 공지사항
			mav = ltmm.entrance(3, board);
			break;	
		case 4 :	// 질문게시판
			mav = ltmm.entrance(4, board);
			break;
		case 5 : 

			break;

		case 6 : 

			break;

		case 7 : 	// 오답노트
			mav = ltmm.entrance(7, null);
			break;

		case 8 : 	

			break;
		case 9 : 

			break;
		case 10 : 

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
	public ModelAndView stMenu(@ModelAttribute  BoardBean board) {

		int code = Integer.parseInt(board.getCaCode());
		
		switch(code) {
		case 1 : 

			break;
		case 2 : 

			break;
		case 3 : 
			// 공지사항

			break;	
		case 4 :	
			break;
			
		case 5 : 

			break;

		case 6 : 

			break;

		case 7 : // 오답노트

			break;

		case 8 : 	

			break;
			
		case 9 : 

			break;
			
		case 10 : 

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

		mav = pm.entrance(3, room);

		return mav;
	}
	
	// 선생님 오답노트 코멘트 페이지
	@RequestMapping(value = "/learningWANCXTPage", method = RequestMethod.GET)
	public ModelAndView learningWANCXTPage(@ModelAttribute BoardBean board) {

		mav = ltmm.entrance(17, board);

		return mav;
	}
	
	

	

}
